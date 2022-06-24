package com.jingtong.platform.endCustomer.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

/** 2018/11/06 Add Change alias to EC */
public interface IEndCustomerService {
    public int deleteEndCustomer(EndCustomer ec);

    public List<ECAlias> searchECAlias(EndCustomer c);

    /**
     * ��ȡ�ն�ҳ���ѯ�б���
     * 
     * @param c
     * @return
     */
    public int searchEndCustomerListCount(EndCustomer ec);

    /**
     * ��ȡ�ն˿ͻ�ҳ���ѯ�б�
     * 
     * @param c
     * @return
     */
    public List<EndCustomer> searchEndCustomerList(EndCustomer ec);

    /**
     * ��ȡ�ն�combobox�б���
     * 
     * @param c
     * @return
     */
    public int getEndCustomerListCount(EndCustomer ec);

    /**
     * ��ȡ�ն˿ͻ�combobox�б�
     * 
     * @param c
     * @return
     */
    public List<EndCustomer> getEndCustomerList(EndCustomer ec);

    public List<EndCustomer> checkEndCustomer(EndCustomer ec);

    /**
     * ����ID��ȡ�ն˿ͻ���Ϣ
     * 
     * @param c
     * @return
     */
    public EndCustomer getEndCustomerById(EndCustomer ec);

    public long getECIdPrc();

    /**
     * ���ñ���
     * 
     * @param ec
     * @return
     */
    public int setECCode(EndCustomer ec);

    /**
     * ���Ϊ��check
     * 
     * @param ec
     * @return
     */
    public int checkEC(EndCustomer ec);

    /**
     * �ն˿ͻ���Ϣע�ᣨ������
     * 
     * @param ec
     * @return
     */
    public long createEndCustomer(EndCustomer ec);

    /**
     * �ն˿ͻ���Ϣ�޸�
     * 
     * @param ec
     * @return
     */
    public int updateEndCustomer(EndCustomer ec);

    /**
     * �ն˿ͻ����
     * 
     * @param ec
     * @return
     */
    public int auditEndCustomer(EndCustomer ec);

    public int setCheck(EndCustomer ec);

    // **************************************����ģ��***************************************//
    public List<ECAlias> getECAliasList(ECAlias ea);

    public int getECAliasListCount(ECAlias ea);

    public int getCountByAliasName(ECAlias ea);

    public long createECAlias(ECAlias ea);

    public int updateECAlias(ECAlias ea);

    public int deleteECAliasById(ECAlias ea);

    public ECAlias getECAliasById(ECAlias ea);

    public EndCustomer getEndCustomerByCode(EndCustomer c);

    public int updateQuoteECid(ECAlias ea);

    public int updateQuotePCid(ECAlias ea);

    public int updateDRECid(ECAlias ea);

    public BooleanResult changeEC(ECAlias ea, EndCustomer ec);

    int changeEC1(ECAlias ea);

    public List<EndCustomer> pendingData(EndCustomer c);

    // 2018/11/06 Add Start
    /**
     * Change Alias to EC
     * 
     * @param ea
     * @param ec
     * @param ec
     * @return
     */
    public BooleanResult changeAlias(ECAlias ea);
    // 2018/11/06 Add End

    public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh);

}
