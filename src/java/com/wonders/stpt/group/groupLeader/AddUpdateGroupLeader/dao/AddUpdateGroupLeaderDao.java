package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.dao;

import java.util.Map;

import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo.AddUpdateGroupLeader;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface AddUpdateGroupLeaderDao extends
		AbstractHibernateDao<AddUpdateGroupLeader>  {
	public Page findAddUpdateGroupLeaderByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
