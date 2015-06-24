package com.wonders.stpt.rectification.rectificationRecord.service;

import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;
import java.util.Map;

import com.wonders.stpt.rectification.rectificationWork.entity.bo.RectificationWork;
import com.wondersgroup.framework.core.bo.Page;

public interface RectificationRecordService {

	/**
	 * 删除实体对象
	 * 
	 * @param RectificationWork
	 */
	public void deleteRectificationRecord(
			RectificationRecord rectificationRecord);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public RectificationRecord findRectificationRecordById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param RectificationWork
	 */
	public void addRectificationRecord(
			RectificationRecord rectificationRecord);

	/**
	 * 更新数据到数据库
	 * 
	 * @param RectificationWork
	 *            实体
	 */
	public void updateRectificationRecord(
			RectificationRecord rectificationRecord);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findRectificationRecordByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @param webTypeId
	 *            网页操作类型.
	 * @return
	 */
	public Page findRectificationRecordByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String webTypeId);
}
