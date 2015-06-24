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

package com.wonders.stpt.sthr.HrBInfo.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.HrBInfo.dao.HrBInfoDao;
import com.wonders.stpt.sthr.HrBInfo.entity.bo.HrBInfo;
import com.wonders.stpt.sthr.HrBInfo.service.HrBInfoService;
import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrBInfoServiceImpl implements HrBInfoService {
	private HrBInfoDao hrBInfoDao;

	public void setHrBInfoDao(HrBInfoDao hrBInfoDao) {
		this.hrBInfoDao = hrBInfoDao;
	}

	public void addHrBInfo(HrBInfo hrBInfo) {
		hrBInfoDao.save(hrBInfo);
	}

	public void deleteHrBInfo(HrBInfo hrBInfo) {
		hrBInfoDao.delete(hrBInfo);
	}

	public HrBInfo findHrBInfoById(String id) {
		return hrBInfoDao.load(id);
	}

	public void updateHrBInfo(HrBInfo hrBInfo) {
		hrBInfoDao.update(hrBInfo);
	}

	public Page findHrBInfoByPage(int pageNo, int pageSize) {
		Page page = hrBInfoDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHrBInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize,String order) {
		return hrBInfoDao.findHrBInfoByPage(filter, pageNo, pageSize,order);
	}
	
	/**
	 * @author sunjiawei
	 * @param id String类型:主键
	 * @describe 根据主键删除
	 */
	public void deleteByHrId(String hrId){
		HrBInfo hrBInfo= findHrBInfoById(hrId);
		if(hrBInfo!=null){
			hrBInfoDao.delete(hrBInfo);
		}
	}
	
	/**
	 * @author ycl
	 * @describe 查询所有
	 * @return
	 */
	public List<HrEt> findAllHrEt(){
		return hrBInfoDao.findAllHrEt();
	}
	
	/**
	 * @author ycl
	 * @describe 根据类别查询所有数据项
	 * @param hretId 类型id
	 * @return List<HrEtD>
	 */
	public List<HrEtD> findAllHrEtDByType(String hretId){
		return hrBInfoDao.findAllHrEtDByType(hretId);
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
		return hrBInfoDao.findAllHrExtInfoByParam(hrId, hretId,paramList);
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrBInfoList(String hrId,String jobNumber,String name,String sex,String birthday,String mobilePhone,String cCompany,String position,String updatePerson,String companyId){
		hrBInfoDao.updateHrBInfoList(hrId,jobNumber,name,sex,birthday,mobilePhone,cCompany,position,updatePerson,companyId);
	}
	
	/**
	 * @author sunjiawei
	 * 根据条件找出所有非系统字段，包括hr_b_info和hr_ext_info两张表
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 * @param hret_name:扩展类别	
	 */
	public List<Object[]> findHrBInfoForExport (Map<String, Object> filter,String order,String hret_name){
		return hrBInfoDao.findHrBInfoForExport(filter, order, hret_name);
	}
	
	/**
	 * @author sunjiawei
	 * 根据扩展类别找出所有扩展字段
	 * @param type_name:扩展类别	 
	 */
	public List<Object[]> findTypeNameForExport(String type_name){
		return hrBInfoDao.findTypeNameForExport(type_name);
	}
	
	/**
	 * @author sunjiawei
	 * 找出权限开关是否开启，“1”为开，“0”为关
	 */
	public List<Object> findHrLimit(){
		return hrBInfoDao.findHrLimit();
	}
	
	/**
	 * @author sunjiawei
	 * 改变权限
	 * @param limit:权限，“1”为开，“0”为关 
	 */
	public void changeLimit(String limit){
		hrBInfoDao.changeLimit(limit);
	}
	
	/**
	 * @author sunjiawei
	 * 找出能控制权限的人
	 */
	public List<Object> findLimitPerson(){
		return hrBInfoDao.findLimitPerson();
	}
}
