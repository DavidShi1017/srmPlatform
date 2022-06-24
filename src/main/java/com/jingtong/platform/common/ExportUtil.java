package com.jingtong.platform.common;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportUtil {
	private XSSFWorkbook wb = null;

	private XSSFSheet sheet = null;

	/**
	 * @param wb
	 * @param sheet
	 */
	public ExportUtil(XSSFWorkbook wb, XSSFSheet sheet) {
		this.wb = wb;
		this.sheet = sheet;
	}

	/**
	 * �ϲ���Ԫ�����ϲ���ĵ�Ԫ��ӱ߿�
	 * 
	 * @param region
	 * @param cs
	 */
	public void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
												// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * ���ñ�ͷ�ĵ�Ԫ����ʽ
	 * 
	 * @return
	 */
	public XSSFCellStyle getHeadStyle() {
		// ������Ԫ������
		XSSFCellStyle cellStyle = wb.createCellStyle();
		//  ���õ�Ԫ����жԳ�
		cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		// ���õ�Ԫ��ֱ���жԳ�
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// ������Ԫ��������ʾ����ʱ�Զ�����
		cellStyle.setWrapText(true);
		// ���õ�Ԫ��������
		XSSFFont font = wb.createFont();
		// ��������Ӵ�
		font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("Arial");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// ���õ�Ԫ��߿�Ϊϸ��?
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * ���ñ���ĵ�Ԫ����ʽ
	 * 
	 * @return
	 */
	public XSSFCellStyle getBodyStyle() {
		// 创建单元格样�?
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对�?
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		// ���õ�Ԫ��ֱ���жԳ�
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// ������Ԫ��������ʾ����ʱ�Զ�����
		cellStyle.setWrapText(false);
		// 设置单元格字体样�?
		XSSFFont font = wb.createFont();
		// ��������Ӵ�
		font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("Arial"); //����
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// ���õ�Ԫ��߿�Ϊϸ��?
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_NONE);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_NONE);
		return cellStyle;
	}
}