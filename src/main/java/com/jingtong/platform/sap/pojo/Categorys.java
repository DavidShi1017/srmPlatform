package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Categorys extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -103653753705237866L;
	private String categoryNumber; // ���ϱ���
	private String categoryName; // ��������
	private String categoryType; // ��� 1����Ʒ 2����Ʒ
	private String categoryUnit; // ��λ
	private float categoryweight; //����
	private String categorySku;	//����
	private float categoryVolume;	//���
	private Double categoryPrice; // ����
	private String state; // ״̬
	private Date modifyDate; // �޸�ʱ��
	private Date createDate;// ����ʱ��
	private String creater;// ������
	private String modifier; // �޸���
	private String vekmeTxt;	//���۵�λ
	private String categorylevel; //����
	private String umrez;	//����������λת������
	public String getCategoryNumber() {
		return categoryNumber;
	}
	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryUnit() {
		return categoryUnit;
	}
	public void setCategoryUnit(String categoryUnit) {
		this.categoryUnit = categoryUnit;
	}
	public float getCategoryweight() {
		return categoryweight;
	}
	public void setCategoryweight(float categoryweight) {
		this.categoryweight = categoryweight;
	}
	public String getCategorySku() {
		return categorySku;
	}
	public void setCategorySku(String categorySku) {
		this.categorySku = categorySku;
	}
	public float getCategoryVolume() {
		return categoryVolume;
	}
	public void setCategoryVolume(float categoryVolume) {
		this.categoryVolume = categoryVolume;
	}
	public Double getCategoryPrice() {
		return categoryPrice;
	}
	public void setCategoryPrice(Double categoryPrice) {
		this.categoryPrice = categoryPrice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getVekmeTxt() {
		return vekmeTxt;
	}
	public void setVekmeTxt(String vekmeTxt) {
		this.vekmeTxt = vekmeTxt;
	}
	public String getCategorylevel() {
		return categorylevel;
	}
	public void setCategorylevel(String categorylevel) {
		this.categorylevel = categorylevel;
	}
	public String getUmrez() {
		return umrez;
	}
	public void setUmrez(String umrez) {
		this.umrez = umrez;
	}
	
}
