package com.jingtong.platform.customer.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * �ͻ�����ϵ
 * @author yw
 *
 */
public class CusCompany extends SearchInfo{
	private long id;
	private String customer_code;//�ͻ�����
	private long cus_partner;//����ϵid  
	private String partnerId;//��˾���루��������飩
	private String partnerName;//��˾���ƣ���������飩
	private String partnerType;//AG �۴﷽  RE ��Ʊ�� RG ��� WE �ʹ﷽
	private String sales_org;//������֯
	private String address;//��ַ
	private String contact;//��ϵ��
	private int sync_state;//ͬ��״̬��1ͬ����0δͬ����
	private Date sync_time;//ͬ��ʱ��
	private String sync_userId;//ͬ���û�
	private String sysnc_exception;	//ͬ���쳣
	private Date create_time;//����ʱ��
	private String create_userId;//�����û�
	private String org_code;//��֯����
	private String sale_group;//������֯-
	private String distri_channel;//��������-
	private String product_group;//��Ʒ��-
	private String nameAddress;
	
	public String getNameAddress() {
		return nameAddress;
	}
	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public long getCus_partner() {
		return cus_partner;
	}
	public void setCus_partner(long cus_partner) {
		this.cus_partner = cus_partner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getSales_org() {
		return sales_org;
	}
	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}
	public String getSale_group() {
		return sale_group;
	}
	public void setSale_group(String sale_group) {
		this.sale_group = sale_group;
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

	
	
}
