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

package com.wonders.stpt.equipment.service.dao;

import java.util.Map;

import com.wonders.stpt.equipment.service.entity.bo.EquipmentServiceInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-25
 * @author modify by $Author$
 * @since 1.0
 */

public interface EquipmentServiceInfoDao extends AbstractHibernateDao<EquipmentServiceInfo> {
	public Page findEquipmentServiceInfoByPage(Map<String, Object> filter, int pageNo, int pageSize);
	

	/**
	 * 查询设备检修表
	 * @param id	主键
	 * @return	设备检修
	 */
	public EquipmentServiceInfo findServiceInfoById(String id);
	
	/**
	 * 保存设备检修表
	 * @param equipmentServiceInfo 设备检修表
	 */
	public void saveServiceInfo(EquipmentServiceInfo equipmentServiceInfo);
	
	/**
	 * 更新设备检修
	 * @param equipmentServiceInfo	设备检修表
	 */
	public void updateServiceInfo(EquipmentServiceInfo equipmentServiceInfo);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
}
