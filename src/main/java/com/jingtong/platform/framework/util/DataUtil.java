package com.jingtong.platform.framework.util;

public class DataUtil {
	// ������ת��Ϊ���ֵ�����,��Ϊ����ʵ����Ҫʹ��������Ϊ��̬
	private static final char[] cnNumbers = { '��', 'Ҽ', '��', '��', '��', '��',
			'½', '��', '��', '��' };

	// ���ּ�ת��������,��Ϊ����ʵ����Ҫʹ��������Ϊ��̬
	private static final char[] series = { 'Ԫ', 'ʰ', '��', 'Ǫ', '��', 'ʰ', '��',
			'Ǫ', '��' };

	// ���ּ�ת��������,��Ϊ����ʵ����Ҫʹ��������Ϊ��̬,С������
	private static final char[] series1 = { '��', '��' };

	/**
	 * ���ַ���ʽ������ת��Ϊ�������� ��Ϊ����ʵ����Ҫ�õ������þ�̬����
	 * 
	 * @param c
	 * @return
	 */
	private static int getNumber(char c) {
		String str = String.valueOf(c);
		return Integer.parseInt(str);
	}

	/**
	 * ����ת��Ϊ���Ĵ�д
	 * 
	 * @param number
	 * @return
	 */
	public static String toChineseNumber(String num) {
		String integerPart = "";
		String floatPart = "";

		if (num.contains(".")) {
			// �������С����
			int dotIndex = num.indexOf(".");
			integerPart = num.substring(0, dotIndex);
			floatPart = num.substring(dotIndex + 1);
		} else {
			integerPart = num;
		}

		// ��Ϊ���ۼ�������StringBuffer
		StringBuffer sb = new StringBuffer();

		// �������ִ���
		for (int i = 0; i < integerPart.length(); i++) {
			int number = getNumber(integerPart.charAt(i));
			sb.append(cnNumbers[number]);
			sb.append(series[integerPart.length() - 1 - i]);
		}

		// С�����ִ���
//		sb.append(".");
		if (floatPart.length() > 0) {
			// sb.append("��");
			for (int i = 0; i < floatPart.length(); i++) {
				int number = getNumber(floatPart.charAt(i));
				sb.append(cnNumbers[number]);
				sb.append(series1[ i]);
			}
		}

		// ����ƴ�Ӻõ��ַ���
		return sb.toString();
	}

	/** 
	  * ��Ԫ����ǰ���㣬������ܳ���Ϊָ���ĳ��ȣ����ַ�������ʽ���� 
	  * @param sourceDate 
	  * @param formatLength 
	  * @return ���������� 
	  */  
	 public static String frontCompWithZore(String prefix,long sourceDate,int formatLength)  
	 {  
	  /* 
	   * prefix  ǰ׺
	   * 0 ָǰ�油���� 
	   * formatLength �ַ��ܳ���Ϊ formatLength 
	   * d ����Ϊ������ 
	   */  
	  String newString = String.format("%0"+formatLength+"d", sourceDate);  
 	  if(!"".equals(prefix)&& null !=prefix)	{
 		 newString=prefix+newString;
	  } 
	  return  newString;  
	 }  
	
	public static void main(String[] args) {
		System.out.println(frontCompWithZore("v",5,7));
		System.out.println(toChineseNumber("123123458.00"));
	}
}
