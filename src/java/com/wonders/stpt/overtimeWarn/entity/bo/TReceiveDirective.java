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

package com.wonders.stpt.overtimeWarn.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TReceiveDirective呈批
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_RECEIVE_DIRECTIVE")
public class TReceiveDirective implements Serializable, BusinessObject {

	private String id; // id

	private String activeid; // activeid

	private String attach; // attach

	private String attachs; // attachs

	private Long autScanFlag; // autScanFlag

	private String chairmanopinion; // chairmanopinion

	private String chiefPerson; // chiefPerson

	private String content; // content

	private String dealfile; // dealfile

	private String deptid; // deptid

	private String deptmaster; // deptmaster

	private String deptopinion; // deptopinion

	private String doclevel; // doclevel

	private String flag; // flag

	private String fleader; // fleader

	private String handle; // handle

	private String keyword; // keyword

	private String leaderopinion; // leaderopinion

	private String nbopinion; // nbopinion

	private String operateTime; // operateTime

	private String operator; // operator

	private String pbopinion; // pbopinion

	private String personname; // personname

	private String processinstanceid; // processinstanceid

	private String receiveDept; // receiveDept

	private String receivePerson; // receivePerson

	private Long removed; // removed

	private String saved; // saved

	private String status; // status

	private String submitdate; // submitdate

	private String submitdept; // submitdept

	private String taskid; // taskid

	private String theme; // theme

	private String title; // title

	private String typeTitle; // typeTitle

	private String workitemid; // workitemid

	private String xbopinion; // xbopinion

	private String xdept; // xdept

	private String xdeptamount; // xdeptamount

	private String zbopinion; // zbopinion

	private String zdept; // zdept

	private String zleader; // zleader
	
