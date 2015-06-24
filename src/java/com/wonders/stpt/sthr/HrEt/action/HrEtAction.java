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

package com.wonders.stpt.sthr.HrEt.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEt.entity.vo.HrEtVO;
import com.wonders.stpt.sthr.HrEt.service.HrEtService;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrEtAction extends BaseAjaxAction {
	private HrEt hrEt = new HrEt();
	private HrEtVO hrEtVO = new HrEtVO();
	private HrEtService hrEtService;
	private List<HrEt> hrEtList;

	public List<HrEt> getHrEtList() {
		return hrEtList;
	}

	public void setHrEtList(List<HrEt> hrEtList) {
		this.hrEtList = hrEtList;
	}

	@Override
	public ValueObject getValueObject() {
		return this.hrEtVO;
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

	public String findHrEtById() {
		String id = super.getServletRequest().getParameter("hretId");

		HrEt bo = hrEtService.findHrEtById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	/**
	 * @author ycl
	 * @modify 2012/3/6
	 * @describe 查询所有扩展类型
	 * @return
	 * @throws ParseException 
	 */
	public String findHrEtByPage() throws ParseException {
		
		String startDate = this.servletRequest.getParameter("startDate");
		String endDate = this.servletRequest.getParameter("endDate");
		Date sDate = null,eDate=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate!=null && !startDate.equals("")){
			sDate = sdf.parse(startDate);
		}
		
		if(endDate!=null && !endDate.equals("")){
			eDate = sdf.parse(endDate);
		}
	
		int start = 0;
		int size = 50;
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
		JSONObject obj = JSONObject.fromObject(this.hrEtVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.hrEtVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(sDate!=null){
			filter.put("updateTime", sDate);
			this.servletRequest.setAttribute("sDate", sDate);
		}
		if(eDate!=null){
			filter.put("eDate", eDate);
			this.servletRequest.setAttribute("eDate", eDate);
		}
		

		Page page = hrEtService.findHrEtByPage(filter, start / size + 1, size);
		this.servletRequest.setAttribute("page", page);
		return "findHrEtByPage";
	}
	
	public String deleteHrEt() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			HrEt bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (HrEt) JSONObject.toBean(obj, HrEt.class);
				if (bean != null) {
					hrEtService.deleteHrEt(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	/**
	 * @author ycl
	 * @modify 2012/3/6
	 * @describe 添加
	 * @return	String 
	 * 				addHrEt 添加成功,
	 * 				failToAdd 添加失败（typeName重复）
	 */				
	public String addHrEt() {
		HrEt hrEt = convertVOToBO(hrEtVO);
		
		if(hrEtService.findHrEtByTypeName(hrEtVO.getTypeName())){
			this.servletRequest.setAttribute("typeStatus", "类型名称已存在,请重新命名");
			this.servletRequest.setAttribute("typeName", hrEtVO.getTypeName());
			this.servletRequest.setAttribute("sortingOrder", hrEtVO.getSortingOrder());
			return "failToAdd";
		}
		
		if(hrEtService.findHrEtBySortingOrder(hrEtVO.getSortingOrder())){
			this.servletRequest.setAttribute("orderStatus","该排列顺序已存在，请重新制定排列顺序");
			this.servletRequest.setAttribute("typeName", hrEtVO.getTypeName());
			this.servletRequest.setAttribute("sortingOrder", hrEtVO.getSortingOrder());
			return "failToAdd";
		}
		
		hrEt.setRemoved("0");
		hrEt.setUpdateTime(new Date());
		hrEtService.addHrEt(hrEt);
		return "addHrEt";
	}
	
	/**
	 * @author ycl
	 * @describe 跳转到添加页面
	 * @return
	 */
	public String showAddPage(){

		return "showAddPage";
	}

	public String updateHrEt() {
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
					HrEtVO bean = (HrEtVO) JSONObject.toBean(obj, HrEtVO.class);
					hrEtService.addHrEt(this.convertVOToBO(bean));
				} else {
					hrEtService.updateHrEt((HrEt) JSONObject.toBean(obj,
							HrEt.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private HrEt convertVOToBO(HrEtVO hrEtVO) {
		HrEt hrEt = new HrEt();
		BeanUtils.copyProperties(hrEtVO, hrEt, new String[] { "hretId" });
		return hrEt;
	}

	private HrEtVO convertBOToVO(HrEt hrEt) {
		HrEtVO hrEtVO = new HrEtVO();
		BeanUtils.copyProperties(hrEt, hrEtVO);
		return hrEtVO;
	}

	public HrEtVO getHrEtVO() {
		return hrEtVO;
	}

	public void setHrEtVO(HrEtVO hrEtVO) {
		this.hrEtVO = hrEtVO;
	}

	public void setHrEtService(HrEtService hrEtService) {
		this.hrEtService = hrEtService;
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键删除
	 * @return
	 */
	public String deleteHrEtById(){
		HrEt hrEt = null;
		hrEt = hrEtService.findHrEtById(hrEtVO.getHretId());
		if(hrEt!=null){
			hrEt.setRemoved("1");
			hrEtService.deleteHrEtById(hrEt);
		}
		return "deleteHrEtById";
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键查询
	 * @return
	 */
	public String findHretByHretId(){
		HrEt hret = hrEtService.findHrEtById(hrEtVO.getHretId());
		this.servletRequest.setAttribute("hret", hret);
		return "findHretByHretId";
	}
	
	/**
	 * @author ycl
	 * @describe 跳转到编辑页面
	 * @return
	 */
	public String showHrEtEditPage(){
		HrEt hret = hrEtService.findHrEtById(hrEtVO.getHretId());
		this.servletRequest.setAttribute("hret", hret);
		return "showHrEtEditPage";
	}
	
	/**
	 * @author ycl
	 * @describe 更新
	 * @return
	 */
	public String editHrEt(){
		
//		HrEt hrEt = convertVOToBO(hrEtVO);
		
		HrEt myHrEt = hrEtService.findHrEtById(hrEtVO.getHretId());
		
		if(myHrEt!=null){
			myHrEt.setRemoved("1");
			hrEtService.updateHrEt(myHrEt);
		}else{
			this.servletRequest.setAttribute("hret", hrEtVO);
			return "failToUpdate";
		}
		
		if(hrEtService.findHrEtByTypeName(hrEtVO.getTypeName())){
			myHrEt.setRemoved("0");
			hrEtService.updateHrEt(myHrEt);
			this.servletRequest.setAttribute("typeStatus", "类型名称已存在,请重新命名");
			this.servletRequest.setAttribute("hret", hrEtVO);
			return "failToUpdate";
		}
		
		if(hrEtService.findHrEtBySortingOrder(hrEtVO.getSortingOrder())){
			myHrEt.setRemoved("0");
			hrEtService.updateHrEt(myHrEt);
			this.servletRequest.setAttribute("orderStatus","该排列顺序已存在，请重新制定排列顺序");
			this.servletRequest.setAttribute("hret", hrEtVO);
			return "failToUpdate";
		}
		
		myHrEt = convertVOToBO(hrEtVO);
		myHrEt.setHretId(hrEtVO.getHretId());
		myHrEt.setUpdateTime(new Date());
		myHrEt.setRemoved("0");
		hrEtService.updateHrEt(myHrEt);
		
		
		List<HrEtD> hretdList = hrEtService.findHrEtDByHretId(myHrEt.getHretId());
		for(int i=0;i<hretdList.size();i++){
			hretdList.get(i).setTypeName(myHrEt.getTypeName());
		}
		hrEtService.updateHrEtD(hretdList);
		hrEtService.updateHretName(hrEtVO.getHretId(), hrEtVO.getTypeName());
		
		return "editHrEt";
	}
	
	/**
	 * @author sunjiawei
	 * @describe 找出所有HrEt表数据	  
	 */
	public String findAllTypeName(){
		hrEtList = hrEtService.findAllTypeName();
		String string = VOUtils.getJsonDataFromCollection(hrEtList);
		createJSonData(string);

		return AJAX;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtList(){
		String hretId = this.servletRequest.getParameter("hretId");
		String typeName = this.servletRequest.getParameter("typeName");
		int sortingOrder = Integer.valueOf(this.servletRequest.getParameter("sortingOrder"));		
		String updatePerson = this.servletRequest.getParameter("updatePerson");
		hrEtService.updateHrEtList(hretId, typeName, sortingOrder, updatePerson);
	}
}
