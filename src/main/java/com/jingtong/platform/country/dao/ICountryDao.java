package com.jingtong.platform.country.dao;

import java.util.List;

import com.jingtong.platform.country.pojo.BranchMapping;
import com.jingtong.platform.country.pojo.Country;
import com.jingtong.platform.country.pojo.SaleCountry;

/**
 * ����
 * 
 * @author lenovo
 *
 */
public interface ICountryDao {

    public List<Country> searchCountry(Country c);

    public long saveCountry(Country c);

    public int updateCountry(Country c);

    public int deleteCountryById(Country c);

    public Country getCountryById(Country c);

    public int getCountryListCount(Country c);

    public int getCountByCode(Country c);

    public int getSaleCountryListCount(SaleCountry c);

    public List<SaleCountry> searchSaleCountry(SaleCountry c);

    public List<Country> searchCountryProvince(Country c);

    public long addSaleCountry(SaleCountry sc);

    public long assignCountry(SaleCountry sc);

    public long assignProvince(SaleCountry sc);

    public long delSaleCountry(SaleCountry sc);

    /**
     * ��ȡ�ն��б���
     * 
     * @param c
     * @return
     */
    public int getBranchMappingListCount(BranchMapping pr);

    /**
     * ��ȡ�ն˿ͻ��б�
     * 
     * @param c
     * @return
     */
    public List<BranchMapping> getBranchMappingList(BranchMapping pr);

    /**
     * �ն˿ͻ���Ϣע�ᣨ������
     * 
     * @param pr
     * @return
     */
    public long createBranchMapping(BranchMapping pr);

    /**
     * �޸��ն˿ͻ���Ϣ
     * 
     * @param pr
     * @return
     */
    public int updateBranchMapping(BranchMapping pr);

    /**
     * ɾ���ն˿ͻ�(����ɾ��)
     * 
     * @param pr
     * @return
     */
    public int deleteBranchMapping(BranchMapping pr);
    
    public Country getOrgCodeByOrgId(Country c);

}
