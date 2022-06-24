package com.jingtong.platform.designReg.dao;

import java.util.List;

import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.designReg.pojo.DesignReg;
import com.jingtong.platform.designReg.pojo.DesignRegDetail;
import com.jingtong.platform.designReg.pojo.DesignRegDetailLog;
import com.jingtong.platform.designReg.pojo.DesignWinAudit;
import com.jingtong.platform.designReg.pojo.Dict;
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

public interface IDesignRegDao {
    /**
     * ��ȡ���ע���б���
     * 
     * @param c
     * @return
     */
    public int getDesignRegListCount(DesignReg dr);

    /**
     * ��ȡ���ע����Ϣ�б�
     * 
     * @param c
     * @return
     */
    public List<DesignReg> getDesignRegList(DesignReg dr);

    public List<DesignRegDetail> checkDesignReg(DesignRegDetail drd);

    /**
     * ����ID��ȡ���ע����Ϣ
     * 
     * @param c
     * @return
     */
    public DesignReg getDesignRegById(DesignReg dr);

    /**
     * ���ע����Ϣ����
     * 
     * @param p
     * @return
     */
    public long createDesignReg(DesignReg dr);

    /**
     * �޸����ע����Ϣ
     * 
     * @param o
     * @return
     */
    public int updateDesignReg(DesignReg dr);

    /**
     * ɾ�����ע����Ϣ(�߼�ɾ��)
     * 
     * @param o
     * @return
     */
    public int deleteDesignReg(DesignReg dr);

    /**
     * ��ȡ���ע����ϸ��Ϣ�б�
     * 
     * @param od
     * @return
     */
    public List<DesignRegDetail> getDesignRegDetailList(DesignRegDetail drd);

    /**
     * ���ע����ϸ��Ϣ����
     * 
     * @param od
     * @return
     */
    public long createDesignRegDetail(DesignRegDetail drd, DesignReg dr);

    /**
     * �޸����ע����ϸ��Ϣ
     * 
     * @param od
     * @return
     */
    public int updateDesignRegDetail(DesignRegDetail drd);

    public int updateDesignRegDetailDesignStatus(DesignRegDetail drd);

    /**
     * ɾ�����ע����ϸ��Ϣ(����ɾ��)
     * 
     * @param od
     * @return
     */
    public int deleteDesignRegDetail(DesignRegDetail drd);

    public List<DesignRegDetail> checkDesignRegDetailList(DesignRegDetail drd);

    public int checkDesignRegDetailCount(DesignRegDetail drd);

    public int auditDRD(DesignRegDetail drd);

    /**
     * ��ȡ�Զ����ɵ���
     */
    public String getSystemIdPrc();

    public int setCheck(DesignRegDetail drd);

    public int setDRNum(DesignReg dr);

    public List<DesignRegDetail> outPutDR(DesignRegDetail drd);

    void updateDRState();

    public List<Dict> getDictOfWeen(Dict m);

    public String getDRECCountryOrg(DesignReg dr);

    public List<DesignRegDetail> getDRDbyIds(DesignRegDetail drd);

    public List<CustomerUser> getDRAuditSale(DesignReg dr);

    long createDesignWinAudit(DesignWinAudit designWinAudit);

    public List<DesignRegDetail> getRoleUserByMenuId(DesignRegDetail drd);

    public DesignRegDetail getDRDbyId(DesignRegDetail drd);

    public int checkDRExpireByDrNum(DesignRegDetail drd);

    public List<DesignRegDetailLog> getDrLogList(DesignRegDetail drd);

    public int getDrLogListCount(DesignRegDetail drd);

    public Long createDesignRegDetailLog(DesignRegDetailLog log);

    public List<Pos> getCustomerPos(Pos pos);

    public Product getProductByMaterialIdOrName(Product p);
    
    public DesignRegDetail getDRTypeByItmeValue(DesignRegDetail drd);
    
    public int updateDesignRegDetailDrType(DesignRegDetail drd);
    
    public DesignReg getDesignRegByDesignRegDetail(DesignRegDetail drd);
    
    public EndCustomer getEndCustomerByCode(EndCustomer ec);

    public List<Disti_branch> getDistiBranchList(Disti_branch db);
    
    public int updateDesignRegDetailRemark(DesignRegDetail drd);

    public CmsTbDict getCmsTbDict(CmsTbDict direct);
    
    public List<Disti_branch> getDistiAliasList(Disti_branch db);
    
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db);
}
