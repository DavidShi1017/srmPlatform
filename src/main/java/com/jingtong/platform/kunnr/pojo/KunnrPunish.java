package com.jingtong.platform.kunnr.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ��������Υ���¼
 * 
 * @author mk
 * 
 */

public class KunnrPunish extends SearchInfo {

 	private static final long serialVersionUID = -5575029897477626479L;
	private Long punish_id ;//number(9) not null primary key,�����̴�����¼����
    private String kunnr;// varchar2(20) not null,�����̱���
    private String violations;// varchar2(50),�����¼�
    private String penalty_amount;// varchar2(20),�������
    private Date bad_time; //date,����ʱ��
    private String creator;// varchar2(20), ������
    private Date create_date;// date ����ʱ��
    
    
	public Long getPunish_id() {
		return punish_id;
	}
	public void setPunish_id(Long punish_id) {
		this.punish_id = punish_id;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getViolations() {
		return violations;
	}
	public void setViolations(String violations) {
		this.violations = violations;
	}
	 
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getPenalty_amount() {
		return penalty_amount;
	}
	public void setPenalty_amount(String penalty_amount) {
		this.penalty_amount = penalty_amount;
	}
	public Date getBad_time() {
		return bad_time;
	}
	public void setBad_time(Date bad_time) {
		this.bad_time = bad_time;
	}
	
}
