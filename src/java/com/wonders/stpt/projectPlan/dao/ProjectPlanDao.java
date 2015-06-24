package com.wonders.stpt.projectPlan.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.projectPlan.entity.bo.ProjectPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface ProjectPlanDao extends AbstractHibernateDao<ProjectPlan> {
	public void saveOrUpdateAll(List<ProjectPlan> list);
	
	public Page findProjectPlanByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
}
