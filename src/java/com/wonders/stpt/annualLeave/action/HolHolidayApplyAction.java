
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
 
package com.wonders.stpt.annualLeave.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import com.wonders.stpt.myNotice.service.TMsgUsermessageService;
import com.wonders.stpt.util.*;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.security.bo.SecurityUser;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.HolHolidayApply;
import com.wonders.stpt.annualLeave.service.HolConfigService;
import com.wonders.stpt.annualLeave.service.HolHolidayApplyService;
import com.wonders.stpt.annualLeave.service.HolHolidayService;
import com.wonders.stpt.annualLeave.utils.CookieUtils;
import com.wonders.stpt.annualLeave.utils.HttpRequestHelper;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.util.PropertiesUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */
 
public class HolHolidayApplyAction extends BaseAjaxAction {
    public static final String CODE_OF_ROOT_TREE="stjt";
    public static final String CODE_OF_ROOT_NODE="shengtongjituan";
	private HolHolidayApply holHolidayApply = new HolHolidayApply();
	private HolHolidayApplyService holHolidayApplyService;
	private HolHolidayService holHolidayService;
	private HolConfigService holConfigService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int size=20; 
	private TMsgUsermessageService msgUsermessageService;
	public HolConfigService getHolConfigService() {
		return holConfigService;
	}
	public void setHolConfigService(HolConfigService holConfigService) {
		this.holConfigService = holConfigService;
	}
	public HolHolidayApply getHolHolidayApply() {
		return holHolidayApply;
	}
	public void setHolHolidayApply(HolHolidayApply holHolidayApply) {
		this.holHolidayApply = holHolidayApply;
	}
	public HolHolidayApplyService getHolHolidayApplyService() {
		return holHolidayApplyService;
	}

    public HolHolidayService getHolHolidayService() {
		return holHolidayService;
	}
	public void setHolHolidayService(HolHolidayService holHolidayService) {
		this.holHolidayService = holHolidayService;
	}
	public void setHolHolidayApplyService(
			HolHolidayApplyService holHolidayApplyService) {
		this.holHolidayApplyService = holHolidayApplyService;
	}

    public TMsgUsermessageService getMsgUsermessageService() {
        return msgUsermessageService;
    }

