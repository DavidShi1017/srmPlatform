package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ���۵��ӿڽṹ_I_HEADER
 * 
 *
 */
public class QuoteToSap extends SearchInfo{
	private long id;
	private String quoteId; //���۵���� 
	private String quoteType; //�������ͣ�Quote Type�� 
	private String customerGroup; //�ͻ����ţ�Customer Group�� 
	private String customer; //�ͻ����ƣ�Customer�� 
	private String ecGroup; //�ն˿ͻ����ţ�EC Group�� 
	private String endCustomer; //�ն˿ͻ���End Customer�� 
	private String totalAmountv; //�ܼƽ�����Total Amount�� 
	private String currency; //���ۻ��ҵ�λ��Currency�� 
	private String project; //��Ŀ���ƣ�Project�� 
	private String assembly; //�Ƿ����ͣ�Assembly�� 
	private String startTime; //���ۿ�ʼʱ�� 
	private String endTime; //������Ч��ֹʱ��
	private String state; //���۵�״̬ 
	private String enquiryMainId; //ѯ�۵�����ID
	private String syncState; //ͬ��״̬��1����ͬ����0��δͬ���� 
	private String syncTime; //ͬ��ʱ��
	private String syncUserId; //ͬ���û�
	private String sysncException; //ͬ���쳣
	private String createTime; //����ʱ��
	private String createUserId; //�����û� 
	private String latestTime; //����ʱ��
	private String latestUserId; //�����û�
	private String orgCode; //������֯ 
	
	
	private String[] ids;//��ѯ����

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getEcGroup() {
		return ecGroup;
	}
	public void setEcGroup(String ecGroup) {
		this.ecGroup = ecGroup;
	}
	public String getEndCustomer() {
		return endCustomer;
	}
	public void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	public String getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}
	public String getSyncUserId() {
		return syncUserId;
	}
	public void setSyncUserId(String syncUserId) {
		this.syncUserId = syncUserId;
	}
	public String getSysncException() {
		return sysncException;
	}
	public void setSysncException(String sysncException) {
		this.sysncException = sysncException;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getLatestUserId() {
		return latestUserId;
	}
	public void setLatestUserId(String latestUserId) {
		this.latestUserId = latestUserId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String getTotalAmountv() {
		return totalAmountv;
	}
	public void setTotalAmountv(String totalAmountv) {
		this.totalAmountv = totalAmountv;
	}
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEnquiryMainId() {
		return enquiryMainId;
	}
	public void setEnquiryMainId(String enquiryMainId) {
		this.enquiryMainId = enquiryMainId;
	}
	public String getSyncState() {
		return syncState;
	}
	public void setSyncState(String syncState) {
		this.syncState = syncState;
	}
	
	
	
}
