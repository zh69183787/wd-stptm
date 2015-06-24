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

package com.wonders.stpt.equipment.service.service.impl;

import java.util.Map;

import com.wonders.stpt.equipment.service.dao.EquipmentServiceInfoDao;
import com.wonders.stpt.equipment.service.entity.bo.EquipmentServiceInfo;
import com.wonders.stpt.equipment.service.service.EquipmentServiceInfoService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-25
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentServiceInfoServiceImpl implements EquipmentServiceInfoService {
	private EquipmentServiceInfoDao equipmentServiceInfoDao;

	public void setEquipmentServiceInfoDao(
			EquipmentServiceInfoDao equipmentServiceInfoDao) {
		this.equipmentServiceInfoDao = equipmentServiceInfoDao;
	}

	public Page findEquipmentServiceInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return equipmentServiceInfoDao.findEquipmentServiceInfoByPage(filter, pageNo, pageSize);
	}

	@Override
	public EquipmentServiceInfo findServiceInfoById(String id) {
		return equipmentServiceInfoDao.findServiceInfoById(id);
	}

	@Override
	public void saveServiceInfo(EquipmentServiceInfo equipmentServiceInfo) {
		equipmentServiceInfoDao.saveServiceInfo(equipmentServiceInfo);
	}

	@Override
	public void updateServiceInfo(EquipmentServiceInfo equipmentServiceInfo) {
		equipmentServiceInfoDao.updateServiceInfo(equipmentServiceInfo);
	}

	@Override
	public void deleteById(String id) {
		equipmentServiceInfoDao.deleteById(id);
	}
}
