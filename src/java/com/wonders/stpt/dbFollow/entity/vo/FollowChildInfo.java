package com.wonders.stpt.dbFollow.entity.vo;

public class FollowChildInfo {
	private String id;
	private String followDeptId;
	private String followDeptName;
	private String require;
	private String planSubmitTime;
	private String removed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFollowDeptId() {
		return followDeptId;
	}
	public void setFollowDeptId(String followDeptId) {
		this.followDeptId = followDeptId;
	}
	public String getFollowDeptName() {
		return followDeptName;
	}
	public void setFollowDeptName(String followDeptName) {
		this.followDeptName = followDeptName;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public String getPlanSubmitTime() {
		return planSubmitTime;
	}
	public void setPlanSubmitTime(String planSubmitTime) {
		this.planSubmitTime = planSubmitTime;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
}
