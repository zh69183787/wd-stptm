package com.wonders.stpt.util;
//
//txt - xml 转化的事例，java main 方法，完整带配置文件，可灵活转化 
//分类： 工作经验&知识库 2006-08-31 12:03 1183人阅读 评论(0) 收藏 举报 
//txt的格式，可以自定义，以统一的 符号分割 ：
//
//天津,万窿桃香园小区
//天津,吉利花园
//天津,学湖里
//
//转化后生成的xml 格式
//
//<?xml version="1.0" encoding="GBK"?>
//<PoiInfo>
//  <Poi v="1">
//    <ExCity v="天津" />
//    <ExNa v="万窿桃香园小区" />
//  </Poi>
//  <Poi v="2">
//    <ExCity v="天津" />
//    <ExNa v="吉利花园" />
//  </Poi>
//
//<PoiInfo>
//
//config配置文件-需要和主程序一起合并使用
//
//<?xml version="1.0" encoding="GBK"?>
//<Config>
// <cpEle>ExCity;ExNa</cpEle>
// <txtPath>e:/cptxt/</txtPath>
// <cpXmlPath>e:/cpxml/</cpXmlPath>
// <spilChar>,</spilChar>  
//</Config>
//
//转化txt - xml Java 主程序
//
//package com.lingtu.cp.cpdatacov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;


/**
 * 
 * 把txt数据转化为xml格式的cpdata 数据,目录遍历,生成.
 * @author chaiqi1@hotmail.com
 *
 */
public class ConvToCPData {
 
 //要拆分的多个节点
 private static String[] cpEle = null;
 //原始txt文件存放地址
 private static String txtPath ="";
 //转换后的xml存放地址
 private static String cpXmlPath ="";
 //txt文档的分割符号
 private static String spilChar=";";
 

 private static SAXBuilder builder = new SAXBuilder();
 private static Document Doc =null;
 
 
 /**
  * 读取配置文件
  * @param configpath
  * @return
  * 
  * 
  */
 public static boolean readConfigFile(String configpath)
 {
  if(configpath.equals("")){
   System.out.println("Sorry,configFile is empty!");
   return false;
  }
  File configFile = new File(configpath);
  
  try
  {
   Doc = builder.build(configFile);
   
   Element node = (Element)XPath.selectSingleNode(Doc, "/Config/cpEle");
   if(node != null)
    cpEle = node.getText().split(";");
   
   node = (Element)XPath.selectSingleNode(Doc, "/Config/txtPath");
   if(node != null)
    txtPath = node.getText();
   
   node = (Element)XPath.selectSingleNode(Doc, "/Config/cpXmlPath");
   if(node != null)
    cpXmlPath = node.getText();
   
   node = (Element)XPath.selectSingleNode(Doc, "/Config/spilChar");
   if(node != null)
    spilChar = node.getText();
   
   
  }
  catch(Exception e)
  {
   System.out.println(e.getMessage());
   System.out.println("Read Config File File");
   return false;
  }
  
  System.out.println("Read Config File OK");
  return true;
 }
 
 /**
  * 通用方法 取得系统时间
  * @param dateformat
  * @return
  */
 public static String getDate(String dateformat) {
  Date myDate = new Date(System.currentTimeMillis());
  SimpleDateFormat sDateformat = new SimpleDateFormat(dateformat);
  return sDateformat.format(myDate).toString();
 }
 
 /**
  * 转化cpData 主程序
  * @param txtPath
  * @param cpdataPath
  * @DES 对整个文件夹的批量转换
  */
 public static void convCPData(){
  
  File file = new File(txtPath);
  int exceptId=0;
  
  File[] entries = file.listFiles(); 
  int sz = entries.length;
  
  ArrayList testDb = new ArrayList();
  ArrayList testDb_name = new ArrayList();
  
  for (int i = 0; i < sz; i++) { 
   if (entries[i].isFile()) { 
    testDb.add(entries[i].getAbsoluteFile().toString());
    testDb_name.add(entries[i].getName().toString());
   } 
  } 
  
  try {
   for(int i=0;i<testDb.size();i++){

    System.out.println("开始匹配 " + testDb_name.get(i) + ","+getDate("hh:mm:ss"));
    
    FileReader fr=new FileReader(testDb.get(i).toString());
    BufferedReader br=new BufferedReader(fr);//建立BufferedReader对象，并实例化为br 
    String Line="";//从文件读取一行字符串 
    String city="";
    String name="";
    String strArr[]=null;
    StringBuffer str = new StringBuffer();
    
    builder = new SAXBuilder();
    Doc = new Document();

    //根节点
    Element rootEle=new Element("PoiInfo");
    Doc.setRootElement(rootEle);
    Element ele = null;
    Element poiEle = null;
    //清1
    int currPoiId =1; 
    
    while(Line!=null){ 
     //System.out.println(Line + "<br>");//输出从文件中读取的数据
     Line = br.readLine();
     
     if(Line!=null&&!Line.equals("")){
      str = str.append(Line);//从文件中继续读取一行数据
      
      //添加Poi节点
      poiEle = new Element("Poi");
      poiEle.setAttribute("v",String.valueOf(currPoiId));
      //poi id ++
      currPoiId++;
      
      //添加POi属性节点
      strArr = Line.split(spilChar);//拆分txt
      if(strArr!=null&&strArr.length>=cpEle.length){
       //遍历生成节点
       for(int j=0;j<cpEle.length;j++){
        ele = new Element(cpEle[j]);
        ele.setAttribute("v",strArr[j]);
        poiEle.addContent(ele);
       }
       
      }else{
       System.out.println("提供,分割的内容 和 cpEle 不统一");
       return;
      }
      //poi添加到root节点
      rootEle.addContent(poiEle);
      
     } 
    } 
    br.close();//关闭BufferedReader对象 
    fr.close();//关闭文件 
   
    XMLOutputter so=new XMLOutputter();
  
    Format format=Format.getPrettyFormat(); //格式化文档  
    format.setEncoding("GBK"); //由于默认的编码是utf-8,中文将显示为乱码，所以设为gbk  
    so.setFormat(format);  

    FileOutputStream fos = new FileOutputStream(cpXmlPath + testDb_name.get(i)+".xml");  
    so.output(Doc,fos);
    
    System.out.println("结束匹配 " + testDb_name.get(i)+","+getDate("hh-mm-ss"));
    
   }
  } catch (Exception e) {
   // TODO 自动生成 catch 块
   e.printStackTrace();
   System.out.println(exceptId);
  }
 }
 
 
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO 自动生成方法存根
  ConvToCPData cpdata = new ConvToCPData();
  
  cpdata.readConfigFile("E:/51DITU_WorkProgram/LT51DITU_WWW_DETAIL_tomcat/src/com/lingtu/cp/cpdatacov/CPDataConfig.xml");
  cpdata.convCPData();
  
 }
 
}
