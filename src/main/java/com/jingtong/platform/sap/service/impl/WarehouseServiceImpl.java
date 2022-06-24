package com.jingtong.platform.sap.service.impl;

import java.util.Date;
import java.util.List;

import com.jingtong.platform.framework.bo.BasicService;
import com.jingtong.platform.sap.dao.ILogInfoDao;
import com.jingtong.platform.sap.dao.WarehouseDao;
import com.jingtong.platform.sap.pojo.ExceptionLog;
import com.jingtong.platform.sap.pojo.LoadPoint;
import com.jingtong.platform.sap.pojo.Warehouses;
import com.jingtong.platform.sap.service.WarehouseService;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class WarehouseServiceImpl extends BasicService  implements WarehouseService {

	private WarehouseDao warehouseDao;
	private JCoDestination dest;
	private ILogInfoDao logInfoDao;

	private final String RFC_WAREHOUSE = "Z_RFC_GET_TVSTZ";
	
	public ILogInfoDao getLogInfoDao() {
		return logInfoDao;
	}
	

	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}


	public void setLogInfoDao(ILogInfoDao logInfoDao) {
		this.logInfoDao = logInfoDao;
	}
	@Override
	public List<Warehouses>  queryWarehouseList(Warehouses warehouse) {
		// TODO Auto-generated method stub
		List<Warehouses> warehouseList = this. warehouseDao.queryWarehouseList(warehouse);
	    return warehouseList;
	}

	@Override
	public void getSapWarehouseLocation() throws Exception {
		// TODO Auto-generated method stub
		JCoFunction function = this.getSapConnection();
		
		//���������ʱΪ��
//		JCoStructure head =	function.getImportParameterList().getStructure("HEAD");//��ȡ�ṹ
//		head.setValue("S_WERK", "");
//		head.setValue("S_LGORT", "");
//		head.setValue("S_VSTEL", "");
		function.execute(dest);
		JCoTable resultTbl = function.getTableParameterList().getTable("OT_TVSTZ");
		int rows = resultTbl.getNumRows();
		if (rows > 0) {
			for(int i=0;i<rows;i++){
				resultTbl.setRow(i);
				Warehouses warehouse = new Warehouses();
				LoadPoint loadPoint = new LoadPoint();
				if(resultTbl.getString("WERKS") == null || resultTbl.getString("WERKS").equals("")){
					throw new Exception("�ֿ���벻��Ϊ��");
				}
				if(resultTbl.getString("NAME1") == null || resultTbl.getString("NAME1").equals("")){
					throw new Exception("�ֿ����Ʋ���Ϊ��");
				}
				if(resultTbl.getString("LGORT") == null || resultTbl.getString("LGORT").equals("")){
					throw new Exception("��λ���벻��Ϊ��");
				}
				if(resultTbl.getString("LGOBE") == null || resultTbl.getString("LGOBE").equals("")){
					throw new Exception("��λ���Ʋ���Ϊ��");
				}
				warehouse.setWarehouseCode(resultTbl.getString("WERKS"));
				warehouse.setWarehouseDesc(resultTbl.getString("NAME1"));
				warehouse.setLocationCode(resultTbl.getString("LGORT"));
				warehouse.setLocationName(resultTbl.getString("LGOBE"));
				warehouse.setVstel(resultTbl.getString("VSTEL"));
				
				
				if(resultTbl.getString("VSTEL") == null || resultTbl.getString("VSTEL").equals("")){
					throw new Exception("װ�˵���벻��Ϊ��");
				}
				if(resultTbl.getString("VTEXT") == null || resultTbl.getString("VTEXT").equals("")){
					throw new Exception("װ�˵����Ʋ���Ϊ��");
				}
				loadPoint.setVstel(resultTbl.getString("VSTEL"));
				loadPoint.setVtext(resultTbl.getString("VTEXT"));
				
				this.addLoadPoint(loadPoint);
				this.addWarhouse(warehouse);
				
			}
		}
	}
	
	private  JCoFunction getSapConnection() throws Exception{
		this.connect("SAP");
		dest = JCoDestinationManager.getDestination("SAP");
		dest.ping();
		// ���ӽӿ�
		JCoFunction function = dest.getRepository().getFunction(this.RFC_WAREHOUSE);// url
		if (function == null) {
			ExceptionLog log = new ExceptionLog();
			log.setInterfaceName("��Ʒ��������SAP/"+this.RFC_WAREHOUSE);
			log.setOperateUser("SAP");
			log.setOperateTime(new Date());
			log.setLogDesc("ʧ�ܣ�");
			log.setLogInfo("����SAPʧ��!");
			this.logInfoDao.addLogInfo(log);
			throw new Exception("����SAPʧ��!");
		}
		return function;

	}

	/**
	 * ����װ�˵�
	 * @param loadPoint
	 * @throws Exception
	 */
	private void addLoadPoint(LoadPoint loadPoint) throws Exception{
		
		List<LoadPoint> loadPointList = this.warehouseDao.queryLoadPointList(loadPoint);
		if(loadPointList == null || loadPointList.size() == 0){
			this.warehouseDao.addLoadPoint(loadPoint);
		}else if(loadPointList.size() == 1){
			loadPoint.setModifyDate(new Date());
			loadPoint.setModifyUser(loadPointList.get(0).getModifyUser());
			this.warehouseDao.updateLoadPoint(loadPoint);
		}else if(loadPointList.size() > 1){
			throw new Exception("crm�д��ڶ�������װ�˵�");
		}
	}
	
	/**
	 * �����ֿ��λ
	 * @param warehouse
	 * @throws Exception
	 */
	private void addWarhouse(Warehouses warehouse) throws Exception{
		List<Warehouses> warehouseList = this.warehouseDao.queryWarehouseList(warehouse);
		if(warehouseList == null || warehouseList.size() == 0){
			this.warehouseDao.addWarehouse(warehouse);
		}else if(warehouseList.size() == 1){
			warehouse.setModifyDate(new Date());
			warehouse.setModifyUser(warehouseList.get(0).getModifyUser());
			this.warehouseDao.updateWarehouse(warehouse);
		}else if(warehouseList.size() > 1){
			throw new Exception("crm�д��ڶ���ֿ��λ");
		}
	}
}
