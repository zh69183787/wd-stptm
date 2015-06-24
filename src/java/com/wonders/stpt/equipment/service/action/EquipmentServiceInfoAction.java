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

package com.wonders.stpt.equipment.service.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wonders.stpt.equipment.info.service.EquipmentInfoService;
import com.wonders.stpt.equipment.service.entity.bo.EquipmentServiceInfo;
import com.wonders.stpt.equipment.service.service.EquipmentServiceInfoService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-25
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentServiceInfoAction extends BaseAjaxAction {
	private EquipmentServiceInfo equipmentServiceInfo = new EquipmentServiceInfo();
	private EquipmentServiceInfoService equipmentServiceInfoService;
	private EquipmentInfoService equipmentInfoService;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final int size = 10;
	

	@Override
	public Object getModel() {
		return equipmentServiceInfo;
	}

	public EquipmentServiceInfo getEquipmentServiceInfo() {
		return equipmentServiceInfo;
	}

	public void setEquipmentServiceInfo(EquipmentServiceInfo equipmentServiceInfo) {
		this.equipmentServiceInfo = equipmentServiceInfo;
	}

	public void setEquipmentInfoService(EquipmentInfoService equipmentInfoService) {
		this.equipmentInfoService = equipmentInfoService;
	}

	public void setEquipmentServiceInfoService(EquipmentServiceInfoService equipmentServiceInfoService) {
		this.equipmentServiceInfoService = equipmentServiceInfoService;
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
	 * 显示设备基础信息list页面
	 */
	public String showEquipmentList(){
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "1";
		}
		EquipmentInfo equipmentInfo = new EquipmentInfo();
		String assetId = servletRequest.getParameter("assetId");
		String equipmentId = servletRequest.getParameter("equipmentId");
		String equipmentName = servletRequest.getParameter("equipmentName");
		String manufacturer = servletRequest.getParameter("manufacturer");
		String provenance = servletRequest.getParameter("provenance");
		String productModel = servletRequest.getParameter("productModel");
		
		
		Map<String, Object> filter = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(assetId)){
			filter.put("assetId", assetId);
			equipmentInfo.setAssetId(assetId);
		}
		if(StringUtils.isNotEmpty(equipmentId)){
			filter.put("equipmentId", equipmentId);
			equipmentInfo.setEquipmentId(equipmentId);
		}
		if(StringUtils.isNotEmpty(equipmentName)){
			filter.put("equipmentName", equipmentName);
			equipmentInfo.setEquipmentName(equipmentName);
		}
		if(StringUtils.isNotEmpty(manufacturer)){
			filter.put("manufacturer", manufacturer);
			equipmentInfo.setManufacturer(manufacturer);
		}
		if(StringUtils.isNotEmpty(provenance)){
			filter.put("provenance", provenance);
			equipmentInfo.setProvenance(provenance);
		}
		if(StringUtils.isNotEmpty(productModel)){
			filter.put("productModel", productModel);
			equipmentInfo.setProductModel(productModel);
		}
		
		/*
		String key = null;
		String value = null;
		
		JSONObject obj = JSONObject.fromObject(this.equipmentServiceInfo);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.equipmentServiceInfo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		*/

		Page page = equipmentInfoService.findEquipmentInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		servletRequest.setAttribute("equipmentInfo", equipmentInfo);
		
		return "showEquipmentList";
	}

	/**
	 * 显示list页面
	 */
	public String showList() {
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.equipmentServiceInfo);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.equipmentServiceInfo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = equipmentServiceInfoService.findEquipmentServiceInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		
		List<EquipmentInfo> equipmentInfoList = new ArrayList<EquipmentInfo>();
		List<String> idList = null;
		if(page!=null && page.getResult().size()>0){
			EquipmentInfo eif ;
			idList = new ArrayList<String>();
			List<EquipmentServiceInfo> resultList = page.getResult();
			for(int i=0; i<resultList.size(); i++){
				eif = equipmentInfoService.findEquipmentById(resultList.get(i).getEquipmentId());
				equipmentInfoList.add(eif);
			}
		}
		servletRequest.setAttribute("equipmentInfoList", equipmentInfoList);
		
		return "showList";
	}

	/**
	 * 显示add页面
	 */
	public String showAdd(){
		String equipmentId = servletRequest.getParameter("id");
		servletRequest.setAttribute("equipmentId", equipmentId);
		
		return "showAdd";
	}
	
	/**
	 * 显示edit页面
	 */
	public String showEdit(){
		String id = servletRequest.getParameter("id");
		equipmentServiceInfo = equipmentServiceInfoService.findServiceInfoById(id);
		
		return "showEdit";
	}
	
	/**
	 * 显示view页面
	 */
	public String showView(){
		String id = servletRequest.getParameter("id");
		equipmentServiceInfo = equipmentServiceInfoService.findServiceInfoById(id);
		
		return "showView";
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){
		equipmentServiceInfo.setRemoved("0");
		equipmentServiceInfo.setOperationDate(sdf.format(new Date()));
		equipmentServiceInfoService.saveServiceInfo(equipmentServiceInfo);
		return "saveAdd";
	}
	
	/**
	 * 保存编辑
	 */
	public String saveEdit(){
		equipmentServiceInfo.setRemoved("0");
		equipmentServiceInfo.setOperationDate(sdf.format(new Date()));
		equipmentServiceInfoService.updateServiceInfo(equipmentServiceInfo);
		
		return "saveEdit";
	}
	
	
	/**
	 * 删除
	 */
	public String delete(){
		String id = servletRequest.getParameter("id");
		equipmentServiceInfoService.deleteById(id);
		return AJAX;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
