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

package com.wonders.stpt.sthr.HrEtD.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * HrEtD实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrEtDVO implements ValueObject {

	private String hretdId;
	private String hretId;
	private String inputType;
	private String isLShow;
	private String itemFName;
	private String itemName;
	private String removed;
	private Long sortingOrder;
	private String typeName;
	private String updatePerson;
	private Date updateTime;

	public void setHretdId(String hretdId) {
		this.hretdId = hretdId;
	}

	public String getHretdId() {
		return hretdId;
	}

	public void setHretId(String hretId) {
		this.hretId = hretId;
	}

	public String getHretId() {
		return hretId;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInputType() {
		return inputType;
	}

	public void setIsLShow(String isLShow) {
		this.isLShow = isLShow;
	}

	public String getIsLShow() {
		return isLShow;
	}

	public void setItemFName(String itemFName) {
		this.itemFName = itemFName;
	}

	public String getItemFName() {
		return itemFName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
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

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
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
