package com.wonders.stpt.rectification.rectificationWork.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.rectification.rectificationWork.entity.bo.RectificationWork;

import com.wonders.stpt.rectification.rectificationWork.dao.RectificationWorkDao;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class RectificationWorkDaoImpl extends AbstractHibernateDaoImpl<RectificationWork>
		implements RectificationWorkDao {

	@Override
	public Page findRectificationWorkByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from RectificationWork t where t.removed = '0' ";
		String countHql = "select count(*) from RectificationWork t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("lines")){
					filterPart += "(t." + paramName + " like :lines_a or t." + paramName + " like :lines_b or t.lines like '%全路网%' )";
					args.add(new HqlParameter("lines_a", filter.get(paramName)+"%"));
					args.add(new HqlParameter("lines_b", "%,"+filter.get(paramName)+"%"));
				}else if(paramName.equals("dangerNumber")||paramName.equals("childSYS")
						||paramName.equals("riskPoint")||paramName.equals("levels")
						||paramName.equals("expertAdvice")||paramName.equals("phenomenon")
						||paramName.equals("rectificationMethod")||paramName.equals("deptWork")
						||paramName.equals("leaderShip")||paramName.equals("targetNode")
						||paramName.equals("cwip")||paramName.equals("workState")
						||paramName.equals("leadDept")||paramName.equals("remark")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else {
					filterPart +=" 1=1 " ;
				}
				filterCounter++;
			}
		}
		if("1".equals(type)){
			filterPart+=" and (t.gonghao = '"+filter.get("gonghao")+"' and t.dept_id='"+filter.get("dept_id")+"' and t.deptName='"+filter.get("deptName")+"' and t.userName='"+filter.get("userName")+"')";
		}else if("2".equals(type)){
			filterPart+=" and t.dept_id='"+filter.get("dept_id")+"' and t.deptName='"+filter.get("deptName")+"'";
		}
		System.out.println(type);
		System.out.println("**"+hql + filterPart+"**");
		filterPart += " ORDER BY t.operationTime DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

}
