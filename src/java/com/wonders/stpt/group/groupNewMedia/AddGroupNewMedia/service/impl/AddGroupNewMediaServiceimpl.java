package com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.dao.AddGroupNewMediaDao;
import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.bo.AddGroupNewMedia;
import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.service.AddGroupNewMediaService;
import com.wondersgroup.framework.core.bo.Page;


public class AddGroupNewMediaServiceimpl implements AddGroupNewMediaService {

	
	private AddGroupNewMediaDao  addGroupNewMediaDao;
	
	public void setAddGroupNewMediaDao(AddGroupNewMediaDao addGroupNewMediaDao) {
		this.addGroupNewMediaDao = addGroupNewMediaDao;
	}

	@Override
	public void addAddGroupNewMedia(AddGroupNewMedia addGroupNewMedia) {
		// TODO Auto-generated method stub
		addGroupNewMediaDao.save(addGroupNewMedia);
	}

	@Override
	public void deleteAddGroupNewMedia(AddGroupNewMedia addGroupNewMedia) {
		// TODO Auto-generated method stub
		addGroupNewMediaDao.delete(addGroupNewMedia);
	}

	@Override
	public AddGroupNewMedia findAddGroupNewMedia(String id) {
		// TODO Auto-generated method stub
		return addGroupNewMediaDao.load(id);
	}

	@Override
	public void updateAddGroupNewMedia(AddGroupNewMedia AddGroupNewMedia) {
		// TODO Auto-generated method stub
		addGroupNewMediaDao.update(AddGroupNewMedia);
	}

	@Override
	public Page findAddGroupNewMediaByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return addGroupNewMediaDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findAddGroupNewMediaByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return addGroupNewMediaDao.findAddGroupNewMediaByPage(filter, pageNo, pageSize, type);
	}


}
