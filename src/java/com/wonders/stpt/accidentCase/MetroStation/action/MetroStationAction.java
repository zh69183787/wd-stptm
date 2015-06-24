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

package com.wonders.stpt.accidentCase.MetroStation.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.accidentCase.MetroStation.entity.bo.MetroStation;
import com.wonders.stpt.accidentCase.MetroStation.entity.vo.MetroStationVO;
import com.wonders.stpt.accidentCase.MetroStation.service.MetroStationService;
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

public class MetroStationAction extends BaseAjaxAction {
	private MetroStation metroStation = new MetroStation();
	private MetroStationVO metroStationVO = new MetroStationVO();
	private MetroStationService metroStationService;
	
	private List<MetroStation> metroStationList;
	
	

	@Override
	public ValueObject getValueObject() {
		return this.metroStationVO;
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

	public String findMetroStationById() {
		String id = super.getServletRequest().getParameter("stationId");

		MetroStation bo = metroStationService.findMetroStationById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findMetroStationByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.metroStationVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.metroStationVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = metroStationService.findMetroStationByPage(filter, start
				/ size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page, MetroStation.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteMetroStation() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			MetroStation bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (MetroStation) JSONObject
						.toBean(obj, MetroStation.class);
				if (bean != null) {
					metroStationService.deleteMetroStation(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addMetroStation() {
		MetroStation metroStation = convertVOToBO(metroStationVO);
		if (metroStation != null) {
			metroStationService.addMetroStation(metroStation);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateMetroStation() {
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
					MetroStationVO bean = (MetroStationVO) JSONObject.toBean(
							obj, MetroStationVO.class);
					metroStationService.addMetroStation(this
							.convertVOToBO(bean));
				} else {
					metroStationService
							.updateMetroStation((MetroStation) JSONObject
									.toBean(obj, MetroStation.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private MetroStation convertVOToBO(MetroStationVO metroStationVO) {
		MetroStation metroStation = new MetroStation();
		BeanUtils.copyProperties(metroStationVO, metroStation,
				new String[] { "stationId" });
		return metroStation;
	}

	private MetroStationVO convertBOToVO(MetroStation metroStation) {
		MetroStationVO metroStationVO = new MetroStationVO();
		BeanUtils.copyProperties(metroStation, metroStationVO);
		return metroStationVO;
	}

	public MetroStationVO getMetroStationVO() {
		return metroStationVO;
	}

	public void setMetroStationVO(MetroStationVO metroStationVO) {
		this.metroStationVO = metroStationVO;
	}

	public void setMetroStationService(MetroStationService metroStationService) {
		this.metroStationService = metroStationService;
	}
	
	/**
	 * @author yaochenglong
	 * @describe 根据地铁线路查询地铁站台
	 * @return String类型
	 */
	public String findStationsByMetroLine(){

		String line = this.servletRequest.getParameter("line");
		metroStationList = metroStationService.findStationsByMetroLine(line);
		String jsonData = VOUtils.getJsonDataFromCollection(metroStationList);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	
	
	
	
	
	
	
	
	
	
}
