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

package com.wonders.stpt.hiddenDangersCorrect.dao.impl;

import com.wonders.stpt.hiddenDangersCorrect.dao.HiddenDangersCorrectDao;
import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HiddenDangersCorrect;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HiddenDangersCorrect实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HiddenDangersCorrectDaoImpl extends
		AbstractHibernateDaoImpl<HiddenDangersCorrect> implements
		HiddenDangersCorrectDao {
	public Page findHiddenDangersCorrectByPage(Map<String, Object> filter,
			int pageNo, int pageSize,String operateTypeId) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HiddenDangersCorrect t ";
		String countHql = "select count(*) from HiddenDangersCorrect t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("lines")){
					filterPart += "(t." + paramName + " like :lines_a or t." + paramName + " like :lines_b or t.lines like '%全路网%' )";
					args.add(new HqlParameter("lines_a", filter.get(paramName)+"%"));
					args.add(new HqlParameter("lines_b", "%,"+filter.get(paramName)+"%"));
				}else if(paramName.equals("dangersState")||paramName.equals("correctMethod")||paramName.equals("major")||paramName.equals("workDept")||paramName.equals("workPerson")||paramName.equals("inputDept")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("lastDateStart")){
					filterPart += "t.lastDate >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("lastDateEnd")){
					filterPart += "t.lastDate <= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("finishDateStart")){
					filterPart += "t.finishDate >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("finishDateEnd")){
					filterPart += "t.finishDate <= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("inputDateStart")){
					filterPart += "t.inputDate >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("inputDateEnd")){
					filterPart += "t.inputDate <= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("deptId")){
					if(filter.get("companyId")!=null){
						if ("1".equals(operateTypeId)) {
							filterPart += "t.inputDeptId = '" + filter.get(paramName)+"' ";
						}else if("2".equals(operateTypeId)){
							filterPart += "(t.inputDeptId = '" + filter.get(paramName) +"' or t.workDeptId like '%," +   
							              filter.get("companyId") + ",%'" + " or t.supervisionDeptId = '" + filter.get("companyId")+"') ";
							           //   " and (t.checkState = '2' or t.checkState = '3') ";
						}else if ("3".equals(operateTypeId)) {
							filterPart += "(t.inputDeptId = '" + filter.get(paramName) +"' or t.workDeptId like '%," +   
				              filter.get("companyId") + ",%'" + " or t.supervisionDeptId = '" + filter.get("companyId")+"') ";
						}else{
							//filterPart += " t.checkState = '2' ";
							filterPart += " 1=1 ";
						}
					}else{
						filterPart += " 1=1 ";
					}
					
				}else if(paramName.equals("workState")){
					filterPart += "t.workState = :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("companyId")){
					filterPart += " 1 = 1 ";
				}
				else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " ORDER BY t.createTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	public void updateCheckState(String check_state,String id){
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "update hidden_dangers_correct set check_state = '"+check_state+"' where id = '"+id+"'";
		session.createSQLQuery(sql).executeUpdate();
	}
	
	public List<Object[]> findForExport(Map<String, Object> filter,String operateTypeId){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		String sql = "select lines,dangersState,correctMethod,workPerson,supervisionDept,major,input_date," +
				"(case dangersClass when '1' then '设施设备' when '2' then '管理' when '3' then '规章制度'" +
			" when '4' then '现场作业' when '5' then '新线遗留' else '' end) dangersClass,last_date," +
			"inputDept,createPerson,(case workState when '1' then '已完成' when '2' then '整改中' else '' end) workState," +
			"finish_date,(case importance when '0' then '重大隐患' when '1' then '一般隐患'" +
			" when '2' then '较大隐患' else '' end) importance,workDept," +
			"(case checkState when '1' then '未审核' when '2' then '审核通过' when '3' then '返回修改' else '' end) checkState," +
			"remark from ("+
			"select t.lines,replace(t.dangers_state,'<br>','\n') dangersState,replace(t.correct_method,'<br>','\n') correctMethod,t.work_person workPerson,"+
			"t.supervision_dept supervisionDept,t.major,t.input_date,t.dangers_class dangersClass,"+
			"t.last_date,t.input_dept inputDept,t.create_person createPerson,t.work_state workState,"+
			"t.finish_date,t.importance,t.work_dept workDept,t.check_state checkState,replace(t.remark,'<br>','\n') remark," +
			"t.input_dept_id inputDeptId,t.work_dept_id workDeptId,t.supervision_dept_id supervisionDeptId "+
			" from hidden_dangers_correct t order by t.create_time desc "+
			" ) where 1=1 ";
		if (!filter.isEmpty()) {			
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {				
				String paramName = i.next();				
				if(paramName.equals("lines")){
					sql += " and (lines like '"+filter.get(paramName)+"%' or lines like '%,"+filter.get(paramName)+"%' or lines like '%全路网%') ";
				}else if(paramName.equals("major")||paramName.equals("workDept")||paramName.equals("workPerson")||paramName.equals("inputDept")){
					sql += " and "+paramName+" like '%"+filter.get(paramName)+"%' " ;										
				}else if(paramName.equals("lastDateStart")){
					sql += " and last_date >= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;										
				}else if(paramName.equals("lastDateEnd")){
					sql += " and last_date <= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;												
				}else if(paramName.equals("finishDateStart")){
					sql += " and finish_date >= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;										
				}else if(paramName.equals("finishDateEnd")){
					sql += " and finish_date <= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;												
				}else if(paramName.equals("inputDateStart")){
					sql += " and input_date >= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;										
				}else if(paramName.equals("inputDateEnd")){
					sql += " and input_date <= to_date('"+format.format(filter.get(paramName))+"','YYYY-MM-DD') " ;												
				}else if(paramName.equals("deptId")&&filter.get("companyId")!=null){
					if ("1".equals(operateTypeId)) {
						sql += " and inputDeptId = '"+filter.get(paramName)+"' " ;	
					}else if("2".equals(operateTypeId)){
						sql += " and (inputDeptId = '" + filter.get(paramName) +"' or workDeptId like '%," +   
						              filter.get("companyId") + ",%'" + " or supervisionDeptId = '" + filter.get("companyId")+
						        //      "') and (checkState = '2' or checkState = '3') ";
                                "') ";
					}else if ("3".equals(operateTypeId)) {
						sql += " and (inputDeptId = '" + filter.get(paramName) +"' or workDeptId like '%," +   
			              filter.get("companyId") + ",%'" + " or supervisionDeptId = '" + filter.get("companyId")+
								"') ";
					}
				}else if(paramName.equals("companyId")){
					sql += " and 1 = 1 ";
				}else{
					sql += " and "+paramName+" = '"+filter.get(paramName)+"' " ;	
				}		
			}			
		}
		Query query = session.createSQLQuery(sql).addScalar("lines", Hibernate.STRING).addScalar("dangersState", Hibernate.STRING)
					.addScalar("correctMethod", Hibernate.STRING).addScalar("workPerson", Hibernate.STRING)
					.addScalar("supervisionDept", Hibernate.STRING).addScalar("major", Hibernate.STRING)
					.addScalar("input_date", Hibernate.DATE).addScalar("dangersClass", Hibernate.STRING)
					.addScalar("last_date", Hibernate.DATE).addScalar("inputDept", Hibernate.STRING)
					.addScalar("createPerson", Hibernate.STRING).addScalar("workState", Hibernate.STRING)
					.addScalar("finish_date", Hibernate.DATE).addScalar("importance", Hibernate.STRING)
					.addScalar("workDept", Hibernate.STRING).addScalar("checkState", Hibernate.STRING)
					.addScalar("remark", Hibernate.STRING);
		return query.list();
	}
}
