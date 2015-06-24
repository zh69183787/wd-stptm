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

package com.wonders.stpt.construction.TConstructionNotice.dao;

import com.wonders.stpt.construction.ConstructionMetroLine.entity.bo.ConstructionMetroLine;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.construction.TConstructionNotice.entity.bo.TConstructionNotice;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public interface TConstructionNoticeDao extends
		AbstractHibernateDao<TConstructionNotice> {
	public Page findTConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<TConstructionNotice> dataList);
	
	/**
	 * @author ycl
	 * @describe 查询所有线路
	 * @return
	 */
	public List<ConstructionMetroLine> findAllMetroLine();
	
	/**
	 * @author ycl
	 * @description 根据线路id查询
	 * @return int
	 */
	public int findCountByLineNo(String lineNo);
}
