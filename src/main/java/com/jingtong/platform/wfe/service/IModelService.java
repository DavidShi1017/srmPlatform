package com.jingtong.platform.wfe.service;

import java.util.List;

import com.jingtong.platform.wfe.pojo.ActProcdef;


/**
 * 
 * ����ģ��
 * 
 */
public interface IModelService {

	/**
	 * ��ȡ����ģ��
	 * @return
	 */
	public List<ActProcdef> getModelByRole(ActProcdef actProcdef);
}
