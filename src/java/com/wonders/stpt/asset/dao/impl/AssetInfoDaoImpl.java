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

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.wonders.stpt.asset.dao.AssetInfoDao;
import com.wonders.stpt.asset.entity.bo.AssetInfo;
import com.wonders.stpt.asset.entity.bo.AssetInfoHistory;
import com.wonders.stpt.asset.entity.bo.CfCodeInfo;
import com.wonders.stpt.asset.service.AssetInfoService;
import com.wonders.stpt.securityreport.entity.bo.SecReport;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * AssetInfoʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-11
 * @author modify by $Author$
 * @since 1.0
 */

public class AssetInfoDaoImpl extends AbstractHibernateDaoImpl<AssetInfo> implements AssetInfoDao {
	
	public Page findAssetInfoByPage(Map<String, Object> filter, int pageNo,int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetInfo t ";
		String countHql = "select count(*) from AssetInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("assetId") || paramName.equals("assetName") 
						|| paramName.equals("specification") || paramName.equals("model") || paramName.equals("owner") || paramName.equals("usePerson")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("routeNum") && filter.get(paramName).equals("-1") ){
					filterPart += "t." + paramName + " <> :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("useTime")){
					filterPart += "t." + paramName + " >= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("stopuseTime")){
					filterPart += "t." + paramName + " <= :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("operateTime")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)+"%"));
				}else if(paramName.equals("registry") && filter.get(paramName).equals("0")){
					filterPart += "t." + paramName + " <> :" + paramName;
					args.add(new HqlParameter(paramName, "1"));
				}else if(paramName.equals("yuanzhi")){
					filterPart += "to_number(decode(translate(t." + paramName + ",'\\1234567890.','\\'),null,t." + paramName + ",'-1'))>=0 " +
							"and to_number(decode(translate(t." + paramName + ",'\\1234567890.','\\'),null,t." + paramName + ",'-1'))< :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("useState")){
					if(filter.get(paramName).equals("scrap")){	//scarp：已报废的资产不查询
						filterPart += "(t.useState !=1164 or t.useState is null)";
						//args.add(new HqlParameter(paramName, "1164"));
//						filterPart += "t.removed='0'";
					}else{
						filterPart += "t." + paramName + "=:" + paramName;
						args.add(new HqlParameter(paramName, filter.get(paramName)));
					}
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
			filterPart += " and t.removed='0'";
		}else{
			filterPart += " where t.removed='0'";
		}
		filterPart += " order by t.assetId asc";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}
	
	public List<AssetInfo> findAllAssetInfoByFilter(Map<String, Object> filter) {
		if (filter == null)
			filter = new HashMap<String, Object>();
//		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetInfo t ";
//		String countHql = "select count(*) from AssetInfo t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("assetId") || paramName.equals("assetName") 
						|| paramName.equals("specification") || paramName.equals("model") || paramName.equals("owner") || paramName.equals("usePerson")){
					filterPart += "t." + paramName + " like %" + filter.get(paramName)+"%";
//					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("routeNum") && filter.get(paramName).equals("-1") ){
					filterPart += "t." + paramName + " <> '" + filter.get(paramName)+"'";
//					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "='" + filter.get(paramName)+"'";
//					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
//			filterPart += "and t.useState !=1164 or t.useState is null";
			filterPart += " and (t.useState !=1164 or t.useState is null)";
			filterPart += " and t.removed='0'";
		}else{
			filterPart += " where t.removed='0'";
		}
		filterPart += " order by t.assetId asc ";
		
		hql += filterPart;
		
		return getHibernateTemplate().find(hql); 
	}
	

	public void saveAssetInfo(AssetInfo assetInfo) {
		getHibernateTemplate().save(assetInfo);
	}

	public AssetInfo findById(String id) {
		String hql = "from AssetInfo t where t.id='"+id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return (AssetInfo) query.uniqueResult();
	}

	public void updateAssetInfo(AssetInfo assetInfo) {
		getHibernateTemplate().update(assetInfo);
	}

	public void deleteAssetInfo(AssetInfo assetInfo) {
		getHibernateTemplate().delete(assetInfo);
	}

	public AssetInfo findByAssetId(String id) {
		String hql ="from AssetInfo t where t.assetId='"+id+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return (AssetInfo) query.uniqueResult();
	}

	public AssetInfo findByXH(String xh) {
		String hql ="from AssetInfo t where t.xh='"+xh+"' and t.removed='0'";
		Query query = getSession().createQuery(hql);
		return (AssetInfo) query.uniqueResult();
	}

	public String findNextXHByRouteNum(String routeNum) {
		String hql ="from AssetInfo t where t.routeNum='"+routeNum+"' and t.removed='0' order by t.id DESC";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		List<AssetInfo> list = query.list();
		if(list!=null && list.size()>0 && list.get(0).getXh()!=null){
			return (Long.valueOf(list.get(0).getXh())+1)+"";
		}
		return null;
	}

	public AssetInfo findByRouteNumAndAssetId(String routeNum, String assetId) {
		String hql ="from AssetInfo t where t.routeNum='"+routeNum+"' and t.assetId='"+assetId+"' and t.removed='0' order by t.id DESC";
		Query query = getSession().createQuery(hql);
		List<AssetInfo> list = query.setMaxResults(1).list();
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	public void saveAssetInfoList(List<AssetInfo> assetInfoList) {
		getHibernateTemplate().saveOrUpdateAll(assetInfoList);
	}


	/** 
	 * 导出资产清册维护Excel
	 * author:mengjie
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findAssetInfoForExport (Map<String, Object> filter,String order){			
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetInfo t ";
//		String countHql = "select count(*) from AssetInfo t ";
		String filterPart = " where t.removed='0' ";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				
				String paramName = i.next();
				
				if(paramName.equals("assetId")){
					String paramNameVal = "";
					try {
						paramNameVal = java.net.URLDecoder.decode((String) filter.get(paramName),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					filterPart += " and t." + paramName + " like '%" + paramNameVal+"%'";
				}else if (paramName.equals("yuanzhi")) {
					filterPart += " and to_number(t." + paramName + ") <= '" + filter.get(paramName)+"'";
				}
				else if(!filter.get(paramName).equals("-1")){
					filterPart += " and t." + paramName + " = '" + filter.get(paramName)+"'";
				}
				filterCounter++;
			}
		}
		filterPart += " order by t.id DESC";
		
		//hql += filterPart;
//		filterPart += " t.assetName='chrom1' and t.routeNum='2000' order by t.id DESC";
		
//		System.out.println("##filterPart##"+hql+filterPart);
//		java.net.URLEncoder.encode(arg0);
//		List<AssetInfo> list = (List<AssetInfo>) findByHQLWithPage(hql+filterPart,args, filterCounter, filterCounter, filterPart);
		
		List<AssetInfo> list = getHibernateTemplate().find(hql+filterPart); 
		return list;
	}

	/** 
	 * 导出资产动态管理Excel
	 * author:mengjie
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 */
	public List<AssetInfo> findDynamicManagementForExport (Map<String, Object> filter,String order){			
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AssetInfo t ";
//		String countHql = "select count(*) from AssetInfo t ";
		String filterPart = " where t.removed='0' and t.registry='1' ";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				
				String paramName = i.next();
				
				if(paramName.equals("assetId")){
					String paramNameVal = "";
					try {
						paramNameVal = java.net.URLDecoder.decode((String) filter.get(paramName),"utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					filterPart += " and t." + paramName + " like '%" + paramNameVal+"%'";
				}else if(!filter.get(paramName).equals("-1")){
					filterPart += " and t." + paramName + " = '" + filter.get(paramName)+"'";
				}
				filterCounter++;
				
			}
		}
		
		filterPart += " order by t.id DESC";
		
		//hql += filterPart;
//		filterPart += " t.assetName='chrom1' and t.routeNum='2000' order by t.id DESC";
		
/*		test sql‘s grammar
		System.out.println("##filterPart##"+hql+filterPart);  */
		
		List<AssetInfo> list = getHibernateTemplate().find(hql+filterPart); 
		String hqlHistory = "select t from AssetInfoHistory t where t.removed='0' and t.registry='1' ";
		String partH ="";
		for(int i=0;i< list.size();i++){
			if(i==list.size()-1){
				partH+= list.get(i).getId()+"";
			}else{
				partH+= list.get(i).getId()+",";
			}
		}
		hqlHistory+="and t.assetInfoId in("+partH+") order by t.id desc";
		
		List<AssetInfoHistory> listHistory = new ArrayList<AssetInfoHistory>();
		if(!"".equals(partH))
		  listHistory = getHibernateTemplate().find(hqlHistory); 
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean s  =true;
		try {
			AssetInfo infoBo = new AssetInfo();
			AssetInfoHistory hisBo =  new AssetInfoHistory();
			for(int i=0;i< list.size();i++){
				for(int j=0;j< listHistory.size();j++){
					infoBo = list.get(i);
					hisBo = listHistory.get(j);
					
					if(infoBo.getId().toString().equals(hisBo.getAssetInfoId())){
						if(sd.parse(infoBo.getOperateTime()).before(sd.parse(hisBo.getOperateTime()))){
							Long infoId = infoBo.getId();
							BeanUtils.copyProperties(hisBo,infoBo ,new String[] { "id" });
							infoBo.setId(infoId);
//							infoBo.setAssetName(hisBo.getAssetName());
							
						}
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

	public void inventory(String[] id) {
		String sql ="update T_ASSET_INFO t set t.REGISTRY='1' where t.id in ";
		if(id!=null && id.length>0){
			sql += "(";
			for(int i=0; i<id.length; i++){
				if(i!=id.length-1){
					sql += id[i]+",";
				}else{
					sql += id[i]+")";
				}
			}
		}
		getSession().createSQLQuery(sql).executeUpdate();
	}

	public List<AssetInfo> findAssetInfoByAssetId(String assetId) {
		String hql ="from AssetInfo t where t.assetId='"+assetId+"' and t.registry ='1' order by t.operateTime DESC";
		Query query = getSession().createQuery(hql);
		return query.setMaxResults(1).list();
	}
	
	public List<AssetInfo> findAllAsset(){
//		String hql = "select new AssetInfo(assetId,xjpdjlTime,assetName,model,useTime) from AssetInfo t where t.removed='0'";
//		String hql = "select t_asset_task_check.*,t_user.* from t_asset_task_check inner join t_user on t_asset_task_check.checkperson =t_user.name ";
		String hql = "from AssetInfo t where t.removed='0' order by t.id desc";
//		Query query = getSession().createQuery(hql); 
		return getHibernateTemplate().find(hql);
	} 
	
//	public List<AssetInfo> findAllAsset(){
//		String hql = "from AssetInfo t where t.removed='0'";
//		return getHibernateTemplate().find(hql);
//	} 
	
//	public List<Object> findCfCodeInfoIdByTypeIdAndNameAndCode(long typeId,String name, String code) {
//		String hql ="select t.id from CfCodeInfo t where t.name='"+name+"' and t.typeId="+typeId+" and t.code like '%"+code+"%' and t.removed='0'";
//		Query query = getSession().createQuery(hql); 
//		return query.setMaxResults(1).list();
//	}
	
//	public static void main(String[] args) {
//		Date time=new Date();
//		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		String a="2012-01-01 18:09:20";
//		String b="2012-01-01 18:09:20";
//		
//		String c="2012-01-02 01:01:01";
//		boolean s  =true;
//		try {
//			time = sd.parse(a);
//			s  = sd.parse(a).after(sd.parse(b));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(s);
//	}
	
	
	
	
	
	

	
}
