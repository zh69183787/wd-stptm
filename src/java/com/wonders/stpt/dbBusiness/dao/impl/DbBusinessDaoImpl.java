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

package com.wonders.stpt.dbBusiness.dao.impl;

import com.wonders.stpt.dbBusiness.dao.DbBusinessDao;
import com.wonders.stpt.dbBusiness.entity.bo.DbBusiness;
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

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2013-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class DbBusinessDaoImpl extends AbstractHibernateDaoImpl<DbBusiness>
		implements DbBusinessDao {

	public List<Object[]> findDbBusinessByPage(String orderValue,int startRow, int pageSize,
			String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow) {
		List<Object[]> list = new ArrayList<Object[]>();

		String sql = "select * from(select e.creat_memo,e.creat_time,e.dept_name,e.return_time,e.user_name,e.status, e.follow_state,e.follow_type,t5.endtime,e.id,e.flag,e.attribute,e.taskid " +
				"from (select c.*,d.cincident from" +
				"(select t1.id dept_id,t1.name dept_name,t2.name user_name,t6.status,t7.taskid,t.* from t_doc_db t,cs_organ_node t1,cs_user t2,incidents t6,tasks t7  " +
				"where instr(','||t.depement_zid||',',','||t1.id||',')>0 and t2.login_name = t.operator and t.removed=0 and t6.processname = '督办流程' and t6.incident = t.instant_id " +
				" and t7.incident = t.instant_id and t7.processname = '督办流程' and t7.steplabel = 'Begin' )" +
				" c left join (select t3.cincident,t3.pincident,t4.helpurl from t_subprocess t3,tasks t4 " +
				"where t3.pname = '督办流程' and t3.cname = '部门内部子流程' and t3.cincident = t4.incident and t4.processname = '部门内部子流程' and t4.steplabel = '部门接受人工作分发')d " +
				"on d.pincident = c.instant_id and instr(d.helpurl,':'||c.dept_id||'<')>0)e left join incidents t5 on t5.incident = e.cincident and t5.processname = '部门内部子流程' and t5.status = 2";
		sql += getOrders(orderValue);		
		sql += ") f where 1=1 ";
		sql = getDbBusinessFullSql(sql, creatememo, creatTimeStart, creatTimeEnd,
				deptName, userName, returnTimeStart,returnTimeEnd,manageTime,beyondDay,isEnd,isFollow);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	public int countDbBusiness(String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow) {
//		String sql = "from t_doc_db t,cs_organ_node t1 "
//				+ "where instr(','||t.depement_zid||',',','||t1.id||',')>0 "
//				+ "and t.removed=0";
		String sql = "from(select e.creat_memo,e.creat_time,e.dept_name,e.return_time,e.user_name,e.status, e.follow_state,e.follow_type,t5.endtime,e.taskid " +
		"from (select c.*,d.cincident from" +
		"(select t1.id dept_id,t1.name dept_name,t2.name user_name,t6.status,t7.taskid,t.* from t_doc_db t,cs_organ_node t1,cs_user t2,incidents t6,tasks t7  " +
		"where instr(','||t.depement_zid||',',','||t1.id||',')>0 and t2.login_name = t.operator and t.removed=0 and t6.processname = '督办流程' and t6.incident = t.instant_id " +
		" and t7.incident = t.instant_id and t7.processname = '督办流程' and t7.steplabel = 'Begin' order by to_number(t.instant_id) desc)" +
		" c left join (select t3.cincident,t3.pincident,t4.helpurl from t_subprocess t3,tasks t4 " +
		"where t3.pname = '督办流程' and t3.cname = '部门内部子流程' and t3.cincident = t4.incident and t4.processname = '部门内部子流程' and t4.steplabel = '部门接受人工作分发')d " +
		"on d.pincident = c.instant_id and instr(d.helpurl,':'||c.dept_id||'<')>0)e left join incidents t5 on t5.incident = e.cincident and t5.processname = '部门内部子流程' and t5.status = 2) f where 1=1 ";
		sql = getDbBusinessFullSql(sql,creatememo, creatTimeStart, creatTimeEnd,
				deptName, userName, returnTimeStart,returnTimeEnd,manageTime,beyondDay,isEnd,isFollow);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(
						"select count(*) count_num " + sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		// System.out.println("num==="+query.uniqueResult());
		// return (Integer) query.uniqueResult();
		return (Integer)query.uniqueResult();
	}

	public List<Object[]> findDbBusinessByIdAndDeptName(String id,String deptName) {
		List<Object[]> list = new ArrayList<Object[]>();

		String sql = "select * from(select e.creat_memo,e.creat_time,e.dept_name,e.return_time,e.user_name,e.status, e.follow_state,e.follow_type,t5.endtime,e.id,e.flag,e.attribute " +
				"from (select c.*,d.cincident from" +
				"(select t1.id dept_id,t1.name dept_name,t2.name user_name,t6.status,t.* from t_doc_db t,cs_organ_node t1,cs_user t2,incidents t6 " +
				"where instr(','||t.depement_zid||',',','||t1.id||',')>0 and t2.login_name = t.operator and t.removed=0 and t6.processname = '督办流程' and t6.incident = t.instant_id order by to_number(t.instant_id) desc)" +
				" c left join (select t3.cincident,t3.pincident,t4.helpurl from t_subprocess t3,tasks t4 " +
				"where t3.pname = '督办流程' and t3.cname = '部门内部子流程' and t3.cincident = t4.incident and t4.processname = '部门内部子流程' and t4.steplabel = '部门接受人工作分发')d " +
				"on d.pincident = c.instant_id and instr(d.helpurl,':'||c.dept_id||'<')>0)e left join incidents t5 on t5.incident = e.cincident and t5.processname = '部门内部子流程' and t5.status = 2) f where 1=1 " +
				"and f.id =  '" + id + "' and f.dept_name =  '" + deptName + "' ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.list();
		return list;
	} 
	
	
	
	public String getDbBusinessFullSql(String sql, String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow) {
		if (creatememo != null && !"".equals(creatememo)) {
			sql += " and f.creat_memo like '%" + creatememo + "%' ";
		}
		if (creatTimeStart != null && !"".equals(creatTimeStart)) {
			sql += " and f.creat_time >= '" + creatTimeStart + "' ";
		}
		if (creatTimeEnd != null && !"".equals(creatTimeEnd)) {
			sql += " and f.creat_time <= '" + creatTimeEnd + "' ";
		}
		if (deptName != null && !"".equals(deptName)) {
			sql += " and f.dept_name like '%" + deptName + "%' ";
		}
		
		if (userName != null && !"".equals(userName)) {
			sql += " and f.user_name like '%" + userName + "%' ";
		}
		if (returnTimeStart != null && !"".equals(returnTimeStart)) {
			sql += " and f.return_time >= '" + returnTimeStart + "' ";
		}
		if (returnTimeEnd != null && !"".equals(returnTimeEnd)) {
			sql += " and f.return_time <= '" + returnTimeEnd + "' ";
		}
//		if (manageTime != null && !"".equals(manageTime)) {
//			sql += " and f.manageTime like '%" + manageTime + "%' ";
//		}
//		
//		if (beyondDay != null && !"".equals(beyondDay)) {
//			sql += " and beyondDay like '%" + beyondDay + "%' ";
//		}
		if (isEnd != null && !"".equals(isEnd)) {
			sql += " and follow_state = '" + isEnd + "' ";
		}
		if (isFollow != null && !"".equals(isFollow)) {
			sql += " and follow_type = '" + isFollow + "' ";
		}
		
		return sql;
	}

	@Override
	public void saveAdd(DbBusiness assetTask) {
		getHibernateTemplate().save(assetTask);
	}

	@Override
	public DbBusiness findTaskById(String id) {
		String hql = "from DbBusiness t where t.id='" + id + "'";
		return (DbBusiness) getHibernateTemplate().get(DbBusiness.class, id);
	}

	@Override
	public void updateTask(DbBusiness assetTask) {
		getHibernateTemplate().update(assetTask);
	}

	@Override
	public void deleteTaskById(String id) {
		String hql = "delete from DbBusiness t where t.id='" + id + "'";
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery(hql).executeUpdate();
		tx.commit();
		session.close();
	}

	@Override
	public List<DbBusiness> findAllTask() {
		List<DbBusiness> list = new ArrayList<DbBusiness>();
		SQLQuery query = this
				.getSession()
				.createSQLQuery(
						"select distinct checkpersonlist from t_asset_task t where t.removed='0'");
		query.addScalar("checkpersonlist", Hibernate.STRING);
		list = query.setResultTransformer(
				Transformers.aliasToBean(DbBusiness.class)).list();
		return list;
	}

	private String getOrders(String orderValue){
		String sql = "";
		switch (Integer.valueOf(orderValue)){
			case 1:{sql = " order by creat_time ";break;}
			case 2:{sql = " order by creat_time desc ";break;}
			case 3:{sql = " order by return_time ";break;}
			case 4:{sql = " order by return_time desc ";break;}
			case 5:{sql = " order by (case when t5.endtime is null then sysdate else t5.endtime end)-to_date(creat_time,'yyyy-mm-dd') ";break;}
			case 6:{sql = " order by (case when t5.endtime is null then sysdate else t5.endtime end)-to_date(creat_time,'yyyy-mm-dd') desc";break;}
			case 7:{sql = " order by (case when t5.endtime is null then sysdate else t5.endtime end)-to_date(creat_time,'yyyy-mm-dd')-(to_date(return_time,'yyyy-mm-dd')-to_date(creat_time,'yyyy-mm-dd')) ";break;}
			case 8:{sql = " order by (case when t5.endtime is null then sysdate else t5.endtime end)-to_date(creat_time,'yyyy-mm-dd')-(to_date(return_time,'yyyy-mm-dd')-to_date(creat_time,'yyyy-mm-dd')) desc ";break;}
			case 9:{sql = " order by dept_name ";break;}
			default:{sql = "";break;}
		}
		return sql;
	}
}
