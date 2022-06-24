package com.jingtong.platform.kunnr.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.allUser.pojo.AllUsers;
import com.jingtong.platform.allUser.service.IAllUserService;
import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.base.pojo.BooleanResult;
 
import com.jingtong.platform.framework.annotations.Decode;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.mail.MailService;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.FileUtil;
import com.jingtong.platform.framework.util.JavaBeanXMLUtil;
 import com.jingtong.platform.framework.util.XMLInfo;
 import com.jingtong.platform.kunnr.pojo.Knvp;
import com.jingtong.platform.kunnr.pojo.Kunnr;
import com.jingtong.platform.kunnr.pojo.KunnrAcount;
import com.jingtong.platform.kunnr.pojo.KunnrAddress;
import com.jingtong.platform.kunnr.pojo.KunnrBanks;
import com.jingtong.platform.kunnr.pojo.KunnrBrand;
import com.jingtong.platform.kunnr.pojo.KunnrBusiness;
import com.jingtong.platform.kunnr.pojo.KunnrLicense;
import com.jingtong.platform.kunnr.pojo.KunnrLogisticsArea;
import com.jingtong.platform.kunnr.pojo.KunnrPolicy;
import com.jingtong.platform.kunnr.pojo.KunnrPunish;
import com.jingtong.platform.kunnr.pojo.KunnrSalesArea;
import com.jingtong.platform.kunnr.service.IKunnrService;
 import com.jingtong.platform.org.pojo.Borg;
import com.jingtong.platform.org.service.IOrgService;
 
public class KunnrAction extends BaseAction {

	/**
	 * getChannel
	 */
	private static final long serialVersionUID = -638516699213585547L;
	private static Log logger = LogFactory.getLog(KunnrAction.class);
	 
	private String type; // �ػ������Ƿ�������֪ͨ
	private static String customerManagement = "customerManagement"; // ��ɫ�����ܲ��ͻ�������
	private IOrgService orgServiceHessian; // ����֯�ӿ�
	private IAllUserService allUserServiceHessian;
	
	private IKunnrService kunnrService;
	
	
	private String appUrl;
	private Kunnr kunnr;
	private String kunnrId;// kunnrSAP����
	private Long channelId;
	private String status;
	private @Decode
	String businessManager;
	private @Decode
	String businessCompetent;
	private KunnrBusiness kunnrBusiness;
	private KunnrSalesArea kunnrSalesArea;
	private KunnrPolicy kunnrPolicy;
	private List<Kunnr> kunnrList;
	private List<KunnrBusiness> kunnrBusinessList;
	private List<KunnrSalesArea> kunnrSalesAreaList;
	private List<KunnrAddress> kunnrAddressList;
	private List<KunnrBrand> kunnrBrandList;
	private List<KunnrAcount> kunnrAcountList;
	private List<KunnrLicense> kunnrLicenseList;
	private List<KunnrBanks> kunnrBanklist;//�����̿�����Ϣ
	private List<KunnrPolicy> kunnrPolicyList;
	

	private int addressListSize;
	private int brandListSize;
	private int acountListSize;
	private KunnrPunish kunnrPunish;
	
	private List<KunnrPunish> kunnrPunishList;
	private String konzsTxt;
	
	private int licenseListSize;
	private int kunnrSalesAreaListSize;
	private int bCustomerTargetListSize;
	private int kunnrPolicyListSize;
	private List<KunnrBanks> bKunnrBanklist;//�����̿�����Ϣ
	private int aKunnrBankSize;//�����˻�����
	private String userId;
	private String nextUserId;
	private String eventId;
	private String title;
	private String xmlFilePath;
	private File[] upload;
	private String[] uploadFileName;

	private String subFolders;
	private int total = 0;
	private String attachmentsName;// ����Name
	private String attachmentsPath;// ����·��
	private String opera;
	private @Decode
	String name1;
	private String freezeOrClose;// ���� �رչ��ñ�־
	private BooleanResult executeResult;// ���������������Ϣ
	private String kunnrCode;// kunnrcode
	private @Decode
	String dictTypeName;// �ֵ��������� ����˰������
	private String[] licenseName;
	private File[] license;
	private String[] licenseFileName;
	private Date[] licenseValid;
	private @Decode
	String dictTypeValue;
	private int num;
	private String orgId;
	@Decode
	private String orgName;

