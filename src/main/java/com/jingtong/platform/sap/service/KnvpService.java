package com.jingtong.platform.sap.service;

import java.util.List;

import com.jingtong.platform.sap.pojo.Knvp;

public interface KnvpService {
/**
 * ��ȡ ��Ʊ�����۴﷽���ʹ﷽�����������
 */
	public void getKnvpFromSAP();
	
	public List<Knvp> getKunnrList(String kunnr);
}
