package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.dao;

import java.util.Map;

import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.bo.NewsMediaGroupCommunication;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;



public interface NewsMediaGroupCommunicationDao extends
AbstractHibernateDao<NewsMediaGroupCommunication> {

	public Page findNewsMediaGroupCommunicationByPagge(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
