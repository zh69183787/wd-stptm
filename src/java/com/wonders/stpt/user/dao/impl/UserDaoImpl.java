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

package com.wonders.stpt.user.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.user.entity.bo.User;
import com.wonders.stpt.user.dao.UserDao;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * User实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

public class UserDaoImpl extends AbstractHibernateDaoImpl<User> implements
		UserDao {
	public Page findUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from User t ";
		String countHql = "select count(*) from User t ";
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
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	public List<User> findAssetTaskCheckPersonByName(String name, String checkperson) {
		String hql ="select t1.name from User t1 inner join AssetTaskCheck t2 on t1.name = t2.checkperson";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}
	
	public List<User> findAllUser() {
		List<User> list = new ArrayList<User>();
		//Transaction tran=this.getSession().beginTransaction();
		SQLQuery query=this.getSession().createSQLQuery("select login_name as loginName,company from t_user");
		query.addScalar("loginName", Hibernate.STRING);
		query.addScalar("company", Hibernate.STRING);
		list=query.setResultTransformer(Transformers.aliasToBean(User.class)).list();
		return list;
	}
	
	public List<User> findUserByCheckpersonlist(String checkpersonlist) {
		List<User> list = new ArrayList<User>();
		//Transaction tran=this.getSession().beginTransaction();
		SQLQuery query=this.getSession().createSQLQuery("select login_name as loginName,company from t_user t,t_user_relation r where t.id=r.t_id and (r.agent is null or r.agent=0) and  r.c_id in("+checkpersonlist+")");
		query.addScalar("loginName", Hibernate.STRING);
		query.addScalar("company", Hibernate.STRING);
		list=query.setResultTransformer(Transformers.aliasToBean(User.class)).list();
		return list;
	}
	
}
