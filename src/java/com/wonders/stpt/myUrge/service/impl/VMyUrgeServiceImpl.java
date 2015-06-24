package com.wonders.stpt.myUrge.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wonders.stpt.evaluate.FlowEvaluation.dao.TFlowEvaluationDao;
import com.wonders.stpt.myUrge.dao.VMyUrgeDao;
import com.wonders.stpt.myUrge.service.VMyUrgeService;
import com.wondersgroup.framework.core.bo.Page;



public class VMyUrgeServiceImpl implements VMyUrgeService {
	private VMyUrgeDao myUrgeDao;

	public void setMyUrgeDao(VMyUrgeDao vMyUrgeDao) {
		this.myUrgeDao = vMyUrgeDao;
	}
	@Override
	public List<Object[]> findVMyUrgeByPage(HttpServletRequest rs,int pageNo, int pageSize) {
		// TODO 自动生成的方法存根
		return myUrgeDao.findVMyUrgeByPage(rs,pageNo,pageSize);
	}
	public int getCountMyUrge(HttpServletRequest rs) throws SQLException {
		return myUrgeDao.getCountMyUrge(rs);
	}



}
