package com.jingtong.platform.sampleOrder.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;



public interface ISampleOrderDao{
	/**
	 * ��ȡ�����б���
	 * @param c
	 * @return
	 */
	public int getSampleOrderListCount(SampleOrder o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param c
	 * @return
	 */
	public List<SampleOrder> getSampleOrderList(SampleOrder o);
	/**
	 * ����ID��ȡ������Ϣ
	 * @param c
	 * @return
	 */
	public SampleOrder getSampleOrderById(SampleOrder o);
	/**
	 * ������Ϣ����
	 * @param p
	 * @return
	 */
	public long createSampleOrder(SampleOrder o);
	/**
	 * �޸Ķ�����Ϣ
	 * @param o
	 * @return
	 */
	public int updateSampleOrder(SampleOrder o);
	/**
	 * ɾ��������Ϣ(�߼�ɾ��)
	 * @param o
	 * @return
	 */
	public int deleteSampleOrder(SampleOrder o);
	
	
	

	/**
	 * ��ȡ������ϸ��Ϣ�б�
	 * @param od
	 * @return
	 */
	public List<SampleOrderDetail> getSampleOrderDetailList(SampleOrderDetail od);

	/**
	 * ������ϸ��Ϣ����
	 * @param od
	 * @return
	 */
	public long createSampleOrderDetail(SampleOrderDetail od);
	/**
	 * �޸Ķ�����ϸ��Ϣ
	 * @param od
	 * @return
	 */
	public int updateSampleOrderDetail(SampleOrderDetail od);
	/**
	 * ɾ��������ϸ��Ϣ(����ɾ��)
	 * @param od
	 * @return
	 */
	public int deleteSampleOrderDetail(SampleOrderDetail od);
	
	
	/**
	 * ��ȡ�Զ����ɵ���
	 */
	public String getSystemIdPrc();
	public int deleteSODofMain(SampleOrderDetail sod);
	int importUpdateCount(SampleOrderDetail sod);
	int importUpdate(SampleOrderDetail sod);
	
	public int getSampleOrderAndDetailListCount(SampleOrder o);
	public List<SampleOrderAndDetail> getSampleOrderAndDetailList(SampleOrder o) ;
	public int getRoleByLoginId(SampleOrder o);

	public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public List<Borg> getOrgList(Borg borg);
    
    public List<AccountManager> getAccountManageList(AccountManager manager);
    
    public List<Role> getRoleForSampleOrder(String userId);
    
    int auditSampleOrder(SampleOrder so);
    
    int auditSampleOrderDetail(SampleOrderDetail sod);
}