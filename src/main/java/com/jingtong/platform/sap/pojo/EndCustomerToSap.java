package com.jingtong.platform.sap.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * �ն˿ͻ�������
 * 
 *
 */

public class EndCustomerToSap extends SearchInfo{
	private long id;
	private String disti_customer_id;//�ͻ����
	private String disti_customer_name;//�ͻ�����
	private String disti_groupId;//�ն˿ͻ����ű���
	private String endCustomerGroupName;//�ն˿ͻ���������
	private String end_customer_id;//�ն˿ͻ�����
	private String end_customer_name;//�ն˿ͻ�����
	private String customer_type;//�ն˿ͻ�����
	private String customer_property;//�ն˿ͻ�����
	private String currency_code;//���Ҵ���
	private String country;//���Ҵ���
	private String province;//����
	private String address;//��ַ
	private String contact_name;//��ϵ��
	private String tel;//�绰
	private String createUserId;//
	private String createTime;//
	private String state;//״̬ 0�½���1���ͨ����2���δ����3���ᣬ4�ⶳ-
	
	private String[] ids;//��ѯ����

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDisti_customer_id() {
		return disti_customer_id;
	}

	public void setDisti_customer_id(String disti_customer_id) {
		this.disti_customer_id = disti_customer_id;
	}

	public String getDisti_customer_name() {
		return disti_customer_name;
	}

	public void setDisti_customer_name(String disti_customer_name) {
		this.disti_customer_name = disti_customer_name;
	}

	public String getDisti_groupId() {
		return disti_groupId;
	}

	public void setDisti_groupId(String disti_groupId) {
		this.disti_groupId = disti_groupId;
	}

	public String getEndCustomerGroupName() {
		return endCustomerGroupName;
	}

	public void setEndCustomerGroupName(String endCustomerGroupName) {
		this.endCustomerGroupName = endCustomerGroupName;
	}

	public String getEnd_customer_id() {
		return end_customer_id;
	}

	public void setEnd_customer_id(String end_customer_id) {
		this.end_customer_id = end_customer_id;
	}

	public String getEnd_customer_name() {
		return end_customer_name;
	}

	public void setEnd_customer_name(String end_customer_name) {
		this.end_customer_name = end_customer_name;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getCustomer_property() {
		return customer_property;
	}

	public void setCustomer_property(String customer_property) {
		this.customer_property = customer_property;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	
	
	
	
}
