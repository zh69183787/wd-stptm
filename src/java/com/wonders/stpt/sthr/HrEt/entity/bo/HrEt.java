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

package com.wonders.stpt.sthr.HrEt.entity.bo;

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
 * HrEt实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "HR_ET")
public class HrEt implements Serializable, BusinessObject {

	private String hretId; // hretId

	private String removed; // removed

	private Long sortingOrder; // sortingOrder

	private String typeName; // typeName

	private String updatePerson; // updatePerson

	private Date updateTime; // updateTime

	public void setHretId(String hretId) {
		this.hretId = hretId;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "HRET_ID")
	public String getHretId() {
		return hretId;
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

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "TYPE_NAME", nullable = true, length = 100)
	public String getTypeName() {
		return typeName;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	@Column(name = "UPDATE_PERSON", nullable = true, length = 20)
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME", nullable = true, length = 11)
	public Date getUpdateTime() {
		return updateTime;
	}

}
