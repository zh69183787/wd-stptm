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

package com.wonders.stpt.hiddenDangersCorrect.action;

import com.wonders.stpt.export.ExportExcel;
import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HdcChangeLog;
import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HiddenDangersCorrect;
import com.wonders.stpt.hiddenDangersCorrect.entity.vo.HiddenDangersCorrectVO;
import com.wonders.stpt.hiddenDangersCorrect.service.HdcChangeLogService;
import com.wonders.stpt.hiddenDangersCorrect.service.HiddenDangersCorrectService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HiddenDangersCorrectAction extends BaseAjaxAction {
	private HiddenDangersCorrect hiddenDangersCorrect = new HiddenDangersCorrect();
	private HiddenDangersCorrectVO hiddenDangersCorrectVO = new HiddenDangersCorrectVO();
	private HiddenDangersCorrectService hiddenDangersCorrectService;
    private HdcChangeLogService hdcChangeLogService;
	@Override
	public ValueObject getValueObject() {
		return this.hiddenDangersCorrectVO;
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

	public String findHiddenDangersCorrectById() {
		String id = super.getServletRequest().getParameter("id");
		String type = super.getServletRequest().getParameter("type");

		HiddenDangersCorrect bo = hiddenDangersCorrectService
				.findHiddenDangersCorrectById(id);
//		if (bo != null) {
//			String json = VOUtils.getJsonData(convertBOToVO(bo));
//			createJSonData("{\"success\":true,\"result\":[" + json.toString()
//					+ "]}");
//		}
		hiddenDangersCorrectVO = convertBOToVO(bo);
		if ("edit".equals(type)){
		  return "edit";
		}			
		else return "view";
	}

	public String findHiddenDangersCorrectByPage() throws ParseException{
		Page page;
		String lastDateStart = this.servletRequest.getParameter("lastDateStart");
		String lastDateEnd = this.servletRequest.getParameter("lastDateEnd");
		String finishDateStart = this.servletRequest.getParameter("finishDateStart");
		String finishDateEnd = this.servletRequest.getParameter("finishDateEnd");
		String inputDateStart = this.servletRequest.getParameter("inputDateStart");
		String inputDateEnd = this.servletRequest.getParameter("inputDateEnd");
		String currentPageStr = this.servletRequest.getParameter("number");	
		Cookie[] cookies = this.servletRequest.getCookies();
		String operateType = this.servletRequest.getParameter("operateType");
		String companyId = this.servletRequest.getParameter("companyId");
		
		String deptId = null;
		for (Cookie cookie:cookies) {
			if("deptId".equals(cookie.getName()))
			{
				deptId = cookie.getValue();
				
				break;
			}
		}
		if(companyId==null){
			try {
				companyId = getNodesInfo(deptId,"pid");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
		JSONObject obj = JSONObject.fromObject(this.hiddenDangersCorrectVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.hiddenDangersCorrectVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
	    this.servletRequest.setAttribute("operateType", operateType);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=lastDateStart&&!"".equals(lastDateStart)){	
			Date date =  format.parse(lastDateStart);
			filter.put("lastDateStart", date);
	        this.servletRequest.setAttribute("lastDateStart", lastDateStart);
		}
		if(null!=lastDateEnd&&!"".equals(lastDateEnd)){	
			Date date =  format.parse(lastDateEnd);
			filter.put("lastDateEnd", date);
	        this.servletRequest.setAttribute("lastDateEnd", lastDateEnd);
		}
		if(null!=finishDateStart&&!"".equals(finishDateStart)){	
			Date date =  format.parse(finishDateStart);
			filter.put("finishDateStart", date);
	        this.servletRequest.setAttribute("finishDateStart", finishDateStart);
		}
		if(null!=finishDateEnd&&!"".equals(finishDateEnd)){	
			Date date =  format.parse(finishDateEnd);
			filter.put("finishDateEnd", date);
	        this.servletRequest.setAttribute("finishDateEnd", finishDateEnd);
		}
		if(null!=inputDateStart&&!"".equals(inputDateStart)){	
			Date date =  format.parse(inputDateStart);
			filter.put("inputDateStart", date);
	        this.servletRequest.setAttribute("inputDateStart", inputDateStart);
		}
		if(null!=inputDateEnd&&!"".equals(inputDateEnd)){	
			Date date =  format.parse(inputDateEnd);
			filter.put("inputDateEnd", date);
	        this.servletRequest.setAttribute("inputDateEnd", inputDateEnd);
		}
		if (null != deptId && !"".equals(deptId)) {
			filter.put("deptId", deptId);
			this.servletRequest.setAttribute("deptId", deptId);
		}
		if (null != companyId && !"".equals(companyId)) {
			filter.put("companyId", companyId);
			this.servletRequest.setAttribute("companyId", companyId);
		}
		String ifExport = this.servletRequest.getParameter("ifExport");
		if("yes".equals(ifExport)){//导出excel
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename=dangersExport.xls"); 
			List<Object[]> dataset = new ArrayList<Object[]>();
			dataset = hiddenDangersCorrectService.findForExport(filter, operateType);
			String[] headers = {"线路/处所","隐患情况","整改措施","责任人","督办单位","所属专业","填报日期","隐患类型","整改时限","填报单位","填报人","落实情况","实际完成时间","重要程度","责任单位/部门","审核状态","备注"};
			short[] width = {5000,10000,10000,3000,8000,3000,4000,3000,4000,8000,3000,3000,4000,3000,10000,3000,10000};
			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("隐患排查数据导出", headers, dataset, out,width); 
				out.close();  

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return null;
		}else{
			if (currentPage == 0) {
				page = hiddenDangersCorrectService.findHiddenDangersCorrectByPage(
						filter, start / size + 1, size, operateType);
			}else {
				page = hiddenDangersCorrectService.findHiddenDangersCorrectByPage(
						filter, currentPage, size, operateType);
			}
			this.servletRequest.setAttribute("page", page);
//			String json = VOUtils.getJsonDataFromPage(page,
//					HiddenDangersCorrect.class);
//			createJSonData(json);
			return SUCCESS;
		}
		
	}

	public String deleteHiddenDangersCorrect() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			HiddenDangersCorrect bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (HiddenDangersCorrect) JSONObject.toBean(obj,
						HiddenDangersCorrect.class);
				if (bean != null) {
					hiddenDangersCorrectService
							.deleteHiddenDangersCorrect(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addHiddenDangersCorrect() {
		HiddenDangersCorrect hiddenDangersCorrect = convertVOToBO(hiddenDangersCorrectVO);
		
		if (hiddenDangersCorrect != null) {
			Date date = new Date();
			hiddenDangersCorrect.setCreateTime(date);
			hiddenDangersCorrect.setCheckState("1");
			hiddenDangersCorrectService
					.addHiddenDangersCorrect(hiddenDangersCorrect);
		}
		//createJSonData("{\"success\":true}");
		return SUCCESS;
	}

	public String updateHiddenDangersCorrect() {
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
					HiddenDangersCorrectVO bean = (HiddenDangersCorrectVO) JSONObject
							.toBean(obj, HiddenDangersCorrectVO.class);
					hiddenDangersCorrectService.addHiddenDangersCorrect(this
							.convertVOToBO(bean));
				} else {
					hiddenDangersCorrectService
							.updateHiddenDangersCorrect((HiddenDangersCorrect) JSONObject
									.toBean(obj, HiddenDangersCorrect.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private HiddenDangersCorrect convertVOToBO(
			HiddenDangersCorrectVO hiddenDangersCorrectVO) {
		HiddenDangersCorrect hiddenDangersCorrect = new HiddenDangersCorrect();
		BeanUtils.copyProperties(hiddenDangersCorrectVO, hiddenDangersCorrect,
				new String[] { "id" });
		return hiddenDangersCorrect;
	}

	private HiddenDangersCorrectVO convertBOToVO(
			HiddenDangersCorrect hiddenDangersCorrect) {
		HiddenDangersCorrectVO hiddenDangersCorrectVO = new HiddenDangersCorrectVO();
		BeanUtils.copyProperties(hiddenDangersCorrect, hiddenDangersCorrectVO);
		return hiddenDangersCorrectVO;
	}

	public HiddenDangersCorrectVO getHiddenDangersCorrectVO() {
		return hiddenDangersCorrectVO;
	}

	public void setHiddenDangersCorrectVO(
			HiddenDangersCorrectVO hiddenDangersCorrectVO) {
		this.hiddenDangersCorrectVO = hiddenDangersCorrectVO;
	}

	public void setHiddenDangersCorrectService(
			HiddenDangersCorrectService hiddenDangersCorrectService) {
		this.hiddenDangersCorrectService = hiddenDangersCorrectService;
	}

    public HdcChangeLogService getHdcChangeLogService() {
        return hdcChangeLogService;
    }

    public void setHdcChangeLogService(HdcChangeLogService hdcChangeLogService) {
        this.hdcChangeLogService = hdcChangeLogService;
    }

    /**
     * @author sunjiawei
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @describe 添加页面跳转
     */	
	public String toAdd() throws FileNotFoundException, IOException {				
		Date date = new Date();
		hiddenDangersCorrectVO.setInputDate(date);
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String deptId = "";
//		String deptName = "";
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie:cookies) {
//			if("deptId".equals(cookie.getName()))
//			{
//				deptId = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
//				break;
//			}
//		}
//		for (Cookie cookie:cookies) {
//			if("deptName".equals(cookie.getName()))
//			{
//				deptName = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
//				break;
//			}
//		}
//		String pid = getNodesInfo(deptId,"pid");
//		String deptNameDescription = getNodesInfo(deptId,"description");
//		if("2500".equals(pid)){
//			deptName = deptNameDescription.replace("集团-", "");
//		}else if(deptNameDescription.indexOf("-")>-1){
//			deptName = deptNameDescription.substring(0,deptNameDescription.indexOf("-"));
//		}
//		hiddenDangersCorrectVO.setInputDept(deptName);
		return SUCCESS;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 根据主键修改
	 * @return String	 
	 */
	public String updateHiddenDangersCorrectById(){		
		HiddenDangersCorrect hiddenDangersCorrect = convertVOToBO(hiddenDangersCorrectVO);
		String id = hiddenDangersCorrectVO.getId();
		hiddenDangersCorrect.setId(id);	
		HiddenDangersCorrect bo = hiddenDangersCorrectService.findHiddenDangersCorrectById(id);
		hiddenDangersCorrect.setCreatePerson(bo.getCreatePerson());
		hiddenDangersCorrect.setCreateTime(bo.getCreateTime());
		hiddenDangersCorrect.setInputDept(bo.getInputDept());
		Date date = new Date();
		hiddenDangersCorrect.setEditTime(date);
		hiddenDangersCorrect.setCheckState("1");

        //zhoushun 20150323 判断整改时限是否修改
        if(hiddenDangersCorrect.getLastDate()!=null && bo.getLastDate()!=null &&
                bo.getLastDate().compareTo(hiddenDangersCorrect.getLastDate()) !=0 ){
            HdcChangeLog log = new HdcChangeLog(id,bo.getLastDate(),hiddenDangersCorrect.getLastDate(),hiddenDangersCorrect.getCreatePerson());
            this.hdcChangeLogService.addLog(log);
        }
        //zhoushun 20150323 判断整改时限是否修改

        if (hiddenDangersCorrect != null) {
			hiddenDangersCorrectService.updateHiddenDangersCorrect(hiddenDangersCorrect);			
		}
		return SUCCESS;
	}
	
	public void deleteById() throws ParseException {
		String id = this.servletRequest.getParameter("id");	
		HiddenDangersCorrect bo = hiddenDangersCorrectService.findHiddenDangersCorrectById(id);
		if (bo != null) {
			hiddenDangersCorrectService.deleteHiddenDangersCorrect(bo);
//			hiddenDangersCorrectVO.setId(null);
//			hiddenDangersCorrectVO.setWhichTable(whichTable);
//			findHiddenDangersCorrectByPage();
		}
		
	}
	
	/**
	 * 查询责任单位/部门
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String findOwnerWithFuzzySearch() throws FileNotFoundException, IOException{
//		servletResponse.setContentType("application/json;charset=UTF-8");          
//		servletResponse.setCharacterEncoding("UTF-8");
		
		Properties properties = loadProperties("config.properties");
		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String appName = properties.getProperty("appName");
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String secret = properties.getProperty("secret");//config.propertites中读取
		String dataParams = request.getParameter("dataParams");
		//String dataParams = new String(request.getParameter("dataParams").getBytes("iso-8859-1"),"utf-8"); 

		String serverUrl = properties.getProperty("urlCa").toString() + properties.getProperty("serverPath").toString();
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
					+ "&method=" + method + "&dataParams=" + dataParams+ "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
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
		return AJAX;
	}
	
	//读取配置文件
	public Properties loadProperties(String fileName){
		String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			System.out.println("配置文件没有找到！");
		} catch (IOException e) {
			System.out.println("读取配置文件失败！");
		}
		return props;
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
	
	/**
	 * 通过CA的getNodesInfo借口得到部门信息
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String getNodesInfo(String deptId,String valueType) throws FileNotFoundException, IOException{
//		servletResponse.setContentType("application/json;charset=UTF-8");          
//		servletResponse.setCharacterEncoding("UTF-8");
		
		Properties properties = loadProperties("config.properties");
		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String appName = properties.getProperty("appName");
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie:cookies) {
			if("token".equals(cookie.getName()))
			{
				token = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
				break;
			}
		}
		String method = "getNodesInfo";
		String secret = properties.getProperty("secret");//config.propertites中读取
		String dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><id>"+deptId+"</id></dataParams>";
		//String dataParams = new String(request.getParameter("dataParams").getBytes("iso-8859-1"),"utf-8"); 

		String serverUrl = properties.getProperty("urlCa").toString() + properties.getProperty("serverPath").toString();
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
					+ "&method=" + method + "&dataParams=" + dataParams+ "&sign="
					+ getMD5(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");
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
				//JSONArray resultArray = new JSONArray();
				//System.out.println("textEntity==========="+textEntity);
				JSONObject array = JSONObject.fromObject(textEntity);
				//getServletResponse().setCharacterEncoding("utf-8");
				//this.getServletResponse().getWriter().print(textEntity);
				String returnValue = "";
				if("100".equals(array.getString("code"))){
					JSONObject paramData = array.optJSONObject("params").optJSONObject("param");
					if("pid".equals(valueType)){
						returnValue = paramData.getString("pid");
					}else if("description".equals(valueType)){
						returnValue = paramData.getString("description");
					}
				}
				return returnValue;
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
	
	public void updateCheckState(){
		String check_state = this.servletRequest.getParameter("check_state");
		String id = this.servletRequest.getParameter("id");
		hiddenDangersCorrectService.updateCheckState(check_state,id);
	}


    /**
     * 隐患排查历史记录
     * @return
     * @throws ParseException
     */
    public String findHdcLogByPage() throws ParseException{
        Page page;
        String orgLastDateStart = this.servletRequest.getParameter("orgLastDateStart");
        String orgLastDateEnd = this.servletRequest.getParameter("orgLastDateEnd");

        String nowLastDateStart = this.servletRequest.getParameter("nowLastDateStart");
        String nowLastDateEnd = this.servletRequest.getParameter("nowLastDateEnd");

        String finishDateStart = this.servletRequest.getParameter("finishDateStart");
        String finishDateEnd = this.servletRequest.getParameter("finishDateEnd");
        String inputDateStart = this.servletRequest.getParameter("inputDateStart");
        String inputDateEnd = this.servletRequest.getParameter("inputDateEnd");
        String currentPageStr = this.servletRequest.getParameter("number");
        Cookie[] cookies = this.servletRequest.getCookies();
        String operateType = this.servletRequest.getParameter("operateType");
        String companyId = this.servletRequest.getParameter("companyId");

        String deptId = null;
        for (Cookie cookie:cookies) {
            if("deptId".equals(cookie.getName()))
            {
                deptId = cookie.getValue();

                break;
            }
        }
        if(companyId==null){
            try {
                companyId = getNodesInfo(deptId,"pid");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
        JSONObject obj = JSONObject.fromObject(this.hiddenDangersCorrectVO);
        Iterator it = obj.keys();
        while (it.hasNext()) {
            key = (String) it.next();
            value = this.servletRequest.getParameter(key);
            if (value != null && value.trim().length() > 0) {
                Object res = this.getValueByParamName(
                        this.hiddenDangersCorrectVO, key);
                if (res != null) {
                    filter.put(key, res);
                }
            }
        }
        this.servletRequest.setAttribute("operateType", operateType);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(null!=orgLastDateStart&&!"".equals(orgLastDateStart)){
            Date date =  format.parse(orgLastDateStart);
            filter.put("orgLastDateStart", date);
            this.servletRequest.setAttribute("orgLastDateStart", orgLastDateStart);
        }
        if(null!=orgLastDateEnd&&!"".equals(orgLastDateEnd)){
            Date date =  format.parse(orgLastDateEnd);
            filter.put("orgLastDateEnd", date);
            this.servletRequest.setAttribute("orgLastDateEnd", orgLastDateEnd);
        }
        if(null!=nowLastDateStart&&!"".equals(nowLastDateStart)){
            Date date =  format.parse(nowLastDateStart);
            filter.put("nowLastDateStart", date);
            this.servletRequest.setAttribute("nowLastDateStart", nowLastDateStart);
        }
        if(null!=nowLastDateEnd&&!"".equals(nowLastDateEnd)){
            Date date =  format.parse(nowLastDateEnd);
            filter.put("nowLastDateEnd", date);
            this.servletRequest.setAttribute("nowLastDateEnd", nowLastDateEnd);
        }
        if(null!=finishDateStart&&!"".equals(finishDateStart)){
            Date date =  format.parse(finishDateStart);
            filter.put("finishDateStart", date);
            this.servletRequest.setAttribute("finishDateStart", finishDateStart);
        }
        if(null!=finishDateEnd&&!"".equals(finishDateEnd)){
            Date date =  format.parse(finishDateEnd);
            filter.put("finishDateEnd", date);
            this.servletRequest.setAttribute("finishDateEnd", finishDateEnd);
        }
        if(null!=inputDateStart&&!"".equals(inputDateStart)){
            Date date =  format.parse(inputDateStart);
            filter.put("inputDateStart", date);
            this.servletRequest.setAttribute("inputDateStart", inputDateStart);
        }
        if(null!=inputDateEnd&&!"".equals(inputDateEnd)){
            Date date =  format.parse(inputDateEnd);
            filter.put("inputDateEnd", date);
            this.servletRequest.setAttribute("inputDateEnd", inputDateEnd);
        }
        if (null != deptId && !"".equals(deptId)) {
            filter.put("deptId", deptId);
            this.servletRequest.setAttribute("deptId", deptId);
        }
        if (null != companyId && !"".equals(companyId)) {
            filter.put("companyId", companyId);
            this.servletRequest.setAttribute("companyId", companyId);
        }

        if (currentPage == 0) {
            page = hdcChangeLogService.findHdcChangeLogByPage(
                    filter, start / size + 1, size, operateType);
        }else {
            page = hdcChangeLogService.findHdcChangeLogByPage(
                    filter, currentPage, size, operateType);
        }
        this.servletRequest.setAttribute("page", page);
//			String json = VOUtils.getJsonDataFromPage(page,
//					HiddenDangersCorrect.class);
//			createJSonData(json);
        return SUCCESS;

    }

    public String findHdcLogById() {
        String id = super.getServletRequest().getParameter("id");
        String logId = super.getServletRequest().getParameter("logId");
        String type = super.getServletRequest().getParameter("type");

        HiddenDangersCorrect bo = hiddenDangersCorrectService
                .findHiddenDangersCorrectById(id);
        HdcChangeLog logBo = hdcChangeLogService.findHdcChangeLogById(logId);
//		if (bo != null) {
//			String json = VOUtils.getJsonData(convertBOToVO(bo));
//			createJSonData("{\"success\":true,\"result\":[" + json.toString()
//					+ "]}");
//		}
        hiddenDangersCorrectVO = convertBOToVO(bo);
        servletRequest.setAttribute("logBo",logBo);
        return SUCCESS;
    }
}
