package com.jingtong.platform.wfe.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jingtong.platform.base.dao.impl.BaseDaoImpl;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.file.pojo.BudgetFileTmp;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.dao.IEventDao;
import com.jingtong.platform.wfe.pojo.Cc;
import com.jingtong.platform.wfe.pojo.Leave;
import com.jingtong.platform.wfe.pojo.LinkMan;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.pojo.TripWay;

@SuppressWarnings("rawtypes")
public class EventDaoImpl extends BaseDaoImpl implements IEventDao {

	/**
	 * ��ȡ����ָ����ǰ�����˶�Ӧ������Detail(��һ��,�п��ܶ���)
	 * @param eventId
	 * @param userId
	 * @return
	 */
	
	public String getProEventDetail(String eventId, String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", Long.parseLong(eventId.trim()));
		params.put("cur_user_id", userId);
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getDeatilByCurUserAndEventID", params);
	}
	
	
	public Long getEvent_XmlTempId() {
		return (Long)getSqlMapClientTemplate().queryForObject("wfe.getEvent_XmlTempID");
	}

	
	public void updateLeave(Leave leave) {
		getSqlMapClientTemplate().update("wfe.updateEntity", leave);
	}

	
	public String getWfeActionId(String eventId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getWfeActionId",eventId);
	}
	/**
	 * ��ȡ������ϸ
	 */
	
	public ProEventTotal getEventTotalById(Long eventId) {
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalById", eventId);
	}
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	
	public ProEventTotal getEventTotalById(Long eventId,Long ccId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", eventId);
		params.put("cc_id", ccId);
		
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalByIdAndCcId", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail) {
		return getSqlMapClientTemplate().queryForList(
				"wfe.getEventDetailList", eventDetail);
	}
	
	
	public Long createBudgetFileTmp(BudgetFileTmp budgetFileTmp) {
		return (Long) getSqlMapClientTemplate().insert(
				"wfe.createBudgetFileTmp", budgetFileTmp);
	}

	/** ��ϵ�˸���  */
	
	public int getLinkManCount(LinkMan linkMan) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"wfe.getLinkManCount", linkMan);
	}
	
	/** ѡ������ϵ�� */
	
	@SuppressWarnings("unchecked")
	public List<LinkMan> getLinkManList(LinkMan linkMan) {
		return getSqlMapClientTemplate().queryForList("wfe.getLinkManList",
				linkMan);
	}
	
	/** ������ϵ��  */
	
	public Long createLinkMan(LinkMan linkMan) {
		return (Long) getSqlMapClientTemplate().insert("wfe.createLinkMan",
				linkMan);
	}
	
	/** �޸���ϵ�� */
	
	public int updateLinkMan(LinkMan linkMan) {
		return getSqlMapClientTemplate().update("wfe.updateLinkMan", linkMan);
	}

	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		return getSqlMapClientTemplate().queryForList("wfe.getStationListByEventId", eventId);
	}
	
	
	public void updateBuglogFile(Long eventId, String state){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventId", eventId);
		map.put("state", state);
		getSqlMapClientTemplate().update("wfe.updateBuglogFile", map);
	}
	
	
	public int getTripWayCount(TripWay tripWay) {
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripWayCount", tripWay);
	};
	
	
	@SuppressWarnings("unchecked")
	public List<TripWay> getTripWayList(TripWay tripWay) {
		return getSqlMapClientTemplate().queryForList("wfe.getTripWayList", tripWay);
	};
	/**
	 * ��������ģ���ѯ��������
	 */
	
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripApplyListCount", proEventTotal);
	}
	/**
	 * ��������Ų�ѯ�����б�
	 */
	
	@SuppressWarnings("unchecked")
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getTripApplyList", proEventTotal);
	}
	
	/***
	 * ����������
	 * @param cc
	 * @return
	 */
	
	public StringResult createCc(final List<Cc> ccList){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            
			public Object doInSqlMapClient(SqlMapExecutor executor)throws SQLException {
                executor.startBatch();
                for(Cc cc : ccList) {
                	executor.insert("wfe.createCc", cc);
                }
                executor.executeBatch();
                StringResult stringResult = new StringResult();
                stringResult.setCode("success");
                return stringResult;
            }
        });
		return null;
	}
	
	/**
	 * �޸ĳ�����Ϣ
	 * @param businessTripApplyList
	 * @return
	 */
	
	public int  updateCc(Cc cc){
		return getSqlMapClientTemplate().update("wfe.updateCc", cc);
	}
	
	/**
	 * ��ȡ������Ϣ�б�����
	 * @param cc
	 * @return
	 */
	
	public int  getCcListCount(Cc cc){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getCcListCount", cc);
	}
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Cc> getCcList(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcList", cc);
	}
	/***
	 * ��ȡ������Ϣ�б�
	 * @param cc
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Cc> getCcListByEventId(Cc cc){
		return getSqlMapClientTemplate().queryForList("wfe.getCcListByEventId", cc);

	}

	/**������ԱID���Ҹ��˶�Ӧ�ĸ�λID**/
	
	public String getStationIdByUserId(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getStationIdByUserId", userId);
	}
	
	
	public Integer getCancelEventCount(ProcessEventTotal eventTotal){
		return (Integer)getSqlMapClientTemplate().queryForObject("wfe.getCancelEventCount", eventTotal);
	}

	
	@SuppressWarnings("unchecked")
	public List<ProcessEventTotal> getCancelEventList(ProcessEventTotal eventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getCancelEventList", eventTotal);
	}

}
