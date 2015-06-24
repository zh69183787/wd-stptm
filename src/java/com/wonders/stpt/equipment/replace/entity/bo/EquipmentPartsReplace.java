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

package com.wonders.stpt.equipment.replace.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * EquipmentPartsReplaceʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-21
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_EQUIPMENT_PARTS_REPLACE")
public class EquipmentPartsReplace implements Serializable, BusinessObject {

	private String id; // id

	private String consumeRate; // consumeRate

	private String cost; // cost

	private String equipmentId; // equipmentId

	private String manufacturer; // manufacturer

	private String notes; // notes

	private String operateTime; // operateTime

	private String partsAmount; // partsAmount

	private String partsName; // partsName

	private String price; // price

	private String productModel; // productModel

	private String removed; // removed

	private String replaceDate; // replaceDate

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

	public void setConsumeRate(String consumeRate) {
		this.consumeRate = consumeRate;
	}

	@Column(name = "CONSUME_RATE", nullable = true, length = 20)
	public String getConsumeRate() {
		return consumeRate;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Column(name = "COST", nullable = true, length = 20)
	public String getCost() {
		return cost;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "EQUIPMENT_ID", nullable = true, length = 40)
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "MANUFACTURER", nullable = true, length = 200)
	public String getManufacturer() {
		return manufacturer;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "NOTES", nullable = true, length = 400)
	public String getNotes() {
		return notes;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 20)
	public String getOperateTime() {
		return operateTime;
	}

	public void setPartsAmount(String partsAmount) {
		this.partsAmount = partsAmount;
	}

	@Column(name = "PARTS_AMOUNT", nullable = true, length = 20)
	public String getPartsAmount() {
		return partsAmount;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}

	@Column(name = "PARTS_NAME", nullable = true, length = 100)
	public String getPartsName() {
		return partsName;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "PRICE", nullable = true, length = 20)
	public String getPrice() {
		return price;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	@Column(name = "PRODUCT_MODEL", nullable = true, length = 100)
	public String getProductModel() {
		return productModel;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setReplaceDate(String replaceDate) {
		this.replaceDate = replaceDate;
	}

	@Column(name = "REPLACE_DATE", nullable = true, length = 20)
	public String getReplaceDate() {
		return replaceDate;
	}

}
