package com.jingtong.platform.webservice.service;

import java.util.List;

import com.jingtong.platform.webservice.pojo.Page;
import com.jingtong.platform.webservice.pojo.ProcessEventDetail;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.webservice.pojo.UserUtil;

public interface IWebService {
	/**
	 * ��������
	 * @param res
	 * @return
	 */
	public  String startWorkflow(Object[] res);
	
	/**
	 * ��ѯ��������
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTodoTasks(Object[] res);
	
	/**
	 * �����ѯ
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findTasksByUser(Object[] res);
	/**
	 * �����Ѱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  Page<ProcessEventTotal> findProcessedTasks(Object[] res);
	/**
	 * ������������
	 * @param res
	 * @return
	 */
	public String startSemiAutomaticWorkflow(Object[] res);
	/**
	 * �鿴������ϸ
	 * 
	 * @param processInstanceId
	 * @return
	 */
	public  List<ProcessEventDetail> findTasksByUserProcessInstanceId(String processInstanceId);
	/**
	 * ������
	 * @param res
	 * @return
	 */
	public  String complete(Object[] res);
	/**
	 * ������������
	 * @param res
	 * @return
	 */
	public String startAnyProcessWorkflow(Object[] res);
	/**
	 * ����ȡ��
	 * 
	 * @param res
	 * @return
	 */
	public  String cancelEvent(String eventId);
	/**
	 * �̶����������¸�����������������
	 * @param eventId
	 * @return
	 */
	public  UserUtil startWorkflowFix(Object[] res);
	/**
	 * �̶����������¸�����������������
	 * @param eventId
	 * @return
	 */
	public  UserUtil startProcessNexUser(Object[] res);
	/**
	 * ��������д�����
	 * @param res
	 * @return
	 */
	public  String processCommit(Object[] res);
	/**
	 * �����������������ǵ���
	 * @param eventId
	 * @return
	 */
	public  String processWorkflowFix(Object[] res);
	
	/**
	 * �������� cy
	 * @param bytes  xml������ֽ���
	 * @param processDefinitionName ��������
	 * @return
	 */
	
	public String deployProcessDefinition(String  xmlString,String processDefinitionName);
	
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil completeEndTask(Object[] res);
	/**
	 * ȡ������
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil backProEvent(Object[] res);
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectSemUser(Object[] res);
	/**
	 * �������ڵ�����ֱ�����
	 * 
	 * @param res
	 * @return
	 */
	public  UserUtil selectAnyExecuteAction(Object[] res);
}
