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

package com.wonders.stpt.accidentCase.AccidentCaseApproval.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.vo.AccidentCaseApprovalVO;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.service.AccidentCaseApprovalService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-2-27
 * @author modify by $Author$
 * @since 1.0
 */

public class AccidentCaseApprovalAction extends BaseAjaxAction {
	private AccidentCaseApproval accidentCaseApproval = new AccidentCaseApproval();
	private AccidentCaseApprovalVO accidentCaseApprovalVO = new AccidentCaseApprovalVO();
	private AccidentCaseApprovalService accidentCaseApprovalService;

	@Override
	public ValueObject getValueObject() {
		return this.accidentCaseApprovalVO;
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

	public String findAccidentCaseApprovalById() {
		String id = super.getServletRequest().getParameter("approvalId");

		AccidentCaseApproval bo = accidentCaseApprovalService
				.findAccidentCaseApprovalById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	@SuppressWarnings("unchecked")
	public String findAccidentCaseApprovalByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.accidentCaseApprovalVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.accidentCaseApprovalVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = accidentCaseApprovalService.findAccidentCaseApprovalByPage(
				filter, start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page,
				AccidentCaseApproval.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteAccidentCaseApproval() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			AccidentCaseApproval bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (AccidentCaseApproval) JSONObject.toBean(obj,
						AccidentCaseApproval.class);
				if (bean != null) {
					accidentCaseApprovalService
							.deleteAccidentCaseApproval(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addAccidentCaseApproval() {			
		AccidentCaseApproval accidentCaseApproval = convertVOToBO(accidentCaseApprovalVO);
		if (accidentCaseApproval != null) {
			accidentCaseApprovalService
					.addAccidentCaseApproval(accidentCaseApproval);
		}		
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateAccidentCaseApproval() {
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
					AccidentCaseApprovalVO bean = (AccidentCaseApprovalVO) JSONObject
							.toBean(obj, AccidentCaseApprovalVO.class);
					accidentCaseApprovalService.addAccidentCaseApproval(this
							.convertVOToBO(bean));
				} else {
					accidentCaseApprovalService
							.updateAccidentCaseApproval((AccidentCaseApproval) JSONObject
									.toBean(obj, AccidentCaseApproval.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private AccidentCaseApproval convertVOToBO(
			AccidentCaseApprovalVO accidentCaseApprovalVO) {
		AccidentCaseApproval accidentCaseApproval = new AccidentCaseApproval();
		BeanUtils.copyProperties(accidentCaseApprovalVO, accidentCaseApproval,
				new String[] { "approvalId" });
		return accidentCaseApproval;
	}

	private AccidentCaseApprovalVO convertBOToVO(
			AccidentCaseApproval accidentCaseApproval) {
		AccidentCaseApprovalVO accidentCaseApprovalVO = new AccidentCaseApprovalVO();
		BeanUtils.copyProperties(accidentCaseApproval, accidentCaseApprovalVO);
		return accidentCaseApprovalVO;
	}

	public AccidentCaseApprovalVO getAccidentCaseApprovalVO() {
		return accidentCaseApprovalVO;
	}

	public void setAccidentCaseApprovalVO(
			AccidentCaseApprovalVO accidentCaseApprovalVO) {
		this.accidentCaseApprovalVO = accidentCaseApprovalVO;
	}

	public void setAccidentCaseApprovalService(
			AccidentCaseApprovalService accidentCaseApprovalService) {
		this.accidentCaseApprovalService = accidentCaseApprovalService;
	}
}
