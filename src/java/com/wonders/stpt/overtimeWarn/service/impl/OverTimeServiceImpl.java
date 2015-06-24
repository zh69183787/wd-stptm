package com.wonders.stpt.overtimeWarn.service.impl;

import java.util.List;

import com.wonders.stpt.overtimeWarn.dao.OverTimeDao;
import com.wonders.stpt.overtimeWarn.entity.bo.HtXx;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocReceive;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocSend;
import com.wonders.stpt.overtimeWarn.entity.bo.TReceiveDirective;
import com.wonders.stpt.overtimeWarn.service.OverTimeService;

public class OverTimeServiceImpl implements OverTimeService {
	private OverTimeDao overTimeDao = null;

	public OverTimeDao getOverTimeDao() {
		return overTimeDao;
	}

	public void setOverTimeDao(OverTimeDao overTimeDao) {
		this.overTimeDao = overTimeDao;
	}

	@Override
	public List findRecordByPage(TDocReceive bean, int pageNo, int pageSize) {
		return overTimeDao.findRecordByPage(bean, pageNo, pageSize);
	}

	@Override
	public int recordCount(TDocReceive bean) {
		return overTimeDao.recordCount(bean);

	}

	@Override
	public int recordCount(HtXx bean) {
		return overTimeDao.recordCount(bean);
	}

	@Override
	public List findRecordByPage(HtXx bean, int pageNo, int pageSize) {
		return overTimeDao.findRecordByPage(bean, pageNo, pageSize);
	}

	// 发文
	public List findRecordByPage(TDocSend bean, int pageNo, int pageSize) {

		return overTimeDao.findRecordByPage(bean, pageNo, pageSize);
	}

	public int recordCount(TDocSend bean) {
		return overTimeDao.recordCount(bean);

	}

	// 呈批
	public List findRecordByPage(TReceiveDirective bean, int pageNo,
			int pageSize) {

		return overTimeDao.findRecordByPage(bean, pageNo, pageSize);
	}

	public int recordCount(TReceiveDirective bean) {
		return overTimeDao.recordCount(bean);

	}

	// 合同审批
	public List findRecordByPageL(HtXx bean, int pageNo, int pageSize) {

		return overTimeDao.findRecordByPageL(bean, pageNo, pageSize);
	}

	public int recordCountL(HtXx bean) {

		return overTimeDao.recordCountL(bean);
	}

	@Override
	public List findRecordByPagei(HtXx bean, int pageNo, int pageSize) {

		return overTimeDao.findRecordByPagei(bean, pageNo, pageSize);
	}

	@Override
	public int recordCounti(HtXx bean) {
		return overTimeDao.recordCounti(bean);
	}

	@Override
	public List findRecordByPaget(HtXx bean, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return overTimeDao.findRecordByPaget(bean, pageNo, pageSize);
	}

	@Override
	public int recordCountt(HtXx bean) {
		// TODO Auto-generated method stub
		return overTimeDao.recordCountt(bean);
	}

}
