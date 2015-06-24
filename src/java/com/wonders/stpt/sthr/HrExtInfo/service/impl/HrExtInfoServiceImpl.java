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

package com.wonders.stpt.sthr.HrExtInfo.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrExtInfo.dao.HrExtInfoDao;
import com.wonders.stpt.sthr.HrExtInfo.entity.bo.HrExtInfo;
import com.wonders.stpt.sthr.HrExtInfo.service.HrExtInfoService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-7
 * @author modify by $Author$
 * @since 1.0
 */

public class HrExtInfoServiceImpl implements HrExtInfoService {
	private HrExtInfoDao hrExtInfoDao;

	public void setHrExtInfoDao(HrExtInfoDao hrExtInfoDao) {
		this.hrExtInfoDao = hrExtInfoDao;
	}

	public void addHrExtInfo(HrExtInfo hrExtInfo) {
		hrExtInfoDao.save(hrExtInfo);
	}

	public void deleteHrExtInfo(HrExtInfo hrExtInfo) {
		hrExtInfoDao.delete(hrExtInfo);
	}

	public HrExtInfo findHrExtInfoById(String id) {
		return hrExtInfoDao.load(id);
	}

	public void updateHrExtInfo(HrExtInfo hrExtInfo) {
		hrExtInfoDao.update(hrExtInfo);
	}

	public Page findHrExtInfoByPage(int pageNo, int pageSize) {
		Page page = hrExtInfoDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHrExtInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return hrExtInfoDao.findHrExtInfoByPage(filter, pageNo, pageSize);
	}
	
	/**
	 * @author ycl
	 * @describe 查询所有
	 * @return
	 */
	public List<HrEt> findAllHrEt(){
		return hrExtInfoDao.findAllHrEt();
	}
	
	/**
	 * @author ycl
	 * @describe 根据类别查询所有数据项
	 * @param hretId 类型id
	 * @return List<HrEtD>
	 */
	public List<Object[]> findAllHrEtDByType(String hretId){
		return hrExtInfoDao.findAllHrEtDByType(hretId);
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
		return hrExtInfoDao.findAllHrExtInfoByParam(hrId, hretId,paramList);
	}
	
	/**
	 * @author sunjiawei
	 * @param id String类型:主键
	 * @describe 根据主键删除
	 */
	public void deleteById(String id){
		HrExtInfo hrExtInfo = findHrExtInfoById(id);
		if(hrExtInfo!=null){
			hrExtInfoDao.delete(hrExtInfo);
		}
	}
}
