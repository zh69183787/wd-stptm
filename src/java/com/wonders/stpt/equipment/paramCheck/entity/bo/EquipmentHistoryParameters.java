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

package com.wonders.stpt.equipment.paramCheck.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * EquipmentHistoryParametersʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_EQUIPMENT_HISTORY_PARAMETERS")
public class EquipmentHistoryParameters implements Serializable, BusinessObject {

	private String id; // id

	private String equipmentId; // equipmentId

	private String operationDate; // operationDate

	private String paramterId; // paramterId

	private String paramterValue; // paramterValue

	private String removed; // removed
	
	private String checkDate;
	
	private String checkPerson;
	
	private String groupId;

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

	public void setParamterId(String paramterId) {
		this.paramterId = paramterId;
	}

	@Column(name = "PARAMTER_ID", nullable = true, length = 40)
	public String getParamterId() {
		return paramterId;
	}

	public void setParamterValue(String paramterValue) {
		this.paramterValue = paramterValue;
	}

	@Column(name = "PARAMTER_VALUE", nullable = true, length = 40)
	public String getParamterValue() {
		return paramterValue;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	
	@Column(name="CHECK_DATE",nullable = true, length = 20)
	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name="CHECK_PERSON",nullable = true, length = 40)
	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	@Column(name="GROUP_ID",nullable = true,length = 40)
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	

}
