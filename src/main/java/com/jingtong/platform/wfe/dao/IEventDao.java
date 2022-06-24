package com.jingtong.platform.wfe.dao;

import java.util.List;

import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.Leave;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

public interface IEventDao {
	
	/**
	 * ��ȡ����ָ�������˶�Ӧ������Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * ��ȡXml��ʱ�ļ���
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	public void updateLeave(Leave leave);
	
	public String  getWfeActionId(String eventId);
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId,Long ccId);
	/**
	 * ��������Ų�ѯ��ϸ
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	/**
	 * ��������
	 * 
	 * @param budgetFileTmp
	 * @return
	 */
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp);
	
	/**
	 * ��ȡ������ϵ����
	 * 
	 * @param linkMan
	 * @return
	 */
	public int getLinkManCount(LinkMan linkMan);
	
	/**
	 * ��ѯ������ϵ��
	 * 
	 * @param linkMan
	 * @return
	 */
	public List<LinkMan> getLinkManList(LinkMan linkMan);
	
	/**
	 * ����������ϵ��
	 * 
	 * @param linkMan
	 * @return
	 */
	public Long createLinkMan(LinkMan linkMan);
	
	/**
	 * ���³�����ϵ��
	 * 
	 * @param linkMan
	 */
	public int updateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	/**
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * ��������Ų�ѯ����
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/***
	 * ����������
	 * @param cc
	 * @return
	 */
	public StringResult createCc(List<Cc> cc);
	
	/**
	 * �޸ĳ�����Ϣ
	 * @param businessTripApplyList
	 * @return
	 */
	public int  updateCc(Cc cc);
	
	/**
	 * ��ȡ������Ϣ�б�����
	 * @param cc
	 * @return
	 */
	public int  getCcListCount(Cc cc);
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcList(Cc cc);
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	public List<Cc> getCcListByEventId(Cc cc);

	public String getStationIdByUserId(String userId);
	
	public Integer getCancelEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);

}
