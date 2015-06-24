
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.annualLeave.service.HolConfigService;
import com.wonders.stpt.annualLeave.utils.CookieUtils;
import com.wonders.stpt.annualLeave.utils.DownloadUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * 设置公休
 * @author ycl
 * @version $Revision$ 
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */
 
@SuppressWarnings("serial")
public class HolConfigAction extends BaseAjaxAction { 
	private HolConfig holConfig = new HolConfig();
	private HolConfigService holConfigService;
	private File file;
	private String fileFileName;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int size = 20;

	public HolConfig getHolConfig() {
		return holConfig;
	}

	public void setHolConfig(HolConfig holConfig) {
		this.holConfig = holConfig;
	}

	public HolConfigService getHolConfigService() {
		return holConfigService;
	}

	public void setHolConfigService(HolConfigService holConfigService) {
		this.holConfigService = holConfigService;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	@Override
	public Object getModel() {
		return holConfig;
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
	@SuppressWarnings("unchecked")
	public String showList(){
		String pageNo = servletRequest.getParameter("pageNo");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "0";
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.holConfig);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.holConfig, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		String beginDate =  servletRequest.getParameter("beginDate");
		String endDate = servletRequest.getParameter("endDate");
		filter.put("removed", 0l);
		if(StringUtils.isNotEmpty(beginDate)){
			filter.put("beginDate", beginDate);
			servletRequest.setAttribute("beginDate", beginDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			filter.put("endDate", endDate);
			servletRequest.setAttribute("endDate", endDate);
		}
		Page page = holConfigService.findHolConfigByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);
		//String json = VOUtils.getJsonDataFromPage(page, HolidaysSet.class);
		//createJSonData(json);
		return SUCCESS;
	}
	
	public String showAdd(){
		return SUCCESS;
	}
	
	public String showEdit() throws Exception{
		String id = servletRequest.getParameter("id");
		HolConfig holConfig = holConfigService.load(id);
		servletRequest.setAttribute("holConfig", holConfig);
		return SUCCESS;
	}
	
	public void saveHolConfig(){
		try {
			boolean hasDay = holConfigService.isHolConfigExist(holConfig.getHdate());
			if(hasDay){
				servletResponse.getWriter().write("exist");
			}else{
				//holConfig.setUpdateOrInput("0");//标记为页面添加
				String date = holConfig.getHdate();
				if(StringUtils.isNotEmpty(date)){
					holConfig.setHyear(date.substring(0,4));
					holConfig.setHmonth(date.substring(5,7));
					holConfig.setHday(date.substring(8,10));
				}
				holConfig.setOperateTime(sdf.format(new Date()));
				Object o = CookieUtils.getCookieValue(servletRequest, "userId");
				if(o!=null){
					holConfig.setOperator(Long.valueOf(o.toString()));
				}
				holConfig.setRemoved(0l);
				holConfigService.addHolConfig(holConfig);
				
				
				servletResponse.getWriter().write("success");
			}
		} catch (Exception e) {
			logger.error("保存节假日记录出错！！"+e);
			e.printStackTrace();
		}
	}
	
	public void saveEditHolConfig(){
		try {
			boolean hasDay = holConfigService.isHolConfigExist(holConfig.getId(), holConfig.getHdate());
			if(hasDay){
				servletResponse.getWriter().write("exist");
			}else{
				HolConfig old = holConfigService.load(holConfig.getId());
				if(old!=null){
					old.setHdate(holConfig.getHdate());
					String date = holConfig.getHdate();
					if(StringUtils.isNotEmpty(date)){
						old.setHyear(date.substring(0,4));
						old.setHmonth(date.substring(5,7));
						old.setHday(date.substring(8,10));
					}
					old.setOperateTime(sdf.format(new Date()));
					Object o = CookieUtils.getCookieValue(servletRequest, "userId");
					if(o!=null){
						old.setOperator(Long.valueOf(o.toString()));
					}
					old.setMemo(holConfig.getMemo());
					old.setRemoved(0l);
					
					holConfigService.save(old);
					servletResponse.getWriter().write("success");
				}
			}
		} catch (Exception e) {
			logger.error("保存节假日记录出错！！"+e);
			e.printStackTrace();
		}
	}
	
	public void deleteHolConfig(){
		try {
			String id = getServletRequest().getParameter("id");
			holConfigService.deleteByIdLogically(id);
		} catch (Exception e) {
			logger.error("删除节假日出错！"+e);
			e.printStackTrace();
		}
		
	}
	
	public String showUpload(){
		return SUCCESS;
	}
	
	public void batchUploadHolConfig() throws IOException{
		if(file!=null && file.length()>0){
			String operator = servletRequest.getParameter("userId");
			HSSFRow row;
			//HSSFCell cell;
			String cellData;
			InputStream is = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			List<HolConfig> saveList = new ArrayList<HolConfig>();
			String errorLine = "";
			int successNum = 0;
			for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
				HolConfig holConfig = new HolConfig();
				row = sheet.getRow(i);
				
				cellData = getCellData(row,0);		//日期
				
				if(StringUtils.isNotEmpty(cellData)){
					String reg = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(cellData);
					boolean dateFlag = m.matches();
					if(dateFlag){		//满足yyyy-mm-dd格式
						List<HolConfig> old = holConfigService.findByDay(cellData);
						if(old!=null && old.size()==1){		//更新
							holConfig = old.get(0);
						}
						holConfig.setHdate(cellData);
						holConfig.setHyear(cellData.substring(0,4));
						holConfig.setHmonth(cellData.substring(5,7));
						holConfig.setHday(cellData.substring(8,10));
						
						cellData = getCellData(row, 1);		//名称
						holConfig.setMemo(cellData);
						
						cellData = getCellData(row, 2);
						holConfig.setOperateTime(sdf.format(new Date()));
						//holConfig.setOperator(operator);
						
						
						if(StringUtils.isNotEmpty(operator)){
							holConfig.setOperator(Long.valueOf(operator));
						}
						holConfig.setRemoved(0l);
						saveList.add(holConfig);
						successNum++;
					}else{
						errorLine+=(i+1)+",";
					}
				}
			}
			String msg ="";
			if(errorLine!=null && !"".equals(errorLine)){
				msg = "上传失败！第"+errorLine.substring(0,errorLine.length()-1)+"行日期格式错误，请修改后重新上传！";
			}else{
				try {
					holConfigService.saveOrUpdateAll(saveList);
					msg ="上传成功！共有"+successNum+"条数据解析入库！";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			servletResponse.setContentType("text/html");
			servletResponse.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			this.servletResponse.getWriter().print("{'msg':'" + msg + "'}");
		}
	}
	
		/**
		 * @author ycl
		 * @param row 行
		 * @param no 列
		 * @return String
		 */
		@SuppressWarnings("deprecation")
		public String getCellData(HSSFRow row,int no){
			if(row!=null){
				HSSFCell cell = row.getCell((short) no);
				if(cell!=null){
					return cell.toString().trim();
				}else{
					return null;
				}
			}else{
				return null;
			}
			
		}
	
	
	
		/**
		 * 下载模板
		 * @throws IOException 
		 */
		@SuppressWarnings("deprecation")
		public void downloadTemplete() throws IOException {
			String filePath = servletRequest.getRealPath("annualLeave/holConfig")+ File.separator + "templete.xls";
			DownloadUtil.downloadFile(servletResponse, filePath);
		}
		
		
		
	public String findHolConfigDays() throws IOException, ParseException{
		String startDate = servletRequest.getParameter("startDate");
		String endDate = servletRequest.getParameter("endDate");
		List<HolConfig> list = holConfigService.findBetweenHdate(startDate, endDate);
		
		Calendar start = Calendar.getInstance();
		start.setTime(sdf.parse(startDate));
		Calendar end = Calendar.getInstance();
		end.setTime(sdf.parse(endDate));
		
		List<String> allDate = new ArrayList<String>();
		
		if(startDate.equals(endDate)){
			allDate.add(startDate);
		}else{
			while (!sdf.format(start.getTime()).equals(sdf.format(end.getTime()))) {
				allDate.add(sdf.format(start.getTime()));
				start.set(Calendar.DAY_OF_MONTH, start.get(Calendar.DAY_OF_MONTH)+1);
			}
			allDate.add(sdf.format(start.getTime()));
		}
		
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				for(int m=0;m<allDate.size();m++){
					if(allDate.get(m).equals(list.get(i).getHdate())){
						allDate.remove(m);
						break;
					}
				}
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(allDate);
		
		servletResponse.setContentType("text/html");
		servletResponse.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
		servletResponse.getWriter().print(jsonArray.toString());
		return AJAX;
	}
		
}
	
