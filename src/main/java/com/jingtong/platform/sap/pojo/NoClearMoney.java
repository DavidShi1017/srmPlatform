package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * ��ȡδ���ֽ�ƾ֤�嵥
 */
public class NoClearMoney  extends SearchInfo{

	
	private static final long serialVersionUID = 1L;
	/*********���ز���**********/
	private long id;
	private String bukrs;//��˾����
	private String gjahr;//������
	private String belnr;//���ƾ֤��
	private String buzei;//����Ŀ
	private String hkont;//��ƿ�Ŀ
	private Date budat;//��������
	private String zuonr;//�����
	private double dmbtr;//���
	private String remark;//��ע
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBukrs() {
		return bukrs;
	}
	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}
	public String getGjahr() {
		return gjahr;
	}
	public void setGjahr(String gjahr) {
		this.gjahr = gjahr;
	}
	public String getBelnr() {
		return belnr;
	}
	public void setBelnr(String belnr) {
		this.belnr = belnr;
	}
	public String getBuzei() {
		return buzei;
	}
	public void setBuzei(String buzei) {
		this.buzei = buzei;
	}
	public String getHkont() {
		return hkont;
	}
	public void setHkont(String hkont) {
		this.hkont = hkont;
	}
	public Date getBudat() {
		return budat;
	}
	public void setBudat(Date budat) {
		this.budat = budat;
	}
	public String getZuonr() {
		return zuonr;
	}
	public void setZuonr(String zuonr) {
		this.zuonr = zuonr;
	}
	public double getDmbtr() {
		return dmbtr;
	}
	public void setDmbtr(double dmbtr) {
		this.dmbtr = dmbtr;
	}
	  
}
