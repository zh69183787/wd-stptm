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

package com.wonders.stpt.equipment.replace.service.impl;

import java.util.Map;

import com.wonders.stpt.equipment.replace.dao.EquipmentPartsReplaceDao;
import com.wonders.stpt.equipment.replace.entity.bo.EquipmentPartsReplace;
import com.wonders.stpt.equipment.replace.service.EquipmentPartsReplaceService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-21
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentPartsReplaceServiceImpl implements EquipmentPartsReplaceService {
	private EquipmentPartsReplaceDao equipmentPartsReplaceDao;

	public void setEquipmentPartsReplaceDao(EquipmentPartsReplaceDao equipmentPartsReplaceDao) {
		this.equipmentPartsReplaceDao = equipmentPartsReplaceDao;
	}

	public Page findEquipmentPartsReplaceByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		return equipmentPartsReplaceDao.findEquipmentPartsReplaceByPage(filter,pageNo, pageSize);
	}

	@Override
	public EquipmentPartsReplace findPartsReplaceById(String id) {
		return equipmentPartsReplaceDao.findPartsReplaceById(id);
	}

	@Override
	public void savePartsReplace(EquipmentPartsReplace equipmentPartsReplace) {
		equipmentPartsReplaceDao.savePartsReplace(equipmentPartsReplace);
	}

	@Override
	public void updatePartsReplace(EquipmentPartsReplace equipmentPartsReplace) {
		equipmentPartsReplaceDao.updatePartsReplace(equipmentPartsReplace);
	}

	@Override
	public void deletePartsReplaceById(String id) {
		equipmentPartsReplaceDao.deletePartsReplaceById(id);
	}
	
	
}
