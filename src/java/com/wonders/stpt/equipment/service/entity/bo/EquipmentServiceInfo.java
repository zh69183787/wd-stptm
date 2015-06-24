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

package com.wonders.stpt.equipment.service.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * EquipmentServiceInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-25
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_EQUIPMENT_SERVICE_INFO")
public class EquipmentServiceInfo implements Serializable, BusinessObject {

	private String id; // id

	private String equipmentId; // equipmentId

	private String implementUnit; // implementUnit

	private String notes; // notes

	private String serviceContent; // serviceContent

	private String serviceCost; // serviceCost

	private String serviceDate; // serviceDate

	private String serviceLevel; // serviceLevel
	
	private String removed;
	
	private String operationDate;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator = "system.uuid")
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

	public void setImplementUnit(String implementUnit) {
		this.implementUnit = implementUnit;
	}

	@Column(name = "IMPLEMENT_UNIT", nullable = true, length = 40)
	public String getImplementUnit() {
		return implementUnit;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "NOTES", nullable = true, length = 400)
	public String getNotes() {
		return notes;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	@Column(name = "SERVICE_CONTENT", nullable = true, length = 400)
	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}

	@Column(name = "SERVICE_COST", nullable = true, length = 20)
	public String getServiceCost() {
		return serviceCost;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	@Column(name = "SERVICE_DATE", nullable = true, length = 20)
	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	@Column(name = "SERVICE_LEVEL", nullable = true, length = 20)
	public String getServiceLevel() {
		return serviceLevel;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "OPERATION_DATE", nullable = true, length = 20)
	public String getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}

	
	
}
