
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
 
package com.wonders.stpt.myNotice.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * ʵ�����
 * @author ycl
 * @version $Revision$ 
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */
 
public interface TMsgUsermessageDao extends AbstractHibernateDao<Object>{
	public List<Object[]>  findTMsgUsermessageByPage( String userId,MsgUserMassage bean, int pageNo,
			int pageSize);
	
	public int recordCount (String userId,MsgUserMassage bean);
	
	public MsgUserMassage findMessageById(Long id);
	
	public void updateMsgState(MsgUserMassage bean,String state);
		
	public int deleteItem (String msgType,Long id);	
	
}
	
