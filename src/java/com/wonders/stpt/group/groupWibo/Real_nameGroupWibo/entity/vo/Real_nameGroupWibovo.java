package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

//本级和下级团组织实名认证微博开设情况（包括官方微博、项目名义微博、以及团干部、服务明星、志愿者个人微博）

public class Real_nameGroupWibovo implements ValueObject{

	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String wiboName;//微博名称（所属直属单位团组织）
	private String certification;//是否已认证
	private String wiboAddress;//微博地址
	private String linkName;//联系人
	private String mobilPhone;//手机
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
	public String getWiboName() {
		return wiboName;
	}
	public void setWiboName(String wiboName) {
		this.wiboName = wiboName;
	}
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getWiboAddress() {
		return wiboAddress;
	}
	public void setWiboAddress(String wiboAddress) {
		this.wiboAddress = wiboAddress;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getMobilPhone() {
		return mobilPhone;
	}
	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
