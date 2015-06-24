package com.wonders.stpt.group.groupMember.AddUpdateGroupMember.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


//团员青年信息登记和更新表：
@Entity
@Table(name="T_GROUP_MEMBER")
public class AddUpdateGroupMember implements Serializable,BusinessObject{

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
	@Id
	@GenericGenerator(name="system-uuid",strategy="uuid.hex")
	@GeneratedValue(generator="system-uuid")
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="REMOVED",nullable=true,length=11)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Column(name="USER_NAME",nullable=true,length=100)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="DEPT_NAME",nullable=true,length=100)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name="UPDATE_DATE",nullable=true,length=100)
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name="UNIT",nullable=true,length=100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="LEAGUE_MEMBER_MALE",nullable=true,length=100)
	public int getGroupMale() {
		return groupMale;
	}
	public void setGroupMale(int groupMale) {
		this.groupMale = groupMale;
	}
	@Column(name="LEAGUE_MEMBER_FEMALE",nullable=true,length=100)
	public int getGroupFemMale() {
		return groupFemMale;
	}
	public void setGroupFemMale(int groupFemMale) {
		this.groupFemMale = groupFemMale;
	}
	@Column(name="UNDER35_MALE",nullable=true,length=100)
	public int getUnder_35YouthMale() {
		return under_35YouthMale;
	}
	public void setUnder_35YouthMale(int under_35YouthMale) {
		this.under_35YouthMale = under_35YouthMale;
	}
	@Column(name="UNDER35_FEMALE",nullable=true,length=100)
	public int getUnder_35YouthFemale() {
		return under_35YouthFemale;
	}
	public void setUnder_35YouthFemale(int under_35YouthFemale) {
		this.under_35YouthFemale = under_35YouthFemale;
	}
	@Column(name="NOT_GROUP_UNDER28",nullable=true,length=100)
	public int getUnder_28YouthNotGroup() {
		return under_28YouthNotGroup;
	}
	public void setUnder_28YouthNotGroup(int under_28YouthNotGroup) {
		this.under_28YouthNotGroup = under_28YouthNotGroup;
	}
	@Column(name="MEMBER28",nullable=true,length=100)
	public int getMember28() {
		return member28;
	}
	public void setMember28(int member28) {
		this.member28 = member28;
	}
	@Column(name="MEMBER35",nullable=true,length=100)
	public int getMember35() {
		return member35;
	}
	public void setMember35(int member35) {
		this.member35 = member35;
	}
	@Column(name="NUMBER_OF_NEW_LEAGUE",nullable=true,length=100)
	public int getNewGroup() {
		return newGroup;
	}
	public void setNewGroup(int newGroup) {
		this.newGroup = newGroup;
	}
	@Column(name="DIRECTING_THE_NUMBER",nullable=true,length=100)
	public int getNewMember() {
		return newMember;
	}
	public void setNewMember(int newMember) {
		this.newMember = newMember;
	}
	@Column(name="OPERATE_TIME",nullable=true,length=100)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
