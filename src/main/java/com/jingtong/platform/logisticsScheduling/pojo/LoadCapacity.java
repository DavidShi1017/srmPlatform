package com.jingtong.platform.logisticsScheduling.pojo;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * �˵�ʵ��
 * 
 * @author xujiakun
 * 
 */
public class LoadCapacity extends SearchInfo {

	private static final long serialVersionUID = -3155216126617302268L;

	/**
	 * id
	 */
	private Long id;
	/**
	 * װ�˵�
	 */
	private String  vstel;

	/**
	 * ���˶�
	 */
	private String hand_team;

	/**
	 * ��ʼʱ��
	 */
	private String start_date;
	/**
	 * ����ʱ��
	 */
	private String end_date;
	/**װж����    װж��/Сʱ
	 * 
	 */
	private double scheduling=0D;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVstel() {
		return vstel;
	}
	public void setVstel(String vstel) {
		this.vstel = vstel;
	}
	
	public double getScheduling() {
		return scheduling;
	}
	public void setScheduling(double scheduling) {
		this.scheduling = scheduling;
	}
	public String getHand_team() {
		return hand_team;
	}
	public void setHand_team(String hand_team) {
		this.hand_team = hand_team;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	

}
