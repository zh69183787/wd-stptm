package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.dao.NewsMediaGroupActiveDao;
import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.bo.NewsMediaGroupActive;
import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.service.NewsMediaGroupActiveService;
import com.wondersgroup.framework.core.bo.Page;

public class NewsMediaGroupActiveServiceImpl implements
		NewsMediaGroupActiveService {

	private NewsMediaGroupActiveDao newsMediaGroupActiveDao;
	
	public void setNewsMediaGroupActiveDao(
			NewsMediaGroupActiveDao newsMediaGroupActiveDao) {
		this.newsMediaGroupActiveDao = newsMediaGroupActiveDao;
	}

	@Override
	public void addNewsMediaGroupActive(
			NewsMediaGroupActive newsMediaGroupActive) {
		// TODO Auto-generated method stub
		newsMediaGroupActiveDao.save(newsMediaGroupActive);
	}

	@Override
	public void deleteNewsMediaGroupActive(
			NewsMediaGroupActive newsMediaGroupActive) {
		// TODO Auto-generated method stub
		newsMediaGroupActiveDao.delete(newsMediaGroupActive);
	}

	@Override
	public NewsMediaGroupActive findNewsMediaGroupActive(String id) {
		// TODO Auto-generated method stub
		return newsMediaGroupActiveDao.load(id);
	}

	@Override
	public void updateNewsMediaGroupActive(
			NewsMediaGroupActive newsMediaGroupActive) {
		// TODO Auto-generated method stub
		newsMediaGroupActiveDao.update(newsMediaGroupActive);
	}

	//查找所有
	@Override
	public Page findNewsMediaGroupActiveByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return newsMediaGroupActiveDao.findAllWithPage(pageNo, pageSize);
	}
//调用DAO中的方法
	@Override
	public Page findNewsMediaGroupActiveByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return newsMediaGroupActiveDao.findNewsMediaGroupActiveByPage(filter, pageNo, pageSize, type);
	}

}
