package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.dao.AddGroupMemberDirectlyDao;
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo.AddGroupMemberDirectly;
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.service.AddGroupMemberDirectlyService;
import com.wondersgroup.framework.core.bo.Page;

public class AddGroupMemberDirectlyServiceimpl implements
		AddGroupMemberDirectlyService {

	private AddGroupMemberDirectlyDao addGroupMemberDirectlyDao;
	
	public void setAddGroupMemberDirectlyDao(
			AddGroupMemberDirectlyDao addGroupMemberDirectlyDao) {
		this.addGroupMemberDirectlyDao = addGroupMemberDirectlyDao;
	}

	@Override
	public void addAddGroupMemberDirectly(
			AddGroupMemberDirectly addGroupMemberDirectly) {
		// TODO Auto-generated method stub
		addGroupMemberDirectlyDao.save(addGroupMemberDirectly);
	}

	@Override
	public void deleteAddGroupMemberDirectly(
			AddGroupMemberDirectly addGroupMemberDirectly) {
		// TODO Auto-generated method stub
		addGroupMemberDirectlyDao.delete(addGroupMemberDirectly);
	}

	@Override
	public AddGroupMemberDirectly findAddGroupMemberDirectly(String id) {
		// TODO Auto-generated method stub
		return addGroupMemberDirectlyDao.load(id);
	}

	@Override
	public void updateAddGroupMemberDirectly(
			AddGroupMemberDirectly AddGroupMemberDirectly) {
		// TODO Auto-generated method stub
		addGroupMemberDirectlyDao.update(AddGroupMemberDirectly);
	}

	@Override
	public Page findAddGroupMemberDirectlyByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return addGroupMemberDirectlyDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findAddGroupMemberDirectlyByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return addGroupMemberDirectlyDao.findAddGroupMemberDirectlyByPage(filter, pageNo, pageSize, type);
	}

}
