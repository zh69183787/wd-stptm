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

package com.wonders.stpt.evaluate.FlowEvaluationItem.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * TFlowEvaluationItemʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

public class TFlowEvaluationItemVO implements ValueObject {

	private String id;
	private String flowId;
	private String name;
	private String operatePerson;
	private String removed;
	private Long sortingOrder;
	private Date updateDate;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setSortingOrder(Long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public Long getSortingOrder() {
		return sortingOrder;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

}
