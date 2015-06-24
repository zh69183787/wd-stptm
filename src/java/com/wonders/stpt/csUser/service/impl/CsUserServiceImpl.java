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

package com.wonders.stpt.csUser.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.csUser.dao.CsUserDao;
import com.wonders.stpt.csUser.service.CsUserService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-27
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
	
	public List<CsUser> findById(long id){
		return csUserDao.findById(id);
	}
}
