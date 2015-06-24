package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.bo.NewsMediaGroupActive;
import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.vo.NewsMediaGroupActivevo;
import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.service.NewsMediaGroupActiveService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class GroupActiveAction extends BaseAjaxAction{
	private NewsMediaGroupActive newsMediaGroupActive = new NewsMediaGroupActive();
	private NewsMediaGroupActivevo newsMediaGroupActivevo = new NewsMediaGroupActivevo();
	private NewsMediaGroupActiveService newsMediaGroupActiveService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public NewsMediaGroupActive getNewsMediaGroupActive() {
		return newsMediaGroupActive;
	}

	public void setNewsMediaGroupActive(NewsMediaGroupActive newsMediaGroupActive) {
		this.newsMediaGroupActive = newsMediaGroupActive;
	}

	public NewsMediaGroupActivevo getNewsMediaGroupActivevo() {
		return newsMediaGroupActivevo;
	}

	public void setNewsMediaGroupActivevo(
			NewsMediaGroupActivevo newsMediaGroupActivevo) {
		this.newsMediaGroupActivevo = newsMediaGroupActivevo;
	}

	public NewsMediaGroupActiveService getNewsMediaGroupActiveService() {
		return newsMediaGroupActiveService;
	}
	
	public void setNewsMediaGroupActiveService(
			NewsMediaGroupActiveService newsMediaGroupActiveService) {
		this.newsMediaGroupActiveService = newsMediaGroupActiveService;
	}

	@Override
	public ValueObject getValueObject() {
		return this.newsMediaGroupActivevo;
	}
	
	private NewsMediaGroupActive convertVOToBO(NewsMediaGroupActivevo aVO) {
		NewsMediaGroupActive bo = new NewsMediaGroupActive();
		BeanUtils.copyProperties(aVO, bo, new String[] { "id" });
		return bo;
	}
	private NewsMediaGroupActivevo convertBOToVO(NewsMediaGroupActive bo) {
		NewsMediaGroupActivevo aVO = new NewsMediaGroupActivevo();
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
		NewsMediaGroupActive bo=convertVOToBO(newsMediaGroupActivevo);
		
		Date date=new Date();
		bo.setOperateTime(sdf.format(date));
		
		if(bo==null){
			return "add";
		}
		
		if (bo != null) {					
			newsMediaGroupActiveService.addNewsMediaGroupActive(bo);
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
		JSONObject obj = JSONObject.fromObject(this.newsMediaGroupActivevo);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			
				value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.newsMediaGroupActivevo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if (currentPage == 0) {
			   page = newsMediaGroupActiveService.findNewsMediaGroupActiveByPage(filter, start / size + 1, size, null);
			}else {
				page = newsMediaGroupActiveService.findNewsMediaGroupActiveByPage(filter, currentPage,size,null);
			}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String findById(){
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		newsMediaGroupActive=newsMediaGroupActiveService.findNewsMediaGroupActive(id);
		newsMediaGroupActivevo=convertBOToVO(newsMediaGroupActive);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}

	public String update(){
		
		NewsMediaGroupActive newsMediaGroupActive=convertVOToBO(newsMediaGroupActivevo);
		newsMediaGroupActive.setId(newsMediaGroupActivevo.getId());
		if(newsMediaGroupActive!=null)
			newsMediaGroupActiveService.updateNewsMediaGroupActive(newsMediaGroupActive);
		return SUCCESS;
	}
	
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		NewsMediaGroupActive no=newsMediaGroupActiveService.findNewsMediaGroupActive(id);
		if(no!=null){
			no.setRemoved("1");
			newsMediaGroupActiveService.updateNewsMediaGroupActive(no);
		}
		
	}
}
