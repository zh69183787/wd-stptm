package com.wonders.stpt.myUrge.service.impl;



import java.util.List;
import java.util.Map;



import com.wonders.stpt.myUrge.dao.UrgeDao;
import com.wonders.stpt.myUrge.service.UrgeService;
import com.wondersgroup.framework.core.bo.Page;
public class UrgeServiceImpl implements UrgeService{
	private UrgeDao urgeDao;
	public UrgeDao getUrgeDao() {
		return urgeDao;
	}
	public void setUrgeDao(UrgeDao urgeDao) {
		this.urgeDao = urgeDao;
	}
	public int getUrgeCount(String userName,String mainDeptId,String nowDeptId,boolean falg){
		return urgeDao.getUrgeCount(userName,mainDeptId,nowDeptId,falg);
	}
	public List getUrgeList(String userName){
		return urgeDao.getUrgeList(userName);
	}
	public Page getUrgePage(String userName,int start,int limit){
		return urgeDao.getUrgePage(userName, start, limit); 
	}
	public Map urgeLeaders(String processName, String incident) {
		// TODO Auto-generated method stub
		return urgeDao.urgeLeaders(processName, incident);
	}
	public Map urgePersons(String processName, String incident) {
		// TODO Auto-generated method stub
		return urgeDao.urgePersons(processName, incident);
	}
}
