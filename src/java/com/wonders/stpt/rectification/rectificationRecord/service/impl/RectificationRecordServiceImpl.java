package com.wonders.stpt.rectification.rectificationRecord.service.impl;

import java.util.Map;

import com.wonders.stpt.rectification.rectificationRecord.dao.RectificationRecordDao;
import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;
import com.wonders.stpt.rectification.rectificationRecord.service.RectificationRecordService;
import com.wondersgroup.framework.core.bo.Page;

public class RectificationRecordServiceImpl implements
		RectificationRecordService {

	private RectificationRecordDao rrdao;
	
	public void setRrdao(RectificationRecordDao rrdao) {
		this.rrdao = rrdao;
	}

	@Override
	public void deleteRectificationRecord(
			RectificationRecord rectificationRecord) {
		// TODO Auto-generated method stub
		rrdao.delete(rectificationRecord);
	}

	@Override
	public RectificationRecord findRectificationRecordById(String id) {
		// TODO Auto-generated method stub
		
		return rrdao.load(id);
	}

	@Override
	public void addRectificationRecord(RectificationRecord rectificationRecord) {
		// TODO Auto-generated method stub
		rrdao.save(rectificationRecord);
	}

	@Override
	public void updateRectificationRecord(
			RectificationRecord rectificationRecord) {
		// TODO Auto-generated method stub
		rrdao.update(rectificationRecord);
	}

	@Override
	public Page findRectificationRecordByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return rrdao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findRectificationRecordByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String webTypeId) {
		// TODO Auto-generated method stub
		return rrdao.findRectificationRecordByPage(filter, pageNo, pageSize, webTypeId);
	}
}
