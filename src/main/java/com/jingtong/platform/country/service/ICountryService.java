package com.jingtong.platform.country.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.country.pojo.BranchMapping;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.country.pojo.SaleCountry;

/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */
public interface ICountryService {
public List<Country> searchCountry(Country g);
	
	public long saveCountry(Country c);
	
	public int updateCountry(Country c);
	
	public int deleteCountryById(Country c);
	
	public Country getCountryById(Country c);
	
	
	public int getCountryListCount(Country c);
	public int getCountByCode(Country c);

	public int getSaleCountryListCount(SaleCountry sc);

	public List<SaleCountry> searchSaleCountry(SaleCountry sc);

	public List<Country> searchCountryProvince(Country c);

	public BooleanResult addSaleCountry(List<SaleCountry> scList);

	public BooleanResult delSaleCountry(List<SaleCountry> scList);
	
	/**
	 * ��ȡ�ն��б���
	 * @param c
	 * @return
	 */
	public int getBranchMappingListCount(BranchMapping pr);
	/**
	 * ��ȡ�ն˿ͻ��б�
	 * @param c
	 * @return
	 */
	public List<BranchMapping> getBranchMappingList(BranchMapping pr);
	
	
	/**
	 * �ն˿ͻ���Ϣע�ᣨ������
	 * @param pr
	 * @return
	 */
	public long createBranchMapping(BranchMapping pr);
	/**
	 * �ն˿ͻ���Ϣ�޸�
	 * @param pr
	 * @return
	 */
	public int updateBranchMapping(BranchMapping pr);
	
	public int deleteBranchMapping(BranchMapping pr);
}
