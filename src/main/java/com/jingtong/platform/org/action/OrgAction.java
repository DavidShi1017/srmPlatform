package com.jingtong.platform.org.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.conpoint.service.IConpointService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.login.service.ILDAPService;
import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.pojo.SapTOrgUnit;
import com.jingtong.platform.org.service.IOrgService;
import com.jingtong.platform.position.action.PositionTypeAction;
import com.jingtong.platform.staffAmount.pojo.StaffAmount;
import com.jingtong.platform.staffAmount.service.IStaffService;

/**
 * org
 * 
 * @author
 */
public class OrgAction extends BaseAction {

	private Logger logger = Logger.getLogger(PositionTypeAction.class);

	private static final long serialVersionUID = 1L;

	private IOrgService orgService;
	private IStaffService staffAmountService;
	protected String actionName;
	@Decode
	private String orgName;
	private String orgId;
	private Borg borg;
	private Long orgParentId;
	private int total = 0;
	private String orgParentName;
	private Long orgLevel;
	private List<SapTOrgUnit> slist = new ArrayList<SapTOrgUnit>();
	private String orgUnit;
	private String txtmd;
	private IConpointService conpointService;
	private String groupConpoinit;
	private String creatOrgConpoinit;
	private String updateorgConpoinit;
	private TransactionTemplate transactionTemplate;
	private ILDAPService LDAPService;
	private boolean validate;
	private String orgIdIn;   //���ݵ�ǰ����֯ID����ȡ��֯��
	private List<Borg> orgList; //��֯�б�
	private List<Borg> companyList; 
	@PermissionSearch
	public String toOrgMangerMain() {

		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "borg", include = { "orgId", "orgCode", "orgName",
			"orgCity", "orgLevel", "orgRange", "orgParentId", "orgParentName",
			"shortName", "jianPing", "costCenter","sapOrgId","search" })
	public String showOrgDetail() {
		// ��ȡȨ�޵�
		/*
		 * AllUsers users = this.getUser(); Conpoint conpoint = new Conpoint();
		 * conpoint.setUserId(users.getUserId());
		 * conpoint.setConpointId(BigDecimal.valueOf(750));
		 * conpoint.setCloseFlag("Y"); groupConpoinit =
		 * conpointService.isAut(conpoint);
		 * 
		 * Conpoint conpointCreate = new Conpoint();
		 * conpointCreate.setUserId(users.getUserId());
		 * conpointCreate.setConpointId(BigDecimal.valueOf(770));
		 * conpointCreate.setCloseFlag("Y"); creatOrgConpoinit =
		 * conpointService.isAut(conpointCreate);
		 * 
		 * Conpoint conpointUpdate = new Conpoint();
		 * conpointUpdate.setUserId(users.getUserId());
		 * conpointUpdate.setConpointId(BigDecimal.valueOf(771));
		 * conpointUpdate.setCloseFlag("Y"); updateorgConpoinit =
		 * conpointService.isAut(conpointUpdate);
		 */

		if (orgId != null) {
			borg = orgService.getOrgByOrgId(orgId);
			borg.setSearch("("+borg.getOrgId()+")"+(borg.getOrgParentName()== null?"":borg.getOrgParentName()));
		}
		return JSON;
	}

