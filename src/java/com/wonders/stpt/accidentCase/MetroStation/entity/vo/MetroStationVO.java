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

package com.wonders.stpt.accidentCase.MetroStation.entity.vo;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroStation实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroStationVO implements ValueObject {

	private String stationId;
	private Long downNumber;
	private String line;
	private String stationName;
	private Long upNumber;

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setDownNumber(Long downNumber) {
		this.downNumber = downNumber;
	}

	public Long getDownNumber() {
		return downNumber;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLine() {
		return line;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setUpNumber(Long upNumber) {
		this.upNumber = upNumber;
	}

	public Long getUpNumber() {
		return upNumber;
	}

}
