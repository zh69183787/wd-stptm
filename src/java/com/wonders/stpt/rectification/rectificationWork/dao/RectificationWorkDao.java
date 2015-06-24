package com.wonders.stpt.rectification.rectificationWork.dao;

import java.util.Map;

import com.wonders.stpt.rectification.rectificationWork.entity.bo.RectificationWork;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;
/**
 * 整改工作情况DAo
 * @author Administrator
 *
 */
public interface RectificationWorkDao extends 
AbstractHibernateDao<RectificationWork> {

	public Page findRectificationWorkByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
