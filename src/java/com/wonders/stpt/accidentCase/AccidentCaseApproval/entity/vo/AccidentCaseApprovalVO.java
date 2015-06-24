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

package com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * AccidentCaseApproval实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-2-27
 * @author modify by $Author$
 * @since 1.0
 */

public class AccidentCaseApprovalVO implements ValueObject {

	private String approvalId;
	private String approvalContent;
	private String approvalPerson;
	private String approvalPersonName;
	private String approvalStatus;
	private Date approvalTime;
	private String caseId;

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPersonName(String approvalPersonName) {
		this.approvalPersonName = approvalPersonName;
	}

	public String getApprovalPersonName() {
		return approvalPersonName;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseId() {
		return caseId;
	}

}
