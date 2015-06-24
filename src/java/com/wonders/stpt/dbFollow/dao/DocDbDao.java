package com.wonders.stpt.dbFollow.dao;

import java.util.Collection;
import java.util.List;

import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface DocDbDao extends AbstractHibernateDao<Object>{
	public Object[] findDocDbById(String id);
	
	public List<Object[]> findDeptByName(String name);
	
	public void updateDocDbById(String id, String followType);
	
	/**
	 * 根据dbId更新DocDb的办结状态
	 * @param dbId
	 * @return
	 */
	public void updateDocDbFollowStateById(String dbId);
	
	public String getDeptLeader(String dept_id);
	
	public void saveOrUpdateAll(Collection col);
	
	public void updateTodoItem(String id);
	
	
}
