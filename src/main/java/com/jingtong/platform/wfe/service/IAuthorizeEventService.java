package com.jingtong.platform.wfe.service;

import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.ProEventLookUp;

public interface IAuthorizeEventService {
	public static final String ERROR = "error";
	
	public static final String SUCCESS = "success";
	
	public static final String ERROR_MESSAGE = "����ʧ�ܣ�";
	
	public static final String SUCCESS_MESSAGE = "�����ɹ���";
	
	/**
	 * ������֯ID������Ա��Ϣ
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId);
	
	/**
	 * ������Ȩ����鿴
	 * @param proEventLookUp
	 * @return
	 */
	public StringResult createAuthorization(ProEventLookUp proEventLookUp);
	
	/**
	 * ɾ����Ȩ����鿴
	 * @return
	 */
	public StringResult deleteAuthorization(String[] lookUpIds);
	
	/**
	 * @param proEventLookUp
	 * @return
	 */
	public int getEventReaderListCount(ProEventLookUp proEventLookUp);
	
	/**
	 * @param proEventLookUp
	 * @return
	 */
	public List<ProEventLookUp> getEventReaderList(ProEventLookUp proEventLookUp);
	
	/**
	 * �����û�Id������Ȩ����Id
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getEventIdList(String userId);
	
	public List<ProcessEventTotal> getAuthorizeEventJsonList(ProcessEventTotal processEventTotal);
	
	public int getAuthorizeEventJsonListCount(ProcessEventTotal processEventTotal);

}
