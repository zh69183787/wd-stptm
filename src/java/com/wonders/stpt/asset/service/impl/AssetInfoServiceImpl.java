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

package com.wonders.stpt.asset.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.dao.AssetInfoDao;
import com.wonders.stpt.asset.dao.AssetInfoHistoryDao;
import com.wonders.stpt.asset.dao.CfCodeInfoDao;
import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetInfoServiceImpl implements AssetInfoService {
	private AssetInfoDao assetInfoDao;
	private AssetInfoHistoryDao assetInfoHistoryDao;
	private CfCodeInfoDao cfCodeInfoDao; 

	public void setCfCodeInfoDao(CfCodeInfoDao cfCodeInfoDao) {
		this.cfCodeInfoDao = cfCodeInfoDao;
	}

	public void setAssetInfoHistoryDao(AssetInfoHistoryDao assetInfoHistoryDao) {
		this.assetInfoHistoryDao = assetInfoHistoryDao;
	}

	public void setAssetInfoDao(AssetInfoDao assetInfoDao) {
		this.assetInfoDao = assetInfoDao;
	}

	@Override
	public Page findAssetInfoByPage(Map<String, Object> filter, int pageNo,int pageSize) {
		return assetInfoDao.findAssetInfoByPage(filter, pageNo, pageSize);
	}
	
	@Override
	public List<AssetInfo> findAllAssetInfoByFilter(Map<String, Object> filter) {
		
		return assetInfoDao.findAllAssetInfoByFilter(filter);
	}

	@Override
	public void saveAssertInfo(AssetInfo assetInfo) {
		assetInfoDao.saveAssetInfo(assetInfo);
	}

	@Override
	public AssetInfo findById(String id) {
		return assetInfoDao.findById(id);
	}

	@Override
	public void saveAssetInfoHistory(AssetInfoHistory assetInfoHistory) {
		assetInfoHistoryDao.saveAssetInfoHistory(assetInfoHistory);
	}

	@Override
	public void updateAssetInfo(AssetInfo assetInfo) {
		assetInfoDao.updateAssetInfo(assetInfo);
	}

	@Override
	public void deleteAssetInfo(AssetInfo assetInfo) {
		assetInfoDao.deleteAssetInfo(assetInfo);
	}

	@Override
	public List<CfCodeInfo> findCfInfoCodeById(long id) {
		return cfCodeInfoDao.findCfCodeInfo(id);
	}

	@Override
	public void updateAssryInfoHistory(AssetInfoHistory history) {
		assetInfoHistoryDao.updateAssetInfoHistory(history);
	}

	@Override
	public CfCodeInfo findCfCodeInfoById(long id) {
		return cfCodeInfoDao.findCfCodeInfoById(id);
	}

	@Override
	public void deleteAssetInfoHistoryByAssetInfoId(long id) {
		assetInfoHistoryDao.deleteAssetInfoHistoryByAssetInfoId(id);
	}

	@Override
	public List<CfCodeInfo> findCfCodeInfoListTypeById(long id) {
		
		return cfCodeInfoDao.findCfCodeInfoListTypeById(id);
	}

	@Override
	public AssetInfo findByAssetId(String id) {
		return assetInfoDao.findByAssetId(id);
	}

	@Override
	public AssetInfo findByXH(String xh) {
		return assetInfoDao.findByXH(xh);
	}

	@Override
	public List<AssetInfoHistory> findAllAssetHistoryByAssetInfoId(String id) {
		return assetInfoHistoryDao.findAllAssetHistoryByAssetInfoId(id);
	}

	@Override
	public String findNextXHByRouteNum(String routeNum) {
		return assetInfoDao.findNextXHByRouteNum(routeNum);
	}

	@Override
	public AssetInfoHistory findAssetInfoHistoryById(String id) {
		
		return assetInfoHistoryDao.findAssetInfoHistoryById(id);
	}

	@Override
	public String findCfCodeInfoIdByCodeInfoIdAndName(long typeId,String name) {
		if(name==null)
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByCodeInfoIdAndName(typeId, name);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	@Override
	public String findCfCodeInfoIdByCodeAndName(String code, String name) {
		if(code==null || name==null || "".equals(code) || "".equals(name))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByCodeAndName(code, name);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}
	
	@Override
	public String findCfCodeInfoIdByCodeInfoIdAndCode(long codeInfoId,String code) {
		if(code==null || "".equals(code))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByCodeInfoIdAndCode(codeInfoId, code);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}
	
	@Override
	public String findCfCodeInfoIdByTypeIdAndName(long typeId, String name) {
		if(name==null || "".equals(name))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByTypeIdAndName(typeId, name);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	@Override
	public AssetInfo findByRouteNumAndAssetId(String routeNum, String assetId) {
		if(routeNum==null || assetId==null || "".equals(routeNum) || "".equals(assetId))
			return null;
		return assetInfoDao.findByRouteNumAndAssetId(routeNum, assetId);
	}

	@Override
	public String findCfCodeInfoIdByTypeIdAndCode(long typeId, String code) {
		if(code==null || "".equals(code))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByTypeIdAndCode(typeId, code);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	@Override
	public String findCodeById(String id) {
		if(id==null || "".equals(id))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCodeById(id);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	@Override
	public String findCfCodeInfoIdByTypeIdAndNameAndCode(long typeId,String name, String code) {
		if(name==null || "".equals(name))
			return null;
		List<Object> resultList = cfCodeInfoDao.findCfCodeInfoIdByTypeIdAndNameAndCode(typeId, name,code);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	@Override
	public void saveAssetInfoList(List<AssetInfo> assetInfoList) {
		assetInfoDao.saveAssetInfoList(assetInfoList);
	}

	@Override
	public void saveAssetInfoHistoryList(List<AssetInfoHistory> assetInfoHistoryList) {
		assetInfoHistoryDao.saveAssetInfoHistoryList(assetInfoHistoryList);
	}

	@Override
	public List<CfCodeInfo> findAllCfCodeInfo() {
		return cfCodeInfoDao.findAllCfCodeInfo();
	}


	@Override
	public void inventory(String[] id) {
		assetInfoDao.inventory(id);
	}
	@Override
	public List<CfCodeInfo> findCfCodeInfoByTypeId(long id) {
		// TODO Auto-generated method stub
		return cfCodeInfoDao.findCfCodeInfoIdByTypeId(id);
	}
	@Override
	public List<AssetInfoHistory> findHistoryByAssetId(String assetId) {
		return assetInfoHistoryDao.findHistoryByAssetId(assetId);
	}

	@Override
	public AssetInfo findAssetInfoByAssetId(String assetId) {
		List<AssetInfo> list = assetInfoDao.findAssetInfoByAssetId(assetId);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	public List<AssetInfo> findAssetInfoForExport (Map<String, Object> filter,String order)
	{
		return assetInfoDao.findAssetInfoForExport(filter, order);		
	}
	@Override
	public List<AssetInfo> findDynamicManagementForExport(
			Map<String, Object> filter, String order) {
		// TODO Auto-generated method stub
		return assetInfoDao.findDynamicManagementForExport(filter, order);
	}

	@Override
	public String findByTypeIdAndNameAndCodeInfoId(long typeId, String name,String code_info_id) {
		if(name==null || "".equals(name))
			return null;
		List<Object> resultList = cfCodeInfoDao.findByTypeIdAndNameAndCodeInfoId(typeId, name,code_info_id);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0)).trim();
	}

	public List<CfCodeInfo> findCfInfoCodeForStationByTypeId(long typeId,String code_info_id){
		return cfCodeInfoDao.findCfInfoCodeForStationByTypeId(typeId,code_info_id);
	}
	
	public List<CfCodeInfo> findCfInfoCodeForAllTypeByLength(long typeId,int length){
		return cfCodeInfoDao.findCfInfoCodeForAllTypeByLength(typeId,length);
	}
	
	public List<AssetInfo> findAllAsset(){
		return assetInfoDao.findAllAsset();
	}

	
}
