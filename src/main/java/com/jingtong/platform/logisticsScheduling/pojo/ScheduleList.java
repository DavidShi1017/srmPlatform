package com.jingtong.platform.logisticsScheduling.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * װ���嵥��������
 * 
 * @author xujiakun
 * 
 */
public class ScheduleList extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	/**
	 * id
	 */
	private Long id;
	/**
	 * װ�˵���
	 */
	private String  bolnr;

	/**
	 * װ�˵�
	 */
	private String  vstel;

	/**
	 * ����
	 */
	private double brgew=0d;
	/**
	 * ��ʼʱ��
	 */
	private String workstart_date;
	/**
	 * ����ʱ��
	 */
	private String workend_date;
	/**���˶�*
	 */
	private String team;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBolnr() {
		return bolnr;
	}
	public void setBolnr(String bolnr) {
		this.bolnr = bolnr;
	}
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	public double getBrgew() {
		return brgew;
	}
	public void setBrgew(double brgew) {
		this.brgew = brgew;
	}
	public String getWorkstart_date() {
		return workstart_date;
	}
	public void setWorkstart_date(String workstart_date) {
		this.workstart_date = workstart_date;
	}
	public String getWorkend_date() {
		return workend_date;
	}
	public void setWorkend_date(String workend_date) {
		this.workend_date = workend_date;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	

}
