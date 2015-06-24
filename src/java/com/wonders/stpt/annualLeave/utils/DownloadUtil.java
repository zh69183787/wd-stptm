package com.wonders.stpt.annualLeave.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {

	/**
	 * 
	 * @param response 
	 * @param filePath 具体物理路径
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletResponse response , String diskFilePath) throws IOException{
		File file = new File(diskFilePath);
		String filename = new String("templete.xls".getBytes("iso-8859-1"),"gbk");
		response.setContentType("application/x-msdownload");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(filename.getBytes("gbk"), "iso-8859-1"));
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];
		long k = 0;
		OutputStream myout = response.getOutputStream();
		while (k < file.length()) {
			int j = buff.read(b, 0, 1024);
			k += j;
			myout.write(b, 0, j);
		}
		myout.flush();
	}
}
