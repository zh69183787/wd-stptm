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

package com.wonders.stpt.annualLeave.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wondersgroup.framework.core.bo.Page;


public interface HolConfigService {

	public void deleteHolConfig(HolConfig holConfig);

	
	public HolConfig findHolConfigById(String id);

	
	public void addHolConfig(HolConfig holConfig);

	
	public void updateHolConfig(HolConfig holConfig);

	
	public Page findHolConfigByPage(int pageNo, int pageSize);


	public Page findHolConfigByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	public HolConfig load(String id);
	
	public boolean isHolConfigExist(String day);
	
	public boolean isHolConfigExist(String id,String day);
	
	public void save(HolConfig holConfig);
	
	public void deleteByIdLogically(String id);
	
	public boolean isUserExist(String loginName);
	
	public List<HolConfig> findByDay(String day);
	
	public void saveOrUpdateAll(List<HolConfig> list);

	public List<HolConfig> findAll();
	
	public List<HolConfig> findBetweenHdate(String start,String end);
}
