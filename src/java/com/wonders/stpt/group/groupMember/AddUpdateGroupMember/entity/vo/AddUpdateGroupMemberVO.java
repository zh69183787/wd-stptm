package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class AddUpdateGroupMemberVO implements ValueObject {

	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String updateDate;//更新日期
	private String unit;//单位
	private int groupMale;//共青团员男
	private int groupFemMale;//共青团员女
	private int under_35YouthMale;//35岁以下青年（含35岁）男
	private int under_35YouthFemale;//35岁以下青年（含35岁）女
	private int under_28YouthNotGroup;//28岁以下未入团青年（含28岁）
	private int member28;//28岁党员（含28岁）
	private int member35;//35岁党员（含35岁）
	private int newGroup;//今年新入团人数
	private int newMember;//今年推优入党人数
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
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getGroupMale() {
		return groupMale;
	}
	public void setGroupMale(int groupMale) {
		this.groupMale = groupMale;
	}
	public int getGroupFemMale() {
		return groupFemMale;
	}
	public void setGroupFemMale(int groupFemMale) {
		this.groupFemMale = groupFemMale;
	}
	public int getUnder_35YouthMale() {
		return under_35YouthMale;
	}
	public void setUnder_35YouthMale(int under_35YouthMale) {
		this.under_35YouthMale = under_35YouthMale;
	}
	public int getUnder_35YouthFemale() {
		return under_35YouthFemale;
	}
	public void setUnder_35YouthFemale(int under_35YouthFemale) {
		this.under_35YouthFemale = under_35YouthFemale;
	}
	public int getUnder_28YouthNotGroup() {
		return under_28YouthNotGroup;
	}
	public void setUnder_28YouthNotGroup(int under_28YouthNotGroup) {
		this.under_28YouthNotGroup = under_28YouthNotGroup;
	}
	public int getMember28() {
		return member28;
	}
	public void setMember28(int member28) {
		this.member28 = member28;
	}
	public int getMember35() {
		return member35;
	}
	public void setMember35(int member35) {
		this.member35 = member35;
	}
	public int getNewGroup() {
		return newGroup;
	}
	public void setNewGroup(int newGroup) {
		this.newGroup = newGroup;
	}
	public int getNewMember() {
		return newMember;
	}
	public void setNewMember(int newMember) {
		this.newMember = newMember;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
