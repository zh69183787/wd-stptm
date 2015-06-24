package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.dao.AddUpdateGroupMemberDao;
import com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo.AddUpdateGroupMember;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;



public class AddUpdateGroupMemberDaoImpl extends 
		AbstractHibernateDaoImpl<AddUpdateGroupMember>
		implements AddUpdateGroupMemberDao {
	@Override
	public Page findAddUpdateGroupMemberByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from AddUpdateGroupMember t where t.removed = '0' ";
		String countHql = "select count(*) from AddUpdateGroupMember t where t.removed = '0' ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("updateDate")||paramName.equals("unit")||paramName.equals("groupMale")||
				   paramName.equals("groupFemMale")||paramName.equals("under_35YouthMale")||paramName.equals("under_35YouthFemale")||paramName.equals("under_28YouthNotGroup")||
				   paramName.equals("member28")||paramName.equals("member35")||paramName.equals("newGroup")||paramName.equals("newMember")||paramName.equals("operateTime")
						){
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

	public List<Object[]> countAll(){
		String sql = "select sum(t.league_member_male),sum(t.league_member_female),sum(t.under35_male),sum(t.under35_female),"+
				" sum(t.not_group_under28),sum(t.member28),sum(t.member35),sum(t.number_of_new_league),sum(t.directing_the_number) "+
				" from t_group_member t";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}
}
