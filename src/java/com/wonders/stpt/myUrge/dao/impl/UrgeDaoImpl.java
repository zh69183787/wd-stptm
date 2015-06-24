package com.wonders.stpt.myUrge.dao.impl;



import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;



import com.wonders.stpt.myUrge.dao.UrgeDao;
import com.wonders.stpt.myUrge.entity.bo.UrgeInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Removable;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.core5.dao.support.Filters;
import com.wondersgroup.framework.core5.dao.support.QueryCallBack;

import com.wondersgroup.framework.core5.storeprocedure.SpParameters;
import com.wondersgroup.framework.core5.storeprocedure.bean.SpSupportBean;

public class UrgeDaoImpl extends AbstractHibernateDaoImpl<UrgeInfo> implements UrgeDao{

	public List getUrgeList(String userName){
		String sName = "ST/"+userName;
		String sHql = "from TApprovedinfo t where t.username ='"+sName+"'";
        sHql += " order by t.incidentno";
		List list = this.getHibernateTemplate().find(sHql);
		return list;
	}
	
	public Page getUrgePage(String userName,int start,int limit) {
		String sName = userName;
		String sql = "select count(*) from ((select part1.*,part2.name,part2.mobile1 from " +
		"((select * from v_urge_info where username = 'ST/"+sName+"') part1 " +
		"join (select login_name,name,mobile1  from v_userdep) part2 on substr(part1.taskuser,4) = part2.login_name )) " +
		"part3 join (select login_name ,name as apply_user,deptName from v_userdep) part4 on trim(substr(part3.initiator,4)) = part4.login_name)";
					
		List list = new ArrayList();
		Connection conn = this.getSession().connection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UrgeInfo ui = new UrgeInfo();
				ui.setProcessname(rs.getString("processname"));
				ui.setTaskid(rs.getString("taskid"));
				ui.setIncident(rs.getInt("incident"));
				ui.setSteplabel(rs.getString("steplabel"));
				ui.setTaskuser(rs.getString("taskuser"));
				ui.setPname(rs.getString("pname"));
				ui.setPincident(rs.getInt("pincident"));
				ui.setSummary(rs.getString("summary"));
				ui.setInitiator(rs.getString("initiator"));
				ui.setPtaskid(rs.getString("ptaskid"));
				ui.setOverduetime(rs.getDate("overduetime").toString());
				ui.setFormType(rs.getString("formType"));
				list.add(ui);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		Page page = new Page();
		page.setResult(list);
		page.setCurrentPageNo(start);
		page.setCurrentPageSize(limit);
		page.setTotalSize(list.size());
        return page;
	}
	
