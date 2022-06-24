package com.jingtong.platform.ecgroup.dao;

import java.util.List;

import com.jingtong.platform.ecgroup.pojo.GroupInfo;


/** 
 * @author cl 
 * @createDate 2016-5-25
 * 
 */

public interface IGroupInfoDao {
	/**
	 * ��ѯEC������Ϣ
	 * @return
	 */
	public List<GroupInfo> searchGroupInfo(GroupInfo g);
	
	/**
	 * ����µ���Ϣ
	 * @param g
	 */
	public long saveGroupInfo(GroupInfo g);
	
	/**
	 * �޸���Ϣ
	 * @param g
	 */
	public int updateGroupInfo(GroupInfo g);
	
	
	
	/**
	 * ɾ����Ϣ
	 * @param g
	 */
	public int deleteGroupInfoById(GroupInfo g);

	public GroupInfo getGroupInfoById(GroupInfo g);
	
	
	public int getGroupInfoListCount(GroupInfo g);
	public int getCountByEcgroupId(GroupInfo g);
	
	public int auditGroup(GroupInfo g);

	public int getGroupListCount(GroupInfo g);

	List<GroupInfo> getGroupList(GroupInfo g);

	public int setECGroupCode(GroupInfo g);
}
