package com.jingtong.platform.quote.dao;

import java.util.List;

import com.jingtong.platform.country.pojo.SaleCountry;
import com.jingtong.platform.customer.pojo.CustomerUser;
import com.jingtong.platform.customer.pojo.Disti_branch;
import com.jingtong.platform.quote.pojo.Quote;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public interface IQuoteDao {
    /**
     * ��ȡ���۵��б���
     * 
     * @param c
     * @return
     */
    public int getQuoteListCount(Quote o);

    /**
     * ��ȡ���۵���Ϣ�б�
     * 
     * @param c
     * @return
     */
    public List<Quote> getQuoteList(Quote o);

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

    /**
     * ����ID��ȡ���۵���Ϣ
     * 
     * @param c
     * @return
     */
    public Quote getQuoteById(Quote o);

    /**
     * ���۵���Ϣ����
     * 
     * @param p
     * @return
     */
    public long createQuote(Quote o);

    /**
     * �޸ı��۵���Ϣ
     * 
     * @param o
     * @return
     */
    public int updateQuote(Quote o);

    /**
     * ɾ�����۵���Ϣ(�߼�ɾ��)
     * 
     * @param o
     * @return
     */
    public int deleteQuote(Quote o);

    public int auditQuote(Quote o);

    /**
     * ��ȡ���۵���ϸ��Ϣ�б�
     * 
     * @param od
     * @return
     */
    public List<QuoteDetail> getQuoteDetailList(QuoteDetail od);

    /**
     * ���۵���ϸ��Ϣ����
     * 
     * @param od
     * @return
     */
    public long createQuoteDetail(QuoteDetail od);

    /**
     * �޸ı��۵���ϸ��Ϣ
     * 
     * @param od
     * @return
     */
    public int updateQuoteDetail(QuoteDetail od);

    /**
     * ɾ�����۵���ϸ��Ϣ(����ɾ��)
     * 
     * @param od
     * @return
     */
    public int deleteQuoteDetail(QuoteDetail od);

    /**
     * ��ȡ�Զ����ɵ���
     */
    public String getSystemIdPrc();

    public int getAuditQuoteListCount(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteList(QuoteDetail qd);

    int changeQuoteDetailState(QuoteDetail od);

    public int setQuoteCode(Quote o);

    public long createQuoteLog(QuoteDetail od);

    public List<QuoteDetail> getQuoteLogList(QuoteDetail ed);

    public List<QuoteDetail> outPutQuote(QuoteDetail e);

    public List<CustomerUser> getAuditors(CustomerUser cusUser);

    public int getOutPortQuoteListCount(QuoteDetail qd);

    public List<QuoteDetail> getOutPortQuoteList(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteListNoPage(QuoteDetail qd);

    public String getQuotePCCountryOrg(Quote q);

    public int agreeQuoteDetail(QuoteDetail qd);

    public List<QuoteDetail> checkQuote(QuoteDetail qd);

    public int updateRemark(QuoteDetail qd);

    public int updatePCid(Quote e);

    public int updateECid(Quote e);

    public List<CustomerUser> getQuoteAuditSale(Quote o);

    public Double getReginalMin(QuoteDetail qd);
    
    public List<QuoteDetail> getReginalMins();

    public Double getCMM(QuoteDetail qd);
    
    public List<QuoteDetail> getCMMs();

    public int getOutQuotePriceCount(QuoteDetail qd);

    public List<QuoteDetail> getOutQuotePrice(QuoteDetail qd);

    public int updateDebitDate(QuoteDetail qd);

    public int updateQuoteForForward(QuoteDetail qd);

    public int getAuditQuoteListCountForBL(QuoteDetail qd);

    public List<QuoteDetail> getAuditQuoteListForBL(QuoteDetail qd);

    public QuoteDetail getQuoteDetailById(QuoteDetail qd);

    public List<SaleCountry> searchSaleCountry(SaleCountry c);
    
    public List<Disti_branch> getDistiAliasList(Disti_branch db);
    
    public List<Disti_branch> getDistiBranchAliasList(Disti_branch db);
}
