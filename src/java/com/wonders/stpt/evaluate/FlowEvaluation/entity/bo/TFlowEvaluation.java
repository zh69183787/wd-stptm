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

package com.wonders.stpt.evaluate.FlowEvaluation.entity.bo;

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
 * TFlowEvaluationʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-13
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_FLOW_EVALUATION")
public class TFlowEvaluation implements Serializable, BusinessObject {

	private String id; // id

	private String beEvaluatedDept; // beEvaluatedDept

	private String evaluateDept; // evaluateDept

	private String evaluationContent; // evaluationContent

	private String evaluationDetail; // evaluationDetail

	private String evaluationPerson; // evaluationPerson

	private Date evaluationTime; // evaluationTime

	private String flowId; // flowId

	private String flowName; // flowName

	private String goodMediumBad; // goodMediumBad

	private String removed; // removed

	private String updatePerson; // updatePerson

	private Date updateTime; // updateTime

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

	public void setBeEvaluatedDept(String beEvaluatedDept) {
		this.beEvaluatedDept = beEvaluatedDept;
	}

	@Column(name = "BE_EVALUATED_DEPT", nullable = true, length = 40)
	public String getBeEvaluatedDept() {
		return beEvaluatedDept;
	}

	public void setEvaluateDept(String evaluateDept) {
		this.evaluateDept = evaluateDept;
	}

	@Column(name = "EVALUATE_DEPT", nullable = true, length = 40)
	public String getEvaluateDept() {
		return evaluateDept;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	@Column(name = "EVALUATION_CONTENT", nullable = true, length = 4000)
	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationDetail(String evaluationDetail) {
		this.evaluationDetail = evaluationDetail;
	}

	@Column(name = "EVALUATION_DETAIL", nullable = true, length = 4000)
	public String getEvaluationDetail() {
		return evaluationDetail;
	}

	public void setEvaluationPerson(String evaluationPerson) {
		this.evaluationPerson = evaluationPerson;
	}

	@Column(name = "EVALUATION_PERSON", nullable = true, length = 20)
	public String getEvaluationPerson() {
		return evaluationPerson;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	@Column(name = "EVALUATION_TIME", nullable = true, length = 7)
	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	@Column(name = "FLOW_ID", nullable = true, length = 40)
	public String getFlowId() {
		return flowId;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	@Column(name = "FLOW_NAME", nullable = true, length = 40)
	public String getFlowName() {
		return flowName;
	}

	public void setGoodMediumBad(String goodMediumBad) {
		this.goodMediumBad = goodMediumBad;
	}

	@Column(name = "GOOD_MEDIUM_BAD", nullable = true, length = 10)
	public String getGoodMediumBad() {
		return goodMediumBad;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	@Column(name = "UPDATE_PERSON", nullable = true, length = 10)
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME", nullable = true, length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

}
