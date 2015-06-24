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

package com.wonders.stpt.accidentCase.MetroStation.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.MetroStation.dao.MetroStationDao;
import com.wonders.stpt.accidentCase.MetroStation.entity.bo.MetroStation;
import com.wonders.stpt.accidentCase.MetroStation.service.MetroStationService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroStationServiceImpl implements MetroStationService {
	private MetroStationDao metroStationDao;

	public void setMetroStationDao(MetroStationDao metroStationDao) {
		this.metroStationDao = metroStationDao;
	}

	public void addMetroStation(MetroStation metroStation) {
		metroStationDao.save(metroStation);
	}

	public void deleteMetroStation(MetroStation metroStation) {
		metroStationDao.delete(metroStation);
	}

	public MetroStation findMetroStationById(String id) {
		return metroStationDao.load(id);
	}

	public void updateMetroStation(MetroStation metroStation) {
		metroStationDao.update(metroStation);
	}

	public Page findMetroStationByPage(int pageNo, int pageSize) {
		Page page = metroStationDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findMetroStationByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return metroStationDao.findMetroStationByPage(filter, pageNo, pageSize);
	}
	
	/**
	 * @author yaochenglong
	 * @describe 根据地铁线路查询地铁站台
	 * @param line String类型:地铁线路
	 * @return List<MetroStation>
	 */
	public List<MetroStation> findStationsByMetroLine(String line){
		return metroStationDao.findStationsByMetroLine(line);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
