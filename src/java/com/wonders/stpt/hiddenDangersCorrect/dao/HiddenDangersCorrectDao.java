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

package com.wonders.stpt.hiddenDangersCorrect.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HiddenDangersCorrect;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public interface HiddenDangersCorrectDao extends
		AbstractHibernateDao<HiddenDangersCorrect> {
	public Page findHiddenDangersCorrectByPage(Map<String, Object> filter,
			int pageNo, int pageSize,String operateTypeId);
	
	public void updateCheckState(String check_state,String id);
	
	public List<Object[]> findForExport(Map<String, Object> filter,String operateTypeId);
}
