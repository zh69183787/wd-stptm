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

package com.wonders.stpt.securityreport.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.securityreport.entity.bo.SecReport;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

public interface SecReportDao extends AbstractHibernateDao<SecReport> {
	public Page findSecReportByPage(Map<String, Object> filter, int pageNo,
			int pageSize, String order);
	
	/**
	 * @author mengjie
	 * 
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 * 
	 */
	public List<Object[]> findSecReportForExport (Map<String, Object> filter,String order);
}
