package com.jingtong.platform.login.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jingtong.platform.account.service.IAccountService;
import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.dao.ICustomerDao;
import com.jingtong.platform.framework.util.EncryptUtil;
import com.jingtong.platform.login.pojo.ValidateResult;
import com.jingtong.platform.login.service.ICAService;
import com.jingtong.platform.login.service.ILDAPService;
import com.jingtong.platform.role.pojo.Role;

/** 2018/11/12 Add Get Role Ids */
public class CAServiceImpl implements ICAService {

	private static final Log logger = LogFactory.getLog(CAServiceImpl.class);

	private IAllUserService allUserService;
	private ILDAPService LDAPService;
	
	// 2018/11/12 Add Start
	private ICustomerDao customerDao;

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	// 2018/11/12 Add End
	
	public ValidateResult validateUser(String passport, String password) {
		return validateUser(passport, password, false);
	}

	public ValidateResult validateUserByLDAP(String passport, String password) {
		return validateUser(passport, password, true);
	}

	private ValidateResult validateUser(String passport, String password, boolean validate) {

		// ��ʼ������ֵ ״̬ = ʧ��
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_LOGIN);

		// �˺Ż�����Ϊ��
		if (StringUtils.isEmpty(passport) || StringUtils.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// ����passport�����û���Ϣ
		AllUsers loginUser = allUserService.getAllUserByPassport(passport);

		// �жϵ�¼�û��Ƿ���OSAPϵͳ��
		if (loginUser == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		// 2018/11/12 Add Start
		// Get User Role Ids
		List<Role> roleIds = customerDao.getRoleIdsByLoginId(loginUser);
		if (roleIds != null && roleIds.size() > 0) {
			StringBuilder strIds = new StringBuilder();
			for (Role role : roleIds) {
				String roleId = role.getRoleId();
				if (roleId != null && !"".equals(roleId) && !"".equals(roleId.trim())) {
					if (strIds.length() > 0) {
						strIds.append(",");
					}
					strIds.append(roleId);
				}
			}
			if (strIds.length() > 0) {
				loginUser.setRoleIds(strIds.toString());
			}
		}
		// 2018/11/12 Add End
		
		// ϵͳ����Ա����������֤ �������
		if ("admin".equals(passport) || "X".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId().toUpperCase()) || passport.equals(loginUser.getLoginId())
						|| passport.equals(loginUser.getMobile()) || passport.equals(loginUser.getPhone()))
						&& (EncryptUtil.md5Encry(password).equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		if ("V".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile())
						|| passport.equals(loginUser.getPhone()))
						&& (EncryptUtil.md5Encry(password).equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		// ����֤
		if (validate) {
			if (LDAPService.authenticate(passport, password)) {

				// �ж� ���˺����� �� ims �Ƿ�һ�� / ��һ��ʱ �޸�ims����
				try {
					String pw = EncryptUtil.md5Encry(password);

					// ����һ�� -> �ɹ�
					if (pw.equals(loginUser.getPassWd())) {
						return setSuccessResult(result, loginUser);
					} else {
						AllUsers allUsers = new AllUsers();
						allUsers.setUserId(loginUser.getUserId());
						allUsers.setPassWd(pw);

						BooleanResult r = allUserService.updateAllUser(allUsers, "2");
						// �޸ĳɹ� -> �ɹ�
						// �޸�ʧ�� -> ���µ�¼
						if (r.getResult()) {
							return setSuccessResult(result, loginUser);
						} else {
							logger.error("passport:" + passport + " ���˺�������ECP��һ��");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else {
			try {
				if ((passport.equals(loginUser.getLoginId().toUpperCase()) || passport.equals(loginUser.getLoginId()))
						&& EncryptUtil.md5Encry(password).equals(loginUser.getPassWd())) {
					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	public ValidateResult validateToken(String token) {
		ValidateResult result = new ValidateResult();
		/*
		 * result.setResultCode(ICAService.RESULT_FAILED);
		 * result.setMessage(ICAService.INCORRECT_TOKEN);
		 * 
		 * try { AllUsers user = (AllUsers) memcachedCacheService.get(token); if (user
		 * != null) { // ������֤һ�κ� ʧЧ memcachedCacheService.remove(token); return
		 * setSuccessResult(result, user); } } catch (Exception e) { }
		 */
		return result;
	}

	public String generateToken(Object object) {
		/*
		 * try { String token = OidUtil.newId(); memcachedCacheService.add(token,
		 * object, IMemcachedCacheService.CACHE_KEY_SSO_TOKEN_DEFAULT_EXP);
		 * 
		 * return token; } catch (Exception e) {
		 * logger.error(LogUtil.parserBean(object), e); }
		 */

		return null;
	}

	private ValidateResult setSuccessResult(ValidateResult result, AllUsers user) {
		result.setResultCode(ICAService.RESULT_SUCCESS);
		user.setPassWd(null);
		result.setAllUser(user);
		result.setMessage(null);
		return result;
	}

	public IAllUserService getAllUserService() {
		return allUserService;
	}

	public void setAllUserService(IAllUserService allUserService) {
		this.allUserService = allUserService;
	}

	public ILDAPService getLDAPService() {
		return LDAPService;
	}

	public void setLDAPService(ILDAPService lDAPService) {
		LDAPService = lDAPService;
	}

	@Override
	public AllUsers queryAllUser(AllUsers user) {
		AllUsers allUser = this.allUserService.getAllUserByPassport(user.getLoginId());
//		List<AllUsers> userList = allUserService.searchAllUsers(user);
//		if(userList !=null && userList.size() !=0){
//			allUser = userList.get(0);
//		}
		return allUser;
	}

	@Override
	public ValidateResult singleValidateUser(String passport, String password) {
		boolean validate = false;
		// ��ʼ������ֵ ״̬ = ʧ��
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_LOGIN);

		// �˺Ż�����Ϊ��
		if (StringUtils.isEmpty(passport) || StringUtils.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// ����passport�����û���Ϣ
		AllUsers loginUser = allUserService.getAllUserByPassport(passport);

		// �жϵ�¼�û��Ƿ���OSAPϵͳ��
		if (loginUser == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		// ϵͳ����Ա����������֤ �������
		if ("admin".equals(passport) || "X".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile())
						|| passport.equals(loginUser.getPhone())) && (password.equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		if ("V".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile())
						|| passport.equals(loginUser.getPhone())) && (password.equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		// ����֤
		if (validate) {
			if (LDAPService.authenticate(passport, password)) {

				// �ж� ���˺����� �� ims �Ƿ�һ�� / ��һ��ʱ �޸�ims����
				try {
					String pw = password;

					// ����һ�� -> �ɹ�
					if (pw.equals(loginUser.getPassWd())) {
						return setSuccessResult(result, loginUser);
					} else {
						AllUsers allUsers = new AllUsers();
						allUsers.setUserId(loginUser.getUserId());
						allUsers.setPassWd(pw);

						BooleanResult r = allUserService.updateAllUser(allUsers, "2");
						// �޸ĳɹ� -> �ɹ�
						// �޸�ʧ�� -> ���µ�¼
						if (r.getResult()) {
							return setSuccessResult(result, loginUser);
						} else {
							logger.error("passport:" + passport + " ���˺�������ECP��һ��");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else {
			try {
				if (passport.equals(loginUser.getLoginId()) && password.equals(loginUser.getPassWd())) {
					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

	@Override
	public ValidateResult singleValidateUserByLDAP(String passport, String password) {
		boolean validate = true;
		// ��ʼ������ֵ ״̬ = ʧ��
		ValidateResult result = new ValidateResult();
		result.setResultCode(ICAService.RESULT_FAILED);
		result.setMessage(ICAService.INCORRECT_LOGIN);

		// �˺Ż�����Ϊ��
		if (StringUtils.isEmpty(passport) || StringUtils.isEmpty(password)) {
			result.setMessage(ICAService.INCORRECT_NULL);
			return result;
		}

		// ����passport�����û���Ϣ
		AllUsers loginUser = allUserService.getAllUserByPassport(passport);

		// �жϵ�¼�û��Ƿ���OSAPϵͳ��
		if (loginUser == null) {
			result.setMessage(ICAService.INCORRECT_LOGINID);
			return result;
		}

		// ϵͳ����Ա����������֤ �������
		if ("admin".equals(passport) || "X".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile())
						|| passport.equals(loginUser.getPhone())) && (password.equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		if ("V".equals(loginUser.getCustType())) {
			try {
				if ((passport.equals(loginUser.getLoginId()) || passport.equals(loginUser.getMobile())
						|| passport.equals(loginUser.getPhone())) && (password.equals(loginUser.getPassWd()))) {

					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
			// ��֤ʧ��
			return result;
		}

		// ����֤
		if (validate) {
			if (LDAPService.authenticate(passport, password)) {

				// �ж� ���˺����� �� ims �Ƿ�һ�� / ��һ��ʱ �޸�ims����
				try {
					String pw = password;

					// ����һ�� -> �ɹ�
					if (pw.equals(loginUser.getPassWd())) {
						return setSuccessResult(result, loginUser);
					} else {
						AllUsers allUsers = new AllUsers();
						allUsers.setUserId(loginUser.getUserId());
						allUsers.setPassWd(pw);

						BooleanResult r = allUserService.updateAllUser(allUsers, "2");
						// �޸ĳɹ� -> �ɹ�
						// �޸�ʧ�� -> ���µ�¼
						if (r.getResult()) {
							return setSuccessResult(result, loginUser);
						} else {
							logger.error("passport:" + passport + " ���˺�������ECP��һ��");
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		} else {
			try {
				if (passport.equals(loginUser.getLoginId()) && password.equals(loginUser.getPassWd())) {
					return setSuccessResult(result, loginUser);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return result;
	}

}
