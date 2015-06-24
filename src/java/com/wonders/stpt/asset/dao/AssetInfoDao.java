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
import java.util.Map;

import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wondersgroup.framework.core.bo.Page;
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

public interface AssetInfoDao extends AbstractHibernateDao<AssetInfo> {
	
	/**
	 * 分页查询
	 * @param filter 查询条件
	 * @param pageNo 页码
	 * @param pageSize 每页的条数
	 * @return	分页好后的数据
	 */
	public Page findAssetInfoByPage(Map<String, Object> filter, int pageNo,int pageSize);
	
	/**
	 * 查询搜索满足条件的资产信息
	 * @param filter 查询条件
	 * @return 分页好后的数据
	 */
	public List<AssetInfo> findAllAssetInfoByFilter(Map<String, Object> filter);
	
	/**
	 * 保存新增
	 * @param assetInfo 资产信息
	 */
	public void saveAssetInfo(AssetInfo assetInfo);
	
	/**
	 * 根据id查询
	 * @param id 主键 
	 * @return	资产信息
	 */
	public AssetInfo findById(String id);
	
	
	/**
	 * 更新资产信息
	 * @param assetInfo 资产信息
	 */
	public void updateAssetInfo(AssetInfo assetInfo);
	
	/**
	 * 删除资产信息
	 * @param assetInfo 资产信息
	 */
	public void deleteAssetInfo(AssetInfo assetInfo);
	
	
	/**
	 * 根据资产编号查询
	 * @param id
	 * @return	资产
	 */
	public AssetInfo findByAssetId(String id);
	
	/**
	 * 根据资产序号查询
	 * @param xh
	 * @return	资产
	 */
	public AssetInfo findByXH(String xh);
	
	/**
	 * 根据线路查询下一个资产的序号
	 * @param routeNum 线路
	 * @return 序号
	 */
	public String findNextXHByRouteNum(String routeNum);
	
	
	/**
	 * 查询资产
	 * @param routeNum 线路
	 * @param assetId	资产编号
	 * @return	资产信息
	 */
	public AssetInfo findByRouteNumAndAssetId(String routeNum,String assetId);
	
	/**
	 * 保存资产信息，
	 * @param assetInfoList	资产信息list
	 */
	public void saveAssetInfoList(List<AssetInfo> assetInfoList);
	
	/**
	 * author:mengjie
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findAssetInfoForExport (Map<String, Object> filter,String order);
	/**
	 * author:mengjie
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findDynamicManagementForExport(Map<String, Object> filter, String order);
	/**
	 * 资产入册
	 * @param id 主键
	 */
	public void inventory(String[] id);
	
		
	/**
	 * 根据assetId查询资产
	 * @param assetId	资产id
	 * @return	资产
	 */
	public List<AssetInfo> findAssetInfoByAssetId(String assetId);


	/**
	 * 查出所有资产信息，供盘点时导出所有资产信息使用
	 * author：mengjie
	 * @return
	 */
	public List<AssetInfo> findAllAsset();
	
}
