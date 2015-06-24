package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo.AddUpdateGroupLeader;
import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.vo.AddUpdateGroupLeadervo;
import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.service.AddUpdateGroupLeaderService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;



public class GroupLeaderAction extends BaseAjaxAction{

	private AddUpdateGroupLeader addUpdateGroupLeader=new AddUpdateGroupLeader();
	private AddUpdateGroupLeaderService leaderService;
	private AddUpdateGroupLeadervo avo=new AddUpdateGroupLeadervo();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public AddUpdateGroupLeadervo getAvo() {
		return avo;
	}
	public void setAvo(AddUpdateGroupLeadervo avo) {
		this.avo = avo;
	}
	public void setLeaderService(AddUpdateGroupLeaderService leaderService) {
		this.leaderService = leaderService;
	}
	private AddUpdateGroupLeader convertVOToBO(AddUpdateGroupLeadervo aVO) {
		AddUpdateGroupLeader addUpdateGroupLeader = new AddUpdateGroupLeader();
		BeanUtils.copyProperties(aVO, addUpdateGroupLeader, new String[] { "id" });
		return addUpdateGroupLeader;
	}
	private AddUpdateGroupLeadervo convertBOToVO(AddUpdateGroupLeader addUpdateGroupLeader) {
		AddUpdateGroupLeadervo aVO = new AddUpdateGroupLeadervo();
		BeanUtils.copyProperties(addUpdateGroupLeader, aVO);
		return aVO;
	}
	@Override
	public ValueObject getValueObject() {
		return this.avo;
	}

	public String findGroupLeaderByPage(){
		System.out.println("findGroupLeaderByPage");
		
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
			   page = leaderService.findAddUpdateGroupLeaderByPage(filter, start / size + 1, size, null);
			}else {
				page = leaderService.findAddUpdateGroupLeaderByPage(filter, currentPage,
						size,null);
			}
		System.out.println("end");
			//String json = VOUtils.getJsonDataFromPage(page, HrBInfo.class);
			//createJSonData(json);
			this.servletRequest.setAttribute("page", page);
		
		return "findGroupLeaderByPage";
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

	public String toAdd(){
		
		return "toAdd";
	}
	
	public String add(){
		AddUpdateGroupLeader addUpdateGroupLeader=convertVOToBO(avo);
		
		Date date=new Date();
		addUpdateGroupLeader.setOperateTime(sdf.format(date));
		System.out.println(addUpdateGroupLeader.getBranchName());
		if(addUpdateGroupLeader!=null){
			leaderService.addAddUpdateGroupLeader(addUpdateGroupLeader);
		}
		this.servletRequest.setAttribute("addSuccess", "success");	
		return "add";
	}

	public String findGroupLeaderById(){
		
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		addUpdateGroupLeader=leaderService.findAddUpdateGroupLeader(id);
		avo=convertBOToVO(addUpdateGroupLeader);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}

    public String updateGroupLeaderId(){
    	System.out.println("updateGroupLeaderId");
    	AddUpdateGroupLeader addUpdateGroupLeader=convertVOToBO(avo);
    	Date date=new Date();
    	if(addUpdateGroupLeader!=null){
    		addUpdateGroupLeader.setId(avo.getId());
    		addUpdateGroupLeader.setOperateTime(sdf.format(date));
    		leaderService.updateAddUpdateGroupLeader(addUpdateGroupLeader);
    		return SUCCESS;
    	}
    	return "error";
    }

    public void deleteData(){
    	String id=this.servletRequest.getParameter("id");
    	AddUpdateGroupLeader lo=leaderService.findAddUpdateGroupLeader(id);
    	if(lo!=null){
    		lo.setRemoved("1");
    		leaderService.updateAddUpdateGroupLeader(lo);
    	}
    	
    }

}
