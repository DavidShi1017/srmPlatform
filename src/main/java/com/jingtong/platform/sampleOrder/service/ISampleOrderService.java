package com.jingtong.platform.sampleOrder.service;



import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.sampleOrder.pojo.AccountManager;
import com.jingtong.platform.sampleOrder.pojo.SampleOrder;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderAndDetail;
import com.jingtong.platform.sampleOrder.pojo.SampleOrderDetail;

public interface ISampleOrderService {
	/**
	 * ��ȡ�����б���
	 * @param o
	 * @return
	 */
	public int getSampleOrderListCount(SampleOrder o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param o
	 * @return
	 */
	public List<SampleOrder> getSampleOrderList(SampleOrder o);
	/**
	 * ����ID��ȡ������Ϣ
	 * @param o
	 * @return
	 */
	public SampleOrder getSampleOrderById(SampleOrder o);
	/**
	 * ������Ϣ����
	 * @param o
	 * @return
	 */
	public BooleanResult createSampleOrder(SampleOrder o,List<SampleOrderDetail> odList);
	/**
	 * �޸Ķ�����Ϣ
	 * @param o
	 * @return
	 */
	public BooleanResult updateSampleOrder(SampleOrder o,List<SampleOrderDetail> odList,SampleOrderDetail od);
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
	int importUpdate(SampleOrderDetail o);
	int importUpdateCount(SampleOrderDetail o);
	
	/**
	 * ��ȡ�����б���
	 * @param o
	 * @return
	 */
	public int getSampleOrderAndDetailListCount(SampleOrder o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param o
	 * @return
	 */
	public List<SampleOrderAndDetail> getSampleOrderAndDetailList(SampleOrder o);
	
	public int getRoleByLoginId(SampleOrder o);
	
    public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public List<Borg> getOrgList(Borg borg);
    
    public List<AccountManager> getAccountManageList(AccountManager manager);
    
    public List<Role> getRoleForSampleOrder(String userId);
    
    public BooleanResult auditSampleOrder(SampleOrder so, List<SampleOrderDetail> odList);
}
