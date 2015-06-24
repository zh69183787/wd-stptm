package com.wonders.stpt.group.groupWork.NewsMediaGroupWork.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.dao.NewsMediaGroupWorkDao;
import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.entity.bo.NewsMediaGroupWork;
import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.service.NewsMediaGroupWorkService;
import com.wondersgroup.framework.core.bo.Page;

public class NewsMediaGroupWorkServiceImpl implements NewsMediaGroupWorkService {

	private NewsMediaGroupWorkDao newsMediaGroupWorkDao;
	
	public void setNewsMediaGroupWorkDao(NewsMediaGroupWorkDao newsMediaGroupWorkDao) {
		this.newsMediaGroupWorkDao = newsMediaGroupWorkDao;
	}

	@Override
	public void addNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork) {
		// TODO Auto-generated method stub
		newsMediaGroupWorkDao.save(newsMediaGroupWork);
	}

	@Override
	public void deleteNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork) {
		// TODO Auto-generated method stub
		newsMediaGroupWorkDao.delete(newsMediaGroupWork);
	}

	@Override
	public NewsMediaGroupWork findNewsMediaGroupWork(String id) {
		// TODO Auto-generated method stub
		return newsMediaGroupWorkDao.load(id);
	}

	@Override
	public void updateNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork) {
		// TODO Auto-generated method stub
		newsMediaGroupWorkDao.update(newsMediaGroupWork);
	}

	@Override
	public Page findNewsMediaGroupWorkByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return newsMediaGroupWorkDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findNewsMediaGroupWorkByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return newsMediaGroupWorkDao.findNewsMediaGroupWorkByPage(filter, pageNo, pageSize, type);
	}

}
