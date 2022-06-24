package com.jingtong.platform.kunnr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
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

/**
 * ������
 * 
 * @author mengkun
 * 
 */
public interface IKunnrService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	static final String DB_SUCCESS="���ݱ������ݿ�ɹ�.";
	static final String DB_FAIL="���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա!";
	static final String SAP_SUCCESS="���ݴ���ɹ�.";
	static final String SAP_FAIL="���ݴ���ʧ��,�����Ի���ϵϵͳ����Ա!";



	/**
	 * 
	 * ����������¼
	 * 
	 * @return
	 */
	public List<KunnrPunish> kunnrPunishSearch(KunnrPunish kunnr);

	public List<Knvp> getknvpList(Knvp knvp);
	/**
	 * 
	 * ����������¼����
	 * 
	 * @return
	 */
	public Long savekunnrPunish(KunnrPunish kunnr);
 	
	

	/**
	 * �����̱�������
	 * 
	 * @return
	 */
	public String getRanKunnrCode();

	/**
	 * �����̿�����������
	 * 
	 * @param business
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            �ļ��A
	 */
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * * �����̿���֤�ձ���
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid);
	

	/**
	 * * ���Ʊ��֤��
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * �����̶���ػ���������
	 * 
	 * @param kunnr
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            �ļ��A
	 */
	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);
	/**
	 * ���N���_�� ���
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrOpen(Kunnr kunnr);

	/**
	 * �����̶���
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreeze(Kunnr kunnr);

	/**
	 * �����̹غ�
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrClose(Kunnr kunnr);

	/**
	 * �������޸�
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrUpdate(Kunnr kunnr);

	/**
	 * 
	 * �����̲�ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * �����б��̲�ѯ
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
	 * ����������
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrPolicy getKunnrPolicyEntity(Kunnr kunnr);
	/**
	 * �����̵�ַ��Ϣ�б� ���ʹ﷽
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);
	/****
	 * 
	 * �����������˻���Ϣ
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBanks> getKunnrBanksList(Kunnr kunnr);


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
	 * ���������۷�Χ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);

	/**
	 * ���������ѯ
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	
	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * ��֤�Ƿ��Ǵ˽�ɫ�û�
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	
	/**
	 * �޸ľ������ۿ�
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * ����������ͼ
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreezeXview(Kunnr kunnr);
	
	/**
	 * 
	 * ������ΥԼ��¼��ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrPunishSearchCount(KunnrPunish kunnrPunish);

	/**
	 * �޸��Ƿ����
	 * @param kunnr
	 * @return
	 */
	public int updateKunnrAutoDemand(Kunnr kunnr);
	
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
