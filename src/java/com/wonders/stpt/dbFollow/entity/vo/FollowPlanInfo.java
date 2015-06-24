package com.wonders.stpt.dbFollow.entity.vo;

public class FollowPlanInfo {
	private String id;
	private String removed;
	private String planName;
	private String planFinishTime;
	private String planResult;
	private String finishTime;
	private String attach;
	private String changeReason;
	private String ifChanged;
	
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
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanFinishTime() {
		return planFinishTime;
	}
	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	public String getPlanResult() {
		return planResult;
	}
	public void setPlanResult(String planResult) {
		this.planResult = planResult;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public String getIfChanged() {
		return ifChanged;
	}
	public void setIfChanged(String ifChanged) {
		this.ifChanged = ifChanged;
	}
	
}
