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

package com.wonders.stpt.construction.TConstructionNotice.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.construction.ConstructionMetroLine.entity.bo.ConstructionMetroLine;
import com.wonders.stpt.construction.TConstructionNotice.entity.bo.TConstructionNotice;
import com.wonders.stpt.construction.TConstructionNotice.entity.vo.TConstructionNoticeVO;
import com.wonders.stpt.construction.TConstructionNotice.service.TConstructionNoticeService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class TConstructionNoticeAction extends BaseAjaxAction {
	private TConstructionNotice tConstructionNotice = new TConstructionNotice();
	private TConstructionNoticeVO tConstructionNoticeVO = new TConstructionNoticeVO();
	private TConstructionNoticeService constructionNoticeService;

	public void setConstructionNoticeService(
			TConstructionNoticeService constructionNoticeService) {
		this.constructionNoticeService = constructionNoticeService;
	}

	@Override
	public ValueObject getValueObject() {
		return this.tConstructionNoticeVO;
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

	public String findTConstructionNoticeById() {
		String id = super.getServletRequest().getParameter("id");

		TConstructionNotice bo = constructionNoticeService
				.findTConstructionNoticeById(id);
		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findTConstructionNoticeByPage() {
		int start = 0;
		int size = 20;
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
		JSONObject obj = JSONObject.fromObject(this.tConstructionNoticeVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.tConstructionNoticeVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = constructionNoticeService.findTConstructionNoticeByPage(
				filter, start / size + 1, size);
		String json = VOUtils.getJsonDataFromPage(page,
				TConstructionNotice.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteTConstructionNotice() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			TConstructionNotice bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (TConstructionNotice) JSONObject.toBean(obj,
						TConstructionNotice.class);
				if (bean != null) {
					constructionNoticeService.deleteTConstructionNotice(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addTConstructionNotice() {
		TConstructionNotice tConstructionNotice = convertVOToBO(tConstructionNoticeVO);
		if (tConstructionNotice != null) {
			constructionNoticeService
					.addTConstructionNotice(tConstructionNotice);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateTConstructionNotice() {
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
					TConstructionNoticeVO bean = (TConstructionNoticeVO) JSONObject
							.toBean(obj, TConstructionNoticeVO.class);
					constructionNoticeService.addTConstructionNotice(this
							.convertVOToBO(bean));
				} else {
					constructionNoticeService
							.updateTConstructionNotice((TConstructionNotice) JSONObject
									.toBean(obj, TConstructionNotice.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private TConstructionNotice convertVOToBO(
			TConstructionNoticeVO tConstructionNoticeVO) {
		TConstructionNotice tConstructionNotice = new TConstructionNotice();
		BeanUtils.copyProperties(tConstructionNoticeVO, tConstructionNotice,
				new String[] { "id" });
		return tConstructionNotice;
	}

	private TConstructionNoticeVO convertBOToVO(
			TConstructionNotice tConstructionNotice) {
		TConstructionNoticeVO tConstructionNoticeVO = new TConstructionNoticeVO();
		BeanUtils.copyProperties(tConstructionNotice, tConstructionNoticeVO);
		return tConstructionNoticeVO;
	}

	public TConstructionNoticeVO getTConstructionNoticeVO() {
		return tConstructionNoticeVO;
	}

	public void setTConstructionNoticeVO(
			TConstructionNoticeVO tConstructionNoticeVO) {
		this.tConstructionNoticeVO = tConstructionNoticeVO;
	}

	public String findTConstructionNotice() {
		
		int currentPage = 1;	//当前页数
		int size = 20;			//每页显示的页数
		
		String pageNo = this.servletRequest.getParameter("pageNo");
		
		if(pageNo!=null && !pageNo.equals("")){
			currentPage = Integer.valueOf(pageNo);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.tConstructionNoticeVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.tConstructionNoticeVO, key);
				if (res != null) {
					if(key.equals("lineNo")){
						if(Integer.valueOf(value)!=-1){
							filter.put(key, res);
						}
					}else{
						filter.put(key, res);
					}
				}
			}
		}
		Page page = constructionNoticeService.findTConstructionNoticeByPage(filter,currentPage, size);
		this.servletRequest.setAttribute("page", page);
		List<ConstructionMetroLine> metroLineList = constructionNoticeService.findAllMetroLine();
		
		this.servletRequest.setAttribute("metroLineList", metroLineList);
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if(showOrHide==null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
		
		return SUCCESS;
	}
	
	public String findTConstructionNoticeOnly() {
			
			int currentPage = 1;	//当前页数
			int size = 20;			//每页显示的页数
			
			String pageNo = this.servletRequest.getParameter("pageNo");
			
			if(pageNo!=null && !pageNo.equals("")){
				currentPage = Integer.valueOf(pageNo);
			}
	
			String key = null;
			String value = null;
			Map<String, Object> filter = new HashMap<String, Object>();
			JSONObject obj = JSONObject.fromObject(this.tConstructionNoticeVO);
			Iterator it = obj.keys();
			while (it.hasNext()) {
				key = (String) it.next();
				value = this.servletRequest.getParameter(key);
				if (value != null && value.trim().length() > 0) {
					Object res = this.getValueByParamName(
							this.tConstructionNoticeVO, key);
					if (res != null) {
						if(key.equals("lineNo")){
							if(Integer.valueOf(value)!=-1){
								filter.put(key, res);
							}
						}else{
							filter.put(key, res);
						}
					}
				}
			}
			Page page = constructionNoticeService.findTConstructionNoticeByPage(filter,currentPage, size);
			this.servletRequest.setAttribute("page", page);
			List<ConstructionMetroLine> metroLineList = constructionNoticeService.findAllMetroLine();
			
			this.servletRequest.setAttribute("metroLineList", metroLineList);
			
			String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
			if(showOrHide==null || "".equals(showOrHide))
				showOrHide = "hide";
			ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);
			
			return SUCCESS;
		}
	
	/*************************************************************/
	/**
	 * @author ycl
	 * @describe 跳转到上传页面
	 * @return
	 */
	public String showUploadPage(){
		
		return SUCCESS;
	}
	
	private File uploadify = null;		//上传的文件
	private String uploadifyFileName;	//上传的文件名
	private List<String> errorInfo = new ArrayList<String>();	//错误信息
	private int nullNum = 0;

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public String UploadAndSavdData() throws IOException, ParseException{
		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间

		// 获取资源文件中定义的上传路径
		//savePath = getText("saveDir");
		savePath = ServletActionContext.getServletContext().getRealPath("uploadFiles")+File.separator;
		File dir = new File(savePath);
		if(!dir.exists()){
			dir.mkdir();
		}

		// 获取扩展名
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName
					.lastIndexOf("."));
		}
		newFileName = nowTime + extName;
		File newFile = new File(savePath + newFileName);
		
		//复制文件到指定路径
		constructionNoticeService.copy(uploadify, newFile);
		List<TConstructionNotice> dataList= null ;
		//解析excel2003
		dataList = analysisByTwoThree(newFile.getAbsolutePath());
		/*
		if(newFile.toString().endsWith("xls")){
			dataList = analysisByTwoThree(newFile.getAbsolutePath());
		}else if(newFile.toString().endsWith("xlsx")){
			dataList = analysisByTwoSeven(newFile.getAbsolutePath());
		}
		*/
		

		if(dataList!=null && dataList.size()>0 && errorInfo.size() == 0){
			constructionNoticeService.saveAll(dataList);
		}

		String dataInfo = "";
		dataInfo += "有效数据："+dataList.size()+"条|";
		
		String jsonData = VOUtils.getJsonDataFromCollection(errorInfo);
		String responseData = "";
		if(errorInfo!=null && errorInfo.size()!=0){
			for(int i=0;i<errorInfo.size();i++){
				responseData += errorInfo.get(i)+"|";
			}
			dataInfo +="无效数据："+errorInfo.size()+"条|";
			dataInfo = "导入失败！|" + dataInfo;
		}else{
			dataInfo = "导入成功！" + dataInfo;
			dataInfo = dataInfo.replace("|", ",");
			dataInfo = dataInfo.substring(0,dataInfo.length()-1);
		}
		if(!responseData.equals("")){
			responseData = responseData.substring(0, responseData.length()-1);
		}
		
		responseData = dataInfo+responseData;
		HttpServletResponse response = this.servletResponse;
		response.setCharacterEncoding("utf-8"); //务必，防止返回文件名是乱码 
		this.servletResponse.getWriter().print(responseData);
		//createJSonData(jsonData);
		return null;
	}
	
	/**
	 * @author ycl
	 * @describe 分析excel2003文件
	 * @param path	文件的全路径
	 * @throws IOException 
	 * @throws IOException
	 * @throws ParseException 
	 * @throws ParseException 
	 */
	public List<TConstructionNotice> analysisByTwoThree(String path) throws IOException{
		
		List<ConstructionMetroLine> metroLineList = constructionNoticeService.findAllMetroLine();
		if(metroLineList==null || metroLineList.size()==0){
			return null;
		}
		List<TConstructionNotice> dataList = new ArrayList<TConstructionNotice>();
		TConstructionNotice constructionNotice = null;
		HSSFRow row;
		//HSSFCell cell;
		String cellData;
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 获得excel中的第一张表
		HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		boolean lineStatus ;
		
		for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
			lineStatus = false;
			constructionNotice = new TConstructionNotice();
			row = sheet.getRow(i);
			
			//线路编号
			cellData = getCellData(row,0);
			if(cellData==null || cellData.trim().equals("")){
				cellData = "跨线";
			}else{
				cellData = cellData.replace("十一", "11");
				cellData = cellData.replace("十二", "12");
				cellData = cellData.replace("十三", "13");
				
				cellData = cellData.replace("一", "1");
				cellData = cellData.replace("二", "2");
				cellData = cellData.replace("三", "3");
				cellData = cellData.replace("四", "4");
				cellData = cellData.replace("五", "5");
				cellData = cellData.replace("六", "6");
				cellData = cellData.replace("七", "7");
				cellData = cellData.replace("八", "8");
				cellData = cellData.replace("九", "9");
				cellData = cellData.replace("十", "10");
			}
			cellData = cellData.replace("跨线", "全网");
			
			for(int j=0;j<metroLineList.size();j++){
				if(metroLineList.get(j).getLineName().equals(cellData)){
					constructionNotice.setLineNo(metroLineList.get(j).getLineId());
					lineStatus = true;
					break;
				}
			}
			
			if(!lineStatus){
				String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),0)+"\"列，数据有误，无法导入";
				this.errorInfo.add(errorInfo);
				continue;
			}
			
			//日期
			cellData = getCellData(row, 1);
			if(checkCellData(cellData)){
				if(cellData.startsWith("12") || cellData.startsWith("13") || cellData.startsWith("14")){
					cellData = "20"+cellData;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				try {
					constructionNotice.setConstructionDate(sdf.parse(cellData));
				} catch (ParseException e) {
					String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),1)+"\"列，数据有误，无法导入";
					this.errorInfo.add(errorInfo);
					continue;
				}
			}else{
				nullNum++;
			}
			 
			//申请单位
			cellData = getCellData(row, 2);
			if(checkCellData(cellData)){
				constructionNotice.setApplyUnit(cellData);
			}else{
				nullNum++;
			}
			
			//施工单位
			cellData = getCellData(row, 3);
			if(checkCellData(cellData)){
				constructionNotice.setConstructionUnit(cellData);
			}else{
				nullNum++;
			}
			
			//负责人
			cellData = getCellData(row, 4);
			if(checkCellData(cellData)){
				constructionNotice.setResponsiblePerson(cellData);
			}else{
				nullNum++;
			}
			
			//施工范围
			cellData = getCellData(row, 5);
			if(checkCellData(cellData)){
				constructionNotice.setConstrustionRange(cellData);
			}else{
				nullNum++;
			}
			
			
			
			cellData = getCellData(row, 6);
			
			if(checkCellData(cellData)){
				
				//申通旧模板*********************************
				if(cellData.contains("——")){		
					String date = getCellData(row, 1);
					String startDate = cellData.split("——")[0];
					String endDate = cellData.split("——")[1];
					if(date.startsWith("12") || date.startsWith("13") || date.startsWith("14")){
						date = "20"+date;
					}
					date = date.replace("年", "").replace("月", "").replace("日", "");
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd"); 
					String dateTemp = null;
					
					if(startDate.startsWith("次")){
						try {
							Date temp = formatYMD.parse(date);
							temp.setDate(temp.getDate()+1);
							startDate = startDate.substring(1).replace(":", "");
							dateTemp = formatYMD.format(temp) + startDate+"00";
							constructionNotice.setConstructionStartDate(format.parse(dateTemp));
							
						} catch (ParseException e1) {
							String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
							this.errorInfo.add(errorInfo);
							continue;
						}
						
					}else{
						startDate = startDate.replace(":", "");
						dateTemp = date + startDate+"00";
						try {
							constructionNotice.setConstructionStartDate(format.parse(dateTemp));
						} catch (ParseException e) {
							String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
							this.errorInfo.add(errorInfo);
							continue;
						}
					}
					
					if(endDate.startsWith("次")){
						try {
							Date temp = formatYMD.parse(date);
							temp.setDate(temp.getDate()+1);
							endDate = endDate.substring(1).replace(":", "");
							dateTemp = formatYMD.format(temp) + endDate+"00";
							constructionNotice.setConstructionEndDate(format.parse(dateTemp));
						} catch (ParseException e1) {
							String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
							this.errorInfo.add(errorInfo);
							continue;
						}
						
					}else{
						endDate = endDate.replace(":", "");
						dateTemp = date + endDate+"00";
						try {
							constructionNotice.setConstructionEndDate(format.parse(dateTemp));
						} catch (ParseException e) {
							String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
							this.errorInfo.add(errorInfo);
							continue;
						}
					}
					
					
					
					//施工内容
					cellData = getCellData(row, 7);
					if(checkCellData(cellData)){
						constructionNotice.setConstructionDetail(cellData);
					}else{
						nullNum++;
					}
					
					//牵引动力
					cellData = getCellData(row, 8);
					if(checkCellData(cellData)){
						constructionNotice.setDragDynamic(cellData);
					}else{
						nullNum++;
					}
					
					//触网停电范围
					cellData = getCellData(row, 9);
					if(checkCellData(cellData)){
						constructionNotice.setPowerOffRange(cellData);
					}else{
						nullNum++;
					}
					
					//计划编号
					cellData = getCellData(row, 10);
					if(checkCellData(cellData)){
						constructionNotice.setProjectNo(cellData);
					}else{
						nullNum++;
					}
					
					//新模板********************************************	
				}else{								
					//施工开始时间
					cellData = getCellData(row, 6);
					if(checkCellData(cellData)){
						String date = getCellData(row, 1);
						
						if(checkCellData(date)){
							if(date.startsWith("12")){
								date = "20"+date;
							}
							date = date.replace("年", "").replace("月", "").replace("日", "");
							
							SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
							SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd"); 
							String dateTemp = null;
							if(cellData.startsWith("次")){
								try {
									Date temp = formatYMD.parse(date);
									temp.setDate(temp.getDate()+1);
									cellData = cellData.substring(1).replace(":", "");
									dateTemp = formatYMD.format(temp) + cellData+"00";
									constructionNotice.setConstructionStartDate(format.parse(dateTemp));
								} catch (ParseException e1) {
									String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
									this.errorInfo.add(errorInfo);
									continue;
								}
								
							}else{
								cellData = cellData.replace(":", "");
								dateTemp = date + cellData+"00";
								try {
									constructionNotice.setConstructionStartDate(format.parse(dateTemp));
								} catch (ParseException e) {
									String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),6)+"\"列，数据有误，无法导入";
									this.errorInfo.add(errorInfo);
									continue;
								}
							}
						}
						
						
					}
					
					//施工结束时间
					cellData = getCellData(row, 7);
					if(checkCellData(cellData)){
						String date = getCellData(row, 1);
						if(checkCellData(date)){
							if(date.startsWith("12")){
								date = "20"+date;
							}
							date = date.replace("年", "").replace("月", "").replace("日", "");
							SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
							SimpleDateFormat formatYMD = new SimpleDateFormat("yyyyMMdd"); 
							String dateTemp = null;
							if(cellData.startsWith("次")){
								try {
									Date temp = formatYMD.parse(date);
									temp.setDate(temp.getDate()+1);
									cellData = cellData.substring(1).replace(":", "");
									dateTemp = formatYMD.format(temp) + cellData+"00";
									constructionNotice.setConstructionEndDate(format.parse(dateTemp));
								} catch (ParseException e1) {
									String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),7)+"\"列，数据有误，无法导入";
									this.errorInfo.add(errorInfo);
									continue;
								}
								
							}else{
								cellData = cellData.replace(":", "");
								dateTemp = date + cellData+"00";
								try {
									constructionNotice.setConstructionEndDate(format.parse(dateTemp));
								} catch (ParseException e) {
									String errorInfo ="第 " +(i+1)+" 行，\""+getCellData(sheet.getRow(0),7)+"\"列，数据有误，无法导入";
									this.errorInfo.add(errorInfo);
									continue;
								}
							}
						}
						
					}
					
					//施工内容
					cellData = getCellData(row, 8);
					if(checkCellData(cellData)){
						constructionNotice.setConstructionDetail(cellData);
					}else{
						nullNum++;
					}
					
					//牵引动力
					cellData = getCellData(row, 9);
					if(checkCellData(cellData)){
						constructionNotice.setDragDynamic(cellData);
					}else{
						nullNum++;
					}
					
					//触网停电范围
					cellData = getCellData(row, 10);
					if(checkCellData(cellData)){
						constructionNotice.setPowerOffRange(cellData);
					}else{
						nullNum++;
					}
					
					//计划编号
					cellData = getCellData(row, 11);
					if(checkCellData(cellData)){
						constructionNotice.setProjectNo(cellData);
					}else{
						nullNum++;
					}
				}
				
			}
			
			
			if(nullNum>=4) break;
			else nullNum = 0;
			
			constructionNotice.setCreateDate(new Date());
			constructionNotice.setRemoved("0");
			constructionNotice.setLastUpdateDate(constructionNotice.getCreateDate());
			//更新人的名字,未定
			constructionNotice.setUpdatePerson("批量导入");
			
			dataList.add(constructionNotice);

		}
		return dataList;
	}
	
	/**
	 * @author ycl
	 * @describe 分析excel2007文件
	 * @param path	文件的全路径
	 * @throws IOException
	 */
	public List<TConstructionNotice> analysisByTwoSeven(String path) throws IOException {
		
		return null;
	}
	
	
	/**
	 * @author ycl
	 * @param row 行
	 * @param no 列
	 * @return String
	 */
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
	 * @author ycl
	 * @param cellData 单元格数据
	 * @return boolean
	 */
	public boolean checkCellData(String cellData){
		if(cellData!=null && !cellData.equals("") && !cellData.equals("null")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author yaochenglong
	 * @describe 文件下载
	 * @return String类型
	 */
	public String downloadFile() throws IOException{
		
        String fullPath=ServletActionContext.getServletContext().getRealPath("construction")+File.separator+"templete.xls";  
        //fileName= new String(fileName.getBytes("utf-8"),"iso-8859-1");
        
        InputStream is=new FileInputStream(fullPath);  
        int len=0;  
        byte []buffers=new byte[1024];  
        this.getServletResponse().reset();  
        this.getServletResponse().setContentType("application/x-msdownload");  
        this.getServletResponse().addHeader("Content-Disposition", "attachment;filename="+"templete.xls");  
          
        //把文件内容通过输出流打印到页面上供下载  
        while((len=is.read(buffers))!=-1){  
        	OutputStream os = this.getServletResponse().getOutputStream();  
            os.write(buffers, 0, len);  
            os.flush();
        }  
        
        is.close();  
        
		return null;
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键查询
	 */
	public String findConstructionById(){
		TConstructionNotice construction = null;
		String id = this.servletRequest.getParameter("id");
		if(id!=null && !id.equals("")){
			construction = constructionNoticeService.findTConstructionNoticeById(id);
		}
		this.servletRequest.setAttribute("construction", construction); 
		return SUCCESS;
	}
	
	/**
	 * @author ycl
	 * @description 查询各线路总数据条数
	 */
	public String showLineInfo(){
		String resultString ="";
		List<ConstructionMetroLine> lineList = constructionNoticeService.findAllMetroLine();
		if(lineList!=null && lineList.size()!=0){
			for(int i=0; i<lineList.size(); i++){
				resultString += lineList.get(i).getLineName() + "("
					+ constructionNoticeService.findCountByLineNo(lineList.get(i).getLineId()) + ") ";
			}
		}
		resultString = "{\"value\":\""+resultString.replace("全网", "跨线")+"\"}";
		createJSonData(resultString); 
		return AJAX;
		
	}
	
	
	
}
