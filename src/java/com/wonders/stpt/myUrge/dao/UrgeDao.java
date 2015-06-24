package com.wonders.stpt.myUrge.dao;



import java.util.List;
import java.util.Map;

import com.wonders.stpt.myUrge.entity.bo.UrgeInfo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface UrgeDao extends AbstractHibernateDao<UrgeInfo>{
	int getUrgeCount(String userName,String mainDeptId,String nowDeptId,boolean falg);
	List getUrgeList(String userName);
	Page getUrgePage(String userName,int start,int limit);
	Map urgeLeaders(String processName, String incident);
	Map urgePersons(String processName, String incident);
}
