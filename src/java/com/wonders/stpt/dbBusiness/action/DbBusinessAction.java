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

package com.wonders.stpt.dbBusiness.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.wonders.stpt.dbBusiness.entity.bo.DbBusiness;
import com.wonders.stpt.dbBusiness.entity.vo.DbCount;
import com.wonders.stpt.dbBusiness.service.DbBusinessService;
import com.wonders.stpt.dbBusiness.util.DateUtils;
import com.wonders.stpt.dbBusiness.util.PageInfo;
import com.wonders.stpt.dbBusiness.util.PageResultSet;
import com.wonders.stpt.dbBusiness.util.TodoItemVo;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class DbBusinessAction extends BaseAjaxAction {
	private DbBusiness dbBusiness = new DbBusiness();
	private DbBusinessService dbBusinessService;
	private TodoItemVo vo = new TodoItemVo();
	private static Properties properties = new Properties();
	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat sdfToDate = new SimpleDateFormat(
			"yyyy-MM-dd");





	private final int size = 20;

	public DbBusinessAction() {
		initProperties();
	}

//	@Override
//	public Object getModel() {
//		return dbBusiness;
//	}

	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public TodoItemVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	public DbBusiness getDbBusiness() {
		return dbBusiness;
	}

	public void setDbBusiness(DbBusiness dbBusiness) {
		this.dbBusiness = dbBusiness;
	}

	public void setDbBusinessService(DbBusinessService dbBusinessService) {
		this.dbBusinessService = dbBusinessService;
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
	 * 显示list页面
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findDbBusinessByPage() {
		String deptCount = this.servletRequest.getParameter("deptCount");
		List list = new ArrayList();
		PageResultSet<Object> result  = new PageResultSet<Object>();
		if(!"yes".equals(deptCount)){
			String creatememo = this.servletRequest.getParameter("creatememo");//督办事项
			String creatTimeStart = this.servletRequest.getParameter("creatTimeStart");
			String creatTimeEnd = this.servletRequest.getParameter("creatTimeEnd");
			String deptName = this.servletRequest.getParameter("deptName");//主办部门
			String userName = this.servletRequest.getParameter("userName");//发起人
			String returnTimeStart = this.servletRequest.getParameter("returnTimeStart");//要求回复时间开始
			String returnTimeEnd = this.servletRequest.getParameter("returnTimeEnd");//要求回复时间结束
			String manageTime = this.servletRequest.getParameter("manageTime");//办理时间
			String beyondDay = this.servletRequest.getParameter("beyondDay");//超期天数
			String isEnd = this.servletRequest.getParameter("isEnd");
			String isFollow = this.servletRequest.getParameter("isFollow");
			String orderValue = this.servletRequest.getParameter("orderValue");
			if(orderValue==null||"".equals(orderValue)){
				orderValue = "2";
			}
			
			this.servletRequest.setAttribute("creatememo", creatememo);
			if(creatTimeStart!=null&&!"".equals(creatTimeStart)){
				this.servletRequest.setAttribute("creatTimeStart", creatTimeStart);
			}
			if(creatTimeEnd!=null&&!"".equals(creatTimeEnd)){
				this.servletRequest.setAttribute("creatTimeEnd", creatTimeEnd);
			}
			this.servletRequest.setAttribute("deptName", deptName);
			//第二行
			this.servletRequest.setAttribute("userName", userName);
			if(returnTimeStart!=null&&!"".equals(returnTimeStart)){
				this.servletRequest.setAttribute("returnTimeStart", returnTimeStart);
			}
			if(returnTimeEnd!=null&&!"".equals(returnTimeEnd)){
				this.servletRequest.setAttribute("returnTimeEnd", returnTimeEnd);
			}
			this.servletRequest.setAttribute("manageTime", manageTime);
			//第三行
			this.servletRequest.setAttribute("beyondDay", beyondDay);
			this.servletRequest.setAttribute("isEnd", isEnd);
			this.servletRequest.setAttribute("isFollow", isFollow);
			this.servletRequest.setAttribute("orderValue", orderValue);
			
			int totalRows = this.dbBusinessService.countDbBusiness(creatememo, creatTimeStart, creatTimeEnd,
					deptName, userName, returnTimeStart,returnTimeEnd, manageTime, beyondDay,isEnd,isFollow);
			PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
			result.setPageInfo(pageinfo);
			list = dbBusinessService.findDbBusinessByPage(orderValue,pageinfo.getBeginIndex(), vo.pageSize ,creatememo, creatTimeStart, creatTimeEnd,
					deptName, userName, returnTimeStart,returnTimeEnd,manageTime,beyondDay,isEnd,isFollow);
		}else{
			list = dbBusinessService.findDbBusinessByPage("9",0, 10000 ,null, null, null,
					null, null, null,null,null,null,null,null);
		}
		Date nowDate = new Date();
		long DAY = 24L * 60L * 60L * 1000L;  
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[]) list.get(i);
			String creatTime = (String) array[1];//立项时间
			String returnTime = (String) array[3];//回复时间
			Date endTime = (Date) array[8];//结束时间
			String tmpManageTime;
			if(null!=endTime){
				tmpManageTime = DateUtils.getDayString(creatTime, DateUtils.Date2String(endTime));
			}else{
				tmpManageTime = DateUtils.getDayString(creatTime, DateUtils.Date2String(nowDate));
			}
			array[10] = tmpManageTime;//办理时间
			
			String normalManageTime = DateUtils.getDayString(creatTime, returnTime);
			Integer beyondDays = Integer.parseInt(tmpManageTime)-Integer.parseInt(normalManageTime);
			if( beyondDays <= 0){
				array[11] = 0;//超期天数
			}else{
				array[11] = beyondDays.toString();
			}
			
			String followOrNot = (String) array[7];//是否跟踪
			String end = (String) array[6];//是否办结
			String status = array[5].toString();//老平台中的办结状态
			if(null!=end){
				if(end.equals("2")){
					array[6] = "已办结";
				}else if(end.equals("1")){
					array[6] = "未办结";
				}
			}else{
				if(null!=followOrNot){
					if(null!=end){
						if(end.equals("2")){
							array[6] = "已办结";
						}else{
							array[6] = "未办结";
						}
					}else{
						array[6] = "未办结";
					}
				}else{
					if(null!=status){
						if(status.equals("1")){
							array[6] = "未办结";
						}else{
							array[6] = "已办结";
						}
					}else{
						array[6] = "未办结";
					}
				}
			}
			
//			if(null!=followOrNot || null!=end){
//				if(end.equals("2")){
//					array[6] = "已办结";
//				}else{
//					array[6] = "未办结";
//				}
			if(null!=followOrNot){
				if(followOrNot.equals("1")){
					array[7] = "简单跟踪";
				}else if(followOrNot.equals("2")){
					array[7] = "按计划跟踪";
				}else{
					array[7] = "不跟踪";
				}
			}else{
				array[7] = "不跟踪";
			}
		}
		
		if("yes".equals(deptCount)){
			List list1 = new ArrayList();
			//String deptName = "";//记录上一条数据的部门名称
			int countAll = 0;
			int countDone = 0;
			int countUnDone = 0;
			int countOverTime = 0;
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Object[] array = (Object[]) list.get(i);
					Object[] arrayNext = null;
					if(i!=(list.size()-1)){
						arrayNext = (Object[]) list.get(i+1);
					}
					countAll += 1;
					if("已办结".equals(String.valueOf(array[6]))){
						countDone += 1;
					}else{
						countUnDone += 1;
					}
					if(!"0".equals(String.valueOf(array[11]))){
						countOverTime += 1;
					}
					if(i==(list.size()-1)||!String.valueOf(array[2]).equals(String.valueOf( arrayNext[2]))){
						DbCount vo = new DbCount();
						vo.setDeptName(String.valueOf(array[2]));
						vo.setCountAll(countAll);
						vo.setCountDone(countDone);
						vo.setCountUnDone(countUnDone);
						vo.setCountOverTime(countOverTime);
						list1.add(vo);
						
						
						countAll = 0;
						countDone = 0;
						countUnDone = 0;
						countOverTime = 0;
					}
					//deptName = String.valueOf(array[2]);
				}
			}
			list = list1;
		}
		
		result.setList(list);			
		this.servletRequest.setAttribute("result", result);
		if("yes".equals(deptCount)){
			return "count";
		}
		return SUCCESS;
	}



	/**
	 * 显示view页面
	 * 
	 * @throws Exception
	 */
	public String toDbBusinessView(){
		String id = this.servletRequest.getParameter("id");
		String deptName = this.servletRequest.getParameter("deptName");//主办部门
		List list = dbBusinessService.findDbBusinessByIdAndDeptName(id, deptName);
		//System.out.println("list.size======"+list.size());
		Date nowDate = new Date();
		long DAY = 24L * 60L * 60L * 1000L;  
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[]) list.get(i);
			String creatTime = (String) array[1];//立项时间
			String returnTime = (String) array[3];//回复时间
			Date endTime = (Date) array[8];//结束时间
			String tmpManageTime;
			if(null!=endTime){
				tmpManageTime = DateUtils.getDayString(creatTime, DateUtils.Date2String(endTime));
			}else{
				tmpManageTime = DateUtils.getDayString(creatTime, DateUtils.Date2String(nowDate));
			}
			array[10] = tmpManageTime;//办理时间
			
			String normalManageTime = DateUtils.getDayString(creatTime, returnTime);
			Integer beyondDays = Integer.parseInt(tmpManageTime)-Integer.parseInt(normalManageTime);
			if( beyondDays <= 0){
				array[11] = 0;//超期天数
			}else{
				array[11] = beyondDays.toString();
			}
			
			String followOrNot = (String) array[7];//是否跟踪
			String end = (String) array[6];//是否办结
			String status = array[5].toString();//老平台中的办结状态
			if(null!=end){
				if(end.equals("2")){
					array[6] = "已办结";
				}else if(end.equals("1")){
					array[6] = "未办结";
				}
			}else{
				if(null!=followOrNot){
					if(null!=end){
						if(end.equals("2")){
							array[6] = "已办结";
						}else{
							array[6] = "未办结";
						}
					}else{
						array[6] = "未办结";
					}
				}else{
					if(null!=status){
						if(status.equals("1")){
							array[6] = "未办结";
						}else{
							array[6] = "已办结";
						}
					}else{
						array[6] = "未办结";
					}
				}
			}
//			if(null!=followOrNot || null!=end){
//				if(end.equals("2")){
//					array[6] = "已办结";
//				}else{
//					array[6] = "未办结";
//				}
			if(null!=followOrNot){
				if(followOrNot.equals("1")){
					array[7] = "简单跟踪";
				}else if(followOrNot.equals("2")){
					array[7] = "计划跟踪";
				}
			}
		}
		PageResultSet<Object> result  = new PageResultSet<Object>();
		result.setList(list);
		this.servletRequest.setAttribute("result", result);
//		this.servletRequest.setAttribute("list", list);
		return SUCCESS;
	}
	

	/**
	 * 删除
	 */
	public String delete() {
		String id = servletRequest.getParameter("id");
		dbBusinessService.deleteTaskById(id);
		return AJAX;
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

	// 根据DbBusiness.taskmemoFilter(String类型)字段的值，得到分页查询AssetInfo的条件filter
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
}
  
	