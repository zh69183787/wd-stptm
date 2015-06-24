package com.wonders.stpt.dbFollow.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_DB_FOLLOW_PLAN")
public class DbFollowPlan {
	private String id;
	private String followChildId;
	private String planName;
	private String planFinishTime;
	private String planResult;
	private String finishTime;
	private String attach;
	private String planState;
	private Date createTime;
	private String createPerson;
	private Date modifyTime;
	private String modifyPerson;
	private String removed;
	private String planChangeHistory;
	
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
	
	@Column(name = "FOLLOW_CHILD_ID", nullable = true, length = 40)
	public String getFollowChildId() {
		return followChildId;
	}
	public void setFollowChildId(String followChildId) {
		this.followChildId = followChildId;
	}
	
	@Column(name = "PLAN_NAME", nullable = true, length = 200)
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	@Column(name = "PLAN_FINISH_TIME", nullable = true, length = 40)
	public String getPlanFinishTime() {
		return planFinishTime;
	}
	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	
	@Column(name = "PLAN_RESULT", nullable = true, length = 200)
	public String getPlanResult() {
		return planResult;
	}
	public void setPlanResult(String planResult) {
		this.planResult = planResult;
	}
	
	@Column(name = "FINISH_TIME", nullable = true, length = 40)
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	
	@Column(name = "ATTACH", nullable = true, length = 100)
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	@Column(name = "PLAN_STATE", nullable = true, length = 1)
	public String getPlanState() {
		return planState;
	}
	public void setPlanState(String planState) {
		this.planState = planState;
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
	
	@Column(name = "PLAN_CHANGE_HISTORY", nullable = true, length = 4000)
	public String getPlanChangeHistory() {
		return planChangeHistory;
	}
	public void setPlanChangeHistory(String planChangeHistory) {
		this.planChangeHistory = planChangeHistory;
	}
	
}
