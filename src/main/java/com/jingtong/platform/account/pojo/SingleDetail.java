package com.jingtong.platform.account.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class SingleDetail extends SearchInfo {

	private static final long serialVersionUID = -7217743280414889116L;

	
	private Long transaction_id;
	private Long detail_id;
	private Long plan_id;
	
	private String cost_type;			//��������
	private String cost_type_content;	//������������
	
	private Date cost_date;				//��������
	private String cost_purpose;		//��֧��;
	private Long invoice_num;			//��Ʊ����
	private BigDecimal invoice_amount;	//��Ʊ���
	private BigDecimal audit_money;// ʵ�ʽ��
	private String cost_memo;// ��ע
	private String payee;// �տ���
	private String project;// ��Ŀ
	private String project_manager;// ��Ŀ����
	private Date financial_operate_date;// ������ʱ��
	private String status;// ����״̬
	private Date start_date;
	private Date end_date;
	private Long org_id; //�ɱ�����ID
	private String org_name; //�ɱ���������
	private String budget_number; //Ԥ����
	private Long bndetail_id; //Ԥ���ܱ�ID
	private Long event_id; //����ID
	private Long write_event_id; //����ID
	private  Date businessStartDate; //ҵ��ʼʱ��
	private  Date businessEndDate; //ҵ�����ʱ��
	
	public Long getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(Long detail_id) {
		this.detail_id = detail_id;
	}
	public Long getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(Long plan_id) {
		this.plan_id = plan_id;
	}
	public String getCost_type() {
		return cost_type;
	}
	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
	}
	public Date getCost_date() {
		return cost_date;
	}
	public void setCost_date(Date cost_date) {
		this.cost_date = cost_date;
	}
	public String getCost_purpose() {
		return cost_purpose;
	}
	public void setCost_purpose(String cost_purpose) {
		this.cost_purpose = cost_purpose;
	}
	public Long getInvoice_num() {
		return invoice_num;
	}
	public void setInvoice_num(Long invoice_num) {
		this.invoice_num = invoice_num;
	}
	public BigDecimal getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(BigDecimal invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public String getCost_memo() {
		return cost_memo;
	}
	public void setCost_memo(String cost_memo) {
		this.cost_memo = cost_memo;
	}
	public String getCost_type_content() {
		return cost_type_content;
	}
	public void setCost_type_content(String cost_type_content) {
		this.cost_type_content = cost_type_content;
	}
	public BigDecimal getAudit_money() {
		return audit_money;
	}
	public void setAudit_money(BigDecimal audit_money) {
		this.audit_money = audit_money;
	}
	public Long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProject_manager() {
		return project_manager;
	}
	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}
	public Date getFinancial_operate_date() {
		return financial_operate_date;
	}
	public void setFinancial_operate_date(Date financial_operate_date) {
		this.financial_operate_date = financial_operate_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Long getOrg_id() {
		return org_id;
	}
	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getBudget_number() {
		return budget_number;
	}
	public void setBudget_number(String budget_number) {
		this.budget_number = budget_number;
	}
	public Long getBndetail_id() {
		return bndetail_id;
	}
	public void setBndetail_id(Long bndetail_id) {
		this.bndetail_id = bndetail_id;
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public Long getWrite_event_id() {
		return write_event_id;
	}
	public void setWrite_event_id(Long write_event_id) {
		this.write_event_id = write_event_id;
	}
	public Date getBusinessStartDate() {
		return businessStartDate;
	}
	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}
	public Date getBusinessEndDate() {
		return businessEndDate;
	}
	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}
}
