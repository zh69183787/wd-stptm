package com.wonders.stpt.annualLeave.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.HolHolidayApply;
import com.wonders.stpt.annualLeave.model.data.entity.bo.OldCsUser;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHoliday;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHolidayapp;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface HolHolidayApplyDao extends
		AbstractHibernateDao<HolHolidayApply> {
	public Page findHolHolidayApplyByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	public void saveHolHolidayApplyAndUpdateHolHoliday(HolHolidayApply holHolidayApply,List<HolHoliday> holHolidayList);
	
	public void updateHolHolidayApply(HolHolidayApply holHolidayApply);
	
	public CsUser findCsUserById(long id);
	
	public List<HolHolidayApply> findByApplyUserAndHolState(long applyUser,long[] holState);
	
	
	//begin
	public List<THolHoliday> findAllTholidayAfterYear(String year);
	
	public OldCsUser findOldCsUserById(String id);
	public List<CsUser> findNewCsUserByLoginName(String loginName);
	public List<THolHolidayapp> findAllTholidayApp();
	public String findLoginNameByIdAndDeptId(String id,String deptId);
	public String findNewCsUserIdByLoginNameAndDeptId(String loginName,String deptId);
	public void saveAllHolidayApply(List<HolHolidayApply> list);
    public List<CsUser> findCsUserByIds(List<Long> userId);
}
