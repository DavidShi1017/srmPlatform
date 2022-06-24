package com.jingtong.platform.quote.service;

import java.util.List;

import com.jingtong.platform.base.pojo.BooleanResult;
import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public interface IQuoteService {
    /**
     * ��ȡ�����б���
     * 
     * @param o
     * @return
     */
    public int getQuoteListCount(Quote o);

    /**
     * ��ȡ������Ϣ�б�
     * 
     * @param o
     * @return
     */
    public List<Quote> getQuoteList(Quote o);

    /**
     * ����ID��ȡ������Ϣ
     * 
     * @param o
     * @return
     */
    public Quote getQuoteById(Quote o);

    /**
     * ������Ϣ����
     * 
     * @param o
     * @return
     */
    public BooleanResult createQuote(Quote o, List<QuoteDetail> odList);

    /**
     * �޸ı�����Ϣ
     * 
     * @param o
     * @return
     */
    public BooleanResult updateQuote(Quote o, List<QuoteDetail> odList, QuoteDetail od);

    /**
     * ɾ��������Ϣ(�߼�ɾ��)
     * 
     * @param o
     * @return
     */
    public int deleteQuote(Quote o);

    /**
     * ��ȡ������ϸ��Ϣ�б�
     * 
     * @param od
     * @return
     */
    public List<QuoteDetail> getQuoteDetailList(QuoteDetail od);

    /**
     * ������ϸ��Ϣ����
     * 
     * @param od
     * @return
     */
    public long createQuoteDetail(QuoteDetail od);

    /**
     * �޸ı�����ϸ��Ϣ
     * 
     * @param od
     * @return
     */
    public int updateQuoteDetail(QuoteDetail od);

    /**
     * ɾ��������ϸ��Ϣ(����ɾ��)
     * 
     * @param od
     * @return
     */
    public int deleteQuoteDetail(QuoteDetail od);

    /**
     * ��ȡ�Զ����ɵ���
     */
    public String getSystemIdPrc();

    /**
     * ��ȡ����˱��۵��б���
     * 
     * @param c
     * @return
     */
    public int getEDIQuoteCount(Quote o);

    /**
     * ��ȡ����˱��۵���Ϣ�б�
     * 
     * @param c
     * @return
     */
    public List<Quote> getEDIQuote(Quote o);

    public int getAuditQuoteListCount(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteList(QuoteDetail qd);

    public int changeQuoteDetailState(QuoteDetail e);

    BooleanResult auditQuoteDetail(Quote e, List<QuoteDetail> edList);

    public int auditQuote(Quote e);

    public List<QuoteDetail> getQuoteLogList(QuoteDetail qd);

    public List<QuoteDetail> outPutQuote(QuoteDetail qd);

    public List<CustomerUser> getAuditors(CustomerUser cusUser);

    public List<QuoteDetail> getOutPortQuoteList(QuoteDetail e);

    public int getOutPortQuoteListCount(QuoteDetail e);

    public List<QuoteDetail> getAuditQuoteListNoPage(QuoteDetail e);

    public String getQuotePCCountryOrg(Quote q);

    public int agreeQuoteDetail(QuoteDetail qd);

    public long createQuoteLog(QuoteDetail qd);

    public List<QuoteDetail> checkQuote(QuoteDetail qd);

    public int updateRemark(QuoteDetail qd);

    public int updateECid(Quote e);

    public int updatePCid(Quote e);

    public List<CustomerUser> getQuoteAuditSale(Quote q);

    public Double getReginalMin(QuoteDetail qd);
    
    public List<QuoteDetail> getReginalMins();

    public List<QuoteDetail> getCMMs();
    
    public Double getCMM(QuoteDetail qd);

    public int getOutQuotePriceCount(QuoteDetail qd);

    public List<QuoteDetail> getOutQuotePrice(QuoteDetail qd);

    public int updateDebitDate(QuoteDetail qd);

    public int updateUserForQuoteForward(QuoteDetail qd);

    public int getAuditQuoteListCountForBL(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteListForBL(QuoteDetail qd);

    public QuoteDetail getQuoteDetailById(QuoteDetail qd);
    
    public List<SaleCountry> searchSaleCountry(String userId);
    
    public Disti_branch getDistAlias(Disti_branch disti);
    
    public Disti_branch getDistBranchAlias(Disti_branch distibranch);

}
