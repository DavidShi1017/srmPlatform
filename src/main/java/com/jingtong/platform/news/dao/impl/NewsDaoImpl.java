package com.jingtong.platform.news.dao.impl;

import java.util.List;

import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.news.dao.INewsDao;
 import com.jingtong.platform.news.pojo.NewsDetail;
import com.jingtong.platform.news.pojo.NewsFile;
import com.jingtong.platform.news.pojo.NewsRecord;
import com.jingtong.platform.news.pojo.NewsTotal;

public class NewsDaoImpl extends BaseDaoImpl implements INewsDao {

	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsList() {
		return getSqlMapClientTemplate().queryForList("news.getNewsList");
	}

	/**
	 * ��ȡ�����ܱ���Ϣ
	 * 
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsTotal> getNewsTotalList() {
		return getSqlMapClientTemplate().queryForList("news.getNewsTotalList");
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsDetailList(NewsTotal lanNewsTotal) {
		return getSqlMapClientTemplate().queryForList("news.getNewsDetailList",
				lanNewsTotal);
	}

	/**
	 * ��ȡ������ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	
	public NewsDetail getNewsDetail(NewsDetail NewsDetail) {
		return (NewsDetail) getSqlMapClientTemplate().queryForObject(
				"news.getNewsDetail", NewsDetail);
	}

	/**
	 * �������ŵ������
	 * 
	 * @param NewsDetail
	 * @return
	 */
	
	public int updateNewsDetail(NewsDetail NewsDetail) {
		return getSqlMapClientTemplate().update("news.updateNewsDetail",
				NewsDetail);
	}

	/**
	 * ��ȡ������ϸ����
	 * 
	 * @param NewsDetail
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsFile> getNewsFileList(NewsFile NewsFile) {
		return getSqlMapClientTemplate().queryForList("news.getNewsFileList",
				NewsFile);
	}

	/**
	 * ��ȡ������Ŀ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsTotal> getNewsTreeTypeList(NewsTotal lanNewsTotal) {
		return getSqlMapClientTemplate().queryForList(
				"news.getNewsTreeTypeList", lanNewsTotal);
	}

	/**
	 * ���������ܱ�lanNewsTotal
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public Long createNewsTotal(NewsTotal lanNewsTotal) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsTotal",
				lanNewsTotal);
	}

	/**
	 * ��ȡ�����ܱ�����
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public int getNewsTotalJsonCount(NewsTotal lanNewsTotal) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"news.getNewsTotalJsonCount", lanNewsTotal);
	}

	/**
	 * ��ȡ�����ܱ��ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsTotal> getNewsTotalJsonList(NewsTotal lanNewsTotal) {
		return getSqlMapClientTemplate().queryForList(
				"news.getNewsTotalJsonList", lanNewsTotal);
	}

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public int getNewsDetailJsonCount(NewsDetail lanNewsDetail) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"news.getNewsDetailJsonCount", lanNewsDetail);
	}

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsDetailJsonList(NewsDetail lanNewsDetail) {
		return getSqlMapClientTemplate().queryForList(
				"news.getNewsDetailJsonList", lanNewsDetail);

	}

	/**
	 * ɾ����ϸ
	 * 
	 * @param NewsDetail
	 * @return
	 */
	
	public int deleteNewsDetail(NewsDetail NewsDetail) {
		return getSqlMapClientTemplate().update("news.deleteNewsDetail",
				NewsDetail);
	}

	/**
	 * ��ȡ������ϸ������
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public int getNewsDetailMoreListCount(NewsTotal lanNewsTotal) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"news.getNewsDetailMoreListCount", lanNewsTotal);
	}

	/**
	 * ��ȡ������ϸ���ҳ
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsDetailMoreList(NewsTotal lanNewsTotal) {
		return getSqlMapClientTemplate().queryForList(
				"news.getNewsDetailMoreList", lanNewsTotal);
	}

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	
	public Long createNewsDetail(NewsDetail lanNewsDetail) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsDetail",
				lanNewsDetail);
	}

	/**
	 * ����������ϸ��lanNewsDetail
	 * 
	 * @param lanNewsDetail
	 * @return
	 */
	
	public Long createNewsFile(NewsFile newsFilebean) {
		return (Long) getSqlMapClientTemplate().insert("news.createNewsFile",
				newsFilebean);
	}

	/**
	 * ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public int updateNewsTotal(NewsTotal lanNewsTotal) {
		return getSqlMapClientTemplate().update("news.updateNewsTotal",
				lanNewsTotal);

	}

	/**
	 * ��ȡ���������ܱ�
	 * 
	 * @param lanNewsTotal
	 * @return
	 */
	
	public NewsTotal getNewsTotal(NewsTotal lanNewsTotal) {
		return (NewsTotal) this.getSqlMapClientTemplate().queryForObject(
				"news.getNewsTotal", lanNewsTotal);
	}

	/**
	 * �޸�������ϸ������
	 * 
	 * @param newsFilebean
	 * @return
	 */
	
	public int updateNewsFile(NewsFile newsFilebean) {
		return getSqlMapClientTemplate().update("news.updateNewsFile",
				newsFilebean);
	}

	
	public int getTotalShowCount() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"news.getTotalShowCount");
	}

	
	public Long addRecord(NewsRecord r) {
		return (Long) this.getSqlMapClientTemplate()
				.insert("news.addRecord", r);
	}

	
	public void recordScanTime(NewsRecord r) {
		this.getSqlMapClientTemplate().update("news.recordScanTime", r);
	}

	
	public List<NewsRecord> searchScanRecord(NewsRecord r) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int blurNewsCode(NewsTotal newsTotal) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"news.blurNewsCode",newsTotal);
	}

	 
}
