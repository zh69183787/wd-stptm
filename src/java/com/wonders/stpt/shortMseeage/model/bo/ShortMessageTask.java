package com.wonders.stpt.shortMseeage.model.bo;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SHORTMESSAGE_TASK")
public class ShortMessageTask {
	private String id;
	private String receiversMsg;
	private String content;
	private Date planSendTime;
	private String sendUserLoginname;
	private String sendUserName;
	private String sendType;
	private String removed;
	private Date operateTime;
	private String taskStatus;
	private Long period;
	private String groupsMsg;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="RECEIVERS_MSG", nullable=true, length=4000)
	public String getReceiversMsg() {
		return receiversMsg;
	}
	public void setReceiversMsg(String receiversMsg) {
		this.receiversMsg = receiversMsg;
	}
	
	@Column(name="CONTENT", nullable=true, length=4000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PLAN_SEND_TIME",nullable=true)
	public Date getPlanSendTime() {
		return planSendTime;
	}
	public void setPlanSendTime(Date planSendTime) {
		this.planSendTime = planSendTime;
	}
	
	@Column(name="SEND_USER_LOGINNAME", nullable=true, length=32)
	public String getSendUserLoginname() {
		return sendUserLoginname;
	}
	public void setSendUserLoginname(String sendUserLoginname) {
		this.sendUserLoginname = sendUserLoginname;
	}
	
	@Column(name="SEND_USER_NAME", nullable=true, length=32)
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	
	@Column(name="SEND_TYPE", nullable=true, length=1)
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	@Column(name="REMOVED", nullable=true, length=1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPERATE_TIME")
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name="TASK_STATUS", nullable=true, length=1)
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@Column(name="PERIOD", nullable=true )
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	
	@Column(name="GROUPS_MSG", nullable=true, length=4000)
	public String getGroupsMsg() {
		return groupsMsg;
	}
	public void setGroupsMsg(String groupsMsg) {
		this.groupsMsg = groupsMsg;
	}
	
	
	
}
