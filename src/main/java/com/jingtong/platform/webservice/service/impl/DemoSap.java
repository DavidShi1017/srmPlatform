package com.jingtong.platform.webservice.service.impl;

import com.jingtong.platform.framework.bo.BasicService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class DemoSap extends BasicService{
	private JCoDestination dest;
	public void testDemo(){
		 //��ν����ͬ�ֶεĲ���ֵ����sap
		try { 
			//��������  
			this.connect("SAP");
			dest = JCoDestinationManager.getDestination("SAP");
			dest.ping();
			JCoFunction function = 	dest.getRepository().getFunction("");//url
			if (function == null){
				throw new Exception("����SAPʧ��!"); 
			}
			// ����Ĳ���
			function.getImportParameterList().setValue("BUDAT",""); //������
			function.getImportParameterList().setValue("BUNAM","");//�����ˣ����ϼ���(30������ȷ��)
			JCoTable item = function.getTableParameterList().getTable("ITEM");
				       item.appendRow(); //������� 
						item.setValue("MATNR", "");//����
						item.setValue("WERKS", "");//���� 
			function.execute(dest); 
    		String materialDocument = function.getExportParameterList().getValue("MATERIALDOCUMENT").toString();//sap������Ϣ
    		String matdocumentYear = function.getExportParameterList().getValue("MATDOCUMENTYEAR").toString();//sap������Ϣ
			JCoTable resultTbl = function.getTableParameterList().getTable("T_RETURN"); 
			int rows = resultTbl.getNumRows();
		
		}catch (Exception e1) { 
				e1.printStackTrace();
		} 
		
	}
	
	public static void main(String[] args) {
		
	}
	
	
	

}
