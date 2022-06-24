package com.jingtong.platform.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.webservice.dao.IWebDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.webservice.service.IClient;

/**
 * ���ýӿڿͻ���
 * 
 * @author XIA_QX
 *
 */
public class IClientImpl implements IClient {

    public static Properties env;
    private String smtpServer = "smtp.exmail.qq.com";// =env.getProperty("allUser.smtpServer");
    private String from = "wei.yao@jing-tong.com";// =env.getProperty("allUser.from");
    private String displayName = "PortalSystem";// =env.getProperty("allUser.displayName");
    private String emailaddress = "wei.yao@jing-tong.com";// =env.getProperty("allUser.emailaddress");
    private String emailpassword = "jing-tong.com";// =env.getProperty("allUser.emailpassword");

    /*
     * ���䷢�ͷ�������mail.yytex.net �˺��ǣ�yysrm@yytex.net �����ǣ�yuyuejiafangsrm123456
     * 
     */

    public IWebDao iWebDao;
    public TransactionTemplate transactionTemplate;
    public IAllUserService allUserService;

    public IWebDao getiWebDao() {
        return iWebDao;
    }

    public void setiWebDao(IWebDao iWebDao) {
        this.iWebDao = iWebDao;
    }

    public IAllUserService getAllUserService() {
        return allUserService;
    }

    public void setAllUserService(IAllUserService allUserService) {
        this.allUserService = allUserService;
    }

    /**
     * ����webservice�����
     * 
     * @param wsdlUrl
     * @param packageName
     * @param method
     * @param vCode
     * @return
     * @throws Exception
     */
    private Object[] executeClient(String wsdlUrl, String packageName, String method, String vCode) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        org.apache.cxf.endpoint.Client client = dcf.createClient(wsdlUrl);
        QName name = new QName(packageName, method);
        Object[] objects = client.invoke(name, vCode);

        if ("".equals(objects) || null == objects || objects.length <= 0) {
            // throw new Exception("���ýӿڷ�������");
            objects[0] = "���ýӿڷ���ʧ��";
        }

