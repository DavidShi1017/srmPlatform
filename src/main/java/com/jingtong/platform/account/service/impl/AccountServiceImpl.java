package com.jingtong.platform.account.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.account.dao.IAccountDao;
import com.jingtong.platform.account.pojo.BNumberDetail;
import com.jingtong.platform.account.pojo.BudgetNumber;
import com.jingtong.platform.account.pojo.CostType;
import com.jingtong.platform.account.pojo.ExpensesDetail;
import com.jingtong.platform.account.pojo.ExpensesTotal;
import com.jingtong.platform.account.pojo.PayeeInfo;
import com.jingtong.platform.account.pojo.Region;
import com.jingtong.platform.account.pojo.SingleDetail;
import com.jingtong.platform.account.pojo.SingleTotal;
import com.jingtong.platform.account.pojo.WorkPlanTotal;
import com.jingtong.platform.account.service.IAccountService;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.framework.util.LogUtil;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.wfe.pojo.ProEventDetail;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.service.IEventService;

public class AccountServiceImpl implements IAccountService {

	private static final Logger logger = Logger
			.getLogger(AccountServiceImpl.class);

	private IAccountDao accountDao;
	private String downloadPath;
	private TransactionTemplate transactionTemplate;

	/**
	 * ���汨����
	 */
	public StringResult createSingle(final SingleTotal singleTotal) {
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public StringResult doInTransaction(TransactionStatus ts) {
						StringResult stringResult = new StringResult();
						try {
							SingleTotal total = accountDao
									.getSingleTotal(singleTotal);
							if (total == null) { // �ܵ��Ƿ��Ѿ��������ݿ�
								Long businessKey = accountDao
										.saveSingleTotal(singleTotal);
								if (businessKey != 0l) {
									for (SingleDetail detail : singleTotal
											.getDetailList()) {
										detail.setPlan_id(businessKey);
									}
									accountDao.saveSingleDetail(singleTotal
											.getDetailList());

									/*
									 * // ��ѯ������ϸ SingleDetail singleDetail = new
									 * SingleDetail(); List<BNumberDetail>
									 * bNumberDetails = new
									 * ArrayList<BNumberDetail>();
									 * singleDetail.setPlan_id(businessKey);
									 * singleDetail.setStart(0);
									 * singleDetail.setEnd(10000);
									 * 
									 * List<SingleDetail> singleDetails = new
									 * ArrayList<SingleDetail>();
									 * 
									 * singleDetails =
									 * accountDao.searchSingleDetailList
									 * (singleDetail);
									 * 
									 * if (singleDetails != null &&
									 * singleDetails.size() > 0) { for
									 * (SingleDetail singleDetail2 :
									 * singleDetails) { // ����������ϸ // �ۼ�Ԥ��
									 * BigDecimal b1 = new BigDecimal(0);
									 * BNumberDetail bd = new BNumberDetail();
									 * // �ۼ�Ԥ��
									 * bd.setBnumber_id(singleDetail2.getBndetail_id
									 * ()); // ���ñ������ID
									 * bd.setPlan_id(singleDetail2
									 * .getPlan_id()); // ��������ID
									 * bd.setDetail_id(
									 * singleDetail2.getDetail_id()); // ������ϸID
									 * bd.setOp_money1(b1.subtract(singleDetail2
									 * .getInvoice_amount()));// �������
									 * bd.setOperatorid
									 * (singleTotal.getUser_id());// ��������Ϣ
									 * bd.setOperatorname
									 * (singleTotal.getUser_name()); // ����������
									 * bd.
									 * setOp_orgid(String.valueOf(singleTotal.
									 * getOrg_id()));// ��֯ID
									 * bd.setOp_orgname(singleTotal
									 * .getOrg_name()); // ��֯����
									 * bd.setReason("ְ�ܷ�������");
									 * bd.setREASON_NUM(Long.valueOf(10));//
									 * ����ԭ�� bd.setCHECK_FLAG("Y");
									 * bd.setBudget_number
									 * (singleDetail2.getBudget_number());//
									 * Ԥ���Ŀ���
									 * 
									 * bNumberDetails.add(bd); }
									 * accountDao.creatBudgetDetail
									 * (bNumberDetails,"1");// ���ýӿڱ���
									 * 
									 * }
									 */
									stringResult.setCode(IEventService.SUCCESS);
								} else {
									stringResult.setCode(IEventService.ERROR);
									stringResult.setResult("�����ܵ�ʧ�ܣ������ظ��ύ��");
									return stringResult;
								}
							} else {
								// ��ѯ������ϸ
								SingleDetail singleDetail = new SingleDetail();
								// List<BNumberDetail> bNumberDetails = new
								// ArrayList<BNumberDetail>();
								singleDetail.setPlan_id(total.getPlan_id());
								singleDetail.setStart(0);
								singleDetail.setEnd(10000);

								List<SingleDetail> singleDetails = new ArrayList<SingleDetail>();
								singleDetails = accountDao
										.searchSingleDetailList(singleDetail);
								if (singleDetails != null
										&& singleDetails.size() > 0) { // ��ϸ�Ƿ��Ѿ��������ݿ�
									stringResult.setCode(IEventService.ERROR);
									stringResult.setResult("���ݿ����������ݣ������ظ��ύ��");
									return stringResult;
								} else {
									for (SingleDetail detail : singleTotal
											.getDetailList()) {// ��ϸû�в������ݿ�
										detail.setPlan_id(total.getPlan_id());
									}
									accountDao.saveSingleDetail(singleTotal
											.getDetailList());
									/*
									 * singleDetails =
									 * accountDao.searchSingleDetailList
									 * (singleDetail);
									 * 
									 * if (singleDetails != null &&
									 * singleDetails.size() > 0) { for
									 * (SingleDetail singleDetail2 :
									 * singleDetails) { // ����������ϸ // �ۼ�Ԥ��
									 * BigDecimal b1 = new BigDecimal(0);
									 * BNumberDetail bd = new BNumberDetail();
									 * // �ۼ�Ԥ��
									 * bd.setBnumber_id(singleDetail2.getBndetail_id
									 * ()); // ���ñ������ID
									 * bd.setPlan_id(singleDetail2
									 * .getPlan_id()); // ��������ID
									 * bd.setDetail_id(
									 * singleDetail2.getDetail_id()); // ������ϸID
									 * bd.setOp_money1(b1.subtract(singleDetail2
									 * .getInvoice_amount()));// �������
									 * bd.setOperatorid
									 * (singleTotal.getUser_id());// ��������Ϣ
									 * bd.setOperatorname
									 * (singleTotal.getUser_name()); // ����������
									 * bd.
									 * setOp_orgid(String.valueOf(singleTotal.
									 * getOrg_id()));// ��֯ID
									 * bd.setOp_orgname(singleTotal
									 * .getOrg_name()); // ��֯����
									 * bd.setReason("ְ�ܷ�������");
									 * bd.setREASON_NUM(Long.valueOf(10));//
									 * ����ԭ�� bd.setCHECK_FLAG("Y");
									 * bd.setBudget_number
									 * (singleDetail2.getBudget_number());//
									 * Ԥ���Ŀ���
									 * 
									 * bNumberDetails.add(bd); }
									 * accountDao.creatBudgetDetail
									 * (bNumberDetails,"1");// ���ýӿڱ���
									 * 
									 * }
									 */
									stringResult.setCode(IEventService.SUCCESS);
								}
							}

						} catch (Exception e) {
							ts.setRollbackOnly(); // ���������г�����лع�
							logger.error(e);
							stringResult.setCode(IEventService.ERROR);
							stringResult.setResult(IEventService.ERROR_MESSAGE);
						}
						return stringResult;
					}
				});
		return result;
	}

	/***
	 * �����ճ���������
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public StringResult createExpenses(final ExpensesTotal expensesTotal) {
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					
					public StringResult doInTransaction(TransactionStatus ts) {
						StringResult stringResult = new StringResult();
						try {
							// �ܵ��Ƿ��Ѿ��������ݿ�
							Long businessKey = accountDao
									.saveExpensesTotal(expensesTotal);
							if (businessKey != 0l) {
								for (ExpensesDetail detail : expensesTotal
										.getDetailList()) {
									detail.setExpenses_total_id(businessKey);
								}
								accountDao.saveExpensesDetail(expensesTotal
										.getDetailList());
								stringResult.setCode(IEventService.SUCCESS);
							} else {
								stringResult.setCode(IEventService.ERROR);
								stringResult.setResult("�����ܵ�ʧ�ܣ������ظ��ύ��");
								return stringResult;
							}

						} catch (Exception e) {
							ts.setRollbackOnly(); // ���������г�����лع�
							logger.error(e);
							stringResult.setCode(IEventService.ERROR);
							stringResult.setResult(IEventService.ERROR_MESSAGE);
						}
						return stringResult;
					}
				});
		return result;
	}

	/***
	 * ��ѯ�ճ������ܵ��б�
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public int searchExpensesCount(ExpensesTotal expensesTotal) {
		try {
			return accountDao.searchExpensesCount(expensesTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	/***
	 * ��ѯ�ճ������ܵ��б�
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public List<ExpensesTotal> searchExpensesList(ExpensesTotal expensesTotal) {
		try {
			return accountDao.searchExpensesList(expensesTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	/***
	 * ��ȡ�����ճ������ܵ�
	 * 
	 * @param expensesTotal
	 * @return
	 */
	public ExpensesTotal getExpensesTotalAndDetail(ExpensesTotal expensesTotal) {
		ExpensesTotal total = new ExpensesTotal();
		List<ExpensesDetail> detailList = new ArrayList<ExpensesDetail>();

		ExpensesDetail detail = new ExpensesDetail();
		try {
			total = accountDao.getExpenses(expensesTotal); // ��ȡ�����ܵ�
			if (total != null) {
				if (total.getTransaction_id() != null) {
					List<ProEventDetail> eventDetailList = accountDao
							.getAuditorListByEventId(Long.parseLong(total.getTransaction_id()));
					StringBuilder sb = new StringBuilder();
					for (ProEventDetail eventDetail : eventDetailList) {
						if (sb.length() == 0) {
							sb.append(eventDetail.getCurUserName());
						} else {
							sb.append("��" + eventDetail.getCurUserName());
						}
					}
					total.setAuditor(sb.toString());
				}
				detail.setExpenses_total_id(total.getExpenses_total_id());
				detailList = accountDao.searchExpensesDetailList(detail);
				total.setDetailList(detailList);
			}
			

		} catch (Exception e) {
			logger.error(e);
		}
		return total;
	}
	/**
	 * ��ѯ��ǰ�û���ɫ
	 */
	
	public boolean getUserRoles(AllUsers user, String roleId) {
		user.setRoleIds(roleId);
		boolean flag = false;
		try {
			flag = accountDao.getUserRoles(user) > 0;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(user), e);
		}
		return flag;
	}

	/**
	 * ��ѯ����������(��ҳ)
	 */
	 
	public int searchTraReimburCount(SingleTotal singleTotal) {
		try {
			return accountDao.searchTraReimburCount(singleTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleTotal), e);
		}
		return 0;
	}

	/**
	 * ��ѯ������(��ҳ)
	 */
	 
	public List<SingleTotal> searchTraReimburList(SingleTotal singleTotal) {
		try {
			return accountDao.searchTraReimburList(singleTotal);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleTotal), e);
		}
		return null;
	}

	/**
	 * ��ѯ��������ϸ����(��ҳ)
	 */
	 
	public int searchSingleDetailCount(SingleDetail singleDetail) {
		try {
			return accountDao.searchSingleDetailCount(singleDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleDetail), e);
		}
		return 0;
	}

	/**
	 * ��ѯ��������ϸ(��ҳ)
	 */
	 
	public List<SingleDetail> searchSingleDetailList(SingleDetail singleDetail) {
		try {
			return accountDao.searchSingleDetailList(singleDetail);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(singleDetail), e);
		}
		return null;
	}

	/**
	 * ͨ��������ѯ������(���������ݣ�����ҳ)
	 */
	 
	public SingleTotal searchSingelTotalByPlanId(Long planId,
			String transaction_id) {
		try {
			SingleTotal sinTotal = accountDao.searchSingleTotalByPlanId(planId);
			List<SingleDetail> detailList = accountDao
					.searchSingleDetailByPlanId(planId);
			if (transaction_id != null) {
				List<ProEventDetail> eventDetailList = accountDao
						.getAuditorListByEventId(Long.parseLong(transaction_id));
				StringBuilder sb = new StringBuilder();
				for (ProEventDetail eventDetail : eventDetailList) {
					if (sb.length() == 0) {
						sb.append(eventDetail.getCurUserName());
					} else {
						sb.append("��" + eventDetail.getCurUserName());
					}
				}
				sinTotal.setAuditor(sb.toString());
			}
			sinTotal.setDetailList(detailList);
			return sinTotal;
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(planId), e);
		}
		return null;
	}

	/**
	 * ���
	 */
	 
	public StringResult playMoney(String ids) {
		StringResult result = new StringResult();
		try {
			accountDao.playMoney(ids);
			result.setCode(IAccountService.SUCCESS);
			result.setResult(IAccountService.SUCCESS_MESSAGE);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
			result.setResult(IAccountService.ERROR_MESSAGE);
		}
		return result;
	}

	/**
	 * ��������������Excel��
	 */
	 
	public File exportTraReimbur(SingleTotal singleTotal) {
		try {
			// ������ģ���ļ�����·��
			File saveDir = new File(downloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(downloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			List<SingleTotal> singleTotalList = accountDao
					.searchTraReimburListToExcel(singleTotal);
			// д���ݵ�Excel�����
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			// ����������ʽ
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // ���õ�Ԫ��ı�����ɫ

			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);

			Label label;
			jxl.write.Number labelNum;
			BigDecimal total = new BigDecimal(0);
			// ���õ�һ���и�
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "�տ���", formatHead);
			sheet.addCell(label);
			// �����п�
			sheet.setColumnView(0, 20);

			label = new Label(1, 0, "�տ��˺�", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 40);

			label = new Label(2, 0, "ʵ����˺���", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);

			label = new Label(3, 0, "����ID", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);

			label = new Label(4, 0, "������̧ͷ˵��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 50);

			label = new Label(5, 0, "���ƾ֤��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);

			for (int i = 0; i < singleTotalList.size(); i++) {
				label = new Label(0, i + 1, singleTotalList.get(i).getPay_ee(),
						formatTable);
				sheet.addCell(label);
				label = new Label(1, i + 1, singleTotalList.get(i)
						.getPay_account(), formatTable);
				sheet.addCell(label);
				labelNum = new jxl.write.Number(2, i + 1, singleTotalList
						.get(i).getAudit_money().doubleValue(), formatTable);// ��ֵ��ʽ
				sheet.addCell(labelNum);
				label = new Label(3, i + 1, singleTotalList.get(i)
						.getTransaction_id(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i + 1, singleTotalList.get(i).getMemo(),
						formatTable);
				sheet.addCell(label);
				label = new Label(5, i + 1, singleTotalList.get(i)
						.getFinancial_doc_num(), formatTable);
				sheet.addCell(label);
				total = total.add(singleTotalList.get(i).getAudit_money());
			}
			label = new Label(2, singleTotalList.size() + 1, "SUM:"
					+ total.toString(), formatTable);
			sheet.addCell(label);
			book.write();
			book.close();
			return rootFile;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	 
	public StringResult updateSingle(SingleTotal singleTotal) {
		StringResult result = new StringResult();
		/*
		 * List<SingleDetail> singleDetailListForInsert = new
		 * ArrayList<SingleDetail>(); List<SingleDetail>
		 * singleDetailListForUpdate = new ArrayList<SingleDetail>();
		 */
		try {
			/*
			 * for (SingleDetail singleDetail : singleTotal.getDetailList()) {
			 * if (singleDetail.getDetail_id() != null) {
			 * singleDetailListForUpdate.add(singleDetail); } else {
			 * singleDetailListForInsert.add(singleDetail); } }
			 */
			accountDao.updateSingleTotal(singleTotal);
			accountDao.updateSingleDetail(singleTotal.getDetailList());

			/*
			 * if(singleDetailListForUpdate != null &&
			 * singleDetailListForUpdate.size() >0){ //��Ҫ����ʱ�����
			 * accountDao.updateSingleDetail(singleDetailListForUpdate); }
			 * if(singleDetailListForInsert != null &&
			 * singleDetailListForInsert.size() >0){ //��Ҫ����ʱ�����
			 * accountDao.saveSingleDetail(singleDetailListForInsert); }
			 */
			// accountDao.updateCostCenter(singleTotal);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	/**
	 * �޸���ϸ
	 * 
	 * @param details
	 * @return
	 */
	 
	public StringResult updateSingleDetail(List<SingleDetail> details) {
		// ��ʼ��
		StringResult result = new StringResult();
		try {
			accountDao.updateSingleDetail(details);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public int searchWorkPlanCount(WorkPlanTotal workPlan) {
		try {
			return accountDao.searchWorkPlanCount(workPlan);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(workPlan), e);
		}
		return 0;
	}

	/**
	 * ѡ����Ŀ(��ҳ)
	 */
	 
	public List<WorkPlanTotal> searchWorkPlan(WorkPlanTotal workPlan) {
		try {
			return accountDao.searchWorkPlan(workPlan);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(workPlan), e);
		}
		return null;
	}

	 
	public int searchCostTypeCount(CmsTbDict dict) {
		try {
			return accountDao.searchCostTypeCount(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}
		return 0;
	}

	 
	public List<CmsTbDict> searchCostTypeList(CmsTbDict dict) {
		try {
			return accountDao.searchCostTypeList(dict);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dict), e);
		}
		return null;
	}

	 
	public String getStationIdByDetailId(Long eventDetailId) {
		try {
			return accountDao.getStationIdByDetailId(eventDetailId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public int getPayeeInfoCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeInfoCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public List<PayeeInfo> getPayeeInfoList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeInfoList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public StringResult addPayeeInfo(PayeeInfo payeeInfo) {
		StringResult result = new StringResult();
		try {
			accountDao.addPayeeInfo(payeeInfo);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public StringResult removePayeeInfo(String ids, String modifier) {
		StringResult result = new StringResult();
		try {
			accountDao.removePayeeInfo(ids, modifier);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public PayeeInfo getPayeeInfoById(Long id) {
		try {
			return accountDao.getPayeeInfoById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public StringResult modifyPayeeInfo(PayeeInfo payeeInfo) {
		StringResult result = new StringResult();
		try {
			accountDao.modifyPayeeInfo(payeeInfo);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public int getPayeeCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public List<PayeeInfo> getPayeeList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayeeList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public int getPayeeInfoCountByName(String userName) {
		try {
			return accountDao.getPayeeInfoCountByName(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public PayeeInfo getDefaultPayee(String userName) {
		try {
			return accountDao.getDefaultPayee(userName);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public int getPayAccountCount(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayAccountCount(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public List<PayeeInfo> getPayAccountList(PayeeInfo payeeInfo) {
		try {
			return accountDao.getPayAccountList(payeeInfo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public int getHisEventCount(ProcessEventTotal eventTotal) {
		try {
			return accountDao.getHisEventCount(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	};

	 
	public List<ProcessEventTotal> getHisEventList(ProcessEventTotal eventTotal) {
		try {
			return accountDao.getHisEventList(eventTotal);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	};

	 
	public List<SingleDetail> getSingleDetailList(String transactionId) {
		try {
			return accountDao.getSingleDetailList(transactionId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public StringResult updateFinancialDocNum(String transaction_id,
			String financial_doc_num) {
		StringResult result = new StringResult();
		try {
			accountDao.updateFinancialDocNum(transaction_id, financial_doc_num);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public int countSingleTotal(String transaction_id) {
		try {
			return accountDao.countSingleTotal(transaction_id);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public StringResult batchUpdateFinancialDocNum(
			List<SingleTotal> singleTotalList) {
		StringResult result = new StringResult();
		try {
			accountDao.batchUpdateFinancialDocNum(singleTotalList);
			result.setCode(IAccountService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setCode(IAccountService.ERROR);
		}
		return result;
	}

	 
	public int getReimburDetailCount(SingleDetail singleDetail) {
		try {
			return accountDao.getReimburDetailCount(singleDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	 
	public List<SingleDetail> getReimburDetailList(SingleDetail singleDetail) {
		try {
			return accountDao.getReimburDetailList(singleDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	 
	public File exportReimberDetailList(SingleDetail singleDetail) {

		try {
			// ������ģ���ļ�����·��
			File saveDir = new File(downloadPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File rootFile = new File(downloadPath + "/" + "emptyBasis.xls");
			if (!rootFile.exists()) {
				rootFile.createNewFile();
			}

			List<SingleDetail> singleDetailList = accountDao
					.getReimburDetailListNoPage(singleDetail);
			// д���ݵ�Excel�����
			WritableWorkbook book = Workbook.createWorkbook(rootFile);
			WritableSheet sheet = book.createSheet("Sheet_1", 0);
			// ����������ʽ
			WritableFont fontHead = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD);
			WritableCellFormat formatHead = new WritableCellFormat(fontHead);
			formatHead.setAlignment(Alignment.CENTRE);
			formatHead.setVerticalAlignment(VerticalAlignment.CENTRE);
			formatHead.setBackground(jxl.format.Colour.YELLOW); // ���õ�Ԫ��ı�����ɫ

			WritableCellFormat formatTable = new WritableCellFormat();
			formatTable.setAlignment(Alignment.CENTRE);
			formatTable.setVerticalAlignment(VerticalAlignment.CENTRE);

			Label label;
			jxl.write.Number labelNum;
			// ���õ�һ���и�
			sheet.setRowView(0, 400);
			label = new Label(0, 0, "����ID", formatHead);
			sheet.addCell(label);
			// �����п�
			sheet.setColumnView(0, 20);

			label = new Label(1, 0, "�տ���", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(1, 20);

			label = new Label(2, 0, "��Ŀ", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(2, 20);

			label = new Label(3, 0, "��Ŀ����", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(3, 20);

			label = new Label(4, 0, "��������", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(4, 20);

			label = new Label(5, 0, "��������", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(5, 20);

			label = new Label(6, 0, "��֧��;", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(6, 40);

			label = new Label(7, 0, "��Ʊ����", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(7, 20);

			label = new Label(8, 0, "��Ʊ���", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(8, 20);

			label = new Label(9, 0, "ʵ�ʽ��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(9, 20);

			label = new Label(10, 0, "��ע", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(10, 40);

			label = new Label(11, 0, "������ʱ��", formatHead);
			sheet.addCell(label);
			sheet.setColumnView(11, 20);

			String temp1 = "yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat formatter1 = new SimpleDateFormat(temp1);
			String temp2 = "yyyy-MM-dd";
			SimpleDateFormat formatter2 = new SimpleDateFormat(temp2);
			for (int i = 0; i < singleDetailList.size(); i++) {
				labelNum = new jxl.write.Number(0, i + 1, singleDetailList
						.get(i).getTransaction_id().doubleValue(), formatTable);// ��ֵ��ʽ
				sheet.addCell(labelNum);
				label = new Label(1, i + 1, singleDetailList.get(i).getPayee(),
						formatTable);
				sheet.addCell(label);
				label = new Label(2, i + 1, singleDetailList.get(i)
						.getProject(), formatTable);
				sheet.addCell(label);
				label = new Label(3, i + 1, singleDetailList.get(i)
						.getProject_manager(), formatTable);
				sheet.addCell(label);
				label = new Label(4, i + 1, singleDetailList.get(i)
						.getCost_type_content(), formatTable);
				sheet.addCell(label);
				label = new Label(5, i + 1, singleDetailList.get(i)
						.getCost_date() == null ? ""
						: formatter2.format(singleDetailList.get(i)
								.getCost_date()), formatTable);
				sheet.addCell(label);
				label = new Label(6, i + 1, singleDetailList.get(i)
						.getCost_purpose(), formatTable);
				sheet.addCell(label);
				labelNum = new jxl.write.Number(7, i + 1, singleDetailList
						.get(i).getInvoice_num().doubleValue(), formatTable);
				sheet.addCell(labelNum);
				labelNum = new jxl.write.Number(8, i + 1, singleDetailList
						.get(i).getInvoice_amount().doubleValue(), formatTable);
				sheet.addCell(labelNum);
				label = new Label(9, i + 1, singleDetailList.get(i)
						.getAudit_money() == null ? "" : singleDetailList
						.get(i).getAudit_money().toString(), formatTable);
				sheet.addCell(label);
				label = new Label(10, i + 1, singleDetailList.get(i)
						.getCost_memo(), formatTable);
				sheet.addCell(label);
				label = new Label(11, i + 1, singleDetailList.get(i)
						.getFinancial_operate_date() == null ? ""
						: formatter1.format(singleDetailList.get(i)
								.getFinancial_operate_date()), formatTable);
				sheet.addCell(label);
			}
			book.write();
			book.close();
			return rootFile;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;

	}

	/***
	 * ��ȡְ�ܷ�������
	 * 
	 * @param costType
	 * @return
	 */
	 
	public List<CostType> getCostTypeListByCostTypeParentId(CostType costType) {
		try {
			return accountDao.getCostTypeListByCostTypeParentId(costType);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * ����ñ������
	 */
	 
	public int getBudgetNumberTotal(BudgetNumber budgetNumber) {
		try {
			return accountDao.getBudgetNumberTotal(budgetNumber);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	/**
	 * ����ñ����ϸ
	 */
	 
	public List<BudgetNumber> getBudgetNumberList(BudgetNumber budgetNumber) {
		try {
			return accountDao.getBudgetNumberList(budgetNumber);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * ����������ɹ��Ժ����
	 */
	 
	public BooleanResult activateAccountPlan(SingleTotal singleTotal) {
		// ��ʼ��
		BooleanResult booleanResult = new BooleanResult();

		try {
			SingleTotal total = new SingleTotal();
			total = accountDao.getSingleTotal(singleTotal); // ��ȡ�ܵ�

			SingleDetail singleDetail = new SingleDetail();
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			singleDetail.setPlan_id(total.getPlan_id());
			singleDetail.setStart(0);
			singleDetail.setEnd(10000);

			List<SingleDetail> singleDetails = new ArrayList<SingleDetail>();

			singleDetails = accountDao.searchSingleDetailList(singleDetail);

			if (singleDetails != null && singleDetails.size() > 0) {
				for (SingleDetail singleDetail2 : singleDetails) { // ����������ϸ
					// �ۼ�Ԥ��
					BigDecimal b1 = new BigDecimal(0);
					BNumberDetail bd = new BNumberDetail();
					// �ۼ�Ԥ��
					bd.setBnumber_id(singleDetail2.getBndetail_id()); // ���ñ������ID
					bd.setPlan_id(singleDetail2.getPlan_id()); // ��������ID
					bd.setDetail_id(singleDetail2.getDetail_id()); // ������ϸID
					bd.setOp_money1(b1.subtract(singleDetail2.getAudit_money()));// �������
					bd.setOperatorid(total.getUser_id());// ��������Ϣ
					bd.setOperatorname(total.getUser_name()); // ����������
					bd.setOp_orgid(String.valueOf(total.getOrg_id()));// ��֯ID
					bd.setOp_orgname(total.getOrg_name()); // ��֯����
					bd.setReason("ְ�ܷ��ú���");
					bd.setREASON_NUM(Long.valueOf(3));// ����ԭ��
					bd.setCHECK_FLAG("Y");
					bd.setBudget_number(singleDetail2.getBudget_number());// Ԥ���Ŀ���
					bNumberDetails.add(bd);
				}
				if (singleDetails != null && singleDetails.size() > 0) {
					accountDao.creatBudgetDetail(bNumberDetails, "2");// ���ýӿڱ���
				}
				booleanResult.setResult(true);
			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("Ԥ���������");
			logger.error(e);
		}
		return booleanResult;
	}

	/**
	 * ���������걨ʱ�ܾ�����
	 */
	 
	public BooleanResult refuseAccountPlan(SingleTotal singleTotal) {

		// ��ʼ��
		BooleanResult booleanResult = new BooleanResult();
		try {
			SingleTotal total = new SingleTotal();
			total = accountDao.getSingleTotal(singleTotal); // ��ȡ�ܵ�

			SingleDetail singleDetail = new SingleDetail();
			List<BNumberDetail> bNumberDetails = new ArrayList<BNumberDetail>();
			singleDetail.setPlan_id(total.getPlan_id());
			singleDetail.setStart(0);
			singleDetail.setEnd(10000);

			List<SingleDetail> singleDetails = new ArrayList<SingleDetail>();

			singleDetails = accountDao.searchSingleDetailList(singleDetail);

			if (singleDetails != null && singleDetails.size() > 0) {
				for (SingleDetail singleDetail2 : singleDetails) { // ����������ϸ
					// �ۼ�Ԥ��
					// BigDecimal b1 = new BigDecimal(0);
					BNumberDetail bd = new BNumberDetail();
					// �ۼ�Ԥ��
					bd.setBnumber_id(singleDetail2.getBndetail_id()); // ���ñ������ID
					bd.setPlan_id(singleDetail2.getPlan_id()); // ��������ID
					bd.setDetail_id(singleDetail2.getDetail_id()); // ������ϸID
					bd.setOp_money1(singleDetail2.getInvoice_amount());// �������
					bd.setOperatorid(singleTotal.getUser_id());// ��������Ϣ
					bd.setOperatorname(singleTotal.getUser_name()); // ����������
					bd.setOp_orgid(String.valueOf(singleTotal.getOrg_id()));// ��֯ID
					bd.setOp_orgname(singleTotal.getOrg_name()); // ��֯����
					bd.setReason("ְ�ܷ�������");
					bd.setREASON_NUM(Long.valueOf(10));// ����ԭ��
					bd.setCHECK_FLAG("Y");
					bd.setBudget_number(singleDetail2.getBudget_number());// Ԥ���Ŀ���

					bNumberDetails.add(bd);
				}
				if (singleDetails != null && singleDetails.size() > 0) {
					accountDao.creatBudgetDetail(bNumberDetails, "3");// ���ýӿڱ���
				}
				booleanResult.setResult(true);
			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("ְ�ܷ������ݿⱣ���������ϵ����Ա��");
			logger.error(e);
		}
		return booleanResult;
	}

	 
	public StringResult modifyAuditExpenseForm(SingleTotal singleTotal) {
		StringResult stringResult = new StringResult();
		try {
			accountDao.modifyAuditExpenseForm(singleTotal);
			stringResult.setCode("success");
		} catch (Exception e) {
			stringResult.setCode("error");
			logger.error(e);
		}
		return stringResult;
	}

	 
	public ProEventTotal getEventTotalById(Long eventId) {
		try {
			return accountDao.getEventTotalById(eventId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}

		return null;
	}

	 
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId) {
		try {
			ProEventDetail proEventDetail = new ProEventDetail();
			proEventDetail.setEventId(eventId);
			List<ProEventDetail> temp = accountDao
					.getEventDetailList(proEventDetail);
			Map<Long, ProEventDetail> eventDetailMap = new LinkedHashMap<Long, ProEventDetail>();
			StringBuffer link = new StringBuffer();
			for (ProEventDetail detail : temp) {
				// ɾ��link
				link.delete(0, link.length());

				if (!"".equals(detail.getFilename())
						&& detail.getFilename() != null) {
					link.append("<a href=\"javascript:downLoad(");
					link.append(detail.getFileId());
					link.append(");\">");
					link.append(detail.getFilename());
					link.append("</a>&nbsp;");
				}
				ProEventDetail ped = eventDetailMap.get(detail.getEventDtlId());
				if (ped == null) {
					detail.setLink(link.toString());
					eventDetailMap.put(detail.getEventDtlId(), detail);
				} else {
					ped.setLink(link.append(ped.getLink()).toString());
				}
			}
			return new ArrayList<ProEventDetail>(eventDetailMap.values());
		} catch (Exception e) {
			logger.error("��ѯ����" + eventId + " ����", e);
		}
		return null;
	}

	 
	public List<Region> searchRegion(Region region) {
		try {
			return accountDao.searchRegion(region);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IAccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public int searchLoginTimes(String UserName) {
		try {
			return accountDao.searchLoginTimes(UserName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void addLoginTimes(String UserName) {
		try {
			accountDao.addLoginTimes(UserName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteLoginTimes(String UserName) {
		try {
			accountDao.deleteLoginTimes(UserName);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
