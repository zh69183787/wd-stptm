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

import java.util.*;

import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.GlobalFunc;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganNodeType;
import com.wondersgroup.framework.security.bo.SecurityUser;
import org.apache.commons.lang.StringUtils;

import com.wonders.stpt.annualLeave.dao.HolConfigDao;
import com.wonders.stpt.annualLeave.dao.HolHolidayApplyDao;
import com.wonders.stpt.annualLeave.dao.HolHolidayDao;
import com.wonders.stpt.annualLeave.model.HolConfig;
import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.HolHolidayApply;
import com.wonders.stpt.annualLeave.model.data.entity.bo.OldCsUser;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHoliday;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHolidayapp;
import com.wonders.stpt.annualLeave.service.HolHolidayApplyService;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HolHolidayApplyServiceImpl implements HolHolidayApplyService {
	private HolHolidayApplyDao holHolidayApplyDao;
	private HolHolidayDao holHolidayDao;
	
	public HolHolidayDao getHolHolidayDao() {
		return holHolidayDao;
	}

	public void setHolHolidayDao(HolHolidayDao holHolidayDao) {
		this.holHolidayDao = holHolidayDao;
	}

	public void setHolHolidayApplyDao(HolHolidayApplyDao holHolidayApplyDao) {
		this.holHolidayApplyDao = holHolidayApplyDao;
	}

	public void addHolHolidayApply(HolHolidayApply holHolidayApply) {
		holHolidayApplyDao.save(holHolidayApply);
	}

	public void deleteHolHolidayApply(HolHolidayApply holHolidayApply) {
		holHolidayApplyDao.delete(holHolidayApply);
	}

	public HolHolidayApply findHolHolidayApplyById(String id) {
		return holHolidayApplyDao.load(id);
	}

	public void updateHolHolidayApply(HolHolidayApply holHolidayApply) {
		holHolidayApplyDao.updateHolHolidayApply(holHolidayApply);
	}

	public Page findHolHolidayApplyByPage(int pageNo, int pageSize) {
		Page page = holHolidayApplyDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHolHolidayApplyByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return holHolidayApplyDao.findHolHolidayApplyByPage(filter, pageNo,
				pageSize);
	}

	@Override
	public boolean saveHolHolidayApplyAndUpdateHolHoliday(HolHolidayApply holHolidayApply,List<HolHoliday> list) {
		try {
			holHolidayApplyDao.saveHolHolidayApplyAndUpdateHolHoliday(holHolidayApply,list);		//申请休假
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public CsUser findCsUserById(long id) {
		return holHolidayApplyDao.findCsUserById(id);
	}

	@Override
	public List<HolHolidayApply> findByApplyUserAndHolState(long applyUser,long[] holState) {
		
		return holHolidayApplyDao.findByApplyUserAndHolState(applyUser, holState);
	}

	@Override
	public void setHolidayData() {
		
		List<THolHoliday> tholidayList =  holHolidayApplyDao.findAllTholidayAfterYear("2013");
		List<THolHolidayapp> tholidayAppList = holHolidayApplyDao.findAllTholidayApp();			//863的值为1902
		List<HolHoliday> holidayList = new ArrayList<HolHoliday>();
		List<HolHolidayApply> holidayApplyList = new ArrayList<HolHolidayApply>();
		if(tholidayList!=null && tholidayList.size()>0){
			int error=0;
			for(int i=0,length=tholidayList.size(); i<length; i++ ){
				System.out.println("公休:"+i);
				THolHoliday t = tholidayList.get(i);
				t.setOperator(2641l);			//默认设置为汝文峰，loginName=G00100000074,newCsUserId=2641
				
				List<CsUser> newCsUserList = findNewCsUserByOldCsUserId(Long.valueOf(t.getHolId()));
				if(newCsUserList!=null && newCsUserList.size()>0){
					for(int m=0,mlength=newCsUserList.size(); m<mlength; m++){
						HolHoliday temp = t.convert(t);
						temp.setHolId(newCsUserList.get(m).getId()+"");
						temp.setId(UUID.randomUUID().toString());
						holidayList.add(temp);
					}
				}
				
				if(newCsUserList==null){
					HolHoliday temp = t.convert(t);
					temp.setRemoved(-1l);
					temp.setId(UUID.randomUUID().toString());
					holidayList.add(temp);
					error++;
				}
			}
			holHolidayDao.saveAllHolHoliday(holidayList);
			System.out.println("公休成功："+holidayList.size()+"条");
 			System.out.println("公休失败："+error+"条");
 			
		}
		System.out.println("公休申请开始，原有公休申请:"+tholidayAppList.size());
		if(tholidayAppList!=null && tholidayAppList.size()>0){
			List<HolHolidayApply> saveList = new ArrayList<HolHolidayApply>();
			List<HolHolidayApply> errorList = new ArrayList<HolHolidayApply>();
			
			for(int i=0,length=tholidayAppList.size(); i<length; i++){
				System.out.println("公休申请:"+i);
				THolHolidayapp t = tholidayAppList.get(i);
				HolHolidayApply h = t.convert(t);
				
				String applyLoginName = holHolidayApplyDao.findLoginNameByIdAndDeptId(t.getApplyUser()+"", t.getHolDeptId());		
				String checkLoginName = holHolidayApplyDao.findLoginNameByIdAndDeptId(t.getCheckUser()+"", t.getHolDeptId());
				String newDeptId = getDeptMap().get(t.getHolDeptId());
				String applyNewCsUserId = holHolidayApplyDao.findNewCsUserIdByLoginNameAndDeptId(applyLoginName, newDeptId);
				String checkNewCsUserId = holHolidayApplyDao.findNewCsUserIdByLoginNameAndDeptId(checkLoginName, newDeptId);
				
				if(StringUtils.isNotEmpty(newDeptId)){
					h.setHolDeptId(newDeptId);
				}else{
					h.setRemoved(-1l);
				}
				if(StringUtils.isNotEmpty(applyNewCsUserId)){
					h.setApplyUser(Long.valueOf(applyNewCsUserId));
				}else{
					//h.setApplyUser(null);		//老库中的申请用户在新库中找不到
					h.setRemoved(-1l);
				}
				if(StringUtils.isNotEmpty(checkNewCsUserId)){
					h.setCheckUser(Long.valueOf(checkNewCsUserId));
				}else{
					//h.setCheckUser(null);
					h.setRemoved(-1l);
				}
				if(h.getRemoved()==-1){
					errorList.add(h);
				}else{
					saveList.add(h);
				}
			}
			holHolidayApplyDao.saveAllHolidayApply(saveList);
			holHolidayApplyDao.saveAllHolidayApply(errorList);
			System.out.println("公休申请成功："+saveList.size()+"条");
			System.out.println("公休申请失败："+errorList.size()+"条");
		}
	}
	
	/**
	 * 
	 * @param id oldCsUserId
	 * @return
	 */
	public List<CsUser> findNewCsUserByOldCsUserId(long id){
		
		OldCsUser oldCsUser = holHolidayApplyDao.findOldCsUserById(id+"");		//先查老库csUser,得到loginName		
		if(oldCsUser!=null){
			List<CsUser> newCsUserList = holHolidayApplyDao.findNewCsUserByLoginName(oldCsUser.getLoginName());	//查询新库CssUser
			return newCsUserList;
		}
		return null;
	}
	
	//得到deptMap
	public Map<String, String> getDeptMap(){
		Map<String, String> deptMap=new HashMap<String, String>();
		deptMap.put("10094", "2950");
		deptMap.put("10095", "2951");
		deptMap.put("10115", "2921");
		deptMap.put("10116", "2922");
		deptMap.put("10190", "2519");
		deptMap.put("11030", "2550");
		deptMap.put("11470", "2518");
		deptMap.put("11473", "2509");
		deptMap.put("11476", "2551");
		deptMap.put("11550", "2980");
		deptMap.put("11910", "2687");
		deptMap.put("2102", "2502");
		deptMap.put("2103", "2505");
		deptMap.put("2104", "2506");
		deptMap.put("2106", "2507");
		deptMap.put("2107", "2508");
		deptMap.put("2109", "2514");
		deptMap.put("2111", "2510");
		deptMap.put("2112", "2512");
		deptMap.put("2113", "2513");
		deptMap.put("2116", "2549");
		deptMap.put("2117", "2503");
		deptMap.put("2118", "2511");
		deptMap.put("2119", "2557");
		deptMap.put("2121", "3020");
		deptMap.put("2122", "2952");
		deptMap.put("2123", "2960");
		deptMap.put("2124", "2958");
		deptMap.put("2125", "2947");
		deptMap.put("2126", "2948");
		deptMap.put("2127", "2956");
		deptMap.put("2128", "2953");
		deptMap.put("2129", "2949");
		deptMap.put("2130", "2954");
		
		return deptMap;
	}


    @Override
    public List<CsUser> findCsUserByIds(String ids) {
        if(StringUtils.isNotBlank(ids)){
            List<String> list = Arrays.asList(ids.split(","));
            List<Long> idList = new ArrayList<Long>();
            for (String id : list) {
                idList.add(Long.parseLong(id));
            }
           return  holHolidayApplyDao.findCsUserByIds(idList);
        }
        return null;
    }

}
