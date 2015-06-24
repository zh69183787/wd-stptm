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

package com.wonders.stpt.supplier.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

public interface SupplierService {
	/**
	 * 分页查询
	 * @param filter 查询条件
	 * @param pageNo 页码
	 * @param pageSize	每页的条数
	 * @return 分页好后的数据
	 */
	public Page findSupplierByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * 保存新增
	 * @param supplier 供应商
	 */
	public void saveSupplier(Supplier supplier);
	
	/**
	 * 查看详细
	 * @param id 主键
	 * @return 供应商
	 */
	public Supplier findSupplierById(long id);
	
	/**
	 * 保存修改
	 * @param supplier 供应商
	 */
	public void update(Supplier supplier);
	
	
	/**
	 * 查询
	 * @param type 厂商类别
	 * @return 厂商
	 */
	public List<Supplier> findSupplierByType(String type);
	
	
	/**
	 * 根据类型和名称查询主键
	 * @param type	类型
	 * @param name	名称
	 * @return	主键
	 */
	public String findIdByTypeAndName(String type,String name);
	
	/**
	 * 查询所有厂商
	 * @return	厂商
	 */
	public List<Supplier> findAllSupplier();
	
	
	/**
	 * 根据名称和类型查询
	 * @param name	名称
	 * @param type	类型
	 * @return
	 */
	public List<Supplier> findSupplierByNameAndType(String name,String type);
	
		
}
