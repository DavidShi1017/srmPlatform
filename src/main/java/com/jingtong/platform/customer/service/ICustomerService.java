package com.jingtong.platform.customer.service;

import java.util.List;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.customer.pojo.CusCompany;
import com.jingtong.platform.customer.pojo.Customer;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.role.pojo.Role;

/** 2018/11/15 Add CMD Data to forward list */
public interface ICustomerService {
	/**
	 * ��ȡ�б���
	 * 
	 * @param c
	 * @return
	 */
	public int getCustomerListCount(Customer c);

	/**
	 * ��ȡ�ͻ��б�
	 * 
	 * @param c
	 * @return
	 */
	public List<Customer> getCustomerList(Customer c);

	/**
	 * ���ݻ�ȡ��ȡ�ͻ���Ϣ
	 * 
	 * @param c
	 * @return
	 */
	public Customer getCustomerById(Customer c);

	/**
	 * ��ȡ��˾��ϵ�б�
	 * 
	 * @param c
	 * @return
	 */
	public List<CusCompany> getCusCompanyList(CusCompany c);

	//
	/**
	 * ��ѯ��Ա����ҵ�ܹ���
	 * 
	 * @param CustomerUser
	 * @return
	 */
	public List<CustomerUser> searchCustomerUser(CustomerUser alluser);

	/**
	 * ��ѯ��Աcount����ҵ�ܹ���
	 * 
	 * @param CustomerUser
	 * @return
	 */
	public int searchCustomerUserCount(CustomerUser alluser);

	public int setCusomerSignForUser(CustomerUser cusUser);

	public List<CustomerUser> getLoginCusUser(CustomerUser cusUser);

	public BooleanResult createUser(CustomerUser cu);

	public BooleanResult updateCustomerUser(CustomerUser cu);

	public CustomerUser getUsersByUserId(String ids);

	public List<CustomerUser> getDistiListOfThisUser(CustomerUser cusUser);

	public long bindDistiToThisUser(CustomerUser cusUser);

	public int unBind(CustomerUser cusUser);

	public int getCountByCusUser(CustomerUser cu);

	public List<Disti_branch> getDistiBranchList(Disti_branch db);

	public int getDistiBranchCount(Disti_branch db);

	public int getDistiNameCount(Disti_branch db);

	public List<Disti_branch> getDistiNameList(Disti_branch db);

	public int getDBCountByPayer_to(Disti_branch db);

	public int deleteDistiBranch(Disti_branch db);

	public int updateDistiBranch(Disti_branch db);

	public long createDistiBranch(Disti_branch db);

	public Disti_branch getDistiBranchById(Disti_branch db);

	public String getAllUserByLoginId(String upperCase);

	int setCreateUserForUser(CustomerUser cusUser);

	int setModifyUserForUser(CustomerUser cusUser);

	public int getRole1Count(Role r);

	public List<Role> getRole1List(Role r);

	// 2018/11/15 Add Start
	public int searchAllUsersForForwardCount(AllUsers allUser);

	public List<AllUsers> searchAllUsersForForward(AllUsers allUser);
	// 2018/11/15 Add End

}