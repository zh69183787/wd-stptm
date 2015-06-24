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

package com.wonders.stpt.csUser.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-27
 * @author modify by $Author$
 * @since 1.0
 */

public interface CsUserDao extends AbstractHibernateDao<CsUser> {
	public Page findCsUserByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * 根据用户Id查找相关信息
	 * @param id csUser id
	 * @return
	 */
	public List<CsUser> findById(long id);
}
