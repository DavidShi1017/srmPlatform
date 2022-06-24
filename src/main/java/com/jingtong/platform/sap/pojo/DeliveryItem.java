package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class DeliveryItem extends SearchInfo{

private static final long serialVersionUID = 3494761267120341239L;
	
	private long id;
	private String salesDocument;//����ƾ֤
	private long salesDocumentItem;//����ƾ֤��Ŀ
	private String materialNumber;//���Ϻ�
	private double totalQty;//�����۵�λ��ʾ���ۼƶ�������
	private double salesUnit;//���۵�λ 
	private String txt;//��Ŀ�ı�
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSalesDocument() {
		return salesDocument;
	}
	public void setSalesDocument(String salesDocument) {
		this.salesDocument = salesDocument;
	}
	public long getSalesDocumentItem() {
		return salesDocumentItem;
	}
	public void setSalesDocumentItem(long salesDocumentItem) {
		this.salesDocumentItem = salesDocumentItem;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public double getSalesUnit() {
		return salesUnit;
	}
	public void setSalesUnit(double salesUnit) {
		this.salesUnit = salesUnit;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	
	
	
	
}
