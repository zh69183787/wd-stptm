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

package com.wonders.stpt.construction.TConstructionNotice.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * TConstructionNotice实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class TConstructionNoticeVO implements ValueObject {

	private String id;
	private String applyUnit;
	private Date constructionDate;
	private String constructionDetail;
	private Date constructionEndDate;
	private Date constructionStartDate;
	private String constructionUnit;
	private String construstionRange;
	private Date createDate;
	private String dragDynamic;
	private Date lastUpdateDate;
	private String lineNo;
	private String powerOffRange;
	private String projectNo;
	private String removed;
	private String responsiblePerson;
	private String updatePerson;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}

	public String getApplyUnit() {
		return applyUnit;
	}

	public void setConstructionDate(Date constructionDate) {
		this.constructionDate = constructionDate;
	}

	public Date getConstructionDate() {
		return constructionDate;
	}

	public void setConstructionDetail(String constructionDetail) {
		this.constructionDetail = constructionDetail;
	}

	public String getConstructionDetail() {
		return constructionDetail;
	}

	public void setConstructionEndDate(Date constructionEndDate) {
		this.constructionEndDate = constructionEndDate;
	}

	public Date getConstructionEndDate() {
		return constructionEndDate;
	}

	public void setConstructionStartDate(Date constructionStartDate) {
		this.constructionStartDate = constructionStartDate;
	}

	public Date getConstructionStartDate() {
		return constructionStartDate;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstrustionRange(String construstionRange) {
		this.construstionRange = construstionRange;
	}

	public String getConstrustionRange() {
		return construstionRange;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setDragDynamic(String dragDynamic) {
		this.dragDynamic = dragDynamic;
	}

	public String getDragDynamic() {
		return dragDynamic;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setPowerOffRange(String powerOffRange) {
		this.powerOffRange = powerOffRange;
	}

	public String getPowerOffRange() {
		return powerOffRange;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

}
