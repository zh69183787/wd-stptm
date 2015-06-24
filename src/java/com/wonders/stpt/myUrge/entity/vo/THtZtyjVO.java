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

package com.wonders.stpt.myUrge.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * THtZtyjʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class THtZtyjVO implements ValueObject {

	private Long id;
	private String adddate;
	private String dept;
	private String deptId;
	private Long incidentno;
	private String operation;
	private Long parentId;
	private String process;
	private String receiver;
	private String removed;
	private String replaySuggest;
	private String state;
	private String state2;
	private String stepname;
	private String suggest;
	private String type;
	private String userfullname;
	private String username;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}

	public String getAdddate() {
		return adddate;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDept() {
		return dept;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setIncidentno(Long incidentno) {
		this.incidentno = incidentno;
	}

	public Long getIncidentno() {
		return incidentno;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getProcess() {
		return process;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setReplaySuggest(String replaySuggest) {
		this.replaySuggest = replaySuggest;
	}

	public String getReplaySuggest() {
		return replaySuggest;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState2() {
		return state2;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getStepname() {
		return stepname;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}

	public String getUserfullname() {
		return userfullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
