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

package com.wonders.stpt.accidentCase.TAttach.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface TAttachDao extends AbstractHibernateDao<TAttach> {
	public Page findTAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author yaochenglong
	 * @describe 根据groupId查询附件
	 * @param groupId String类型:groupId值
	 * @return List<TAttach>类型
	 */
	public List<TAttach> findTAttachByGroupID(String groupId);
}
