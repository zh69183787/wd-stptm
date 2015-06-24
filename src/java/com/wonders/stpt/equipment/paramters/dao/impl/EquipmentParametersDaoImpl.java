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

package com.wonders.stpt.equipment.paramters.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.equipment.paramters.dao.EquipmentParametersDao;
import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * EquipmentParametersʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentParametersDaoImpl extends AbstractHibernateDaoImpl<EquipmentParameters> implements EquipmentParametersDao {

	@Override
	public void saveEquipmentParameters(List<EquipmentParameters> parameterList) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if(parameterList!=null && parameterList.size()>0){
			for(EquipmentParameters parameters : parameterList){
				session.save(parameters);
			}
		}
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentParameters> findParametersByEquipmentId(String equipmentId) {
		String hql ="from EquipmentParameters t where t.equipmentId='"+equipmentId+"' and removed='0' order by t.operationDate DESC,t,id ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void updateParameter(String id, String paramValue) {
		String hql ="from EquipmentParameters t where t.id='"+id+"' and removed='0'";
		EquipmentParameters parameters = (EquipmentParameters) getHibernateTemplate().find(hql);
		if(parameters!=null){
			parameters.setParameterValue(paramValue);
		}
		getHibernateTemplate().update(parameters);
	}

	@Override
	public long findParameterAmountByEquipmentId(String equipmentId) {
		String hql ="select count(*) from EquipmentParameters t where t.equipmentId='"+equipmentId+"' and removed='0'";
		
		return (Long)getSession().createQuery(hql).uniqueResult();
	}
	
}
