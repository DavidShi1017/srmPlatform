package com.jingtong.platform.account.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

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
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.JavaBeanXMLUtil;
import com.jingtong.platform.framework.util.XMLInfo;
import com.jingtong.platform.framework.util.XmlUtil;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.webservice.pojo.ProcessEventTotal;
import com.jingtong.platform.webservice.pojo.UserUtil;
import com.jingtong.platform.webservice.resps.JsonUtil;
import com.jingtong.platform.webservice.service.IWebService;
import com.jingtong.platform.wfe.pojo.OaXmlBean;
import com.jingtong.platform.wfe.pojo.ProEventTotal;
import com.jingtong.platform.wfe.service.IEventService;

public class AccountAction extends BaseAction {

	private static final long serialVersionUID = 6050882117631405933L;
	private static final Logger logger = Logger.getLogger(AccountAction.class);
	private String tmp;
	private IAccountService accountService;
	private IEventService eventService;
	private IOrgService orgService;
	private IWebService webService;

	private String userId;
	@Decode
	private String userName;
	private String orgId;
	private String orgName;
	private static String key = "fix_znfysq"; // ����ģ��
	@Decode
	private String title; // �������
	private String eventId; // ����ID
	private String status; // ����״̬
	private Date startDate;
	private Date endDate;

	private String costCenterText;
	private String costCenter;
	private String payType;
	private String playMoneyFlag;

	private Date cost_date;
	private String cost_purpose;

	private String toDoDetail;
	private String curStaId;

	/**
	 * ������Ϣ
	 */
	private File[] upload;
	private String[] uploadFileName;

	private SingleTotal singleTotal;// �����ܵ�
	private String detailJsonStr; // ��ϸJson�ַ���

	private String modelKey; // ����Ťת����
	private String modelValues; // ����Ťת����ֵ

	private UserUtil userUtil; // �¸��������б�
	private String nextUserId; // ��һ������

	private String appUrl;
	private String xmlFilePath; // Xml�ļ�·��
	private String subFolders;

	private int total = 0;
	private List<SingleTotal> singleTotalList;
	private List<SingleDetail> singleDetailList;
	private List<WorkPlanTotal> planList;
	// private List<CmsTbDict> costTypeList;
	private List<PayeeInfo> payeeInfoList;
	private List<ProcessEventTotal> proEventTotalList;

	private String modifyFlag;
	private String planId;
	private String transactionId;
	private String projectId;
	private PayeeInfo payeeInfo;
	@Decode
	private String payee;
	private String payAccount;
	private String payArea;
	private String payBank;
	private String payAreaCode;
	private String payBankAlias;
	private String payBankAliCode;
	private String payBankCode;
	private String accountAlias;
	private String email;
	private String id;
	private String ids;
	private String operateFlag;
	private String searchStr;
	private String transaction_id;
	private String financial_doc_num;
	private File uploadFile;
	private String searchStrFlag;
	private String type;
	private String pro_manager_id;

	@Decode
	private String project;
	@Decode
	private String costTypeContent;
	private String download; // �Ƿ�������ɱ�ʶ
	private List<CostType> costTypeList;
	private String cost_parent_id;

	private List<BudgetNumber> budgetList;
	private String theYear;
	private String theMonth;
	private String budget_number;
	private String expType;
	private String audit_money;
	private BooleanResult executeResult;// ���������������Ϣ
	private String auditMoney;
	private String flag;
	private List<ProEventTotal> eventTotals;
	private List<OaXmlBean> oaXmlBeanList;
	private Date businessStartDate; // ҵ��ʼʱ��
	private Date businessEndDate; // ҵ�����ʱ��
	private int level;
	private String pid;// �ϼ�ID
	private List<Region> regionList;// �������б�
	private String text;
	private ExpensesTotal expensesTotal; // �ճ������ܵ�
	private List<ExpensesDetail> expensesDetailList; // �ճ�������ϸ�б�
	private List<ExpensesTotal> expensesTotalList; // �ճ������ܵ��б�

	/**
	 * ��ת֧����쵥
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createSinglePrepare() {
		// �ύ��
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());

		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		return CREATE_PREPARE + "_Single";
	}

	/**
	 * ��ת���÷ѱ���
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createTraReimburPrepare() {
		// �ύ��
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		total = accountService.getPayeeInfoCountByName(userName);
		if (total > 0) {
			payeeInfo = accountService.getDefaultPayee(userName);
		}
		return CREATE_PREPARE + "_Tra";
	}

	/**
	 * ��ӡ������
	 * 
	 * @return
	 */
	@PermissionSearch
	public String printTraReimbur() {
		singleTotal = accountService.searchSingelTotalByPlanId(
				Long.parseLong(planId.trim()), transaction_id);
		return "printTraReimbur";
	}

	@PermissionSearch
	public String searchTraReimburPrepare() {
		// �жϵ�ǰ�û��Ƿ�Ϊ���ɽ�ɫ
		if (accountService.getUserRoles(this.getUser(), "cashier")) {
			modifyFlag = "Y";
		} else {
			modifyFlag = "N";
		}
		return "searchTraReimburPrepare";
	}

