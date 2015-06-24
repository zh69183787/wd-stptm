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

package com.wonders.stpt.user.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

public interface UserService {
	/**
	 * 删除实体对象
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public User findUserById(Long id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 更新数据到数据库
	 * 
	 * @param user
	 *            实体
	 */
	public void updateUser(User user);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findUserByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * 根据checkperson联查user表中与checkperson一样的name
	 * @param name  user表中name
	 * @param checkperson  AssetTaskCheck表中checkperson
	 * @return
	 */
	public List<User> findAssetTaskCheckPersonByName(String name, String checkperson);
	
	/**
	 * 查找T_USER表中所有的信息
	 * @return
	 */
	public List<User> findAllUser(); 
	
	/**
	 * 根据task表中的checkpersonlist，联查t_user_relation与t_user表
	 * @param checkpersonlist
	 * @return t_user中的信息
	 */
	public List<User> findUserByCheckpersonlist(String checkpersonlist);
}
