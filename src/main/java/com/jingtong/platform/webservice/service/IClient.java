package com.jingtong.platform.webservice.service;

import java.util.List;
import com.jingtong.platform.webservice.pojo.SendMessage;

/**
 * webservice���ÿͻ���
 * @author XIA_QX
 *
 */
public interface IClient {

	public void executeClientEmailExpireDR();

	void executeClientSendEmail();

	void executeClientEmailExpireQuote();

//	/**
//	 * ��ʱ��ֹѯ�۵�
//	 */
//	public void executeClientXunjia();
	
 }
