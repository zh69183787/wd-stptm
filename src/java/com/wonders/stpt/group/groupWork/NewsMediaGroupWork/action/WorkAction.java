package com.wonders.stpt.group.groupWork.NewsMediaGroupWork.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.entity.bo.NewsMediaGroupWork;
import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.entity.vo.NewsMediaGroupWorkvo;
import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.service.NewsMediaGroupWorkService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class WorkAction extends BaseAjaxAction {

	private NewsMediaGroupWork newsMediaGroupWork=new NewsMediaGroupWork();
	private NewsMediaGroupWorkvo avo=new NewsMediaGroupWorkvo();
	private NewsMediaGroupWorkService workService;
	public NewsMediaGroupWorkvo getAvo() {
		return avo;
	}
	public void setAvo(NewsMediaGroupWorkvo avo) {
		this.avo = avo;
	}
	public void setWorkService(NewsMediaGroupWorkService workService) {
		this.workService = workService;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ValueObject getValueObject() {
		return this.avo;
	}
	private NewsMediaGroupWork convertVOToBO(NewsMediaGroupWorkvo aVO) {
		NewsMediaGroupWork newsMediaGroupWork = new NewsMediaGroupWork();
		BeanUtils.copyProperties(aVO, newsMediaGroupWork, new String[] { "id" });
		return newsMediaGroupWork;
	}
	private NewsMediaGroupWorkvo convertBOToVO(NewsMediaGroupWork newsMediaGroupWork) {
		NewsMediaGroupWorkvo aVO = new NewsMediaGroupWorkvo();
		BeanUtils.copyProperties(newsMediaGroupWork, aVO);
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

	public String findGroupWorkByPage(){
		
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");	
		int currentPage = 0;
		System.out.println(currentPageStr+"+++++++++++++++++");
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		System.out.println("start----"+pStart+"-----limit-----"+pSize);
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}
		
		String key = null;
		String value = null;
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
		
		if (currentPage == 0) {
			   page = workService.findNewsMediaGroupWorkByPage(filter, start / size + 1, size, null);
					
			}else {
				page = workService.findNewsMediaGroupWorkByPage(filter, currentPage,
						size,null);
			}
		System.out.println("end");
			this.servletRequest.setAttribute("page", page);
		return "findGroupWorkByPage";
	}
	
	public String toAdd(){
		return "toAdd";
	}
	
	public String add(){
		NewsMediaGroupWork  newsMediaGroupWork=convertVOToBO(avo);
		Date date=new Date();
		if(newsMediaGroupWork!=null){
			newsMediaGroupWork.setOperateTime(sdf.format(date));
			workService.addNewsMediaGroupWork(newsMediaGroupWork);
			this.servletRequest.setAttribute("addSuccess", "success");
			return "add";
		}
		return "error";
	}
	
	public String findGroupWorkById(){
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		newsMediaGroupWork=workService.findNewsMediaGroupWork(id);
		avo=convertBOToVO(newsMediaGroupWork);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}
	public String updateGroupWorkId(){
		NewsMediaGroupWork newsMediaGroupWork=convertVOToBO(avo);
		newsMediaGroupWork.setId(avo.getId());
		Date date=new Date();
		System.out.println("update");
		newsMediaGroupWork.setOperateTime(sdf.format(date));
		if(newsMediaGroupWork!=null){
			workService.updateNewsMediaGroupWork(newsMediaGroupWork);
			return SUCCESS;
		}
		return "error";
	}
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		NewsMediaGroupWork nwo=workService.findNewsMediaGroupWork(id);
		if(nwo!=null){
			nwo.setRemoved("1");
			workService.updateNewsMediaGroupWork(nwo);
		}
	}
}
