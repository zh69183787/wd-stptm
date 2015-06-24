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

package com.wonders.stpt.sthr.HrExtInfo.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.sthr.HrExtInfo.entity.bo.HrExtInfo;
import com.wonders.stpt.sthr.HrExtInfo.entity.vo.HrExtInfoVO;
import com.wonders.stpt.sthr.HrExtInfo.service.HrExtInfoService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-7
 * @author modify by $Author$
 * @since 1.0
 */

public class HrExtInfoAction extends BaseAjaxAction {
	private HrExtInfo hrExtInfo = new HrExtInfo();
	private HrExtInfoVO hrExtInfoVO = new HrExtInfoVO();
	private HrExtInfoService hrExtInfoService;

	@Override
	public ValueObject getValueObject() {
		return this.hrExtInfoVO;
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

	public String findHrExtInfoById() {
		String id = super.getServletRequest().getParameter("id");

		HrExtInfo bo = hrExtInfoService.findHrExtInfoById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findHrExtInfoByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.hrExtInfoVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.hrExtInfoVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = hrExtInfoService.findHrExtInfoByPage(filter, start / size
				+ 1, size);
		String json = VOUtils.getJsonDataFromPage(page, HrExtInfo.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteHrExtInfo() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			HrExtInfo bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (HrExtInfo) JSONObject.toBean(obj, HrExtInfo.class);
				if (bean != null) {
					hrExtInfoService.deleteHrExtInfo(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addHrExtInfo() {		
		HrExtInfo hrExtInfo = convertVOToBO(hrExtInfoVO);
		Date date = new Date();
		hrExtInfo.setUpdateTime(date);
//		double d1 = hrExtInfo.getNExt1();
//		Double double1 = null;
//		hrExtInfo.setNExt1(double1);
		
		//d1=
			//long d=null;
		if (hrExtInfo != null) {
			hrExtInfoService.addHrExtInfo(hrExtInfo);
		}
		createJSonData("{\"success\":true}");
		return SUCCESS;
	}

	public String updateHrExtInfo() {
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
					HrExtInfoVO bean = (HrExtInfoVO) JSONObject.toBean(obj,
							HrExtInfoVO.class);
					hrExtInfoService.addHrExtInfo(this.convertVOToBO(bean));
				} else {
					hrExtInfoService.updateHrExtInfo((HrExtInfo) JSONObject
							.toBean(obj, HrExtInfo.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private HrExtInfo convertVOToBO(HrExtInfoVO hrExtInfoVO) {
		HrExtInfo hrExtInfo = new HrExtInfo();
		BeanUtils.copyProperties(hrExtInfoVO, hrExtInfo, new String[] { "id" });
		return hrExtInfo;
	}

	private HrExtInfoVO convertBOToVO(HrExtInfo hrExtInfo) {
		HrExtInfoVO hrExtInfoVO = new HrExtInfoVO();
		BeanUtils.copyProperties(hrExtInfo, hrExtInfoVO);
		return hrExtInfoVO;
	}

	public HrExtInfoVO getHrExtInfoVO() {
		return hrExtInfoVO;
	}

	public void setHrExtInfoVO(HrExtInfoVO hrExtInfoVO) {
		this.hrExtInfoVO = hrExtInfoVO;
	}

	public void setHrExtInfoService(HrExtInfoService hrExtInfoService) {
		this.hrExtInfoService = hrExtInfoService;
	}
	
	
	public String findHrInfoByType(){
		List<Object[]> hretdList = null;
		List<Object[]> hrExtInfoList = null;
		List<String> paramList = new ArrayList<String>();
		
		String id = super.getServletRequest().getParameter("hrId");
		String hretId = this.servletRequest.getParameter("hretId");
		String tempPageNo = this.getServletRequest().getParameter("pageNo");
		int pageNo = 1;
		if(tempPageNo!=null && !tempPageNo.equals("")){
			pageNo = Integer.valueOf(tempPageNo);
		}
		int pageSize = 10;	//每页的数量
		int pageCount = 0;	//总页数
		int count = 0;	//总条数
		
		//根据类别查询所有数据项
		if(hretId!=null && !hretId.equals("")){
			hretdList = hrExtInfoService.findAllHrEtDByType(hretId);
		}
		String jsonData1 = VOUtils.getJsonDataFromCollection(hretdList);
		//根据HrId,hretId,hretdId查询所有的个人扩展信息
		if(id!=null && !id.equals("") && hretdList!=null && hretdList.size()!=0){
			for(int i=0;i<hretdList.size();i++){
				paramList.add(hretdList.get(i)[1].toString());
			}
			hrExtInfoList = hrExtInfoService.findAllHrExtInfoByParam(id, hretId,paramList);
			
			List<String[]> dataList = new ArrayList<String[]>();
			String[] tempString1 = null;
			
			for(int i=0;i<hretdList.size();i++){
				tempString1 = new String[hretdList.get(i).length];
				for(int j=0;j<hretdList.get(i).length;j++){
					tempString1[j] = hretdList.get(i)[j].toString();
				}
				dataList.add(tempString1);
			}
			
			if(hrExtInfoList!=null && hrExtInfoList.size()>0){
				count = hrExtInfoList.size();
				if(count%pageSize == 0){
					pageCount = count/pageSize;
				}else{
					pageCount = count/pageSize+1;
				}
			}
			
			
			if(hrExtInfoList.size()>pageSize){
				List<Object[]> temp = hrExtInfoList;
				hrExtInfoList = new ArrayList<Object[]>();
				
				if(pageNo==1){
					for(int i=0; i<pageSize; i++){
						hrExtInfoList.add(temp.get(i));
					}
				}else{
					if(pageNo==pageCount){
						for(int i=(pageNo-1)*pageSize; i<temp.size(); i++){
							hrExtInfoList.add(temp.get(i));
						}
					}else{
						for(int i=(pageNo-1)*pageSize; i<pageSize*pageNo; i++){
							hrExtInfoList.add(temp.get(i));
						}
					}
					
				}
			}
			
			String[] tempString2 = null;
			for(int i=0;i<hrExtInfoList.size();i++){
				tempString2 = new String[hrExtInfoList.get(i).length];
				for(int j=0;j<hrExtInfoList.get(i).length;j++){
					tempString2[j] = String.valueOf(hrExtInfoList.get(i)[j]);
				}
				dataList.add(tempString2);
			}
			String[] pageData = new String[5];
			pageData[0] = String.valueOf(pageNo);	//当前页数
			pageData[1] = String.valueOf(count);	//总条数
			pageData[2] = String.valueOf(pageCount);	//总页数
			//显示条数开始
			if(pageNo==1){
				pageData[3] = "1";
			}else{
				pageData[3] = String.valueOf(((pageNo-1)*pageSize+1));
			}
			
			//显示条数结束************************************
			if(pageNo==pageCount ){
				pageData[4] = String.valueOf(count);
			}else{
				pageData[4] = String.valueOf((pageNo)*pageSize);
			}
			dataList.add(pageData);
			String jsonData =  VOUtils.getJsonDataFromCollection(dataList);
			createJSonData(jsonData);
		}
				
		return AJAX;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 根据主键修改
	 * @return String	 
	 */
	public String updateHrExtInfoById(){		
		HrExtInfo hrExtInfo = convertVOToBO(hrExtInfoVO);
		hrExtInfo.setId(hrExtInfoVO.getId());	
		Date date = new Date();
		hrExtInfo.setUpdateTime(date);
		if (hrExtInfo != null) {
			hrExtInfoService.updateHrExtInfo(hrExtInfo);			
		}
		return SUCCESS;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 根据主键删除
	 * @return String	 
	 */
	public String deleteById(){
		String id = this.servletRequest.getParameter("id");			
	    String type = this.servletRequest.getParameter("type");		
		if (id != null) {
			hrExtInfoService.deleteById(id);
			hrExtInfoVO.setId(null);
			
		}
		if("view".equals(type)) return "view";
		else if("edit".equals(type)) return AJAX;
		return SUCCESS;
		
	}
}
