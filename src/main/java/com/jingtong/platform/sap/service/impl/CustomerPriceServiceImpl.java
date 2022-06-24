package com.jingtong.platform.sap.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.sap.dao.ChannelPriceDao;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.ChannelPrice;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Material;
import com.jingtong.platform.sap.service.CustomerPriceService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class CustomerPriceServiceImpl extends BasicService implements CustomerPriceService {
    private JCoDestination dest;
    private ChannelPriceDao channelPriceDao;
    private ILogInfoDao logInfoDao;

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public ChannelPriceDao getChannelPriceDao() {
        return channelPriceDao;
    }

    public void setChannelPriceDao(ChannelPriceDao channelPriceDao) {
        this.channelPriceDao = channelPriceDao;
    }

    @Override
    public String getCustomerPriceFromSAP() {
        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");
            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("Z_RFC_GET_MATERIAL_PRICE_CUS");// url
            if (function == null) {
                throw new Exception("����SAPʧ��!");
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// �������ڸ�ʽ
            String date = (df.format(new Date()).toString()).replace("-", "");// new Date()Ϊ��ȡ��ǰϵͳʱ��
            function.getImportParameterList().setValue("IP_DATAB", date);
            function.execute(dest);
            // ��ȡ�ӿڽ�������
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_PRICE");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {
                int i = 0;
                boolean errorFlag = false;
                for (; i < rows; i++) {
                    resultTbl.setRow(i);
                    ChannelPrice material = new ChannelPrice();
                    material.setVkorg(resultTbl.getString("VKORG"));
                    material.setVtweg(resultTbl.getString("VTWEG"));
                    material.setMatnr((Long.parseLong(resultTbl.getString("MATNR")) + "").trim());
                    material.setDatab(PropUtil.getStringToDate(resultTbl.getString("DATAB")));
                    material.setDatbi(PropUtil.getStringToDate(resultTbl.getString("DATBI")));
                    material.setKonwa(resultTbl.getString("KONWA"));
                    material.setKpein(resultTbl.getFloat("KPEIN"));
                    material.setKfrst(resultTbl.getString("KFRST"));
                    if ("KAR".equals(resultTbl.getString("KMEIN"))) {// ��kar��תΪ������λ
                        Material mater = this.channelPriceDao.getMaterial(material.getMatnr());
                        if (mater == null) {
                            material.setKbetr(resultTbl.getFloat("KBETR"));
                            material.setKmein(resultTbl.getString("KMEIN"));
                        } else {
                            material.setKmein("ST");
                        }
                    } else {
                        material.setKbetr(resultTbl.getFloat("KBETR"));
                        material.setKmein(resultTbl.getString("KMEIN"));
                    }
                    String kunnr = resultTbl.getString("KUNNR") + "";
                    if (kunnr != null && kunnr.trim().length() > 0) {
                        material.setKunnr((Long.parseLong(kunnr)) + "");
                    } else {
                        material.setKunnr(resultTbl.getString("KUNNR"));
                    }
                    material.setMaktx(resultTbl.getString("MAKTX"));
                    material.setChannelPriceType("1");
                    int count = this.channelPriceDao.getChannelPriceCount(material);
                    try {
                        if (count == 0) {
                            this.channelPriceDao.creatChannelPrice(material);
                        } else if (count == 1) {
                            this.channelPriceDao.updateChannelPrice(material);
                        } else {
                        }
                    } catch (Exception e) {
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("��Ʒ�ͻ��۸�/Z_RFC_GET_MATERIAL_PRICE_CUS");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("ʧ�ܣ�");
                        log.setLogInfo(e.getMessage());
                        errorFlag = true;
                        this.logInfoDao.addLogInfo(log);
                        continue;
                    }
                }
                if (i == rows && (!errorFlag)) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("��Ʒ�ͻ��۸�/Z_RFC_GET_MATERIAL_PRICE_CUS");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("�ɹ���");
                    log.setLogInfo("�ɹ�");
                    this.logInfoDao.addLogInfo(log);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        CustomerPriceServiceImpl dap = new CustomerPriceServiceImpl();
        dap.getCustomerPriceFromSAP();

    }
}
