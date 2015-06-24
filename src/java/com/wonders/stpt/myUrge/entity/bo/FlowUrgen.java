package com.wonders.stpt.myUrge.entity.bo;



import java.text.MessageFormat;

public class FlowUrgen {
	private String gid;//数据唯一标识，FN_GEN_GUID()
	private String processname;//流程名称
	private String incident;//流程实例号
	private String taskid;//被催办流程节点的TASKID
	private long urgened_user;//被催办人的内部编号
	private String urgened_username;//被催办人的姓名
	private String urgen_time;//催办的时间
	private long send_user;//发送人的内部编号
	private String send_username;//发送人的姓名
	private String urgened_mb;//接收催办短信的手机号
	private String send_content;//短信内容
	
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getUrgened_username() {
		return urgened_username;
	}
	public void setUrgened_username(String urgened_username) {
		this.urgened_username = urgened_username;
	}
	public String getUrgen_time() {
		return urgen_time;
	}
	public void setUrgen_time(String urgen_time) {
		this.urgen_time = urgen_time;
	}
	
	public void setUrgen_timeFormat(String urgen_time) {
		String dateTime = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}",new Object[]{new java.sql.Date(System.currentTimeMillis())});
		this.urgen_time = dateTime;
	}

	public String getSend_username() {
		return send_username;
	}
	public void setSend_username(String send_username) {
		this.send_username = send_username;
	}
	public String getUrgened_mb() {
		return urgened_mb;
	}
	public void setUrgened_mb(String urgened_mb) {
		this.urgened_mb = urgened_mb;
	}
	public String getSend_content() {
		return send_content;
	}
	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}
	public long getUrgened_user() {
		return urgened_user;
	}
	public void setUrgened_user(long urgened_user) {
		this.urgened_user = urgened_user;
	}
	public long getSend_user() {
		return send_user;
	}
	public void setSend_user(long send_user) {
		this.send_user = send_user;
	}

}
