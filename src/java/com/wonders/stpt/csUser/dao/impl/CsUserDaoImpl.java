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

package com.wonders.stpt.csUser.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.csUser.dao.CsUserDao;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * CsUser实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-27
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
	
	@Override
	public List<CsUser> findById(long id) {
		String hql ="from CsUser t where t.id='"+id+"' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}
}
