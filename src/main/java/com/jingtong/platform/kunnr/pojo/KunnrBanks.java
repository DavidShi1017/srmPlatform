package com.jingtong.platform.kunnr.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * �ʹ﷽
 * 
 * @author xxping
 * 
 */
public class KunnrBanks extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private Long id;
	private String kunnr;
	private String kunnrName;
	private String banktype;// ��������
	private String bankno;// ���к���
	private String name1;// ������
	private String mobile;// ��ϵ��ʽ

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

	public String getBanktype() {
		return banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
