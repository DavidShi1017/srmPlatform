package com.jingtong.platform.enquiry.dao;

import java.util.List;

import com.jingtong.platform.enquiry.pojo.Enquiry;
import com.jingtong.platform.enquiry.pojo.EnquiryDetail;



public interface IEnquiryDao {
	/**
	 * ��ȡѯ�۵��б���
	 * @param c
	 * @return
	 */
	public int getEnquiryListCount(Enquiry o);
	/**
	 * ��ȡѯ�۵���Ϣ�б�
	 * @param c
	 * @return
	 */
	public List<Enquiry> getEnquiryList(Enquiry o);
	/**
	 * ����ID��ȡѯ�۵���Ϣ
	 * @param c
	 * @return
	 */
	public Enquiry getEnquiryById(Enquiry o);
	/**
	 * ѯ�۵���Ϣ����
	 * @param p
	 * @return
	 */
	public long createEnquiry(Enquiry o);
	/**
	 * �޸�ѯ�۵���Ϣ
	 * @param o
	 * @return
	 */
	public int updateEnquiry(Enquiry o);
	/**
	 * ɾ��ѯ�۵���Ϣ(�߼�ɾ��)
	 * @param o
	 * @return
	 */
	public int deleteEnquiry(Enquiry o);
	
	
	

	/**
	 * ��ȡѯ�۵���ϸ��Ϣ�б�
	 * @param od
	 * @return
	 */
	public List<EnquiryDetail> getEnquiryDetailList(EnquiryDetail od);

	/**
	 * ѯ�۵���ϸ��Ϣ����
	 * @param od
	 * @return
	 */
	public long createEnquiryDetail(EnquiryDetail od);
	/**
	 * �޸�ѯ�۵���ϸ��Ϣ
	 * @param od
	 * @return
	 */
	public int updateEnquiryDetail(EnquiryDetail od);
	/**
	 * ɾ��ѯ�۵���ϸ��Ϣ(����ɾ��)
	 * @param od
	 * @return
	 */
	public int deleteEnquiryDetail(EnquiryDetail od);

}
