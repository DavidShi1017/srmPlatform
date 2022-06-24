package com.jingtong.platform.order.dao;

import java.util.List;

import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;



public interface IOrderDao{
	/**
	 * ��ȡ�����б���
	 * @param c
	 * @return
	 */
	public int getOrderListCount(StarderOrder o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param c
	 * @return
	 */
	public List<StarderOrder> getOrderList(StarderOrder o);
	/**
	 * ����ID��ȡ������Ϣ
	 * @param c
	 * @return
	 */
	public StarderOrder getOrderById(StarderOrder o);
	/**
	 * ������Ϣ����
	 * @param p
	 * @return
	 */
	public long createOrder(StarderOrder o);
	/**
	 * �޸Ķ�����Ϣ
	 * @param o
	 * @return
	 */
	public int updateOrder(StarderOrder o);
	/**
	 * ɾ��������Ϣ(�߼�ɾ��)
	 * @param o
	 * @return
	 */
	public int deleteOrder(StarderOrder o);
	
	
	

	/**
	 * ��ȡ������ϸ��Ϣ�б�
	 * @param od
	 * @return
	 */
	public List<OrderDetail> getOrderDetailList(OrderDetail od);

	/**
	 * ������ϸ��Ϣ����
	 * @param od
	 * @return
	 */
	public long createOrderDetail(OrderDetail od);
	/**
	 * �޸Ķ�����ϸ��Ϣ
	 * @param od
	 * @return
	 */
	public int updateOrderDetail(OrderDetail od);
	/**
	 * ɾ��������ϸ��Ϣ(����ɾ��)
	 * @param od
	 * @return
	 */
	public int deleteOrderDetail(OrderDetail od);
	
	
	/**
	 * ��ȡ�Զ����ɵ���
	 */
	public String getSystemIdPrc();
	public List<OrderDetail> downloadOrderData(OrderDetail od);
	public int deleteOrderOfMain(OrderDetail od);
}