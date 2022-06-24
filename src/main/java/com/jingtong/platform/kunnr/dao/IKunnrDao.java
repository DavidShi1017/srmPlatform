package com.jingtong.platform.kunnr.dao;

import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.kunnr.pojo.Knvp;
import com.jingtong.platform.kunnr.pojo.Kunnr;
import com.jingtong.platform.kunnr.pojo.KunnrAcount;
import com.jingtong.platform.kunnr.pojo.KunnrAddress;
import com.jingtong.platform.kunnr.pojo.KunnrBanks;
import com.jingtong.platform.kunnr.pojo.KunnrBrand;
import com.jingtong.platform.kunnr.pojo.KunnrBusiness;
import com.jingtong.platform.kunnr.pojo.KunnrLicense;
import com.jingtong.platform.kunnr.pojo.KunnrLogisticsArea;
import com.jingtong.platform.kunnr.pojo.KunnrPolicy;
import com.jingtong.platform.kunnr.pojo.KunnrPunish;
import com.jingtong.platform.kunnr.pojo.KunnrSalesArea;

public interface IKunnrDao {

	/**
	 * �����̱�������
	 * 
	 * @return
	 */
	public String getRanKunnrCode();

	/**
	 * ���������̻�����Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public long createKunnr(Kunnr kunnr);

	/**
	 * ������������ϸ��Ϣ
	 * 
	 * @param business
	 * @return
	 */
	public long createKunnrBusiness(KunnrBusiness business);

	/**
	 * �����������ʹ��ַ
	 * 
	 * @param address
	 * @return
	 */
	public void createKunnrAddress(List<KunnrAddress> kunnrAddressList,
			String kunnr);

	/**
	 * ���������̾�ӪƷ��
	 * 
	 * @param brand
	 * @return
	 */
	public void createKunnrBrand(List<KunnrBrand> kunnrBrandList, String kunnr);

	/**
	 * �����������ۿ�
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void createKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * ��������������
	 * 
	 * @param kunnrPolicyList
	 * @param kunnr
	 */
	public void createKunnrPolicy(List<KunnrPolicy> kunnrPolicyList,
			String kunnr,String flag);
	/**
	 * �����˻���Ϣ
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrBanks(final List<KunnrBanks> kunnrBanklist,
		final String kunnr) ;
	
	/***
	 * �鿴������ �����˻���Ϣ
	 * @param KunnrBanks
	 * @return
	 */
	public List<KunnrBanks> getKunnrBankList(Kunnr kunnr);
	/**
	 * ����֤����Ϣ
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrLicense(List<KunnrLicense> kunnrLicenseList,
			String kunnr);

	/**
	 * �޸ľ����̻�����Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public Integer updateKunnr(Kunnr kunnr);

	/**
	 * �޸ľ�������ϸ��Ϣ
	 * 
	 * @param business
	 * @return
	 */
	public Integer updateKunnrBusiness(KunnrBusiness business);

	/**
	 * �޸ľ������ʹ��ַ
	 * 
	 * @param address
	 * @return
	 */
	public void updateAndCreateKunnrAddress(
			List<KunnrAddress> kunnrAddressList, String kunnr);

	/**
	 * �޸ľ����̾�ӪƷ��
	 * 
	 * @param brand
	 * @return
	 */
	public void updateAndCreateKunnrBrand(List<KunnrBrand> kunnrBrandList,
			String kunnr);

	/**
	 * �޸ľ������ۿ�
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);

	/**
	 * ɾ��Ʒ��
	 * 
	 * @param killBrand
	 */
	public void removeBrand(String killBrand);

	/**
	 * ɾ���ۿ�
	 * 
	 * @param killAcount
	 */
	public void removeAcount(String killAcount);

	/**
	 * �����̶���
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrFreeze(Kunnr kunnr);

	/**
	 * �����̹غ�
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrClose(Kunnr kunnr);

	/**
	 * 
	 * �����̲�ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * �������б��ѯ
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);

	/**
	 * 
	 * ��������Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public Kunnr getKunnrEntity(Kunnr kunnr);

	/**
	 * ��������ϸ��Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr);

	/**
	 * �����̵�ַ��Ϣ�б� ���ʹ﷽
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);

	/**
	 * ������Ʒ���б�
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr);

	/**
	 * �������ۿ�˵���б�
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr);

	/**
	 * ������֤����Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr);
	
	/**
	 * �������۷�Χ
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createSaleArea(List<KunnrSalesArea> kunnrSalesAreaList,
			String kunnr);
	
	 
	/**
	 * ���������۷�Χ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);
	
	/**
	 * �޸ľ����̷�Χ
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateSalesArea(List<KunnrSalesArea> salesAreaList,
			String kunnr);
	
	/**
	 * ɾ�����۷�Χ
	 * 
	 * @param killSalesArea
	 */
	public void removeSalesArea(List<KunnrSalesArea> salesAreaList);
	

	/**
	 * ���������ѯ
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	/**
	 * �޸���������
	 * @param areaList
	 * @return
	 */
	public void updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * ��֤�Ƿ��Ǵ˽�ɫ�û�
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	/**
	 * ����������
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr);
	

	/**
	 * �����̴�����¼����
	 * 
	 * */
	
	public Long savekunnrPunish(KunnrPunish kunnrPunish);
	 

	/**
	 * 
	 * �����̴�����¼��ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrPunishSearchCount(KunnrPunish kunnr);

	/**
	 * 
	 * �����̴�����¼�б��ѯ
	 * 
	 * @return
	 */
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnr);

	/**
	 * �Ƿ��Զ�����
	 * @param kunnr
	 * @return
	 */
	public int updateKunnrAutoDemand(Kunnr kunnr);
	
	public List<Knvp> getknvpList(Knvp knvp);
	
	/**
	 * ��ѯ������
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> queryKunnrCompany(Kunnr kunnr);

	public List<Knvp> getAllknvpList(Knvp knvp);
	
	public List<Knvp> getAllknvpList1(Knvp knvp);

	public int getknvpListCount(Knvp knvp);
	
	public List<AllUsers> getAllUserByCode(AllUsers user);
	
	/**
	 * ���ݿͻ��������ͻ�
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrByKunnr(Kunnr kunnr);
	
	public Kunnr getPhonesByKunnr(Kunnr kunnr);
}
