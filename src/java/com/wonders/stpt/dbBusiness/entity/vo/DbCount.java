package com.wonders.stpt.dbBusiness.entity.vo;

public class DbCount {
	private String deptName;
	private int countAll;
	private int countDone;
	private int countUnDone;
	private int countOverTime;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public int getCountDone() {
		return countDone;
	}
	public void setCountDone(int countDone) {
		this.countDone = countDone;
	}
	public int getCountUnDone() {
		return countUnDone;
	}
	public void setCountUnDone(int countUnDone) {
		this.countUnDone = countUnDone;
	}
	public int getCountOverTime() {
		return countOverTime;
	}
	public void setCountOverTime(int countOverTime) {
		this.countOverTime = countOverTime;
	}
	
	
}
