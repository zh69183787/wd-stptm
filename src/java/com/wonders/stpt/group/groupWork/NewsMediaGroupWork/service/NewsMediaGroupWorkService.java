package com.wonders.stpt.group.groupWork.NewsMediaGroupWork.service;

import java.util.Map;


import com.wonders.stpt.group.groupWork.NewsMediaGroupWork.entity.bo.NewsMediaGroupWork;
import com.wondersgroup.framework.core.bo.Page;

public interface NewsMediaGroupWorkService {

	public void addNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork);

	public void deleteNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork);
	
	public NewsMediaGroupWork findNewsMediaGroupWork(String id);
	
	public void updateNewsMediaGroupWork(NewsMediaGroupWork newsMediaGroupWork);
	
	public Page findNewsMediaGroupWorkByPage(int pageNo,int pageSize);
	
	public Page findNewsMediaGroupWorkByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);

}
