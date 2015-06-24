package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.service;

import java.util.Map;

import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo.AddGroupMemberDirectly;
import com.wondersgroup.framework.core.bo.Page;

public interface AddGroupMemberDirectlyService {

	public void addAddGroupMemberDirectly(AddGroupMemberDirectly addGroupMemberDirectly);

	public void deleteAddGroupMemberDirectly(AddGroupMemberDirectly addGroupMemberDirectly);
	
	public AddGroupMemberDirectly findAddGroupMemberDirectly(String id);
	
	public void updateAddGroupMemberDirectly(AddGroupMemberDirectly AddGroupMemberDirectly);
	
	public Page findAddGroupMemberDirectlyByPage(int pageNo,int pageSize);
	
	public Page findAddGroupMemberDirectlyByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);

}
