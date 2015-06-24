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

package com.wonders.stpt.annualLeave.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.wonders.stpt.annualLeave.dao.HolConfigDao;
import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HolConfigʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HolConfigDaoImpl extends AbstractHibernateDaoImpl<HolConfig>
		implements HolConfigDao {
	public Page findHolConfigByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HolConfig t ";
		String countHql = "select count(*) from HolConfig t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("memo")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("beginDate")){
					filterPart += "t.hdate >= '" + filter.get(paramName)+"'";
					//args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("endDate")){
					filterPart += "t.hdate <= '" + filter.get(paramName)+"'";
					//args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.hdate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	

	@Override
	public List<HolConfig> findByYearAndHolPersonId(String year,String holPersonId) {
		String hql="from HolConfig t where t.holYear=? and t.holPersonId=? and t.removed='0'";
		Query query  = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, year).setString(1, holPersonId);
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public HolConfig findLastHolConfigByholPersonId(String holPersonId) {
		String hql="from HolConfig t where t.holPersonId=? and t.removed='0' order by t.holYear desc";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0,holPersonId).setMaxResults(1);
		List<HolConfig> list = query.list();
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}


	@Override
	public void saveAll(List<HolConfig> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByLoginName(String loginName) {
		String hql="from User t where t.loginName=? and t.removed='0'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, loginName);
		return query.list();
	}

	@Override
	public List<HolConfig> findByDay(String day) {
		String hql="from HolConfig t where t.hday=? and t.removed=0";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, day);
		return query.list();
	}

	@Override
	public List<HolConfig> findByIdAndDay(String id, String day) {
		String hql="from HolConfig t where t.id!=? and t.hdate=? and t.removed='0'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id).setString(1, day);
		return query.list();
	}



	@Override
	public void deleteByIdLogically(String id) {
		String hql="update HolConfig t set t.removed='1' where t.id=? and t.removed='0'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString(0, id).executeUpdate();
	}



	@Override
	public void saveOrUpdateAll(List<HolConfig> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}



	@Override
	public void save(HolConfig entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}



	@Override
	public HolConfig findById(String id) {
		String hql="from HolConfig t where t.id=? and t.removed='0'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id).setMaxResults(1);
		List<HolConfig> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}



	@Override
	public List<HolConfig> findAllHolConfig() {
		String hql ="from HolConfig t where t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<HolConfig> findBetweenHdate(String start,String end){
		if(StringUtils.isEmpty(start) || StringUtils.isEmpty(end)){
			return null;
		}
		String hql="from HolConfig t where t.hdate>=? and t.hdate<=? and t.removed='0' order by t.hdate asc";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, start).setString(1, end);
		return query.list();
	}
}
