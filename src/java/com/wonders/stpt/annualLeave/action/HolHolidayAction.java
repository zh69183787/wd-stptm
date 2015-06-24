
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.VUserdep;
import com.wonders.stpt.annualLeave.service.HolHolidayService;
import com.wonders.stpt.annualLeave.utils.DownloadUtil;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */
 
@SuppressWarnings("serial")
public class HolHolidayAction extends BaseAjaxAction { 
	private HolHoliday holHoliday = new HolHoliday();
	private HolHolidayService holHolidayService;
	private File file;
	private String fileFileName;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int size = 20;
	
	public HolHoliday getHolHoliday() {
		return holHoliday;
	}

	public void setHolHoliday(HolHoliday holHoliday) {
		this.holHoliday = holHoliday;
	}

	public HolHolidayService getHolHolidayService() {
		return holHolidayService;
	}

	public void setHolHolidayService(HolHolidayService holHolidayService) {
		this.holHolidayService = holHolidayService;
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
		return holHoliday;
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
		String holName = servletRequest.getParameter("holName");
		String holLoginName = servletRequest.getParameter("holLoginName");
		String deptName = servletRequest.getParameter("deptName");
		if(StringUtils.isEmpty(pageNo)){
			pageNo = "1";
		}
		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.holHoliday);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.holHoliday, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if(StringUtils.isNotEmpty(holName)){
			filter.put("holName", holName);
			servletRequest.setAttribute("holName", holName);
		}
		if(StringUtils.isNotEmpty(holLoginName)){
			filter.put("holLoginName",holLoginName);
			servletRequest.setAttribute("holLoginName", holLoginName);
		}
		if(StringUtils.isNotEmpty(deptName)){
			filter.put("deptName",deptName);
			servletRequest.setAttribute("deptName", deptName);
		}
		
		Page page = holHolidayService.getHoliday(filter, Integer.valueOf(pageNo), size);
		
//		if(page!=null && page.getResult()!=null){
//			List<VUserdep> userList = new ArrayList<VUserdep>();
//			List<HolHoliday> list = page.getResult();
//			for(int i=0,length=list.size(); i<length; i++){
//				VUserdep user = holHolidayService.findVUserdepById(Long.valueOf(list.get(i).getHolId()));
//				userList.add(user);
//			}
//			servletRequest.setAttribute("userList", userList);
//		}
		servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String showUpload(){
		return SUCCESS;
	}
	
