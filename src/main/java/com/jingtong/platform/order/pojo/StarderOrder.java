package com.jingtong.platform.order.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * ��׼��������
 * @author yw
 *
 */
public class StarderOrder extends SearchInfo{
	private long id;
	private String order_id;//�������(Purchase Order)������ƾ֤��-   //��sap������ƾ֤�źͿͻ��ɹ������ŷֱ��Ӧ�ĸ���portalȱһ��
	private String po_item;
	private String type_id;//�������ͣ�Order Type������ƾ֤����-
	private String branch_id;//����������
	private String currency_code;//����-
	private String ship_to;//�ʹ﷽-
	private String payer_to;//���-
	private String billing_to;//��Ʊ��-
	private String sale_to;//�۴﷽-
	private String saler;//������Ա
	private String sale_company;// ���۹�˾��Sale Company��
	private String end_customer;//�ն˿ͻ���End Customer��
	private String project_name;// ��Ŀ����(Project Name) 
	private String contact_info;//�ն˿ͻ���ϵ��Ϣ(EC ContactInfo)
	private String sap_order_id;//����ƾ֤��-
	private String sale_group;//������֯-
	private String distri_channel;//��������-
	private String product_group;//��Ʒ��-
	private int state;//״̬
	private String remark;//��ע
	private int sync_state;//ͬ��״̬
	private Date sync_time;//ͬ��ʱ��
	private String sync_userId;//ͬ���û�
	private String sysnc_exception;//ͬ���쳣
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//����ʱ��
	private String latest_userId;//������
	private String org_code;//������֯
	
	private String customer_name;//
	private String shipToName;//
	private String shipToAddress;//�ʹ﷽-
	private String payerToName;//
	private String billingToName;//
	private Date start_date;
	private Date end_date;
	private String start_dateStr;//
	private String end_dateStr;//
	//��SAP̧ͷ�ı����ն˿ͻ�+��Ŀ����+�ͻ���ַ�ϲ�����
	private String loginId;//��½�˺�
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public String getSap_order_id() {
		return sap_order_id;
	}
	public void setSap_order_id(String sap_order_id) {
		this.sap_order_id = sap_order_id;
	}
	public String getSale_group() {
		return sale_group;
	}
	public void setSale_group(String sale_group) {
		this.sale_group = sale_group;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getDistri_channel() {
		return distri_channel;
	}
	public void setDistri_channel(String distri_channel) {
		this.distri_channel = distri_channel;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getShip_to() {
		return ship_to;
	}
	public String getSale_to() {
		return sale_to;
	}
	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
	}
	public void setShip_to(String ship_to) {
		this.ship_to = ship_to;
	}
	public String getPayer_to() {
		return payer_to;
	}
	public void setPayer_to(String payer_to) {
		this.payer_to = payer_to;
	}
	public String getBilling_to() {
		return billing_to;
	}
	public void setBilling_to(String billing_to) {
		this.billing_to = billing_to;
	}
	public String getSaler() {
		return saler;
	}
	public void setSaler(String saler) {
		this.saler = saler;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getSync_state() {
		return sync_state;
	}
	public void setSync_state(int sync_state) {
		this.sync_state = sync_state;
	}
	public Date getSync_time() {
		return sync_time;
	}
	public void setSync_time(Date sync_time) {
		this.sync_time = sync_time;
	}
	public String getSync_userId() {
		return sync_userId;
	}
	public void setSync_userId(String sync_userId) {
		this.sync_userId = sync_userId;
	}
	public String getSysnc_exception() {
		return sysnc_exception;
	}
	public void setSysnc_exception(String sysnc_exception) {
		this.sysnc_exception = sysnc_exception;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(Date latest_time) {
		this.latest_time = latest_time;
	}
	public String getLatest_userId() {
		return latest_userId;
	}
	public void setLatest_userId(String latest_userId) {
		this.latest_userId = latest_userId;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getSale_company() {
		return sale_company;
	}
	public void setSale_company(String sale_company) {
		this.sale_company = sale_company;
	}
	public String getEnd_customer() {
		return end_customer;
	}
	public void setEnd_customer(String end_customer) {
		this.end_customer = end_customer;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getContact_info() {
		return contact_info;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getPayerToName() {
		return payerToName;
	}
	public void setPayerToName(String payerToName) {
		this.payerToName = payerToName;
	}
	public String getBillingToName() {
		return billingToName;
	}
	public void setBillingToName(String billingToName) {
		this.billingToName = billingToName;
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
	public String getStart_dateStr() {
		return start_dateStr;
	}
	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}
	public String getEnd_dateStr() {
		return end_dateStr;
	}
	public void setEnd_dateStr(String end_dateStr) {
		this.end_dateStr = end_dateStr;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	
	
	
}
