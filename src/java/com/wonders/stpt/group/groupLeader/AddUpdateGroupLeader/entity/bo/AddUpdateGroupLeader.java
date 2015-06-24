package com.wonders.stpt.group.groupLeader.AddUpdateGroupLeader.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;


//团干部信息登记和更新：
@Entity
@Table(name="T_GROUP_LEADER")
public class AddUpdateGroupLeader implements Serializable,BusinessObject {

	private String id;	//主键
	private String removed;// 标记是否删除
	private String  userName;//用户名
	private String deptName;//部门名
	private String unitdirectly;//所属直属单位团组织
	private String branchName;//支部名称
	private String branchSecretary;//支部书记
	private int branchNumber;//支部人数（后台汇总时以每家公司为单位，人数由多至少排列）
	private String job;//担任团内职务
	private String gender;//性别
	private String birthday;//出生年月
	private String politicsStatus;//政治面貌
	private String levelEducation;//文化程度
	private String degree;//学位
	private String postOffice;//岗位职务
	private String titleorTechnicalLevel;//职称或技术等级
	private String mobilPhone;//手机
	private String wiboorMicro;//微博与微信名
	private String fullorParttime;//全职或兼职
	private String operateTime;//操作时间
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid.hex")
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
	@Column(name="UNITDIRECTLY",nullable=true,length=100)
	public String getUnitdirectly() {
		return unitdirectly;
	}
	public void setUnitdirectly(String unitdirectly) {
		this.unitdirectly = unitdirectly;
	}
	@Column(name="BRANCHNAME",nullable=true,length=100)
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name="BRANCHSECRETARY",nullable=true,length=100)
	public String getBranchSecretary() {
		return branchSecretary;
	}
	public void setBranchSecretary(String branchSecretary) {
		this.branchSecretary = branchSecretary;
	}
	@Column(name="BRANCHNUMBER",nullable=true,length=100)
	public int getBranchNumber() {
		return branchNumber;
	}
	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}
	@Column(name="JOB",nullable=true,length=100)
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Column(name="GENDER",nullable=true,length=100)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="BIRTHDAY",nullable=true,length=100)
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Column(name="POLITICSSTATUS",nullable=true,length=100)
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	@Column(name="LEVELEDUCATION",nullable=true,length=100)
	public String getLevelEducation() {
		return levelEducation;
	}
	public void setLevelEducation(String levelEducation) {
		this.levelEducation = levelEducation;
	}
	@Column(name="DEGREE",nullable=true,length=100)
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Column(name="POSTOFFICE",nullable=true,length=100)
	public String getPostOffice() {
		return postOffice;
	}
	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	@Column(name="TITLEORTECHNICALLEVEL",nullable=true,length=100)
	public String getTitleorTechnicalLevel() {
		return titleorTechnicalLevel;
	}
	public void setTitleorTechnicalLevel(String titleorTechnicalLevel) {
		this.titleorTechnicalLevel = titleorTechnicalLevel;
	}
	@Column(name="MOBILPHONE",nullable=true,length=100)
	public String getMobilPhone() {
		return mobilPhone;
	}
	public void setMobilPhone(String mobilPhone) {
		this.mobilPhone = mobilPhone;
	}
	@Column(name="WIBOORMICRO",nullable=true,length=100)
	public String getWiboorMicro() {
		return wiboorMicro;
	}
	public void setWiboorMicro(String wiboorMicro) {
		this.wiboorMicro = wiboorMicro;
	}
	@Column(name="FULLORPARTTIME",nullable=true,length=100)
	public String getFullorParttime() {
		return fullorParttime;
	}
	public void setFullorParttime(String fullorParttime) {
		this.fullorParttime = fullorParttime;
	}
	@Column(name="OPERATETIME",nullable=true,length=100)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
}
