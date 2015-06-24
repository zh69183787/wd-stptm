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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.wonders.stpt.CrossIpLogin;
import com.wonders.stpt.UserInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.csUser.service.CsUserService;
import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wonders.stpt.supplier.service.SupplierService;
import com.wonders.stpt.task.entity.bo.AssetTask;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wonders.stpt.task.service.AssetTaskCheckService;
import com.wonders.stpt.task.service.AssetTaskService;
import com.wonders.stpt.user.entity.bo.User;
import com.wonders.stpt.user.service.UserService;
import com.wonders.stpt.util.AnalyseXml;
import com.wonders.stpt.util.DownLoadZipFiles;
import com.wonders.stpt.util.StringUtil;
import com.wonders.stpt.util.TxtCut;
import com.wonders.stpt.util.ZipFileUtil;
import com.wonders.stpt.util.GenerateTXT;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetTaskAction extends BaseAjaxAction {
	private AssetTask assetTask = new AssetTask();
	private AssetTaskService assetTaskService;
	private AssetInfoService assetInfoService;
	private AssetTaskCheckService assetTaskCheckService;
	private SupplierService supplierService;
	private UserService userService;
	private CsUserService csUserService;

	private static Properties properties = new Properties();
	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat sdfToDate = new SimpleDateFormat(
			"yyyy-MM-dd");

	private File file;
	private String fileFileName;
	private static final int BUFFER_SIZE = 20 * 1024; // 20K
	private final long UNIT = 13499l;		//权属单位，使用单位,codeInfoId
	private final long LINE_STATION_ID = 1120l;		//线路，车站


	/**
	 * @return the csUserService
	 */
	public CsUserService getCsUserService() {
		return csUserService;
	}

	/**
	 * @param csUserService the csUserService to set
	 */
	public void setCsUserService(CsUserService csUserService) {
		this.csUserService = csUserService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	public SupplierService getSupplierService() {
		return supplierService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	private final int size = 20;

	public AssetTaskAction() {
		initProperties();
	}

	@Override
	public Object getModel() {
		return assetTask;
	}

	public void setAssetInfoService(AssetInfoService assetInfoService) {
		this.assetInfoService = assetInfoService;
	}

	public void setAssetTaskCheckService(
			AssetTaskCheckService assetTaskCheckService) {
		this.assetTaskCheckService = assetTaskCheckService;
	}

	public AssetTask getAssetTask() {
		return assetTask;
	}

	public void setAssetTask(AssetTask assetTask) {
		this.assetTask = assetTask;
	}

	public void setAssetTaskService(AssetTaskService assetTaskService) {
		this.assetTaskService = assetTaskService;
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
	 * 显示list页面,普通权限，无法新增任务
	 * 
	 * @throws Exception
	 */
	public String showListCommon() throws Exception {

		servletRequest.setAttribute("today", sdfToDate.format(new Date()));

		String pageNo = servletRequest.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}

		CrossIpLogin crossIpLogin = new CrossIpLogin();
		UserInfo userInfo = new UserInfo();
		crossIpLogin.setUserInfo(servletRequest, userInfo);

		String userId = userInfo.getUserId();
		servletRequest.setAttribute("userId", userId);

		String start1 = ServletActionContext.getRequest()
				.getParameter("start1");
		String start2 = ServletActionContext.getRequest()
				.getParameter("start2");
		String end1 = ServletActionContext.getRequest().getParameter("end1");
		String end2 = ServletActionContext.getRequest().getParameter("end2");

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.assetTask);
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.assetTask, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if (start1 != null && !"".equals(start1.trim()))
			filter.put("start1", start1);
		if (start2 != null && !"".equals(start2.trim()))
			filter.put("start2", start2);
		if (end1 != null && !"".equals(end1.trim()))
			filter.put("end1", end1);
		if (end2 != null && !"".equals(end2.trim()))
			filter.put("end2", end2);

		if (userId != null) {
			//filter.put("checkpersonlist", userId);
			//filter.put("taskuser", userId);
		}

		ServletActionContext.getRequest().setAttribute("start1", start1);
		ServletActionContext.getRequest().setAttribute("start2", start2);
		ServletActionContext.getRequest().setAttribute("end1", end1);
		ServletActionContext.getRequest().setAttribute("end2", end2);

		Page page = assetTaskService.findAssetTaskByPage(filter, Integer
				.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		List<String> finishRate = new ArrayList<String>();
		List<AssetTask> taskList;
		if (page != null && page.getResult().size() > 0) {
			taskList = page.getResult();

			for (int i = 0; i < taskList.size(); i++) {
				Page page2 = assetInfoService.findAssetInfoByPage(
						getAssetInfoFilterByString(taskList.get(i)
								.getTaskmemoFilter()), 1, size);
				int allCount = page2.getTotalSize();
				int finishCount = assetTaskCheckService
						.findSumOfTaskCheckByTaskId(taskList.get(i).getId());
				finishRate.add(getPercentage(allCount, finishCount));
			}
		}

		ServletActionContext.getRequest()
				.setAttribute("finishRate", finishRate);

		String showOrHide = ServletActionContext.getRequest().getParameter(
				"showOrHide");
		if (showOrHide == null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest()
				.setAttribute("showOrHide", showOrHide);

		return "showListCommon";
	}

	/**
	 * 显示list页面，可添加任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showListManage() throws Exception {

		showListCommon();

		return "showListManage";
	}

	/**
	 * 显示add页面
	 */
	public String showAdd() {
		String filterCondition = ServletActionContext.getRequest()
				.getParameter("filterCondition");

		String pageNo = servletRequest.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}

		Map<String, Object> filter = new TreeMap<String, Object>();
		String type1 = servletRequest.getParameter("type1"); // 得到的是id,需要查CfCodeInfo
		if (type1 == null || type1.equals("-1"))
			type1 = null;
		String type2 = servletRequest.getParameter("type2"); // 得到的是id,需要查CfCodeInfo
		if (type2 == null || type2.equals("-1"))
			type2 = null;
		String type3 = servletRequest.getParameter("type3"); // 得到的是id,需要查CfCodeInfo
		if (type3 == null || type3.equals("-1"))
			type3 = null;
		String routeNum = servletRequest.getParameter("routeNum"); // 得到的是id,需要查CfCodeInfo
		if (routeNum == null || routeNum.equals("-1"))
			routeNum = null;
		String stationNum = servletRequest.getParameter("stationNum"); // 得到的是id,需要查CfCodeInfo
		if (stationNum == null || stationNum.equals("-1"))
			stationNum = null;
		String ownerDuty = servletRequest.getParameter("ownerDuty"); // 得到的是id,需要查CfCodeInfo
		if (ownerDuty == null || ownerDuty.equals("-1"))
			ownerDuty = null;
		String useTime = servletRequest.getParameter("useTime");
		String stopuseTime = servletRequest.getParameter("stopuseTime");
		String userDuty = servletRequest.getParameter("userDuty"); // 得到的是id,需要查CfCodeInfo
		if (userDuty == null || userDuty.equals("-1"))
			userDuty = null;
		String owner = servletRequest.getParameter("owner");
		String usePerson = servletRequest.getParameter("usePerson");
		String maintainDep = servletRequest.getParameter("maintainDep"); // 得到的是id,需要查CfCodeInfo
		if (maintainDep == null || maintainDep.equals("-1"))
			maintainDep = null;

		filter = fillFilter("type1", type1, filter);
		filter = fillFilter("type2", type2, filter);
		filter = fillFilter("type3", type3, filter);
		filter = fillFilter("routeNum", routeNum, filter);
		filter = fillFilter("stationNum", stationNum, filter);
		filter = fillFilter("ownerDuty", ownerDuty, filter);
		filter = fillFilter("useTime", useTime, filter);
		filter = fillFilter("stopuseTime", stopuseTime, filter);
		filter = fillFilter("userDuty", userDuty, filter);
		filter = fillFilter("owner", owner, filter);
		filter = fillFilter("usePerson", usePerson, filter);
		filter = fillFilter("maintainDep", maintainDep, filter);
		filter.put("registry", "1");

		StringBuilder sb2 = new StringBuilder();
		if (filterCondition == null || "".equals(filterCondition.trim())) {
			if (filter.size() > 0) {
				Iterator<?> it = filter.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString();
					sb2.append(key).append("：").append(filter.get(key)).append(
							"；");
				}
			}
			filterCondition = sb2.toString();
		} else {
			String[] filterArray = filterCondition.split("；");
			if (filterArray.length > 0) {
				for (int i = 0; i < filterArray.length; i++) {
					String[] filterKeyAndValue = filterArray[i].split("：");
					filter.put(filterKeyAndValue[0], filterKeyAndValue[1]);
				}
			}
		}
		filter.put("useState", "scrap");

		StringBuilder sb = new StringBuilder();
		String searchCondition = ServletActionContext.getRequest()
				.getParameter("searchCondition");
		if (searchCondition == null || "".equals(searchCondition.trim())) {
			sb = combineSearchCondition("大类", type1 == null ? ""
					: assetInfoService.findCfCodeInfoById(Long.valueOf(type1))
							.getName(), sb);
			sb = combineSearchCondition("中类", type2 == null ? ""
					: assetInfoService.findCfCodeInfoById(Long.valueOf(type2))
							.getName(), sb);
			sb = combineSearchCondition("小类", type3 == null ? ""
					: assetInfoService.findCfCodeInfoById(Long.valueOf(type3))
							.getName(), sb);
			sb = combineSearchCondition("所属线路", routeNum == null ? ""
					: assetInfoService.findCfCodeInfoById(
							Long.valueOf(routeNum)).getName(), sb);
			sb = combineSearchCondition("所属车站", stationNum == null ? ""
					: assetInfoService.findCfCodeInfoById(
							Long.valueOf(stationNum)).getName(), sb);
			sb = combineSearchCondition("权属单位", ownerDuty == null ? ""
					: assetInfoService.findCfCodeInfoById(
							Long.valueOf(ownerDuty)).getName(), sb);
			sb = combineSearchCondition("开始使用日期", useTime, sb);
			sb = combineSearchCondition("停止使用日期", stopuseTime, sb);
			sb = combineSearchCondition("使用单位", userDuty == null ? ""
					: assetInfoService.findCfCodeInfoById(
							Long.valueOf(userDuty)).getName(), sb);
			sb = combineSearchCondition("权属责任人", owner, sb);
			sb = combineSearchCondition("使用责任人", usePerson, sb);
			sb = combineSearchCondition("维护部门", maintainDep == null ? ""
					: assetInfoService.findCfCodeInfoById(
							Long.valueOf(maintainDep)).getName(), sb);
			searchCondition = sb.toString();
		}

		ServletActionContext.getRequest().setAttribute("filterCondition",
				filterCondition);

		Page page = assetInfoService.findAssetInfoByPage(filter, Integer
				.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		ServletActionContext.getRequest().setAttribute("searchCondition",
				searchCondition);

		CrossIpLogin crossIpLogin = new CrossIpLogin();
		UserInfo userInfo = new UserInfo();
		crossIpLogin.setUserInfo(servletRequest, userInfo);

		String userId = userInfo.getUserId();
		servletRequest.setAttribute("userId", userId);

		return "showAdd";
	}

	/**
	 * 保存新增
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public String saveAdd() throws FileNotFoundException, IOException {

		// 如果需是控件输入的值，则需要判断输入的用户名是否存在，如存在，需要将用户id存入assetTask.setCheckPersonList
		/*
		 * HttpServletRequest request = getServletRequest();
		 * 
		 * String userName = servletRequest.getParameter("userName");
		 * 
		 * if(assetTask.getCheckpersonlist()==null ||
		 * "".equals(assetTask.getCheckpersonlist())){
		 * 
		 * String appName = request.getParameter("appName"); String token =
		 * request.getParameter("token"); String method =
		 * request.getParameter("method"); String secret =
		 * request.getParameter("secret"); String dataType = "xml"; String
		 * dataParams =
		 * "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><userName>"
		 * +userName.trim()+"</userName></dataParams>";
		 * 
		 * URL url = null; HttpURLConnection http = null; String textEntity="";
		 * String path =
		 * Thread.currentThread().getContextClassLoader().getResource
		 * ("config.properties").getPath();
		 * 
		 * Properties properties2 = new Properties(); properties2.load(new
		 * FileInputStream(path));
		 * 
		 * String serverUrl = properties2.getProperty("serverUrl").toString();
		 * 
		 * try { url = new URL(serverUrl+"/dataExchange"); http =
		 * (HttpURLConnection) url.openConnection(); http.setDoInput(true);
		 * http.setDoOutput(true); http.setUseCaches(false);
		 * http.setConnectTimeout(50000); http.setReadTimeout(50000);
		 * http.setRequestMethod("POST");
		 * //http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
		 * http.setRequestProperty("Content-Type",
		 * "application/x-www-form-urlencoded"); http.connect(); String param =
		 * "&appName=" + appName + "&token=" + token +
		 * "&method="+method+"&dataType="
		 * +dataType+"&dataParams="+dataParams+"&sign=" +
		 * getMD5(appName+token+method+secret);
		 * 
		 * OutputStreamWriter osw=new
		 * OutputStreamWriter(http.getOutputStream(),"utf-8"); osw.write(param);
		 * osw.flush(); osw.close();
		 * 
		 * if (http.getResponseCode() == 200) { BufferedReader in = new
		 * BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
		 * String inputLine; while ((inputLine = in.readLine()) != null) {
		 * textEntity += inputLine; }
		 * 
		 * Document document = DocumentHelper.parseText(textEntity); resultElem
		 * = document.getRootElement().element("result"); List<Element>
		 * codeElems = resultElem.selectNodes("code"); codeElem = null;
		 * if(codeElems!=null && codeElems.size()>0){ codeElem =
		 * codeElems.get(0);
		 * 
		 * String code = codeElem.getText(); if("100".equals(code)){
		 * //code=100,有数据
		 * 
		 * } } in.close(); System.out.println(textEntity);
		 * getServletResponse().setCharacterEncoding("utf-8");
		 * this.getServletResponse().getWriter().print(textEntity); return null;
		 * }else{ return "没有通过用户认证"; } }catch (Exception e) {
		 * e.printStackTrace(); } finally { if (http != null) http.disconnect();
		 * }
		 * 
		 * }
		 */

		assetTask.setOperateDate(sdf.format(new Date()));
		assetTask.setRemoved("0");
		assetTask.setTaskstatus("0");
		assetTaskService.saveAdd(assetTask);
		return "saveAdd";
	}

	/**
	 * 显示view页面
	 * 
	 * @throws Exception
	 */
	public String showView() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("id");
		assetTask = this.assetTaskService.findTaskById(id);

		String pageNo = servletRequest.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}

		Map<String, Object> filter = new HashMap<String, Object>();
		String filterCondition;
		if (assetTask != null) {
			filterCondition = assetTask.getTaskmemoFilter();
			filter = getAssetInfoFilterByString(filterCondition);
		}
		filter.put("useState", "scrap");
		// assetTask = assetTaskService.findTaskById(id);

		Page page = assetInfoService.findAssetInfoByPage(filter, Integer
				.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		return "showView";
	}

	/**
	 * 显示edit页面
	 */
	public String showEdit() {
		String id = servletRequest.getParameter("id");
		assetTask = assetTaskService.findTaskById(id);
		return "showEdit";
	}

	/**
	 * 保存修改
	 */
	public String saveEdit() {
		assetTaskService.updateTask(assetTask);
		return "saveEdit";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String id = servletRequest.getParameter("id");
		assetTaskService.deleteTaskById(id);
		return AJAX;
	}

	/**
	 * 显示资产条件搜索页面
	 */
	public String showAssetSearchPage() {

		String pageNo = servletRequest.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}
		AssetInfo assetInfo = new AssetInfo();

		Map<String, Object> filter = new TreeMap<String, Object>();
		String type1 = servletRequest.getParameter("type1"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setType1(type1);
		if (type1 == null || type1.equals("-1")) {
			type1 = null;
		}
		String type2 = servletRequest.getParameter("type2"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setType2(type2);
		if (type2 == null || type2.equals("-1"))
			type2 = null;
		String type3 = servletRequest.getParameter("type3"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setType3(type3);
		if (type3 == null || type3.equals("-1"))
			type3 = null;
		String routeNum = servletRequest.getParameter("routeNum"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setRouteNum(routeNum);
		if (routeNum == null || routeNum.equals("-1"))
			routeNum = null;
		String stationNum = servletRequest.getParameter("stationNum"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setStationNum(stationNum);
		if (stationNum == null || stationNum.equals("-1"))
			stationNum = null;
		String ownerDuty = servletRequest.getParameter("ownerDuty"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setOwnerDuty(ownerDuty);
		if (ownerDuty == null || ownerDuty.equals("-1"))
			ownerDuty = null;
		String useTime = servletRequest.getParameter("useTime");
		assetInfo.setUseTime(useTime);

		String stopuseTime = servletRequest.getParameter("stopuseTime");
		assetInfo.setStopuseTime(stopuseTime);

		String userDuty = servletRequest.getParameter("userDuty"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setUserDuty(userDuty);
		if (userDuty == null || userDuty.equals("-1"))
			userDuty = null;
		String owner = servletRequest.getParameter("owner");
		assetInfo.setOwner(owner);
		String usePerson = servletRequest.getParameter("usePerson");
		assetInfo.setUsePerson(usePerson);
		String maintainDep = servletRequest.getParameter("maintainDep"); // 得到的是id,需要查CfCodeInfo
		assetInfo.setMaintainDep(maintainDep);
		if (maintainDep == null || maintainDep.equals("-1"))
			maintainDep = null;

		ServletActionContext.getRequest().setAttribute("assetInfo", assetInfo);

		filter = fillFilter("type1", type1, filter);
		filter = fillFilter("type2", type2, filter);
		filter = fillFilter("type3", type3, filter);
		filter = fillFilter("routeNum", routeNum, filter);
		filter = fillFilter("stationNum", stationNum, filter);
		filter = fillFilter("ownerDuty", ownerDuty, filter);
		filter = fillFilter("useTime", useTime, filter);
		filter = fillFilter("stopuseTime", stopuseTime, filter);
		filter = fillFilter("userDuty", userDuty, filter);
		filter = fillFilter("owner", owner, filter);
		filter = fillFilter("usePerson", usePerson, filter);
		filter = fillFilter("maintainDep", maintainDep, filter);
		filter.put("registry", "1");
		filter.put("useState", "scrap");			//已报废的资产不盘点
		servletRequest.setAttribute("useState", "scrap");
		
		Page page = assetInfoService.findAssetInfoByPage(filter, Integer
				.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(Long
				.valueOf(properties.getProperty("TYPE1").toString()));
		servletRequest.setAttribute("type1List", type1List);

		if (type1 != null) {
			List<CfCodeInfo> type2List = assetInfoService
					.findCfInfoCodeById(Long.valueOf(type1));
			ServletActionContext.getRequest().setAttribute("type2List",
					type2List);
		}

		if (type2 != null) {
			List<CfCodeInfo> type3List = assetInfoService
					.findCfInfoCodeById(Long.valueOf(type2));
			ServletActionContext.getRequest().setAttribute("type3List",
					type3List);
		}

		if (routeNum != null) {
			List<CfCodeInfo> stationList = assetInfoService
					.findCfInfoCodeById(Long.valueOf(routeNum));
			ServletActionContext.getRequest().setAttribute("stationList",
					stationList);
		}

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(Long
				.valueOf(properties.getProperty("LINE_ID").toString()));
		servletRequest.setAttribute("lineList", lineList);

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(Long
				.valueOf(properties.getProperty("UNIT").toString()));
		servletRequest.setAttribute("unitList", unitList);

		List<CfCodeInfo> maintainDeptList = assetInfoService
				.findCfInfoCodeById(Long.valueOf(properties.getProperty(
						"MAINTAIN_DEPT").toString()));
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);

		String showOrHide = ServletActionContext.getRequest().getParameter(
				"showOrHide");
		if (showOrHide == null || "".equals(showOrHide))
			showOrHide = "show";
		ServletActionContext.getRequest()
				.setAttribute("showOrHide", showOrHide);

		return "showAssetSearchPage";
	}

	/**
	 * 将要盘点的资产转换成xml文件下载
	 * 
	 * @throws Exception
	 */
	public String downloadAssetTaskAsXML() throws Exception {
		String paramId = ServletActionContext.getRequest().getParameter(
				"paramId");
		// 字典项
		List<CfCodeInfo> allCodeInfoList = assetInfoService.findAllCfCodeInfo();
		Map<Long, CfCodeInfo> codeInfoMap = new HashMap<Long, CfCodeInfo>();
		if (allCodeInfoList != null && allCodeInfoList.size() > 0) {
			for (int i = 0; i < allCodeInfoList.size(); i++) {
				codeInfoMap.put(allCodeInfoList.get(i).getId(), allCodeInfoList
						.get(i));
			}
			allCodeInfoList = null;
		}
		// 厂商
		List<Supplier> allSupplierList = supplierService.findAllSupplier();
		Map<Long, Supplier> supplierMap = new HashMap<Long, Supplier>();
		if (allSupplierList != null && allSupplierList.size() > 0) {
			for (int i = 0; i < allSupplierList.size(); i++) {
				supplierMap.put(allSupplierList.get(i).getId(), allSupplierList
						.get(i));
			}
			allSupplierList = null;
		}

		AssetTask assetTask = this.assetTaskService.findTaskById(paramId);
		List<AssetInfo> assetInfoList = null;
		if (assetTask != null) {
			assetInfoList = assetInfoService
					.findAllAssetInfoByFilter(getAssetInfoFilterByString(assetTask
							.getTaskmemoFilter()));
		}

		if (assetInfoList != null && assetInfoList.size() > 0) {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("assets");
			Element taskId = root.addElement("taskId");
			taskId.setText(paramId);
			paramId = null;
			CfCodeInfo tempCodeInfo;

			// 定义dom节点
			Element assetElem, id, assetId, assetName, assetPic, benqizhijiu, bfcljlDbjl, bfcljlFsfy, bfcljlTime, bfcljlZrr, buildAddr, compactPrice, czl, cd, depreciationWay, discardTime, dxpl, equipmentState, finalAccountPrice, gxgzjlDbjl, gxgzjlFsfy, gxgzjlTime, gxgzjlZrr, handoverTime, jingzhi, jszlqd, leaveFactoryPrice, leijizhejiu, maintainDep, makeTime, manufacturer, memo, model, mpztbw, operateTime, operator, owner, ownerDuty, payTime, provideTime, purchaseTime, qtjlDbjl, qtjlFsfy, qtjlTime, qtjlZrr, routeNum, shebeiqingdan, shelfLife, sjsynx, specification, stationNum, stopuseTime, type1, type2, type3, userDuty, useLife, usePerson, useState, useTime, vendor, wxjlDbjl, wxjlFsfy, wxjlTime, wxjlZrr, xjpdjlDbjl, xjpdjlFsfy, xjpdjlTime, xjpdjlZrr, yuanzhi, yzjl, zcdbjlDbjl, zcdbjlFsfy, zcdbjlTime, zcdbjlZrr;

			StringBuilder sb = new StringBuilder();

			// Element tempElement = DocumentHelper.createElement("asset");
			for (int i = 0, len = assetInfoList.size(); i < len; i++) {
				assetElem = root.addElement("asset");
				// assetElem = tempElement;
				id = assetElem.addElement("id");
				id.setText(assetInfoList.get(i).getId() == null ? ""
						: assetInfoList.get(i).getId().toString());
				id = null;
				assetId = assetElem.addElement("assetId");
				assetId.setText(assetInfoList.get(i).getAssetId() == null ? ""
						: assetInfoList.get(i).getAssetId());
				assetId = null;
				assetName = assetElem.addElement("name");
				assetName
						.setText(assetInfoList.get(i).getAssetName() == null ? ""
								: assetInfoList.get(i).getAssetName());
				assetName = null;
				assetPic = assetElem.addElement("assetPic");
				assetPic
						.setText(assetInfoList.get(i).getAssetPic() == null ? ""
								: assetInfoList.get(i).getAssetPic());
				assetPic = null;
				benqizhijiu = assetElem.addElement("benqizhijiu");
				benqizhijiu
						.setText(assetInfoList.get(i).getBenqizhijiu() == null ? ""
								: assetInfoList.get(i).getBenqizhijiu());
				benqizhijiu = null;
				bfcljlDbjl = assetElem.addElement("bfcljlDbjl");
				bfcljlDbjl
						.setText(assetInfoList.get(i).getBfcljlDbjl() == null ? ""
								: assetInfoList.get(i).getBfcljlDbjl());
				bfcljlDbjl = null;
				bfcljlFsfy = assetElem.addElement("bfcljlFsfy");
				bfcljlFsfy
						.setText(assetInfoList.get(i).getBfcljlFsfy() == null ? ""
								: assetInfoList.get(i).getBfcljlFsfy());
				bfcljlFsfy = null;
				bfcljlTime = assetElem.addElement("bfcljlTime");
				bfcljlTime
						.setText(assetInfoList.get(i).getBfcljlTime() == null ? ""
								: assetInfoList.get(i).getBfcljlTime());
				bfcljlTime = null;
				bfcljlZrr = assetElem.addElement("bfcljlZrr");
				bfcljlZrr
						.setText(assetInfoList.get(i).getBfcljlZrr() == null ? ""
								: assetInfoList.get(i).getBfcljlZrr());
				bfcljlZrr = null;
				buildAddr = assetElem.addElement("buildAddr");
				buildAddr
						.setText(assetInfoList.get(i).getBuildAddr() == null ? ""
								: assetInfoList.get(i).getBuildAddr());
				buildAddr = null;
				compactPrice = assetElem.addElement("compactPrice");
				compactPrice
						.setText(assetInfoList.get(i).getCompactPrice() == null ? ""
								: assetInfoList.get(i).getCompactPrice());
				compactPrice = null;
				czl = assetElem.addElement("czl");
				czl.setText(assetInfoList.get(i).getCzl() == null ? ""
						: assetInfoList.get(i).getCzl());
				czl = null;
				cd = assetElem.addElement("cd");
				cd.setText(assetInfoList.get(i).getCd() == null ? ""
						: assetInfoList.get(i).getCd());
				cd = null;
				depreciationWay = assetElem.addElement("depreciationWay");
				if (assetInfoList.get(i).getDepreciationWay() != null
						&& !""
								.equals(assetInfoList.get(i)
										.getDepreciationWay())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getDepreciationWay()));
					if (tempCodeInfo != null) {
						depreciationWay.setText(tempCodeInfo.getName());
					}
				} else {
					depreciationWay.setText("");
				}
				depreciationWay = null;
				discardTime = assetElem.addElement("discardTime");
				discardTime
						.setText(assetInfoList.get(i).getDiscardTime() == null ? ""
								: assetInfoList.get(i).getDiscardTime());
				discardTime = null;
				dxpl = assetElem.addElement("dxpl");
				dxpl.setText(assetInfoList.get(i).getDxpl() == null ? ""
						: assetInfoList.get(i).getDxpl());
				dxpl = null;
				equipmentState = assetElem.addElement("equipmentState");
				if (assetInfoList.get(i).getEquipmentState() != null
						&& !"".equals(assetInfoList.get(i).getEquipmentState())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getEquipmentState()));
					if (tempCodeInfo != null) {
						equipmentState.setText(tempCodeInfo.getName());
					}
				} else {
					equipmentState.setText("");
				}
				equipmentState = null;
				finalAccountPrice = assetElem.addElement("finalAccountPrice");
				finalAccountPrice.setText(assetInfoList.get(i)
						.getFinalAccountPrice() == null ? "" : assetInfoList
						.get(i).getFinalAccountPrice());
				finalAccountPrice = null;
				gxgzjlDbjl = assetElem.addElement("gxgzjlDbjl");
				gxgzjlDbjl
						.setText(assetInfoList.get(i).getGxgzjlDbjl() == null ? ""
								: assetInfoList.get(i).getGxgzjlDbjl());
				gxgzjlDbjl = null;
				gxgzjlFsfy = assetElem.addElement("gxgzjlFsfy");
				gxgzjlFsfy
						.setText(assetInfoList.get(i).getGxgzjlFsfy() == null ? ""
								: assetInfoList.get(i).getGxgzjlFsfy());
				gxgzjlFsfy = null;
				gxgzjlTime = assetElem.addElement("gxgzjlTime");
				gxgzjlTime
						.setText(assetInfoList.get(i).getGxgzjlTime() == null ? ""
								: assetInfoList.get(i).getGxgzjlTime());
				gxgzjlTime = null;
				gxgzjlZrr = assetElem.addElement("gxgzjlZrr");
				gxgzjlZrr
						.setText(assetInfoList.get(i).getGxgzjlZrr() == null ? ""
								: assetInfoList.get(i).getGxgzjlZrr());
				gxgzjlZrr = null;
				handoverTime = assetElem.addElement("handoverTime");
				handoverTime
						.setText(assetInfoList.get(i).getHandoverTime() == null ? ""
								: assetInfoList.get(i).getHandoverTime());
				handoverTime = null;
				jingzhi = assetElem.addElement("jingzhi");
				jingzhi.setText(assetInfoList.get(i).getJingzhi() == null ? ""
						: assetInfoList.get(i).getJingzhi());
				jingzhi = null;
				jszlqd = assetElem.addElement("jszlqd");
				jszlqd.setText(assetInfoList.get(i).getJszlqd() == null ? ""
						: assetInfoList.get(i).getJszlqd());
				jszlqd = null;
				leaveFactoryPrice = assetElem.addElement("leaveFactoryPrice");
				leaveFactoryPrice.setText(assetInfoList.get(i)
						.getLeaveFactoryPrice() == null ? "" : assetInfoList
						.get(i).getLeaveFactoryPrice());
				leaveFactoryPrice = null;
				leijizhejiu = assetElem.addElement("leijizhejiu");
				leijizhejiu
						.setText(assetInfoList.get(i).getLeijizhejiu() == null ? ""
								: assetInfoList.get(i).getLeijizhejiu());
				leijizhejiu = null;
				maintainDep = assetElem.addElement("maintainDep");
				if (assetInfoList.get(i).getMaintainDep() != null
						&& !"".equals(assetInfoList.get(i).getMaintainDep())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getMaintainDep()));
					if (tempCodeInfo != null) {
						maintainDep.setText(tempCodeInfo.getName());
					}
				} else {
					maintainDep.setText("");
				}
				maintainDep = null;
				makeTime = assetElem.addElement("makeTime");
				makeTime
						.setText(assetInfoList.get(i).getMakeTime() == null ? ""
								: assetInfoList.get(i).getMakeTime());
				makeTime = null;
				manufacturer = assetElem.addElement("manufacturer");
				if (assetInfoList.get(i).getManufacturer() != null
						&& !"".equals(assetInfoList.get(i).getManufacturer())) {
					Supplier supplier = null;
					try {
						supplier = supplierMap.get(Long.valueOf(assetInfoList
								.get(i).getManufacturer()));
						if (supplier != null && !"".equals(supplier.getName())) {
							manufacturer.setText(supplier.getName());
						} else {
							manufacturer.setText("");
						}
					} catch (NumberFormatException e) {
						manufacturer.setText("");
					} finally {
						supplier = null;
					}

				} else {
					manufacturer.setText("");
				}
				manufacturer = null;
				memo = assetElem.addElement("memo");
				memo.setText(assetInfoList.get(i).getMemo() == null ? ""
						: assetInfoList.get(i).getMemo());
				memo = null;
				model = assetElem.addElement("model");
				model.setText(assetInfoList.get(i).getModel() == null ? ""
						: assetInfoList.get(i).getModel());
				model = null;
				mpztbw = assetElem.addElement("mpztbw");
				if (assetInfoList.get(i).getMpztbw() != null
						&& !"".equals(assetInfoList.get(i).getMpztbw())) {

					// CfCodeInfo cfCodeInfo =
					// assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoList.get(i).getMpztbw()));
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getMpztbw()));
					if (tempCodeInfo != null) {
						mpztbw.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					mpztbw.setText("");
				}
				mpztbw = null;
				operateTime = assetElem.addElement("operateTime");
				operateTime
						.setText(assetInfoList.get(i).getOperateTime() == null ? ""
								: assetInfoList.get(i).getOperateTime());
				operateTime = null;
				operator = assetElem.addElement("operator");
				operator
						.setText(assetInfoList.get(i).getOperator() == null ? ""
								: assetInfoList.get(i).getOperator());
				operator = null;
				owner = assetElem.addElement("owner");
				owner.setText(assetInfoList.get(i).getOwner() == null ? ""
						: assetInfoList.get(i).getOwner());
				owner = null;
				ownerDuty = assetElem.addElement("ownerDuty");
				if (assetInfoList.get(i).getOwnerDuty() != null
						&& !"".equals(assetInfoList.get(i).getOwnerDuty())) {
					// CfCodeInfo cfCodeInfo =
					// assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoList.get(i).getOwnerDuty()));
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getOwnerDuty()));
					if (tempCodeInfo != null) {
						ownerDuty.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					ownerDuty.setText("");
				}
				ownerDuty = null;
				payTime = assetElem.addElement("payTime");
				payTime.setText(assetInfoList.get(i).getPayTime() == null ? ""
						: assetInfoList.get(i).getPayTime());
				payTime = null;
				provideTime = assetElem.addElement("provideTime");
				provideTime
						.setText(assetInfoList.get(i).getProvideTime() == null ? ""
								: assetInfoList.get(i).getProvideTime());
				provideTime = null;
				purchaseTime = assetElem.addElement("purchaseTime");
				purchaseTime
						.setText(assetInfoList.get(i).getPurchaseTime() == null ? ""
								: assetInfoList.get(i).getPurchaseTime());
				purchaseTime = null;
				qtjlDbjl = assetElem.addElement("qtjlDbjl");
				qtjlDbjl
						.setText(assetInfoList.get(i).getQtjlDbjl() == null ? ""
								: assetInfoList.get(i).getQtjlDbjl());
				qtjlDbjl = null;
				qtjlFsfy = assetElem.addElement("qtjlFsfy");
				qtjlFsfy
						.setText(assetInfoList.get(i).getQtjlFsfy() == null ? ""
								: assetInfoList.get(i).getQtjlFsfy());
				qtjlFsfy = null;
				qtjlTime = assetElem.addElement("qtjlTime");
				qtjlTime
						.setText(assetInfoList.get(i).getQtjlTime() == null ? ""
								: assetInfoList.get(i).getQtjlTime());
				qtjlTime = null;
				qtjlZrr = assetElem.addElement("qtjlZrr");
				qtjlZrr.setText(assetInfoList.get(i).getQtjlZrr() == null ? ""
						: assetInfoList.get(i).getQtjlZrr());
				qtjlZrr = null;
				routeNum = assetElem.addElement("routeNum");
				if (assetInfoList.get(i).getRouteNum() != null
						&& !"".equals(assetInfoList.get(i).getRouteNum())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getRouteNum()));
					if (tempCodeInfo != null) {
						routeNum.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					routeNum.setText("");
				}
				routeNum = null;
				shebeiqingdan = assetElem.addElement("shebeiqingdan");
				shebeiqingdan
						.setText(assetInfoList.get(i).getShebeiqingdan() == null ? ""
								: assetInfoList.get(i).getShebeiqingdan());
				shebeiqingdan = null;
				shelfLife = assetElem.addElement("shelfLife");
				shelfLife
						.setText(assetInfoList.get(i).getShelfLife() == null ? ""
								: assetInfoList.get(i).getShelfLife());
				shelfLife = null;
				sjsynx = assetElem.addElement("sjsynx");
				sjsynx.setText(assetInfoList.get(i).getSjsynx() == null ? ""
						: assetInfoList.get(i).getSjsynx());
				sjsynx = null;
				specification = assetElem.addElement("specification");
				specification
						.setText(assetInfoList.get(i).getSpecification() == null ? ""
								: assetInfoList.get(i).getSpecification());
				specification = null;
				stationNum = assetElem.addElement("stationNum");
				if (assetInfoList.get(i).getStationNum() != null
						&& !"".equals(assetInfoList.get(i).getStationNum())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getStationNum()));
					if (tempCodeInfo != null) {
						stationNum.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					stationNum.setText("");
				}
				stationNum = null;
				stopuseTime = assetElem.addElement("stopuseTime");
				stopuseTime
						.setText(assetInfoList.get(i).getStopuseTime() == null ? ""
								: assetInfoList.get(i).getStopuseTime());
				stopuseTime = null;
				type1 = assetElem.addElement("type1");
				if (assetInfoList.get(i).getType1() != null
						&& !"".equals(assetInfoList.get(i).getType1())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType1()));
					if (tempCodeInfo != null) {
						type1.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type1.setText("");
				}
				type1 = null;
				type2 = assetElem.addElement("type2");
				if (assetInfoList.get(i).getType2() != null
						&& !"".equals(assetInfoList.get(i).getType2())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType2()));
					if (tempCodeInfo != null) {
						type2.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type2.setText("");
				}
				type2 = null;
				type3 = assetElem.addElement("type3");
				if (assetInfoList.get(i).getType3() != null
						&& !"".equals(assetInfoList.get(i).getType3())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType3()));
					if (tempCodeInfo != null) {
						type3.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type3.setText("");
				}
				type3 = null;
				userDuty = assetElem.addElement("userDuty");
				if (assetInfoList.get(i).getUserDuty() != null
						&& !"".equals(assetInfoList.get(i).getUserDuty())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getUserDuty()));
					if (tempCodeInfo != null) {
						userDuty.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					userDuty.setText("");
				}
				userDuty = null;
				useLife = assetElem.addElement("useLife");
				useLife.setText(assetInfoList.get(i).getUseLife() == null ? ""
						: assetInfoList.get(i).getUseLife());
				useLife = null;
				usePerson = assetElem.addElement("usePerson");
				usePerson
						.setText(assetInfoList.get(i).getUsePerson() == null ? ""
								: assetInfoList.get(i).getUsePerson());
				usePerson = null;
				useState = assetElem.addElement("useState");
				if (assetInfoList.get(i).getUseState() != null
						&& !"".equals(assetInfoList.get(i).getUseState())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getUseState()));
					if (tempCodeInfo != null) {
						useState.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					useState.setText("");
				}
				useState = null;
				useTime = assetElem.addElement("useTime");
				useTime.setText(assetInfoList.get(i).getUseTime() == null ? ""
						: assetInfoList.get(i).getUseTime());
				useTime = null;
				vendor = assetElem.addElement("vendor");
				if (assetInfoList.get(i).getVendor() != null
						&& !"".equals(assetInfoList.get(i).getVendor())) {
					Supplier supplier;
					try {
						supplier = supplierMap.get(Long.valueOf(assetInfoList
								.get(i).getVendor()));
						if (supplier != null && !"".equals(supplier.getName())) {
							vendor.setText(supplier.getName());
						} else {
							vendor.setText("");
						}
					} catch (NumberFormatException e) {
						vendor.setText("");
					} finally {
						supplier = null;
					}
				} else {
					vendor.setText("");
				}
				vendor = null;
				wxjlDbjl = assetElem.addElement("wxjlDbjl");
				wxjlDbjl
						.setText(assetInfoList.get(i).getWxjlDbjl() == null ? ""
								: assetInfoList.get(i).getWxjlDbjl());
				wxjlDbjl = null;
				wxjlFsfy = assetElem.addElement("wxjlFsfy");
				wxjlFsfy
						.setText(assetInfoList.get(i).getWxjlFsfy() == null ? ""
								: assetInfoList.get(i).getWxjlFsfy());
				wxjlFsfy = null;
				wxjlTime = assetElem.addElement("wxjlTime");
				wxjlTime
						.setText(assetInfoList.get(i).getWxjlTime() == null ? ""
								: assetInfoList.get(i).getWxjlTime());
				wxjlTime = null;
				wxjlZrr = assetElem.addElement("wxjlZrr");
				wxjlZrr.setText(assetInfoList.get(i).getWxjlZrr() == null ? ""
						: assetInfoList.get(i).getWxjlZrr());
				wxjlZrr = null;
				// xh = assetElem.addElement("xh");
				xjpdjlDbjl = assetElem.addElement("xjpdjlDbjl");
				xjpdjlDbjl
						.setText(assetInfoList.get(i).getXjpdjlDbjl() == null ? ""
								: assetInfoList.get(i).getXjpdjlDbjl());
				xjpdjlDbjl = null;
				xjpdjlFsfy = assetElem.addElement("xjpdjlFsfy");
				xjpdjlFsfy
						.setText(assetInfoList.get(i).getXjpdjlFsfy() == null ? ""
								: assetInfoList.get(i).getXjpdjlFsfy());
				xjpdjlFsfy = null;
				xjpdjlTime = assetElem.addElement("xjpdjlTime");
				xjpdjlTime
						.setText(assetInfoList.get(i).getXjpdjlTime() == null ? ""
								: assetInfoList.get(i).getXjpdjlTime());
				xjpdjlTime = null;
				xjpdjlZrr = assetElem.addElement("xjpdjlZrr");
				xjpdjlZrr
						.setText(assetInfoList.get(i).getXjpdjlZrr() == null ? ""
								: assetInfoList.get(i).getXjpdjlZrr());
				xjpdjlZrr = null;
				yuanzhi = assetElem.addElement("yuanzhi");
				yuanzhi.setText(assetInfoList.get(i).getYuanzhi() == null ? ""
						: assetInfoList.get(i).getYuanzhi());
				yuanzhi = null;
				yzjl = assetElem.addElement("yzjl");
				yzjl.setText(assetInfoList.get(i).getYzjl() == null ? ""
						: assetInfoList.get(i).getYzjl());
				yzjl = null;
				zcdbjlDbjl = assetElem.addElement("zcdbjlDbjl");
				zcdbjlDbjl
						.setText(assetInfoList.get(i).getZcdbjlDbjl() == null ? ""
								: assetInfoList.get(i).getZcdbjlDbjl());
				zcdbjlDbjl = null;
				zcdbjlFsfy = assetElem.addElement("zcdbjlFsfy");
				zcdbjlFsfy
						.setText(assetInfoList.get(i).getZcdbjlFsfy() == null ? ""
								: assetInfoList.get(i).getZcdbjlFsfy());
				zcdbjlFsfy = null;
				zcdbjlTime = assetElem.addElement("zcdbjlTime");
				zcdbjlTime
						.setText(assetInfoList.get(i).getZcdbjlTime() == null ? ""
								: assetInfoList.get(i).getZcdbjlTime());
				zcdbjlTime = null;
				zcdbjlZrr = assetElem.addElement("zcdbjlZrr");
				zcdbjlZrr
						.setText(assetInfoList.get(i).getZcdbjlZrr() == null ? ""
								: assetInfoList.get(i).getZcdbjlZrr());
				zcdbjlZrr = null;
				// System.out.println(i+"*"+assetElem.asXML());
				// sb.append(assetElem.asXML());
				assetElem = null;
			}
			allCodeInfoList = null;
			allSupplierList = null;
			assetInfoList = null;

			File tempFile = new File(
					ServletActionContext.getServletContext()
							.getRealPath(
									"jsp" + File.separator + "task"
											+ File.separator + "file"
											+ File.separator + "assetTask.xml"));
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();

			XMLWriter writer = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(tempFile), "UTF-8"), outputFormat);
			writer.write(document);
			writer.close();

			InputStream is = new FileInputStream(tempFile);
			int len = 0;
			byte[] buffers = new byte[1024];
			this.getServletResponse().reset();
			this.getServletResponse()
					.setContentType("application/x-msdownload");
			String filename = "task" + sdfToDate.format(new Date()) + ".xml";
			this.getServletResponse().addHeader("Content-Disposition",
					"attachment;filename=" + filename);
			// 把文件内容通过输出流打印到页面上供下载
			while ((len = is.read(buffers)) != -1) {
				OutputStream os = this.getServletResponse().getOutputStream();
				os.write(buffers, 0, len);
				os.flush();
			}
			is.close();
			tempFile.delete();
			// assetInfoList = null;
		}

		return null;
	}

	/**
	 * 跳转到上传文件页面
	 */
	public String showUploadPage() {
		return "showUploadPage";
	}

	/**
	 * 上传文件并保存盘点任务,根据盘点文件生成,暂时不用
	 * @author mengjie
	 * @throws IOException
	 */
	public String uploadFile3() throws IOException {

		String responseData = "";
		List<AssetTaskCheck> assetTaskCheckList = null;

		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间
		String[] strings=new String[0]; 
		
        String wrongValue = "0102";
        Map<String,String> map = new HashMap<String,String>();
        BufferedReader br = new BufferedReader(new FileReader("C:pandian.txt"));
//        br.
//        txtLines = br.readLine();
        List<String> txtLines = new ArrayList<String>();
        String line=null;
        while((line=br.readLine())!=null){
            txtLines.add(line);
        }
            
		// 获取资源文件中定义的上传路径
		savePath = ServletActionContext.getServletContext().getRealPath(
				"jsp" + File.separator + "task" + File.separator + "file"
						+ File.separator);
		File dir = new File(savePath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 获取扩展名
		if (fileFileName.lastIndexOf(".") >= 0) {
			extName = fileFileName.substring(fileFileName.lastIndexOf("."));
		}
		newFileName = nowTime + extName;
		File newFile = new File(savePath + File.separator + newFileName);

		// 存放在WebRoot/jsp/task/file/目录下
		copy(file, newFile);

		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(newFile);

			Element taskElement = document.getRootElement().element("taskId");
			String taskId = taskElement.getText();
			Element rootElement = document.getRootElement();

			List<Element> assetElementList = rootElement.elements("asset"); // 获取所有asset元素
			List<Element> assetInfoIdElemList =null;
			assetTaskCheckList = new ArrayList<AssetTaskCheck>();
			
			
//			txtLines
			Map<String,String> mapPerson = new HashMap<String,String>();
			Map<String,String> mapCheckDate = new HashMap<String,String>();
			for(String txtLine : txtLines ){
				String txtAssetId = txtLine.substring(50, 70); //新资产编号
				String txtCheckDate = txtLine.substring(70, 89); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
				String txtCheckPerson = txtLine.substring(89, 109); //盘点人   90-109位
				mapPerson.put(txtAssetId, txtCheckDate);
				mapCheckDate.put(txtAssetId, txtCheckPerson);
//				String txtCheckInfo = str.substring(109, 113); //盘点结果 110-113位 
//				String txtCheckMoreInfo = str.substring(114, 120); //盘点结果详细，115-120位
			}
			
			
			for(Element assetElement : assetElementList ){
				assetInfoIdElemList = assetElement.elements("assetId");
				
				String result = assetElement.attributeValue("result");
				String assetId = assetInfoIdElemList.get(0).getTextTrim();
				
				if(result!=null&&!"".equals(result)&&assetId!=null&&!"".equals(assetId)){
					AssetTaskCheck assetTaskCheck = new AssetTaskCheck();
//					assetId = assetIdNodes.get(0).getText();
					assetTaskCheck.setTaskId(taskId);
					assetTaskCheck.setAssetInfoId(assetId);
					assetTaskCheck.setOperateDate(sdf.format(new Date()));
					assetTaskCheck.setCheckdate(mapPerson.get(assetId));
					assetTaskCheck.setCheckperson(mapCheckDate.get(assetId));
					assetTaskCheck.setCheckinfo(changeNum(result));// 设置盘点结果
					assetTaskCheck.setRemoved("0");
					assetTaskCheckList.add(assetTaskCheck);
				}
				
				
			}
			if (assetTaskCheckList != null && assetTaskCheckList.size() > 0) {
				responseData = "上传成功！总共" + assetTaskCheckList.size() + "条盘点结果！";
				assetTaskCheckService.saveTaskCheckList(assetTaskCheckList);
			} else {
				responseData = "没有盘点结果，请检查上传的文件是否正确！";
			}

		} catch (DocumentException e) {
			System.out.println("解析xml文件发生错误！！！");
			responseData = "解析上传的文件发生错误，无法执行，请检查上传的文件是否符合xml文件规范！";
			e.printStackTrace();
		}

		HttpServletResponse response = this.servletResponse;
		response.setCharacterEncoding("UTF-8"); // 务必，防止返回文件名是乱码
		this.servletResponse.getWriter().print(responseData);

		return null;
	}

	/**
	 * 根据pandian.txt生成上传盘点结果
	 * @author mengjie
	 * @return
	 * @throws IOException
	 */
	public String uploadFile() throws IOException {

		String responseData = "";
		List<AssetTaskCheck> assetTaskCheckList = null;

		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间
		String[] strings=new String[0]; 
		
        String wrongValue = "0102";
        Map<String,String> map = new HashMap<String,String>();
//        br.
//        txtLines = br.readLine();
        
            
		// 获取资源文件中定义的上传路径
		savePath = ServletActionContext.getServletContext().getRealPath(
				"jsp" + File.separator + "task" + File.separator + "file"
						+ File.separator);
		File dir = new File(savePath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 获取扩展名
		if (fileFileName.lastIndexOf(".") >= 0) {
			extName = fileFileName.substring(fileFileName.lastIndexOf("."));
		}
		newFileName = nowTime + extName;
		File newFile = new File(savePath + File.separator + newFileName);

		// 存放在WebRoot/jsp/task/file/目录下
		copy(file, newFile);
		BufferedReader br = new BufferedReader(new FileReader(newFile));
		List<String> txtLines = new ArrayList<String>();
        String line=null;
        while((line=br.readLine())!=null){
            txtLines.add(line);
        }
        
			assetTaskCheckList = new ArrayList<AssetTaskCheck>();
			
//			Map<String,String> mapPerson = new HashMap<String,String>();
//			Map<String,String> mapCheckDate = new HashMap<String,String>();
//			Map<String,String> mapTaskId = new HashMap<String,String>();
			String txtChangeCheckInfo = null;
			int errorNum = 0;
			for(String txtLine : txtLines ){
				String txtTaskId = null; //任务ID
				String txtAssetId = null; //新资产编号
				String txtCheckDate = null; //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
				String txtCheckPerson = null; //盘点人   90-109位
				String txtCheckInfo = null; //盘点结果 110-113位 
				String txtCheckInfoMore = null; //盘点结果详细 115-120位 
			//	txtChangeCheckInfo = changeNum(txtCheckInfo);
				try{
					txtTaskId = txtLine.substring(0,32).trim(); //任务ID
					txtAssetId = txtLine.substring(50, 70).trim(); //新资产编号
					txtCheckDate = txtLine.substring(70, 89).trim(); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
					txtCheckPerson = txtLine.substring(89, 109).trim(); //盘点人   90-109位
					txtCheckInfo = txtLine.substring(109, 113).trim(); //盘点结果 110-113位 
					txtCheckInfoMore = txtLine.substring(114, 120).trim(); //盘点结果详细 115-120位
					if(txtCheckInfo.equals(wrongValue)){
						txtCheckInfo = txtCheckInfoMore;
					}
					txtChangeCheckInfo = changeNum(txtCheckInfo);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				if(txtTaskId != null){
					AssetTaskCheck assetTaskCheck = new AssetTaskCheck();
					assetTaskCheck.setTaskId(txtTaskId);
					assetTaskCheck.setAssetInfoId(txtAssetId);
					assetTaskCheck.setOperateDate(sdf.format(new Date()));
					assetTaskCheck.setCheckdate(txtCheckDate);
					assetTaskCheck.setCheckperson(txtCheckPerson);
					assetTaskCheck.setCheckinfo(txtChangeCheckInfo);// 设置盘点结果
					assetTaskCheck.setRemoved("0");
					
					
					// 为 true 数据库中没有  切为正确数据，符合新增记录条件（txt格式正确 +数据库中不存在）
					boolean checkTaskCheck = assetTaskCheckService.checkAssetTaskCheck(assetTaskCheck);
					
					if(checkTaskCheck){
						assetTaskCheckList.add(assetTaskCheck);
					}else{
						errorNum++;
					}
				}
			}
			if (assetTaskCheckList != null && assetTaskCheckList.size() > 0) {
				responseData = "上传完成！总共成功上传" + assetTaskCheckList.size() + "条盘点结果。";
				
				assetTaskCheckService.saveTaskCheckList(assetTaskCheckList);
			} else {
				responseData = "没有盘点结果，请检查上传的文件是否正确！";
			}

			
//			System.out.println("解析xml文件发生错误！！！");
//			responseData = "解析上传的文件发生错误，无法执行，请检查上传的文件是否符合xml文件规范！";
		

		HttpServletResponse response = this.servletResponse;
		response.setCharacterEncoding("UTF-8"); // 务必，防止返回文件名是乱码
		this.servletResponse.getWriter().print(responseData);
		newFile.delete();
		return null;
	}
	
	/**
	 * 上传文件并保存盘点任务
	 * 原导入方法，供备用
	 * @throws IOException
	 */
	public String uploadFileTest() throws IOException {

		String responseData = "";
		List<AssetTaskCheck> assetTaskCheckList = null;

		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss")
				.format(new Date());// 当前时间

		// 获取资源文件中定义的上传路径
		savePath = ServletActionContext.getServletContext().getRealPath(
				"jsp" + File.separator + "task" + File.separator + "file"
						+ File.separator);
		File dir = new File(savePath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		// 获取扩展名
		if (fileFileName.lastIndexOf(".") >= 0) {
			extName = fileFileName.substring(fileFileName.lastIndexOf("."));
		}
		newFileName = nowTime + extName;
		File newFile = new File(savePath + File.separator + newFileName);

		// 存放在WebRoot/jsp/task/file/目录下
		copy(file, newFile);

		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(newFile);

			Element taskElement = document.getRootElement().element("taskId");
			String taskId = taskElement.getText();
			Element rootElement = document.getRootElement();

			List<Element> assetElementList = rootElement.elements("asset"); // 获取所有asset元素

			if (assetElementList != null && assetElementList.size() > 0) {

				assetTaskCheckList = new ArrayList<AssetTaskCheck>();
				for (int i = 0; i < assetElementList.size(); i++) {
					Element assetElem = assetElementList.get(i);
					String result = assetElem.attributeValue("result");
					List<Element> assetInfoIdElemList = assetElem
							.elements("assetId");
					if (result != null && !"".equals(result)) { // 盘点结果存在
						List<Node> assetIdNodes = assetElem.selectNodes("//id");
						String assetId;
						if (assetIdNodes != null && assetIdNodes.size() > 0) {
							AssetTaskCheck assetTaskCheck = new AssetTaskCheck();
							assetId = assetIdNodes.get(0).getText();
							assetTaskCheck.setTaskId(taskId);

							assetTaskCheck.setCheckinfo(result); // 设置盘点结果
							assetTaskCheck.setOperateDate(sdf
									.format(new Date()));
							assetTaskCheck.setRemoved("0");

							if (assetInfoIdElemList != null && assetInfoIdElemList.size() > 0) {
								assetTaskCheck.setAssetInfoId(assetInfoIdElemList.get(0).getText());
								assetTaskCheckList.add(assetTaskCheck);
							}
						}
					}
				}
			} else {
				responseData = "上传文件错误！";
			}

			if (assetTaskCheckList != null && assetTaskCheckList.size() > 0) {
				responseData = "上传成功！总共" + assetTaskCheckList.size() + "条盘点结果！";
				assetTaskCheckService.saveTaskCheckList(assetTaskCheckList);
			} else {
				responseData = "没有盘点结果，请检查上传的文件是否正确！";
			}

		} catch (DocumentException e) {
			System.out.println("解析xml文件发生错误！！！");
			responseData = "解析上传的文件发生错误，无法执行，请检查上传的文件是否符合xml文件规范！";
			e.printStackTrace();
		}

		HttpServletResponse response = this.servletResponse;
		response.setCharacterEncoding("UTF-8"); // 务必，防止返回文件名是乱码
		this.servletResponse.getWriter().print(responseData);

		return null;
	}

	/**
	 * 查看盘点结果
	 * 
	 * @throws Exception
	 */
	public String showCheckResult() throws Exception {

		getServletResponse().setContentType("text/html;charset=utf-8");
		String id = ServletActionContext.getRequest().getParameter("id");
		List<AssetTaskCheck> assetTaskCheckList = new ArrayList<AssetTaskCheck>();
		List<String> resultList = new ArrayList<String>();

		assetTask = this.assetTaskService.findTaskById(id);

		String pageNo = servletRequest.getParameter("pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			pageNo = "0";
		}

		Map<String, Object> filter = new HashMap<String, Object>();
		String filterCondition;
		if (assetTask != null) {
			filterCondition = assetTask.getTaskmemoFilter();
			filter = getAssetInfoFilterByString(filterCondition);
		}

		Page page = assetInfoService.findAssetInfoByPage(filter, Integer
				.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		if (page != null && page.getResult() != null
				&& page.getResult().size() > 0) {

			for (int i = 0; i < page.getResult().size(); i++) {
				AssetInfo assetInfo = (AssetInfo) page.getResult().get(i);

				AssetTaskCheck taskCheck = assetTaskCheckService
						.findTaskByAssetInfoIdAndTaskId(assetInfo.getAssetId(),
								id);
				String checkType = properties.getProperty("CHECK_TYPE");
				if (taskCheck != null) {
					CfCodeInfo cfCodeInfo;
					try {
						cfCodeInfo = assetTaskCheckService.findCfCodeInfoById(Long.valueOf(taskCheck.getCheckinfo()));
						if (cfCodeInfo != null) {
							resultList.add(cfCodeInfo.getName());
						} else {
							resultList.add("未盘点");
						}
					} catch (Exception e) {
						resultList.add("未盘点");
					}
					
				} else {
					resultList.add("未盘点");
				}

			}
		}
		ServletActionContext.getRequest()
				.setAttribute("resultList", resultList);

		return "showCheckResult";
	}

	/**
	 * 通过ca认证交换
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public String findDeptUserName() throws FileNotFoundException, IOException {
		HttpServletRequest request = getServletRequest();

		String appName = request.getParameter("appName");
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String secret = request.getParameter("secret");
		String dataType = request.getParameter("dataType");
		String dataParams = request.getParameter("dataParams");

		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";

		String path = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath();

		Properties properties2 = new Properties();
		properties2.load(new FileInputStream(path));

		String serverUrl = properties2.getProperty("urlCa").toString()
				+ properties2.getProperty("serverPath").toString();

		try {
			url = new URL(serverUrl + "/dataExchange");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			// http.setRequestProperty("Content-Type",
			// "text/xml; charset=UTF-8");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + token
					+ "&method=" + method + "&dataType=" + dataType
					+ "&dataParams=" + dataParams + "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http
					.getOutputStream(), "utf-8");
			osw.write(param);
			osw.flush();
			osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						http.getInputStream(), "utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
				// System.out.println(textEntity);
				getServletResponse().setCharacterEncoding("utf-8");
				this.getServletResponse().getWriter().print(textEntity);
				return null;
			} else {
				return "没有通过用户认证";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (http != null)
				http.disconnect();
		}

		return null;
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

	// 初始化配置文件
	public void initProperties() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("assetInfo.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 填写资产分页查询的过滤条件
	public Map<String, Object> fillFilter(String key, Object value,
			Map<String, Object> filter) {
		if (value != null && !"".equals(value.toString().trim())) {
			filter.put(key, value);
		}
		return filter;
	}

	// 将资产搜索条件组合成一个字符串
	public StringBuilder combineSearchCondition(String key, Object value,
			StringBuilder sb) {
		if (value != null && !"".equals(value.toString().trim())) {
			sb.append(key).append("：").append(value.toString().trim()).append(
					"；");
		}
		return sb;
	}

	// 根据AssetTask.taskmemoFilter(String类型)字段的值，得到分页查询AssetInfo的条件filter
	public Map<String, Object> getAssetInfoFilterByString(String filterCondition)
			throws Exception {
		Map<String, Object> filter = new HashMap<String, Object>();
		if (filterCondition == null || "".equals(filterCondition)) {
			return null;
		} else {
			String[] conditions = filterCondition.split("；");
			if (conditions != null && conditions.length >= 1) {
				for (int i = 0; i < conditions.length; i++) {
					String[] keyAndValue = conditions[i].split("：");
					filter.put(keyAndValue[0], keyAndValue[1]);
				}
			}
			return filter;
		}
	}

	// 得到百分比数字，小数点保留2位
	public String getPercentage(int allCount, int finishCount) {
		if (finishCount != 0) {
			DecimalFormat df = new DecimalFormat("#.00");
			double result = Double.valueOf(finishCount)
					/ Double.valueOf(allCount) * 100.0d;
			if (result >= 100)
				return "100";
			String returnRusult = df.format(result);
			if (returnRusult.endsWith(".00"))
				return returnRusult.substring(0, returnRusult.indexOf("."));
			return returnRusult;
		}
		return "0";
	}

	// 复制文件到本地
	public void copy(File src, File dst) {

		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				int word = 0;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((word = in.read(buffer)) != -1) {
					out.write(buffer, 0, word);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * 将要盘点的资产转换成txt文件下载
	 * author:mengjie
	 * @throws Exception
	 */
	public String downloadAssetTaskAsTXT() throws Exception {
		String taskId = ServletActionContext.getRequest().getParameter("paramId"); //paramId=<s:property value="##task.id"
		String savepath = ServletActionContext.getServletContext().getRealPath(
				"jsp" + File.separator + "task" + File.separator + "file"
						+ File.separator);
		
		
		// 字典项
		List<CfCodeInfo> allCodeInfoList = assetInfoService.findAllCfCodeInfo();
		Map<Long, CfCodeInfo> codeInfoMap = new HashMap<Long, CfCodeInfo>();
		if (allCodeInfoList != null && allCodeInfoList.size() > 0) {
			for (int i = 0; i < allCodeInfoList.size(); i++) {
				codeInfoMap.put(allCodeInfoList.get(i).getId(), allCodeInfoList.get(i));
			}
			allCodeInfoList = null;
		}
		
		AssetTask assetTask = this.assetTaskService.findTaskById(taskId); //paramId=<s:property value="##task.id"
		List<AssetInfo> assetInfoList = null;
		if (assetTask != null) {
			assetInfoList = assetInfoService
					.findAllAssetInfoByFilter(getAssetInfoFilterByString(assetTask
							.getTaskmemoFilter()));
		}
		
		if (assetInfoList != null && assetInfoList.size() > 0) {
			String text0 ="";
			String text1 ="";
			String text2 ="";
			String text3 ="";
			String text4 ="";
			String text5 ="";
			String text6 ="";
			String text7 ="";
			String text8 ="";
			String text9 ="";
			String text10 =""; 
			String text11 =""; 
			String text12 =""; 
			String text13 =""; 
			
			
			
			/*	 ZCXX.TXT    主当数据表*/
			
			String dateTest = "0000-00-00 00:00:00";
//			StringBuffer text0 = new StringBuffer();
			
			SimpleDateFormat dateTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMdd"); //2000131
			SimpleDateFormat sdf8 = new SimpleDateFormat("yyyyMMdd");//20000131
			SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-M-dd");//2000-8-10
			SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM-dd"); //2000-01-31
			
			
			for (int i = 0, len = assetInfoList.size(); i < len; i++) {
				text0 += StringUtil.fillBytes(assetTask.getId(), 50, null, false);
				text0 += StringUtil.fillBytes(assetInfoList.get(i).getAssetId(), 20, null, false);
				text0 += StringUtil.fillBytes("",20,null,false);//无数据：71-90位
				String xjpdjlTime = assetInfoList.get(i).getXjpdjlTime();//巡检盘点－时间 91-110位
				int xjpdjlTimeLength = (xjpdjlTime==null||"".equals(xjpdjlTime))?0:xjpdjlTime.length();
				if(xjpdjlTimeLength == 0){
					text0 += StringUtil.fillBytes(dateTest,20,null,false); 
				}else if(xjpdjlTimeLength == 7){
					text0 += StringUtil.fillBytes(dateTo.format(sdf7.parse(xjpdjlTime)),20,null,false);
				}else if(xjpdjlTimeLength == 8){
					text0 += StringUtil.fillBytes(dateTo.format(sdf8.parse(xjpdjlTime)),20,null,false);
				}else if(xjpdjlTimeLength == 9){
					text0 += StringUtil.fillBytes(dateTo.format(sdf9.parse(xjpdjlTime)),20,null,false);
				}else if(xjpdjlTimeLength == 10){
					text0 += StringUtil.fillBytes(dateTo.format(sdf10.parse(xjpdjlTime)),20,null,false);
				}else{
					text0 += StringUtil.fillBytes(xjpdjlTime,20,null,false);
				}
				text0 += StringUtil.fillBytes(assetInfoList.get(i).getAssetName(),50,null,false);//资产名称：111-160位
				text0 += StringUtil.fillBytes(assetInfoList.get(i).getModel(),50,null,false);//型号：161-210位
				String date = assetInfoList.get(i).getUseTime();
				int dateLength = (date==null||"".equals(date))?0:date.length();
				if(dateLength == 0){
					text0 += StringUtil.fillBytes(dateTest,20,null,false); //启用时间 211-230位(yyyy-mm-dd hh:mm:ss)
				}else if(dateLength == 7){
					text0 += StringUtil.fillBytes(dateTo.format(sdf7.parse(date)),20,null,false);
				}else if(dateLength == 8){
					text0 += StringUtil.fillBytes(dateTo.format(sdf8.parse(date)),20,null,false);
				}else if(dateLength == 9){
					text0 += StringUtil.fillBytes(dateTo.format(sdf9.parse(date)),20,null,false);
				}else if(dateLength == 10){
					text0 += StringUtil.fillBytes(dateTo.format(sdf10.parse(date)),20,null,false);
				}else{
					text0 += StringUtil.fillBytes(date,20,null,false);
				}
				text0 += StringUtil.fillBytes("",2,null,true);//无数据：231-232位
				
			}
			GenerateTXT.generateTXT(text0,savepath+ File.separator,"ZCXX");
			
			/*for (int i = 0, len = assetInfoList.size(); i < len; i++) {
				text0.append(StringUtil.fillBytesBuffer(assetTask.getId(), 50, null, false));
				text0.append(StringUtil.fillBytesBuffer(assetInfoList.get(i).getAssetId(), 20, null, false));
				text0.append(StringUtil.fillBytesBuffer("",20,null,false));//无数据：71-90位
				String xjpdjlTime = assetInfoList.get(i).getXjpdjlTime();//巡检盘点－时间 91-110位
				int xjpdjlTimeLength = (xjpdjlTime==null||"".equals(xjpdjlTime))?0:xjpdjlTime.length();
				if(xjpdjlTimeLength == 0){
					text0.append(StringUtil.fillBytesBuffer(dateTest,20,null,false)); 
				}else if(xjpdjlTimeLength == 7){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf7.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 8){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf8.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 9){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf9.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 10){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf10.parse(xjpdjlTime)),20,null,false));
				}else{
					text0.append(StringUtil.fillBytesBuffer(xjpdjlTime,20,null,false));
				}
				text0.append(StringUtil.fillBytesBuffer(assetInfoList.get(i).getAssetName(),50,null,false));//资产名称：111-160位
				text0.append(StringUtil.fillBytesBuffer(assetInfoList.get(i).getModel(),50,null,false));//型号：161-210位
				String date = assetInfoList.get(i).getUseTime();
				int dateLength = (date==null||"".equals(date))?0:date.length();
				if(dateLength == 0){
					text0.append(StringUtil.fillBytesBuffer(dateTest,20,null,false)); //启用时间 211-230位(yyyy-mm-dd hh:mm:ss)
				}else if(dateLength == 7){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf7.parse(date)),20,null,false));
				}else if(dateLength == 8){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf8.parse(date)),20,null,false));
				}else if(dateLength == 9){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf9.parse(date)),20,null,false));
				}else if(dateLength == 10){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf10.parse(date)),20,null,false));
				}else{
					text0.append(StringUtil.fillBytesBuffer(date,20,null,false));
				}
				text0.append(StringUtil.fillBytesBuffer("",2,null,true));//无数据：231-232位
				
			}
			GenerateTXT.generateTXT(text0.toString(),savepath+ File.separator,"ZCXX");*/
			
			/* 导出全部资产
			 * StringBuffer text0 = new StringBuffer();
			for(int i=0; i<allAssetInfoList.size(); i++){
				text0.append(StringUtil.fillBytesBuffer(allAssetInfoList.get(i).getAssetId(),50,null,false));
//				text0 += StringUtil.fillBytes(allAssetInfoList.get(i).getAssetId(),50,null,false); //资产编号：1-50位
//				text0 += allAssetInfoList.get(i).getXjpdjlTime(); //上次巡检时间：91-110位(yyyy-mm-dd hh:mm:ss)
//				text0 += allAssetInfoList.get(i).getAssetName();//资产名称：111-160位
//				text0 += allAssetInfoList.get(i).getModel();//型号：161-210位
//				text0 += allAssetInfoList.get(i).getUseTime();//启用时间：211-230位(yyyy-mm-dd hh:mm:ss)
				
				text0.append(StringUtil.fillBytesBuffer("",20,null,false));//新资产编号：51-70位
				text0.append(StringUtil.fillBytesBuffer("",20,null,false));//无数据：71-90位
				
				String xjpdjlTime = allAssetInfoList.get(i).getXjpdjlTime();//巡检盘点－时间
				int xjpdjlTimeLength = (xjpdjlTime==null||"".equals(xjpdjlTime))?0:xjpdjlTime.length();
				if(xjpdjlTimeLength == 0){
					text0.append(StringUtil.fillBytesBuffer(dateTest,20,null,false)); //启用时间
				}else if(xjpdjlTimeLength == 7){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf7.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 8){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf8.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 9){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf9.parse(xjpdjlTime)),20,null,false));
				}else if(xjpdjlTimeLength == 10){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf10.parse(xjpdjlTime)),20,null,false));
				}else{
					text0.append(StringUtil.fillBytesBuffer(xjpdjlTime,20,null,false));
				}
//				text0.append(StringUtil.fillBytesBuffer(allAssetInfoList.get(i).getXjpdjlTime(),20,null,false)); //上次巡检时间：91-110位(yyyy-mm-dd hh:mm:ss)
				text0.append(StringUtil.fillBytesBuffer(allAssetInfoList.get(i).getAssetName(),50,null,false));//资产名称：111-160位
				text0.append(StringUtil.fillBytesBuffer(allAssetInfoList.get(i).getModel(),50,null,false));//型号：161-210位
				
				String date = allAssetInfoList.get(i).getUseTime();
				int dateLength = (date==null||"".equals(date))?0:date.length();
				if(dateLength == 0){
					text0.append(StringUtil.fillBytesBuffer(dateTest,20,null,false)); //启用时间
				}else if(dateLength == 7){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf7.parse(date)),20,null,false));
				}else if(dateLength == 8){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf8.parse(date)),20,null,false));
				}else if(dateLength == 9){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf9.parse(date)),20,null,false));
				}else if(dateLength == 10){
					text0.append(StringUtil.fillBytesBuffer(dateTo.format(sdf10.parse(date)),20,null,false));
				}else{
					text0.append(StringUtil.fillBytesBuffer(date,20,null,false));
				}
//				text0.append(StringUtil.fillBytesBuffer(allAssetInfoList.get(i).getUseTime(),20,null,false));//启用时间：211-230位(yyyy-mm-dd hh:mm:ss)
				text0.append(StringUtil.fillBytesBuffer("",2,null,true));//无数据：231-232位
			}
			GenerateTXT.generateTXT(text0.toString(),savepath+ File.separator,"ZCXX");*/
		
			/* ZCQSDW.txt        权属单位编码表, */
			List<CfCodeInfo> ownerDutyList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("OWNER_DUTY")));
			Map<String,String> ownerDutyMap = new HashMap<String,String>();
			for(CfCodeInfo u:ownerDutyList){
				ownerDutyMap.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<ownerDutyList.size(); i++){
				text1 += StringUtil.fillBytes(ownerDutyList.get(i).getCode(),10,null,false);
				text1 += StringUtil.fillBytes(ownerDutyList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text1,savepath+ File.separator,"ZCQSDW");
			
			
			/*ZCSYDW.txt 使用单位编码表*/
			List<CfCodeInfo> useDutyList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("USE_DUTY")));
			Map<String,String> useDutyMap = new HashMap<String,String>();
			for(CfCodeInfo u:useDutyList){
				useDutyMap.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<useDutyList.size(); i++){
				text2 += StringUtil.fillBytes(useDutyList.get(i).getCode(),10,null,false);
				text2 += StringUtil.fillBytes(useDutyList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text2,savepath+ File.separator,"ZCSYDW");
			
			/* ZCWHDW.txt       维护单位编码表*/
			List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("MAINTAIN_DEPT")));
			Map<String,String> maintainDeptMap = new HashMap<String,String>();
			for(CfCodeInfo u:maintainDeptList){
				maintainDeptMap.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<maintainDeptList.size(); i++){
				text3 += StringUtil.fillBytes(maintainDeptList.get(i).getCode(),10,null,false);
				text3 += StringUtil.fillBytes(maintainDeptList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text3,savepath+ File.separator,"ZCWHDW");
			
			/* XLBM.txt            线路编码表*/		
			List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("LINE_ID")));
			Map<String,String> lineMap = new HashMap<String,String>();
			for(CfCodeInfo u:lineList){
				lineMap.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<lineList.size(); i++){
				text4 += StringUtil.fillBytes(lineList.get(i).getCode(),10,null,false);
				text4 += StringUtil.fillBytes(lineList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text4,savepath+ File.separator,"XLBM");
			
			/*  CZBM.txt           车站编码表*/	
			String LINE_ID = String.valueOf(properties.getProperty("LINE_ID"));
			List<CfCodeInfo> stationList = assetInfoService.findCfInfoCodeForStationByTypeId(LINE_STATION_ID,LINE_ID);
			Map<String,String> stationMap = new HashMap<String,String>();  
			for(CfCodeInfo u:stationList){
				stationMap.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<stationList.size(); i++){
				text5 += StringUtil.fillBytes(stationList.get(i).getCode(),10,null,false);
				text5 += StringUtil.fillBytes(stationList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text5,savepath+ File.separator,"CZBM");
			
			/*  ZCDLBM.txt           资产大类编码表*/	
			int length1 = 2;
			List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeForAllTypeByLength(Long.valueOf(properties.getProperty("TYPE")),length1);
			Map<String,String> type1Map = new HashMap<String,String>();  
			for(CfCodeInfo u:type1List){
				type1Map.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<type1List.size(); i++){
				text6 += StringUtil.fillBytes(type1List.get(i).getCode(),10,null,false);
				text6 += StringUtil.fillBytes(type1List.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text6,savepath+ File.separator,"ZCDLBM");
			
			/*  ZCZLBM.txt           资产中类编码表*/	
			int length2 = 4;
			List<CfCodeInfo> type2List = assetInfoService.findCfInfoCodeForAllTypeByLength(Long.valueOf(properties.getProperty("TYPE")),length2);
			Map<String,String> type2Map = new HashMap<String,String>();  
			for(CfCodeInfo u:type2List){
				type2Map.put(u.getId()+"", u.getName());
			}
			for(int i=0; i<type2List.size(); i++){
				text7 += StringUtil.fillBytes(type2List.get(i).getCode(),10,null,false);
				text7 += StringUtil.fillBytes(type2List.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text7,savepath+ File.separator,"ZCZLBM");
			
			/*  TCXLBM.txt           资产小类编码表*/	
			int length3 = 6;
			List<CfCodeInfo> type3List = assetInfoService.findCfInfoCodeForAllTypeByLength(Long.valueOf(properties.getProperty("TYPE")),length3);
//			Map<String,String> type3Map = new HashMap<String,String>();  
//			for(CfCodeInfo u:type3List){
//				type3Map.put(u.getId()+"", u.getName());
//			}
			for(int i=0; i<type3List.size(); i++){
				text8 += StringUtil.fillBytes(type3List.get(i).getCode(),10,null,false);
				text8 += StringUtil.fillBytes(type3List.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text8,savepath+ File.separator,"TCXLBM");
			
			
			
			/* ZCQSDW.txt        当前使用状态表 */
			List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("USE_STATUS")));
			for(int i=0; i<useStatusList.size(); i++){
				text9 += StringUtil.fillBytes(useStatusList.get(i).getCode(),10,null,false);
				text9 += StringUtil.fillBytes(useStatusList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text9,savepath+ File.separator,"DQSYZT");
			
			/* DQSBZT.txt        当前设备状态表 */
			List<CfCodeInfo> equipmentStatusList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("EQUIPMENT_STATUS")));
			for(int i=0; i<equipmentStatusList.size(); i++){
				text10 += StringUtil.fillBytes(equipmentStatusList.get(i).getCode(),10,null,false);
				text10 += StringUtil.fillBytes(equipmentStatusList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text10,savepath+ File.separator,"DQSBZT");
			
			/* MPZTWZ.txt        铭牌张贴位置表 */
			List<CfCodeInfo> namePositionList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("NAMEPLATE_POSITION")));
			for(int i=0; i<namePositionList.size(); i++){
				text11 += StringUtil.fillBytes(namePositionList.get(i).getCode(),10,null,false);
				text11 += StringUtil.fillBytes(namePositionList.get(i).getName(),100,null,true);
			}
			GenerateTXT.generateTXT(text11,savepath+ File.separator,"MPZTWZ");
			
			
			/* PDYHXX.txt        盘点用户信息表 */
			List<AssetTask> taskCheckPersonList =  assetTaskService.findAllTask();
			for(int i=0; i<taskCheckPersonList.size(); i++){
				List<User> userList =  userService.findUserByCheckpersonlist(taskCheckPersonList.get(i).getCheckpersonlist());
				for(int j=0; j<userList.size(); j++){
					text13 += StringUtil.fillBytes(userList.get(j).getLoginName(),20,null,false);
					text13 += "888888";//因无法取出加密的密码，故此处统一置为“0000”
					text13 += StringUtil.fillBytes("",4,null,false);
					text13 += StringUtil.fillBytes(userList.get(j).getCompany(),10,null,true);
				}
			}
			GenerateTXT.generateTXT(text13,savepath+ File.separator,"PDYHXX");
			
			//downloadAssetTaskAsXMLCopy();
			List<File> files = new ArrayList<File>();   
			files.add(new File(savepath+ File.separator+"ZCXX.txt"));
			files.add(new File(savepath+ File.separator+"ZCQSDW.txt"));
			files.add(new File(savepath+ File.separator+"ZCSYDW.txt"));
			files.add(new File(savepath+ File.separator+"ZCWHDW.txt"));
			files.add(new File(savepath+ File.separator+"XLBM.txt"));
			files.add(new File(savepath+ File.separator+"CZBM.txt"));
			files.add(new File(savepath+ File.separator+"ZCDLBM.txt"));
			files.add(new File(savepath+ File.separator+"ZCZLBM.txt"));
			files.add(new File(savepath+ File.separator+"TCXLBM.txt"));
			files.add(new File(savepath+ File.separator+"DQSYZT.txt"));
			files.add(new File(savepath+ File.separator+"DQSBZT.txt"));
			files.add(new File(savepath+ File.separator+"MPZTWZ.txt"));
			files.add(new File(savepath+ File.separator+"PDYHXX.txt"));
			DownLoadZipFiles.downLoadFiles(files, servletRequest, servletResponse);
		}
		return null ;
	}

	
	
	/**
	 * 根据pandian.txt生成待上传xml
	 */
	public void generateXML(){
		
	    String[] strings=new String[0]; 
        String line=null;
        String wrongValue = "0102";
        Map<String,String> map = new HashMap<String,String>();
        File tempFile = new File(
				ServletActionContext.getServletContext().getRealPath(
								"jsp" + File.separator + "task"+ File.separator + "file"));
        try {
//        	theSamewordDel();
            BufferedReader br = new BufferedReader(new FileReader("C:pandian.txt"));
            while((line=br.readLine())!=null){
                String[] temp=new String[strings.length+1];
                System.arraycopy(strings, 0, temp, 0, strings.length);
                temp[temp.length-1]=line;
                strings=temp;
            }
            for(String str:strings){
            	String str1 = str.substring(50, 70); //新资产编号
				String str2 = str.substring(70, 89); //盘点时间(yyyy-mm-dd hh:mm:ss) 71-89位
				String str3 = str.substring(89, 109); //盘点人   90-109位
				String str4 = str.substring(109, 113); //盘点结果 110-113位 
				String str5 = str.substring(114, 120); //盘点结果详细，115-120位
				
				if(str4.equals(wrongValue)){
					str4=str5;
				}
				map.put(str1, str4);
//				System.out.println(str5);
//				System.out.println("-----------");
            }
//            AnalyseXml.analyseXml("C:\\3333.xml", "D:\\安装文件\\CuteStudio-3.10-Final\\workspace\\apps\\WebRoot\\jsp\\task\\file\\assetTaskCheck.xml", map);
            AnalyseXml.analyseXml(tempFile+File.separator + "assetTask.xml", tempFile+File.separator + "assetTaskCheck.xml", map);
            try {
            	downloadExecute();
//            	tempFile.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	
	/**
	 * 把文件内容通过输出流打印到页面上供下载
	 * @return
	 * @throws Exception
	 */
	public String downloadExecute() throws Exception {  
		File tempFile = new File(
				ServletActionContext.getServletContext()
						.getRealPath(
								"jsp" + File.separator + "task"
										+ File.separator + "file"
										+ File.separator + "assetTaskCheck.xml"));

		InputStream is = new FileInputStream(tempFile);
		int len = 0;
		byte[] buffers = new byte[1024];
		this.getServletResponse().reset();
		this.getServletResponse()
				.setContentType("application/x-msdownload");
		String filename = "assetTaskCheck(" + sdfToDate.format(new Date()) + ").xml";
		this.getServletResponse().addHeader("Content-Disposition","attachment;filename=" + filename);
		while ((len = is.read(buffers)) != -1) {
			OutputStream os = this.getServletResponse().getOutputStream();
			os.write(buffers, 0, len);
			os.flush();
		}
		is.close();
		tempFile.delete();
		// assetInfoList = null;
        return null;  
    }  
	
	/**
	 * 根据资产编号来删除文本里面的相同行，若编号相同，则保留最新的一条
	 * 此方法须指定pandian.txt的路径
	 * @author mengjie
	 **/

	public void theSamewordDel() throws IOException{
			//读取文本文件内容，并存到WordList数组内
			File f = new File("C:pandian.txt");
			if(!f.exists()){
				System.out.println("Sorry,the file is unexists(对不起，文件不存在！)!");
			}else{			
				FileInputStream in = new FileInputStream(f);
				InputStreamReader inReader = new InputStreamReader(in); 
				BufferedReader br = new BufferedReader(inReader);
				ArrayList<String> astr=new ArrayList<String>();
				String data = br.readLine();//一次读入一行，直到读入null为文件结束  
				while( data!=null){  
					//System.out.println(data);  
					astr.add(data);
					data = br.readLine(); //接着读下一行  
//					System.out.println("11111111"+astr.size());
				}  
				
				//读取完后，删除文件
				f.delete();
				
				//删除重复行，获取不重复String数组str
				ArrayList<String> astr_back=new ArrayList<String>();
				boolean boo = true;

				for(int i=0;i<astr.size();i++){			
					for(int j=i;j<astr.size();j++){
						
						System.out.println("第"+i+","+j+"次比较："+astr.get(i)+":"+astr.get(j)+"--"+astr.get(i).equals(astr.get(j)));
						
						if(astr.get(i).substring(50, 70).equals(astr.get(j).substring(50, 70)) && 
								Date(astr.get(i).substring(70, 89)) <= Date(astr.get(j).substring(70, 89)) &&(i != j)){//用Date构造
							System.out.println("找到重复行："+astr.get(i)+".....状态：已过滤!");
							boo =false;
							break;
						}
						//System.out.println("比较结果："+boo);
					}
					if(boo){
						astr_back.add(astr.get(i));
						//System.out.println(astr_back.size());
					}
					boo = true;
				}
				
				String[] str = new String[astr_back.size()];
				for(int i=0;i<astr_back.size();i++){
					str[i] = astr_back.get(i);
					//System.out.println(str[i]);
				}
				
				//创建文件
				File fn= new File("C:pandian.txt");
				fn.createNewFile();
				
				//写入数据
				FileWriter fw = new FileWriter(fn);
				for(int i = 0;i<str.length;i++){
					//System.out.println(str[i]);
					fw.write(str[i] + "\r\n");
					//fw.write("我在写入东西\r\n");
				}
				fw.flush();//清空缓存区
				fw.close(); 
				System.out.println("Proccess Finished(处理结束)!");
				//System.out.println(astr == astr_back);
				//System.out.println(astr.size());
				//System.out.println(astr_back.size());
				
				
			}
		}
		
	/**
	 * 将盘点结果文档上截取的盘点时间进行转换
	 * @param StringToDate
	 * @return
	 */
	public static long Date(String StringToDate){
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
				Date d1;
				try {
					d1 = sdf.parse(StringToDate);
					return d1.getTime();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
		}
	
	/**
	 * 将盘点结果文档上截取的盘点结果进行转换
	 * @param n txt上的盘点结果
	 * @return
	 */
	public String changeNum(String n){
		if(n.equals("0101")){return "13514";}
		else if(n.equals("0102")){return "13515";}
		else if(n.equals("010201")){return "13516";}
		else if(n.equals("010202")){return "13517";}
		else if(n.equals("010203")){return "13518";}
		else if(n.equals("010204")){return "13519";}
		else if(n.equals("010205")){return "13520";}
		else if(n.equals("010206")){return "13535";}
		else if(n.equals("010207")){return "13536";}
		else if(n.equals("010208")){return "13822";}
		else{
			return null;
		}
	}
	
	/**
	 * 扫描器中公司单位只能识别2位中文
	 * @param n 公司单位名称
	 * @return
	 */
	public String changeCompany(String n){
		return null;
	}
	/**
	 * 将要盘点的资产转换成xml文件备份下载
	 * 
	 * @throws Exception
	 */
	public String downloadAssetTaskAsXMLCopy() throws Exception {
		String paramId = ServletActionContext.getRequest().getParameter(
				"paramId");
		// 字典项
		List<CfCodeInfo> allCodeInfoList = assetInfoService.findAllCfCodeInfo();
		Map<Long, CfCodeInfo> codeInfoMap = new HashMap<Long, CfCodeInfo>();
		if (allCodeInfoList != null && allCodeInfoList.size() > 0) {
			for (int i = 0; i < allCodeInfoList.size(); i++) {
				codeInfoMap.put(allCodeInfoList.get(i).getId(), allCodeInfoList
						.get(i));
			}
			allCodeInfoList = null;
		}
		// 厂商
		List<Supplier> allSupplierList = supplierService.findAllSupplier();
		Map<Long, Supplier> supplierMap = new HashMap<Long, Supplier>();
		if (allSupplierList != null && allSupplierList.size() > 0) {
			for (int i = 0; i < allSupplierList.size(); i++) {
				supplierMap.put(allSupplierList.get(i).getId(), allSupplierList
						.get(i));
			}
			allSupplierList = null;
		}

		AssetTask assetTask = this.assetTaskService.findTaskById(paramId);
		List<AssetInfo> assetInfoList = null;
		if (assetTask != null) {
			assetInfoList = assetInfoService
					.findAllAssetInfoByFilter(getAssetInfoFilterByString(assetTask
							.getTaskmemoFilter()));
		}

		if (assetInfoList != null && assetInfoList.size() > 0) {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("assets result='a'");
			Element taskId = root.addElement("taskId");
			taskId.setText(paramId);
			paramId = null;
			CfCodeInfo tempCodeInfo;

			// 定义dom节点
			Element assetElem, id, assetId, assetName, assetPic, benqizhijiu, bfcljlDbjl, bfcljlFsfy, bfcljlTime, bfcljlZrr, buildAddr, compactPrice, czl, cd, depreciationWay, discardTime, dxpl, equipmentState, finalAccountPrice, gxgzjlDbjl, gxgzjlFsfy, gxgzjlTime, gxgzjlZrr, handoverTime, jingzhi, jszlqd, leaveFactoryPrice, leijizhejiu, maintainDep, makeTime, manufacturer, memo, model, mpztbw, operateTime, operator, owner, ownerDuty, payTime, provideTime, purchaseTime, qtjlDbjl, qtjlFsfy, qtjlTime, qtjlZrr, routeNum, shebeiqingdan, shelfLife, sjsynx, specification, stationNum, stopuseTime, type1, type2, type3, userDuty, useLife, usePerson, useState, useTime, vendor, wxjlDbjl, wxjlFsfy, wxjlTime, wxjlZrr, xjpdjlDbjl, xjpdjlFsfy, xjpdjlTime, xjpdjlZrr, yuanzhi, yzjl, zcdbjlDbjl, zcdbjlFsfy, zcdbjlTime, zcdbjlZrr;

			StringBuilder sb = new StringBuilder();

			// Element tempElement = DocumentHelper.createElement("asset");
			for (int i = 0, len = assetInfoList.size(); i < len; i++) {
				assetElem = root.addElement("asset");
				
				// assetElem = tempElement;
				id = assetElem.addElement("id");
				id.setText(assetInfoList.get(i).getId() == null ? ""
						: assetInfoList.get(i).getId().toString());
				id = null;
				assetId = assetElem.addElement("assetId");
				assetId.setText(assetInfoList.get(i).getAssetId() == null ? ""
						: assetInfoList.get(i).getAssetId());
				assetId = null;
				assetName = assetElem.addElement("name");
				assetName
						.setText(assetInfoList.get(i).getAssetName() == null ? ""
								: assetInfoList.get(i).getAssetName());
				assetName = null;
				assetPic = assetElem.addElement("assetPic");
				assetPic
						.setText(assetInfoList.get(i).getAssetPic() == null ? ""
								: assetInfoList.get(i).getAssetPic());
				assetPic = null;
				benqizhijiu = assetElem.addElement("benqizhijiu");
				benqizhijiu
						.setText(assetInfoList.get(i).getBenqizhijiu() == null ? ""
								: assetInfoList.get(i).getBenqizhijiu());
				benqizhijiu = null;
				bfcljlDbjl = assetElem.addElement("bfcljlDbjl");
				bfcljlDbjl
						.setText(assetInfoList.get(i).getBfcljlDbjl() == null ? ""
								: assetInfoList.get(i).getBfcljlDbjl());
				bfcljlDbjl = null;
				bfcljlFsfy = assetElem.addElement("bfcljlFsfy");
				bfcljlFsfy
						.setText(assetInfoList.get(i).getBfcljlFsfy() == null ? ""
								: assetInfoList.get(i).getBfcljlFsfy());
				bfcljlFsfy = null;
				bfcljlTime = assetElem.addElement("bfcljlTime");
				bfcljlTime
						.setText(assetInfoList.get(i).getBfcljlTime() == null ? ""
								: assetInfoList.get(i).getBfcljlTime());
				bfcljlTime = null;
				bfcljlZrr = assetElem.addElement("bfcljlZrr");
				bfcljlZrr
						.setText(assetInfoList.get(i).getBfcljlZrr() == null ? ""
								: assetInfoList.get(i).getBfcljlZrr());
				bfcljlZrr = null;
				buildAddr = assetElem.addElement("buildAddr");
				buildAddr
						.setText(assetInfoList.get(i).getBuildAddr() == null ? ""
								: assetInfoList.get(i).getBuildAddr());
				buildAddr = null;
				compactPrice = assetElem.addElement("compactPrice");
				compactPrice
						.setText(assetInfoList.get(i).getCompactPrice() == null ? ""
								: assetInfoList.get(i).getCompactPrice());
				compactPrice = null;
				czl = assetElem.addElement("czl");
				czl.setText(assetInfoList.get(i).getCzl() == null ? ""
						: assetInfoList.get(i).getCzl());
				czl = null;
				cd = assetElem.addElement("cd");
				cd.setText(assetInfoList.get(i).getCd() == null ? ""
						: assetInfoList.get(i).getCd());
				cd = null;
				depreciationWay = assetElem.addElement("depreciationWay");
				if (assetInfoList.get(i).getDepreciationWay() != null
						&& !""
								.equals(assetInfoList.get(i)
										.getDepreciationWay())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getDepreciationWay()));
					if (tempCodeInfo != null) {
						depreciationWay.setText(tempCodeInfo.getName());
					}
				} else {
					depreciationWay.setText("");
				}
				depreciationWay = null;
				discardTime = assetElem.addElement("discardTime");
				discardTime
						.setText(assetInfoList.get(i).getDiscardTime() == null ? ""
								: assetInfoList.get(i).getDiscardTime());
				discardTime = null;
				dxpl = assetElem.addElement("dxpl");
				dxpl.setText(assetInfoList.get(i).getDxpl() == null ? ""
						: assetInfoList.get(i).getDxpl());
				dxpl = null;
				equipmentState = assetElem.addElement("equipmentState");
				if (assetInfoList.get(i).getEquipmentState() != null
						&& !"".equals(assetInfoList.get(i).getEquipmentState())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getEquipmentState()));
					if (tempCodeInfo != null) {
						equipmentState.setText(tempCodeInfo.getName());
					}
				} else {
					equipmentState.setText("");
				}
				equipmentState = null;
				finalAccountPrice = assetElem.addElement("finalAccountPrice");
				finalAccountPrice.setText(assetInfoList.get(i)
						.getFinalAccountPrice() == null ? "" : assetInfoList
						.get(i).getFinalAccountPrice());
				finalAccountPrice = null;
				gxgzjlDbjl = assetElem.addElement("gxgzjlDbjl");
				gxgzjlDbjl
						.setText(assetInfoList.get(i).getGxgzjlDbjl() == null ? ""
								: assetInfoList.get(i).getGxgzjlDbjl());
				gxgzjlDbjl = null;
				gxgzjlFsfy = assetElem.addElement("gxgzjlFsfy");
				gxgzjlFsfy
						.setText(assetInfoList.get(i).getGxgzjlFsfy() == null ? ""
								: assetInfoList.get(i).getGxgzjlFsfy());
				gxgzjlFsfy = null;
				gxgzjlTime = assetElem.addElement("gxgzjlTime");
				gxgzjlTime
						.setText(assetInfoList.get(i).getGxgzjlTime() == null ? ""
								: assetInfoList.get(i).getGxgzjlTime());
				gxgzjlTime = null;
				gxgzjlZrr = assetElem.addElement("gxgzjlZrr");
				gxgzjlZrr
						.setText(assetInfoList.get(i).getGxgzjlZrr() == null ? ""
								: assetInfoList.get(i).getGxgzjlZrr());
				gxgzjlZrr = null;
				handoverTime = assetElem.addElement("handoverTime");
				handoverTime
						.setText(assetInfoList.get(i).getHandoverTime() == null ? ""
								: assetInfoList.get(i).getHandoverTime());
				handoverTime = null;
				jingzhi = assetElem.addElement("jingzhi");
				jingzhi.setText(assetInfoList.get(i).getJingzhi() == null ? ""
						: assetInfoList.get(i).getJingzhi());
				jingzhi = null;
				jszlqd = assetElem.addElement("jszlqd");
				jszlqd.setText(assetInfoList.get(i).getJszlqd() == null ? ""
						: assetInfoList.get(i).getJszlqd());
				jszlqd = null;
				leaveFactoryPrice = assetElem.addElement("leaveFactoryPrice");
				leaveFactoryPrice.setText(assetInfoList.get(i)
						.getLeaveFactoryPrice() == null ? "" : assetInfoList
						.get(i).getLeaveFactoryPrice());
				leaveFactoryPrice = null;
				leijizhejiu = assetElem.addElement("leijizhejiu");
				leijizhejiu
						.setText(assetInfoList.get(i).getLeijizhejiu() == null ? ""
								: assetInfoList.get(i).getLeijizhejiu());
				leijizhejiu = null;
				maintainDep = assetElem.addElement("maintainDep");
				if (assetInfoList.get(i).getMaintainDep() != null
						&& !"".equals(assetInfoList.get(i).getMaintainDep())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getMaintainDep()));
					if (tempCodeInfo != null) {
						maintainDep.setText(tempCodeInfo.getName());
					}
				} else {
					maintainDep.setText("");
				}
				maintainDep = null;
				makeTime = assetElem.addElement("makeTime");
				makeTime
						.setText(assetInfoList.get(i).getMakeTime() == null ? ""
								: assetInfoList.get(i).getMakeTime());
				makeTime = null;
				manufacturer = assetElem.addElement("manufacturer");
				if (assetInfoList.get(i).getManufacturer() != null
						&& !"".equals(assetInfoList.get(i).getManufacturer())) {
					Supplier supplier = null;
					try {
						supplier = supplierMap.get(Long.valueOf(assetInfoList
								.get(i).getManufacturer()));
						if (supplier != null && !"".equals(supplier.getName())) {
							manufacturer.setText(supplier.getName());
						} else {
							manufacturer.setText("");
						}
					} catch (NumberFormatException e) {
						manufacturer.setText("");
					} finally {
						supplier = null;
					}

				} else {
					manufacturer.setText("");
				}
				manufacturer = null;
				memo = assetElem.addElement("memo");
				memo.setText(assetInfoList.get(i).getMemo() == null ? ""
						: assetInfoList.get(i).getMemo());
				memo = null;
				model = assetElem.addElement("model");
				model.setText(assetInfoList.get(i).getModel() == null ? ""
						: assetInfoList.get(i).getModel());
				model = null;
				mpztbw = assetElem.addElement("mpztbw");
				if (assetInfoList.get(i).getMpztbw() != null
						&& !"".equals(assetInfoList.get(i).getMpztbw())) {

					// CfCodeInfo cfCodeInfo =
					// assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoList.get(i).getMpztbw()));
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getMpztbw()));
					if (tempCodeInfo != null) {
						mpztbw.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					mpztbw.setText("");
				}
				mpztbw = null;
				operateTime = assetElem.addElement("operateTime");
				operateTime
						.setText(assetInfoList.get(i).getOperateTime() == null ? ""
								: assetInfoList.get(i).getOperateTime());
				operateTime = null;
				operator = assetElem.addElement("operator");
				operator
						.setText(assetInfoList.get(i).getOperator() == null ? ""
								: assetInfoList.get(i).getOperator());
				operator = null;
				owner = assetElem.addElement("owner");
				owner.setText(assetInfoList.get(i).getOwner() == null ? ""
						: assetInfoList.get(i).getOwner());
				owner = null;
				ownerDuty = assetElem.addElement("ownerDuty");
				if (assetInfoList.get(i).getOwnerDuty() != null
						&& !"".equals(assetInfoList.get(i).getOwnerDuty())) {
					// CfCodeInfo cfCodeInfo =
					// assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoList.get(i).getOwnerDuty()));
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getOwnerDuty()));
					if (tempCodeInfo != null) {
						ownerDuty.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					ownerDuty.setText("");
				}
				ownerDuty = null;
				payTime = assetElem.addElement("payTime");
				payTime.setText(assetInfoList.get(i).getPayTime() == null ? ""
						: assetInfoList.get(i).getPayTime());
				payTime = null;
				provideTime = assetElem.addElement("provideTime");
				provideTime
						.setText(assetInfoList.get(i).getProvideTime() == null ? ""
								: assetInfoList.get(i).getProvideTime());
				provideTime = null;
				purchaseTime = assetElem.addElement("purchaseTime");
				purchaseTime
						.setText(assetInfoList.get(i).getPurchaseTime() == null ? ""
								: assetInfoList.get(i).getPurchaseTime());
				purchaseTime = null;
				qtjlDbjl = assetElem.addElement("qtjlDbjl");
				qtjlDbjl
						.setText(assetInfoList.get(i).getQtjlDbjl() == null ? ""
								: assetInfoList.get(i).getQtjlDbjl());
				qtjlDbjl = null;
				qtjlFsfy = assetElem.addElement("qtjlFsfy");
				qtjlFsfy
						.setText(assetInfoList.get(i).getQtjlFsfy() == null ? ""
								: assetInfoList.get(i).getQtjlFsfy());
				qtjlFsfy = null;
				qtjlTime = assetElem.addElement("qtjlTime");
				qtjlTime
						.setText(assetInfoList.get(i).getQtjlTime() == null ? ""
								: assetInfoList.get(i).getQtjlTime());
				qtjlTime = null;
				qtjlZrr = assetElem.addElement("qtjlZrr");
				qtjlZrr.setText(assetInfoList.get(i).getQtjlZrr() == null ? ""
						: assetInfoList.get(i).getQtjlZrr());
				qtjlZrr = null;
				routeNum = assetElem.addElement("routeNum");
				if (assetInfoList.get(i).getRouteNum() != null
						&& !"".equals(assetInfoList.get(i).getRouteNum())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getRouteNum()));
					if (tempCodeInfo != null) {
						routeNum.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					routeNum.setText("");
				}
				routeNum = null;
				shebeiqingdan = assetElem.addElement("shebeiqingdan");
				shebeiqingdan
						.setText(assetInfoList.get(i).getShebeiqingdan() == null ? ""
								: assetInfoList.get(i).getShebeiqingdan());
				shebeiqingdan = null;
				shelfLife = assetElem.addElement("shelfLife");
				shelfLife
						.setText(assetInfoList.get(i).getShelfLife() == null ? ""
								: assetInfoList.get(i).getShelfLife());
				shelfLife = null;
				sjsynx = assetElem.addElement("sjsynx");
				sjsynx.setText(assetInfoList.get(i).getSjsynx() == null ? ""
						: assetInfoList.get(i).getSjsynx());
				sjsynx = null;
				specification = assetElem.addElement("specification");
				specification
						.setText(assetInfoList.get(i).getSpecification() == null ? ""
								: assetInfoList.get(i).getSpecification());
				specification = null;
				stationNum = assetElem.addElement("stationNum");
				if (assetInfoList.get(i).getStationNum() != null
						&& !"".equals(assetInfoList.get(i).getStationNum())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getStationNum()));
					if (tempCodeInfo != null) {
						stationNum.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					stationNum.setText("");
				}
				stationNum = null;
				stopuseTime = assetElem.addElement("stopuseTime");
				stopuseTime
						.setText(assetInfoList.get(i).getStopuseTime() == null ? ""
								: assetInfoList.get(i).getStopuseTime());
				stopuseTime = null;
				type1 = assetElem.addElement("type1");
				if (assetInfoList.get(i).getType1() != null
						&& !"".equals(assetInfoList.get(i).getType1())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType1()));
					if (tempCodeInfo != null) {
						type1.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type1.setText("");
				}
				type1 = null;
				type2 = assetElem.addElement("type2");
				if (assetInfoList.get(i).getType2() != null
						&& !"".equals(assetInfoList.get(i).getType2())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType2()));
					if (tempCodeInfo != null) {
						type2.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type2.setText("");
				}
				type2 = null;
				type3 = assetElem.addElement("type3");
				if (assetInfoList.get(i).getType3() != null
						&& !"".equals(assetInfoList.get(i).getType3())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getType3()));
					if (tempCodeInfo != null) {
						type3.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					type3.setText("");
				}
				type3 = null;
				userDuty = assetElem.addElement("userDuty");
				if (assetInfoList.get(i).getUserDuty() != null
						&& !"".equals(assetInfoList.get(i).getUserDuty())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getUserDuty()));
					if (tempCodeInfo != null) {
						userDuty.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					userDuty.setText("");
				}
				userDuty = null;
				useLife = assetElem.addElement("useLife");
				useLife.setText(assetInfoList.get(i).getUseLife() == null ? ""
						: assetInfoList.get(i).getUseLife());
				useLife = null;
				usePerson = assetElem.addElement("usePerson");
				usePerson
						.setText(assetInfoList.get(i).getUsePerson() == null ? ""
								: assetInfoList.get(i).getUsePerson());
				usePerson = null;
				useState = assetElem.addElement("useState");
				if (assetInfoList.get(i).getUseState() != null
						&& !"".equals(assetInfoList.get(i).getUseState())) {
					tempCodeInfo = codeInfoMap.get(Long.valueOf(assetInfoList
							.get(i).getUseState()));
					if (tempCodeInfo != null) {
						useState.setText(tempCodeInfo.getName());
						tempCodeInfo = null;
					}
				} else {
					useState.setText("");
				}
				useState = null;
				useTime = assetElem.addElement("useTime");
				useTime.setText(assetInfoList.get(i).getUseTime() == null ? ""
						: assetInfoList.get(i).getUseTime());
				useTime = null;
				vendor = assetElem.addElement("vendor");
				if (assetInfoList.get(i).getVendor() != null
						&& !"".equals(assetInfoList.get(i).getVendor())) {
					Supplier supplier;
					try {
						supplier = supplierMap.get(Long.valueOf(assetInfoList
								.get(i).getVendor()));
						if (supplier != null && !"".equals(supplier.getName())) {
							vendor.setText(supplier.getName());
						} else {
							vendor.setText("");
						}
					} catch (NumberFormatException e) {
						vendor.setText("");
					} finally {
						supplier = null;
					}
				} else {
					vendor.setText("");
				}
				vendor = null;
				wxjlDbjl = assetElem.addElement("wxjlDbjl");
				wxjlDbjl
						.setText(assetInfoList.get(i).getWxjlDbjl() == null ? ""
								: assetInfoList.get(i).getWxjlDbjl());
				wxjlDbjl = null;
				wxjlFsfy = assetElem.addElement("wxjlFsfy");
				wxjlFsfy
						.setText(assetInfoList.get(i).getWxjlFsfy() == null ? ""
								: assetInfoList.get(i).getWxjlFsfy());
				wxjlFsfy = null;
				wxjlTime = assetElem.addElement("wxjlTime");
				wxjlTime
						.setText(assetInfoList.get(i).getWxjlTime() == null ? ""
								: assetInfoList.get(i).getWxjlTime());
				wxjlTime = null;
				wxjlZrr = assetElem.addElement("wxjlZrr");
				wxjlZrr.setText(assetInfoList.get(i).getWxjlZrr() == null ? ""
						: assetInfoList.get(i).getWxjlZrr());
				wxjlZrr = null;
				// xh = assetElem.addElement("xh");
				xjpdjlDbjl = assetElem.addElement("xjpdjlDbjl");
				xjpdjlDbjl
						.setText(assetInfoList.get(i).getXjpdjlDbjl() == null ? ""
								: assetInfoList.get(i).getXjpdjlDbjl());
				xjpdjlDbjl = null;
				xjpdjlFsfy = assetElem.addElement("xjpdjlFsfy");
				xjpdjlFsfy
						.setText(assetInfoList.get(i).getXjpdjlFsfy() == null ? ""
								: assetInfoList.get(i).getXjpdjlFsfy());
				xjpdjlFsfy = null;
				xjpdjlTime = assetElem.addElement("xjpdjlTime");
				xjpdjlTime
						.setText(assetInfoList.get(i).getXjpdjlTime() == null ? ""
								: assetInfoList.get(i).getXjpdjlTime());
				xjpdjlTime = null;
				xjpdjlZrr = assetElem.addElement("xjpdjlZrr");
				xjpdjlZrr
						.setText(assetInfoList.get(i).getXjpdjlZrr() == null ? ""
								: assetInfoList.get(i).getXjpdjlZrr());
				xjpdjlZrr = null;
				yuanzhi = assetElem.addElement("yuanzhi");
				yuanzhi.setText(assetInfoList.get(i).getYuanzhi() == null ? ""
						: assetInfoList.get(i).getYuanzhi());
				yuanzhi = null;
				yzjl = assetElem.addElement("yzjl");
				yzjl.setText(assetInfoList.get(i).getYzjl() == null ? ""
						: assetInfoList.get(i).getYzjl());
				yzjl = null;
				zcdbjlDbjl = assetElem.addElement("zcdbjlDbjl");
				zcdbjlDbjl
						.setText(assetInfoList.get(i).getZcdbjlDbjl() == null ? ""
								: assetInfoList.get(i).getZcdbjlDbjl());
				zcdbjlDbjl = null;
				zcdbjlFsfy = assetElem.addElement("zcdbjlFsfy");
				zcdbjlFsfy
						.setText(assetInfoList.get(i).getZcdbjlFsfy() == null ? ""
								: assetInfoList.get(i).getZcdbjlFsfy());
				zcdbjlFsfy = null;
				zcdbjlTime = assetElem.addElement("zcdbjlTime");
				zcdbjlTime
						.setText(assetInfoList.get(i).getZcdbjlTime() == null ? ""
								: assetInfoList.get(i).getZcdbjlTime());
				zcdbjlTime = null;
				zcdbjlZrr = assetElem.addElement("zcdbjlZrr");
				zcdbjlZrr
						.setText(assetInfoList.get(i).getZcdbjlZrr() == null ? ""
								: assetInfoList.get(i).getZcdbjlZrr());
				zcdbjlZrr = null;
				// System.out.println(i+"*"+assetElem.asXML());
				// sb.append(assetElem.asXML());
				assetElem = null;
			}
			allCodeInfoList = null;
			allSupplierList = null;
			assetInfoList = null;

			File tempFile = new File(
					ServletActionContext.getServletContext()
							.getRealPath(
									"jsp" + File.separator + "task"
											+ File.separator + "file"
											+ File.separator + "assetTask.xml"));
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();

			XMLWriter writer = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(tempFile), "UTF-8"), outputFormat);
			writer.write(document);
			writer.close();

//			InputStream is = new FileInputStream(tempFile);
//			int len = 0;
//			byte[] buffers = new byte[1024];
//			this.getServletResponse().reset();
//			this.getServletResponse()
//					.setContentType("application/x-msdownload");
//			String filename = "task" + sdfToDate.format(new Date()) + ".xml";
//			this.getServletResponse().addHeader("Content-Disposition",
//					"attachment;filename=" + filename);
//			// 把文件内容通过输出流打印到页面上供下载
//			while ((len = is.read(buffers)) != -1) {
//				OutputStream os = this.getServletResponse().getOutputStream();
//				os.write(buffers, 0, len);
//				os.flush();
//			}
//			is.close();
//			tempFile.delete();
			// assetInfoList = null;
		}

		return null;
	}

	
}
  
	