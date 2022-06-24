package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.pojo.CrmOrderItem;
import com.jingtong.platform.sap.pojo.CrmOrderToSap;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.service.CrmOrderToSAPService;
import com.jingtong.platform.sap.service.SAPService;
import com.jingtong.platform.sap.service.impl.CrmOrderToSAPServiceImpl;



public class CrmOrderToSAPAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708360048296344402L;

	private CrmOrderToSAPService crmOrderService;
 	private String crmOrderId;//������
 	private String ids; 
 	private SAPService sapService;
 	
	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCrmOrderId() {
		return crmOrderId;
	}

	public void setCrmOrderId(String crmOrderId) {
		this.crmOrderId = crmOrderId;
	}

	public CrmOrderToSAPService getCrmOrderService() {
		return crmOrderService;
	}
    
	public void setCrmOrderService(CrmOrderToSAPService crmOrderService) {
		this.crmOrderService = crmOrderService;
	}
    
	public void judgeCrmOrderToSAP(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=GBK");
	    PrintWriter pw = null;
	    String message="0;";
	    int counts=0;
		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		order.setIds(ids.split(","));
		List<CrmOrderItem> resultItem=crmOrderService.getCrmOrderItems(order);
		for(CrmOrderItem ci:resultItem){
			if(ci.getCreditId()==null||ci.getCreditId().equals("")||ci.getWerks()==null||ci.getWerks().equals("") ||ci.getLgort()==null||ci.getLgort().equals("")){
				message+=ci.getMatnr()+"�ֿ⡢��λ�����ô��ڿ�ֵ!�Ƿ�ȷ��? </br>";
				counts+=1;
				//break; 
			}
			ProductStock ps = this.sapService.getMaterialInventoryFromSap(ci.getMatnr(),ci.getWerks(),ci.getLgort()); 
			if(ps==null || ps.getStocknum()==0){
				message+=ci.getMatnr()+"SAP���Ϊ���Ƿ�ȷ��? </br>";
				counts+=1;
				//break; 
		  	}else{
		  		BigDecimal src = (new BigDecimal(ci.getKwmeng().toString())).setScale(4,BigDecimal.ROUND_HALF_UP);
				BigDecimal dst = (new BigDecimal(ps.getStocknum())).setScale(4,BigDecimal.ROUND_HALF_UP);
				int bd=src.compareTo(dst);
				if(bd>0){
					message+=ci.getMatnr()+"SAP��治���Ƿ�ȷ��? </br>";
					counts+=1;
					//break; 
				}
		  	}
		}
		//��ȡSAP���
		
		
		if(counts==0){
			message="1;";//û�д���
		}
		try {
		pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   pw.write(message+"");
	   pw.close();
	}
	public void getCrmOrderToSAP(){
		
	   HttpServletResponse response = ServletActionContext.getResponse();
	   response.setContentType("text/html; charset=GBK");
       PrintWriter pw = null;
	
		String message="";
		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		List<CrmOrderToSap> orderList =crmOrderService.getCrmOrderTotal(order);
		order=orderList.get(0); 
		order.setIds(ids.split(","));
  		List<CrmOrderItem> items=crmOrderService.getCrmOrderItems(order);
 		if(null !=items && items.size()>0){
 			if("2".equals(order.getOrderType())){
 				   order.setSapOrderType(null);
 				   items=crmOrderService.getCrmOrderItems(order);
 	  			   if(null !=items &&items.size()>0){
	  				    order.setAuart("ZOR3");
 	 	                order.setSpart("01");

	 	 	 			for(int i=0;i<items.size();i++){
		 	 				if(items.get(i).getOutPrice()==0){
		 	 	 				items.get(i).setPstyv("TANN");
		 	  				}else{
		 	 	 				items.get(i).setPstyv("");
		 	   				}
		 	  			}
	 	  	 	 	    message=message+crmOrderService.crmOderToSap(order, items);
 	  		        }
 	 	 			
 			}else{
 				
 				
 				order.setSapOrderType("1");
 				//TANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){
 	  	 			order.setAuart("ZFD2");
 	  				for(int i=0;i<items.size();i++){
 	 					items.get(i).setPstyv("KLN2");
 	 				}
 	 				message=crmOrderService.crmOderToSap(order, items);
 	 	 		}
 	 			
 	 			order.setSapOrderType("0");
 				//TANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){

 	  	 			String vtweg =order.getVtweg();
 	 	 	 		if("10".equals(vtweg)){
 	 	 	 			
 	 	  	 			order.setAuart("ZOR1");
 	  	 	 			
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("TANN");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	  			}
 	 	 	 		}else{
 	 	  	 			order.setAuart("ZOR6");
 	 	                order.setSpart("00");
 	  	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("ZNN1");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	 			}
 	 	 	 		}
 	  	 	 	    message=message+crmOrderService.crmOderToSap(order, items) ;
 	  	 		}
 	 			
 	  			
 	  			order.setSapOrderType("2");
 				//ϴ�ӹ�������
 				items=crmOrderService.getCrmOrderItems(order);
 	  			if(null !=items &&items.size()>0){
 	  	 			order.setAuart("ZTS1");
 	                order.setSpart("02");
 	  	 			String vtweg =order.getVtweg();
 	 	 	 		if("10".equals(vtweg)){
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("TANN");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	  			}
 	 	 	 		}else{
 	 	 	 			for(int i=0;i<items.size();i++){
 	 	 	 				if(items.get(i).getOutPrice()==0){
 	 	 	 	 				items.get(i).setPstyv("ZNN1");
 	 	 	  				}else{
 	 	 	 	 				items.get(i).setPstyv("");
 	 	 	   				}
 	 	 	 			}
 	 	 	 		}
 	 				message=message+crmOrderService.crmOderToSap(order, items) ;
 	 	 		}
 	 			 
 			}
 			
 			order.setSapOrderType(null);
 			order.setIds(null);
   	  		List<CrmOrderItem> resultItem=crmOrderService.getCrmOrderItems(order);
            if(null !=resultItem && resultItem.size()>0){
            	order.setStatus("5");
            	crmOrderService.updateOrderStatus(order);
            	message="1||"+message;
            }else{
            	order.setStatus("10");
            	crmOrderService.updateOrderStatus(order);
            	message="2||"+message;
            }
  			
 			
 		}else{
 			message="0||û����Ҫ�ύ�����ݣ����ʵ!";
 		}
 		
		
 		
 		
 		
 		
 	  
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   pw.write(message+"");
	   pw.close();
 	    
 	}
	
	public String crmOrderSoDelete(){
		this.setFailMessage("");
		this.setSuccessMessage("");
   		CrmOrderToSap order= new CrmOrderToSap();
		order.setId(crmOrderId);
		order.setSapOrderType(null);//(orderType)
		order.setIds(ids.split(","));
		List<CrmOrderItem> resultItem=crmOrderService.getDeleteCrmOrder(order);
		String msg ="";
		for(CrmOrderItem ci:resultItem){
			msg=msg+crmOrderService.crmOrderSoDelete(ci);
 		}
		this.setSuccessMessage(msg);
  		return RESULT_MESSAGE;
	}
	
	
	
	
	
	public String getCrmOrderToSAPTest(){
		

		CrmOrderToSap order = new CrmOrderToSap();
		/*order.setId("38824");//CRM_ID 38823
		order.setAuart("ZOR1"); //����ƾ֤����
		order.setVkorg("2000"); //������֯
		order.setVtweg("10");   //��������
		order.setSpart("00");   //��Ʒ��
		order.setVkbur("1001"); //���۲��� �����г���
		order.setBzirk("100001"); //���۵��� ����
		order.setAudat("20150709");  //ƾ֤���� 
		order.setBstkd("HN2014120215"); //�ͻ��ɹ������� HN2014120215
		order.setKunag("1000447");  //�۴﷽
		order.setKunnr("1000447");  //�ʹ﷽ 1003886
		order.setKunrg("1000447");  //���
		order.setKunre("1000447");  //��Ʊ��
		order.setAbrvw("1");  //ʹ�ñ�ʾ
		order.setPernr("10004");   //���۹�Ա
		order.setPrsdt("20150709");   //��������
		order.setText("�ͻ��ֿ⳵�����Ƹ߶�3m");   //��ע

		
		CrmOrderItem item = new CrmOrderItem();
 		item.setPosnr("1"); //��Ŀ��
		item.setMatnr("70000000126"); //���Ϻ�
		item.setKwmeng("10"); //����
		item.setVrkme("PC");  //��λ
		item.setCharg("");  //����
		item.setLgort("0004");  //����
		item.setWerks("1000");  //����
		item.setFather("");  //����Ŀ
		item.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1
		item.setPrice("-12");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item.setCreditId("2132");  //CRM���ú�
		item.setText("��Ҫ���µ���������");  //CRM����Ŀ�ĵ�
		item.setPromotionId("132");  //�����󷽰���
		item.setPromoDetailId("232");  //�����ӷ�����

		CrmOrderItem item2 = new CrmOrderItem();
 		item2.setPosnr("20"); //��Ŀ��
		item2.setMatnr("10101047900"); //���Ϻ�
		item2.setKwmeng("2"); //����
		item2.setVrkme("CAR");  //��λ
		item2.setCharg("");  //����
		item2.setLgort("1005");  //����
		item2.setWerks("1010");  //����
		item2.setFather("");  //����Ŀ
		item2.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1
		item2.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item2.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item2.setCreditId("2132");  //CRM���ú�
		item2.setText("");  //CRM����Ŀ�ĵ�
		item2.setPromotionId("");  //�����󷽰���
		item2.setPromoDetailId("");  //�����ӷ�����
*/

	/*	order.setId("1200");//CRM_ID 38824
		order.setAuart("ZOR1"); //����ƾ֤����
		order.setVkorg("2000"); //������֯
		order.setVtweg("10");   //��������
		order.setSpart("00");   //��Ʒ��
		order.setVkbur("1001"); //���۲��� �����г���
		order.setBzirk("100001"); //���۵��� ����
		order.setAudat("20150709");  //ƾ֤���� 
		order.setBstkd("HN2014120215"); //�ͻ��ɹ������� HN2014120215
		order.setKunag("1000447");  //�۴﷽
		order.setKunnr("1003886");  //�ʹ﷽ 1003886
		order.setKunrg("1000447");  //���
		order.setKunre("1000447");  //��Ʊ��
		order.setAbrvw("1");  //ʹ�ñ�ʾ
		order.setPernr("10004");   //���۹�Ա
		order.setPrsdt("20150709");   //��������
		order.setText("�ͻ��ֿ⳵�����Ƹ߶�3m");   //��ע

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //��Ŀ��
		item.setMatnr("10101000115"); //���Ϻ�
		item.setKwmeng("4"); //����
		item.setVrkme("CAR");  //��λ
		item.setCharg("");  //����
		item.setWerks("1000");  //����
		item.setLgort("0021");  //����		
		item.setFather("");  //����Ŀ
		item.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1
		item.setPrice("-12");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item.setCreditId("2132");  //CRM���ú�
		item.setText("��Ҫ���µ���������");  //CRM����Ŀ�ĵ�
		item.setPromotionId("132");  //�����󷽰���
		item.setPromoDetailId("232");  //�����ӷ�����

		CrmOrderItem item2 = new CrmOrderItem();
		item2.setPosnr("20"); //��Ŀ��
		item2.setMatnr("10101047900"); //���Ϻ�
		item2.setKwmeng("2"); //����
		item2.setVrkme("CAR");  //��λ
		item2.setCharg("");  //����
		item2.setWerks("1000");  //����
		item2.setLgort("0021");  //����
		item2.setFather("");  //����Ŀ
		item2.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1
		item2.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item2.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item2.setCreditId("2132");  //CRM���ú�
		item2.setText("");  //CRM����Ŀ�ĵ�
		item2.setPromotionId("");  //�����󷽰���
		item2.setPromoDetailId("");  //�����ӷ�����

		CrmOrderItem item3 = new CrmOrderItem();

		item3.setPosnr("30"); //��Ŀ��
		item3.setMatnr("70000000295"); //���Ϻ�
		item3.setKwmeng("1"); //����
		item3.setVrkme("PC");  //��λ
		item3.setCharg("");  //����
		item3.setWerks("2000");  //����
		item3.setLgort("9050");  //����
		item3.setFather("");  //����Ŀ
		item3.setPstyv("TANN");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1
		item3.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item3.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item3.setCreditId("");  //CRM���ú�
		item3.setText("");  //CRM����Ŀ�ĵ�
		item3.setPromotionId("");  //�����󷽰���
		item3.setPromoDetailId("");  //�����ӷ�����
*/
		
	/*	order.setId("20033");//CRM_ID 20033
		order.setAuart("ZFD2"); //����ƾ֤����
		order.setVkorg("2000"); //������֯
		order.setVtweg("10");   //��������
		order.setSpart("00");   //��Ʒ��
		order.setVkbur("1001"); //���۲��� �����г���
		order.setBzirk("100001"); //���۵��� ����
		order.setAudat("20150710");  //ƾ֤���� 
		order.setBstkd("HN39293"); //�ͻ��ɹ������� HN39293
		order.setKunag("1000447");  //�۴﷽
		order.setKunnr("1003886");  //�ʹ﷽
		order.setKunrg("1000447");  //���
		order.setKunre("1000447");  //��Ʊ��
		order.setAbrvw("3");  //ʹ�ñ�ʾ  1��Ʊ  2��Ʊ 3����Ʊ
		order.setPernr("10004");   //���۹�Ա
		order.setPrsdt("20150710");   //��������
		order.setText("��ֵ��Ʒ�붩��23333һͬ����");   //��ע

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //��Ŀ��
		item.setMatnr("10202016900"); //���Ϻ�
		item.setKwmeng("30000"); //����
		item.setVrkme("PC");  //��λ
		item.setCharg("");  //����
		item.setWerks("2000");  //����
		item.setLgort("9010");  //����
		item.setFather("");  //����Ŀ
		item.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item.setCreditId("");  //CRM���ú�
		item.setText("��Ҫ���µ���������");  //CRM����Ŀ�ĵ�
		item.setPromotionId("");  //�����󷽰���
		item.setPromoDetailId("");  //�����ӷ�����

		CrmOrderItem item2 = new CrmOrderItem();

		item2.setPosnr("20"); //��Ŀ��
		item2.setMatnr("10201006600"); //���Ϻ�
		item2.setKwmeng("1750"); //����
		item2.setVrkme("PC");  //��λ
		item2.setCharg("");  //����
		item2.setWerks("2000");  //����
		item2.setLgort("9010");  //����
		item2.setFather("");  //����Ŀ
		item2.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item2.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item2.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item2.setCreditId("");  //CRM���ú�
		item2.setText("");  //CRM����Ŀ�ĵ�
		item2.setPromotionId("");  //�����󷽰���
		item2.setPromoDetailId("");  //�����ӷ�����

		CrmOrderItem item3 = new CrmOrderItem();

		item3.setPosnr("30"); //��Ŀ��
		item3.setMatnr("10201009600"); //���Ϻ�
		item3.setKwmeng("1750"); //����
		item3.setVrkme("PC");  //��λ
		item3.setCharg("");  //����
		item3.setWerks("2000");  //����
		item3.setLgort("9010");  //����
		item3.setFather("");  //����Ŀ
		item3.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item3.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item3.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item3.setCreditId("");  //CRM���ú�
		item3.setText("");  //CRM����Ŀ�ĵ�
		item3.setPromotionId("");  //�����󷽰���
		item3.setPromoDetailId("");  //�����ӷ�����

		CrmOrderItem item4 = new CrmOrderItem();

		item4.setPosnr("40"); //��Ŀ��
		item4.setMatnr("10206001000"); //���Ϻ�
		item4.setKwmeng("500"); //����
		item4.setVrkme("PC");  //��λ
		item4.setCharg("");  //����
		item4.setWerks("2000");  //����
		item4.setLgort("");  //����
		item4.setFather("");  //����Ŀ
		item4.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item4.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item4.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item4.setCreditId("");  //CRM���ú�
		item4.setText("");  //CRM����Ŀ�ĵ�
		item4.setPromotionId("");  //�����󷽰���
		item4.setPromoDetailId("");  //�����ӷ�����
		*/
		
		order.setId("200212");//CRM_ID 200212
		order.setAuart("ZTS1"); //����ƾ֤����
		order.setVkorg("2000"); //������֯
		order.setVtweg("10");   //��������
		order.setSpart("00");   //��Ʒ��
		order.setVkbur("1001"); //���۲��� �����г���
		order.setBzirk("100001"); //���۵��� ����
		order.setAudat("20150723");  //ƾ֤���� 
		order.setBstkd("HN3424"); //�ͻ��ɹ������� HN3424
		order.setKunag("1000447");  //�۴﷽
		order.setKunnr("1003886");  //�ʹ﷽
		order.setKunrg("1000447");  //���
		order.setKunre("1000447");  //��Ʊ��
		order.setAbrvw("3");  //ʹ�ñ�ʾ  1��Ʊ  2��Ʊ 3����Ʊ
		order.setPernr("10004");   //���۹�Ա
		order.setPrsdt("20150723");   //��������
		order.setText("");   //��ע

		CrmOrderItem item = new CrmOrderItem();
		item.setPosnr("10"); //��Ŀ��
		item.setMatnr("10900000244"); //���Ϻ�
		item.setKwmeng("2"); //����
		item.setVrkme("CAR");  //��λ
		item.setCharg("");  //����
		item.setWerks("2000");  //����
		item.setLgort("9012");  //����
		item.setFather("");  //����Ŀ
		item.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item.setCreditId("");  //CRM���ú�
		item.setText("��Ҫ���µ���������");  //CRM����Ŀ�ĵ�
		item.setPromotionId("");  //�����󷽰���
		item.setPromoDetailId("");  //�����ӷ�����
		
		CrmOrderItem item2 = new CrmOrderItem();

		item2.setPosnr("20"); //��Ŀ��
		item2.setMatnr("10900000292"); //���Ϻ�
		item2.setKwmeng("10"); //����
		item2.setVrkme("CAR");  //��λ
		item2.setCharg("");  //����
		item2.setWerks("2000");  //����
		item2.setLgort("9012");  //����
		item2.setFather("");  //����Ŀ
		item2.setPstyv("");  //����ƾ֤��Ŀ����  ����ۼ۸�Ϊ��ʱ����ͨ�ͻ�ֵΪTANN,�ն˿ͻ�ֵΪZNN1,��Ϊ��ֵ����ΪKLN2
		item2.setPrice("");  //�ۿۼ� ��Ϊ����Ʒ��ֵΪ����=������-�����
		item2.setWaerk("CNY");  //���� Ĭ�ϴ���CNY
		item2.setCreditId("");  //CRM���ú�
		item2.setText("");  //CRM����Ŀ�ĵ�
		item2.setPromotionId("");  //�����󷽰���
		item2.setPromoDetailId("");  //�����ӷ�����
		List<CrmOrderItem> items = new ArrayList<CrmOrderItem>();
		items.add(item);
		items.add(item2);
       
        String message=crmOrderService.crmOderToSap(order, items);
		
		return message;
		
	}
	
}
