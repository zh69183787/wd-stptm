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

package com.wonders.stpt.accidentCase.MetroAccidentCase.service.impl;

import java.util.Map;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.dao.AccidentCaseApprovalDao;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wonders.stpt.accidentCase.MetroAccidentCase.dao.MetroAccidentCaseDao;
import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo.MetroAccidentCase;
import com.wonders.stpt.accidentCase.MetroAccidentCase.service.MetroAccidentCaseService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroAccidentCaseServiceImpl implements MetroAccidentCaseService {
	private MetroAccidentCaseDao metroAccidentCaseDao;
	private AccidentCaseApprovalDao accidentCaseApprovalDao;

	public AccidentCaseApprovalDao getAccidentCaseApprovalDao() {
		return accidentCaseApprovalDao;
	}

	public void setAccidentCaseApprovalDao(
			AccidentCaseApprovalDao accidentCaseApprovalDao) {
		this.accidentCaseApprovalDao = accidentCaseApprovalDao;
	}

	public void setMetroAccidentCaseDao(
			MetroAccidentCaseDao metroAccidentCaseDao) {
		this.metroAccidentCaseDao = metroAccidentCaseDao;
	}

	public void addMetroAccidentCase(MetroAccidentCase metroAccidentCase) {
		metroAccidentCaseDao.save(metroAccidentCase);
	}

	public void deleteMetroAccidentCase(MetroAccidentCase metroAccidentCase) {
		metroAccidentCaseDao.delete(metroAccidentCase);
	}

	public MetroAccidentCase findMetroAccidentCaseById(String id) {
		return metroAccidentCaseDao.load(id);
	}

	public void updateMetroAccidentCase(MetroAccidentCase metroAccidentCase) {
		metroAccidentCaseDao.update(metroAccidentCase);
	}

	public Page findMetroAccidentCaseByPage(int pageNo, int pageSize) {
		Page page = metroAccidentCaseDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findMetroAccidentCaseByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return metroAccidentCaseDao.findMetroAccidentCaseByPage(filter, pageNo,
				pageSize);
	}
	
	public Page findMetroAccidentCaseByPage2(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return metroAccidentCaseDao.findMetroAccidentCaseByPage2(filter, pageNo,
				pageSize);
	}
	
	/**
	 * @author ycl
	 * @param id String类型
	 * @describe 根据主键删除
	 * @return void
	 */
	public void deleteMetroAccidentCaseById(String id){
		
		MetroAccidentCase metroAccidentCase = metroAccidentCaseDao.findMetroAccidentCaseByCaseId(id);
		if(metroAccidentCase!=null){
				metroAccidentCaseDao.delete(metroAccidentCase);
		}
	}
	
	/**
	 * @author sunjiawei
	 * @param accidentCaseApproval 实体
	 * @describe 添加审核记录
	 */
	public void addAccidentCaseApproval(AccidentCaseApproval accidentCaseApproval) {		
		accidentCaseApprovalDao.save(accidentCaseApproval);
	}

	
	public Page findMetroAccidentCaseByUpdatePerson(Map<String, Object> filter,int pageNo, int pageSize) {
		return metroAccidentCaseDao.findMetroAccidentCaseByUpdatePerson(filter, pageNo,
				pageSize);
	}
	
	
	
	
	
	
	
	
	
}
