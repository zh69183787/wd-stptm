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

package com.wonders.stpt.supplier.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.supplier.dao.SupplierDao;
import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Supplierʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

public class SupplierDaoImpl extends AbstractHibernateDaoImpl<Supplier> implements SupplierDao {
	public Page findSupplierByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Supplier t ";
		String countHql = "select count(*) from Supplier t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("name") || paramName.equals("legalPerson") || paramName.equals("address")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
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
		filterPart += " order by t.id DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

	@Override
	public void saveSupplier(Supplier supplier) {
		getHibernateTemplate().save(supplier);
	}

	@Override
	public Supplier findSupplierById(long id) {
		String hql = "from Supplier t where t.id=" + id + " and t.removed='0' ";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Supplier supplier = (Supplier) session.createQuery(hql).uniqueResult();
		tx.commit();
		session.close();
		return supplier;
	}
	
	/**
	 * 保存修改
	 * @param supplier 供应商
	 */
	@Override
	public void update(Supplier supplier){
		getHibernateTemplate().update(supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findSupplierByType(String type) {
		String hql ="from Supplier t where t.type='"+type+"' and removed='0' order by t.id DESC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Supplier> findIdByTypeAndName(String type, String name) {
		String hql ="from Supplier t where t.type='"+type+"' and t.name='"+name+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return query.setMaxResults(1).list();
	}

	@Override
	public List<Supplier> findAllSupplier() {
		String hql ="from Supplier t where removed='0' order by t.id DESC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Supplier> findSupplierByNameAndType(String name, String type) {
		String hql ="from Supplier t where t.name like '%"+name+"%' and t.type='"+type+"' and t.removed='0' order by nlssort(t.name,'NLS_SORT=SCHINESE_PINYIN_M')";
		
		return getHibernateTemplate().find(hql);
	}
}
