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

package com.wonders.stpt.task.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.dao.CfCodeInfoDao;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.task.dao.AssetTaskCheckDao;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wonders.stpt.task.service.AssetTaskCheckService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-7-4
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetTaskCheckServiceImpl implements AssetTaskCheckService {
	private AssetTaskCheckDao assetTaskCheckDao;
	private CfCodeInfoDao cfCodeInfoDao; 

	public void setCfCodeInfoDao(CfCodeInfoDao cfCodeInfoDao) {
		this.cfCodeInfoDao = cfCodeInfoDao;
	}

	public void setAssetTaskCheckDao(AssetTaskCheckDao assetTaskCheckDao) {
		this.assetTaskCheckDao = assetTaskCheckDao;
	}

	public Page findAssetTaskCheckByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		return assetTaskCheckDao.findAssetTaskCheckByPage(filter, pageNo,pageSize);
	}

	@Override
	public int findSumOfTaskCheckByTaskId(String id) {
		return assetTaskCheckDao.findSumOfTaskCheckByTaskId(id);
	}

	@Override
	public void saveTaskCheckList(List<AssetTaskCheck> list) {
		assetTaskCheckDao.saveTaskCheckList(list);
	}

	@Override
	public List<AssetTaskCheck> findAllByTaskIdList(String taskId) {
		return assetTaskCheckDao.findAllByTaskId(taskId);
	}
	
	@Override
	public CfCodeInfo findCfCodeInfoById(long id) {
		return cfCodeInfoDao.findCfCodeInfoById(id);
	}

	@Override
	public AssetTaskCheck findTaskByAssetInfoIdAndTaskId(String assetInfoId, String taskId) {
		return assetTaskCheckDao.findTaskByAssetInfoIdAndTaskId(assetInfoId, taskId);
	}

	@Override
	public CfCodeInfo findCfCodeInfoByTypeIdAndCode(String typeId, String code) {
		return cfCodeInfoDao.findByTypeIdAndCode(typeId, code);
	}

	@Override
	public List<AssetTaskCheck> findByAssetInfoId(String assetInfoId) {
		return assetTaskCheckDao.findByAssetInfoId(assetInfoId);
	}
	
	public List<AssetTaskCheck> findByTaskId(String taskId){
		return assetTaskCheckDao.findByTaskId(taskId);
	}

	@Override
	public boolean checkAssetTaskCheck(AssetTaskCheck assetTaskCheck) {
		// TODO Auto-generated method stub
		return assetTaskCheckDao.checkAssetTaskCheck(assetTaskCheck);
	}
	
	@Override
	public int findSumOfTaskByTaskId(String id){
		return assetTaskCheckDao.findSumOfTaskByTaskId(id);
	}
	
}
