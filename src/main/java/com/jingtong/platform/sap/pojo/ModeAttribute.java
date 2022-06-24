package com.jingtong.platform.sap.pojo;

import com.jingtong.platform.base.pojo.SearchInfo;

public class ModeAttribute extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5085077756424755887L;

	private long mprAttId;	//	����	NUMBER
	private String mprModelId;	//	ģ��ID	VARCHAR2(20)
	private String mprAttCode;	//	ģ������CODE	VARCHAR2(30)
	private String mprAttContent;	//	ģ����������	VARCHAR2(50)
	private String mprAttDataType;	//	��������	VARCHAR2(20)
	private int mprAttDataLength;	//	���ݳ���	NUMBER
	private String 	mprAttDescript;	//	ģ����������	VARCHAR2(200)
	private String 	mprAttIsnull;	//	�Ƿ��Ϊ��	VARCHAR2(2)
 	private String mprAttDataRange;	//	���ݷ�ΧSQL	VARCHAR2(100)
 	private String mprAttIscumulative;	//	�������Ƿ���Ҫ�ۼ�	VARCHAR2(2)
 	private int orderBy;	//	�����ֶ�	NUMBER(10)
 	private String mprAttKey;	//	�ֶ�����(MONEYΪ�ƻ������)	VARCHAR2(20)
	public long getMprAttId() {
		return mprAttId;
	}
	public void setMprAttId(long mprAttId) {
		this.mprAttId = mprAttId;
	}
	public String getMprModelId() {
		return mprModelId;
	}
	public void setMprModelId(String mprModelId) {
		this.mprModelId = mprModelId;
	}
	public String getMprAttCode() {
		return mprAttCode;
	}
	public void setMprAttCode(String mprAttCode) {
		this.mprAttCode = mprAttCode;
	}
	public String getMprAttContent() {
		return mprAttContent;
	}
	public void setMprAttContent(String mprAttContent) {
		this.mprAttContent = mprAttContent;
	}
	public String getMprAttDataType() {
		return mprAttDataType;
	}
	public void setMprAttDataType(String mprAttDataType) {
		this.mprAttDataType = mprAttDataType;
	}
	public int getMprAttDataLength() {
		return mprAttDataLength;
	}
	public void setMprAttDataLength(int mprAttDataLength) {
		this.mprAttDataLength = mprAttDataLength;
	}
	public String getMprAttDescript() {
		return mprAttDescript;
	}
	public void setMprAttDescript(String mprAttDescript) {
		this.mprAttDescript = mprAttDescript;
	}
	public String getMprAttIsnull() {
		return mprAttIsnull;
	}
	public void setMprAttIsnull(String mprAttIsnull) {
		this.mprAttIsnull = mprAttIsnull;
	}
	public String getMprAttDataRange() {
		return mprAttDataRange;
	}
	public void setMprAttDataRange(String mprAttDataRange) {
		this.mprAttDataRange = mprAttDataRange;
	}
	public String getMprAttIscumulative() {
		return mprAttIscumulative;
	}
	public void setMprAttIscumulative(String mprAttIscumulative) {
		this.mprAttIscumulative = mprAttIscumulative;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getMprAttKey() {
		return mprAttKey;
	}
	public void setMprAttKey(String mprAttKey) {
		this.mprAttKey = mprAttKey;
	}
 	
}
