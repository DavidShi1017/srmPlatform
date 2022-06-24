package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class SampleOrderDetail extends SearchInfo{
	
	private static final long serialVersionUID = -3386093021023024761L;
	
	private long id;
	private String salesId;//����ƾ֤
	private String row_no;//����ƾ֤��Ŀ
	private String material_id;//���Ϻ�
	private String order_QTY;//�����۵�λ��ʾ���ۼƶ�������
	private String sale_unit;//���۵�λ 
	private String remark;//��Ŀ�ı�
	private Date delivery_date;//�ƻ�����
	private String delivery_dateStr; 
	private String po_item;
	
	
	private long main_id;// ��������ID
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getRow_no() {
		return row_no;
	}
	public void setRow_no(String row_no) {
		this.row_no = row_no;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getOrder_QTY() {
		return order_QTY;
	}
	public void setOrder_QTY(String order_QTY) {
		this.order_QTY = order_QTY;
	}
	public String getSale_unit() {
		return sale_unit;
	}
	public void setSale_unit(String sale_unit) {
		this.sale_unit = sale_unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDelivery_dateStr() {
		return delivery_dateStr;
	}
	public void setDelivery_dateStr(String delivery_dateStr) {
		this.delivery_dateStr = delivery_dateStr;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
	}

	
	
	
}
