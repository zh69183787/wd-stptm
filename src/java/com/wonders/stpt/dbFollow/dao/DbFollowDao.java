package com.wonders.stpt.dbFollow.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowChild;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowPlan;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface DbFollowDao extends AbstractHibernateDao<Object>{
	@SuppressWarnings("unchecked")
	public void saveOrUpdateAll(Collection col);
	
	@SuppressWarnings("unchecked")
	public Object load(Serializable id,Class clazz);
	
	public List<DbFollowChild> findFollowChildByParentId(String parentId);
	
	public Page findDbFollowByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public Page findDbFollowChildByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<DbFollowPlan> findFollowPlanByFollowChildId(String followChildId);

	public List<DbFollow> findDbFollowById(String id);
	
	public boolean ifAllDeptDone(String parent_id);
	
	public boolean ifFollowed(String dbId);
}
