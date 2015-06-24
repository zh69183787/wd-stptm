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

package com.wonders.stpt.equipment.replace.dao;

import java.util.Map;

import com.wonders.stpt.equipment.replace.entity.bo.EquipmentPartsReplace;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface EquipmentPartsReplaceDao extends AbstractHibernateDao<EquipmentPartsReplace> {
	
	public Page findEquipmentPartsReplaceByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 设备更换
	 */
	public EquipmentPartsReplace findPartsReplaceById(String id);
	
	/**
	 * 保存
	 * @param equipmentPartsReplace 设备零件更换
	 */
	public void savePartsReplace(EquipmentPartsReplace equipmentPartsReplace);
	
	/**
	 * 更新
	 * @param equipmentPartsReplace 设备零件更换
	 */
	public void updatePartsReplace(EquipmentPartsReplace equipmentPartsReplace);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deletePartsReplaceById(String id);
}
