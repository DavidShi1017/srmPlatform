package com.jingtong.platform.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * File ������
 * 
 * @author
 * 
 */
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	private static final int BUFFER_SIZE = 16 * 1024;

	public static boolean saveFile(String path, String content, boolean append) {
		boolean flag = false;
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path, append),
					BUFFER_SIZE);
			out.write(content.getBytes("GBK"));
			flag = true;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	/**
	 * �ļ����Ϊ
	 * 
	 * @param source
	 *            Դ�ļ�
	 * @param target
	 *            Ŀ���ļ�
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean saveAsFile(File source, File target) {
		boolean flag = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(source),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(target),
					BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}

			flag = true;
		} catch (Exception e) {
			logger.error("�ļ����Ϊʧ�ܣ�", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	/**
	 * xlsx�ļ����ݶ�ʧ��Ҫ��������
	 * 
	 * @param source
	 *            Դ�ļ�
	 * @param target
	 *            Ŀ���ļ�
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean saveAsFile1(File source, File target) {
		boolean flag = false;
		
		// �ļ����������Ҫ�رգ�
		OutputStream os = null;
		try {
			// �ֽ�����������������Ҫ�رգ�
			InputStream is = new ByteArrayInputStream(readFileToByteArray(source));
			os = new FileOutputStream(target);

			byte[] buf = new byte[BUFFER_SIZE];
			int len;
			while (((len = is.read(buf)) != -1)) {
				os.write(buf, 0, len);
			}
			os.flush();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
	
	public static String readFile(String filePath) {
		StringBuffer fileContent = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
			br = new BufferedReader(isr);

			String tempStr = br.readLine();
			while (tempStr != null) {
				fileContent.append(tempStr);
				tempStr = br.readLine();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return fileContent.toString();
	}

	/**
	 * ��ȡ�ļ������� (.txt)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtention(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * ��ȡ�ļ���
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf(".") == -1) {
			return fileName;
		} else {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}
	}

	/**
	 * ��ȡ�ļ������� (txt)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf(".") == -1) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
	}
	
	/**
	 * �޸��ļ���
	 * @param source
	 * @param targetName
	 * @return
	 */
	public static boolean modifyFileNameTo(File source, String targetName){
		boolean flag = false;
		try{
			source.renameTo(new File(targetName));
			flag = true;
		}catch(Exception e){
			logger.error(e);
		}
		return flag;
	}
	
	/**
	 * �ж��ļ��Ƿ����
	 * @param fileName
	 * @return
	 */
	public static boolean exists(String fileName){
		File source = new File(fileName);
		return source.exists();
	}

	/**
	 * ��ȡ�ֽ�����added by axfy
	 * @param src
	 * @return
	 */
	public static byte[] readFileToByteArray(File src) {
        // �ļ�����������Ҫ�رգ�
        InputStream is = null;
        try {
            is = new FileInputStream(src);
            // �ֽ����������������Ҫ�رգ�
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
           
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

		

}
