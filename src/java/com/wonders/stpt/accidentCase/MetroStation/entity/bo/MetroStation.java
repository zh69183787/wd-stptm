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

package com.wonders.stpt.accidentCase.MetroStation.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * MetroStation实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "METRO_STATION")
public class MetroStation implements Serializable, BusinessObject {

	private String stationId; // stationId

	private Long downNumber; // downNumber

	private String line; // line

	private String stationName; // stationName

	private Long upNumber; // upNumber

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATION_ID")
	public String getStationId() {
		return stationId;
	}

	public void setDownNumber(Long downNumber) {
		this.downNumber = downNumber;
	}

	@Column(name = "DOWN_NUMBER", nullable = true, length = 22)
	public Long getDownNumber() {
		return downNumber;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "LINE", nullable = false, length = 20)
	public String getLine() {
		return line;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name = "STATION_NAME", nullable = true, length = 20)
	public String getStationName() {
		return stationName;
	}

	public void setUpNumber(Long upNumber) {
		this.upNumber = upNumber;
	}

	@Column(name = "UP_NUMBER", nullable = true, length = 22)
	public Long getUpNumber() {
		return upNumber;
	}

}
