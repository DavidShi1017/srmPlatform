package com.jingtong.platform.cusInventroy.dao;

import java.util.List;

import com.jingtong.platform.cusInventroy.pojo.CusInventroy;
import com.jingtong.platform.ecgroup.pojo.GroupInfo;
import com.jingtong.platform.endCustomer.pojo.EndCustomer;
import com.jingtong.platform.pos.pojo.Pos;
import com.jingtong.platform.product.pojo.Product;

/**
 * @author cl
 * @createDate 2016-6-16
 * 
 */
public interface ICusInventroyDao {
	/**
	 * ��ѯCusInventroy��Ϣ
	 * 
	 * @return
	 */
	public List<CusInventroy> searchCusInventroyList(CusInventroy c);

	/**
	 * ��ѯCusInventroy��Ϣ������
	 * 
	 * @return
	 */
	public int getCusInventroyListCount(CusInventroy c);
	
	public CusInventroy getCusInventroyById(CusInventroy c);
	
	
	
	public long createCusInventroy(CusInventroy c);
	public int updateCusInventroy(CusInventroy c);//����ID���ϴ�ʱ
	public int updateCusInventroyStatus(CusInventroy c);//����FileID���ϴ�ʱ,�޸�status_num
	public int updateCusInventroyByFileId(CusInventroy c);//����fileID,EDI���ʱ
	
	
	public List<CusInventroy> searchCusInventroyListNoPage(CusInventroy c);
	
	 public int searchProductCount(CusInventroy c);
	 public int searchCustomerCount(CusInventroy c);
	 public String  searchCustomerName(CusInventroy c);
	 public CusInventroy getDistiName(CusInventroy c);
	 
	 //��ȡ�ļ�ID
	 public Long getFileId();
	 
	 //���EDI״̬Ϊ1
	 public List<CusInventroy> searchCusInventroyListForEDI(CusInventroy c);
	 public int getCusInventroyListCountForEDI(CusInventroy c);
	 
	 //��ȡstatus_num
	 public int getCusInventroyDetailListCount(CusInventroy c);
	 public int getCusInventroyDetailListCountForError(CusInventroy c);
	 
	 //�����鿴ҳ��
	 public List<CusInventroy> searchCusInventroyListById(CusInventroy c);
	 public int getCusInventroyListCountById(CusInventroy c);
	 //��������
	 public List<CusInventroy> searchCusInventroyListByIdForOne(CusInventroy c);
	
	 
	 //edi���
	public int searchCusInventroyFileIdCount(CusInventroy c);
	public List<Integer> searchCusInventroyFileId(CusInventroy c);
	//����״̬Ϊ9/3/0
	public int resetCusInventroy(CusInventroy c);
	public int approvedCusInventroy(CusInventroy c);
	public int rejectCusInventroy(CusInventroy c);
	
	//����
	public List<CusInventroy> searchCusInventroyListForB(CusInventroy c);
	public int getCusInventroyListCountForB(CusInventroy c);
	public List<CusInventroy> searchCusInventroyListForBAll(CusInventroy c);
	public void updateFrequencyMarkCusInventroy(CusInventroy c) throws Exception ;
	
	public CusInventroy getInvByfileId(CusInventroy c) ;

	Product getProductInfo(CusInventroy c);
}
