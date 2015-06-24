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

import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.VUserdep;
import com.wonders.stpt.csUser.entity.bo.CsUser;
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

public interface HolHolidayDao extends AbstractHibernateDao<HolHoliday> {
    public Page find(Map filter,int pageNo,int pageSize);

	public Page findHolHolidayByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	public List<HolHoliday> findByYearAndHolPersonId(String year,String loginName);
	
	public List<HolHoliday> findByStartYearAndHolPersonId(String year,String loginName);
	
	public List<HolHoliday> findAllNotUsedByPersonId(String loginName);
	
	public List<CsUser> findUsersByName(String name);
	
	public HolHoliday findLastHolidaysSetByholPersonId(String loginName);
	
	public void saveOrUpdateAll(List<HolHoliday> list);

	public com.wonders.stpt.csUser.entity.bo.CsUser findUserById(long id);
	
	public HolHoliday findById(String id);
	
	public Object[] findHolHolidaySettime();
	
	public void updateHolHolidaySettime(Long overyear,Long month);
	
	public VUserdep findVUserdepById(Long id);
		
	public void saveAllHolHoliday(List<HolHoliday> list);
	
	public List<HolHoliday> findOtherAccountByLoginName(String loginName,String holyear);
	
	
}
