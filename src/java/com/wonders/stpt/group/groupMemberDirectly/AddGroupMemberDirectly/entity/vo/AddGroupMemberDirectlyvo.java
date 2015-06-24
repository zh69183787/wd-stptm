package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class AddGroupMemberDirectlyvo implements ValueObject {

	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String type;//类型可多选
	private String unit;//单位
	private String activeTitleOrContext;//活动主题或内容
	private String time;//时间（**年**月**日）
	private String address;//地点
	private String head;//负责人
	private int participation;//参与人数e 
	private String operateTime;//操作时间
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getActiveTitleOrContext() {
		return activeTitleOrContext;
	}
	public void setActiveTitleOrContext(String activeTitleOrContext) {
		this.activeTitleOrContext = activeTitleOrContext;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getParticipation() {
		return participation;
	}
	public void setParticipation(int participation) {
		this.participation = participation;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
