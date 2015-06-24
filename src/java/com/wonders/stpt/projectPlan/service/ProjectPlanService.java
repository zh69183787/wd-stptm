package com.wonders.stpt.projectPlan.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.projectPlan.entity.bo.ProjectPlan;
import com.wondersgroup.framework.core.bo.Page;

public interface ProjectPlanService {
	public void saveOrUpdateAll(List<ProjectPlan> list);
	
	public Page findProjectPlanByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public void save(ProjectPlan entity);
	
	public void update(ProjectPlan entity);
	
	public ProjectPlan findById(String id);
}
