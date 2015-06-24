package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.dao.AddUpdateGroupMemberDao;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.service.AddUpdateGroupMemberService;
import com.wondersgroup.framework.core.bo.Page;

public class AddUpdateGroupMemberServiceImpl implements
		AddUpdateGroupMemberService {
	private AddUpdateGroupMemberDao addUpdateGroupMemberDao;
	
	public void setAddUpdateGroupMemberDao(
			AddUpdateGroupMemberDao addUpdateGroupMemberDao) {
		this.addUpdateGroupMemberDao = addUpdateGroupMemberDao;
	}

	@Override
	public void addAddUpdateGroupMember(
			AddUpdateGroupMember addUpdateGroupMember) {
		// TODO Auto-generated method stub
		addUpdateGroupMemberDao.save(addUpdateGroupMember);
	}

	@Override
	public void deleteAddUpdateGroupMember(
			AddUpdateGroupMember AddUpdateGroupMember) {
		// TODO Auto-generated method stub
		addUpdateGroupMemberDao.delete(AddUpdateGroupMember);
	}

	@Override
	public AddUpdateGroupMember findAddUpdateGroupMember(String id) {
		// TODO Auto-generated method stub
		return addUpdateGroupMemberDao.load(id);
	}

	@Override
	public void updateAddUpdateGroupMember(
			AddUpdateGroupMember AddUpdateGroupMember) {
		// TODO Auto-generated method stub
		System.out.println("update");
		addUpdateGroupMemberDao.update(AddUpdateGroupMember);
	}

	@Override
	public Page findAddUpdateGroupMemberByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return addUpdateGroupMemberDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findAddUpdateGroupMemberByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return addUpdateGroupMemberDao.findAddUpdateGroupMemberByPage(filter, pageNo, pageSize, type);
	}
	
	@Override
	public List<Object[]> countAll(){
		return addUpdateGroupMemberDao.countAll(); 
	}

}
