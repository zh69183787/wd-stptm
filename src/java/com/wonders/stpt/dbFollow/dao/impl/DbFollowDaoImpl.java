package com.wonders.stpt.dbFollow.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.stpt.dbFollow.dao.DbFollowDao;
import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowChild;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class DbFollowDaoImpl extends AbstractHibernateDaoImpl<Object> implements DbFollowDao {

	@Override
	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(Collection col){
		this.getHibernateTemplate().saveOrUpdateAll(col);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object load(Serializable id,Class clazz) {
		Object instance = getHibernateTemplate().get(clazz, id);
		return instance;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DbFollowChild> findFollowChildByParentId(String parentId){
		String hql = "from DbFollowChild t where t.removed = '0' and t.parentId = '"+parentId+"' order by t.createTime";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public Page findDbFollowByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from DbFollow t where t.removed = '0' ";
		String countHql = "select count(*) from DbFollow t where t.removed = '0' ";
		String filterPart = "";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("dbName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
			}
		}
		filterPart += " ORDER BY t.createTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	@Override
	public Page findDbFollowChildByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t1,t from DbFollow t,DbFollowChild t1 where t.removed = '0' and t1.removed = '0' and t.id = t1.parentId ";
		String countHql = "select count(*) from DbFollow t,DbFollowChild t1 where t.removed = '0' and t1.removed = '0' and t.id = t1.parentId ";
		String filterPart = "";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("dbName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("planSubmitTimeStart")){
					filterPart += "t1.planSubmitTime >=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("planSubmitTimeEnd")){
					filterPart += "t1.planSubmitTime <=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("oldDeptId")){
					filterPart += "t1.followDeptId =:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));					
				}else if(paramName.equals("followType")){
					filterPart += "t." + paramName + " =:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));					
				}else if(paramName.equals("id")){
					filterPart += "t1." + paramName + " =:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));					
				}
			}
		}
		filterPart += " ORDER BY t1.planSubmitTime ";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DbFollowPlan> findFollowPlanByFollowChildId(String followChildId){
		String hql = "from DbFollowPlan t where t.removed = '0' and t.followChildId = '"+followChildId+"' order by t.planFinishTime";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DbFollow> findDbFollowById(String id){
		String hql = "from DbFollow t where t.removed = '0' and t.id = '"+id+"' order by t.createTime";
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public boolean ifAllDeptDone(String parent_id){
		String sql = "select count(*) count_num from t_db_follow_child t where parent_id = '"+parent_id+"' and t.follow_state != '2' ";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("count_num", Hibernate.INTEGER);
		if((Integer)query.uniqueResult()==0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean ifFollowed(String dbId){
		String sql = "select count(*) count_num from t_db_follow t where t.db_id = ? and t.removed = '0'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("count_num", Hibernate.INTEGER).setString(0, dbId);
		if((Integer)query.uniqueResult()>0){
			return true;
		}else{
			return false;
		}
	}
}
