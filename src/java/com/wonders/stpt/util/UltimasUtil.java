package com.wonders.stpt.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wonders.stpt.myNotice.dao.impl.TMsgUsermessageDaoImpl;

public class UltimasUtil {
	private static String domainName = "ST";

	public static List getPersonByDeptId(String deptId) {
		List<String> list = new ArrayList<String>();
		HttpServletRequest req = null;
		try{
			req = PageUtils.GetRequest();
			String sql=" select a.ID,a.LOGIN_NAME,a.NAME from cs_user a ";
			sql+=" inner join ( ";
			sql+=" select distinct c.security_user_id as SECURITY_USER_ID, c.ORGAN_NODE_ID, ";
			sql+="   c.orders from cs_user_organnode c,cs_organ_node n where c.organ_node_id=n.id  ";
			sql+="   and c.organ_node_id in (select cs1.id from cs_organ_node cs, cs_organ_model cs1 where  parent_node_id='"+deptId+"' and  cs.id = cs1.id) ";
			sql+="  and c.security_user_id  not in (select security_user_id from cs_user_group a ";
			sql+="            inner join cs_group b on a.security_group_id = b.id";
			sql+="            where b.code='bmlingdaosingle') ";
			sql+=" ) b on a.id=b.SECURITY_USER_ID";
			sql+="	order by b.organ_node_id, b.orders ";
			
			TMsgUsermessageDaoImpl dao = (TMsgUsermessageDaoImpl) WebApplicationContextUtils
			.getWebApplicationContext(req.getSession().getServletContext()).getBean("msgUsermessageDaoDaoImpl");
			SQLQuery query = dao.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql);
			List lst = query.setFirstResult(0).setMaxResults(1).list();
			if (lst!=null ){
				for(int i =0;i<lst.size();i++){
					Object[] arr = (Object[]) lst.get(i);
					String str=domainName+arr[1]+":"+arr[2]+":"+arr[0];
					list.add(str);
				}
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
		
	}

}
