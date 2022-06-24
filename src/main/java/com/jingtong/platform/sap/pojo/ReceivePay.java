package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ReceivePay extends SearchInfo{

	/**
	 * �ؿ��¼
	 */
	private static final long serialVersionUID = 3847173929512634491L;
	
	private long id;//ID	ID	NUMBER
	private String kunnr;//������	kunnr	VARCHAR2(30)
	private String saleCompany;//���۵�λ
	private String accountYear;//�˻���
	private double receiveMoney;//�ؿ���	receive_money	NUMBER
	private Date receiveDate;//�ؿ�����	receive_date	DATE
	private String skpz;//�ο�ƾ֤
	private double syMoney;//ʣ����	sy_money	NUMBER
	private String receiveId;//�ؿ�ƾ֤��	receiveId	VARCHAR2(30)
	private String createUser;//������	CREATEUSER	VARCHAR2(30)
	private Date createDate;//��������	CREATEDATE	DATE
	private String modifyUser;//�޸���	MODIFYUSER	VARCHAR2(30)
	private Date modifyDate;//�޸�����	MODIFYDATE	DATE
	private String status;//״̬	status	char(1)
	private String remark;//ժҪ
	//����ֻ��Ϊ��ʱ������
	private String kunnrName;//����������
	private String thisDate;
	private String createUserName;
	private String stringReceiveMoney;
	private String stringSyMoney;
	private String sgType;
	private String buzei; //ƾ֤����Ŀ
	private String sp;
	private String belnr;
	
	
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public String getBuzei() {
		return buzei;
	}
	public void setBuzei(String buzei) {
		this.buzei = buzei;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public double getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(double receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public double getSyMoney() {
		return syMoney;
	}
	public void setSyMoney(double syMoney) {
		this.syMoney = syMoney;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSaleCompany() {
		return saleCompany;
	}
	public void setSaleCompany(String saleCompany) {
		this.saleCompany = saleCompany;
	}
	public String getAccountYear() {
		return accountYear;
	}
	public void setAccountYear(String accountYear) {
		this.accountYear = accountYear;
	}
	public String getSkpz() {
		return skpz;
	}
	public void setSkpz(String skpz) {
		this.skpz = skpz;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public String getThisDate() {
		return thisDate;
	}
	public void setThisDate(String thisDate) {
		this.thisDate = thisDate;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getStringReceiveMoney() {
		return stringReceiveMoney;
	}
	public void setStringReceiveMoney(String stringReceiveMoney) {
		this.stringReceiveMoney = stringReceiveMoney;
	}
	public String getStringSyMoney() {
		return stringSyMoney;
	}
	public void setStringSyMoney(String stringSyMoney) {
		this.stringSyMoney = stringSyMoney;
	}
	public String getSgType() {
		return sgType;
	}
	public void setSgType(String sgType) {
		this.sgType = sgType;
	}
	public String getBelnr() {
		return belnr;
	}
	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}
	
}
