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

package com.wonders.stpt.accidentCase.MetroAccidentCase.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.MetroAccidentCase.dao.MetroAccidentCaseDao;
import com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo.MetroAccidentCase;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * MetroAccidentCase实体定义
 * 
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroAccidentCaseDaoImpl extends
		AbstractHibernateDaoImpl<MetroAccidentCase> implements
		MetroAccidentCaseDao {
	public Page findMetroAccidentCaseByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroAccidentCase t ";
		String countHql = "select count(*) from MetroAccidentCase t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("approvalStatus")){
					if(filter.get(paramName).equals("eeee")){
						filterPart += "t." + paramName + "<> :" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
					
				}else if(paramName.equals("metroStationName")||paramName.equals("keyWord")||paramName.equals("responsibleDeptName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("occurSTime1")){
					filterPart += "t.occurSTime >= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else if(paramName.equals("occurSTime2")){
					filterPart += "t.occurSTime <= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				
				filterCounter++;
			}
			filterPart += " order by t.occurSTime desc";
			
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	
	
	/**
	 * @author yaochenglong
	 * @param filter Map类型 过滤条件
	 * @param pageNo int类型 页码
	 * @param pageSize int类型 一页显示的最大条数
	 * @describe 分页查询\
	 * @return Page
	 */
	public Page findMetroAccidentCaseByPage2(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroAccidentCase t ";
		String countHql = "select count(*) from MetroAccidentCase t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("approvalStatus")){
					if(filter.get(paramName)==null||filter.get(paramName).equals("eeee")||filter.get(paramName).equals("")){
						filterPart += "t." + paramName + "<> :" + paramName;
						args.add(new HqlParameter(paramName, "0001"));
					}else{
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
					
				}else if(paramName.equals("metroStationName")||paramName.equals("keyWord")||paramName.equals("responsibleDeptName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("occurSTime1")){
					filterPart += "t.occurSTime >= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else if(paramName.equals("occurSTime2")){
					filterPart += "t.occurSTime <= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				
				filterCounter++;
			}
			filterPart += " order by t.occurSTime desc";
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	/**
	 * @author yaochenglong
	 * @param caseId String类型:主键
	 * @describe 根据主键查询
	 * @return MetroAccidentCase
	 */
	public MetroAccidentCase findMetroAccidentCaseByCaseId(String caseId){
		
		String hql = "from MetroAccidentCase a where a.caseId='"+caseId+"'";
		List<MetroAccidentCase> metroAccidentCaseList = getHibernateTemplate().find(hql);
		if(metroAccidentCaseList!=null && metroAccidentCaseList.size()!=0){
			return metroAccidentCaseList.get(0); 
		}else{
			return null;
		}
		
		
	}
	
	public Page findMetroAccidentCaseByUpdatePerson(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroAccidentCase t ";
		String countHql = "select count(*) from MetroAccidentCase t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("approvalStatus")){
					if(filter.get(paramName).equals("eeee")){
						filterPart += "t." + paramName + "<> :" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}else{
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
					
				}else if(paramName.equals("metroStationName")||paramName.equals("keyWord")||paramName.equals("responsibleDeptName")||paramName.equals("updatePerson")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("occurSTime1")){
					filterPart += "t.occurSTime >= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else if(paramName.equals("occurSTime2")){
					filterPart += "t.occurSTime <= to_date(' " + filter.get(paramName) +"','yyyy-MM-dd')";
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				
				filterCounter++;
			}
			filterPart += " order by t.occurSTime desc";
			
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
}
