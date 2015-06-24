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
 * Incidentsʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class IncidentsVO implements ValueObject {

	private Long incident;
	private String processname;
	private Date endtime;
	private String helpurl;
	private String initiator;
	private String mainss;
	private String parenttaskid;
	private String priorities;
	private Long priority;
	private Long processversion;
	private Date starttime;
	private Long status;
	private String summary;
	private Date timelimit;

	public void setIncident(Long incident) {
		this.incident = incident;
	}

	public Long getIncident() {
		return incident;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getProcessname() {
		return processname;
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

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setMainss(String mainss) {
		this.mainss = mainss;
	}

	public String getMainss() {
		return mainss;
	}

	public void setParenttaskid(String parenttaskid) {
		this.parenttaskid = parenttaskid;
	}

	public String getParenttaskid() {
		return parenttaskid;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}

	public String getPriorities() {
		return priorities;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getPriority() {
		return priority;
	}

	public void setProcessversion(Long processversion) {
		this.processversion = processversion;
	}

	public Long getProcessversion() {
		return processversion;
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

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return summary;
	}

	public void setTimelimit(Date timelimit) {
		this.timelimit = timelimit;
	}

	public Date getTimelimit() {
		return timelimit;
	}

}
