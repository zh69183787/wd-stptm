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

import com.wonders.stpt.task.dao.AssetTaskDao;
import com.wonders.stpt.task.entity.bo.AssetTask;
import com.wonders.stpt.task.service.AssetTaskService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetTaskServiceImpl implements AssetTaskService {
	private AssetTaskDao assetTaskDao;

	public void setAssetTaskDao(AssetTaskDao assetTaskDao) {
		this.assetTaskDao = assetTaskDao;
	}

	public Page findAssetTaskByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return assetTaskDao.findAssetTaskByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveAdd(AssetTask assetTask) {
		assetTaskDao.saveAdd(assetTask);
	}

	@Override
	public AssetTask findTaskById(String id) {
		return assetTaskDao.findTaskById(id);
	}

	@Override
	public void updateTask(AssetTask assetTask) {
		assetTaskDao.updateTask(assetTask);
	}

	@Override
	public void deleteTaskById(String id) {
		assetTaskDao.deleteTaskById(id);
	}
	
	public List<AssetTask> findAllTask(){
		return assetTaskDao.findAllTask();
	}
	
	
}
