package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ���ü�¼
 * 
 * @author cl
 * 
 */
public class CreditLog extends SearchInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1070411349807923275L;
	private Long creditLogId;// id
	private String custmoerId;// �ͻ�id
	private String kunnr;// �ͻ����
	private String name1;// �ͻ�����
	private Double creditdbekr;// ��������
	private Double creditxyjy;// ����ǰ���ý���
	private Date createTime;//����ʱ��
	private String revisionId;//������id
	private String revisionName;//����������
	private String remark; //��ע 
	private String creditlogtype;//����
	private double beforeInterestRate;//����ǰ������
	private double afterInterestRate;//�����������
	private Date startDate;//����ǰ�Ľ�ֹ����
	private Date endDate;//������Ľ�ֹ����
	////
	private String creditId;//���ú�
	private Double creditUseAmount;//��ʹ������
	
	public CreditLog() {
		
	}
	
	



	public String getRemark() {
		return remark;
	}





	public void setRemark(String remark) {
		this.remark = remark;
	}





	public Long getCreditLogId() {
		return creditLogId;
	}
	public void setCreditLogId(Long creditLogId) {
		this.creditLogId = creditLogId;
	}
	public String getCustmoerId() {
		return custmoerId;
	}
	public void setCustmoerId(String custmoerId) {
		this.custmoerId = custmoerId;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}

	public Double getCreditdbekr() {
		return creditdbekr;
	}





	public void setCreditdbekr(Double creditdbekr) {
		this.creditdbekr = creditdbekr;
	}





	public Double getCreditxyjy() {
		return creditxyjy;
	}





	public void setCreditxyjy(Double creditxyjy) {
		this.creditxyjy = creditxyjy;
	}





	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public String getRevisionId() {
		return revisionId;
	}





	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}





	public String getRevisionName() {
		return revisionName;
	}
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}





	public String getCreditlogtype() {
		return creditlogtype;
	}


	public void setCreditlogtype(String creditlogtype) {
		this.creditlogtype = creditlogtype;
	}





	public String getCreditId() {
		return creditId;
	}





	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}





	public Double getCreditUseAmount() {
		return creditUseAmount;
	}





	public void setCreditUseAmount(Double creditUseAmount) {
		this.creditUseAmount = creditUseAmount;
	}





	public double getBeforeInterestRate() {
		return beforeInterestRate;
	}





	public void setBeforeInterestRate(double beforeInterestRate) {
		this.beforeInterestRate = beforeInterestRate;
	}





	public double getAfterInterestRate() {
		return afterInterestRate;
	}





	public void setAfterInterestRate(double afterInterestRate) {
		this.afterInterestRate = afterInterestRate;
	}





	public Date getStartDate() {
		return startDate;
	}





	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}





	public Date getEndDate() {
		return endDate;
	}





	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


	

}
