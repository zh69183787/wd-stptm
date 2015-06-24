package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.bo.Real_nameGroupWibo;
import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.vo.Real_nameGroupWibovo;
import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.service.Real_nameGroupWiboService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class GroupRealnameAction extends BaseAjaxAction{
	private Real_nameGroupWibo real_nameGroupWibo = new Real_nameGroupWibo();
	private Real_nameGroupWibovo real_nameGroupWibovo = new Real_nameGroupWibovo();
	private Real_nameGroupWiboService real_nameGroupWiboService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Real_nameGroupWibo getReal_nameGroupWibo() {
		return real_nameGroupWibo;
	}

	public void setReal_nameGroupWibo(Real_nameGroupWibo realNameGroupWibo) {
		real_nameGroupWibo = realNameGroupWibo;
	}

	public Real_nameGroupWibovo getReal_nameGroupWibovo() {
		return real_nameGroupWibovo;
	}

	public void setReal_nameGroupWibovo(Real_nameGroupWibovo realNameGroupWibovo) {
		real_nameGroupWibovo = realNameGroupWibovo;
	}

	public Real_nameGroupWiboService getReal_nameGroupWiboService() {
		return real_nameGroupWiboService;
	}

	public void setReal_nameGroupWiboService(
			Real_nameGroupWiboService realNameGroupWiboService) {
		real_nameGroupWiboService = realNameGroupWiboService;
	}
	
	@Override
	public ValueObject getValueObject() {
		return this.real_nameGroupWibovo;
	}
	
	private Real_nameGroupWibo convertVOToBO(Real_nameGroupWibovo aVO) {
		Real_nameGroupWibo real_nameGroupWibo = new Real_nameGroupWibo();
		BeanUtils.copyProperties(aVO, real_nameGroupWibo, new String[] { "id" });
		return real_nameGroupWibo;
	}
	private Real_nameGroupWibovo convertBOToVO(Real_nameGroupWibo real_nameGroupWibo) {
		Real_nameGroupWibovo aVO = new Real_nameGroupWibovo();
		BeanUtils.copyProperties(real_nameGroupWibo, aVO);
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
		Real_nameGroupWibo real_nameGroupWibo=convertVOToBO(real_nameGroupWibovo);
		
		Date date=new Date();
		real_nameGroupWibo.setOperateTime(sdf.format(date));
		
		if(real_nameGroupWibo==null){
			return "add";
		}
		
		if (real_nameGroupWibo != null) {					
			real_nameGroupWiboService.addReal_nameGroupWibo(real_nameGroupWibo);
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
		JSONObject obj = JSONObject.fromObject(this.real_nameGroupWibovo);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			
				value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.real_nameGroupWibovo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		if (currentPage == 0) {
			   page = real_nameGroupWiboService.findReal_nameGroupWiboByPage(filter, start / size + 1, size, null);
			}else {
				page = real_nameGroupWiboService.findReal_nameGroupWiboByPage(filter, currentPage,size,null);
			}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String findById(){
		String type=this.servletRequest.getParameter("type");
		String id=this.servletRequest.getParameter("id");
		real_nameGroupWibo=real_nameGroupWiboService.findReal_nameGroupWibo(id);
		real_nameGroupWibovo=convertBOToVO(real_nameGroupWibo);
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return "view";
	}

	public String update(){
		
		Real_nameGroupWibo real_nameGroupWibo=convertVOToBO(real_nameGroupWibovo);
		real_nameGroupWibo.setId(real_nameGroupWibovo.getId());
		if(real_nameGroupWibo!=null)
		real_nameGroupWiboService.updateReal_nameGroupWibo(real_nameGroupWibo);
		return SUCCESS;
	}
	public void deleteData(){
		String id=this.servletRequest.getParameter("id");
		Real_nameGroupWibo ro=real_nameGroupWiboService.findReal_nameGroupWibo(id);
		if(ro!=null){
			ro.setRemoved("1");
			real_nameGroupWiboService.updateReal_nameGroupWibo(ro);
		}
		
	}
}
