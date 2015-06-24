package com.wonders.stpt.rectification.rectificationRecord.action;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;
import com.wonders.stpt.rectification.rectificationRecord.entity.vo.RectificationRecordvo;
import com.wonders.stpt.rectification.rectificationRecord.service.RectificationRecordService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class RectificationRecordAction extends BaseAction {

	private RectificationRecordService  recordService;
	private RectificationRecord record=new RectificationRecord();
	private RectificationRecordvo avo=new RectificationRecordvo();
	
	public RectificationRecord getRecord() {
		return record;
	}
	public void setRecord(RectificationRecord record) {
		this.record = record;
	}
	public RectificationRecordService getRecordService() {
		return recordService;
	}
	public RectificationRecordvo getAvo() {
		return avo;
	}
	public void setAvo(RectificationRecordvo avo) {
		this.avo = avo;
	}
	public void setRecordService(RectificationRecordService recordService) {
		this.recordService = recordService;
	}

	@Override
	public ValueObject getValueObject() {
		return this.avo;
	}
	private RectificationRecord convertVOToBO(RectificationRecordvo aVO) {
		RectificationRecord bo = new RectificationRecord();
		BeanUtils.copyProperties(aVO, bo, new String[] { "id" });
		return bo;
	}
	private RectificationRecordvo convertBOToVO(RectificationRecord bo) {
		RectificationRecordvo aVO = new RectificationRecordvo();
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
		record=convertVOToBO(avo);
		if(record!=null){
			Date date=new Date();
			record.setOperatetime(date);
			record.setCreatetime(date);
			record.setRemoved("0");
			recordService.addRectificationRecord(record);
			return SUCCESS;
		}
		return ERROR;
	}

	public String toAdd(){
		String wid=this.servletRequest.getParameter("wid");
		System.out.println(wid);
		this.servletRequest.setAttribute("wid", wid);
		return "toAdd";
	}
	
	public void deleteRectificationRecordById(){
		String id=this.servletRequest.getParameter("id");
		record=recordService.findRectificationRecordById(id);
		if(record!=null){
			record.setRemoved("1");
			recordService.updateRectificationRecord(record);
		}
	}
	
	public String findRectificationRecordByPage(){
		System.out.println(avo.getWid());
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
		if (currentPage == 0) {
			   //page = rectificationWorkService.findRectificationWorkByPage(filter, start / size + 1, size, null);
			page=recordService.findRectificationRecordByPage(filter, start / size + 1, size, null);
		}else {
				page = recordService.findRectificationRecordByPage(filter, currentPage,size,null);
			}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}

	public String findRectificationRecordById(){
			String id=this.servletRequest.getParameter("id");
			String type=this.servletRequest.getParameter("type");
			record=recordService.findRectificationRecordById(id);
			avo=convertBOToVO(record);
			if(type.equals("edit")){
				return "edit";
			}else if(type.equals("view")){
				return "view";
			}else{
				return ERROR;
			}
	}
	public String updateRectificationRecordById(){
		record=convertVOToBO(avo);
		record.setId(avo.getId());
		Date date =new Date();
		record.setCreatetime(date);
		record.setOperatetime(date);
		if(avo.getRemoved()==""||avo.getRemoved()==null)
			record.setRemoved("0");
		if(record!=null){
			recordService.updateRectificationRecord(record);
			return SUCCESS;
		}
		return ERROR;
	}
}
