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

package com.wonders.stpt.equipment.info.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * EquipmentInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_EQUIPMENT_INFO")
public class EquipmentInfo implements Serializable, BusinessObject {

	private String id; // id

	private String assetId; // assetId

	private String equipmentId; // equipmentId

	private String equipmentName; // equipmentName

	private String heavyRepairFrequency; // heavyRepairFrequency

	private String installSite; // installSite

	private String manufacturer; // manufacturer

	private String mediumRepairFrequency; // mediumRepairFrequency

	private String notes; // notes

	private String price; // price

	private String productModel; // productModel

	private String provenance; // provenance

	private String usefulLife; // usefulLife

	private String useDate; // useDate
	
	private String operateTime;

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

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	@Column(name = "ASSET_ID", nullable = true, length = 40)
	public String getAssetId() {
		return assetId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Column(name = "EQUIPMENT_ID", nullable = true, length = 100)
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	@Column(name = "EQUIPMENT_NAME", nullable = true, length = 100)
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setHeavyRepairFrequency(String heavyRepairFrequency) {
		this.heavyRepairFrequency = heavyRepairFrequency;
	}

	@Column(name = "HEAVY_REPAIR_FREQUENCY", nullable = true, length = 20)
	public String getHeavyRepairFrequency() {
		return heavyRepairFrequency;
	}

	public void setInstallSite(String installSite) {
		this.installSite = installSite;
	}

	@Column(name = "INSTALL_SITE", nullable = true, length = 100)
	public String getInstallSite() {
		return installSite;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "MANUFACTURER", nullable = true, length = 200)
	public String getManufacturer() {
		return manufacturer;
	}

	public void setMediumRepairFrequency(String mediumRepairFrequency) {
		this.mediumRepairFrequency = mediumRepairFrequency;
	}

	@Column(name = "MEDIUM_REPAIR_FREQUENCY", nullable = true, length = 20)
	public String getMediumRepairFrequency() {
		return mediumRepairFrequency;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "NOTES", nullable = true, length = 400)
	public String getNotes() {
		return notes;
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

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	@Column(name = "PROVENANCE", nullable = true, length = 50)
	public String getProvenance() {
		return provenance;
	}

	public void setUsefulLife(String usefulLife) {
		this.usefulLife = usefulLife;
	}

	@Column(name = "USEFUL_LIFE", nullable = true, length = 20)
	public String getUsefulLife() {
		return usefulLife;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	@Column(name = "USE_DATE", nullable = true, length = 20)
	public String getUseDate() {
		return useDate;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 20)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	

}
