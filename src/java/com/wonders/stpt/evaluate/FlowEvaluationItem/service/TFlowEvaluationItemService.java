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

package com.wonders.stpt.evaluate.FlowEvaluationItem.service;

import java.util.Map;

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

public interface TFlowEvaluationItemService {
	/**
	 * ɾ��ʵ�����
	 * 
	 * @param tFlowEvaluationItem
	 */
	public void deleteTFlowEvaluationItem(
			TFlowEvaluationItem tFlowEvaluationItem);

	/**
	 * 
	 * ͨ��IDװ����Ӧ�Ķ���ʵ������Ӧ��ʵ�岻���ڣ�����null
	 * 
	 * @param id
	 *            ���
	 * @return
	 */
	public TFlowEvaluationItem findTFlowEvaluationItemById(String id);

	/**
	 * �־û�һ��ʵ�����
	 * 
	 * @param tFlowEvaluationItem
	 */
	public void addTFlowEvaluationItem(TFlowEvaluationItem tFlowEvaluationItem);

	/**
	 * ������ݵ���ݿ�
	 * 
	 * @param tFlowEvaluationItem
	 *            ʵ��
	 */
	public void updateTFlowEvaluationItem(
			TFlowEvaluationItem tFlowEvaluationItem);

	/**
	 * ��ݷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findTFlowEvaluationItemByPage(int pageNo, int pageSize);

	/**
	 * ���Map�й�������ͷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param filter
	 *            �������<propertyName,properyValue>
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
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
	
	/**
	 * @author ycl
	 * @description 根据主键删除
	 * @param id
	 */
	public void deleteEvaluationItemById(String id);
	
	
}
