package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.service;

import java.util.List;
import java.util.Map;


import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wondersgroup.framework.core.bo.Page;

public interface AddUpdateGroupMemberService {

	public void addAddUpdateGroupMember(AddUpdateGroupMember addUpdateGroupMember);

	public void deleteAddUpdateGroupMember(AddUpdateGroupMember AddUpdateGroupMember);
	
	public AddUpdateGroupMember findAddUpdateGroupMember(String id);
	
	public void updateAddUpdateGroupMember(AddUpdateGroupMember AddUpdateGroupMember);
	
	public Page findAddUpdateGroupMemberByPage(int pageNo,int pageSize);
	
	public Page findAddUpdateGroupMemberByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
	
	public List<Object[]> countAll();

}
