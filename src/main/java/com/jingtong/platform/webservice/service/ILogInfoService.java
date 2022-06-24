package com.jingtong.platform.webservice.service;

import java.util.List;

import com.jingtong.platform.webservice.pojo.ExceptionLog;

/**
 * ��־ҵ����
 * @author XIA_QX
 *
 */
public interface ILogInfoService {
   /**
    * 
    * ������־��Ϣ
    * @param elog
    * @throws Exception
    */
   	public void addLogInfo(ExceptionLog elog) throws Exception;
   	
   	/**
   	 * ��ѯ��־��Ϣ
   	 * @param elog
   	 * @return
   	 * @throws Exception
   	 */
   	public List<ExceptionLog> searchLogList(ExceptionLog elog)throws Exception;
   	/**
   	 * ��ѯ��־��¼
   	 * @param elog
   	 * @return
   	 * @throws Exception
   	 */
    public int	searchLogListCount(ExceptionLog elog)throws Exception;
}
