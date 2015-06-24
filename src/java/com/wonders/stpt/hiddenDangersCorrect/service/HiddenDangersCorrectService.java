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

package com.wonders.stpt.hiddenDangersCorrect.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HiddenDangersCorrect;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public interface HiddenDangersCorrectService {
	/**
	 * 删除实体对象
	 * 
	 * @param hiddenDangersCorrect
	 */
	public void deleteHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public HiddenDangersCorrect findHiddenDangersCorrectById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param hiddenDangersCorrect
	 */
	public void addHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect);

	/**
	 * 更新数据到数据库
	 * 
	 * @param hiddenDangersCorrect
	 *            实体
	 */
	public void updateHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findHiddenDangersCorrectByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @param webTypeId
	 *            网页操作类型.
	 * @return
	 */
	public Page findHiddenDangersCorrectByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String webTypeId);
	
	public void updateCheckState(String check_state,String id);
	
	public List<Object[]> findForExport(Map<String, Object> filter,String operateTypeId);
}
