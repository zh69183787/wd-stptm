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

package com.wonders.stpt.evaluate.FlowEvaluationItem.dao;

import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public interface TFlowEvaluationItemDao extends
		AbstractHibernateDao<TFlowEvaluationItem> {
	public Page findTFlowEvaluationItemByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	/**
	 * @author ycl
	 * @description 根据名称查询
	 * @param id
	 * 			流程id 
	 * @param name
	 * 			评价名称
	 */
	public boolean findByName(String flowId,String name);
	
}
