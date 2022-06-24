package com.jingtong.platform.sampleOrder.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;
/**
 * ��Ʒ��������
 * @author yw
 *
 */
public class SampleOrder extends SearchInfo{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long id;
	private String order_id;//�������(Purchase Order)
	private String po_item;
	private String type_id;//�������ͣ�Order Type��
	private String branch_id;//�����̣�Channel Branch��
	private String ship_to;//���˷���Ship To��
	private String payer_to;//�����Payer To��
	private String billing_to;//��Ʊ��(Billing To) 
	private String sale_to;//�۴﷽-
	private String saler;//������Ա(Sales) 
	private String sale_company;// ���۹�˾��Sale Company��
	private String company;// �ͻ����ƹ�˾��Company��
	private String end_customer;//�ն˿ͻ���End Customer��
	private String project_name;// ��Ŀ����(Project Name) 
	private String contact_info;//�ն˿ͻ���ϵ��Ϣ(EC ContactInfo)
	private String sap_order_id;//����ƾ֤��-
	private String sale_group;//������֯-HK01
	private String distri_channel;//��������-00
	private String product_group;//��Ʒ��-00
	private int state;//״̬
	private String remark;//��ע
	private String ween_remark;//��ע
	private int sync_state;//ͬ��״̬
	private Date sync_time;//ͬ��ʱ��
	private String sync_userId;//ͬ���û�
	private String sysnc_exception;//ͬ���쳣
	private String create_userId;//
	private Date create_time;
	private Date latest_time;//����ʱ��
	private String latest_userId;//������
	private String org_code;//������֯
	private String zip;//
	private String loginId;//
	
	private String customer_name;//
	private String shipToName;//
	private String payerToName;//
	private String shipToAddress;//�ʹ﷽-
	private String billingToName;//
	private String contact_name;//�ն˿ͻ���ϵ��Ϣ(EC ContactInfo)
	private String contact_tel;//�ն˿ͻ���ϵ��Ϣ(EC ContactInfo)
	private Date start_date;
	private Date end_date;
	private String start_dateStr;//
	private String end_dateStr;//
	private String cus_country;//
	private String country;//
	private String city;//
	private String street;//
	private String application_desc;//
	
	// �ʹ�����
    private String ship_to_region;
    // ������
    private String applicant_name;
    // ���빫˾
    private String applicant_company;
    // ������
    private String account_manager;
    
    private String manager_id;
    
    // ��鱸ע
    private String comments;
    
    private String states;
	
    private String shipToRegions;
    
    private String roleType;

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApplication_desc() {
		return application_desc;
	}
	public void setApplication_desc(String application_desc) {
		this.application_desc = application_desc;
	}
	public long getId() {
		return id;
	}
	public String getSap_order_id() {
		return sap_order_id;
	}
	public void setSap_order_id(String sap_order_id) {
		this.sap_order_id = sap_order_id;
	}
	public String getSale_group() {
		return sale_group;
	}
	public void setSale_group(String sale_group) {
		this.sale_group = sale_group;
	}
	public String getDistri_channel() {
		return distri_channel;
	}
	public void setDistri_channel(String distri_channel) {
		this.distri_channel = distri_channel;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(String shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getShip_to() {
		return ship_to;
	}
	public void setShip_to(String ship_to) {
		this.ship_to = ship_to;
	}
	public String getPayer_to() {
		return payer_to;
	}
	public void setPayer_to(String payer_to) {
		this.payer_to = payer_to;
	}
	public String getBilling_to() {
		return billing_to;
	}
	public void setBilling_to(String billing_to) {
		this.billing_to = billing_to;
	}
	public String getSaler() {
		return saler;
	}
	public void setSaler(String saler) {
		this.saler = saler;
	}
	public String getSale_company() {
		return sale_company;
	}
	public void setSale_company(String sale_company) {
		this.sale_company = sale_company;
	}
	public String getEnd_customer() {
		return end_customer;
	}
	public void setEnd_customer(String end_customer) {
		this.end_customer = end_customer;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getContact_info() {
		return contact_info;
	}
	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
		public String getWeen_remark() {
		return ween_remark;
	}
	public void setWeen_remark(String ween_remark) {
		this.ween_remark = ween_remark;
	}
	public int getSync_state() {
		return sync_state;
	}
	public void setSync_state(int sync_state) {
		this.sync_state = sync_state;
	}
	public Date getSync_time() {
		return sync_time;
	}
	public void setSync_time(Date sync_time) {
		this.sync_time = sync_time;
	}
	public String getSync_userId() {
		return sync_userId;
	}
	public void setSync_userId(String sync_userId) {
		this.sync_userId = sync_userId;
	}
	public String getSysnc_exception() {
		return sysnc_exception;
	}
	public void setSysnc_exception(String sysnc_exception) {
		this.sysnc_exception = sysnc_exception;
	}
	public String getCreate_userId() {
		return create_userId;
	}
	public void setCreate_userId(String create_userId) {
		this.create_userId = create_userId;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getLatest_time() {
		return latest_time;
	}
	public void setLatest_time(Date latest_time) {
		this.latest_time = latest_time;
	}
	public String getLatest_userId() {
		return latest_userId;
	}
	public void setLatest_userId(String latest_userId) {
		this.latest_userId = latest_userId;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getSale_to() {
		return sale_to;
	}
	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getPayerToName() {
		return payerToName;
	}
	public void setPayerToName(String payerToName) {
		this.payerToName = payerToName;
	}
	public String getBillingToName() {
		return billingToName;
	}
	public void setBillingToName(String billingToName) {
		this.billingToName = billingToName;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_tel() {
		return contact_tel;
	}
	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}
	public String getPo_item() {
		return po_item;
	}
	public void setPo_item(String po_item) {
		this.po_item = po_item;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStart_dateStr() {
		return start_dateStr;
	}
	public void setStart_dateStr(String start_dateStr) {
		this.start_dateStr = start_dateStr;
	}
	public String getEnd_dateStr() {
		return end_dateStr;
	}
	public void setEnd_dateStr(String end_dateStr) {
		this.end_dateStr = end_dateStr;
	}
	public String getCus_country() {
		return cus_country;
	}
	public void setCus_country(String cus_country) {
		this.cus_country = cus_country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
    public String getShip_to_region() {
        return ship_to_region;
    }
    public void setShip_to_region(String ship_to_region) {
        this.ship_to_region = ship_to_region;
    }
    public String getApplicant_name() {
        return applicant_name;
    }
    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }
    public String getApplicant_company() {
        return applicant_company;
    }
    public void setApplicant_company(String applicant_company) {
        this.applicant_company = applicant_company;
    }
    public String getAccount_manager() {
        return account_manager;
    }
    public void setAccount_manager(String account_manager) {
        this.account_manager = account_manager;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getStates() {
        return states;
    }
    public void setStates(String states) {
        this.states = states;
    }
    public String getManager_id() {
        return manager_id;
    }
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
    public String getShipToRegions() {
        return shipToRegions;
    }
    public void setShipToRegions(String shipToRegions) {
        this.shipToRegions = shipToRegions;
    }
    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
