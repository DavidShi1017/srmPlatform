package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Kunnr;

 
public interface KunnrsService {

	/**
	 * @remark ��SAP��ȡ�ͻ�������
	 */
	public void getKunnrListFromSAP();
	public void getKunnrListFromSAP(String customer_code,String sales_org);
	
}
