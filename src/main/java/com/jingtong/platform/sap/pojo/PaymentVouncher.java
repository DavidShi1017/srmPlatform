package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class PaymentVouncher extends SearchInfo {

	/**
	 * �ؿ�ƾ֤��
	 */
	private String paymentCode;
	/**
	 * �ؿ����
	 */
	private String paymentYear;
	/**
	 * �ؿ���
	 */
	private double paymentMoney;
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentYear() {
		return paymentYear;
	}
	public void setPaymentYear(String paymentYear) {
		this.paymentYear = paymentYear;
	}
	public double getPaymentMoney() {
		return paymentMoney;
	}
	public void setPaymentMoney(double paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

 
}
