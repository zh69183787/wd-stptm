package com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.vo.AddUpdateGroupMemberVO;
import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.bo.AddGroupNewMedia;
import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.vo.AddGroupNewMediavo;
import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.service.AddGroupNewMediaService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class AddGroupNewMediaAction extends BaseAjaxAction {
	private AddGroupNewMedia addGroupNewMedia=new AddGroupNewMedia();
	private AddGroupNewMediavo avo=new AddGroupNewMediavo();
	private AddGroupNewMediaService newMediaService;
	public AddGroupNewMediavo getAvo() {
		return avo;
	}
	public void setAvo(AddGroupNewMediavo avo) {
		this.avo = avo;
	}
	public void setNewMediaService(AddGroupNewMediaService newMediaService) {
		this.newMediaService = newMediaService;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ValueObject getValueObject() {
		return this.avo;
	}
	private AddGroupNewMedia convertVOToBO(AddGroupNewMediavo aVO) {
		AddGroupNewMedia addGroupNewMedia = new AddGroupNewMedia();
		BeanUtils.copyProperties(aVO, addGroupNewMedia, new String[] { "id" });
		return addGroupNewMedia;
	}
	private AddGroupNewMediavo convertBOToVO(AddGroupNewMedia addGroupNewMedia) {
		AddGroupNewMediavo aVO = new AddGroupNewMediavo();
		BeanUtils.copyProperties(addGroupNewMedia, aVO);
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

	public String findGroupNewMediaByPage(){
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
			   page = newMediaService.findAddGroupNewMediaByPage(filter, start / size + 1, size, null);
			}else {
				page = newMediaService.findAddGroupNewMediaByPage(filter, currentPage,
						size,null);
			}
		System.out.println("end");
			//String json = VOUtils.getJsonDataFromPage(page, HrBInfo.class);
			//createJSonData(json);
			this.servletRequest.setAttribute("page", page);
		return "findGroupNewMediaByPage";
	}

	public String add(){
		AddGroupNewMedia addGroupNewMedia=convertVOToBO(avo);
		Date date=new Date();
		System.out.println("add");
		if(addGroupNewMedia!=null){
			addGroupNewMedia.setOperateTime(sdf.format(date));
			newMediaService.addAddGroupNewMedia(addGroupNewMedia);
			this.servletRequest.setAttribute("addSuccess", "success");
			return "add";
		}
		return "error";
	}
	public String toAdd(){
		return "toAdd";
	}
	
	public String findGroupNewMediaById(){
		
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		addGroupNewMedia=newMediaService.findAddGroupNewMedia(id);
		avo=convertBOToVO(addGroupNewMedia);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}
	
	public String updateGroupNewMediaId(){
		AddGroupNewMedia  addGroupNewMedia=convertVOToBO(avo);
		addGroupNewMedia.setId(avo.getId());
		Date date=new Date();
		if(addGroupNewMedia!=null){
			addGroupNewMedia.setOperateTime(sdf.format(date));
			newMediaService.updateAddGroupNewMedia(addGroupNewMedia);
			return SUCCESS;
		}
		
		return "error";
	}
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		AddGroupNewMedia nmo=newMediaService.findAddGroupNewMedia(id);
		if(nmo!=null){
			nmo.setRemoved("1");
			newMediaService.updateAddGroupNewMedia(nmo);
		}
	}
	
}
