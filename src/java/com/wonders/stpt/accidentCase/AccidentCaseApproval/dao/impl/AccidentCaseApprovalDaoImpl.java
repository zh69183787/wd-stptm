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

package com.wonders.stpt.accidentCase.AccidentCaseApproval.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.dao.AccidentCaseApprovalDao;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * AccidentCaseApproval实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-2-27
 * @author modify by $Author$
 * @since 1.0
 */

public class AccidentCaseApprovalDaoImpl extends
		AbstractHibernateDaoImpl<AccidentCaseApproval> implements
		AccidentCaseApprovalDao {
	public Page findAccidentCaseApprovalByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AccidentCaseApproval t ";
		String countHql = "select count(*) from AccidentCaseApproval t ";
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
}
