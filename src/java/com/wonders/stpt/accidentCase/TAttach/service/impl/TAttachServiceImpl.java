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

package com.wonders.stpt.accidentCase.TAttach.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.accidentCase.TAttach.dao.TAttachDao;
import com.wonders.stpt.accidentCase.TAttach.entity.bo.TAttach;
import com.wonders.stpt.accidentCase.TAttach.service.TAttachService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TAttachServiceImpl implements TAttachService {
	private TAttachDao attachDao;

	public void setAttachDao(TAttachDao attachDao) {
		this.attachDao = attachDao;
	}

	public void addTAttach(TAttach tAttach) {
		attachDao.save(tAttach);
	}

	public void deleteTAttach(TAttach tAttach) {
		attachDao.delete(tAttach);
	}

	public TAttach findTAttachById(Long id) {
		return attachDao.load(id);
	}

	public void updateTAttach(TAttach tAttach) {
		attachDao.update(tAttach);
	}

	public Page findTAttachByPage(int pageNo, int pageSize) {
		Page page = attachDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findTAttachByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		return attachDao.findTAttachByPage(filter, pageNo, pageSize);
	}
	
	public void saveTAttach(TAttach tAttach){
		attachDao.save(tAttach);
	}
	
	/**
	 * @author yaochenglong
	 * @describe 根据groupId查询附件
	 * @param groupId String类型:groupId值
	 * @return List<TAttach>类型
	 */
	public List<TAttach> findTAttachByGroupID(String groupid){
		
		return attachDao.findTAttachByGroupID(groupid);
		
	}
	
}
