package com.jingtong.platform.sap.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.sap.pojo.SAPReport;
import com.jingtong.platform.sap.service.SAPReportService;

public class SAPReportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 658092040336599888L;

	private SAPReportService sapReportService;
	private List<SAPReport> reports;
	private List<SAPReport> pGroupRules;
	private List<SAPReport> mvgs;//ϵ��s
	private List<SAPReport> replist;
	private List<SAPReport> rep;
	private String promoId;//�id
	private String mvgr;//ϵ��
	private SAPReport sapReport=new SAPReport();
	@PermissionSearch
	@JsonResult(field = "replist", include = { "id","mvgr1","itemName","freect","netwr","costrt","costmt","state"})
	public String getFinalReport(){
		List<List<SAPReport>> lists = new ArrayList<List<SAPReport>>();
		/****sap���޸�*******/
		//��ȡ�ʱ�䣬ֻҪ���ʱ��
		SAPReport saprep = this.sapReportService.getPromotion(promoId);
		SAPReport prt = new SAPReport();
		prt.setP_promotionId("");
		prt.setP_spart("");
		prt.setP_mvgr1("X");
		prt.setP_matnr("X");
		prt.setP_kunnr("");
		prt.setP_kwmeng("");
		prt.setS_promotionId("");
		prt.setS_spart("");
		prt.setS_mvgr1("");
		prt.setS_matnr("");
		prt.setS_kunnr("");
		prt.setPbegin_Date(saprep.getPbegin_Date());
		prt.setPend_Date(saprep.getPend_Date());
		reports = this.sapReportService.getFinalReport(prt);
		 
		lists.add(reports);
		//lists.add(reports);
		//��ȡ�û�������ӻ
/*		List<SAPReport> promoDetails = this.sapReportService.getPromoDetails(this.promoId);
		for(SAPReport pts : promoDetails){
			
//			 *  P_SPART	CHAR1	�Ƿ���ʾƷ��
//				P_MVGR1	CHAR1	�Ƿ���ʾϵ��
//				P_MATNR	CHAR1	�Ƿ���ʾ����
//				P_KUNNR	CHAR1	�Ƿ���ʾ�۴﷽
			
			SAPReport prt = new SAPReport();
			prt.setP_promotionId("0");
			prt.setP_spart("0");
			prt.setP_mvgr1("X");
			prt.setP_matnr("X");
			prt.setP_kunnr("0");
			prt.setS_promotionId(pts.getPromotiondetailId());
			prt.setS_spart("");
			prt.setS_mvgr1("");
			prt.setS_matnr("");
			prt.setS_kunnr("");
			reports = this.sapReportService.getFinalReport(prt);
			lists.add(reports);
		}*/
	 	pGroupRules = this.sapReportService.getPgroupRules(promoId);//��ȡ����װ
		mvgs = this.sapReportService.getMvgs();
		List<SAPReport> list = new ArrayList<SAPReport>();
		double allCost = 0.0D;
		 DecimalFormat df=new DecimalFormat("#.#"); 
		 DecimalFormat df1=new DecimalFormat("#.0000"); 
		for(SAPReport rl : pGroupRules){
			for(List<SAPReport> rport:lists){
			for(SAPReport pt : rport){
				String matnr ="";
				if(!(pt.getMatnr().length()>0)){
				    continue;
				}else{
					matnr = Long.parseLong(pt.getMatnr())+"";
				}
				if(rl.getMaterialNumber().equals(matnr)){
					SAPReport report = new SAPReport();
					for(SAPReport mv:mvgs){
//						System.out.println(mv.getItemValue()+"------"+pt.getMvgr1());
						if(mv.getItemValue().equals(pt.getMvgr1())){
							report.setMvgr1(pt.getMvgr1());//ϵ�б��
							report.setItemName(mv.getItemName());//ϵ������
							break;
						}
					}
					if(list.isEmpty()){
						//��ȡ����
						report.setFreeCost(report.getFreeCost()+(rl.getFreeCost() * Float.parseFloat(pt.getKlmeng())));
						//���
						report.setNetwrs(report.getNetwrs()+Double.parseDouble(pt.getNetwr()));
//						System.out.println("=========="+report.getNetwrs());
						report.setPromotionId(pt.getPromotionId());
						allCost =allCost+(report.getNetwrs()+Double.parseDouble(pt.getNetwr()));
					}else{
						for(SAPReport rp : list){
//							System.out.println(rp.getNetwr()+"---"+rp.getMvgr1()+"------"+pt.getMvgr1());
							if(rp.getMvgr1().equals(pt.getMvgr1())){//ͬһϵ���ۼ�
								//��ȡ����
								rp.setFreeCost(rp.getFreeCost()+(rl.getFreeCost() * Float.parseFloat(pt.getKlmeng())));
								//���
								
								rp.setNetwrs(rp.getNetwrs()+Double.parseDouble(pt.getNetwr()));
								rp.setPromotionId(pt.getPromotionId());
								allCost =allCost+(rp.getNetwrs()+Double.parseDouble(pt.getNetwr()));
							}else{
								//��ȡ����
								report.setFreeCost(report.getFreeCost()+(rl.getFreeCost() * Float.parseFloat(pt.getKlmeng())));
								//���
								report.setNetwrs(report.getNetwrs()+Double.parseDouble(pt.getNetwr()));
								report.setPromotionId(pt.getPromotionId());
								allCost =allCost+(report.getNetwrs()+Double.parseDouble(pt.getNetwr()));
							}
							break;
						}
					}
					list.add(report);
				 }
			   }
			}
		}
		replist = new ArrayList<SAPReport>();
		int i=1;
		float allfree=0;//����
		double allmathe=0;
		double allrate=0;
		double cost=0D;
		for(SAPReport sp:list){
			if(sp.getItemName()==null){
				continue;
			}
			if(sp.getNetwrs()>0){
				sp.setId(i+""); 
				cost = cost+sp.getNetwrs();
				sp.setNetwr(df.format(sp.getNetwrs()));
				//DecimalFormat df1 = new DecimalFormat("#.00"); 
//				sp.setCostRate(Double.parseDouble(df.format(sp.getFreeCost()<=0.0?0:(sp.getFreeCost()/sp.getNetwrs())*100)));
//				sp.setCostMathe(Double.parseDouble(df.format(sp.getNetwrs()<=0.0?0:(sp.getNetwrs()/allCost)*100)));
				sp.setFreect(df.format(sp.getFreeCost()));
				sp.setCostrt(df.format(sp.getFreeCost()<=0.0?0:(sp.getFreeCost()/sp.getNetwrs())*100));
				sp.setCostmt(df1.format(sp.getNetwrs()<=0.0?0:(sp.getNetwrs()/cost)*100));
				
				sp.setState("closed");
				allfree = allfree+sp.getFreeCost();
//				allmathe = allmathe + sp.getCostMathe();
//				allrate = allrate + sp.getCostRate();
				allmathe = allmathe + sp.getNetwrs();
				allrate = allrate + sp.getFreeCost();
				i++;
//				System.out.println("hello: ϵ/�У�"+sp.getMvgr1()+"---���ƣ�"+sp.getItemName()+"---CostRate��"+sp.getCostRate()+"---CostMathe��"+sp.getCostMathe()+"---FreeCost��"+sp.getFreeCost()+"---Netwrs��"+sp.getNetwrs());
				replist.add(sp);
			}
		}
		SAPReport p = new SAPReport();
		p.setId("10000010");
		p.setItemName("�ϼ�");
		p.setMvgr1("111");
		p.setNetwr(df.format(cost));
		p.setFreect(df.format(allfree));
//		p.setCostRate(allrate);
//		p.setCostMathe(allmathe);
		p.setCostrt(df.format((allrate/cost)*100));
		p.setCostmt(df.format((allmathe/cost)*100));
		
		replist.add(p); 
		return JSON;
	}
	@PermissionSearch
	@JsonResult(field = "replist", include = {  "id","mvgr1","itemName","freect","netwr","costrt","costmt","state"})
	public String getFinalReportDetail(){
		List<List<SAPReport>> list = new ArrayList<List<SAPReport>>();
		List<SAPReport> prtlist = new ArrayList<SAPReport>();
		Double allCost=0.0d;
		/****sap���޸�*******/
		//��ȡ�ʱ�䣬ֻҪ���ʱ��
		SAPReport saprep = this.sapReportService.getPromotion(promoId);
		SAPReport prt = new SAPReport();
		prt.setP_promotionId("0");
		prt.setP_spart("0");
		prt.setP_mvgr1("X");
		prt.setP_matnr("X");
		prt.setP_kunnr("0");
		prt.setS_promotionId("");
		prt.setS_spart("");
		prt.setS_mvgr1(this.mvgr);
		prt.setS_matnr("");
		prt.setS_kunnr("");
		prt.setPbegin_Date(saprep.getPbegin_Date());
		prt.setPend_Date(saprep.getPend_Date());
		reports = this.sapReportService.getFinalReport(prt);
		list.add(reports);
		//��ȡ�û�������ӻ
/*		List<SAPReport> promoDetails = this.sapReportService.getPromoDetails(this.promoId);
		for(SAPReport pts : promoDetails){
			SAPReport prt = new SAPReport();
//			 
//			 *  P_SPART	CHAR1	�Ƿ���ʾƷ��
//				P_MVGR1	CHAR1	�Ƿ���ʾϵ��
//				P_MATNR	CHAR1	�Ƿ���ʾ����
//				P_KUNNR	CHAR1	�Ƿ���ʾ�۴﷽
			 
			prt.setP_promotionId("0");
			prt.setP_spart("0");
			prt.setP_mvgr1("X");
			prt.setP_matnr("X");
			prt.setP_kunnr("0");
			prt.setS_promotionId(pts.getPromotiondetailId());
			prt.setS_spart("");
			prt.setS_mvgr1(this.mvgr);
			prt.setS_matnr("");
			prt.setS_kunnr("");
			reports = this.sapReportService.getFinalReport(prt);//��ȡsap
			list.add(reports);
		}*/
		pGroupRules = this.sapReportService.getPgroupRules(promoId);//��ȡ����װ
		DecimalFormat df = new DecimalFormat("#.#"); 
		DecimalFormat df1 = new DecimalFormat("#.0000"); 
		for( SAPReport ports :pGroupRules){
			SAPReport sap = new SAPReport();
			for(List<SAPReport> poxrts: list){
				for(SAPReport spt: poxrts){
					String matnr = Long.parseLong(spt.getMatnr())+"";
					if(ports.getMaterialNumber().equals(matnr)){
						sap.setMvgr1(spt.getMvgr1());//ϵ�б��
						sap.setItemName("("+matnr+")"+spt.getMaktx());//ϵ������MAKTX
						//��ȡ����
						sap.setFreeCost(sap.getFreeCost()+(ports.getFreeCost() * Float.parseFloat(spt.getKlmeng())));
						//���
						sap.setNetwrs(sap.getNetwrs()+Double.parseDouble(spt.getNetwr()));
						sap.setPromotionId(spt.getPromotionId());
//						allCost =allCost+(sap.getNetwrs()+Double.parseDouble(spt.getNetwr()));

						allCost =allCost+(Double.parseDouble(spt.getNetwr()));
					}
				}
			}
			prtlist.add(sap);
		}
		int i=10000;
		replist = new ArrayList<SAPReport>();
		for(SAPReport sapt:prtlist){
			if(sapt.getItemName()==null){
				continue;
			}
				sapt.setId(i+"");
				sapt.setNetwr(df.format(sapt.getNetwrs()));
				sapt.setFreect(df.format(sapt.getFreeCost()));
				sapt.setCostrt(df.format(sapt.getFreeCost()<=0.0?0:(sapt.getFreeCost()/sapt.getNetwrs())*100));
				sapt.setCostmt(df1.format(sapt.getNetwrs()<=0.0?0:(sapt.getNetwrs()/allCost)*100));
				sapt.setState("");
			i++;	
//			System.out.println("hello: ϵ�У�"+sapt.getMvgr1()+"---���ƣ�"+sapt.getItemName()+"---CostRate��"+sapt.getCostRate()+"---CostMathe��"+sapt.getCostMathe()+"---FreeCost��"+sapt.getFreeCost()+"---Netwrs��"+sapt.getNetwrs());
			replist.add(sapt);
		}
		return JSON;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public List<SAPReport> getReports() {
		return reports;
	}
	public void setReports(List<SAPReport> reports) {
		this.reports = reports;
	} 
	public SAPReportService getSapReportService() {
		return sapReportService;
	} 
	public void setSapReportService(SAPReportService sapReportService) {
		this.sapReportService = sapReportService;
	}
 
	public List<SAPReport> getpGroupRules() {
		return pGroupRules;
	}
 
	public void setpGroupRules(List<SAPReport> pGroupRules) {
		this.pGroupRules = pGroupRules;
	} 
	public String getPromoId() {
		return promoId;
	}  
	public void setPromoId(String promoId) {
		this.promoId = promoId;
	}
    public List<SAPReport> getMvgs() {
		return mvgs;
	} 
	public void setMvgs(List<SAPReport> mvgs) {
		this.mvgs = mvgs;
	}
	public List<SAPReport> getReplist() {
		return replist;
	}
	public void setReplist(List<SAPReport> replist) {
		this.replist = replist;
	}
	public String getMvgr() {
		return mvgr;
	}
	public void setMvgr(String mvgr) {
		this.mvgr = mvgr;
	}
	
	
	public String getProductGroupReport(){
		List<SAPReport> ptlist = new ArrayList<SAPReport>();
		replist = new ArrayList<SAPReport>();
		float dmcost=0.0f;//DM��
		float freeCost=0.0f;//������
		float salesNum=0.0f;//������
		double netwr=0.0f;//���۶�
		float demandNum=0.0f;//�����
		 
		//�Ȼ�ȡ����װ������
		ptlist = this.sapReportService.getpgRulesByPromoId(this.promoId);
		//��ȡ���з�
		SAPReport ps =this.sapReportService.getDisplayCost(this.promoId);
		//float displayCost = this.sapReportService.getDisplayCost(this.promoId);
		float displayCost = ps.getDemandNum();
		//��sap��ȡ����ʱ���ڵ������������۽��
		SAPReport saprep = this.sapReportService.getPromotion(promoId);
		//��ȡ�۴﷽(�����������һ���۴﷽)
		List<SAPReport> port = this.sapReportService.getKunnrByProm(promoId);
		SAPReport pst = new SAPReport();
		if((!port.isEmpty())&&port.size()>0){
			pst = port.get(0);
		}
		
		SAPReport prt = new SAPReport();
		prt.setP_promotionId("");
		prt.setP_spart("");
		prt.setP_mvgr1("X");
		prt.setP_matnr("X");
		prt.setP_kunnr("");
		prt.setP_kwmeng("X");
		prt.setS_promotionId("");
		prt.setS_spart("");
		prt.setS_mvgr1("");
		prt.setS_matnr("");
		prt.setS_kunnr("");
		prt.setPurchasebegindate(saprep.getPurchasebegindate());
		prt.setPurchaseenddate(saprep.getPurchaseenddate());
		prt.setPbegin_Date(saprep.getPbegin_Date());
		prt.setPend_Date(saprep.getPend_Date());
		prt.setS_kunnr(pst.getKunnr());
		HashMap<String, SAPReport> map = new HashMap<String, SAPReport>();
		map = this.sapReportService.getPromotionSale(prt);
		DecimalFormat df = new DecimalFormat("#.0"); 
		for(SAPReport pt:ptlist){
			pt.setPromotionId(this.promoId);
			if(pt.getDemandNums()!=null&&pt.getDemandNums().length()>0){//�Ѿ�תΪcar
				pt.setDemandNum(Float.parseFloat(pt.getDemandNums().trim()));
			}else{
				pt.setDemandNum(0);
			}
			
			SAPReport pot = (SAPReport) map.get(pt.getMaterialNumber().trim());
			if(pot==null){
				//pt.setDmcost(pt.getDmcost()*pt.getSalesNum());
				pt.setDmcost(pt.getDmcost());
				pt.setFreeCost(0.0f);
				pt.setNetwrs(Double.parseDouble(df.format(Float.parseFloat(pt.getNetwr()))));
				pt.setDemandNumI(Math.round(pt.getDemandNum()));
				pt.setSalesNums(Math.round((Float.parseFloat(pt.getSalesNum()+""))));
				pt.setCxCost(0);
				dmcost=dmcost+pt.getDmcost();
			    freeCost=freeCost+pt.getFreeCost();
			    salesNum = salesNum+Float.parseFloat(df.format(pt.getSalesNum()));
			    demandNum = demandNum+Float.parseFloat(df.format(pt.getDemandNum()));
			    netwr = netwr+pt.getNetwrs();
			    replist.add(pt);
				continue;
			}
			pt.setSalesNum(Float.parseFloat(df.format(Float.parseFloat(pot.getKlmeng()+"")/Float.parseFloat(pt.getUmrez()))));//������(ת��Ϊcar)
			pt.setNetwr(pot.getNetwr());//���۶�
			//������
			if(pot.getNetwrs()>0D){
				pt.setCostRate(Double.parseDouble(df.format(((pt.getFreeCost()+pt.getDmcost()))/(pot.getNetwrs()*10000)*100))); 
			}else{
				pt.setCostRate(0D);
			}
			//�����
			if(pt.getDemandNum()>0){
				float f= pt.getSalesNum();
//				System.out.println(f+"======"+pt.getDemandNum());
				pt.setDclv(Float.parseFloat(df.format((f/pt.getDemandNum())*100)));
			}else{
				pt.setDclv(0.0f);
			}
			//pt.setDmcost(pt.getDmcost()*pt.getSalesNum());
			pt.setDmcost(pt.getDmcost());
			pt.setFreeCost(0.0f);
			pt.setFreeCost(Float.parseFloat(df.format((Float.parseFloat(pt.getChannelPrice())-pt.getOutPrice())*Float.parseFloat(pot.getKlmeng()))));
			pt.setNetwrs(Double.parseDouble(df.format(Float.parseFloat(pt.getNetwr()))));
			pt.setDemandNumI(Math.round(pt.getDemandNum()));
			pt.setSalesNums(Math.round((Float.parseFloat(pt.getSalesNum()+""))));
			pt.setCxCost(Math.round(pt.getFreeCost()));
			replist.add(pt);
			dmcost=dmcost+pt.getDmcost();
		    freeCost=freeCost+pt.getFreeCost();
		    salesNum = salesNum+Float.parseFloat(df.format(pt.getSalesNum()));
		    netwr = netwr+pt.getNetwrs();
		    demandNum = demandNum+Float.parseFloat(df.format(pt.getDemandNum())); 
		}
		SAPReport p = new SAPReport();
		p.setMaterialName("�ϼƣ�");
		p.setDiscountType("");
		p.setChannelPrice("");
		p.setFreeCost(Float.parseFloat(df.format(freeCost)));
		p.setDisplayCost(Float.parseFloat(df.format(displayCost)));
		p.setDmcost(Float.parseFloat(df.format(dmcost)));
		p.setSalesNum(Float.parseFloat(df.format(salesNum)));
		p.setNetwrs(Double.parseDouble(df.format(netwr)));
		p.setDemandNum(Float.parseFloat(df.format(demandNum)));
		p.setDemandNumI(Math.round(demandNum));
		p.setSalesNums(Math.round(salesNum));
		p.setCxCost(Math.round(p.getFreeCost()));
		float a = 0f;
		if(demandNum>=1){
			a=demandNum;
		}else{
			a=1;
		}
		//������
		if(!(netwr>0)){
			p.setCostRate(0d);
		}else{
			//System.out.println("freeCost:"+freeCost+"  displayCost:"+displayCost+" dmcost:"+dmcost+" a:"+a +"  netwr��"+netwr);
			p.setCostRate(Double.parseDouble(df.format(((freeCost+displayCost+dmcost))/(netwr>0?(netwr*10000):1)*100)));
		}
		//�����
		if(p.getDemandNum()>0){
			float f= p.getSalesNum();
			p.setDclv(Float.parseFloat(df.format((f/p.getDemandNum())*100)));
		}else{
			p.setDclv(0.0f);
		}
		//�����ܼ� 
		sapReport.setCxCost(Math.round(p.getFreeCost()));
		sapReport.setClCost(Math.round(p.getDisplayCost()));
		sapReport.setDmCostI(Math.round(p.getDmcost()));
		sapReport.setCostRate(p.getCostRate());
		sapReport.setXsCost(Math.round(Float.parseFloat(p.getNetwrs()+"")*10000));
		float fl=p.getFreeCost()+p.getDisplayCost()+p.getDmcost();
		//System.out.println((p.getFreeCost()+p.getDisplayCost()+p.getDmcost()));
		sapReport.setFyAll(Math.round(fl));
		replist.add(p);
		return "toCase";
	}
	
	
	public SAPReport getSapReport() {
		return sapReport;
	}
	public void setSapReport(SAPReport sapReport) {
		this.sapReport = sapReport;
	}
	
	
}
