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

package com.wonders.stpt.sthr.HrEtD.dao;

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

public interface HrEtDDao extends AbstractHibernateDao<HrEtD> {
	public Page findHrEtDByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author ycl
	 * @param id 主键
	 * @describe 根据主键查询
	 * @return
	 */
	public String findHrEtTypeNameById(String id);
	
	/**
	 * @author ycl
	 * @param inputType
	 * @param hretId
	 * @describe 根据类型查询
	 * @return	List<HrEtD>
	 */
	public List<HrEtD> findAllHrEtDByInputType(String hretId,String inputType);
	
	/**
	 * @author ycl
	 * @describe 根据itemName查询
	 * @param hretId
	 * @param itemName
	 * @return boolean
	 */
	public boolean findHrEtDByItemName(String hretId,String itemName);
	
	/**
	 * @author ycl
	 * @describe 根据类型、排列顺序查询
	 * @param hretId
	 * @param sortingOrder
	 * @return boolean
	 */
	public boolean findHrEtDBySortingOrder(String hretId,long sortingOrder);
	
	/**
	 * @author ycl
	 * @describe 逻辑删除
	 * @param hrEtD
	 */
	public void deleteHrEtDByLogic(HrEtD hrEtD);
	
	/**
	 * @author ycl
	 * @describe 根据主键查询
	 * @param id
	 */
	public HrEt findHrEtById(String id);
	
	/**
	 * @author ycl
	 * @describe 查询表中显示字段的总数
	 * @return
	 */
	public int findCountOfIsLSHowByHretId(String hretId);
	
	/**
	 * @author sunjiawei
	 * @describe 查询HrExtInfo表中字段
	 * @param id为HrExtInfo表主键
	 * @param hretId为HrEtD表外键	
	 */
	public List<Object[]> findHrExtInfoById(String id,String hretId);
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtDList(String hretdId,int sortingOrder,String itemName,String isLShow,String updatePerson);
}
