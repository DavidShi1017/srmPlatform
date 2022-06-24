package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

@SuppressWarnings("serial")
public class TravelTotal extends SearchInfo{
	private String id;
	private Long travelId;
	private String transactionId;	//����Id
	private String orgId;			//�ύ�˲���Id
	private String orgName;		//�ύ�˲���	
	private BigDecimal totalMoney;//�����ܽ��
	private BigDecimal auditMoney; //����ܽ��
	private BigDecimal oldTotalMoney;
	
	private String costType;//��������
	private String costTypeName;
	private String costOrgId;//�ɱ�����
	private String costOrgName;//�ɱ�����

	private String budgetNumber;//���ñ��
	private String bndetailId;//��������Id
	private String memo;//��������
	
	private String createUserId;//������
	private String operatorId;//�޸���
	private String createUserName;//����������
	private Date createDate;//����ʱ��
	private String flag;//ɾ�����

	
	private String payee;// �տ���
	private String payaccount;// �տ����˺�
	private String payBank;//����
	private String payType;//֧����ʽ
	
	private String writeEventId;
	private BigDecimal paycash;
	private BigDecimal chouqian;
	private BigDecimal dkMoney;

	private String writeEvent;
	private String eventId;//����Id
	private String title;
	private String status;
	private String writeStatus;
	private Date startDate;
	private Date endDate;
	private String auditor;
	private String[] orgIds;
	
	private String year;
	private String month;
	private BigDecimal budgetNumberBalance;
	
	private List<TravelDetail> travelDetailList;
	
	
	public Long getTravelId() {
		return travelId;
	}
	public void setTravelId(Long travelId) {
		this.travelId = travelId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getAuditMoney() {
		return auditMoney;
	}
	public void setAuditMoney(BigDecimal auditMoney) {
		this.auditMoney = auditMoney;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getCostOrgId() {
		return costOrgId;
	}
	public void setCostOrgId(String costOrgId) {
		this.costOrgId = costOrgId;
	}
	public String getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	public String getBndetailId() {
		return bndetailId;
	}
	public void setBndetailId(String bndetailId) {
		this.bndetailId = bndetailId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getPayaccount() {
		return payaccount;
	}
	public void setPayaccount(String payaccount) {
		this.payaccount = payaccount;
	}
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public List<TravelDetail> getTravelDetailList() {
		return travelDetailList;
	}
	public void setTravelDetailList(List<TravelDetail> travelDetailList) {
		this.travelDetailList = travelDetailList;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getWriteEventId() {
		return writeEventId;
	}
	public void setWriteEventId(String writeEventId) {
		this.writeEventId = writeEventId;
	}
	public BigDecimal getPaycash() {
		return paycash;
	}
	public void setPaycash(BigDecimal paycash) {
		this.paycash = paycash;
	}
	public BigDecimal getChouqian() {
		return chouqian;
	}
	public void setChouqian(BigDecimal chouqian) {
		this.chouqian = chouqian;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCostOrgName() {
		return costOrgName;
	}
	public void setCostOrgName(String costOrgName) {
		this.costOrgName = costOrgName;
	}
	public String getWriteEvent() {
		return writeEvent;
	}
	public void setWriteEvent(String writeEvent) {
		this.writeEvent = writeEvent;
	}
	public String getWriteStatus() {
		return writeStatus;
	}
	public void setWriteStatus(String writeStatus) {
		this.writeStatus = writeStatus;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCostTypeName() {
		return costTypeName;
	}
	public void setCostTypeName(String costTypeName) {
		this.costTypeName = costTypeName;
	}
	public String[] getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getBudgetNumberBalance() {
		return budgetNumberBalance;
	}
	public void setBudgetNumberBalance(BigDecimal budgetNumberBalance) {
		this.budgetNumberBalance = budgetNumberBalance;
	}
	public BigDecimal getDkMoney() {
		return dkMoney;
	}
	public void setDkMoney(BigDecimal dkMoney) {
		this.dkMoney = dkMoney;
	}
	public BigDecimal getOldTotalMoney() {
		return oldTotalMoney;
	}
	public void setOldTotalMoney(BigDecimal oldTotalMoney) {
		this.oldTotalMoney = oldTotalMoney;
	}


}
