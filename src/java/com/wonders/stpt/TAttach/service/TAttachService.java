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

package com.wonders.stpt.TAttach.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface TAttachService {
	/**
	 * 删除实体对象
	 * 
	 * @param tAttach
	 */
	public void deleteTAttach(TAttach tAttach);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public TAttach findTAttachById(Long id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param tAttach
	 */
	public void addTAttach(TAttach tAttach);

	/**
	 * 更新数据到数据库
	 * 
	 * @param tAttach
	 *            实体
	 */
	public void updateTAttach(TAttach tAttach);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findTAttachByPage(int pageNo, int pageSize);

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
	public Page findTAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	public void saveTAttach(TAttach tAttach);
	
	/**
	 * @author yaochenglong
	 * @describe 根据groupId查询附件
	 * @param groupId String类型:groupId值
	 * @return List<TAttach>类型
	 */
	public List<TAttach> findTAttachByGroupID(String groupId);
}