	private List<KunnrLogisticsArea> lAreaList;
	private String vkgrp;
	private String bzirk;
	private String vkbur;
	private List<KunnrLogisticsArea> areaList;
	private List<KunnrLogisticsArea> areaLists;

	private String resultJson;

	private @Decode
	String channelName;
	private String updateFlag;
	private String curStaId;
	private String modifyFlag;

	private String bhxjFlag;
	private String zgFlag;
	private String uploadFileFileName;
	private File uploadFile;
	private String updateType;
	private String nameUpdateFile;
	private String nameUpdatePath;// ����·��

	private String freezeButton;
	// private String[] nameUpdate;
	private File[] nameUpload;
	private String[] nameUpdateFileName;
	private String locco;
	private List<KunnrSalesArea> killSalesArea;
	private String codes;
	private String name3;
	private String konzs;
	private String customerType;
	private String flag; //����֯׷�ݾ����̱�־
	private String kunnrErp; //erp�еľ����̱��
	
	private String maximum;
	private String billType;
	private List<Knvp> knvpList;
	private String parvw;
	private String kunnrWe;//�ʹ�
 
	 
	
 	
  
	
	/**
	 * �۴﷽���������Ʊ��
	 * */
	@PermissionSearch
	@JsonResult(field = "knvpList", include = { "kunnr", "name1", "street1"})
	public String getknvpList() {
		Knvp knvp = new Knvp();
		if(null ==kunnrId  || "".equals(kunnrId)){
			knvp.setKunnr(this.getUser().getLoginId());
 		}else{
 			knvp.setKunnr(kunnrId);
  		}
		knvp.setParvws(parvw.split("/"));
		
 		knvpList = kunnrService.getknvpList(knvp);
		 
		return JSON;
	}
	
	public String viewAllknvpList(){
		return "viewAllknvpList";
	}
	
	public String viewAllfkList(){
		return "viewAllfkList";
	}
	
	/**
	 * ��ѯȫ���ʹ��0001007031
	 * */
	@PermissionSearch
	@JsonResult(field = "knvpList", include = { "kunnr", "name1", "street1"},total = "total")
	public String getAllknvpList() {
		Knvp knvp = new Knvp();
		knvp.setParvws(parvw.split("/"));
		knvp.setStart(getStart());
		if(this.kunnrWe!=null && this.kunnrWe.length()>0){
			try{
			knvp.setKunn2(java.net.URLDecoder.decode(this.kunnrWe,"UTF-8"));
			}catch(Exception e){
				
			}
		}
		if(getEnd()==0){
			knvp.setEnd(10000000);
		}else{
			knvp.setEnd(getEnd());
		}
		total = kunnrService.getknvpListCount(knvp);
 		knvpList = kunnrService.getAllknvpList(knvp);
		 
		return JSON;
	}
	
