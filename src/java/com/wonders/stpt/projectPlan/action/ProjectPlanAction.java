package com.wonders.stpt.projectPlan.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.annualLeave.utils.DownloadUtil;
import com.wonders.stpt.export.ExportExcel;
import com.wonders.stpt.projectPlan.entity.bo.ProjectPlan;
import com.wonders.stpt.projectPlan.entity.vo.ProjectPlanVO;
import com.wonders.stpt.projectPlan.service.ProjectPlanService;
import com.wonders.stpt.util.GlobalFuncNew;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core5.model.vo.ValueObject;


public class ProjectPlanAction extends BaseAjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file; // 上传的文件
	private String fileFileName; // 上传的文件名
	private static final int BUFFER_SIZE = 20 * 1024; // 20K
	private ProjectPlanService projectPlanService;
	private ProjectPlan projectPlan = new ProjectPlan();
	private ProjectPlanVO projectPlanVO = new ProjectPlanVO();
	
	public ProjectPlan getProjectPlan() {
		return projectPlan;
	}

	public ProjectPlanVO getProjectPlanVO() {
		return projectPlanVO;
	}

	public void setProjectPlan(ProjectPlan projectPlan) {
		this.projectPlan = projectPlan;
	}

	public void setProjectPlanVO(ProjectPlanVO projectPlanVO) {
		this.projectPlanVO = projectPlanVO;
	}

	@Override
	public ValueObject getValueObject() {
		return this.projectPlanVO;
	}
	
	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public void setProjectPlanService(ProjectPlanService projectPlanService){
		this.projectPlanService = projectPlanService;
	}
	
	
	public String findProjectPlanByPage() throws ParseException{
		Page page;
		String applyCompanyId = this.servletRequest.getParameter("applyCompanyId");
		String planProjectName = this.servletRequest.getParameter("planProjectName");
		String year = this.servletRequest.getParameter("year");
		String currentPageStr = this.servletRequest.getParameter("number");	
		
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
		JSONObject obj = JSONObject.fromObject(this.projectPlanVO);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.projectPlanVO, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if (null != applyCompanyId && !"".equals(applyCompanyId)) {
			filter.put("applyCompanyId", applyCompanyId);
			this.servletRequest.setAttribute("applyCompanyId", applyCompanyId);
		}
		if (null != planProjectName && !"".equals(planProjectName)) {
			filter.put("planProjectName", planProjectName);
			this.servletRequest.setAttribute("planProjectName", planProjectName);
		}
		if (null != year && !"".equals(year)) {
			filter.put("year", year);
			this.servletRequest.setAttribute("year", year);
		}		
		
		String ifExport = this.servletRequest.getParameter("ifExport");
		if("yes".equals(ifExport)){
			page = projectPlanService.findProjectPlanByPage(
					filter, 1, 99999);
			return export(page.getResult());
		}else{
			if (currentPage == 0) {
				page = this.projectPlanService.findProjectPlanByPage(
						filter, start / size + 1, size);
			}else {
				page = projectPlanService.findProjectPlanByPage(
						filter, currentPage, size);
			}
			
			this.servletRequest.setAttribute("page", page);
			
			List<Object[]> deptList = GlobalFuncNew.getDepts();
			this.servletRequest.setAttribute("deptList", deptList);	
			this.servletRequest.setAttribute("curYear", Calendar.getInstance().get(Calendar.YEAR));	
			return SUCCESS;
		}
	}
	
	public String export(List<ProjectPlan> list){
		
		List<Object[]> datasets = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
				Object[] params = new Object[9];
				params[0] = list.get(i).getApplyCompany();
				params[1] = list.get(i).getPlanProjectName();
				params[2] = list.get(i).getProperty();
				params[3] = list.get(i).getEstimate();
				params[4] = list.get(i).getAccording();
				params[5] = list.get(i).getPlan();
				params[6] = list.get(i).getTarget();
				params[7] = list.get(i).getRemark();
				params[8] = list.get(i).getId();
				datasets.add(params);
		}
		
		String downloadFileName = "项目计划列表.xls";
		try {
			this.servletResponse.setContentType("octets/stream");  
			this.servletResponse.addHeader("Content-Disposition","attachment;filename="+new String(downloadFileName.getBytes("gb2312"), "ISO8859-1" ));
			String[] headers = {"申报单位","计划项目名称","项目属性","概算（万元）","立项依据","实施方案","项目目标","备注","计划ID"};
			short[] width = {8000,10000,5000,5000,10000,10000,10000,10000,10000};

			try {
				OutputStream out = this.servletResponse.getOutputStream();
				ExportExcel ee = new ExportExcel();
				ee.exportExcel("项目计划列表导出", headers, datasets, out,width); 
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public String updateProjectPlanById(){
		ProjectPlan projectPlan = convertVOToBO(projectPlanVO);
		projectPlan.setId(this.servletRequest.getParameter("id"));
		projectPlanService.update(projectPlan);
		return SUCCESS;
	}
	
	/**
     * @author sunjiawei
     * @param null
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @describe 添加页面跳转
     */	
	public String toAdd() throws FileNotFoundException, IOException {	
		List<Object[]> deptList = GlobalFuncNew.getDepts();
		this.servletRequest.setAttribute("deptList", deptList);
		return SUCCESS;
	}
	
	public String addProjectPlan() {
		ProjectPlan projectPlan = convertVOToBO(projectPlanVO);
		
		if (projectPlan != null) {
			projectPlan.setRemoved("0");
			String nowTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 当前时间
			projectPlan.setOperateTime(nowTime2);
			projectPlan.setYear(new SimpleDateFormat("yyyy").format(new Date()));
			Cookie[] cookies = this.servletRequest.getCookies();
			for (Cookie cookie : cookies) {
				if ("loginName".equals(cookie.getName())){
					projectPlan.setOperateUser(cookie.getValue());
				}
			}
			projectPlanService.save(projectPlan);
		}
		return SUCCESS;
	}
	
	public String findProjectPlanById() {
		String id = super.getServletRequest().getParameter("id");
		String type = super.getServletRequest().getParameter("type");
		ProjectPlan bo = projectPlanService.findById(id);
		projectPlanVO = convertBOToVO(bo);
		if ("edit".equals(type)){
			List<Object[]> deptList = GlobalFuncNew.getDepts();
			this.servletRequest.setAttribute("deptList", deptList);			
		  return "edit";
		}			
		else return "view";
	}
	
	public void deleteById() throws ParseException {
		String id = this.servletRequest.getParameter("id");	
		ProjectPlan bo = projectPlanService.findById(id);
		if (bo != null) {
			bo.setRemoved("1");
			projectPlanService.update(bo);
		}
		
	}
	
	/**
	 * 下载模板
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public void downloadTemplete() throws IOException {
		String filePath = servletRequest.getRealPath("projectPlan")+ File.separator + "ProjectPlanImportTemplate.xls";
		DownloadUtil.downloadFile(servletResponse, filePath);
	}	
	
	/**
	 * 上传文件(批量导入)
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws IOException
	 * @throws IOException
	 */
	public String fileUpload() {

		String responseData = "";
		HSSFRow row = null;

		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 当前时间
		String nowTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 当前时间
		int saveStatus; // 保存状态,0:新增保存，1:修改保存，2:报错不保存
		String errorInfo; // 每一行的错误信息
		String errorAll = "插入失败的行："; // 第0行，第0列，显示插入失败的数据列

		// 获取资源文件中定义的上传路径
		savePath = ServletActionContext.getServletContext().getRealPath("projectPlan" + File.separator + "file" + File.separator);
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

		// 存放在WebRoot/jsp/asset/file/目录下
		copy(file, newFile);

		// 复制到本地后，将file置为null
		file = null;

		InputStream is = null;
		HSSFWorkbook hssfWorkbook = null;
		HSSFSheet sheet;

		int count = -1;
		int errorCount = 0; // 错误数据
		int successCount = 0; // 成功数据
		int modifyCount = 0; // 修改数据
		try {
			is = new FileInputStream(newFile);

			hssfWorkbook = new HSSFWorkbook(is);
			is = null;

			// 获得excel中的第一张表
			sheet = hssfWorkbook.getSheetAt(0);

			count = sheet.getPhysicalNumberOfRows();
			String cellData;
			String dataId;
			boolean flag = true;
			List<ProjectPlan> saveList = new ArrayList<ProjectPlan>();
			for (int i = sheet.getFirstRowNum() + 1; i < count; i++) {
				ProjectPlan projectPlan = new ProjectPlan();
				row = sheet.getRow(i); // 循环行
				if(row==null){
					continue;
				}
				
				//申报单位
				cellData = getCellData(row, 0);
				if(cellData!=null){
					if(cellData.length()<50){
						List<Object[]> depts = GlobalFuncNew.getDeptByName(cellData.trim());
						if(depts.size() > 0){
							projectPlan.setApplyCompanyId(String.valueOf(depts.get(0)[0]));
							projectPlan.setApplyCompany((String)depts.get(0)[1]);						
						}else{
							responseData += "第"+(i+1)+"行的申报单位不在下拉列表中，上传失败。<br>";
							flag = false;						
						}											
					}else{
						responseData += "第"+(i+1)+"行的申报单位超过50个字，上传失败。<br>";
						flag = false;	
					}
				}else{
					responseData += "第"+(i+1)+"行的申报单位不能为空，上传失败。<br>";
					flag = false;
				}
				
				//计划项目名称
				cellData = getCellData(row, 1);
				if(cellData!=null){
					if(cellData.length()<50){
						projectPlan.setPlanProjectName(cellData);	
					}else{
						responseData += "第"+(i+1)+"行的计划项目名称超过50个字，上传失败。<br>";
						flag = false;						
					}
				}else{
					responseData += "第"+(i+1)+"行的计划项目名称不能为空，上传失败。<br>";
					flag = false;
				}
				
				//项目属性
				cellData = getCellData(row, 2);
				if(cellData!=null){
					if(cellData.length()<50){
						projectPlan.setProperty(cellData);						
					}else{
						responseData += "第"+(i+1)+"行的项目属性超过50个字，上传失败。<br>";
						flag = false;						
					}
				}
				
				//概算（万元）
				cellData = getCellData(row, 3);
				if(cellData!=null){
					if(cellData.length()<50){
						try{
							double d = Double.parseDouble(cellData);
							projectPlan.setEstimate(cellData);
						}catch(Exception e){
							responseData += "第"+(i+1)+"行的概算不是数字格式，上传失败。<br>";
							flag = false;
						}						
					}else{
						responseData += "第"+(i+1)+"行的概算超过50个字，上传失败。<br>";
						flag = false;						
					}
				}else{
					responseData += "第"+(i+1)+"行的概算不能为空，上传失败。<br>";
					flag = false;
				}
				
				//立项依据
				cellData = getCellData(row, 4);
				if(cellData!=null){
					if(cellData.length()<500){
						projectPlan.setAccording(cellData);						
					}else{
						responseData += "第"+(i+1)+"行的立项依据超过500个字，上传失败。<br>";
						flag = false;						
					}
				}
				
				//实施方案
				cellData = getCellData(row, 5);
				if(cellData!=null){
					if(cellData.length()<2000){
						projectPlan.setPlan(cellData);						
					}else{
						responseData += "第"+(i+1)+"行的实施方案超过2000个字，上传失败。<br>";
						flag = false;						
					}
				}
				
				//项目目标
				cellData = getCellData(row, 6);
				if(cellData!=null){
					if(cellData.length()<2000){
						projectPlan.setTarget(cellData);						
					}else{
						responseData += "第"+(i+1)+"行的项目目标超过2000个字，上传失败。<br>";
						flag = false;						
					}
				}
				
				//备注
				cellData = getCellData(row, 7);
				if(cellData!=null){
					if(cellData.length()<2000){
						projectPlan.setRemark(cellData);	
					}else{
						responseData += "第"+(i+1)+"行的备注超过2000个字，上传失败。<br>";
						flag = false;						
					}
				}
				
				projectPlan.setRemoved("0");
				projectPlan.setOperateTime(nowTime2);
				projectPlan.setYear(new SimpleDateFormat("yyyy").format(new Date()));
				Cookie[] cookies = this.servletRequest.getCookies();
				for (Cookie cookie : cookies) {
					if ("loginName".equals(cookie.getName())){
						projectPlan.setOperateUser(cookie.getValue());
					}
				}
				if(flag){
					saveList.add(projectPlan);
				}
			}
			if(saveList!=null&&saveList.size()>0&&flag){
				this.projectPlanService.saveOrUpdateAll(saveList);
				responseData = "上传成功！";
			}
		} catch (FileNotFoundException e) {
			responseData = "上传文件失败，请稍后再试！";
			e.printStackTrace();
		} catch (IOException e) {
			responseData = "上传文件失败，请稍后再试！";
			e.printStackTrace();
		} finally {
			
		}

		servletResponse.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
		try {
			// servletResponse.getWriter().print((new
			// StringBuilder("{'msg':'")).append(responseData).append("','fileName':'").append(downloadFileName).append("'}").toString());
			this.servletResponse.getWriter().print("{'msg':'" + responseData + "'}");
			// this.servletResponse.getWriter().print(downloadFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// 复制文件到本地
	public void copy(File src, File dst) {

		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
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
	 * 得到单元格的值
	 * 
	 * @param row
	 *            行
	 * @param no
	 *            第几列
	 * @return 单元格的值
	 */
	public String getCellData(HSSFRow row, int no) {
		String result = null;
		if (row != null) {
			HSSFCell cell = row.getCell(no);
			if (cell != null) {
//				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//					result = String.format("%.0f", cell.getNumericCellValue()).trim();
//				} else {
					result = cell.toString().replace("\n", "").replace("(", "（").replace(")", "）").trim();
//				}
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		String a = "ss";
		try{
		System.out.println(Double.parseDouble(a));
		}catch(Exception e){
			System.out.println("error");
		}
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
	
	private ProjectPlan convertVOToBO(
			ProjectPlanVO projectPlanVO) {
		ProjectPlan projectPlan = new ProjectPlan();
		BeanUtils.copyProperties(projectPlanVO, projectPlan,
				new String[] { "id" });
		return projectPlan;
	}
	
	private ProjectPlanVO convertBOToVO(
			ProjectPlan projectPlan) {
		ProjectPlanVO projectPlanVO = new ProjectPlanVO();
		BeanUtils.copyProperties(projectPlan, projectPlanVO);
		return projectPlanVO;
	}
}