    public void setMsgUsermessageService(TMsgUsermessageService msgUsermessageService) {
        this.msgUsermessageService = msgUsermessageService;
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



	public String findHolHolidayApplyByPage() throws IOException, DocumentException{
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.holHolidayApply);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.holHolidayApply, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		filter.put("removed", 0l);
		filter.put("applyUser",Long.valueOf(CookieUtils.getCookieValue(servletRequest, "userId")));
		
		String startDate = servletRequest.getParameter("startDate");
		if(StringUtils.isNotEmpty(startDate)){
			filter.put("startDate", startDate);
			servletRequest.setAttribute("startDate", startDate);
		}
		String holDays = servletRequest.getParameter("holDays");
		if(StringUtils.isNotEmpty(holDays)){
			filter.put("holDays", Long.valueOf(holDays));
			servletRequest.setAttribute("holDays", holDays);
		}
		String holState = servletRequest.getParameter("holState");
		if(StringUtils.isNotEmpty(holState)){
			filter.put("holState", Long.valueOf(holState));
			servletRequest.setAttribute("holState", holState);
		}
		
		Page page = holHolidayApplyService.findHolHolidayApplyByPage(filter,Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		
		Properties properties = PropertiesUtils.load("config.properties");
		String token = CookieUtils.getCookieValue(servletRequest, "token");
		String secret = properties.getProperty("secret");
		String appName = properties.getProperty("appName");
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		String userId = CookieUtils.getCookieValue(servletRequest, "userId");
		String dataParams =          "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptId + "</id></params>";
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getDeptLeaders",secret,"xml",dataParams);	
		Document document = DocumentHelper.parseText(usePersonXMl);
		Element rootElem = document.getRootElement();
		Node datas = rootElem.selectSingleNode("params");
		List<Node> dataList = datas.selectNodes("param");
		List<User> leaders = new ArrayList<User>();
		
		boolean showStatus = false;
		if(dataList!=null && dataList.size()>0){
			for(int i=0,length=dataList.size(); i<length; i++){
				User u = new User();
				u.setId(Long.valueOf(dataList.get(i).selectSingleNode("id").getText()));
				u.setName(dataList.get(i).selectSingleNode("name").getText());
				leaders.add(u);
				if((u.getId()+"").equals(userId)){
					showStatus = true;
				}
			}
		}
		servletRequest.setAttribute("leaders", leaders);
		servletRequest.setAttribute("showStatus", showStatus);
		
		return SUCCESS;
	}
	
	public String findHolHolidayApplyByPageForApprove() throws IOException, DocumentException{
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.holHolidayApply);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.holHolidayApply, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		filter.put("removed", 0l);
		
		String startDate = servletRequest.getParameter("startDate");
		if(StringUtils.isNotEmpty(startDate)){
			filter.put("startDate", startDate);
			servletRequest.setAttribute("startDate", startDate);
		}
		String holDays = servletRequest.getParameter("holDays");
		if(StringUtils.isNotEmpty(holDays)){
			filter.put("holDays", Long.valueOf(holDays));
			servletRequest.setAttribute("holDays", holDays);
		}
		String holState = servletRequest.getParameter("holState");
		if(StringUtils.isNotEmpty(holState)){
			filter.put("holState", Long.valueOf(holState));
			servletRequest.setAttribute("holState", holState);
		}
		String applyName = servletRequest.getParameter("applyName");
		if(StringUtils.isNotEmpty(applyName)){
			filter.put("applyName", applyName);
			servletRequest.setAttribute("applyName", applyName);
		}
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		String userId = CookieUtils.getCookieValue(servletRequest, "userId");
		filter.put("holDeptId", deptId);
		filter.put("checkUser", Long.valueOf(userId));
		Page page = holHolidayApplyService.findHolHolidayApplyByPage(filter,Integer.valueOf(pageNo), size);
		if(page!=null && page.getResult()!=null){
			List<com.wonders.stpt.csUser.entity.bo.CsUser> userList = new ArrayList<com.wonders.stpt.csUser.entity.bo.CsUser>();
			List<HolHolidayApply> list = page.getResult();
			for(int i=0,leng=list.size(); i<leng; i++){
				com.wonders.stpt.csUser.entity.bo.CsUser csUser = holHolidayService.findUserById(list.get(i).getApplyUser());
				userList.add(csUser);
			}
			servletRequest.setAttribute("userList", userList);
		}
		
		servletRequest.setAttribute("page", page);
		
		Properties properties = PropertiesUtils.load("config.properties");
		String token = CookieUtils.getCookieValue(servletRequest, "token");
		String secret = properties.getProperty("secret");
		String appName = properties.getProperty("appName");
	
		String dataParams =          "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptId + "</id></params>";
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getDeptLeaders",secret,"xml",dataParams);	
		Document document = DocumentHelper.parseText(usePersonXMl);
		Element rootElem = document.getRootElement();
		Node datas = rootElem.selectSingleNode("params");
		List<Node> dataList = datas.selectNodes("param");
		List<User> leaders = new ArrayList<User>();
		
		boolean showStatus = false;
		if(dataList!=null && dataList.size()>0){
			for(int i=0,length=dataList.size(); i<length; i++){
				User u = new User();
				u.setId(Long.valueOf(dataList.get(i).selectSingleNode("id").getText()));
				u.setName(dataList.get(i).selectSingleNode("name").getText());
				leaders.add(u);
				if((u.getId()+"").equals(userId)){
					showStatus = true;
				}
			}
		}
		servletRequest.setAttribute("leaders", leaders);
		servletRequest.setAttribute("showStatus", showStatus);
		
		return SUCCESS;
	}
	
	
	public String findHolHolidayApplyByPageForDept() throws IOException, DocumentException{
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.holHolidayApply);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.holHolidayApply, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		
		filter.put("removed", 0l);
		filter.put("holDeptId",deptId+"");
		String applyName = servletRequest.getParameter("applyName");
		if(StringUtils.isNotEmpty(applyName)){
			filter.put("applyName", applyName);
			servletRequest.setAttribute("applyName", applyName);
		}
		
		String startDate = servletRequest.getParameter("startDate");
		if(StringUtils.isNotEmpty(startDate)){
			filter.put("startDate", startDate);
			servletRequest.setAttribute("startDate", startDate);
		}
		String holDays = servletRequest.getParameter("holDays");
		if(StringUtils.isNotEmpty(holDays)){
			filter.put("holDays", Long.valueOf(holDays));
			servletRequest.setAttribute("holDays", holDays);
		}
		String holState = servletRequest.getParameter("holState");
		if(StringUtils.isNotEmpty(holState)){
			filter.put("holState", Long.valueOf(holState));
			servletRequest.setAttribute("holState", holState);
		}
		
