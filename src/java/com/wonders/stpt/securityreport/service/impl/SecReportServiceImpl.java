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

package com.wonders.stpt.securityreport.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.securityreport.entity.bo.SecReport;
import com.wonders.stpt.securityreport.dao.SecReportDao;
import com.wonders.stpt.securityreport.service.SecReportService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

public class SecReportServiceImpl implements SecReportService {
	private SecReportDao secReportDao;

	public void setSecReportDao(SecReportDao secReportDao) {
		this.secReportDao = secReportDao;
	}

	@SuppressWarnings("deprecation")
	public void addSecReport(SecReport secReport) {
//		secReportDao.save(secReport);
//		secReport.setCreatPerson("");
//		secReport.setCreatePersonName("test");
		secReport.setCreatTime(new Date());
		secReport.setModifyPerson(null);
		secReport.setModifyPersonName(null);
		
		secReportDao.save(secReport);
	}

	public void deleteSecReport(SecReport secReport) {
		secReportDao.delete(secReport);
	}

	public SecReport findSecReportById(String id) {
//		System.out.println("#########################:"+id);
		
		return secReportDao.load(id);
	}

	public void updateSecReport(SecReport secReport) {
		secReport.setModifyTime(new Date());
//		secReport.setCreatePersonName(null);
//		secReport.setCreatPerson(null);
		//secReport.setCreatTime(new Date());
		secReportDao.update(secReport);
	}

	public Page findSecReportByPage(int pageNo, int pageSize) {
		Page page = secReportDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findSecReportByPage(Map<String, Object> filter, int pageNo,
			int pageSize, String order) {
		return secReportDao.findSecReportByPage(filter, pageNo, pageSize, order);
	}
	public List<Object[]> findSecReportForExport (Map<String, Object> filter,String order)
	{
		return secReportDao.findSecReportForExport(filter, order);		
	}
}
