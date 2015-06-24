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

package com.wonders.stpt.equipment.paramters.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * EquipmentParametersʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_EQUIPMENT_PARAMETERS")
public class EquipmentParameters implements Serializable, BusinessObject {

	private String id; // id

	private String equipmentId; // equipmentId

	private String operationDate; // operationDate

	private String parameterName; // parameterName

	private String parameterUnit; // parameterUnit

	private String parameterValue; // parameterValue

	private String removed; // removed

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "EQUIPMENT_ID", nullable = true, length = 40)
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	@Column(name = "OPERATION_DATE", nullable = true, length = 20)
	public String getOperationDate() {
		return operationDate;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	@Column(name = "PARAMETER_NAME", nullable = true, length = 20)
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterUnit(String parameterUnit) {
		this.parameterUnit = parameterUnit;
	}

	@Column(name = "PARAMETER_UNIT", nullable = true, length = 10)
	public String getParameterUnit() {
		return parameterUnit;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Column(name = "PARAMETER_VALUE", nullable = true, length = 20)
	public String getParameterValue() {
		return parameterValue;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

}
