package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class SAPReport extends SearchInfo{
	private String state;
	private String id;
  
	/****************��������۶�������*************************/
	private static final long serialVersionUID = -4981852886317802327L;
	private String kunnr;//�ͻ�����
	private String promotionId;//�ӻ���
	private String spart;//Ʒ��
	private String mvgr1;//ϵ��
	private String matnr;//����
	private String kwmeng;//����
	private String vrkme;//��λ
	private String klmeng;//ת������
	private String netwr="0.0";//���
	private String maktx;//��������MAKTX
	
	
	private double netwrs;//���
	private double costRate;//������
	private double costMathe;//Ʒ��ռ��
	private String costrt;
	private String costmt;
	
	
	public String getCostrt() {
		return costrt;
	}
	public void setCostrt(String costrt) {
		this.costrt = costrt;
	}
	public String getCostmt() {
		return costmt;
	}
	public void setCostmt(String costmt) {
		this.costmt = costmt;
	}
	public String getMaktx() {
		return maktx;
	}
	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	 
	public double getCostRate() {
		return costRate;
	}
	public void setCostRate(double costRate) {
		this.costRate = costRate;
	}
	public double getCostMathe() {
		return costMathe;
	}
	public void setCostMathe(double costMathe) {
		this.costMathe = costMathe;
	}
	 
	public double getNetwrs() {
		return netwrs;
	}
	public void setNetwrs(double netwrs) {
		this.netwrs = netwrs;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getMvgr1() {
		return mvgr1;
	}
	public void setMvgr1(String mvgr1) {
		this.mvgr1 = mvgr1;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getKwmeng() {
		return kwmeng;
	}
	public void setKwmeng(String kwmeng) {
		this.kwmeng = kwmeng;
	}
	public String getVrkme() {
		return vrkme;
	}
	public void setVrkme(String vrkme) {
		this.vrkme = vrkme;
	}
	public String getKlmeng() {
		return klmeng;
	}
	public void setKlmeng(String klmeng) {
		this.klmeng = klmeng;
	}
	public String getNetwr() {
		return netwr;
	}
	public void setNetwr(String netwr) {
		this.netwr = netwr;
	}
	/*****************��������۶������ݣ�����********************/
	private String p_promotionId;//�Ƿ���ʾ�ӻ���X:ѡ��  ��0����ѡ��
	private String p_spart;//�Ƿ���ʾƷ��
	private String p_mvgr1;//�Ƿ���ʾϵ��
	private String p_matnr;//�Ƿ���ʾ����
	private String p_kunnr;//�Ƿ���ʾ�۴﷽
	private String p_kwmeng;//�Ƿ���ʾ��λ
	private String s_promotionId;//�ӻ���
	private String s_spart;//Ʒ��
	private String s_mvgr1;//ϵ��
	private String s_matnr;//����
	private String s_kunnr;//�ͻ�
	
	public String getP_kwmeng() {
		return p_kwmeng;
	}
	public void setP_kwmeng(String p_kwmeng) {
		this.p_kwmeng = p_kwmeng;
	}
	public String getP_promotionId() {
		return p_promotionId;
	}
	public void setP_promotionId(String p_promotionId) {
		this.p_promotionId = p_promotionId;
	}
	public String getP_spart() {
		return p_spart;
	}
	public void setP_spart(String p_spart) {
		this.p_spart = p_spart;
	}
	public String getP_mvgr1() {
		return p_mvgr1;
	}
	public void setP_mvgr1(String p_mvgr1) {
		this.p_mvgr1 = p_mvgr1;
	}
	public String getP_matnr() {
		return p_matnr;
	}
	public void setP_matnr(String p_matnr) {
		this.p_matnr = p_matnr;
	}
	public String getP_kunnr() {
		return p_kunnr;
	}
	public void setP_kunnr(String p_kunnr) {
		this.p_kunnr = p_kunnr;
	}
	public String getS_promotionId() {
		return s_promotionId;
	}
	public void setS_promotionId(String s_promotionId) {
		this.s_promotionId = s_promotionId;
	}
	public String getS_spart() {
		return s_spart;
	}
	public void setS_spart(String s_spart) {
		this.s_spart = s_spart;
	}
	public String getS_mvgr1() {
		return s_mvgr1;
	}
	public void setS_mvgr1(String s_mvgr1) {
		this.s_mvgr1 = s_mvgr1;
	}
	public String getS_matnr() {
		return s_matnr;
	}
	public void setS_matnr(String s_matnr) {
		this.s_matnr = s_matnr;
	}
	public String getS_kunnr() {
		return s_kunnr;
	}
	public void setS_kunnr(String s_kunnr) {
		this.s_kunnr = s_kunnr;
	}
	 
	/******************����װ��********************/
	private long productGroupRuleId;//��Ʒ��������
	private long productGroupId;//��Ʒ����
	private String materialNumber;//��Ʒ���
	private float outPrice=0.0f;//������
	private String channelPrice="0.0";//�����۸�
	private float freeCost;//����
	private String freect;
	private float outRetailPrice;//�������ۼ�
	private float channelRetailPrice;//��̬���ۼ۸�
	private float dmcost;//DM��
	private float displayCost;//���з�
	private String discountType;//����
	private String materialName;//��Ʒ����
	//private float costRate;������
	private float demandNum;//�����
	private float salesNum;//������
	private float sales;//���۶�
	private String isShippromo;//�Ƿ��ͷ
	private String demandNums;
	private float dclv;//�����
	private String umrez;//��������
	private int fyAll;//�����ܽ�
	private int demandNumI=0;//�����
	private int salesNums=0;//������
	private int cxCost=0;//������
	private int clCost;//���з�
	private int dmCostI;//DM��
	private int xsCost;//���۶�
	
	
	public int getClCost() {
		return clCost;
	}
	public void setClCost(int clCost) {
		this.clCost = clCost;
	}
	public int getDmCostI() {
		return dmCostI;
	}
	public void setDmCostI(int dmCostI) {
		this.dmCostI = dmCostI;
	}
	public int getXsCost() {
		return xsCost;
	}
	public void setXsCost(int xsCost) {
		this.xsCost = xsCost;
	}
	public void setFyAll(int fyAll) {
		this.fyAll = fyAll;
	}
	public int getCxCost() {
		return cxCost;
	}
	public void setCxCost(int cxCost) {
		this.cxCost = cxCost;
	}
	public int getDemandNumI() {
		return demandNumI;
	}
	public void setDemandNumI(int demandNumI) {
		this.demandNumI = demandNumI;
	}
	public int getSalesNums() {
		return salesNums;
	}
	public void setSalesNums(int salesNums) {
		this.salesNums = salesNums;
	}
 
	public int getFyAll() {
		return fyAll;
	}
	public String getUmrez() {
		return umrez;
	}
	public void setUmrez(String umrez) {
		this.umrez = umrez;
	}
	public float getDemandNum() {
		return demandNum;
	}
	public void setDemandNum(float demandNum) {
		this.demandNum = demandNum;
	}
	public float getDclv() {
		return dclv;
	}
	public void setDclv(float dclv) {
		this.dclv = dclv;
	}
	public String getDemandNums() {
		return demandNums;
	}
	public void setDemandNums(String demandNums) {
		this.demandNums = demandNums;
	}
	public String getIsShippromo() {
		return isShippromo;
	}
	public void setIsShippromo(String isShippromo) {
		this.isShippromo = isShippromo;
	}
	public float getDmcost() {
		return dmcost;
	}
	public void setDmcost(float dmcost) {
		this.dmcost = dmcost;
	}
	public float getDisplayCost() {
		return displayCost;
	}
	public void setDisplayCost(float displayCost) {
		this.displayCost = displayCost;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
 
	public float getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(float salesNum) {
		this.salesNum = salesNum;
	}
	public float getSales() {
		return sales;
	}
	public void setSales(float sales) {
		this.sales = sales;
	}
	public float getChannelRetailPrice() {
		return channelRetailPrice;
	}
	public void setChannelRetailPrice(float channelRetailPrice) {
		this.channelRetailPrice = channelRetailPrice;
	}
	public float getOutRetailPrice() {
		return outRetailPrice;
	}
	public void setOutRetailPrice(float outRetailPrice) {
		this.outRetailPrice = outRetailPrice;
	}
	public String getFreect() {
		return freect;
	}
	public void setFreect(String freect) {
		this.freect = freect;
	}
	public long getProductGroupRuleId() {
		return productGroupRuleId;
	}
	public void setProductGroupRuleId(long productGroupRuleId) {
		this.productGroupRuleId = productGroupRuleId;
	}
	public long getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(long productGroupId) {
		this.productGroupId = productGroupId;
	}
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public float getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(float outPrice) {
		this.outPrice = outPrice;
	}
	public String getChannelPrice() {
		return channelPrice;
	}
	public void setChannelPrice(String channelPrice) {
		this.channelPrice = channelPrice;
	}
	public float getFreeCost() {
		return freeCost;
	}
	public void setFreeCost(float freeCost) {
		this.freeCost = freeCost;
	}
	 /**********ϵ��***************/
	private String itemValue;
	private String itemName;

	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*************�ӻ******************/
	private String promotiondetailId;

	public String getPromotiondetailId() {
		return promotiondetailId;
	}
	public void setPromotiondetailId(String promotiondetailId) {
		this.promotiondetailId = promotiondetailId;
	}
	/*************�******************/
	private String	promotion_ID; //�����
	private String	promotion_Name; //�����
	private Date	pbegin_Date; //���ʼʱ��
	private Date	pend_Date; //�����ʱ��
	private Date purchasebegindate;//������ʼʱ��
	private Date purchaseenddate;//��������ʱ��

	public Date getPurchasebegindate() {
		return purchasebegindate;
	}
	public void setPurchasebegindate(Date purchasebegindate) {
		this.purchasebegindate = purchasebegindate;
	}
	public Date getPurchaseenddate() {
		return purchaseenddate;
	}
	public void setPurchaseenddate(Date purchaseenddate) {
		this.purchaseenddate = purchaseenddate;
	}
	public String getPromotion_ID() {
		return promotion_ID;
	}
	public void setPromotion_ID(String promotion_ID) {
		this.promotion_ID = promotion_ID;
	}
	public String getPromotion_Name() {
		return promotion_Name;
	}
	public void setPromotion_Name(String promotion_Name) {
		this.promotion_Name = promotion_Name;
	}
	public Date getPbegin_Date() {
		return pbegin_Date;
	}
	public void setPbegin_Date(Date pbegin_Date) {
		this.pbegin_Date = pbegin_Date;
	}
	public Date getPend_Date() {
		return pend_Date;
	}
	public void setPend_Date(Date pend_Date) {
		this.pend_Date = pend_Date;
	}
	@Override
	public String toString() {
		return "SAPReport [kunnr=" + kunnr + ", promotionId=" + promotionId
				+ ", spart=" + spart + ", netwr=" + netwr + ", maktx=" + maktx
				+ ", netwrs=" + netwrs + ", costRate=" + costRate
				+ ", costMathe=" + costMathe + ", costrt=" + costrt
				+ ", costmt=" + costmt + ", productGroupRuleId="
				+ productGroupRuleId + ", productGroupId=" + productGroupId
				+ ", materialNumber=" + materialNumber + ", outPrice="
				+ outPrice + ", channelPrice=" + channelPrice + ", freeCost="
				+ freeCost + ", freect=" + freect + ", outRetailPrice="
				+ outRetailPrice + ", channelRetailPrice=" + channelRetailPrice
				+ ", dmcost=" + dmcost + ", displayCost=" + displayCost
				+ ", discountType=" + discountType + ", materialName="
				+ materialName + ", demandNum=" + demandNum + ", salesNum="
				+ salesNum + ", sales=" + sales + ", isShippromo="
				+ isShippromo + ", demandNums=" + demandNums + ", dclv=" + dclv
				+ ", umrez=" + umrez + ", itemValue=" + itemValue
				+ ", itemName=" + itemName + ", promotiondetailId="
				+ promotiondetailId + ", promotion_ID=" + promotion_ID
				+ ", promotion_Name=" + promotion_Name + ", pbegin_Date="
				+ pbegin_Date + ", pend_Date=" + pend_Date
				+ ", purchasebegindate=" + purchasebegindate
				+ ", purchaseenddate=" + purchaseenddate + "]";
	}
	
	
	
}
