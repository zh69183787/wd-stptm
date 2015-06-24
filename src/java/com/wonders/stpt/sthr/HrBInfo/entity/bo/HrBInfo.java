/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.sthr.HrBInfo.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * HrBInfo实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "HR_B_INFO")
public class HrBInfo implements Serializable, BusinessObject {

	private String hrId; // hrId

	private Date birthday; // birthday

	private String birthplace; // birthplace

	private String companyAddress; // companyAddress
	
	private String companyId;

	private String companyPhone; // companyPhone

	private String companyZip; // companyZip

	private String cCompany; // cCompany

	private String highestDegree; // highestDegree

	private String homePhone; // homePhone

	private String hukou; // hukou

	private String idCard; // idCard

	private String isRetire; // isRetire

	private String jobNumber; // jobNumber

	private String jobTitles; // jobTitles

	private String mobilePhone; // mobilePhone

	private String name; // name

	private String nation; // nation

	private String politicalLandscape; // politicalLandscape

	private String position; // position

	private String remarks; // remarks

	private String reportDept; // reportDept

	private String reportDeptName; // reportDeptName

	private String reportPerson; // reportPerson

	private String reportPersonName; // reportPersonName

	private String residentialAddress; // residentialAddress

	private String sex; // sex

	private String technicalGrade; // technicalGrade
	
	private String technicalMajor;

	private String updatePerson; // updatePerson

	private Date updateTime; // updateTime

	private String zipCode; // zipCode

	public void setHrId(String hrId) {
		this.hrId = hrId;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "HR_ID")
	public String getHrId() {
		return hrId;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "BIRTHDAY", nullable = true, length = 7)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "BIRTHPLACE", nullable = true, length = 20)
	public String getBirthplace() {
		return birthplace;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "COMPANY_ADDRESS", nullable = true, length = 20)
	public String getCompanyAddress() {
		return companyAddress;
	}
	
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_ID", nullable = true, length = 20)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	@Column(name = "COMPANY_PHONE", nullable = true, length = 20)
	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}

	@Column(name = "COMPANY_ZIP", nullable = true, length = 20)
	public String getCompanyZip() {
		return companyZip;
	}

	public void setCCompany(String cCompany) {
		this.cCompany = cCompany;
	}

	@Column(name = "C_COMPANY", nullable = true, length = 20)
	public String getCCompany() {
		return cCompany;
	}

	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}

	@Column(name = "HIGHEST_DEGREE", nullable = true, length = 20)
	public String getHighestDegree() {
		return highestDegree;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "HOME_PHONE", nullable = true, length = 20)
	public String getHomePhone() {
		return homePhone;
	}

	public void setHukou(String hukou) {
		this.hukou = hukou;
	}

	@Column(name = "HUKOU", nullable = true, length = 20)
	public String getHukou() {
		return hukou;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "ID_CARD", nullable = true, length = 40)
	public String getIdCard() {
		return idCard;
	}

	public void setIsRetire(String isRetire) {
		this.isRetire = isRetire;
	}

	@Column(name = "IS_RETIRE", nullable = true, length = 20)
	public String getIsRetire() {
		return isRetire;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Column(name = "JOB_NUMBER", nullable = true, length = 20)
	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobTitles(String jobTitles) {
		this.jobTitles = jobTitles;
	}

	@Column(name = "JOB_TITLES", nullable = true, length = 20)
	public String getJobTitles() {
		return jobTitles;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "MOBILE_PHONE", nullable = true, length = 20)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "NATION", nullable = true, length = 20)
	public String getNation() {
		return nation;
	}

	public void setPoliticalLandscape(String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	@Column(name = "POLITICAL_LANDSCAPE", nullable = true, length = 20)
	public String getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "POSITION", nullable = true, length = 20)
	public String getPosition() {
		return position;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "REMARKS", nullable = true, length = 4000)
	public String getRemarks() {
		return remarks;
	}

	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	@Column(name = "REPORT_DEPT", nullable = true, length = 20)
	public String getReportDept() {
		return reportDept;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	@Column(name = "REPORT_DEPT_NAME", nullable = true, length = 20)
	public String getReportDeptName() {
		return reportDeptName;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	@Column(name = "REPORT_PERSON", nullable = true, length = 20)
	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	@Column(name = "REPORT_PERSON_NAME", nullable = true, length = 20)
	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	@Column(name = "RESIDENTIAL_ADDRESS", nullable = true, length = 200)
	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "SEX", nullable = true, length = 1)
	public String getSex() {
		return sex;
	}

	public void setTechnicalGrade(String technicalGrade) {
		this.technicalGrade = technicalGrade;
	}

	@Column(name = "TECHNICAL_GRADE", nullable = true, length = 20)
	public String getTechnicalGrade() {
		return technicalGrade;
	}
	
	public void setTechnicalMajor(String technicalMajor) {
		this.technicalMajor = technicalMajor;
	}

	@Column(name = "TECHNICAL_MAJOR", nullable = true, length = 20)
	public String getTechnicalMajor() {
		return technicalMajor;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	@Column(name = "UPDATE_PERSON", nullable = true, length = 20)
	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_TIME", nullable = true, length = 11)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "ZIP_CODE", nullable = true, length = 20)
	public String getZipCode() {
		return zipCode;
	}

}
