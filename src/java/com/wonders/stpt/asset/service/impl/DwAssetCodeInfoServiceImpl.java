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

import com.wonders.stpt.asset.dao.DwAssetCodeInfoDao;
import com.wonders.stpt.asset.entity.bo.DwAssetCodeInfo;
import com.wonders.stpt.asset.service.DwAssetCodeInfoService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author Arthur
 * @version $Revision$
 * @date 2013-1-6
 * @author modify by $Author$
 * @since 1.0
 */

public class DwAssetCodeInfoServiceImpl implements DwAssetCodeInfoService {
	private DwAssetCodeInfoDao dwAssetCodeInfoDao;

	public void setDwAssetCodeInfoDao(DwAssetCodeInfoDao dwAssetCodeInfoDao) {
		this.dwAssetCodeInfoDao = dwAssetCodeInfoDao;
	}

	public void addDwAssetCodeInfo(DwAssetCodeInfo dwAssetCodeInfo) {
		dwAssetCodeInfoDao.save(dwAssetCodeInfo);
	}

	public void deleteDwAssetCodeInfo(DwAssetCodeInfo dwAssetCodeInfo) {
		dwAssetCodeInfoDao.delete(dwAssetCodeInfo);
	}

	public DwAssetCodeInfo findDwAssetCodeInfoById(Long id) {
		return dwAssetCodeInfoDao.load(id);
	}

	public void updateDwAssetCodeInfo(DwAssetCodeInfo dwAssetCodeInfo) {
		dwAssetCodeInfoDao.update(dwAssetCodeInfo);
	}

	public Page findDwAssetCodeInfoByPage(int pageNo, int pageSize) {
		Page page = dwAssetCodeInfoDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findDwAssetCodeInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return dwAssetCodeInfoDao.findDwAssetCodeInfoByPage(filter, pageNo, pageSize);
	}

	@Override
	public List<DwAssetCodeInfo> findAllDwAsset() {
		return dwAssetCodeInfoDao.findAllDwAsset();
	}

	@Override
	public DwAssetCodeInfo findDwAssetByTypeIdAndDm(long typeId, String dm) {
		return dwAssetCodeInfoDao.findDwAssetByTypeIdAndDm(typeId, dm);
	}

}
