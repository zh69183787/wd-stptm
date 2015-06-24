package com.wonders.stpt.group.groupActive.NewsMediaGroupActive.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


//本级和下级团组织新媒体活动展开情况(包括微话题、微访谈、微直播及其他新媒体活动)：
@Entity
@Table(name="T_GROUP_ACTIVE")
public class NewsMediaGroupActive implements Serializable , BusinessObject {

	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String activeName;//活动（话题）名称
	private String startTime;//开展时间
	private String activeIntroduction;//活动简介（100字以内）
	private String influence_covers_the_youth; //影响覆盖青年情况
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
	@Column(name="ACTIVENAME",nullable=true,length=100)
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	@Column(name="STARTTIME",nullable=true,length=100)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name="ACTIVEINTRODUCTION",nullable=true,length=1000)
	public String getActiveIntroduction() {
		return activeIntroduction;
	}
	public void setActiveIntroduction(String activeIntroduction) {
		this.activeIntroduction = activeIntroduction;
	}
	@Column(name="INFLUENCE_COVERS_THE_YOUTH",nullable=true,length=100)
	public String getInfluence_covers_the_youth() {
		return influence_covers_the_youth;
	}
	public void setInfluence_covers_the_youth(String influence_covers_the_youth) {
		this.influence_covers_the_youth = influence_covers_the_youth;
	}
	@Column(name="OPERATETIME",nullable=true,length=100)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
