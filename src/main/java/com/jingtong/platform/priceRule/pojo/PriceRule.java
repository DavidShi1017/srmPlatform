package com.jingtong.platform.priceRule.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * �۸����
 * @author yw
 *
 */
public class PriceRule extends SearchInfo{
	private long id;
	private String org_id;//��������루Org_ID��
	private String office_id;//��������루Org_ID��
	private String material_id;//���ϱ�ţ�12NC��
	private String currency_code;//���ҵ�λ��Currency�� 
	private double sale_price;//���ۼ۸�Fieldmin��
	private String price_type;//�۸����� (FM������� ,SPֹͣ��,ZMP1�ͻ��ۣ�ZMB1�г�ͳһ��)
	private Date start_date;//��ʼʱ��(Start Date) 
	private Date end_date;//��ֹʱ�䣨End Date��
	private String customer_code;//�ͻ�����
	private String customer_name;//�ͻ�����
	private String material_name;//�������ƣ�Book Part��
	private String material_type;//�������ͣ�Product Type��
	private String basic_type;//�������ͣ�Basic Type��
	private String material_desc;//��������(Product Desc)
	private String start_dateStr;
	private String end_dateStr;
	private int state;//״̬
	private String remark;//��ע
	private int isDeleted;//�Ƿ�ɾ��
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//����ʱ��
	private String latest_userId;//������
	private String org_code;//������֯
	private String perUnit;//���ڼ۸���㣬sale_priceΪÿperUnit����λ�ļ۸�
	
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
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	public String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}
	public String getBasic_type() {
		return basic_type;
	}
	public void setBasic_type(String basic_type) {
		this.basic_type = basic_type;
	}
	public String getMaterial_desc() {
		return material_desc;
	}
	public void setMaterial_desc(String material_desc) {
		this.material_desc = material_desc;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
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
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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
	public String getPrice_type() {
		return price_type;
	}
	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getPerUnit() {
		return perUnit;
	}
	public void setPerUnit(String perUnit) {
		this.perUnit = perUnit;
	}
	public String getOffice_id() {
		return office_id;
	}
	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	
	
}
