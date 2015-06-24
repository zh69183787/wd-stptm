package com.wonders.stpt.projectPlan.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.projectPlan.dao.ProjectPlanDao;
import com.wonders.stpt.projectPlan.entity.bo.ProjectPlan;
import com.wonders.stpt.projectPlan.service.ProjectPlanService;
import com.wondersgroup.framework.core.bo.Page;

public class ProjectPlanServiceImpl implements ProjectPlanService{
	private ProjectPlanDao projectPlanDao;
	
	public void setProjectPlanDao(ProjectPlanDao projectPlanDao){
		this.projectPlanDao = projectPlanDao;
	}
	
	public void saveOrUpdateAll(List<ProjectPlan> list){
		projectPlanDao.saveOrUpdateAll(list);
	}
	
	public Page findProjectPlanByPage(Map<String, Object> filter,
			int pageNo, int pageSize){
		return projectPlanDao.findProjectPlanByPage(filter, pageNo, pageSize);
	}
	
	public void save(ProjectPlan entity){
		projectPlanDao.save(entity);
	}
	
	public void update(ProjectPlan entity){
		projectPlanDao.update(entity);
	}
	
	public ProjectPlan findById(String id){
		return projectPlanDao.load(id);
	}
}
