package com.wonders.stpt.dbFollow.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_DB_FOLLOW_CHILD")
public class DbFollowChild {
	private String id;
	private String parentId;
	private String followDeptId;
	private String followDeptName;
	private String require;
	private String planSubmitTime;
	private String followState;
	private String attach;
	private Date createTime;
	private String createPerson;
	private Date modifyTime;
	private String modifyPerson;
	private String removed;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PARENT_ID", nullable = true, length = 40)
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name = "FOLLOW_DEPT_ID", nullable = true, length = 40)
	public String getFollowDeptId() {
		return followDeptId;
	}
	public void setFollowDeptId(String followDeptId) {
		this.followDeptId = followDeptId;
	}
	
	@Column(name = "FOLLOW_DEPT_NAME", nullable = true, length = 100)
	public String getFollowDeptName() {
		return followDeptName;
	}
	public void setFollowDeptName(String followDeptName) {
		this.followDeptName = followDeptName;
	}
	
	@Column(name = "REQUIRE", nullable = true, length = 200)
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	
	@Column(name = "PLAN_SUBMIT_TIME", nullable = true, length = 40)
	public String getPlanSubmitTime() {
		return planSubmitTime;
	}
	public void setPlanSubmitTime(String planSubmitTime) {
		this.planSubmitTime = planSubmitTime;
	}
	
	@Column(name = "FOLLOW_STATE", nullable = true, length = 1)
	public String getFollowState() {
		return followState;
	}
	public void setFollowState(String followState) {
		this.followState = followState;
	}
	
	@Column(name = "ATTACH", nullable = true, length = 100)
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "CREATE_PERSON", nullable = true, length = 50)
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	
	@Column(name = "MODIFY_TIME", nullable = true, length = 7)
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "MODIFY_PERSON", nullable = true, length = 50)
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	
	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
}
