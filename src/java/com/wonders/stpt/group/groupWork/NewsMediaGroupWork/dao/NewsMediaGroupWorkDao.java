package com.wonders.stpt.group.groupWork.NewsMediaGroupWork.dao;

import java.util.Map;

import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.entity.bo.NewsMediaGroupWork;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface NewsMediaGroupWorkDao extends
		AbstractHibernateDao<NewsMediaGroupWork> {

	public Page findNewsMediaGroupWorkByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}