        // ����web Service//������ý��
        // System.out.println(objects[0].toString());
        return objects;
    }

    @Override
    public void executeClientEmailExpireDR() {

        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientEmailExpireDR");
        log.setInterfaceName("executeClientEmailExpireDR");
        iWebDao.insertJobLog(log);
        
        // 1.��ѯ����Ҫ�ʼ�֪ͨ��DR����Ŀ������Ч��30��ģ�
        List<DesignRegDetail> drdList = new ArrayList<DesignRegDetail>();
        drdList = iWebDao.getEmailExpireDR();

        // 2.����DR��ͷ���򴴽��˷����ʼ�
        for (DesignRegDetail drd : drdList) {
            // String content = "<br>&nbsp;&nbsp;The material "+drd.getMaterial_name()+" you
            // submit in DR "+drd.getDrNum()+" expires in "+ drd.getEnd_dateStr() +", please
            // login platform to view";
            String content = "<br>&nbsp;&nbsp;Your DR " + drd.getDrNum() + " will be expired on " + drd.getEnd_dateStr()
                    + ". if this project is in progress,please update it accordingly.";
            CustomerUser cusUser = new CustomerUser();
            cusUser = iWebDao.getUsersByUserId(drd.getCreate_userId());
            if (cusUser != null) {
                String contents = "Dear user" + content + "<br>";
                Message m = new Message();
                m.setContent(contents);
                m.setType("Remind DRExpire");
                m.setSendNumber(cusUser.getEmail());
                iWebDao.saveMessage(m);
                // sendMailByAddree(cusUser.getEmail(),contents);
            }
        }
        log.setLogInfo("END : executeClientEmailExpireDR");
        log.setInterfaceName("executeClientEmailExpireDR");
        iWebDao.insertJobLog(log);
    }

    @Override
    public void executeClientEmailExpireQuote() {
        
        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientEmailExpireQuote");
        log.setInterfaceName("executeClientEmailExpireQuote");
        iWebDao.insertJobLog(log);

        // 1.��ѯ����Ҫ�ʼ�֪ͨ��DR����Ŀ������Ч��30��ģ�
        List<QuoteDetail> qdList = new ArrayList<QuoteDetail>();
        qdList = iWebDao.getEmailExpireQuote();

        // 2.����DR��ͷ���򴴽��˷����ʼ�
        for (QuoteDetail qd : qdList) {
            String content = "";
            String expireDate = "";
            if (qd.getIsAgree() == 0) {
                expireDate = qd.getLatest_timeStr();
            } else {
                continue;
            }
            content = "<br>&nbsp;&nbsp;Please be reminded to trigger debit for Part nbr " + qd.getMaterial_name()
                    + " in Quote nbr " + qd.getQuote_id() + " on time.  Otherwise, the approved Quote nbr "
                    + qd.getQuote_id() + " will be expired and closed on " + expireDate;

            CustomerUser cusUser = new CustomerUser();
            cusUser = iWebDao.getUsersByUserId(qd.getCreate_userId());
            if (cusUser != null) {
                String contents = "Dear user" + content + "<br>";
                Message m = new Message();
                m.setContent(contents);
                m.setType("Remind QuoteExpire");
                m.setSendNumber(cusUser.getEmail());
                iWebDao.saveMessage(m);
            }
        }
        log.setLogInfo("END : executeClientEmailExpireQuote");
        log.setInterfaceName("executeClientEmailExpireQuote");
        iWebDao.insertJobLog(log);
    }

    /**
     * ��ʱ�����ʼ�������ʼ�
     */
    @Override
    public void executeClientSendEmail() {
        ExceptionLog log = new ExceptionLog();
        log.setLogInfo("START : executeClientSendEmail");
        log.setInterfaceName("executeClientSendEmail");
        iWebDao.insertJobLog(log);
        // 1.��ѯ
        List<Message> mList = new ArrayList<Message>();
        Message message = new Message();
        mList = iWebDao.searchMessage(message);

        //
        for (Message m : mList) {
            // �������ʼ�
            String result = sendMailByAddree(m.getSendNumber(), m.getContent());
            if ("Success".equals(result)) {
                m.setState("1");
                iWebDao.updateMessage(m);
            } else {
                m.setState("0");
                m.setResultMessage("Error!");
                iWebDao.updateMessage(m);
            }
            // �ѷ��ͱ��
        }
        
        log.setLogInfo("END : executeClientSendEmail");
        log.setInterfaceName("executeClientSendEmail");
        iWebDao.insertJobLog(log);
    }

    public static void main(String[] arg) {
        System.out.println("111111111111");
        IClientImpl a = new IClientImpl();
        a.sendMailByAddree("33787495@qq.com", "11111111111111111�ߵ�");
    }

    /**
     * �������䷢���ʼ�
     * 
     * @return
     */
    public String sendMailByAddree(String emailAddress, String content) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
//			MailService mail = new MailService("smtp.office365.com", "service@ween-semi.com",
//					"PortalSystem", "service@ween-semi.com", "Ls^8!vD6Xa",
//					emailAddress, "WeEn Portal System Notifications", content);// �ʼ�����
            MailService mail = new MailService("10.200.9.22", "servicetest@ween-semi.com",
                    "WeEn Portal System Notifications", emailAddress, " ", content);// �ʼ�����
            map = mail.send(); // �ʼ�����
            System.out.println(map);
        } catch (Exception e) {
            System.out.println("*************Exception!**************");
            return "Failed";
        }
        if (!"success".equals(map.get("state"))) {
            return "Failed";
        }
        if ("success".equals(map.get("state"))) {
            return "Success";
        } else {
            return "Failed";
        }
        //////////
    }

    public static Properties getEnv() {
        return env;
    }

    public static void setEnv(Properties env) {
        IClientImpl.env = env;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getEmailpassword() {
        return emailpassword;
    }

    public void setEmailpassword(String emailpassword) {
        this.emailpassword = emailpassword;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}
