package com.jingtong.platform.wfe.service;

import java.io.File;
import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.BusinessTripApply;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

public interface IEventService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	public static final String NEXT = "next";

	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";

	public static final String ERROR_INPUT_MESSAGE = "����ʧ�ܣ���������";

	public static final String ERROR_NULL_MESSAGE = "����ʧ�ܣ������Ѳ����ڣ�";
	
	public Long getEvent_XmlTempId();

	public StringResult createEvent(String key, String userId, String title,String nextUser,
			String eventContent, String userList, String memo);
	
	public String getProEventDetail(String eventId, String userId);
	
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
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * ��������Ų�ѯ����
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/**
	 * ��ȡ������ϸ�б�������
	 * 
	 * @param eventId
	 * @return
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId);
	
	/**
	 * �������ϴ�
	 * 
	 * @param uploadFiles
	 * @param uploadFileNames
	 * @param eventDetailId
	 * @param timestamp
	 * @return
	 */
	public boolean processAttachments(File[] uploadFiles,
			String[] uploadFileNames, Long eventDetailId, String timestamp,String key);
	
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
	public BooleanResult saveOrUpdateLinkMan(LinkMan linkMan);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	public void updateBuglogFile(Long eventId, String state);
	
	public int getTripWayCount(TripWay tripWay);
	
	public List<TripWay> getTripWayList(TripWay tripWay);
	
	public File exportBusinessTripApplyList(List<BusinessTripApply> businessTripApplyList);
	
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
	
	public int getCancelEventCount(ProcessEventTotal eventTotal);
	
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal);
}
