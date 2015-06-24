package com.wonders.stpt.projectPlan.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.projectPlan.dao.ProjectPlanDao;
import com.wonders.stpt.projectPlan.entity.bo.ProjectPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class ProjectPlanDaoImpl extends AbstractHibernateDaoImpl<ProjectPlan> implements ProjectPlanDao{
	public void saveOrUpdateAll(List<ProjectPlan> list){
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}
	
	public Page findProjectPlanByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from ProjectPlan t ";
		String countHql = "select count(*) from ProjectPlan t ";
		String filterPart = "";
		int filterCounter = 0;
		filterPart += " where t.removed = '0' ";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("planProjectName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		filterPart += " ORDER BY t.operateTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
}