	public Map urgeLeaders(String processName, String incident){
		Map map1 = new HashMap();
		List list = null;
	
		String sql="select distinct a.taskid as taskid," +
				" a.taskuser as taskuser," +
				" b.summary as summary," +
				" e.name as name," +
				" vdl.id as userid," +
				" vdl.name as leader_name," +
				" vdl.mobile_leader as mobile1" +
				" from tasks a, incidents b, v_userdep e, v_dept_leaders vdl" +
				" where vdl.Dept_id = e.parent_node_id" +
				" and e.removed = 0" +
				" and a.status = 1" +
				" and a.substatus = 3" +
				" and (exists (select '' from t_subprocess ts" +
				" where ts.pname = '"+processName+"'" +
				" and ts.pincident = '"+incident+"'" +
				" and a.processname = ts.cname" +
				" and to_char(a.incident) = ts.cincident) or" +
				" a.processname = '"+processName+"' and to_char(a.incident) = '"+incident+"')" +
				" and UPPER(a.taskuser) = 'ST/' || UPPER(e.login_name)" +
				" and a.incident = b.incident" +
				" and a.processname = b.processname" +
				" and a.taskuser like 'ST/%'" ;
		
		/**String sql="select distinct a.taskid as taskid," +
		" a.taskuser as taskuser," +
		" b.summary as summary," +
		" e.name as name," +
		" vdl.id as userid," +
		" vdl.name as leader_name," +
		" vdl.mobile_leader as mobile1" +
		" from tasks a, incidents b, v_userdep e, v_dept_leaders vdl" +
		" where vdl.Dept_id = e.parent_node_id" +
		" and e.removed = 0" +
		" and a.status = 1" +
		" and a.substatus = 3" +
		" and (exists (select '' from t_subprocess ts" +
		" where  a.processname = ts.cname" +
		" and to_char(a.incident) = ts.cincident) or" +
		" a.processname = '"+processName+"' and to_char(a.incident) = '"+incident+"')" +
		" and UPPER(a.taskuser) = 'ST/' || UPPER(e.login_name)" +
		" and a.incident = b.incident" +
		" and a.processname = b.processname" +
		" and a.taskuser like 'ST/%'" ;**/
		
				
				

		//SQLQuery query = this.getSession().createSQLQuery(sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory()
		.getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("taskid", Hibernate.STRING);
		query.addScalar("taskuser", Hibernate.STRING);
		query.addScalar("summary", Hibernate.STRING);
		query.addScalar("userid", Hibernate.STRING);
		query.addScalar("name", Hibernate.STRING);
		query.addScalar("leader_name", Hibernate.STRING);
		query.addScalar("mobile1", Hibernate.STRING);
		String summary = null;
		List temp = query.list();
		if(temp != null && temp.size() >0){
			
			list = new ArrayList();
			Iterator iter = temp.iterator();
			while(iter.hasNext()){
				Object[] objs = (Object[]) iter.next();
				Map map =  new HashMap();
				
				String id = (String) objs[0];
				String user = (String) objs[1];
				summary = (String) objs[2];
				String userid = (String) objs[3];
				String name = (String) objs[4];
				String leaderName = (String) objs[5];
				String mobile1 = (String) objs[6];
				map.put("taskid", id);
				map.put("taskuser", user);
				//map.put("summary", summary);
				map.put("userid", userid);
				map.put("name", name);
				map.put("mobile1", mobile1);
				map.put("leaderName", leaderName);
				list.add(map);
			}
			//map1.put(summary, list);
			map1.put("list", list);
			map1.put("summary", summary);
		}
		
		map1.put("processName", processName);
		map1.put("incident", incident);
		return map1;
	}
	
