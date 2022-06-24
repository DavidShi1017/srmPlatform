package com.jingtong.platform.sap.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public interface SAPService {

	/** ����Ʒ����������
	 * @param punit String	ͳ�Ƶ�λ��ST��CAR
	 * @param pmatnr String	���ϱ���
	 * @param pwerks String	��������
	 * @param plgort String	��λ����
	 * @return
	 */
	public ProductStock getMaterialInventoryFromSap(String pmatnr,String pwerks,String plgort);
	
	public void getMaterialInventory();
	
	public CuspayAccdoc getCuspayAccdocFromSAP(CuspayAccdoc doc);
	
	public boolean autoGetCusPlusFromSap();
	
	public boolean getCusPlusFromSap(Date begda,Date endda,String kunnr) ;
	
	
	public List<NoClearMoney> getweiqingMoneyFromSAP(String p_bukrs,String p_gjahr);
	
	/**
	 * ��ȡ���ƾ֤
	 * @throws Exception
	 */
	public NoClearMoney getReceiveCode(String belnr) throws Exception;
	
	public long checkExistReceivePay(ReceivePay receivePay);
	
	/**
	 * ����ؿ�ƾ֤
	 * @param receivePay
	 */
	public long addReceivePay(ReceivePay receivePay) throws Exception;
	
	public int updateCreditByReceive(String kunnrId);
	
	public List<String> getClearMoneyList();

	public void getReceivePayFromSap();

	public void getInitialCredit();

	public void getZSVoucher();

	public void getFSVoucher();
}
