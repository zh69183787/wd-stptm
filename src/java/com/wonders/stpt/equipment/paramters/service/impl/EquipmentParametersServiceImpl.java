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

package com.wonders.stpt.equipment.paramters.service.impl;

import java.util.List;

import com.wonders.stpt.equipment.paramters.dao.EquipmentParametersDao;
import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;
import com.wonders.stpt.equipment.paramters.service.EquipmentParametersService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentParametersServiceImpl implements EquipmentParametersService {
	private EquipmentParametersDao equipmentParametersDao;

	public void setEquipmentParametersDao(EquipmentParametersDao equipmentParametersDao) {
		this.equipmentParametersDao = equipmentParametersDao;
	}

	@Override
	public void saveEquipmentParameters(List<EquipmentParameters> parameterList) {
		equipmentParametersDao.saveEquipmentParameters(parameterList);
	}

	@Override
	public List<EquipmentParameters> findParametersByEquipmentId(String equipmentId) {
		return equipmentParametersDao.findParametersByEquipmentId(equipmentId);
	}

	@Override
	public void updateParameter(String id, String paramValue) {
		
	}

	@Override
	public long findParameterAmountByEquipmentId(String equipmentId) {
		return equipmentParametersDao.findParameterAmountByEquipmentId(equipmentId);
	}

	
}
