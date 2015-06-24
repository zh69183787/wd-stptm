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

package com.wonders.stpt.accidentCase.MetroStation.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.MetroStation.entity.bo.MetroStation;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-22
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroStationService {
	/**
	 * 删除实体对象
	 * 
	 * @param metroStation
	 */
	public void deleteMetroStation(MetroStation metroStation);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public MetroStation findMetroStationById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param metroStation
	 */
	public void addMetroStation(MetroStation metroStation);

	/**
	 * 更新数据到数据库
	 * 
	 * @param metroStation
	 *            实体
	 */
	public void updateMetroStation(MetroStation metroStation);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findMetroStationByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findMetroStationByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author yaochenglong
	 * @describe 根据地铁线路查询地铁站台
	 * @return List<MetroStation>
	 */
	public List<MetroStation> findStationsByMetroLine(String line);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
