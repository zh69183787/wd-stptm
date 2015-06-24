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

package com.wonders.stpt.overtimeWarn.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * Tasksʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class TasksVO implements ValueObject {

	private String taskid;
	private String assignedtouser;
	private Float cost;
	private Date delaytime;
	private Date endtime;
	private String helpurl;
	private Long incident;
	private String localss;
	private String notes;
	private Date overduetime;
	private String processname;
	private Long processversion;
	private Date qendtime;
	private Date qstarttime;
	private String recipient;
	private Long recipienttype;
	private String referer;
	private Long scaned;
	private Date starttime;
	private Long status;
	private String stepid;
	private String steplabel;
	private Long substatus;
	private Float taskrate;
	private Long tasktime;
	private String taskuser;
	private Date urgentduetime;

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setAssignedtouser(String assignedtouser) {
		this.assignedtouser = assignedtouser;
	}

	public String getAssignedtouser() {
		return assignedtouser;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getCost() {
		return cost;
	}

	public void setDelaytime(Date delaytime) {
		this.delaytime = delaytime;
	}

	public Date getDelaytime() {
		return delaytime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setHelpurl(String helpurl) {
		this.helpurl = helpurl;
	}

	public String getHelpurl() {
		return helpurl;
	}

	public void setIncident(Long incident) {
		this.incident = incident;
	}

	public Long getIncident() {
		return incident;
	}

	public void setLocalss(String localss) {
		this.localss = localss;
	}

	public String getLocalss() {
		return localss;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setOverduetime(Date overduetime) {
		this.overduetime = overduetime;
	}

	public Date getOverduetime() {
		return overduetime;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessversion(Long processversion) {
		this.processversion = processversion;
	}

	public Long getProcessversion() {
		return processversion;
	}

	public void setQendtime(Date qendtime) {
		this.qendtime = qendtime;
	}

	public Date getQendtime() {
		return qendtime;
	}

	public void setQstarttime(Date qstarttime) {
		this.qstarttime = qstarttime;
	}

	public Date getQstarttime() {
		return qstarttime;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipienttype(Long recipienttype) {
		this.recipienttype = recipienttype;
	}

	public Long getRecipienttype() {
		return recipienttype;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getReferer() {
		return referer;
	}

	public void setScaned(Long scaned) {
		this.scaned = scaned;
	}

	public Long getScaned() {
		return scaned;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getStatus() {
		return status;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public String getStepid() {
		return stepid;
	}

	public void setSteplabel(String steplabel) {
		this.steplabel = steplabel;
	}

	public String getSteplabel() {
		return steplabel;
	}

	public void setSubstatus(Long substatus) {
		this.substatus = substatus;
	}

	public Long getSubstatus() {
		return substatus;
	}

	public void setTaskrate(Float taskrate) {
		this.taskrate = taskrate;
	}

	public Float getTaskrate() {
		return taskrate;
	}

	public void setTasktime(Long tasktime) {
		this.tasktime = tasktime;
	}

	public Long getTasktime() {
		return tasktime;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	public String getTaskuser() {
		return taskuser;
	}

	public void setUrgentduetime(Date urgentduetime) {
		this.urgentduetime = urgentduetime;
	}

	public Date getUrgentduetime() {
		return urgentduetime;
	}

}
