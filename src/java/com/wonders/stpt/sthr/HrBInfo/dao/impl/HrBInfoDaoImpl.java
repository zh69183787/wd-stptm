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

package com.wonders.stpt.sthr.HrBInfo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.sthr.HrBInfo.dao.HrBInfoDao;
import com.wonders.stpt.sthr.HrBInfo.entity.bo.HrBInfo;
import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HrBInfo实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrBInfoDaoImpl extends AbstractHibernateDaoImpl<HrBInfo> implements
		HrBInfoDao {
	public Page findHrBInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize,String order) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from HrBInfo t ";
		String countHql = "select count(*) from HrBInfo t ";
		String filterPart = "";
		int filterCounter = 0;		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("jobNumber")||paramName.equals("name")||paramName.equals("companyId")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("birthdayStart")){
					filterPart += "t.birthday >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("birthdayEnd")){
					filterPart += "t.birthday <= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("hretName")){
					filterPart += "t.hrId in(select h.hrId from HrExtInfo h where h.hretName=:"+"bb"+")";
					args.add(new HqlParameter("bb", filter.get(paramName)));
				}
//				else if(paramName.equals("birthday1")){							
//					filterPart += "t.birthday >= :" + "aa";						
//					args.add(new HqlParameter("aa",filter.get(paramName)));							
//				}else if(paramName.equals("birthday2")){							
//					filterPart += "t.birthday <= :" + "aa";						
//					args.add(new HqlParameter("aa",filter.get(paramName)));							
//				}
				else {
				    filterPart += "t." + paramName + "=:" + paramName;
				    args.add(new HqlParameter(paramName, filter.get(paramName)));
				}				
				filterCounter++;
			}			
		}
		if(order==null||"".equals(order)) filterPart += " ORDER BY t.updateTime DESC";
		else filterPart += " ORDER BY t.updateTime "+order;
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
	public List<HrEtD> findAllHrEtDByType(String hretId){
		String hql ="from HrEtD t where t.hretId='"+hretId+"' and t.removed='0' and t.isLShow=1 order by t.sortingOrder ASC";
		return getHibernateTemplate().find(hql);
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
//		session.getTransaction().begin();
		List<Object[]> resultList = session.createSQLQuery(sql).list();
//		session.getTransaction().commit();
//		session.close();
		return resultList;
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrBInfoList(String hrId,String jobNumber,String name,String sex,String birthday,String mobilePhone,String cCompany,String position,String updatePerson,String companyId){
		String sql="update HR_B_INFO set job_number = '"+jobNumber+"',name = '"+name+"',sex = '"+sex+"',birthday = to_date('"+birthday+"','yyyy-MM-dd'),mobile_phone = '"+mobilePhone+"',c_company = '"+cCompany+"',position = '"+position+"',update_time = sysdate,update_person = '"+updatePerson+"',company_id = '"+companyId+"' where hr_id ='"+hrId+"'";
		Session session = this.getSession();
		session.createSQLQuery(sql).executeUpdate();
	}
	
	
	/**
	 * @author sunjiawei
	 * 根据条件找出所有非系统字段，包括hr_b_info和hr_ext_info两张表
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 * @param hret_name:扩展类别	
	 */
	public List<Object[]> findHrBInfoForExport (Map<String, Object> filter,String order,String hret_name){
		String sql = "";
		Session session = this.getSession();
		if("".equals(hret_name)){
			sql = "select t.job_number,t.name,t.id_card,(case when t.sex ='1' then '男' when t.sex='0' then '女' end) as sex," +
					"to_char(t.birthday,'YYYY-MM-DD') as birthday,t.nation,t.birthplace,t.political_landscape,t.highest_degree," +
					"t.residential_address,t.zip_code,t.home_phone,t.mobile_phone,t.c_company,t.position,t.job_titles,t.technical_grade," +
					"t.company_address,t.company_zip,t.company_phone,t.hukou,t.is_retire,t.remarks from hr_b_info t where 1=1 ";
		}else{
			sql = "select t.job_number,t.name,t.id_card,(case when t.sex ='1' then '男' when t.sex='0' then '女' end) as sex," +
			"to_char(t.birthday,'YYYY-MM-DD') as birthday,t.nation,t.birthplace,t.political_landscape,t.highest_degree," +
			"t.residential_address,t.zip_code,t.home_phone,t.mobile_phone,t.c_company,t.position,t.job_titles,t.technical_grade," +
			"t.company_address,t.company_zip,t.company_phone,t.hukou,t.is_retire,t.remarks";
			String sql1 = "select item_f_name,sorting_order FROM hr_et_d where is_l_show = 1 and removed = 0 and type_name = '"+hret_name+"' order by sorting_order";
			//System.out.println("sql1+++++++++"+sql1);
			List<Object[]> list = session.createSQLQuery(sql1).list();
			if(null!=list&&list.size()>0){
				for(int i=0;i<list.size();i++){
					if("D".equals(String.valueOf(list.get(i)[0]).substring(0, 1))){
						sql += ",to_char(h."+list.get(i)[0]+",'YYYY-MM-DD')";
					}else if("L".equals(String.valueOf(list.get(i)[0]).substring(0, 1))){
						sql += ",to_char(h."+list.get(i)[0]+")";
					}else {
						sql += ",h."+list.get(i)[0];
					}
				}
			}
			sql += " from hr_b_info t,hr_ext_info h where t.hr_id=h.hr_id and h.hret_name='"+hret_name+"'";
		}
		if (!filter.isEmpty()) {			
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {				
				String paramName = i.next();				
				if(paramName.equals("job_number")||paramName.equals("name")||paramName.equals("company_id")){
					sql += " and t." + paramName + " like '%"+filter.get(paramName)+"%' " ;										
				}else if(paramName.equals("birthdayStart")){
					sql += " and t.birthday >= to_date('"+filter.get(paramName)+"','YYYY-MM-DD') " ;					
				}else if(paramName.equals("birthdayEnd")){
					sql += " and t.birthday <= to_date('"+filter.get(paramName)+"','YYYY-MM-DD') " ;
				}else {
				    sql += " and t." + paramName + " = '"+filter.get(paramName)+"' " ;				    
				}					
			}			
		}
		if(order==null||"".equals(order)) sql += " ORDER BY t.update_time DESC";
		else sql += " ORDER BY t.update_time "+order;
		//System.out.println("sql===="+sql);
		
		return session.createSQLQuery(sql).list();
	}
	
	/**
	 * @author sunjiawei
	 * 根据扩展类别找出所有扩展字段
	 * @param type_name:扩展类别	 
	 */
	public List<Object[]> findTypeNameForExport(String type_name){
		String sql = "select t.item_name,t.sorting_order from hr_et_d t where t.is_l_show = 1 and t.removed = 0 and t.type_name = '"+type_name+"' order by t.sorting_order";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();
	}
	
	/**
	 * @author sunjiawei
	 * 找出权限开关是否开启，“1”为开，“0”为关
	 */
	public List<Object> findHrLimit(){
		String sql = "select distinct(t.limit_button) from hr_limit t";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();
	}
	
	/**
	 * @author sunjiawei
	 * 改变权限
	 * @param limit:权限，“1”为开，“0”为关 
	 */
	public void changeLimit(String limit){
		String sql = "update hr_limit set limit_button='"+limit+"'";
		Session session = this.getSession();
		session.createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * @author sunjiawei
	 * 找出能控制权限的人
	 */
	public List<Object> findLimitPerson(){
		String sql = "select user_id from hr_limit";
		Session session = this.getSession();
		return session.createSQLQuery(sql).list();
	}
}
