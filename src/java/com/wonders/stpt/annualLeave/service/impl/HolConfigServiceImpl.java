/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.annualLeave.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.annualLeave.dao.HolConfigDao;
import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.annualLeave.service.HolConfigService;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HolConfigServiceImpl implements HolConfigService {
	private HolConfigDao holConfigDao;

	public void setHolConfigDao(HolConfigDao holConfigDao) {
		this.holConfigDao = holConfigDao;
	}

	public void addHolConfig(HolConfig holConfig) {
		holConfigDao.save(holConfig);
	}

	public void deleteHolConfig(HolConfig holConfig) {
		holConfigDao.delete(holConfig);
	}

	public HolConfig findHolConfigById(String id) {
		return holConfigDao.findById(id);
	}

	public void updateHolConfig(HolConfig holConfig) {
		holConfigDao.update(holConfig);
	}

	public Page findHolConfigByPage(int pageNo, int pageSize) {
		Page page = holConfigDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHolConfigByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return holConfigDao.findHolConfigByPage(filter, pageNo, pageSize);
	}

	@Override
	public HolConfig load(String id) {
		
		return holConfigDao.findById(id);
	}

	@Override
	public boolean isHolConfigExist(String day) {
		List<HolConfig> list = holConfigDao.findByDay(day);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public boolean isHolConfigExist(String id, String day) {
		List<HolConfig> list = holConfigDao.findByIdAndDay(id, day);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public void save(HolConfig holConfig) {
		holConfigDao.save(holConfig);
	}

	@Override
	public void deleteByIdLogically(String id) {
		holConfigDao.deleteByIdLogically(id);
	}

	@Override
	public boolean isUserExist(String loginName) {
		List<User>list = holConfigDao.findUserByLoginName(loginName);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public List<HolConfig> findByDay(String day) {
		return holConfigDao.findByDay(day);
	}

	@Override
	public void saveOrUpdateAll(List<HolConfig> list) {
		holConfigDao.saveOrUpdateAll(list);
	}

	@Override
	public List<HolConfig> findAll() {
		
		return holConfigDao.findAllHolConfig();
	}

	@Override
	public List<HolConfig> findBetweenHdate(String start, String end) {
		
		return holConfigDao.findBetweenHdate(start,end);
	}
	
	
}
