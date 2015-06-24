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

package com.wonders.stpt.accidentCase.MetroAccidentCase.dao;

import java.util.Map;

import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo.MetroAccidentCase;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroAccidentCaseDao extends
		AbstractHibernateDao<MetroAccidentCase> {
	public Page findMetroAccidentCaseByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public Page findMetroAccidentCaseByPage2(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	
	public Page findMetroAccidentCaseByUpdatePerson(Map<String, Object> filter,
			int pageNo, int pageSize);
	/**
	 * @author yaochenglong
	 * @param caseId String类型 主键
	 * @describe 根据主键查询
	 * @return MetroAccidentCase
	 */
	public MetroAccidentCase findMetroAccidentCaseByCaseId(String caseId);
	
	
}
