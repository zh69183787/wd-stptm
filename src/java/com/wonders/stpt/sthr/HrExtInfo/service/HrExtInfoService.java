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

package com.wonders.stpt.sthr.HrExtInfo.service;

import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrExtInfo.entity.bo.HrExtInfo;
import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-7
 * @author modify by $Author$
 * @since 1.0
 */

public interface HrExtInfoService {
	/**
	 * 删除实体对象
	 * 
	 * @param hrExtInfo
	 */
	public void deleteHrExtInfo(HrExtInfo hrExtInfo);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public HrExtInfo findHrExtInfoById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param hrExtInfo
	 */
	public void addHrExtInfo(HrExtInfo hrExtInfo);

	/**
	 * 更新数据到数据库
	 * 
	 * @param hrExtInfo
	 *            实体
	 */
	public void updateHrExtInfo(HrExtInfo hrExtInfo);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findHrExtInfoByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findHrExtInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize);
	
	/**
	 * @author ycl
	 * @describe 查询所有
	 * @return
	 */
	public List<HrEt> findAllHrEt();
	
	/**
	 * @author ycl
	 * @describe 根据类别查询所有数据项
	 * @param hretId 类型id
	 * @return List<HrEtD>
	 */
	public List<Object[]> findAllHrEtDByType(String hretId);
	
	/**
	 * @author ycl
	 * @describe 根据3个id查询所有个人扩展信息
	 * @param hrId HrBInfo的主键
	 * @param hretId HrEt的主键
	 * @param paramList 存放要查询的字段
	 * @return List<HrExtInfo>
	 */
	public List<Object[]> findAllHrExtInfoByParam(String hrId,String hretId,List<String> paramList);
	
	/**
	 * @author sunjiawei
	 * @param id String类型:主键
	 * @describe 根据主键删除
	 */
	public void deleteById(String id);
}
