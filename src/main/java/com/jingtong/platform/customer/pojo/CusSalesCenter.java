package com.jingtong.platform.customer.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * �ͻ���������
 * @author yw
 *
 */
public class CusSalesCenter  extends SearchInfo{
	private long id;
	private String customer_code;//�ͻ�����
	private long main_id;//��������id
	private String sales_center_id;//�������ı��루�����������ı�飩
	private String sales_center_name;//�����������ƣ������������ı�飩
	private int sync_state;//ͬ��״̬��1ͬ����0δͬ����
	private Date sync_time;//ͬ��ʱ��
	private String sync_userId;//ͬ���û�
	private String sysnc_exception;	//ͬ���쳣
	private Date create_time;//����ʱ��
	private String create_userId;//�����û�
	private String org_code;//��֯����
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
	public String getSales_center_id() {
		return sales_center_id;
	}
	public void setSales_center_id(String sales_center_id) {
		this.sales_center_id = sales_center_id;
	}
	public String getSales_center_name() {
		return sales_center_name;
	}
	public void setSales_center_name(String sales_center_name) {
		this.sales_center_name = sales_center_name;
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
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	
	
	
	
}
