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

import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.annualLeave.dao.HolHolidayDao;
import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.VUserdep;
import com.wonders.stpt.annualLeave.service.HolHolidayService;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HolHolidayServiceImpl implements HolHolidayService {
	private HolHolidayDao holHolidayDao;

	public void setHolHolidayDao(HolHolidayDao holHolidayDao) {
		this.holHolidayDao = holHolidayDao;
	}

	public void addHolHoliday(HolHoliday holHoliday) {
		holHolidayDao.save(holHoliday);
	}

	public void deleteHolHoliday(HolHoliday holHoliday) {
		holHolidayDao.delete(holHoliday);
	}

	public HolHoliday findHolHolidayById(String id) {
		return holHolidayDao.findById(id);
	}

	public void updateHolHoliday(HolHoliday holHoliday) {
		holHolidayDao.update(holHoliday);
	}

	public Page findHolHolidayByPage(int pageNo, int pageSize) {
		Page page = holHolidayDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHolHolidayByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return holHolidayDao.findHolHolidayByPage(filter, pageNo, pageSize);
	}

	@Override
	public List<HolHoliday> findByYearAndHolPersonId(String year,String loginName) {
		
		return holHolidayDao.findByYearAndHolPersonId(year, loginName);
	}
	
	@Override
	public List<HolHoliday> findByStartYearAndHolPersonId(String year,
			String loginName) {
		return holHolidayDao.findByStartYearAndHolPersonId(year,loginName);
	}

	@Override
	public boolean isUserExist(String loginName) {
		List<CsUser> list = holHolidayDao.findUsersByName(loginName);
		if(list!=null && list.size()>0) return true;
		return false;
	}
	
	
	@Override
	public List<CsUser> findUsersByLoginName(String loginName) {
		return holHolidayDao.findUsersByName(loginName);
	}

	@Override
	public HolHoliday findLastHolidaysSetByholPersonId(String loginName) {
		
		return holHolidayDao.findLastHolidaysSetByholPersonId(loginName);
	}

	@Override
	public void saveAll(List<HolHoliday> list) {
		holHolidayDao.saveOrUpdateAll(list);
	}

	@Override
	public com.wonders.stpt.csUser.entity.bo.CsUser findUserById(long id) {
		return holHolidayDao.findUserById(id);
	}

	@Override
	public Object[] findHolHolidaySettime() {
		return holHolidayDao.findHolHolidaySettime();
	}

	@Override
	public void updateHolHolidaySettime(Long overyear, Long month) {
		holHolidayDao.updateHolHolidaySettime(overyear,month);
	}

	@Override
	public VUserdep findVUserdepById(Long id) {
		return holHolidayDao.findVUserdepById(id);
	}

	@Override
	public List<HolHoliday> findOtherAccountByHolId(String holId,String holyear) {
		CsUser csUser = holHolidayDao.findUserById(Long.valueOf(holId));
		if(csUser!=null){
			String loginName = csUser.getLoginName();
			if(StringUtils.isNotEmpty(loginName) && loginName.length()>=12){
				loginName = loginName.substring(0,12);
				return holHolidayDao.findOtherAccountByLoginName(loginName,holyear);
			}
		}
		return null;
	}

    public Page getHoliday(Map filter, int pageNo,
                           int pageSize){
        return holHolidayDao.find(filter,pageNo,pageSize);
    }

	
}
