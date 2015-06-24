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

package com.wonders.stpt.myUrge.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.components.Autocompleter;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;


import com.wonders.stpt.myUrge.dao.VMyUrgeDao;
import com.wonders.stpt.myUrge.entity.bo.VUrgeInfo;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.security.bo.SecurityUser;

/**
 * TFlowEvaluationʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class VMyUrgeDaoImpl extends
		AbstractHibernateDaoImpl<VUrgeInfo> implements VMyUrgeDao {
	public String countsql="";
	public List<Object[]> findVMyUrgeByPage(HttpServletRequest rs,
			int startRow, int pageSize) {
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		String sql=getSql(rs);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}
	public String  getSql(HttpServletRequest rs){
		
		String user=AuthorUtil.GetLoginUserName();
		if(user.length()>12){
			user = user.substring(0,12);
		}
		String user_dept_id =AuthorUtil.GetLoginDeptID();
		SecurityUser loginUser = AuthorUtil.GetLoginUser();
		String mainDeptId  =loginUser.getExt5();
		List map = this.getLoginNameDeptId(user);
		String deptSql = "";
	/*	if(map!=null && map.size()>1){
			int intTemp=0;
			try {
				intTemp = this.getDeptLevel(user_dept_id);
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
//			if((mainDeptId==null)==false){//如果有主部门
			if(mainDeptId!=null && !"".equals(mainDeptId) &&  !"null".equals(mainDeptId)){//如果有主部门		
				
				int intTempMain=0;
				try {
					intTempMain = this.getDeptLevel(mainDeptId);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if(mainDeptId.equals(user_dept_id)){//如果主部门是当前登陆部门
					if(intTemp == 1)//当前登陆部门集团部门
						deptSql = " and (instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + user + ":<+>') >0) ";
					else if(intTemp > 1)//如果当前登陆部门不是集团部门
						deptSql = " and instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 ";
					else
						deptSql = " and 1<>1 ";
				}else{//如果主部门不是当前登陆部门

					if(intTempMain == 1){//如果主部门是集团部门
						deptSql = " and instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 ";
					}else if(intTempMain > 1){//如果主部门不是集团部门

						if(intTemp > 1){//如果当前登陆部门不是集团部门
							deptSql = " and instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 ";
						}else if(intTemp == 1){
							deptSql = " and (instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + user + ":<+>') >0) ";
						}else{
							//deptSql = " and 1<>1 ";
						}
					}else{
						//deptSql = " and 1<>1 ";
					}
				}
			}else{//如果没有主部门


				if(intTemp > 1){//如果当前登陆部门不是集团部门
					deptSql = " and instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 ";
				}else if(intTemp == 1){
					deptSql = " and (instr(t.helpurl,'ST/" + user + ":"+user_dept_id+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + user + ":<+>') >0) ";
				}else{
					deptSql = " and 1<>1 ";
				}
			}
			
		}*/
			
		

		String sql = " select part3.* from ( select part1.processname, "+
				" part1.incident, "+
				" i.summary, "+
				" i.initiator, "+
	        	" i.priorities, "+
	        	" decode(i.priorities,'急件','<font color=\"red\"><b>急件</b></font>','') as priorities_show, "+
				" (select name "+
				" from cs_user csu "+
				" where removed = 0 "+
				" and 'ST/' || csu.login_name = i.initiator) as username "+
				" FROM ((select t.processname, to_char(t.incident) incident "+
				" from tasks t "+
				" where exists (select '' "+
				" from processes e "+
				" where T.processname = e.processname "+
				" and e.launchtype = 0 "+
				" and e.processname <> '拟办子流程' "+
				" AND e.processname <> '办结子流程') "+
				" and t.substatus = 3 " +
				" AND T.RECIPIENTTYPE<>19 "+
				" and t.status = 1) union "+
				" (select a.pname, a.pincident "+
				" from t_subprocess a, "+
				" (select c.processname, c.incident "+
				" from tasks c "+
				" where not exists (select '' "+
				" from processes b "+
				" where c.processname = b.processname "+
				" and b.launchtype = 0 "+
				" and b.processname <> '拟办子流程' "+
				" AND b.processname <> '办结子流程') "+
				" and c.substatus = 3 " +
				" AND c.RECIPIENTTYPE<>19 "+
				" and c.status = 1) d "+
				" where d.processname = a.cname "+
				" and to_char(d.incident) = a.cincident)) PART1, "+
				" ((select t.processname processname, to_char(t.incident) incident "+ //--经历的主流程 
				" from tasks t "+
				" where t.assignedtouser = 'ST/"+user+"' "+
				deptSql+
				" AND EXISTS (select '' "+
				" from processes a "+
				" where T.processname = a.processname "+
				" and a.launchtype = 0 "+
				" and a.processname <> '拟办子流程' "+
				" AND a.processname <> '办结子流程') "+
				" AND t.status = 3 " +
				" AND T.RECIPIENTTYPE<>19 ) union "+
				" (select b.pname processname, b.pincident incident "+ // --经历的子流程
				" from T_SUBPROCESS b, tasks t "+
				" where b.cname = t.processname "+
				" and b.cincident = t.incident "+
				" and t.assignedtouser = 'ST/"+user+"' "+
				deptSql+
				" AND NOT EXISTS (select '' "+
				" from processes a "+
				" where T.processname = a.processname "+
				" and a.launchtype = 0 "+
				" and a.processname <> '拟办子流程' "+
				" AND a.processname <> '办结子流程') "+
				" AND t.status = 3 AND T.RECIPIENTTYPE<>19) "+
				" ) PART2, "+
				" incidents i "+
				" where part1.processname = part2.processname "+
				" and part1.incident = part2.incident "+
				" and part1.processname = i.processname "+
				" and part1.incident = to_char(i.incident) ) part3 ";
		//String sql = "select part3.*  from ( select part1.processname,  part1.incident,  i.summary,  (select h.name  from cs_user_organnode g, cs_organ_node h, cs_organ_model j  where g.security_user_id =  (select id  from cs_user csu  where removed = 0  and 'ST/' || csu.login_name = i.initiator)  and j.org_node_id = g.organ_node_id  and j.org_tree_id = '1040'  and j.parent_node_id = h.id  and rownum = 1) as deptname,  (select name  from cs_user csu  where removed = 0  and 'ST/' || csu.login_name = i.initiator) as username  FROM ((select t.processname, to_char(t.incident) incident  from tasks t  where exists (select ''  from processes e  where T.processname = e.processname  and e.launchtype = 0  and e.processname <> '拟办子流程'  AND e.processname <> '办结子流程')  and t.substatus = 3  and t.status = 1 AND t.RECIPIENTTYPE<>19) union  (select a.pname, a.pincident  from t_subprocess a,  (select c.processname, c.incident  from tasks c  where not exists (select ''  from processes b  where c.processname = b.processname  and b.launchtype = 0  and b.processname <> '拟办子流程'  AND b.processname <> '办结子流程')  and c.substatus = 3  and c.status = 1 AND c.RECIPIENTTYPE<>19) d  where d.processname = a.cname  and to_char(d.incident) = a.cincident)) PART1,  ((select t.processname processname, to_char(t.incident) incident  from tasks t  where t.assignedtouser = 'ST/" + user + "' " + deptSql + " AND EXISTS (select '' " + " from processes a " + " where T.processname = a.processname " + " and a.launchtype = 0 " + " and a.processname <> '拟办子流程' " + " AND a.processname <> '办结子流程') " + " AND t.status = 3 AND t.RECIPIENTTYPE<>19) union " + " (select b.pname processname, b.pincident incident " + " from T_SUBPROCESS b, tasks t " + " where b.cname = t.processname " + " and b.cincident = t.incident " + " and t.assignedtouser = 'ST/" + user + "' " + deptSql + " AND NOT EXISTS (select '' " + " from processes a " + " where T.processname = a.processname " + " and a.launchtype = 0 " + " and a.processname <> '拟办子流程' " + " AND a.processname <> '办结子流程') " + " AND t.status = 3 AND t.RECIPIENTTYPE<>19) " + " ) PART2, " + " incidents i " + " where part1.processname = part2.processname " + " and part1.incident = part2.incident " + " and part1.processname = i.processname " + " and part1.incident = to_char(i.incident) ) part3" + " where " + " part3.processname || ':' || part3.incident not in (select tfuf.processname || ':' || tfuf.incidents from  " + " t_flow_urge_filter tfuf where tfuf .removed = 0) ";
		String urge = rs.getParameter("urge");

		if(urge==null){
			sql += " where part3.processname || ':' || part3.incident not in (select tfuf.processname || ':' || tfuf.incidents from  t_flow_urge_filter tfuf where tfuf .removed = 0) ";
		}else{
			sql += " where part3.processname || ':' || part3.incident in (select tfuf.processname || ':' || tfuf.incidents from  t_flow_urge_filter tfuf where tfuf .removed = 0) ";
		}
		sql = "select main.*,deptInfo.deptName from (("
	    		+sql
	    		+ ") main left join  ( select ta.processname, ta.incident,"
	    		+ "(select co.name from cs_organ_node co where replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = co.id ) as deptName "
	    		+ " from tasks ta, (select distinct processname, processversion, steplabel from processsteps where stepid like '%B' ) tb "
	    		+ " where ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
	    		+ ")deptInfo on(main.processname = deptInfo.processname and main.incident = to_char(deptInfo.incident)))" ;
		sql="select * from ("+sql+") where 1=1";
		//查询条件
		String priorities = rs.getParameter("vUrgeInfo.priorities");
		String processname = rs.getParameter("vUrgeInfo.processname");
		String summary = rs.getParameter("vUrgeInfo.summary");
		String username = rs.getParameter("vUrgeInfo.username");
		String deptname = rs.getParameter("vUrgeInfo.deptname");
		if(priorities!=null && priorities.length()>0){
			sql+=" and priorities like '%"+priorities+"%'";
		}
		if(processname!=null && processname.length()>0){
			sql+=" and processname like '%"+processname+"%'";
		}
		if(summary!=null && summary.length()>0){
			sql+=" and summary like '%"+summary+"%'";
		}
		if(username!=null && username.length()>0){
			sql+=" and username like '%"+username+"%'";
		}
		if(deptname!=null && deptname.length()>0){
			sql+=" and deptname like '%"+deptname+"%'";
		}
		return sql;
	}
	public int getCountMyUrge(HttpServletRequest rs) throws SQLException{
		String sql=getSql(rs);
		sql="select count(*)  as count_num from ("+sql+")a";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer)query.uniqueResult();
	}
	public List<VUrgeInfo> findVMyUrgeById(String flowId){
		//String hql ="from TFlowEvaluationItem t where t.flowId='"+flowId+"' and t.removed='1' order by t.sortingOrder ASC";
		//return getHibernateTemplate().find(hql);
		return null;
	}
	public int getDeptLevel(String deptId) throws SQLException{
		
		if(deptId==null)
			return 0;
		
		String sql = " select  level "+
			"  from cs_organ_model m "+
			" inner join cs_organ_node n1 on m.org_node_id = n1.id "+
			" inner join cs_organ_node n2 on m.parent_node_id = n2.id "+
			" where n1.id = "+deptId+" "+
			" connect by prior n1.id = n2.id ";
		
		Object obj= CommonDao.GetNewDatabaseDao().fetchColumnWithNewSession(sql);
		if(obj!=null){
			return Integer.parseInt(obj.toString());
		}
		
		return 0;
	}
	
	
	private  List getLoginNameDeptId(String loginname){
		List list = new ArrayList();
		String sql = " select t.parent_node_id,t.deptName from v_userdep t WHERE t.REMOVED = 0 and t.LOGIN_NAME = '"+loginname+"' order by t.deptOrders";
		list   =CommonDao.GetOldDatabaseDao().fetchAll(sql);
		return list   ;
	}
	
	
	
	
	
}