	public Map urgePersons(String processName, String incident){
		Map map1 = new HashMap();
		List list = null;
		/*
		String sql=" select distinct a.taskid          as taskid, "+
					" a.taskuser        as taskuser, "+
					" b.summary         as summary, "+
					" e.name            as name, "+
					" vdl.name          as leader_name, "+
					" vdl.mobile_leader as mobile1 "+
					" from tasks a, incidents b, v_userdep e, v_dept_leaders vdl "+
					" where exists "+
					" (select '' "+
					" from t_subprocess ts "+
					" where ts.pname = '"+processName+"' "+
					" and ts.pincident = '"+incident+"' "+
					" and ((a.processname = ts.cname and "+
					" to_char(a.incident) = ts.cincident) or "+
					" (a.processname = '"+processName+"' and to_char(a.incident) = '"+incident+"'))) "+
					" and a.status = 1 "+
					" and a.substatus = 3 "+
					" and a.incident = b.incident "+
					" and a.processname = b.processname "+
					" and (UPPER(a.taskuser) = 'ST/' || UPPER(e.login_name)) "+
					" and e.removed = 0 "+
					" and vdl.Dept_id = e.parent_node_id";
					*/
		String sql="select a.taskid as taskid,a.taskuser as taskuser ,b.summary as summary, e.id as userid,e.name as name,e.mobile1 as mobile1 "
			  +" from tasks a,incidents b,cs_user e where "
			  
			  +" (trim(a.processname),trim(a.incident)) in (" 
			  +" (select '"+processName+"','"+incident+"' from dual) union"
			  +" (select trim(ts.cname),ts.cincident from t_subprocess ts where ts.pname='"+processName+"' and ts.pincident='"+incident+"')"
			  +" )"
			  
			  +" and a.status = 1 and a.incident = b.incident"
			  +" and trim(a.processname) = trim(b.processname)"
			  +" and (UPPER(a.taskuser) = 'ST/' || UPPER(e.login_name))"
			  +" and e.removed != 1";

		//SQLQuery query = this.getSession().createSQLQuery(sql);
		
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory()
		.getCurrentSession().createSQLQuery(sql);
		
		query.addScalar("taskid", Hibernate.STRING);
		query.addScalar("taskuser", Hibernate.STRING);
		query.addScalar("summary", Hibernate.STRING);
		query.addScalar("userid", Hibernate.STRING);
		query.addScalar("name", Hibernate.STRING);
		//query.addScalar("leader_name", Hibernate.STRING);
		query.addScalar("mobile1", Hibernate.STRING);
		String summary = null;
		List temp = query.list();
		if(temp != null && temp.size() >0){
			
			list = new ArrayList();
			Iterator iter = temp.iterator();
			while(iter.hasNext()){
				Object[] objs = (Object[]) iter.next();
				Map map =  new HashMap();
				summary = (String) objs[2];
				String id = (String) objs[0];
				String user = (String) objs[1];
				String name = (String) objs[4];
				String userid = (String) objs[3];
				String mobile1 = (String) objs[5];
				//String leaderName = (String) objs[4];
				map.put("taskid", id);
				map.put("taskuser", user);
				//map.put("summary", summary);
				map.put("userid", userid);
				map.put("name", name);
				map.put("mobile1", mobile1);
				//map.put("leaderName", leaderName);
				list.add(map);
			}
			map1.put("list", list);
			map1.put("summary", summary);
			
		}
		map1.put("processName", processName);
		map1.put("incident", incident);
		
		
		
		
		return map1;
	}
	@Override
	public void bulkUpdate(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int countByCriteria(DetachedCriteria arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int countByHQL(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int countByHQL(String arg0, List arg1) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List findByCriteria(DetachedCriteria arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByCriteriaWithPage(DetachedCriteria arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByCriteriaWithPage(Criteria arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByCriteriaWithPage(DetachedCriteria arg0, int arg1,
			int arg2, boolean arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByCriteriaWithPage(Criteria arg0, int arg1, int arg2,
			boolean arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findByHQL(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findByHQL(String arg0, List arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByHQLWithPage(String arg0, int arg1, int arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByHQLWithPage(String arg0, List arg1, int arg2, int arg3,
			String arg4) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByHQLWithPage(String arg0, List arg1, int arg2, int arg3,
			String arg4, boolean arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getSequenceNextValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UrgeInfo loadWithLazy(Serializable arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page query(Page arg0, Filters arg1, Sorts arg2, QueryCallBack arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveOrUpdate(UrgeInfo arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(UrgeInfo arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteById(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object executeStoreFunction(String arg0, Class arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void executeStoreProcedure(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeStoreProcedure(String arg0, SpSupportBean arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object[] executeStoreProcedure(String arg0, SpParameters arg1,
			Object... arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findAll(Sorts arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findAllWithPage(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findBy(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findBy(Map arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findBy(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findBy(Map arg0, Sorts arg1, Boolean arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findBy(String arg0, Object arg1, Sorts arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findByLike(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List findByLike(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByWithPage(Map arg0, Map arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page findByWithPage(Map arg0, Map arg1, int arg2, int arg3,
			boolean arg4) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UrgeInfo findUniqueBy(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UrgeInfo load(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void remove(Removable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeById(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save(UrgeInfo arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(UrgeInfo arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUrgeCount(String userName, String mainDeptId,
			String nowDeptId, boolean falg) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
