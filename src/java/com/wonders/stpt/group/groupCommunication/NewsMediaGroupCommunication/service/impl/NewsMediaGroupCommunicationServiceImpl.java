package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.dao.NewsMediaGroupCommunicationDao;
import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.bo.NewsMediaGroupCommunication;
import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.service.NewsMediaGroupCommunicationService;
import com.wondersgroup.framework.core.bo.Page;

public class NewsMediaGroupCommunicationServiceImpl implements
		NewsMediaGroupCommunicationService {
	private NewsMediaGroupCommunicationDao newsMediaGroupCommunicationDao;
	
	public void setNewsMediaGroupCommunicationDao(
			NewsMediaGroupCommunicationDao newsMediaGroupCommunicationDao) {
		this.newsMediaGroupCommunicationDao = newsMediaGroupCommunicationDao;
	}

	@Override
	public void addNewsMediaGroupCommunication(
			NewsMediaGroupCommunication newsMediaGroupCommunication) {
		// TODO Auto-generated method stub
		newsMediaGroupCommunicationDao.save(newsMediaGroupCommunication);
	}

	@Override
	public void deleteNewsMediaGroupCommunication(
			NewsMediaGroupCommunication newsMediaGroupCommunication) {
		// TODO Auto-generated method stub
		newsMediaGroupCommunicationDao.delete(newsMediaGroupCommunication);
	}

	@Override
	public NewsMediaGroupCommunication findNewsMediaGroupCommunication(String id) {
		// TODO Auto-generated method stub
		return newsMediaGroupCommunicationDao.load(id);
	}

	@Override
	public void updateNewsMediaGroupCommunication(
			NewsMediaGroupCommunication newsMediaGroupCommunication) {
		// TODO Auto-generated method stub
		newsMediaGroupCommunicationDao.update(newsMediaGroupCommunication);
	}

	@Override
	public Page findNewsMediaGroupCommunicationByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return newsMediaGroupCommunicationDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findNewsMediaGroupCommunicationByPage(
			Map<String, Object> filter, int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return newsMediaGroupCommunicationDao.findNewsMediaGroupCommunicationByPagge(filter, pageNo, pageSize, type);
	}

}
