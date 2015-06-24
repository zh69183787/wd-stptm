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

package com.wonders.stpt.user.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.user.entity.bo.User;
import com.wonders.stpt.user.service.UserService;
import com.wonders.stpt.user.entity.vo.UserVO;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

public class UserAction extends BaseAjaxAction {
	private User user = new User();
	private UserVO userVO = new UserVO();
	private UserService userService;

	@Override
	public ValueObject getValueObject() {
		return this.userVO;
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

	public String findUserById() {
		String id = super.getServletRequest().getParameter("id");

		User bo = userService.findUserById(Long.parseLong(id));

		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findUserByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.userVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.userVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = userService.findUserByPage(filter, start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page, User.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteUser() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			User bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (User) JSONObject.toBean(obj, User.class);
				if (bean != null) {
					userService.deleteUser(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addUser() {
		User user = convertVOToBO(userVO);
		if (user != null) {
			userService.addUser(user);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateUser() {
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
					UserVO bean = (UserVO) JSONObject.toBean(obj, UserVO.class);
					userService.addUser(this.convertVOToBO(bean));
				} else {
					userService.updateUser((User) JSONObject.toBean(obj,
							User.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private User convertVOToBO(UserVO userVO) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user, new String[] { "id" });
		return user;
	}

	private UserVO convertBOToVO(User user) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(user, userVO);
		return userVO;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
