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

package com.wonders.stpt.accidentCase.AccidentCaseApproval.service.impl;

import java.util.Map;

import com.wonders.stpt.accidentCase.AccidentCaseApproval.dao.AccidentCaseApprovalDao;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo.AccidentCaseApproval;
import com.wonders.stpt.accidentCase.AccidentCaseApproval.service.AccidentCaseApprovalService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-2-27
 * @author modify by $Author$
 * @since 1.0
 */

public class AccidentCaseApprovalServiceImpl implements
		AccidentCaseApprovalService {
	private AccidentCaseApprovalDao accidentCaseApprovalDao;

	public void setAccidentCaseApprovalDao(
			AccidentCaseApprovalDao accidentCaseApprovalDao) {
		this.accidentCaseApprovalDao = accidentCaseApprovalDao;
	}

	public void addAccidentCaseApproval(
			AccidentCaseApproval accidentCaseApproval) {
		accidentCaseApprovalDao.save(accidentCaseApproval);
	}

	public void deleteAccidentCaseApproval(
			AccidentCaseApproval accidentCaseApproval) {
		accidentCaseApprovalDao.delete(accidentCaseApproval);
	}

	public AccidentCaseApproval findAccidentCaseApprovalById(String id) {
		return accidentCaseApprovalDao.load(id);
	}

	public void updateAccidentCaseApproval(
			AccidentCaseApproval accidentCaseApproval) {
		accidentCaseApprovalDao.update(accidentCaseApproval);
	}

	public Page findAccidentCaseApprovalByPage(int pageNo, int pageSize) {
		Page page = accidentCaseApprovalDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findAccidentCaseApprovalByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return accidentCaseApprovalDao.findAccidentCaseApprovalByPage(filter,
				pageNo, pageSize);
	}
}
