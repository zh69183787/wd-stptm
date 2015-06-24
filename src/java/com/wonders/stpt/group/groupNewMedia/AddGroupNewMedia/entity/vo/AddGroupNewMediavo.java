package com.wonders.stpt.group.groupNewMedia.AddGroupNewMedia.entity.vo;

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


//团组织新媒体建设信息登记（汇总情况以公司为单位）表：

public class AddGroupNewMediavo implements ValueObject{
	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String directlyUnitName;//名称（所属直属单位团组织）
	private String startTime;//开通（开展）时间
	private String webIntroduction;//网站（页）简介
	private String honor;//被评过的市级荣誉
	private String independentDomain;//是否独立域名
	private String averageDailyTraffic;//日均访问量
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
	public String getDirectlyUnitName() {
		return directlyUnitName;
	}
	public void setDirectlyUnitName(String directlyUnitName) {
		this.directlyUnitName = directlyUnitName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getWebIntroduction() {
		return webIntroduction;
	}
	public void setWebIntroduction(String webIntroduction) {
		this.webIntroduction = webIntroduction;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public String getIndependentDomain() {
		return independentDomain;
	}
	public void setIndependentDomain(String independentDomain) {
		this.independentDomain = independentDomain;
	}
	public String getAverageDailyTraffic() {
		return averageDailyTraffic;
	}
	public void setAverageDailyTraffic(String averageDailyTraffic) {
		this.averageDailyTraffic = averageDailyTraffic;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
