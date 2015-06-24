package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.dao.AddUpdateGroupLeaderDao;
import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo.AddUpdateGroupLeader;
import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.service.AddUpdateGroupLeaderService;
import com.wondersgroup.framework.core.bo.Page;

public class AddUpdateGroupLeaderServiceImpl implements
		AddUpdateGroupLeaderService {

	private AddUpdateGroupLeaderDao addUpdateGroupLeaderDao;
	
	public void setAddUpdateGroupLeaderDao(
			AddUpdateGroupLeaderDao addUpdateGroupLeaderDao) {
		this.addUpdateGroupLeaderDao = addUpdateGroupLeaderDao;
	}

	@Override
	public void addAddUpdateGroupLeader(
			AddUpdateGroupLeader addUpdateGroupLeader) {
		// TODO Auto-generated method stub
		addUpdateGroupLeaderDao.save(addUpdateGroupLeader);
	}

	@Override
	public void deleteAddUpdateGroupLeader(
			AddUpdateGroupLeader addUpdateGroupLeader) {
		// TODO Auto-generated method stub
		addUpdateGroupLeaderDao.delete(addUpdateGroupLeader);
	}

	@Override
	public AddUpdateGroupLeader findAddUpdateGroupLeader(String id) {
		// TODO Auto-generated method stub
		return addUpdateGroupLeaderDao.load(id);
	}

	@Override
	public void updateAddUpdateGroupLeader(
			AddUpdateGroupLeader AddUpdateGroupLeader) {
		// TODO Auto-generated method stub
		addUpdateGroupLeaderDao.update(AddUpdateGroupLeader);
	}

	@Override
	public Page findAddUpdateGroupLeaderByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return addUpdateGroupLeaderDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findAddUpdateGroupLeaderByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return addUpdateGroupLeaderDao.findAddUpdateGroupLeaderByPage(filter, pageNo, pageSize, type);
	}

}
