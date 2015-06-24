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

package com.wonders.stpt.annualLeave.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * HolHolidayApplyʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TA_HOL_HOLIDAYAPP")
public class HolHolidayApply implements Serializable, BusinessObject {

	private String id; // id

	private String applyDate; // applyDate

	private Long applyUser; // applyUser

	private String checkDate; // checkDate

	private String checkResult; // checkResult

	private Long checkUser; // checkUser

	private String holComment; // holComment

	private Long holDays; // holDays

	private String holDeptId; // holDeptId

	private Long holState; // holState

	private String noticeIdList; // noticeIdList

	private String noticeNameList; // noticeNameList

	private Long removed; // removed

	private String startDate; // startDate
	private String todoId;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "APPLY_DATE", nullable = true, length = 19)
	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyUser(Long applyUser) {
		this.applyUser = applyUser;
	}

	@Column(name = "APPLY_USER", nullable = true, length = 22)
	public Long getApplyUser() {
		return applyUser;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_DATE", nullable = true, length = 19)
	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@Column(name = "CHECK_RESULT", nullable = true, length = 200)
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckUser(Long checkUser) {
		this.checkUser = checkUser;
	}

	@Column(name = "CHECK_USER", nullable = true, length = 22)
	public Long getCheckUser() {
		return checkUser;
	}

	public void setHolComment(String holComment) {
		this.holComment = holComment;
	}

	@Column(name = "HOL_COMMENT", nullable = true, length = 4000)
	public String getHolComment() {
		return holComment;
	}

	public void setHolDays(Long holDays) {
		this.holDays = holDays;
	}

	@Column(name = "HOL_DAYS", nullable = true, length = 22)
	public Long getHolDays() {
		return holDays;
	}

	public void setHolDeptId(String holDeptId) {
		this.holDeptId = holDeptId;
	}

	@Column(name = "HOL_DEPT_ID", nullable = true, length = 200)
	public String getHolDeptId() {
		return holDeptId;
	}

	public void setHolState(Long holState) {
		this.holState = holState;
	}

	@Column(name = "HOL_STATE", nullable = true, length = 22)
	public Long getHolState() {
		return holState;
	}

	public void setNoticeIdList(String noticeIdList) {
		this.noticeIdList = noticeIdList;
	}

	@Column(name = "NOTICE_ID_LIST", nullable = true, length = 4000)
	public String getNoticeIdList() {
		return noticeIdList;
	}

	public void setNoticeNameList(String noticeNameList) {
		this.noticeNameList = noticeNameList;
	}

	@Column(name = "NOTICE_NAME_LIST", nullable = true, length = 4000)
	public String getNoticeNameList() {
		return noticeNameList;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 22)
	public Long getRemoved() {
		return removed;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "START_DATE", nullable = true, length = 4000)
	public String getStartDate() {
		return startDate;
	}

	@Column(name = "TODO_ID", nullable = true, length = 4000)
	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	
}
