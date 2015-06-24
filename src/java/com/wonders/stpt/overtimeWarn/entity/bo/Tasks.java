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
 * Tasksʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "TASKS")
public class Tasks implements Serializable, BusinessObject {

	private String taskid; // taskid

	private String assignedtouser; // assignedtouser

	private Float cost; // cost

	private Date delaytime; // delaytime

	private Date endtime; // endtime

	private String helpurl; // helpurl

	private Long incident; // incident

	private String localss; // localss

	private String notes; // notes

	private Date overduetime; // overduetime

	private String processname; // processname

	private Long processversion; // processversion

	private Date qendtime; // qendtime

	private Date qstarttime; // qstarttime

	private String recipient; // recipient

	private Long recipienttype; // recipienttype

	private String referer; // referer

	private Long scaned; // scaned

	private Date starttime; // starttime

	private Long status; // status

	private String stepid; // stepid

	private String steplabel; // steplabel

	private Long substatus; // substatus

	private Float taskrate; // taskrate

	private Long tasktime; // tasktime

	private String taskuser; // taskuser

	private Date urgentduetime; // urgentduetime

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	private String overTime ="";
	
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TASKID")
	public String getTaskid() {
		return taskid;
	}

	public void setAssignedtouser(String assignedtouser) {
		this.assignedtouser = assignedtouser;
	}

	@Column(name = "ASSIGNEDTOUSER", nullable = true, length = 1024)
	public String getAssignedtouser() {
		return assignedtouser;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	@Column(name = "COST", nullable = true, length = 53)
	public Float getCost() {
		return cost;
	}

	public void setDelaytime(Date delaytime) {
		this.delaytime = delaytime;
	}

	@Column(name = "DELAYTIME", nullable = true, length = 7)
	public Date getDelaytime() {
		return delaytime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	@Column(name = "ENDTIME", nullable = true, length = 7)
	public Date getEndtime() {
		return endtime;
	}

	public void setHelpurl(String helpurl) {
		this.helpurl = helpurl;
	}

	@Column(name = "HELPURL", nullable = true, length = 1024)
	public String getHelpurl() {
		return helpurl;
	}

	public void setIncident(Long incident) {
		this.incident = incident;
	}

	@Column(name = "INCIDENT", nullable = true, length = 20)
	public Long getIncident() {
		return incident;
	}

	public void setLocalss(String localss) {
		this.localss = localss;
	}

	@Column(name = "LOCALSS", nullable = true, length = 4000)
	public String getLocalss() {
		return localss;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "NOTES", nullable = true, length = 1024)
	public String getNotes() {
		return notes;
	}

	public void setOverduetime(Date overduetime) {
		this.overduetime = overduetime;
	}

	@Column(name = "OVERDUETIME", nullable = true, length = 7)
	public Date getOverduetime() {
		return overduetime;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	@Column(name = "PROCESSNAME", nullable = true, length = 512)
	public String getProcessname() {
		return processname;
	}

	public void setProcessversion(Long processversion) {
		this.processversion = processversion;
	}

	@Column(name = "PROCESSVERSION", nullable = true, length = 20)
	public Long getProcessversion() {
		return processversion;
	}

	public void setQendtime(Date qendtime) {
		this.qendtime = qendtime;
	}

	@Column(name = "QENDTIME", nullable = true, length = 7)
	public Date getQendtime() {
		return qendtime;
	}

	public void setQstarttime(Date qstarttime) {
		this.qstarttime = qstarttime;
	}

	@Column(name = "QSTARTTIME", nullable = true, length = 7)
	public Date getQstarttime() {
		return qstarttime;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "RECIPIENT", nullable = true, length = 512)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipienttype(Long recipienttype) {
		this.recipienttype = recipienttype;
	}

	@Column(name = "RECIPIENTTYPE", nullable = true, length = 20)
	public Long getRecipienttype() {
		return recipienttype;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	@Column(name = "REFERER", nullable = true, length = 64)
	public String getReferer() {
		return referer;
	}

	public void setScaned(Long scaned) {
		this.scaned = scaned;
	}

	@Column(name = "SCANED", nullable = true, length = 22)
	public Long getScaned() {
		return scaned;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Column(name = "STARTTIME", nullable = true, length = 7)
	public Date getStarttime() {
		return starttime;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "STATUS", nullable = true, length = 20)
	public Long getStatus() {
		return status;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	@Column(name = "STEPID", nullable = true, length = 64)
	public String getStepid() {
		return stepid;
	}

	public void setSteplabel(String steplabel) {
		this.steplabel = steplabel;
	}

	@Column(name = "STEPLABEL", nullable = true, length = 256)
	public String getSteplabel() {
		return steplabel;
	}

	public void setSubstatus(Long substatus) {
		this.substatus = substatus;
	}

	@Column(name = "SUBSTATUS", nullable = true, length = 20)
	public Long getSubstatus() {
		return substatus;
	}

	public void setTaskrate(Float taskrate) {
		this.taskrate = taskrate;
	}

	@Column(name = "TASKRATE", nullable = true, length = 53)
	public Float getTaskrate() {
		return taskrate;
	}

	public void setTasktime(Long tasktime) {
		this.tasktime = tasktime;
	}

	@Column(name = "TASKTIME", nullable = true, length = 20)
	public Long getTasktime() {
		return tasktime;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	@Column(name = "TASKUSER", nullable = true, length = 1024)
	public String getTaskuser() {
		return taskuser;
	}

	public void setUrgentduetime(Date urgentduetime) {
		this.urgentduetime = urgentduetime;
	}

	@Column(name = "URGENTDUETIME", nullable = true, length = 7)
	public Date getUrgentduetime() {
		return urgentduetime;
	}

}
