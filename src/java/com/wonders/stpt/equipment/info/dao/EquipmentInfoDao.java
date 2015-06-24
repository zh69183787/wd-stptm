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

package com.wonders.stpt.equipment.info.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface EquipmentInfoDao extends AbstractHibernateDao<EquipmentInfo> {
	/**
	 * 分页查询
	 * @param filter 过滤条件
	 * @param pageNo	当前页码
	 * @param pageSize 每页显示的数量
	 * @return	分页后的数据
	 */
	public Page findEquipmentInfoByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	/**
	 * 保存设备信息
	 * @param equipmentInfo 设备信息
	 */
	public void saveEquipmentInfo(EquipmentInfo equipmentInfo);
	
	/**
	 *	查询
	 * @param id 主键
	 */
	public EquipmentInfo findEquipmentById(String id);
	
	/**
	 * 更新
	 * @param equipmentInfo 设备信息
	 */
	public void updateEquipment(EquipmentInfo equipmentInfo);
	
	/**
	 * 查询所有相关资产的设备
	 * @param assetId	资产id
	 * @return	设备
	 */
	public List<EquipmentInfo> findAllEquipmentInfoByAssetId(String assetId);
}
