package com.jingtong.platform.product.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.jingtong.platform.base.action.BaseAction;
import com.jingtong.platform.framework.annotations.JsonResult;
import com.jingtong.platform.framework.annotations.PermissionSearch;
import com.jingtong.platform.framework.util.ExcelUtil;
import com.jingtong.platform.framework.util.StockUtil;
import com.jingtong.platform.product.pojo.Product;
import com.jingtong.platform.product.service.IProductService;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ProductAction extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private IProductService productService;
    private Product p;
    private List<Product> pList;
    private String id;
    private String state;
    private String material_id;
    private String oldMaterial_id;
    private String material_name;
    private int total;
    private String uploadFile;
    private String isDRItem;
    private String isOrderItem;
    private String isQuoteItem;
    private String useFor;
    private String currency_code;
    private String office_id;
    private String customer_id;
    private String path;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String toSearchProduct() {
        return "toSearchProduct";
    }

    public String toSearchProductForUse() {
        String role = this.getUser().getRoleIds();
        useFor = role;
        return "toSearchProductForUse";
    }

    public String importExcel() {
        return "importExcel";
    }

    public String toViewProduct() {
        p = new Product();
        p.setId(Long.valueOf(id));
        p = productService.getProductById(p);
        return "toViewProduct";
    }

    public String toCreateProduct() {
        return "toCreateProduct";
    }

    public String toUpdateProduct() {
        return "toCreateProduct";
    }

    public String toAuditOpinion() {
        p = new Product();
        p.setId(Long.valueOf(id));
        p.setState(Integer.valueOf(state));
        p = productService.getProductById(p);
        return "toAuditOpinion";
    }

    public String toSyncProduct() {
        return "toSyncProduct";
    }

    /**
     * ��ѯ������Ϣ
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "pList", include = { "id", "material_id", "material_name", "material_exp", "material_type",
            "division", "lead_time", "base_unit", "sale_unit", "unit_change", "isOrderItem", "perUnit", "isQuoteItem",
            "isDRItem", "limited_qty", "moq", "cost", "customer_id", "state", "isDeleted", "sortId", "create_time",
            "create_userId", "material_groupId", "pbPrice", "latest_time", "latest_userId", "latest_deptId",
            "customer_group", "numerator", "denominator", "pq", "isLocked", "rfs_date" }, total = "total")
    public String getProductList() {
        try {
            p = new Product();
            p.setStart(getStart());
            p.setEnd(getEnd());
            p.setSort("aa.id");
            p.setDir("desc");
            p.setMaterial_id(material_id);
            if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
                try {
                    material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                    material_name = material_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            p.setIsDRItem(isDRItem);
            p.setIsOrderItem(isOrderItem);
            p.setIsQuoteItem(isQuoteItem);
            p.setUseFor(useFor);
            p.setMaterial_name(material_name);
            // p.setState(Integer.valueOf(state));
            total = productService.getProductListCount(p);
            if (total > 0) {
                pList = productService.getProductList(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * ��ѯDR��Ϣ
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "pList", include = { "id", "material_id", "material_name", "material_exp", "material_type",
            "division", "lead_time", "base_unit", "sale_unit", "unit_change", "isOrderItem", "perUnit", "isQuoteItem",
            "isDRItem", "limited_qty", "moq", "cost", "customer_id", "state", "isDeleted", "sortId", "create_time",
            "create_userId", "material_groupId", "pbPrice", "latest_time", "latest_userId", "latest_deptId",
            "customer_group", "numerator", "denominator", "pq", "isLocked" }, total = "total")
    public String getDRProductList() {
        try {
            p = new Product();
            p.setStart(getStart());
            p.setEnd(getEnd());
            p.setSort("aa.id");
            p.setDir("desc");
            p.setMaterial_id(material_id);
            if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
                try {
                    material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                    material_name = material_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            p.setCurrency_code(currency_code);
            p.setMaterial_name(material_name);
            p.setOffice_id(office_id);
            p.setCustomer_id(customer_id);
            p.setIsDRItem(isDRItem);
            p.setIsOrderItem(isOrderItem);
            p.setIsQuoteItem(isQuoteItem);
            // p.setState(Integer.valueOf(state));
            total = productService.getDRProductListCount(p);
            if (total > 0) {
                pList = productService.getDRProductList(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * ��ѯQuote����
     */

    /**
     * ��ѯDR��Ϣ
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "pList", include = { "id", "material_id", "material_name", "material_exp", "material_type",
            "division", "lead_time", "base_unit", "sale_unit", "unit_change", "isOrderItem", "perUnit", "isQuoteItem",
            "isDRItem", "limited_qty", "moq", "cost", "customer_id", "state", "isDeleted", "sortId", "create_time",
            "create_userId", "material_groupId", "pbPrice", "latest_time", "latest_userId", "latest_deptId",
            "customer_group", "numerator", "denominator", "pq", "isLocked" }, total = "total")
    public String getQuoteProductList() {
        try {
            p = new Product();
            p.setStart(getStart());
            p.setEnd(getEnd());
            p.setSort("aa.id");
            p.setDir("desc");
            p.setMaterial_id(material_id);
            if (StringUtils.isNotEmpty(material_name) && StringUtils.isNotEmpty(material_name.trim())) {
                try {
                    material_name = java.net.URLDecoder.decode(material_name, "UTF-8");
                    material_name = material_name.toUpperCase();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            p.setCurrency_code(currency_code);
            p.setMaterial_name(material_name);
            p.setOffice_id(office_id);
            p.setCustomer_id(customer_id);

            total = productService.getQuoteProductListCount(p);
            if (total > 0) {
                pList = productService.getQuoteProductList(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON;
    }

    /**
     * �����Ϣ
     * 
     * @return
     */
    public String createProduct() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        p.setCreate_userId(this.getUser().getUserId());

        long result = productService.createProduct(p);
        if (result > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }

        return RESULT_MESSAGE;
    }

    /**
     * �޸���Ϣ
     * 
     * @return
     */
    public String updateProduct() {
        this.setSuccessMessage("");
        this.setFailMessage("");
        p.setLatest_userId(this.getUser().getUserId());

        long result = productService.updateProduct(p);
        if (result > 0) {
            this.setSuccessMessage("Success!");
        } else {
            this.setFailMessage("Failed!");
        }
        return RESULT_MESSAGE;
    }

    /**
     * �����Ϣ
     * 
     * @return
     */
    public String deleteProduct() {
        this.setFailMessage("");
        this.setSuccessMessage("");
        p = new Product();
        p.setId(Long.valueOf(id));
        int i = productService.deleteProduct(p);
        if (i > 0) {
            this.setSuccessMessage("Success !");
        } else {
            this.setFailMessage("failed !");
        }
        return RESULT_MESSAGE;
    }

    public String importData() {
        // ��������
        FileInputStream fileIn = null;
        Workbook rwb = null;
        List<Product> pList = new ArrayList<Product>();
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** ȥ�����еõ���ʵ���� **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                this.setFailMessage("�����ExcelΪ�գ�");
                return RESULT_MESSAGE;
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "12NC  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "Lead Time  is not completed yet!");
                        break;
                    }

                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "Order Item  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(4, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "Quote Item  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(5, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "DR Item  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(6, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "Limited QTY  is not completed yet!");
                        break;
                    }

                }

                if (!"".equals(contentResult.toString())) {
                    this.setFailMessage(contentResult.toString());
                    return RESULT_MESSAGE;
                }

                for (int i = 1; i < actualRows; i++) {
                    p = new Product();
                    p.setMaterial_id(rs.getCell(0, i).getContents().trim());
                    p.setMaterial_name(rs.getCell(1, i).getContents().trim());
                    p.setLead_time(Double.valueOf(rs.getCell(2, i).getContents().trim()));
                    p.setIsOrderItem(rs.getCell(3, i).getContents().trim());
                    p.setIsQuoteItem(rs.getCell(4, i).getContents().trim());
                    p.setIsDRItem(rs.getCell(5, i).getContents().trim());
                    p.setLimited_qty(Double.valueOf(rs.getCell(6, i).getContents().trim()));
                    p.setLatest_userId(this.getUser().getUserId());
                    pList.add(p);
                }
            }
            int result = 1;
            for (Product product : pList) {
                result = productService.updateProduct(product);
            }
            if (result > 0) {
                this.setSuccessMessage("Success !");
            } else {
                this.setFailMessage("failed !" + contentResult.toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            this.setFailMessage("Excelģ�����");
        } catch (BiffException e) {

            this.setFailMessage("03���ϰ汾Excel�����ݲ�֧��");
            return RESULT_MESSAGE;
        } catch (Exception e) {

            this.setFailMessage("failed (�������ݸ�ʽ����)��");
            return RESULT_MESSAGE;
        }
        return RESULT_MESSAGE;

    }

    public void downloadExcelModel() {
        try {

            List<String> list = new ArrayList<String>();
            list.add("12NC");
            list.add("BookPart");
            list.add("Lead Time");
            list.add("Order Item");
            list.add("Quote Item");
            list.add("DR Item");
            list.add("Sample Limited QTY");

            File source = new File("ProductInfo.xls");
            WritableWorkbook wwb = Workbook.createWorkbook(source);
            WritableSheet sheet = wwb.createSheet("ProductInfo", 0);
            Label label = null;
            Label label2 = null;
            Label label3 = null;
            Label label4 = null;
            Label label5 = null;
            Label label6 = null;
            Label label7 = null;
            // ��������;
            WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.RED);

            WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
            // ���ñ�����ɫ;
            cellFormat1.setBackground(Colour.YELLOW);
            // ���ñ߿�;
            cellFormat1.setBorder(Border.ALL, BorderLineStyle.HAIR);
            for (int i = 0; i < list.size(); i++) {
                // Label(x,y,z)����x����Ԫ��ĵ�x+1�У���y+1��, ��Ԫ���������y
                // ��Label������Ӷ�����ָ����Ԫ���λ�ú�����
                label = new Label(i, 0, list.get(i).toString(), cellFormat1);
                // ������õĵ�Ԫ����ӵ���������
                sheet.addCell(label);
            }

            List<Product> pList = new ArrayList<Product>();
            p = new Product();
            p.setIsDownLoad("Y");
            pList = productService.getProductListNoPage(p);

            for (int i = 0; i < pList.size(); i++) {
                // Label(x,y,z)����x����Ԫ��ĵ�x+1�У���y+1��, ��Ԫ���������y
                // ��Label������Ӷ�����ָ����Ԫ���λ�ú�����
                label = new Label(0, i + 1, pList.get(i).getMaterial_id());
                label2 = new Label(1, i + 1, pList.get(i).getMaterial_name());
                label3 = new Label(2, i + 1, String.valueOf(pList.get(i).getLead_time()));
                label4 = new Label(3, i + 1, pList.get(i).getIsOrderItem());
                label5 = new Label(4, i + 1, pList.get(i).getIsQuoteItem());
                label6 = new Label(5, i + 1, pList.get(i).getIsDRItem());
                label7 = new Label(6, i + 1, String.valueOf(pList.get(i).getLimited_qty()));
                // ������õĵ�Ԫ����ӵ���������
                sheet.addCell(label);
                sheet.setColumnView(0, 30);
                sheet.addCell(label2);
                sheet.setColumnView(1, 40);
                sheet.addCell(label3);
                sheet.setColumnView(2, 30);
                sheet.addCell(label4);
                sheet.setColumnView(3, 30);
                sheet.addCell(label5);
                sheet.setColumnView(4, 30);
                sheet.addCell(label6);
                sheet.setColumnView(5, 30);
                sheet.addCell(label7);
                sheet.setColumnView(6, 30);
            }
            wwb.write(); // д������
            wwb.close(); // �ر��ļ�

            display(source, "ProductInfo.xls", ServletActionContext.getResponse());

        } catch (Exception e) {
            this.setFailMessage("ProductInfoģ�浼������");
        }
    }

    private boolean display(File file, String fileName, HttpServletResponse response) {
        FileInputStream in = null;
        OutputStream out = null;
        try {
            fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName);
            in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            response.flushBuffer();
        } catch (Exception ex) {
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (final Exception e) {
                    return false;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public IProductService getProductService() {
        return productService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public List<Product> getpList() {
        return pList;
    }

    public void setpList(List<Product> pList) {
        this.pList = pList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public String getIsDRItem() {
        return isDRItem;
    }

    public void setIsDRItem(String isDRItem) {
        this.isDRItem = isDRItem;
    }

    public String getIsOrderItem() {
        return isOrderItem;
    }

    public void setIsOrderItem(String isOrderItem) {
        this.isOrderItem = isOrderItem;
    }

    public String getIsQuoteItem() {
        return isQuoteItem;
    }

    public void setIsQuoteItem(String isQuoteItem) {
        this.isQuoteItem = isQuoteItem;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getOldMaterial_id() {
        return oldMaterial_id;
    }

    public void setOldMaterial_id(String oldMaterial_id) {
        this.oldMaterial_id = oldMaterial_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getOffice_id() {
        return office_id;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String findOrderExcelXlsx(String path) {
        StringBuilder contentResult = new StringBuilder();
        List<Product> pList = new ArrayList<Product>();
        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook xwb = new XSSFWorkbook(file);
            XSSFSheet sheet = xwb.getSheetAt(0);
            // ���� row��cell
            XSSFRow row;
            // ѭ���������е�����
            if (sheet.getPhysicalNumberOfRows() <= 1) {
                return "�����ExcelΪ�գ�";
            }
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row == null) {// ��������
                    continue;
                }
                p = new Product();
                XSSFCell cell0 = row.getCell(0);
                if (cell0 != null) {
                    if (cell0.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        // ���ָ�ʽ�޷�����
                        cell0.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    if ("".equals(cell0.getStringCellValue())) {// ���ϱ���Ϊ�����������账����Ը߰汾���һ�������⣩
                        continue;
                    }
                    p.setMaterial_id(cell0.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": 12NC  is not completed yet!");
                    break;
                }
                XSSFCell cell1 = row.getCell(1);
                if (cell1 != null) {
                    if ("".equals(cell1.getStringCellValue())) {// ��������Ϊ�����������账����Ը߰汾���һ�������⣩
                        continue;
                    }
                    p.setMaterial_name(cell1.getStringCellValue().trim());
                }
                XSSFCell cell2 = row.getCell(2);
                if (cell2 != null) {
                    if (cell2.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        p.setLead_time(cell2.getNumericCellValue());
                    } else {
                        contentResult.append("Row" + i + ": Lead_time format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": Lead_time  is not completed yet!");
                    break;
                }
                XSSFCell cell3 = row.getCell(3);
                if (cell3 != null && !"".equals(cell3.getStringCellValue().trim())) {
                    p.setIsOrderItem(cell3.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": OrderItem  is not completed yet!");
                    break;
                }
                XSSFCell cell4 = row.getCell(4);
                if (cell4 != null && !"".equals(cell4.getStringCellValue().trim())) {
                    p.setIsQuoteItem(cell4.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": QuoteItem  is not completed yet!");
                    break;
                }
                XSSFCell cell5 = row.getCell(5);
                if (cell5 != null && !"".equals(cell5.getStringCellValue().trim())) {
                    p.setIsDRItem(cell5.getStringCellValue().trim());
                } else {
                    contentResult.append("Row" + i + ": DRItem  is not completed yet!");
                    break;
                }
                XSSFCell cell6 = row.getCell(6);
                if (cell6 != null) {
                    if (cell6.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        p.setLimited_qty(cell6.getNumericCellValue());
                    } else {
                        contentResult.append("Row" + i + ": Limited Qty format error!");
                        break;
                    }
                } else {
                    contentResult.append("Row" + i + ": Limited Qty  is not completed yet!");
                    break;
                }
                p.setLatest_userId(this.getUser().getUserId());
                pList.add(p);
            }
            int result = 1;
            for (Product product : pList) {
                result = productService.updateProduct(product);
                if (result == 0) {
                    contentResult.append(product.getMaterial_id() + "is not exist!");
                    break;
                }
            }
            if (!"".equals(contentResult.toString())) {
                return contentResult.toString();
            }

            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "failed (�������ݸ�ʽ����)��";
        }

        return "Success";
    }

    /**
     * 
     * 
     * @param path
     * @return
     */
    private String findOrderExcelXls(String path) {

        // ��������
        FileInputStream fileIn = null;
        Workbook rwb = null;
        List<Product> pList = new ArrayList<Product>();
        StringBuilder contentResult = new StringBuilder();
        try {
            fileIn = new FileInputStream(uploadFile);
            rwb = Workbook.getWorkbook(fileIn);
            Sheet rs = rwb.getSheet(0);
            int column = 0;
            column = rs.getColumns();
            int actualRows = 0;
            /** ȥ�����еõ���ʵ���� **/
            actualRows = StockUtil.delEmptyRow(rs);
            if (actualRows == 0 && column == 0) {
                return "�����ExcelΪ�գ�";
            } else {

                for (int i = 1; i < actualRows; i++) {
                    if ("".equals(rs.getCell(0, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                        contentResult.append("row" + i + "12NC  is not completed yet!");
                        break;
                    }
                    if ("".equals(rs.getCell(2, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                    }

                    if ("".equals(rs.getCell(3, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                    }
                    if ("".equals(rs.getCell(4, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                    }
                    if ("".equals(rs.getCell(5, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                    }
                    if ("".equals(rs.getCell(6, i).getContents().trim())) {
                        if (contentResult.length() > 0) {
                            contentResult.append("</br>");
                        }
                    }

                }

                if (!"".equals(contentResult.toString())) {
                    this.setFailMessage(contentResult.toString());
                    return RESULT_MESSAGE;
                }

                for (int i = 1; i < actualRows; i++) {
                    p = new Product();
                    p.setMaterial_id(rs.getCell(0, i).getContents().trim());
                    p.setMaterial_name(rs.getCell(1, i).getContents().trim());
                    if (rs.getCell(2, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(2, i);
                        double leadtime = numberCell.getValue();
                        p.setLead_time(leadtime);
                    } else {
                        try {
                            Double d = Double.valueOf(rs.getCell(2, i).getContents().trim());
                            p.setLead_time(d);
                        } catch (Exception e) {
                            p.setLead_time(0.0d);
                        }
                    }
                    p.setIsOrderItem(rs.getCell(3, i).getContents().trim());
                    p.setIsQuoteItem(rs.getCell(4, i).getContents().trim());
                    p.setIsDRItem(rs.getCell(5, i).getContents().trim());
                    if (rs.getCell(6, i).getType() == CellType.NUMBER) {
                        NumberCell numberCell = (NumberCell) rs.getCell(6, i);
                        double limitedQty = numberCell.getValue();
                        p.setLimited_qty(limitedQty);
                    } else {
                        try {
                            Double d = Double.valueOf(rs.getCell(6, i).getContents().trim());
                            p.setLimited_qty(d);
                        } catch (Exception e) {
                            p.setLimited_qty(0.0d);
                        }
                    }
                    p.setLatest_userId(this.getUser().getUserId());
                    pList.add(p);
                }
            }
            int result = 1;
            for (Product product : pList) {
                result = productService.updateProduct(product);
                if (result == 0) {
                    contentResult.append(product.getMaterial_id() + "is not exist!");
                }
            }
            if (result > 0) {
                return "Success";
            } else {
                return "failed !" + contentResult.toString();
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            return "Excelģ�����";
        } catch (Exception e) {

            return "failed (�������ݸ�ʽ����)��";
        }
    }

    public String findOrderExcel() {
        String result;
        if (ExcelUtil.getExcelStyle(path).intValue() == 1) {
            result = findOrderExcelXls(uploadFile);
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else if (ExcelUtil.getExcelStyle(path).intValue() == 2) {
            result = findOrderExcelXlsx(uploadFile);
            if ("Success".equals(result)) {
                this.setSuccessMessage("Success!");
            } else {
                this.setFailMessage(result);
            }
            return RESULT_MESSAGE;
        } else {
            this.setFailMessage("failed (�������ݸ�ʽ����)��");
            return RESULT_MESSAGE;
        }
    }

}
