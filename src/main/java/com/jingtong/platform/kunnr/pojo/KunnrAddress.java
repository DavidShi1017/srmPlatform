package com.jingtong.platform.kunnr.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * 送达方
 * 
 * @author xxping
 * 
 */
public class KunnrAddress extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217329092938365378L;
	private Long id;
	private String kunnr;
	private String kunnrName;
	private String kunnrSd;// 送达方
	private String address;
	private String name;
	private String telephone;
	private String mobile;
	private String maximum;      //最大通行车型      add 2013/08/12
	private String maximumTxt;

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getKunnrSd() {
		return kunnrSd;
	}

	public void setKunnrSd(String kunnrSd) {
		this.kunnrSd = kunnrSd;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getMaximumTxt() {
		return maximumTxt;
	}

	public void setMaximumTxt(String maximumTxt) {
		this.maximumTxt = maximumTxt;
	}

}
