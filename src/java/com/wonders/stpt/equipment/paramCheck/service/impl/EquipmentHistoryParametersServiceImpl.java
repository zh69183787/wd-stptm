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

package com.wonders.stpt.equipment.paramCheck.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.equipment.paramCheck.dao.EquipmentHistoryParametersDao;
import com.wonders.stpt.equipment.paramCheck.entity.bo.EquipmentHistoryParameters;
import com.wonders.stpt.equipment.paramCheck.service.EquipmentHistoryParametersService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentHistoryParametersServiceImpl implements EquipmentHistoryParametersService {
	private EquipmentHistoryParametersDao equipmentHistoryParametersDao;

	public void setEquipmentHistoryParametersDao(
			EquipmentHistoryParametersDao equipmentHistoryParametersDao) {
		this.equipmentHistoryParametersDao = equipmentHistoryParametersDao;
	}

	public Page findEquipmentHistoryParametersByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return equipmentHistoryParametersDao.findEquipmentHistoryParametersByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveHistoryParameters(List<EquipmentHistoryParameters> equipmentHistoryParameterList) {
		equipmentHistoryParametersDao.saveHistoryParameters(equipmentHistoryParameterList);
	}

	@Override
	public String findGroupId() {
		return equipmentHistoryParametersDao.findGroupId();
	}

	@Override
	public List<EquipmentHistoryParameters> findHistoryParametersByEquipmentId(String equipmentId) {
		return equipmentHistoryParametersDao.findHistoryParametersByEquipmentId(equipmentId);
	}
}
