package com.jingtong.platform.logisticsScheduling.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * װ�˵�
 * 
 * @author xujiakun
 * 
 */
public class ScheduleSmtor extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	/**
	 * id
	 */
	private Long id;
	private String smtorIds;
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
	 * �˵����ȼ�
	 */
	private String bolnr_level;
	/**
	 * �ص����ȼ�
	 */
	private String location_level;
	/**״̬0 δ�ŵ� ��1 �Ѿ��ŵ�**/
	private String states;
	/**���� 1����**/
	private String is_lock;
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
	public String getBolnr_level() {
		return bolnr_level;
	}
	public void setBolnr_level(String bolnr_level) {
		this.bolnr_level = bolnr_level;
	}
	public String getLocation_level() {
		return location_level;
	}
	public void setLocation_level(String location_level) {
		this.location_level = location_level;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getSmtorIds() {
		return smtorIds;
	}
	public void setSmtorIds(String smtorIds) {
		this.smtorIds = smtorIds;
	}
	public String getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(String is_lock) {
		this.is_lock = is_lock;
	}
	

}
