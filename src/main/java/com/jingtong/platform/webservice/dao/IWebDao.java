package com.jingtong.platform.webservice.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.message.pojo.Message;
import com.jingtong.platform.quote.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.ExceptionLog;

/**
 * �ӿ�ͬ��DAO��
 * 
 * @author XIA_QX
 *
 */
public interface IWebDao {
    /**
     * ����userid��ѯ�û���Ϣ����ѯ��Ӧid�����䣩
     * 
     * @param ids
     * @return
     */
    public CustomerUser getUsersByUserId(String ids);

    /**
     * ��ѯ�跢���ʼ���DR
     * 
     * @return
     */
    public List<DesignRegDetail> getEmailExpireDR();

    /**
     * ��ѯ�跢���ʼ���Quote
     * 
     * @return
     */
    public List<QuoteDetail> getEmailExpireQuote();

    /**
     * ��ѯ�ʼ�����ȡ��Ҫ���͵��ʼ�
     * 
     * @param c
     * @return
     */
    public List<Message> searchMessage(Message c);

    /**
     * �޸��ʼ�״̬Ϊ�ѷ���
     * 
     * @param c
     * @return
     */
    public int updateMessage(Message c);

    /**
     * ������ʼ�
     * 
     * @param g
     * @return
     */
    public long saveMessage(Message g);

    public long insertJobLog(ExceptionLog log);

}
