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

package com.wonders.stpt.evaluate.FlowEvaluation.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.evaluate.FlowEvaluation.dao.TFlowEvaluationDao;
import com.wonders.stpt.evaluate.FlowEvaluation.entity.bo.TFlowEvaluation;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TFlowEvaluationʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationDaoImpl extends
		AbstractHibernateDaoImpl<TFlowEvaluation> implements TFlowEvaluationDao {
	public Page findTFlowEvaluationByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from TFlowEvaluation t ";
		String countHql = "select count(*) from TFlowEvaluation t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("evaluateDept") || paramName.equals("beEvaluatedDept")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("goodMediumBad")){		//好评中评差评
					if(!filter.get(paramName).equals("-1")){
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + " !=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
				}else if(paramName.equals("flowId")){
					if(!filter.get(paramName).equals("-1")){
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + " !=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
				}else if(paramName.equals("startDate")){
					filterPart += "to_char(t.evaluationTime,'yyyy-MM-dd')" + " >= :" + "sdate";
					args.add(new HqlParameter("sdate", filter.get(paramName)));
				}else if(paramName.equals("endDate")){
					filterPart += "to_char(t.evaluationTime,'yyyy-MM-dd')" + " <= :" + "edate";
					args.add(new HqlParameter("edate", filter.get(paramName)));
				}else if(paramName.equals("evaluationDetail")){
					filterPart += "t." + paramName + " =:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.evaluationTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	public List<TFlowEvaluationItem> findItemByFlowId(String flowId){
		String hql ="from TFlowEvaluationItem t where t.flowId='"+flowId+"' and t.removed='1' order by t.sortingOrder ASC";
		return getHibernateTemplate().find(hql);
	}
	
}
