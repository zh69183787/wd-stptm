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

package com.wonders.stpt.hiddenDangersCorrect.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HiddenDangersCorrect;
import com.wonders.stpt.hiddenDangersCorrect.dao.HiddenDangersCorrectDao;
import com.wonders.stpt.hiddenDangersCorrect.service.HiddenDangersCorrectService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HiddenDangersCorrectServiceImpl implements
		HiddenDangersCorrectService {
	private HiddenDangersCorrectDao hiddenDangersCorrectDao;

	public void setHiddenDangersCorrectDao(
			HiddenDangersCorrectDao hiddenDangersCorrectDao) {
		this.hiddenDangersCorrectDao = hiddenDangersCorrectDao;
	}

	public void addHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect) {
		hiddenDangersCorrectDao.save(hiddenDangersCorrect);
	}

	public void deleteHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect) {
		hiddenDangersCorrectDao.delete(hiddenDangersCorrect);
	}

	public HiddenDangersCorrect findHiddenDangersCorrectById(String id) {
		return hiddenDangersCorrectDao.load(id);
	}

	public void updateHiddenDangersCorrect(
			HiddenDangersCorrect hiddenDangersCorrect) {
		hiddenDangersCorrectDao.update(hiddenDangersCorrect);
	}

	public Page findHiddenDangersCorrectByPage(int pageNo, int pageSize) {
		Page page = hiddenDangersCorrectDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHiddenDangersCorrectByPage(Map<String, Object> filter,
			int pageNo, int pageSize,String webTypeId) {
		return hiddenDangersCorrectDao.findHiddenDangersCorrectByPage(filter,
				pageNo, pageSize,webTypeId);
	}
	
	public void updateCheckState(String check_state,String id){
		hiddenDangersCorrectDao.updateCheckState(check_state, id);
	}
	
	public List<Object[]> findForExport(Map<String, Object> filter,String operateTypeId){
		return hiddenDangersCorrectDao.findForExport(filter, operateTypeId);
	}
}
