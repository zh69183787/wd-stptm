package com.wonders.stpt.rectification.rectificationRecord.dao;

import java.util.Map;

import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;
/**
 * 整改记录DAO
 * @author Administrator
 *
 */
public interface RectificationRecordDao extends
		AbstractHibernateDao<RectificationRecord> {

	public Page findRectificationRecordByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
	
}
