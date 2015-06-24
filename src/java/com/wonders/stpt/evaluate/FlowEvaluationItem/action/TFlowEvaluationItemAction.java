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

package com.wonders.stpt.evaluate.FlowEvaluationItem.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.vo.TFlowEvaluationItemVO;
import com.wonders.stpt.evaluate.FlowEvaluationItem.service.TFlowEvaluationItemService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationItemAction extends BaseAjaxAction {
	
	private TFlowEvaluationItem tFlowEvaluationItem = new TFlowEvaluationItem();
	private TFlowEvaluationItemVO tFlowEvaluationItemVO = new TFlowEvaluationItemVO();
	private TFlowEvaluationItemService flowEvaluationItemService;
	private Map<String, String> flowMap = new TreeMap<String, String>();

	/**
	 * @author ycl
	 * @description 初始化流程
	 */
	public TFlowEvaluationItemAction() {
		flowMap.put("001", "流程1");
		flowMap.put("002", "流程2");
		flowMap.put("003", "流程3");
		flowMap.put("004", "流程4");
		flowMap.put("005", "流程5");
	}
	
	@Override
	public ValueObject getValueObject() {
		return this.tFlowEvaluationItemVO;
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

	public String findTFlowEvaluationItemById() {
		String id = super.getServletRequest().getParameter("id");

		TFlowEvaluationItem bo = flowEvaluationItemService
				.findTFlowEvaluationItemById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findTFlowEvaluationItemByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.tFlowEvaluationItemVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.tFlowEvaluationItemVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = flowEvaluationItemService.findTFlowEvaluationItemByPage(
				filter, start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page,
				TFlowEvaluationItem.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteTFlowEvaluationItem() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			TFlowEvaluationItem bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (TFlowEvaluationItem) JSONObject.toBean(obj,
						TFlowEvaluationItem.class);
				if (bean != null) {
					flowEvaluationItemService.deleteTFlowEvaluationItem(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addTFlowEvaluationItem() {
		TFlowEvaluationItem tFlowEvaluationItem = convertVOToBO(tFlowEvaluationItemVO);
		if (tFlowEvaluationItem != null) {
			flowEvaluationItemService
					.addTFlowEvaluationItem(tFlowEvaluationItem);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateTFlowEvaluationItem() {
		String[] modifiedData = (String[]) super.getParameters().get(
				"modifiedData");
		if (modifiedData != null) {
			JSONArray modifiedArr = JSONArray.fromObject("[" + modifiedData[0]
					+ "]");
			JSONObject obj = null;
			for (int i = 0; i < modifiedArr.size(); i++) {
				obj = (JSONObject) modifiedArr.get(i);
				Object isNew = obj.get("isNew");
				if (isNew != null && ((Boolean) isNew).booleanValue()) {
					TFlowEvaluationItemVO bean = (TFlowEvaluationItemVO) JSONObject
							.toBean(obj, TFlowEvaluationItemVO.class);
					flowEvaluationItemService.addTFlowEvaluationItem(this
							.convertVOToBO(bean));
				} else {
					flowEvaluationItemService
							.updateTFlowEvaluationItem((TFlowEvaluationItem) JSONObject
									.toBean(obj, TFlowEvaluationItem.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private TFlowEvaluationItem convertVOToBO(
			TFlowEvaluationItemVO tFlowEvaluationItemVO) {
		TFlowEvaluationItem tFlowEvaluationItem = new TFlowEvaluationItem();
		BeanUtils.copyProperties(tFlowEvaluationItemVO, tFlowEvaluationItem,
				new String[] { "id" });
		return tFlowEvaluationItem;
	}

	private TFlowEvaluationItemVO convertBOToVO(
			TFlowEvaluationItem tFlowEvaluationItem) {
		TFlowEvaluationItemVO tFlowEvaluationItemVO = new TFlowEvaluationItemVO();
		BeanUtils.copyProperties(tFlowEvaluationItem, tFlowEvaluationItemVO);
		return tFlowEvaluationItemVO;
	}

	public TFlowEvaluationItemVO getTFlowEvaluationItemVO() {
		return tFlowEvaluationItemVO;
	}

	public void setTFlowEvaluationItemVO(
			TFlowEvaluationItemVO tFlowEvaluationItemVO) {
		this.tFlowEvaluationItemVO = tFlowEvaluationItemVO;
	}

	public void setFlowEvaluationItemService(
			TFlowEvaluationItemService tFlowEvaluationItemService) {
		this.flowEvaluationItemService = tFlowEvaluationItemService;
	}
	
	
	/**
	 * @author ycl
	 * @description 分页显示
	 */
	public String showEvaluationItem(){
		
		int size = 20;
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.tFlowEvaluationItemVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.tFlowEvaluationItemVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		int pageNo = 1;
		String no = this.servletRequest.getParameter("pageNo"); 
		if(no!=null && !no.equals("")){
			pageNo = Integer.valueOf(no);
		}

		Page page = flowEvaluationItemService.findTFlowEvaluationItemByPage(filter, pageNo, size);
		
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("flowMap", flowMap);
		this.servletRequest.setAttribute("flowId", tFlowEvaluationItemVO.getFlowId());
		
		return "showEvaluationItem";
	}
	
	/**
	 * @author ycl
	 * @description 跳转到添加页面
	 */
	public String showAddPage(){
		this.servletRequest.setAttribute("flowMap", flowMap);
		String flowId = this.servletRequest.getParameter("flowId");
		servletRequest.setAttribute("flowId", flowId);
		return "showAddPage";
	}
	
	
	/**
	 * @author ycl
	 * @description 添加
	 */
	public String saveEvaluationItem(){
		
		String msg = "";
		TFlowEvaluationItem flowEvaluationItem = convertVOToBO(tFlowEvaluationItemVO);
		
		//查询评价角度是否存在
		if(flowEvaluationItemService.findByName(tFlowEvaluationItemVO.getFlowId(),tFlowEvaluationItemVO.getName())){
			msg = "该评价角度已存在！";
			this.servletRequest.setAttribute("msg", msg);
			this.servletRequest.setAttribute("flowId", tFlowEvaluationItemVO.getFlowId());
			this.servletRequest.setAttribute("flowMap", flowMap);
			return "saveEvaluationItem";
		}
		flowEvaluationItem.setUpdateDate(new Date());
		flowEvaluationItem.setRemoved("1");
		flowEvaluationItemService.addTFlowEvaluationItem(flowEvaluationItem);
		
		return "saveEvaluationItem";
	}
	
	/**
	 * @author ycl
	 * @descripting 删除
	 */
	public String deleteEvaluationItem(){
		String id = this.servletRequest.getParameter("id");
		if(id!=null && !id.equals("")){
			flowEvaluationItemService.deleteEvaluationItemById(id);
		}
		return AJAX;
	}
	
	/**
	 * @author ycl
	 * @description 跳转到编辑页面
	 */
	public String showEditPage(){
		String id = this.servletRequest.getParameter("id");
		if(id!=null && !id.equals("")){
			TFlowEvaluationItem tempItem = flowEvaluationItemService.findTFlowEvaluationItemById(id);
			tFlowEvaluationItemVO = convertBOToVO(tempItem);
			this.servletRequest.setAttribute("flowName", flowMap.get(tFlowEvaluationItemVO.getFlowId()));
		}
		return "showEditPage";
	}
	
	/**
	 * @author ycl
	 * @description 保存修改
	 */
	public String updateEvaluationItem(){
		
		String id = this.servletRequest.getParameter("id");
		String msg = "";
		if(id!=null && !id.equals("")){
			TFlowEvaluationItem item = flowEvaluationItemService.findTFlowEvaluationItemById(id);
			if(item!=null){
				item.setRemoved("0");
				flowEvaluationItemService.updateTFlowEvaluationItem(item);
				//查询评价角度是否存在
				if(flowEvaluationItemService.findByName(item.getFlowId(), tFlowEvaluationItemVO.getName())){
					msg = "该评价角度已存在！";
					item.setRemoved("1");
					flowEvaluationItemService.updateTFlowEvaluationItem(item);
					this.servletRequest.setAttribute("msg", msg);
					this.servletRequest.setAttribute("flowName", servletRequest.getParameter("flowName"));
					return "updateEvaluationItem";
				}
				item.setName(tFlowEvaluationItemVO.getName());
				item.setSortingOrder(tFlowEvaluationItemVO.getSortingOrder());
				item.setUpdateDate(new Date());
				item.setRemoved("1");
				flowEvaluationItemService.updateTFlowEvaluationItem(item);
				this.servletRequest.setAttribute("msg", "保存成功");
			}
		}
		return "updateEvaluationItem";
	}
	
}
