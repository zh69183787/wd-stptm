package com.wonders.stpt.dbFollow.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowChild;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowPlan;
import com.wondersgroup.framework.core.bo.Page;

public interface DocDbService {
	public Object[] findDocDbById(String id);
	
	public void updateDocDbById(String id, String followType);
	
	public List<Object[]> findDeptByName(String name);
	
	public void save(Object obj);
	
	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(Collection col);
	
	@SuppressWarnings("unchecked")
	Object load(Serializable id, Class clazz);
	
	public List<DbFollowChild> findFollowChildByParentId(String parentId);

	public void update(Object obj);
	
	public Page findDbFollowByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public Page findDbFollowChildByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<DbFollowPlan> findFollowPlanByFollowChildId(String followChildId);

	/**
	 * 根据id查找DbFollow
	 * @param id
	 * @return
	 */
	public List<DbFollow> findDbFollowById(String id);
	
	/**
	 * 根据dbId更新DocDb的办结状态
	 * @param dbId
	 * @return
	 */
	public void updateDocDbFollowStateById(String dbId);
	
	public String getDeptLeader(String dept_id);
	
	public void saveOrUpdateAll2(Collection col);
	
	public void updateTodoItem(String id);
	
	public boolean ifAllDeptDone(String parent_id);
	
	public boolean ifFollowed(String dbId);
}
