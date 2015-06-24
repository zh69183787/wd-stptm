package com.wonders.stpt.myUrge.service;



import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
public interface UrgeService {
	int getUrgeCount(String userName,String mainDeptId,String nowDeptId,boolean falg);
	List getUrgeList(String userName);
	Page getUrgePage(String userName,int start,int limit);
	Map urgeLeaders(String processName, String incident);
	Map urgePersons(String processName, String incident);
}
