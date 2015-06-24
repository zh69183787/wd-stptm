package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface AddUpdateGroupMemberDao extends
		AbstractHibernateDao<AddUpdateGroupMember> {
	
	public Page findAddUpdateGroupMemberByPage(Map<String, Object> filter,int pageNo,int pageSize,String type);
	
	public List<Object[]> countAll();
}
