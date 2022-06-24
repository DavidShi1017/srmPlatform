package com.jingtong.platform.enquiry.service;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;

public interface IEnquiryService {
	/**
	 * ��ȡ�����б���
	 * @param o
	 * @return
	 */
	public int getEnquiryListCount(Enquiry o);
	/**
	 * ��ȡ������Ϣ�б�
	 * @param o
	 * @return
	 */
	public List<Enquiry> getEnquiryList(Enquiry o);
	/**
	 * ����ID��ȡ������Ϣ
	 * @param o
	 * @return
	 */
	public Enquiry getEnquiryById(Enquiry o);
	/**
	 * ������Ϣ����
	 * @param o
	 * @return
	 */
	public BooleanResult createEnquiry(Enquiry o,List<EnquiryDetail> odList);
	/**
	 * �޸Ķ�����Ϣ
	 * @param o
	 * @return
	 */
	public BooleanResult updateEnquiry(Enquiry o,List<EnquiryDetail> odList,EnquiryDetail od);
	/**
	 * ɾ��������Ϣ(�߼�ɾ��)
	 * @param o
	 * @return
	 */
	public int deleteEnquiry(Enquiry o);
	
	
	

	/**
	 * ��ȡ������ϸ��Ϣ�б�
	 * @param od
	 * @return
	 */
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail od);

	/**
	 * ������ϸ��Ϣ����
	 * @param od
	 * @return
	 */
	public long createEnquiryDetail(EnquiryDetail od);
	/**
	 * �޸Ķ�����ϸ��Ϣ
	 * @param od
	 * @return
	 */
	public int updateEnquiryDetail(EnquiryDetail od);
	/**
	 * ɾ��������ϸ��Ϣ(����ɾ��)
	 * @param od
	 * @return
	 */
	public int deleteEnquiryDetail(EnquiryDetail od);
}