	/**
	 * ��ѯ������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleTotalList", include = { "plan_id",
			"transaction_id", "title", "pay_ee", "cost_org_name",
			"cost_center_name", "total_money", "audit_money", "pay_type",
			"create_date", "has_play_money", "status", "financial_doc_num",
			"write_eventId", "write_status" }, total = "total")
	public String searchTraReimbur() {
		SingleTotal singleTotal = new SingleTotal();
		singleTotal.setStart(getStart());
		singleTotal.setEnd(getEnd());
		// �жϵ�ǰ�û��Ƿ�Ϊ����������ɫ��VP,�������ֻ�ܲ鿴�Լ��ı�����������ǿ��Բ鿴ȫ��������
		/*
		 * if (!(accountService.getUserRoles(this.getUser(), "financial") ||
		 * accountService .getUserRoles(this.getUser(), "f_manager"))) {
		 * singleTotal.setUser_id(this.getUser().getUserId()); }
		 */
		singleTotal.setUser_id(this.getUser().getUserId());
		try {
			if (StringUtils.isNotEmpty(eventId)
					&& StringUtils.isNotEmpty(eventId.trim())) {
				singleTotal.setEventId(eventId.trim());
			}
			if (StringUtils.isNotEmpty(title)
					&& StringUtils.isNotEmpty(title.trim())) {
				singleTotal.setTitle(title.trim());
			}
			if (StringUtils.isNotEmpty(status)
					&& StringUtils.isNotEmpty(status.trim())) {
				singleTotal.setStatus(status.trim());
			}
			if (StringUtils.isNotEmpty(payee)
					&& StringUtils.isNotEmpty(payee.trim())) {
				singleTotal.setPay_ee(payee.trim());
			}
			if (startDate != null) {
				singleTotal.setStartDate(startDate);
			}
			if (endDate != null) {
				singleTotal.setEndDate(endDate);
			}
			if (StringUtils.isNotEmpty(playMoneyFlag)
					&& StringUtils.isNotEmpty(playMoneyFlag.trim())) {
				singleTotal.setHas_play_money(playMoneyFlag.trim());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		total = accountService.searchTraReimburCount(singleTotal);
		if (total != 0) {
			singleTotalList = accountService.searchTraReimburList(singleTotal);
		}
		return JSON;
	}

	public String searchSingleDetailPrepare() {
		return "searchSingleDetailPrepare";
	}

	/**
	 * ��ѯ��������ϸ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "detail_id",
			"cost_type_content", "cost_date", "cost_purpose", "invoice_num",
			"invoice_amount", "cost_memo", "financial_doc_num", "org_name",
			"budget_number", "audit_money", "businessStartDate",
			"businessEndDate" }, total = "total")
	public String searchSingleDetail() {
		SingleDetail singleDetail = new SingleDetail();
		singleDetail.setPlan_id(Long.parseLong(planId.trim()));
		singleDetail.setStart(getStart());
		singleDetail.setEnd(getEnd());
		try {
			if (cost_date != null) {
				singleDetail.setCost_date(cost_date);
			}
			if (StringUtils.isNotEmpty(cost_purpose)
					&& StringUtils.isNotEmpty(cost_purpose.trim())) {
				cost_purpose = new String(getServletRequest().getParameter(
						"cost_purpose").getBytes("ISO8859-1"), "UTF-8");
				singleDetail.setCost_purpose(cost_purpose.trim());
			}
		} catch (Exception e) {
			logger.error("cost_date:" + cost_date + "cost_purpose:"
					+ cost_purpose, e);
		}
		total = accountService.searchSingleDetailCount(singleDetail);
		if (total != 0) {
			singleDetailList = accountService
					.searchSingleDetailList(singleDetail);
		}
		return JSON;
	}

	/**
	 * ���
	 * 
	 * @return
	 */
	public String playMoney() {
		StringResult result = accountService.playMoney(planId);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(result.getResult());
		} else {
			this.setFailMessage(result.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ������Excel(��ѯ��ȫ�����,���ڲ���,��Ҫ�鿴ȫ��������)
	 * 
	 * @return
	 */
	public String downLoadExcel() {
		SingleTotal singleTotal = new SingleTotal();
		try {
			if (StringUtils.isNotEmpty(eventId)
					&& StringUtils.isNotEmpty(eventId.trim())) {
				singleTotal.setEventId(eventId.trim());
			}
			if (StringUtils.isNotEmpty(title)
					&& StringUtils.isNotEmpty(title.trim())) {
				singleTotal.setTitle(title.trim());
			}
			if (StringUtils.isNotEmpty(status)
					&& StringUtils.isNotEmpty(status.trim())) {
				singleTotal.setStatus(status.trim());
			}
			if (StringUtils.isNotEmpty(userName)
					&& StringUtils.isNotEmpty(userName.trim())) {
				singleTotal.setUser_name(userName.trim());
			}
			if (startDate != null) {
				singleTotal.setStartDate(startDate);
			}
			if (endDate != null) {
				singleTotal.setEndDate(endDate);
			}
			if (StringUtils.isNotEmpty(playMoneyFlag)
					&& StringUtils.isNotEmpty(playMoneyFlag.trim())) {
				singleTotal.setHas_play_money(playMoneyFlag.trim());
			}
			File source = accountService.exportTraReimbur(singleTotal);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
			if (source != null) {
				display(source, "������_" + df.format(new Date()) + ".xls",
						ServletActionContext.getResponse());
				source.delete();
			} else {
				this.setFailMessage("Excel��������������");
			}
		} catch (Exception e) {
			this.setFailMessage("Excel��������������");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ����ѡ���¸�������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result" })
	public String selectNexUser() {
		String role = "";
		/*
		 * if (accountService.getUserRoles(this.getUser(),
		 * "Department_Manager")) { role = "Department_Manager"; } else if
		 * (accountService.getUserRoles(this.getUser(),
		 * "Department_deputy_Chief")) { role = "Department_deputy_Chief"; }
		 * else if (accountService.getUserRoles(this.getUser(),
		 * "Department_Chief")) { role = "Department_Chief"; } else if
		 * (accountService.getUserRoles(this.getUser(), "sqjl")) { role =
		 * "sqjl"; } else if (accountService.getUserRoles(this.getUser(),
		 * "dqjl")) { role = "dqjl"; } else if
		 * (accountService.getUserRoles(this.getUser(), "x_s_z_j")) { role =
		 * "x_s_z_j"; } else { role = "yuan_gong"; }
		 */
		Double totalMoney = 0D;
		if (StringUtils.isNotEmpty(auditMoney)) {
			totalMoney = new Double(auditMoney);
		}

		Object[] res = new Object[4];
		res[0] = key;
		res[1] = this.getUser().getUserId();
		res[2] = "role,totalMoney";
		res[3] = role + "," + totalMoney;// �������һ��ͬ��ʱҵ�����
		userUtil = webService.startWorkflowFix(res);
		return JSON;
	}

	// ����ͨ����������
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String activateAccountPlan() {
		BooleanResult result = null;
		SingleTotal singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(eventId)) {
			try {
				singleTotal.setWrite_eventId(eventId);
				result = accountService.activateAccountPlan(singleTotal);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(result.getCode() + "\n" + "���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա");
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	// ����ܾ���ʱ�����
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String refuseAccountPlan() {
		BooleanResult result = null;
		SingleTotal singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(eventId)) {
			try {
				singleTotal.setTransaction_id(eventId);
				result = accountService.refuseAccountPlan(singleTotal);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(result.getCode() + "\n" + "���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա");
			}
		}
		setExecuteResult(result);
		return JSON;
	}

	/**
	 * ȡ���¸�������
	 * 
	 * @return
	 */
	public String cancelNextUser() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		try {
			webService.cancelEvent(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		this.setSuccessMessage("ok");
		return RESULT_MESSAGE;
	}

	/**
	 * ��ȡ�¸������˲�����
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String processWorkflowFix() {
		this.setSuccessMessage("");
		this.setFailMessage("");

		Object[] res = new Object[7];
		res[0] = eventId;
		res[1] = this.getUser().getUserId();
		res[2] = nextUserId;
		res[3] = title;
		if ("2".equals(flag)) {// �ճ����������鿴ҳ��
			res[4] = appUrl + "/account/accountAction!searchExpenseRcForm.jspa";
		} else {
			res[4] = appUrl + "/account/accountAction!searchExpenseForm.jspa";
		}
		res[5] = key;
		res[6] = "";
		String result = webService.processWorkflowFix(res);
		if ("success".equals(result)) {
			// ���渽��
			if (upload != null && upload.length > 0) {
				eventService.processAttachments(upload, uploadFileName,
						Long.valueOf(eventId),
						String.valueOf(new Date().getTime()), key);
			}

			StringResult strResult = new StringResult();

			if ("2".equals(flag)) {// �ճ������걨
				List<ExpensesDetail> detailList = JsonUtil.getDTOList(
						detailJsonStr, ExpensesDetail.class);
				expensesTotal.setDetailList(detailList); // ��ϸ�б�
				expensesTotal.setTransaction_id(eventId);
				expensesTotal.setUser_id(this.getUser().getUserId());
				expensesTotal.setUser_name(this.getUser().getUserName());
				expensesTotal.setCreator_id(Long.parseLong(this.getUser()
						.getUserId()));
				expensesTotal.setOperator_id(Long.parseLong(this.getUser()
						.getUserId()));
				strResult = accountService.createExpenses(expensesTotal); // ���浽�ճ����ñ�

			} else { // ְ�ܷ����걨
				List<SingleDetail> detailList = JsonUtil.getDTOList(
						detailJsonStr, SingleDetail.class);
				// ְ�ܷ����ᱨ
				singleTotal.setDetailList(detailList);// ��ϸ�б�
				singleTotal.setTransaction_id(eventId);
				singleTotal.setUser_id(this.getUser().getUserId());
				singleTotal.setUser_name(this.getUser().getUserName());
				singleTotal.setCreator_id(Long.parseLong(this.getUser()
						.getUserId()));
				singleTotal.setOperator_id(Long.parseLong(this.getUser()
						.getUserId()));
				strResult = accountService.createSingle(singleTotal); // ���汨��������
			}
			if (IAccountService.SUCCESS.equals(strResult.getCode())) {
				this.setSuccessMessage("���������ɹ�,�����Ϊ��" + eventId);
			} else {
				webService.cancelEvent(eventId);
				this.setFailMessage(strResult.getResult());
				return RESULT_MESSAGE;
			}
			if ("2".equals(flag)) {
				// �ճ�����д��XML
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", expensesTotal, getUser().getUserId(),
						getUser().getUserName(), null)) {
					webService.cancelEvent(eventId);
					this.setFailMessage("д��XML����");
					return RESULT_MESSAGE;
				}
			} else {
				// ְ�ܷ���дXML�ļ�
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId
						+ ".xml", singleTotal, getUser().getUserId(), getUser()
						.getUserName(), null)) {
					webService.cancelEvent(eventId);
					this.setFailMessage("д��XML����");
					return RESULT_MESSAGE;
				}
			}

		} else {
			this.setFailMessage("��������ʧ��!");
		}
		return RESULT_MESSAGE;
	}

	/***
	 * ��ת����̨���봦��ҳ��
	 * 
	 * @return
	 */
	public String toSaveSingleTotal() {

		return "toSaveSingleTotal";
	}

	/***
	 * ��ת����̨���봦��ҳ��
	 * 
	 * @return
	 */
	public String saveSingleTotal() {
		singleTotal = new SingleTotal();
		StringResult strResult = new StringResult();

		if (StringUtils.isNotEmpty(eventId)) {
			ProEventTotal eventTotal = eventService.getEventTotalById(Long
					.valueOf(eventId));
			String pathFile = xmlFilePath + File.separator
					+ eventTotal.getSubFolders() + File.separator + eventId
					+ ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, singleTotal);
			if (info != null) {
				singleTotal = (SingleTotal) info.getObject();
			}
			singleTotal.setTransaction_id(eventId);

			strResult = accountService.createSingle(singleTotal); // ���汨��������
		}
		if ("success".equals(strResult.getCode())) {
			this.setSuccessMessage("�����ɹ���");
		} else {
			this.setFailMessage(strResult.getResult());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �������������в鿴����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchExpenseForm() {
		singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, singleTotal);
			if (info != null) {
				singleTotal = (SingleTotal) info.getObject();
			}
		}
		return "searchExpenseForm";
	}

	/**
	 * �ճ��������������в鿴����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchExpenseRcForm() {
		expensesTotal = new ExpensesTotal();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil
					.XML2JavaBean(pathFile, expensesTotal);
			if (info != null) {
				expensesTotal = (ExpensesTotal) info.getObject();
			}
		}
		return "searchExpenseRcForm";
	}

	/**
	 * �鿴��ϸ����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchExpenseDetailForm() {
		singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(planId)) {
			singleTotal = accountService.searchSingelTotalByPlanId(
					Long.parseLong(planId.trim()), transaction_id);
		}
		return "searchExpenseForm";
	}

	/**
	 * �������������в鿴����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchAuditExpenseForm() {
		singleTotal = new SingleTotal();
		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders
					+ File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, singleTotal);
			if (info != null) {
				singleTotal = (SingleTotal) info.getObject();
			}
		}
		return "searchAuditExpenseForm";
	}

	/**
	 * ��ѯ��Ŀ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "planList", include = { "totalId", "title" }, total = "total")
	public String searchWorkPlan() {
		planList = new ArrayList<WorkPlanTotal>();
		WorkPlanTotal plan = new WorkPlanTotal();
		if ("process".equals(type)) {// ��������ʱ�����ᱨ�˲���Ŀ
			plan.setPro_manager_id(Long.parseLong(pro_manager_id));
		} else {
			plan.setPro_manager_id(Long.parseLong(getUser().getUserId()));
		}
		if (StringUtils.isNotEmpty(title)
				&& StringUtils.isNotEmpty(title.trim())) {
			try {
				title = new String(getServletRequest().getParameter("title")
						.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			plan.setTitle(title);
		}
		plan.setStart(getStart());
		plan.setEnd(getEnd());
		total = accountService.searchWorkPlanCount(plan);
		if (total != 0) {
			planList = accountService.searchWorkPlan(plan);
		}
		return JSON;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateExpenseForm() {
		List<SingleDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				SingleDetail.class);
		singleTotal.setDetailList(detailList);
		singleTotal.setOperator_id(Long.parseLong(this.getUser().getUserId()));
		String xmlFile = eventService.getEventTotalById(
				Long.valueOf(singleTotal.getTransaction_id())).getSubFolders();
		// ����XML�ļ�
		if (!JavaBeanXMLUtil.JavaBean2XML(
				xmlFilePath + "/" + singleTotal.getTransaction_id() + ".xml",
				singleTotal, getUser().getUserId(), getUser().getUserName(),
				xmlFile)) {
			this.setFailMessage("����XML����!");
			return RESULT_MESSAGE;
		}
		StringResult stringResult = accountService.updateSingle(singleTotal);
		if (IAccountService.SUCCESS.equals(stringResult.getCode())) {
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �ļ�����
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {
		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}

	/**
	 * �տ�����Ϣά��ҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchPayeeInfo() {
		return "toSearchPayeeInfo";
	}

	/**
	 * �տ�����Ϣ�б��ѯ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "payeeInfoList", include = { "id", "payee",
			"payAccount", "payArea", "payBank", "payAreaCode", "payBankAlias",
			"payBankAliCode", "payBankCode", "accountAlias", "email" }, total = "total")
	public String getPayeeInfoJsonList() {
		payeeInfoList = new ArrayList<PayeeInfo>();
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(payee) && StringUtils.isNotEmpty(payee))
			payeeInfo.setPayee(payee.trim());
		if (StringUtils.isNotEmpty(payAccount)
				&& StringUtils.isNotEmpty(payAccount))
			payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setStart(getStart());
		payeeInfo.setEnd(getEnd());
		// System.out.println(this.getUser().getUserId());
		payeeInfo.setCreator(this.getUser().getUserId());
		total = accountService.getPayeeInfoCount(payeeInfo);
		if (total != 0) {
			payeeInfoList = accountService.getPayeeInfoList(payeeInfo);
		}
		return JSON;
	}

	/**
	 * �����տ���ҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toAddPayeeInfo() {
		return "payeeInfo";
	}

	/**
	 * �����տ���
	 * 
	 * @return
	 */
	public String addPayeeInfo() {
		payeeInfo = new PayeeInfo();
		payeeInfo.setPayee(payee.trim());
		payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setPayArea(payArea.trim());
		payeeInfo.setPayBank(payBank.trim());
		// payeeInfo.setPayAreaCode(payAreaCode.trim());
		// payeeInfo.setPayBankAlias(payBankAlias.trim());
		// payeeInfo.setPayBankAliCode(payBankAliCode.trim());
		// payeeInfo.setPayBankCode(payBankCode.trim());
		payeeInfo.setEmail(email.trim());
		payeeInfo.setCreator(getUser().getUserId());
		StringResult result = accountService.addPayeeInfo(payeeInfo);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}
		;
		return RESULT_MESSAGE;
	}

	/**
	 * �տ���ɾ��
	 * 
	 * @return
	 */
	public String removePayeeInfo() {
		StringResult result = accountService.removePayeeInfo(ids, getUser()
				.getUserId());
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �տ�����Ϣ�޸�ҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toModifyPayeeInfo() {
		payeeInfo = accountService.getPayeeInfoById(Long.valueOf(id));
		return "payeeInfo";
	}

	/**
	 * �տ�����Ϣ�޸�
	 * 
	 * @return
	 */
	public String modifyPayeeInfo() {
		payeeInfo = new PayeeInfo();
		payeeInfo.setId(Long.valueOf(id));
		payeeInfo.setPayee(payee.trim());
		payeeInfo.setPayAccount(payAccount.trim());
		payeeInfo.setPayArea(payArea.trim());
		payeeInfo.setPayBank(payBank.trim());
		// payeeInfo.setPayAreaCode(payAreaCode.trim());
		// payeeInfo.setPayBankAlias(payBankAlias.trim());
		// payeeInfo.setPayBankAliCode(payBankAliCode.trim());
		// payeeInfo.setPayBankCode(payBankCode.trim());
		payeeInfo.setEmail(email.trim());
		payeeInfo.setModifier(getUser().getUserId());
		StringResult result = accountService.modifyPayeeInfo(payeeInfo);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}
		;
		return RESULT_MESSAGE;
	}

	/**
	 * �����տ�����Ϣ
	 * 
	 * @return
	 */
	@JsonResult(field = "payeeInfoList", include = { "payee" }, total = "total")
	@PermissionSearch
	public String getPayeeJsonList() {
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(searchStr)
				&& StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter(
						"searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			payeeInfo.setSearchStr(searchStr);
		}
		payeeInfo.setStart(getStart());
		payeeInfo.setEnd(getEnd());
		payeeInfo.setCreator(this.getUser().getUserId());
		total = accountService.getPayeeCount(payeeInfo);
		if (total != 0) {
			payeeInfoList = accountService.getPayeeList(payeeInfo);
		}
		return JSON;
	}

	/**
	 * �����˺���Ϣ
	 * 
	 * @return
	 */
	@JsonResult(field = "payeeInfoList", include = { "id", "payAccount" })
	@PermissionSearch
	public String getPayAccountJsonList() {
		payeeInfo = new PayeeInfo();
		if (StringUtils.isNotEmpty(payee)
				&& StringUtils.isNotEmpty(payee.trim())) {
			try {
				payee = new String(getServletRequest().getParameter("payee")
						.getBytes("ISO8859-1"), "UTF-8");
				payeeInfo.setCreator(this.getUser().getUserId());
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			payeeInfo.setPayee(payee);
		}
		payeeInfoList = accountService.getPayAccountList(payeeInfo);
		return JSON;
	}

	/**
	 * ��ѯĬ�������˺�
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "id")
	public String getDefaultPayAccount() {
		try {
			payee = new String(getServletRequest().getParameter("payee")
					.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		id = accountService.getDefaultPayee(payee).getId().toString();
		return JSON;
	}

	/**
	 * �����տ��˺�ҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toAddNewAccount() {
		try {
			payee = new String(getServletRequest().getParameter("payee")
					.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return "toAddNewAccount";
	}

	/**
	 * ��ʷ���������ѯ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "proEventTotalList", include = { "eventId",
			"eventTitle", "creatdate" }, total = "total")
	public String getHisEventJsonList() {
		ProcessEventTotal eventTotal = new ProcessEventTotal();
		if (StringUtils.isNotEmpty(searchStr)
				&& StringUtils.isNotEmpty(searchStr.trim())) {
			try {
				searchStr = new String(getServletRequest().getParameter(
						"searchStr").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			eventTotal.setEventTitle(searchStr);
		}
		eventTotal.setStart(getStart());
		eventTotal.setEnd(getEnd());
		eventTotal.setInitator(getUser().getUserId());
		total = accountService.getHisEventCount(eventTotal);
		if (total != 0) {
			proEventTotalList = accountService.getHisEventList(eventTotal);
		}
		return JSON;
	}

	/**
	 * ��ѯ��������ϸ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "detail_id",
			"cost_type", "cost_type_content", "cost_date", "cost_purpose",
			"invoice_num", "invoice_amount", "cost_memo" })
	public String getSingleDetailJsonList() {
		singleDetailList = accountService.getSingleDetailList(transactionId);
		return JSON;
	}

	/**
	 * ���²���ƾ֤��
	 * 
	 * @return
	 */
	public String updateFinancialDocNum() {
		System.out.println(transaction_id);
		System.out.println(financial_doc_num);
		StringResult result = accountService.updateFinancialDocNum(
				transaction_id, financial_doc_num);
		if (IAccountService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ���ƾ֤�ŵ���ҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toImportFinancialDocNum() {
		return "toImportFinancialDocNum";
	}

	/**
	 * ����ƾ֤�ŵ���
	 * 
	 * @return
	 */
	public String importData() {

		FileInputStream fileIn = null;
		Workbook rwb = null;
		StringBuilder titleResult = new StringBuilder();
		StringBuilder contentResult = new StringBuilder();

		try {
			fileIn = new FileInputStream(uploadFile);
			rwb = Workbook.getWorkbook(fileIn);
			Sheet rs = rwb.getSheet(0);
			int row = rs.getRows();
			int column = rs.getColumns();

			if (row == 0 && column == 0) {
				this.setFailMessage("�����ExcelΪ�գ�");
				return RESULT_MESSAGE;
			} else {
				if (!"����ID".equals(rs.getCell(0, 0).getContents().trim())) {
					titleResult.append("ģ���е�1��1���ֶ�������--����ID��   ");
				}
				if (!"���ƾ֤��".equals(rs.getCell(1, 0).getContents().trim())) {
					titleResult.append("ģ���е�1��2���ֶ�������--���ƾ֤�ţ�   ");
				}
				if (!"".equals(titleResult.toString())) {
					this.setFailMessage(titleResult.toString());
					return RESULT_MESSAGE;
				} else {
					List<SingleTotal> list = new ArrayList<SingleTotal>();
					for (int i = 1; i < row; i++) {
						singleTotal = new SingleTotal();
						if ("".equals(rs.getCell(0, i).getContents().trim())
								&& "".equals(rs.getCell(1, i).getContents()
										.trim())) {// ��ǰ��Ϊ��
							continue;
						} else {
							if ("".equals(rs.getCell(0, i).getContents().trim())) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("��" + (i + 1)
										+ "������ID����Ϊ��!");
							} else if (accountService.countSingleTotal(rs
									.getCell(0, i).getContents().trim()) == 0) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("��" + (i + 1)
										+ "������ID������!");
							}
							if (!rs.getCell(1, i).getContents().trim()
									.matches("^\\d{10}$")) {
								if (contentResult.length() > 0) {
									contentResult.append("</br>");
								}
								contentResult.append("��" + (i + 1)
										+ "�л��ƾ֤�ű���Ϊ10λ����������!");
							}

							singleTotal.setTransaction_id((rs.getCell(0, i)
									.getContents().trim()));
							singleTotal.setFinancial_doc_num(rs.getCell(1, i)
									.getContents().trim());

							list.add(singleTotal);
						}

					}

					if (contentResult.length() > 0) {
						this.setFailMessage(contentResult.toString());
						return RESULT_MESSAGE;
					}

					StringResult result = accountService
							.batchUpdateFinancialDocNum(list);

					if (IAccountService.SUCCESS.equals(result.getCode())) {
						this.setSuccessMessage("�����ɹ�!");
					} else {
						this.setFailMessage("����ʧ��!");
					}

				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error(e);
			this.setFailMessage("Excelģ�����");
			return RESULT_MESSAGE;
		} catch (BiffException e) {
			logger.error(e);
			this.setFailMessage("03���ϰ汾Excel�����ݲ�֧��");
			return RESULT_MESSAGE;
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("����ʧ�ܣ�");
			return RESULT_MESSAGE;
		}
		return RESULT_MESSAGE;

	}

	/**
	 * ������ϸ��ѯҳ����ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchReimburDetail() {
		return "toSearchReimburDetail";
	}

	/**
	 * ��������ϸ�б��ѯ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "singleDetailList", include = { "transaction_id",
			"payee", "project", "project_manager", "cost_type_content",
			"cost_date", "cost_purpose", "invoice_num", "invoice_amount",
			"audit_money", "cost_memo", "financial_operate_date" }, total = "total")
	public String getReimburDetailJsonList() {
		SingleDetail singleDetail = new SingleDetail();
		if (StringUtils.isNotEmpty(transactionId)
				&& StringUtils.isNotEmpty(transactionId.trim()))
			singleDetail.setTransaction_id(Long.parseLong(transactionId));
		if (StringUtils.isNotEmpty(payee)
				&& StringUtils.isNotEmpty(payee.trim()))
			singleDetail.setPayee(payee);
		if (StringUtils.isNotEmpty(project)
				&& StringUtils.isNotEmpty(project.trim()))
			singleDetail.setProject(project);
		if (StringUtils.isNotEmpty(costTypeContent)
				&& StringUtils.isNotEmpty(costTypeContent.trim()))
			singleDetail.setCost_type_content(costTypeContent);
		if (StringUtils.isNotEmpty(status)
				&& StringUtils.isNotEmpty(status.trim()))
			singleDetail.setStatus(status);
		if (startDate != null)
			singleDetail.setStart_date(startDate);
		if (endDate != null)
			singleDetail.setEnd_date(endDate);
		singleDetail.setStart(getStart());
		singleDetail.setEnd(getEnd());
		total = accountService.getReimburDetailCount(singleDetail);
		if (total != 0) {
			singleDetailList = accountService
					.getReimburDetailList(singleDetail);
		}
		return JSON;
	}

	/**
	 * excel����������ϸ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportReimberDetailList() {
		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		SingleDetail singleDetail = new SingleDetail();
		if (StringUtils.isNotEmpty(transactionId)
				&& StringUtils.isNotEmpty(transactionId.trim()))
			singleDetail.setTransaction_id(Long.parseLong(transactionId));
		if (StringUtils.isNotEmpty(payee)
				&& StringUtils.isNotEmpty(payee.trim()))
			singleDetail.setPayee(payee);
		if (StringUtils.isNotEmpty(project)
				&& StringUtils.isNotEmpty(project.trim()))
			singleDetail.setProject(project);
		if (StringUtils.isNotEmpty(costTypeContent)
				&& StringUtils.isNotEmpty(costTypeContent.trim()))
			singleDetail.setCost_type_content(costTypeContent);
		if (StringUtils.isNotEmpty(status)
				&& StringUtils.isNotEmpty(status.trim()))
			singleDetail.setStatus(status);
		if (startDate != null)
			singleDetail.setStart_date(startDate);
		if (endDate != null)
			singleDetail.setEnd_date(endDate);
		try {
			File source = accountService.exportReimberDetailList(singleDetail);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
			if (source != null) {
				display(source, "����������Ŀ��ϸ_" + df.format(new Date()) + ".xls",
						ServletActionContext.getResponse());
				source.delete();
				ServletActionContext.getRequest().getSession()
						.setAttribute("DownLoad", "Over");
			} else {
				this.setFailMessage("Excel���ݵ�������");
			}
		} catch (Exception e) {
			this.setFailMessage("Excel���ݵ�������");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * У�������Ƿ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "download")
	public String checkDownLoadOver() {
		Object obj = ServletActionContext.getRequest().getSession()
				.getAttribute("DownLoad");
		if (obj == null || "Ing".equals(obj)) {
			download = "No";
		} else {
			download = "Yes";
		}
		return JSON;
	}

	/***
	 * ѡ���������
	 * 
	 * @return
	 */
	public String selectCostType() {
		return "selectCostType";
	}

	/**
	 * ��ʼ����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "costTypeList", include = { "id", "text", "state" })
	public String getCostTypeTreeListByAjax() {
		costTypeList = new ArrayList<CostType>();
		CostType costType = new CostType();
		/*
		 * if(StringUtils.isNotEmpty(type)){ costType.setType(type); }
		 */
		costType.setType("02");// ����ְ�ܷ��ú��г�����
		costType.setPARENT_ID(Long.valueOf(cost_parent_id));

		List<CostType> cList = null;
		try {
			cList = accountService.getCostTypeListByCostTypeParentId(costType);
		} catch (Exception e) {
			logger.error(cost_parent_id, e);
		}
		if (cList == null || cList.size() == 0) {
			return JSON;
		}
		for (CostType c : cList) {
			CostType cType = new CostType();
			cType.setId(String.valueOf(c.getCOST_TYPE_ID()));
			cType.setText(c.getCOST_TYPE_NAME());
			cType.setState("closed");
			costTypeList.add(cType);
		}
		return JSON;
	}

	/***
	 * ��ת����ȡ���ñ��ҳ��
	 * 
	 * @return
	 */
	public String bNumberSearchPrepare() {
		return "bNumberSearchPrepare";
	}

	/***
	 * ��ȡ���ñ���б�
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "budgetList", include = { "bnumber_id", "subjectId",
			"subjectName", "budget_number", "budget_money", "init_money",
			"budget_type", "channelName", "costCentId", "costCenterName",
			"creatdate", "theYear", "theMonth", "tra_flag" }, total = "total")
	public String bNumberSearch() {
		budgetList = new ArrayList<BudgetNumber>();
		BudgetNumber budgetNumber = new BudgetNumber();
		budgetNumber.setStart(this.getStart());
		budgetNumber.setEnd(this.getEnd());
		// �ճ�ְ�ܷ���
		budgetNumber.setBudget_type("02");

		if (StringUtils.isNotEmpty(orgId)) {
			budgetNumber.setCostCentId(orgId);
		}

		if (StringUtils.isNotEmpty(expType)) { //
			budgetNumber.setSubjectId(expType);
		}
		if (StringUtils.isNotEmpty(budget_number)) { // ���ñ��
			budgetNumber.setBudget_number(budget_number);
		}

		// ֻ���Լ����ܿ���
		total = accountService.getBudgetNumberTotal(budgetNumber);
		if (total != 0) {
			budgetList = accountService.getBudgetNumberList(budgetNumber);
		}
		return JSON;
	}

	/**
	 * ��ת�����ú���ҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createWriteExpenseFormPrepare() {
		singleTotal = new SingleTotal();
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();

		singleTotal = accountService.searchSingelTotalByPlanId(
				Long.parseLong(planId.trim()), transaction_id);
		tmp = "";
		for (SingleDetail singleDetail : singleTotal.getDetailList()) {
			tmp += singleDetail.getCost_type() + ",";
		}
		return "createWriteExpenseFormPrepare";
	}

	/**
	 * ��ѯ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "eventTotals", include = { "eventId", "eventTitle",
			"money" }, total = "total")
	public String getTravleJsonList() {
		eventTotals = new ArrayList<ProEventTotal>();
		/** ���ݲ�ͬ��λ�ò�ͬ��ģ�� add by kidd.zeng **/
		String roleId = null;
		/** �����û�id���Ҹ��û��ĸ�λ:������Ӧ��province_luxi�� **/
		roleId = eventService.getStationIdByUserId(this.getUser().getUserId());
		String roleIdHead = roleId.split("_")[0];
		/** ���´����� **/
		if ("office".equals(roleIdHead)) {
			key = "fix_travel_bscjl";
		} else if ("province".equals(roleIdHead)) {
			/** ʡ������ **/
			key = "fix_travel_sqjl";
		} else if ("manage".equals(roleIdHead) || "da".equals(roleIdHead)) {
			/** ���������KA **/
			key = "fix_travel_dqjl";
		} else if ("zong".equals(roleIdHead) || "ka".equals(roleIdHead)) {
			/** �����ܼ�,�ܼ༶�� **/
			key = "fix_travel_xszj";
		} else {
			String roleIdLast = roleId.substring(roleId.length() - 4,
					roleId.length());
			if ("jian".equals(roleIdLast)) {
				key = "fix_travel_xszj";
			} else {
				/** ����ְ�ܲ��� **/
				key = "fix_travel_znbm_new";
			}
		}
		ProEventTotal proEventTotal = new ProEventTotal();
		proEventTotal.setModelId(key);
		proEventTotal.setInitator(this.getUser().getUserId());
		eventTotals = eventService.getTripApplyList(proEventTotal);
		if (eventTotals != null && eventTotals.size() > 0) {
			for (ProEventTotal p : eventTotals) {
				String pathFile = xmlFilePath + File.separator
						+ p.getSubFolders() + File.separator + p.getEventId()
						+ ".xml";
				OaXmlBean oaXmlBean = new OaXmlBean();
				oaXmlBean.setParameter("new");
				oaXmlBean.setParameter1("id");
				oaXmlBean.setParameter2("name");
				oaXmlBean.setParameter3("text");
				oaXmlBeanList = XmlUtil.readXml(pathFile, oaXmlBean, null);
				if (oaXmlBeanList != null && oaXmlBeanList.size() > 0) {
					for (OaXmlBean bean : oaXmlBeanList) {
						if ("Ԥ�ƻ��ѽ��".equals(bean.getParameter2())) {
							p.setMoney(new BigDecimal(bean.getParameter3()));
							break;
						}
					}
				}

			}

		}
		return JSON;
	}

	/**
	 * �����������޸ĸ��ֺͳ�Ƿ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyAuditExpenseForm() {
		StringResult result = new StringResult();
		List<SingleDetail> detailList = JsonUtil.getDTOList(detailJsonStr,
				SingleDetail.class);
		result = accountService.modifyAuditExpenseForm(singleTotal);
		if ("success".equals(result.getCode())) {
			// ��ʼ��
			singleTotal = accountService.searchSingelTotalByPlanId(
					singleTotal.getPlan_id(), null);
			singleTotal.setAudit_money(new BigDecimal(audit_money)); // ��¼�������
			for (SingleDetail singleDetail : singleTotal.getDetailList()) {// ��ϸ
				for (SingleDetail s : detailList) {
					if (s.getDetail_id() - singleDetail.getDetail_id() == 0l) {
						singleDetail.setAudit_money(s.getAudit_money());
						break;
					}
				}
			}
			StringResult strResult = accountService.updateSingle(singleTotal);
			if (IAccountService.SUCCESS.equals(strResult.getCode())) {
				// дXML�ļ�
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/"
						+ singleTotal.getWrite_eventId() + ".xml", singleTotal,
						getUser().getUserId(), getUser().getUserName(), null)) {
					this.setFailMessage("д��XML����");
					return RESULT_MESSAGE;
				}
			}
			this.setSuccessMessage("�����ɹ�!");
		} else {
			this.setFailMessage("����ʧ��!");
		}

		return RESULT_MESSAGE;
	}

	/**
	 * ������ѯ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "regionList", include = { "id", "text" })
	public String searchRegion() {
		String idColumn = "ZWL0" + level;
		String textColumn = "ZWL0" + level + "T";
		String paraColumn = level == 1 ? "" : "ZWL0" + (level - 1);
		Region region = new Region();
		region.setIdColumn(idColumn);
		region.setTextColumn(textColumn);
		region.setParaColumn(paraColumn);
		try {
			if (StringUtils.isNotEmpty(text)) {
				text = new String(getServletRequest().getParameter("text")
						.getBytes("ISO8859-1"), "UTF-8");
				region.setText(text.trim());
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		region.setPid(pid);
		regionList = accountService.searchRegion(region);
		return JSON;
	}

	/***
	 * ��ת���ճ����ô���
	 * 
	 * @return
	 */
	public String toAddExpenses() {
		// �ύ��
		userId = getUser().getUserId();
		userName = getUser().getUserName();
		Borg org = orgService.getOrgByOrgId(getUser().getOrgId());

		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		flag = "2";
		total = accountService.getPayeeInfoCountByName(userName);
		if (total > 0) {
			payeeInfo = accountService.getDefaultPayee(userName);
		}
		return "toAddExpenses";
	}

	/***
	 * ��ת���ճ����ò�ѯҳ��
	 * 
	 * @return
	 */
	public String toSearchExpenses() {
		return "toSearchExpenses";
	}

	/**
	 * ��ѯ������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "expensesTotalList", include = { "expenses_total_id",
			"transaction_id", "title", "pay_ee", "cost_org_name",
			"cost_center_name", "total_money", "audit_money", "pay_type",
			"create_date", "has_play_money", "status", "financial_doc_num" }, total = "total")
	public String searchExpensesJsonList() {
		ExpensesTotal eTotal = new ExpensesTotal();
		eTotal.setStart(getStart());
		eTotal.setEnd(getEnd());

		// �жϵ�ǰ�û��Ƿ�Ϊ����������ɫ��VP,�������ֻ�ܲ鿴�Լ��ı�����������ǿ��Բ鿴ȫ��������
		eTotal.setUser_id(this.getUser().getUserId());

		if (StringUtils.isNotEmpty(eventId)
				&& StringUtils.isNotEmpty(eventId.trim())) {
			eTotal.setEventId(eventId.trim());
		}
		if (StringUtils.isNotEmpty(title)
				&& StringUtils.isNotEmpty(title.trim())) {
			eTotal.setTitle(title.trim());
		}
		if (StringUtils.isNotEmpty(status)
				&& StringUtils.isNotEmpty(status.trim())) {
			eTotal.setStatus(status.trim());
		}
		if (StringUtils.isNotEmpty(payee)
				&& StringUtils.isNotEmpty(payee.trim())) {
			eTotal.setPay_ee(payee.trim());
		}
		if (startDate != null) {
			eTotal.setStartDate(startDate);
		}
		if (endDate != null) {
			eTotal.setEndDate(endDate);
		}
		if (StringUtils.isNotEmpty(playMoneyFlag)
				&& StringUtils.isNotEmpty(playMoneyFlag.trim())) {
			eTotal.setHas_play_money(playMoneyFlag.trim());
		}
		total = accountService.searchExpensesCount(eTotal);
		if (total != 0) {
			expensesTotalList = accountService.searchExpensesList(eTotal);
		}
		return JSON;
	}
	/**
	 * ��ӡ������
	 * 
	 * @return
	 */
	@PermissionSearch
	public String printExpenses() {
		ExpensesTotal total = new ExpensesTotal();
		if(StringUtils.isNotEmpty(planId)){
			total.setExpenses_total_id(Long.parseLong(planId.trim()));
			expensesTotal = accountService.getExpensesTotalAndDetail(total);
		}
		if("1".equals(type)){
			return "searchExpenseRcForm";
		}else{
			return "printExpenses";
		}
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SingleTotal getSingleTotal() {
		return singleTotal;
	}

	public void setSingleTotal(SingleTotal singleTotal) {
		this.singleTotal = singleTotal;
	}

	public String getDetailJsonStr() {
		return detailJsonStr;
	}

	public void setDetailJsonStr(String detailJsonStr) {
		this.detailJsonStr = detailJsonStr;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	public IWebService getWebService() {
		return webService;
	}

	public void setWebService(IWebService webService) {
		this.webService = webService;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getModelKey() {
		return modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public String getModelValues() {
		return modelValues;
	}

	public void setModelValues(String modelValues) {
		this.modelValues = modelValues;
	}

	public UserUtil getUserUtil() {
		return userUtil;
	}

	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<WorkPlanTotal> getPlanList() {
		return planList;
	}

	public void setPlanList(List<WorkPlanTotal> planList) {
		this.planList = planList;
	}

	/*
	 * public List<CmsTbDict> getCostTypeList() { return costTypeList; }
	 * 
	 * public void setCostTypeList(List<CmsTbDict> costTypeList) {
	 * this.costTypeList = costTypeList; }
	 */

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getCostCenterText() {
		return costCenterText;
	}

	public void setCostCenterText(String costCenterText) {
		this.costCenterText = costCenterText;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getToDoDetail() {
		return toDoDetail;
	}

	public void setToDoDetail(String toDoDetail) {
		this.toDoDetail = toDoDetail;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPlayMoneyFlag() {
		return playMoneyFlag;
	}

	public void setPlayMoneyFlag(String playMoneyFlag) {
		this.playMoneyFlag = playMoneyFlag;
	}

	public List<SingleTotal> getSingleTotalList() {
		return singleTotalList;
	}

	public void setSingleTotalList(List<SingleTotal> singleTotalList) {
		this.singleTotalList = singleTotalList;
	}

	public List<SingleDetail> getSingleDetailList() {
		return singleDetailList;
	}

	public void setSingleDetailList(List<SingleDetail> singleDetailList) {
		this.singleDetailList = singleDetailList;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Date getCost_date() {
		return cost_date;
	}

	public void setCost_date(Date cost_date) {
		this.cost_date = cost_date;
	}

	public String getCost_purpose() {
		return cost_purpose;
	}

	public void setCost_purpose(String cost_purpose) {
		this.cost_purpose = cost_purpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<PayeeInfo> getPayeeInfoList() {
		return payeeInfoList;
	}

	public void setPayeeInfoList(List<PayeeInfo> payeeInfoList) {
		this.payeeInfoList = payeeInfoList;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayArea() {
		return payArea;
	}

	public void setPayArea(String payArea) {
		this.payArea = payArea;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getPayAreaCode() {
		return payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
	}

	public String getPayBankAlias() {
		return payBankAlias;
	}

	public void setPayBankAlias(String payBankAlias) {
		this.payBankAlias = payBankAlias;
	}

	public String getPayBankAliCode() {
		return payBankAliCode;
	}

	public void setPayBankAliCode(String payBankAliCode) {
		this.payBankAliCode = payBankAliCode;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOperateFlag() {
		return operateFlag;
	}

	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	public PayeeInfo getPayeeInfo() {
		return payeeInfo;
	}

	public void setPayeeInfo(PayeeInfo payeeInfo) {
		this.payeeInfo = payeeInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public List<ProcessEventTotal> getProEventTotalList() {
		return proEventTotalList;
	}

	public void setProEventTotalList(List<ProcessEventTotal> proEventTotalList) {
		this.proEventTotalList = proEventTotalList;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getFinancial_doc_num() {
		return financial_doc_num;
	}

	public void setFinancial_doc_num(String financial_doc_num) {
		this.financial_doc_num = financial_doc_num;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getSearchStrFlag() {
		return searchStrFlag;
	}

	public void setSearchStrFlag(String searchStrFlag) {
		this.searchStrFlag = searchStrFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPro_manager_id() {
		return pro_manager_id;
	}

	public void setPro_manager_id(String pro_manager_id) {
		this.pro_manager_id = pro_manager_id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCostTypeContent() {
		return costTypeContent;
	}

	public void setCostTypeContent(String costTypeContent) {
		this.costTypeContent = costTypeContent;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public List<CostType> getCostTypeList() {
		return costTypeList;
	}

	public void setCostTypeList(List<CostType> costTypeList) {
		this.costTypeList = costTypeList;
	}

	public String getCost_parent_id() {
		return cost_parent_id;
	}

	public void setCost_parent_id(String cost_parent_id) {
		this.cost_parent_id = cost_parent_id;
	}

	public List<BudgetNumber> getBudgetList() {
		return budgetList;
	}

	public void setBudgetList(List<BudgetNumber> budgetList) {
		this.budgetList = budgetList;
	}

	public String getTheYear() {
		return theYear;
	}

	public void setTheYear(String theYear) {
		this.theYear = theYear;
	}

	public String getTheMonth() {
		return theMonth;
	}

	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}

	public String getBudget_number() {
		return budget_number;
	}

	public void setBudget_number(String budget_number) {
		this.budget_number = budget_number;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getAudit_money() {
		return audit_money;
	}

	public void setAudit_money(String audit_money) {
		this.audit_money = audit_money;
	}

	public BooleanResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}

	public String getAuditMoney() {
		return auditMoney;
	}

	public void setAuditMoney(String auditMoney) {
		this.auditMoney = auditMoney;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<ProEventTotal> getEventTotals() {
		return eventTotals;
	}

	public void setEventTotals(List<ProEventTotal> eventTotals) {
		this.eventTotals = eventTotals;
	}

	public List<OaXmlBean> getOaXmlBeanList() {
		return oaXmlBeanList;
	}

	public void setOaXmlBeanList(List<OaXmlBean> oaXmlBeanList) {
		this.oaXmlBeanList = oaXmlBeanList;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Date getBusinessEndDate() {
		return businessEndDate;
	}

	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Region> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ExpensesTotal getExpensesTotal() {
		return expensesTotal;
	}

	public void setExpensesTotal(ExpensesTotal expensesTotal) {
		this.expensesTotal = expensesTotal;
	}

	public List<ExpensesDetail> getExpensesDetailList() {
		return expensesDetailList;
	}

	public void setExpensesDetailList(List<ExpensesDetail> expensesDetailList) {
		this.expensesDetailList = expensesDetailList;
	}

	public List<ExpensesTotal> getExpensesTotalList() {
		return expensesTotalList;
	}

	public void setExpensesTotalList(List<ExpensesTotal> expensesTotalList) {
		this.expensesTotalList = expensesTotalList;
	}
}