	private String processstatus;//完成状态
	private String year; //年份
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
	}

	private String overTime ="";
	private String beginDate="";
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private String endDate="";
	
	public TReceiveDirective(){
		
	}
	
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setActiveid(String activeid) {
		this.activeid = activeid;
	}

	@Column(name = "ACTIVEID", nullable = true, length = 50)
	public String getActiveid() {
		return activeid;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Column(name = "ATTACH", nullable = true, length = 4000)
	public String getAttach() {
		return attach;
	}

	public void setAttachs(String attachs) {
		this.attachs = attachs;
	}

	@Column(name = "ATTACHS", nullable = true, length = 4000)
	public String getAttachs() {
		return attachs;
	}

	public void setAutScanFlag(Long autScanFlag) {
		this.autScanFlag = autScanFlag;
	}

	@Column(name = "AUT_SCAN_FLAG", nullable = true, length = 22)
	public Long getAutScanFlag() {
		return autScanFlag;
	}

	public void setChairmanopinion(String chairmanopinion) {
		this.chairmanopinion = chairmanopinion;
	}

	@Column(name = "CHAIRMANOPINION", nullable = true, length = 1000)
	public String getChairmanopinion() {
		return chairmanopinion;
	}

	public void setChiefPerson(String chiefPerson) {
		this.chiefPerson = chiefPerson;
	}

	@Column(name = "CHIEF_PERSON", nullable = true, length = 200)
	public String getChiefPerson() {
		return chiefPerson;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT", nullable = true, length = 4000)
	public String getContent() {
		return content;
	}

	public void setDealfile(String dealfile) {
		this.dealfile = dealfile;
	}

	@Column(name = "DEALFILE", nullable = true, length = 4000)
	public String getDealfile() {
		return dealfile;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	@Column(name = "DEPTID", nullable = true, length = 50)
	public String getDeptid() {
		return deptid;
	}

	public void setDeptmaster(String deptmaster) {
		this.deptmaster = deptmaster;
	}

	@Column(name = "DEPTMASTER", nullable = true, length = 200)
	public String getDeptmaster() {
		return deptmaster;
	}

	public void setDeptopinion(String deptopinion) {
		this.deptopinion = deptopinion;
	}

	@Column(name = "DEPTOPINION", nullable = true, length = 1000)
	public String getDeptopinion() {
		return deptopinion;
	}

	public void setDoclevel(String doclevel) {
		this.doclevel = doclevel;
	}

	@Column(name = "DOCLEVEL", nullable = true, length = 20)
	public String getDoclevel() {
		return doclevel;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setFleader(String fleader) {
		this.fleader = fleader;
	}

	@Column(name = "FLEADER", nullable = true, length = 200)
	public String getFleader() {
		return fleader;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	@Column(name = "HANDLE", nullable = true, length = 1000)
	public String getHandle() {
		return handle;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "KEYWORD", nullable = true, length = 200)
	public String getKeyword() {
		return keyword;
	}

	public void setLeaderopinion(String leaderopinion) {
		this.leaderopinion = leaderopinion;
	}

	@Column(name = "LEADEROPINION", nullable = true, length = 1000)
	public String getLeaderopinion() {
		return leaderopinion;
	}

	public void setNbopinion(String nbopinion) {
		this.nbopinion = nbopinion;
	}

	@Column(name = "NBOPINION", nullable = true, length = 200)
	public String getNbopinion() {
		return nbopinion;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 200)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 200)
	public String getOperator() {
		return operator;
	}

	public void setPbopinion(String pbopinion) {
		this.pbopinion = pbopinion;
	}

	@Column(name = "PBOPINION", nullable = true, length = 1000)
	public String getPbopinion() {
		return pbopinion;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	@Column(name = "PERSONNAME", nullable = true, length = 4000)
	public String getPersonname() {
		return personname;
	}

	public void setProcessinstanceid(String processinstanceid) {
		this.processinstanceid = processinstanceid;
	}

	@Column(name = "PROCESSINSTANCEID", nullable = true, length = 50)
	public String getProcessinstanceid() {
		return processinstanceid;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	@Column(name = "RECEIVE_DEPT", nullable = true, length = 200)
	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	@Column(name = "RECEIVE_PERSON", nullable = true, length = 200)
	public String getReceivePerson() {
		return receivePerson;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public Long getRemoved() {
		return removed;
	}

	public void setSaved(String saved) {
		this.saved = saved;
	}

	@Column(name = "SAVED", nullable = true, length = 200)
	public String getSaved() {
		return saved;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", nullable = true, length = 50)
	public String getStatus() {
		return status;
	}

	public void setSubmitdate(String submitdate) {
		this.submitdate = submitdate;
	}

	@Column(name = "SUBMITDATE", nullable = true, length = 38)
	public String getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdept(String submitdept) {
		this.submitdept = submitdept;
	}

	@Column(name = "SUBMITDEPT", nullable = true, length = 1000)
	public String getSubmitdept() {
		return submitdept;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	@Column(name = "TASKID", nullable = true, length = 200)
	public String getTaskid() {
		return taskid;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Column(name = "THEME", nullable = true, length = 200)
	public String getTheme() {
		return theme;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	@Column(name = "TYPE_TITLE", nullable = true, length = 200)
	public String getTypeTitle() {
		return typeTitle;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}

	@Column(name = "WORKITEMID", nullable = true, length = 100)
	public String getWorkitemid() {
		return workitemid;
	}

	public void setXbopinion(String xbopinion) {
		this.xbopinion = xbopinion;
	}

	@Column(name = "XBOPINION", nullable = true, length = 1000)
	public String getXbopinion() {
		return xbopinion;
	}

	public void setXdept(String xdept) {
		this.xdept = xdept;
	}

	@Column(name = "XDEPT", nullable = true, length = 200)
	public String getXdept() {
		return xdept;
	}

	public void setXdeptamount(String xdeptamount) {
		this.xdeptamount = xdeptamount;
	}

	@Column(name = "XDEPTAMOUNT", nullable = true, length = 200)
	public String getXdeptamount() {
		return xdeptamount;
	}

	public void setZbopinion(String zbopinion) {
		this.zbopinion = zbopinion;
	}

	@Column(name = "ZBOPINION", nullable = true, length = 1000)
	public String getZbopinion() {
		return zbopinion;
	}

	public void setZdept(String zdept) {
		this.zdept = zdept;
	}

	@Column(name = "ZDEPT", nullable = true, length = 200)
	public String getZdept() {
		return zdept;
	}

	public void setZleader(String zleader) {
		this.zleader = zleader;
	}

	@Column(name = "ZLEADER", nullable = true, length = 200)
	public String getZleader() {
		return zleader;
	}

}