		Page page = holHolidayApplyService.findHolHolidayApplyByPage(filter,Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		
		if(page!=null && page.getResult()!=null && page.getResult().size()>0){
			List<CsUser> applyUserList = new ArrayList<CsUser>();
			for(int i=0,length=page.getResult().size(); i<length; i++){
				HolHolidayApply apply = (HolHolidayApply) page.getResult().get(i);
				CsUser csUser = holHolidayApplyService.findCsUserById(apply.getApplyUser());
				applyUserList.add(csUser);
			}
			servletRequest.setAttribute("applyUserList", applyUserList);
		}
		
		
		Properties properties = PropertiesUtils.load("config.properties");
		String token = CookieUtils.getCookieValue(servletRequest, "token");
		String secret = properties.getProperty("secret");
		String appName = properties.getProperty("appName");
		//String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		String userId = CookieUtils.getCookieValue(servletRequest, "userId");
		String dataParams =          "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptId + "</id></params>";
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getDeptLeaders",secret,"xml",dataParams);	
		Document document = DocumentHelper.parseText(usePersonXMl);
		Element rootElem = document.getRootElement();
		Node datas = rootElem.selectSingleNode("params");
		List<Node> dataList = datas.selectNodes("param");
		List<User> leaders = new ArrayList<User>();
		
		boolean showStatus = false;
		if(dataList!=null && dataList.size()>0){
			for(int i=0,length=dataList.size(); i<length; i++){
				User u = new User();
				u.setId(Long.valueOf(dataList.get(i).selectSingleNode("id").getText()));
				u.setName(dataList.get(i).selectSingleNode("name").getText());
				leaders.add(u);
				if((u.getId()+"").equals(userId)){
					showStatus = true;
				}
			}
		}
		servletRequest.setAttribute("leaders", leaders);
		servletRequest.setAttribute("showStatus", showStatus);
		
		return SUCCESS;
	}
	
	public String showHolHolidaysApplyAdd() throws IOException, DocumentException{
		
  	String userId =CookieUtils.getCookieValue(servletRequest, "userId");

		long[] stateArray = {55l,58l};		//申请中，初始保存的
		List<HolHolidayApply> applyList = holHolidayApplyService.findByApplyUserAndHolState(Long.valueOf(userId),stateArray);
		if(applyList!=null && applyList.size()>0){
			servletRequest.setAttribute("ayylying","yes");
		}
		
		String startYear ="";
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		int thisYearMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		
		Object[] settime =holHolidayService.findHolHolidaySettime();
		String overyear="";
		String overmonth="";
		if(settime!=null){
			overyear = settime[0].toString();		//超期年份
			overmonth = settime[1].toString();			//超期月份
			if(Integer.valueOf(thisYearMonth)>=Integer.valueOf(overmonth)){
				startYear = thisYear-Integer.valueOf(overyear)+1+""; 
			}else{
				startYear = thisYear-Integer.valueOf(overyear)+"";
			}
			if(overmonth.length()==1){
				overmonth ="0"+overmonth;
			}
			String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
			servletRequest.setAttribute("thisYearEndDate", thisYearEndDate);
		}
		String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
		String nextYearEndDate="";
		long allDaysLeft=0l;
		List<HolHoliday> list = holHolidayService.findByStartYearAndHolPersonId(startYear, userId);
		if(list!=null && list.size()>0){
			for(int i=0,length=list.size(); i<length; i++){
				HolHoliday temp = list.get(i);
				if(thisYear==Integer.valueOf(temp.getHolYear())){
					servletRequest.setAttribute("thisYearHolHoliday", temp);
				}
				if((thisYear-1)== Integer.valueOf(temp.getHolYear())){			//如果是去年的数据
					servletRequest.setAttribute("lastYearCount", temp.getHolDaysLeft());
					servletRequest.setAttribute("lastYearEndDate", (Integer.valueOf(temp.getHolYear())+Integer.valueOf(overyear)+"-"+overmonth+"-01"));
				}
				if((thisYear-2)==Integer.valueOf(temp.getHolYear())){			//如果是前年的数据
					servletRequest.setAttribute("yearBeforeLastYearCount", temp.getHolDaysLeft());
					servletRequest.setAttribute("yearBeforeLastYearEndDate", (Integer.valueOf(temp.getHolYear())+Integer.valueOf(overyear)+"-"+overmonth+"-01"));
				}
				
				allDaysLeft+=temp.getHolDaysLeft();
			}
		}
		servletRequest.setAttribute("allDaysLeft", allDaysLeft);
		
		List<HolConfig> holConfigList = holConfigService.findAll();
		if(holConfigList!=null){
			String allDays = "";
			for(int i=0,length=holConfigList.size(); i<length; i++){
				allDays +=holConfigList.get(i).getHyear()+"-"+holConfigList.get(i).getHmonth()+"-"+holConfigList.get(i).getHday()+",";
			}
			servletRequest.setAttribute("days", allDays);
		}
		
		List<HolHoliday> holHolidayList = holHolidayService.findByYearAndHolPersonId(thisYear+"", userId);
		if(holHolidayList!=null && holHolidayList.size()==1){
			servletRequest.setAttribute("holHoliday", holHolidayList.get(0));
		}
		
		Properties properties = PropertiesUtils.load("config.properties");
		String token = CookieUtils.getCookieValue(servletRequest, "token");
		String secret = properties.getProperty("secret");
		String appName = properties.getProperty("appName");
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		String dataParams =          "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptId + "</id></params>";
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getDeptLeaders",secret,"xml",dataParams);	
		Document document = DocumentHelper.parseText(usePersonXMl);
		Element rootElem = document.getRootElement();
		Node datas = rootElem.selectSingleNode("params");
		List<Node> dataList = datas.selectNodes("param");
		List<User> leaders = new ArrayList<User>();
		
		boolean showStatus = false;
		if(dataList!=null && dataList.size()>0){
			for(int i=0,length=dataList.size(); i<length; i++){
				User u = new User();
				u.setId(Long.valueOf(dataList.get(i).selectSingleNode("id").getText()));
				u.setName(dataList.get(i).selectSingleNode("name").getText());
				leaders.add(u);
				if((u.getId()+"").equals(userId)){
					showStatus = true;
				}
			}
		}
		servletRequest.setAttribute("leaders", leaders);
		servletRequest.setAttribute("showStatus", showStatus);
		
		
		
		return SUCCESS;
	}
	
	
	public String saveHolidaysApply() throws IOException, DocumentException{
		String leaderId = servletRequest.getParameter("leaderId");
		String leaderName = servletRequest.getParameter("leaderName");
		String holComment = servletRequest.getParameter("holComment");
		String holDaysArray = servletRequest.getParameter("holDaysArray");
		String holState = servletRequest.getParameter("holState");
        String noticeNameList = servletRequest.getParameter("noticeNameList");
        String noticeIdList = servletRequest.getParameter("noticeIdList");
		int applyDays = 0;
		HolHolidayApply holHolidayApply = new HolHolidayApply();
		
		
		String userId = CookieUtils.getCookieValue(servletRequest, "userId");
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		
		if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(deptId)){
			holHolidayApply.setApplyUser(Long.valueOf(userId));
			holHolidayApply.setHolDeptId(deptId);
		}
		String startDate = "";
		String[] holDays=null;
		if(StringUtils.isNotEmpty(holDaysArray)){
			holDays = holDaysArray.split(","); 
			applyDays = holDays.length;
		}
		
		
		if(holDays!=null && holDays.length>0){
			holHolidayApply.setHolDays((long)holDays.length);
		}
		
