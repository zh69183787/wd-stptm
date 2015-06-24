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

package com.wonders.stpt.evaluate.FlowEvaluation.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.evaluate.FlowEvaluation.dao.TFlowEvaluationDao;
import com.wonders.stpt.evaluate.FlowEvaluation.entity.bo.TFlowEvaluation;
import com.wonders.stpt.evaluate.FlowEvaluation.service.TFlowEvaluationService;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationServiceImpl implements TFlowEvaluationService {
	private TFlowEvaluationDao flowEvaluationDao;

	public void setFlowEvaluationDao(TFlowEvaluationDao tFlowEvaluationDao) {
		this.flowEvaluationDao = tFlowEvaluationDao;
	}

	public void addTFlowEvaluation(TFlowEvaluation tFlowEvaluation) {
		flowEvaluationDao.save(tFlowEvaluation);
	}

	public void deleteTFlowEvaluation(TFlowEvaluation tFlowEvaluation) {
		flowEvaluationDao.delete(tFlowEvaluation);
	}

	public TFlowEvaluation findTFlowEvaluationById(String id) {
		return flowEvaluationDao.load(id);
	}

	public void updateTFlowEvaluation(TFlowEvaluation tFlowEvaluation) {
		flowEvaluationDao.update(tFlowEvaluation);
	}

	public Page findTFlowEvaluationByPage(int pageNo, int pageSize) {
		Page page = flowEvaluationDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findTFlowEvaluationByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return flowEvaluationDao.findTFlowEvaluationByPage(filter, pageNo,
				pageSize);
	}
	
	@Override
	public List<TFlowEvaluationItem> findItemByFlowId(String flowId){
		return flowEvaluationDao.findItemByFlowId(flowId);
	}
	
	@Override
	public void deleteFlowEvaluationById(String id){
		flowEvaluationDao.deleteById(id);
	}
}
