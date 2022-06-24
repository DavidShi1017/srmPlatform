package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class KunnrCompany extends SearchInfo{

	/**
	 * �ͻ�����ϵ
	 */
	private static final long serialVersionUID = -3386093021023024761L;
	
	private long id;
	private String customer_code;//�ͻ�����
	private String sales_org;//������֯
	private String distri_channel;//��������-00
	private String product_group;//��Ʒ��-00
	private String partnerType;//������鹦�ܣ�������ͣ�-
	private String partner_num;//������������
	private String partnerId;//�������Ŀͻ��ţ������룩-
	private String partnerName;//���ƣ�������ƣ�
	private String salesId;//��Ա��ţ�����
	private String nameFoemat;//Ա���������˵ĸ�ʽ����������
	private String address;
	
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getSales_org() {
		return sales_org;
	}
	public void setSales_org(String sales_org) {
		this.sales_org = sales_org;
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
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getPartner_num() {
		return partner_num;
	}
	public void setPartner_num(String partner_num) {
		this.partner_num = partner_num;
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
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getNameFoemat() {
		return nameFoemat;
	}
	public void setNameFoemat(String nameFoemat) {
		this.nameFoemat = nameFoemat;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
 
}
