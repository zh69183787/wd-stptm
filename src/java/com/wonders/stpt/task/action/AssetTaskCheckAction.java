
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
 
package com.wonders.stpt.task.action;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wonders.stpt.task.service.AssetTaskCheckService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2012-7-4
 * @author modify by $Author$
 * @since 1.0
 */
 
public class AssetTaskCheckAction extends BaseAjaxAction { 
	private AssetTaskCheck assetTaskCheck = new AssetTaskCheck();
	private AssetTaskCheckService assetTaskCheckService;
	

	
	public void setAssetTaskCheckService(AssetTaskCheckService assetTaskCheckService) {
		this.assetTaskCheckService = assetTaskCheckService;
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


	public String findAssetTaskCheckByPage(){
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
		JSONObject obj = JSONObject.fromObject(this.assetTaskCheck);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.assetTaskCheck, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = assetTaskCheckService.findAssetTaskCheckByPage(filter, start/size+1, size);
		String json = VOUtils.getJsonDataFromPage(page, AssetTaskCheck.class);
		createJSonData(json);
		return AJAX;
	}
	
	
}
	
