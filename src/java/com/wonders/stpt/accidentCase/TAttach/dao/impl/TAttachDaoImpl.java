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

package com.wonders.stpt.accidentCase.TAttach.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.TAttach.dao.TAttachDao;
import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TAttach实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TAttachDaoImpl extends AbstractHibernateDaoImpl<TAttach> implements
		TAttachDao {
	public Page findTAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from TAttach t ";
		String countHql = "select count(*) from TAttach t ";
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
	 * @author yaochenglong
	 * @describe 根据groupId查询附件
	 * @param groupId String类型:groupId值
	 * @return List<TAttach>类型
	 */
	public List<TAttach> findTAttachByGroupID(String groupId){
		
		String hql = "from TAttach a where a.groupid='"+groupId+"' and a.status='upload'";
		return getHibernateTemplate().find(hql);
	}
}
