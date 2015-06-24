package com.wonders.stpt.myUrge.dao;


import java.util.List;
import java.util.Map;

import com.wonders.stpt.myUrge.entity.bo.FlowUrgen;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;





public interface FlowUrgenDao extends AbstractHibernateDao<FlowUrgen>{
	public abstract FlowUrgen findById(String id);
	public abstract void update(FlowUrgen flowUrgen);
	public abstract Object insert(FlowUrgen flowUrgen);
	//public abstract String findByCode(String code);
	public abstract List showFlowUrgenByCode(String hql);
	public abstract Map AddUrgeInfo(Map map);
	public abstract int getUrgeLogCount ();
	public abstract List<Object[]> findUrgeLogByPage (int startRow, int pageSize);
}



