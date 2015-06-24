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

package com.wonders.stpt.user.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

public interface UserDao extends AbstractHibernateDao<User> {
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
