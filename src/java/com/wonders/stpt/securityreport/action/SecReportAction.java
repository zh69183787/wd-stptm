/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.securityreport.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.CrossIpLogin;
import com.wonders.stpt.UserInfo;
import com.wonders.stpt.securityreport.entity.bo.SecReport;
import com.wonders.stpt.securityreport.service.SecReportService;
import com.wonders.stpt.securityreport.entity.vo.SecReportVO;
import com.wonders.stpt.securityreport.entity.vo.XmlNodeVo;

import com.wonders.stpt.securityreport.ExportExcel;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
import com.wondersgroup.framework.core5.util.parameter.ParseException;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

public class SecReportAction extends BaseAjaxAction {
	private SecReport secReport = new SecReport();
	private SecReportVO secReportVO = new SecReportVO();
	private XmlNodeVo xmlNodeVo = new XmlNodeVo();
	private SecReportService secReportService;

	@Override
	public ValueObject getValueObject() {
		return this.secReportVO;
	}

	public Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}

	public String findSecReportById() {
		String id = super.getServletRequest().getParameter("id");		
		
		if(null!=id&&!"".equals(id)){
			secReport = secReportService.findSecReportById(id);
			secReportVO = new SecReportVO();
			secReportVO = convertBOToVO(secReport);
			System.out.println("secReportVO==="+secReport.getCreatPerson());	
			System.out.println("secReportVO==="+secReport.getCreatTime());	
			String content = secReport.getContent();
//			System.out.println("content==="+secReport.getContent());	
			Document doc = null;  
	        try {  
	          doc = DocumentHelper.parseText(content);  
	       } catch (DocumentException e2) {  
	           // TODO 自动生成 catch 块  
	           e2.printStackTrace();  
	        }
	       //从xml取数据
	       Element root = doc.getRootElement();
	              
	       Element node1 = root.element("node1");
	       Element node2 = root.element("node2");
	       Element node3 = root.element("node3");
	       Element node4 = root.element("node4");
	       Element node5 = root.element("node5");
	       Element node6 = root.element("node6");
	       Element node7 = root.element("node7");
	       Element node8 = root.element("node8");
	 
	       Element node1_1 = node1.element("node1_1");
	       Element node1_2 = node1.element("node1_2");
	       Element node1_remark = node1.element("node1_remark");
	       
	       Element node2_1 = node2.element("node2_1");
	       Element node2_remark = node2.element("node2_remark");
	       
	       Element node3_1 = node3.element("node3_1");
	       Element node3_2 = node3.element("node3_2");
	       Element node3_3 = node3.element("node3_4");
	       Element node3_4 = node3.element("node3_4");
	       Element node3_remark = node3.element("node3_remark");
	      
	       Element node4_1 = node4.element("node4_1");
	       Element node4_2 = node4.element("node4_2");
	       Element node4_3 = node4.element("node4_3");
	       Element node4_4 = node4.element("node4_4");
	       Element node4_remark = node4.element("node4_remark");
	       
	       Element node5_1 = node5.element("node5_1");
	       Element node5_2 = node5.element("node5_2");
	       Element node5_3 = node5.element("node5_3");
	       Element node5_4 = node5.element("node5_4");
	       Element node5_5 = node5.element("node5_5");
	       Element node5_6 = node5.element("node5_6");
	       Element node5_7 = node5.element("node5_7");
	       Element node5_8 = node5.element("node5_8");
	       Element node5_9 = node5.element("node5_9");
	       Element node5_10 = node5.element("node5_10");
	       Element node5_11 = node5.element("node5_11");
	       Element node5_remark = node5.element("node5_remark");
	       
	       Element node6_1 = node6.element("node6_1");
	       Element node6_2 = node6.element("node6_2");
	       Element node6_3 = node6.element("node6_3");
	       Element node6_4 = node6.element("node6_4");
	       Element node6_5 = node6.element("node6_5");
	       Element node6_6 = node6.element("node6_6");
	       Element node6_7 = node6.element("node6_7");
	       Element node6_remark = node6.element("node6_remark");
	       
	       Element node7_1 = node7.element("node7_1");
	       Element node7_2 = node7.element("node7_2");
	       Element node7_3 = node7.element("node7_3");
	       Element node7_4 = node7.element("node7_4");
	       Element node7_remark = node7.element("node7_remark");
	       
	       Element node8_1 = node8.element("node8_1");
	       Element node8_2 = node8.element("node8_2");
	       Element node8_3 = node8.element("node8_3");
	       Element node8_4 = node8.element("node8_4");
	       Element node8_5 = node8.element("node8_5");
	       Element node8_6 = node8.element("node8_6");
	       Element node8_7 = node8.element("node8_7");
	       Element node8_8 = node8.element("node8_8");
	       Element node8_9 = node8.element("node8_9");
	       Element node8_10 = node8.element("node8_10");
	       Element node8_11 = node8.element("node8_11");
	       Element node8_12 = node8.element("node8_12");
	       Element node8_remark = node8.element("node8_remark");
       
	       String text_node1_1 = node1_1.getTextTrim();	     
	       String text_node1_2 = node1_2.getTextTrim();	
	       String text_node1_remark = node1_remark.getTextTrim();
	       System.out.println("text_node1_remark==="+text_node1_remark);
	       String text_node2_1 = node2_1.getTextTrim();	
	       String text_node2_remark = node2_remark.getTextTrim();
	              
	       String text_node3_1 = node3_1.getTextTrim();	
	       String text_node3_2 = node3_2.getTextTrim();	
	       String text_node3_3 = node3_3.getTextTrim();	
	       String text_node3_4 = node3_4.getTextTrim();
	       String text_node3_remark = node3_remark.getTextTrim();
	       
	       String text_node4_1 = node4_1.getTextTrim();	
	       String text_node4_2 = node4_2.getTextTrim();	
	       String text_node4_3 = node4_3.getTextTrim();	
	       String text_node4_4 = node4_4.getTextTrim();
	       String text_node4_remark = node4_remark.getTextTrim();

	       String text_node5_1 = node5_1.getTextTrim();	
	       String text_node5_2 = node5_2.getTextTrim();	
	       String text_node5_3 = node5_3.getTextTrim();	
	       String text_node5_4 = node5_4.getTextTrim();
	       String text_node5_5 = node5_5.getTextTrim();	
	       String text_node5_6 = node5_6.getTextTrim();	
	       String text_node5_7 = node5_7.getTextTrim();	
	       String text_node5_8 = node5_8.getTextTrim();
	       String text_node5_9 = node5_9.getTextTrim();	
	       String text_node5_10 = node5_10.getTextTrim();	
	       String text_node5_11 = node5_11.getTextTrim();	
	       String text_node5_remark = node5_remark.getTextTrim();

	       String text_node6_1 = node6_1.getTextTrim();	
	       String text_node6_2 = node6_2.getTextTrim();	
	       String text_node6_3 = node6_3.getTextTrim();	
	       String text_node6_4 = node6_4.getTextTrim();
	       String text_node6_5 = node6_5.getTextTrim();	
	       String text_node6_6 = node6_6.getTextTrim();	
	       String text_node6_7 = node6_7.getTextTrim();		
	       String text_node6_remark = node6_remark.getTextTrim();

	       String text_node7_1 = node7_1.getTextTrim();	
	       String text_node7_2 = node7_2.getTextTrim();	
	       String text_node7_3 = node7_3.getTextTrim();	
	       String text_node7_4 = node7_4.getTextTrim();
	       String text_node7_remark = node7_remark.getTextTrim();

	       String text_node8_1 = node8_1.getTextTrim();	
	       String text_node8_2 = node8_2.getTextTrim();	
	       String text_node8_3 = node8_3.getTextTrim();	
	       String text_node8_4 = node8_4.getTextTrim();
	       String text_node8_5 = node8_5.getTextTrim();	
	       String text_node8_6 = node8_6.getTextTrim();	
	       String text_node8_7 = node8_7.getTextTrim();	
	       String text_node8_8 = node8_8.getTextTrim();
	       String text_node8_9 = node8_9.getTextTrim();	
	       String text_node8_10 = node8_10.getTextTrim();	
	       String text_node8_11 = node8_11.getTextTrim();
	       String text_node8_12 = node8_12.getTextTrim();
//	       String text_node8_remark = node8_remark.getTextTrim();
	    
	       xmlNodeVo.setNode1_1(text_node1_1);
	       xmlNodeVo.setNode1_2(text_node1_2);
	       xmlNodeVo.setNode1_remark(text_node1_remark);
	       
	       xmlNodeVo.setNode2_1(text_node2_1);
	       xmlNodeVo.setNode2_remark(text_node2_remark);
	       
	       xmlNodeVo.setNode3_1(text_node3_1);
	       xmlNodeVo.setNode3_2(text_node3_2);
	       xmlNodeVo.setNode3_3(text_node3_3);
	       xmlNodeVo.setNode3_4(text_node3_4); 
	       xmlNodeVo.setNode3_remark(text_node3_remark);

	       xmlNodeVo.setNode4_1(text_node4_1);
	       xmlNodeVo.setNode4_2(text_node4_2);
	       xmlNodeVo.setNode4_3(text_node4_3);
	       xmlNodeVo.setNode4_4(text_node4_4); 
	       xmlNodeVo.setNode4_remark(text_node4_remark);

	       xmlNodeVo.setNode5_1(text_node5_1);
	       xmlNodeVo.setNode5_2(text_node5_2);
	       xmlNodeVo.setNode5_3(text_node5_3);
	       xmlNodeVo.setNode5_4(text_node5_4);
	       xmlNodeVo.setNode5_5(text_node5_5);
	       xmlNodeVo.setNode5_6(text_node5_6);
	       xmlNodeVo.setNode5_7(text_node5_7);
	       xmlNodeVo.setNode5_8(text_node5_8);
	       xmlNodeVo.setNode5_9(text_node5_9);
	       xmlNodeVo.setNode5_10(text_node5_10);
	       xmlNodeVo.setNode5_11(text_node5_11);
	       xmlNodeVo.setNode5_remark(text_node5_remark);

	       xmlNodeVo.setNode6_1(text_node6_1);
	       xmlNodeVo.setNode6_2(text_node6_2);
	       xmlNodeVo.setNode6_3(text_node6_3);
	       xmlNodeVo.setNode6_4(text_node6_4);
	       xmlNodeVo.setNode6_5(text_node6_5);
	       xmlNodeVo.setNode6_6(text_node6_6);
	       xmlNodeVo.setNode6_7(text_node6_7);
	       xmlNodeVo.setNode6_remark(text_node6_remark);

	       xmlNodeVo.setNode7_1(text_node7_1);
	       xmlNodeVo.setNode7_2(text_node7_2);
	       xmlNodeVo.setNode7_3(text_node7_3);
	       xmlNodeVo.setNode7_4(text_node7_4); 
	       xmlNodeVo.setNode7_remark(text_node7_remark);
	       
	       xmlNodeVo.setNode8_1(text_node8_1);
	       xmlNodeVo.setNode8_2(text_node8_2);
	       xmlNodeVo.setNode8_3(text_node8_3);
	       xmlNodeVo.setNode8_4(text_node8_4);
	       xmlNodeVo.setNode8_5(text_node8_5);
	       xmlNodeVo.setNode8_6(text_node8_6);
	       xmlNodeVo.setNode8_7(text_node8_7);
	       xmlNodeVo.setNode8_8(text_node8_8);
	       xmlNodeVo.setNode8_9(text_node8_9);
	       xmlNodeVo.setNode8_10(text_node8_10);
	       xmlNodeVo.setNode8_11(text_node8_11);
	       xmlNodeVo.setNode8_12(text_node8_12);
//	       xmlNodeVo.setNode8_remark(text_node8_remark);
      
	       try {
	    	   text_node8_11 = URLEncoder.encode(text_node8_11,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
/*	       String strDeptId = deptId.getTextTrim();
	       String strDeptName = deptName.getTextTrim();*/		
		}
		if(null!=id&&!"".equals(id)){
			String jumpType = super.getServletRequest().getParameter("jumpType");
			if("operation".equals(jumpType)){
				return "toOperation";
			}else{
				return "toView";
			}
			
		}
		return "toOperation";
	}

	public String findSecReportByPage() throws ParseException, java.text.ParseException {
	int start = 0;
	Page page;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		String currentPageStr = this.servletRequest.getParameter("number");	
		String creatTimeStart = this.servletRequest.getParameter("creatTimeStart");
		String creatTimeEnd = this.servletRequest.getParameter("creatTimeEnd");
		String modifyTimeStart = this.servletRequest.getParameter("modifyTimeStart");
		String modifyTimeEnd = this.servletRequest.getParameter("modifyTimeEnd");
		String filingDateStart = this.servletRequest.getParameter("filingDateStart");
		String filingDateEnd = this.servletRequest.getParameter("filingDateEnd");
		String updateTimeOrder = this.servletRequest.getParameter("updateTimeOrder");
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		
		filter.put("remove", "0");
		
		JSONObject obj = JSONObject.fromObject(this.secReportVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.secReportVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(creatTimeStart)&&null!=creatTimeStart){	
			Date date =  format.parse(creatTimeStart);
			filter.put("creatTimeStart", date);
	        this.servletRequest.setAttribute("creatTimeStart", creatTimeStart);
		}
		if(!"".equals(creatTimeEnd)&&null!=creatTimeEnd){	
			Date date =  format.parse(creatTimeEnd);
			filter.put("creatTimeEnd", date);
	        this.servletRequest.setAttribute("creatTimeEnd", creatTimeEnd);
		}
		
		if(!"".equals(modifyTimeStart)&&null!=modifyTimeStart){	
			Date date =  format.parse(modifyTimeStart);
			filter.put("modifyTimeStart", date);
	        this.servletRequest.setAttribute("modifyTimeStart", modifyTimeStart);
		}
		if(!"".equals(modifyTimeEnd)&&null!=modifyTimeEnd){	
			Date date =  format.parse(modifyTimeEnd);
			filter.put("modifyTimeEnd", date);
	        this.servletRequest.setAttribute("modifyTimeEnd", modifyTimeEnd);
		}
		
		if(!"".equals(filingDateStart)&&null!=filingDateStart){	
			Date date =  format.parse(filingDateStart);
			filter.put("filingDateStart", date);
	        this.servletRequest.setAttribute("filingDateStart", filingDateStart);
		}
		if(!"".equals(filingDateEnd)&&null!=filingDateEnd){	
			Date date =  format.parse(filingDateEnd);
			filter.put("filingDateEnd", date);
	        this.servletRequest.setAttribute("filingDateEnd", filingDateEnd);
		}
		
		
		if (currentPage == 0) {
			   page = secReportService.findSecReportByPage(filter, start / size + 1,
					size,updateTimeOrder);
			}
		else {
				page = secReportService.findSecReportByPage(filter, currentPage,
						size,updateTimeOrder);
			}
			
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("updateTimeOrder", updateTimeOrder);
		
		
//		String json = VOUtils.getJsonDataFromPage(page, SecReport.class);
//		createJSonData(json);
		return "toList";	
	}	
		


/*	public String deleteSecReport() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			SecReport bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (SecReport) JSONObject.toBean(obj, SecReport.class);
				if (bean != null) {
					secReportService.deleteSecReport(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}*/
	public String deleteSecReport(){
			String id = servletRequest.getParameter("id");
			secReport = secReportService.findSecReportById(id);
			secReport.setRemove( "1");
			secReportService.updateSecReport(secReport);
			return "toDelete";
		}
	/**
	 * 新增 或者 更新操作
	 * **/
	public String addOrUpdateSecReport() {
		
		String addSuccess =""; //标示是否操作成功
		
		
		SecReport secReport = convertVOToBO(secReportVO);//修改原来的convertVOToBO 方法，已删除 new id
		/*String r_year =  this.servletRequest.getParameter("r_year");
		String r_month =  this.servletRequest.getParameter("r_month");
		String r_day =  this.servletRequest.getParameter("r_day");
		String filing_date = r_year+"-"+r_month+"-"+r_day;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date filingDate = format.parse(filing_date);
			secReport.setFilingDate(filingDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String content = "";
		content += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		content += "<content>";
		content += "<node1><node1_1>"+xmlNodeVo.getNode1_1()+"</node1_1>" +
						  "<node1_2>"+xmlNodeVo.getNode1_2()+"</node1_2>" +
						  "<node1_remark>"+xmlNodeVo.getNode1_remark()+"</node1_remark></node1>";
		content += "<node2><node2_1>"+xmlNodeVo.getNode2_1()+"</node2_1>" +
							"<node2_remark>"+xmlNodeVo.getNode2_remark()+"</node2_remark></node2>";
		content += "<node3><node3_1>"+xmlNodeVo.getNode3_1()+"</node3_1>" +
							"<node3_2>"+xmlNodeVo.getNode3_2()+"</node3_2>" +
							"<node3_3>"+xmlNodeVo.getNode3_3()+"</node3_3>" +
							"<node3_4>"+xmlNodeVo.getNode3_4()+"</node3_4>" +
							"<node3_remark>"+xmlNodeVo.getNode3_remark()+"</node3_remark></node3>";
		content += "<node4><node4_1>"+xmlNodeVo.getNode4_1()+"</node4_1>" +
							"<node4_2>"+xmlNodeVo.getNode4_2()+"</node4_2>" +
							"<node4_3>"+xmlNodeVo.getNode4_3()+"</node4_3>" +
							"<node4_4>"+xmlNodeVo.getNode4_4()+"</node4_4>" +
							"<node4_remark>"+xmlNodeVo.getNode4_remark()+"</node4_remark></node4>";
		content += "<node5><node5_1>"+xmlNodeVo.getNode5_1()+"</node5_1>" +
							"<node5_2>"+xmlNodeVo.getNode5_2()+"</node5_2>" +
							"<node5_3>"+xmlNodeVo.getNode5_3()+"</node5_3>" +
							"<node5_4>"+xmlNodeVo.getNode5_4()+"</node5_4>" +
							"<node5_5>"+xmlNodeVo.getNode5_5()+"</node5_5>" +
							"<node5_6>"+xmlNodeVo.getNode5_6()+"</node5_6>" +
							"<node5_7>"+xmlNodeVo.getNode5_7()+"</node5_7>" +
							"<node5_8>"+xmlNodeVo.getNode5_8()+"</node5_8>" +
							"<node5_9>"+xmlNodeVo.getNode5_9()+"</node5_9>" +
							"<node5_10>"+xmlNodeVo.getNode5_10()+"</node5_10>" +
							"<node5_11>"+xmlNodeVo.getNode5_11()+"</node5_11>" +
							"<node5_remark>"+xmlNodeVo.getNode5_remark()+"</node5_remark></node5>"; 
		content += "<node6><node6_1>"+xmlNodeVo.getNode6_1()+"</node6_1>" +
							"<node6_2>"+xmlNodeVo.getNode6_2()+"</node6_2>" +
							"<node6_3>"+xmlNodeVo.getNode6_3()+"</node6_3>" +
							"<node6_4>"+xmlNodeVo.getNode6_4()+"</node6_4>" +
							"<node6_5>"+xmlNodeVo.getNode6_5()+"</node6_5>" +
							"<node6_6>"+xmlNodeVo.getNode6_6()+"</node6_6>" +
							"<node6_7>"+xmlNodeVo.getNode6_7()+"</node6_7>" +
							"<node6_remark>"+xmlNodeVo.getNode6_remark()+"</node6_remark></node6>";
		content += "<node7><node7_1>"+xmlNodeVo.getNode7_1()+"</node7_1>" +
							"<node7_2>"+xmlNodeVo.getNode7_2()+"</node7_2>" +
							"<node7_3>"+xmlNodeVo.getNode7_3()+"</node7_3>" +
							"<node7_4>"+xmlNodeVo.getNode7_4()+"</node7_4>" +
							"<node7_remark>"+xmlNodeVo.getNode7_remark()+"</node7_remark></node7>";
		content += "<node8><node8_1>"+xmlNodeVo.getNode8_1()+"</node8_1>" +
							"<node8_2>"+xmlNodeVo.getNode8_2()+"</node8_2>" +
							"<node8_3>"+xmlNodeVo.getNode8_3()+"</node8_3>" +
							"<node8_4>"+xmlNodeVo.getNode8_4()+"</node8_4>" +
							"<node8_5>"+xmlNodeVo.getNode8_5()+"</node8_5>" +
							"<node8_6>"+xmlNodeVo.getNode8_6()+"</node8_6>" +
							"<node8_7>"+xmlNodeVo.getNode8_7()+"</node8_7>" +
							"<node8_8>"+xmlNodeVo.getNode8_8()+"</node8_8>" +
							"<node8_9>"+xmlNodeVo.getNode8_9()+"</node8_9>" +
							"<node8_10>"+xmlNodeVo.getNode8_10()+"</node8_10>" +
							"<node8_11>"+xmlNodeVo.getNode8_11()+"</node8_11>" +
							"<node8_12>"+xmlNodeVo.getNode8_12()+"</node8_12></node8>"; 
		content += "</content>";
		secReport.setContent(content);
//		secReport.setId(null);
		
		
		if (secReport != null) {
			//当对象 ID 不为空时 执行  更行操作；为空时执行新增操作
			if(null!=secReportVO.getId()&&!"".equals(secReportVO.getId())){
				secReportService.updateSecReport(secReport);
				addSuccess ="updateOK";//修改成功标示
			}else{
				secReport.setId(null);
				secReportService.addSecReport(secReport);
				super.getServletRequest().setAttribute("id", secReport.getId());
				return "toSuccess";
			}
			
		}
		super.getServletRequest().setAttribute("addSuccess", addSuccess);
		return "toUpdate";
	}

	public String addSecReport() {
		
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateSecReport() {
		String[] modifiedData = (String[]) super.getParameters().get(
				"modifiedData");
		if (modifiedData != null) {
			JSONArray modifiedArr = JSONArray.fromObject("[" + modifiedData[0]
					+ "]");
			JSONObject obj = null;
			for (int i = 0; i < modifiedArr.size(); i++) {
				obj = (JSONObject) modifiedArr.get(i);
				Object isNew = obj.get("isNew");
				if (isNew != null && ((Boolean) isNew).booleanValue()) {
					SecReportVO bean = (SecReportVO) JSONObject.toBean(obj,
							SecReportVO.class);
					secReportService.addSecReport(this.convertVOToBO(bean));
				} else {
					secReportService.updateSecReport((SecReport) JSONObject
							.toBean(obj, SecReport.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private SecReport convertVOToBO(SecReportVO secReportVO) {
		SecReport secReport = new SecReport();
		BeanUtils.copyProperties(secReportVO, secReport);
		return secReport;
	}

	private SecReportVO convertBOToVO(SecReport secReport) {
		SecReportVO secReportVO = new SecReportVO();
		BeanUtils.copyProperties(secReport, secReportVO);
		return secReportVO;
	}


	public SecReportVO getSecReportVO() {
		return secReportVO;
	}

	public void setSecReportVO(SecReportVO secReportVO) {
		this.secReportVO = secReportVO;
	}

	public void setSecReportService(SecReportService secReportService) {
		this.secReportService = secReportService;
	}

	public XmlNodeVo getXmlNodeVo() {
		return xmlNodeVo;
	}

	public void setXmlNodeVo(XmlNodeVo xmlNodeVo) {
		this.xmlNodeVo = xmlNodeVo;
	}
	
	
	/**
	 * @author mengjie
	 * 数据导出excel 
	 */
	public void exportExcel(){
		String updateTimeOrder = this.servletRequest.getParameter("updateTimeOrder"); 
		String titleYear = this.servletRequest.getParameter("titleYear");
		String titleQuarter = this.servletRequest.getParameter("titleQuarter");	
		String createPersonName = this.servletRequest.getParameter("createPersonName");
		String modifyPersonName = this.servletRequest.getParameter("createPersonName");
		String creatTimeStart = this.servletRequest.getParameter("creatTimeStart");
		String creatTimeEnd = this.servletRequest.getParameter("creatTimeEnd");
		String modifyTimeStart = this.servletRequest.getParameter("modifyTimeStart");
		String modifyTimeEnd = this.servletRequest.getParameter("modifyTimeEnd");
		String filingDateStart = this.servletRequest.getParameter("filingDateStart");
		String filingDateEnd = this.servletRequest.getParameter("filingDateEnd");
		String company = this.servletRequest.getParameter("company");
//		String politicalLandscape = this.servletRequest.getParameter("politicalLandscape");
//		String isRetire = this.servletRequest.getParameter("isRetire");
		//String position = this.servletRequest.getParameter("position");
	
//		try {
//			hretName = new String(hretName.getBytes("ISO-8859-1"),"utf-8");	
//			name = new String(name.getBytes("ISO-8859-1"),"utf-8");
//			cCompany = new String(cCompany.getBytes("ISO-8859-1"),"utf-8");
//			position = new String(position.getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
		
		this.servletResponse.setContentType("octets/stream");  
		this.servletResponse.addHeader("Content-Disposition","attachment;filename=SecReport.xls");  
		ExportExcel<SecReport> ex = new ExportExcel<SecReport>();  
		String[] headers = {"年份","季度","工作单位","填表日期","创建时间","创建人","修改时间","修改人"};
		String[] all_headers = headers;
//		if("".equals(hretName)){
//			all_headers = headers;
//		}else{
//			List<Object[]> listHeaders = hrBInfoService.findTypeNameForExport(hretName);
//			all_headers = new String[headers.length+listHeaders.size()];
//			if(null!=listHeaders&&listHeaders.size()>0){
//				
//				for(int i=0;i<headers.length;i++){
//					all_headers[i]=headers[i];
//				}				
//				for(int i=0;i<listHeaders.size();i++){
//					all_headers[i+headers.length]=String.valueOf(listHeaders.get(i)[0]);
//				}
//			}
//			
//		}
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(!"".equals(titleYear)&&null!=titleYear){	
			filter.put("titleYear", titleYear);
		}
		if(!"".equals(titleQuarter)&&null!=titleQuarter){	
			filter.put("titleQuarter", titleQuarter);
		}
		if(!"".equals(company)&&null!=company){	
			filter.put("company", company);
		}		
		if(!"".equals(creatTimeStart)&&null!=creatTimeStart){				
			filter.put("creatTimeStart", creatTimeStart);
		}
		if(!"".equals(creatTimeEnd)&&null!=creatTimeEnd){			
			filter.put("creatTimeEnd", creatTimeEnd);
		}
		if(!"".equals(modifyTimeStart)&&null!=modifyTimeStart){	
			filter.put("modifyTimeStart", modifyTimeStart);
		}
		if(!"".equals(modifyTimeEnd)&&null!=modifyTimeEnd){	
			filter.put("modifyTimeEnd", modifyTimeEnd);
		}
		if(!"".equals(filingDateStart)&&null!=filingDateStart){	
			filter.put("filingDateStart", filingDateStart);
		}
		if(!"".equals(filingDateEnd)&&null!=filingDateEnd){	
			filter.put("filingDateEnd", filingDateEnd);
		}
		if(!"".equals(createPersonName)&&null!=createPersonName){	
			filter.put("createPersonName", createPersonName);
		}
		if(!"".equals(modifyPersonName)&&null!=modifyPersonName){	
			filter.put("modifyPersonName", modifyPersonName);
		}
//		if(!"".equals(position)&&null!=position){	
//			filter.put("position", position);	        
//		}
		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String filterButton=properties.getProperty("filterButton");
//		String deptId = "";		
/*		if("on".equals(filterButton)){
			List<Object> list = secReportService.findHrLimit();
			if(null!=list&&list.size()>0){
				String limit = String.valueOf(list.get(0));
				if("1".equals(limit)){
					CrossIpLogin crossIpLogin = new CrossIpLogin();
					UserInfo userInfo = new UserInfo();
					crossIpLogin.setUserInfo(servletRequest,userInfo);
					deptId = userInfo.getDeptId();
					filter.put("company_id", deptId);
				}				
			}
		}*/
		
		List<Object[]> dataset = new ArrayList<Object[]>();
		dataset = secReportService.findSecReportForExport(filter, updateTimeOrder);
/*//		if(null!=dataset&&dataset.size()>0){
//			for(int i=1;i<dataset.size();i++){
////				if(dataset.get(i)[1].equals(dataset.get(i-1)[1])){
//					for(int j=0;j<all_headers.length-headers.length;j++){
//						dataset.get(i-1)[j+headers.length] = String.valueOf(dataset.get(i-1)[j+headers.length])+"、"+String.valueOf(dataset.get(i)[j+headers.length]);
//					}
////					dataset.remove(i);
////					i=i-1;
//			}
////			}
//		}
*/		try {
			OutputStream out = this.servletResponse.getOutputStream();
			ex.exportExcel("安全报表数据库数据导出",all_headers,dataset,out);  
			out.close();  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
