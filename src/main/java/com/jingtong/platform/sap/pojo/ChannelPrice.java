package com.jingtong.platform.sap.pojo;

import java.sql.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ChannelPrice extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3494761267120341239L;

	private long id;
	private String vkorg;	//������֯
	private String vtweg;	//��������
	private String matnr;	//���Ϻ�
	private Date datab;		//��Ч��ʼ����
	private Date datbi;		//��Ч��ֹ����
 	private float kbetr;	//�۸�
 	private String konwa;	//���ʵ�λ
 	private float kpein;		//�������۵�λ
	private String kmein;	//������λ
	private String kfrst;	//��׼״̬
	private String kunnr;	//�ͻ�����
	private String maktx;	//��������
	private String channelPriceType;	//0: �����۸�1: �ͻ��۸�
	private Date createDate;
	private String createUser;
	private Date modifyDate;
	private String modifyUser; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public Date getDatab() {
		return datab;
	}
	public void setDatab(Date datab) {
		this.datab = datab;
	}
	public Date getDatbi() {
		return datbi;
	}
	public void setDatbi(Date datbi) {
		this.datbi = datbi;
	}
	public float getKbetr() {
		return kbetr;
	}
	public void setKbetr(float kbetr) {
		this.kbetr = kbetr;
	}
	 
	public String getKonwa() {
		return konwa;
	}
	public void setKonwa(String konwa) {
		this.konwa = konwa;
	}
	public float getKpein() {
		return kpein;
	}
	public void setKpein(float kpein) {
		this.kpein = kpein;
	}
	public String getKmein() {
		return kmein;
	}
	public void setKmein(String kmein) {
		this.kmein = kmein;
	}
	public String getKfrst() {
		return kfrst;
	}
	public void setKfrst(String kfrst) {
		this.kfrst = kfrst;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getMaktx() {
		return maktx;
	}
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	public String getChannelPriceType() {
		return channelPriceType;
	}
	public void setChannelPriceType(String channelPriceType) {
		this.channelPriceType = channelPriceType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
 	
}
