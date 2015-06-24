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

import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.HolHolidayApply;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.organization.bo.OrganNode;


public interface HolHolidayApplyService {
	
	public void deleteHolHolidayApply(HolHolidayApply holHolidayApply);

	
	public HolHolidayApply findHolHolidayApplyById(String id);


	public void addHolHolidayApply(HolHolidayApply holHolidayApply);

	public void updateHolHolidayApply(HolHolidayApply holHolidayApply);


	public Page findHolHolidayApplyByPage(int pageNo, int pageSize);

	
	public Page findHolHolidayApplyByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	/**
	 * 保存公休申请并且修改公休
	 * @param holHolidayApply
	 */
	public boolean saveHolHolidayApplyAndUpdateHolHoliday(HolHolidayApply holHolidayApply,List<HolHoliday> list);
	
	public CsUser findCsUserById(long id);

    public List<CsUser> findCsUserByIds(String ids);
	
	public List<HolHolidayApply> findByApplyUserAndHolState(long applyUser,long[] holState);
	
	
	public void setHolidayData();
		
}
