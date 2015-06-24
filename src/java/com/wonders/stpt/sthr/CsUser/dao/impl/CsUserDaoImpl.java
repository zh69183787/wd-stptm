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

package com.wonders.stpt.sthr.CsUser.dao.impl;

import com.wonders.stpt.sthr.CsUser.dao.CsUserDao;
import com.wonders.stpt.sthr.CsUser.entity.bo.CsUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * CsUser实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-3-14
 * @author modify by $Author$
 * @since 1.0
 */

public class CsUserDaoImpl extends AbstractHibernateDaoImpl<CsUser> implements
		CsUserDao {
	public Page findCsUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from CsUser t ";
		String countHql = "select count(*) from CsUser t ";
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
	
	/**
	 * @describe 工号及姓名校验
	 * @author sunjiawei
	 */
	public List<Object[]> checkJobNumber(String jobNumber){
		String sql = "select t.name  from t_user t where t.login_name='"+jobNumber+"'";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();
	}
}
