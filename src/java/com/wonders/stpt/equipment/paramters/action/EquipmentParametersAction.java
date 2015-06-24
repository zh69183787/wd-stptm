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

package com.wonders.stpt.equipment.paramters.action;

import com.wonders.stpt.equipment.paramters.entity.bo.EquipmentParameters;
import com.wonders.stpt.equipment.paramters.service.EquipmentParametersService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-27
 * @author modify by $Author$
 * @since 1.0
 */

public class EquipmentParametersAction extends BaseAjaxAction {
	private EquipmentParameters equipmentParameters = new EquipmentParameters();
	private EquipmentParametersService equipmentParametersService;


	public void setEquipmentParametersService(
			EquipmentParametersService equipmentParametersService) {
		this.equipmentParametersService = equipmentParametersService;
	}
}
