package com.jingtong.platform.sap.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ExcludeDao;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.Exclude;
import com.jingtong.platform.sap.service.ExcludeService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class ExcludeServiceImpl extends BasicService implements ExcludeService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private ExcludeDao excludeDao;

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public ExcludeDao getExcludeDao() {
        return excludeDao;
    }

    public void setExcludeDao(ExcludeDao excludeDao) {
        this.excludeDao = excludeDao;
    }

    @Override
    public void getExcludeFromSap() {
        Exclude exc = new Exclude();
        System.out.println("-----��Ʒ�������������ݽӿ�----------");
        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");
            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("Z_RFC_GET_MATERIAL_EXCLUDE");
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("��Ʒ��������������/Z_RFC_GET_MATERIAL_EXCLUDE");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo("����SAPʧ��!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("����SAPʧ��!");
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (exc.getpCrdat().trim().length() > 0) {
                function.getImportParameterList().setValue("P_CRDAT",
                        (df.format(exc.getpCrdat()).toString()).replace("-", ""));
            } else {
                function.getImportParameterList().setValue("P_CRDAT",
                        (df.format(new Date()).toString()).replace("-", ""));
            }
            // ��ȡ�ӿڴ��������
            if (exc.getsMatnr().trim().length() > 0) {
                JCoTable item1 = function.getTableParameterList().getTable("S_MATNR");
                item1.appendRow();
                // ����
                item1.setValue("SIGN", "I");
                item1.setValue("OPTION", "EQ");// EQ
                item1.setValue("LOW", exc.getsMatnr());
            }
            function.execute(dest);
            // ��ȡ�ӿڽ�������
            JCoTable resultTbl = function.getTableParameterList().getTable("OT_KOTG");
            int rows = resultTbl.getNumRows();
            if (rows > 0) {

                this.excludeDao.getExcludeCount();
                int i = 0;
                for (; i < rows; i++) {
                    try {
                        resultTbl.setRow(i);
                        Exclude excu = new Exclude();
                        String kunnr = resultTbl.getString("KUNNR");
                        try {
                            kunnr = Long.parseLong(kunnr) + "";
                        } catch (Exception e) {
                            kunnr = resultTbl.getString("KUNNR");
                        }
                        excu.setKunnr(kunnr);
                        String matnr = resultTbl.getString("MATNR");
                        try {
                            matnr = Long.parseLong(matnr) + "";
                        } catch (Exception e) {
                            matnr = resultTbl.getString("MATNR");
                        }
                        excu.setMatnr(matnr);
                        excu.setCreateUser(exc.getCreateUser());
                        excu.setModifyUser(exc.getModifyUser());
                        this.excludeDao.saveExclude(excu);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("��Ʒ��������������/Z_RFC_GET_MATERIAL_EXCLUDE");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("ʧ�ܣ�");
                        log.setLogInfo("����SAPʧ��!");
                        this.logInfoDao.addLogInfo(log);
                        continue;
                    }
                }
                if (i == rows) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("��Ʒ��������������/Z_RFC_GET_MATERIAL_EXCLUDE");
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
    }

    public static void main(String[] args) {
        ExcludeServiceImpl impl = new ExcludeServiceImpl();
        impl.getExcludeFromSap();
    }

    @Override
    public int getExcludeTotal(Exclude exc) {
        // TODO Auto-generated method stub
        return this.excludeDao.getExcludeTotal(exc);
    }

    @Override
    public List<Exclude> getExcludeList(Exclude exc) {
        // TODO Auto-generated method stub
        return this.excludeDao.getExcludeList(exc);
    }
}
