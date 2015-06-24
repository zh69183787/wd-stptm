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

package com.wonders.stpt.asset.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public interface AssetInfoService {
	
	/**
	 * 分页查询
	 * @param filter 查询条件
	 * @param pageNo 页码
	 * @param pageSize 每页的条数
	 * @return 分页好后的数据
	 */
	public Page findAssetInfoByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
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
	public void saveAssertInfo(AssetInfo assetInfo);
	
	
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
	 * 查询字典表
	 * @param id codeInfoId
	 */
	public List<CfCodeInfo> findCfInfoCodeById(long id);
	
	/**
	 * 查询字典表，所有车站（不包括线路）
	 * author:mengjie
	 * @param typeId 类别id
	 */
	public List<CfCodeInfo> findCfInfoCodeForStationByTypeId(long typeId,String code_info_id);

	
	/**
	 * 查询字典表
	 * @param id	主键
	 * @return	分类
	 */
	public CfCodeInfo findCfCodeInfoById(long id);
	
	
	
	/**
	 * 保存资产历史信息
	 * @param history  资产历史信息
	 */
	public void saveAssetInfoHistory(AssetInfoHistory history);
	
	/**
	 * 保存更新
	 * @param history 历史资产信息
	 */
	public void updateAssryInfoHistory(AssetInfoHistory history);
	
	/**
	 * 逻辑删除历史表
	 * @param id 资产信息表主键
	 */
	public void deleteAssetInfoHistoryByAssetInfoId(long id);
	
	
	/**
	 * 查询某一个类别
	 * @param id 主键
	 * @return 分类
	 */
	public List<CfCodeInfo> findCfCodeInfoListTypeById(long id);
	 
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
	 * 查询资产历史
	 * @param id	资产id
	 * @return	资产历史
	 */
	public List<AssetInfoHistory> findAllAssetHistoryByAssetInfoId(String id);
	
	/**
	 * 根据线路查询下一个资产的序号
	 * @param routeNum 线路
	 * @return 序号
	 */
	public String findNextXHByRouteNum(String routeNum);
	
	/**
	 * 查询资产历史信息
	 * @param id 资产历史id
	 * @return	资产历史
	 */
	public AssetInfoHistory findAssetInfoHistoryById(String id);
	
	/**
	 * 主键查询
	 * 根据名称和类型查询主键
	 * @param codeInfoId	编码id
	 * @param name	名称
	 * @return	主键
	 */
	public String findCfCodeInfoIdByCodeInfoIdAndName(long codeInfoId,String name);
	
	/**
	 * 主键查询
	 * 根据名称和类型查询主键
	 * @param codeId 编码
	 * @param name	名称
	 * @return	主键
	 */
	public String findCfCodeInfoIdByCodeAndName(String code,String name);
	
	/**
	 * 主键查询
	 * 根据codeInfoId和code查询
	 * @param codeInfoId 类别id
	 * @param code	；类别编号
	 * @return	主键
	 */
	public String findCfCodeInfoIdByCodeInfoIdAndCode(long codeInfoId,String code);
	
	/**
	 * 主键查询
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @return	主键
	 */
	public String findCfCodeInfoIdByTypeIdAndName(long typeId,String name);
	
	/**
	 * 查询资产
	 * @param routeNum 线路
	 * @param assetId	资产编号
	 * @return	资产信息
	 */
	public AssetInfo findByRouteNumAndAssetId(String routeNum,String assetId);
	
	/**
	 * 主键查询
	 * 根据typeId和code查询主键
	 * @param typeId	类型id
	 * @param code	类别编号
	 * @return	主键
	 */
	public String findCfCodeInfoIdByTypeIdAndCode(long typeId,String code);
	
	/**
	 * 查询code
	 * @param id	主键
	 * @return	code
	 */
	public String findCodeById(String id);
	
	/**
	 * 主键查询
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @param code 类别
	 * @return	主键
	 */
	public String findCfCodeInfoIdByTypeIdAndNameAndCode(long typeId,String name,String code);
	
	/**
	 * 保存资产信息
	 * @param assetInfoList	资产信息list
	 */
	public void saveAssetInfoList(List<AssetInfo> assetInfoList);
	
	
	/**
	 * 保存资产信息
	 * @param assetInfoHistoryList	资产信息list
	 */
	public void saveAssetInfoHistoryList(List<AssetInfoHistory> assetInfoHistoryList);
	
	/**
	 * 查询所有字典表中数据
	 */
	public List<CfCodeInfo> findAllCfCodeInfo();
	
	
	/**
	 * @author mengjie
	 * 导出资产清册维护Excel
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findAssetInfoForExport (Map<String, Object> filter,String order);

	/**
	 * @author mengjie
	 * 导出资产动态管理Excel
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findDynamicManagementForExport (Map<String, Object> filter,String order);
	
	/**
	 * @author mengjie
	 * 根据typeid  查询所有 CfCodeInfo
	 * @param id codeInfoId
	 */
	public List<CfCodeInfo> findCfCodeInfoByTypeId(long id);
	

	
	/**
	 * 资产入册
	 * @param id 主键
	 */
	public void inventory(String[] id);
	
	/**
	 * 根据资产id查询历史资产表
	 * @param assetId	资产id
	 * @return 历史资产信息list
	 */
	public List<AssetInfoHistory> findHistoryByAssetId(String assetId);

	/**
	 * 根据assetId查询资产
	 * @param assetId	资产id
	 * @return	资产
	 */
	public AssetInfo findAssetInfoByAssetId(String assetId);
	
	/**
	 * 
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @param code_info_id 类别
	 * @return	主键
	 */
	public String findByTypeIdAndNameAndCodeInfoId(long typeId,String name,String code_info_id);

	/**
	 * 根据类别的长度，查出大中小类
	 * author：mengjie
	 * @param typeId  总类别typeid
	 * @param length  长度（区分大中小类）
	 * @return
	 */
	public List<CfCodeInfo> findCfInfoCodeForAllTypeByLength(long typeId,int length);
	
	/**
	 * 查出所有资产信息，供盘点时导出所有资产信息使用
	 * author：mengjie
	 * @return
	 */
	public List<AssetInfo> findAllAsset();
}
