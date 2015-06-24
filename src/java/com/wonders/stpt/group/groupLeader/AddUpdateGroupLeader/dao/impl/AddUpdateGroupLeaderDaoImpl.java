package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.dao.AddUpdateGroupLeaderDao;
import com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo.AddUpdateGroupLeader;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class AddUpdateGroupLeaderDaoImpl extends 
AbstractHibernateDaoImpl<AddUpdateGroupLeader>
		implements AddUpdateGroupLeaderDao {

	@Override
	public Page findAddUpdateGroupLeaderByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AddUpdateGroupLeader t where t.removed = '0' ";
		String countHql = "select count(*) from AddUpdateGroupLeader t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		
		if (!filter.isEmpty()) {
			
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("unitdirectly")||
					paramName.equals("branchName")||
					paramName.equals("branchSecretary")||
					paramName.equals("job")||
					paramName.equals("gender")||
					paramName.equals("birthday")||
					paramName.equals("politicsStatus")||
					paramName.equals("levelEducation")||
					paramName.equals("degree")||
					paramName.equals("postOffice")||
					paramName.equals("titleorTechnicalLevel")||
					paramName.equals("mobilPhone")||
					paramName.equals("wiboorMicro")||
					paramName.equals("fullorParttime")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("branchNumber")){
					filterPart += "t." + paramName + " = " + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					
				}
				filterCounter++;
			}
		}
		System.out.println(hql + filterPart);
		filterPart += " ORDER BY t.operateTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

}
