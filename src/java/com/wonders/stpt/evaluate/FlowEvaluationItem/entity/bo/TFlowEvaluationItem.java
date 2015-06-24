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

package com.wonders.stpt.evaluate.FlowEvaluationItem.entity.bo;

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
 * TFlowEvaluationItemʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-4-11
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_FLOW_EVALUATION_ITEM")
public class TFlowEvaluationItem implements Serializable, BusinessObject {

	private String id; // id

	private String flowId; // flowId

	private String name; // name

	private String operatePerson; // operatePerson

	private String removed; // removed

	private Long sortingOrder; // sortingOrder

	private Date updateDate; // updateDate

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

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	@Column(name = "FLOW_ID", nullable = true, length = 40)
	public String getFlowId() {
		return flowId;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	@Column(name = "OPERATE_PERSON", nullable = true, length = 40)
	public String getOperatePerson() {
		return operatePerson;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setSortingOrder(Long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	@Column(name = "SORTING_ORDER", nullable = true, length = 22)
	public Long getSortingOrder() {
		return sortingOrder;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATE_DATE", nullable = true, length = 7)
	public Date getUpdateDate() {
		return updateDate;
	}

}
