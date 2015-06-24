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

package com.wonders.stpt.dbBusiness.service.impl;

import com.wonders.stpt.dbBusiness.dao.DbBusinessDao;
import com.wonders.stpt.dbBusiness.entity.bo.DbBusiness;
import com.wonders.stpt.dbBusiness.service.DbBusinessService;
import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class DbBusinessServiceImpl implements DbBusinessService {
	private DbBusinessDao dbBusinessDao;

	public void setDbBusinessDao(DbBusinessDao dbBusinessDao) {
		this.dbBusinessDao = dbBusinessDao;
	}

	public List<Object[]> findDbBusinessByPage(String orderValue,int startRow,int pageSize,String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow){
		return dbBusinessDao.findDbBusinessByPage(orderValue,startRow, pageSize, creatememo, creatTimeStart, creatTimeEnd,
				deptName, userName, returnTimeStart,returnTimeEnd,manageTime,beyondDay,isEnd,isFollow);
	}
	
	public int countDbBusiness(String creatememo,String creatTimeStart,String creatTimeEnd, String deptName, String userName,String returnTimeStart,String returnTimeEnd,
			String manageTime,String beyondDay,String isEnd,String isFollow){
		return dbBusinessDao.countDbBusiness(creatememo, creatTimeStart, creatTimeEnd,
				deptName, userName, returnTimeStart,returnTimeEnd,manageTime,beyondDay,isEnd,isFollow);
	}
	public List<Object[]> findDbBusinessByIdAndDeptName(String id,String deptName){
		return dbBusinessDao.findDbBusinessByIdAndDeptName(id,deptName);
	}
	@Override
	public void saveAdd(DbBusiness dbBusiness) {
		dbBusinessDao.saveAdd(dbBusiness);
	}

	@Override
	public DbBusiness findTaskById(String id) {
		return dbBusinessDao.findTaskById(id);
	}

	@Override
	public void updateTask(DbBusiness dbBusiness) {
		dbBusinessDao.updateTask(dbBusiness);
	}

	@Override
	public void deleteTaskById(String id) {
		dbBusinessDao.deleteTaskById(id);
	}
	
	public List<DbBusiness> findAllTask(){
		return dbBusinessDao.findAllTask();
	}
	
	
}
