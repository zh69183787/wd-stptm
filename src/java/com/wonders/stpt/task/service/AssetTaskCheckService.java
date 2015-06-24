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

package com.wonders.stpt.task.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-7-4
 * @author modify by $Author$
 * @since 1.0
 */

public interface AssetTaskCheckService {
	
	public Page findAssetTaskCheckByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	/**
	 * 查询盘点结果的总数
	 * @param id	盘点任务id
	 * @return	总数量
	 */
	public int findSumOfTaskCheckByTaskId(String id);
	
	
	/**
	 * 保存盘点结果
	 * @param list 盘点结果
	 */
	public void saveTaskCheckList(List<AssetTaskCheck> list);
	
	
	
	/**
	 * 查询所有的资产盘点任务
	 * @param taskId
	 * @return 资产盘点结果
	 */
	public List<AssetTaskCheck> findAllByTaskIdList(String taskId);
	
	/**
	 * 查询分类信息
	 * @param id 主键
	 * @return 盘点结果
	 */
	public CfCodeInfo findCfCodeInfoById(long id);
	
	/**
	 * 查询盘点结果
	 * @param assetInfoId	资产信息主键
	 * @param taskId	盘点任务主键
	 * @return	盘点结果
	 */
	public AssetTaskCheck findTaskByAssetInfoIdAndTaskId(String assetInfoId,String taskId);
	
	/**
	 * 查询分类信息
	 * @param typeId 类型id
	 * @param code	编号
	 * @return	分类信息
	 */
	public CfCodeInfo findCfCodeInfoByTypeIdAndCode(String typeId,String code);
	
	/**
	 * 查询盘点结果
	 * @param assetInfoId 资产信息主键
	 * @return 资产盘点结果
	 */
	public List<AssetTaskCheck> findByAssetInfoId(String assetInfoId);
	
	
	
	/**
	 * @param taskId 盘点任务ID
	 * @return
	 */
	public List<AssetTaskCheck> findByTaskId(String taskId);

	/**
	 * 检验盘点文件的合法性
	 * @param assetTaskCheck txt中截取的数据
	 * @return
	 */
	public boolean checkAssetTaskCheck(AssetTaskCheck assetTaskCheck);
	
	
	/**
	 * 查询盘点结果的总数
	 * @param id	盘点任务id
	 * @return	总数量
	 */
	public int findSumOfTaskByTaskId(String id);
	
	
	
	
	
	
	
	
	
}
