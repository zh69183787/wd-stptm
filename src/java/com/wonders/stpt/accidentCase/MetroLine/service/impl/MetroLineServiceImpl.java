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

package com.wonders.stpt.accidentCase.MetroLine.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.MetroLine.dao.MetroLineDao;
import com.wonders.stpt.accidentCase.MetroLine.entity.bo.MetroLine;
import com.wonders.stpt.accidentCase.MetroLine.service.MetroLineService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroLineServiceImpl implements MetroLineService {
	private MetroLineDao metroLineDao;

	public void setMetroLineDao(MetroLineDao metroLineDao) {
		this.metroLineDao = metroLineDao;
	}

	public void addMetroLine(MetroLine metroLine) {
		metroLineDao.save(metroLine);
	}

	public void deleteMetroLine(MetroLine metroLine) {
		metroLineDao.delete(metroLine);
	}

	public MetroLine findMetroLineById(String id) {
		return metroLineDao.load(id);
	}

	public void updateMetroLine(MetroLine metroLine) {
		metroLineDao.update(metroLine);
	}

	public Page findMetroLineByPage(int pageNo, int pageSize) {
		Page page = metroLineDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findMetroLineByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return metroLineDao.findMetroLineByPage(filter, pageNo, pageSize);
	}
	
	/**
	 * @author yaochenglong
	 * @describe 查询所有地铁线路
	 * @return List<MetroLine>
	 */
	public List<MetroLine> findAllMetroLine(){
		return metroLineDao.findAll();
	}
	
}
