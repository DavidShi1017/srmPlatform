package com.jingtong.platform.sap.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Kunnr extends SearchInfo{

	/**
	 * �ͻ�������
	 */
	private static final long serialVersionUID = -3386093021023024761L;
 
	private long id;
	private String customer_name;//�ͻ�����
	private String customer_code;//�ͻ�����
	private String country;//���Ҵ���
	private String district;//���۵���
	private String sale_office;//���۲���
	private String sales_org;//������֯
	private String nielsen;//Nielsen��ʶ�����룩
	private String address;//��ַ
	private String state;//�����ʶ
	private String name1;//����1
	private String tele1;//��һ���绰����
	private String nameFoemat;//Ա���������˵ĸ�ʽ���� 
	private String currency_code;//������
	private String remark;//
	private String Customer_type;//
	private String nielsenRemark;//Nielsen��ʶ���������ƣ�
	
	private String syncUser;
	private Date syncTime; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSale_office() {
		return sale_office;
	}
	public void setSale_office(String sale_office) {
		this.sale_office = sale_office;
	}
	public String getSales_org() {
		return sales_org;
	}
	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
	}
	public String getNielsen() {
		return nielsen;
	}
	public void setNielsen(String nielsen) {
		this.nielsen = nielsen;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getTele1() {
		return tele1;
	}
	public void setTele1(String tele1) {
		this.tele1 = tele1;
	}
	public String getNameFoemat() {
		return nameFoemat;
	}
	public void setNameFoemat(String nameFoemat) {
		this.nameFoemat = nameFoemat;
	}
	
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustomer_type() {
		return Customer_type;
	}
	public void setCustomer_type(String customer_type) {
		Customer_type = customer_type;
	}
	public String getNielsenRemark() {
		return nielsenRemark;
	}
	public void setNielsenRemark(String nielsenRemark) {
		this.nielsenRemark = nielsenRemark;
	}
	public String getSyncUser() {
		return syncUser;
	}
	public void setSyncUser(String syncUser) {
		this.syncUser = syncUser;
	}
	public Date getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}
	
	
 	
	
}
