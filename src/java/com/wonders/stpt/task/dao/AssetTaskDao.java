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

package com.wonders.stpt.task.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.task.entity.bo.AssetTask;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface AssetTaskDao extends AbstractHibernateDao<AssetTask> {
	
	/**
	 * 分页查询
	 * @param filter 过滤条件
	 * @param pageNo 当前页码
	 * @param pageSize 每页显示的数量
	 * @return 分页后的数据
	 */
	public Page findAssetTaskByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	/**
	 * 保存新增
	 * @param assetTask 资产任务盘点
	 */
	public void saveAdd(AssetTask assetTask);
	
	/**
	 * 查看详细
	 * @param id 主键
	 * @return	资产盘点任务
	 */
	public AssetTask findTaskById(String id);
	
	/**
	 * 保存修改
	 * @param assetTask 资产盘点任务
	 */
	public void updateTask(AssetTask assetTask);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteTaskById(String id);
	
	/**查找所有
	 * @return
	 */
	public List<AssetTask> findAllTask();
	

}
