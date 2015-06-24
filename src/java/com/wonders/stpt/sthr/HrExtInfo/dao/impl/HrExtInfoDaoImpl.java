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

package com.wonders.stpt.sthr.HrExtInfo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrExtInfo.dao.HrExtInfoDao;
import com.wonders.stpt.sthr.HrExtInfo.entity.bo.HrExtInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HrExtInfo实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-7
 * @author modify by $Author$
 * @since 1.0
 */

public class HrExtInfoDaoImpl extends AbstractHibernateDaoImpl<HrExtInfo>
		implements HrExtInfoDao {
	public Page findHrExtInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HrExtInfo t ";
		String countHql = "select count(*) from HrExtInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				filterPart += "t." + paramName + "=:" + paramName;
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	/**
	 * @author ycl
	 * @describe 查询所有
	 * @return
	 */
	public List<HrEt> findAllHrEt(){
		String hql = "from HrEt t where t.removed='0' order by t.sortingOrder ASC";
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * @author ycl
	 * @describe 根据类别查询所有数据项
	 * @param hretId 类型id
	 * @return List<HrEtD>
	 */
	public List<Object[]> findAllHrEtDByType(String hretId){
//		String hql ="from HrEtD t where t.hretId='"+hretId+"' and t.removed='0' and t.isLShow=1 order by t.sortingOrder ASC";
		String sql = "select t.ITEM_NAME,t.ITEM_F_NAME,t.HRET_ID from HR_ET_D t where t.HRET_ID='"+hretId+"' and t.REMOVED='0' and t.IS_L_SHOW=1 order by t.SORTING_ORDER ASC";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();
	}
	
	/**
	 * @author ycl
	 * @describe 根据3个id查询所有个人扩展信息
	 * @param hrId HrBInfo的主键
	 * @param hretId HrEt的主键
	 * @param paramList 存放要查询的字段
	 * @return List<HrExtInfo>
	 */
	public List<Object[]> findAllHrExtInfoByParam(String hrId,String hretId,List<String> paramList){
		paramList.add("ID");
		paramList.add("HRET_ID");
		paramList.add("UPDATE_PERSON");
		String sql = "select ";
		for(int i=0;i<paramList.size();i++){
			if(i!=paramList.size()-1){
				if(paramList.get(i).contains("L")){
					sql +="to_char(t."+paramList.get(i)+"),";
				}else{
					sql +="t."+paramList.get(i)+",";
				}
			}else{
				if(paramList.get(i).contains("L")){
					sql +="to_char(t."+paramList.get(i)+")";
				}else{
					sql +="t."+paramList.get(i);
				}
			}
		}
		sql += " from HR_EXT_INFO t where t.HR_ID='"+hrId+"' and t.HRET_ID='"+hretId+"' order by t.ID DESC";
		Session session = this.getSession();
		
		SQLQuery sqlQuery = session.createSQLQuery(sql).addScalar("");
		
		
		return session.createSQLQuery(sql).list();
	}
}
