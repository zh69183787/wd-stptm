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

package com.wonders.stpt.dbBusiness.service;

import com.wonders.stpt.dbBusiness.entity.bo.DbBusiness;
import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface DbBusinessService {

	/**
	 * 分页查询
	 * 
	 * @param filter
	 *            过滤条件
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示的数量
	 * @return 分页后的数据
	 */
	public List<Object[]> findDbBusinessByPage(String orderValue,int startRow,int pageSize,String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow);
	
	public int countDbBusiness(String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow);
	
	public List<Object[]> findDbBusinessByIdAndDeptName(String id,String deptName);
	/**
	 * 保存新增
	 * 
	 * @param assetTask
	 *            资产任务盘点
	 */
	public void saveAdd(DbBusiness assetTask);

	/**
	 * 查看详细
	 * 
	 * @param id
	 *            主键
	 * @return 资产盘点任务
	 */
	public DbBusiness findTaskById(String id);

	/**
	 * 保存修改
	 * 
	 * @param assetTask
	 *            资产盘点任务
	 */
	public void updateTask(DbBusiness assetTask);

	/**
	 * 删除
	 * 
	 * @param id
	 *            主键
	 */
	public void deleteTaskById(String id);

	/**
	 * 查找所有
	 * 
	 * @return
	 */
	public List<DbBusiness> findAllTask();

}
