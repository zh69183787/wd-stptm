package com.wonders.stpt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 自动生成TXT
 * @param data  内容数据
 * @param outFile 生成路径 ....jsp\task\file\mydir\
 * @param dir 文件夹 
 * @return
 */
public class GenerateTXT{

	public static String generateTXT(String data,String outFile,String txtName) {
		if(data==null)	data ="";
		if(outFile==null) return "error";
		if(txtName==null) return "error";
		if(txtName.lastIndexOf(".txt") < 0) {
			txtName+=".txt";
		}
		
	//	File f= new File( "C:\\test2.txt "); 
		File dir= new File(outFile); 
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File f= new File(outFile+txtName); 
		
		FileOutputStream   fos   =   null; 
	
		try{ 
			f.createNewFile(); 
			fos = new FileOutputStream(f); 
			fos.write(data.getBytes("GBK")); 
			
			fos.flush(); 
			fos.close(); 
		}catch(IOException   e){ 
			System.out.println(e); 
		} 
		 
		 return null;
	 }
}	