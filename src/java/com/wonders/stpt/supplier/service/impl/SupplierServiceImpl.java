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

package com.wonders.stpt.supplier.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.supplier.dao.SupplierDao;
import com.wonders.stpt.supplier.entity.bo.Supplier;
import com.wonders.stpt.supplier.service.SupplierService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

public class SupplierServiceImpl implements SupplierService {
	
	private SupplierDao supplierDao;

	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public Page findSupplierByPage(Map<String, Object> filter, int pageNo, int pageSize) {
		return supplierDao.findSupplierByPage(filter, pageNo, pageSize);
	}

	@Override
	public void saveSupplier(Supplier supplier) {
		supplierDao.saveSupplier(supplier);
	}

	@Override
	public Supplier findSupplierById(long id) {
		return supplierDao.findSupplierById(id);
	}

	@Override
	public void update(Supplier supplier) {
		supplierDao.update(supplier);
	}

	@Override
	public List<Supplier> findSupplierByType(String type) {
		return supplierDao.findSupplierByType(type);
	}

	@Override
	public String findIdByTypeAndName(String type, String name) {
		if(type==null || name==null || "".equals(type) || "".equals(name))
			return null;
		List<Supplier> resultList = supplierDao.findIdByTypeAndName(type, name);
		if(resultList==null || resultList.size()<1)
			return null;
		return String.valueOf(resultList.get(0).getId()).trim();
	}

	@Override
	public List<Supplier> findAllSupplier() {
		return supplierDao.findAllSupplier();
	}

	@Override
	public List<Supplier> findSupplierByNameAndType(String name, String type) {
		return supplierDao.findSupplierByNameAndType(name, type);
	}
	
	
	
}
