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

package com.wonders.stpt.accidentCase.MetroLine.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.accidentCase.MetroLine.entity.bo.MetroLine;
import com.wonders.stpt.accidentCase.MetroLine.entity.vo.MetroLineVO;
import com.wonders.stpt.accidentCase.MetroLine.service.MetroLineService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroLineAction extends BaseAjaxAction {
	private MetroLine metroLine = new MetroLine();
	private MetroLineVO metroLineVO = new MetroLineVO();
	private MetroLineService metroLineService;
	
	//地铁线路集合
	private List<MetroLine> metroLineList;

	public List<MetroLine> getMetroLineList() {
		return metroLineList;
	}
	

	@Override
	public ValueObject getValueObject() {
		return this.metroLineVO;
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

	public String findMetroLineById() {
		String id = super.getServletRequest().getParameter("lineId");

		MetroLine bo = metroLineService.findMetroLineById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findMetroLineByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.metroLineVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.metroLineVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = metroLineService.findMetroLineByPage(filter, start / size
				+ 1, size);
		String json = VOUtils.getJsonDataFromPage(page, MetroLine.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteMetroLine() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			MetroLine bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (MetroLine) JSONObject.toBean(obj, MetroLine.class);
				if (bean != null) {
					metroLineService.deleteMetroLine(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addMetroLine() {
		MetroLine metroLine = convertVOToBO(metroLineVO);
		if (metroLine != null) {
			metroLineService.addMetroLine(metroLine);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateMetroLine() {
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
					MetroLineVO bean = (MetroLineVO) JSONObject.toBean(obj,
							MetroLineVO.class);
					metroLineService.addMetroLine(this.convertVOToBO(bean));
				} else {
					metroLineService.updateMetroLine((MetroLine) JSONObject
							.toBean(obj, MetroLine.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private MetroLine convertVOToBO(MetroLineVO metroLineVO) {
		MetroLine metroLine = new MetroLine();
		BeanUtils.copyProperties(metroLineVO, metroLine,
				new String[] { "lineId" });
		return metroLine;
	}

	private MetroLineVO convertBOToVO(MetroLine metroLine) {
		MetroLineVO metroLineVO = new MetroLineVO();
		BeanUtils.copyProperties(metroLine, metroLineVO);
		return metroLineVO;
	}

	public MetroLineVO getMetroLineVO() {
		return metroLineVO;
	}

	public void setMetroLineVO(MetroLineVO metroLineVO) {
		this.metroLineVO = metroLineVO;
	}

	public void setMetroLineService(MetroLineService metroLineService) {
		this.metroLineService = metroLineService;
	}
	
	
	/**
	 * @author yaochenglong
	 * @describe 查询所有地铁线路
	 * 
	 */
	public String findAllMetroLine(){
		metroLineList = metroLineService.findAllMetroLine();
		String string = VOUtils.getJsonDataFromCollection(metroLineList);
		createJSonData(string);

		return AJAX;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
