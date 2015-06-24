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

package com.wonders.stpt.equipment.replace.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.equipment.replace.dao.EquipmentPartsReplaceDao;
import com.wonders.stpt.equipment.replace.entity.bo.EquipmentPartsReplace;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-21
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentPartsReplaceDaoImpl extends AbstractHibernateDaoImpl<EquipmentPartsReplace> implements EquipmentPartsReplaceDao {
	public Page findEquipmentPartsReplaceByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from EquipmentPartsReplace t ";
		String countHql = "select count(*) from EquipmentPartsReplace t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("partsName") || paramName.equals("manufacturer")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("replaceDate")){
					filterPart += "t." + paramName + " >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.replaceDate DESC,t.partsName ASC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
	}

	@Override
	public EquipmentPartsReplace findPartsReplaceById(String id) {
		String hql = "from EquipmentPartsReplace t where t.id='"+id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return (EquipmentPartsReplace) query.uniqueResult();
	}

	@Override
	public void savePartsReplace(EquipmentPartsReplace equipmentPartsReplace) {
		getHibernateTemplate().save(equipmentPartsReplace);
	}

	@Override
	public void updatePartsReplace(EquipmentPartsReplace equipmentPartsReplace) {
		getHibernateTemplate().update(equipmentPartsReplace);
	}

	@Override
	public void deletePartsReplaceById(String id) {
		String hql = "delete from EquipmentPartsReplace t where t.id='"+id+"' and t.removed='0'";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}
}
