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

package com.wonders.stpt.hiddenDangersCorrect.entity.bo;

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
 * HiddenDangersCorrect实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-8-16
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "HIDDEN_DANGERS_CORRECT")
public class HiddenDangersCorrect implements Serializable, BusinessObject {

	private String id; // id

	private String correctMethod; // correctMethod

	private String createPerson; // createPerson

	private Date createTime; // createTime

	private String dangersClass; // dangersClass

	private String dangersState; // dangersState

	private String editPerson; // editPerson

	private Date editTime; // editTime

	private Date finishDate; // finishDate

	private String importance; // importance

	private Date inputDate; // inputDate

	private String inputDept; // inputDept

	private Date lastDate; // lastDate

	private String lines; // lines

	private String major; // major

	private String remark; // remark

	private String whichTable; // whichTable

	private String workDept; // workDept

	private String workPerson; // workPerson

	private String workState; // workState
	
	private String supervisionDept;
//------------------begin-------------------------------
//---------------add by wan huaqiu on 2012-12-12--------
	/**
	 * workDept id
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
//-----------------end-----------------------------------	

	
	private String checkState;
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setCorrectMethod(String correctMethod) {
		this.correctMethod = correctMethod;
	}

	@Column(name = "CORRECT_METHOD", nullable = true, length =4000)
	public String getCorrectMethod() {
		return correctMethod;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "CREATE_PERSON", nullable = true, length = 20)
	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setDangersClass(String dangersClass) {
		this.dangersClass = dangersClass;
	}

	@Column(name = "DANGERS_CLASS", nullable = true, length = 1)
	public String getDangersClass() {
		return dangersClass;
	}

	public void setDangersState(String dangersState) {
		this.dangersState = dangersState;
	}

	@Column(name = "DANGERS_STATE", nullable = true, length = 800)
	public String getDangersState() {
		return dangersState;
	}

	public void setEditPerson(String editPerson) {
		this.editPerson = editPerson;
	}

	@Column(name = "EDIT_PERSON", nullable = true, length = 20)
	public String getEditPerson() {
		return editPerson;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@Column(name = "EDIT_TIME", nullable = true, length = 7)
	public Date getEditTime() {
		return editTime;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	@Column(name = "FINISH_DATE", nullable = true, length = 7)
	public Date getFinishDate() {
		return finishDate;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	@Column(name = "IMPORTANCE", nullable = true, length = 1)
	public String getImportance() {
		return importance;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Column(name = "INPUT_DATE", nullable = true, length = 7)
	public Date getInputDate() {
		return inputDate;
	}

	public void setInputDept(String inputDept) {
		this.inputDept = inputDept;
	}

	@Column(name = "INPUT_DEPT", nullable = true, length = 100)
	public String getInputDept() {
		return inputDept;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Column(name = "LAST_DATE", nullable = true, length = 7)
	public Date getLastDate() {
		return lastDate;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	@Column(name = "LINES", nullable = true, length = 100)
	public String getLines() {
		return lines;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "MAJOR", nullable = true, length = 100)
	public String getMajor() {
		return major;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 4000)
	public String getRemark() {
		return remark;
	}

	public void setWhichTable(String whichTable) {
		this.whichTable = whichTable;
	}

	@Column(name = "WHICH_TABLE", nullable = true, length = 1)
	public String getWhichTable() {
		return whichTable;
	}

	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}

	@Column(name = "WORK_DEPT", nullable = true, length = 500)
	public String getWorkDept() {
		return workDept;
	}

	public void setWorkPerson(String workPerson) {
		this.workPerson = workPerson;
	}

	@Column(name = "WORK_PERSON", nullable = true, length = 100)
	public String getWorkPerson() {
		return workPerson;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	@Column(name = "WORK_STATE", nullable = true, length = 1)
	public String getWorkState() {
		return workState;
	}

	@Column(name = "SUPERVISION_DEPT", nullable = true, length = 100)
	public String getSupervisionDept() {
		return supervisionDept;
	}

	public void setSupervisionDept(String supervisionDept) {
		this.supervisionDept = supervisionDept;
	}

	//------------------begin-------------------------------
//---------------add by wan huaqiu on 2012-12-12--------
	@Column(name = "INPUT_DEPT_ID", nullable = true, length = 50)
	public String getInputDeptId() {
		return inputDeptId;
	}

	public void setInputDeptId(String inputDeptId) {
		this.inputDeptId = inputDeptId;
	}

	@Column(name = "WORK_DEPT_ID", nullable = true, length = 200)
	public String getWorkDeptId() {
		return workDeptId;
	}

	public void setWorkDeptId(String workDeptId) {
		this.workDeptId = workDeptId;
	}

	@Column(name = "SUPERVISION_DEPT_ID", nullable = true, length = 50)
	public String getSupervisionDeptId() {
		return supervisionDeptId;
	}

	public void setSupervisionDeptId(String supervisionDeptId) {
		this.supervisionDeptId = supervisionDeptId;
	}
	
	@Column(name = "CHECK_STATE", nullable = true, length = 1)
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
//-------------------end---------------------------------	
	
}
