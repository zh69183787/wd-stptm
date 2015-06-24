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
@Table(name="T_SHORTMESSAGE")
public class ShortMessage {
	private String id;
	private String receiverLoginname;
	private String receiverName;
	private String content;
	private Date sendTime;
	private String sendUserLoginname;
	private String sendUserName;
	private String returnValue;
	private String mobile;
	private Date planSendTime;
	private String taskId;
	
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
	
	@Column(name="RECEIVER_LOGINNAME", nullable=true, length=32)
	public String getReceiverLoginname() {
		return receiverLoginname;
	}
	public void setReceiverLoginname(String receiverLoginname) {
		this.receiverLoginname = receiverLoginname;
	}
	
	@Column(name="RECEIVER_NAME", nullable=true, length=32)
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Column(name="CONTENT", nullable=true, length=4000)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SEND_TIME")
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
	
	@Column(name="RETURN_VALUE", nullable=true, length=100)
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	
	@Column(name="MOBILE", nullable=true, length=50)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PLAN_SEND_TIME")
	public Date getPlanSendTime() {
		return planSendTime;
	}
	public void setPlanSendTime(Date planSendTime) {
		this.planSendTime = planSendTime;
	}
	
	@Column(name="TASK_ID", nullable=true, length=32)
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
}
