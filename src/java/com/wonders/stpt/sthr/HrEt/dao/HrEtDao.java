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

package com.wonders.stpt.sthr.HrEt.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface HrEtDao extends AbstractHibernateDao<HrEt> {
	public Page findHrEtByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author ycl
	 * @param hrEt
	 * 			hrEt实例
	 * @describe 删除
	 */
	public void deleteHrEtById(HrEt hrEt);
	
	/**
	 * @author ycl
	 * @param typeName 类型名称
	 * @return boolean
	 */
	public boolean findHrEtByTypeName(String typeName);
	
	/**
	 * @author ycl
	 * @param sortingOrder 排序名称
	 * @return boolean
	 */
	public boolean findHrEtBySortingOrder(long sortingOrder);
	
	/**
	 * @author ycl
	 * @describe 根据hretId查询HrEtD
	 * @param hretId
	 * @return List<HrEtD>
	 */
	public List<HrEtD> findHrEtDByHretId(String hretId);
	
	/**
	 * @author ycl
	 * @describe 更新HrEtD
	 * @param hretdList
	 */
	public void updateHrEtD(List<HrEtD> hretdList);
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtList(String hretId,String typeName,int sortingOrder,String updatePerson );
	
	/**
	 * @author sunjiawei
	 * @describe 修改扩展数据类别名称的时候同时修改HR_EXT_INFO表中hret_name字段的值
	 */	
	public void updateHretName(String hretId,String hretName);
	
	/**
	 * @author sunjiawei
	 * @describe 找出所有未删除的数据
	 */	
	public List<HrEt> findAllTypeName();
}
