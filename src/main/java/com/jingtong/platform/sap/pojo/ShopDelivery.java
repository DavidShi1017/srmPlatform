package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ShopDelivery extends SearchInfo {

	/**
	 * ������
	 */
	private static final long serialVersionUID = -2253330082939333627L;
	private long id;
	private String crmOrderId;
	private String sapOderId;
	private String vbeln;// ��������
	private String vgpos;//SAP��������Ŀ��
 	
	private String kunnr_ag;// �۴﷽
	private String kunnr_we;// �ʹ﷽
	private String sendAddress;// �ͻ���ַ
	private String linkMan;// ��ϵ��(ʲô��ϵ��)
	private String linkPhone;// ��ϵ�绰
	private String billId;// ��Ʊƾ֤
	private Date planToDate;// �ƻ���������
	private Date realSendDate;// ʵ�ʷ�������
	private Date planLoadDate;// �ƻ�װ������
	private String creditId;// ���ñ���
	private String loadPointId;// װ�˵�
	private String shipAgent;// ���˴���
	private String erdat;// ��������
	private String posnr;// �к�
	private String matnr;// ����
	private String werks;// ����
	private String lgort;// ����
	private String arktx;// ���۶�����Ŀ���ı�
	private String brgew;// ����
	private String volum;// ���
	private String lfimg;// ����
	private String vrkme;// ��λ
	private String dele;// ɾ����ʾ
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
	private String status;// 0 ��ʼ����1 ������϶�������������
 	private String price;
	
	 
	 

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVgpos() {
		return vgpos;
	}

	public void setVgpos(String vgpos) {
		this.vgpos = vgpos;
	}

	public String getKunnr_ag() {
		return kunnr_ag;
	}

	public void setKunnr_ag(String kunnr_ag) {
		this.kunnr_ag = kunnr_ag;
	}

	public String getKunnr_we() {
		return kunnr_we;
	}

	public void setKunnr_we(String kunnr_we) {
		this.kunnr_we = kunnr_we;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Date getPlanToDate() {
		return planToDate;
	}

	public void setPlanToDate(Date planToDate) {
		this.planToDate = planToDate;
	}

	public Date getRealSendDate() {
		return realSendDate;
	}

	public void setRealSendDate(Date realSendDate) {
		this.realSendDate = realSendDate;
	}

	public Date getPlanLoadDate() {
		return planLoadDate;
	}

	public void setPlanLoadDate(Date planLoadDate) {
		this.planLoadDate = planLoadDate;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public String getLoadPointId() {
		return loadPointId;
	}

	public void setLoadPointId(String loadPointId) {
		this.loadPointId = loadPointId;
	}

	public String getShipAgent() {
		return shipAgent;
	}

	public void setShipAgent(String shipAgent) {
		this.shipAgent = shipAgent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCrmOrderId() {
		return crmOrderId;
	}

	public void setCrmOrderId(String crmOrderId) {
		this.crmOrderId = crmOrderId;
	}

	public String getSapOderId() {
		return sapOderId;
	}

	public void setSapOderId(String sapOderId) {
		this.sapOderId = sapOderId;
	}

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public String getErdat() {
		return erdat;
	}

	public void setErdat(String erdat) {
		this.erdat = erdat;
	}

	public String getPosnr() {
		return posnr;
	}

	public void setPosnr(String posnr) {
		this.posnr = posnr;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
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

	public String getArktx() {
		return arktx;
	}

	public void setArktx(String arktx) {
		this.arktx = arktx;
	}

	public String getBrgew() {
		return brgew;
	}

	public void setBrgew(String brgew) {
		this.brgew = brgew;
	}

	public String getVolum() {
		return volum;
	}

	public void setVolum(String volum) {
		this.volum = volum;
	}

	public String getLfimg() {
		return lfimg;
	}

	public void setLfimg(String lfimg) {
		this.lfimg = lfimg;
	}

	public String getVrkme() {
		return vrkme;
	}

	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}

	public String getDele() {
		return dele;
	}

	public void setDele(String dele) {
		this.dele = dele;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * �ӿڲ���
	 */
	private String s_vbeln;// S_VBELN Table ��������
	private String s_vstel;// S_VSTEL Table װ�˵�
	private String s_kunnr;// S_KUNNR Table �ʹ﷽
	private String s_tddat;// S_TDDAT Table �ƻ�����(ͬ���������ڣ���ʼʱ�����)
	private String s_wadat_ist;// S_WADAT_IST Table ��������
	private String s_lifnr;// S_LIFNR Table ���˴���
	private String s_ernam;// S_ERNAM Table ������
	private String s_erdat;// S_ERDAT Table ��������
	private String s_kodat;// S_KODAT Table ��������
	private String s_bolnr;// S_BOLNR Table ��������

	public String getS_vbeln() {
		return s_vbeln;
	}

	public void setS_vbeln(String s_vbeln) {
		this.s_vbeln = s_vbeln;
	}

	public String getS_vstel() {
		return s_vstel;
	}

	public void setS_vstel(String s_vstel) {
		this.s_vstel = s_vstel;
	}

	public String getS_kunnr() {
		return s_kunnr;
	}

	public void setS_kunnr(String s_kunnr) {
		this.s_kunnr = s_kunnr;
	}

	public String getS_tddat() {
		return s_tddat;
	}

	public void setS_tddat(String s_tddat) {
		this.s_tddat = s_tddat;
	}

	public String getS_wadat_ist() {
		return s_wadat_ist;
	}

	public void setS_wadat_ist(String s_wadat_ist) {
		this.s_wadat_ist = s_wadat_ist;
	}

	public String getS_lifnr() {
		return s_lifnr;
	}

	public void setS_lifnr(String s_lifnr) {
		this.s_lifnr = s_lifnr;
	}

	public String getS_ernam() {
		return s_ernam;
	}

	public void setS_ernam(String s_ernam) {
		this.s_ernam = s_ernam;
	}

	public String getS_erdat() {
		return s_erdat;
	}

	public void setS_erdat(String s_erdat) {
		this.s_erdat = s_erdat;
	}

	public String getS_kodat() {
		return s_kodat;
	}

	public void setS_kodat(String s_kodat) {
		this.s_kodat = s_kodat;
	}

	public String getS_bolnr() {
		return s_bolnr;
	}

	public void setS_bolnr(String s_bolnr) {
		this.s_bolnr = s_bolnr;
	}

}
