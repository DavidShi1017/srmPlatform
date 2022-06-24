package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.framework.util.DateUtil;

public class TravelDetail {
	
	private Long travelId;
	private Long travleDtId;
	
	private String travelNum;//��������
	private Date travelStartDate;//������ʼʱ��
	private Date travelEndDate;//�����ֹʱ��
	private String peerPerson;//ͬ����
	private String travelPlace;//�����
	
	private BigDecimal invoiceAmount;//������
	private BigDecimal auditMoney;// ʵ�ʽ��
	private String meno;//���뱨��˵��
	private String flag;
	private String travelStartDateSt;//������ʼʱ��
	private String travelEndDateSt;//�����ֹʱ��
	
	public Long getTravelId() {
		return travelId;
	}
	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}
	public Long getTravleDtId() {
		return travleDtId;
	}
	public void setTravleDtId(Long travleDtId) {
		this.travleDtId = travleDtId;
	}
	public String getTravelNum() {
		return travelNum;
	}
	public void setTravelNum(String travelNum) {
		this.travelNum = travelNum;
	}
	public Date getTravelStartDate() {
		return travelStartDate;
	}
	public void setTravelStartDate(Date travelStartDate) {
		this.travelStartDate = travelStartDate;
	}
	public Date getTravelEndDate() {
		return travelEndDate;
	}
	public void setTravelEndDate(Date travelEndDate) {
		this.travelEndDate = travelEndDate;
	}
	public String getPeerPerson() {
		return peerPerson;
	}
	public void setPeerPerson(String peerPerson) {
		this.peerPerson = peerPerson;
	}
	public String getTravelPlace() {
		return travelPlace;
	}
	public void setTravelPlace(String travelPlace) {
		this.travelPlace = travelPlace;
	}
	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public BigDecimal getAuditMoney() {
		return auditMoney;
	}
	public void setAuditMoney(BigDecimal auditMoney) {
		this.auditMoney = auditMoney;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTravelStartDateSt() {
		return travelStartDateSt;
	}
	public void setTravelStartDateSt(String travelStartDateSt) {
		this.travelStartDateSt = travelStartDateSt;
		this.setTravelStartDate(DateUtil.getDateTime(travelStartDateSt));	
	}
	public String getTravelEndDateSt() {
		return travelEndDateSt;
	}
	public void setTravelEndDateSt(String travelEndDateSt) {
		this.travelEndDateSt = travelEndDateSt;
		this.setTravelEndDate(DateUtil.getDateTime(travelEndDateSt));	
	}

}
