package com.wonders.stpt.util;


import java.util.*;
import java.io.*;
import java.sql.*;


public class FileUtil {
	public static final String UPLOAD_FILE_SAVE_PATH = "uploadFileSavePath";

	public static String getDocumentPath() {
		return PropertiesReader.GetProptery(UPLOAD_FILE_SAVE_PATH);
	}

	/**
	 * �õ�ϵͳ���õ��ϴ��ļ�����·��
	 * 
	 * @return String
	 */
	public static String getPath(String loadPath) {
		String path = PropertiesReader.GetProptery(loadPath);
		if (path == null || "".equals(path)) {
			throw new java.lang.IllegalStateException("�ϴ��ļ��ı���·��û�����á�"
					+ loadPath + "=?");
		}

		File file = new File(path);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		return path;
	}

	/**
	 * �����Ƶ���ǰ���µ��ļ���,���������·��

	 * 
	 * @param fileName
	 *            String
	 */
	public static String move(String fileName, String loadPath) {
		String path = getPath(loadPath);

		// �������µ��ļ�·���ַ�
		String abstractPath = getAbstractPath(path);

		File file = new File(path + fileName);
		if (file.isFile()) {
			InputStream is = null;
			OutputStream out = null;
			try {
				is = new FileInputStream(file);
				path = path + abstractPath + fileName;
				File toFile = new File(path);
				if (toFile.exists()) {
					toFile.delete();
				} 
				toFile.createNewFile();
				out = new FileOutputStream(toFile);
				byte[] b = new byte[256];
				for (int length = 0; (length = is.read(b, 0, 256)) > 0;) {
					out.write(b, 0, length);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ex1) {
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException ex2) {
				}
			}
			file.delete();
		}
		return abstractPath;
	}

	/**
	 * ���ļ���ͬһ��Ŀ¼�¸���һ�� fileName ԭ4�ļ��� path �ļ���·�� cFileName ���ƺ���ļ���

	 */
	public static void copy(String fileName, String cFileName, String path) {
		// ԭ�ļ�
		File file = new File(path + fileName);
		if (file.isFile()) {
			InputStream is = null;
			OutputStream out = null;
			try {
				// ԭ�ļ���һ��������
				is = new FileInputStream(file);
				// �����ļ���·��
				File toFile = new File(path + cFileName);
				if (toFile.exists()) {
					toFile.delete();
				}
				// ���������ļ�
				toFile.createNewFile();
				// �����ļ��������

				out = new FileOutputStream(toFile);
				// ����
				byte[] b = new byte[256];
				for (int length = 0; (length = is.read(b, 0, 256)) > 0;) {
					out.write(b, 0, length);

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException ex1) {
				}
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException ex2) {
				}
			}
			// file.delete();
		}
		// return abstractPath;
	}

	/*
	 * �������µ��ļ�·���ַ�
	 */
	private static String getAbstractPath(String path) {
		Calendar cl = Calendar.getInstance();
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH);
		StringBuffer buffer = new StringBuffer();
		buffer.append(year);
		buffer.append("/");
		month++;
		if (month < 10) {
			buffer.append(0);
		}
		buffer.append(month);
		buffer.append("/");
		File renameTo = new File(path + buffer.toString());
		if (!renameTo.isDirectory()) {
			renameTo.mkdirs();
		}
		return buffer.toString();
	}

	/*
	 * 
	 * ����ֵ ɾ��󸽼�������

	 * 
	 * ����˵�� al ���и�������Ϣ str ��ɾ��ļ�¼ num ÿ���¼���Ԫ�ظ���
	 */

	public static ArrayList getAfterDel(ArrayList al, String[] str, int num) {
		int temp, temp1, temp2;

		for (int i = str.length - 1; i >= 0; i--) {
			temp = Integer.parseInt(str[i]);
			temp1 = (temp - 1) * num;
			temp2 = temp * num - 1;
			for (int j = temp2; j >= temp1; j--) {
				if (j < al.size())
					al.remove(j);
			}
		}
		return al;
	}

	// ��ø������б�

	public static ArrayList getFjList(String sid) {
		ArrayList result = new ArrayList();
		String strSql = "select SID,File_Name,Ext_Name,File_Size from  t_docs_fj where state='1' and parent_sid="+ sid;
		CommonDao dao= CommonDao.GetOldDatabaseDao();
		List lst = dao.fetchAll(strSql);
		if(lst!=null && lst.size()>0){
			for( int i = 0 ; i <lst.size(); i++){
				Object[] arr = (Object[])lst.get(i);
				result.add(arr[1]);
				result.add(arr[2]);
				if(arr[3]!=null){
					Float fileSize= Float.parseFloat(arr[3].toString()) *  1000 ;
					result.add(fileSize);
				}
				else {
					result.add(0);
				}
				result.add(arr[0]);
			}
		}
		dao.getSessionFactory().close();
		return result;
		/*Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList result = new ArrayList();
		String strSql = "select * from  t_docs_fj where state='1' and parent_sid="
				+ sid;
		try {
			conn = DataManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			while (rs.next()) {
				result.add(rs.getString("File_Name"));
				result.add(rs.getString("Ext_Name"));
				result.add(""
						+ Float.valueOf(rs.getString("File_Size")).floatValue()
						* 1000.0);
				result.add(rs.getString("SID"));
				result.add(FileUtil.getDocumentPath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}*/
		
	}

}
