package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.service;

import java.util.Map;


import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo.AddUpdateGroupLeader;
import com.wondersgroup.framework.core.bo.Page;

public interface AddUpdateGroupLeaderService {

	public void addAddUpdateGroupLeader(AddUpdateGroupLeader addUpdateGroupLeader);

	public void deleteAddUpdateGroupLeader(AddUpdateGroupLeader addUpdateGroupLeader);
	
	public AddUpdateGroupLeader findAddUpdateGroupLeader(String id);
	
	public void updateAddUpdateGroupLeader(AddUpdateGroupLeader AddUpdateGroupLeader);
	
	public Page findAddUpdateGroupLeaderByPage(int pageNo,int pageSize);
	
	public Page findAddUpdateGroupLeaderByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);

}
