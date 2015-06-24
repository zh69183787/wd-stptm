/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.equipment.replace.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.wonders.stpt.equipment.replace.entity.bo.EquipmentPartsReplace;
import com.wonders.stpt.equipment.replace.service.EquipmentPartsReplaceService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-21
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentPartsReplaceAction extends BaseAjaxAction {
	private EquipmentPartsReplace equipmentPartsReplace = new EquipmentPartsReplace();
	private EquipmentPartsReplaceService equipmentPartsReplaceService;
	private final static int size = 10;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	
	@Override
	public Object getModel() {
		return equipmentPartsReplace;
	}

	public EquipmentPartsReplace getEquipmentPartsReplace() {
		return equipmentPartsReplace;
	}

	public void setEquipmentPartsReplace(EquipmentPartsReplace equipmentPartsReplace) {
		this.equipmentPartsReplace = equipmentPartsReplace;
	}

	public void setEquipmentPartsReplaceService(
			EquipmentPartsReplaceService equipmentPartsReplaceService) {
		this.equipmentPartsReplaceService = equipmentPartsReplaceService;
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

	/**
	 * 显示list页面
	 * @return
	 */
	public String showList() {

		String pageNo = servletRequest.getParameter("pageNo");
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "0";
		}
		
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.equipmentPartsReplace);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.equipmentPartsReplace, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = equipmentPartsReplaceService.findEquipmentPartsReplaceByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		return "showList";
	}

	/**
	 * 显示add页面
	 */
	public String showAdd(){
		return "showAdd";
	}
	
	/**
	 * 显示view页面
	 */
	public String showView(){
		String id = servletRequest.getParameter("id");
		equipmentPartsReplace = equipmentPartsReplaceService.findPartsReplaceById(id);
		return "showView";
	}
	
	/**
	 * 显示edit页面
	 */
	public String showEdit(){
		String id = servletRequest.getParameter("id");
		equipmentPartsReplace = equipmentPartsReplaceService.findPartsReplaceById(id);
		return "showEdit";
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){
		equipmentPartsReplace.setRemoved("0");
		equipmentPartsReplace.setOperateTime(sdf.format(new Date()));
		equipmentPartsReplaceService.savePartsReplace(equipmentPartsReplace);
		return "saveAdd";
	}
	
	/**
	 * 保存修改
	 */
	public String saveEdit(){
		equipmentPartsReplace.setRemoved("0");
		equipmentPartsReplace.setOperateTime(sdf.format(new Date()));
		equipmentPartsReplaceService.updatePartsReplace(equipmentPartsReplace);
		return "saveEdit";
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		String id  = servletRequest.getParameter("id");
		equipmentPartsReplaceService.deletePartsReplaceById(id);
		return AJAX;
	}
	
	
}
