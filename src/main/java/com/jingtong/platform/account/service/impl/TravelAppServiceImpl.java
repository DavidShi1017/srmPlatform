package com.jingtong.platform.account.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.account.dao.IAccountDao;
import com.jingtong.platform.account.dao.ITravelAppDao;
import com.jingtong.platform.account.pojo.BNumberDetail;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.TravelDetail;
import com.jingtong.platform.account.pojo.TravelTotal;
import com.jingtong.platform.account.service.ITravelAppService;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.service.IEventService;

public class TravelAppServiceImpl  implements ITravelAppService{
	private static final Logger logger = Logger.getLogger(TravelAppServiceImpl.class);
	private TransactionTemplate transactionTemplate;
	private ITravelAppDao travelAppDao;
	private IAccountDao accountDao;
	
	public StringResult saveTravelTotal(final TravelTotal travelTotal) {
		StringResult result = (StringResult) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus ts) {
				StringResult result = new StringResult();
				try {
					
						/*List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
						BudgetNumber budgetNumber=new BudgetNumber();
						budgetNumber.setStart(0);
						budgetNumber.setEnd(1);
						budgetNumber.setCostCentId(travelTotal.getCostOrgId());
						budgetNumber.setSubjectId(travelTotal.getCostType());
						budgetNumber.setBudget_number(travelTotal.getBudgetNumber());
						List<BudgetNumber> budgetNumberList =accountDao.getBudgetNumberList(budgetNumber);
						if(budgetNumberList!=null&&budgetNumberList.size()!=0){
							budgetNumber=budgetNumberList.get(0);
							if(budgetNumber.getBudget_money().compareTo(travelTotal.getTotalMoney())==-1){
								result.setCode(IEventService.ERROR);
								result.setResult("�����ܵ�ʧ�ܣ�Ԥ����С������������ظ��ύ��");
								return result;
							}
						}else{
							result.setCode(IEventService.ERROR);
							result.setResult("Ԥ����Ϊ"+travelTotal.getBudgetNumber()+"�ķ��ÿ�Ŀ���޷���ѯ�����ݣ�����ϵ����Ա��");
							return result;
						}*/
						Long businessKey = travelAppDao.createTravelTotal(travelTotal);
						if (businessKey != 0l) {
							for (TravelDetail detail : travelTotal.getTravelDetailList()) {
								detail.setTravelId(businessKey);
								travelAppDao.createTravelDetail(detail);
							}
							
							
							/*if (travelTotal != null) {
									// �ۼ�Ԥ��
									BigDecimal b1 = new BigDecimal(0);
									BNumberDetail bd = new BNumberDetail();
									// �ۼ�Ԥ��
									bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // ���ñ������ID
									bd.setPlan_id(travelTotal.getTravelId()); // ��������ID
									bd.setOp_money1(b1.subtract(travelTotal.getTotalMoney()));// �������
									bd.setOperatorid(travelTotal.getCreateUserId());// ��������Ϣ
									bd.setOperatorname(travelTotal.getPayee()); // ����������
									bd.setOp_orgid(String.valueOf(travelTotal.getOrgId()));// ��֯ID
									bd.setOp_orgname(travelTotal.getOrgName()); // ��֯����
									bd.setReason("���÷�������");
									bd.setREASON_NUM(Long.valueOf(11));// ����ԭ��
									bd.setCHECK_FLAG("Y");
									bd.setOa_id(travelTotal.getTransactionId());
									bd.setBudget_number(travelTotal.getBudgetNumber());// Ԥ���Ŀ���
									if(StringUtils.isEmpty(bd.getOa_id())){
										result.setCode(IEventService.ERROR);
										result.setResult("�����ܵ�ʧ��,�޷�����Ԥ����Ϣ�������ظ��ύ��");
									}
									bNumberDetails.add(bd);
									accountDao.creatBudgetDetail(bNumberDetails,"1");// ���ýӿڱ���
							}*/
							result.setCode(IEventService.SUCCESS);
						}else{
							ts.setRollbackOnly();
							result.setCode(IEventService.ERROR);
							result.setResult("�����ܵ�ʧ�ܣ������ظ��ύ��");
						}
					
							
					} catch (Exception e) {
						logger.error(e);
						ts.setRollbackOnly();
						e.printStackTrace();
						result.setCode(IEventService.ERROR);
						result.setResult(IEventService.ERROR_MESSAGE);
					}
				return result;
				}	
		});
		return result;
	}
	
	/**
	 * ���������걨ʱ�ܾ�����
	 */
	public BooleanResult refuseTravelTr(TravelTotal travelTotal) {
		//��ʼ��
		BooleanResult booleanResult = new BooleanResult();
		try {
			travelTotal.setStart(0);
			travelTotal.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(travelTotal);
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			if(tlise!=null&&tlise.size()!=0){
				travelTotal = tlise.get(0);
			}else{
				booleanResult.setResult(false);
				booleanResult.setCode("��ѯ������ʧ��");
			}
			BNumberDetail bd = new BNumberDetail();
			// �ۼ�Ԥ��
			bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // ���ñ������ID
			bd.setPlan_id(travelTotal.getTravelId()); // ��������ID
			bd.setREASON_NUM(Long.valueOf(11));// ����ԭ��
			bd.setBudget_number(travelTotal.getBudgetNumber());// Ԥ���Ŀ���
			/*bd.setOld_oa_id(travelTotal.getTransactionId());
			if(bd.getPlan_id()==null||bd.getOld_oa_id()==null||"".equals(bd.getOld_oa_id())){
				booleanResult.setResult(false);
				booleanResult.setCode("���÷������뷵��Ԥ��,�����ֶ�Ϊ����������ϵ����Ա��");
				return booleanResult;
			}*/
			bNumberDetails.add(bd);
			accountDao.creatBudgetDetail(bNumberDetails,"3");// ���ýӿڱ���
			booleanResult.setResult(true);
			booleanResult.setCode("�ܾ��ɹ�");
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("���÷������뷵��Ԥ��ʧ�ܣ�����ϵ����Ա��");
			logger.error(TravelAppServiceImpl.class,e);
		}
		return booleanResult;
	}
	
	public int searchTravelCount(TravelTotal travelTotal) {
		try {
			return travelAppDao.searchTravelCount(travelTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelTotal), e);
		}
		return 0;
	}
	
	
	 
	public List<TravelTotal> searchTravel(TravelTotal travelTotal) {
		try {
			return travelAppDao.searchTravel(travelTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelTotal), e);
		}
		return null;
	}
	
	 
	public TravelTotal searchTraveltotalByTravelId(String travelId,
			String eventId) {
		try {
			TravelTotal travelTotal=new TravelTotal();
			travelTotal.setId(travelId);
			travelTotal.setStart(0);
			travelTotal.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(travelTotal);
			if(tlise!=null&&tlise.size()!=0){
				travelTotal = tlise.get(0);
			}
			List<TravelDetail> detailList = travelAppDao.searchTravelDetail(travelTotal);
			if(travelId != null){
				List<ProEventDetail> eventDetailList = accountDao
						.getAuditorListByEventId(Long.parseLong(eventId));
				StringBuilder sb = new StringBuilder();
				for (ProEventDetail eventDetail : eventDetailList) {
					if (sb.length() == 0) {
						sb.append(eventDetail.getCurUserName());
					} else {
						sb.append("��" + eventDetail.getCurUserName());
					}
				}
				travelTotal.setAuditor(sb.toString());
			}
			
			travelTotal.setTravelDetailList(detailList);
			return travelTotal;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(travelId), e);
		}
		return null;
	}
	
	
	 
	public BooleanResult updateTravel(final TravelTotal travelTotal) {
		BooleanResult result = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			 
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				try {
					travelAppDao.updateTravelTotal(travelTotal);
					List<TravelDetail> travelDetailList=travelTotal.getTravelDetailList();
					TravelDetail travelDetail=new TravelDetail();
					for (int i = 0; i < travelDetailList.size(); i++) {
						travelDetail=travelDetailList.get(i);
						travelAppDao.updateTravelDetail(travelDetail);
					}
					result.setResult(true);
					result.setCode("�޸ı������ɹ�");
				} catch (Exception e) {
					logger.error(e);
					ts.setRollbackOnly();
					e.printStackTrace();
					result.setResult(false);
					result.setCode("�޸ı���������");
				}
				return result;
			}});
		return result;
	}
	
	 
	public BooleanResult exitTravelTotal(TravelTotal travelTotal) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			TravelTotal total=new TravelTotal();
			total.setWriteEventId(travelTotal.getWriteEventId());
			total.setId(Long.toString(travelTotal.getTravelId()));
			total.setStart(0);
			total.setEnd(1);
			List<TravelTotal> tlise=travelAppDao.searchTravel(total);
			travelTotal=tlise.get(0);
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			BigDecimal b1 = new BigDecimal(0);
			BNumberDetail bd = new BNumberDetail();
			bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // ���ñ������ID
			bd.setPlan_id(travelTotal.getTravelId()); // ��������ID
			bd.setOp_money1(b1.subtract(travelTotal.getAuditMoney()));
			bd.setBudget_number(travelTotal.getBudgetNumber());// Ԥ���Ŀ���
			bd.setOperatorid(travelTotal.getCreateUserId());// ��������Ϣ
			bd.setOperatorname(travelTotal.getPayee()); // ����������
			bd.setOp_orgid(String.valueOf(travelTotal.getOrgId()));// ��֯ID
			bd.setOp_orgname(travelTotal.getOrgName()); // ��֯����
			bd.setREASON_NUM(Long.valueOf(3));// ����ԭ��
			bd.setReason("���÷��ú���");
			bd.setCHECK_FLAG("Y");
			bd.setOa_id(travelTotal.getWriteEventId());
			/*bd.setOld_oa_id(travelTotal.getTransactionId());
			if(StringUtils.isEmpty(bd.getOa_id())||StringUtils.isEmpty(bd.getOld_oa_id())){
				booleanResult.setResult(false);
				booleanResult.setCode("�޸Ĳ���Ԥ��ʧ�ܣ�����ϵ����Ա");
			}*/
			bNumberDetails.add(bd);
			accountDao.creatBudgetDetail(bNumberDetails,"2");// ���ýӿڱ���
			booleanResult.setResult(true);
			booleanResult.setCode("�޸Ĳ��ú���������Ϣ�ɹ���");
		} catch (Exception e) {
			logger.error(TravelAppServiceImpl.class,e);
			booleanResult.setResult(false);
			booleanResult.setCode("�޸Ĳ��ú���������Ϣʧ�ܣ�");
		}
		
		return booleanResult;
	}
	
	 
	public BooleanResult updateTravelApp(final TravelTotal travelTotal){
		BooleanResult result = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			 
			public Object doInTransaction(TransactionStatus ts) {
				BooleanResult result = new BooleanResult();
				try {
					travelAppDao.updateTravelTotal(travelTotal);
					List<TravelDetail> travelDetailList=travelTotal.getTravelDetailList();
					TravelDetail travelDetail=new TravelDetail();
					for (int i = 0; i < travelDetailList.size(); i++) {
						travelDetail=travelDetailList.get(i);
						travelAppDao.updateTravelDetail(travelDetail);
					}
					
					List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
					BigDecimal b1 = new BigDecimal(0);
					BNumberDetail bd = new BNumberDetail();
					bd.setBnumber_id(Long.parseLong(travelTotal.getBndetailId())); // ���ñ������ID
					bd.setPlan_id(travelTotal.getTravelId()); // ��������ID
					bd.setOp_money1(b1.subtract(travelTotal.getTotalMoney()));
					bd.setBudget_number(travelTotal.getBudgetNumber());// Ԥ���Ŀ���
					bd.setREASON_NUM(Long.valueOf(11));// ����ԭ��
					//bd.setOld_oa_id(travelTotal.getTransactionId());
					bNumberDetails.add(bd);
					//accountDao.updateBudgetDetail(bNumberDetails);
					result.setResult(true);
					result.setCode("�޸Ĳ������������Ϣ�ɹ���");
					
				} catch (Exception e) {
					logger.error(e);
					ts.setRollbackOnly();
					e.printStackTrace();
					result.setResult(false);
					result.setCode("�޸����뵥����,����ϵ����Ա");
				}
				return result;
			}});
		return result;
	}
	
	 
	public BudgetNumber getBudgetNumber(BudgetNumber budgetNumber) {
		List<BudgetNumber> bListudgetNumber=travelAppDao.getBudgetNumberList(budgetNumber);
		if(bListudgetNumber==null){
			return null;
		}else if(bListudgetNumber.size()>1){
			return null;
		}else{
			return  bListudgetNumber.get(0);
		}
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}
	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public ITravelAppDao getTravelAppDao() {
		return travelAppDao;
	}
	public void setTravelAppDao(ITravelAppDao travelAppDao) {
		this.travelAppDao = travelAppDao;
	}
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	
}
