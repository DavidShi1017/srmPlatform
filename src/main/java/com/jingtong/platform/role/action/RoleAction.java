package com.jingtong.platform.role.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.StringResult;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.dict.service.IDictService;
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.menu.action.MenuTreeAjaxAction;
import com.jingtong.platform.role.pojo.Role;
import com.jingtong.platform.role.service.IRoleService;
import com.jingtong.platform.station.pojo.Station;

public class RoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645806566226562765L;

	private static final Log logger = LogFactory
			.getLog(MenuTreeAjaxAction.class);
	private String orgId;
	private String bhxjFlag;
	private String loginId;
	private String userName;
	private String phone;
	private String mobile;
	private int total;
	@Decode
	private String roleType;
	private IRoleService roleService;
	@Decode
	private String roleId;
	@Decode
	private String roleName;
	@Decode
	private String stationId;
	@Decode
	private String stationName;
	@Decode
	private String positionTypeId;
	private List<Station> stationList = new ArrayList<Station>();
	private List<CmsTbDict> dictlist = new ArrayList<CmsTbDict>();
	private Role role;
	/**
	 * Ȩ�޵�
	 */
	private String conpointId;
	private String roleIds;
	private String sids;
	private IDictService dictService;
	private String whichPage;
	/**
	 * �˵�id
	 */
	private String menuId;

	private String ids;

	private List<Role> roleList = new ArrayList<Role>();
	private List<Role> roleLists = new ArrayList<Role>();
    private String kunnrSign;
	
	/**
	 * ��ѯ�˵�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRole() {
		return "searchRole";
	}

	/**
	 * ��Ȩ�޵�ʹ��/���˵�ʹ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchRole4Config() {

		return "searchRole4Config";
	}

	/**
	 * ȡ���⴦��״̬
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dictlist", include = { "itemName", "itemValue" })
	public String getCustTypeList() {
		CmsTbDict cm = new CmsTbDict();
		cm.setItemName("��ɫ����");
		dictlist = dictService.getByCmsTbDictList(cm);
		return JSON;
	}

	/**
	 * ְλ��λ��ѯ����ɫ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchPositionType4Role() {
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}

		return "searchPositionType4Role";
	}

	/**
	 * ְλ��λ��ѯ����ɫ��
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stationList", include = { "stationId", "stationName",
			"orgId", "orgName" }, total = "total")
	public String getPositionType4RoleJsonList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
			if (StringUtils.isNotEmpty(stationName)
					&& StringUtils.isNotEmpty(stationName.trim())) {
				r.setRoleName(stationName.trim());
			}
			if (StringUtils.isNotEmpty(stationId)
					&& StringUtils.isNotEmpty(stationId.trim())) {
				r.setDescn(stationId.trim());
			}
			total = roleService.getPositionType4RoleCount(r);
			if (total != 0) {
				stationList = roleService.getPositionType4RoleList(r);
			} else {
				stationList = null;
			}
		} else {
			stationList = null;
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn",
			"roleType" }, total = "total")
	public String getRoleJsonList() {
		Role r = new Role();
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}
		total = roleService.getRoleCount(r);
		if (total != 0) {
			roleList = roleService.getRoleList(r);
		} else {
			roleList = null;
		}
		return JSON;
	}

	/**
	 * �޸�/��ѯ��ɫ��Ϣ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateRolePrepare() {

		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {

			try {
				roleId = new String(roleId.trim().getBytes("ISO8859-1"),
						"UTF-8");
				role = roleService.getRoleByRoleId(roleId);
			} catch (UnsupportedEncodingException e) {
				logger.error(roleId, e);
			}
		}
		role = role == null ? new Role() : role;
		return UPDATE_PREPARE;
	}

	public String updateRole() {
		if (!validate(role)) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		StringResult result = roleService.updateRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}else{
			this.setSuccessMessage("��ɫ�޸ĳɹ�");
		}
		return RESULT_MESSAGE;
	}

	private boolean validate(Role role) {

		if (role == null) {
			return false;
		}
		if (StringUtils.isEmpty(role.getRoleId())
				|| StringUtils.isEmpty(role.getRoleId().trim())
				|| StringUtils.isEmpty(role.getRoleName())
				|| StringUtils.isEmpty(role.getRoleName().trim())) {
			return false;
		}

		return true;
	}

	@PermissionSearch
	public String createRolePrepare() {
		return CREATE_PREPARE;
	}

	/**
	 * ������ɫ
	 * 
	 * @return
	 */
	public String createRole() {
		if (!validate(role)) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}
		if(roleService.getRoleCount1(role)!=0l){
			this.setFailMessage("�ý�ɫ�ѱ�ռ��");
			return RESULT_MESSAGE;
		}
		StringResult result = roleService.createRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}
		this.setSuccessMessage("�����ɹ�");
		return RESULT_MESSAGE;
	}

	public String deleteRole() {
		String[] l = ids.split(",");
		Role role = new Role();
		role.setCodes(l);
		/*Role role1 = new Role();
		role.setCodes(l);
		for(int i = 0 ;i<l.length;i++){
		    role1.setRoleId(l[i]);
		    if(roleService.getConRole(role1)>0){
		    	this.setFailMessage("��ɫID"+l[i]+"�»���δɾ��Ȩ�޵� ");
		    	return RESULT_MESSAGE;
		    }
		}*/
		StringResult result = roleService.deleteRole(role);
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + result.getResult() + "����ɫ!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��Ȩ�޵�ʹ��/���˵�ʹ��
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "roleList", include = { "roleId", "roleName", "descn" }, total = "total")
	public String getRole4ConfigJsonList() {
		Role r = new Role();
		// r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim());
		}

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtils.isNotEmpty(conpointId)
				&& StringUtils.isNotEmpty(conpointId.trim())) {
			try {
				r.setConpointId(Long.parseLong(conpointId.trim()));
			} catch (Exception e) {
				logger.error("conpointId:" + conpointId, e);
				return JSON;
			}

			total = roleService.getRole4ConpointCount(r);
			if (total != 0) {
				roleList = roleService.getRole4ConpointList(r);
			} else {
				roleList = null;
			}

		} else if (StringUtils.isNotEmpty(menuId)
				&& StringUtils.isNotEmpty(menuId.trim())) {
			try {
				r.setMenuId(Long.parseLong(menuId.trim()));
			} catch (Exception e) {
				logger.error("menuId:" + menuId, e);
				return JSON;
			}

			total = roleService.getRole4MenuCount(r);
			if (total != 0) {
				roleList = roleService.getRole4MenuList(r);
			} else {
				roleList = null;
			}
		}
		return JSON;
	}
	
	
	/**
	 * 
	 * ���ݽ�ɫ��ѯ��Ա
	 * */
	@PermissionSearch
	@JsonResult(field = "roleList", include = { "emp_code", "emp_name", "orgName" }, total = "total")
	public String getUserByRole(){
		
		role =new Role();
		role.setRoleId(roleId);
		role.setEmp_code(stationId);
		role.setEmp_name(stationName);
		roleList = roleService.getUserByRole(role);//(r);
		
		return JSON;
		
	}
	
	

	/**
	 * ѡ���ɫ ����Ȩ��/ְλ��λ
	 * 
	 * @return
	 */
	public String selectRole() {
		Role r = new Role();
		StringResult result = null;

		if (StringUtils.isNotEmpty(roleIds)
				&& StringUtils.isNotEmpty(roleIds.trim())) {

			String[] temp = roleIds.split(",");
			String[] ids = new String[temp.length];
			int i = 0;

			for (String t : temp) {
				ids[i++] = t.trim();
			}
			r.setCodes(ids);
		}
		if (StringUtils.isNotEmpty(kunnrSign) && StringUtils.isNotEmpty(kunnrSign.trim())) {
 			r.setKunnrSign(kunnrSign);
		}
		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			r.setStationId(stationId.trim());

			result = roleService.selectRole4Station(r);
		}

		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		}

		if (IRoleService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage("�ɹ�ά����ɫ��ţ�" + result.getResult());
		}

		return RESULT_MESSAGE;
	}

	public String searchAddressBookPre() {
		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "roleList", include = { "id", "roleId", "roleName",
			"descn" }, total = "total")
	public String getSelectedRoleJsonList() {
		Role r = new Role();
		r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId) &&
				  StringUtils.isNotEmpty(roleId.trim())) {
			r.setRoleId(roleId.trim()
					);
		 }

		if (StringUtils.isNotEmpty(roleName)
				&& StringUtils.isNotEmpty(roleName.trim())) {
			r.setRoleName(roleName.trim());
		}

		if (StringUtils.isNotEmpty(stationId)
				&& StringUtils.isNotEmpty(stationId.trim())) {
			r.setEmp_code(stationId.trim());

			total = roleService.getSelectedRole4StationCount(r);
			if (total != 0) {
				roleList = roleService.getSelectedRole4StationList(r);
			} else {
				roleList = null;
			}
		}
		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "roleLists", include = { "id", "roleId", "roleName",
			"descn" }, total = "total")
	public String getSelectedRoleJsonListJosn() {
		Role r = new Role();
		r = getSearchInfo(r);
		r.setStart(getStart());
		r.setEnd(getEnd());
		if (StringUtils.isNotEmpty(roleId)
				&& StringUtils.isNotEmpty(roleId.trim())) {
			try {
				roleId = new String(this.getRoleId().getBytes("ISO8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			r.setRoleId(roleId.trim());
			r.setRoleName(roleId.trim());
		}
		if (StringUtils.isNotEmpty(roleType)
				&& StringUtils.isNotEmpty(roleType.trim())) {
			try {
				roleType = new String(this.getRoleType().getBytes("ISO8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			r.setRoleType(roleType);
		}
		total = roleService.getRole1Count(r);
		if (total != 0) {
			roleLists = roleService.getRole1List(r);
		} else {
			roleLists = null;
		}

		return JSON;
	}

	/**
	 * ѡ���ɫ ����Ȩ��/ְλ��λ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchSelectedRole() {
		stationId = this.getStationId();
		roleId = "";
		whichPage = whichPage;
		return "searchSelectedRole";
	}

	/**
	 * ѡ���ɫ ����Ȩ��/ְλ��λ
	 * 
	 * @return
	 */
	public String deleteSelectedRole() {
		int i = 0;
		Role role = new Role();
		String type = null;
		String[] ls = sids.split(",");
		String[] l = new String[ls.length];
		for (i = 0; i < ls.length; i++) {
			if (ls.length >= 0) {
				l[i] = ls[i];
				type = "station";
			}
		}
		/*
		 * for (Role r : roleList) { if (r.getStationRoleId() != null) { l[i++]
		 * = r.getStationRoleId().toString(); type = "station"; } }
		 */
		// ����Ч�Ľ�ɫid
		if (i == 0) {
			this.setFailMessage(IRoleService.ERROR_INPUT_MESSAGE);
			return RESULT_MESSAGE;
		}

		role.setCodes(l);
		StringResult result = null;
		if ("station".equals(type)) {
			result = roleService.deleteSelectedRole4Station(role);
		} /*
		 * else if ("positionType".equals(type)) { result =
		 * roleService.deleteSelectedRole4PositionType(role); }
		 */
		if (IRoleService.ERROR.equals(result.getCode())) {
			this.setFailMessage(result.getResult());
		} else {
			this.setSuccessMessage("�ѳɹ�ɾ��" + l.length + "����ɫ��");
		}

		return RESULT_MESSAGE;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getConpointId() {
		return conpointId;
	}

	public void setConpointId(String conpointId) {
		this.conpointId = conpointId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getPositionTypeId() {
		return positionTypeId;
	}

	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}

	public List<Role> getRoleLists() {
		return roleLists;
	}

	public void setRoleLists(List<Role> roleLists) {
		this.roleLists = roleLists;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getSids() {
		return sids;
	}

	public void setSids(String sids) {
		this.sids = sids;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public IDictService getDictService() {
		return dictService;
	}

	public void setDictService(IDictService dictService) {
		this.dictService = dictService;
	}

	public List<CmsTbDict> getDictlist() {
		return dictlist;
	}

	public void setDictlist(List<CmsTbDict> dictlist) {
		this.dictlist = dictlist;
	}

	public String getKunnrSign() {
		return kunnrSign;
	}

	public void setKunnrSign(String kunnrSign) {
		this.kunnrSign = kunnrSign;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLogger() {
		return logger;
	}

	public String getWhichPage() {
		return whichPage;
	}

	public void setWhichPage(String whichPage) {
		this.whichPage = whichPage;
	}

	
}
