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

package com.wonders.stpt.accidentCase.MetroAccidentCase.service;

import java.util.Map;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo.MetroAccidentCase;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroAccidentCaseService {
	/**
	 * 删除实体对象
	 * 
	 * @param metroAccidentCase
	 */
	public void deleteMetroAccidentCase(MetroAccidentCase metroAccidentCase);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public MetroAccidentCase findMetroAccidentCaseById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param metroAccidentCase
	 */
	public void addMetroAccidentCase(MetroAccidentCase metroAccidentCase);

	/**
	 * 更新数据到数据库
	 * 
	 * @param metroAccidentCase
	 *            实体
	 */
	public void updateMetroAccidentCase(MetroAccidentCase metroAccidentCase);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findMetroAccidentCaseByPage(int pageNo, int pageSize);
	
	

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findMetroAccidentCaseByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	public Page findMetroAccidentCaseByPage2(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	/**
	 * @author ycl
	 * @param id String类型:主键
	 * @describe 根据主键删除
	 */
	public void deleteMetroAccidentCaseById(String id);
	
	/**
	 * @author sunjiawei
	 * @param accidentCaseApproval 实体
	 * @describe 添加审核记录
	 */
	public void addAccidentCaseApproval(AccidentCaseApproval accidentCaseApproval);
	
	
	public Page findMetroAccidentCaseByUpdatePerson(Map<String, Object> filter,
			int pageNo, int pageSize);
	
}
