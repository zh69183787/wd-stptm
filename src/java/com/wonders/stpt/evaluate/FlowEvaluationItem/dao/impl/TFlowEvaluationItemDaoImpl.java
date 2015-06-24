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

package com.wonders.stpt.evaluate.FlowEvaluationItem.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.evaluate.FlowEvaluationItem.dao.TFlowEvaluationItemDao;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TFlowEvaluationItemʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationItemDaoImpl extends
		AbstractHibernateDaoImpl<TFlowEvaluationItem> implements
		TFlowEvaluationItemDao {
	public Page findTFlowEvaluationItemByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from TFlowEvaluationItem t ";
		String countHql = "select count(*) from TFlowEvaluationItem t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("flowId")){
					if(!filter.get(paramName).equals("-1")){
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + "!=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				
				filterCounter++;
			}
		}
		filterPart += " order by t.flowId ASC,t.sortingOrder ASC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	@Override
	public boolean findByName(String flowId,String name){
		String hql = "from TFlowEvaluationItem t where t.flowId='"+flowId+"' and t.name='"+name+"' and removed='1'";
		Query query = getSession().createQuery(hql);
		List<?> list = query.list();
		if(list!=null && list.size()>0) return true;
		else return false;
	}
	
}
