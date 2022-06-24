package com.jingtong.platform.endCustomer.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;

/** 2018/11/06 Add Change alias to EC */
public interface IEndCustomerDao {
	/**
	 * ��ȡ�ն�ҳ���ѯ�б���
	 * @param c
	 * @return
	 */
	public int searchEndCustomerListCount(EndCustomer ec);
	
	public List<ECAlias> searchECAlias(EndCustomer ec) ;
	/**
	 * ��ȡ�ն˿ͻ�ҳ���ѯ�б�
	 * @param c
	 * @return
	 */
	public List<EndCustomer> searchEndCustomerList(EndCustomer ec);
	/**
	 * ��ȡ�ն��б���
	 * @param c
	 * @return
	 */
	public int getEndCustomerListCount(EndCustomer ec);
	/**
	 * ��ȡ�ն˿ͻ��б�
	 * @param c
	 * @return
	 */
	public List<EndCustomer> getEndCustomerList(EndCustomer ec);
	/**
	 * ����ID��ȡ�ն˿ͻ���Ϣ
	 * @param c
	 * @return
	 */
	public EndCustomer getEndCustomerById(EndCustomer ec);
	public List<EndCustomer> checkEndCustomer(EndCustomer ec);
	
    public long getECIdPrc();
    /**
     * ���ñ���
     * @param ec
     * @return
     */
    public int setECCode(EndCustomer ec);
    /**
     * ���Ϊ��check
     * @param ec
     * @return
     */
    public int checkEC(EndCustomer ec);
	/**
	 * �ն˿ͻ���Ϣע�ᣨ������
	 * @param ec
	 * @return
	 */
	public long createEndCustomer(EndCustomer ec);
	/**
	 * �޸��ն˿ͻ���Ϣ
	 * @param ec
	 * @return
	 */
	public int updateEndCustomer(EndCustomer ec);
	/**
	 * ɾ���ն˿ͻ�(����ɾ��)
	 * @param ec
	 * @return
	 */
	public int deleteEndCustomer(EndCustomer ec);
	
	
	public int updateQuoteInfo(ECAlias ea);
	public int updateQuoteEcInfo(ECAlias ea);
	
	
	/**
	 * �ն˿ͻ����
	 * @param ec
	 * @return
	 */
	public int auditEndCustomer(EndCustomer ec);
	
	
	
	
	
	
//**************************************����ģ��***************************************//	
	public int setCheck(EndCustomer ec);
	public List<ECAlias> getECAliasList(ECAlias ea);
	public int getECAliasListCount(ECAlias ea);
	public int getCountByAliasName(ECAlias ea);
	public long createECAlias(ECAlias ea);
	public int updateECAlias(ECAlias ea);
	public int deleteECAliasById(ECAlias ea);
	public ECAlias getECAliasById(ECAlias ea);
	public EndCustomer getEndCustomerByCode(EndCustomer c);
	int updateQuoteECid(ECAlias ea);
	int updateQuotePCid(ECAlias ea);
	int updateDRECid(ECAlias ea);
	int changeEC(ECAlias ea);
	
	public List<EndCustomer> pendingData(EndCustomer ec) ;
	
	// 2018/11/06 Add Start
	public EndCustomer getEndCustomerByNameCity(EndCustomer c);
	public int rollBackEndCustomer(EndCustomer c);
	public int changeAlias(ECAlias ea);
	public List<CustomerUser> getAuditors(CustomerUser cusUser);
	// 2018/11/06 Add End
	
	public List<CmsTbDict> getNewHierarchyList(CmsTbDict nh);
}
