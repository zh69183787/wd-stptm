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

package com.wonders.stpt.sthr.HrEt.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.sthr.HrEt.dao.HrEtDao;
import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HrEt实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrEtDaoImpl extends AbstractHibernateDaoImpl<HrEt> implements
		HrEtDao {
	public Page findHrEtByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HrEt t ";
		String countHql = "select count(*) from HrEt t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("typeName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("updateTime")){
					filterPart += "t." + paramName + " > :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("eDate")){
					filterPart += "t." + "updateTime" + " <= :" + paramName;
					Date endDate = (Date) filter.get(paramName);
					endDate.setHours(23);
					endDate.setMinutes(59);
					endDate.setSeconds(59);
					args.add(new HqlParameter(paramName, endDate));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
			filterPart += " and t.removed='0' order by t.sortingOrder ASC";
		}else{
			filterPart += " where t.removed='0' order by t.sortingOrder ASC";
		}
		
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	/**
	 * @author ycl
	 * @param id
	 * 			主键
	 * @describe 根据主键查询
	 * @return
	 */
	public HrEt findHrEtById(String id){
		
		String hql = "from HrEt a where a.hretId='"+id+"'";
		List<HrEt> hretlList = getHibernateTemplate().find(hql);
		if(hretlList!=null && hretlList.size()>0){
			return hretlList.get(0);
		}else{
			return null;
		}
		
	}
	
	/**
	 * @author ycl
	 * @param hrEt
	 * 			hrEt实例
	 * @describe 逻辑删除
	 */
	public void deleteHrEtById(HrEt hrEt){
		getHibernateTemplate().update(hrEt);
	}
	
	/**
	 * @author ycl
	 * @param typeName 类型名称
	 * @return HrEt
	 */
	public boolean findHrEtByTypeName(String typeName){
		String hql ="from HrEt a where a.typeName='"+typeName+"' and a.removed='0'";
		List<HrEt> hretList = getHibernateTemplate().find(hql);
		if(hretList!=null && hretList.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author ycl
	 * @param sortingOrder 排序名称
	 * @return boolean
	 */
	public boolean findHrEtBySortingOrder(long sortingOrder){
		String hql = "from HrEt a where a.sortingOrder='"+sortingOrder+"' and a.removed='0'";
		List<HrEt> hretList = getHibernateTemplate().find(hql);
		if(hretList!=null && hretList.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author ycl
	 * @describe 根据hretId查询HrEtD
	 * @param hretId
	 * @return List<HrEtD>
	 */
	public List<HrEtD> findHrEtDByHretId(String hretId){
		String hql = "from HrEtD t where t.hretId='"+hretId+"'";
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * @author ycl
	 * @describe 更新HrEtD
	 * @param hretdList
	 */
	public void updateHrEtD(List<HrEtD> hretdList){
		getHibernateTemplate().saveOrUpdateAll(hretdList);
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtList(String hretId,String typeName,int sortingOrder,String updatePerson ){
		String sql="update HR_ET set type_name = '"+typeName+"',sorting_order = "+sortingOrder+",update_person = '"+updatePerson+"',update_time = sysdate where hret_id = '"+hretId+"'";
		Session session = this.getSession();
		session.createSQLQuery(sql).executeUpdate();
		
		sql = "update HR_ET_D set type_name = '"+typeName+"' where hret_id = '"+hretId+"'";
		session.createSQLQuery(sql).executeUpdate();
		
		sql = "update HR_EXT_INFO set hret_name = '"+typeName+"' where hret_id = '"+hretId+"'";
		session.createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * @author sunjiawei
	 * @describe 修改扩展数据类别名称的时候同时修改HR_EXT_INFO表中hret_name字段的值
	 */	
	public void updateHretName(String hretId,String hretName){
		String sql = "update HR_EXT_INFO set hret_name = '"+hretName+"' where hret_id = '"+hretId+"'";
		Session session = this.getSession();
		session.createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * @author sunjiawei
	 * @describe 找出所有未删除的数据
	 */	
	public List<HrEt> findAllTypeName(){
		/*
		String sql = "select type_name from HR_ET where removed ='0'";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();*/
		
		String hql = "from HrEt c where c.removed = '0'";
		
		return getHibernateTemplate().find(hql);
		
	}
	
	
}
