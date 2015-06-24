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

package com.wonders.stpt.sthr.HrEtD.action;

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

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wonders.stpt.sthr.HrEtD.entity.vo.HrEtDVO;
import com.wonders.stpt.sthr.HrEtD.service.HrEtDService;
import com.wonders.stpt.sthr.HrExtInfo.entity.bo.HrExtInfo;
import com.wonders.stpt.sthr.HrExtInfo.entity.vo.HrExtInfoVO;
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

public class HrEtDAction extends BaseAjaxAction {
	private HrEtD hrEtD = new HrEtD();
	private HrEtDVO hrEtDVO = new HrEtDVO();
	private HrEtDService hrEtDService;	
	private HrExtInfo hrExtInfo;

	public HrExtInfo getHrExtInfo() {
		return hrExtInfo;
	}

	public void setHrExtInfo(HrExtInfo hrExtInfo) {
		this.hrExtInfo = hrExtInfo;
	}

	

	@Override
	public ValueObject getValueObject() {
		return this.hrEtDVO;
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
	 * @author ycl
	 * @describe 根据主键查询
	 * @return
	 */
	public String findHrEtDById() {
		String id = super.getServletRequest().getParameter("hretdId");

		HrEtD hrEtD = hrEtDService.findHrEtDById(id);
		this.servletRequest.setAttribute("hrEtD", hrEtD);
		return "findHrEtDById";
	}

	public String findHrEtDByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.hrEtDVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.hrEtDVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = hrEtDService
				.findHrEtDByPage(filter, start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page, HrEtD.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteHrEtD() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			HrEtD bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (HrEtD) JSONObject.toBean(obj, HrEtD.class);
				if (bean != null) {
					hrEtDService.deleteHrEtD(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	/**
	 * @author ycl
	 * @describe 添加
	 * @return
	 */
	public String addHrEtD() {
		String inputType = null;
		String itemFName = null;
		int max = 0;
		HrEtD hrEtD = convertVOToBO(hrEtDVO);
		
		//判断数据项名称和排列顺序是否存在
		if(hrEtDService.findHrEtDByItemName(hrEtD.getHretId(),hrEtD.getItemName())){
			this.servletRequest.setAttribute("itemNameStatus", "该数据名称已存在，请重新命名");
			return "faliToAdd";
		}
		if(hrEtDService.findHrEtDBySortingOrder(hrEtD.getHretId(),hrEtD.getSortingOrder())){
			this.servletRequest.setAttribute("sortingOrderStatus", "该排列顺序已存在，请重新填写");
			return "faliToAdd";
		}
		
		/*
		int numOfShow = hrEtDService.findCountOfIsLSHowByHretId(hrEtDVO.getHretId());
		if(hrEtD.getIsLShow().equals("1") && (numOfShow ==-1 || numOfShow>=4)){
			this.servletRequest.setAttribute("outOfShow", "最多显示4个字段");
			return "faliToAdd";
		}
		*/
		
		String SInputType = hrEtDVO.getInputType();
		
		if(hrEtDVO.getInputType().equals("普通文本（200字）")){
			inputType = "T_EXT";
			max = 20;
		}else if(hrEtDVO.getInputType().equals("长文本（4000字）")){
			inputType = "LT_EXT";
			max = 5;
		}else if(hrEtDVO.getInputType().equals("数字")){
			inputType = "N_EXT";
			max = 10;
		}else if(hrEtDVO.getInputType().equals("日期（年月日）")){
			inputType = "D_EXT";
			max = 5;
		}else if(hrEtDVO.getInputType().substring(0, 3).equals("下拉框")){
			inputType = "S_EXT";
			max = 5;
			
			SInputType = SInputType.replace("，", ",");
			hrEtD.setInputType(SInputType);
			SInputType = SInputType.substring(0, 3);
			//hrEtDVO.setInputType(SInputType);
		}else if(hrEtDVO.getInputType().substring(0, 3).equals("多选框")){
			inputType = "C_EXT";
			max = 5;
			
			SInputType = SInputType.replace("，", ",");
			hrEtD.setInputType(SInputType);
			SInputType = SInputType.substring(0, 3);
			//hrEtDVO.setInputType(SInputType);
		}
		
		List<HrEtD> hretList = hrEtDService.findAllHrEtDByInputType(hrEtDVO.getHretId(),SInputType);
		if(hretList!=null && hretList.size()!=0){
			itemFName = hretList.get(0).getItemFName();
			String prefix = itemFName.substring(0,(itemFName.lastIndexOf("T")+1));
			String number = itemFName.substring((itemFName.lastIndexOf("T")+1));
			number = String.valueOf((Integer.valueOf(number)+1));
			if(Integer.valueOf(number) > max){
				this.servletRequest.setAttribute("outOfDB","数据库字段已用完，请联系管理员！");
				return "faliToAdd";
			}
			hrEtD.setItemFName(prefix+number);
		}else{
			itemFName = inputType+"1";
			hrEtD.setItemFName(itemFName);
		}
		
		hrEtD.setUpdateTime(new Date());
		hrEtD.setRemoved("0");
		
		if (hrEtD != null) {
			hrEtDService.addHrEtD(hrEtD);
		}
		
		return "addHrEtD";
	}
	
	/**
	 * @author ycl
	 * @describe 跳转到添加页面
	 * @return
	 */
	public String showAddPage(){
		String hretId = this.servletRequest.getParameter("hretId");
		String typeName =  hrEtDService.findHrEtTypeNameById(hretId);
		this.servletRequest.setAttribute("typeName", typeName);
		return "showAddPage";
	}

	public String updateHrEtD() {
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
					HrEtDVO bean = (HrEtDVO) JSONObject.toBean(obj,
							HrEtDVO.class);
					hrEtDService.addHrEtD(this.convertVOToBO(bean));
				} else {
					hrEtDService.updateHrEtD((HrEtD) JSONObject.toBean(obj,
							HrEtD.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private HrEtD convertVOToBO(HrEtDVO hrEtDVO) {
		HrEtD hrEtD = new HrEtD();
		BeanUtils.copyProperties(hrEtDVO, hrEtD, new String[] { "hretdId" });
		return hrEtD;
	}

	private HrEtDVO convertBOToVO(HrEtD hrEtD) {
		HrEtDVO hrEtDVO = new HrEtDVO();
		BeanUtils.copyProperties(hrEtD, hrEtDVO);
		return hrEtDVO;
	}

	public HrEtDVO getHrEtDVO() {
		return hrEtDVO;
	}

	public void setHrEtDVO(HrEtDVO hrEtDVO) {
		this.hrEtDVO = hrEtDVO;
	}

	public void setHrEtDService(HrEtDService hrEtDService) {
		this.hrEtDService = hrEtDService;
	}
	/**
	 * @author ycl
	 * @describe 根据HrEtId查询
	 * @return
	 */
	public String findHrEtDByHrEtId() {
		int start = 0;
		int size = 20;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		String type = this.servletRequest.getParameter("type");
		if("extAdd".equals(type)||"extEdit".equals(type)||"extView".equals(type)){
			size=100;			
		}
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.hrEtDVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.hrEtDVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		
		HrEt hrEt = hrEtDService.findHrEtById(hrEtDVO.getHretId());
		if(hrEt!=null){
			this.servletRequest.setAttribute("typeName", hrEt.getTypeName());
			this.servletRequest.setAttribute("hretId", hrEt.getHretId());
		}
        
		Page page = hrEtDService.findHrEtDByPage(filter, start / size + 1, size);
		
		List<HrEtD> list = page.getResult();		
		for(int i=0;i<list.size();i++){					
			String ext = list.get(i).getItemFName();			
			ext=ext.replace("LT_EXT","ltExt");
			ext=ext.replace("T_EXT","tExt");
			ext=ext.replace("D_EXT","dExt");
			ext=ext.replace("N_EXT","nExt");
			ext=ext.replace("S_EXT","sExt");
			ext=ext.replace("C_EXT","cExt");
			list.get(i).setItemFName(ext);
			
//			String input_type = list.get(i).getInputType();
//			if("下拉".equals(input_type.substring(0, 2))){
//				this.servletRequest.setAttribute("sInputType", "下拉框");
//			}
//			if("多选".equals(input_type.substring(0, 2))){
//				this.servletRequest.setAttribute("cInputType", "多选框");
//			}
		}
		this.servletRequest.setAttribute("page", page);	
		
		if("extEdit".equals(type)||"extView".equals(type)){			
			String id=this.servletRequest.getParameter("id");	
			String hrId=this.servletRequest.getParameter("hrId");
			List<Object[]> list2 = hrEtDService.findHrExtInfoById(id, hrEtDVO.getHretId());	
			List<String> list3 = new ArrayList<String>();
			if(list2!=null&&list2.size()!=0){
				if(list2.get(0) instanceof Object[]){
					for(int i=0;i<list2.get(0).length;i++){
						  if(list2.get(0)[i]==null){
							  list3.add("");
						  }else{
							  list3.add(String.valueOf(list2.get(0)[i]));
						  }
					    }
				}else {
					if(list2.get(0)==null){
						  list3.add("");
					  }else{
						  list3.add(String.valueOf(list2.get(0)));
					  }
				}			    
			}

			this.servletRequest.setAttribute("list3", list3);	
			this.servletRequest.setAttribute("id", id);
			this.servletRequest.setAttribute("hrId", hrId);
			if("extEdit".equals(type)){	
			  return "extEdit";
			}else return "extView";
		}
		
		if("extAdd".equals(type)){
			String hrId = this.servletRequest.getParameter("hrId");
			this.servletRequest.setAttribute("hrId", hrId);	
			return "extAdd";			
		}else return "findHrEtDByHrEtId";
	}
	/**
	 * @author ycl
	 * @describe 逻辑删除
	 * @return
	 */
	public String deleteHrEtDById(){
		HrEtD hrEtD = hrEtDService.findHrEtDById(hrEtDVO.getHretdId());
		hrEtD.setRemoved("1");
		hrEtDService.deleteHrEtDByLogic(hrEtD);
		return "deleteHrEtDById";
	}
	
	/**
	 * @author ycl
	 * @describe 跳转到编辑页面
	 * @return
	 */
	public String showEditPage(){
		String id = super.getServletRequest().getParameter("hretdId");
		HrEtD hrEtD = hrEtDService.findHrEtDById(id);
		this.servletRequest.setAttribute("hrEtD", hrEtD);
		
		return "showEditPage";
	}
	
	/**
	 * @author ycl
	 * @describe 更新
	 * @return
	 */
	public String editHrEtD(){
		
		HrEtD hrEtD = convertVOToBO(hrEtDVO);
		hrEtD.setHretdId(hrEtDVO.getHretdId());
		hrEtD.setRemoved("1");
		
		String getIsSHow = hrEtD.getIsLShow();
		String isShow = this.servletRequest.getParameter("isShow");
		
		hrEtDService.updateHrEtD(hrEtD);
		
		
		//判断数据项名称和排列顺序是否存在
		if(hrEtDService.findHrEtDByItemName(hrEtD.getHretId(),hrEtD.getItemName())){
			this.servletRequest.setAttribute("itemNameStatus", "该数据名称已存在，请重新命名");
			hrEtD.setRemoved("0");
			this.servletRequest.setAttribute("hrEtD", hrEtD);
			hrEtD.setIsLShow(isShow);
			hrEtDService.updateHrEtD(hrEtD);
			return "faliToAdd";
		}
		if(hrEtDService.findHrEtDBySortingOrder(hrEtD.getHretId(),hrEtD.getSortingOrder())){
			hrEtD.setRemoved("0");
			this.servletRequest.setAttribute("hrEtD", hrEtD);
			hrEtD.setIsLShow(isShow);
			hrEtDService.updateHrEtD(hrEtD);
			this.servletRequest.setAttribute("sortingOrderStatus", "该排列顺序已存在，请重新填写");
			return "faliToAdd";
		}
		
		/*
		int numOfShow = hrEtDService.findCountOfIsLSHowByHretId(hrEtDVO.getHretId());
		if(hrEtD.getIsLShow().equals("1") && (numOfShow ==-1 || numOfShow>=4)){
			hrEtD.setRemoved("0");
			hrEtD.setIsLShow(isShow);
			hrEtDService.updateHrEtD(hrEtD);
			hrEtD.setIsLShow(getIsSHow);
			this.servletRequest.setAttribute("hrEtD", hrEtD);
			this.servletRequest.setAttribute("outOfShow", "最多显示4个字段");
			return "faliToAdd";
		}
		*/
		hrEtD.setRemoved("0");
		hrEtD.setUpdateTime(new Date());
		hrEtDService.updateHrEtD(hrEtD);
		
		return "editHrEtD";
	}
	
	private HrExtInfoVO convertBOToVO(HrExtInfo hrExtInfo) {
		HrExtInfoVO hrExtInfoVO = new HrExtInfoVO();
		BeanUtils.copyProperties(hrExtInfo, hrExtInfoVO);
		return hrExtInfoVO;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtDList(){
		String hretdId = this.servletRequest.getParameter("hretdId");
		int sortingOrder = Integer.valueOf(this.servletRequest.getParameter("sortingOrder"));
		String itemName = this.servletRequest.getParameter("itemName");
		String isLShow = this.servletRequest.getParameter("isLShow");
		isLShow = isLShow.replace("是", "1");
		isLShow = isLShow.replace("否", "0");
		String updatePerson = this.servletRequest.getParameter("updatePerson");
		
		hrEtDService.updateHrEtDList(hretdId, sortingOrder, itemName, isLShow, updatePerson);
	}
}
