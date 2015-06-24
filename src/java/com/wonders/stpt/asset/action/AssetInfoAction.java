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

package com.wonders.stpt.asset.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.asset.entity.bo.DwAssetCodeInfo;
import com.wonders.stpt.asset.entity.bo.ExportExcel;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.asset.service.DwAssetCodeInfoService;
import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wonders.stpt.equipment.info.service.EquipmentInfoService;
import com.wonders.stpt.model.UserDeptInfo;
import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wonders.stpt.supplier.service.SupplierService;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wonders.stpt.task.service.AssetTaskCheckService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
public class AssetInfoAction extends BaseAjaxAction {
	private AssetInfo assetInfo = new AssetInfo();
	private AssetInfoService assetInfoService;
	private DwAssetCodeInfoService dwAssetCodeInfoService;
	private final int size = 20;
	private final long TYPE1 = 13441l; // 大类(CodeInfoId)
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private AssetInfoHistory assetInfoHistory = new AssetInfoHistory();
	private final String SUPPLIER = "1"; // 供应商
	private final String MANUFACTURER = "0"; // 生产商
	private final long LINE_ID = 13421l; // 线路,codeInfoId
	private final long UNIT = 13461l; // 权属单位，使用单位,codeInfoId

	private final long MAINTAIN_DEPT = 13500l; // 维护部门,codeInfoId
	private final long DEPRECIATION = 1052l; // 折旧方法,codeInfoId
	private final long USE_STATUS = 1055l; // 当前使用状态,codeInfoId
	private final long EQUIPMENT_STATUS = 1058l; // 当前设备状态,codeInfoId
	private final long NAMEPLATE_POSITION = 1061l; // 铭牌张贴部位,codeInfoId
	private final long CHECK_TYPE = 10162l; // 盘点结果类型,codeInfoId
	private final long STATION_TYPE_ID = 1120l; // 车站，type_id

	private final long TYPE_ID = 1100l; // 大中小类，type_id
	private final long LINE_STATION_ID = 1120l; // 线路，车站

	private static final int BUFFER_SIZE = 20 * 1024; // 20K

	private static Properties properties = new Properties();

	private File file; // 上传的文件
	private String fileFileName; // 上传的文件名

	private EquipmentInfoService equipmentInfoService;
	private SupplierService supplierService;
	private AssetTaskCheckService assetTaskCheckService;

	public AssetInfoAction() {
		initProperties();
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setAssetTaskCheckService(AssetTaskCheckService assetTaskCheckService) {
		this.assetTaskCheckService = assetTaskCheckService;
	}

	public void setEquipmentInfoService(EquipmentInfoService equipmentInfoService) {
		this.equipmentInfoService = equipmentInfoService;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
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

	@Override
	public Object getModel() {
		return assetInfo;
	}

	public AssetInfo getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(AssetInfo assetInfo) {
		this.assetInfo = assetInfo;
	}

	public void setAssetInfoService(AssetInfoService assetInfoService) {
		this.assetInfoService = assetInfoService;
	}

	public void setDwAssetCodeInfoService(DwAssetCodeInfoService dwAssetCodeInfoService) {
		this.dwAssetCodeInfoService = dwAssetCodeInfoService;
	}

	/**
	 * 跳转动态管理
	 */
	public String showDynamicManagement() {

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

		String operateTime = ServletActionContext.getRequest().getParameter("operateTime");
		if (operateTime != null && !"".equals(operateTime)) {
			assetInfo.setOperateTime(operateTime);
			filter = fillFilter("operateTime", operateTime, filter);
		}

		String assetId = ServletActionContext.getRequest().getParameter("assetId");
		if (assetId != null && !"".equals(assetId)) {
			assetInfo.setAssetId(assetId);
			filter = fillFilter("assetId", assetId, filter);
		}

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

		Page page = assetInfoService.findAssetInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("TYPE1").toString()));
		servletRequest.setAttribute("type1List", type1List);

		if (type1 != null) {
			List<CfCodeInfo> type2List = assetInfoService.findCfInfoCodeById(Long.valueOf(type1));
			ServletActionContext.getRequest().setAttribute("type2List", type2List);
		}

		if (type2 != null) {
			List<CfCodeInfo> type3List = assetInfoService.findCfInfoCodeById(Long.valueOf(type2));
			ServletActionContext.getRequest().setAttribute("type3List", type3List);
		}

		if (routeNum != null) {
			List<CfCodeInfo> stationList = assetInfoService.findCfInfoCodeById(Long.valueOf(routeNum));
			ServletActionContext.getRequest().setAttribute("stationList", stationList);
		}

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("LINE_ID").toString()));
		servletRequest.setAttribute("lineList", lineList);

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("UNIT").toString()));
		servletRequest.setAttribute("unitList", unitList);

		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("MAINTAIN_DEPT").toString()));
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);

		List<String> type1NameList, type2NameList, type3NameList;
		if (page != null && page.getResult() != null && page.getResult().size() > 0) {
			// 查询分页数据中的大中小类所对应的中文名称
			List<CfCodeInfo> typeAllList = assetInfoService.findCfCodeInfoByTypeId(TYPE_ID); // 大、中、小类所有数据
			Map<String, String> typeAllMap = null;
			if (typeAllList != null && typeAllList.size() > 0) {
				typeAllMap = new HashMap<String, String>();
				for (CfCodeInfo cfCodeInfo : typeAllList) {
					typeAllMap.put(cfCodeInfo.getId().toString(), cfCodeInfo.getName());
				}
			}

			// 查询分页数据中的线路和车站所对应的中文名称
			List<CfCodeInfo> lineAndStationAllList = assetInfoService.findCfCodeInfoByTypeId(LINE_STATION_ID);
			Map<String, String> lineStationAllMap = null;
			List<String> lineNameList = null, stationNameList = null;
			if (lineAndStationAllList != null && lineAndStationAllList.size() > 0) {
				lineNameList = new ArrayList<String>();
				stationNameList = new ArrayList<String>();
				lineStationAllMap = new HashMap<String, String>();
				for (int i = 0; i < lineAndStationAllList.size(); i++) {
					lineStationAllMap.put(lineAndStationAllList.get(i).getId().toString(), lineAndStationAllList.get(i).getName());
				}
			}

			type1NameList = new ArrayList<String>();
			type2NameList = new ArrayList<String>();
			type3NameList = new ArrayList<String>();
			AssetInfo tempAsset;
			Timestamp timestamp = null;
			for (int i = 0; i < page.getResult().size(); i++) {
				// 将原来数据库中存贮的TimeStamp格式的时间转换成yyyy-MM-dd HH:mm:ss格式

				tempAsset = (AssetInfo) page.getResult().get(i);

				// 将原来数据库中存贮的TimeStamp格式的时间转换成yyyy-MM-dd HH:mm:ss格式
				String time = tempAsset.getOperateTime();
				try {
					timestamp = new Timestamp(Long.valueOf(time));
					tempAsset.setOperateTime(timestamp.toString());

				} catch (NumberFormatException e) {
					// 转换不成功，说明格式正确，是（yyyy-MM-dd HH:mm:ss）
				}

				if (typeAllMap != null && typeAllMap.size() > 0) {
					type1NameList.add(typeAllMap.get(tempAsset.getType1()));
					type2NameList.add(typeAllMap.get(tempAsset.getType2()));
					type3NameList.add(typeAllMap.get(tempAsset.getType3()));
				} else {
					type1NameList.add(null);
					type2NameList.add(null);
					type3NameList.add(null);
				}

				if (lineStationAllMap != null && lineStationAllMap.size() > 0) {
					lineNameList.add(lineStationAllMap.get(tempAsset.getRouteNum()));
					stationNameList.add(lineStationAllMap.get(tempAsset.getStationNum()));
				} else {
					lineNameList.add(null);
					stationNameList.add(null);
				}

			}
			ServletActionContext.getRequest().setAttribute("type1NameList", type1NameList);
			ServletActionContext.getRequest().setAttribute("type2NameList", type2NameList);
			ServletActionContext.getRequest().setAttribute("type3NameList", type3NameList);

			ServletActionContext.getRequest().setAttribute("lineNameList", lineNameList);
			ServletActionContext.getRequest().setAttribute("stationNameList", stationNameList);
		}
		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if (showOrHide == null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);

		return "showDynamicManagement";

	}

	/**
	 * 资产清册维护
	 */
	public String showInventory() {

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

		String operateTime = ServletActionContext.getRequest().getParameter("operateTime");
		if (operateTime != null && !"".equals(operateTime)) {
			assetInfo.setOperateTime(operateTime);
			filter = fillFilter("operateTime", operateTime, filter);
		}

		String registry = ServletActionContext.getRequest().getParameter("registry");
		if (registry != null && !registry.equals("-1")) {
			assetInfo.setRegistry(registry);
			filter = fillFilter("registry", registry, filter);
		}

		String assetId = ServletActionContext.getRequest().getParameter("assetId");
		if (assetId != null && !"".equals(assetId)) {
			assetInfo.setAssetId(assetId);
			filter = fillFilter("assetId", assetId, filter);
		}

		String yuanzhi = ServletActionContext.getRequest().getParameter("yuanzhi");
		if (yuanzhi != null && !"".equals(yuanzhi)) {
			assetInfo.setYuanzhi(yuanzhi);
			filter = fillFilter("yuanzhi", yuanzhi, filter);
		}

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

		Page page = assetInfoService.findAssetInfoByPage(filter, Integer.valueOf(pageNo), size);
		servletRequest.setAttribute("page", page);

		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("TYPE1").toString()));
		servletRequest.setAttribute("type1List", type1List);

		if (type1 != null) {
			List<CfCodeInfo> type2List = assetInfoService.findCfInfoCodeById(Long.valueOf(type1));
			ServletActionContext.getRequest().setAttribute("type2List", type2List);
		}

		if (type2 != null) {
			List<CfCodeInfo> type3List = assetInfoService.findCfInfoCodeById(Long.valueOf(type2));
			ServletActionContext.getRequest().setAttribute("type3List", type3List);
		}

		if (routeNum != null) {
			List<CfCodeInfo> stationList = assetInfoService.findCfInfoCodeById(Long.valueOf(routeNum));
			ServletActionContext.getRequest().setAttribute("stationList", stationList);
		}

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("LINE_ID").toString()));
		servletRequest.setAttribute("lineList", lineList);

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("UNIT").toString()));
		servletRequest.setAttribute("unitList", unitList);
		
		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(Long.valueOf(properties.getProperty("MAINTAIN_DEPT").toString()));
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);
		
