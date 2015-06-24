package com.wonders.stpt.group.groupMemberDirectly.AddGroupMemberDirectly.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * 
 * @author Administrator
 *
 */

//各直属单位团组织团工作信息登记表：
@Entity
@Table(name="T_GROUP_MEMBERDIRECTLY")
public class AddGroupMemberDirectly implements Serializable,BusinessObject {
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
	@Column(name="REMOVED",nullable=true,length=1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Column(name="USERNAME",nullable=true,length=100)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="DEPTNAME",nullable=true,length=100)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name="TYPE",nullable=true,length=100)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="UNIT",nullable=true,length=100)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="ACTIVETITLEORCONTEXT",nullable=true,length=100)
	public String getActiveTitleOrContext() {
		return activeTitleOrContext;
	}
	public void setActiveTitleOrContext(String activeTitleOrContext) {
		this.activeTitleOrContext = activeTitleOrContext;
	}@Column(name="TIME",nullable=true,length=100)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column(name="ADDRESS",nullable=true,length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="HEAD",nullable=true,length=100)
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	@Column(name="PARTICIPATION",nullable=true,length=100)
	public int getParticipation() {
		return participation;
	}
	public void setParticipation(int participation) {
		this.participation = participation;
	}
	@Column(name="OPERATETIME",nullable=true,length=100)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
}
