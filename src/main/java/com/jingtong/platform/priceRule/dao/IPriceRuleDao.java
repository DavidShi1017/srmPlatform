package com.jingtong.platform.priceRule.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.priceRule.pojo.PriceRule;

public interface IPriceRuleDao {
	/**
	 * ��ȡ�ն��б���
	 * @param c
	 * @return
	 */
	public int getPriceRuleListCount(PriceRule pr);
	/**
	 * ��ȡ�ն˿ͻ��б�
	 * @param c
	 * @return
	 */
	public List<PriceRule> getPriceRuleList(PriceRule pr);
	/**
	 * ����ID��ȡ�ն˿ͻ���Ϣ
	 * @param c
	 * @return
	 */
	public PriceRule getPriceRuleById(PriceRule pr);
	/**
	 * �ն˿ͻ���Ϣע�ᣨ������
	 * @param pr
	 * @return
	 */
	public long createPriceRule(PriceRule pr);
	/**
	 * �޸��ն˿ͻ���Ϣ
	 * @param pr
	 * @return
	 */
	public int updatePriceRule(PriceRule pr);
	/**
	 * ɾ���ն˿ͻ�(����ɾ��)
	 * @param pr
	 * @return
	 */
	public int deletePriceRule(PriceRule pr);
	/**
	 * �ն˿ͻ����
	 * @param pr
	 * @return
	 */
	public int auditPriceRule(PriceRule pr);
	
	/**
	 * ��ȡ�ն��б���(control)
	 * @param c
	 * @return
	 */
	public int getControlPriceRuleListCount(PriceRule pr);
	/**
	 * ��ȡ�ն˿ͻ��б�(control)
	 * @param c
	 * @return
	 */
	public List<PriceRule> getControlPriceRuleList(PriceRule pr);
	
	
	public int getOrgListCount(Borg borg);
	public List<Borg> getOrgList(Borg borg);
	
	public int getPriceRuleListCountCmRm(PriceRule pr) ;
	public List<PriceRule> getPriceRuleListCmRmQm(PriceRule priceRule);
}
