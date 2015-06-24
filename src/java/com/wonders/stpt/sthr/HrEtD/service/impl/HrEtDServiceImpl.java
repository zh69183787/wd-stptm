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

package com.wonders.stpt.sthr.HrEtD.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.dao.HrEtDDao;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wonders.stpt.sthr.HrEtD.service.HrEtDService;
import com.wonders.stpt.sthr.HrExtInfo.dao.HrExtInfoDao;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class HrEtDServiceImpl implements HrEtDService {
	private HrEtDDao hrEtDDao;
	private HrExtInfoDao hrExtInfoDao;

	public HrExtInfoDao getHrExtInfoDao() {
		return hrExtInfoDao;
	}

	public void setHrExtInfoDao(HrExtInfoDao hrExtInfoDao) {
		this.hrExtInfoDao = hrExtInfoDao;
	}

	public void setHrEtDDao(HrEtDDao hrEtDDao) {
		this.hrEtDDao = hrEtDDao;
	}

	public void addHrEtD(HrEtD hrEtD) {
		hrEtDDao.save(hrEtD);
	}

	public void deleteHrEtD(HrEtD hrEtD) {
		hrEtDDao.delete(hrEtD);
	}

	public HrEtD findHrEtDById(String id) {
		return hrEtDDao.load(id);
	}

	public void updateHrEtD(HrEtD hrEtD) {
		hrEtDDao.update(hrEtD);
	}

	public Page findHrEtDByPage(int pageNo, int pageSize) {
		Page page = hrEtDDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findHrEtDByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return hrEtDDao.findHrEtDByPage(filter, pageNo, pageSize);
	}
	
	/**
	 * @author ycl
	 * @param id 主键
	 * @describe 根据主键查询
	 * @return
	 */
	public String findHrEtTypeNameById(String id){
		return hrEtDDao.findHrEtTypeNameById(id);
	}
	
	/**
	 * @author ycl
	 * @param inputType
	 * @param hretId
	 * @describe 根据类型查询
	 * @return	List<HrEtD>
	 */
	public List<HrEtD> findAllHrEtDByInputType(String hretId,String inputType){
		return hrEtDDao.findAllHrEtDByInputType(hretId,inputType);
	}
	
	/**
	 * @author ycl
	 * @describe 根据itemName查询
	 * @param hretId
	 * @param itemName
	 * @return boolean
	 */
	public boolean findHrEtDByItemName(String hretId,String itemName){
		return hrEtDDao.findHrEtDByItemName(hretId,itemName);
	}
	
	/**
	 * @author ycl
	 * @describe 根据类型、排列顺序查询
	 * @param hretId
	 * @param sortingOrder
	 * @return boolean
	 */
	public boolean findHrEtDBySortingOrder(String hretId,long sortingOrder){
		return hrEtDDao.findHrEtDBySortingOrder(hretId,sortingOrder);
	}
	
	/**
	 * @author ycl
	 * @describe 逻辑删除
	 * @param hrEtD
	 */
	public void deleteHrEtDByLogic(HrEtD hrEtD){
		hrEtDDao.deleteHrEtDByLogic(hrEtD);
	}
	
	/**
	 * @author ycl
	 * @describe 根据主键查询
	 * @param id
	 */
	public HrEt findHrEtById(String id){
		return hrEtDDao.findHrEtById(id);
	}
	
	/**
	 * @author ycl
	 * @describe 查询表中显示字段的总数
	 * @return
	 */
	public int findCountOfIsLSHowByHretId(String hretId){
		return hrEtDDao.findCountOfIsLSHowByHretId(hretId);
	}
	
	/**
	 * @author sunjiawei
	 * @describe 查询HrExtInfo表中字段
	 * @param id为HrExtInfo表主键
	 * @param hretId为HrEtD表外键	
	 */
	public List<Object[]> findHrExtInfoById(String id,String hretId){
		return hrEtDDao.findHrExtInfoById(id,hretId);
	}
	
	/**
	 * @author sunjiawei
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrEtDList(String hretdId,int sortingOrder,String itemName,String isLShow,String updatePerson){
		hrEtDDao.updateHrEtDList(hretdId, sortingOrder, itemName, isLShow, updatePerson);
	}
}
