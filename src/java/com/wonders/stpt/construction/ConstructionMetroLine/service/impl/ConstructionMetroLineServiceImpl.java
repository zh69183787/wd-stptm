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

package com.wonders.stpt.construction.ConstructionMetroLine.service.impl;

import com.wonders.stpt.construction.ConstructionMetroLine.dao.ConstructionMetroLineDao;
import com.wonders.stpt.construction.ConstructionMetroLine.service.ConstructionMetroLineService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class ConstructionMetroLineServiceImpl implements ConstructionMetroLineService {
	private ConstructionMetroLineDao constructionMetroLineDao;

	public void setMetroLineDao(ConstructionMetroLineDao metroLineDao) {
		this.constructionMetroLineDao = metroLineDao;
	}
	
}
