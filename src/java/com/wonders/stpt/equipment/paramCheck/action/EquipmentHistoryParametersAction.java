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

package com.wonders.stpt.equipment.paramCheck.action;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wonders.stpt.equipment.info.service.EquipmentInfoService;
import com.wonders.stpt.equipment.paramCheck.entity.bo.EquipmentHistoryParameters;
import com.wonders.stpt.equipment.paramCheck.service.EquipmentHistoryParametersService;
import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;
import com.wonders.stpt.equipment.paramters.service.EquipmentParametersService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentHistoryParametersAction extends BaseAjaxAction {
	private EquipmentHistoryParameters equipmentHistoryParameters = new EquipmentHistoryParameters();
	private EquipmentHistoryParametersService equipmentHistoryParametersService;
	private EquipmentInfoService equipmentInfoService;
	private EquipmentParametersService equipmentParametersService; 
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private TreeMap<Long,List<EquipmentHistoryParameters>> map = new TreeMap<Long,List<EquipmentHistoryParameters>>();
	
	private final int size = 10;


	

	@Override
	public Object getModel() {
		return equipmentHistoryParameters;
	}

	public EquipmentHistoryParameters getEquipmentHistoryParameters() {
		return equipmentHistoryParameters;
	}

	public void setEquipmentHistoryParameters(
			EquipmentHistoryParameters equipmentHistoryParameters) {
		this.equipmentHistoryParameters = equipmentHistoryParameters;
	}

	public void setEquipmentHistoryParametersService(
			EquipmentHistoryParametersService equipmentHistoryParametersService) {
		this.equipmentHistoryParametersService = equipmentHistoryParametersService;
	}
	
	public void setEquipmentInfoService(EquipmentInfoService equipmentInfoService) {
		this.equipmentInfoService = equipmentInfoService;
	}
	
	public void setEquipmentParametersService(
			EquipmentParametersService equipmentParametersService) {
		this.equipmentParametersService = equipmentParametersService;
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
		

		Page page = equipmentInfoService.findEquipmentInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		servletRequest.setAttribute("equipmentInfo", equipmentInfo);
		
		return "showEquipmentList";
	}
	
	

	/**
	 *	显示list页面 
	 */
	public String showList() {
		String pageNo = servletRequest.getParameter("pageNo");
		if(pageNo==null || "".equals(pageNo)){
			pageNo = "1";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.equipmentHistoryParameters);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.equipmentHistoryParameters, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = equipmentHistoryParametersService.findEquipmentHistoryParametersByPage(filter, Integer.valueOf(pageNo),size);
		
		return "showList";
	}

	/**
	 * 显示add页面
	 */
	public String showAdd(){
		String equipmentId = servletRequest.getParameter("id"); 
		List<EquipmentParameters> paramList = equipmentParametersService.findParametersByEquipmentId(equipmentId);
		
		if(paramList!=null && paramList.size()>0){
			servletRequest.setAttribute("paramList", paramList);
		}else{
			servletRequest.setAttribute("errorInfo", "该设备尚未设置参数！");
		}
		
		
		servletRequest.setAttribute("equipmentId", equipmentId);
		
		return "showAdd";
	}
	
	/**
	 * 显示edit页面
	 */
	public String showEdit(){
		
		return "showEdit";
	}
	
	/**
	 * 显示view页面
	 */
	public String showView(){
		String id = servletRequest.getParameter("id");
		
		return "showView";
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){
		String equipmentId = servletRequest.getParameter("equipmentId");
		String checkPerson = servletRequest.getParameter("checkPerson");
		String checkDate = servletRequest.getParameter("checkDate");
		String[] paramIds = servletRequest.getParameterValues("paramId");
		String[] paramValue = servletRequest.getParameterValues("paramValue");
		
		
		
		EquipmentHistoryParameters historyParameters ;
		List<EquipmentHistoryParameters> historyParamList = new ArrayList<EquipmentHistoryParameters>();
		
		if(paramIds!=null && paramValue!=null && paramIds.length==paramValue.length){
			
			String groupId = equipmentHistoryParametersService.findGroupId();
			
			for(int i=0; i<paramIds.length; i++){
				historyParameters = new EquipmentHistoryParameters();
				historyParameters.setEquipmentId(equipmentId);
				historyParameters.setParamterId(paramIds[i]);
				historyParameters.setParamterValue(paramValue[i]);
				historyParameters.setCheckPerson(checkPerson);
				historyParameters.setCheckDate(checkDate);
				historyParameters.setOperationDate(sdf.format(new Date()));
				historyParameters.setRemoved("0");
				historyParameters.setGroupId(groupId);
				historyParamList.add(historyParameters);
			}
		}
		equipmentHistoryParametersService.saveHistoryParameters(historyParamList);
		
		
		return "saveAdd";
	}
	
	/**
	 * 保存编辑
	 */
	public String saveEdit(){
		
		return "saveEdit";
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		String id = servletRequest.getParameter("id");
		return AJAX;
	}
	
	/**
	 * 查看历史参数检查
	 */
	public String showHistoryCheck(){
		String equipmentId = servletRequest.getParameter("id");
		List<EquipmentHistoryParameters> paramHistoryList = equipmentHistoryParametersService.findHistoryParametersByEquipmentId(equipmentId);
		
		
		List<EquipmentParameters> paramsList = equipmentParametersService.findParametersByEquipmentId(equipmentId);
		long paramAmount = paramsList.size();
		
		getParametersListByGroup(paramHistoryList, paramAmount);
		
		
		servletRequest.setAttribute("map", map);
		servletRequest.setAttribute("paramsList", paramsList);
		
		
		return "showHistoryCheck";
	}
	
	
	
	public void getParametersListByGroup(List<EquipmentHistoryParameters> list,long paramAmount){
		
		int listSize = list.size();
		
		if(list!=null && list.size()>0 && paramAmount>0){
			EquipmentHistoryParameters historyParameters ;
			List<EquipmentHistoryParameters> mapValueList = new ArrayList<EquipmentHistoryParameters>();
			for(int i=0; i<listSize; i++){
				long groupId = Long.valueOf(list.get(0).getGroupId());
				
				if((i+1)%paramAmount!=0 || i==0){
					historyParameters = list.get(0);
					list.remove(0);
					mapValueList.add(historyParameters);
					if(i==listSize-1){
						map.put(groupId, mapValueList);
					}
				}else{
					historyParameters = list.get(0);
					list.remove(0);
					mapValueList.add(historyParameters);
					map.put(groupId, mapValueList);
					break;
				}
			}
			if(list.size()>0)
				getParametersListByGroup(list,paramAmount);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
