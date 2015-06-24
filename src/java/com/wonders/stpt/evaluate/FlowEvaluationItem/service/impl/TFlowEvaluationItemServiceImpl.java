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

package com.wonders.stpt.evaluate.FlowEvaluationItem.service.impl;

import java.util.Map;

import com.wonders.stpt.evaluate.FlowEvaluationItem.dao.TFlowEvaluationItemDao;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wonders.stpt.evaluate.FlowEvaluationItem.service.TFlowEvaluationItemService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationItemServiceImpl implements
		TFlowEvaluationItemService {
	private TFlowEvaluationItemDao flowEvaluationItemDao;

	public void setFlowEvaluationItemDao(
			TFlowEvaluationItemDao tFlowEvaluationItemDao) {
		this.flowEvaluationItemDao = tFlowEvaluationItemDao;
	}

	public void addTFlowEvaluationItem(TFlowEvaluationItem tFlowEvaluationItem) {
		flowEvaluationItemDao.save(tFlowEvaluationItem);
	}

	public void deleteTFlowEvaluationItem(
			TFlowEvaluationItem tFlowEvaluationItem) {
		flowEvaluationItemDao.delete(tFlowEvaluationItem);
	}

	public TFlowEvaluationItem findTFlowEvaluationItemById(String id) {
		return flowEvaluationItemDao.load(id);
	}

	public void updateTFlowEvaluationItem(
			TFlowEvaluationItem tFlowEvaluationItem) {
		flowEvaluationItemDao.update(tFlowEvaluationItem);
	}

	public Page findTFlowEvaluationItemByPage(int pageNo, int pageSize) {
		Page page = flowEvaluationItemDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findTFlowEvaluationItemByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return flowEvaluationItemDao.findTFlowEvaluationItemByPage(filter,
				pageNo, pageSize);
	}

	@Override
	public boolean findByName(String flowId, String name) {
		return flowEvaluationItemDao.findByName(flowId, name);
	}
	
	@Override
	public void deleteEvaluationItemById(String id){
		flowEvaluationItemDao.deleteById(id);
	}
}
