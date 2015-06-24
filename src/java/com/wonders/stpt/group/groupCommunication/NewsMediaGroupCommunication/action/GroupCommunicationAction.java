package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.bo.NewsMediaGroupCommunication;
import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.vo.NewsMediaGroupCommunicationvo;
import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.service.NewsMediaGroupCommunicationService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class GroupCommunicationAction extends BaseAjaxAction{
	private NewsMediaGroupCommunication newsMediaGroupCommunication = new NewsMediaGroupCommunication();
	private NewsMediaGroupCommunicationvo newsMediaGroupCommunicationvo = new NewsMediaGroupCommunicationvo();
	private NewsMediaGroupCommunicationService newsMediaGroupCommunicationService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public NewsMediaGroupCommunication getNewsMediaGroupCommunication() {
		return newsMediaGroupCommunication;
	}

	public void setNewsMediaGroupCommunication(
			NewsMediaGroupCommunication newsMediaGroupCommunication) {
		this.newsMediaGroupCommunication = newsMediaGroupCommunication;
	}

	public NewsMediaGroupCommunicationvo getNewsMediaGroupCommunicationvo() {
		return newsMediaGroupCommunicationvo;
	}

	public void setNewsMediaGroupCommunicationvo(
			NewsMediaGroupCommunicationvo newsMediaGroupCommunicationvo) {
		this.newsMediaGroupCommunicationvo = newsMediaGroupCommunicationvo;
	}

	public NewsMediaGroupCommunicationService getNewsMediaGroupCommunicationService() {
		return newsMediaGroupCommunicationService;
	}

	public void setNewsMediaGroupCommunicationService(
			NewsMediaGroupCommunicationService newsMediaGroupCommunicationService) {
		this.newsMediaGroupCommunicationService = newsMediaGroupCommunicationService;
	}

	@Override
	public ValueObject getValueObject() {
		return this.newsMediaGroupCommunicationvo;
	}
	
	private NewsMediaGroupCommunication convertVOToBO(NewsMediaGroupCommunicationvo aVO) {
		NewsMediaGroupCommunication bo = new NewsMediaGroupCommunication();
		BeanUtils.copyProperties(aVO, bo, new String[] { "id" });
		return bo;
	}
	private NewsMediaGroupCommunicationvo convertBOToVO(NewsMediaGroupCommunication bo) {
		NewsMediaGroupCommunicationvo aVO = new NewsMediaGroupCommunicationvo();
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
		NewsMediaGroupCommunication bo=convertVOToBO(newsMediaGroupCommunicationvo);
		
		Date date=new Date();
		bo.setOperateTime(sdf.format(date));
		
		if(bo==null){
			return "add";
		}
		
		if (bo != null) {					
			newsMediaGroupCommunicationService.addNewsMediaGroupCommunication(bo);
		}
		this.servletRequest.setAttribute("addSuccess", "success");		
		return SUCCESS;
	}

	public String toAdd(){
		
		return SUCCESS;
	}
	
	public String findByPage(){
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
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.newsMediaGroupCommunicationvo);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			
				value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.newsMediaGroupCommunicationvo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if (currentPage == 0) {
			   page = newsMediaGroupCommunicationService.findNewsMediaGroupCommunicationByPage(filter, start / size + 1, size, null);
			}else {
				page = newsMediaGroupCommunicationService.findNewsMediaGroupCommunicationByPage(filter, currentPage,size,null);
			}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String findById(){
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		newsMediaGroupCommunication=newsMediaGroupCommunicationService.findNewsMediaGroupCommunication(id);
		newsMediaGroupCommunicationvo=convertBOToVO(newsMediaGroupCommunication);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}

	public String update(){
		
		NewsMediaGroupCommunication newsMediaGroupCommunication=convertVOToBO(newsMediaGroupCommunicationvo);
		newsMediaGroupCommunication.setId(newsMediaGroupCommunicationvo.getId());
		if(newsMediaGroupCommunication!=null)
			newsMediaGroupCommunicationService.updateNewsMediaGroupCommunication(newsMediaGroupCommunication);
		return SUCCESS;
	}
	
	public void deleteData(){
		String id = this.servletRequest.getParameter("id");	
		NewsMediaGroupCommunication bo = newsMediaGroupCommunicationService.findNewsMediaGroupCommunication(id);
		if(bo!=null){
			bo.setRemoved("1");
			newsMediaGroupCommunicationService.updateNewsMediaGroupCommunication(bo);
		}
	}
}
