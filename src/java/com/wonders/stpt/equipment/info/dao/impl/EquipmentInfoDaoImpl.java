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

package com.wonders.stpt.equipment.info.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.equipment.info.dao.EquipmentInfoDao;
import com.wonders.stpt.equipment.info.entity.bo.EquipmentInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * EquipmentInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentInfoDaoImpl extends AbstractHibernateDaoImpl<EquipmentInfo> implements EquipmentInfoDao {
	public Page findEquipmentInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from EquipmentInfo t ";
		String countHql = "select count(*) from EquipmentInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("assetId") || paramName.equals("equipmentId") || paramName.equals("equipmentName") || 
						paramName.equals("manufacturer") || paramName.equals("provenance") || paramName.equals("productModel")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		
		filterPart += " order by t.operateTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
	}

	@Override
	public void saveEquipmentInfo(EquipmentInfo equipmentInfo) {
		getHibernateTemplate().save(equipmentInfo);
	}

	@Override
	public EquipmentInfo findEquipmentById(String id) {
		return (EquipmentInfo) getHibernateTemplate().get(EquipmentInfo.class, id);
	}

	@Override
	public void updateEquipment(EquipmentInfo equipmentInfo) {
		getHibernateTemplate().update(equipmentInfo);
	}

	@Override
	public List<EquipmentInfo> findAllEquipmentInfoByAssetId(String assetId) {
		String hql ="from EquipmentInfo t where t.assetId='"+assetId+"'";
		return getHibernateTemplate().find(hql);
	}
}
