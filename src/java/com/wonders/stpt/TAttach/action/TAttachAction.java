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

package com.wonders.stpt.TAttach.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wonders.stpt.accidentCase.TAttach.entity.vo.TAttachVO;
import com.wonders.stpt.TAttach.service.TAttachService;
import com.wonders.stpt.accidentCase.Util.ConstantUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TAttachAction extends BaseAjaxAction {
	private TAttach tAttach = new TAttach();
	private TAttachVO tAttachVO = new TAttachVO();
	private File uploadify = null;
	private String uploadifyFileName;
	private TAttachService attachService;
	private static final int BUFFER_SIZE = 20 * 1024; // 20K
	
	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public void setAttachService(TAttachService attachService) {
		this.attachService = attachService;
	}

	@Override
	public ValueObject getValueObject() {
		return this.tAttachVO;
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

	public String findTAttachById() {
		String id = super.getServletRequest().getParameter("id");

		TAttach bo = attachService.findTAttachById(Long.parseLong(id));

		if (bo != null) {
			String json = VOUtils.getJsonData(convertBOToVO(bo));
			createJSonData("{\"success\":true,\"result\":[" + json.toString()
					+ "]}");
		}
		return AJAX;
	}

	public String findTAttachByPage() {
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
		JSONObject obj = JSONObject.fromObject(this.tAttachVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.tAttachVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		Page page = attachService.findTAttachByPage(filter, start / size + 1,
				size);
		String json = VOUtils.getJsonDataFromPage(page, TAttach.class);
		createJSonData(json);
		return AJAX;
	}

	public String deleteTAttach() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] deleteData = (String[]) super.getParameters()
				.get("deleteData");
		if (deleteData != null) {
			JSONArray deleteArr = JSONArray.fromObject("[" + deleteData[0]
					+ "]");
			JSONObject obj = null;
			TAttach bean = null;
			for (int i = 0; i < deleteArr.size(); i++) {
				obj = (JSONObject) deleteArr.get(i);
				bean = (TAttach) JSONObject.toBean(obj, TAttach.class);
				if (bean != null) {
					attachService.deleteTAttach(bean);
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String addTAttach() {
		TAttach tAttach = convertVOToBO(tAttachVO);
		if (tAttach != null) {
			attachService.addTAttach(tAttach);
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	public String updateTAttach() {
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
					TAttachVO bean = (TAttachVO) JSONObject.toBean(obj,
							TAttachVO.class);
					attachService.addTAttach(this.convertVOToBO(bean));
				} else {
					attachService.updateTAttach((TAttach) JSONObject.toBean(
							obj, TAttach.class));
				}
			}
		}
		createJSonData("{\"success\":true}");
		return AJAX;
	}

	private TAttach convertVOToBO(TAttachVO tAttachVO) {
		TAttach tAttach = new TAttach();
		BeanUtils.copyProperties(tAttachVO, tAttach, new String[] { "id" });
		return tAttach;
	}

	private TAttachVO convertBOToVO(TAttach tAttach) {
		TAttachVO tAttachVO = new TAttachVO();
		BeanUtils.copyProperties(tAttach, tAttachVO);
		return tAttachVO;
	}

	public TAttachVO getTAttachVO() {
		return tAttachVO;
	}

	public void setTAttachVO(TAttachVO tAttachVO) {
		this.tAttachVO = tAttachVO;
	}

	
	
	
	/**
	 * @author yaochenglong
	 * @describe 根据groupId查询附件
	 * @return String类型
	 */
	public String findTAttachByGroupID(){
		String groupID = this.servletRequest.getParameter("groupID"); 
		List<TAttach> attchList = attachService.findTAttachByGroupID(groupID);
		String jsonData = VOUtils.getJsonDataFromCollection(attchList);
		createJSonData(jsonData);
		
		return AJAX;
	}
	
	/**
	 * @author yaochenglong
	 * @describe 根据主键删除
	 * @return String类型
	 */
	public String deleteTAttachById(){
		
		String id = this.servletRequest.getParameter("id");
		tAttach = attachService.findTAttachById(Long.valueOf(id));
		tAttach.setStatus("delete");
		if(tAttach!=null){
			attachService.updateTAttach(tAttach);
		}
		return AJAX;
	}
	
	/**
	 * @author yaochenglong
	 * @describe 文件下载
	 * @return String类型
	 */
	public String downloadFile() throws IOException{
		
		String fileName = this.servletRequest.getParameter("fileName");
		//String realName = this.servletRequest.getParameter("realName");
        String fullPath=ConstantUtil.UPLOAD_DIR+fileName;  
        fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");
        InputStream is=new FileInputStream(fullPath);  
        int len=0;  
        byte []buffers=new byte[1024];  
        this.getServletResponse().reset();  
        this.getServletResponse().setContentType("application/x-msdownload"); 
        this.getServletResponse().addHeader("Content-Disposition", "attach;filename="+fileName);  
          
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
	 * @author yaochenglong
	 * @describe 上传文件件
	 * @return String
	 */
	public String fileUpload() throws IOException {
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间

		// 获取资源文件中定义的上传路径
		String savePath = ConstantUtil.UPLOAD_DIR;
		if(!(new File(savePath)).exists()){
			new File(savePath).mkdirs();
		}
		
		//获取扩展名
		if (uploadifyFileName.lastIndexOf(".") >= 0) {
			extName = uploadifyFileName.substring(uploadifyFileName
					.lastIndexOf(".")+1);
		}
		newFileName = nowTime +"."+ extName;
		File newFile = new File(savePath + newFileName);
		copy(uploadify, newFile);
		
		tAttach = new TAttach();
		tAttach.setFilename(uploadifyFileName.substring(0,uploadifyFileName.lastIndexOf(".")));
		tAttach.setFileextname(extName);
		tAttach.setPath(savePath);
		tAttach.setFilesize(uploadify.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(new Date());
		tAttach.setUploaddate(date);
		tAttach.setRemoved(0l);
		tAttach.setSavefilename(newFileName);
		tAttach.setStatus("upload");

		String groupid = this.servletRequest.getParameter("groupid");
		tAttach.setGroupid(groupid);
		attachService.saveTAttach(tAttach);
		servletRequest.setAttribute("groupId",tAttach.getGroupid());
		createJSonData(tAttach.getGroupid());
		
		
		return AJAX;
	}

	/**
	 * @author yaochenglong
	 * @param src File类型:文件源地址 
	 * @param dst File类型:文件存放地址
	 * @describe 复制文件到本地
	 * @return void
	 */
	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream

				(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream

				(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
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
	
	
	
	
	
	
}
