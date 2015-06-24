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

package com.wonders.stpt.construction.TConstructionNotice.service.impl;

import com.wonders.stpt.construction.ConstructionMetroLine.entity.bo.ConstructionMetroLine;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.construction.TConstructionNotice.dao.TConstructionNoticeDao;
import com.wonders.stpt.construction.TConstructionNotice.entity.bo.TConstructionNotice;
import com.wonders.stpt.construction.TConstructionNotice.service.TConstructionNoticeService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class TConstructionNoticeServiceImpl implements
		TConstructionNoticeService {
	private TConstructionNoticeDao constructionNoticeDao;
	
	private static final int BUFFER_SIZE = 20 * 1024; // 20K

	public void setConstructionNoticeDao(
			TConstructionNoticeDao constructionNoticeDao) {
		this.constructionNoticeDao = constructionNoticeDao;
	}

	public void addTConstructionNotice(TConstructionNotice tConstructionNotice) {
		constructionNoticeDao.save(tConstructionNotice);
	}

	public void deleteTConstructionNotice(
			TConstructionNotice tConstructionNotice) {
		constructionNoticeDao.delete(tConstructionNotice);
	}

	public TConstructionNotice findTConstructionNoticeById(String id) {
		return constructionNoticeDao.load(id);
	}

	public void updateTConstructionNotice(
			TConstructionNotice tConstructionNotice) {
		constructionNoticeDao.update(tConstructionNotice);
	}

	public Page findTConstructionNoticeByPage(int pageNo, int pageSize) {
		Page page = constructionNoticeDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findTConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return constructionNoticeDao.findTConstructionNoticeByPage(filter,
				pageNo, pageSize);
	}
	
	/**
	 * @author ycl
	 * @describe 复制文件到本地
	 * @param src 源地址
	 * @param dst 目标地址
	 * 
	 */
	public void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
				
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @author ycl
	 * @describe 查询所有地铁线路
	 * @return List<MetroLine>
	 */
	public List<ConstructionMetroLine> findAllMetroLine(){
		return constructionNoticeDao.findAllMetroLine();
	}
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<TConstructionNotice> dataList){
		constructionNoticeDao.saveAll(dataList);
	}
	
	public int findCountByLineNo(String lineNo){
		return constructionNoticeDao.findCountByLineNo(lineNo);
	}
	
}
