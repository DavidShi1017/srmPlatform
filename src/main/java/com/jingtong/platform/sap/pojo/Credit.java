package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
import com.jingtong.platform.framework.annotations.Decode;

/**
 * ����
 * 
 * @author cl
 * 
 */
public class Credit extends SearchInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9028466006760258326L;
	
	////
	private Long creditid;
	@Decode
	////
	private String kunnr;// �ͻ����
	@Decode
	private String name1;// �ͻ�����
	@Decode
	private String sortl;// �ͻ����
	private Double dbekr;// �ͻ�����
	private String dbekr1;// �ͻ�����
	private Double jhdwf;// ������������δ�����
	private Double wzjhd;// δ���������Ķ������
	
	////
	private Double xyjy;// ���ý���
	private String xyjy1;// ���ý���
	@Decode
	private String xyjyStr;// ���ý���
	
	////
	private String status;// �Ƿ�ɾ��
	private int deadline;// ��ͬ����
	private int deadline1;// ʵ������
	private String modifyUser;
	private Date modifyDate;// �޸�ʱ��
	private String createUser;
	private Date createDate;// �޸�ʱ��
	private double overdueInterestRate;//��������
    
	/////////
	private String pcreditId; //�������ñ���
	private String creditRange;//�Ŵ���Χ
	private String creditRangeName;
	
	private String creditType;//����
	private String materialNumber ;//��ƷƷ��
 	//Credit_amount	number
	private double interestRate;//����	
 	private Date beginDate;
	private Date endDate;
 	private String policyCode ;//���߱��
 	private String creditName;//��������
 	private String endDateStr;
 	private Double syxy;//��ʹ������
 	private String syl;//ʹ����
	private double xyTake;//ռ������

	private int systemSource; 
	 
	private String creditSort;
	
 	private String remark;
 	
 	private double renewalInterestRate;//��������

  	
	public double getXyTake() {
		return xyTake;
	}

	public void setXyTake(double xyTake) {
		this.xyTake = xyTake;
	}

	public String getCreditSort() {
		return creditSort;
	}

	public void setCreditSort(String creditSort) {
		this.creditSort = creditSort;
	}

  	
	public double getRenewalInterestRate() {
		return renewalInterestRate;
	}

	public void setRenewalInterestRate(double renewalInterestRate) {
		this.renewalInterestRate = renewalInterestRate;
	}

 	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreditRangeName() {
		return creditRangeName;
	}

	public void setCreditRangeName(String creditRangeName) {
		this.creditRangeName = creditRangeName;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getPcreditId() {
		return pcreditId;
	}

	public void setPcreditId(String pcreditId) {
		this.pcreditId = pcreditId;
	}

	public String getCreditRange() {
		return creditRange;
	}

	public void setCreditRange(String creditRange) {
		this.creditRange = creditRange;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public int getSystemSource() {
		return systemSource;
	}

	public void setSystemSource(int systemSource) {
		this.systemSource = systemSource;
	}

	public Long getCreditid() {
		return creditid;
	}

	public void setCreditid(Long creditid) {
		this.creditid = creditid;
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

	public String getSortl() {
		return sortl;
	}

	public void setSortl(String sortl) {
		this.sortl = sortl;
	}

	public Double getDbekr() {
		return dbekr;
	}

	public void setDbekr(Double dbekr) {
		this.dbekr = dbekr;
	}

	public Double getJhdwf() {
		return jhdwf;
	}

	public void setJhdwf(Double jhdwf) {
		this.jhdwf = jhdwf;
	}

	public Double getWzjhd() {
		return wzjhd;
	}

	public void setWzjhd(Double wzjhd) {
		this.wzjhd = wzjhd;
	}

	public Double getXyjy() {
		return xyjy;
	}

	public void setXyjy(Double xyjy) {
		this.xyjy = xyjy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getXyjyStr() {
		return xyjyStr;
	}

	public void setXyjyStr(String xyjyStr) {
		this.xyjyStr = xyjyStr;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getDeadline1() {
		return deadline1;
	}

	public void setDeadline1(int deadline1) {
		this.deadline1 = deadline1;
	}

	public String getDbekr1() {
		return dbekr1;
	}

	public void setDbekr1(String dbekr1) {
		this.dbekr1 = dbekr1;
	}

	public String getXyjy1() {
		return xyjy1;
	}

	public void setXyjy1(String xyjy1) {
		this.xyjy1 = xyjy1;
	}

	public double getOverdueInterestRate() {
		return overdueInterestRate;
	}

	public void setOverdueInterestRate(double overdueInterestRate) {
		this.overdueInterestRate = overdueInterestRate;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Double getSyxy() {
		return syxy;
	}

	public void setSyxy(Double syxy) {
		this.syxy = syxy;
	}

	public String getSyl() {
		return syl;
	}

	public void setSyl(String syl) {
		this.syl = syl;
	}

}
