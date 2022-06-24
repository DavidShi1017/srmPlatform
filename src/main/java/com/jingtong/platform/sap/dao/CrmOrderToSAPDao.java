package com.jingtong.platform.sap.dao;

import java.util.List;

import com.jingtong.platform.sap.pojo.Credit;
import com.jingtong.platform.sap.pojo.CreditLog;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.OldProduct;
import com.jingtong.platform.sap.pojo.PaymentDetailRecords;
import com.jingtong.platform.sap.pojo.ReceivePay;

public interface CrmOrderToSAPDao {

    public List<CrmOrderToSap> getCrmOrderTotal(CrmOrderToSap order);
 	public List<CrmOrderItem> getCrmOrderItems(CrmOrderToSap order);
 	public int updateOrderDetail(CrmOrderItem orderId);
	
 	public int updateProductStock(CrmOrderItem order);
 	
 	/**
 	 * �޸ľɻ����
 	 * */
 	public int updateOldProductStock(OldProduct oldP);
 	
 	/**
	 * �ͻ������޸�
	 * */
 	public int updateCreditByOrder(Credit credit);
 	
 	/**
 	 * �����б�
 	 * */
 	public List<Credit> getCreditList(Credit credit);
 	
 	/**
 	 * �޸�����
 	 * */
 	public int updateCredit(Credit credit);
 	
 	public int updateLimitedNumByOrder(CrmOrderItem item);
 	
 	public long addCreditLog(CreditLog credl);
 	
 	public List<CrmOrderItem> getUseRepaymentList(CrmOrderItem item);
 	
 	public int updateOrderStatus(CrmOrderToSap order);
 	
 	public List<ReceivePay> getNewReceivePayId(String kunnrId);
 	
 	public int updateReceivePay(ReceivePay receive);
 	
 	public long saveRepaymentRecords(PaymentDetailRecords pay);
 	
 	public List<CrmOrderItem> getDeleteCrmOrder(CrmOrderToSap order);
 	
 	public List<PaymentDetailRecords> getPayMentList(PaymentDetailRecords payment);
 	
 	public String getLimitedNumByOrder(CrmOrderItem item);
 	
 	public int updateOrderDetailStatus(CrmOrderItem item);
   	
}
