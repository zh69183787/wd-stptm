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

package com.wonders.stpt.accidentCase.MetroAccidentCase.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.service.AccidentCaseApprovalService;
import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo.MetroAccidentCase;
import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.vo.MetroAccidentCaseVO;
import com.wonders.stpt.accidentCase.MetroAccidentCase.service.MetroAccidentCaseService;
import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wonders.stpt.accidentCase.TAttach.service.TAttachService;
import com.wonders.stpt.accidentCase.Util.ConstantUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroAccidentCaseAction extends BaseAjaxAction {
	private MetroAccidentCase metroAccidentCase = new MetroAccidentCase();
	private MetroAccidentCaseVO metroAccidentCaseVO = new MetroAccidentCaseVO();
	private MetroAccidentCaseService metroAccidentCaseService;
	private AccidentCaseApprovalService accidentCaseApprovalService;
	private TAttach tAttach;
	
	private TAttachService attachService;
	
	public TAttachService getAttachService() {
		return attachService;
	}

	public void setAttachService(TAttachService attachService) {
		this.attachService = attachService;
	}

	public TAttach getTAttach() {
		return tAttach;
	}

	public void setTAttach(TAttach attach) {
		tAttach = attach;
	}

	private static final int BUFFER_SIZE = 20 * 1024; // 20K
	private File uploadify = null;
	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	private String uploadifyFileName;

	@Override
	public ValueObject getValueObject() {
		return this.metroAccidentCaseVO;
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

	public String findMetroAccidentCaseById() {
		String id = super.getServletRequest().getParameter("caseId");
		String type = super.getServletRequest().getParameter("type");
		String caseType = "";
		MetroAccidentCase bo = metroAccidentCaseService
				.findMetroAccidentCaseById(id);
		// if (bo != null) {
		// String json = VOUtils.getJsonData(convertBOToVO(bo));
		// createJSonData("{\"success\":true,\"result\":[" + json.toString()
		// + "]}");
		// }
		caseType = bo.getCaseType();
		metroAccidentCaseVO = convertBOToVO(bo);
		if (type.equals("edit")){
		  if(caseType.equals("1")) return "edit_E";
		  else return "edit";
		}			
		else if (type.equals("view")){
			if(caseType.equals("1")) return "view_E";
			  else return "view";
		}else
			
		return SUCCESS;
	}

	/**
	 * @author yaochenglong
	 * @param null
	 * @describe 分页查询
	 * @return String 
	 */
	public String findMetroAccidentCaseByPage() {

		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		if(metroAccidentCaseVO.getCaseType()==null||metroAccidentCaseVO.getCaseType().equals("")){
			metroAccidentCaseVO.setCaseType("1");
		}
		
		if(metroAccidentCaseVO.getApprovalStatus()==null||metroAccidentCaseVO.getApprovalStatus().equals("")){
			metroAccidentCaseVO.setApprovalStatus("eeee");
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.metroAccidentCaseVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			if(key.equals("caseType")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else if(key.equals("approvalStatus")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else{
				value = this.servletRequest.getParameter(key);
				if (value != null && value.trim().length() > 0) {
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}
			}
		}
		
		String occurSTime1 = this.servletRequest.getParameter("occurSTime1");
		if(occurSTime1!=null&&!"".equals(occurSTime1)){
			filter.put("occurSTime1", occurSTime1);
			this.servletRequest.setAttribute("occurSTime1", occurSTime1);
		}
		String occurSTime2 = this.servletRequest.getParameter("occurSTime2");
		if(occurSTime2!=null&&!"".equals(occurSTime2)){
			filter.put("occurSTime2", occurSTime2);
			this.servletRequest.setAttribute("occurSTime2", occurSTime2);
		}
		
		if (currentPage == 0) {
			page = metroAccidentCaseService.findMetroAccidentCaseByPage(filter,
					start / size + 1, size);
		} else {
			page = metroAccidentCaseService.findMetroAccidentCaseByPage(filter,
					currentPage, size);
		}

		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	/**
	 * @author yaochenglong
	 * @describe 分页查询 
	 * @return String
	 */
	public String findMetroAccidentCaseByPage2() {

		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

//		if(metroAccidentCaseVO.getCaseType()==null||metroAccidentCaseVO.getCaseType().equals("")){
//			metroAccidentCaseVO.setCaseType("1");
//		}
		
		if(metroAccidentCaseVO.getApprovalStatus()==null||metroAccidentCaseVO.getApprovalStatus().equals("")){
			metroAccidentCaseVO.setApprovalStatus("eeee");
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.metroAccidentCaseVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			if(key.equals("caseType")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else if(key.equals("approvalStatus")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else{
				value = this.servletRequest.getParameter(key);
				if (value != null && value.trim().length() > 0) {
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}
			}
		}
		String occurSTime1 = this.servletRequest.getParameter("occurSTime1");
		if(occurSTime1!=null&&!"".equals(occurSTime1)){
			filter.put("occurSTime1", occurSTime1);
			this.servletRequest.setAttribute("occurSTime1", occurSTime1);
		}
		String occurSTime2 = this.servletRequest.getParameter("occurSTime2");
		if(occurSTime2!=null&&!"".equals(occurSTime2)){
			filter.put("occurSTime2", occurSTime2);
			this.servletRequest.setAttribute("occurSTime2", occurSTime2);
		}
		
		if (currentPage == 0) {
			page = metroAccidentCaseService.findMetroAccidentCaseByPage2(filter,
					start / size + 1, size);
		} else {
			page = metroAccidentCaseService.findMetroAccidentCaseByPage2(filter,
					currentPage, size);
		}

		this.servletRequest.setAttribute("page", page);
		System.out.println("ssssss==="+this.servletRequest.getParameter("caseType"));
		if("".equals(this.servletRequest.getParameter("caseType"))||this.servletRequest.getParameter("caseType")==null){
			
			return "list_all";
		}else {
			metroAccidentCaseVO.setCaseType(this.servletRequest.getParameter("caseType"));
			return "list";
		}
		//return SUCCESS;
	}

	
	public String deleteMetroAccidentCase() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			MetroAccidentCase bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (MetroAccidentCase) JSONObject.toBean(obj,
						MetroAccidentCase.class);
				if (bean != null) {
					metroAccidentCaseService.deleteMetroAccidentCase(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return SUCCESS;
	}
	
	
	
	

	public String addMetroAccidentCase() throws ParseException {
		String caseType = super.getServletRequest().getParameter("caseType");
		MetroAccidentCase metroAccidentCase = convertVOToBO(metroAccidentCaseVO);		
		
		//时间控件转格式
		String sOccurSTime=super.getServletRequest().getParameter("occurSTime");
		String sReportTime=super.getServletRequest().getParameter("reportTime");
		sOccurSTime=(((sOccurSTime.replace("时", "")).replace("分", "00")).replace(":", "")).replace("-", "");
		sReportTime=(((sReportTime.replace("时", "")).replace("分", "00")).replace(":", "")).replace("-", "");		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date dOccurSTime = null;
		Date dReportTime = null;
		dOccurSTime=simpleDateFormat.parse(sOccurSTime);
		dReportTime=simpleDateFormat.parse(sReportTime);
		if (metroAccidentCase != null) {
			Date date = new Date();
			metroAccidentCase.setUpdateTime(date);
			metroAccidentCase.setOccurSTime(dOccurSTime);
			metroAccidentCase.setReportTime(dReportTime);
			metroAccidentCaseService.addMetroAccidentCase(metroAccidentCase);	
			metroAccidentCaseVO.setReportTime(date);
		}
		createJSonData("{\"success\":true}");

//		if (caseType.equals("1"))
//			return "1";
//		else if (caseType.equals("2"))
//			return "2";
//		else if (caseType.equals("3"))
//			return "3";
//		else if (caseType.equals("4"))
//			return "4";
		if(caseType.equals("1")) return "add_E";
		else return "add";
	}

	public String updateMetroAccidentCase() throws ParseException {
		// System.out.println("进入action");
		/*
		 * String[] modifiedData = (String[]) super.getParameters().get(
		 * "modifiedData"); if (modifiedData != null) { JSONArray modifiedArr =
		 * JSONArray.fromObject("[" + modifiedData[0] + "]"); JSONObject obj =
		 * null; for (int i = 0; i < modifiedArr.size(); i++) { obj =
		 * (JSONObject) modifiedArr.get(i); Object isNew = obj.get("isNew"); if
		 * (isNew != null && ((Boolean) isNew).booleanValue()) {
		 * MetroAccidentCaseVO bean = (MetroAccidentCaseVO) JSONObject
		 * .toBean(obj, MetroAccidentCaseVO.class);
		 * metroAccidentCaseService.addMetroAccidentCase(this
		 * .convertVOToBO(bean)); } else { metroAccidentCaseService
		 * .updateMetroAccidentCase((MetroAccidentCase) JSONObject .toBean(obj,
		 * MetroAccidentCase.class)); } } }
		 * createJSonData("{\"success\":true}");
		 */
		// String id = super.getServletRequest().getParameter("caseId");
		// metroAccidentCaseVO.setCaseId(id);
		// String a = this.getServletRequest().getParameter("aaaa");
		// System.out.println(a);
		// accidentCaseApprovalService.addAccidentCaseApproval(accidentCaseApproval);
		MetroAccidentCase metroAccidentCase = convertVOToBO(metroAccidentCaseVO);
		metroAccidentCase.setCaseId(metroAccidentCaseVO.getCaseId());
		// Date hh = new Date();
		// System.out.println(hh);

		// System.out.println(metroAccidentCase.getOccurSTime());
		String caseId = super.getServletRequest().getParameter("caseId");
		String approvalStatus = super.getServletRequest().getParameter(
				"approvalStatus");
		String approvalPerson = super.getServletRequest().getParameter(
				"approvalPerson");
		String approvalPersonName = super.getServletRequest().getParameter(
				"approvalPersonName");
		Date date = new Date();

		AccidentCaseApproval accidentCaseApproval = new AccidentCaseApproval();
		accidentCaseApproval.setCaseId(caseId);
		accidentCaseApproval.setApprovalStatus(approvalStatus);
		accidentCaseApproval.setApprovalPerson(approvalPerson);
		accidentCaseApproval.setApprovalPersonName(approvalPersonName);
		accidentCaseApproval.setApprovalTime(date);
		
		//时间控件转格式
		String sOccurSTime=super.getServletRequest().getParameter("occurSTime");
		String sReportTime=super.getServletRequest().getParameter("reportTime");
		sOccurSTime=(((sOccurSTime.replace("时", "")).replace("分", "00")).replace(":", "")).replace("-", "");
		sReportTime=(((sReportTime.replace("时", "")).replace("分", "00")).replace(":", "")).replace("-", "");		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date dOccurSTime = null;
		Date dReportTime = null;
		dOccurSTime=simpleDateFormat.parse(sOccurSTime);
		dReportTime=simpleDateFormat.parse(sReportTime);
		metroAccidentCase.setOccurSTime(dOccurSTime);
		metroAccidentCase.setReportTime(dReportTime);
		
		metroAccidentCase.setUpdateTime(date);
		
		if (metroAccidentCase != null) {
			metroAccidentCaseService.updateMetroAccidentCase(metroAccidentCase);
			metroAccidentCaseService
					.addAccidentCaseApproval(accidentCaseApproval);
		}
		createJSonData("{\"success\":true}");

		return SUCCESS;
	}

	private MetroAccidentCase convertVOToBO(
			MetroAccidentCaseVO metroAccidentCaseVO) {
		MetroAccidentCase metroAccidentCase = new MetroAccidentCase();
		BeanUtils.copyProperties(metroAccidentCaseVO, metroAccidentCase,
				new String[] { "caseId" });
		return metroAccidentCase;
	}

	private MetroAccidentCaseVO convertBOToVO(
			MetroAccidentCase metroAccidentCase) {
		MetroAccidentCaseVO metroAccidentCaseVO = new MetroAccidentCaseVO();
		BeanUtils.copyProperties(metroAccidentCase, metroAccidentCaseVO);
		return metroAccidentCaseVO;
	}

	public MetroAccidentCaseVO getMetroAccidentCaseVO() {
		return metroAccidentCaseVO;
	}

	public void setMetroAccidentCaseVO(MetroAccidentCaseVO metroAccidentCaseVO) {
		this.metroAccidentCaseVO = metroAccidentCaseVO;
	}

	public void setMetroAccidentCaseService(
			MetroAccidentCaseService metroAccidentCaseService) {
		this.metroAccidentCaseService = metroAccidentCaseService;
	}

	/**
	 * @author yaochenglong
	 * @describe 根据主键删除
	 * @return String
	 */
	public String deleteMetroAccidentCaseById() {
		String caseId = this.servletRequest.getParameter("caseId");
//		String caseId = metroAccidentCaseVO.getCaseId();
		if (caseId != null) {
			metroAccidentCaseService.deleteMetroAccidentCaseById(caseId);
		}

		return "deleteMetroAccidentCaseById";
	}
	
    /**
     * @author sunjiawei
     * @param null
     * @describe 添加页面跳转
     */	
	public String toAdd() {
		String caseType = super.getServletRequest().getParameter("caseType");
		metroAccidentCaseVO.setCaseType(caseType);
		Date date = new Date();
		metroAccidentCaseVO.setReportTime(date);
		if(caseType.equals("1")){
			return "add_E";
		}else
		return "add";
	}

	/**
	 * @author yaochenglong
	 * @describe 上传文件件
	 * @return String
	 */
	public String fileUpload() throws IOException {
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间

		// 获取资源文件中定义的上传路径
		String savePath = ConstantUtil.UPLOAD_DIR;
		if(!(new File(savePath)).exists()){
			new File(savePath).mkdirs();
		}
		
		//获取扩展名
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName
					.lastIndexOf(".")+1);
		}
		newFileName = nowTime +"."+ extName;
		File newFile = new File(savePath + newFileName);
		copy(uploadify, newFile);
		
		tAttach = new TAttach();
		tAttach.setFilename(uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf(".")));
		tAttach.setFileextname(extName);
		tAttach.setPath(savePath);
		tAttach.setFilesize(uploadify.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		tAttach.setUploaddate(date);
		tAttach.setRemoved(0l);
		tAttach.setSavefilename(newFileName);
		tAttach.setStatus("upload");

//		HttpSession session = this.servletRequest.getSession();
//		String uploaderLoginName = this.servletRequest.getSession().getAttribute("loginName").toString();
//		tAttach.setUploaderLoginName(uploaderLoginName);
		
		String groupid = this.servletRequest.getParameter("groupid");
		tAttach.setGroupid(groupid);
		attachService.saveTAttach(tAttach);
		servletRequest.setAttribute("groupId",tAttach.getGroupid());
		createJSonData(tAttach.getGroupid());
		String caseId = this.servletRequest.getParameter("caseId");
		if(caseId!=null && caseId!=""){
			metroAccidentCase = metroAccidentCaseService.findMetroAccidentCaseById(caseId);
			metroAccidentCase.setAttachment(nowTime);
			metroAccidentCaseService.updateMetroAccidentCase(metroAccidentCase);
		}
		
		return "fileUpload";
	}

	/**
	 * @author yaochenglong
	 * @param src File类型:文件源地址 
	 * @param dst File类型:文件存放地址
	 * @describe 复制文件到本地
	 * @return void
	 */
	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream

				(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream

				(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author yaochenglong
	 * @param null
	 * @describe 分页查询
	 * @return String 
	 */
	public String findMetroAccidentCaseByUpdatePerson() {

		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}

		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		if(metroAccidentCaseVO.getCaseType()==null||metroAccidentCaseVO.getCaseType().equals("")){
			metroAccidentCaseVO.setCaseType("1");
		}
		
		if(metroAccidentCaseVO.getApprovalStatus()==null||metroAccidentCaseVO.getApprovalStatus().equals("")){
			metroAccidentCaseVO.setApprovalStatus("eeee");
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.metroAccidentCaseVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			if(key.equals("caseType")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else if(key.equals("approvalStatus")){
				if(this.servletRequest.getParameter(key)==null){
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}else{
					value = this.servletRequest.getParameter(key);
					if (value != null && value.trim().length() > 0) {
						Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
						if (res != null) {
							filter.put(key, res);
						}
					}
				}
			}else{
				value = this.servletRequest.getParameter(key);
				if (value != null && value.trim().length() > 0) {
					Object res = this.getValueByParamName(this.metroAccidentCaseVO,key);
					if (res != null) {
						filter.put(key, res);
					}
				}
			}
		}
		String occurSTime1 = this.servletRequest.getParameter("occurSTime1");
		if(occurSTime1!=null&&!"".equals(occurSTime1)){
			filter.put("occurSTime1", occurSTime1);
			this.servletRequest.setAttribute("occurSTime1", occurSTime1);
		}
		String occurSTime2 = this.servletRequest.getParameter("occurSTime2");
		if(occurSTime2!=null&&!"".equals(occurSTime2)){
			filter.put("occurSTime2", occurSTime2);
			this.servletRequest.setAttribute("occurSTime2", occurSTime2);
		}
		
		String updatePerson = "";
		String loginName = "";
		String deptId = "";
		Cookie[] cookies = this.servletRequest.getCookies();
		for (Cookie cookie:cookies) {
			if("loginName".equals(cookie.getName()))
			{
				loginName = cookie.getValue();//带部门id的工号
			}
			if("deptId".equals(cookie.getName()))
			{
				deptId = cookie.getValue();
			}
		}
		if(!"".equals(loginName)&&!"".equals(deptId)){
			updatePerson = loginName.replace(deptId, "");
			//System.out.println(updatePerson);
		}
		//String updatePerson = (String) this.servletRequest.getSession().getAttribute("loginName");
		if(updatePerson!=null && !updatePerson.equals("")){
			filter.put("updatePerson", updatePerson);
		}
		
		if (currentPage == 0) {
			page = metroAccidentCaseService.findMetroAccidentCaseByUpdatePerson(filter,
					start / size + 1, size);
		} else {
			page = metroAccidentCaseService.findMetroAccidentCaseByUpdatePerson(filter,
					currentPage, size);
		}

		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键删除
	 * @return
	 */
	public String deleteByIdAndUpdatePerson(){
		
		String caseId = this.servletRequest.getParameter("caseId");
		if (caseId != null) {
			metroAccidentCaseService.deleteMetroAccidentCaseById(caseId);
		}
		
		return "deleteByIdAndUpdatePerson";
	}
	
	/**
	 * @author ycl
	 * @describe 跳转到编辑页面
	 * @return
	 */
	public String findByIdAndUpdatePerson() {
		String id = super.getServletRequest().getParameter("caseId");
		String caseType = super.getServletRequest().getParameter("caseType");
		MetroAccidentCase bo = metroAccidentCaseService.findMetroAccidentCaseById(id);
		metroAccidentCaseVO = convertBOToVO(bo);
		if(caseType.equals("1")){
			return "findByIdAndUpdatePerson_E";
		}else
		return "findByIdAndUpdatePerson";
		//return "findByIdAndUpdatePerson";
	}

}
