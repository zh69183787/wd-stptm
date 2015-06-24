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

package com.wonders.stpt.equipment.paramCheck.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.equipment.paramCheck.dao.EquipmentHistoryParametersDao;
import com.wonders.stpt.equipment.paramCheck.entity.bo.EquipmentHistoryParameters;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * EquipmentHistoryParametersʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentHistoryParametersDaoImpl extends AbstractHibernateDaoImpl<EquipmentHistoryParameters> implements EquipmentHistoryParametersDao {
	public Page findEquipmentHistoryParametersByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from EquipmentHistoryParameters t ";
		String countHql = "select count(*) from EquipmentHistoryParameters t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				filterPart += "t." + paramName + "=:" + paramName;
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public void saveHistoryParameters(List<EquipmentHistoryParameters> equipmentHistoryParameterList) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if(equipmentHistoryParameterList!=null && equipmentHistoryParameterList.size()>0){
			for(EquipmentHistoryParameters parameters : equipmentHistoryParameterList){
				getHibernateTemplate().save(parameters);
			}
		}
		tx.commit();
		session.close();
	}
	
	@Override
	public String findGroupId() {
		String sql ="select SEQ_EQUIPMENT_PARAM_CHECK.NEXTVAL from dual";
		return String.valueOf(getSession().createSQLQuery(sql).uniqueResult());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentHistoryParameters> findHistoryParametersByEquipmentId(String equipmentId) {
		String hql ="from EquipmentHistoryParameters t where t.equipmentId='"+equipmentId+"' order by t.groupId ASC,t.paramterId ASC";
		return getHibernateTemplate().find(hql);
	} 
	
}
