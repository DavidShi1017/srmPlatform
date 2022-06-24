package com.jingtong.platform.sap.pojo;


import java.io.Serializable;
import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Customer extends SearchInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long custNumber;    //�ŵ����kunnr
	private String custName;    //�ŵ�����
	private String custOrg;     //�ŵ���֯
	private String pCustNumber; //�ϼ��ͻ�
	private String custLevel;   //�ŵ�ȼ�
	private String custType; 	//�ŵ�����
	private String isVip; 		//�Ƿ��ص��ŵ�
	private String custState;	//�ŵ�״̬
	private String custLegal;	//����
	private String custContacter;	//�ͻ���ϵ��
	private String contacterSex;	//��ϵ���Ա�	
	private int contacterAge;	//��ϵ������
	private String contacterPhone;	//��ϵ�绰
	private String contacterMobile;	//��ϵ�ֻ�	
	private String email;		//���� 
	private long custChannel;	//�ŵ�����
	private String custDelivery;	//���ͷ�ʽ
	private String companyKind;	//�ŵ���ҵ����	
	private String companySystem;	//�ŵ���ҵϵͳ	
	private String businessLicensen;	//Ӫҵִ��
	private Date licensenDate; 	//Ӫҵִ����Ч��
	private Date lastAudit;		//�ϴ�����
	private int tradeYears;		//��Ӫ����
	private String tradeType;	//��Ӫ��ʽ
	private String mainBrand;	//��ӪƷ��		
	private Date cooperation; 	//��ʼ����ʱ��		
	private String companyWebSite;	//�ŵ���վ��ַ
	private String companyPhone;	//�ŵ���ҵ�绰
	private String companyFax;		//�ŵ���ҵ����
	private String companyPost;		//�ʱ�
	private String companyArea;		//���
	private String companyProvince; //��˾����ʡ
	private String companyCity; 	//��˾������
	private String companyReg;		//��˾������
	private String companyLocation; //��˾���ڵ�
	private String locationDes;		//����ο�λ��
	private String assetInfo;		//�ʲ���Ϣ
	private String rowInfo;			//������Ϣ
	private String hasTallyman;		//�������Ա
	private float longItude;		//����
	private float latItude;			//γ��
	private String remark;			//��ע
	private Date createDate;		//����ʱ��
	private long createUser;		//������
	private Date modifyDate;		//����޸�ʱ��
	private long modifyUser;		//�޸���
	private String template;		//�ͻ����(��������)
	private String registeredAddress;//��˾ע���ַ
	private float registeredCapital; //ע���ʽ�
	private String idnNumber;		//�������֤
	public Long getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(Long custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustOrg() {
		return custOrg;
	}
	public void setCustOrg(String custOrg) {
		this.custOrg = custOrg;
	}
	public String getpCustNumber() {
		return pCustNumber;
	}
	public void setpCustNumber(String pCustNumber) {
		this.pCustNumber = pCustNumber;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustLegal() {
		return custLegal;
	}
	public void setCustLegal(String custLegal) {
		this.custLegal = custLegal;
	}
	public String getCustContacter() {
		return custContacter;
	}
	public void setCustContacter(String custContacter) {
		this.custContacter = custContacter;
	}
	public String getContacterSex() {
		return contacterSex;
	}
	public void setContacterSex(String contacterSex) {
		this.contacterSex = contacterSex;
	}
	public int getContacterAge() {
		return contacterAge;
	}
	public void setContacterAge(int contacterAge) {
		this.contacterAge = contacterAge;
	}
	public String getContacterPhone() {
		return contacterPhone;
	}
	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}
	public String getContacterMobile() {
		return contacterMobile;
	}
	public void setContacterMobile(String contacterMobile) {
		this.contacterMobile = contacterMobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCustChannel() {
		return custChannel;
	}
	public void setCustChannel(long custChannel) {
		this.custChannel = custChannel;
	}
	public String getCustDelivery() {
		return custDelivery;
	}
	public void setCustDelivery(String custDelivery) {
		this.custDelivery = custDelivery;
	}
	public String getCompanyKind() {
		return companyKind;
	}
	public void setCompanyKind(String companyKind) {
		this.companyKind = companyKind;
	}
	public String getCompanySystem() {
		return companySystem;
	}
	public void setCompanySystem(String companySystem) {
		this.companySystem = companySystem;
	}  
	public String getBusinessLicensen() {
		return businessLicensen;
	}
	public void setBusinessLicensen(String businessLicensen) {
		this.businessLicensen = businessLicensen;
	}
	public Date getLicensenDate() {
		return licensenDate;
	}
	public void setLicensenDate(Date licensenDate) {
		this.licensenDate = licensenDate;
	}
	public Date getLastAudit() {
		return lastAudit;
	}
	public void setLastAudit(Date lastAudit) {
		this.lastAudit = lastAudit;
	}
	public int getTradeYears() {
		return tradeYears;
	}
	public void setTradeYears(int tradeYears) {
		this.tradeYears = tradeYears;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getMainBrand() {
		return mainBrand;
	}
	public void setMainBrand(String mainBrand) {
		this.mainBrand = mainBrand;
	}
	public Date getCooperation() {
		return cooperation;
	}
	public void setCooperation(Date cooperation) {
		this.cooperation = cooperation;
	}
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyPost() {
		return companyPost;
	}
	public void setCompanyPost(String companyPost) {
		this.companyPost = companyPost;
	}
	public String getCompanyArea() {
		return companyArea;
	}
	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}
	public String getCompanyProvince() {
		return companyProvince;
	}
	public void setCompanyProvince(String companyProvince) {
		this.companyProvince = companyProvince;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	public String getCompanyReg() {
		return companyReg;
	}
	public void setCompanyReg(String companyReg) {
		this.companyReg = companyReg;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	public String getLocationDes() {
		return locationDes;
	}
	public void setLocationDes(String locationDes) {
		this.locationDes = locationDes;
	}
	public String getAssetInfo() {
		return assetInfo;
	}
	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}
	public String getRowInfo() {
		return rowInfo;
	}
	public void setRowInfo(String rowInfo) {
		this.rowInfo = rowInfo;
	}
	public String getHasTallyman() {
		return hasTallyman;
	}
	public void setHasTallyman(String hasTallyman) {
		this.hasTallyman = hasTallyman;
	}
	public float getLongItude() {
		return longItude;
	}
	public void setLongItude(float longItude) {
		this.longItude = longItude;
	}
	public float getLatItude() {
		return latItude;
	}
	public void setLatItude(float latItude) {
		this.latItude = latItude;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(long modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public float getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(float registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getIdnNumber() {
		return idnNumber;
	}
	public void setIdnNumber(String idnNumber) {
		this.idnNumber = idnNumber;
	}
	 
	 
}
