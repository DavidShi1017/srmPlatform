package com.jingtong.platform.account.service;

import java.util.List;

import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;

public interface ITravelAppService {

	//�������뵥
	public StringResult  saveTravelTotal(TravelTotal travelTotal);
	/**
	 * ��ѯ����������(��ҳ)
	 */
	public int searchTravelCount(TravelTotal travelTotal);
	
	
	/**
	 * ��ѯ������(��ҳ)
	 */
	public List<TravelTotal> searchTravel(TravelTotal travelTotal);
	
	
	/**
	 * ��ѯ������ͨ��travelId
	 * @param travelId
	 * @param transactionId
	 * @return
	 */
	
	public TravelTotal searchTraveltotalByTravelId(String travelId,String transactionId);
	
	/**
	 * �޸ı�����
	 * @param travelTotal
	 * @return
	 */
	public BooleanResult updateTravel(final TravelTotal travelTotal);
	
	/**
	 * ������������ܾ�
	 * @param travelTotal
	 * @return
	 */
	public BooleanResult refuseTravelTr(TravelTotal travelTotal);
	/**
	 * �������һ���޸�Ԥ�����
	 */
	public BooleanResult exitTravelTotal(TravelTotal travelTotal);
	
	/**
	 * ��ȡԤ�����
	 */
	public BudgetNumber getBudgetNumber(BudgetNumber budgetNumber);
	
	/**
	 * ���ˣ��޸����뵥��Ϣ
	 */
	public BooleanResult updateTravelApp(final TravelTotal travelTotal);

}
