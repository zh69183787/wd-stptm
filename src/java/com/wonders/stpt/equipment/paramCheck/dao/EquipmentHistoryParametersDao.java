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

package com.wonders.stpt.equipment.paramCheck.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.equipment.paramCheck.entity.bo.EquipmentHistoryParameters;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public interface EquipmentHistoryParametersDao extends AbstractHibernateDao<EquipmentHistoryParameters> {
	public Page findEquipmentHistoryParametersByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	
	/**
	 * 保存
	 * @param equipmentHistoryParameterList 设备参数检查
	 */
	public void saveHistoryParameters(List<EquipmentHistoryParameters> equipmentHistoryParameterList);
	
	/**
	 * 查询参数的分组id,数据库中建立的SEQ_EQUIPMENT_PARAM_CHECK
	 * @return
	 */
	public String findGroupId();
	
	/**
	 * 查询设备历史参数检查
	 * @param equipmentId	设备id
	 * @return	历史参数检查
	 */
	public List<EquipmentHistoryParameters> findHistoryParametersByEquipmentId(String equipmentId);
}
