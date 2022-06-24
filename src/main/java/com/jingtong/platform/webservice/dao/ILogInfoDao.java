package com.jingtong.platform.webservice.dao;

import java.util.List;

import com.jingtong.platform.webservice.pojo.ExceptionLog;

/**
 * ��־������
 * @author XIA_QX
 *
 */
public interface ILogInfoDao {
	/**
	 * ������־��Ϣ
	 * @param elog
	 * @throws Exception
	 */
   public void addLogInfo(ExceptionLog elog)throws Exception;
   
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
   public Integer searchLogListCount(ExceptionLog elog)throws Exception;
}
