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

package com.wonders.stpt.sthr.HrEtD.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.dao.HrEtDDao;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HrEtD实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrEtDDaoImpl extends AbstractHibernateDaoImpl<HrEtD> implements
		HrEtDDao {
	public Page findHrEtDByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HrEtD t ";
		String countHql = "select count(*) from HrEtD t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("itemName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
			if(100==pageSize) filterPart += " and t.removed='0' and t.isLShow='1' order by t.sortingOrder ASC";
			else filterPart += " and t.removed='0' order by t.sortingOrder ASC";
		}else{
			if(100==pageSize) filterPart += " where t.removed='0' and t.isLShow='1' order by t.sortingOrder ASC";
			else filterPart += " where t.removed='0' order by t.sortingOrder ASC";
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	
	/**
	 * @author ycl
	 * @param id 主键
	 * @describe 根据主键查询
	 * @return
	 */
	public String findHrEtTypeNameById(String id){
		
		String hql = "from HrEt a where a.hretId='"+id+"' and a.removed='0'";
		List<HrEt> hretList = getHibernateTemplate().find(hql);
		if(hretList!=null && hretList.size()!=0){
			return hretList.get(0).getTypeName();
		}else{
			return null;
		}
	}
	
	/**
	 * @author ycl
	 * @param inputType
	 * @param hretId
	 * @describe 根据类型查询
	 * @return	List<HrEtD>
	 */
	public List<HrEtD> findAllHrEtDByInputType(String hretId,String inputType){
		String hql = "from HrEtD a where a.inputType like '"+inputType+"%' and a.hretId='"+hretId+"' order by a.hretdId DESC";
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * @author ycl
	 * @describe 根据itemName查询
	 * @param hretId
	 * @param itemName
	 * @return boolean
	 */
	public boolean findHrEtDByItemName(String hretid,String itemName){
		String hql = "from HrEtD t where t.itemName='"+itemName+"' and t.hretId='"+hretid+"' and t.removed='0'";
		List<HrEtD> hretdList = getHibernateTemplate().find(hql);
		if(hretdList!=null && hretdList.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author ycl
	 * @describe 根据类型、排列顺序查询
	 * @param hretId
	 * @param sortingOrder
	 * @return boolean
	 */
	public boolean findHrEtDBySortingOrder(String hretId,long sortingOrder){
		String hql = "from HrEtD t where t.sortingOrder='"+sortingOrder+"' and t.hretId='"+hretId+"' and t.removed='0'";
		List<HrEtD> hretdList = getHibernateTemplate().find(hql);
		if(hretdList!=null && hretdList.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @author ycl
	 * @describe 逻辑删除
	 * @param hrEtD
	 */
	public void deleteHrEtDByLogic(HrEtD hrEtD){
		getHibernateTemplate().update(hrEtD);
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键查询
	 * @param id
	 */
	public HrEt findHrEtById(String id){
		String hql ="from HrEt t where t.hretId='"+id+"'";
		List<HrEt> hretList = getHibernateTemplate().find(hql);
		if(hretList!=null && hretList.size()!=0){
			return hretList.get(0);
		}else{
			return null;
		}
	}
	
	
	/**
	 * @author ycl
	 * @describe 查询表中显示字段的总数
	 * @return
	 */
	public int findCountOfIsLSHowByHretId(String hretId){
		String sql = "select count(*) from HR_ET_D t where t.HRET_ID='"+hretId+"' and t.REMOVED='0' and t.IS_L_SHOW='1'";
		Session session = this.getSession();
		List<Object> objectList = session.createSQLQuery(sql).list();
		if(objectList!=null && objectList.size()>0){
			return Integer.valueOf(objectList.get(0).toString());
		}else{
			return -1;
		}
		
	}
	
	/**
	 * @author sunjiawei
	 * @describe 查询HrExtInfo表中字段
	 * @param id为HrExtInfo表主键
	 * @param hretId为HrEtD表外键	
	 */
	public List<Object[]> findHrExtInfoById(String id,String hretId){
		String sql="select t.ITEM_F_NAME from HR_ET_D t where t.HRET_ID='"+hretId+"' and t.REMOVED='0' order by t.SORTING_ORDER ASC";
		Session session = this.getSession();
		List<String> list = session.createSQLQuery(sql).list();
		sql="select t.report_person,t.report_person_name,";
		for(int i=0;i<list.size();i++){
			if(i!=list.size()-1){				
				sql +="t."+list.get(i)+",";							
			}else{
				sql +="t."+list.get(i);				
			}
			if("LT".equals(list.get(i).substring(0,2))){
				sql=sql.replace("t."+list.get(i), "to_char(t."+list.get(i)+")");					
			}	
		}
		sql += " from HR_EXT_INFO t where t.id='"+id+"'";       		
		return session.createSQLQuery(sql).list();
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtDList(String hretdId,int sortingOrder,String itemName,String isLShow,String updatePerson){
		String sql = "update HR_ET_D set sorting_order = "+sortingOrder+",item_name = '"+itemName+"',is_l_show = '"+isLShow+"',update_person = '"+updatePerson+"',update_time = sysdate where hretd_id = '"+hretdId+"'";
		Session session = this.getSession();
		session.createSQLQuery(sql).executeUpdate();
	}
}
