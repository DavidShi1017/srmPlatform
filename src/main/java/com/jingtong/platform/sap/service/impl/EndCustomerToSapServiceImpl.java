package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.EndCustomerToSapDao;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.EndCustomerToSap;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.service.EndCustomerToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class EndCustomerToSapServiceImpl extends BasicService implements EndCustomerToSapService {

    private JCoDestination dest;
    private EndCustomerToSapDao endCustomerToSapDao;
    private ILogInfoDao logInfoDao;

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public JCoDestination getDest() {
        return dest;
    }

    public void setDest(JCoDestination dest) {
        this.dest = dest;
    }

    public EndCustomerToSapDao getEndCustomerToSapDao() {
        return endCustomerToSapDao;
    }

    public void setEndCustomerToSapDao(EndCustomerToSapDao endCustomerToSapDao) {
        this.endCustomerToSapDao = endCustomerToSapDao;
    }

    @Override
    public String endCustomerToSap(EndCustomerToSap endCustomer, EndCustomer ec) {
        // ����sap�ӿڻ�ȡ�ն˿ͻ�
        String resultMessage = "";

        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");

            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("ZSDFU003");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("�ն˿ͻ�����SAP/ZSDFU003");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo("����SAPʧ��!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("����SAPʧ��!");
            }
            if (endCustomer != null) {
                // ������Ϣ
                JCoTable head = function.getTableParameterList().getTable("T_ABLE");// ��ȡ��ֵ
                head.appendRow();

                head.setValue("KUNNR", endCustomer.getDisti_customer_id());
                head.setValue("CNAME", endCustomer.getDisti_customer_name());
                head.setValue("EGCODE", endCustomer.getDisti_groupId());
                head.setValue("EGCNAM", endCustomer.getEndCustomerGroupName());
                head.setValue("ECODE", endCustomer.getEnd_customer_id());
                head.setValue("ECNAM", endCustomer.getEnd_customer_name());
                head.setValue("ETYPE", endCustomer.getCustomer_type());
                head.setValue("EPROG", endCustomer.getCustomer_property());
                head.setValue("WAERS", endCustomer.getCurrency_code());
                head.setValue("LAND1", endCustomer.getCountry());
                head.setValue("REGIO", endCustomer.getProvince());
                head.setValue("ADDRESS", endCustomer.getAddress());
                head.setValue("CONAM", endCustomer.getContact_name());
                head.setValue("TELNUM", endCustomer.getTel());
                head.setValue("ERNAM", endCustomer.getCreateUserId());
                head.setValue("ERDAT", endCustomer.getCreateTime());
                head.setValue("STATUS", endCustomer.getState());
            }

            function.execute(dest);
            // ��ȡ�ӿڽ�������
            JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
            resultTbl.getString("ZFLAG");
            resultMessage = resultTbl.getString("ZMESG");
            endCustomerToSapDao.updateEndCustomerState(ec);
            resultMessage = "Success!";

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("�ն˿ͻ�����SAP/T_ABLE");
            log.setOperateUser("SAP");
            log.setOperateTime(new Date());
            log.setLogDesc("ʧ�ܣ�");
            log.setLogInfo(e.getMessage());
            try {
                this.logInfoDao.addLogInfo(log);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return resultMessage + ";<br/>";
    }

    public static void main(String[] args) {

    }

    @Override
    public List<EndCustomerToSap> getEndCustomerTotal(EndCustomerToSap endCustomer) {
        try {
            return endCustomerToSapDao.getEndCustomerTotal(endCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
