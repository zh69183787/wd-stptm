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

package com.wonders.stpt.myNotice.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Text;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.stpt.dbFollow.entity.bo.TMsgUserMassage;
import com.wonders.stpt.myNotice.dao.TMsgUsermessageDao;
import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.GlobalFunc;
import com.wonders.stpt.util.PageUtils;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TMsgUsermessageʵ�嶨��
 * 
 * @author tc
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class TMsgUsermessageDaoImpl extends
		AbstractHibernateDaoImpl <Object>implements TMsgUsermessageDao {
	
	public List<Object[]> findTMsgUsermessageByPage(String userId,
			MsgUserMassage bean, int startRow, int pageSize) {

		List<Object[]> list = new ArrayList<Object[]>();
		String strSql = "";
		String strMsgType = "1";
		String useId = userId;
		String readflag = "";

		String sender = bean.getSender();
		String receiver = bean.getReceiver();
		String title = bean.getTitle();
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();
		readflag = bean.getCaseType();
		
		
		String fj = "(case when A.HAS_ATTA=1 then '<img src=\"../images/fj.gif\" alt=\"附件\">' else '&nbsp;' end) as HAS_ATTA";
		// String ca =
		// "(case when A.STATE=0 then '<img src=\"../images/m_new.gif\" alt=\"未读\">' when A.STATE>0 then '<img src=\"../images/m_olds.gif\" alt=\"已读\">' end) as states";
		String ca = " STATE as states";
		strMsgType = bean.getMsgType() + "";

		if (strMsgType == null || strMsgType.equals("")) {
			strMsgType = "1";
		}

		String strFilerSender = "";
		if (sender != null && !"".equals(sender)) {
			strFilerSender = " inner join cs_user b on a.empidsend = b.id and b.name like '%"
					+ sender + "%' ";
		}
		String strFilerReceiver = "";
		if (receiver != null && !"".equals(receiver)) {
			strFilerReceiver = " inner join cs_user c on A.EMPIDREC = c.id and c.name like '%"
					+ receiver + "%' ";
		}

		if (strMsgType.equals("1")) {
			strSql = "select (select t.name from cs_user t where t.id=A.EMPIDSEND) as sender,"
					+ " (select t.name from cs_user t where t.id=A.EMPIDREC) as receiver,";
			strSql += fj + "," + ca + ",A.TITLE,SENDDATE,SEEDATE,";
			strSql += " A.STATE,A.SID,A.EMPIDREC,a.msgcount  from T_MSG_USERMESSAGE A";
			strSql += strFilerSender;
			strSql += " where A.RECRDELETE='1' and A.RECRSTATE='1' and A.EMPIDREC="
					+ useId;

		}

		if (strMsgType.equals("2")) {
			strSql = "select (select t.name from cs_user t where t.id=A.EMPIDSEND) as sender,"
					+ "(select NVL(t.name, '') from cs_user t where t.id=A.EMPIDREC) as receiver,";
			strSql += fj + "," + ca + ",A.TITLE,SENDDATE,SEEDATE,";
			strSql += " A.STATE,A.SID,A.EMPIDREC,a.msgcount from T_MSG_USERMESSAGE A ";
			strSql += strFilerReceiver;
			strSql += " where A.SENDDELETE='1' and A.STATE='0' and A.SENDSTATE='1' and A.EMPIDSEND="
					+ useId;
		}

		if (strMsgType.equals("3")) {
			strSql = "select (select t.name from cs_user t where t.id=A.EMPIDSEND) as sender,"
					+ "(select NVL(t.name, '')  from cs_user t where t.id=A.EMPIDREC) as receiver,";
			strSql += fj + "," + ca + ",A.TITLE,SENDDATE,SEEDATE,";
			strSql += " A.STATE,A.SID,A.EMPIDREC,a.msgcount  from T_MSG_USERMESSAGE A";
			strSql += strFilerReceiver;
			strSql += " where A.SENDDELETE='1' and A.STATE<>'0' and A.SENDSTATE='1' and A.EMPIDSEND="
					+ useId;

		}
		if (strMsgType.equals("4")) {
			strSql = "select (select t.name from cs_user t where t.id=A.EMPIDSEND) as sender,"
					+ "(select t.name from cs_user t where t.id=A.EMPIDREC) as receiver,";
			strSql += fj + "," + ca + ",A.TITLE,SENDDATE,SEEDATE,";
			strSql += " A.STATE,A.SID,A.EMPIDREC,a.msgcount  from T_MSG_USERMESSAGE A "
					+ strFilerSender + strFilerReceiver;
			strSql += " where (A.SENDDELETE='1' and A.EMPIDSEND=" + useId
					+ " and A.SENDSTATE='0')";
			strSql += " or (A.RECRDELETE='1' and A.EMPIDREC=" + useId
					+ " and A.RECRSTATE='0')";
		}
		strSql = "select * from (" + strSql + ") where 1=1";
		if (strMsgType.equals("1")) {
			if (readflag.equals("1")) {
				strSql += " and State = 1 ";
			} else {
				strSql += " and State = 0 ";
			}
		}
		if (title != null && !"".equals(title)) {
			strSql += " and Title like '%" + bean.getTitle() + "%'";
		}

		if (beginDate != null && !"".equals(beginDate)) {
			strSql += " and SendDate >='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			strSql += " and SendDate <='" + endDate + "'";
		}
		strSql += " order by SendDate desc ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(strSql);
		//SQLQuery query = CommonDao.GetOldDatabaseDao().getOpenedSession().createSQLQuery(strSql);
		
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();

		return list;

	}

	@Override
	public int recordCount(String userId, MsgUserMassage bean) {

		List<Object[]> list = new ArrayList<Object[]>();
		String strSql = "";
		String strMsgType = "1";
		String useId = userId;
		String readflag = "";
		String receiver = bean.getReceiver();
		String sender = bean.getSender();
		String title = bean.getTitle();
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();

		readflag = bean.getCaseType();
		strMsgType = bean.getMsgType();

		String strFilerReceiver = "";
		if (receiver != null && !"".equals(receiver)) {
			strFilerReceiver = " inner join cs_user b on A.EMPIDREC = b.id and b.name like '%"
					+ receiver + "%' ";
		}

		if (strMsgType == null || strMsgType.equals("")) {
			strMsgType = "1";
		}
		String strFilerSender = "";
		if (sender != null && !"".equals(sender)) {
			strFilerSender = " inner join cs_user c on a.empidsend = c.id and c.name like '%"
					+ bean.getSender() + "%' ";
		}

		if (strMsgType.equals("1")) {
			strSql = "select count(*) as count_num from T_MSG_USERMESSAGE A "
					+ strFilerSender
					+ " where  A.RECRDELETE='1' and A.RECRSTATE='1' and A.EMPIDREC="
					+ useId;
		}
		if (strMsgType.equals("2")) {
			strSql = "select count(*) as count_num from T_MSG_USERMESSAGE A "
					+ strFilerReceiver
					+ "  where  A.SENDDELETE='1' and A.STATE='0' and A.SENDSTATE='1' and A.EMPIDSEND="
					+ useId;
		}
		if (strMsgType.equals("3")) {
			strSql = "select count(*)  as count_num from T_MSG_USERMESSAGE A "
					+ strFilerReceiver
					+ " where  A.SENDDELETE='1' and A.STATE<>'0' and A.SENDSTATE='1' and A.EMPIDSEND="
					+ useId;
		}
		if (strMsgType.equals("4")) {
			strSql = "select count(*) as count_num from T_MSG_USERMESSAGE A "
					+ strFilerSender + strFilerReceiver;
			strSql += " where (A.SENDDELETE='1' and A.EMPIDSEND=" + useId
					+ " and A.SENDSTATE='0')";
			strSql += " or (A.RECRDELETE='1' and A.EMPIDREC=" + useId
					+ " and A.RECRSTATE='0')";
		}

		if (title != null && !"".equals(title)) {
			strSql += " and Title like '%" + bean.getTitle() + "%'";
		}

		if (beginDate != null && !"".equals(beginDate)) {
			strSql += " and SendDate >='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			strSql += " and SendDate <='" + endDate + "'";
		}

		if (strMsgType.equals("1")) {
			if (readflag.equals("1")) {
				strSql += " and State = 1 ";
			} else {
				strSql += " and State = 0 ";
			}
		}

		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(strSql);
		//SQLQuery query = CommonDao.GetOldDatabaseDao().getOpenedSession().createSQLQuery(strSql);
		query.addScalar("count_num", Hibernate.INTEGER);
		
		return (Integer) query.uniqueResult();

	}

	@Override
	public MsgUserMassage findMessageById(Long id) {

		String strSql = "select (select t.name from cs_user t where t.id=A.EMPIDSEND) as sender,";
		strSql += "nvl(b.name,' ') as receiver,A.TITLE,SENDDATE,SEEDATE,nvl(content,' ') as content,";
		strSql += " A.STATE,A.SID,A.EMPIDREC,nvl(a.msgcount ,0) as msgcount,A.EMPIDSEND from T_MSG_USERMESSAGE A  left join cs_user b on a.empidrec=b.id ";
		strSql += " where SID=" + id;
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(strSql);
		List list = query.setFirstResult(0).setMaxResults(1).list();

		MsgUserMassage bean = new MsgUserMassage();
		if (list != null && list.size() > 0) {
			Object[] arr = (Object[]) list.get(0);
			bean.setSid(id);
			bean.setSender(arr[0] + "");
			bean.setReceiver(arr[1] + "");
			bean.setTitle(arr[2] + "");
			bean.setSenddate(arr[3] + "");
			bean.setMsgcount(Integer.parseInt(arr[9].toString()));
			if (arr[4] == null) {
				bean.setSeedate("");
			} else {

				bean.setSeedate(arr[4] + "");
			}

			bean.setContent(arr[5] + "");
			bean.setEmpidsend(Long.valueOf(arr[10]+""));
			bean.setEmpidrec(Long.valueOf(arr[8]+""));
		}
		return bean;
	}

	@Override
	public int deleteItem(String msgType,Long id) {
		int rtn = 0;
		
		Transaction tx = null;
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String strSql="";
		try{
			String useId = AuthorUtil.GetLoginUserID(PageUtils.GetRequest());
			//String hql = "delete from T_MSG_USERMESSAGE  where sid=" + id;
			tx = session.beginTransaction();
			if (msgType.equals("1")) {
				strSql = "update T_MSG_USERMESSAGE set recrstate='0' where sid in("
						+ id;
				strSql += ") and empidrec=" + useId;
			}

			if (msgType.equals("2") || msgType.equals("3")) { 
				strSql = "update T_MSG_USERMESSAGE set sendstate='0' where sid in("
						+ id;
				strSql += ") and empidsend=" + useId;
			}

			if (msgType.equals("4")) { 
				strSql = "update T_MSG_USERMESSAGE set senddelete='0' where sid in("
						+ id;
				strSql += ") and empidsend=" + useId;

				session.createSQLQuery(strSql).executeUpdate();
				strSql = "update T_MSG_USERMESSAGE set recrdelete='0' where sid in("
						+ id;
				strSql += ") and empidrec=" + useId;
			}
			session.createSQLQuery(strSql).executeUpdate();
			
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
		}finally{
			if(session!=null){
				session.close();
			}
		}

		return rtn;
	}

	@Override
	public void updateMsgState(MsgUserMassage bean, String state) {
		// TODO Auto-generated method stub
		
		//Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Session session = CommonDao.GetOldDatabaseDao().getOpenedSession();
		Transaction tx = null;
		try{
			String sql="update T_MSG_USERMESSAGE set STATE='"+state+"',autoalert='1',seedate=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ";
			sql+=" where sid= "+bean.getSid()+" and (EMPIDREC =0 or EMPIDREC="+AuthorUtil.GetLoginUserID()+") and seedate is null";
			tx = session.beginTransaction();
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		}catch(Exception ex)
		{
			if(tx!=null){
				tx.rollback();
			}
			ex.printStackTrace();
		}
		//session.close();
		
	}

}
