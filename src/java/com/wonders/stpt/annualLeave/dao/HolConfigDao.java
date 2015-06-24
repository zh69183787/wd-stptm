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

package com.wonders.stpt.annualLeave.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public interface HolConfigDao extends AbstractHibernateDao<HolConfig> {
	public Page findHolConfigByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	public List<HolConfig> findByYearAndHolPersonId(String year,String holPersonId);
	/**
	 * 根据员工id查询最近年份的一条数据
	 * @param holPersonId
	 * @return
	 */
	public HolConfig findLastHolConfigByholPersonId(String holId);
	
	public void saveAll(List<HolConfig> list);
	
	/**
	 * 根据用户工号查询
	 * @param loginName
	 * @return
	 */
	public List<User> findUserByLoginName(String loginName);
	
	public List<HolConfig> findByDay(String day);
	
	public List<HolConfig> findByIdAndDay(String id,String day);
	
	public void deleteByIdLogically(String id);
	
	public void saveOrUpdateAll(List<HolConfig> list);
	
	public void save(HolConfig holConfig);
	
	public HolConfig findById(String id);
	
	public List<HolConfig> findAllHolConfig();
	
	public List<HolConfig> findBetweenHdate(String start,String end);
}
