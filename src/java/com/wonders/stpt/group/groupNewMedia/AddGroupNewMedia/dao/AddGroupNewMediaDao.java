package com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.dao;

import java.util.Map;

import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.bo.AddGroupNewMedia;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface AddGroupNewMediaDao extends
		AbstractHibernateDao<AddGroupNewMedia> {

	public Page findAddGroupNewMediaByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