		holHolidayApply.setCheckUser(Long.valueOf(leaderId));
		holHolidayApply.setHolComment(holComment);
        if(StringUtils.isNotBlank(noticeIdList))
        holHolidayApply.setNoticeIdList(noticeIdList);
        if(StringUtils.isNotBlank(noticeNameList))
        holHolidayApply.setNoticeNameList(noticeNameList);
		
		//holHolidayApply.setHolState(0);		//状态不明
		holHolidayApply.setRemoved(0l);
		holHolidayApply.setStartDate(holDaysArray);
		holHolidayApply.setHolState(Long.valueOf(holState));
		holHolidayApply.setApplyDate(sdfTime.format(new Date()));
		
		String startYear ="";
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		int thisYearMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		
		Object[] settime =holHolidayService.findHolHolidaySettime();
		String overyear="";
		String overmonth="";
		if(settime!=null){
			overyear = settime[0].toString();		//超期年份
			overmonth = settime[1].toString();			//超期月份
			if(Integer.valueOf(thisYearMonth)>=Integer.valueOf(overmonth)){
				startYear = thisYear-Integer.valueOf(overyear)+1+""; 
			}else{
				startYear = thisYear-Integer.valueOf(overyear)+"";
			}
			if(overmonth.length()==1){
				overmonth ="0"+overmonth;
			}
			String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
			servletRequest.setAttribute("thisYearEndDate", thisYearEndDate);
		}
		List<HolHoliday> list = holHolidayService.findByStartYearAndHolPersonId(startYear, userId);//查询个人当前工号所有公休日期
		List<HolHoliday> saveHolHolList = new ArrayList<HolHoliday>();
		if(list!=null && list.size()>0){
			for(int i=list.size()-1; i>=0; i--){
				HolHoliday temp = list.get(i);
				//****************************************************************************
				List<HolHoliday> otherAccountList = holHolidayService.findOtherAccountByHolId(temp.getHolId(),temp.getHolYear());		//相同年份下的其他账号下单公休假期
				if(temp.getHolDaysLeft()>=applyDays){//上一年可用公休大于申请公休天数
					temp.setHolDaysLeft(temp.getHolDaysLeft()-applyDays);
					if(otherAccountList!=null && otherAccountList.size()>0){
						for(int m=0;m<otherAccountList.size();m++){
							HolHoliday temp2 = otherAccountList.get(m);
							temp2.setHolDaysLeft(temp.getHolDaysLeft());
							if(!temp2.getId().equals(temp.getId())){
								saveHolHolList.add(temp2);//将记录添加到list中
							}
						}
					}
					saveHolHolList.add(temp);//将记录添加到list中
					break;
				}else{
					applyDays = (int)(applyDays-temp.getHolDaysLeft());
					temp.setHolDaysLeft(0l);
					if(otherAccountList!=null && otherAccountList.size()>0){
						for(int m=0;m<otherAccountList.size();m++){
							HolHoliday temp2 = otherAccountList.get(m);
							temp2.setHolDaysLeft(temp.getHolDaysLeft());
							if(!temp2.getId().equals(temp.getId())){
								saveHolHolList.add(temp2);//将记录添加到list中
							}
						}
					}
					
					saveHolHolList.add(temp);
				}
			}
		}
		
