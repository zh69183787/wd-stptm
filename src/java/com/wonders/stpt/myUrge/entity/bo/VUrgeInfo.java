package com.wonders.stpt.myUrge.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * VUrgeInfoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class VUrgeInfo implements Serializable, BusinessObject {

	// Fields

	private String priorities;
	private String processname;
	private String summary;
	private String username;
	private String deptname;
	private String incident;
	private String initiator;
	private String priorities_show;

	public String getPriorities() {
		return this.priorities;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}
	public String getProcessname() {
		return this.processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getIncident() {
		return this.incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String geIinitiator() {
		return this.initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getPriorities_show() {
		return this.priorities_show;
	}

	public void setPriorities_show(String priorities_show) {
		this.priorities_show = priorities_show;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}