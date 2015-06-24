package com.wonders.stpt.myUrge.entity.bo;



public class UrgeInfo {
	//从tasks获得
	private String processname;
	private String taskid;
	private int incident;
	private String taskuser;
	private String steplabel;
	private String overduetime;
	
	//从subprocess获得
	private String pname;
	private int pincident;
	private String ptaskid;
	//从incidents获得
	private String summary;
	private String initiator;
	//查得
	private String initdepartment;
	//分类
	private String formType;
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public int getIncident() {
		return incident;
	}
	public void setIncident(int incident) {
		this.incident = incident;
	}
	public String getTaskuser() {
		return taskuser;
	}
	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}
	public String getSteplabel() {
		return steplabel;
	}
	public void setSteplabel(String steplabel) {
		this.steplabel = steplabel;
	}
	public String getOverduetime() {
		return overduetime;
	}
	public void setOverduetime(String overduetime) {
		this.overduetime = overduetime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getInitdepartment() {
		return initdepartment;
	}
	public void setInitdepartment(String initdepartment) {
		this.initdepartment = initdepartment;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPincident() {
		return pincident;
	}
	public void setPincident(int pincident) {
		this.pincident = pincident;
	}
	public String getPtaskid() {
		return ptaskid;
	}
	public void setPtaskid(String ptaskid) {
		this.ptaskid = ptaskid;
	}
}