		//保存
		boolean st = holHolidayApplyService.saveHolHolidayApplyAndUpdateHolHoliday(holHolidayApply,saveHolHolList);
		
		//最后需开打，863本地不能用
		CsUser leader = holHolidayApplyService.findCsUserById(Long.valueOf(leaderId));
		Map p = this.getTaskParam(holHolidayApply.getId()+"",leader.getLoginName());
		
		JSONObject jsonObject = JSONObject.fromObject(p);
        String pJson = jsonObject.toString();
        String result = HttpRequestHelper.portalService(pJson, PropertiesUtils.getPropertyValue("todoAdd"));
        if("".equals(result) || "error".equals(result)){
            LOG.error("Failed to add task with param " + pJson);
        }else{
        	holHolidayApply.setTodoId(result);
            holHolidayApplyService.updateHolHolidayApply(holHolidayApply);
        }
		
		return SUCCESS;
	}
	
	//同意
	public void submitApply() throws IOException{//通过id和成功标志
		String id = servletRequest.getParameter("id");
		String holState = servletRequest.getParameter("holState");
		holHolidayApply = holHolidayApplyService.findHolHolidayApplyById(id);
		if(StringUtils.isNotEmpty(holState)){
			holHolidayApply.setHolState(Long.valueOf(holState));
			if("60".equals(holState) || "61".equals(holState)){		//60:驳回,61:申请取消
				String startYear ="";
				int thisYear = Calendar.getInstance().get(Calendar.YEAR);
				int thisYearMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
				
				Object[] settime =holHolidayService.findHolHolidaySettime();//超期
				String overyear="";
				String overmonth="";
				if(settime!=null){
					overyear = settime[0].toString();		//超期年份
					overmonth = settime[1].toString();			//超期月份
					if(Integer.valueOf(thisYearMonth)>=Integer.valueOf(overmonth)){
						startYear = thisYear-Integer.valueOf(overyear)+1+""; 
					}else{
						startYear = thisYear-Integer.valueOf(overyear)+"";
					}
					if(overmonth.length()==1){
						overmonth ="0"+overmonth;
					}
					String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
				}
				List<HolHoliday> list = holHolidayService.findByStartYearAndHolPersonId(startYear, holHolidayApply.getApplyUser()+"");//查询当前工号的所有公休
				long applyDays = holHolidayApply.getHolDays();//
				List<HolHoliday> saveHolHolList = new ArrayList<HolHoliday>();
				if(list!=null && list.size()>0){
					/***************/
					for(int i=0;i<list.size();i++){
						if(applyDays<=0)break;//
						HolHoliday temp = list.get(i);
						List<HolHoliday> otherAccountList = holHolidayService.findOtherAccountByHolId(temp.getHolId(),temp.getHolYear());		//相同年份下的其他账号下单公休假期
						if(otherAccountList!=null && otherAccountList.size()>0){//当前年份的可用公休天数+申请天数《=15则只在当前年份将申请天数驳回
							long app1=otherAccountList.get(0).getHolDaysLeft();//可用
							for(int m=0;m<otherAccountList.size();m++){
								HolHoliday temp2 = otherAccountList.get(m);
								temp2.setHolDaysLeft(temp2.getHolDaysLeft()+applyDays>temp2.getHolDays()? temp2.getHolDays():temp2.getHolDaysLeft()+applyDays);
								saveHolHolList.add(temp2);
							}
							long app2=otherAccountList.get(0).getHolDays();//当年总数
							applyDays=(long)(app1+applyDays-app2>0?(app1+applyDays-app2):0);
						}
					}
					/**************/
					/*for(int i=list.size()-1; i>=0; i--){
						HolHoliday temp = list.get(i);
						List<HolHoliday> otherAccountList = holHolidayService.findOtherAccountByHolId(temp.getHolId(),temp.getHolYear());		//相同年份下的其他账号下单公休假期
						if(temp.getHolDaysLeft()>=holHolidayApply.getHolDays()){
							if(temp.getHolDays()-temp.getHolDaysLeft()-applyDays>=0){
								temp.setHolDaysLeft(temp.getHolDaysLeft()+applyDays);
								if(otherAccountList!=null && otherAccountList.size()>0){
									for(int m=0;m<otherAccountList.size();m++){
										HolHoliday temp2 = otherAccountList.get(m);
										temp2.setHolDaysLeft(temp.getHolDaysLeft());
										if(!temp2.getId().equals(temp.getId())){
											saveHolHolList.add(temp2);
										}
									}
								}
								saveHolHolList.add(temp);
								break;
							}
						}else{
							applyDays = (int)(applyDays-temp.getHolDaysLeft());
							temp.setHolDaysLeft(temp.getHolDays());
							saveHolHolList.add(temp);
							if(otherAccountList!=null && otherAccountList.size()>0){
								for(int m=0;m<otherAccountList.size();m++){
									HolHoliday temp2 = otherAccountList.get(m);
									temp2.setHolDaysLeft(temp.getHolDaysLeft());
									if(!temp2.getId().equals(temp.getId())){
										saveHolHolList.add(temp2);
									}
								}
							}
							if(applyDays<=0){
								break;
							}
						}
					}*/
				}
				//保存
				boolean st = holHolidayApplyService.saveHolHolidayApplyAndUpdateHolHoliday(holHolidayApply,saveHolHolList);
				
				
			}else{
				holHolidayApplyService.updateHolHolidayApply(holHolidayApply);
                //发送通知和短信
                CsUser sender = holHolidayApplyService.findCsUserById(holHolidayApply.getApplyUser());
                List<CsUser> noticeList = holHolidayApplyService.findCsUserByIds(holHolidayApply.getNoticeIdList());
                System.out.println("================================="+noticeList.size());
                String strIsNeedSendShortMessageFlag = servletRequest.getParameter("isNeedSendShortMessage");
                if(StringUtils.isNotBlank(strIsNeedSendShortMessageFlag)&&strIsNeedSendShortMessageFlag.equals("0")){
                    msgUsermessageService.sendApplyHolidayNoticeMsg(sender, noticeList,holHolidayApply.getStartDate(),holHolidayApply.getId(), false);
                }else{
                    msgUsermessageService.sendApplyHolidayNoticeMsg(sender, noticeList,holHolidayApply.getStartDate(),holHolidayApply.getId(), true);
                }
                System.out.println("==============发送成功==================="+noticeList.size());
			}
			
			if(holHolidayApply.getTodoId()!=null  && (!"55".equals(holState) && !"58".equals(holState))){
				HttpRequestHelper.portalService(holHolidayApply.getTodoId(), PropertiesUtils.load("todo.properties").getProperty("todoUpdate"));
			}
		}
	}
	
	//审批
	public String showApplyView() throws IOException, DocumentException{
		String id = servletRequest.getParameter("id");
		HolHolidayApply holHolidayApply= holHolidayApplyService.findHolHolidayApplyById(id);
		
		CsUser csUser = holHolidayApplyService.findCsUserById(holHolidayApply.getApplyUser());
		servletRequest.setAttribute("csUser", csUser);
		
		
		String startYear ="";
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		int thisYearMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		
		Object[] settime =holHolidayService.findHolHolidaySettime();
		String overyear="";
		String overmonth="";
		if(settime!=null){
			overyear = settime[0].toString();		//超期年份
			overmonth = settime[1].toString();			//超期月份
			if(Integer.valueOf(thisYearMonth)>=Integer.valueOf(overmonth)){
				startYear = thisYear-Integer.valueOf(overyear)+1+""; 
			}else{
				startYear = thisYear-Integer.valueOf(overyear)+"";
			}
			if(overmonth.length()==1){
				overmonth ="0"+overmonth;
			}
			String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
			servletRequest.setAttribute("thisYearEndDate", thisYearEndDate);
		}
		String thisYearEndDate = thisYear+"-"+overmonth+"-01";			//今年超期日期
		String nextYearEndDate="";
		long allDaysLeft=0l;
		List<HolHoliday> list = holHolidayService.findByStartYearAndHolPersonId(startYear, csUser.getId()+"");
		if(list!=null && list.size()>0){
			for(int i=0,length=list.size(); i<length; i++){
				HolHoliday temp = list.get(i);
				if(i==0){
					servletRequest.setAttribute("thisYearHolHoliday", list.get(i));
				}
				if((thisYear-1)== Integer.valueOf(temp.getHolYear())){			//如果是去年的数据
					servletRequest.setAttribute("lastYearCount", temp.getHolDaysLeft());
					servletRequest.setAttribute("lastYearEndDate", (Integer.valueOf(temp.getHolYear())+Integer.valueOf(overyear)+"-"+overmonth+"-01"));
				}
				if((thisYear-2)==Integer.valueOf(temp.getHolYear())){			//如果是前年的数据
					servletRequest.setAttribute("yearBeforeLastYearCount", temp.getHolDaysLeft());
					servletRequest.setAttribute("yearBeforeLastYearEndDate", (Integer.valueOf(temp.getHolYear())+Integer.valueOf(overyear)+"-"+overmonth+"-01"));
				}
				allDaysLeft+=list.get(i).getHolDaysLeft();
			}
		}
		
		servletRequest.setAttribute("allDaysLeft", allDaysLeft);
		servletRequest.setAttribute("holHolidayApply", holHolidayApply);
		
		String showStatus = "no";
		Properties properties = PropertiesUtils.load("config.properties");
		String token = CookieUtils.getCookieValue(servletRequest, "token");
		String secret = properties.getProperty("secret");
		String appName = properties.getProperty("appName");
		String deptId = CookieUtils.getCookieValue(servletRequest, "deptId");
		String userId = CookieUtils.getCookieValue(servletRequest, "userId");
		String dataParams =          "<?xml version=\"1.0\" encoding=\"utf-8\"?><params><id>" + deptId + "</id></params>";
		String usePersonXMl = getUserInfoThroughCA(appName,token,"getDeptLeaders",secret,"xml",dataParams);	
		Document document = DocumentHelper.parseText(usePersonXMl);
		Element rootElem = document.getRootElement();
		Node datas = rootElem.selectSingleNode("params");
		List<Node> dataList = datas.selectNodes("param");
		List<User> leaders = new ArrayList<User>();
		
		if(dataList!=null && dataList.size()>0){
			for(int i=0,length=dataList.size(); i<length; i++){
				if(dataList.get(i).selectSingleNode("id").getText().toString().equals(userId)){
					showStatus = "yes";
					servletRequest.setAttribute("showStatus", showStatus);
					break;
				}
			}
		}
		
		
		return SUCCESS;
	}
	
	
	 /**
     * 调用待办接口传递参数
     * @param threadId
     * @return
	 * @throws IOException 
     */
    private Map getTaskParam(String applyId,String userId) {
        Map param = null;
        if(applyId != null && userId != null){
            param = new HashMap();
            param.put("app", "holiday");
            param.put("type", "1");
            param.put("occurTime", sdfTime.format(new Date()));
            param.put("title", "公休审核");
            param.put("loginName", "ST/"+userId);
            param.put("status", "0");
            param.put("removed", "0");
            param.put("typename", "公休审核");
            String url;
			try {
				url = PropertiesUtils.load("todo.properties").getProperty("holidayApprove")+applyId;
		        param.put("url", url);
			} catch (IOException e) {
				e.printStackTrace();
			}
            param.put("pname", "公休审核");
            param.put("pincident", "1");
            param.put("cname", "公休审核");
            param.put("cincident", "1");
            param.put("stepName", "公休审核");
            param.put("initiator", "ST/"+userId);
        }
        return param;
    }
	
	// 调用ca接口获得用户信息xml格式
	public String getUserInfoThroughCA(String appName, String token, String method, String secret, String dataType, String dataParams) {

		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";

		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();

		Properties properties2 = new Properties();
		try {
			properties2.load(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}

		String serverUrl = properties2.getProperty("urlCa").toString() + properties2.getProperty("serverPath").toString();

		try {
			url = new URL(serverUrl + "/dataExchange");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + token + "&method=" + method + "&dataType=" + dataType + "&dataParams=" + dataParams + "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
			osw.write(param);
			osw.flush();
			osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
			} else {
				return "没有通过用户认证";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (http != null)
				http.disconnect();
		}
		return textEntity;
	}

    public String choosePerson(){
        return SUCCESS;
    }

    public String loadPersonMenu() throws IOException {

        String parentId = PageUtils.GetParameter("id");
        List result = new ArrayList();
        List childNodeList = new ArrayList();
        if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")) {
            OrganNode parentNode= GlobalFuncNew.GetNodeById(parentId);

            if(parentNode.getOrganNodeType()!= null &&  parentNode.getOrganNodeType().getPeople()==1){

                List<UserAndOrder> list = GlobalFuncNew.getUsesByOrgNode(parentId);
                for(int i =0;i<list.size();i++){
                    UserAndOrder element = list.get(i);

                    //String userId = element.get(NodeUserDAO.SECURITY_SUER_ID).toString();
                    String order = element.getOrders();
                    //SecurityUser menuInfo = userService.loadUserById(Long.parseLong(userId));
                    SecurityUser menuInfo = element.getUser();
                    menuInfo.setOrders(Long.parseLong(order));

                    UserViewModel menuViewModel = new UserViewModel();
//					SecurityUser menuInfo = (SecurityUser) iter.next();
                    BeanUtils.copyProperties(menuInfo, menuViewModel, new String[]{"sex"});
                    menuViewModel.setLeaf(true);
                    menuViewModel.setName(URLEncoder.encode(menuViewModel.getName(), "UTF-8"));
                    result.add(menuViewModel);
                }
                HttpServletResponse response = PageUtils.getResponse();
                response.getWriter().print(MixPersonReturnString(result));
                return AJAX;
            }
            else
            {
                //OrganTree tree=organTreeService.getOrganTreeByCode(CODE_OF_ROOT_TREE);
                OrganTree tree = GlobalFuncNew.getOrganTreeByCode(CODE_OF_ROOT_TREE);

                //OrganNode node=organNodeService.loadOrganNodeById(Long.valueOf(parentId).longValue());
                OrganNode node=GlobalFuncNew.GetNodeById(parentId);
                //OrganNode[] chilrenNodes = organNodeService.getChildNodes(node,tree);
                OrganNode[] chilrenNodes  = GlobalFuncNew.getOrganNodesByParentAndTree( tree.getId(),node.getId());
                if(chilrenNodes !=null){
                    childNodeList= Arrays.asList(chilrenNodes);
                }

            }
        }
        else {

            //OrganNode rootNode = organNodeService.loadOrganNodeByCode(CODE_OF_ROOT_NODE);
            OrganNode rootNode  = GlobalFuncNew.GetNodeByCode(CODE_OF_ROOT_TREE);
            childNodeList.add(rootNode );
        }
        for(int i =0;i<childNodeList.size();i++){

            try{
                RadioOrganViewModel menuViewModel = new RadioOrganViewModel();

                OrganNode menuInfo = (OrganNode)childNodeList.get(i);
                if(menuInfo !=null){
                    BeanUtils.copyProperties(menuInfo, menuViewModel, new String[] { "icon" });
                    menuInfo.setName(URLEncoder.encode(this.getShortOrgName(menuInfo.getName()),"UTF-8"));
                    result.add(menuInfo);
                }


            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        HttpServletResponse response = PageUtils.getResponse();
        response.getWriter().print(MixDeptReturnString(result));
        return AJAX;
    }


	
	// 得到MD5加密后的字符串
	public static String getMD5(String value) {
		String result = null;
		try {
			byte[] valueByte = value.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(valueByte);
			result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return result;
	}
	
	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

	
	public void setHolidayData(){
		holHolidayApplyService.setHolidayData(); 
	}

    public String MixPersonReturnString(List result){
        String returnText = "";

        for(int i=0;i<result.size();i++){
            UserViewModel node = (UserViewModel)result.get(i);

            returnText += node.getId() + ",";
            returnText += node.getName() + ",1!";

        }
        if(!"".equals(returnText )){
            returnText = returnText.substring(0,returnText.length()-1);
        }


        return returnText.trim();
    }


    public  String getShortOrgName(String name){
        if (name==null || name.equals("")){
            return name;
        }
        Pattern pattern = Pattern.compile("[0-9][^0-9]");
        String[] strs = pattern.split(name);
        if (strs.length > 1) {
            strs[0] = name.substring(0, name.indexOf(name.replaceFirst(strs[0], "")) + 1);
            strs[1] = name.replaceFirst(strs[0], "");
            return strs[1];
        }
        else {
            return name;
        }

    }

    public String MixDeptReturnString(List result){
        String returnText = "";

        for(int i=0;i<result.size();i++){
            OrganNode node = (OrganNode)result.get(i);

            returnText += node.getId() + ",";
            returnText += node.getName() + ",0!";
        }
        if(returnText.length()>0){
            returnText = returnText.substring(0,returnText.length()-1);
        }


        return returnText.trim();
    }


}
	
