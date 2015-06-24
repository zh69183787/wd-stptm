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

package com.wonders.stpt.myUrge.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.myUrge.entity.bo.VUrgeInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * 
 * @author laowei
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public interface VMyUrgeDao extends
		AbstractHibernateDao<VUrgeInfo> {
	public List<Object[]> findVMyUrgeByPage(HttpServletRequest rs,int pageNo, int pageSize);
	
	/**
	 * @author laowei
	 * @description 
	 * @param flowId
	 * @throws SQLException 
	 */
	public int getCountMyUrge(HttpServletRequest rs) throws SQLException ;
	
	
}
