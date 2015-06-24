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

package com.wonders.stpt.user.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.user.entity.bo.User;
import com.wonders.stpt.user.dao.UserDao;
import com.wonders.stpt.user.service.UserService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) {
		userDao.save(user);
	}

	public void deleteUser(User user) {
		userDao.delete(user);
	}

	public User findUserById(Long id) {
		return userDao.load(id);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public Page findUserByPage(int pageNo, int pageSize) {
		Page page = userDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return userDao.findUserByPage(filter, pageNo, pageSize);
	}
	
	public List<User> findAssetTaskCheckPersonByName(String name, String checkperson){
		 return userDao.findAssetTaskCheckPersonByName(name, checkperson);
	}
	
	public List<User> findAllUser(){
		return userDao.findAllUser();
	}
	
	public List<User> findUserByCheckpersonlist(String checkpersonlist){
		return userDao.findUserByCheckpersonlist(checkpersonlist);
	}
}
