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

package com.wonders.stpt.evaluate.FlowEvaluation.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.evaluate.FlowEvaluation.entity.bo.TFlowEvaluation;
import com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo.TFlowEvaluationItem;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public interface TFlowEvaluationService {
	/**
	 * 删除
	 * 
	 * @param tFlowEvaluation
	 */
	public void deleteTFlowEvaluation(TFlowEvaluation tFlowEvaluation);

	/**
	 * 
	 * 查询
	 * @param id
	 * @return
	 */
	public TFlowEvaluation findTFlowEvaluationById(String id);

	/**
	 * 新增
	 * @param tFlowEvaluation
	 */
	public void addTFlowEvaluation(TFlowEvaluation tFlowEvaluation);

	/**
	 * 更新
	 * @param tFlowEvaluation
	 */
	public void updateTFlowEvaluation(TFlowEvaluation tFlowEvaluation);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *           页码
	 * @param pageSize
	 *            总页数
	 * @return
	 */
	public Page findTFlowEvaluationByPage(int pageNo, int pageSize);

	/**
	 * 分页查询
	 * 
	 * @param filter
	 *            <propertyName,properyValue>
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            总页数
	 * @return
	 */
	public Page findTFlowEvaluationByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	/**
	 * @author ycl
	 * @description 根据flowId查询流程
	 * @param flowId
	 */
	public List<TFlowEvaluationItem> findItemByFlowId(String flowId);
	
	/**
	 * @author ycl
	 * @description 根据主键删除
	 */
	public void deleteFlowEvaluationById(String id);
	
	
	
}
