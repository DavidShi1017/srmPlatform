package com.jingtong.platform.sap.pojo;

import java.util.Date;

import com.jingtong.platform.base.pojo.SearchInfo;

public class Material extends SearchInfo {
    /**
     * 
     */
    private static final long serialVersionUID = -2137619553590110727L;

    private long id;
    private String material_id; // ���Ϻ�
    private String material_exp;// �������������ı���
    private String material_type; // ������������()
    private String remark; // ����
    private String base_unit; // ����������λ
    private String sale_unit; // ���۵�λ
    private String unitRatio; // ��λ����
    private String state; // ���������������״̬
    private String isLocked; // ���ϱ�����
    private String moq; // �Ի�׼������λ��������С������
    private String material_name; // Book Part
//	private String limited_qty;	//	Sample Limited QTY
    private String cost; // �ɱ�
    private String factory; // ����
    private String wm_Unit; // WM��λ(PQ)
    private String numerator; // ����
    private String denominator; // ��ĸ
    private String isDeleted; // ɾ����ʶ

    private String isOrderItem;// �ܷ��µ���Order Item����portal excel���룩
    private String isQuoteItem;// �ܷ񱨼ۣ�Quote Item����portal excel���룩
    private String isDRItem;// �ܷ���ע��DR Item����portal excel���룩

    private String sync_userId;
    private Date sync_time;
    private Date rfs_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getMaterial_exp() {
        return material_exp;
    }

    public void setMaterial_exp(String material_exp) {
        this.material_exp = material_exp;
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

    public void setSale_unit(String sale_unit) {
        this.sale_unit = sale_unit;
    }

    public String getUnitRatio() {
        return unitRatio;
    }

    public void setUnitRatio(String unitRatio) {
        this.unitRatio = unitRatio;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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

    public String getWm_Unit() {
        return wm_Unit;
    }

    public void setWm_Unit(String wm_Unit) {
        this.wm_Unit = wm_Unit;
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

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getSync_userId() {
        return sync_userId;
    }

    public void setSync_userId(String sync_userId) {
        this.sync_userId = sync_userId;
    }

    public Date getSync_time() {
        return sync_time;
    }

    public void setSync_time(Date sync_time) {
        this.sync_time = sync_time;
    }

    public Date getRfs_date() {
        return rfs_date;
    }

    public void setRfs_date(Date rfs_date) {
        this.rfs_date = rfs_date;
    }

}
