package com.jingtong.platform.webservice.pojo;


import java.io.Serializable;

/**
 * ��Ѷ���ŷ�����
 * @author XIA_QX
 *
 */
public class SendMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778320889553578490L;

	private String mobileNumber;//�ֻ�����
	private String messageContent;//��������
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	
}
