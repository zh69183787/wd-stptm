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

package com.wonders.stpt.sthr.CsUser.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.CsUser.dao.CsUserDao;
import com.wonders.stpt.sthr.CsUser.entity.bo.CsUser;
import com.wonders.stpt.sthr.CsUser.service.CsUserService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author 2055
 * @version $Revision$
 * @date 2012-3-14
 * @author modify by $Author$
 * @since 1.0
 */

public class CsUserServiceImpl implements CsUserService {
	private CsUserDao csUserDao;

	public void setCsUserDao(CsUserDao csUserDao) {
		this.csUserDao = csUserDao;
	}

	public void addCsUser(CsUser csUser) {
		csUserDao.save(csUser);
	}

	public void deleteCsUser(CsUser csUser) {
		csUserDao.delete(csUser);
	}

	public CsUser findCsUserById(Long id) {
		return csUserDao.load(id);
	}

	public void updateCsUser(CsUser csUser) {
		csUserDao.update(csUser);
	}

	public Page findCsUserByPage(int pageNo, int pageSize) {
		Page page = csUserDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findCsUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return csUserDao.findCsUserByPage(filter, pageNo, pageSize);
	}			
	
	/**
	 * @describe 工号及姓名校验
	 * @author sunjiawei
	 */
	public List<Object[]> checkJobNumber(String jobNumber){
		return csUserDao.checkJobNumber(jobNumber);
	}
}
