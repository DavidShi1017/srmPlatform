package com.jingtong.platform.pos.service;

import java.util.List;

import com.jingtong.platform.endCustomer.pojo.ECAlias;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.quote.pojo.QuoteDetail;

public interface IPosService {

    /***
     * ����POS����
     * 
     * @param pos
     * @return
     */
    public long createPosInfo(Pos pos);

    /***
     * ��ѯ����
     * 
     * @param pos
     * @return
     */
    public int searchPosListCount(Pos pos);

    /***
     * POS�б�
     * 
     * @param pos
     * @return
     */
    public List<Pos> searchPosList(Pos pos);

    public Pos getquote(Pos pos);

    public List<Pos> getPosDebitNumber(Pos pos);

    public Pos getquoListBydebitNumBook(Pos pos);

    public double getMinPrice(Pos pos);

    public List<Pos> searchPosListByPos(Pos pos);

    public int searchCustomerCount(Pos pos);

    public int searchDRCount(Pos pos);

    public int searchRelationshipCount(Pos pos);

    public int searchProductCount(Pos pos);

    public int searchDistiBranch(Pos pos);

    public int getQuoteInfoCount(Pos pos);

    public int getQuoteDetailCount(Pos pos);

    public int getReQuoteDetailCount(Pos pos);

    public Pos getDistiName(Pos pos);

    public int searchEndCustomerCount(Pos pos);

    public List<ECAlias> searchEcList(Pos pos);
//	public ECAlias searchEcList(Pos pos);

    public int getDictCount(Pos pos);

    public int updatePos(Pos pos);

    public int updatePosTips(Pos pos);

    public List<QuoteDetail> searchQuoteList(Pos pos);

    // ��ȡfile_id

    public long getFileId();

    // ������ѯҳ��
    public int searchPosDetailListCountById(Pos pos);

    public List<Pos> searchPosDetailListById(Pos pos);

    public List<Pos> searchPosDetailListByIdForOne(Pos pos);

    // �༭PCEC
    public int updatePCEC(Pos pos);

    public Pos getPosById(Pos pos);

    // ��ȡstatus_num
    public int searchPosDetailListCount(Pos pos);

    public int searchPosDetailListCountForError(Pos pos);

    // ��ȡfile_id(claim)

    public long getClaimFileId();

    public int searchPosListCountForAll(Pos pos);

    public List<Pos> searchPosListForAll(Pos pos);

    // ���EDI����
    public int searchPosListCountForEDI(Pos pos);

    public List<Pos> searchPosListForEDI(Pos pos);

    public int searchPosFileIdCount(Pos pos);

    public List<Integer> searchPosFileId(Pos pos);

    // EDI������飨����ID��
    public int searchPosListCountByIds(Pos pos);

    public List<Pos> searchPosListByIds(Pos pos);

    // ����״̬Ϊ9
    public int resetPos(Pos pos);

    public int approvePos(Pos pos);

    public int rejectPos(Pos pos);

    public double getPassedQty(Pos pos);
    public List<Pos> getPassedQtys();

    // POS����
    public int searchPosListCountForBb(Pos pos);

    public List<Pos> searchPosListForBb(Pos pos);

    // POS�����������أ�����ҳ��
    public List<Pos> searchPosListForBbAll(Pos pos);

    // Claim����
    public int searchClaimListCountForBb(Pos pos);

    public List<Pos> searchClaimListForBb(Pos pos);

    // Claim�����������أ�����ҳ��
    public List<Pos> searchClaimListForBbAll(Pos pos);

    /**
     * ����ԭʼ���ݱ���
     * 
     * @param pos
     * @return
     */
    public int searchPosTrackingDetailCount(Pos pos);

    public List<Pos> searchPosTrackingDetail(Pos pos);

    public List<Pos> searchPosTrackingDetailNoPage(Pos pos);

    public void checkEDI(Long id) throws Exception;

    public void claimCheckEDI() throws Exception;

    public void claimCheckEDI(Pos pos) throws Exception;

    public Pos getPosByfileId(Pos pos);

    public Pos searchPosByIdForOneCheck(Pos pos);

    List<Pos> searchPosListForPosCheckInvoice(Pos pos);

    public List<Pos> getClaimErrorCodeDetail(Pos pos);

    public List<Pos> searchPosDetailList6q6t(Pos pos);

    public int searchPosDetailListCount6q6t(Pos pos);
    
    public List<Pos> getPosByfileIds(Pos pos);
}
