package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.dao.Real_nameGroupWiboDao;
import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.bo.Real_nameGroupWibo;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class Real_nameGroupWiboDaoImpl extends AbstractHibernateDaoImpl<Real_nameGroupWibo>
		implements Real_nameGroupWiboDao {

	@Override
	public Page findReal_nameGroupWiboByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Real_nameGroupWibo t where t.removed = '0' ";
		String countHql = "select count(*) from Real_nameGroupWibo t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("wiboName")){
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
