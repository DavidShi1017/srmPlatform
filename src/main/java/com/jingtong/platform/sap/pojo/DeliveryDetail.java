package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DeliveryDetail extends SearchInfo{

	private static final long serialVersionUID = 3494761267120341239L;
	
	

	private long id;
	private String distDocument;//���ۺͷ���ƾ֤�� 
	private long distDocumentItem;//���ۺͷ���ƾ֤����Ŀ��
	private long deliveryPlanNum;//�����ƻ��к�
	private String deliveryPlanDate;//�ƻ������� 
	private double updateQty;//�����۵�λ�Ƶ��������� 
	private double netPrice;//����
	private double totalPrice;//����Ŀ�ܼ�
	private double taxPrice;//��˰����
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDistDocument() {
		return distDocument;
	}
	public void setDistDocument(String distDocument) {
		this.distDocument = distDocument;
	}
	public long getDistDocumentItem() {
		return distDocumentItem;
	}
	public void setDistDocumentItem(long distDocumentItem) {
		this.distDocumentItem = distDocumentItem;
	}
	public long getDeliveryPlanNum() {
		return deliveryPlanNum;
	}
	public void setDeliveryPlanNum(long deliveryPlanNum) {
		this.deliveryPlanNum = deliveryPlanNum;
	}
	public String getDeliveryPlanDate() {
		return deliveryPlanDate;
	}
	public void setDeliveryPlanDate(String deliveryPlanDate) {
		this.deliveryPlanDate = deliveryPlanDate;
	}
	public double getUpdateQty() {
		return updateQty;
	}
	public void setUpdateQty(double updateQty) {
		this.updateQty = updateQty;
	}
	public double getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}
	
	
	
	
	
}