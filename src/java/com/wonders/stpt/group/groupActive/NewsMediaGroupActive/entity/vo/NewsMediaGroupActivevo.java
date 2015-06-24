package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class NewsMediaGroupActivevo implements ValueObject {
	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String activeName;//活动（话题）名称
	private String startTime;//开展时间
	private String activeIntroduction;//活动简介（100字以内）
	private String influence_covers_the_youth; //影响覆盖青年情况
	private String operateTime;//操作时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getActiveIntroduction() {
		return activeIntroduction;
	}
	public void setActiveIntroduction(String activeIntroduction) {
		this.activeIntroduction = activeIntroduction;
	}
	public String getInfluence_covers_the_youth() {
		return influence_covers_the_youth;
	}
	public void setInfluence_covers_the_youth(String influence_covers_the_youth) {
		this.influence_covers_the_youth = influence_covers_the_youth;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
