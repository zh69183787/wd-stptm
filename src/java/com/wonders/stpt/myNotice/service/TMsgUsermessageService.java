
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
 
package com.wonders.stpt.myNotice.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.security.bo.SecurityUser;

/**
 * ҵ�����
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public interface TMsgUsermessageService
{
	/**
	 * ɾ��ʵ�����
	 * @param tMsgUsermessage
	 */
	public void deleteTMsgUsermessage(MsgUserMassage tMsgUsermessage);

	/**
	 *
	 * ͨ��IDװ����Ӧ�Ķ���ʵ������Ӧ��ʵ�岻���ڣ�����null
	 * @param id ����
	 * @return
	 */
	public MsgUserMassage findTMsgUsermessageById(Long id);

	/**
	 * �־û�һ��ʵ�����
	 *
	 * @param tMsgUsermessage
	 */
	public void addTMsgUsermessage(MsgUserMassage tMsgUsermessage);

	/**
	 * ������ݵ���ݿ�
	 * @param tMsgUsermessage ʵ��
	 */
	public void updateTMsgUsermessage(MsgUserMassage tMsgUsermessage);

	/**
	 * ��ݷ�ҳ������з�ҳ��ѯ.
	 *
	 * @param pageNo ��ǰҳ��
	 * @param pageSize ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findTMsgUsermessageByPage(int pageNo, int pageSize);

	/**
	 * ���Map�й��������ͷ�ҳ������з�ҳ��ѯ.
	 *
	 * @param filter ��������<propertyName,properyValue>
	 * @param pageNo ��ǰҳ��
	 * @param pageSize ÿҳ��ʾ��¼��.
	 * @return
	 */
	public List findTMsgUsermessageByPage(String userId,MsgUserMassage bean, int pageNo,
			int pageSize);

	public int recordCount(String userId,MsgUserMassage bean) ;

	public int deleteItem(String msgType,Long id);

	public void updateMsgState(MsgUserMassage bean,String state);


	public void sendMsg();

	public void sendShortMsgNotice(CsUser sender,CsUser receive, String content) ;

    public void sendApplyHolidayNoticeMsg(CsUser sender ,List<CsUser> noticeList,String applyDate,String applyId, boolean isNeedSendShortMessage) ;
}
	
