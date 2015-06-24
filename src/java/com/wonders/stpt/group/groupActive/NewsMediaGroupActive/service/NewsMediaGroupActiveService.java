package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.service;

import java.util.Map;

import com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.bo.NewsMediaGroupActive;
import com.wondersgroup.framework.core.bo.Page;

public interface NewsMediaGroupActiveService {

	public void addNewsMediaGroupActive(NewsMediaGroupActive newsMediaGroupActive);
	
	public void deleteNewsMediaGroupActive(NewsMediaGroupActive newsMediaGroupActive);
	
	public NewsMediaGroupActive findNewsMediaGroupActive(String id);
	
	public void updateNewsMediaGroupActive(NewsMediaGroupActive newsMediaGroupActive);
	
	public Page findNewsMediaGroupActiveByPage(int pageNo,int pageSize);
	
	public Page findNewsMediaGroupActiveByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);
}
