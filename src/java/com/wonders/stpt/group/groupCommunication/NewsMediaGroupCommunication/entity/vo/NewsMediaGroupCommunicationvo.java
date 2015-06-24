package com.wonders.stpt.group.groupCommunication.NewsMediaGroupCommunication.entity.vo;

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


//本级和下级团组织利用新媒体工具拓宽沟通和响应渠道情况

public class NewsMediaGroupCommunicationvo implements ValueObject{
	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String technicalMeans;//技术手段
	private String directlyUnitName;//名称（所属直属单位团组织）
	private String coverPepole;//累计覆盖人次
	private String honor;//被评过的市级荣誉
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
	public String getTechnicalMeans() {
		return technicalMeans;
	}
	public void setTechnicalMeans(String technicalMeans) {
		this.technicalMeans = technicalMeans;
	}
	public String getDirectlyUnitName() {
		return directlyUnitName;
	}
	public void setDirectlyUnitName(String directlyUnitName) {
		this.directlyUnitName = directlyUnitName;
	}
	public String getCoverPepole() {
		return coverPepole;
	}
	public void setCoverPepole(String coverPepole) {
		this.coverPepole = coverPepole;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
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
