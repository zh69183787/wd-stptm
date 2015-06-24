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

package com.wonders.stpt.supplier.action;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wonders.stpt.supplier.service.SupplierService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
public class SupplierAction extends BaseAjaxAction {
	private Supplier supplier = new Supplier();
	private SupplierService supplierService;
	private final int size = 20;		//每页显示数据的条数

	
	@Override
	public Object getModel() {
		return supplier;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
	 * 显示list页面,显示列表
	 */
	public String showList() {
		
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.supplier);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.supplier, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		Page page = supplierService.findSupplierByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		return "showList";
	}


	/**
	 * 显示add页面,添加新增
	 */
	public String showAdd(){
		
		return "showAdd";
	}
	
	/**
	 * 保存新增
	 */
	public String saveAdd(){
		supplier.setRemoved("0");
		supplierService.saveSupplier(supplier);
		return "saveAdd";
	}
	
	
	/**
	 * 查看view页面，查看详细
	 */
	public String showView(){
		String id = servletRequest.getParameter("id");
		supplier = supplierService.findSupplierById(Long.valueOf(id));
		return "showView";
	}
	
	/**
	 * 删除(逻辑删除),将removed置"1"
	 */
	public String delete(){
		String id = servletRequest.getParameter("id");
		supplier = supplierService.findSupplierById(Long.valueOf(id));
		supplier.setRemoved("1");
		supplierService.update(supplier);
		return AJAX;
	}
	
	/**
	 * 显示edit页面，修改
	 */
	public String showEdit(){
		String id = servletRequest.getParameter("id");
		supplier = supplierService.findSupplierById(Long.valueOf(id));
		return "showEdit";
	}
	
	
	/**
	 * 保存修改
	 */
	public String saveEdit(){
		supplier.setRemoved("0");
		supplierService.update(supplier);
		return "saveEdit";
	}
	
	
	/**
	 * 根据类型查询供应商 
	 * 
	 */
	public String showSupplier(){
		String type = servletRequest.getParameter("type");
		
		List<Supplier> list = supplierService.findSupplierByType(type);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * 根据名称和类型查询 
	 */
	public String findSupplierByNameAndType(){
		String name = ServletActionContext.getRequest().getParameter("name");
		String type = ServletActionContext.getRequest().getParameter("type");
		
		
		List<Supplier> list = supplierService.findSupplierByNameAndType(name, type);
		
		String jsonData = VOUtils.getJsonDataFromCollection(list);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	
	
	
	

}
