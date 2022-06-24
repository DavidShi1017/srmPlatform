package com.jingtong.platform.sap.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import com.jingtong.platform.dict.pojo.CmsTbDict;
import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.framework.bo.PropUtil;
import com.jingtong.platform.framework.util.DateUtil;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.SAPDao;
import com.jingtong.platform.sap.dao.ShopDeliveryDao;
import com.jingtong.platform.sap.pojo.Accrual;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.pojo.ShopDelivery;
import com.jingtong.platform.sap.service.ShopDeliveryService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class ShopDeliveryServiceImpl extends BasicService implements ShopDeliveryService {
	private JCoDestination dest;
	private ILogInfoDao logInfoDao;
	private ShopDeliveryDao shopDeliveryDao;
	private SAPDao sapDao;
	 
	 

	public SAPDao getSapDao() {
		return sapDao;
	}

	public void setSapDao(SAPDao sapDao) {
		this.sapDao = sapDao;
	}

	

	public ShopDeliveryDao getShopDeliveryDao() {
		return shopDeliveryDao;
	}

	public void setShopDeliveryDao(ShopDeliveryDao shopDeliveryDao) {
		this.shopDeliveryDao = shopDeliveryDao;
	}

	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}

	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}
	
	@Override
	public boolean autoGetCusDownFromSap() {
		return this.getCusDownFromSap(null, null, null);
	}
	
	

	/**
	 *  
	 * @return
	 */
	@Override
	public boolean getDeliveryFromSap() {
		System.out.println("-----��ʼ���ý������ӿ�----------");
		boolean errorFlag = false;
		try {
			//��������  
			this.connect("SAP");
			dest = JCoDestinationManager.getDestination("SAP");
			dest.ping();
			//���ӽӿ� 
			JCoFunction function = 	dest.getRepository().getFunction("ZCRM_DN_GET");//url
			if (function == null){
				ExceptionLog log = new ExceptionLog();
				log.setInterfaceName("������ͬ��/ZCRM_DN_GET");
				log.setOperateUser("SAP");
				log.setOperateTime(new Date());
				log.setLogDesc("ʧ�ܣ�");
				log.setLogInfo("����SAPʧ��!");
				this.logInfoDao.addLogInfo(log);
				errorFlag=true;
				throw new Exception("����SAPʧ��!"); 
			}
		     //��ȡ�ӿڴ��������
	//		if(punit.trim().length()>0){
	//			function.getImportParameterList().setValue("P_UNIT", punit);
	//		}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		    Date date = new Date();
		    Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(date); 
			//��ȡ�ӿڴ��������
			JCoTable item = function.getTableParameterList().getTable("S_ERDAT");//��������
//	         JCoTable item = function.getTableParameterList().getTable("S_WADAT_IST");//������������
			 item.appendRow(); //�������  
			 //����
			 item.setValue("SIGN", "I");
			 item.setValue("OPTION", "EQ");
			 String dateB = (df.format(new Date()).toString()).replace("-", "");// new Date()Ϊ��ȡ��ǰϵͳʱ��
 			// calendar.add(Calendar.DAY_OF_MONTH, -100); 
			 item.setValue("LOW", dateB);//��λ
			// calendar.add(Calendar.DAY_OF_MONTH, -3);
			 item.setValue("HIGH", dateB);//��λ
	        
		/*	JCoTable svbeln = function.getTableParameterList().getTable("S_VBELN");//��������
            //��ѯһ�����е��ţ�LOW ���ܴ���գ�����յĻ���Ϊ��ѯ��
			svbeln.appendRow(); //�������
			svbeln.setValue("SIGN","I");
			svbeln.setValue("OPTION","EQ");
			svbeln.setValue("LOW","80433602");
			 */
			function.execute(dest); 
			//��ȡ�ӿڽ�������
			JCoTable resultTbl = function.getTableParameterList().getTable("T_OUT"); 
			int rows = resultTbl.getNumRows();
			if(rows>0){
				for(int i=0;i<rows;i++){
					resultTbl.setRow(i);
					ShopDelivery del = new ShopDelivery();
					del.setCrmOrderId(resultTbl.getString("ID"));
					del.setSapOderId(resultTbl.getString("VGBEL"));
					del.setVgpos(resultTbl.getString("VGPOS"));//sap����Ŀ��
					del.setKunnr_ag(resultTbl.getString("KUNAG"));// �۴﷽
					del.setKunnr_we(resultTbl.getString("KUNNR"));// �ʹ﷽
					del.setSendAddress(resultTbl.getString("STRAS"));// �ͻ���ַ
					del.setLinkMan(resultTbl.getString("FULLNAME"));// ��ϵ��(ʲô��ϵ��)
					del.setLinkPhone(resultTbl.getString("TELF1"));// ��ϵ�绰
					del.setBillId("");// ��Ʊƾ֤
					del.setPlanToDate(null);// �ƻ���������
//					del.setPlanLoadDate(PropUtil.getStringToDate(resultTbl.getString("TDDAT")));// �ƻ�װ������
					del.setPlanLoadDate(new SimpleDateFormat("yyyy/MM/dd").parse(resultTbl.getString("TDDAT").replaceAll("-", "/")));// �ƻ�װ������
					del.setRealSendDate(null);// ʵ�ʷ�������
					//CREDIT_ID ---- CRM���ú�
					//OUT_MONEY  ---- �������

					del.setCreditId(resultTbl.getString("CREDIT_ID"));// ���ñ���
					del.setLoadPointId("VSTEL");// װ�˵�
					del.setShipAgent("LIFNR");// ���˴��� 
					del.setVbeln(resultTbl.getString("VBELN"));// ��������
					del.setErdat(resultTbl.getString("ERDAT").replaceAll("-", "/"));
					del.setPosnr(resultTbl.getString("POSNR"));//�к�
					del.setMatnr(resultTbl.getString("MATNR"));
					del.setWerks(resultTbl.getString("WERKS"));//����
					del.setLgort(resultTbl.getString("LGORT"));//����
					//del.setArktx(resultTbl.getString("ARKTX"));//���۶�����Ŀ���ı�
					del.setBrgew(resultTbl.getString("BRGEW"));
					del.setVolum(resultTbl.getString("VOLUM"));
					del.setPrice(resultTbl.getString("OUT_MONEY"));
					del.setLfimg(resultTbl.getString("LFIMG"));//ʵ���ѽ���������λ��
					del.setVrkme(resultTbl.getString("VRKME"));//���۵�λ����λ��
					del.setDele("");
  			        del.setStatus("0");
  			        double lf = Double.parseDouble(del.getLfimg());
  			        if("".equals(del.getCrmOrderId()) || "".equals(del.getCreditId()) || lf==0 ){
  			        	continue;
  			        }
  			        
					int rs = this.shopDeliveryDao.getDeliverys(del);
					if(rs>0){
						 this.shopDeliveryDao.updateDelivery(del);
					}else{
						long deliId =this.shopDeliveryDao.insertDelivery(del);
						 
						
						
						del.setVbeln(deliId+"");
						shopDeliveryDao.updateOrderPrc(del);
 						
					}
				}
				
				List<Accrual> creDelList =new ArrayList<Accrual>();
				creDelList=shopDeliveryDao.getCreditByOrder();
				if(null !=creDelList && creDelList.size()>0){
					for(int j=0;j<creDelList.size();j++){
						Accrual acc =new Accrual();
	 					acc =creDelList.get(j);
	 					if(null==acc.getRepayMentDate()||"".equals(acc.getRepayMentDate())){
 	 						acc.setRepayMentDate(DateUtil.addDays(acc.getUseDate(),acc.getRepaymentDays()));
	 					}
	 					shopDeliveryDao.insetAccrual(acc);
 					}
					shopDeliveryDao.updateAccrual();
					errorFlag = true;
 				}
				
 			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLog log = new ExceptionLog();
			log.setInterfaceName("������ͬ��/Z_RFC_GET_SHIPPING_LIST");
			log.setOperateUser("SAP");
			log.setOperateTime(new Date());
			log.setLogDesc("ʧ�ܣ�");
			log.setLogInfo(e.getMessage());
			try {
				this.logInfoDao.addLogInfo(log);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			errorFlag=false;
		}
		 return errorFlag;
	}

	
	 
	
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//�������ڸ�ʽ
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
//        calendar.add(Calendar.DAY_OF_MONTH, -2);  
//        System.out.println("==="+df.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 0);  
        System.out.println("==="+df.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_MONTH, 3);  
        System.out.println("==="+df.format(calendar.getTime()));
        
        ShopDeliveryServiceImpl p = new ShopDeliveryServiceImpl();
        p.getDeliveryFromSap();
	}

	@Override
	public void updateinfoPro(Map promap) {
		this.shopDeliveryDao.updateinfoPro(promap);
	}

	@Override
	public boolean getCusDownFromSap(Date begda, Date endda, String kunnr) {
		// TODO Auto-generated method stub
		return false;
	} 
}
