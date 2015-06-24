package com.wonders.stpt.rectification.rectificationWork.action;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.junit.runner.Request;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;
import com.wonders.stpt.rectification.rectificationRecord.entity.vo.RectificationRecordvo;
import com.wonders.stpt.rectification.rectificationRecord.service.RectificationRecordService;
import com.wonders.stpt.rectification.rectificationWork.entity.bo.RectificationWork;
import com.wonders.stpt.rectification.rectificationWork.entity.vo.RectificationWorkVo;
import com.wonders.stpt.rectification.rectificationWork.service.RectificationWorkService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
/**
 * 整改工作情况action
 * @author Administrator
 *
 */
public class RectificationWorkAction extends BaseAction {

	private RectificationWorkService rectificationWorkService;
	private RectificationWork rectificationWork=new RectificationWork();
	private RectificationWorkVo avo=new RectificationWorkVo();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public RectificationRecordvo getAvo1() {
		return avo1;
	}
	public void setAvo1(RectificationRecordvo avo1) {
		this.avo1 = avo1;
	}

	private RectificationRecordService  recordService;
	private RectificationRecord record=new RectificationRecord();
	private RectificationRecordvo avo1=new RectificationRecordvo();
	
	public RectificationWorkService getRectificationWorkService() {
		return rectificationWorkService;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public RectificationRecordService getRecordService() {
		return recordService;
	}
	public void setRecordService(RectificationRecordService recordService) {
		this.recordService = recordService;
	}
	public RectificationRecord getRecord() {
		return record;
	}
	public void setRecord(RectificationRecord record) {
		this.record = record;
	}
	public void setRectificationWorkService(
			RectificationWorkService rectificationWorkService) {
		this.rectificationWorkService = rectificationWorkService;
	}
	public RectificationWork getRectificationWork() {
		return rectificationWork;
	}
	public void setRectificationWork(RectificationWork rectificationWork) {
		this.rectificationWork = rectificationWork;
	}
	public RectificationWorkVo getAvo() {
		return avo;
	}
	public void setAvo(RectificationWorkVo avo) {
		this.avo = avo;
	}
	@Override
	public ValueObject getValueObject() {
		return this.avo;
	}
	
	private RectificationWork convertVOToBO(RectificationWorkVo aVO) {
		RectificationWork bo = new RectificationWork();
		BeanUtils.copyProperties(aVO, bo, new String[] { "id" });
		return bo;
	}
	private RectificationWorkVo convertBOToVO(RectificationWork bo) {
		RectificationWorkVo aVO = new RectificationWorkVo();
		BeanUtils.copyProperties(bo, aVO);
		return aVO;
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
	
	public String add(){
		RectificationWork rw=convertVOToBO(avo);
		if(rw!=null){
			Date date=new Date();
			rw.setOperationTime(date);
			rw.setCreateTime(date);
			rw.setRemoved("0");
			rectificationWorkService.addRectificationWork(rw);
			return SUCCESS;
		}
		return ERROR;
	}
	public String toAdd(){
		String inputDate = df.format(new Date());
		this.servletRequest.setAttribute("inputDate", inputDate);
		return "toAdd";
	}
	
	public void deleteRectificationWorkById(){
		String id=this.servletRequest.getParameter("id");
		rectificationWork=rectificationWorkService.findRectificationWorkById(id);
		if(rectificationWork!=null){
			rectificationWork.setRemoved("1");
			rectificationWorkService.updateRectificationWork(rectificationWork);
		}
	}
	
 	public String findRectificationWorkByPage(){
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");	
		String operateType = this.servletRequest.getParameter("operateType");
		Cookie[] cookies = this.servletRequest.getCookies();
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
		
		String key = null;
		String value = null;
		//获取页面参数加入map中
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.avo);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			
				value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.avo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		//页面获取参数结束
		String dept_id = null;
		String gonghao=null;
		String deptName=null;
		String userName=null;
		int i=0;
		for (Cookie cookie:cookies) {
			if("deptId".equals(cookie.getName()))
			{
				dept_id = cookie.getValue();
				i++;
			}
			if("loginName".equals(cookie.getName()))
			{
				gonghao = cookie.getValue();
				i++;
			}
			if("deptName".equals(cookie.getName()))
			{
				try {
					deptName = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			if("userName".equals(cookie.getName()))
			{
				try {
					userName = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			if(i==4)break;
		}
		this.servletRequest.setAttribute("operateType", operateType);
		System.out.println(operateType+"----"+dept_id+"----"+deptName+"----"+gonghao+"----"+userName);
		if(null!=dept_id&&!"".equals(dept_id)){
			filter.put("dept_id", dept_id);
		}
		if(null!=gonghao&&!"".equals(gonghao)){
			filter.put("gonghao", gonghao);
		}
		if(null!=deptName&&!"".equals(deptName)){
			filter.put("deptName", deptName);
		}
		if(null!=userName&&!"".equals(userName)){
			filter.put("userName", userName);
		}
		System.out.println(filter.get(gonghao));
		if (currentPage == 0) {
			    page = rectificationWorkService.findRectificationWorkByPage(filter, start / size + 1, size, operateType);
			}else {
			    page = rectificationWorkService.findRectificationWorkByPage(filter, currentPage,size,operateType);
			}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String findRectificationWorkById(){
		String id=this.servletRequest.getParameter("id");
		String type=this.servletRequest.getParameter("type");
		rectificationWork=rectificationWorkService.findRectificationWorkById(id);
		avo=convertBOToVO(rectificationWork);
		if(type.equals("edit")){
			return "edit";
		}else if(type.equals("view")){
			return "view";
		}else{
			return ERROR;
		}
		
	}

	public String updateRectificationWorkById(){
		rectificationWork=convertVOToBO(avo);
		rectificationWork.setId(avo.getId());
		Date date=new Date();
		rectificationWork.setCreateTime(date);
		rectificationWork.setOperationTime(date);
		rectificationWork.setRemoved("0");
		if(rectificationWork!=null){
			rectificationWorkService.updateRectificationWork(rectificationWork);
			return SUCCESS;
		}
		return ERROR;
	}

	public String findRectificationWorkAndRecordById(){
		String id=this.servletRequest.getParameter("wid");
		System.out.println(id);
		if(id==""&&id==null){
			id=avo1.getWid();
		}
		rectificationWork=rectificationWorkService.findRectificationWorkById(id);
		avo=convertBOToVO(rectificationWork);
		
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
		
		String key = null;
		String value = null;
		//获取页面参数加入map中
		Map<String, Object> filter = new HashMap<String, Object>();
		/*JSONObject obj = JSONObject.fromObject(this.avo1);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			
				value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.avo1, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		//页面获取参数结束*/
		filter.put("wid", id);
		if (currentPage == 0) {
			page=recordService.findRectificationRecordByPage(filter, start / size + 1, size,null);
		}else {
			page=recordService.findRectificationRecordByPage(filter, currentPage, size, null);
		}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
}
