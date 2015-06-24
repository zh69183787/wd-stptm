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

package com.wonders.stpt.equipment.info.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.equipment.info.dao.EquipmentInfoDao;
import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wonders.stpt.equipment.info.service.EquipmentInfoService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentInfoServiceImpl implements EquipmentInfoService {
	private EquipmentInfoDao equipmentInfoDao;

	public void setEquipmentInfoDao(EquipmentInfoDao equipmentInfoDao) {
		this.equipmentInfoDao = equipmentInfoDao;
	}

	public Page findEquipmentInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return equipmentInfoDao.findEquipmentInfoByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveEquipmentInfo(EquipmentInfo equipmentInfo) {
		equipmentInfoDao.saveEquipmentInfo(equipmentInfo);
	}

	@Override
	public EquipmentInfo findEquipmentById(String id) {
		return equipmentInfoDao.findEquipmentById(id);
	}

	@Override
	public void updateEquipment(EquipmentInfo equipmentInfo) {
		equipmentInfoDao.updateEquipment(equipmentInfo);
	}

	@Override
	public void deleteById(String id) {
		equipmentInfoDao.deleteById(id);
	}

	@Override
	public List<EquipmentInfo> findAllEquipmentInfoByAssetId(String assetId) {
		return equipmentInfoDao.findAllEquipmentInfoByAssetId(assetId);
	}

	@Override
	public void delete(EquipmentInfo equipmentInfo) {
		equipmentInfoDao.delete(equipmentInfo);
	}
}
