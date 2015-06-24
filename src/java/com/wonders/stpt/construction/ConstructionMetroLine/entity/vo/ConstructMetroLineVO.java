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

package com.wonders.stpt.construction.ConstructionMetroLine.entity.vo;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroLine实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class ConstructMetroLineVO implements ValueObject {

	private String lineId;
	private String lineName;
	private Long sortingOrder;

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setSortingOrder(Long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	public Long getSortingOrder() {
		return sortingOrder;
	}

}
