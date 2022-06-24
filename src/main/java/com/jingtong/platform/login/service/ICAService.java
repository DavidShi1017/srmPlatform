package com.jingtong.platform.login.service;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.login.pojo.ValidateResult;


/**
 * Ȩ����֤
 * 
 * @author xujiakun
 * 
 */
public interface ICAService {

	/**
	 * �ɹ�
	 */
	public static final String RESULT_SUCCESS = "0";

	/**
	 * ʧ��
	 */
	public static final String RESULT_FAILED = "1";

	/**
	 * ϵͳ������
	 */
	public static final String RESULT_ERROR = "2";

	public static final String INCORRECT_NULL = "User name or password can not be empty��";

	//public static final String INCORRECT_LOGINID = "The user does not exist in the system��";
	public static final String INCORRECT_LOGINID = "User name or password entered is incorrect��";
	public static final String INCORRECT_LOGIN = "User name or password entered is incorrect��";

	public static final String INCORRECT_TOKEN = "token��֤ʧ�ܣ�";

	/**
	 * ��֤��¼����ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUser(String passport, String password);

	/**
	 * ��֤��¼��ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult validateUserByLDAP(String passport, String password);
	
	/**
	 * �����¼��֤��¼����ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult singleValidateUser(String passport, String password);
	
	/**
	 * �����¼��֤��¼��ͨ������֤��
	 * 
	 * @param passport
	 * @param password
	 * @return
	 */
	public ValidateResult singleValidateUserByLDAP(String passport, String password);
	
	

	/**
	 * ��֤token
	 * 
	 * @param token
	 * @return
	 */
	public ValidateResult validateToken(String token);

	/**
	 * �������token
	 * 
	 * @param object
	 * @return
	 */
	public String generateToken(Object object);
	
	/**
	 * �����¼ʱ��ѯ�û�
	 * @return
	 */
	public AllUsers queryAllUser(AllUsers user);

}
