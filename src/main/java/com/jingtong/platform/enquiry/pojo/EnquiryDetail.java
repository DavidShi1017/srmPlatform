package com.jingtong.platform.enquiry.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * ѯ����ϸ��
 * @author yw
 *
 */
public class EnquiryDetail extends SearchInfo{
	private long id;
	private String enquiry_id;//ѯ�۵���
	private String row_no;//ѯ�۵��кţ�Quote Detail Row��
	private String material_id;// ���ϱ��루12NC��
	private String drNum;//��Ŀע��ı���(DR Number) 
	private double qty;// ����������QTY��
	private double target_resale;//Ŀ�����ۼ۸�Target Resale
	private double target_cost;//Ŀ������۸�Target Cost��
	private double amount;//����Ŀ�ܼۣ�Value��
	private String reason;//����ԭ��(Justification)
	private String competitor;//��������(Competitor)
	private Date start_date;//��ʼ��������(�ն˿ͻ�)Start of Production
	private String start_dateStr;
	private String cus_remark;//�����̱�ע(CusRemarks)
	private double suggest_resale;//�������ۼ۸�(Suggest Resale) 
	private double suggest_cost;//���������۸�(Suggest Cost)
	private double cus_profits_percent ;//�ͻ�����ٷֱ�(Disti Margin) 
	private double profits_percent;//��������ٷֱ�(Mfr Margin) 
	private String remark;//�������(WeenRemarks) 
	private int state;// ���۵�״̬(Status)
	private long main_id;//���۵�����ID 
	private Date latest_time;//����ʱ��
	private String latest_userId;//������	
	private String ids;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEnquiry_id() {
		return enquiry_id;
	}
	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}
	public String getRow_no() {
		return row_no;
	}
	public void setRow_no(String row_no) {
		this.row_no = row_no;
	}
	public String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}
	
	public String getDrNum() {
		return drNum;
	}
	public void setDrNum(String drNum) {
		this.drNum = drNum;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getTarget_resale() {
		return target_resale;
	}
	public void setTarget_resale(double target_resale) {
		this.target_resale = target_resale;
	}
	public double getTarget_cost() {
		return target_cost;
	}
	public void setTarget_cost(double target_cost) {
		this.target_cost = target_cost;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getCus_remarks() {
		return cus_remark;
	}
	public void setCus_remarks(String cus_remarks) {
		this.cus_remark = cus_remarks;
	}
	public double getSuggest_resale() {
		return suggest_resale;
	}
	public void setSuggest_resale(double suggest_resale) {
		this.suggest_resale = suggest_resale;
	}
	public double getSuggest_cost() {
		return suggest_cost;
	}
	public void setSuggest_cost(double suggest_cost) {
		this.suggest_cost = suggest_cost;
	}
	public double getCus_profits_percent() {
		return cus_profits_percent;
	}
	public void setCus_profits_percent(double cus_profits_percent) {
		this.cus_profits_percent = cus_profits_percent;
	}
	public double getProfits_percent() {
		return profits_percent;
	}
	public void setProfits_percent(double profits_percent) {
		this.profits_percent = profits_percent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getMain_id() {
		return main_id;
	}
	public void setMain_id(long main_id) {
		this.main_id = main_id;
	}
	public Date getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(Date latest_time) {
		this.latest_time = latest_time;
	}
	public String getLatest_userId() {
		return latest_userId;
	}
	public void setLatest_userId(String latest_userId) {
		this.latest_userId = latest_userId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getStart_dateStr() {
		return start_dateStr;
	}
	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}
	public String getCus_remark() {
		return cus_remark;
	}
	public void setCus_remark(String cus_remark) {
		this.cus_remark = cus_remark;
	}

	
}
