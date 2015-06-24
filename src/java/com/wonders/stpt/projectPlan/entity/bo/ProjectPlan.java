package com.wonders.stpt.projectPlan.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

@Entity
@Table(name = "PCL_PROJECT_PLAN")
public class ProjectPlan implements Serializable, BusinessObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "APPLY_COMPANY",  length = 100)
	public String getApplyCompany() {
		return applyCompany;
	}
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}
	
	@Column(name = "PLAN_PROJECT_NAME",  length = 100)
	public String getPlanProjectName() {
		return planProjectName;
	}
	public void setPlanProjectName(String planProjectName) {
		this.planProjectName = planProjectName;
	}
	
	@Column(name = "PROPERTY",  length = 100)
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	@Column(name = "ESTIMATE",  length = 100)
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	
	@Column(name = "ACCORDING",  length = 1000)
	public String getAccording() {
		return according;
	}
	public void setAccording(String according) {
		this.according = according;
	}
	
	@Column(name = "PLAN",  length = 4000)
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	@Column(name = "TARGET",  length = 4000)
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	@Column(name = "REMARK",  length = 4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "REMOVED",  length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Column(name = "OPERATE_TIME",  length = 4000)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "OPERATE_USER",  length = 4000)
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	
	@Column(name = "SERIAL",  length = 100)
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	@Column(name = "APPLY_COMPANY_ID",  length = 100)
	public String getApplyCompanyId() {
		return applyCompanyId;
	}
	public void setApplyCompanyId(String applyCompanyId) {
		this.applyCompanyId = applyCompanyId;
	}
	
	@Column(name = "YEAR",  length = 10)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	
}
