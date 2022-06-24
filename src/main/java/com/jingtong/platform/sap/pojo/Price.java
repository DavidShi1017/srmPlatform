package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Price extends SearchInfo{


	private static final long serialVersionUID = -3386093021023024761L;
	
	private long id;
	private String type;//������������-
	private String remark;//������������
	private String salesOrg;//������֯
	private String materialNumber;//���Ϻ�-
	private String customerNumber;//�ͻ����
	private String price;//�۸�
	private String ratioUnit;//���ʵ�λ�����ң�
	private String priceUnit;//�������۵�λ�����۵�λ��
	private String conditionUnit;//������λ��ÿ��
	private Date startDate;//��Ч��ʼʱ��
	private Date endDate;//��Ч����ʱ��
	private String officeId;//
	private Date create_time;
	
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getConditionUnit() {
		return conditionUnit;
	}
	public void setConditionUnit(String conditionUnit) {
		this.conditionUnit = conditionUnit;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	
	
	
	
	
 
}
