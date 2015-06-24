package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.dao.AddGroupMemberDirectlyDao;
import com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo.AddGroupMemberDirectly;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class AddGroupMemberDirectlyDaoImpl extends AbstractHibernateDaoImpl<AddGroupMemberDirectly>
		implements AddGroupMemberDirectlyDao {

	@Override
	public Page findAddGroupMemberDirectlyByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AddGroupMemberDirectly t where t.removed = '0' ";
		String countHql = "select count(*) from AddGroupMemberDirectly t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				 if(paramName.equals("type")||
						 paramName.equals("unit")||
						 paramName.equals("activeTitleOrContext")||
						 paramName.equals("time")||
						 paramName.equals("address")||
						 paramName.equals("head")||
						 paramName.equals("participation")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}
				filterCounter++;
			}
		}
		
		filterPart += " ORDER BY t.operateTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

}
