package com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.service;


import java.util.Map;


import com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.bo.AddGroupNewMedia;
import com.wondersgroup.framework.core.bo.Page;

public interface AddGroupNewMediaService {

	public void addAddGroupNewMedia(AddGroupNewMedia addGroupNewMedia);

	public void deleteAddGroupNewMedia(AddGroupNewMedia addGroupNewMedia);
	
	public AddGroupNewMedia findAddGroupNewMedia(String id);
	
	public void updateAddGroupNewMedia(AddGroupNewMedia AddGroupNewMedia);
	
	public Page findAddGroupNewMediaByPage(int pageNo,int pageSize);
	
	public Page findAddGroupNewMediaByPage(Map<String,Object> filter,int pageNo,int pageSize,String type);

}
