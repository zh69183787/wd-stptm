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

package com.wonders.stpt.equipment.service.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.equipment.service.dao.EquipmentServiceInfoDao;
import com.wonders.stpt.equipment.service.entity.bo.EquipmentServiceInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * EquipmentServiceInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-25
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentServiceInfoDaoImpl extends AbstractHibernateDaoImpl<EquipmentServiceInfo> implements EquipmentServiceInfoDao {
	public Page findEquipmentServiceInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from EquipmentServiceInfo t ";
		String countHql = "select count(*) from EquipmentServiceInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("implementUnit")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("serviceDate")){
					filterPart += "t." + paramName + " >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
			filterPart += " and t.removed='0'";
		}else{
			filterPart += " where t.removed='0'";
		}
		filterPart += " order by t.operationDate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
	}

	@Override
	public EquipmentServiceInfo findServiceInfoById(String id) {
		String hql = "from EquipmentServiceInfo t where t.id='"+id+"' and removed='0'";
		Query query = getSession().createQuery(hql);
		return (EquipmentServiceInfo) query.uniqueResult();
	}

	@Override
	public void saveServiceInfo(EquipmentServiceInfo equipmentServiceInfo) {
		getHibernateTemplate().save(equipmentServiceInfo);
	}

	@Override
	public void updateServiceInfo(EquipmentServiceInfo equipmentServiceInfo) {
		getHibernateTemplate().update(equipmentServiceInfo);
	}

	@Override
	public void deleteById(String id) {
		String hql ="delete from  EquipmentServiceInfo t where t.id='"+id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	
}
