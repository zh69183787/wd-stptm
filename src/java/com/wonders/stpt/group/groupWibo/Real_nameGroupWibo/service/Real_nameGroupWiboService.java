package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.service;

import java.util.Map;

import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.bo.Real_nameGroupWibo;
import com.wondersgroup.framework.core.bo.Page;

public interface Real_nameGroupWiboService {

	public void addReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo);

	public void deleteReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo);
	
	public Real_nameGroupWibo findReal_nameGroupWibo(String id);
	
	public void updateReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo);
	
	public Page findReal_nameGroupWiboByPage(int pageNo,int pageSize);
	
	public Page findReal_nameGroupWiboByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);

}
