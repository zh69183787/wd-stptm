package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.service;

import java.util.Map;

import com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.bo.NewsMediaGroupCommunication;
import com.wondersgroup.framework.core.bo.Page;

public interface NewsMediaGroupCommunicationService {

	public void addNewsMediaGroupCommunication(NewsMediaGroupCommunication newsMediaGroupCommunication);

	public void deleteNewsMediaGroupCommunication(NewsMediaGroupCommunication newsMediaGroupCommunication);
	
	public NewsMediaGroupCommunication findNewsMediaGroupCommunication(String id);
	
	public void updateNewsMediaGroupCommunication(NewsMediaGroupCommunication newsMediaGroupCommunication);
	
	public Page findNewsMediaGroupCommunicationByPage(int pageNo,int pageSize);
	
	public Page findNewsMediaGroupCommunicationByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
