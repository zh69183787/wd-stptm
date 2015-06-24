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

package com.wonders.stpt.sthr.HrBInfo.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.sthr.HrBInfo.entity.bo.HrBInfo;
import com.wonders.stpt.sthr.HrEt.entity.bo.HrEt;
import com.wonders.stpt.sthr.HrEtD.entity.bo.HrEtD;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface HrBInfoService {
	/**
	 * 删除实体对象
	 * 
	 * @param hrBInfo
	 */
	public void deleteHrBInfo(HrBInfo hrBInfo);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public HrBInfo findHrBInfoById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param hrBInfo
	 */
	public void addHrBInfo(HrBInfo hrBInfo);

	/**
	 * 更新数据到数据库
	 * 
	 * @param hrBInfo
	 *            实体
	 */
	public void updateHrBInfo(HrBInfo hrBInfo);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findHrBInfoByPage(int pageNo, int pageSize);

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
	public Page findHrBInfoByPage(Map<String, Object> filter, int pageNo,
			int pageSize,String order);
	
	/**
	 * @author sunjiawei
	 * @param id String类型:主键
	 * @describe 根据主键删除
	 */
	public void deleteByHrId(String hrId);
	
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
	public List<HrEtD> findAllHrEtDByType(String hretId);
	
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
	 * @describe 列表页面双击某条数据进行数据修改
	 */
	public void updateHrBInfoList(String hrId,String jobNumber,String name,String sex,String birthday,String mobilePhone,String cCompany,String position,String updatePerson,String companyId);

	/**
	 * @author sunjiawei
	 * 根据条件找出所有非系统字段，包括hr_b_info和hr_ext_info两张表
	 * @param filter:条件数据集
	 * @param order:排列顺序
	 * @param hret_name:扩展类别	
	 */
	public List<Object[]> findHrBInfoForExport (Map<String, Object> filter,String order,String hret_name);
	
	/**
	 * @author sunjiawei
	 * 根据扩展类别找出所有扩展字段
	 * @param type_name:扩展类别	 
	 */
	public List<Object[]> findTypeNameForExport(String type_name);
	
	/**
	 * @author sunjiawei
	 * 找出权限开关是否开启，“1”为开，“0”为关
	 */
	public List<Object> findHrLimit();
	
	/**
	 * @author sunjiawei
	 * 改变权限
	 * @param limit:权限，“1”为开，“0”为关 
	 */
	public void changeLimit(String limit);
	
	/**
	 * @author sunjiawei
	 * 找出能控制权限的人
	 */
	public List<Object> findLimitPerson();
}
