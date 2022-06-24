package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;


public class PriceDetail extends SearchInfo{
	
	private static final long serialVersionUID = -3386093021023024761L;
	
	
	
	private String id;
	private String type;//������������
	private String salesOrg;//������֯ 
	private String materialNumber;//���Ϻ�
	private String customerNumber;//�ͻ����
	private String price;//�۸�( ��������ٷ��� )
	private String ratioUnit;//���ʵ�λ(���һ�ٷ���)
	private String priceUnit;//�������۵�λ 
	private String termUnit;//������λ
	private Date startDate;//��Ч��ʼ����  
	private Date endDate;//��Ч��������
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRatioUnit() {
		return ratioUnit;
	}
	public void setRatioUnit(String ratioUnit) {
		this.ratioUnit = ratioUnit;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public String getTermUnit() {
		return termUnit;
	}
	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
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
	
	
	
	
	
}
