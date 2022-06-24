package com.jingtong.platform.base.service;


import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
 
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    /**
     * ����getParameter���������������Ͳ���ֵ����xss���ˡ�
     * �����Ҫ���ԭʼ��ֵ����ͨ��super.getParameterValues(name)����ȡ
     * getParameterNames,getParameterValues��getParameterMapҲ������Ҫ����
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }
    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        if(value != null){
            for (int i = 0; i < value.length; i++) {
                value[i] = xssEncode(value[i]);
            }
        }
        return value;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Map getParameterMap() {
        return super.getParameterMap();
    }
    /**
     * ����������xss©���İ���ַ�ֱ���滻��ȫ���ַ� �ڱ�֤��ɾ�����ݵ�����±���
     * @return ���˺��ֵ
     */
    private static String xssEncode(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        return value;
    }
}
