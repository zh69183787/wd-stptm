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

package com.wonders.stpt.task.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.task.dao.AssetTaskCheckDao;
import com.wonders.stpt.task.entity.bo.AssetTask;
import com.wonders.stpt.task.entity.bo.AssetTaskCheck;
import com.wonders.stpt.task.service.AssetTaskService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * AssetTaskCheckʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-7-4
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetTaskCheckDaoImpl extends AbstractHibernateDaoImpl<AssetTaskCheck> implements AssetTaskCheckDao {
	public Page findAssetTaskCheckByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetTaskCheck t ";
		String countHql = "select count(*) from AssetTaskCheck t ";
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
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public int findSumOfTaskCheckByTaskId(String id) {
		/*String sql ="select count(*) from T_ASSET_TASK_CHECK t where t.TASK_ID='"+id+"' and t.REMOVED='0'";
		SQLQuery query = getSession().createSQLQuery(sql);*/
		
		//String hql = "from AssetTaskCheck t where t.taskId='"+id+"' and t.removed='0'";
		String hql = "select distinct t.assetInfoId from AssetTaskCheck t where t.taskId='"+id+"' and t.removed='0'";
		
		//List<AssetTaskCheck> list = getHibernateTemplate().find(hql);
		List<String> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0)
			return list.size();
		return 0;
	}


	
	@Override
	public void saveTaskCheckList(List<AssetTaskCheck> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public List<AssetTaskCheck> findAllByTaskId(String taskId) {
		String hql = "from AssetTaskCheck t where t.taskId='"+taskId+"' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public AssetTaskCheck findTaskByAssetInfoIdAndTaskId(String assetInfoId, String taskId) {
		String hql ="from AssetTaskCheck t where t.assetInfoId='"+assetInfoId+"' and t.taskId='"+taskId+"' and t.removed='0' order by t.id DESC";
		List<AssetTaskCheck> list = getSession().createQuery(hql).setMaxResults(1).list();
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<AssetTaskCheck> findByAssetInfoId(String assetInfoId) {
		String hql ="from AssetTaskCheck t where t.assetInfoId='"+assetInfoId+"' and t.removed='0' order by t.checkdate";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<AssetTaskCheck> findByTaskId(String taskId) {
		String hql ="from AssetTaskCheck t where t.taskId='"+taskId+"' and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public int findSumOfTaskByTaskId(String id) {
		/*String sql ="select count(*) from T_ASSET_TASK_CHECK t where t.TASK_ID='"+id+"' and t.REMOVED='0'";
		SQLQuery query = getSession().createSQLQuery(sql);*/
		
		//String hql = "from AssetTaskCheck t where t.taskId='"+id+"' and t.removed='0'";
		String hql = "select distinct t.id from AssetTask t where t.id='"+id+"' and t.removed='0'";
		
		//List<AssetTaskCheck> list = getHibernateTemplate().find(hql);
		List<String> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0)
			return list.size();
		return 0;
	}
	
	@Override
	public boolean checkAssetTaskCheck(AssetTaskCheck assetTaskCheck) {
		//  验证taskeID 是否合法
		int num  = this.findSumOfTaskByTaskId(assetTaskCheck.getTaskId());
		if(num > 0){
			// TODO Auto-generated method stub
			String hql ="from AssetTaskCheck t where t.taskId='"+assetTaskCheck.getTaskId()+"' " +
					" and t.checkdate='" +assetTaskCheck.getCheckdate()+"'" +
					" and t.checkperson='" +assetTaskCheck.getCheckperson()+"'" +
					" and t.checkinfo='" +assetTaskCheck.getCheckinfo()+"'" +
					" and t.assetInfoId='" +assetTaskCheck.getAssetInfoId()+"'" +
					" and t.removed='0'";
			
			List<AssetTaskCheck> list = getHibernateTemplate().find(hql);
			//若list为空，则数据库中没有该条资产的记录，可以保存，返回true
			if(list==null||list.size()==0){
				return true;
			}
			
			return false;
		}
		return false;
	}
	

}
