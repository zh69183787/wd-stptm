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

package com.wonders.stpt.asset.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.dao.DwAssetCodeInfoDao;
import com.wonders.stpt.asset.entity.bo.DwAssetCodeInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwAssertCodeInfo实体定义
 * 
 * @author LY
 * @version $Revision$
 * @date 2013-1-6
 * @since 1.0
 */

public class DwAssetCodeInfoDaoImpl extends AbstractHibernateDaoImpl<DwAssetCodeInfo> implements DwAssetCodeInfoDao {
	public Page findDwAssetCodeInfoByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from DwAssertCodeInfo t ";
		String countHql = "select count(*) from DwAssertCodeInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				filterPart += "t." + paramName + "=:" + paramName;
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
	}

	@Override
	public List<DwAssetCodeInfo> findAllDwAsset() {
		String hql = "from DwAssetCodeInfo";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public DwAssetCodeInfo findDwAssetByTypeIdAndDm(long typeId, String dm) {
		String hql = "from DwAssetCodeInfo d where d.typeId= '" + typeId + "' and d.dm = '" + dm + "' and d.removed='0'";
		List<DwAssetCodeInfo> dwAsset = getHibernateTemplate().find(hql);
		if (dwAsset == null || dwAsset.size() < 1) {
			return null;
		} else {
			return dwAsset.get(0);
		}
	}
}
