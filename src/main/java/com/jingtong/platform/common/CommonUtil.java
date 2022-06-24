package com.jingtong.platform.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    // ���õ���excle�ļ���Ӧ��response������Ϣ
    public static void setExcelResponseInfo(HttpServletRequest request, HttpServletResponse response, String fileName) {
        response.setContentType("application/binary;charset=utf-8");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Pragma", "private");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Type", "application/force-download");
        fileName = CommonUtil.processFileName(request, fileName);
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// ��װ�������ƺ͸�ʽ
    }

    // ������Excle�ļ�������������
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {// ie
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");
                codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// ���,chrome��
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    }

    public static List<Map<String, String>> listBean2ListMap(List<T> listobj) {
        if (listobj == null) {
            return null;
        }
        List<Map<String, String>> li = new ArrayList<Map<String, String>>();
        for (T p : listobj) {
            li.add(CommonUtil.transBean2Map(p));
        }
        return li;
    }

    // bean����ת��Map����
    public static Map<String, String> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // ����class����
                if (!key.equals("class")) {
                    // �õ�property��Ӧ��getter����
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, String.valueOf(value));
                }

            }
        } catch (Exception e) {
            LOGGER.error("transBean2Map Error {}", e);
        }
        return map;

    }

    // ����Excel
    public static void exportExcel(List<Map<String, String>> list, String[] titles, String[] keys,
            ServletOutputStream outputStream) throws Exception {
        try {
            // ����һ��workbook ��Ӧһ��excelӦ���ļ�
            // XSSFWorkbook workBook = new XSSFWorkbook();

            int rowaccess = 100;// �ڴ��л����¼����
            /* keep 100 rows in memory,exceeding rows will be flushed to disk */
            SXSSFWorkbook workBook = new SXSSFWorkbook(rowaccess);

            // ��workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
            // XSSFSheet sheet = workBook.createSheet("sheet1");
            Sheet sheet = workBook.createSheet();
            // ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            // XSSFCellStyle headStyle = exportUtil.getHeadStyle();//��ͷ��Ԫ����ʽ
//			XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();//���嵥Ԫ����ʽ

            // ������ͷ
            // XSSFRow headRow = sheet.createRow(0);
            Row rowheader = sheet.createRow(0);
            // ���ÿ�ȣ���һ�� 50���ַ���ȣ�
            // sheet.setColumnWidth((short) 0, 50 * 256);
            // XSSFCell cell = null;
            Cell cellheader = null;
            for (int i = 0; i < titles.length; i++) {
                // cell = headRow.createCell(i);
                cellheader = rowheader.createCell(i);
                // cell.setCellStyle(headStyle);
                cellheader.setCellValue(titles[i]);
            }
            if (list != null && list.size() > 0) {
                for (int j = 0; j < list.size(); j++) {
                    // XSSFRow bodyRow = sheet.createRow(j + 1);
                    Row row = sheet.createRow(j + 1);
                    Map<String, String> goods = list.get(j);
                    for (int k = 0; k < keys.length; k++) {
                        Cell cell = row.createCell(k);
                        String cellvalueString = "";
                        if (goods.get(keys[k]) != null) {
                            cellvalueString = String.valueOf(goods.get(keys[k]));
                        }
                        if ("null".equals(cellvalueString) || "".equals(cellvalueString)) {
                            cell.setCellValue("");
                        } else {
                            Boolean isNum = false;// data�Ƿ�Ϊ��ֵ��
                            Boolean isNumKexue = false; // ��ѧ������
                            Boolean isPercent = false;// data�Ƿ�Ϊ�ٷ���
                            if (cellvalueString != null || "".equals(cellvalueString)) {
                                // �ж�data�Ƿ�Ϊ��ֵ��
                                isNum = cellvalueString.toString().matches("^(-?\\d+)(\\.\\d+)?$");

                                isNumKexue = cellvalueString.toString().matches("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");
                                // �ж�data�Ƿ�Ϊ�ٷ������Ƿ������%����
                                isPercent = cellvalueString.toString().contains("%");
                            }
                            CellStyle contextstyle = workBook.createCellStyle();// .createCellStyle();
                            // �����Ԫ����������ֵ���ͣ��漰����Ǯ��������������������cell������Ϊ��ֵ�ͣ�����data������Ϊ��ֵ����
                            if ((isNum || isNumKexue) && !isPercent && "qty".equals(keys[k])
                                    || "res_qty".equals(keys[k]) || "amount".equals(keys[k])
                                    || "target_cost".equals(keys[k]) || "target_resale".equals(keys[k])
                                    || "sale_price".equals(keys[k]) || "stop_price".equals(keys[k])
                                    || "suggest_cost".equals(keys[k]) || "suggest_resale".equals(keys[k])) {
                                DataFormat df = workBook.createDataFormat();// .createDataFormat(); // �˴��������ݸ�ʽ

                                if ("qty".equals(keys[k]) || "res_qty".equals(keys[k])) {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,##0"));// ���ݸ�ʽֻ��ʾ����
                                    // ���õ�Ԫ���ʽ
                                    cell.setCellStyle(contextstyle);
                                    BigDecimal bd = new BigDecimal(Double.valueOf(cellvalueString));

                                    // ss = String.valueOf(d1.format(Double.valueOf(cellvalueString))) ;
                                    cell.setCellValue(Double.parseDouble(bd.toPlainString()));
                                } else if ("target_cost".equals(keys[k]) || "target_resale".equals(keys[k])
                                        || "suggest_cost".equals(keys[k]) || "suggest_resale".equals(keys[k])
                                        || "sale_price".equals(keys[k]) || "stop_price".equals(keys[k])) {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,###,##0.0000"));// ���ݸ�ʽֻ��ʾ����
                                    // ���õ�Ԫ���ʽ
                                    cell.setCellStyle(contextstyle);
                                    // ���õ�Ԫ������Ϊdouble����
                                    String ss = "";
                                    DecimalFormat d1 = new DecimalFormat("##########0.0000");
                                    ss = String.valueOf(d1.format(Double.valueOf(cellvalueString)));
                                    cell.setCellValue(Double.parseDouble(ss.toString()));
                                } else {
                                    contextstyle.setDataFormat(df.getFormat("#,###,###,###,###,##0.00"));// ������λС����
                                    // ���õ�Ԫ���ʽ
                                    cell.setCellStyle(contextstyle);
                                    // ���õ�Ԫ������Ϊdouble����
                                    cell.setCellValue(Double.parseDouble(cellvalueString.toString()));
                                }

                            } else if ("qty".equals(keys[k])) {
                                DataFormat df1 = workBook.createDataFormat();
                                // ���õ�Ԫ���ʽ
                                contextstyle.setDataFormat(df1.getFormat("#,###,###,###,##0"));
                                cell.setCellStyle(contextstyle);
                                BigDecimal bd = new BigDecimal(Double.valueOf(cellvalueString));

                                // ss = String.valueOf(d1.format(Double.valueOf(cellvalueString))) ;
                                cell.setCellValue(Double.parseDouble(bd.toPlainString()));
                            } else {
                                cell.setCellStyle(contextstyle);
                                // ���õ�Ԫ������Ϊ�ַ���
                                cell.setCellValue(cellvalueString.toString());
                            }
                        }
                    }
                    // ÿ�������ﵽ���õ�ֵ��ˢ�����ݵ�Ӳ��,�������ڴ�
                    if (j % rowaccess == 0) {
                        ((SXSSFSheet) sheet).flushRows();
                    }
                }
            }
            try {
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * ȥ���ַ�����ͷ�ͽ�β���ַ� * @param stream Ҫ������ַ���
     * 
     * @param trimstr Ҫȥ�����ַ���
     * 
     * @param leftOrRight ȥ����߻����ұߣ�����2�� ���"l" �ұ�"r"
     * 
     * @return �������ַ���
     */
    public static String sideTrim(String stream, String trimstr, String leftOrRight) {
        // null���߿��ַ�����ʱ�򲻴���
        if (stream == null || stream.length() == 0 || trimstr == null || trimstr.length() == 0) {
            return stream;
        }

        // ����λ��
        int epos = 0;

        // ������ʽ
        String regpattern = "[" + trimstr + "]*+";
        Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

        // ȥ����β��ָ���ַ�
        StringBuffer buffer = new StringBuffer(stream).reverse();
        Matcher matcher = pattern.matcher(buffer);
        if ("R".equals(leftOrRight)) {
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
            }
        } else if ("L".equals(leftOrRight)) { // ȥ����ͷ��ָ���ַ�
            matcher = pattern.matcher(stream);
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = stream.substring(epos);
            }
        } else {
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = new StringBuffer(buffer.substring(epos)).reverse().toString();
            }
            // ȥ����ͷ��ָ���ַ�
            matcher = pattern.matcher(stream);
            if (matcher.lookingAt()) {
                epos = matcher.end();
                stream = stream.substring(epos);
            }
        }

        // ���ش������ַ���
        return stream;
    }

}
