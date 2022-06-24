package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.QuoteToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.QuoteDetail;
import com.jingtong.platform.sap.pojo.QuoteToSap;
import com.jingtong.platform.sap.service.QuoteToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class QuoteToSapServiceImpl extends BasicService implements QuoteToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private QuoteToSapDao quoteToSapDao;
    private TransactionTemplate transactionTemplate;

    public QuoteToSapDao getQuoteToSapDao() {
        return quoteToSapDao;
    }

    public void setQuoteToSapDao(QuoteToSapDao quoteToSapDao) {
        this.quoteToSapDao = quoteToSapDao;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public QuoteToSapDao getQuoteDao() {
        return quoteToSapDao;
    }

    public void setQuoteDao(QuoteToSapDao quoteToSapDao) {
        this.quoteToSapDao = quoteToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public String quoteToSap(QuoteToSap quote, List<QuoteDetail> quoteDetail, Quote q) {
        // ����sap�ӿڻ�ȡ���۵�
        String resultMessage = "";

        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");

            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("ZSDFU009");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("���۵�����SAP/ZSDFU009");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo("����SAPʧ��!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("����SAPʧ��!");
            }

            if (quote != null) {
                // ������Ϣ��ͷ��
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// ��ȡ�ṹ

                head.setValue("ID", quote.getId());
                head.setValue("QUOTE_ID", quote.getQuoteId());
                head.setValue("TYPE_ID", quote.getQuoteType());
                head.setValue("CUSGROUP_ID", quote.getCustomerGroup());
                head.setValue("CUSTOMER_ID", quote.getCustomer());
                head.setValue("ECGROUP_ID", quote.getEcGroup());
                head.setValue("EC_ID", quote.getEndCustomer());
                head.setValue("TOTAL_AMOUNT", quote.getTotalAmountv());
                head.setValue("CURRENCY_CODE", quote.getCurrency());
                head.setValue("PROJECT_NAME", quote.getProject());
                head.setValue("ISDELIVERY", quote.getAssembly());
                head.setValue("START_DATE", quote.getStartTime());
                head.setValue("LATEST_EXPIRE", quote.getEndTime());
                head.setValue("STATE", quote.getState());
                head.setValue("ENQUIRY_MAINID", quote.getEnquiryMainId());
                head.setValue("SYNC_STATE", quote.getSyncState());
                head.setValue("SYNC_TIME", quote.getSyncTime());
                head.setValue("SYNC_USERID", quote.getSyncUserId());
                head.setValue("SYSNC_EXCEPTION", quote.getSysncException());
                head.setValue("CREATE_TIME", quote.getCreateTime());
                head.setValue("CREATE_USERID", quote.getCreateUserId());
                head.setValue("LATEST_TIME", quote.getLatestTime());
                head.setValue("LATEST_USERID", quote.getLatestUserId());
                head.setValue("ORG_CODE", quote.getOrgCode());

            }
            if (quoteDetail != null) {
                // ������Ϣ����ϸ��
                JCoTable item = function.getTableParameterList().getTable("T_ABLE");// ��ȡ��ֵ
                for (QuoteDetail it : quoteDetail) {
                    item.appendRow(); // �������
                    item.setValue("ID", "11");
                    item.setValue("QUOTE_ID", it.getQuoteId());
                    item.setValue("ROW_NO", it.getRowNo());
                    item.setValue("MATERIAL_ID", it.getMaterialId());
                    item.setValue("DRNUM", it.getDrId());
                    item.setValue("QYT", it.getQty());
                    item.setValue("TARGET_RESALE", it.getTargetResale());
                    item.setValue("TARGET_COST", it.getTargetCost());

                    item.setValue("CUS_PROFITS_PERCENT", it.getMfrMargin());
                    item.setValue("AMOUNT", it.getAmount());
                    item.setValue("SUGGEST_RESALE", it.getSuggestResale());
                    item.setValue("SUGGEST_COST", it.getSuggestCost());
                    item.setValue("PROFITS_PERCENT", it.getCusProfitsPercent());
                    item.setValue("REASON", it.getJustification());
                    item.setValue("COMPETITOR", it.getCompetitor());
                    item.setValue("START_DATE", it.getStartOfProduction());
                    item.setValue("CUS_REMARK", it.getCusRemarks());
                    item.setValue("REMARK", it.getWeenRemarks());
                    item.setValue("STATE", it.getStatus());
                    item.setValue("MAIN_ID", it.getMainId());
                    item.setValue("ENQUIRY_DETAIL_ID", it.getEnquiryDetailId());
                    item.setValue("LATEST_TIME", it.getLatestTime());
                    item.setValue("LATEST_USERID", it.getLatestUserId());

                }
            }
            function.execute(dest);
            // ��ȡ�ӿڽ�������

            JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
            resultMessage = resultTbl.getString("ZMESG");
            quoteToSapDao.updateQuoteState(q);
            resultMessage = "Success!";

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("���۵�����SAP/ZSDFU009");
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
        return resultMessage + "<br/>";
    }

    @Override
    public List<QuoteDetail> getQuoteDetail(QuoteToSap quote) {
        try {
            return quoteToSapDao.getQuoteDetail(quote);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<QuoteToSap> getQuoteTotal(QuoteToSap quote) {
        try {
            return quoteToSapDao.getQuoteTotal(quote);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
