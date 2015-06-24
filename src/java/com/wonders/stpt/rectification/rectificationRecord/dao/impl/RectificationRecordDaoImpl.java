package com.wonders.stpt.rectification.rectificationRecord.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.rectification.rectificationRecord.dao.RectificationRecordDao;
import com.wonders.stpt.rectification.rectificationRecord.entity.bo.RectificationRecord;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
/**
 * 整改记录DAOIMPL
 * @author Administrator
 *
 */
public class RectificationRecordDaoImpl extends AbstractHibernateDaoImpl<RectificationRecord>
		implements RectificationRecordDao {

	@Override
	public Page findRectificationRecordByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from RectificationRecord t where t.removed = '0' ";
		String countHql = "select count(*) from RectificationRecord t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("cwip")||paramName.equals("targetNode")
						||paramName.equals("rectificationMethod")||paramName.equals("wid")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}
				filterCounter++;
			}
		}
		filterPart += " ORDER BY t.operatetime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

}
