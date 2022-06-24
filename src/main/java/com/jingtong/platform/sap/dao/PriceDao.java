package com.jingtong.platform.sap.dao;

import com.jingtong.platform.sap.pojo.Price;


public interface PriceDao {

	public String createPrice(Price price);
	
	public int updatePrice(Price price);
	

	
	public int getPriceList(Price price);
	
	public int deletePrice(Price price);
	
	//ɾ����ǰʱ���ǰһ�������
	public int deletePriceInfoForSap(Price price);
	
}
