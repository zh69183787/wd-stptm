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

package com.wonders.stpt.securityreport.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.securityreport.entity.bo.SecReport;
import com.wonders.stpt.securityreport.dao.SecReportDao;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * SecReport实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

public class SecReportDaoImpl extends AbstractHibernateDaoImpl<SecReport>
		implements SecReportDao {
	public Page findSecReportByPage(Map<String, Object> filter, int pageNo,
			int pageSize,String order) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from SecReport t ";
		String countHql = "select count(*) from SecReport t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("company")||paramName.equals("createPersonName")||paramName.equals("modifyPersonName")||paramName.equals("titleYear")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("creatTimeStart")||paramName.equals("modifyTimeStart")||paramName.equals("filingDateStart")){
					if(paramName.equals("creatTimeStart")){
						filterPart += "t.creatTime >= :" + paramName;
					}else if(paramName.equals("modifyTimeStart")){
						filterPart += "t.modifyTime >= :" + paramName;
					}else if(paramName.equals("filingDateStart")){
						filterPart += "t.filingDate >= :" + paramName;
					}
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("creatTimeEnd")||paramName.equals("modifyTimeEnd")||paramName.equals("filingDateEnd")){
					if(paramName.equals("creatTimeEnd")){
						filterPart += "t.creatTime <= :" + paramName;
					}else if(paramName.equals("modifyTimeEnd")){
						filterPart += "t.modifyTime <= :" + paramName;
					}else if(paramName.equals("filingDateEnd")){
						filterPart += "t.filingDate <= :" + paramName;
					}
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
					
				}
				
				
				
				filterCounter++;
			}
			
		}
//		filterPart += " order by t.filingDate desc";
		if(order==null||"".equals(order)) filterPart += " ORDER BY t.modifyTime DESC";
		else filterPart += " ORDER BY t.modifyTime "+order;
		
		
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	/**
	 * 根据条件找出所有非系统字段，包括hr_b_info和hr_ext_info两张表
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 * @param hret_name:扩展类别	
	 */
	public List<Object[]> findSecReportForExport (Map<String, Object> filter,String order){			
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from SecReport t ";
//		String countHql = "select count(*) from SecReport t ";
		String filterPart = " where remove=0 ";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
//			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
//				if (filterCounter > 0) {
					filterPart += " and ";
//				}
				String paramName = i.next();
				if(paramName.equals("company")||paramName.equals("createPersonName")||paramName.equals("modifyPersonName")||paramName.equals("titleYear")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("creatTimeStart")||paramName.equals("modifyTimeStart")||paramName.equals("filingDateStart")){
					if(paramName.equals("creatTimeStart")){
						filterPart += "t.creatTime >= :" + paramName;
					}else if(paramName.equals("modifyTimeStart")){
						filterPart += "t.modifyTime >= :" + paramName;
					}else if(paramName.equals("filingDateStart")){
						filterPart += "t.filingDate >= :" + paramName;
					}
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("creatTimeEnd")||paramName.equals("modifyTimeEnd")||paramName.equals("filingDateEnd")){
					if(paramName.equals("creatTimeEnd")){
						filterPart += "t.creatTime <= :" + paramName;
					}else if(paramName.equals("modifyTimeEnd")){
						filterPart += "t.modifyTime <= :" + paramName;
					}else if(paramName.equals("filingDateEnd")){
						filterPart += "t.filingDate <= :" + paramName;
					}
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
					
				}
				
				
				
				filterCounter++;
			}
			
		}
	
//		filterPart += " order by t.filingDate desc";
		if(order==null||"".equals(order)) filterPart += " ORDER BY t.modifyTime DESC";
		else filterPart += " ORDER BY t.modifyTime "+order;
		
		List<SecReport> list = findByHQL(hql+filterPart,args);
		
		List<Object[]> listobj = new ArrayList<Object[]>();
		//"年份","季度","工作单位","填表日期","创建时间","创建人","修改时间","修改人"};
		for(int i=0;i<list.size();i++){
			Object[] obj = new Object[8];
			obj[0] = list.get(i).getTitleYear();
			obj[1] = list.get(i).getTitleQuarter();
			obj[2] = list.get(i).getCompany();
			obj[3] = list.get(i).getFilingDate();
			obj[4] = list.get(i).getCreatTime();
			obj[5] = list.get(i).getCreatePersonName();
			obj[6] = list.get(i).getModifyTime();
			obj[7] = list.get(i).getModifyPersonName();
			listobj.add(obj);
		}
		System.out.println("##listobj##"+listobj.size());
		return listobj;
	}

	
}
