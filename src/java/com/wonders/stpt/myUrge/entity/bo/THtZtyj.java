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

package com.wonders.stpt.myUrge.entity.bo;

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
 * THtZtyjʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_HT_ZTYJ")
public class THtZtyj implements Serializable, BusinessObject {

	private Long id; // id

	private String adddate; // adddate

	private String dept; // dept

	private String deptId; // deptId

	private Long incidentno; // incidentno

	private String operation; // operation

	private Long parentId; // parentId

	private String process; // process

	private String receiver; // receiver

	private String removed; // removed

	private String replaySuggest; // replaySuggest

	private String state; // state

	private String state2; // state2

	private String stepname; // stepname

	private String suggest; // suggest

	private String type; // type

	private String userfullname; // userfullname

	private String username; // username

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	@Column(name = "ADDDATE", nullable = true, length = 200)
	public String getAdddate() {
		return adddate;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "DEPT", nullable = true, length = 200)
	public String getDept() {
		return dept;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column(name = "DEPT_ID", nullable = true, length = 200)
	public String getDeptId() {
		return deptId;
	}

	public void setIncidentno(Long incidentno) {
		this.incidentno = incidentno;
	}

	@Column(name = "INCIDENTNO", nullable = true, length = 22)
	public Long getIncidentno() {
		return incidentno;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "OPERATION", nullable = true, length = 200)
	public String getOperation() {
		return operation;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARENT_ID", nullable = true, length = 22)
	public Long getParentId() {
		return parentId;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Column(name = "PROCESS", nullable = true, length = 200)
	public String getProcess() {
		return process;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "RECEIVER", nullable = true, length = 200)
	public String getReceiver() {
		return receiver;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 200)
	public String getRemoved() {
		return removed;
	}

	public void setReplaySuggest(String replaySuggest) {
		this.replaySuggest = replaySuggest;
	}

	@Column(name = "REPLAY_SUGGEST", nullable = true, length = 4000)
	public String getReplaySuggest() {
		return replaySuggest;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "STATE", nullable = true, length = 200)
	public String getState() {
		return state;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	@Column(name = "STATE2", nullable = true, length = 200)
	public String getState2() {
		return state2;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	@Column(name = "STEPNAME", nullable = true, length = 200)
	public String getStepname() {
		return stepname;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	@Column(name = "SUGGEST", nullable = true, length = 4000)
	public String getSuggest() {
		return suggest;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE", nullable = true, length = 200)
	public String getType() {
		return type;
	}

	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}

	@Column(name = "USERFULLNAME", nullable = true, length = 200)
	public String getUserfullname() {
		return userfullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERNAME", nullable = true, length = 200)
	public String getUsername() {
		return username;
	}

}
