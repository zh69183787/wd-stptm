package com.wonders.stpt.myUrge.service.impl;



import java.util.List;
import java.util.Map;
import com.wonders.stpt.myUrge.dao.FlowUrgenDao;
import com.wonders.stpt.myUrge.entity.bo.FlowUrgen;
import com.wonders.stpt.myUrge.service.FlowUrgenService;


public class FlowUrgenServiceImpl implements FlowUrgenService {
	private FlowUrgenDao flowUrgenDao;
	


	public String findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public FlowUrgen findById(String id) {
		// TODO Auto-generated method stub
		return flowUrgenDao.findById(id);
	}

	public Object insert(FlowUrgen flowUrgen) {
		// TODO Auto-generated method stub
		return flowUrgenDao.insert(flowUrgen);
	}

	public List showFlowUrgenByCode(String hql) {
		// TODO Auto-generated method stub
		return flowUrgenDao.showFlowUrgenByCode(hql);
	}

	public void update(FlowUrgen flowUrgen) {
		// TODO Auto-generated method stub
		flowUrgenDao.update(flowUrgen);
	}

	public FlowUrgenDao getFlowUrgenDao() {
		return flowUrgenDao;
	}

	public void setFlowUrgenDao(FlowUrgenDao flowUrgenDao) {
		this.flowUrgenDao = flowUrgenDao;
	}

	public Map AddUrgeInfo(Map map) {
		return flowUrgenDao.AddUrgeInfo(map);
	}

	@Override
	public List<Object[]> findUrgeLogByPage(int startRow, int pageSize) {
		return flowUrgenDao.findUrgeLogByPage(startRow, pageSize);
	}

	@Override
	public int getUrgeLogCount() {
		return flowUrgenDao.getUrgeLogCount();
	}

}
