package com.jingtong.platform.kunnr.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ���۷�Χ
 * 
 * @author ljwang
 * 
 */
public class KunnrSalesArea extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private Long id;
	private String kunnr;
	private String kunnrName;
	private String vkorg;          //������֯
	private String vkorgTxt;       
	private String spart;           //��Ʒ��
	private String spartTxt;
	private String werks;         //��������
	private String werksTxt;
	private String vsbed;          //װ������
	private String vsbedTxt;
	private String vtweg;          //��������
	private String vtwegTxt;      //������������
	private String createDate;     //����ʱ��
	private String lastModifyDate;      //ʱ���
	
	private String sales_province; //ʡ
	private String sales_city; //��
	private String sales_area; //��
	private String sales_town; //��
	
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
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
	public String getVsbed() {
		return vsbed;
	}
	public void setVsbed(String vsbed) {
		this.vsbed = vsbed;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getVtwegTxt() {
		return vtwegTxt;
	}
	public void setVtwegTxt(String vtwegTxt) {
		this.vtwegTxt = vtwegTxt;
	}
	public String getSpartTxt() {
		return spartTxt;
	}
	public void setSpartTxt(String spartTxt) {
		this.spartTxt = spartTxt;
	}
	public String getWerksTxt() {
		return werksTxt;
	}
	public void setWerksTxt(String werksTxt) {
		this.werksTxt = werksTxt;
	}
	public String getVsbedTxt() {
		return vsbedTxt;
	}
	public void setVsbedTxt(String vsbedTxt) {
		this.vsbedTxt = vsbedTxt;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVkorgTxt() {
		return vkorgTxt;
	}
	public void setVkorgTxt(String vkorgTxt) {
		this.vkorgTxt = vkorgTxt;
	}
	public String getSales_province() {
		return sales_province;
	}
	public void setSales_province(String sales_province) {
		this.sales_province = sales_province;
	}
	public String getSales_city() {
		return sales_city;
	}
	public void setSales_city(String sales_city) {
		this.sales_city = sales_city;
	}
	public String getSales_area() {
		return sales_area;
	}
	public void setSales_area(String sales_area) {
		this.sales_area = sales_area;
	}
	public String getSales_town() {
		return sales_town;
	}
	public void setSales_town(String sales_town) {
		this.sales_town = sales_town;
	}
}
