package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.action;

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
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo.AddGroupMemberDirectly;
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.vo.AddGroupMemberDirectlyvo;
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.service.AddGroupMemberDirectlyService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class GroupMemberDirectlyAction extends BaseAjaxAction{

	AddGroupMemberDirectly addGroupMemberDirectly=new AddGroupMemberDirectly();
	AddGroupMemberDirectlyvo avo=new AddGroupMemberDirectlyvo();
	AddGroupMemberDirectlyService memberDirectlyService;
	public AddGroupMemberDirectlyvo getAvo() {
		return avo;
	}
	public void setAvo(AddGroupMemberDirectlyvo avo) {
		this.avo = avo;
	}
	public void setMemberDirectlyService(
			AddGroupMemberDirectlyService memberDirectlyService) {
		this.memberDirectlyService = memberDirectlyService;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ValueObject getValueObject() {
		return this.avo;
	}
	private AddGroupMemberDirectly convertVOToBO(AddGroupMemberDirectlyvo aVO) {
		AddGroupMemberDirectly addGroupMemberDirectly = new AddGroupMemberDirectly();
		BeanUtils.copyProperties(aVO, addGroupMemberDirectly, new String[] { "id" });
		return addGroupMemberDirectly;
	}
	private AddGroupMemberDirectlyvo convertBOToVO(AddGroupMemberDirectly addGroupMemberDirectly) {
		AddGroupMemberDirectlyvo aVO = new AddGroupMemberDirectlyvo();
		BeanUtils.copyProperties(addGroupMemberDirectly, aVO);
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

	public String findGroupMemberDirectlyByPage(){
		System.out.println("findGroupMemberDirectlyByPage");
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
			   page = memberDirectlyService.findAddGroupMemberDirectlyByPage(filter, start / size + 1, size, null);
					   
			}else {
				page = memberDirectlyService.findAddGroupMemberDirectlyByPage(filter, currentPage,
						size,null);
			}
			//String json = VOUtils.getJsonDataFromPage(page, HrBInfo.class);
			//createJSonData(json);
			this.servletRequest.setAttribute("page", page);
		return "findGroupMemberDirectlyByPage";
	}
	
	public String toAdd(){
		
		return "toAdd";
	}

	public String add(){
		AddGroupMemberDirectly  addGroupMemberDirectly=convertVOToBO(avo);
		Date date=new Date();
		System.out.println(addGroupMemberDirectly.getUnit());
		if(addGroupMemberDirectly!=null){
			addGroupMemberDirectly.setOperateTime(sdf.format(date));
			memberDirectlyService.addAddGroupMemberDirectly(addGroupMemberDirectly);
		}
		this.servletRequest.setAttribute("addSuccess", "success");	
		return "add";
	}
	
	public String findGroupMemberDirectlyById(){
		String types=this.servletRequest.getParameter("types");
		String id=this.servletRequest.getParameter("id");
		addGroupMemberDirectly=memberDirectlyService.findAddGroupMemberDirectly(id);
		avo=convertBOToVO(addGroupMemberDirectly);
		if (types.equals("edit"))
			return "edit";
		else if (types.equals("view"))
			return "view";
		return "view";
	}
	
	public String updateGroupMemberDirectlyId(){
		addGroupMemberDirectly=convertVOToBO(avo);
		addGroupMemberDirectly.setId(avo.getId());
		System.out.println(addGroupMemberDirectly.getUnit());
		Date date=new Date();
		if(addGroupMemberDirectly!=null){
			addGroupMemberDirectly.setOperateTime(sdf.format(date));
			memberDirectlyService.updateAddGroupMemberDirectly(addGroupMemberDirectly);
			return SUCCESS;
		}
		return "error";
	}
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		AddGroupMemberDirectly mdo=memberDirectlyService.findAddGroupMemberDirectly(id);
		if(mdo!=null){
			mdo.setRemoved("1");
			memberDirectlyService.updateAddGroupMemberDirectly(mdo);
		}
	}
}
