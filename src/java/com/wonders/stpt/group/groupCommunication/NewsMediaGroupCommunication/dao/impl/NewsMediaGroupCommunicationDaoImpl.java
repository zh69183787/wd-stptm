package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.dao.NewsMediaGroupCommunicationDao;
import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.bo.NewsMediaGroupCommunication;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class NewsMediaGroupCommunicationDaoImpl extends
		AbstractHibernateDaoImpl<NewsMediaGroupCommunication> implements NewsMediaGroupCommunicationDao {

	@Override
	public Page findNewsMediaGroupCommunicationByPagge(
			Map<String, Object> filter, int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from NewsMediaGroupCommunication t where t.removed = '0' ";
		String countHql = "select count(*) from NewsMediaGroupCommunication t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("directlyUnitName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else{
					filterPart += "t." + paramName + " = :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));					
				}
				filterCounter++;
			}
		}
		filterPart += " ORDER BY t.operateTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

}
