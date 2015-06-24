package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.dao;

import java.util.Map;

import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo.AddGroupMemberDirectly;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface AddGroupMemberDirectlyDao extends
		AbstractHibernateDao<AddGroupMemberDirectly> {

	public Page findAddGroupMemberDirectlyByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
