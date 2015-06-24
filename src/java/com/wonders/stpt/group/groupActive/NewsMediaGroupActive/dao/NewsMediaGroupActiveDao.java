package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.dao;

import java.util.Map;

import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.bo.NewsMediaGroupActive;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface NewsMediaGroupActiveDao extends 
AbstractHibernateDao<NewsMediaGroupActive> {

	public Page findNewsMediaGroupActiveByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
	
}
