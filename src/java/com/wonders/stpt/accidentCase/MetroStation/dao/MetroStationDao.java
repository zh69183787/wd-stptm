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

package com.wonders.stpt.accidentCase.MetroStation.dao;

import com.wonders.stpt.accidentCase.MetroStation.entity.bo.MetroStation;
import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroStationDao extends AbstractHibernateDao<MetroStation> {
	public Page findMetroStationByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author yaochenglong
	 * @describe 根据地铁线路查询地铁站台
	 * @return List<MetroStation>
	 */
	public List<MetroStation> findStationsByMetroLine(String line);
}
