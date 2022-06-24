package com.jingtong.platform.sap.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.sap.pojo.CuspayAccdoc;
import com.jingtong.platform.sap.pojo.NoClearMoney;
import com.jingtong.platform.sap.pojo.ProductStock;
import com.jingtong.platform.sap.pojo.ReceivePay;
import com.jingtong.platform.sap.service.SAPService;



public class SAPAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708360048296344402L;

	private SAPService sapService;
	
	private ReceivePay receivePay;
	

	public SAPService getSapService() {
		return sapService;
	}

	public void setSapService(SAPService sapService) {
		this.sapService = sapService;
	}
	private String matnr;//����
	private String werks;//����
	private String lgort;//��λ
 	
	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getLgort() {
		return lgort;
	}

	public void setLgort(String lgort) {
		this.lgort = lgort;
	}

	/**
	 * ��Ʒ��������
	 * @param punit String	ͳ�Ƶ�λ��ST��CAR(����)
	 * @param pmatnr String	���ϱ���(����)
	 * @param pwerks String	��������(����)
	 * @param plgort String	��λ����
	 * @throws IOException 
	 * @remark ����˳�� getMaterialInventoryFromSap(String pmatnr,String pwerks,String plgort)
	 */
	public void getMaterialInventoryFromSap() throws IOException{
	   HttpServletResponse response = ServletActionContext.getResponse();
	   response.setContentType("text/html; charset=GBK");
  	   ProductStock ps = this.sapService.getMaterialInventoryFromSap(matnr,werks,lgort);
  	   PrintWriter pw = response.getWriter();
  	   if(ps==null || ps.getStocknum()==0){
  		 pw.write("000");
  	   }else{
		   pw.write(ps.getStocknum()+","+ps.getBigmg());
	//	   pw.write("10"+","+"10");
 	   }
	   pw.close();
		
	}
	
	 
	/**
	 * ͬ��sap ��Ʒ������ӿ�
	 */
	public void getProductStockFromSap(){
		try {
			this.sapService.getMaterialInventory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCusPlusFromSap(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.sapService.getCusPlusFromSap(sdf.parse("2015-09-20"), sdf.parse("2015-09-30"), null);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		if(this.sapService.getCusPlusFromSap(null, null, null)==true){
//			return "1";
//		}else{
//			return "0";
//		}
		return "0";
	}
	/**
	 * �Զ������ͻ��ؿ���ƾ֤����
	 * @return
	 */
	public void getCuspayAccdocFromSAP(){
	  /*P_BUKRS	String	��˾����
		P_KUNNR	String	�ͻ�����
		P_DMBTR	double	���
		P_BELNR_HK	String	�ֽ�ƾ֤����
		P_GJAHR_HK	String	�ֽ�ƾ֤������
		P_BUKRS_HK	String	�ֽ�ƾ֤��˾����
		P_USNAM	String	�û���
		P_XREF1	String	�ؿ�����*/
		HttpServletResponse response = ServletActionContext.getResponse();
		// ������Ӧ�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		try{
			CuspayAccdoc doc = new CuspayAccdoc();
			doc.setP_bukrs(this.receivePay.getSaleCompany());
			doc.setP_kunnr(this.receivePay.getKunnr());
			doc.setP_dmbtr(this.receivePay.getReceiveMoney());
			doc.setP_belnr_hk(this.receivePay.getSkpz());
			doc.setP_gjahr_hk(this.receivePay.getAccountYear());
			doc.setP_bukrs_hk(this.receivePay.getSaleCompany());
			doc.setP_usnam("crm�����û�");
			doc.setP_xref1(this.receivePay.getSgType());
			doc = this.sapService.getCuspayAccdocFromSAP(doc);
			if(doc != null && doc.getL_belnr() != null && !doc.getL_belnr().equals("")){
				this.receivePay.setRemark(java.net.URLDecoder.decode(this.receivePay.getRemark(),"UTF-8"));
				this.receivePay.setReceiveId(doc.getL_belnr());//�ؿ�ƾ֤��
				this.receivePay.setStatus("0");
				this.receivePay.setSyMoney(this.receivePay.getReceiveMoney());
				this.receivePay.setCreateUser(this.getUser().getUserId());
				this.receivePay.setCreateDate(new Date());
				this.receivePay.setModifyUser(this.getUser().getUserId());
				this.receivePay.setModifyDate(new Date());
				this.receivePay.setBuzei("002");
//				this.receivePay.setReceiveDate(new SimpleDateFormat("yyyy-mm-dd").format(this.receivePay.getReceiveDate()));
				long i=this.sapService.addReceivePay(this.receivePay);//�����ؿ�
				if(i>0){
					sapService.updateCreditByReceive(receivePay.getKunnr());
				}
				response.getWriter().print("1");
			}else{
				throw new Exception("����SAP�쳣"+doc.getRetmsg());
			}
			System.out.println(doc.toString());
		}catch(Exception e){
			try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ȡδ��ƾ֤ 
	 * @return
	 */
	public void getWeiqingMoneyFromSAP(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String p_bukrs = request.getParameter("p_bukrs");
		String p_gjahr = new SimpleDateFormat("yyyy").format(new Date());//��ʱȥ��ǰ��ȣ�SAP�д��Ļ�ȥsapֵ"2015";
		List<NoClearMoney> noClearMoneyList  = this.sapService.getweiqingMoneyFromSAP(p_bukrs,p_gjahr);
		List<String> clearMoneyList = this.sapService.getClearMoneyList();
		int record = 1;
		String jsonArray = "[";
		for(NoClearMoney noClearMoney:noClearMoneyList){
			int flag = 1;
			for(String s : clearMoneyList){
				if(s!=null&&s.trim().equals(noClearMoney.getBelnr().trim())){
					flag = 0;
					break;
				}
			}
			if(flag==1){
					jsonArray += "{\"bukrs\":"+noClearMoney.getBukrs()+",\"belnr\":\""+noClearMoney.getBelnr()+"\",\"remark\":\""+noClearMoney.getRemark()+"\",\"dmbtr\":\""+noClearMoney.getDmbtr()+"\",\"buzei\":\""+noClearMoney.getBuzei()+"\"},";
				record++;
			}
		}
		if(jsonArray.length()>0){
			jsonArray = jsonArray.substring(0, jsonArray.length()-1);
		}
		jsonArray +="]";
		HttpServletResponse response = ServletActionContext.getResponse();
		// ������Ӧ�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonArray);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�ؿ�ƾ֤
	 * @throws Exception 
	 */
	public void getReceiveCode() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String belnr = request.getParameter("belnr");
		System.out.println(belnr);
		
		
		NoClearMoney noClearMoney = this.sapService.getReceiveCode(belnr);
		HttpServletResponse response = ServletActionContext.getResponse();
		// ������Ӧ�ĸ�ʽ
		response.setContentType("text/html;charset=UTF-8");
		String json = "";
		if(noClearMoney != null){
			json = "{\"id\":"+noClearMoney.getId()+",\"bukrs\":\""+noClearMoney.getBukrs()+"\",\"gjahr\":\""+noClearMoney.getGjahr()+"\",\"belnr\":\""+noClearMoney.getBelnr()+"\",\"remark\":\""+noClearMoney.getRemark()+"\",\"hkont\":\""+noClearMoney.getHkont()+"\",\"budat\":\""+new SimpleDateFormat("yyyy-MM-dd").format(noClearMoney.getBudat())+"\",\"zuonr\":\""+noClearMoney.getZuonr()+"\",\"dmbtr\":"+noClearMoney.getDmbtr()+"}";
		}
		response.getWriter().write(json);
	}
	
	public void getReceivePayFromSap(){
		this.sapService.getReceivePayFromSap();
	}
	//CRM ͬ�����ó�ʼ��ֵ
	public void getInitialCredit(){
		this.sapService.getInitialCredit();
	}
	//SAPƾ֤ͬ���ӿ�
	public void getZSVoucher(){
		this.sapService.getZSVoucher();
	}
	//SAPƾ֤ͬ���ӿ�
		public void getFSVoucher(){
			this.sapService.getFSVoucher();
		}
	public ReceivePay getReceivePay() {
		return receivePay;
	}

	public void setReceivePay(ReceivePay receivePay) {
		this.receivePay = receivePay;
	}
}
