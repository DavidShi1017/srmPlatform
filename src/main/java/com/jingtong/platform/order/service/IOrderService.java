package com.jingtong.platform.order.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.order.pojo.OrderDetail;
import com.jingtong.platform.order.pojo.StarderOrder;

public interface IOrderService {
	/**
	 * ��ȡ�����б���
	 * @param o
	 * @return
	 */
	public int getOrderListCount(StarderOrder o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param o
	 * @return
	 */
	public List<StarderOrder> getOrderList(StarderOrder o);
	/**
	 * ����ID��ȡ������Ϣ
	 * @param o
	 * @return
	 */
	public StarderOrder getOrderById(StarderOrder o);
	/**
	 * ������Ϣ����
	 * @param o
	 * @return
	 */
	public BooleanResult createOrder(StarderOrder o,List<OrderDetail> odList);
	/**
	 * �޸Ķ�����Ϣ
	 * @param o
	 * @return
	 */
	public BooleanResult updateOrder(StarderOrder o,List<OrderDetail> odList,OrderDetail od);
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
	int deleteOrderOfMain(OrderDetail od);
}
