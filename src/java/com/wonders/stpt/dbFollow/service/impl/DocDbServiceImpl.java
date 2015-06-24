package com.wonders.stpt.dbFollow.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.dbFollow.dao.DbFollowDao;
import com.wonders.stpt.dbFollow.dao.DocDbDao;
import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowChild;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowPlan;
import com.wonders.stpt.dbFollow.service.DocDbService;
import com.wondersgroup.framework.core.bo.Page;

public class DocDbServiceImpl implements DocDbService {
	private DocDbDao docDbDao;
	private DbFollowDao dbFollowDao;
	
	public void setDocDbDao(DocDbDao docDbDao) {
		this.docDbDao = docDbDao;
	}

	public void setDbFollowDao(DbFollowDao dbFollowDao) {
		this.dbFollowDao = dbFollowDao;
	}

	@Override
	public Object[] findDocDbById(String id) {
		return docDbDao.findDocDbById(id);
	}
	
	@Override
	public void updateDocDbById(String id, String followType){
		docDbDao.updateDocDbById(id,followType);
	}
	
	@Override
	public List<Object[]> findDeptByName(String name){
		return docDbDao.findDeptByName(name);
	}
	
	@Override
	public void save(Object obj){
		dbFollowDao.save(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdateAll(Collection col) {
		dbFollowDao.saveOrUpdateAll(col);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object load(Serializable id,Class clazz){
		return dbFollowDao.load(id, clazz);
	}

	@Override
	public List<DbFollowChild> findFollowChildByParentId(String parentId){
		return dbFollowDao.findFollowChildByParentId(parentId);
	}
	
	@Override
	public void update(Object obj){
		dbFollowDao.update(obj);
	}
	
	@Override
	public Page findDbFollowByPage(Map<String, Object> filter,
			int pageNo, int pageSize){
		return dbFollowDao.findDbFollowByPage(filter, pageNo, pageSize);
	}
	
	@Override
	public Page findDbFollowChildByPage(Map<String, Object> filter,
			int pageNo, int pageSize){
		return dbFollowDao.findDbFollowChildByPage(filter, pageNo, pageSize);
	}
	
	@Override
	public List<DbFollowPlan> findFollowPlanByFollowChildId(String followChildId){
		return dbFollowDao.findFollowPlanByFollowChildId(followChildId);
	}
	@Override
	public List<DbFollow> findDbFollowById(String id){
		return dbFollowDao.findDbFollowById(id);
	}
	
	@Override
	public void updateDocDbFollowStateById(String dbId){
		docDbDao.updateDocDbFollowStateById(dbId);
	}
	
	@Override
	public String getDeptLeader(String dept_id){
		return docDbDao.getDeptLeader(dept_id);
	}
	
	@Override
	public void saveOrUpdateAll2(Collection col){
		docDbDao.saveOrUpdateAll(col);
	}
	
	@Override
	public void updateTodoItem(String id){
		docDbDao.updateTodoItem(id);
	}
	
	@Override
	public boolean ifAllDeptDone(String parent_id){
		return dbFollowDao.ifAllDeptDone(parent_id);
	}
	
	public boolean ifFollowed(String dbId){
		return dbFollowDao.ifFollowed(dbId);
	}
}