//		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
//		servletRequest.setAttribute("maintainDeptList", maintainDeptList);

		List<String> type1NameList, type2NameList, type3NameList;

		if (page != null && page.getResult() != null && page.getResult().size() > 0) {
			// 查询分页数据中的大中小类所对应的中文名称
			List<CfCodeInfo> typeAllList = assetInfoService.findCfCodeInfoByTypeId(TYPE_ID); // 大、中、小类所有数据
			Map<String, String> typeAllMap = null;
			if (typeAllList != null && typeAllList.size() > 0) {
				typeAllMap = new HashMap<String, String>();
				for (CfCodeInfo cfCodeInfo : typeAllList) {
					typeAllMap.put(cfCodeInfo.getId().toString(), cfCodeInfo.getName());
				}
			}

			// 查询分页数据中的线路和车站所对应的中文名称
			List<CfCodeInfo> lineAndStationAllList = assetInfoService.findCfCodeInfoByTypeId(LINE_STATION_ID);
			Map<String, String> lineStationAllMap = null;
			List<String> lineNameList = null, stationNameList = null;
			if (lineAndStationAllList != null && lineAndStationAllList.size() > 0) {
				lineNameList = new ArrayList<String>();
				stationNameList = new ArrayList<String>();
				lineStationAllMap = new HashMap<String, String>();
				for (int i = 0; i < lineAndStationAllList.size(); i++) {
					lineStationAllMap.put(lineAndStationAllList.get(i).getId().toString(), lineAndStationAllList.get(i).getName());
				}
			}

			type1NameList = new ArrayList<String>();
			type2NameList = new ArrayList<String>();
			type3NameList = new ArrayList<String>();
			AssetInfo tempAsset;
			Timestamp timestamp = null;
			for (int i = 0; i < page.getResult().size(); i++) {
				// 将原来数据库中存贮的TimeStamp格式的时间转换成yyyy-MM-dd HH:mm:ss格式

				tempAsset = (AssetInfo) page.getResult().get(i);

				// 将原来数据库中存贮的TimeStamp格式的时间转换成yyyy-MM-dd HH:mm:ss格式
				String time = tempAsset.getOperateTime();
				try {
					timestamp = new Timestamp(Long.valueOf(time));
					tempAsset.setOperateTime(timestamp.toString());

				} catch (NumberFormatException e) {
					// 转换不成功，说明格式正确，是（yyyy-MM-dd HH:mm:ss）
				}

				if (typeAllMap != null && typeAllMap.size() > 0) {
					type1NameList.add(typeAllMap.get(tempAsset.getType1()));
					type2NameList.add(typeAllMap.get(tempAsset.getType2()));
					type3NameList.add(typeAllMap.get(tempAsset.getType3()));
				} else {
					type1NameList.add(null);
					type2NameList.add(null);
					type3NameList.add(null);
				}

				if (lineStationAllMap != null && lineStationAllMap.size() > 0) {
					lineNameList.add(lineStationAllMap.get(tempAsset.getRouteNum()));
					stationNameList.add(lineStationAllMap.get(tempAsset.getStationNum()));
				} else {
					lineNameList.add(null);
					stationNameList.add(null);
				}

			}
			ServletActionContext.getRequest().setAttribute("type1NameList", type1NameList);
			ServletActionContext.getRequest().setAttribute("type2NameList", type2NameList);
			ServletActionContext.getRequest().setAttribute("type3NameList", type3NameList);

			ServletActionContext.getRequest().setAttribute("lineNameList", lineNameList);
			ServletActionContext.getRequest().setAttribute("stationNameList", stationNameList);

		}

		String showOrHide = ServletActionContext.getRequest().getParameter("showOrHide");
		if (showOrHide == null || "".equals(showOrHide))
			showOrHide = "hide";
		ServletActionContext.getRequest().setAttribute("showOrHide", showOrHide);

		return "showInventory";

	}

	/**
	 * 跳转到新增页面
	 */
	public String showAdd() {
		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(TYPE1);
		servletRequest.setAttribute("type1List", type1List);
		type1List = null;

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		servletRequest.setAttribute("lineList", lineList);
		lineList = null;

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(UNIT);
		servletRequest.setAttribute("unitList", unitList);
		unitList = null;

		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);
		maintainDeptList = null;

		List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
		servletRequest.setAttribute("depreciationList", depreciationList);
		depreciationList = null;

		List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
		servletRequest.setAttribute("useStatusList", useStatusList);
		useStatusList = null;

		List<CfCodeInfo> equipmentStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		servletRequest.setAttribute("equipmentStatusList", equipmentStatusList);
		equipmentStatusList = null;

		List<CfCodeInfo> namePlatePosition = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
		servletRequest.setAttribute("namePlatePosition", namePlatePosition);
		return "showAdd";
	}

	/**
	 * 保存新增
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public String saveAdd() throws IllegalAccessException, InvocationTargetException {

		Date date = new Date();
		assetInfo.setOperateTime(sdf.format(date));
		assetInfo.setRemoved("0");
		assetInfo.setXh(assetInfoService.findNextXHByRouteNum(assetInfo.getRouteNum()));

		AssetInfo tempAssetInfo = assetInfoService.findByAssetId(assetInfo.getAssetId());
		if (tempAssetInfo != null) {
			ServletActionContext.getRequest().setAttribute("failInfo", "资产编号已存在，请重新输入！");
			List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(TYPE1);
			servletRequest.setAttribute("type1List", type1List);

			/*
			 * List<Supplier> supplierList =
			 * supplierService.findSupplierByType(SUPPLIER); List<Supplier>
			 * manufacturerList =
			 * supplierService.findSupplierByType(MANUFACTURER);
			 * servletRequest.setAttribute("supplierList", supplierList);
			 * servletRequest.setAttribute("manufacturerList",
			 * manufacturerList);
			 */
			List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
			servletRequest.setAttribute("lineList", lineList);

			List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(UNIT);
			servletRequest.setAttribute("unitList", unitList);

			List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
			servletRequest.setAttribute("maintainDeptList", maintainDeptList);

			List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
			servletRequest.setAttribute("depreciationList", depreciationList);

			List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
			servletRequest.setAttribute("useStatusList", useStatusList);

			List<CfCodeInfo> equipmentStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
			servletRequest.setAttribute("equipmentStatusList", equipmentStatusList);

			List<CfCodeInfo> namePlatePosition = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
			servletRequest.setAttribute("namePlatePosition", namePlatePosition);

			List<CfCodeInfo> type2List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType2()));
			servletRequest.setAttribute("type2List", type2List);

			List<CfCodeInfo> type3List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType3()));
			servletRequest.setAttribute("type3List", type3List);

			List<CfCodeInfo> stationList = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getStationNum()));
			servletRequest.setAttribute("stationList", stationList);
			return "fail";
		}

		assetInfoService.saveAssertInfo(assetInfo); // 先保存到资产信息表

		// assetInfoHistory = getHistory(assetInfo);
		// assetInfoHistory.setAssetInfoId(assetInfo.getId().toString());
		// assetInfoService.saveAssetInfoHistory(assetInfoHistory); //保存到历史记录表

		return "saveAdd";
	}

	/**
	 * 跳转到修改页面
	 */
	public String showEdit() {

		servletRequest.setAttribute("saveType", servletRequest.getParameter("saveType"));

		String id = servletRequest.getParameter("paramId");
		assetInfo = assetInfoService.findById(id);

		// 查询使用责任人姓名
		if (assetInfo != null && assetInfo.getUsePerson() != null && !"".equals(assetInfo.getUsePerson())) {
			String[] ids = { assetInfo.getUsePerson() };
			List<UserDeptInfo> usePersonInfoList = getUserDeptInfoListByUserId(ids);
			if (usePersonInfoList != null && usePersonInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("usePerson", usePersonInfoList.get(0));
			}
		}

		// 查询权属责任人姓名
		if (assetInfo != null && assetInfo.getOwner() != null && !"".equals(assetInfo.getOwner())) {
			String[] ids = { assetInfo.getOwner() };
			List<UserDeptInfo> ownerInfoList = getUserDeptInfoListByUserId(ids);
			if (ownerInfoList != null && ownerInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("owner", ownerInfoList.get(0));
			}
		}

		// 查询生产商,供应商
		if (assetInfo != null) {
			try {
				Supplier manufacturer = supplierService.findSupplierById(Long.valueOf(assetInfo.getManufacturer()));
				ServletActionContext.getRequest().setAttribute("manufacturer", manufacturer);
			} catch (NumberFormatException e1) {
				System.err.println("转型失败");
			}

			try {
				Supplier vendor = supplierService.findSupplierById(Long.valueOf(assetInfo.getVendor()));
				ServletActionContext.getRequest().setAttribute("vendor", vendor);
			} catch (NumberFormatException e) {
				System.err.println("转型失败");
			}
		}

		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(TYPE1);
		servletRequest.setAttribute("type1List", type1List);

		List<CfCodeInfo> type2List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType2()));
		servletRequest.setAttribute("type2List", type2List);

		if (assetInfo.getType3() != null) {
			List<CfCodeInfo> type3List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType3()));
			servletRequest.setAttribute("type3List", type3List);
		}

		List<Supplier> supplierList = supplierService.findSupplierByType(SUPPLIER);
		List<Supplier> manufacturerList = supplierService.findSupplierByType(MANUFACTURER);
		servletRequest.setAttribute("supplierList", supplierList);
		servletRequest.setAttribute("manufacturerList", manufacturerList);

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		servletRequest.setAttribute("lineList", lineList);

		List<CfCodeInfo> stationList = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getStationNum()));
		servletRequest.setAttribute("stationList", stationList);

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(UNIT);
		servletRequest.setAttribute("unitList", unitList);

		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);

		List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
		servletRequest.setAttribute("depreciationList", depreciationList);

		List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
		servletRequest.setAttribute("useStatusList", useStatusList);

		List<CfCodeInfo> equipmentStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		servletRequest.setAttribute("equipmentStatusList", equipmentStatusList);

		List<CfCodeInfo> namePlatePosition = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
		servletRequest.setAttribute("namePlatePosition", namePlatePosition);

		return "showEdit";
	}

	/**
	 * 跳转到资产信息历史修改页面
	 */
	public String showHistoryEdit() {

		servletRequest.setAttribute("saveType", servletRequest.getParameter("saveType"));

		String assetId = servletRequest.getParameter("paramId");

		AssetInfoHistory assetInfoHistory2 = null;

		List<AssetInfoHistory> assetinfoHistoryList = assetInfoService.findHistoryByAssetId(assetId);
		if (assetinfoHistoryList != null && assetinfoHistoryList.size() > 0) {
			assetInfoHistory2 = assetinfoHistoryList.get(0);
		}

		// ServletActionContext.getRequest().setAttribute("assetInfoHistory",
		// assetInfoHistory);

		if (assetInfoHistory2 != null) {
			try {
				assetInfo = getAssetInfo(assetInfoHistory2);
			} catch (IllegalAccessException e) {
				System.err.println("assetInfoHistory转assetInfo失败");
			} catch (InvocationTargetException e) {
				System.err.println("assetInfoHistory转assetInfo失败");
			}
		} else {
			assetInfo = assetInfoService.findAssetInfoByAssetId(assetId);
		}

		// 查询使用责任人姓名
		if (assetInfo != null && assetInfo.getUsePerson() != null && !"".equals(assetInfo.getUsePerson())) {
			String[] ids = { assetInfo.getUsePerson() };
			List<UserDeptInfo> usePersonInfoList = getUserDeptInfoListByUserId(ids);
			if (usePersonInfoList != null && usePersonInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("usePerson", usePersonInfoList.get(0));
			}
		}

		// 查询权属责任人姓名
		if (assetInfo != null && assetInfo.getOwner() != null && !"".equals(assetInfo.getOwner())) {
			String[] ids = { assetInfo.getOwner() };
			List<UserDeptInfo> ownerInfoList = getUserDeptInfoListByUserId(ids);
			if (ownerInfoList != null && ownerInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("owner", ownerInfoList.get(0));
			}
		}

		// 查询生产商,供应商
		if (assetInfo != null) {
			try {
				Supplier manufacturer = supplierService.findSupplierById(Long.valueOf(assetInfo.getManufacturer()));
				ServletActionContext.getRequest().setAttribute("manufacturer", manufacturer);
			} catch (NumberFormatException e1) {
				System.err.println("转型失败");
			}

			try {
				Supplier vendor = supplierService.findSupplierById(Long.valueOf(assetInfo.getVendor()));
				ServletActionContext.getRequest().setAttribute("vendor", vendor);
			} catch (NumberFormatException e) {
				System.err.println("转型失败");
			}
		}

		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(TYPE1);
		servletRequest.setAttribute("type1List", type1List);

		List<CfCodeInfo> type2List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType2()));
		servletRequest.setAttribute("type2List", type2List);

		if (assetInfo.getType3() != null) {
			List<CfCodeInfo> type3List = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getType3()));
			servletRequest.setAttribute("type3List", type3List);
		}

		List<Supplier> supplierList = supplierService.findSupplierByType(SUPPLIER);
		List<Supplier> manufacturerList = supplierService.findSupplierByType(MANUFACTURER);
		servletRequest.setAttribute("supplierList", supplierList);
		servletRequest.setAttribute("manufacturerList", manufacturerList);

		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		servletRequest.setAttribute("lineList", lineList);

		List<CfCodeInfo> stationList = assetInfoService.findCfCodeInfoListTypeById(Long.valueOf(assetInfo.getStationNum()));
		servletRequest.setAttribute("stationList", stationList);

		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(UNIT);
		servletRequest.setAttribute("unitList", unitList);

		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
		servletRequest.setAttribute("maintainDeptList", maintainDeptList);

		List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
		servletRequest.setAttribute("depreciationList", depreciationList);

		List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
		servletRequest.setAttribute("useStatusList", useStatusList);

		List<CfCodeInfo> equipmentStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		servletRequest.setAttribute("equipmentStatusList", equipmentStatusList);

		List<CfCodeInfo> namePlatePosition = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
		servletRequest.setAttribute("namePlatePosition", namePlatePosition);

		return "showHistoryEdit";
	}

	/**
	 * 保存修改
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public String saveEdit() throws IllegalAccessException, InvocationTargetException {
		assetInfo.setOperateTime(sdf.format(new Date()));
		assetInfo.setRemoved("0");

		String saveType = servletRequest.getParameter("saveType"); // 0-未入册资产修改,1-已入册资产修改(插入历史表)

		if (saveType == null || "".equals(saveType)) {
			assetInfoService.updateAssetInfo(assetInfo); // 更新未入册资产
		} else {
			String assetInfoId = ServletActionContext.getRequest().getParameter("assetInfoId");
			AssetInfoHistory history = getHistory(assetInfo);
			history.setAssetInfoId(assetInfoId);
			history.setAssetInfoId(assetInfo.getId().toString());
			history.setOperateTime(sdf.format(new Date()));
			assetInfoService.saveAssetInfoHistory(history); // 将新的数据插入历史表
		}

		/*
		 * assetInfoService.deleteAssetInfoHistoryByAssetInfoId(Long.valueOf(
		 * assetInfo.getId())); //更新资产历史表，逻辑删除 AssetInfoHistory history =
		 * getHistory(assetInfo);
		 * history.setAssetInfoId(assetInfo.getId().toString());
		 * history.setOperateTime(sdf.format(new Date()));
		 * assetInfoService.saveAssetInfoHistory(history); //将新的数据插入历史表
		 */
		return "saveEdit";
	}

	/**
	 * 入册
	 */
	public String inventory() {
		// String id = servletRequest.getParameter("id");

		String idArray = ServletActionContext.getRequest().getParameter("idArray");
		if (idArray != null && !"".equals(idArray)) {
			String[] id = idArray.split(",");
			assetInfoService.inventory(id);
		}
		return null;
	}

	/**
	 * 查看详细
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String showView() throws UnsupportedEncodingException {
		String assetId = super.servletRequest.getParameter("paramId");
		assetInfo = assetInfoService.findById(assetId);

		if (assetInfo == null) {
			assetInfo = assetInfoService.findAssetInfoByAssetId(assetId);
		} else {
			// 查询使用责任人姓名
			if (assetInfo != null && assetInfo.getUsePerson() != null && !"".equals(assetInfo.getUsePerson())) {
				String[] ids = { assetInfo.getUsePerson() };
				List<UserDeptInfo> usePersonInfoList = getUserDeptInfoListByUserId(ids);
				if (usePersonInfoList != null && usePersonInfoList.size() > 0) {
					ServletActionContext.getRequest().setAttribute("usePerson", usePersonInfoList.get(0));
				}
			}

			// 查询权属责任人姓名
			if (assetInfo != null && assetInfo.getOwner() != null && !"".equals(assetInfo.getOwner())) {
				String[] ids = { assetInfo.getOwner() };
				List<UserDeptInfo> ownerInfoList = getUserDeptInfoListByUserId(ids);
				if (ownerInfoList != null && ownerInfoList.size() > 0) {
					ServletActionContext.getRequest().setAttribute("owner", ownerInfoList.get(0));
				}
			}

			if (assetInfo.getType1() != null) {
				CfCodeInfo type1 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getType1()));
				servletRequest.setAttribute("type1", type1);
			}

			if (assetInfo.getType2() != null) {
				CfCodeInfo type2 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getType2()));
				servletRequest.setAttribute("type2", type2);
			}

			if (assetInfo.getType3() != null) {
				CfCodeInfo type3 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getType3()));
				servletRequest.setAttribute("type3", type3);
			}

			if (assetInfo.getVendor() != null) {
				Supplier supplier;
				try {
					supplier = supplierService.findSupplierById(Long.valueOf(assetInfo.getVendor()));
					servletRequest.setAttribute("supplier", supplier);
				} catch (NumberFormatException e) {
					System.out.println("供应商数据有误");
				}

			}
			if (assetInfo.getManufacturer() != null) {
				try {
					Supplier manufacturer = supplierService.findSupplierById(Long.valueOf(assetInfo.getManufacturer()));
					servletRequest.setAttribute("manufacturer", manufacturer);
				} catch (NumberFormatException e) {
					System.out.println("生产商数据错误");
				}
			}

			if (assetInfo.getOwnerDuty() != null) {
				CfCodeInfo unit = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getOwnerDuty()));
				servletRequest.setAttribute("unit", unit);
			}

			if (assetInfo.getRouteNum() != null) {
				CfCodeInfo line = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getRouteNum()));
				servletRequest.setAttribute("line", line);
			}

			if (assetInfo.getStationNum() != null) {
				CfCodeInfo station = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getStationNum()));
				servletRequest.setAttribute("station", station);
			}

			if (assetInfo.getMaintainDep() != null) {
				CfCodeInfo maintainDept = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getMaintainDep()));
				servletRequest.setAttribute("maintainDept", maintainDept);
			}

			if (assetInfo.getUserDuty() != null) {
				CfCodeInfo userDuty = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getUserDuty()));
				servletRequest.setAttribute("userDuty", userDuty);
			}

			if (assetInfo.getDepreciationWay() != null) {
				CfCodeInfo depreciationWay = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getDepreciationWay()));
				servletRequest.setAttribute("depreciationWay", depreciationWay);
			}

			if (assetInfo.getUseState() != null) {
				CfCodeInfo useState = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getUseState()));
				servletRequest.setAttribute("useState", useState);
			}

			if (assetInfo.getEquipmentState() != null) {
				CfCodeInfo equipmentState = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getEquipmentState()));
				servletRequest.setAttribute("equipmentState", equipmentState);
			}

			if (assetInfo.getMpztbw() != null) {
				CfCodeInfo mpztbw = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfo.getMpztbw()));
				servletRequest.setAttribute("mpztbw", mpztbw);

			}
		}

		// 查询资产相关设备
		List<EquipmentInfo> equipmentList = equipmentInfoService.findAllEquipmentInfoByAssetId(assetInfo.getAssetId());
		ServletActionContext.getRequest().setAttribute("equipmentList", equipmentList);

		List<Supplier> manufacturerList = supplierService.findSupplierByType("0");
		Map<String, String> manufacturerMap = null;
		List<String> manufacturerNameList; // 生产商名称
		if (manufacturerList != null && manufacturerList.size() > 0) {
			manufacturerMap = new HashMap<String, String>();
			for (int i = 0; i < manufacturerList.size(); i++) {
				manufacturerMap.put(manufacturerList.get(i).getId() + "", manufacturerList.get(i).getName());
			}
		}
		if (equipmentList != null && equipmentList.size() > 0) {
			manufacturerNameList = new ArrayList<String>();
			for (int i = 0; i < equipmentList.size(); i++) {
				manufacturerNameList.add(manufacturerMap.get(equipmentList.get(i).getManufacturer()));
			}
			ServletActionContext.getRequest().setAttribute("manufacturerNameList", manufacturerNameList);
		}

		// 查询资产盘点结果
		List<AssetTaskCheck> taskCheckList = assetTaskCheckService.findByAssetInfoId(assetInfo.getAssetId());
		List<String> resultList = null;
		if (taskCheckList != null && taskCheckList.size() > 0) {

			// 盘点结果
			resultList = new ArrayList<String>();
			for (int i = 0; i < taskCheckList.size(); i++) {
				CfCodeInfo cfCodeInfo;
				try {
					cfCodeInfo = assetTaskCheckService.findCfCodeInfoById(Long.valueOf(taskCheckList.get(i).getCheckinfo()));
					if (cfCodeInfo != null)
						resultList.add(cfCodeInfo.getName());
					else
						resultList.add("未盘点");
				} catch (NumberFormatException e) {
					resultList.add("未盘点");
				}

			}
		}
		ServletActionContext.getRequest().setAttribute("taskCheckList", taskCheckList);
		ServletActionContext.getRequest().setAttribute("resultList", resultList);

		return "showView";
	}

	/**
	 * 删除(物理删除,将删除后的数据存放在history表中)
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public String delete() throws IllegalAccessException, InvocationTargetException {
		String id = servletRequest.getParameter("id");
		assetInfo = assetInfoService.findById(id);
		assetInfoService.deleteAssetInfo(assetInfo); // 更新资产表，物理删除

		// assetInfoService.deleteAssetInfoHistoryByAssetInfoId(Long.valueOf(id));
		// //更新资产历史表，逻辑删除

		return AJAX;
	}

	/**
	 * 根据资产id和序号查询
	 */
	public String findAssetInfoByAssetId() {

		AssetInfo assetInfoByAssetId = null, assetInfoByXH = null;
		String info = null;

		String assetId = ServletActionContext.getRequest().getParameter("assetId");
		String xh = ServletActionContext.getRequest().getParameter("xh");

		if (assetId != null && !"".equals(assetId)) {
			assetInfoByAssetId = assetInfoService.findByAssetId(assetId);
		}
		if (xh != null && !"".equals(xh)) {
			assetInfoByXH = assetInfoService.findByXH(xh);
		}

		if (assetInfoByAssetId != null) { // 资产编号重复
			if (assetInfoByXH == null) { // 序号不重复
				info = "{\"info\":\"资产编号已存在！\"}";
				createJSonData(info);
			}
		}
		return AJAX;
	}

	/**
	 * 显示资产历史信息详细
	 */
	public String showAssetInfoHistory() {

		AssetInfoHistory assetInfoHistory = null;
		List<AssetInfoHistory> assetInfoHistoryList = null;

		String paramHistoryId = ServletActionContext.getRequest().getParameter("paramHistoryId"); // 历史记录主键
		String assetId = ServletActionContext.getRequest().getParameter("paramId"); // /资产编号
		String inventory = ServletActionContext.getRequest().getParameter("inventory"); // 是否查询的是清册数据

		assetInfo = assetInfoService.findAssetInfoByAssetId(assetId); // 清册中的数据
		assetInfoHistoryList = assetInfoService.findHistoryByAssetId(assetId); // 根据assetId查询单个或多个history,可能一个都没有
		if (inventory != null && !"".equals(inventory)) { // 查询的是清册数据
			assetInfoHistory = getHistory(assetInfo);
		} else { // 查询的是历史数据
			if (paramHistoryId == null || "".equals(paramHistoryId)) { // 查询历史表中最新数据
				if (assetInfoHistoryList != null && assetInfoHistoryList.size() > 0) { // 有历史记录
					assetInfoHistory = assetInfoHistoryList.get(0);
					assetInfoHistoryList.remove(0);
					assetInfoHistoryList.add(getHistory(assetInfo));
				} else {
					assetInfoHistoryList = new ArrayList<AssetInfoHistory>();
					assetInfoHistory = getHistory(assetInfo);
				}
			} else {
				assetInfoHistory = assetInfoService.findAssetInfoHistoryById(paramHistoryId); // 根据主键id查询历史表
				for (int i = 0; i < assetInfoHistoryList.size(); i++) {
					if (paramHistoryId.equals(assetInfoHistoryList.get(i).getId())) {
						assetInfoHistoryList.remove(i);
					}
				}
				assetInfoHistoryList.add(getHistory(assetInfo));
			}

		}
		ServletActionContext.getRequest().setAttribute("assetInfoHistory", assetInfoHistory);

		// 查询使用责任人姓名
		if (assetInfoHistory != null && assetInfoHistory.getUsePerson() != null && !"".equals(assetInfoHistory.getUsePerson())) {
			String[] ids = { assetInfoHistory.getUsePerson() };
			List<UserDeptInfo> usePersonInfoList = getUserDeptInfoListByUserId(ids);
			if (usePersonInfoList != null && usePersonInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("usePerson", usePersonInfoList.get(0));
			}
		}

		// 查询权属责任人姓名
		if (assetInfoHistory != null && assetInfoHistory.getOwner() != null && !"".equals(assetInfoHistory.getOwner())) {
			String[] ids = { assetInfoHistory.getOwner() };
			List<UserDeptInfo> ownerInfoList = getUserDeptInfoListByUserId(ids);
			if (ownerInfoList != null && ownerInfoList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("owner", ownerInfoList.get(0));
			}
		}

		if (assetInfoHistory.getType1() != null && !"".equals(assetInfoHistory.getType1())) {
			CfCodeInfo type1 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getType1()));
			servletRequest.setAttribute("type1", type1);
		}

		if (assetInfoHistory.getType2() != null && !"".equals(assetInfoHistory.getType2())) {
			CfCodeInfo type2 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getType2()));
			servletRequest.setAttribute("type2", type2);
		}

		if (assetInfoHistory.getType3() != null && !"".equals(assetInfoHistory.getType3())) {
			CfCodeInfo type3 = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getType3()));
			servletRequest.setAttribute("type3", type3);
		}

		if (assetInfoHistory.getVendor() != null && !"".equals(assetInfoHistory.getVendor())) {
			Supplier supplier = supplierService.findSupplierById(Long.valueOf(assetInfoHistory.getVendor()));
			servletRequest.setAttribute("supplier", supplier);
		}
		if (assetInfoHistory.getManufacturer() != null && !"".equals(assetInfoHistory.getManufacturer())) {
			Supplier manufacturer = supplierService.findSupplierById(Long.valueOf(assetInfoHistory.getManufacturer()));
			servletRequest.setAttribute("manufacturer", manufacturer);
		}

		if (assetInfoHistory.getOwnerDuty() != null && !"".equals(assetInfoHistory.getOwnerDuty())) {
			CfCodeInfo unit = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getOwnerDuty()));
			servletRequest.setAttribute("unit", unit);
		}

		if (assetInfoHistory.getRouteNum() != null && !"".equals(assetInfoHistory.getRouteNum())) {
			CfCodeInfo line = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getRouteNum()));
			servletRequest.setAttribute("line", line);
		}

		if (assetInfoHistory.getStationNum() != null && !"".equals(assetInfoHistory.getStationNum())) {
			CfCodeInfo station = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getStationNum()));
			servletRequest.setAttribute("station", station);
		}

		if (assetInfoHistory.getMaintainDep() != null && !"".equals(assetInfoHistory.getMaintainDep())) {
			CfCodeInfo maintainDept = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getMaintainDep()));
			servletRequest.setAttribute("maintainDept", maintainDept);
		}

		if (assetInfoHistory.getUserDuty() != null && !"".equals(assetInfoHistory.getUserDuty())) {
			CfCodeInfo userDuty = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getUserDuty()));
			servletRequest.setAttribute("userDuty", userDuty);
		}

		if (assetInfoHistory.getDepreciationWay() != null && !"".equals(assetInfoHistory.getDepreciationWay())) {
			CfCodeInfo depreciationWay = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getDepreciationWay()));
			servletRequest.setAttribute("depreciationWay", depreciationWay);
		}

		if (assetInfoHistory.getUseState() != null && !"".equals(assetInfoHistory.getUseState())) {
			CfCodeInfo useState = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getUseState()));
			servletRequest.setAttribute("useState", useState);
		}

		if (assetInfoHistory.getEquipmentState() != null && !"".equals(assetInfoHistory.getEquipmentState())) {
			CfCodeInfo equipmentState = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getEquipmentState()));
			servletRequest.setAttribute("equipmentState", equipmentState);
		}

		if (assetInfoHistory.getMpztbw() != null && !"".equals(assetInfoHistory.getMpztbw())) {
			CfCodeInfo mpztbw = assetInfoService.findCfCodeInfoById(Long.valueOf(assetInfoHistory.getMpztbw()));
			servletRequest.setAttribute("mpztbw", mpztbw);
		}

		ServletActionContext.getRequest().setAttribute("assetInfoHistory", assetInfoHistory);

		// 查询资产相关设备
		List<EquipmentInfo> equipmentList = equipmentInfoService.findAllEquipmentInfoByAssetId(assetId);
		if (equipmentList != null && equipmentList.size() > 0) {
			List<Supplier> manufactuerList = supplierService.findSupplierByType("0");
			Map<String, String> manufacturerNameMap = new HashMap<String, String>();
			if (manufactuerList != null && manufactuerList.size() > 0) {
				for (int i = 0; i < manufactuerList.size(); i++) {
					manufacturerNameMap.put(manufactuerList.get(i).getId() + "", manufactuerList.get(i).getName());
				}
			}

			List<String> supplierNameList = new ArrayList<String>();

			for (int i = 0; i < equipmentList.size(); i++) {
				supplierNameList.add(manufacturerNameMap.get(equipmentList.get(i).getManufacturer()));
			}
			ServletActionContext.getRequest().setAttribute("supplierNameList", supplierNameList);
		}

		ServletActionContext.getRequest().setAttribute("equipmentList", equipmentList);

		List<String> type1HistoryList, type2HistoryList, type3HistoryList, lineHistoryList, stationHistoryList;
		if (assetInfoHistoryList != null && assetInfoHistoryList.size() > 0) {
			type1HistoryList = new ArrayList<String>();
			type2HistoryList = new ArrayList<String>();
			type3HistoryList = new ArrayList<String>();
			lineHistoryList = new ArrayList<String>();
			stationHistoryList = new ArrayList<String>();

			// 查询分页数据中的大中小类所对应的中文名称
			List<CfCodeInfo> typeAllList = assetInfoService.findCfCodeInfoByTypeId(TYPE_ID); // 大、中、小类所有数据
			Map<String, String> typeAllMap = null;
			if (typeAllList != null && typeAllList.size() > 0) {
				typeAllMap = new HashMap<String, String>();
				for (CfCodeInfo cfCodeInfo : typeAllList) {
					typeAllMap.put(cfCodeInfo.getId().toString(), cfCodeInfo.getName());
				}
			}

			// 查询分页数据中的线路和车站所对应的中文名称
			List<CfCodeInfo> lineAndStationAllList = assetInfoService.findCfCodeInfoByTypeId(LINE_STATION_ID);
			Map<String, String> lineStationAllMap = null;
			List<String> lineNameList = null, stationNameList = null;
			if (lineAndStationAllList != null && lineAndStationAllList.size() > 0) {
				lineNameList = new ArrayList<String>();
				stationNameList = new ArrayList<String>();
				lineStationAllMap = new HashMap<String, String>();
				for (int i = 0; i < lineAndStationAllList.size(); i++) {
					lineStationAllMap.put(lineAndStationAllList.get(i).getId().toString(), lineAndStationAllList.get(i).getName());
				}
			}

			for (int i = 0; i < assetInfoHistoryList.size(); i++) {
				type1HistoryList.add(typeAllMap.get(assetInfoHistoryList.get(i).getType1()));
				type2HistoryList.add(typeAllMap.get(assetInfoHistoryList.get(i).getType2()));
				type3HistoryList.add(typeAllMap.get(assetInfoHistoryList.get(i).getType3()));
				lineHistoryList.add(lineStationAllMap.get(assetInfoHistoryList.get(i).getRouteNum()));
				stationHistoryList.add(lineStationAllMap.get(assetInfoHistoryList.get(i).getStationNum()));

			}

			ServletActionContext.getRequest().setAttribute("type1HistoryList", type1HistoryList);
			ServletActionContext.getRequest().setAttribute("type2HistoryList", type2HistoryList);
			ServletActionContext.getRequest().setAttribute("type3HistoryList", type3HistoryList);
			ServletActionContext.getRequest().setAttribute("lineHistoryList", lineHistoryList);
			ServletActionContext.getRequest().setAttribute("stationHistoryList", stationHistoryList);
		}

		ServletActionContext.getRequest().setAttribute("assetInfoHistoryList", assetInfoHistoryList);

		// 查询资产盘点结果
		List<AssetTaskCheck> taskCheckList = assetTaskCheckService.findByAssetInfoId(assetInfo.getAssetId());
		List<String> resultList = null;

		if (taskCheckList != null && taskCheckList.size() > 0) {
			resultList = new ArrayList<String>();
			for (int i = 0; i < taskCheckList.size(); i++) {
				CfCodeInfo cfCodeInfo = assetTaskCheckService.findCfCodeInfoByTypeIdAndCode(String.valueOf(CHECK_TYPE), taskCheckList.get(i).getCheckinfo());
				if (cfCodeInfo != null)
					resultList.add(cfCodeInfo.getName());
				else
					resultList.add("未盘点");
			}
		}
		ServletActionContext.getRequest().setAttribute("taskCheckList", taskCheckList);
		ServletActionContext.getRequest().setAttribute("resultList", resultList);

		return "showAssetInfoHistory";
	}

	/**
	 * 根据大类查询中类,或者根据种类查询小类
	 */
	public String findTypeByCodeInfoId() {
		String codeInfoId = servletRequest.getParameter("id");
		List<CfCodeInfo> type2List = assetInfoService.findCfInfoCodeById(Long.valueOf(codeInfoId));
		String jsonData = VOUtils.getJsonDataFromCollection(type2List);
		createJSonData(jsonData);

		return AJAX;
	}

	/**
	 * 取得历史资产信息
	 * 
	 * @param assetInfo
	 *            资产信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public AssetInfoHistory getHistory(AssetInfo assetInfo) {
		AssetInfoHistory history = new AssetInfoHistory();
		try {
			BeanUtils.copyProperties(history, assetInfo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		history.setId(null);
		history.setAssetInfoId(assetInfo.getAssetId());
		return history;
	}

	/**
	 * 取得资产信息
	 * 
	 * @param assetInfo
	 *            资产信息
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public AssetInfo getAssetInfo(AssetInfoHistory assetInfoHistory) throws IllegalAccessException, InvocationTargetException {
		AssetInfo assetInfo = new AssetInfo();
		BeanUtils.copyProperties(assetInfo, assetInfoHistory);
		try {
			assetInfo.setId(Long.valueOf(assetInfoHistory.getAssetInfoId()));
		} catch (NumberFormatException e) {
			System.out.println("类型转换失败");
		}

		return assetInfo;
	}

	/**
	 * 根据线路查询车站
	 */
	public String findStationByLine() {
		String id = servletRequest.getParameter("id");
		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(Long.valueOf(id));
		String jsonData = VOUtils.getJsonDataFromCollection(lineList);
		createJSonData(jsonData);

		return AJAX;
	}

	/**
	 * 跳转到文件上传页面
	 */
	public String showFileUpload() {
		return "showFileUpload";
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
	public String fileUpload() throws IllegalAccessException, InvocationTargetException {

		List<AssetInfo> excelAssetInfoList = new ArrayList<AssetInfo>();
		List<AssetInfoHistory> historyList = new ArrayList<AssetInfoHistory>();
		String responseData = "";
		HSSFRow row = null;

		String savePath;
		String extName = ""; // 扩展名
		String newFileName = ""; // 新文件名
		String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());// 当前时间
		int saveStatus; // 保存状态,0:新增保存，1:修改保存，2:报错不保存
		String errorInfo; // 每一行的错误信息
		String errorAll = "插入失败的行："; // 第0行，第0列，显示插入失败的数据列

		// 获取资源文件中定义的上传路径
		savePath = ServletActionContext.getServletContext().getRealPath("jsp" + File.separator + "asset" + File.separator + "file" + File.separator);
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
			for (int i = sheet.getFirstRowNum() + 2; i < count; i++) {
				errorInfo = "";

				saveStatus = 0;
				AssetInfo excelAssetInfo = new AssetInfo();
				row = sheet.getRow(i); // 循环行
				if(row==null){
					continue;
				}
				
				// 序号，第1列(从0开始)
				cellData = getCellData(row, 1);
				excelAssetInfo.setXh(cellData);

				// 资产编号,第2列
				cellData = getCellData(row, 2);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写资产编号，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setAssetId(cellData);
					if (cellData == null || cellData.length() != 20) {
						errorInfo += "资产编号位数不对，";
						saveStatus = 2;
					}
				}
				

				// 资产名称,第3列
				cellData = getCellData(row, 3);
				excelAssetInfo.setAssetName(cellData);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写资产名称，";
					saveStatus = 2;
				}

				// 大类,第4列
				cellData = getCellData(row, 4);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写大类，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndName(TYPE1, cellData);
					excelAssetInfo.setType1(dataId);
					if (dataId == null) {
						errorInfo += "大类类型不对，";
						saveStatus = 2;
					}
				}
				

				// 中类,第5列
				cellData = getCellData(row, 5);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写中类，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findByTypeIdAndNameAndCodeInfoId(TYPE_ID, cellData, excelAssetInfo.getType1());
					excelAssetInfo.setType2(dataId);
					if (dataId == null) {
						errorInfo += "中类类型不对，";
						saveStatus = 2;
					}
				}
				

				// 小类,第6列
				cellData = getCellData(row, 6);
				dataId = assetInfoService.findByTypeIdAndNameAndCodeInfoId(TYPE_ID, cellData, excelAssetInfo.getType2());
				excelAssetInfo.setType3(dataId);
				if (cellData != null && !"".equals(cellData) && dataId == null) {
					errorInfo += "小类类型不对，";
					saveStatus = 2;
				}

				// 数量单位,第7列
				cellData = getCellData(row, 7);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写数量单位，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setDw(cellData);	
				}

				// 数量,第8列
				cellData = getCellData(row, 8);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写数量，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setSl(cellData);
				}
				

				// 规格型号,第9列
				cellData = getCellData(row, 9);
				excelAssetInfo.setSpecification(cellData);

				// 规格产地,第10列
				cellData = getCellData(row, 10);
				excelAssetInfo.setCd(cellData);

				// 生产厂商,第11列
				cellData = getCellData(row, 11);
				dataId = supplierService.findIdByTypeAndName(MANUFACTURER, cellData);
				excelAssetInfo.setManufacturer(dataId);

				// 供应商,第12列
				cellData = getCellData(row, 12);
				dataId = supplierService.findIdByTypeAndName(SUPPLIER, cellData);
				excelAssetInfo.setVendor(dataId);

				// 出厂日期,第13列
				cellData = getCellData(row, 13);
				if(cellData!=null && !"".equals(cellData)){
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setMakeTime(date);
					}else{
						errorInfo += "出厂日期格式错误，";
						saveStatus = 2;
					}
				}
				

				// 供应日期,第14列
				cellData = getCellData(row, 14);
				if(cellData!=null && !"".equals(cellData)){
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setProvideTime(date);
					}else{
						errorInfo += "供应日期格式错误，";
						saveStatus = 2;
					}
				}
				

				// 安装地点,第15列
				cellData = getCellData(row, 15);
				excelAssetInfo.setBuildAddr(cellData);

				// 权属单位,第16列
				cellData = getCellData(row, 16);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写权属单位，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndCode(UNIT, cellData);
					excelAssetInfo.setOwnerDuty(dataId);
				}
				

				// 使用单位,第17列
				cellData = getCellData(row, 17);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写使用单位，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndCode(UNIT, cellData);
					excelAssetInfo.setUserDuty(dataId);
				}
				

				// 维护部门,第18列
				cellData = getCellData(row, 18);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写维护部门，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndCode(MAINTAIN_DEPT, cellData);
					excelAssetInfo.setMaintainDep(dataId);
				}
				

				// 所属线路,第19列
				cellData = getCellData(row, 19);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写所属线路，";
					saveStatus = 2;
				}else{
					dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndCode(LINE_ID, cellData);
					excelAssetInfo.setRouteNum(dataId);
				}
				

				// 所属车站,第20列
				cellData = getCellData(row, 20);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写所属车站，";
					saveStatus = 2;
				}else{
					String code = getCellData(row, 19) + cellData;
					dataId = assetInfoService.findCfCodeInfoIdByTypeIdAndCode(STATION_TYPE_ID, code);
					excelAssetInfo.setStationNum(dataId);
				}
				

				// 权属责任人,第21列
				cellData = getCellData(row, 21);
				excelAssetInfo.setOwner(cellData);

				// 使用责任人,第22列
				cellData = getCellData(row, 22);
				excelAssetInfo.setUsePerson(cellData);

				// 开始使用时间,第23列
				cellData = getCellData(row, 23);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写开始使用时间，";
					saveStatus = 2;
				}else{
					String date = isDateFormatted(cellData);
					if(date==null){
						errorInfo += "开始使用时间格式错误，";
						saveStatus = 2;
					}else{
						excelAssetInfo.setUseTime(date);
					}
					
				}
				

				// 停止使用时间,第24列
				cellData = getCellData(row, 24);
				if(cellData!=null && !"".equals(cellData)){
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setStopuseTime(date);
					}else{
						errorInfo += "停止使用时间格式错误，";
						saveStatus = 2;
					}
				}
				

				// 报废时间,第25列
				cellData = getCellData(row, 25);
				if(cellData!=null && !"".equals(cellData)){
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setDiscardTime(date);
					}else{
						errorInfo += "报废时间格式错误，";
						saveStatus = 2;
					}
				}
				

				// 移交时间,第26列
				cellData = getCellData(row, 26);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写移交时间，";
					saveStatus = 2;
				}else{
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setHandoverTime(cellData);
					}else{
						errorInfo += "移交时间格式错误，";
						saveStatus = 2;
					}
				}
				

				// 当前使用状态,第27列
				cellData = getCellData(row, 27);
				dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndName(USE_STATUS, cellData);
				excelAssetInfo.setUseState(dataId);

				// 当前设备状态,第28列
				cellData = getCellData(row, 28);
				dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndName(EQUIPMENT_STATUS, cellData);
				excelAssetInfo.setEquipmentState(dataId);

				// 资产图片,第29列
				cellData = getCellData(row, 29);
				excelAssetInfo.setAssetPic(cellData);

				// 设计使用年限,第30列
				cellData = getCellData(row, 30);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写设计使用年限，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setSjsynx(cellData);
				}
				

				// 预期使用寿命,第31列
				cellData = getCellData(row, 31);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写预期使用寿命，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setUseLife(cellData);
				}
				

				// 保修期至,第32列
				cellData = getCellData(row, 32);
				if(cellData!=null && !"".equals(cellData)){
					String date = isDateFormatted(cellData);
					if(date!=null){
						excelAssetInfo.setShelfLife(date);
					}else{
						errorInfo += "保修期至格式错误，";
						saveStatus = 2;
					}
				}
				

				// 大修频次,第33列
				cellData = getCellData(row, 33);
				if(cellData==null || "".equals(cellData)){
					errorInfo += "必须填写大修频次，";
					saveStatus = 2;
				}else{
					excelAssetInfo.setDxpl(cellData);
				}
				

				// 出厂价,第34列
				cellData = getCellData(row, 34);
				excelAssetInfo.setLeaveFactoryPrice(cellData);

				// 合同价,第35列
				cellData = getCellData(row, 35);
				excelAssetInfo.setCompactPrice(cellData);

				// 原值,第36列
				cellData = getCellData(row, 36);
				excelAssetInfo.setYuanzhi(cellData);
				excelAssetInfo.setFinalAccountPrice(cellData);

				// 技术、规格、操作资料及清单,第37列
				cellData = getCellData(row, 37);
				excelAssetInfo.setJszlqd(cellData);

				// 折旧方法,第38列
				cellData = getCellData(row, 38);
				dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndName(DEPRECIATION, cellData);
				excelAssetInfo.setDepreciationWay(dataId);

				// 月折旧率,第39列
				cellData = getCellData(row, 39);
				excelAssetInfo.setYzjl(cellData);

				// 本期折旧,第40列
				cellData = getCellData(row, 40);
				excelAssetInfo.setBenqizhijiu(cellData);

				// 累计折旧,第41列
				cellData = getCellData(row, 41);
				excelAssetInfo.setLeijizhejiu(cellData);

				// 净值,第42列
				cellData = getCellData(row, 42);
				excelAssetInfo.setJingzhi(cellData);

				// 铭牌张贴部位,第43列
				cellData = getCellData(row, 43);
				dataId = assetInfoService.findCfCodeInfoIdByCodeInfoIdAndName(NAMEPLATE_POSITION, cellData);
				excelAssetInfo.setMpztbw(dataId);

				// 设备清单,第44列
				cellData = getCellData(row, 44);
				excelAssetInfo.setShebeiqingdan(cellData);

				// 备注,第45列
				cellData = getCellData(row, 45);
				excelAssetInfo.setMemo(cellData);

				HSSFCell cell0 =null;
				if(row!=null){
					cell0 = row.createCell(0); // 创建第一列,单元格
				}
				// 创建单元格样式
				HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();

				// 根据“线路”和“资产编号”查询，如果序号相同，修改成，序号不同，修改失败
				AssetInfo tempAssetInfo = assetInfoService.findByRouteNumAndAssetId(excelAssetInfo.getRouteNum(), excelAssetInfo.getAssetId());
				if (tempAssetInfo != null) { // 数据存在
					if(excelAssetInfo.getXh()==null || "".equals(excelAssetInfo.getXh())){
						errorInfo += "序号不能为空！";
						saveStatus = 2;
					}else{
						if (tempAssetInfo.getXh()!=null && !tempAssetInfo.getXh().equals(excelAssetInfo.getXh())) { // 序号不相等，修改失败
							errorInfo += "与原序号" + tempAssetInfo.getXh() + "的资产编号相同，修改失败，";
							saveStatus = 2;
							/*
							 * HSSFFont font = hssfWorkbook.createFont();
							 * font.setColor(HSSFColor.RED.index);
							 * cellStyle.setFont(font); font = null;
							 */
						} else { // 序号相同，修改成功
							excelAssetInfo.setId(tempAssetInfo.getId());
							saveStatus = 1;
						}
					}
				}

				// 检查资产编号是否符合规范
				String assetId = excelAssetInfo.getAssetId();
				if (tempAssetInfo == null && assetId != null && !"".equals(assetId)) {
					if (assetId.length() == 20) {
						String zcqsh = getCellData(row, 16); // 资产权属号
						if (!(assetId.substring(0, 2)).equals(zcqsh)) {
							errorInfo += "资产编号第1和第2位与权属单位不一致,";
							saveStatus = 2;
						}
						String syqsh = getCellData(row, 17); // 使用权属号
						if (!(assetId.substring(2, 4)).equals(syqsh)) {
							errorInfo += "资产编号第3和第4位与使用单位不一致,";
							saveStatus = 2;
						}
						String whbmh = getCellData(row, 18); // 维护部门
						if (!(assetId.substring(5, 7)).equals(whbmh)) {
							errorInfo += "资产编号第6和第7位与维护部门不一致,";
							saveStatus = 2;
						}
						String ssxl = getCellData(row, 19); // 所属线路
						if (!(assetId.substring(7, 9)).equals(ssxl)) {
							errorInfo += "资产编号第8和第9位与所属线路不一致,";
							saveStatus = 2;
						}
						String sscz = getCellData(row, 20); // 所属车站
						if (excelAssetInfo.getStationNum() != null && !"".equals(excelAssetInfo.getStationNum()) && !(assetId.substring(9, 11)).equals(sscz)) {
							errorInfo += "资产编号第10和第11位与所属车站不一致,";
							saveStatus = 2;
						}
						String dl = assetInfoService.findCodeById(excelAssetInfo.getType1()); // 大类
						if (!(assetId.substring(11, 13)).equals(dl)) {
							errorInfo += "资产编号第12和第13位与大类不一致,";
							saveStatus = 2;
						} else {
							String zl = assetInfoService.findCodeById(excelAssetInfo.getType2()); // 中类
							if (zl == null || !(assetId.substring(13, 15)).equals((zl.substring(zl.length() - 2, zl.length())))) {
								errorInfo += "资产编号第14和第15位与中类不一致,";
								saveStatus = 2;
							} else {
								String xl = assetInfoService.findCodeById(excelAssetInfo.getType3()); // 小类
								if (xl != null && !(assetId.substring(15, 17)).equals((xl.substring(xl.length() - 2, xl.length())))) {
									errorInfo += "资产编号第16和第17位与小类不一致,";
									saveStatus = 2;
								}
							}
						}
					}
				}

				if (saveStatus == 1) {
					cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index); // 设置背景色为黄色,修改成功
					excelAssetInfo.setRemoved("0");
					excelAssetInfo.setOperateTime(sdf.format(new Date()));
					excelAssetInfo.setRegistry("0");
					excelAssetInfoList.add(excelAssetInfo);
					modifyCount++;
				} else if (saveStatus == 2) {
					cellStyle.setFillForegroundColor(HSSFColor.RED.index); // 设置背景色为红色,本条数据无效
					errorAll += i + ",";
					errorCount++;
				} else {
					cellStyle.setFillForegroundColor(HSSFColor.WHITE.index); // 设置背景白色，无信息显示
					excelAssetInfo.setRemoved("0");
					excelAssetInfo.setRegistry("0");
					excelAssetInfo.setOperateTime(sdf.format(new Date()));
					excelAssetInfoList.add(excelAssetInfo);
					successCount++;
				}
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				if(cell0!=null){
					cell0.setCellStyle(cellStyle); // 为第一行设置颜色
					if (errorInfo != null && !"".equals(errorInfo)) {
						errorInfo = errorInfo.substring(0, errorInfo.length() - 1);
						cell0.setCellValue(errorInfo); // 为第一行设置错误信息
					}
				}
				cellStyle = null;
			}

			if (errorAll.length() > 7) {
				errorAll = errorAll.substring(0, errorAll.length() - 1);
				sheet.getRow(0).createCell(0).setCellValue(errorAll);
			}

			if (errorCount == 0) { // 数据全部正确
				assetInfoService.saveAssetInfoList(excelAssetInfoList); // 插入数据库资产表
				/*
				 * AssetInfoHistory assetInfoHistory ; int a =
				 * excelAssetInfoList.size(); for(int i=0;
				 * i<excelAssetInfoList.size(); i++){ assetInfoHistory = null;
				 * assetInfoHistory = getHistory(excelAssetInfoList.get(i));
				 * assetInfoHistory
				 * .setAssetInfoId(excelAssetInfoList.get(i).getId
				 * ().toString()); historyList.add(assetInfoHistory); }
				 * assetInfoService.saveAssetInfoHistoryList(historyList);
				 * //插入数据库资产历史表
				 */
			}

		} catch (FileNotFoundException e) {
			responseData = "上传文件失败，请稍后再试！";
			e.printStackTrace();
		} catch (IOException e) {
			responseData = "上传文件失败，请稍后再试！";
			e.printStackTrace();
		} finally {
			// responseData =
			// (.append("\u6761;\u4FEE\u6539").append(modifyCount).append("\u6761;\u9519\u8BEF").append(errorCount).append("\u6761").toString();
			responseData = "成功:" + successCount + "条, 修改:" + modifyCount + "条, 错误:" + errorCount + "条";
			System.out.println("要插入的数据共有：" + excelAssetInfoList.size());
			System.out.println("excel总条数：" + (count - 2));
			System.out.println("错误数据:" + errorCount);
			System.out.println("成功数据:" + successCount);
			System.out.println("修改数据" + modifyCount);
		}

		newFile.delete(); // 删除上传的以时间为文件名的文件

		File[] files = dir.listFiles();
		boolean isFileExist = false; // 文件是否存在,反馈文件是否存在
		int version = -1; // 反馈的版本号
		int maxVersion = 1; // 最大版本号
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.startsWith(fileFileName.substring(0, fileFileName.lastIndexOf(".")))) { // 是反馈文件
					String prefix = fileFileName.substring(0, fileFileName.lastIndexOf("."));
					String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					// version = Integer.valueOf(fileName.replace(prefix,
					// "").replace("反馈","").replace(suffix, ""));
					version = Integer.valueOf(fileName.substring(fileName.lastIndexOf(".") - 1, fileName.lastIndexOf(".")));
					isFileExist = true;
					if (version > maxVersion) {
						maxVersion = version;
					}
				}
			}
		}

		if (version != -1)
			maxVersion++;

		// 生成反馈的文件名

		String downloadFileName = fileFileName.substring(0, fileFileName.lastIndexOf(".")) + "反馈" + maxVersion + fileFileName.substring(fileFileName.lastIndexOf("."), fileFileName.length());
		String feedbackFileName = savePath + File.separator + downloadFileName;

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(feedbackFileName);
			hssfWorkbook.write(fos);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		servletResponse.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
		try {
			// servletResponse.getWriter().print((new
			// StringBuilder("{'msg':'")).append(responseData).append("','fileName':'").append(downloadFileName).append("'}").toString());
			this.servletResponse.getWriter().print("{'msg':'" + responseData + "','fileName':'" + downloadFileName + "'}");
			// this.servletResponse.getWriter().print(downloadFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 下载反馈文件
	 */
	public String downloadFile() {

		String fileName = ServletActionContext.getRequest().getParameter("fileName");
		String downloadPath = ServletActionContext.getServletContext().getRealPath("jsp" + File.separator + "asset" + File.separator + "file");

		String fullPath = downloadPath + File.separator + fileName;
		InputStream is = null;
		try {
			is = new FileInputStream(fullPath);

			int len = 0;
			byte[] buffers = new byte[1024];
			this.getServletResponse().setCharacterEncoding("utf-8");
			this.getServletResponse().reset();
			this.getServletResponse().setContentType("application/x-msdownload");
			// fileName = URLEncoder.encode(fileName, "UTF-8");

			// fileName = URLEncoder.encode(fileName,"UTF-8");

			/*
			 * if(fileName.length()>150){//解决IE 6.0 bug fileName=new
			 * String(fileName.getBytes("GBK"),"ISO-8859-1"); }
			 */
			// new String(fileName.getBytes("gb2312"),"ISO8859-1"));

			this.getServletResponse().addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			OutputStream os = this.getServletResponse().getOutputStream();
			// 把文件内容通过输出流打印到页面上供下载
			while ((len = is.read(buffers)) != -1) {
				os.write(buffers, 0, len);
				os.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
				if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					result = String.format("%.0f", cell.getNumericCellValue()).trim();
				} else {
					result = cell.toString().replace("\n", "").replace("(", "（").replace(")", "）").trim();
				}
			}
		}
		return result;
	}

	/**
	 * @author mengjie 资产清册维护数据导出excel 按搜索条件导出相关数据
	 */
	public void exportExcel() {
		// System.out.println("%%%%"+this.assetInfo.getAssetId()+this.assetInfo.getType1());

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.assetInfo);
		// 遍历json对象
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.assetInfo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		// List<CfCodeInfo> equipmentStatusList =
		// assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		// servletRequest.setAttribute("equipmentStatusList",
		// equipmentStatusList);

		// type类别
		List<CfCodeInfo> typesList = assetInfoService.findCfCodeInfoByTypeId(TYPE_ID);// .findCfInfoCodeById(EQUIPMENT_STATUS);
		Map<String, String> typesMap = new HashMap<String, String>();
		for (CfCodeInfo t : typesList) {
			typesMap.put(t.getId() + "", t.getName());
		}
		// 生产商
		List<Supplier> manufacturerList = supplierService.findSupplierByType(MANUFACTURER);
		Map<String, String> manufacturerMap = new HashMap<String, String>();
		for (Supplier t : manufacturerList) {
			manufacturerMap.put(t.getId() + "", t.getName());
		}
		// 供应商
		List<Supplier> supplierList = supplierService.findSupplierByType(SUPPLIER);
		Map<String, String> supplierMap = new HashMap<String, String>();
		for (Supplier t : supplierList) {
			supplierMap.put(t.getId() + "", t.getName());
		}

		// 获取所有字典项线路对象 list
		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		// 线路对象 KEY:ID VALUE:CODE 2115- 08
		Map<String, String> lineIdCode = new HashMap<String, String>();
		for (CfCodeInfo o : lineList) {
			lineIdCode.put(o.getId() + "", o.getCode());
		}
		// 权属单位，使用单位
		List<CfCodeInfo> UnitList = assetInfoService.findCfInfoCodeById(UNIT);
		Map<String, String> UnitIdMap = new HashMap<String, String>();
		for (CfCodeInfo u : UnitList) {
			UnitIdMap.put(u.getId() + "", u.getName());
		}
		// 维修部门
		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
		Map<String, String> maintainIdMap = new HashMap<String, String>();
		for (CfCodeInfo m : maintainDeptList) {
			maintainIdMap.put(m.getId() + "", m.getName());
		}
		// 车站
		List<CfCodeInfo> stationList = assetInfoService.findCfCodeInfoByTypeId(STATION_TYPE_ID);
		Map<String, String> stationMap = new HashMap<String, String>();
		for (CfCodeInfo m : stationList) {
			stationMap.put(m.getId() + "", m.getCode());
		}
		// 当前使用状态
		List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
		Map<String, String> useStatusMap = new HashMap<String, String>();
		for (CfCodeInfo m : useStatusList) {
			useStatusMap.put(m.getId() + "", m.getName());
		}
		// 当前设备状态
		List<CfCodeInfo> equStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		Map<String, String> equStatusMap = new HashMap<String, String>();
		for (CfCodeInfo m : equStatusList) {
			equStatusMap.put(m.getId() + "", m.getName());
		}
		// 折旧方法,codeInfoId
		List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
		Map<String, String> depreciationMap = new HashMap<String, String>();
		for (CfCodeInfo m : depreciationList) {
			depreciationMap.put(m.getId() + "", m.getName());
		}
		// 铭牌张贴部位
		List<CfCodeInfo> nameplateList = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
		Map<String, String> nameplateMap = new HashMap<String, String>();
		for (CfCodeInfo m : nameplateList) {
			nameplateMap.put(m.getId() + "", m.getName());
		}

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

		String[] headers = { "序号", "资产编号", "资产名称", "大类", "中类", "小类", "单位", "数量", "规格型号", "产地", "生产厂商", "供应商", "出厂日期", "供应日期", "安装地点", "权属单位", "使用单位", "维护部门", "所属线路", "所属车站", "权属责任人", "使用责任人",
				"开始使用时间", "停止使用时间", "报废时间", "移交时间", "当前使用状态", "当前设备状态", "资产图片", "设计使用年限", "预期使用寿命", "保修期至", "大修频次", "出厂价", "合同价", "原值", "技术、规格、操作资料及清单", "折旧方法", "月折旧率%", "本月折旧", "累计折旧", "净值",
				"铭牌张贴部位", "设备清单", "备注", "入册" };
		String[] all_headers = headers;

		String order = null;
		List<AssetInfo> list = assetInfoService.findAssetInfoForExport(filter, order);
		List<Object[]> dataset = new ArrayList<Object[]>();
		List<List<Object[]>> datasetAll = new ArrayList<List<Object[]>>();

		int j = 1;
		for (int i = 0; i < list.size(); i++) {
			Object[] objdata = new Object[46];
			objdata[0] = list.get(i).getId();
			objdata[1] = list.get(i).getAssetId();
			objdata[2] = list.get(i).getAssetName();
			objdata[3] = typesMap.get(list.get(i).getType1());
			objdata[4] = typesMap.get(list.get(i).getType2());
			objdata[5] = typesMap.get(list.get(i).getType3());
			objdata[6] = list.get(i).getDw();
			objdata[7] = list.get(i).getSl();
			objdata[8] = list.get(i).getSpecification();
			// objdata[8] = list.get(i).getModel();
			objdata[9] = list.get(i).getCd();

			objdata[10] = manufacturerMap.get(list.get(i).getManufacturer());
			objdata[11] = supplierMap.get(list.get(i).getVendor());
			objdata[12] = list.get(i).getMakeTime();
			objdata[13] = list.get(i).getProvideTime();
			objdata[14] = list.get(i).getBuildAddr();
			objdata[15] = UnitIdMap.get(list.get(i).getOwnerDuty());
			objdata[16] = UnitIdMap.get(list.get(i).getUserDuty());
			objdata[17] = maintainIdMap.get(list.get(i).getMaintainDep());// 维修部门号
			objdata[18] = lineIdCode.get(list.get(i).getRouteNum());
			objdata[19] = stationMap.get(list.get(i).getStationNum());

			objdata[20] = list.get(i).getOwner();
			objdata[21] = list.get(i).getUsePerson();
			objdata[22] = list.get(i).getUseTime();
			objdata[23] = list.get(i).getStopuseTime();
			objdata[24] = list.get(i).getDiscardTime();
			objdata[25] = list.get(i).getHandoverTime();
			objdata[26] = useStatusMap.get(list.get(i).getUseState());
			objdata[27] = equStatusMap.get(list.get(i).getEquipmentState());
			objdata[28] = list.get(i).getAssetPic();
			objdata[29] = list.get(i).getSjsynx();

			objdata[30] = list.get(i).getUseLife();// 预期使用时间
			objdata[31] = list.get(i).getShelfLife();
			objdata[32] = list.get(i).getDxpl();
			objdata[33] = list.get(i).getLeaveFactoryPrice();
			objdata[34] = list.get(i).getCompactPrice();
			objdata[35] = list.get(i).getYuanzhi();
			objdata[36] = list.get(i).getJszlqd();
			objdata[37] = depreciationMap.get(list.get(i).getDepreciationWay());// 折旧方式
			objdata[38] = list.get(i).getYzjl();
			objdata[39] = list.get(i).getBenqizhijiu();// 本期折旧

			objdata[40] = list.get(i).getLeijizhejiu();
			objdata[41] = list.get(i).getJingzhi();
			objdata[42] = nameplateMap.get(list.get(i).getMpztbw());
			objdata[43] = list.get(i).getShebeiqingdan();
			objdata[45] = list.get(i).getRegistry();
			objdata[44] = list.get(i).getMemo();

			dataset.add(objdata);
			if ((i + 1) == 40000 * j) {
				datasetAll.add(dataset);
				// dataset = new ArrayList<Object[]>();
				// j++;
				break;
			}
		}
		try {
			/*
			 * System.out.println("datasetAll.size()"+datasetAll.size());
			 * //查看当前JVM的内存使用情况
			 * System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024)
			 * + "M");
			 * System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024) +
			 * "M");
			 * System.out.println(Runtime.getRuntime().freeMemory()/(1024*1024)
			 * + "M");
			 */

			for (int m = 0; m < datasetAll.size(); m++) {
				ExportExcel<AssetInfo> ex = new ExportExcel<AssetInfo>();
				this.servletResponse.setContentType("octets/stream");
				this.servletResponse.addHeader("Content-Disposition", "attachment;filename=AssetInfo.xls");
				OutputStream out = servletResponse.getOutputStream();
				ex.exportExcel("资产信息数据库数据导出", all_headers, datasetAll.get(m), out);
				out.flush();
				out.close();
			}
			// 当搜索数据为零时
			if (datasetAll.size() == 0) {
				ExportExcel<AssetInfo> ex = new ExportExcel<AssetInfo>();
				this.servletResponse.setContentType("octets/stream");
				// this.servletResponse.addHeader("Content-Disposition","attachment;filename=("+m+")AssetInfo.xls");
				this.servletResponse.addHeader("Content-Disposition", "attachment;filename=AssetInfo.xls");
				OutputStream out = servletResponse.getOutputStream();
				ex.exportExcel("资产信息数据库数据导出", all_headers, dataset, out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author mengjie 资产动态管理数据导出excel 按搜索条件导出相关数据
	 */
	public void DynamicManagementExportExcel() {
		// System.out.println("%%%%"+this.assetInfo.getAssetId()+this.assetInfo.getType1());

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.assetInfo);
		// 遍历json对象
		Iterator<?> it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(this.assetInfo, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}

		// List<CfCodeInfo> equipmentStatusList =
		// assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		// servletRequest.setAttribute("equipmentStatusList",
		// equipmentStatusList);

		// type类别
		List<CfCodeInfo> typesList = assetInfoService.findCfCodeInfoByTypeId(TYPE_ID);// .findCfInfoCodeById(EQUIPMENT_STATUS);
		Map<String, String> typesMap = new HashMap<String, String>();
		for (CfCodeInfo t : typesList) {
			typesMap.put(t.getId() + "", t.getName());
		}
		// 生产商
		List<Supplier> manufacturerList = supplierService.findSupplierByType(MANUFACTURER);
		Map<String, String> manufacturerMap = new HashMap<String, String>();
		for (Supplier t : manufacturerList) {
			manufacturerMap.put(t.getId() + "", t.getName());
		}
		// 供应商
		List<Supplier> supplierList = supplierService.findSupplierByType(SUPPLIER);
		Map<String, String> supplierMap = new HashMap<String, String>();
		for (Supplier t : supplierList) {
			supplierMap.put(t.getId() + "", t.getName());
		}

		// 获取所有字典项线路对象 list
		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		// 线路对象 KEY:ID VALUE:CODE 2115- 08
		Map<String, String> lineIdCode = new HashMap<String, String>();
		for (CfCodeInfo o : lineList) {
			lineIdCode.put(o.getId() + "", o.getCode());
		}
		// 权属单位，使用单位
		List<CfCodeInfo> UnitList = assetInfoService.findCfInfoCodeById(UNIT);
		Map<String, String> UnitIdMap = new HashMap<String, String>();
		for (CfCodeInfo u : UnitList) {
			UnitIdMap.put(u.getId() + "", u.getName());
		}
		// 维修部门
		List<CfCodeInfo> maintainDeptList = assetInfoService.findCfInfoCodeById(MAINTAIN_DEPT);
		Map<String, String> maintainIdMap = new HashMap<String, String>();
		for (CfCodeInfo m : maintainDeptList) {
			maintainIdMap.put(m.getId() + "", m.getName());
		}
		// 车站
		List<CfCodeInfo> stationList = assetInfoService.findCfCodeInfoByTypeId(STATION_TYPE_ID);
		Map<String, String> stationMap = new HashMap<String, String>();
		for (CfCodeInfo m : stationList) {
			stationMap.put(m.getId() + "", m.getCode());
		}
		// 当前使用状态
		List<CfCodeInfo> useStatusList = assetInfoService.findCfInfoCodeById(USE_STATUS);
		Map<String, String> useStatusMap = new HashMap<String, String>();
		for (CfCodeInfo m : useStatusList) {
			useStatusMap.put(m.getId() + "", m.getName());
		}
		// 当前设备状态
		List<CfCodeInfo> equStatusList = assetInfoService.findCfInfoCodeById(EQUIPMENT_STATUS);
		Map<String, String> equStatusMap = new HashMap<String, String>();
		for (CfCodeInfo m : equStatusList) {
			equStatusMap.put(m.getId() + "", m.getName());
		}
		// 折旧方法,codeInfoId
		List<CfCodeInfo> depreciationList = assetInfoService.findCfInfoCodeById(DEPRECIATION);
		Map<String, String> depreciationMap = new HashMap<String, String>();
		for (CfCodeInfo m : depreciationList) {
			depreciationMap.put(m.getId() + "", m.getName());
		}
		// 铭牌张贴部位
		List<CfCodeInfo> nameplateList = assetInfoService.findCfInfoCodeById(NAMEPLATE_POSITION);
		Map<String, String> nameplateMap = new HashMap<String, String>();
		for (CfCodeInfo m : nameplateList) {
			nameplateMap.put(m.getId() + "", m.getName());
		}

		Properties properties = new Properties();
		String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
		String[] headers = { "序号", "资产编号", "资产名称", "大类", "中类", "小类", "单位", "数量", "规格型号", "产地", "生产厂商", "供应商", "出厂日期", "供应日期", "安装地点", "权属单位", "使用单位", "维护部门", "所属线路", "所属车站", "权属责任人", "使用责任人",
				"开始使用时间", "停止使用时间", "报废时间", "移交时间", "当前使用状态", "当前设备状态", "资产图片", "设计使用年限", "预期使用寿命", "保修期至", "大修频次", "出厂价", "合同价", "原值", "技术、规格、操作资料及清单", "折旧方法", "月折旧率%", "本月折旧", "累计折旧", "净值",
				"铭牌张贴部位", "设备清单", "入册", "备注" };
		String[] all_headers = headers;

		String order = null;
		List<AssetInfo> list = assetInfoService.findDynamicManagementForExport(filter, order);
		List<Object[]> dataset = new ArrayList<Object[]>();
		List<List<Object[]>> datasetAll = new ArrayList<List<Object[]>>();
		int j = 1;
		for (int i = 0; i < list.size(); i++) {

			Object[] objdata = new Object[46];
			objdata[0] = list.get(i).getId();
			objdata[1] = list.get(i).getAssetId();
			objdata[2] = list.get(i).getAssetName();
			objdata[3] = typesMap.get(list.get(i).getType1());
			objdata[4] = typesMap.get(list.get(i).getType2());
			objdata[5] = typesMap.get(list.get(i).getType3());
			objdata[6] = list.get(i).getDw();
			objdata[7] = list.get(i).getSl();
			objdata[8] = list.get(i).getSpecification();
			objdata[9] = list.get(i).getCd();

			objdata[10] = manufacturerMap.get(list.get(i).getManufacturer());
			objdata[11] = supplierMap.get(list.get(i).getVendor());
			objdata[12] = list.get(i).getMakeTime();
			objdata[13] = list.get(i).getProvideTime();
			objdata[14] = list.get(i).getBuildAddr();
			objdata[15] = UnitIdMap.get(list.get(i).getOwnerDuty());
			objdata[16] = UnitIdMap.get(list.get(i).getUserDuty());
			objdata[17] = maintainIdMap.get(list.get(i).getMaintainDep());// 维修部门号
			objdata[18] = lineIdCode.get(list.get(i).getRouteNum());
			objdata[19] = stationMap.get(list.get(i).getStationNum());

			objdata[20] = list.get(i).getOwner();
			objdata[21] = list.get(i).getUsePerson();
			objdata[22] = list.get(i).getUseTime();
			objdata[23] = list.get(i).getStopuseTime();
			objdata[24] = list.get(i).getDiscardTime();
			objdata[25] = list.get(i).getHandoverTime();
			objdata[26] = useStatusMap.get(list.get(i).getUseState());
			objdata[27] = equStatusMap.get(list.get(i).getEquipmentState());
			objdata[28] = list.get(i).getAssetPic();
			objdata[29] = list.get(i).getSjsynx();

			objdata[30] = list.get(i).getUseLife();// 预期使用时间
			objdata[31] = list.get(i).getShelfLife();
			objdata[32] = list.get(i).getDxpl();
			objdata[33] = list.get(i).getLeaveFactoryPrice();
			objdata[34] = list.get(i).getCompactPrice();
			objdata[35] = list.get(i).getYuanzhi();
			objdata[36] = list.get(i).getJszlqd();
			objdata[37] = depreciationMap.get(list.get(i).getDepreciationWay());// 折旧方式
			objdata[38] = list.get(i).getYzjl();
			objdata[39] = list.get(i).getBenqizhijiu();// 本期折旧

			objdata[40] = list.get(i).getLeijizhejiu();
			objdata[41] = list.get(i).getJingzhi();
			objdata[42] = nameplateMap.get(list.get(i).getMpztbw());
			objdata[43] = list.get(i).getShebeiqingdan();
			objdata[45] = list.get(i).getRegistry();
			objdata[44] = list.get(i).getMemo();

			dataset.add(objdata);

			if ((i + 1) == 40000 * j) {
				datasetAll.add(dataset);
				// dataset = new ArrayList<Object[]>();
				// j++;
				break;
			}
		}
		try {
			/*
			 * System.out.println("datasetAll.size()"+datasetAll.size());
			 * //查看当前JVM的内存使用情况
			 * System.out.println(Runtime.getRuntime().totalMemory()/(1024*1024)
			 * + "M");
			 * System.out.println(Runtime.getRuntime().maxMemory()/(1024*1024) +
			 * "M");
			 * System.out.println(Runtime.getRuntime().freeMemory()/(1024*1024)
			 * + "M");
			 */

			for (int m = 0; m < datasetAll.size(); m++) {
				ExportExcel<AssetInfo> ex = new ExportExcel<AssetInfo>();
				this.servletResponse.setContentType("octets/stream");
				// this.servletResponse.addHeader("Content-Disposition","attachment;filename=("+m+")AssetInfo.xls");
				this.servletResponse.addHeader("Content-Disposition", "attachment;filename=AssetInfo.xls");
				OutputStream out = servletResponse.getOutputStream();
				ex.exportExcel("资产信息数据库数据导出", all_headers, datasetAll.get(m), out);
				out.flush();
				out.close();
			}
			// 当搜索数据为零时
			if (datasetAll.size() == 0) {
				ExportExcel<AssetInfo> ex = new ExportExcel<AssetInfo>();
				this.servletResponse.setContentType("octets/stream");
				// this.servletResponse.addHeader("Content-Disposition","attachment;filename=("+m+")AssetInfo.xls");
				this.servletResponse.addHeader("Content-Disposition", "attachment;filename=AssetInfo.xls");
				OutputStream out = servletResponse.getOutputStream();
				ex.exportExcel("资产信息数据库数据导出", all_headers, dataset, out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 填写资产分页查询的过滤条件
	public Map<String, Object> fillFilter(String key, Object value, Map<String, Object> filter) {
		if (value != null && !"".equals(value.toString().trim())) {
			filter.put(key, value);
		}
		return filter;
	}

	// 初始化配置文件
	public void initProperties() {
		String path = Thread.currentThread().getContextClassLoader().getResource("assetInfo.properties").getPath();
		try {
			properties.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	// 读取配置文件
	public Properties loadProperties(String fileName) {
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

	// 将xml格式的string字符串转换成UserDeptInfo
	public List<UserDeptInfo> convertXMLToUserDeptInfo(String xml) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element rootElem = document.getRootElement();

			List<Node> paramsNodeList = rootElem.selectNodes("params");
			List<UserDeptInfo> userDeptInfoList = new ArrayList<UserDeptInfo>();

			if (paramsNodeList != null && paramsNodeList.size() > 0) {
				UserDeptInfo tempUserDeptInfo;
				for (int i = 0; i < paramsNodeList.size(); i++) {
					tempUserDeptInfo = new UserDeptInfo();
					Node currentNode = (Node) paramsNodeList.get(i);

					List<Node> idNodeList = currentNode.selectNodes("//userId");
					List<Node> nameNodeList = currentNode.selectNodes("//userName");
					if (idNodeList != null && idNodeList.size() > 0) {
						tempUserDeptInfo.setUserId(idNodeList.get(0).getText());
						tempUserDeptInfo.setUserName(nameNodeList.get(0).getText());
						userDeptInfoList.add(tempUserDeptInfo);
					}
				}
				return userDeptInfoList;
			} else {
				return null;
			}
		} catch (DocumentException e) {
			System.err.println("转换xml失败");
			return null;
		}
	}

	// 根据userId查询
	public List<UserDeptInfo> getUserDeptInfoListByUserId(String[] userIds) {
		Properties configProperties = loadProperties("config.properties");
		String appName = ServletActionContext.getRequest().getContextPath().substring(1);
		String token = null;
		String method = "getMatchedDeptUsersById";
		String secret = configProperties.getProperty("secret");
		String dataType = "xml";
		Map<String, String> cookieMap = new HashMap<String, String>();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookieMap.put(cookie.getName(), cookie.getValue());
				if ("token".equals(cookie.getName())) {
					try {
						token = java.net.URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		String dataParams = "";
		if (userIds != null && userIds.length > 0) {
			dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams>";
			for (int i = 0; i < userIds.length; i++) {
				dataParams += "<userId>" + userIds[i] + "</userId>";
			}
			dataParams += "</dataParams>";
		}

		String usePersonXMl = getUserInfoThroughCA(appName, token, method, secret, dataType, dataParams);
		return convertXMLToUserDeptInfo(usePersonXMl);
	}

	//查询类型,线路,单位
	public String getAssetInventoryList() {
		List<CfCodeInfo> type1List = assetInfoService.findCfInfoCodeById(TYPE1);
		List<CfCodeInfo> lineList = assetInfoService.findCfInfoCodeById(LINE_ID);
		List<CfCodeInfo> unitList = assetInfoService.findCfInfoCodeById(UNIT);
		servletRequest.setAttribute("type1List", type1List);
		servletRequest.setAttribute("lineList", lineList);
		servletRequest.setAttribute("unitList", unitList);

		List<DwAssetCodeInfo> dw = dwAssetCodeInfoService.findAllDwAsset();
		Map<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < dw.size(); i++) {
			
			map.put(""+dw.get(i).getTypeId()+"/"+dw.get(i).getDm(), String.valueOf(dw.get(i).getValue()));
		}
		servletRequest.setAttribute("map", map);
		return "success";
	}
	
	//查询中类以及中类的value
	public String findTypeAndValueByCodeInfoId() {
		String codeInfoId = servletRequest.getParameter("id");
		List<CfCodeInfo> type2List = assetInfoService.findCfInfoCodeById(Long.valueOf(codeInfoId));
		
		List<DwAssetCodeInfo> dwAssetCodeInfo2 = new ArrayList<DwAssetCodeInfo>();
		for (int i = 0; i < type2List.size(); i++) {
			DwAssetCodeInfo dw = dwAssetCodeInfoService.findDwAssetByTypeIdAndDm(type2List.get(i).getTypeId(), type2List.get(i).getCode());
			dw.setId(String.valueOf(type2List.get(i).getId()));
			dwAssetCodeInfo2.add(dw);
		}
		String jsonData = VOUtils.getJsonDataFromCollection(dwAssetCodeInfo2);
		createJSonData(jsonData);
		return AJAX;
	}

	
	/**
	 * 判断字符串是否符合 yyyy-mm-dd或yyyy/mm/dd
	 * 如果符合返回yyyy-mm-dd格式的字符串，否则返回null
	 * @throws ParseException 
	 */
	private String isDateFormatted(String date){
		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat s2 = new SimpleDateFormat("yyyy/mm/dd");
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Pattern pattern2 = Pattern.compile("\\d{4}/\\d{2}/\\d{2}");
		Matcher matcher1 = pattern.matcher(date);
		if(matcher1.find()){
			return date;
		}else{
			Matcher matcher2 = pattern2.matcher(date);
			if(matcher2.find()){
				try {
					return s1.format(s2.parse(date));
				} catch (ParseException e) {
					return null;
				}
			}else{
				return null;
			}
		}
	}
	
	
	
	
	
}
