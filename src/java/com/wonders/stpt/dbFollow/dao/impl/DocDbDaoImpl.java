package com.wonders.stpt.dbFollow.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wonders.stpt.dbFollow.dao.DocDbDao;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class DocDbDaoImpl extends AbstractHibernateDaoImpl<Object> implements DocDbDao {

	@SuppressWarnings("unchecked")
	@Override
	public Object[] findDocDbById(String id) {
		String sql = "select t.creat_memo,t.depement_zid,t.depement_z from t_doc_db t where t.id='"+id+"'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("creat_memo", Hibernate.STRING)
				.addScalar("depement_zid", Hibernate.STRING).addScalar("depement_z", Hibernate.STRING);
		List<Object[]> list = query.list();
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDeptByName(String name){
		String sql = "select t.id,t.name from cs_organ_node t,cs_organ_model t1 " +
				" where t.organ_node_type_id = 3 and t.removed = 0 and t1.parent_node_id = 2100 "+
				" and t.id = t1.org_node_id and t.name like '%"+name+"%'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("id", Hibernate.INTEGER).addScalar("name", Hibernate.STRING);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateDocDbById(String id, String followType) {
		String sql = "update t_doc_db t set t.follow_type='"+followType+"' where t.id='"+id+"'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateDocDbFollowStateById(String dbId) {
		String sql = "update t_doc_db t set t.follow_state=2 where t.id='"+dbId+"'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public String getDeptLeader(String dept_id){
		String sql = "select login_name from v_dept_leaders t where dept_id = '"+dept_id+"' and rownum < 2 ";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql).addScalar("login_name", Hibernate.STRING);
		return (String)query.uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(Collection col){
		this.getHibernateTemplate().saveOrUpdateAll(col);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void updateTodoItem(String id) {
		String sql = "update t_todo_item t set t.status = 1 where t.app = 'dbFollow' and t.data = '"+id+"'";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
}
