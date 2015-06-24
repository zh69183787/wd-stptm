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

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.wonders.stpt.task.dao.AssetTaskDao;
import com.wonders.stpt.task.entity.bo.AssetTask;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetTaskDaoImpl extends AbstractHibernateDaoImpl<AssetTask> implements AssetTaskDao {
	public Page findAssetTaskByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetTask t ";
		String countHql = "select count(*) from AssetTask t ";
		String filterPart = "";
		String lastFilterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty() && filter.size()>2) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				String paramName = i.next();
				if (filterCounter > 0 && (!paramName.equals("checkpersonlist") && !paramName.equals("taskuser"))) {
					filterPart += " and ";
					filterCounter++;
				}
				
				if(!paramName.equals("checkpersonlist") && !paramName.equals("taskuser")){
					if(paramName.equals("taskname") || paramName.equals("checkpersonlist")){
						filterPart += "t." + paramName + " like :" + paramName;
						args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					}else if(paramName.equals("start1")){
						filterPart += "t.starttime >='" + filter.get("start1")+"'";
					}else if(paramName.equals("start2")){
						filterPart += "t.starttime <='" + filter.get("start2")+"'";
					}else if(paramName.equals("end1")){
						filterPart += "t.endtime >='" + filter.get("end1")+"'";
					}else if(paramName.equals("end2")){
						filterPart += "t.endtime <='" + filter.get("end2")+"'";
					}else{
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
					filterCounter++;
				}
			}
		}
		//lastFilterPart = "(t.taskuser='"+filter.get("taskuser")+"' or t.checkpersonlist like '%"+filter.get("checkpersonlist")+"%')";
		lastFilterPart="1=1";
		if(!"".equals(filterPart)){
			filterPart += " and "+lastFilterPart;
		}else{
			filterPart += " where "+lastFilterPart;
		}
		filterPart += " order by t.operateDate DESC ";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

	@Override
	public void saveAdd(AssetTask assetTask) {
		getHibernateTemplate().save(assetTask);
	}

	@Override
	public AssetTask findTaskById(String id) {
		String hql ="from AssetTask t where t.id='"+id+"'";
		return (AssetTask) getHibernateTemplate().get(AssetTask.class, id);
	}

	@Override
	public void updateTask(AssetTask assetTask) {
		getHibernateTemplate().update(assetTask);
	}

	@Override
	public void deleteTaskById(String id) {
		String hql ="delete from AssetTask t where t.id='"+id+"'";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}
	
	@Override
	
	public List<AssetTask> findAllTask() {
		List<AssetTask> list = new ArrayList<AssetTask>();
		SQLQuery query=this.getSession().createSQLQuery("select distinct checkpersonlist from t_asset_task t where t.removed='0'");
		query.addScalar("checkpersonlist", Hibernate.STRING);
		list=query.setResultTransformer(Transformers.aliasToBean(AssetTask.class)).list();
		return list;
	}
	

	
}
