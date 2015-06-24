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

package com.wonders.stpt.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.wonders.stpt.asset.dao.CfCodeInfoDao;
import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * 
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public class CfCodeInfoDaoImpl extends AbstractHibernateDaoImpl<AssetInfo> implements CfCodeInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CfCodeInfo> findCfCodeInfo(long id) {
		String hql = "from CfCodeInfo t where t.codeInfoId='"+id+"' and t.removed='0' order by t.code ASC";
		return  getHibernateTemplate().find(hql);
	}

	@Override
	public CfCodeInfo findCfCodeInfoById(long id) {
		String hql = "from CfCodeInfo t where t.id='"+id+"' and t.removed='0' order by t.code ASC";
		return (CfCodeInfo) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CfCodeInfo> findCfCodeInfoListTypeById(long id) {
		String hql ="from CfCodeInfo t where t.id='"+id+"' and t.removed='0'";
		CfCodeInfo cfCodeInfo = (CfCodeInfo) getSession().createQuery(hql).uniqueResult();
		if(cfCodeInfo!=null){
			String hql2 ="from CfCodeInfo t where t.typeId='"+cfCodeInfo.getTypeId()+"' and t.codeInfoId='"+cfCodeInfo.getCodeInfoId()+"' and removed='0' order by t.code ASC";
			return getHibernateTemplate().find(hql2);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CfCodeInfo findByTypeIdAndCode(String typeId, String code) {
		String hql ="from CfCodeInfo t where t.typeId="+Long.valueOf(typeId)+" and t.code='"+code+"' and t.removed='0'";
		List<CfCodeInfo> list = getSession().createQuery(hql).setMaxResults(1).list();
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Object> findCfCodeInfoIdByCodeInfoIdAndName(long codeInfoId, String name) {
		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.codeInfoId="+codeInfoId+" and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}
	
	@Override
	public List<Object> findCfCodeInfoIdByCodeInfoIdAndCode(long codeInfoId,String code) {
		String hql ="select t.id from CfCodeInfo t where t.code='"+code+"' and t.codeInfoId="+codeInfoId+" and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}
	
	@Override
	public List<Object> findCfCodeInfoIdByCodeAndName(String code, String name) {
		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.code="+code+" and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}

	@Override
	public List<Object> findCfCodeInfoIdByTypeIdAndName(long typeId, String name) {
		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.typeId="+typeId+" and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}


	@Override
	public List<Object> findCfCodeInfoIdByTypeIdAndCode(long typeId, String code) {
		String hql ="select t.id from CfCodeInfo t where t.code='"+code+"' and t.typeId="+typeId+" and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}

	@Override
	public List<Object> findCodeById(String id) {
		String hql = "select t.code from CfCodeInfo t where t.id='"+id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return query.setMaxResults(1).list();
	}

	@Override
	public List<Object> findCfCodeInfoIdByTypeIdAndNameAndCode(long typeId,String name, String code) {
		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.typeId="+typeId+" and t.code like '%"+code+"%' and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}

	@Override
	public List<CfCodeInfo> findAllCfCodeInfo() {
		String hql = "from CfCodeInfo t where t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<CfCodeInfo> findCfCodeInfoIdByTypeId(long id) {
		String hql = "from CfCodeInfo t where t.removed='0'  and t.typeId="+id;
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<CfCodeInfo> findCfCodeInfoByTypeId(long typeId) {
		String hql = "from CfCodeInfo t where t.typeId='"+typeId+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object> findByTypeIdAndNameAndCodeInfoId(long typeId, String name,String code_info_id) {
		if(code_info_id==null){
			return null;
		}
		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.typeId='"+typeId+"' and t.codeInfoId ='"+code_info_id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql); 
		return query.setMaxResults(1).list();
	}

	public List<CfCodeInfo> findCfInfoCodeForStationByTypeId(long typeId,String code_info_id){
		String hql = "from CfCodeInfo t where t.typeId = '"+typeId+"' and t.codeInfoId != '"+code_info_id+"' and t.removed='0' order by t.code asc";
		return  getHibernateTemplate().find(hql);
	}
	
	public List<CfCodeInfo> findCfInfoCodeForAllTypeByLength(long typeId,int length){
//		String hql="";
		String hql = "from CfCodeInfo t where t.typeId = '"+typeId+"' and length(code)= '"+length+"' and t.removed='0' order by t.code asc";
		return  getHibernateTemplate().find(hql);
	}
}
