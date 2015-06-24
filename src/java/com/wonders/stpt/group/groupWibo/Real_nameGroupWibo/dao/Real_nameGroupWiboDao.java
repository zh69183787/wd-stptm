package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.dao;

import java.util.Map;

import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.bo.Real_nameGroupWibo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface Real_nameGroupWiboDao extends AbstractHibernateDao<Real_nameGroupWibo> {

	public Page findReal_nameGroupWiboByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