	@PermissionSearch
	public String toCreateOrg() {
		try {
			this.orgParentName = new String(
					orgParentName.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(orgParentName, e);
		}
		orgLevel = orgLevel + 1;
		return "toCreateOrg";
	}

	@PermissionSearch
	@JsonResult(field = "companyList", include = {"orgId", "sapOrgId", "orgName" }, total = "total")
	public String getCompanyList() {
		Borg borg = new Borg();
		borg = getSearchInfo(borg);
		if (StringUtils.isNotEmpty(orgId)
				&& StringUtils.isNotEmpty(orgId.trim())) {
			borg.setOrgId(Long.valueOf(orgId));
		}
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			borg.setOrgName(orgName);
		}
		total = orgService.getCompanyListCount(borg);
		if (total != 0) {
			companyList=orgService.getCompanyList(borg);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "orgList", include = { "orgId", "orgName" ,"search","orgLevel"})
	public String getOrgJsonList() {
		Borg borg = new Borg();
		if (StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())) {
			try {
				orgName = new String(getServletRequest().getParameter("orgName")
					.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			borg.setOrgName(orgName);
			orgList = orgService.getOrgList(borg);
			for (Borg b : orgList) {
				b.setSearch("("+b.getOrgId()+")"+b.getOrgName());
			}
		}
		return JSON;
	}
	

	@PermissionSearch
	public String showSapOrg() {
		return "showSapOrg";
	}

	public String CreatOrg() {
		if (!borg.getOrgCity().equals("C")) {
			borg.setOrganiseType("Z");
		} else {
			borg.setOrganiseType("X");// ��˾��Ҫ���ΪX
		}
		borg.setCreateTime(new Date());
		borg.setLastModify(new Date());
		borg.setState("Y");

		// �����������
		Object result = transactionTemplate.execute(new TransactionCallback() {
			String results = "";

			 
			public Object doInTransaction(TransactionStatus ts) {
				boolean flag = true;
				// start���AdGroupName��sAMAccountName
				String orgType = borg.getOrganiseType();
				String groupSN = borg.getShortName();
				String groupJP = borg.getJianPing();
				if (!"X".equals(orgType)) {
					Long pId = borg.getOrgParentId();
					if (pId != null && pId != 0) {
						String tempSN = orgService.getOrgByOrgId(
								String.valueOf(pId)).getAdGroupName();
						groupSN = tempSN + "_" + groupSN;
						String tempJP = orgService.getOrgByOrgId(
								String.valueOf(pId)).getsAMAccountName();
						groupJP = tempJP + "_" + groupJP;
					} else {
						// pIdΪNULL,����root
						pId = 0L;
						borg.setOrgParentId(pId);
					}
				}
				borg.setAdGroupName(groupSN);
				borg.setsAMAccountName(groupJP);
				// end���AdGroupName��sAMAccountName
				if (validate) {
					flag = LDAPService.addOrg2Ad(borg);
				}
				if (flag) {
					BooleanResult booleanResult = orgService.createOrg(borg);
					if (!booleanResult.getResult()) {
						ts.setRollbackOnly();
						results = "F";
						return results;
					}
					results = "T";
					return results;
				} else {
					results = "FA";
					return results;
				}
			}
		});
		if ("F".equals(result.toString())) {
			this.setFailMessage("��֯��Ϣ����ʧ��.");
		} else if ("T".equals(result.toString())) {
			this.setSuccessMessage("��֯��Ϣ�����ɹ�.");
		} else if ("FA".equals(result.toString())) {
			this.setFailMessage("��֯��Ϣ����ʧ��.AD�����֯��Ϣ�����쳣");
		}
		return RESULT_MESSAGE;
	}

	// IMS��֯
	@PermissionSearch
	public String ImsOrgCheck() {
		List<Borg> synOrgList = orgService.getOrgTreeListByPorgId(String
				.valueOf(borg.getOrgId()));
		if (synOrgList != null) {
			for (Borg org : synOrgList) {

				// start���AdGroupName��sAMAccountName
				String orgType = org.getOrganiseType();
				String groupSN = org.getShortName();
				String groupJP = org.getJianPing();
				if (!"X".equals(orgType)) {
					Long pId = org.getOrgParentId();
					String tempSN = orgService.getOrgByOrgId(
							String.valueOf(pId)).getAdGroupName();
					groupSN = tempSN + "_" + groupSN;
					String tempJP = orgService.getOrgByOrgId(
							String.valueOf(pId)).getsAMAccountName();
					groupJP = tempJP + "_" + groupJP;
				}
				org.setAdGroupName(groupSN);
				org.setsAMAccountName(groupJP);
				// end���AdGroupName��sAMAccountName
				BooleanResult booleanResult = orgService
						.updateBorgWithADInfo(org);
				if (booleanResult.getResult()) {
					this.setSuccessMessage("������.");
				} else {
					this.setFailMessage("��Ims[" + borg.getOrgName()
							+ "]�¸�������֯sAMAccountName,ADGroupName����ʧ�ܣ�");
				}

			}
		} else {
			this.setFailMessage("��Ims[" + borg.getOrgName() + "]��Ѱ�Ҳ����κ�����֯��");
		}
		this.setSuccessMessage("������.");
		return RESULT_MESSAGE;

	}

	public String updateOrg() {
		boolean flag = true;
		BooleanResult imsbooleanResult = new BooleanResult();
		Borg oldBorg = orgService
				.getOrgByOrgId(String.valueOf(borg.getOrgId()));
		if (!borg.getOrgName().equals(oldBorg.getOrgName())) {
			if (validate) {
				flag = LDAPService.updateOrg2Ad(oldBorg, borg);
			}
		}
		if ("C".equals(borg.getOrgCity())) {
			borg.setOrganiseType("X");
		}
		if (flag) {
			imsbooleanResult = orgService.updateBorg(borg);
			if (!imsbooleanResult.getResult()) {
				this.setFailMessage("��֯��Ϣ�޸�ʧ��.");
			} else {
				this.setSuccessMessage("��֯��Ϣ�޸ĳɹ�.");
			}
		} else {
			this.setFailMessage("��֯��Ϣ�޸�ʧ��.AD�����֯��Ϣ�޸��쳣");
		}
		return RESULT_MESSAGE;
	}

	@SuppressWarnings("deprecation")
	public String deleteOrg() {
		BooleanResult imsbooleanResult = new BooleanResult();
		StaffAmount staffAmount = new StaffAmount();
		String orgids = orgService.getFnAllChildStrOrg(orgId);
		if (StringUtils.isNotEmpty(orgids)) {
			staffAmount.setOrgIdarrs(orgids.split(","));
		}
		total = staffAmountService.getStaffTotal(staffAmount);
		if (total > 0) {
			this.setSuccessMessage("����֯�ṹ�¹��и�λ����,������h��");
		} else {
			String orgid[] = orgids.split(",");
			for (int i = orgid.length - 1; i >= 0; i--) {
				boolean flag = true;
				borg.setOrgId(Long.parseLong(orgid[i]));
				borg.setSourceId(orgid[i]);
				if (validate) {
					flag = LDAPService.deleteOrg2Ad(borg);
				}
				if (flag) {
					imsbooleanResult = orgService.deleteBorg(borg);
					if (!imsbooleanResult.getResult()) {
						this.setFailMessage("��֯�ṹɾ��ʧ��.");
					} else {
						this.setSuccessMessage("��֯�ṹɾ���ɹ�.");
					}
				} else {
					this.setFailMessage("��֯�ṹɾ��ʧ��.AD�����֯�º�����Ա.");
				}
			}
		}
		return RESULT_MESSAGE;
	}

	public String dropOrg() {
		BooleanResult imsbooleanResult = new BooleanResult();
		imsbooleanResult = orgService.dropBorg(borg);
		if (!imsbooleanResult.getResult()) {
			this.setFailMessage("��֯�ṹ����ʧ��.");
		} else {
			this.setSuccessMessage("��֯�ṹ�����ɹ�.");
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String orgTreePage() {
		return "orgtree";
	}

	public String orgTreePage4Post() {
		return "orgTreePage4Post";
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public Borg getBorg() {
		return borg;
	}

	public void setBorg(Borg borg) {
		this.borg = borg;
	}

	public Long getOrgParentId() {
		return orgParentId;
	}

	public void setOrgParentId(Long orgParentId) {
		this.orgParentId = orgParentId;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public void setCompanyList(List<Borg> companyList) {
	}

	public List<SapTOrgUnit> getSlist() {
		return slist;
	}

	public void setSlist(List<SapTOrgUnit> slist) {
		this.slist = slist;
	}

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getTxtmd() {
		return txtmd;
	}

	public void setTxtmd(String txtmd) {
		this.txtmd = txtmd;
	}

	public String getOrgParentName() {
		return orgParentName;
	}

	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IConpointService getConpointService() {
		return conpointService;
	}

	public void setConpointService(IConpointService conpointService) {
		this.conpointService = conpointService;
	}

	public String getGroupConpoinit() {
		return groupConpoinit;
	}

	public void setGroupConpoinit(String groupConpoinit) {
		this.groupConpoinit = groupConpoinit;
	}

	public String getCreatOrgConpoinit() {
		return creatOrgConpoinit;
	}

	public void setCreatOrgConpoinit(String creatOrgConpoinit) {
		this.creatOrgConpoinit = creatOrgConpoinit;
	}

	public String getUpdateorgConpoinit() {
		return updateorgConpoinit;
	}

	public void setUpdateorgConpoinit(String updateorgConpoinit) {
		this.updateorgConpoinit = updateorgConpoinit;
	}

	public IStaffService getStaffAmountService() {
		return staffAmountService;
	}

	public void setStaffAmountService(IStaffService staffAmountService) {
		this.staffAmountService = staffAmountService;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getOrgIdIn() {
		return orgIdIn;
	}

	public void setOrgIdIn(String orgIdIn) {
		this.orgIdIn = orgIdIn;
	}

	public List<Borg> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Borg> orgList) {
		this.orgList = orgList;
	}
	
}
