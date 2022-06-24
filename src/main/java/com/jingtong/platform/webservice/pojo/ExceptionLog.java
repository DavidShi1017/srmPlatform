package com.jingtong.platform.webservice.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ��־����
 * 
 * @author XIA_QX
 * 
 */
public class ExceptionLog extends SearchInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long eId;// ���
	private String interfaceName;// �ӿ�����
	private String operateUser; // ������
	private Date operateTime;// ����ʱ��
	private String logInfo;// ��־��Ϣ
	private String logDesc;// ����

	public long geteId() {
		return eId;
	}

	public void seteId(long eId) {
		this.eId = eId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
    
}
