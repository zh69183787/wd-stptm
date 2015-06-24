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

import com.wonders.stpt.asset.entity.bo.DwAssetCodeInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author LY
 * @version $Revision$
 * @date 2013-1-6
 * @since 1.0
 */

public interface DwAssetCodeInfoDao extends AbstractHibernateDao<DwAssetCodeInfo> {
	public Page findDwAssetCodeInfoByPage(Map<String, Object> filter, int pageNo, int pageSize);

	// 查询全部DwAsset数据
	public List<DwAssetCodeInfo> findAllDwAsset();

	// 根据typeId,dm查询
	public DwAssetCodeInfo findDwAssetByTypeIdAndDm(long typeId, String dm);
}
