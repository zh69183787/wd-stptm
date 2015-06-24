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

package com.wonders.stpt.sthr.HrBInfo.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.CrossIpLogin;
import com.wonders.stpt.UserInfo;
import com.wonders.stpt.sthr.ExportExcel;
import com.wonders.stpt.sthr.HrBInfo.entity.bo.HrBInfo;
import com.wonders.stpt.sthr.HrBInfo.entity.vo.HrBInfoVO;
import com.wonders.stpt.sthr.HrBInfo.service.HrBInfoService;
import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrBInfoAction extends BaseAjaxAction {
	private HrBInfo hrBInfo = new HrBInfo();
	private HrBInfoVO hrBInfoVO = new HrBInfoVO();
	private HrBInfoService hrBInfoService;	

	@Override
	public ValueObject getValueObject() {
		return this.hrBInfoVO;
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

	public String findHrBInfoById() {
		//总页数
		int pageCount = 0;
		//总条数
		int count = 0;
		//每页显示的条数
		int pageSize = 10;
		
		List<HrEt> hretList = null;
		List<HrEtD> hretdList = null;
		List<Object[]> hrExtInfoList = null;
		List<String[]> showPageData = new ArrayList<String[]>();
		List<String> paramList = new ArrayList<String>();
		String id = super.getServletRequest().getParameter("hrId");
		String type = super.getServletRequest().getParameter("type");
		HrBInfo bo = hrBInfoService.findHrBInfoById(id);
//		if (bo != null) {
//			String json = VOUtils.getJsonData(convertBOToVO(bo));
//			createJSonData("{\"success\":true,\"result\":[" + json.toString()
//					+ "]}");
//		}
		hrBInfoVO=convertBOToVO(bo);
		
		//查询所有类别
		hretList = hrBInfoService.findAllHrEt();
		this.servletRequest.setAttribute("hretList", hretList);
		
		//根据类别查询所有数据项
		if(hretList!=null && hretList.size()!=0){
			hretdList = hrBInfoService.findAllHrEtDByType(hretList.get(0).getHretId());
		}
		
		this.servletRequest.setAttribute("hretdList", hretdList);
		
		//根据HrId,hretId,hretdId查询所有的个人扩展信息
		if(id!=null && !id.equals("") && hretList!=null && hretList.size()!=0 && hretdList!=null && hretdList.size()!=0){
			for(int i=0;i<hretdList.size();i++){
				paramList.add(hretdList.get(i).getItemFName());
			}
			hrExtInfoList = hrBInfoService.findAllHrExtInfoByParam(id, hretList.get(0).getHretId(),paramList);
			
			String[] tempStringArray = null;
			for(int i=0;i<hrExtInfoList.size();i++){
				tempStringArray = new String[hrExtInfoList.get(i).length];
				for(int j=0;j<hrExtInfoList.get(i).length;j++){
					if(hrExtInfoList.get(i)[j]==null){
						tempStringArray[j] = "";
					}else{
						tempStringArray[j] = String.valueOf(hrExtInfoList.get(i)[j]);
					}
					
				}
				showPageData.add(tempStringArray);
			}
		}
		
		if(showPageData!=null && showPageData.size()>0){
			count = showPageData.size();
			if(count%pageSize == 0){
				pageCount = count/pageSize;
			}else{
				pageCount = count/pageSize+1;
			}
		}
		
		if(showPageData.size()>pageSize){
			List<String[]> temp = showPageData;
			showPageData = new ArrayList<String[]>();
			for(int i=0;i<pageSize;i++){
				showPageData.add(temp.get(i));
			}
		}
		
		this.servletRequest.setAttribute("pageNo", "1");
		this.servletRequest.setAttribute("count", count);
		this.servletRequest.setAttribute("pageCount", pageCount);
		this.servletRequest.setAttribute("hrExtInfoList", showPageData);
		
/*		
		String jsonData = VOUtils.getJsonDataFromCollection(hrExtInfoList);
		createJSonData(jsonData);
*/		
		if (type.equals("edit"))
			return "edit";
		else if (type.equals("view"))
			return "view";
		return SUCCESS;
	}

	public String findHrBInfoByPage() throws ParseException {
		Page page;
		String birthdayStart = this.servletRequest.getParameter("birthdayStart");
		String birthdayEnd = this.servletRequest.getParameter("birthdayEnd");
		String currentPageStr = this.servletRequest.getParameter("number");	
		String updateTimeOrder = this.servletRequest.getParameter("updateTimeOrder");
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		
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
		JSONObject obj = JSONObject.fromObject(this.hrBInfoVO);
		
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			if(key.equals("CCompany")){
				value = this.servletRequest.getParameter("cCompany");
			}else{
				value = this.servletRequest.getParameter(key);
			}
			
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.hrBInfoVO, key);
				if (res != null) {
					filter.put(key, res);
					
				}
			}
		}		
		String hretName=this.servletRequest.getParameter("hretName");
		if(!"".equals(hretName)&&null!=hretName){	
			filter.put("hretName", hretName);
	        this.servletRequest.setAttribute("hretName", hretName);
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(birthdayStart)&&null!=birthdayStart){	
			Date date =  format.parse(birthdayStart);
			filter.put("birthdayStart", date);
	        this.servletRequest.setAttribute("birthdayStart", birthdayStart);
		}
		if(!"".equals(birthdayEnd)&&null!=birthdayEnd){	
			Date date =  format.parse(birthdayEnd);
			filter.put("birthdayEnd", date);
	        this.servletRequest.setAttribute("birthdayEnd", birthdayEnd);
		}
		//String timeValue1=this.servletRequest.getParameter("birthday1");
		//String timeValue2=this.servletRequest.getParameter("birthday2");
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");			
//		if(!"".equals(timeValue1)&&null!=timeValue1){					
//			Date birthday1=simpleDateFormat.parse(timeValue1);
//			filter.put("birthday1", birthday1);
//			this.servletRequest.setAttribute("birthday1", timeValue1);
//		}
//		if(!"".equals(timeValue2)&&null!=timeValue2){
//			Date birthday2=simpleDateFormat.parse(timeValue2);
//			filter.put("birthday2", birthday2);
//			this.servletRequest.setAttribute("birthday2", timeValue2);
//		}

		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filterButton=properties.getProperty("filterButton");
		String deptId = "";
		if("on".equals(filterButton)){
			List<Object> list = hrBInfoService.findHrLimit();
			if(null!=list&&list.size()>0){
				String limit = String.valueOf(list.get(0));
				if("1".equals(limit)){
					CrossIpLogin crossIpLogin = new CrossIpLogin();
					UserInfo userInfo = new UserInfo();
					crossIpLogin.setUserInfo(servletRequest,userInfo);
					deptId = userInfo.getDeptId();
					filter.put("companyId", deptId);
				}				
			}
		}
		
		if (currentPage == 0) {
		   page = hrBInfoService.findHrBInfoByPage(filter, start / size + 1,
				size,updateTimeOrder);
		}else {
			page = hrBInfoService.findHrBInfoByPage(filter, currentPage,
					size,updateTimeOrder);
		}
		//String json = VOUtils.getJsonDataFromPage(page, HrBInfo.class);
		//createJSonData(json);
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("updateTimeOrder", updateTimeOrder);
		
		List<Object> list = hrBInfoService.findHrLimit();
		if(null!=list&&list.size()>0){
			String limit = String.valueOf(list.get(0));	
			this.servletRequest.setAttribute("limit", limit);
		}
		
		String limitPerson = "";
		List<Object> list1 = hrBInfoService.findLimitPerson();
		if(null!=list1&&list1.size()>0){			
			for(int i=0;i<list1.size();i++){
				if(i==0){
				    limitPerson = String.valueOf(list1.get(i));
				}else{
				    limitPerson = limitPerson+","+String.valueOf(list1.get(i));
				}
			}
			this.servletRequest.setAttribute("limitPerson", limitPerson);
		}
		return SUCCESS;
	}

	public String deleteHrBInfo() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			HrBInfo bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (HrBInfo) JSONObject.toBean(obj, HrBInfo.class);
				if (bean != null) {
					hrBInfoService.deleteHrBInfo(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addHrBInfo() throws ParseException {		
		HrBInfo hrBInfo = convertVOToBO(hrBInfoVO);		
		Date date = new Date();
		hrBInfo.setUpdateTime(date);
		
		Properties properties = new Properties();
		//String path = Thread.currentThread().getContextClassLoader().getResource("dept.properties").getPath();
		InputStream ipt = this.getClass().getClassLoader().getResourceAsStream("/dept.properties");
	
		try {
			properties.load(ipt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("propertiesLoadError");
			e.printStackTrace();
		}
	
		String cCompany = hrBInfo.getCCompany();
		String companyId=properties.getProperty(cCompany);
		if(null!=companyId)	hrBInfo.setCompanyId(companyId);
		
		if (hrBInfo != null) {					
			hrBInfoService.addHrBInfo(hrBInfo);
		}
		this.servletRequest.setAttribute("addSuccess", "success");		
		createJSonData("{\"success\":true}");
		return SUCCESS;
	}

	public String updateHrBInfo() {
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
					HrBInfoVO bean = (HrBInfoVO) JSONObject.toBean(obj,
							HrBInfoVO.class);
					hrBInfoService.addHrBInfo(this.convertVOToBO(bean));
				} else {
					hrBInfoService.updateHrBInfo((HrBInfo) JSONObject.toBean(
							obj, HrBInfo.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private HrBInfo convertVOToBO(HrBInfoVO hrBInfoVO) {
		HrBInfo hrBInfo = new HrBInfo();
		BeanUtils.copyProperties(hrBInfoVO, hrBInfo, new String[] { "hrId" });
		return hrBInfo;
	}

	private HrBInfoVO convertBOToVO(HrBInfo hrBInfo) {
		HrBInfoVO hrBInfoVO = new HrBInfoVO();
		BeanUtils.copyProperties(hrBInfo, hrBInfoVO);
		return hrBInfoVO;
	}

	public HrBInfoVO getHrBInfoVO() {
		return hrBInfoVO;
	}

	public void setHrBInfoVO(HrBInfoVO hrBInfoVO) {
		this.hrBInfoVO = hrBInfoVO;
	}

	public void setHrBInfoService(HrBInfoService hrBInfoService) {
		this.hrBInfoService = hrBInfoService;
	}
	
	/**
     * @author sunjiawei
     * @param null
     * @describe 添加页面跳转
     */	
	public String toAdd() {				
		return SUCCESS;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 根据主键删除
	 * @return String
	 * @throws ParseException 
	 */
	public String deleteByHrId() throws ParseException {
		String hrId = this.servletRequest.getParameter("hrId");		
		if (hrId != null) {
			hrBInfoService.deleteByHrId(hrId);
			hrBInfoVO.setHrId(null);
			findHrBInfoByPage();
		}
		
		return SUCCESS;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 根据主键修改
	 * @return String	 
	 */
	public String updateHrBInfoByHrId(){		
		HrBInfo hrBInfo = convertVOToBO(hrBInfoVO);
		hrBInfo.setHrId(hrBInfoVO.getHrId());	
		Date date = new Date();
		hrBInfo.setUpdateTime(date);
		
		Properties properties = new Properties();
		//String path = Thread.currentThread().getContextClassLoader().getResource("dept.properties").getPath();
		InputStream ipt = this.getClass().getClassLoader().getResourceAsStream("/dept.properties");
		
		try {
			properties.load(ipt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("propertiesLoadError");
			e.printStackTrace();
		}
		String cCompany = hrBInfo.getCCompany();
		String companyId=properties.getProperty(cCompany);
		if(null!=companyId)	hrBInfo.setCompanyId(companyId);
		
		if (hrBInfo != null) {
			hrBInfoService.updateHrBInfo(hrBInfo);			
		}
		return SUCCESS;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrBInfoList(){
		String hrId = this.servletRequest.getParameter("hrId");
		String jobNumber = this.servletRequest.getParameter("jobNumber");
		String name = this.servletRequest.getParameter("name");
		String sex = this.servletRequest.getParameter("sex");
		sex = sex.replace("男", "1");
		sex = sex.replace("女", "0");
		String birthday = this.servletRequest.getParameter("birthday");
		String mobilePhone = this.servletRequest.getParameter("mobilePhone");
		String cCompany = this.servletRequest.getParameter("cCompany");
		String position = this.servletRequest.getParameter("position");
		String updatePerson = this.servletRequest.getParameter("updatePerson");
		
		Properties properties = new Properties();
		//String path = Thread.currentThread().getContextClassLoader().getResource("dept.properties").getPath();
		InputStream ipt = this.getClass().getClassLoader().getResourceAsStream("/dept.properties");
		
		try {
			properties.load(ipt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("propertiesLoadError");
			e.printStackTrace();
		}
		String companyId=properties.getProperty(cCompany);
		if(null==companyId) companyId = "";
		hrBInfoService.updateHrBInfoList(hrId,jobNumber,name,sex,birthday,mobilePhone,cCompany,position,updatePerson,companyId);
	}
	
	/**
	 * @author sunjiawei
	 * 数据导出excel 
	 */
	public void exportExcel(){
		String updateTimeOrder = this.servletRequest.getParameter("updateTimeOrder"); 
		String hretName = this.servletRequest.getParameter("hretName");
		String jobNumber = this.servletRequest.getParameter("jobNumber");
		String name = this.servletRequest.getParameter("name");
		String sex = this.servletRequest.getParameter("sex");
		String birthdayStart = this.servletRequest.getParameter("birthdayStart");
		String birthdayEnd = this.servletRequest.getParameter("birthdayEnd");
		String cCompany = this.servletRequest.getParameter("cCompany");
		String politicalLandscape = this.servletRequest.getParameter("politicalLandscape");
		String isRetire = this.servletRequest.getParameter("isRetire");
		//String position = this.servletRequest.getParameter("position");
	
//		try {
//			hretName = new String(hretName.getBytes("ISO-8859-1"),"utf-8");	
//			name = new String(name.getBytes("ISO-8859-1"),"utf-8");
//			cCompany = new String(cCompany.getBytes("ISO-8859-1"),"utf-8");
//			position = new String(position.getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
		
		this.servletResponse.setContentType("octets/stream");  
		this.servletResponse.addHeader("Content-Disposition","attachment;filename=HrInfo.xls");  
		ExportExcel<HrBInfo> ex = new ExportExcel<HrBInfo>();  
		String[] headers = {"工号","姓名","身份证","性别","出生日期","民族","籍贯","政治面貌","最高学历","居住地址","居住邮编","居住电话","手机","目前工作单位","目前职务","最高职称","技术等级","工作单位地址","单位邮编","单位电话","支内或农口","在职或退休","备注"};
		String[] all_headers ;
		if("".equals(hretName)){
			all_headers = headers;
		}else{
			List<Object[]> listHeaders = hrBInfoService.findTypeNameForExport(hretName);
			all_headers = new String[headers.length+listHeaders.size()];
			if(null!=listHeaders&&listHeaders.size()>0){
				
				for(int i=0;i<headers.length;i++){
					all_headers[i]=headers[i];
				}				
				for(int i=0;i<listHeaders.size();i++){
					all_headers[i+headers.length]=String.valueOf(listHeaders.get(i)[0]);
				}
			}
			
		}
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(!"".equals(jobNumber)&&null!=jobNumber){	
			filter.put("job_number", jobNumber);
		}
		if(!"".equals(name)&&null!=name){	
			filter.put("name", name);
		}
		if(!"".equals(sex)&&null!=sex){	
			filter.put("sex", sex);
		}		
		if(!"".equals(birthdayStart)&&null!=birthdayStart){				
			filter.put("birthdayStart", birthdayStart);
		}
		if(!"".equals(birthdayEnd)&&null!=birthdayEnd){			
			filter.put("birthdayEnd", birthdayEnd);
		}
		if(!"".equals(cCompany)&&null!=cCompany){	
			filter.put("c_company", cCompany);
		}
		if(!"".equals(politicalLandscape)&&null!=politicalLandscape){	
			filter.put("political_landscape", politicalLandscape);
		}
		if(!"".equals(isRetire)&&null!=isRetire){	
			filter.put("is_retire", isRetire);
		}
//		if(!"".equals(position)&&null!=position){	
//			filter.put("position", position);	        
//		}
		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filterButton=properties.getProperty("filterButton");
		String deptId = "";		
		if("on".equals(filterButton)){
			List<Object> list = hrBInfoService.findHrLimit();
			if(null!=list&&list.size()>0){
				String limit = String.valueOf(list.get(0));
				if("1".equals(limit)){
					CrossIpLogin crossIpLogin = new CrossIpLogin();
					UserInfo userInfo = new UserInfo();
					crossIpLogin.setUserInfo(servletRequest,userInfo);
					deptId = userInfo.getDeptId();
					filter.put("company_id", deptId);
				}				
			}
		}
		
		List<Object[]> dataset = new ArrayList<Object[]>();
		dataset = hrBInfoService.findHrBInfoForExport(filter, updateTimeOrder, hretName);
		if(null!=dataset&&dataset.size()>0){
			for(int i=1;i<dataset.size();i++){
				if(dataset.get(i)[1].equals(dataset.get(i-1)[1])){
					for(int j=0;j<all_headers.length-headers.length;j++){
						dataset.get(i-1)[j+headers.length] = String.valueOf(dataset.get(i-1)[j+headers.length])+"、"+String.valueOf(dataset.get(i)[j+headers.length]);
					}
					dataset.remove(i);
					i=i-1;
				}
			}
		}
		try {
			OutputStream out = this.servletResponse.getOutputStream();
			ex.exportExcel("工会人事数据库数据导出",all_headers,dataset,out);  
			out.close();  

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  


	}
	
	/**
	 * @author sunjiawei
	 * 改变权限
	 */
	public void changeLimit(){
		String limit = this.servletRequest.getParameter("limit");
		hrBInfoService.changeLimit(limit);
	}
}
