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

package com.wonders.stpt.accidentCase.AccidentCaseApproval.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * AccidentCaseApproval实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-2-27
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "ACCIDENT_CASE_APPROVAL")
public class AccidentCaseApproval implements Serializable, BusinessObject {

	private String approvalId; // approvalId

	private String approvalContent; // approvalContent

	private String approvalPerson; // approvalPerson

	private String approvalPersonName; // approvalPersonName

	private String approvalStatus; // approvalStatus

	private Date approvalTime; // approvalTime

	private String caseId; // caseId

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "APPROVAL_ID")
	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	@Column(name = "APPROVAL_CONTENT", nullable = true, length = 4000)
	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	@Column(name = "APPROVAL_PERSON", nullable = true, length = 20)
	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPersonName(String approvalPersonName) {
		this.approvalPersonName = approvalPersonName;
	}

	@Column(name = "APPROVAL_PERSON_NAME", nullable = true, length = 20)
	public String getApprovalPersonName() {
		return approvalPersonName;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Column(name = "APPROVAL_STATUS", nullable = true, length = 20)
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	@Column(name = "APPROVAL_TIME", nullable = true, length = 7)
	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column(name = "CASE_ID", nullable = false, length = 20)
	public String getCaseId() {
		return caseId;
	}

}
