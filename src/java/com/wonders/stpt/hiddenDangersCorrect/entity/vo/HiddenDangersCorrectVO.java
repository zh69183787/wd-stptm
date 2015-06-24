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

package com.wonders.stpt.hiddenDangersCorrect.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * HiddenDangersCorrect实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HiddenDangersCorrectVO implements ValueObject {

	private String id;
	private String correctMethod;
	private String createPerson;
	private Date createTime;
	private String dangersClass;
	private String dangersState;
	private String editPerson;
	private Date editTime;
	private Date finishDate;
	private String importance;
	private Date inputDate;
	private String inputDept;
	private Date lastDate;
	private String lines;
	private String major;
	private String remark;
	private String whichTable;
	private String workDept;
	private String workPerson;
	private String workState;
	private String supervisionDept;
// ------------------begin-------------------------------
// ---------------add by wan huaqiu on 2012-12-12--------
	/**
	 * inputDept id
	 */
	private String inputDeptId;
	
	/**
	 * the departmentId in charge
	 */
	private String workDeptId;
	
	/**
	 * the id of supervision department
	 */
	private String supervisionDeptId;
	
	private String checkState;

// -----------------end-----------------------------------

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCorrectMethod(String correctMethod) {
		this.correctMethod = correctMethod;
	}

	public String getCorrectMethod() {
		return correctMethod;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setDangersClass(String dangersClass) {
		this.dangersClass = dangersClass;
	}

	public String getDangersClass() {
		return dangersClass;
	}

	public void setDangersState(String dangersState) {
		this.dangersState = dangersState;
	}

	public String getDangersState() {
		return dangersState;
	}

	public void setEditPerson(String editPerson) {
		this.editPerson = editPerson;
	}

	public String getEditPerson() {
		return editPerson;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getImportance() {
		return importance;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDept(String inputDept) {
		this.inputDept = inputDept;
	}

	public String getInputDept() {
		return inputDept;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getLines() {
		return lines;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setWhichTable(String whichTable) {
		this.whichTable = whichTable;
	}

	public String getWhichTable() {
		return whichTable;
	}

	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}

	public String getWorkDept() {
		return workDept;
	}

	public void setWorkPerson(String workPerson) {
		this.workPerson = workPerson;
	}

	public String getWorkPerson() {
		return workPerson;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getWorkState() {
		return workState;
	}

	public String getSupervisionDept() {
		return supervisionDept;
	}

	public void setSupervisionDept(String supervisionDept) {
		this.supervisionDept = supervisionDept;
	}

	// ------------------begin-------------------------------
// ---------------add by wan huaqiu on 2012-12-12--------
	public String getInputDeptId() {
		return inputDeptId;
	}

	public void setInputDeptId(String inputDeptId) {
		this.inputDeptId = inputDeptId;
	}

	public String getWorkDeptId() {
		return workDeptId;
	}

	public void setWorkDeptId(String workDeptId) {
		this.workDeptId = workDeptId;
	}

	public String getSupervisionDeptId() {
		return supervisionDeptId;
	}

	public void setSupervisionDeptId(String supervisionDeptId) {
		this.supervisionDeptId = supervisionDeptId;
	}
	
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	
// -----------------end-----------------------------------
	
}
