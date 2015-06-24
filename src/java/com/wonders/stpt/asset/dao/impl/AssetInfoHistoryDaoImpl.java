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

import java.util.List;

import org.hibernate.Query;

import com.wonders.stpt.asset.dao.AssetInfoHistoryDao;
import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * AssetInfoHistory
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetInfoHistoryDaoImpl extends AbstractHibernateDaoImpl<AssetInfo> implements AssetInfoHistoryDao {

	@Override
	public void saveAssetInfoHistory(AssetInfoHistory assetInfoHistory) {
		getHibernateTemplate().save(assetInfoHistory);
	}

	@Override
	public void updateAssetInfoHistory(AssetInfoHistory assetInfoHistory) {
		getHibernateTemplate().update(assetInfoHistory);
	}

	/**
	 * 逻辑删除历史表
	 * @param id 资产信息表主键
	 */
	public void deleteAssetInfoHistoryByAssetInfoId(long id){
		String hql = "update AssetInfoHistory t set t.removed='1' where t.assetInfoId='"+id+"' and t.removed='0'";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<AssetInfoHistory> findAllAssetHistoryByAssetInfoId(String id) {
		String hql ="from AssetInfoHistory t where t.assetInfoId='"+id+"' and removed='0' order by t.operateTime DESC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public AssetInfoHistory findAssetInfoHistoryById(String id) {
		String hql ="from AssetInfoHistory t where t.id='"+id+"'";
		return (AssetInfoHistory) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public void saveAssetInfoHistoryList(List<AssetInfoHistory> assetInfoHistoryList) {
		getHibernateTemplate().saveOrUpdateAll(assetInfoHistoryList);
	}

	@Override
	public List<AssetInfoHistory> findHistoryByAssetId(String assetId) {
		String hql = "from AssetInfoHistory t where t.assetId='"+assetId+"' order by t.operateTime DESC";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}
