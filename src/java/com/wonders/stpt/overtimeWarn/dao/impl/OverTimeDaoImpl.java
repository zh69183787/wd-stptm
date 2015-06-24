package com.wonders.stpt.overtimeWarn.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import com.wonders.stpt.overtimeWarn.dao.OverTimeDao;
import com.wonders.stpt.overtimeWarn.entity.bo.HtXx;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocReceive;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocSend;
import com.wonders.stpt.overtimeWarn.entity.bo.TReceiveDirective;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class OverTimeDaoImpl extends AbstractHibernateDaoImpl<Object> implements
		OverTimeDao {

	/**
	 * 生成收文查询的SQL语句
	 * 
	 * @param bean
	 * @return
	 */
	private String generateSql(TDocReceive bean) {
		String sql = "";
		String overTime = bean.getOverTime();

		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = "select b.overtime, "
					+ " dd.*, "
					+ " case pstatus "
					+ " when '1' then "
					+ " '13222' "
					+ " when '2' then "
					+ " '13223' "
					+ " when '3' then "
					+ " '15042' "
					+ " end as process_status, "
					+ " to_number(decode(dd.swnumber, "
					+ "  '下级单位', "
					+ " '0', "
					+ " '上级单位', "
					+ " '0', "
					+ " '外单位', "
					+ " '0', "
					+ " '市政府', "
					+ " '0', "
					+ " dd.swnumber)) as bb "
					+ " from (select t.swid, "
					+ " t.filedate swdate, "
					+ " t.id, "
					+ " t.swunit, "
					+ " t.filezh, "
					+ " t.num, "
					+ " t.title, "
					+ " t.swtype, "
					+ " t.modelid, "
					+ " t.instanceid, "
					+ " substr(t.swid, instr(t.swid, '-') + 1, length(t.swid)) as swnumber, "
					+ " case t.flag "
					+ " when '2' then "
					+ " '3' "
					+ " else "
					+ " to_char(ins.status) "
					+ " end as pstatus, "
					+ " T.REMARK "
					+ " from incidents ins, t_doc_receive t "
					+ " where t.removed = 0 "
					+ " and ins.processname = t.modelid "
					+ " and to_char(ins.incident) = t.instanceid "
					+ " and ins.status in (1, 2, 8) "
					+ " and t.modelid = '收文流程') dd, "
					+ " (select distinct s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ "  'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid = t3.taskid) "
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid = t3.taskid) "
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1 "
					+ " and  trunc(t3.starttime + nvl((t3.overduetime - t3.starttime), 0) + "
					+ overTime
					+ " -  sysdate )<= 0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where dd.modelid = b.pname "
					+ " and dd.instanceid = b.pincident ";
		}

		if (sql == null || sql.length() <= 0) {
			sql = "select * from T_DOC_RECEIVE where 2=1";
		}
		/********* 组合查询条件 *********/
		String year = bean.getSwtype();
		String type = bean.getSwid();
		String title = bean.getTitle();
		String num = bean.getNum();
		String filezh = bean.getFilezh();
		String swunit = bean.getSwunit();

		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();
		String unitType = bean.getUnittype();
		String pstatus = bean.getProcessstatus();

		if (type != null && !"".equals(type)) {
			sql += " and swid like '%" + type + "%'";
		}

		if (year != null && !"".equals(year)) {
			sql += " and swtype = '" + year + "'";
		}

		if (title != null && !"".equals(title)) {
			sql += " and title  like '%" + title + "%'";
		}

		if (num != null && !"".equals(num)) {
			sql += " and num  =" + num;
		}

		if (filezh != null && !"".equals(filezh)) {
			sql += " and filezh  like '%" + filezh + "%'";
		}

		if (swunit != null && !"".equals(swunit)) {
			sql += " and swunit  like '%" + swunit + "%'";
		}

		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and swdate>='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			sql += " and swdate<='" + endDate + "'";
		}

		if (unitType != null && !"".equals(unitType)) {
			sql += " and swid like '%" + unitType + "%'";
		}

		if (pstatus != null && !"".equals(pstatus)) {
			sql += " and pstatus ='" + pstatus + "'";
		}

		/********* 组合查询条件结束 *********/

		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where  exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.instanceid=zs.instanceid and zs2.overtime1=zs.overtime1) order by overtime2 desc,overtime1 desc, bb";
		}

		return sql;
	}

	/***
	 * 收文查询的分页查询方法
	 */
	@Override
	public List findRecordByPage(TDocReceive bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSql(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	/***
	 * 收文查询的记录条数
	 */
	@Override
	public int recordCount(TDocReceive bean) {
		String sql = this.generateSql(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	// 合同审批SQl
	public String generateSql(HtXx bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = " select "
					+ " b.overtime, "
					+ " aa.* "
					+ " from  "
					+

					" (select * from(select case t.flag "
					+ " when '0' then "
					+ " '13382' "
					+ " when '1' then "
					+ " '13383' "
					+ " else "
					+ " '' "
					+ " end as status, "
					+ " t.* "
					+ " from incidents ins,ht_xx t "
					+ " where t.removed = 0"
					+ " and ins.processname = t.model_id "
					+ " and to_char(ins.incident) = t.pinstance_id"
					+ " ) where 1=1) aa , "
					+

					" (select distinct s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid=t3.taskid )"
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid=t3.taskid )"
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1"
					+ " and trunc(t3.starttime+nvl((t3.overduetime-t3.starttime),0)+"
					+ overTime
					+ "-sysdate)<=0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where aa.model_id = b.pname "
					+ " and aa.pinstance_id = b.pincident ";
		}

		if (sql == null || sql.length() <= 0) {
			sql = "select * from HT_XX where 2=1";
		}

		// 组合查询
		String planNum = bean.getPlanNum(); // 计划编号5
		String contractNum = bean.getContractNum();// 合同编号9
		String selfNum = bean.getSelfNum();// 备用编号44
		String contractName = bean.getContractName();// 合同名称6
		String projectNum = bean.getProjectNum();// 工程编号10
		String signCop = bean.getSignCop();// 对方单位46
		String addPerson = bean.getAddPerson();// 登记人15
		String contractMoney = bean.getContractMoney();// 合同价11

		String status = bean.getStatus(); // 合同状态49
		//String overTime1 = bean.getOverTime(); // 超时天数1
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();

		if (planNum != null && !"".equals(planNum)) {
			sql += " and plan_num like '%" + planNum + "%'";
		}

		if (contractNum != null && !"".equals(contractNum)) {
			sql += " and CONTRACT_NUM like '%" + contractNum + "%'";
		}

		if (selfNum != null && !"".equals(selfNum)) {
			sql += " and SELF_NUM  like '%" + selfNum + "%'";
		}

		if (contractName != null && !"".equals(contractName)) {
			sql += " and CONTRACT_NAME  like'%" + contractName + "%'";
		}

		if (projectNum != null && !"".equals(projectNum)) {
			sql += " and PROJECT_NUM  like '%" + projectNum + "%'";
		}

		if (signCop != null && !"".equals(signCop)) {
			sql += " and SIGN_COP  like '%" + signCop + "%'";
		}

		if (addPerson != null && !"".equals(addPerson)) {
			sql += " and ADD_PERSON  like '%" + addPerson + "%'";
		}

		if (contractMoney != null && !"".equals(contractMoney)) {
			sql += " and CONTRACT_MONEY  like '%" + contractMoney + "%'";
		}

		if (status != null && !"".equals(status)) {
			sql += " and FLAG = '" + status + "'";
		}

		/*if (overTime1 != null && !"".equals(overTime1)) {
			sql += " and overTime1>='" + overTime1 + "'";
		}*/

		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and ADD_TIME>='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			sql += " and ADD_TIME<='" + endDate + "'";
		}

		// 组合查询结束
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where not exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.pinstance_id=zs.pinstance_id and zs2.overtime1>zs.overtime1) order by overtime2 desc,overtime1 desc,add_time desc";
		}
		return sql;
	}

	@Override
	public int recordCount(HtXx bean) {
		String sql = this.generateSql(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	@Override
	public List findRecordByPage(HtXx bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSql(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	// ++1
	public String generateSqli(HtXx bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = " select "
					+ " b.overtime, "
					+ " aa.* "
					+ " from  "
					+

					" (select * from(select case t.flag "
					+ " when '0' then "
					+ " '13382' "
					+ " when '1' then "
					+ " '13383' "
					+ " else "
					+ " '' "
					+ " end as status, "
					+ " t.* "
					+ " from incidents ins,ht_xx t "
					+ " where t.removed = 0"
					+ " and ins.processname = t.model_id "
					+ " and to_char(ins.incident) = t.pinstance_id"
					+ " ) where 1=1) aa , "
					+

					" (select distinct s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid=t3.taskid )"
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid=t3.taskid )"
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1"
					+ " and trunc(t3.starttime+nvl((t3.overduetime-t3.starttime),0)+"
					+ overTime
					+ "-sysdate)<=0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where aa.model_id = b.pname "
					+ " and aa.pinstance_id = b.pincident ";
		}

		if (sql == null || sql.length() <= 0) {
			sql = "select * from HT_XX where 2=1";
		}

		// 组合查询
		String planNum = bean.getPlanNum(); // 计划编号5
		String contractNum = bean.getContractNum();// 合同编号9
		String selfNum = bean.getSelfNum();// 备用编号44
		String contractName = bean.getContractName();// 合同名称6
		String projectNum = bean.getProjectNum();// 工程编号10
		String signCop = bean.getSignCop();// 对方单位46
		String addPerson = bean.getAddPerson();// 登记人15
		String contractMoney = bean.getContractMoney();// 合同价11

		String status = bean.getStatus(); // 合同状态 49
		String overTime1 = bean.getOverTime(); // 超时天数1
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();

		if (planNum != null && !"".equals(planNum)) {
			sql += " and plan_num like '%" + planNum + "%'";
		}

		if (contractNum != null && !"".equals(contractNum)) {
			sql += " and CONTRACT_NUM like '%" + contractNum + "%'";
		}

		if (selfNum != null && !"".equals(selfNum)) {
			sql += " and SELF_NUM  like '%" + selfNum + "%'";
		}

		if (contractName != null && !"".equals(contractName)) {
			sql += " and CONTRACT_NAME  like'%" + contractName + "%'";
		}

		if (projectNum != null && !"".equals(projectNum)) {
			sql += " and PROJECT_NUM  like '%" + projectNum + "%'";
		}

		if (signCop != null && !"".equals(signCop)) {
			sql += " and SIGN_COP  like '%" + signCop + "%'";
		}

		if (addPerson != null && !"".equals(addPerson)) {
			sql += " and ADD_PERSON  like '%" + addPerson + "%'";
		}

		if (contractMoney != null && !"".equals(contractMoney)) {
			sql += " and CONTRACT_MONEY  like '%" + contractMoney + "%'";
		}

		if (status != null && !"".equals(status)) {
			sql += " and FLAG = '" + status + "'";
		}

		/*if (overTime1 != null && !"".equals(overTime1)) {
			sql += " and overTime1>='" + overTime1 + "'";
		}*/

		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and ADD_TIME>='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			sql += " and ADD_TIME<='" + endDate + "'";
		}

		// 组合查询结束
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where not exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.pinstance_id=zs.pinstance_id and zs2.overtime1>zs.overtime1) order by overtime2 desc,overtime1 desc,add_time desc";
		}
		return sql;
	}

	@Override
	public List findRecordByPagei(HtXx bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSqli(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;

	}

	@Override
	public int recordCounti(HtXx bean) {
		String sql = this.generateSqli(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	//

	// 2
	public String generateSqlt(HtXx bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = " select "
					+ " b.overtime, "
					+ " aa.* "
					+ " from  "
					+

					" (select * from(select case t.flag "
					+ " when '0' then "
					+ " '13382' "
					+ " when '1' then "
					+ " '13383' "
					+ " else "
					+ " '' "
					+ " end as status, "
					+ " t.* "
					+ " from incidents ins,ht_xx t "
					+ " where t.removed = 0"
					+ " and ins.processname = t.model_id "
					+ " and to_char(ins.incident) = t.pinstance_id"
					+ " ) where 1=1) aa , "
					+

					" (select distinct s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid=t3.taskid )"
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid=t3.taskid )"
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1"
					+ " and trunc(t3.starttime+nvl((t3.overduetime-t3.starttime),0)+"
					+ overTime
					+ "-sysdate)<=0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where aa.model_id = b.pname "
					+ " and aa.pinstance_id = b.pincident ";
		}

		if (sql == null || sql.length() <= 0) {
			sql = "select * from HT_XX where 2=1";
		}

		// 组合查询
		String planNum = bean.getPlanNum(); // 计划编号5
		String contractNum = bean.getContractNum();// 合同编号9
		String selfNum = bean.getSelfNum();// 备用编号44
		String contractName = bean.getContractName();// 合同名称6
		String projectNum = bean.getProjectNum();// 工程编号10
		String signCop = bean.getSignCop();// 对方单位46
		String addPerson = bean.getAddPerson();// 登记人15
		String contractMoney = bean.getContractMoney();// 合同价11
		String status = bean.getStatus(); // 合同状态 54
		String overTime1 = bean.getOverTime(); // 超时天数1
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();

		if (planNum != null && !"".equals(planNum)) {
			sql += " and plan_num like '%" + planNum + "%'";
		}

		if (contractNum != null && !"".equals(contractNum)) {
			sql += " and CONTRACT_NUM like '%" + contractNum + "%'";
		}

		if (selfNum != null && !"".equals(selfNum)) {
			sql += " and SELF_NUM  like '%" + selfNum + "%'";
		}

		if (contractName != null && !"".equals(contractName)) {
			sql += " and CONTRACT_NAME  like'%" + contractName + "%'";
		}

		if (projectNum != null && !"".equals(projectNum)) {
			sql += " and PROJECT_NUM  like '%" + projectNum + "%'";
		}

		if (signCop != null && !"".equals(signCop)) {
			sql += " and SIGN_COP  like '%" + signCop + "%'";
		}

		if (addPerson != null && !"".equals(addPerson)) {
			sql += " and ADD_PERSON  like '%" + addPerson + "%'";
		}

		if (contractMoney != null && !"".equals(contractMoney)) {
			sql += " and CONTRACT_MONEY  like '%" + contractMoney + "%'";
		}

		if (status != null && !"".equals(status)) {
			sql += " and FlAG ='" + status + "'";
		}

		/*if (overTime1 != null && !"".equals(overTime1)) {
			sql += " and overTime1>='" + overTime1 + "'";
		}*/

		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and ADD_TIME>='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			sql += " and ADD_TIME<='" + endDate + "'";
		}

		// 组合查询结束
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where not exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.pinstance_id=zs.pinstance_id and zs2.overtime1>zs.overtime1) order by overtime2 desc,overtime1 desc,add_time desc";
		}
		return sql;
	}

	@Override
	public List findRecordByPaget(HtXx bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSqlt(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	@Override
	public int recordCountt(HtXx bean) {
		String sql = this.generateSqlt(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	/***
	 * 生成发文sql
	 * 
	 * @param bean
	 * @return
	 */
	private String generateSql(TDocSend bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = " select  b.overtime, aa.*, "
					+ " case pstatus "
					+ " when '1' then "
					+ " '13222' "
					+ " when '2' then "
					+ " '13223' "
					+ " when '3' then "
					+ " '15042' "
					+ " end as processstatus "
					+ " from ((select * "
					+ " from (select t.id, "
					+ " to_char(ta.endtime, 'YYYY-MM-DD') send_date, "
					+ " t.code1, "
					+ " t.code2, "
					+ " decode(t.code3, null, '0', t.code3) code3, "
					+ " t.send_id, "
					+ " t.doc_title, "
					+ " t.doc_count, "
					+ " ROW_NUMBER() OVER(PARTITION BY t.modelid || ' ' || t.pinstanceid ORDER BY TA.ENDTIME DESC) RN, "
					+ " TA.ASSIGNEDTOUSER, "
					+ " CU.NAME, "
					+ " t.operator, "
					+ " t.send_main || ' ' || t.send_inside || ' ' || "
					+ " t.send_main_w || ' ' || t.sned_copy AS CONTENT, "
					+ " t.modelid, "
					+ " t.pinstanceid, "
					+ " '' REMARK, "
					+ " case t.flag "
					+ " when '2' then "
					+ " '3' "
					+ " else "
					+ " to_char(ins.status) "
					+ " end as pstatus "
					+ " from TASKS      TA, "
					+ " incidents  ins, "
					+ " t_doc_send t, "
					+ " CS_USER    CU "
					+ " where t.removed = 0 "
					+ " and t.modelid = '发文流程' "
					+ " and t.modelid = ins.processname "
					+ " and t.pinstanceid = TO_CHAR(ins.INCIDENT) "
					+ " and EXISTS "
					+ " (SELECT '' "
					+ " FROM T_SUBPROCESS TS "
					+ " WHERE TS.PNAME = T.MODELID "
					+ " AND TS.CNAME = '签发领导子流程' "
					+ " AND TS.PINCIDENT = t.pinstanceid "
					+ " AND TS.CNAME = TA.PROCESSNAME "
					+ " AND TS.CINCIDENT = TO_CHAR(TA.INCIDENT)) "
					+ " AND 'ST/' || CU.LOGIN_NAME = UPPER(ASSIGNEDTOUSER) "
					+ " AND TA.TASKUSER LIKE 'ST/%' "
					+ " AND TA.STEPLABEL = '签发领导' "
					+ " and ins.status = 2) "
					+ " where rn = 1) union "
					+ " (select * "
					+ " from (select t.id, "
					+ " '' as send_date, "
					+ " t.code1, "
					+ " t.code2, "
					+ " decode(t.code3, null, '0', t.code3) code3, "
					+ " t.send_id, "
					+ " t.doc_title, "
					+ " t.doc_count, "
					+ " 1 RN, "
					+ " '' as ASSIGNEDTOUSER, "
					+ " '' as NAME, "
					+ " t.operator, "
					+ " t.send_main || ' ' || t.send_inside || ' ' || "
					+ " t.send_main_w || ' ' || t.sned_copy AS CONTENT, "
					+ " t.modelid, "
					+ " t.pinstanceid, "
					+ " '' REMARK, "
					+ " case t.flag "
					+ " when '2' then "
					+ " '3' "
					+ " else "
					+ " to_char(ins.status) "
					+ " end as pstatus "
					+ " from incidents ins, t_doc_send t "
					+ " where t.removed = 0 "
					+ " and t.modelid = '发文流程' "
					+ " and t.modelid = ins.processname "
					+ " and t.pinstanceid = TO_CHAR(ins.INCIDENT) "
					+ " and ins.status = 1) "
					+ " where rn = 1)) aa, "
					+ " (select distinct  s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid = t3.taskid) "
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid = t3.taskid) "
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1 "
					+ " and  trunc(t3.starttime + nvl((t3.overduetime - t3.starttime), 0) + "
					+ overTime
					+ " - "
					+ " sysdate )<= 0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where aa.modelid = b.pname "
					+ " and aa.pinstanceid = b.pincident";
		}
		if (sql == null || sql.length() <= 0) {
			sql = "select * from T_DOC_SEND where 2=1";
		}
		/********* 组合查询条件 *********/
		String name = bean.getSendId();// 文件字号
		String title = bean.getDocTitle();// 文件题名
		String count = bean.getDocCount();// 页数
		String persname = bean.getName();// 签发
		String person = bean.getOperator();// 经办
		String dept = bean.getContent();// 主抄送机关
		String status = bean.getProcessstatus();// 完成状态
		String beginDate = bean.getBeginDate();// 开始时间
		String endDate = bean.getEndDate();// 结束时间
		String year = bean.getYear();// 年份时间
		String unittype = bean.getUnittype();// 分类
		if (unittype != null && !"".equals(unittype)) {
			sql += " and CONTENT like '%" + unittype + "%'";
		}
		if (year != null && !"".equals(year)) {
			sql += " and SEND_DATE like '%" + year + "%'";
		}
		if (name != null && !"".equals(name)) {
			sql += " and SEND_ID like '%" + name + "%'";
		}
		if (title != null && !"".equals(title)) {
			sql += " and DOC_TITLE like '%" + title + "%'";
		}
		if (count != null && !"".equals(count)) {
			sql += " and doc_Count like '%" + count + "%'";
		}
		if (person != null && !"".equals(persname)) {
			sql += " and NAME like '%" + persname + "%'";
		}
		if (person != null && !"".equals(person)) {
			sql += " and operator like '%" + person + "%'";
		}
		if (dept != null && !"".equals(dept)) {
			sql += " and CONTENT like '%" + dept + "%'";
		}
		if (status != null && !"".equals(status)) {
			sql += " and pstatus='" + status + "'";
		}
		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and SEND_DATE>='" + beginDate + "'";
		}

		if (endDate != null && !"".equals(endDate)) {
			sql += " and SEND_DATE<='" + endDate + "'";
		}
		/********* 组合查询条件结束 *********/
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where  exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.pinstanceid=zs.pinstanceid and zs2.overtime1=zs.overtime1) order by overtime2 desc,overtime1 desc,to_number(code3)";
		}

		return sql;

	}

	/**
	 * 发文查询的分页查询方法
	 */
	@SuppressWarnings("unchecked")
	public List findRecordByPage(TDocSend bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSql(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	/***
	 * 发文查询的记录条数
	 */
	public int recordCount(TDocSend bean) {
		String sql = this.generateSql(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	/***
	 * 生成呈批sql
	 * 
	 * @param bean
	 * @return
	 */
	private String generateSql(TReceiveDirective bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {

			sql = " select b.overtime,aa.*,"
					+ " case pstatus"
					+ " when '1' then"
					+ " '13222'"
					+ " when '2' then"
					+ " '13223'"
					+ " when '3' then"
					+ " '15042'"
					+ " end as processstatus"
					+ " from (select t.deptid,"
					+ " t.submitdate,"
					+ " t.id,"
					+ " t.submitdept,"
					+ " t.doclevel,"
					+ " t.title,"
					+ " t.zleader,"
					+ " t.xdept,"
					+ " t.operator,"
					+ " t.processinstanceid,"
					+ " t.activeid,"
					+ " decode(t.zdept, null, '0', t.zdept) zdept,"
					+ " '' REMARK,"
					+ " case t.flag"
					+ " when '2' then"
					+ " '3'"
					+ " else"
					+ " to_char(ins.status)"
					+ " end as pstatus"
					+ " from incidents ins, t_receive_directive t"
					+ " where t.removed = 0"
					+ " and t.activeid = ins.processname"
					+ " and t.processinstanceid = to_char(ins.incident)"
					+ " and ins.status in (1, 2, 8)) aa,"
					+

					" (select distinct s.pname,"
					+ " s.pincident,"
					+ " (case"
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'),"
					+ " 'YYYY.MM.DD') -"
					+ " to_date(to_char(t3.overduetime,"
					+ " 'YYYY.MM.DD'),"
					+ " 'YYYY.MM.DD')) > 0 and"
					+ " t3.overduetime is not null then"
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' ||"
					+ " to_char(to_date(to_char(sysdate,"
					+ " 'YYYY.MM.DD'),"
					+ " 'YYYY.MM.DD') -"
					+ " to_date(to_char(t.overduetime,"
					+ " 'YYYY.MM.DD'),"
					+ " 'YYYY.MM.DD')) ||"
					+ " '天)</b></font>') tt"
					+ " from tasks t"
					+ " where t.status = 1"
					+ " and t.taskid = t3.taskid)"
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2"
					+ " where t2.status = 1"
					+ " and t2.taskid = t3.taskid)"
					+ " end) overtime"
					+ " from tasks t3, t_subprocess s"
					+ " where t3.processname = s.cname"
					+ " and t3.incident = s.cincident"
					+ " and t3.status = 1"
					+ " and trunc(t3.starttime + nvl((t3.overduetime - t3.starttime), 0) +"
					+ overTime
					+ " -"
					+ " sysdate) <= 0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b" + " where aa.activeid = b.pname"
					+ " and aa.processinstanceid = b.pincident";

		}
		if (sql == null || sql.length() <= 0) {
			sql = "select * from T_RECEIVE_DIRECTIVE where 2=1";
		}
		/********* 组合查询条件 *********/
		String zleader = bean.getZleader();// 部门字号
		String deptid = bean.getDeptid();// 字号
		String title = bean.getTitle();// 文件题名
		String submitDept = bean.getSubmitdept();// 呈报部门
		String status = bean.getProcessstatus();// 完成状态
		String beginDate = bean.getBeginDate();// 开始时间
		String endDate = bean.getEndDate();// 结束时间
		String year = bean.getYear();// 年份
		if (year != null && !"".equals(year)) {
			sql += " and SUBMITDATE like '%" + year + "%'";
		}
		if (submitDept != null && !"".equals(submitDept)) {
			sql += " and submitdept like '%" + submitDept + "%'";
		}
		if (status != null && !"".equals(status)) {
			sql += " and pstatus ='" + status + "' ";
		}
		if (zleader != null && !"".equals(zleader)) {
			sql += " and ZLEADER = '" + zleader + "'";
		}
		if (deptid != null && !"".equals(deptid)) {
			sql += " and DEPTID like '%" + deptid + "%'";
		}
		if (title != null && !"".equals(title)) {
			sql += " and TITLE like '%" + title + "%'";
		}
		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and SUBMITDATE>='" + beginDate + "'";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and SUBMITDATE<='" + endDate + "'";
		}
		/********* 组合查询条件结束 *****/
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.processinstanceid = zs.processinstanceid and zs2.overtime1=zs.overtime1) order by overtime2 desc,overtime1 desc,zdept";
		}

		return sql;
	}

	/**
	 * 呈报查询的分页查询方法
	 */
	@SuppressWarnings("unchecked")
	public List findRecordByPage(TReceiveDirective bean, int startRow,
			int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSql(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	/**
	 * 呈报查询的记录条数
	 */
	public int recordCount(TReceiveDirective bean) {
		String sql = this.generateSql(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

	/**
	 * 合同审批（新）
	 */
	private String generateSqlL(HtXx bean) {
		String sql = "";
		String overTime = bean.getOverTime();
		if (overTime == null || "".equals(overTime)) {
			overTime = "0";
		}
		if (overTime != null && !"".equals(overTime)) {
			sql = " select "
					+ " b.overtime, "
					+ " aa.* "
					+ " from  "
					+

					" (select * from(select case t.flag "
					+ " when '0' then "
					+ " '13382' "
					+ " when '1' then "
					+ " '13383' "
					+ " else "
					+ " '' "
					+ " end as status, "
					+ " t.* "
					+ " from incidents ins,ht_xx t "
					+ " where t.removed = 0"
					+ " and ins.processname = t.model_id "
					+ " and to_char(ins.incident) = t.pinstance_id"
					+ " ) where 1=1) aa , "
					+

					" (select distinct s.pname, "
					+ " s.pincident, "
					+ " (case "
					+ " when (to_date(to_char(sysdate, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t3.overduetime, 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) > 0 and "
					+ " t3.overduetime is not null then "
					+ " (select ('<img src=\"../images/task_exp.gif\"></img>  <font color=\"red\"><b>(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t.overduetime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)</b></font>') tt "
					+ " from tasks t "
					+ " where t.status = 1 "
					+ " and t.taskid=t3.taskid )"
					+ " when t3.overduetime is null "
					+ " then "
					+ " (select ('(超时 ' || "
					+ " to_char(to_date(to_char(sysdate, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD') - "
					+ " to_date(to_char(t2.starttime, "
					+ " 'YYYY.MM.DD'), "
					+ " 'YYYY.MM.DD')) || "
					+ " '天)') "
					+ " from tasks t2 "
					+ " where t2.status = 1 "
					+ " and t2.taskid=t3.taskid )"
					+ " end) overtime "
					+ " from tasks t3, t_subprocess s "
					+ " where t3.processname = s.cname "
					+ " and t3.incident = s.cincident "
					+ " and t3.status = 1"
					+ " and trunc(t3.starttime+nvl((t3.overduetime-t3.starttime),0)+"
					+ overTime
					+ "-sysdate)<=0 "
					+ " and t3.assignedtouser like 'ST/%' and ( t3.recipient  not like '%_WF' or t3.recipient is null)"
					+ " ) b " + " where aa.model_id = b.pname "
					+ " and aa.pinstance_id = b.pincident ";
		}
		if (sql == null || sql.length() <= 0) {
			sql = "select * from HT_XX where 2=1";
		}
		/********* 组合查询条件 *********/
		String planNum = bean.getPlanNum();// 计划编号
		String contractNum = bean.getContractNum();// 合同编号
		String selfNum = bean.getSelfNum();// 自用编号
		String countractName = bean.getContractName();// 合同名称
		String projectNum = bean.getProjectNum();// 工程编号
		String signCop = bean.getSignCop();// 对方单位
		String addPerson = bean.getAddPerson();// 登记人
		String coutractMoney = bean.getContractMoney();// 合同价
		String pstatus = bean.getStatusHt();
		String beginDate = bean.getBeginDate();
		String endDate = bean.getEndDate();
		if (pstatus != null && !"".equals(pstatus)) {
			sql += " and flag = '" + pstatus + "'";
		}
		if (planNum != null && !"".equals(planNum)) {
			sql += " and PLAN_NUM like '%" + planNum + "%'";
		}
		if (contractNum != null && !"".equals(contractNum)) {
			sql += " and CONTRACT_NUM like '%" + contractNum + "%'";
		}
		if (selfNum != null && !"".equals(selfNum)) {
			sql += " and SELF_NUM like '%" + selfNum + "%'";
		}
		if (countractName != null && !"".equals(countractName)) {
			sql += " and CONTRACT_NAME like '%" + countractName + "%'";
		}
		if (projectNum != null && !"".equals(projectNum)) {
			sql += " and PROJECT_NUM like '%" + projectNum + "%'";
		}
		if (signCop != null && !"".equals(signCop)) {
			sql += " and SIGN_COP like '%" + signCop + "%'";
		}
		if (addPerson != null && !"".equals(addPerson)) {
			sql += " and ADD_PERSON like '%" + addPerson + "%'";
		}
		if (coutractMoney != null && !"".equals(coutractMoney)) {
			sql += " and CONTRACT_MONEY like '%" + coutractMoney + "%'";
		}
		if (coutractMoney != null && !"".equals(coutractMoney)) {
			sql += " and CONTRACT_MONEY like '%" + coutractMoney + "%'";
		}
		if (beginDate != null && !"".equals(beginDate)) {
			sql += "and ADD_TIME>='" + beginDate + "'";
		}
		if (endDate != null && !"".equals(endDate)) {
			sql += " and ADD_TIME<='" + endDate + "'";
		}
		/********* 组合查询结束 *********/
		if (overTime != null && !"".equals(overTime)) {
			sql = " select * from ( "
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number "
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3))  overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs "
					+ " where  exists  "
					+ " (select * from ("
					+ " select case instr(zz.overtime,'img') "
					+ " when 2 then 1"
					+ " else 0 end as overtime2, "
					+ " to_number"
					+ " (substr(zz.overtime,instr(zz.overtime,'超时 ')+3,instr(zz.overtime,'天')-instr(zz.overtime,'超时 ')-3)) overtime1, "
					+ " zz.* from( "
					+ " "
					+ sql
					+ " "
					+ " )zz "
					+ " )zs2 where zs2.pinstance_id=zs.pinstance_id and zs2.overtime1=zs.overtime1) order by overtime2 desc,overtime1 desc,add_time desc";
		}
		return sql;
	}

	@SuppressWarnings("unchecked")
	public List findRecordByPageL(HtXx bean, int startRow, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = this.generateSqlL(bean);
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}

	public int recordCountL(HtXx bean) {
		String sql = this.generateSqlL(bean);
		sql = "select count(*) as count_num from ( " + sql + " ) a ";
		SQLQuery query = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		return (Integer) query.uniqueResult();
	}

}
