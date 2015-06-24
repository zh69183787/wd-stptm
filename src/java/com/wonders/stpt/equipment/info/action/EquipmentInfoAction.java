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

package com.wonders.stpt.equipment.info.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wonders.stpt.equipment.info.service.EquipmentInfoService;
import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;
import com.wonders.stpt.equipment.paramters.service.EquipmentParametersService;
import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wonders.stpt.supplier.service.SupplierService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentInfoAction extends BaseAjaxAction {
	private static final long serialVersionUID = 1123018703362662597L;
	private EquipmentInfo equipmentInfo = new EquipmentInfo();
	private EquipmentInfoService equipmentInfoService;
	private EquipmentParametersService equipmentParametersService;
	private AssetInfoService assetInfoService;
	private static Properties properties = new Properties();
	private SupplierService supplierService ;
	
	
	private final static int size = 20;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	
	@Override
	public Object getModel() {
		return equipmentInfo;
	}
	

	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public EquipmentInfo getEquipmentInfo() {
		return equipmentInfo;
	}

	public void setEquipmentInfo(EquipmentInfo equipmentInfo) {
		this.equipmentInfo = equipmentInfo;
	}

	public void setAssetInfoService(AssetInfoService assetInfoService) {
		this.assetInfoService = assetInfoService;
	}

	public void setEquipmentInfoService(
			EquipmentInfoService equipmentInfoService) {
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
		JSONObject obj = JSONObject.fromObject(this.equipmentInfo);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this
						.getValueByParamName(this.equipmentInfo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(filter.get("manufacturer")!=null){
			
		}
		
		ServletActionContext.getRequest().setAttribute("manufacturer", filter.get("manufacturer"));
		
		Page page = equipmentInfoService.findEquipmentInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		if(page!=null && page.getResult()!=null && page.getResult().size()>0){
			List<String> supplierNameList = new ArrayList<String>();
			Supplier supplier;
			for(int i=0; i<page.getResult().size(); i++){
				EquipmentInfo tempEquipment = (EquipmentInfo) page.getResult().get(i);
				try {
					supplier = supplierService.findSupplierById(Long.valueOf(tempEquipment.getManufacturer()));
					if(supplier!=null){
						supplierNameList.add(supplier.getName());
					}else{
						supplierNameList.add(null);
					}
				} catch (NumberFormatException e) {
					supplierNameList.add(null);
					System.err.println("转型失败");
				}
			}
			ServletActionContext.getRequest().setAttribute("supplierNameList", supplierNameList);
		}
		
		
		
		
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
		equipmentInfo = equipmentInfoService.findEquipmentById(id);
		List<EquipmentParameters> paramterList = equipmentParametersService.findParametersByEquipmentId(id);
		servletRequest.setAttribute("paramterList", paramterList);
		
		Supplier supplier = supplierService.findSupplierById(Long.valueOf(equipmentInfo.getManufacturer()));
		ServletActionContext.getRequest().setAttribute("supplier", supplier);
		
		return "showView";
	}
	
	/**
	 * 显示edit页面
	 */
	public String showEdit(){
		String id = servletRequest.getParameter("id");
		equipmentInfo = equipmentInfoService.findEquipmentById(id);
		List<EquipmentParameters> paramterList = equipmentParametersService.findParametersByEquipmentId(id);
		servletRequest.setAttribute("paramterList", paramterList);
		
		try {
			Supplier supplier = supplierService.findSupplierById(Long.valueOf(equipmentInfo.getManufacturer()));
			ServletActionContext.getRequest().setAttribute("supplier", supplier);
		} catch (NumberFormatException e) {
			System.err.println("转型失败");
		}
		
		
		return "showEdit";
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){
		
		
		AssetInfo assetInfo = assetInfoService.findAssetInfoByAssetId(equipmentInfo.getAssetId());
		if(assetInfo==null){
			String manufacturerName = ServletActionContext.getRequest().getParameter("manufacturerName");
			ServletActionContext.getRequest().setAttribute("manufacturerName", manufacturerName);
			ServletActionContext.getRequest().setAttribute("errorInfo", "该资产编号不存在，请检查资产编号是否正确！");
			return "fail";
		}
		
		
		equipmentInfo.setOperateTime(sdf.format(new Date()));
		equipmentInfoService.saveEquipmentInfo(equipmentInfo);
		
		String[] paramNameList = servletRequest.getParameterValues("paramName");
		String[] paramValueList = servletRequest.getParameterValues("paramValue");
		
		List<EquipmentParameters> parameterList = new ArrayList<EquipmentParameters>();
		if(paramNameList!=null && paramValueList!=null && paramNameList.length == paramValueList.length){
			EquipmentParameters equipmentParameters ;
			for(int i=0; i<paramNameList.length; i++){
				equipmentParameters = new EquipmentParameters();
				equipmentParameters.setEquipmentId(equipmentInfo.getId());
				equipmentParameters.setParameterName(paramNameList[i]);
				equipmentParameters.setParameterValue(paramValueList[i]);
				equipmentParameters.setRemoved("0");
				equipmentParameters.setOperationDate(sdf.format(new Date()));
				parameterList.add(equipmentParameters);
			}
		}
		equipmentParametersService.saveEquipmentParameters(parameterList);
		
		return "saveAdd";
	}

	/**
	 * 保存修改
	 */
	public String saveEdit(){
		
		
		AssetInfo assetInfo = assetInfoService.findAssetInfoByAssetId(equipmentInfo.getAssetId());
		if(assetInfo==null){
			String manufacturerName = ServletActionContext.getRequest().getParameter("manufacturerName");
			ServletActionContext.getRequest().setAttribute("manufacturerName", manufacturerName);
			ServletActionContext.getRequest().setAttribute("errorInfo", "该资产编号不存在，请检查资产编号是否正确！");
			return "fail";
		}
		
		
		equipmentInfo.setOperateTime(sdf.format(new Date()));
		equipmentInfoService.updateEquipment(equipmentInfo);
		
		String[] paramNameList = servletRequest.getParameterValues("paramName");
		String[] paramValueList = servletRequest.getParameterValues("paramValue");
		
		List<EquipmentParameters> parameterList = new ArrayList<EquipmentParameters>();
		if(paramNameList!=null && paramValueList!=null && paramNameList.length == paramValueList.length){
			EquipmentParameters equipmentParameters ;
			for(int i=0; i<paramNameList.length; i++){
				equipmentParameters = new EquipmentParameters();
				equipmentParameters.setEquipmentId(equipmentInfo.getId());
				equipmentParameters.setParameterName(paramNameList[i]);
				equipmentParameters.setParameterValue(paramValueList[i]);
				equipmentParameters.setRemoved("0");
				equipmentParameters.setOperationDate(sdf.format(new Date()));
				parameterList.add(equipmentParameters);
			}
		}
		equipmentParametersService.saveEquipmentParameters(parameterList);
		
		return "saveEdit";
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		String id = servletRequest.getParameter("id");
		equipmentInfoService.deleteById(id);
		return AJAX;
	}
	
	
	//初始化配置文件
	public void initProperties(){
		String path = Thread.currentThread().getContextClassLoader().getResource("assetInfo.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//填写资产分页查询的过滤条件
	public Map<String, Object> fillFilter(String key,Object value,Map<String, Object> filter){
		if(value!=null && !"".equals(value.toString().trim())){
			filter.put(key, value);
		}
		return filter;
	}
	
	//将资产搜索条件组合成一个字符串
	public StringBuilder combineSearchCondition(String key,Object value,StringBuilder sb){
		if(value!=null && !"".equals(value.toString().trim())){
			sb.append(key).append("：").append(value.toString().trim()).append("；");
		}
		return sb;
	}
}
