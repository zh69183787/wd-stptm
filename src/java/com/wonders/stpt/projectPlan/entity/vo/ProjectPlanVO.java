package com.wonders.stpt.projectPlan.entity.vo;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class ProjectPlanVO implements ValueObject {
	private String id;
	private String applyCompany;
	private String applyCompanyId;
	private String planProjectName;
	private String property;
	private String estimate;
	private String according;
	private String plan;
	private String target;
	private String remark;
	private String removed;
	private String operateTime;
	private String operateUser;
	private String serial;
	private String year;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplyCompany() {
		return applyCompany;
	}
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}
	public String getPlanProjectName() {
		return planProjectName;
	}
	public void setPlanProjectName(String planProjectName) {
		this.planProjectName = planProjectName;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	public String getAccording() {
		return according;
	}
	public void setAccording(String according) {
		this.according = according;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getApplyCompanyId() {
		return applyCompanyId;
	}
	public void setApplyCompanyId(String applyCompanyId) {
		this.applyCompanyId = applyCompanyId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
}
