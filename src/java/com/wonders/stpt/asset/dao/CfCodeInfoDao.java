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
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
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

public interface CfCodeInfoDao extends AbstractHibernateDao<AssetInfo> {
	
	/**
	 * 查询
	 * @param id codeInfoId
	 * @return
	 */
	public List<CfCodeInfo> findCfCodeInfo(long id);
	
	/**
	 * 查询字典表
	 * @param id	主键
	 * @return	分类
	 */
	public CfCodeInfo findCfCodeInfoById(long id);
	
	/**
	 * 查询某一个类别
	 * @param id 主键
	 * @return 分类
	 */
	public List<CfCodeInfo> findCfCodeInfoListTypeById(long id);
	
	/**
	 * 查询类别
	 * @param typeId	类别id
	 * @param code	编号
	 * @return	分类
	 */
	public CfCodeInfo findByTypeIdAndCode(String typeId,String code);
	
	/**
	 * 根据名称和类型查询主键
	 * @param type	类型
	 * @param name	名称
	 * @return	主键
	 */
	public List<Object> findCfCodeInfoIdByCodeInfoIdAndName(long codeInfoId,String name);
	
	/**
	 * 根据codeInfoId和code查询
	 * @param codeInfoId 类别id
	 * @param code	；类别编号
	 * @return	主键
	 */
	public List<Object> findCfCodeInfoIdByCodeInfoIdAndCode(long codeInfoId,String code);
	
	/**
	 * 根据名称和类型查询主键
	 * @param codeId 编码
	 * @param name	名称
	 * @return	主键
	 */
	public List<Object> findCfCodeInfoIdByCodeAndName(String code,String name);
	
	/**
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @return	主键
	 */
	public List<Object> findCfCodeInfoIdByTypeIdAndName(long typeId,String name);
	
	
	/**
	 * 主键查询
	 * 根据typeId和code查询主键
	 * @param typeId	类型id
	 * @param code	类别编号
	 * @return	主键
	 */
	public List<Object> findCfCodeInfoIdByTypeIdAndCode(long typeId,String code);
	
	/**
	 * 查询code
	 * @param id	主键
	 * @return	code
	 */
	public List<Object> findCodeById(String id);
	
	/**
	 * 主键查询
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @param code 类别
	 * @return	
	 */
	public List<Object> findCfCodeInfoIdByTypeIdAndNameAndCode(long typeId,String name,String code);
	
	/**
	 * 查询所有字典表中数据
	 */
	public List<CfCodeInfo> findAllCfCodeInfo();

	public List<CfCodeInfo> findCfCodeInfoIdByTypeId(long id);
	
	/**
	 * 根据类型查询
	 * @param typeId 类型id
	 * @return	List<CfCodeInfo>
	 */
	public List<CfCodeInfo> findCfCodeInfoByTypeId(long typeId);
	
	/**
	 * 
	 * 根据名称和类型查询主键
	 * @param typeId	类别id
	 * @param name	名称
	 * @param code_info_id 类别
	 * @return	主键
	 */
	public List<Object> findByTypeIdAndNameAndCodeInfoId(long typeId,String name,String code_info_id);

	/**
	 * 查询字典表，所有车站（不包括线路）
	 * author:mengjie
	 * @param id codeInfoId
	 */
	public List<CfCodeInfo> findCfInfoCodeForStationByTypeId(long typeId,String code_info_id);

	/**
	 * @param typeId  类别id
	 * @param length  长度（区分大中小类）
	 * @return
	 */
	public List<CfCodeInfo> findCfInfoCodeForAllTypeByLength(long typeId,int length);
	
}
