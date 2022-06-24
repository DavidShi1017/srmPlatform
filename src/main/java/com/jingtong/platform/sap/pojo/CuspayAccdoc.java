package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * �Զ������ͻ��ؿ���ƾ֤�ӿڲ�����
 */
public class CuspayAccdoc  extends SearchInfo{

	
	private static final long serialVersionUID = 1L;
	/*********�������**********/
	private String p_bukrs;//��˾����
	private String p_kunnr;//�ͻ�����
	private double p_dmbtr=0;//���
	private String p_belnr_hk;//�ֽ�ƾ֤����
	private String p_gjahr_hk;//�ֽ�ƾ֤������
	private String p_bukrs_hk;//�ֽ�ƾ֤��˾����
	private String p_usnam;//�û���
	private String p_xref1;//�ؿ�����
	
	
	/*********���ؽ��**********/
	private String retcode;//����ֵ��0-û�д���1-�д���
	private String retmsg;//������Ϣ
	private String l_belnr;//���ƾ֤���
	
	
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public String getL_belnr() {
		return l_belnr;
	}
	public void setL_belnr(String l_belnr) {
		this.l_belnr = l_belnr;
	}
	public String getP_bukrs() {
		return p_bukrs;
	}
	public void setP_bukrs(String p_bukrs) {
		this.p_bukrs = p_bukrs;
	}
	public String getP_kunnr() {
		return p_kunnr;
	}
	public void setP_kunnr(String p_kunnr) {
		this.p_kunnr = p_kunnr;
	}
	public double getP_dmbtr() {
		return p_dmbtr;
	}
	public void setP_dmbtr(double p_dmbtr) {
		this.p_dmbtr = p_dmbtr;
	}
	public String getP_belnr_hk() {
		return p_belnr_hk;
	}
	public void setP_belnr_hk(String p_belnr_hk) {
		this.p_belnr_hk = p_belnr_hk;
	}
	public String getP_gjahr_hk() {
		return p_gjahr_hk;
	}
	public void setP_gjahr_hk(String p_gjahr_hk) {
		this.p_gjahr_hk = p_gjahr_hk;
	}
	public String getP_bukrs_hk() {
		return p_bukrs_hk;
	}
	public void setP_bukrs_hk(String p_bukrs_hk) {
		this.p_bukrs_hk = p_bukrs_hk;
	}
	public String getP_usnam() {
		return p_usnam;
	}
	public void setP_usnam(String p_usnam) {
		this.p_usnam = p_usnam;
	}
	public String getP_xref1() {
		return p_xref1;
	}
	public void setP_xref1(String p_xref1) {
		this.p_xref1 = p_xref1;
	}
	@Override
	public String toString() {
		return "CuspayAccdoc [p_bukrs=" + p_bukrs + ", p_kunnr=" + p_kunnr
				+ ", p_dmbtr=" + p_dmbtr + ", p_belnr_hk=" + p_belnr_hk
				+ ", p_gjahr_hk=" + p_gjahr_hk + ", p_bukrs_hk=" + p_bukrs_hk
				+ ", p_usnam=" + p_usnam + ", p_xref1=" + p_xref1
				+ ", retcode=" + retcode + ", retmsg=" + retmsg + ", l_belnr="
				+ l_belnr + "]";
	}
	
}
