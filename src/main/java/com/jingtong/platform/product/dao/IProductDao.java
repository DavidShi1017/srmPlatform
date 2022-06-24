package com.jingtong.platform.product.dao;

import java.util.List;

import com.jingtong.platform.product.pojo.Product;


public interface IProductDao {
	/**
	 * ��ȡ��Ʒ�б���
	 * @param c
	 * @return
	 */
	public int getProductListCount(Product p);
	/**
	 * ��ȡ��Ʒ��Ϣ�б�
	 * @param c
	 * @return
	 */
	public List<Product> getProductList(Product p);
	/**
	 * ��ȡ��Ʒ�б���
	 * @param c
	 * @return
	 */
	public int getDRProductListCount(Product p);
	/**
	 * ��ȡ��Ʒ��Ϣ�б�
	 * @param c
	 * @return
	 */
	public List<Product> getDRProductList(Product p);
	/**
	 * ����ID��ȡ��Ʒ��Ϣ
	 * @param c
	 * @return
	 */
	public Product getProductById(Product p);
	/**
	 * ��Ʒ��Ϣ����
	 * @param p
	 * @return
	 */
	public long createProduct(Product p);
	/**
	 * �޸Ĳ�Ʒ��Ϣ
	 * @param p
	 * @return
	 */
	public int updateProduct(Product p);
	/**
	 * ɾ����Ʒ��Ϣ(�߼�ɾ��)
	 * @param p
	 * @return
	 */
	public int deleteProduct(Product p);
	List<Product> getProductListNoPage(Product p);
	public int getQuoteProductListCount(Product p);
	public List<Product> getQuoteProductList(Product p);
	public List<Product> getDRQuoteProductListNoPage(Product p);

}
