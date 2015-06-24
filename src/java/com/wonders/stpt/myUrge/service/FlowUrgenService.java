package com.wonders.stpt.myUrge.service;


import java.util.List;
import java.util.Map;

import com.wonders.stpt.myUrge.entity.bo.FlowUrgen;



public interface FlowUrgenService {
	public abstract FlowUrgen findById(String id);
	public abstract void update(FlowUrgen flowUrgen);
	public abstract Object insert(FlowUrgen flowUrgen);
	public abstract String findByCode(String flowUrgen);
	public abstract List showFlowUrgenByCode(String hql);
	public abstract Map AddUrgeInfo(Map map);
	public abstract int getUrgeLogCount ();
	public abstract List<Object[]> findUrgeLogByPage (int startRow, int pageSize);
}

