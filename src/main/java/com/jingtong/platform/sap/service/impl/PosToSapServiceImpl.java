package com.jingtong.platform.sap.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.PosToSapDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.PosDetail;
import com.jingtong.platform.sap.pojo.PosToSap;
import com.jingtong.platform.sap.service.PosToSapService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class PosToSapServiceImpl extends BasicService implements PosToSapService {

    private JCoDestination dest;
    private ILogInfoDao logInfoDao;
    private PosToSapDao posToSapDao;
    private TransactionTemplate transactionTemplate;

    public JCoDestination getDest() {
        return dest;
    }

    public void setDest(JCoDestination dest) {
        this.dest = dest;
    }

    public ILogInfoDao getLogInfoDao() {
        return logInfoDao;
    }

    public void setLogInfoDao(ILogInfoDao logInfoDao) {
        this.logInfoDao = logInfoDao;
    }

    public PosToSapDao getPosToSapDao() {
        return posToSapDao;
    }

    public void setPosToSapDao(PosToSapDao posToSapDao) {
        this.posToSapDao = posToSapDao;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * ������м��ͨ���ģ��������ͷ����ϸ ͬһ��Disti��Ϊһ�� B
     * 
     * @throws Exception
     */
    @Override
    public String posToSapB(List<Pos> posList) throws Exception {

        // ����sap�ӿڻ�ȡ���۵�
        String reMessage = "";
        outterLoop: for (Pos pos : posList) {

            try {
                // ��������
                this.connect("SAP");
                dest = this.getDest("SAP");

                // ���ӽӿ�
                JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
                if (function == null) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("ʧ�ܣ�");
                    log.setLogInfo("����SAPʧ��!");
                    this.logInfoDao.addLogInfo(log);
                    throw new Exception("����SAPʧ��!");
                }
                List<Pos> posship = posToSapDao.getPosShipTo(pos);
                // ������Ϣ��ͷ��
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// ��ȡ�ṹ
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                if (posship.size() > 0) {
                    head.setValue("KUNWE", posship.get(0).getShip_to());// �ʹ﷽
                    head.setValue("KUNRG", posship.get(0).getPayer_to());// ����-----------------����-
                    head.setValue("KUNRE", posship.get(0).getBilling_to());// ��Ʊ----------------����---
                    head.setValue("KUNAG", posship.get(0).getSold_to());// �۴ＴDisti����
                } else {
                    head.setValue("KUNWE", pos.getPayer_to());// �ʹ﷽
                    head.setValue("KUNRG", pos.getPayer_to());// ����-----------------����-
                    head.setValue("KUNRE", pos.getPayer_to());// ��Ʊ----------------����---
                    head.setValue("KUNAG", pos.getPayer_to());// �۴ＴDisti����
                }
                System.out.println("test 5");
                head.setValue("WAERK", pos.getDisti_cost_currency());
                head.setValue("BSTKD", pos.getDebit_number());
                head.setValue("TXTHL", "");
                // ������Ϣ����ϸ��
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// ��ȡ��ֵ

                List<Pos> detailPoslist = pos.getDetailPosList();

                // �׵�֮ǰ�ж����sap ������û�л��ֵ�����ݿ����ѯһ�� ����ֹ2����ͬʱ��ͬһ����¼�׵�
                // ���״̬Ϊ3������sapnumberΪ�գ����Ҵ�����״̬Ϊ�ջ���0
                // �������pos��¼Ϊ�����У�
                for (int i = 0; i < detailPoslist.size(); i++) {
                    Pos detail = detailPoslist.get(i);
                    Pos pos_deal_mark = posToSapDao.getPosById(detail);
                    if ((pos_deal_mark.getSapClaimNbr() != null && !"".equals(pos_deal_mark.getSapClaimNbr()))
                            || (pos_deal_mark.getClaim_deal_mark() != null
                                    && pos_deal_mark.getClaim_deal_mark() == 1L)) {
                        reMessage = "Create an order repeatedly. Please wait a moment and then recreate the order";
                        break outterLoop;
                    } else {
                        detail.setClaim_deal_mark(1L);
                        posToSapDao.updatePosClaimDealMark(detail);
                    }
                }

                String row_no = "10";
                for (int i = 0; i < detailPoslist.size(); i++) {
                    Pos detail = detailPoslist.get(i);
                    row_no = String.valueOf((i * 1 + 1) * 10);
                    item.appendRow(); // �������
                    item.setValue("POSNR", row_no);// �к�
                    item.setValue("MATNR", detail.getM_12nc());
                    item.setValue("KWMENG", detail.getShip_qty());
                    item.setValue("VRKME", "PC");
                    item.setValue("NETWR", detail.getRebatePrice() * 100);
                    item.setValue("TXTPL", "");
                    item.setValue("KPEIN", "100");
                }

                function.execute(dest);
                // ��ȡ�ӿڽ�������

                JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                int rows = resultTbl.getNumRows();
                if (rows > 0) {
                    String flag = resultTbl.getString("ZFLAG");
                    String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");
                    if (vbeln != null && !"".equals(vbeln)) {// ͬ���ɹ������claim��״̬������ƾ֤���Լ�quoteʣ������

                        for (int i = 0; i < detailPoslist.size(); i++) {
                            Pos detail = detailPoslist.get(i);

                            detail.setRes_qty(detail.getQuote_totalqty() - Double.valueOf(detail.getShip_qty()));// ʣ��������ԭʣ������-����ʵ�ʷ���������
                            detail.setDisti_claimnbr(vbeln);// ����ƾ֤��
                            detail.setSapClaimNbr(vbeln);
                            posToSapDao.updatePosState(detail);
                            posToSapDao.updateQuoteResQty(detail);

                            detail.setClaim_deal_mark(0L);
                            posToSapDao.updatePosClaimDealMark(detail);
                        }
                        reMessage = "Create Order Success!";
                    }
                    else {
                        try {
                            for (int i = 0; i < detailPoslist.size(); i++) {
                                Pos detail = detailPoslist.get(i);
                                detail.setClaim_deal_mark(0L);
                                posToSapDao.updatePosClaimDealMark(detail);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("����: " + vbeln + "," + flag);
                    log.setLogInfo(message);
                    if ("".equals(reMessage))
                        reMessage = message;
                    try {
                        pos.setClaim_deal_mark(0L);
                        posToSapDao.updatePosClaimDealMark(pos);

                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    resultTbl.getString("ZFLAG");
                     String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("ʧ�ܣ�" + message);
                    log.setLogInfo("ʧ��! " + vbeln);
                    try {
                        pos.setClaim_deal_mark(0L);
                        posToSapDao.updatePosClaimDealMark(pos);

                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw e1;
                    }
                    dest = null;
                    reMessage = message;
                    return reMessage;
                }

            } catch (Exception e) {
                e.printStackTrace();
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo(e.getMessage());
                pos.setClaim_deal_mark(0L);
                posToSapDao.updatePosClaimDealMark(pos);

                this.logInfoDao.addLogInfo(log);
                dest = null;
                return e.getMessage();
            }

        }

        return reMessage;
    }

    /**
     * ������м��ͨ���ģ��������ͷ����ϸ ͬһ��Disti��Ϊһ��
     */
    @Override
    public String posToSap(List<Pos> posList) {
        // ����sap�ӿڻ�ȡ���۵�
        String reMessage = "";
        List<Pos> headList = new ArrayList<Pos>();
        for (Pos po : posList) {
            boolean exist = false;
            for (Pos head : headList) {
                if (po.getDisti_branch().equals(head.getDisti_branch())) {
                    exist = true;
                    break;
                }
            }
            if (exist == false) {
                headList.add(po);
            }
        }
        for (Pos headPos : headList) {

            try {
                // ��������
                this.connect("SAP");
                dest = this.getDest("SAP");

                // ���ӽӿ�
                JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
                if (function == null) {
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("ʧ�ܣ�");
                    log.setLogInfo("����SAPʧ��!");
                    this.logInfoDao.addLogInfo(log);
                    throw new Exception("����SAPʧ��!");
                }

                // ������Ϣ��ͷ��
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// ��ȡ�ṹ
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                head.setValue("KUNWE", headPos.getShip_to());// �ʹ﷽
                head.setValue("KUNRG", headPos.getPayer_to());// ����-----------------����-
                head.setValue("KUNRE", headPos.getBilling_to());// ��Ʊ----------------����---
                head.setValue("KUNAG", headPos.getDisti_branch());// �۴ＴDisti����
                head.setValue("WAERK", headPos.getDisti_cost_currency());
                head.setValue("BSTKD", headPos.getDebit_number());
                head.setValue("TXTHL", "");
                // ������Ϣ����ϸ��
                JCoTable item = function.getTableParameterList().getTable("T_ITEM");// ��ȡ��ֵ
                for (Pos p : posList) {
                    if (p.getDisti_branch().equals(headPos.getDisti_branch())) {
                        item.appendRow(); // �������
                        item.setValue("POSNR", "10");// �к�
                        item.setValue("MATNR", p.getM_12nc());
                        item.setValue("KWMENG", p.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", p.getRebatePrice() * 100);
                        item.setValue("TXTPL", "");
                        item.setValue("KPEIN", "100");
                    }
                }

                function.execute(dest);
                // ��ȡ�ӿڽ�������

                JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                int rows = resultTbl.getNumRows();
                if (rows > 0) {
                    String flag = resultTbl.getString("ZFLAG");
                    String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");
                    if (vbeln != null && !"".equals(vbeln)) {// ͬ���ɹ������claim��״̬������ƾ֤���Լ�quoteʣ������
                        for (Pos p : posList) {
                            if (p.getDisti_branch().equals(headPos.getDisti_branch())) {
                                p.setRes_qty(p.getQuote_totalqty() - Double.valueOf(p.getShip_qty()));// ʣ��������ԭʣ������-����ʵ�ʷ���������
                                p.setDisti_claimnbr(vbeln);// ����ƾ֤��
                                p.setSapClaimNbr(vbeln);
                                // p1.setRebate_qty(String.valueOf(p.getRemainQty()));//����Rebate_qty��ֵ
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);

                            }
                        }
                        reMessage = "Create Order Success!";
                        // reMessage = "Create Order Success!" + "</br>"+ "Order Number:" + vbeln;
                    }
                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("����: " + vbeln + "," + flag);
                    log.setLogInfo(message);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } else {
                    resultTbl.getString("ZFLAG");
                    String message = resultTbl.getString("ZMESG");
                    String vbeln = resultTbl.getString("VBELN");

                    ExceptionLog log = new ExceptionLog();
                    log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                    log.setOperateUser("SAP");
                    log.setOperateTime(new Date());
                    log.setLogDesc("ʧ�ܣ�" + message);
                    log.setLogInfo("ʧ��! " + vbeln);
                    try {
                        this.logInfoDao.addLogInfo(log);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    dest = null;
                    reMessage = message;
                    return reMessage;
                }
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo(e.getMessage());
                dest = null;
                return e.getMessage();
            }
        }

        return "Create Order Success!";
    }

    /**
     * �ֱ�ͷ����ϸ A
     */
    @Override
    public String posToSapA(PosToSap pos, List<Pos> posList) {
        // ����sap�ӿڻ�ȡ���۵�
        String reMessage = "";

        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");

            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo("����SAPʧ��!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("����SAPʧ��!");
            }

            if (pos != null) {
                // ������Ϣ��ͷ��
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// ��ȡ�ṹ
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", "Rebate");
                head.setValue("KUNWE", pos.getShip_to());// �ʹ﷽
                head.setValue("KUNRG", pos.getPayer_to());// ����
                head.setValue("KUNRE", pos.getBilling_to());// ��Ʊ
                head.setValue("KUNAG", pos.getSale_to());// �۴ＴDisti
                head.setValue("WAERK", pos.getCurrency_code());
                head.setValue("BSTKD", pos.getOrder_id());
                head.setValue("TXTHL", pos.getTxt());

                if (posList != null) {
                    // ������Ϣ����ϸ��
                    JCoTable item = function.getTableParameterList().getTable("T_ITEM");// ��ȡ��ֵ
                    for (Pos it : posList) {
                        if (Double.valueOf(it.getShip_qty()) <= 0) {
                            continue;
                        }
                        item.appendRow(); // �������

                        System.out.println("�кţ�" + it.getRow_no());
                        System.out.println("���ϣ�" + it.getM_12nc());
                        System.out.println("������" + it.getShip_qty());
                        System.out.println("��" + it.getRebatePrice() * 100);
                        item.setValue("POSNR", it.getRow_no());// �к�
                        item.setValue("MATNR", it.getM_12nc());
                        item.setValue("KWMENG", it.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", it.getRebatePrice() * 100);
                        item.setValue("TXTPL", "");
                        item.setValue("KPEIN", "100");
                    }

                    function.execute(dest);
                    // ��ȡ�ӿڽ�������

                    JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                    int rows = resultTbl.getNumRows();

                    if (rows > 0) {
                        String flag = resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");
                        if (vbeln != null && !"".equals(vbeln)) {// ͬ���ɹ������claim��״̬������ƾ֤���Լ�quoteʣ������
                            for (Pos p : posList) {
                                if (Double.valueOf(p.getShip_qty()) <= 0) {
                                    continue;
                                }
                                p.setRes_qty(p.getQuote_totalqty() - p.getRemainQty());// ʣ��������ԭʣ������-����ʵ�ʷ���������
                                p.setDisti_claimnbr(vbeln);// ����ƾ֤��
                                p.setSapClaimNbr(vbeln);
                                p.setRebate_qty(String.valueOf(p.getRemainQty()));// ����Rebate_qty��ֵ
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);
                            }
                            reMessage = "Create Order Success!" + "</br>" + "Order Number:" + vbeln;
                        }
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("����: " + vbeln + "," + flag);

                        log.setLogInfo(message);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");

                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("ʧ�ܣ�" + message);
                        log.setLogInfo("ʧ��! " + vbeln);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        reMessage = message;

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("�������㴫��SAP/ZSDFU008");
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
        dest = null;
        return reMessage + "";
    }

    @Override
    public List<Pos> getPosTotal(Pos pos) {
        try {
            return posToSapDao.getPosTotal(pos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PosToSap> getPosToSapTotal(PosToSap pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PosDetail> getPosDetail(PosToSap pos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String posToSap(PosToSap pos, List<Pos> posList) {
        // ����sap�ӿڻ�ȡ���۵�
        String reMessage = "";

        try {
            // ��������
            this.connect("SAP");
            dest = this.getDest("SAP");

            // ���ӽӿ�
            JCoFunction function = dest.getRepository().getFunction("ZSDFU008");// url
            if (function == null) {
                ExceptionLog log = new ExceptionLog();
                log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                log.setOperateUser("SAP");
                log.setOperateTime(new Date());
                log.setLogDesc("ʧ�ܣ�");
                log.setLogInfo("����SAPʧ��!");
                this.logInfoDao.addLogInfo(log);
                throw new Exception("����SAPʧ��!");
            }

            if (pos != null) {
                // ������Ϣ��ͷ��
                JCoStructure head = function.getImportParameterList().getStructure("I_HEADER");// ��ȡ�ṹ
                head.setValue("VKORG", "HK10");
                head.setValue("VTWEG", "00");
                head.setValue("SPART", "00");
                head.setValue("AUART", pos.getType_id());
                head.setValue("KUNWE", pos.getShip_to());// �ʹ﷽
                head.setValue("KUNRG", pos.getPayer_to());// ����
                head.setValue("KUNRE", pos.getBilling_to());// ��Ʊ
                head.setValue("KUNAG", pos.getSale_to());// �۴ＴDisti
                head.setValue("WAERK", pos.getCurrency_code());
                head.setValue("BSTKD", pos.getOrder_id());
                head.setValue("TXTHL", pos.getTxt());
                if (posList != null) {
                    // ������Ϣ����ϸ��
                    JCoTable item = function.getTableParameterList().getTable("T_ITEM");// ��ȡ��ֵ
                    for (Pos it : posList) {
                        item.appendRow(); // �������

                        System.out.println("�кţ�" + it.getRow_no());
                        System.out.println("���ϣ�" + it.getM_12nc());
                        System.out.println("������" + it.getShip_qty());
                        System.out.println("��" + it.getRebatePrice());
                        item.setValue("POSNR", it.getRow_no());// �к�
                        item.setValue("MATNR", it.getM_12nc());
                        item.setValue("KWMENG", it.getShip_qty());
                        item.setValue("VRKME", "PC");
                        item.setValue("NETWR", it.getRebatePrice() * 100);
                        item.setValue("KPEIN", "100");
                        item.setValue("TXTPL", "");
                    }

                    function.execute(dest);
                    // ��ȡ�ӿڽ�������

                    JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN");
                    int rows = resultTbl.getNumRows();

                    if (rows > 0) {
                        String flag = resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");
                        if (vbeln != null && !"".equals(vbeln)) {// ͬ���ɹ������claim��״̬������ƾ֤���Լ�quoteʣ������
                            for (Pos p : posList) {
                                p.setRes_qty(p.getQuote_totalqty() - p.getRemainQty());// ʣ��������ԭʣ������-����ʵ�ʷ���������
                                p.setDisti_claimnbr(vbeln);// ����ƾ֤��
                                p.setSapClaimNbr(vbeln);
                                p.setRebate_qty(String.valueOf(p.getRemainQty()));// ����Rebate_qty��ֵ
                                posToSapDao.updatePosState(p);
                                posToSapDao.updateQuoteResQty(p);
                            }
                            reMessage = "Create Order Success!" + "</br>" + "Order Number:" + vbeln;
                        }
                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("����: " + vbeln + "," + flag);

                        log.setLogInfo(message);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        resultTbl.getString("ZFLAG");
                        String message = resultTbl.getString("ZMESG");
                        String vbeln = resultTbl.getString("VBELN");

                        ExceptionLog log = new ExceptionLog();
                        log.setInterfaceName("�������㴫��SAP/ZSDFU008");
                        log.setOperateUser("SAP");
                        log.setOperateTime(new Date());
                        log.setLogDesc("ʧ�ܣ�" + message);
                        log.setLogInfo("ʧ��! " + vbeln);
                        try {
                            this.logInfoDao.addLogInfo(log);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        reMessage = message;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLog log = new ExceptionLog();
            log.setInterfaceName("�������㴫��SAP/ZSDFU008");
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
        dest = null;
        return reMessage + "";
    }

    @Override
    public List<Pos> getLockmark(Pos pos) {
        return posToSapDao.getLockmark(pos);
    }

    @Override
    public void updateLockMark(Pos pos1) {
        posToSapDao.updateLockMark(pos1);
    }
}
