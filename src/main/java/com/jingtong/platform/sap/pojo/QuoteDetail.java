package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * ���۵��ӿ�����_T_ABLE
 * 
 *
 */
public class QuoteDetail extends SearchInfo{
	private String id;
	private String quoteId;//���۵���
	private String rowNo;//���۵�����Ŀ��
	private String materialId;//���Ϻ�
	private String drId;//DR����(DR Number)
	private String qty;//����������QTY�� 
	private String targetResale;//Ŀ�����ۼ۸�Target Resale�� 
	private String targetCost;	//Ŀ������۸�Target Cost�� 
	private String cusProfitsPercent;//�ͻ�����ٷֱ�(Disti Margin)
	private String amount;//����Ŀ�ܼۣ�Value�� 
	private String suggestResale;//�������ۼ۸�(Suggest Resale)
	private String suggestCost;// ���������۸�(Suggest Cost)
	private String mfrMargin;//��������ٷֱ�(Mfr Margin)

	private String justification;//����ԭ��(Justification)
	private String competitor;//��������(Competitor)
	private String startOfProduction;//Ͷ������Start of Production
	private String cusRemarks;//�ͻ���ע(CusRemarks)
	private String weenRemarks;//�������(WeenRemarks)
	private String status;//���۵�״̬(Status)
	private String mainId;//���۵�����ID
	private String enquiryDetailId;//ѯ����ϸ��ID
	private String latestTime;//����ʱ��
	private String latestUserId;//�����û�
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	public String getRowNo() {
		return rowNo;
	}
	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getDrId() {
		return drId;
	}
	public void setDrId(String drId) {
		this.drId = drId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getTargetResale() {
		return targetResale;
	}
	public void setTargetResale(String targetResale) {
		this.targetResale = targetResale;
	}
	public String getTargetCost() {
		return targetCost;
	}
	public void setTargetCost(String targetCost) {
		this.targetCost = targetCost;
	}
	public String getCusProfitsPercent() {
		return cusProfitsPercent;
	}
	public void setCusProfitsPercent(String cusProfitsPercent) {
		this.cusProfitsPercent = cusProfitsPercent;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSuggestResale() {
		return suggestResale;
	}
	public void setSuggestResale(String suggestResale) {
		this.suggestResale = suggestResale;
	}
	public String getSuggestCost() {
		return suggestCost;
	}
	public void setSuggestCost(String suggestCost) {
		this.suggestCost = suggestCost;
	}
	public String getMfrMargin() {
		return mfrMargin;
	}
	public void setMfrMargin(String mfrMargin) {
		this.mfrMargin = mfrMargin;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getStartOfProduction() {
		return startOfProduction;
	}
	public void setStartOfProduction(String startOfProduction) {
		this.startOfProduction = startOfProduction;
	}
	public String getCusRemarks() {
		return cusRemarks;
	}
	public void setCusRemarks(String cusRemarks) {
		this.cusRemarks = cusRemarks;
	}
	public String getWeenRemarks() {
		return weenRemarks;
	}
	public void setWeenRemarks(String weenRemarks) {
		this.weenRemarks = weenRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getEnquiryDetailId() {
		return enquiryDetailId;
	}
	public void setEnquiryDetailId(String enquiryDetailId) {
		this.enquiryDetailId = enquiryDetailId;
	}
	public String getLatestTime() {
		return latestTime;
	}
	public void setLatestTime(String latestTime) {
		this.latestTime = latestTime;
	}
	public String getLatestUserId() {
		return latestUserId;
	}
	public void setLatestUserId(String latestUserId) {
		this.latestUserId = latestUserId;
	}
	
	
	
	
	
}
