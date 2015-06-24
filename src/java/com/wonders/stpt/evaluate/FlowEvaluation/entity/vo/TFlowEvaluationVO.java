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

package com.wonders.stpt.evaluate.FlowEvaluation.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * TFlowEvaluationʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-13
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationVO implements ValueObject {

	private String id;
	private String beEvaluatedDept;
	private String evaluateDept;
	private String evaluationContent;
	private String evaluationDetail;
	private String evaluationPerson;
	private Date evaluationTime;
	private String flowId;
	private String flowName;
	private String goodMediumBad;
	private String removed;
	private String updatePerson;
	private Date updateTime;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setBeEvaluatedDept(String beEvaluatedDept) {
		this.beEvaluatedDept = beEvaluatedDept;
	}

	public String getBeEvaluatedDept() {
		return beEvaluatedDept;
	}

	public void setEvaluateDept(String evaluateDept) {
		this.evaluateDept = evaluateDept;
	}

	public String getEvaluateDept() {
		return evaluateDept;
	}

	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}

	public String getEvaluationContent() {
		return evaluationContent;
	}

	public void setEvaluationDetail(String evaluationDetail) {
		this.evaluationDetail = evaluationDetail;
	}

	public String getEvaluationDetail() {
		return evaluationDetail;
	}

	public void setEvaluationPerson(String evaluationPerson) {
		this.evaluationPerson = evaluationPerson;
	}

	public String getEvaluationPerson() {
		return evaluationPerson;
	}

	public void setEvaluationTime(Date evaluationTime) {
		this.evaluationTime = evaluationTime;
	}

	public Date getEvaluationTime() {
		return evaluationTime;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setGoodMediumBad(String goodMediumBad) {
		this.goodMediumBad = goodMediumBad;
	}

	public String getGoodMediumBad() {
		return goodMediumBad;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

}
