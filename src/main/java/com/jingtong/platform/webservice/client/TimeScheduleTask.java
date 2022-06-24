package com.jingtong.platform.webservice.client;

import java.util.Date;

import org.apache.log4j.Logger;

import com.jingtong.platform.webservice.service.IClient;

/**
 * ��ʱ���������
 * @author XIA_QX
 *
 */
public class TimeScheduleTask {
    private IClient iClient;
	Logger log = Logger.getLogger(TimeScheduleTask.class);
	
	
	public void executeClientEmailExpireDR() throws Exception{
		System.out.println("��ʱ����(DRʧЧ����)��ʼ"+new Date());
		//log.fatal("DRʧЧ����:"+new Date());
		iClient.executeClientEmailExpireDR();
	}
	
	
	public void executeClientEmailExpireQuote() throws Exception{
		System.out.println("��ʱ����(QuoteʧЧ����)��ʼ"+new Date());
		//log.fatal("DRʧЧ����:"+new Date());
		iClient.executeClientEmailExpireQuote();
	}
	
	public void executeClientSendEmail() throws Exception{
		System.out.println("��ʱ����(�ʼ����������)��ʼ"+new Date());
		//log.fatal("DRʧЧ����:"+new Date());
		iClient.executeClientSendEmail();
	}

	public IClient getiClient() {
		return iClient;
	}


	public void setiClient(IClient iClient) {
		this.iClient = iClient;
	}


	public Logger getLog() {
		return log;
	}


	public void setLog(Logger log) {
		this.log = log;
	}
	 

	
	
	
}
