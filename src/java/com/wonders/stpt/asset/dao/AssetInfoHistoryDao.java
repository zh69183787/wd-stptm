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

package com.wonders.stpt.asset.dao;

import java.util.List;

import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public interface AssetInfoHistoryDao extends AbstractHibernateDao<AssetInfo> {
	
	/**
	 * 保存资产信息历史记录
	 * @param assetInfoHistory 资产信息
	 */
	public void saveAssetInfoHistory(AssetInfoHistory assetInfoHistory);
	
	/**
	 * 保存修改
	 * @param assetInfoHistory 资产信息
	 */
	public void updateAssetInfoHistory(AssetInfoHistory assetInfoHistory);
	
	/**
	 * 逻辑删除历史表
	 * @param id 资产信息表主键
	 */
	public void deleteAssetInfoHistoryByAssetInfoId(long id);
	
	/**
	 * 查询资产历史
	 * @param id	资产id
	 * @return	资产历史
	 */
	public List<AssetInfoHistory> findAllAssetHistoryByAssetInfoId(String id);
	
	/**
	 * 查询资产历史信息
	 * @param id 资产历史id
	 * @return	资产历史
	 */
	public AssetInfoHistory findAssetInfoHistoryById(String id);
	
	/**
	 * 保存资产信息
	 * @param assetInfoHistoryList	资产信息list
	 */
	public void saveAssetInfoHistoryList(List<AssetInfoHistory> assetInfoHistoryList);
	
	/**
	 * 根据资产id查询历史资产表
	 * @param assetId	资产id
	 * @return 历史资产信息list
	 */
	public List<AssetInfoHistory> findHistoryByAssetId(String assetId);
	
}
