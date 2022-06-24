package com.jingtong.platform.product.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

/**
 * ��Ʒ��Ϣ
 * 
 * @author lenovo
 *
 */
public class Product extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String material_id; // ���ϱ��루12NC��-
    private String material_name;// �������ƣ�BOOK Part��-
    private String material_exp;// ����������Description��-
    private String material_type;// �������ͣ�Material Type����Ʒ��-
    private String material_groupId;// �����飨Material Group��
    private Double lead_time;// �������ڣ�Lead Time����portal excel���룩
    private String base_unit;// ������λ��Base Unit��-
    private String sale_unit;// ���۵�λ��Base Unit Of Sale������-
    private String unit_change;// ��λ���㣨Unit Change�����������ɷ��ӷ�ĸ��
    private String numerator;// ��λ����֮���ӣ�������
    private String denominator;// ��λ����֮��ĸ
    private String isOrderItem;// �ܷ��µ���Order Item����portal excel���룩
    private String isQuoteItem;// �ܷ񱨼ۣ�Quote Item����portal excel���룩
    private String isDRItem;// �ܷ���ע��DR Item����portal excel���룩
    private Double limited_qty;// ��Ʒ����������Sample Limited QTY����portal excel���룩
    private int moq;// ������MOQ��-
    private String cost;// �ɱ���WeEn Cost��
    private String factory;// ����
    private String remark;// ����
    private String customer_id;// �ͻ����
    private int state;// ״̬ ״̬��status��0�½�
    private String isDeleted;// �Ƿ�ɾ�����߼�ɾ����1��0��
    private int sortId; // �����
    private int sync_state;// ͬ��״̬��1ͬ����0δͬ����
    private Date sync_time;// ͬ��ʱ��
    private String sync_userId;// ͬ���û�
    private String sysnc_exception;// ͬ���쳣
    private String create_userId;// �����û�
    private Date create_time;// ����ʱ��
    private Date latest_time;// ����ʱ��
    private String latest_userId;// ������
    private String latest_deptId;// ��������
    private String customer_group;// �ͻ�����(For Customer)
    private String pq; // PQ(WM��λ)�����۵�λ ��base_unit�Ĺ�ϵ 1PQ=numerator*base_unit/denominator
    private String isLocked; // ���ϱ�����
    private String isDownLoad;// ����ģ���ʶ��ֻ������������Ϊ��ģ�
    private String useFor;// ʹ���ںδ���ʶ����YPDΪ��Ʒ������Ʒ������ʾHK11������
    private double pbPrice;// ����priceRule
    private String perUnit;// ����priceRule
    private String currency_code;// ���ڹ���priceRule
    private String office_id;// ���ڹ���priceRule
    private Date rfs_date;
    private String rfs_dateStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsDownLoad() {
        return isDownLoad;
    }

    public void setIsDownLoad(String isDownLoad) {
        this.isDownLoad = isDownLoad;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public String getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(String perUnit) {
        this.perUnit = perUnit;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_exp() {
        return material_exp;
    }

    public void setMaterial_exp(String material_exp) {
        this.material_exp = material_exp;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getMaterial_groupId() {
        return material_groupId;
    }

    public void setMaterial_groupId(String material_groupId) {
        this.material_groupId = material_groupId;
    }

    public double getLead_time() {
        return lead_time;
    }

    public void setLead_time(Double lead_time) {
        this.lead_time = lead_time;
    }

    public String getBase_unit() {
        return base_unit;
    }

    public void setBase_unit(String base_unit) {
        this.base_unit = base_unit;
    }

    public String getSale_unit() {
        return sale_unit;
    }

    public String getCustomer_group() {
        return customer_group;
    }

    public void setCustomer_group(String customer_group) {
        this.customer_group = customer_group;
    }

    public void setSale_unit(String sale_unit) {
        this.sale_unit = sale_unit;
    }

    public String getUnit_change() {
        return unit_change;
    }

    public void setUnit_change(String unit_change) {
        this.unit_change = unit_change;
    }

    public String getIsOrderItem() {
        return isOrderItem;
    }

    public void setIsOrderItem(String isOrderItem) {
        this.isOrderItem = isOrderItem;
    }

    public String getIsQuoteItem() {
        return isQuoteItem;
    }

    public void setIsQuoteItem(String isQuoteItem) {
        this.isQuoteItem = isQuoteItem;
    }

    public String getIsDRItem() {
        return isDRItem;
    }

    public void setIsDRItem(String isDRItem) {
        this.isDRItem = isDRItem;
    }

    public Double getLimited_qty() {
        return limited_qty;
    }

    public void setLimited_qty(Double limited_qty) {
        this.limited_qty = limited_qty;
    }

    public int getMoq() {
        return moq;
    }

    public void setMoq(int moq) {
        this.moq = moq;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
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

    public String getLatest_deptId() {
        return latest_deptId;
    }

    public void setLatest_deptId(String latest_deptId) {
        this.latest_deptId = latest_deptId;
    }

    public String getPq() {
        return pq;
    }

    public void setPq(String pq) {
        this.pq = pq;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public Double getPbPrice() {
        return pbPrice;
    }

    public void setPbPrice(Double pbPrice) {
        this.pbPrice = pbPrice;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public Date getRfs_date() {
        return rfs_date;
    }

    public void setRfs_date(Date rfs_date) {
        this.rfs_date = rfs_date;
    }

    /*public void setPbPrice(double pbPrice) {
        this.pbPrice = pbPrice;
    }*/

    public String getRfs_dateStr() {
        return rfs_dateStr;
    }

    public void setRfs_dateStr(String rfs_dateStr) {
        this.rfs_dateStr = rfs_dateStr;
    }

}
