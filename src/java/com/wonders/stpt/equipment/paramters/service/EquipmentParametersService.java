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

package com.wonders.stpt.equipment.paramters.service;

import java.util.List;

import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;


/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public interface EquipmentParametersService {
	
	/**
	 * 保存设备参数
	 * @param parameterList 设备参数
	 */
	public void saveEquipmentParameters(List<EquipmentParameters> parameterList);
	
	/**
	 * 查询设备参数
	 * @param id 设备id
	 * @return 设备参数
	 */
	public List<EquipmentParameters> findParametersByEquipmentId(String equipmentId);
	
	/**
	 * 更新设备参数值
	 * @param id
	 * @param paramValue
	 */
	public void updateParameter(String id,String paramValue);
	
	/**
	 * 查询设备参数的总数
	 * @param equipmentId 设备id
	 */
	public long findParameterAmountByEquipmentId(String equipmentId);
}
