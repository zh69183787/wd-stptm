package com.wonders.stpt.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class StringUtil {
	
	/**
	 * 字符串自动补位
	 * author : mengjie
	 * @param data 数据
	 * @param num  位数
	 * @param eCode  编码格式
	 * @param flag  是否换行
	 * @return
	 */
	public static String fillBytes(String data,int num,String eCode,boolean flag){
		String d = nullToEmpty(data);
//		System.out.println(d);
//		ASCII
		if(eCode==null||"".equals(eCode)){eCode = "GBK";}
		//if(num==null){}
		int len =0;
			
		try {
			len = d.getBytes(eCode).length;
			String str = "";
			int strByte =0;
			if(flag){
				str = "\r\n";
//				strByte = 4;//  备注：回车换行 占位写法
				strByte = 0;//  备注：回车换行 不占位写法
			}
			
			if(len<num-strByte){
				
				for(int i=len+1;i<=num-strByte;i++){
					d+=" ";
				}
				
				
			}else if(len>num-strByte){
				for(int i=d.length()-1;i>=0;i--){
					d = d.substring(0,i);
//					System.out.println(i+":"+d);
					if(d.getBytes(eCode).length<=num-strByte){
						fillBytes(d,num,eCode,flag);
						break;
					}
				}
			}
			
			return d+=str;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 字符串自动补位
	 * author : mengjie
	 * @param StringBuffer data 数据
	 * @param num  位数
	 * @param eCode  编码格式
	 * @param flag  是否换行
	 * @return
	 */
	public static String fillBytesBuffer(String data,int num,String eCode,boolean flag){
		StringBuffer d = new StringBuffer(nullToEmpty(data));
		if(eCode==null||"".equals(eCode)){eCode = "GBK";}
		//if(num==null){}
		int len =0;
			
		try {
//			len = d.toString().getBytes(eCode).length;
			len = d.toString().length();
			StringBuffer str = new StringBuffer();
			int strByte =0;
			if(flag){
				str.append("\r\n");
//				strByte = 4;//  备注：回车换行 占位写法
				strByte = 0;//  备注：回车换行 不占位写法
			}
			
			if(len<num-strByte){
				
				for(int i=len+1;i<=num-strByte;i++){
					d.append(" ");
//					d+=" ";
				}
				
				
			}else if(len>num-strByte){
				for(int i=d.length()-1;i>=0;i--){
					d = new StringBuffer(d.toString().substring(0,i));
//					System.out.println(i+":"+d);
					if(d.toString().getBytes(eCode).length<=num-strByte){
						fillBytesBuffer(d.toString(),num,eCode,flag);
						break;
					}
				}
			}
			d.append(str);
			return d.toString();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 *  判断数据是否为NULL ,为NULL 返回 ""
	 * @param data 数据
	 * @return 
	 */
	public static String nullToEmpty(String data){
		String o = (data==null)?"":data;
		return o;
	}
	/**
	 *  判断数据是否为NULL ,为NULL 返回 ""
	 * @param StringBuffer data 数据
	 * @return 
	 */
	public static StringBuffer nullToEmpty(StringBuffer data){
		StringBuffer o = (data==null)?new StringBuffer():data;
		return o;
	}
	
	/**
	 *   将时间格式按 YYYY-MM-DD 24hh:mm:ss 输出
	 * @param data 数据
	 * @return  String  YYYY-MM-DD 24hh:mm:ss
	 */
	public static String fillDateFormat(String data){
		String o = nullToEmpty(data);//(data==null)?(data=""):data;
		o = "".equals(o.trim())?"0000-00-00 00:00:00":o;
		
		
		return o;
	}
	
	public static String getNotNullValueString(Object obj){
		String strRtn="";
		if(obj!=null){
			strRtn = obj+"";
		}
		return strRtn;
	}
	
	public   static   void   main(String[]   args) throws UnsupportedEncodingException   { 
//		String d = "的";
//////		d = d.substring(0,5);
//		try {
//			System.out.println(d.getBytes("GBK").length);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String s1 = "2012-12-1";
/*		String s1 = "20119-11";
		String s2 = "2010-08-09 12:12:59";

		int len1 = s1.length();
		
		SimpleDateFormat sfd = new SimpleDateFormat("yyyyM-dd");
		SimpleDateFormat sf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		Date d3 = null;
		try {
			d1 = sfd.parse(s1);
			d2 = sf.parse(s2);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(len1 == 8){
		 String f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1);
		 System.out.println(f); 
		}else{
			System.out.println("11111");
		}*/
//		len = d.toString().getBytes(eCode).length;
		String  d="我是测试数据~~123";
		System.out.println(d.toString().getBytes("GBK").length);
		System.out.println(d.length());
		System.out.println(fillBytes(d,50,null,false));
		System.out.println((d.toString().getBytes("GBK")).length);
		System.out.println(d.length());
		
		
		
		
		
		
//		String date = "2012-12-1";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		     //输入对象不为空
//		   
//		    try {
////		  System.out.println( sdf.parse(date)); //sdf.parse(date);
//		  Date d2 = df.parse(date);
//		  System.out.println( d2);
//		 } catch (java.text.ParseException e) {
//		  // TODO Auto-generated catch block
//			 System.out.println( "0000-00-00 00:00:00"); //sdf.parse(date);
//		 }
//		
		
		
//		
//		d = fillBytes(d,10,"",true);
//		System.out.println(d);
//		List<String> l = new ArrayList<String>();
//		l.add("10");
//		l.add("100");
////		l=null;
		
//		for(int i=0;l!=null&&i<l.size();i++){
//			System.out.println("i："+i);
//		}
		
//		try {
//			System.out.println(String.valueOf(d.getBytes("GBK")));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
