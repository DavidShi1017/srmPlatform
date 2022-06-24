package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Accrual extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8078303555528248029L;
	
	private long id;//ID	ID
	private String kunnr;//�ͻ����	kunnr
	private String kunnrName;
	
	private String creditId;//���ű��	credit_Id
	private String creditRange;///���ŷ�Χ	creditRange
	private String crmOrderId;//CRM������	crm_order_Id
	private String sapOrderId;//SAP������	sap_order_Id
	private String deliveryId;//��������	delivery_Id
	private Date useDate;//ʹ������	use_date
	private double repayMentAmount;//Ӧ������	repayment_amount
	private Date repayMentDate;//Ӧ������	repayment_date
	private int repaymentDays;
	private double repaidAmount;//�ѻ�����	repaid_amount
	private Date repaidDate;//�ѻ�������	repaid_date
	private double rate;//����	rate
	private String createUser;//������	CREATEUSER
	private Date createDate;//��������	CREATEDATE
	private String modifyUser;//�޸���	MODIFYUSER
	private Date modifyDate;//�޸�����	MODIFYDATE
	private String status;//״̬	status
	
	private String bukrs; //���˹�˾
	private String gjahr; //������
	private String belnr; //���ƾ֤��
	private String buzei; //ƾ֤����Ŀ
	
	
 	
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getGjahr() {
		return gjahr;
	}
	public void setGjahr(String gjahr) {
		this.gjahr = gjahr;
	}
	public String getBelnr() {
		return belnr;
	}
	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}
	public String getBuzei() {
		return buzei;
	}
	public void setBuzei(String buzei) {
		this.buzei = buzei;
	}
	public int getRepaymentDays() {
		return repaymentDays;
	}
	public void setRepaymentDays(int repaymentDays) {
		this.repaymentDays = repaymentDays;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
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
	public String getCreditId() {
		return creditId;
	}
	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getCreditRange() {
		return creditRange;
	}
	public void setCreditRange(String creditRange) {
		this.creditRange = creditRange;
	}
	public String getCrmOrderId() {
		return crmOrderId;
	}
	public void setCrmOrderId(String crmOrderId) {
		this.crmOrderId = crmOrderId;
	}
	public String getSapOrderId() {
		return sapOrderId;
	}
	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public double getRepayMentAmount() {
		return repayMentAmount;
	}
	public void setRepayMentAmount(double repayMentAmount) {
		this.repayMentAmount = repayMentAmount;
	}
	public Date getRepayMentDate() {
		return repayMentDate;
	}
	public void setRepayMentDate(Date repayMentDate) {
		this.repayMentDate = repayMentDate;
	}
	public double getRepaidAmount() {
		return repaidAmount;
	}
	public void setRepaidAmount(double repaidAmount) {
		this.repaidAmount = repaidAmount;
	}
	public Date getRepaidDate() {
		return repaidDate;
	}
	public void setRepaidDate(Date repaidDate) {
		this.repaidDate = repaidDate;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
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

	
}
