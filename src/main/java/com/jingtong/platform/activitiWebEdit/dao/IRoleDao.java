package com.jingtong.platform.activitiWebEdit.dao;

import java.util.List;

import com.jingtong.platform.activitiWebEdit.pojo.WorkFlowRole;

public interface IRoleDao {

	/**
	 * ��ѯ�������̽�ɫ����ҵ�ܹ���
	 * 
	 * @param AllUsers
	 * @return
	 */
	public List<WorkFlowRole> searchAllRoles(WorkFlowRole wfr);
	/**
	 * ��ѯ�������̽�ɫCount����ҵ�ܹ���
	 * 
	 * @param AllUsers
	 * @return
	 */
	public int searchAllRolesCount(WorkFlowRole wfr);
	
}
