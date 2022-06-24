package com.jingtong.platform.sap.pojo;


import com.jingtong.platform.base.pojo.SearchInfo;

public class SampleOrderToSap extends SearchInfo{
	
	private static final long serialVersionUID = -3386093021023024761L;
	
	
	private long id;
	private String salesId;//���ۺͷ���ƾ֤�� 
	private String sale_group;//������֯
	private String distri_channel;//��������
	private String product_group;//��Ʒ��  
	private String type_id;//����ƾ֤����  
	private String ship_to;//�ʹ﷽  
	private String payer_to;//���  
	private String billing_to;//��ȡ��Ʊ��  
	private String sale_to;//�۴﷽  
	private String currency_code;//SD ƾ֤���� 
	private String order_id ;//�ͻ��ɹ�������� 
	private String txt;//̧ͷ�ı�
	
	private String end_customer;//�ն˿ͻ���End Customer��
	private String project_name;// ��Ŀ����(Project Name) 
	private String contact_info;//�ն˿ͻ���ϵ��Ϣ(EC ContactInfo)

	
	private String[] ids;//��ѯ����

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
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

	public String getProduct_group() {
		return product_group;
	}

	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
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



	public String getSale_to() {
		return sale_to;
	}

	public void setSale_to(String sale_to) {
		this.sale_to = sale_to;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}





	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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


	
	
	
	
}
