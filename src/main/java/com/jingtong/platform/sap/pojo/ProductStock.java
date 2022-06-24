package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 *��Ʒ���
 */
public class ProductStock extends SearchInfo{

	
	private static final long serialVersionUID = 8549713737278677795L;

	private long id;
	private String werks;//��������
	private String lgort;//��λ����
	private String matnr;//���ϱ���
	private String unit;//��λ
	private float stocknum;//�������
	private float bigmg;//����(��λ)
	private String bigme;//���۵�λ(��λ)
	private String createuser;//������
	private Date createdate;//��������
	private String modifyuser;//�޸���
	private Date modifydate;//�޸�ʱ��
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWerks() {
		return werks;
	}
	public void setWerks(String werks) {
		this.werks = werks;
	}
	public String getLgort() {
		return lgort;
	}
	public void setLgort(String lgort) {
		this.lgort = lgort;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getStocknum() {
		return stocknum;
	}
	public void setStocknum(float stocknum) {
		this.stocknum = stocknum;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	public float getBigmg() {
		return bigmg;
	}
	public void setBigmg(float bigmg) {
		this.bigmg = bigmg;
	}
	public String getBigme() {
		return bigme;
	}
	public void setBigme(String bigme) {
		this.bigme = bigme;
	}
	@Override
	public String toString() {
		return "ProductStock [werks=" + werks + ", lgort=" + lgort + ", matnr="
				+ matnr + ", unit=" + unit + ", stocknum=" + stocknum
				+ ", bigmg=" + bigmg + ", bigme=" + bigme + "]";
	}
	
}
