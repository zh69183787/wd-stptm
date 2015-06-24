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

package com.wonders.stpt.sthr.CsUser.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import com.wonders.stpt.sthr.CsUser.entity.bo.CsUser;
import com.wonders.stpt.sthr.CsUser.entity.vo.CsUserVO;
import com.wonders.stpt.sthr.CsUser.service.CsUserService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-3-14
 * @author modify by $Author$
 * @since 1.0
 */

public class CsUserAction extends BaseAjaxAction {
	private CsUser csUser = new CsUser();
	private CsUserVO csUserVO = new CsUserVO();
	private CsUserService csUserService;

	@Override
	public ValueObject getValueObject() {
		return this.csUserVO;
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

	public String findCsUserById() {
		String id = super.getServletRequest().getParameter("id");

		CsUser bo = csUserService.findCsUserById(Long.parseLong(id));

		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findCsUserByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.csUserVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.csUserVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = csUserService.findCsUserByPage(filter, start / size + 1,
				size);
		String json = VOUtils.getJsonDataFromPage(page, CsUser.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteCsUser() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			CsUser bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (CsUser) JSONObject.toBean(obj, CsUser.class);
				if (bean != null) {
					csUserService.deleteCsUser(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addCsUser() {
		CsUser csUser = convertVOToBO(csUserVO);
		if (csUser != null) {
			csUserService.addCsUser(csUser);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateCsUser() {
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
					CsUserVO bean = (CsUserVO) JSONObject.toBean(obj,
							CsUserVO.class);
					csUserService.addCsUser(this.convertVOToBO(bean));
				} else {
					csUserService.updateCsUser((CsUser) JSONObject.toBean(obj,
							CsUser.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private CsUser convertVOToBO(CsUserVO csUserVO) {
		CsUser csUser = new CsUser();
		BeanUtils.copyProperties(csUserVO, csUser, new String[] { "id" });
		return csUser;
	}

	private CsUserVO convertBOToVO(CsUser csUser) {
		CsUserVO csUserVO = new CsUserVO();
		BeanUtils.copyProperties(csUser, csUserVO);
		return csUserVO;
	}

	public CsUserVO getCsUserVO() {
		return csUserVO;
	}

	public void setCsUserVO(CsUserVO csUserVO) {
		this.csUserVO = csUserVO;
	}

	public void setCsUserService(CsUserService csUserService) {
		this.csUserService = csUserService;
	}
	
	/**
	 * @describe 工号及姓名校验
	 * @author sunjiawei
	 */
	public String checkJobNumber(){
		String ifSuccess = "";
		String jobNumber = this.servletRequest.getParameter("jobNumber");
		String name = this.servletRequest.getParameter("name");
		List<Object[]> list = csUserService.checkJobNumber(jobNumber);
		if(list!=null)  {
			if(list.size()==0){
				ifSuccess="{\"info\":\"wrongJobNumber\"}";
			}
			else{
				if(String.valueOf(list.get(0)).equals(name)){
					ifSuccess ="{\"info\":\"success\"}";
				}else{
					ifSuccess = "{\"info\":\""+list.get(0)+"\"}";
				}
			}
		}
		
		createJSonData(ifSuccess);
		return AJAX;
	}
	
	
}
