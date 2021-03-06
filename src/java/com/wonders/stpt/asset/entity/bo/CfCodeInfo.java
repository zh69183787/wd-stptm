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

package com.wonders.stpt.asset.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * CodeInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ASSET_CODE_INFO")
public class CfCodeInfo implements Serializable, BusinessObject {

	private Long id; // id

	private String code; // code

	private String description; // description

	private Long dispOrder; // dispOrder

	private String name; // name

	private Long operateTime; // operateTime

	private String operator; // operator

	private String remark1; // remark1

	private String remark2; // remark2

	private Long removed; // removed

	private Long typeId; // typeId

	
	private Long codeInfoId;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CODE", nullable = true, length = 100)
	public String getCode() {
		return code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION", nullable = true, length = 255)
	public String getDescription() {
		return description;
	}

	public void setDispOrder(Long dispOrder) {
		this.dispOrder = dispOrder;
	}

	@Column(name = "DISP_ORDER", nullable = true, length = 10)
	public Long getDispOrder() {
		return dispOrder;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 19)
	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	@Column(name = "REMARK1", nullable = true, length = 255)
	public String getRemark1() {
		return remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Column(name = "REMARK2", nullable = true, length = 255)
	public String getRemark2() {
		return remark2;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public Long getRemoved() {
		return removed;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Column(name = "TYPE_ID", nullable = true, length = 19)
	public Long getTypeId() {
		return typeId;
	}

	@Column(name = "CODE_INFO_ID", nullable = true, length = 19)
	public Long getCodeInfoId() {
		return codeInfoId;
	}

	public void setCodeInfoId(Long codeInfoId) {
		this.codeInfoId = codeInfoId;
	}

}
