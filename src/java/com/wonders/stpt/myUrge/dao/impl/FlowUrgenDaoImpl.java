package com.wonders.stpt.myUrge.dao.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;


import com.wonders.stpt.myUrge.dao.FlowUrgenDao;
import com.wonders.stpt.myUrge.entity.bo.FlowUrgen;
import com.wonders.stpt.myUrge.entity.bo.VUrgeInfo;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class FlowUrgenDaoImpl extends AbstractHibernateDaoImpl<FlowUrgen>  implements FlowUrgenDao {
	
	protected Class getEntityClass() { 
		return FlowUrgen.class;
	}
	
	public Object insert(FlowUrgen flowUrgen){		
		super.save(flowUrgen);
		return flowUrgen.getGid();
	}
	
	public void update(FlowUrgen flowUrgen){
		super.update(flowUrgen);		
	}
	
	public FlowUrgen findById(String id){
		FlowUrgen bo=new FlowUrgen();
		if(id!=null&&id.length()>0){
			bo=(FlowUrgen)this.load(id);
		}
		return bo;
	}
	
	/*
	public String findByCode(String code){
		String codeNum="";
		try {
			ExecuteSql dealsql= new ExecuteSql();
			String sql="select * from t_doc_send t where t.send_id like '%"+code+"%' order by t.send_id ";
			System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				codeNum=rs.getString("SEND_ID");
			}
			dealsql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codeNum;
	}
	*/
	
	public List showFlowUrgenByCode(String hql) {
		// TODO Auto-generated method stub
		List it	= this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
		return it;
	}
	
	//添加催办查询信息
	public Map AddUrgeInfo(Map map){
		Map retMap = new HashMap();
		List list = null;
		String info = "";
		list = (ArrayList)map.get("list");
		String processName=StringUtil.getNotNullValueString(map.get("processName"));
		String incident=StringUtil.getNotNullValueString(map.get("incident"));
		if (list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map1 = (HashMap)list.get(i);
				String taskid = StringUtil.getNotNullValueString(map1.get("taskid"));
				String userid = StringUtil.getNotNullValueString(map1.get("userid"));
				
				String hql = "from FlowUrgen fu where fu.processname = '"+processName
							+"' and fu.incident = '"+incident
							+"' and fu.taskid = '"+taskid
							+"' and fu.urgened_user = '"+userid+"'" 
							+" order by fu.urgen_time desc";
				//System.out.println(hql);
				//System.out.println(processName+" "+incident+" "+taskid+" "+userid+" ");
				List flowUrgen_infos = showFlowUrgenByCode(hql);
				int infos_count = flowUrgen_infos.size();
				if(infos_count>0){
					//String link = "/stoa/flowUrgen/flowUrgenList.do?b_query=true"+"&processname="+processName+"&incident"+incident+"&taskid="+taskid;
					String link = "return openList('"+processName+"','"+incident+"','"+taskid+"','"+userid+"')";
					FlowUrgen fu = (FlowUrgen)flowUrgen_infos.get(0);
					info = "已被催办&nbsp;<b><font color='red'>"+infos_count+"</font></b>&nbsp;次<a href='' onclick=\""+link+"\">&nbsp;<b>查看</b></a><br>&nbsp;（最近催办："+fu.getSend_username()+" ["+this.dealWithDate(StringUtil.getNotNullValueString(fu.getUrgen_time()))+"]）";
					
					map1.put("info", info);
					//map1.put("link", "link");

				}else{
					info = "(未被催办)";
					map1.put("info", info);
					//map1.put("link", "");
				}
				
				
				//System.out.println(info);
				list.remove(i);
				list.add(i, map1);

			}
			map.put("list", list);
		}
		return map;
	}
	
	public String dealWithDate(String inputDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d1=new java.util.Date();
		
		Calendar d2 = Calendar.getInstance();
		Calendar d3 = Calendar.getInstance();
		d2.setTime(d1);
		d3.setTime(d1);
		
		d2.set(Calendar.DATE,d2.get(Calendar.DATE));
		d3.set(Calendar.DATE,d3.get(Calendar.DATE)-1);
		String now = formatter.format(d2.getTime());
		
		String previous = formatter.format(d3.getTime());
		
		inputDate = inputDate.replace(previous,"昨天");
		inputDate = inputDate.replace(now,"今天");
		
	
		return inputDate;
	}
	
	
public List<Object[]> findUrgeLogByPage(int startRow, int pageSize) {
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		String sql=getUgreLogListSql();
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}
	
	public int getUrgeLogCount(){
		String sql=getUgreLogListSql();
		sql="select count(*)  as count_num from ("+sql+")a";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer)query.uniqueResult();
	}
	
	
	public String getUgreLogListSql(){
		String sql="";
		String processname = PageUtils.GetParameter("processname");
		String incident = PageUtils.GetParameter("incident");
		String taskid = PageUtils.GetParameter("taskid");
		String userid = PageUtils.GetParameter("userid");
		
		sql = "select t.*,substr(t.urgen_time,1,10) as datetime,i.summary from t_msg_flowurgen t,incidents i where t.incident=i.incident and t.processname = i.processname"
			+" and t.processname='"+processname+"'" 
			+" and t.incident='"+incident+"'" 
			+" and t.taskid='"+taskid+"'" 
			+" and t.urgened_user='"+userid+"'";
		
		return sql;
		
	}

	
	
	
}