	/**
	 * ��ѯȫ���ʹ��1007031
	 * */
	@PermissionSearch
	@JsonResult(field = "knvpList", include = { "kunnr", "name1", "street1"},total = "total")
	public String getAllknvpList1() {
		Knvp knvp = new Knvp();
		knvp.setParvws(parvw.split("/"));
		knvp.setStart(getStart());
		if(this.kunnrWe!=null && this.kunnrWe.length()>0){
			try{
			knvp.setKunn2(java.net.URLDecoder.decode(this.kunnrWe,"UTF-8"));
			}catch(Exception e){
				
			}
		}
		if(getEnd()==0){
			knvp.setEnd(10000000);
		}else{
			knvp.setEnd(getEnd());
		}
		total = kunnrService.getknvpListCount(knvp);
 		knvpList = kunnrService.getAllknvpList1(knvp);
		 
		return JSON;
	}
	
	
	 
	
	/**
	 * ���������ݵ���
	 * 
	 * @return
	 */
	public void kunnrPunishExport() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		kunnrPunish = new KunnrPunish();
		kunnrPunish.setPagination("false");// ��ʹ�÷�ҳ ȫ�������
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(kunnrId)) {
				kunnrPunish.setKunnr(kunnrId);
			}
			 
			kunnrPunishList = kunnrService.kunnrPunishSearch(kunnrPunish);
			
			props.add("kunnr");
			props.add("violations");
			props.add("name3");
			props.add("Penalty_amount");
			props.add("Bad_time");
			props.add("creator");
			props.add("create_date");
			 

			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String("������Υ���¼���ݵ�����".getBytes("GBK"), ("ISO8859-1")) + ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("������Υ���¼����", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font =
				new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_0 = new Label(0, 0, "�����̱��");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "Υ���¼�");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "Υ�洦��");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "Υ��ʱ��");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "������");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			Label label_5 = new Label(5, 0, "����ʱ��");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			 

			ExcelUtil.createExcelWithBook(wbook, props, kunnrPunishList);

		} catch (Exception e) {
			logger.error(e);
		}

	}

 	
	 
	 
	/**
	 * �����̿���������ת
	 * 
	 * @return
	 */
	@PermissionSearch
	public String kunnrApplyPre() {
		kunnrCode = kunnrService.getRanKunnrCode();
		AllUsers user = this.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		kunnr = new Kunnr();
		kunnr.setCreateUserId(Long.parseLong(user.getUserId()));
		kunnr.setCreateUser(user.getUserName());
		kunnr.setCreateDate(sdf.format(new Date()));
		kunnr.setLocco("DM" + kunnrCode);
		kunnr.setKunnr(""+kunnrCode);
		userId = user.getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		kunnr.setOrgId(orgId);
		kunnr.setOrgName(orgName);
		return SUCCESS;
	}

	 
	 
 
	 
	 
 
	/**
	 * ���ܿͻ���ά������ͻ���
	 * 
	 * @return
	 */
	public String updateKunnrApplySapMeg() {
		if (StringUtils.isNotEmpty(eventId) && StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders + File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, new Kunnr());
			if (info != null) {
				kunnr = (Kunnr) info.getObject();
				userId = String.valueOf(kunnr.getCreateUserId());
				String userName = kunnr.getCreateUser();
				kunnr.setKunnrErp(kunnrErp); //�趨ERP�����̱��
				if (!JavaBeanXMLUtil.JavaBean2XML(xmlFilePath + "/" + eventId + ".xml", kunnr, userId, userName, null)) {
					this.setFailMessage("��Ϣд��XML�ļ�����,������");
				} else {
					this.setSuccessMessage("����ɹ�!");
				}
			}
		}
		return RESULT_MESSAGE;
	}

	 
	/**
	 * ���ظ���
	 * 
	 * @return
	 */
	@PermissionSearch
	public void downLoadAttachments() {
		try {
			attachmentsName = new String(attachmentsName.getBytes("ISO8859-1"), "GBK");
			File source = new File(attachmentsPath);
			if (source.exists()) {
				//FileUtil.downFile(source, attachmentsName, this.getServletResponse());
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
	}

	/**
	 * �������������,��Э���д�����ݿ�
	 * 
	 * @return
	 */

	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String kunnrOpen() {
		BooleanResult result = null;
		if (StringUtils.isNotEmpty(eventId) && StringUtils.isNotEmpty(subFolders)) {
			String pathFile = xmlFilePath + File.separator + subFolders + File.separator + eventId + ".xml";
			XMLInfo info = JavaBeanXMLUtil.XML2JavaBean(pathFile, new Kunnr());
			if (info != null) {
				kunnr = (Kunnr) info.getObject();
				kunnr.setCheckOpId(this.getUserId());
				try {
					result = kunnrService.kunnrOpen(kunnr);
				} catch (Exception e) {
					logger.error(e);
					result = new BooleanResult();
					result.setResult(false);
					result.setCode("���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա");
				}

			} else {
				result = new BooleanResult();
				result.setResult(false);
			}

		}
		setExecuteResult(result);
		return JSON;
	}

	 

 
 

	 
	 
	 

	 
	 
	 
 
	 

	/**
	 * 
	 * ���N�̲�ѯ���
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */

	@PermissionSearch
	@JsonResult(field = "kunnrList", include = { "kunnr", "name1", "name3", "mobNumber", "bukrs", "channelName",
		"konzs", "street1", "telNumber", "businessManager", "businessCompetent", "bank", "bankAccount", "status",
		"orgId", "orgName" ,"kunnrErp","customerType","kaManager","isautodemand","maximum","billType","isMortgage" }, total = "total")
	public String kunnrSearch() throws UnsupportedEncodingException {
		kunnr = new Kunnr();
		kunnr.setStart(this.getStart());
		kunnr.setEnd(this.getEnd());
		if("1".equals(this.getUser().getKunnrSign())){
			kunnrId=this.getUser().getLoginId();
		}
		
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnr.setKunnr(kunnrId);
		}
		/*if (StringUtils.isNotEmpty(customerType)) {
			kunnr.setCustomerType(customerType);
		}*/
		kunnr.setCustomerType("Z101");
		if (StringUtils.isNotEmpty(name1)) {
			kunnr.setName1(java.net.URLDecoder.decode(name1,"UTF-8"));
		}if (StringUtils.isNotEmpty(name3)) {
			kunnr.setName3(name3);
		}
		if (StringUtils.isNotEmpty(businessManager)) {
			kunnr.setBusinessManager(businessManager);
		}
		if (StringUtils.isNotEmpty(businessCompetent)) {
			kunnr.setBusinessCompetent(businessCompetent);
		}
		if (channelId != null) {
			kunnr.setChannelId(channelId);
		}
		if (StringUtils.isNotEmpty(status)) {
			kunnr.setStatus(status);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			String[] str = orgId.split(", ");
			if (str.length > 1) {
				kunnr.setOrgId(str[1]);
			} else {
				kunnr.setOrgId(orgId);
			}
		}
		if (StringUtils.isNotEmpty(konzs)) {
			kunnr.setKonzs(konzs);
		}
		if (StringUtils.isNotEmpty(bhxjFlag)) {
			kunnr.setBhxjFlag(bhxjFlag);
		}
		if (StringUtils.isNotEmpty(codes)) {
			String[] str = codes.split(", ");
			kunnr.setCodes(str);
		}
		//����֯׷�ݾ����̱�־
		if(StringUtils.isNotEmpty(flag)){
			kunnr.setBhxjFlag("C");
			kunnr.setOrgId(this.getUser().getOrgId());
		}
		 
		/*try {
			sendTenderMail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		total = kunnrService.kunnrSearchCount(kunnr);
		if (total != 0) {
			kunnrList = kunnrService.kunnrSearch(kunnr);
		}
		return JSON;
	}
	
	 
	/**
	 * ���������ݵ���
	 * 
	 * @return
	 */
	public void kunnrExport() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		kunnr = new Kunnr();
		kunnr.setPagination("false");// ��ʹ�÷�ҳ ȫ�������
		HttpServletResponse response = getServletResponse();
		try {
			if (StringUtils.isNotEmpty(kunnrId)) {
				kunnr.setKunnr(kunnrId);
			}
			if (StringUtils.isNotEmpty(name1)) {
				kunnr.setName1(name1);
			}
			if (StringUtils.isNotEmpty(businessManager)) {
				kunnr.setBusinessManager(businessManager);
			}
			if (StringUtils.isNotEmpty(businessCompetent)) {
				kunnr.setBusinessCompetent(businessCompetent);
			}
			if (channelId != null) {
				kunnr.setChannelId(channelId);
			}
			if (StringUtils.isNotEmpty(status)) {
				kunnr.setStatus(status);
			}
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					kunnr.setOrgId(str[1]);
				} else {
					kunnr.setOrgId(orgId);
				}
			}
			if (StringUtils.isNotEmpty(bhxjFlag)) {
				kunnr.setBhxjFlag(bhxjFlag);
			}
			kunnrList = kunnrService.kunnrSearch(kunnr);

			props.add("kunnr");
			props.add("name1");
			props.add("name3");
			props.add("mobNumber");
			props.add("channelName");
			props.add("warehouseArea");
			props.add("natureEnterprise");
			props.add("orgName");
			props.add("street1");
			props.add("telNumber");
			props.add("faxNumber");
			props.add("locco");
			props.add("bukrs");
			props.add("werks");
			props.add("bank");
			props.add("bankAccount");
			props.add("stceg");
			props.add("zcAddress");
			props.add("fpRecipient");
			props.add("fpAddress");
			props.add("fpContactPhone");
			props.add("healthNumber");
			props.add("businessLicense");
			props.add("businessManager");
			props.add("managerMobile");
			props.add("businessCompetent");
			props.add("competentMobile");
			props.add("kunnrLeader");
			props.add("kunnrPhone");
			props.add("street");
			props.add("maximumTxt");
			props.add("name102");
			props.add("name102tel");
			props.add("name102mob");
			props.add("coverArea");

			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String("���������ݵ�����".getBytes("GBK"), ("ISO8859-1")) + ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("����������", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			WritableFont font =
				new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_0 = new Label(0, 0, "�����̴���");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "����������");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "����");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "��ϵ�绰");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "�ͻ�����");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			Label label_5 = new Label(5, 0, "�ֿ����");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			Label label_6 = new Label(6, 0, "��ҵ����");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);
			Label label_7 = new Label(7, 0, "������֯");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);
			Label label_8 = new Label(8, 0, "��˾��ַ");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);
			Label label_9 = new Label(9, 0, "��˾�绰");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);
			Label label_10 = new Label(10, 0, "����");
			label_10.setCellFormat(cellFormat_top);
			wsheet.addCell(label_10);
			Label label_11 = new Label(11, 0, "�����");
			label_11.setCellFormat(cellFormat_top);
			wsheet.addCell(label_11);
			Label label_12 = new Label(12, 0, "��˾����");
			label_12.setCellFormat(cellFormat_top);
			wsheet.addCell(label_12);
			Label label_13 = new Label(13, 0, "��������");
			label_13.setCellFormat(cellFormat_top);
			wsheet.addCell(label_13);
			Label label_14 = new Label(14, 0, "��������");
			label_14.setCellFormat(cellFormat_top);
			wsheet.addCell(label_14);
			Label label_15 = new Label(15, 0, "�����˺�");
			label_15.setCellFormat(cellFormat_top);
			wsheet.addCell(label_15);
			Label label_16 = new Label(16, 0, "˰��Ǽ�֤");
			label_16.setCellFormat(cellFormat_top);
			wsheet.addCell(label_16);
			Label label_17 = new Label(17, 0, "ע���ַ");
			label_17.setCellFormat(cellFormat_top);
			wsheet.addCell(label_17);
			Label label_18 = new Label(18, 0, "��Ʊ�ļ���");
			label_18.setCellFormat(cellFormat_top);
			wsheet.addCell(label_18);
			Label label_19 = new Label(19, 0, "��Ʊ���͵�ַ");
			label_19.setCellFormat(cellFormat_top);
			wsheet.addCell(label_19);
			Label label_20 = new Label(20, 0, "��Ʊ��ϵ�˵绰");
			label_20.setCellFormat(cellFormat_top);
			wsheet.addCell(label_20);
			Label label_21 = new Label(21, 0, "�������֤");
			label_21.setCellFormat(cellFormat_top);
			wsheet.addCell(label_21);

			Label label_22 = new Label(22, 0, "Ӫҵִ��ע���");
			label_22.setCellFormat(cellFormat_top);
			wsheet.addCell(label_22);
			Label label_23 = new Label(23, 0, "ҵ����");
			label_23.setCellFormat(cellFormat_top);
			wsheet.addCell(label_23);
			Label label_24 = new Label(24, 0, "��ϵ�绰");
			label_24.setCellFormat(cellFormat_top);
			wsheet.addCell(label_24);
			Label label_25 = new Label(25, 0, "ҵ������");
			label_25.setCellFormat(cellFormat_top);
			wsheet.addCell(label_25);
			Label label_26 = new Label(26, 0, "��ϵ�绰");
			label_26.setCellFormat(cellFormat_top);
			wsheet.addCell(label_26);

			Label label_27 = new Label(27, 0, "�����̸�����");
			label_27.setCellFormat(cellFormat_top);
			wsheet.addCell(label_27);
			Label label_28 = new Label(28, 0, "�����̸������ֻ�");
			label_28.setCellFormat(cellFormat_top);
			wsheet.addCell(label_28);

			Label label_29 = new Label(29, 0, "�ջ��ֿ�");
			label_29.setCellFormat(cellFormat_top);
			wsheet.addCell(label_29);
			Label label_30 = new Label(30, 0, "����ͨ�г���");
			label_30.setCellFormat(cellFormat_top);
			wsheet.addCell(label_30);
			Label label_31 = new Label(31, 0, "�ջ���");
			label_31.setCellFormat(cellFormat_top);
			wsheet.addCell(label_31);
			Label label_32 = new Label(32, 0, "�ջ�����ϵ�绰");
			label_32.setCellFormat(cellFormat_top);
			wsheet.addCell(label_32);
			Label label_33 = new Label(33, 0, "�ջ�����ϵ�ֻ�");
			label_33.setCellFormat(cellFormat_top);
			wsheet.addCell(label_33);
			Label label_34 = new Label(34, 0, "��Ӫ���Ƿ�Χ");
			label_34.setCellFormat(cellFormat_top);
			wsheet.addCell(label_34);

			ExcelUtil.createExcelWithBook(wbook, props, kunnrList);

		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * �鿴��������Ϣ
	 * 
	 * @return
	 */
	public String kunnrViewInfo() {
		kunnr = new Kunnr();
		kunnr.setKunnr(kunnrId);
		kunnr = kunnrService.getKunnrEntity(kunnr);
		kunnrBusiness = kunnrService.getKunnrBusinessEntity(kunnr);
		kunnrAddressList = kunnrService.getKunnrAddressList(kunnr);
		kunnrPolicy = kunnrService.getKunnrPolicyEntity(kunnr);
		addressListSize = kunnrAddressList != null ? kunnrAddressList.size() : 0;
		kunnrBrandList = kunnrService.getKunnrBrandList(kunnr);
		brandListSize = kunnrBrandList != null ? kunnrBrandList.size() : 0;
		kunnrAcountList = kunnrService.getKunnrAcountList(kunnr);
		acountListSize = kunnrAcountList != null ? kunnrAcountList.size() : 0;
		kunnrLicenseList = kunnrService.getKunnrLicenseList(kunnr);
		licenseListSize = kunnrLicenseList != null ? kunnrLicenseList.size() : 0;
		kunnrSalesAreaList = kunnrService.getKunnrSalesAreaList(kunnr);
		kunnrSalesAreaListSize = kunnrSalesAreaList != null ? kunnrSalesAreaList.size() : 0;
 		
		bKunnrBanklist = kunnrService.getKunnrBanksList(kunnr);
		aKunnrBankSize = bKunnrBanklist != null ? bKunnrBanklist.size() : 0;
		
        // �ͻ�������Ϣ
		// bCustomerTargetList = kunnrService.getku(kunnr);
		// bCustomerTargetListSize = bCustomerTargetList != null ? bCustomerTargetList.size() : 0;
		// �޸������޸�ҳ��
		if ("edit".equals(opera)) {
			AllUsers user = this.getUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			kunnr.setCreateUserId(Long.parseLong(user.getUserId()));
			kunnr.setCreateUser(user.getUserName());
			kunnr.setCreateDate(sdf.format(new Date()));
			userId = user.getUserId();
			return "editapply";
		}
		return "viewinfo";
	}

	 
	 
	 
	public void responseToAjax(String jsonStr){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ������Ӧ�ĸ�ʽ
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	 
	 
	
	
	
	
	public List<KunnrLogisticsArea> getlAreaList() {
		return lAreaList;
	}

	public void setlAreaList(List<KunnrLogisticsArea> lAreaList) {
		this.lAreaList = lAreaList;
	}

	public String getVkgrp() {
		return vkgrp;
	}

	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}

	public String getBzirk() {
		return bzirk;
	}

	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public Kunnr getKunnr() {
		return kunnr;
	}

	public void setKunnr(Kunnr kunnr) {
		this.kunnr = kunnr;
	}

	 

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public List<KunnrBusiness> getKunnrBusinessList() {
		return kunnrBusinessList;
	}

	public void setKunnrBusinessList(List<KunnrBusiness> kunnrBusinessList) {
		this.kunnrBusinessList = kunnrBusinessList;
	}

	public List<KunnrAddress> getKunnrAddressList() {
		return kunnrAddressList;
	}

	public void setKunnrAddressList(List<KunnrAddress> kunnrAddressList) {
		this.kunnrAddressList = kunnrAddressList;
	}

	public List<KunnrBrand> getKunnrBrandList() {
		return kunnrBrandList;
	}

	public void setKunnrBrandList(List<KunnrBrand> kunnrBrandList) {
		this.kunnrBrandList = kunnrBrandList;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}

	public KunnrBusiness getKunnrBusiness() {
		return kunnrBusiness;
	}

	public void setKunnrBusiness(KunnrBusiness kunnrBusiness) {
		this.kunnrBusiness = kunnrBusiness;
	}

	public List<Kunnr> getKunnrList() {
		return kunnrList;
	}

	public void setKunnrList(List<Kunnr> kunnrList) {
		this.kunnrList = kunnrList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getAttachmentsName() {
		return attachmentsName;
	}

	public void setAttachmentsName(String attachmentsName) {
		this.attachmentsName = attachmentsName;
	}

	public String getAttachmentsPath() {
		return attachmentsPath;
	}

	public void setAttachmentsPath(String attachmentsPath) {
		this.attachmentsPath = attachmentsPath;
	}

	 
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getFreezeOrClose() {
		return freezeOrClose;
	}

	public void setFreezeOrClose(String freezeOrClose) {
		this.freezeOrClose = freezeOrClose;
	}

	public List<KunnrAcount> getKunnrAcountList() {
		return kunnrAcountList;
	}

	public void setKunnrAcountList(List<KunnrAcount> kunnrAcountList) {
		this.kunnrAcountList = kunnrAcountList;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	public String getBusinessCompetent() {
		return businessCompetent;
	}

	public void setBusinessCompetent(String businessCompetent) {
		this.businessCompetent = businessCompetent;
	}

	public BooleanResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}

	public String getKunnrCode() {
		return kunnrCode;
	}

	public void setKunnrCode(String kunnrCode) {
		this.kunnrCode = kunnrCode;
	}

	public String getOpera() {
		return opera;
	}

	public void setOpera(String opera) {
		this.opera = opera;
	}

	public int getAcountListSize() {
		return acountListSize;
	}

	public void setAcountListSize(int acountListSize) {
		this.acountListSize = acountListSize;
	}

	public int getAddressListSize() {
		return addressListSize;
	}

	public void setAddressListSize(int addressListSize) {
		this.addressListSize = addressListSize;
	}

	public int getBrandListSize() {
		return brandListSize;
	}

	public void setBrandListSize(int brandListSize) {
		this.brandListSize = brandListSize;
	}

	 
	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	 

	public String[] getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String[] licenseName) {
		this.licenseName = licenseName;
	}

	public File[] getLicense() {
		return license;
	}

	public void setLicense(File[] license) {
		this.license = license;
	}

	public String[] getLicenseFileName() {
		return licenseFileName;
	}

	public void setLicenseFileName(String[] licenseFileName) {
		this.licenseFileName = licenseFileName;
	}

	public Date[] getLicenseValid() {
		return licenseValid;
	}

	public void setLicenseValid(Date[] licenseValid) {
		this.licenseValid = licenseValid;
	}

	public List<KunnrLicense> getKunnrLicenseList() {
		return kunnrLicenseList;
	}

	public void setKunnrLicenseList(List<KunnrLicense> kunnrLicenseList) {
		this.kunnrLicenseList = kunnrLicenseList;
	}

	public int getLicenseListSize() {
		return licenseListSize;
	}

	public void setLicenseListSize(int licenseListSize) {
		this.licenseListSize = licenseListSize;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public KunnrSalesArea getKunnrSalesArea() {
		return kunnrSalesArea;
	}

	public void setKunnrSalesArea(KunnrSalesArea kunnrSalesArea) {
		this.kunnrSalesArea = kunnrSalesArea;
	}

	public List<KunnrSalesArea> getKunnrSalesAreaList() {
		return kunnrSalesAreaList;
	}

	public void setKunnrSalesAreaList(List<KunnrSalesArea> kunnrSalesAreaList) {
		this.kunnrSalesAreaList = kunnrSalesAreaList;
	}

	 

	public int getKunnrSalesAreaListSize() {
		return kunnrSalesAreaListSize;
	}

	public void setKunnrSalesAreaListSize(int kunnrSalesAreaListSize) {
		this.kunnrSalesAreaListSize = kunnrSalesAreaListSize;
	}

	public int getbCustomerTargetListSize() {
		return bCustomerTargetListSize;
	}

	public void setbCustomerTargetListSize(int bCustomerTargetListSize) {
		this.bCustomerTargetListSize = bCustomerTargetListSize;
	}

	public List<KunnrLogisticsArea> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<KunnrLogisticsArea> areaList) {
		this.areaList = areaList;
	}

	public List<KunnrLogisticsArea> getAreaLists() {
		return areaLists;
	}

	public void setAreaLists(List<KunnrLogisticsArea> areaLists) {
		this.areaLists = areaLists;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	 

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public static String getCustomerManagement() {
		return customerManagement;
	}

	public static void setCustomerManagement(String customerManagement) {
		KunnrAction.customerManagement = customerManagement;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getZgFlag() {
		return zgFlag;
	}

	public void setZgFlag(String zgFlag) {
		this.zgFlag = zgFlag;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getNameUpdateFile() {
		return nameUpdateFile;
	}

	public void setNameUpdateFile(String nameUpdateFile) {
		this.nameUpdateFile = nameUpdateFile;
	}

	public String getNameUpdatePath() {
		return nameUpdatePath;
	}

	public void setNameUpdatePath(String nameUpdatePath) {
		this.nameUpdatePath = nameUpdatePath;
	}

	public String getFreezeButton() {
		return freezeButton;
	}

	public void setFreezeButton(String freezeButton) {
		this.freezeButton = freezeButton;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File[] getNameUpload() {
		return nameUpload;
	}

	public void setNameUpload(File[] nameUpload) {
		this.nameUpload = nameUpload;
	}

	public String[] getNameUpdateFileName() {
		return nameUpdateFileName;
	}

	public void setNameUpdateFileName(String[] nameUpdateFileName) {
		this.nameUpdateFileName = nameUpdateFileName;
	}

	public String getLocco() {
		return locco;
	}

	public void setLocco(String locco) {
		this.locco = locco;
	}

	public List<KunnrSalesArea> getKillSalesArea() {
		return killSalesArea;
	}

	public void setKillSalesArea(List<KunnrSalesArea> killSalesArea) {
		this.killSalesArea = killSalesArea;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getKonzs() {
		return konzs;
	}

	public void setKonzs(String konzs) {
		this.konzs = konzs;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public List<KunnrBanks> getKunnrBanklist() {
		return kunnrBanklist;
	}

	public void setKunnrBanklist(List<KunnrBanks> kunnrBanklist) {
		this.kunnrBanklist = kunnrBanklist;
	}

	public List<KunnrBanks> getbKunnrBanklist() {
		return bKunnrBanklist;
	}

	public void setbKunnrBanklist(List<KunnrBanks> bKunnrBanklist) {
		this.bKunnrBanklist = bKunnrBanklist;
	}

	public int getaKunnrBankSize() {
		return aKunnrBankSize;
	}

	public void setaKunnrBankSize(int aKunnrBankSize) {
		this.aKunnrBankSize = aKunnrBankSize;
	}

	public KunnrPolicy getKunnrPolicy() {
		return kunnrPolicy;
	}

	public void setKunnrPolicy(KunnrPolicy kunnrPolicy) {
		this.kunnrPolicy = kunnrPolicy;
	}

	public List<KunnrPolicy> getKunnrPolicyList() {
		return kunnrPolicyList;
	}

	public void setKunnrPolicyList(List<KunnrPolicy> kunnrPolicyList) {
		this.kunnrPolicyList = kunnrPolicyList;
	}

	public int getKunnrPolicyListSize() {
		return kunnrPolicyListSize;
	}

	public void setKunnrPolicyListSize(int kunnrPolicyListSize) {
		this.kunnrPolicyListSize = kunnrPolicyListSize;
	}


	public KunnrPunish getKunnrPunish() {
		return kunnrPunish;
	}


	public void setKunnrPunish(KunnrPunish kunnrPunish) {
		this.kunnrPunish = kunnrPunish;
	}


	public List<KunnrPunish> getKunnrPunishList() {
		return kunnrPunishList;
	}


	public void setKunnrPunishList(List<KunnrPunish> kunnrPunishList) {
		this.kunnrPunishList = kunnrPunishList;
	}


	public String getKonzsTxt() {
		return konzsTxt;
	}


	public void setKonzsTxt(String konzsTxt) {
		this.konzsTxt = konzsTxt;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getKunnrErp() {
		return kunnrErp;
	}

	public void setKunnrErp(String kunnrErp) {
		this.kunnrErp = kunnrErp;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
  
	public String getMaximum() {
		return maximum;
	}


	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}


	public String getBillType() {
		return billType;
	}


	public void setBillType(String billType) {
		this.billType = billType;
	}


	public List<Knvp> getKnvpList() {
		return knvpList;
	}


	public void setKnvpList(List<Knvp> knvpList) {
		this.knvpList = knvpList;
	}


	public String getParvw() {
		return parvw;
	}


	public void setParvw(String parvw) {
		this.parvw = parvw;
	}


	public String getKunnrWe() {
		return kunnrWe;
	}


	public void setKunnrWe(String kunnrWe) {
		this.kunnrWe = kunnrWe;
	}
 

	public Properties getEnv() {
		return env;
	}


	public void setEnv(Properties env) {
		this.env = env;
	}


	public IAllUserService getAllUserServiceHessian() {
		return allUserServiceHessian;
	}


	public void setAllUserServiceHessian(IAllUserService allUserServiceHessian) {
		this.allUserServiceHessian = allUserServiceHessian;
	}
 
	
}
