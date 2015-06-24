package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.vo.AddUpdateGroupMemberVO;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.service.AddUpdateGroupMemberService;
import com.wonders.stpt.sthr.HrBInfo.entity.bo.HrBInfo;
import com.wonders.stpt.sthr.HrBInfo.entity.vo.HrBInfoVO;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class GroupMemberAction extends BaseAjaxAction{

	private AddUpdateGroupMember addUpdateGroupMember =new AddUpdateGroupMember();
	private AddUpdateGroupMemberService memberService;
	private AddUpdateGroupMemberVO avo=new AddUpdateGroupMemberVO();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public AddUpdateGroupMemberVO getAvo() {
		return avo;
	}
	public void setAvo(AddUpdateGroupMemberVO avo) {
		this.avo = avo;
	}
	@Override
	public ValueObject getValueObject() {
		return this.avo;
	}
	private AddUpdateGroupMember convertVOToBO(AddUpdateGroupMemberVO aVO) {
		AddUpdateGroupMember addUpdateGroupMember = new AddUpdateGroupMember();
		BeanUtils.copyProperties(aVO, addUpdateGroupMember, new String[] { "id" });
		return addUpdateGroupMember;
	}
	private AddUpdateGroupMemberVO convertBOToVO(AddUpdateGroupMember addUpdateGroupMember) {
		AddUpdateGroupMemberVO aVO = new AddUpdateGroupMemberVO();
		BeanUtils.copyProperties(addUpdateGroupMember, aVO);
		return aVO;
	}
	//封装方法	

	public String add(){
		AddUpdateGroupMember addUpdateGroupMember=convertVOToBO(avo);
		
		Date date=new Date();
		addUpdateGroupMember.setOperateTime(sdf.format(date));
		
		if(addUpdateGroupMember==null){
			return "add";
		}
		
		if (addUpdateGroupMember != null) {					
			memberService.addAddUpdateGroupMember(addUpdateGroupMember);
		}
		this.servletRequest.setAttribute("addSuccess", "success");		
		//createJSonData("{\"success\":true}");
		return "add";
	}

	public String toAdd(){
		
		return "toAdd";
	}

	
	public String findGroupMemberByPage(){
		System.out.println("findGroupMemberByPage");
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
			   page = memberService.findAddUpdateGroupMemberByPage(filter, start / size + 1, size, null);
			}else {
				page = memberService.findAddUpdateGroupMemberByPage(filter, currentPage,
						size,null);
			}
		System.out.println("end");
			//String json = VOUtils.getJsonDataFromPage(page, HrBInfo.class);
			//createJSonData(json);
			this.servletRequest.setAttribute("page", page);
			
		return "findGroupMemberByPage";
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

	public String findGroupMemberById(){
		
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		addUpdateGroupMember=memberService.findAddUpdateGroupMember(id);
		avo=convertBOToVO(addUpdateGroupMember);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}

	public String updateGroupMemberId(){
		
		AddUpdateGroupMember addUpdateGroupMember=convertVOToBO(avo);
		addUpdateGroupMember.setId(avo.getId());
		Date date=new Date();
		addUpdateGroupMember.setOperateTime(sdf.format(date));
		System.out.println("-------"+addUpdateGroupMember.getUpdateDate());
		if(addUpdateGroupMember!=null)
			memberService.updateAddUpdateGroupMember(addUpdateGroupMember);
	
		return SUCCESS;
	}
	
	
	
	public void setMemberService(AddUpdateGroupMemberService memberService) {
		this.memberService = memberService;
	}
	
	public String countAll(){
		List<Object[]> list = memberService.countAll();
		String json = VOUtils.getJsonDataFromCollection(list);
		createJSonData(json);
		return AJAX;
	}
	
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		AddUpdateGroupMember mo=memberService.findAddUpdateGroupMember(id);
		if(mo!=null){
			mo.setRemoved("1");
			memberService.updateAddUpdateGroupMember(mo);
		}
		
	}

}
