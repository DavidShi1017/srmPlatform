package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * �����������Ա�
 * 
 */

public class KunnrAttribute extends SearchInfo {

	/**
	 * ��������kunnr_name ��Ϊ�˺����ݿ��ֶ�һ��
	 */
	private static final long serialVersionUID = -5575029897477626479L;
	private Long id;
	private String kunnr;
	private String kunnr_name;
	private Date  create_date;	//	����ʱ��
	private String  create_user;	//	������
	private Date modify_date;	//	�޸�ʱ��
	private String  modify_user;	//	�޸���
	private String state="1";	//	״̬ 
	
	private String sql;//���ڶ�̬�в�ѯ
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
 
	public String getKunnr_name() {
		return kunnr_name;
	}
	public void setKunnr_name(String kunnr_name) {
		this.kunnr_name = kunnr_name;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
