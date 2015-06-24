package com.wonders.stpt.rectification.rectificationWork.service.impl;

import com.wonders.stpt.rectification.rectificationWork.dao.RectificationWorkDao;
import java.util.Map;

import com.wonders.stpt.rectification.rectificationWork.entity.bo.RectificationWork;
import com.wonders.stpt.rectification.rectificationWork.service.RectificationWorkService;
import com.wondersgroup.framework.core.bo.Page;

public class RectificationWorkServiceImpl implements RectificationWorkService {

	private RectificationWorkDao rectificationWorkDao;
	
	public void setRectificationWorkDao(RectificationWorkDao rectificationWorkDao) {
		this.rectificationWorkDao = rectificationWorkDao;
	}

	@Override
	public void deleteRectificationWork(RectificationWork rectificationWork) {
		// TODO Auto-generated method stub
		rectificationWorkDao.delete(rectificationWork);
	}

	@Override
	public RectificationWork findRectificationWorkById(String id) {
		// TODO Auto-generated method stub
		
		return rectificationWorkDao.load(id);
	}

	@Override
	public void addRectificationWork(RectificationWork rectificationWork) {
		// TODO Auto-generated method stub
		
		rectificationWorkDao.save(rectificationWork);
	}

	@Override
	public void updateRectificationWork(RectificationWork rectificationWork) {
		// TODO Auto-generated method stub
		rectificationWorkDao.update(rectificationWork);
	}

	@Override
	public Page findRectificationWorkByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return rectificationWorkDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findRectificationWorkByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String webTypeId) {
		// TODO Auto-generated method stub
		return rectificationWorkDao.findRectificationWorkByPage(filter, pageNo, pageSize, webTypeId);
	}
}
