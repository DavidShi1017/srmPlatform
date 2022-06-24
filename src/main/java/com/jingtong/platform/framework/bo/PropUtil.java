package com.jingtong.platform.framework.bo;



import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.text.ParseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
import org.json.JSONException;

//��������db.properties������
public class PropUtil extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2680965078884381639L;
	public PropUtil(String path){
		// ��ȡ�����ļ�
		InputStream is = 
			this.getClass().getResourceAsStream(path);
		// �������ļ������ݶ�ȡ���ڴ���,�����Ժ�ֱ�ӵ���
		try {
			super.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
/**
 * ��sap���ַ�������תΪJava ��Date����
 * �����ֹ����ʱ���ʽ��12.12.12��12/12/12;20141212;201412(�������Ϊ2014-12-01)��2014.12.12��2014/12/12;2014.12;2014/12;2014-12-12��2014-12;(12.12��12-12��1212�������������)
 * �������յ�ʱ����Ϊ���µ�һ�졢���º��յ���Ϊ����ĵ�һ��
 * ���������������ȫ����null
 */
	public static Date getStringToDate(String str){
//		try {
			SimpleDateFormat  bartDateFormat =   new SimpleDateFormat("yyyy/MM/dd"); 
			if(str==null||"null".equals(str)){
				return null;
			}
			try {
				java.util.Date  date  =  bartDateFormat.parse(str); 
			    java.sql.Date  sqlDate  =  new java.sql.Date(date.getTime());
				 return sqlDate;
			} catch (Exception e) {
				try {
					String d = str.replace("-", "/");
					java.util.Date  date  =  bartDateFormat.parse(d); 
				    java.sql.Date  sqlDate  =  new java.sql.Date(date.getTime());
					 return sqlDate;
				} catch (Exception e2) {
					return null;
				} 
			}
//			str=str.trim();
//			if(str.length()<1||str.length()>10){
//				return null;
//			}else{
//				String[] d = new String[3];
//				/**
//				 * ���20141212��201412
//				 * ������Ȳ���8λ/6λ�������� 
//				 */
//				if(!(str.indexOf(".")>0||str.indexOf("/")>0)){
//					if(str.length()==8){
//						d[0] = str.substring(0, 4);
//						d[1] = str.substring(4, 6);
//						d[2] = str.substring(6, 8);
//					}else if(str.length()==6){
//						d[0] = str.substring(0, 4);
//						d[1] = str.substring(4, 6);
//						d[2] = "01";
//					}
//				}else{
//				/**
//				 * ���
//				 * 12.12.12��12/12/12;2014.12.12��2014/12/12;2014.12;2014/12;2014-12-12��2014-12;
//				 * ��תΪͳһ��ʽ�ٴ���
//				 * ͳһ��ʽ��12-12-12��12-12��2014-12-12��2014-12
//				 */
//				str = str.trim().replace(".", "-");
//				str = str.trim().replace("/", "-");
//				String[] s = str.split("-");
//				
//				if(s.length>3){
//					return null;
//				}else if(s.length==3){
//					int y = Integer.parseInt(s[0]);
//					if((1000>y)){
//						d[0] = "20"+y;
//						d[1] = s[1];
//						d[2] = s[2];
//					}else{
//						d[0] = s[0];
//						d[1] = s[1];
//						d[2] = s[2];
//					}
//				}else if(s.length==2){//ʱ��2014.1 ���յĲ�Ϊ01
//					int y = Integer.parseInt(s[0]);
//					if(!(1000>y)){
//						d[0] = "20"+y; 
//					}else{
//						d[0] = s[0];
//					}
//					d[1] = s[1];
//					d[2] = "01";
//				}else if(s.length==1){//ʱ�����¡��յĲ�01-01
//					int y = Integer.parseInt(s[0]);
//					if(!(1000>y)){
//						d[0] = "20"+y; 
//					}else{
//						d[0] = s[0];
//					}
//					d[1] = "01";
//					d[2] = "01";
//				 }
//				}
//				int m = 0;
//				try {
//					 m = Integer.parseInt(d[1]);
//				} catch (Exception e) {
//					 return null;
//				}
//				if(m<10){
//					str = d[0]+"-0"+m+"-"+d[2];
//				}
//				//str="2014/01/01";
//				//SimpleDateFormat  bartDateFormat =   new SimpleDateFormat("yyyy/MM/dd"); 
//				java.util.Date  date  =  bartDateFormat.parse(str); 
//			    java.sql.Date  sqlDate  =  new java.sql.Date(date.getTime());
//				 return sqlDate;
//			} 
//		} catch (ParseException e) { 
//			return null; 
//		}
	}
	public static void main(String[] args) throws JSONException {
		//System.out.println(PropUtil.getStringToDate("20140101"));
		System.out.println(toList());
		 
	}
	/**
	 * ��web�������ַ���ת�루�������룩
	 */
	public static String str(String str) throws UnsupportedEncodingException{
		 return new String(str.getBytes("8859_1"),"GB2312");
	}
	
	   
    public static String createTableHtml() throws JSONException{
    	StringBuffer html = new StringBuffer();
    	html.append("<table><tr><td style='width:100px;text-align:center;'>�ŵ�����</td><td style='width:100px;text-align:center;'>����ʱ��</td><td style='width:100px;text-align:center;'>��ͷ��</td><td colspan='10' style='text-align:center;'>ִ������</td><td style='width:200px;text-align:center;' colspan='2'>����</td><td style='width:100px;text-align:center;'>����</td></tr>");
    	String res= "[{'data':[{" +
    			"'mendian':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/22/289/image/289d5af9-9be9-4f0c-a292-dadfb6a994de.jpeg'," +
    			"'pgPic':[],'promotionId':'146','rtlnr':'8000378','tiaoma':[{'barcode':'1','url':'xxxxx1'},{'barcode':'2','url':'xxxx2'},{'barcode':'3','url':'xxxxx4'}],'visitDate':1421856000000," +
    			"'xdchenlie':[],'xschenlie':[{" +
    			"'url':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/22/550/image/55043503-f649-4806-a48f-3970b1abc781.jpeg'}," +
    			"{'url':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/22/e09/image/e0937341-8843-47d5-8cf4-bc43f6da2120.jpeg'}]}," +
    			"{'mendian':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/30/7df/image/7df2c34d-6dd4-4dbe-80b4-a314cb8e0ab2.jpeg','pgPic':[]," +
    			"'promotionId':'146','rtlnr':'8000378','tiaoma':[],'visitDate':1422547200000,'xdchenlie':[]," +
    			"'xschenlie':[{'url':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/30/7df/image/7dff97b6-c57d-422a-9904-0f9b3bf54485.jpeg'}," +
    			"{'url':'http://sale.lanju.cn:82/pic/853/000/001/form/2015-01/30/711/image/71139e27-1426-4a8e-aed4-b1390a8078ef.jpeg'}]}]}],'info':'�ɹ�','result':'0'}]";
 
    	JSONArray array = JSONArray.fromObject(res); 
    	List list = new ArrayList(); 
    	for(Iterator iter = array.iterator(); iter.hasNext();){ 
    	 JSONObject jsonObject = (JSONObject)iter.next(); 
    	 //System.out.println(jsonObject.getString("data"));
    	 JSONArray detail = JSONArray.fromObject(jsonObject.getString("data")); 
    	 for(Iterator itd = detail.iterator(); itd.hasNext();){
    		 html.append("<tr align='center'>");
    		 JSONObject tr = (JSONObject)itd.next(); 
    		 String mendian = tr.getString("mendian");//�ŵ��ַ
    		 //String promotionId = tr.getString("promotionId");//�
    		 String rtlnr = tr.getString("rtlnr");//�ŵ����
    		 String visitDate = tr.getString("visitDate");//�ݷ�ʱ��
    		 html.append("<td style='width:100px;text-align:center;'>"+rtlnr+"</td><td style='width:100px;text-align:center;'>"+visitDate+"</td><td style='width:100px;text-align:center;'><img style='cursor:pointer' width = '40px' height ='50px' src='"+mendian+"'></img></td>");
    		
    		//��������
    		 JSONArray tiaoma = JSONArray.fromObject(tr.getString("tiaoma")); 
    		 int i=0;
    		 for(Iterator tm = tiaoma.iterator();tm.hasNext();){
    			 i++;
    			 JSONObject tmtd = (JSONObject)tm.next(); 
    			 String barCode = tmtd.getString("barcode");
    			 String url = tmtd.getString("url");
    			 html.append("<td style='width:60px;text-align:center;'>"+barCode+"<br/><img style='cursor:pointer' width = '45px' height ='50px' src='"+url+"'></img></td>");
    		 }
    		 if(i<10){ 
    				 html.append("<td style='width:10px;text-align:center;' colspan='"+(10-i)+"'>&nbsp;</td>");
    		 }
    		 //ϴ�ӽ�������
    		 JSONArray xd = JSONArray.fromObject(tr.getString("xdchenlie")); 
    		 int s =0;
    		 for(Iterator td = xd.iterator();td.hasNext();){
    			 s++;
    			 JSONObject xdtd = (JSONObject)td.next(); 
    			 String url = xdtd.getString("url");
    			 html.append("<td style='width:60px;text-align:center;'>ϴ�ӳ���<br/><img style='cursor:pointer' width = '45px' height ='50px' src='"+url+"'></img></td>");
    		 }
    		 if(s==0){
    			 html.append("<td'>&nbsp;&nbsp;</td>");
    		 }
    		//��ɱ��������
    		 JSONArray xs = JSONArray.fromObject(tr.getString("xschenlie")); 
    		 int l=0;
    		 for(Iterator xsd = xs.iterator();xsd.hasNext();){
    			 l++;
    			 JSONObject xstd = (JSONObject)xsd.next(); 
    			 String url = xstd.getString("url");
    			 html.append("<td style='width:60px;text-align:center;'>��ɱ����<br/><img style='cursor:pointer' width = '45px' height ='50px' src='"+url+"'></img></td>");
    		 }
    		 if(l==0){
    			 html.append("<td'>&nbsp;&nbsp;</td>");
    		 }
    		//��������
    		 html.append("<td style='width:60px;text-align:center;'>����<br/>ͼƬ</td></tr>");
    	 }
    	}
    	 html.append("</table>");
    	return html.toString();
    }
    
    public static List toList(){
    	String str = "[{'data':[{'demandNum':11,'executeNum':11,'guid':'8FF1FFA2-92D5-4DEE-8C0E-483EF84CC72F','kunnr':'1001441','promotionCode':'146','rtlnr':'8000378','times':0}],'info':'�ɹ�','result':'0'}]";
    	JSONArray array = JSONArray.fromObject(str); 
    	List list = new ArrayList(); 
    	for(Iterator iter = array.iterator(); iter.hasNext();){ 
    	 JSONObject jsonObjects = (JSONObject)iter.next(); 
       	 JSONArray detail = JSONArray.fromObject(jsonObjects.getString("data"));
    	 for(Iterator itd = detail.iterator(); itd.hasNext();){
    		 JSONObject jsonObject = (JSONObject)itd.next();
	    	 Execution ex = new Execution();
	    	 ex.setDemandNum(jsonObject.getString("demandNum"));
	    	 ex.setExecuteNum(jsonObject.getString("executeNum"));
	    	 ex.setGuid(jsonObject.getString("guid"));
	    	 ex.setKunnr(jsonObject.getString("kunnr"));
	    	 ex.setPromotionCode(jsonObject.getString("promotionCode"));
	    	 ex.setRtlnr(jsonObject.getString("rtlnr"));
	    	 ex.setTimes(jsonObject.getString("times"));
	    	 list.add(ex);
    	  }
    	}
    	return list;
    }
}

class Execution {
	private String demandNum;
	private String executeNum;
	private String guid;
	private String kunnr;
	private String promotionCode;
	private String rtlnr;
	private String times;
	public String getDemandNum() {
		return demandNum;
	}
	public void setDemandNum(String demandNum) {
		this.demandNum = demandNum;
	}
	public String getExecuteNum() {
		return executeNum;
	}
	public void setExecuteNum(String executeNum) {
		this.executeNum = executeNum;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public String getRtlnr() {
		return rtlnr;
	}
	public void setRtlnr(String rtlnr) {
		this.rtlnr = rtlnr;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
}