	/**
	 * 下载模板
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public void downloadTemplete() throws IOException {
		String filePath = servletRequest.getRealPath("annualLeave/holHolidays")+ File.separator + "templete.xls";
		DownloadUtil.downloadFile(servletResponse, filePath);
	}

	
	public void batchUploadHolHoldays() throws IOException{
		if(file!=null && file.length()>0){
			HSSFRow row;
			//HSSFCell cell;
			InputStream is = new FileInputStream(file);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// 获得excel中的第一张表
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			List<HolHoliday> saveList = new ArrayList<HolHoliday>();
			String yearError = "";
			String repeatError="";
			String dayError="";
			String loginNameError="";
			String deptError="";
			String operatorId = servletRequest.getParameter("userId");
			
			for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
				HolHoliday holHoliday = new HolHoliday();
				row = sheet.getRow(i);
				
				String year = getCellData(row,0);				//年度
				String loginName =getCellData(row, 1);			//工号
				String dayCounts = getCellData(row, 2);			//天数
				String remark = getCellData(row, 3);			//备注
				
				String reg = "[0-9]{4}";
				Pattern p = Pattern.compile(reg);
				Matcher m = p.matcher(year);
				boolean yearFlag = m.matches();
				if(!yearFlag){
					yearError+=(i+1)+","; 
					continue;
				}
				
				List<CsUser> userList = holHolidayService.findUsersByLoginName(loginName);
				if(userList==null || userList.size()<1){
					loginNameError+=(i+1)+",";
					continue;
				}
				CsUser csUser = userList.get(0);
				List<HolHoliday> list = holHolidayService.findByYearAndHolPersonId(year, csUser.getId()+"");
				if(list!=null && list.size()>0){
					repeatError+=(i+1)+",";
					continue;
				}
				
				//holHoliday.setHolName(csUser.getName());
				//holHoliday.setHolLoginName(csUser.getLoginName());
				//holHoliday.setDeptName(csUser.getDept());
				holHoliday.setHolId(csUser.getId()+"");
				//holHoliday.setHolId(loginName);
				
				if(!StringUtils.isNumeric(dayCounts)){
					dayCounts+=(i+1)+",";
					continue;
				}
				
				holHoliday.setHolYear(year);
				holHoliday.setHolDays(Long.valueOf(dayCounts));
				holHoliday.setRemark(remark);
				
				HolHoliday last = this.holHolidayService.findLastHolidaysSetByholPersonId(loginName);		//同工号，上一次的数据
				if(last!=null){
					holHoliday.setHolDaysLeft(last.getHolDaysLeft()+holHoliday.getHolDays());
					holHoliday.setHolDaysWait(last.getHolDaysWait());
				}else{
					holHoliday.setHolDaysLeft(holHoliday.getHolDays()); 
					holHoliday.setHolDaysWait(0l);
				}
				holHoliday.setRemoved(0l);
				if(StringUtils.isNotEmpty(operatorId)){
					holHoliday.setOperator(Long.valueOf(operatorId));
				}
				holHoliday.setOperateTime(sdf.format(new Date()));
				saveList.add(holHoliday);
				
			}
			
			servletResponse.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			servletResponse.setContentType("ajax");
			String msg ="";
			if(StringUtils.isEmpty(yearError) && StringUtils.isEmpty(dayError) && StringUtils.isEmpty(repeatError) && StringUtils.isEmpty(loginNameError)){
				try {
					holHolidayService.saveAll(saveList);
					msg="上传成功!共有"+saveList.size()+"条数据入库！";
					servletResponse.getWriter().write("{\"message\":\"success\",\"info\":\""+msg+"\"}");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else{
				String error1="",error2="",error3="",error4="";
				if(StringUtils.isNotEmpty(yearError)){
					error1="第"+yearError.substring(0,yearError.length()-1)+"行，年份格式错误！";
				}
				if(StringUtils.isNotEmpty(loginNameError)){
					error4="第"+loginNameError.substring(0,loginNameError.length()-1)+"行，该用户不存在！";
				}
				if(StringUtils.isNotEmpty(repeatError)){
					error2="第"+repeatError.substring(0,repeatError.length()-1)+"行，数据重复！该用户在该年份下已设置过公休！";				
				}
				if(StringUtils.isNotEmpty(dayError)){
					error3="第"+dayError.substring(0,dayError.length()-1)+"行，公休天数格式错误！";
				}
				servletResponse.getWriter().write("{\"message\":\"error\",\"error1\":\""+error1+"\",\"error2\":\""+error2+"\",\"error3\":\""+error3+"\",\"error4\":\""+error4+"\"}");
			}
			
			
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


	public void updateHolHolidays(){
		String id = servletRequest.getParameter("id");
		String days = servletRequest.getParameter("days");
		HolHoliday holHoliday = holHolidayService.findHolHolidayById(id);
		if(holHoliday!=null){
			if(StringUtils.isNotEmpty(days)){
				holHoliday.setHolDays(Long.valueOf(days));
			}
		}
		holHolidayService.updateHolHoliday(holHoliday);
	}

	public void findHolHolidaySettime() throws IOException{
		Object[] data = holHolidayService.findHolHolidaySettime();
		if(data!=null && data.length==2){
			servletResponse.getWriter().write("{\"overyear\":\""+data[0]+"\",\"month\":\""+data[1]+"\"}");
		}
	}
	
	public void updateHolHolidaysSettime() throws IOException{
		String overyear = servletRequest.getParameter("overyear");
		String month = servletRequest.getParameter("month");
		if(StringUtils.isNotEmpty(overyear) && StringUtils.isNotEmpty(month)){
			holHolidayService.updateHolHolidaySettime(Long.valueOf(overyear),Long.valueOf(month));
			servletResponse.getWriter().write("{\"message\":\"success\"}");
		}
	}

}
	
