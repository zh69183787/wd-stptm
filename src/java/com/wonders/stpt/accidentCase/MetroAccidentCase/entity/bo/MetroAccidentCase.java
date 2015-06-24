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

package com.wonders.stpt.accidentCase.MetroAccidentCase.entity.bo;

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
 * MetroAccidentCase实体定义
 * 
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "METRO_ACCIDENT_CASE")
public class MetroAccidentCase implements Serializable, BusinessObject {

	private String caseId; // caseId

	private String approvalStatus; // approvalStatus

	private String attachment; // attachment

	private String caseCategory; // caseCategory

	private String caseCategoryDuty; // caseCategoryDuty

	private String caseCategoryName; // caseCategoryName

	private String caseCType; // caseCType

	private String caseCTypeCode; // caseCTypeCode

	private String caseIntroduction; // caseIntroduction

	private String caseName; // caseName

	private String caseType; // caseType

	private String causeAnalysis; // causeAnalysis

	private String commentsAnalysis; // commentsAnalysis

	private String correctiveMeasures; // correctiveMeasures

	private String keyWord; // keyWord

	private String metroLine; // metroLine

	private String metroLineName; // metroLineName

	private String metroStation; // metroStation

	private String metroStationName; // metroStationName

	private Date occurETime; // occurETime

	private Date occurSTime; // occurSTime

	private String remarks; // remarks

	private String reportDept; // reportDept

	private String reportDeptName; // reportDeptName

	private String reportPerson; // reportPerson

	private String reportPersonName; // reportPersonName

	private Date reportTime; // reportTime

	private String responsibleDept; // responsibleDept

	private String responsibleDeptName; // responsibleDeptName

	private String responsiblePerson; // responsiblePerson

	private String responsiblePersonName; // responsiblePersonName

	private String treatmentMeasures; // treatmentMeasures

	private String updatePerson; // updatePerson

	private Date updateTime; // updateTime

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "CASE_ID")
	public String getCaseId() {
		return caseId;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Column(name = "APPROVAL_STATUS", nullable = true, length = 20)
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@Column(name = "ATTACHMENT", nullable = true, length = 200)
	public String getAttachment() {
		return attachment;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	@Column(name = "CASE_CATEGORY", nullable = true, length = 20)
	public String getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategoryDuty(String caseCategoryDuty) {
		this.caseCategoryDuty = caseCategoryDuty;
	}

	@Column(name = "CASE_CATEGORY_DUTY", nullable = true, length = 4000)
	public String getCaseCategoryDuty() {
		return caseCategoryDuty;
	}

	public void setCaseCategoryName(String caseCategoryName) {
		this.caseCategoryName = caseCategoryName;
	}

	@Column(name = "CASE_CATEGORY_NAME", nullable = true, length = 20)
	public String getCaseCategoryName() {
		return caseCategoryName;
	}

	public void setCaseCType(String caseCType) {
		this.caseCType = caseCType;
	}

	@Column(name = "CASE_C_TYPE", nullable = true, length = 20)
	public String getCaseCType() {
		return caseCType;
	}

	public void setCaseCTypeCode(String caseCTypeCode) {
		this.caseCTypeCode = caseCTypeCode;
	}

	@Column(name = "CASE_C_TYPE_CODE", nullable = true, length = 20)
	public String getCaseCTypeCode() {
		return caseCTypeCode;
	}

	public void setCaseIntroduction(String caseIntroduction) {
		this.caseIntroduction = caseIntroduction;
	}

	@Column(name = "CASE_INTRODUCTION", nullable = true, length = 4000)
	public String getCaseIntroduction() {
		return caseIntroduction;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@Column(name = "CASE_NAME", nullable = true, length = 100)
	public String getCaseName() {
		return caseName;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	@Column(name = "CASE_TYPE", nullable = true, length = 20)
	public String getCaseType() {
		return caseType;
	}

	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	@Column(name = "CAUSE_ANALYSIS", nullable = true, length = 4000)
	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	public void setCommentsAnalysis(String commentsAnalysis) {
		this.commentsAnalysis = commentsAnalysis;
	}

	@Column(name = "COMMENTS_ANALYSIS", nullable = true, length = 4000)
	public String getCommentsAnalysis() {
		return commentsAnalysis;
	}

	public void setCorrectiveMeasures(String correctiveMeasures) {
		this.correctiveMeasures = correctiveMeasures;
	}

	@Column(name = "CORRECTIVE_MEASURES", nullable = true, length = 4000)
	public String getCorrectiveMeasures() {
		return correctiveMeasures;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "KEY_WORD", nullable = true, length = 100)
	public String getKeyWord() {
		return keyWord;
	}

	public void setMetroLine(String metroLine) {
		this.metroLine = metroLine;
	}

	@Column(name = "METRO_LINE", nullable = true, length = 20)
	public String getMetroLine() {
		return metroLine;
	}

	public void setMetroLineName(String metroLineName) {
		this.metroLineName = metroLineName;
	}

	@Column(name = "METRO_LINE_NAME", nullable = true, length = 20)
	public String getMetroLineName() {
		return metroLineName;
	}

	public void setMetroStation(String metroStation) {
		this.metroStation = metroStation;
	}

	@Column(name = "METRO_STATION", nullable = true, length = 20)
	public String getMetroStation() {
		return metroStation;
	}

	public void setMetroStationName(String metroStationName) {
		this.metroStationName = metroStationName;
	}

	@Column(name = "METRO_STATION_NAME", nullable = true, length = 20)
	public String getMetroStationName() {
		return metroStationName;
	}

	public void setOccurETime(Date occurETime) {
		this.occurETime = occurETime;
	}

	@Column(name = "OCCUR_E_TIME", nullable = true, length = 11)
	public Date getOccurETime() {
		return occurETime;
	}

	public void setOccurSTime(Date occurSTime) {
		this.occurSTime = occurSTime;
	}

	@Column(name = "OCCUR_S_TIME", nullable = true, length = 11)
	public Date getOccurSTime() {
		return occurSTime;
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

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "REPORT_TIME", nullable = true, length = 7)
	public Date getReportTime() {
		return reportTime;
	}

	public void setResponsibleDept(String responsibleDept) {
		this.responsibleDept = responsibleDept;
	}

	@Column(name = "RESPONSIBLE_DEPT", nullable = true, length = 20)
	public String getResponsibleDept() {
		return responsibleDept;
	}

	public void setResponsibleDeptName(String responsibleDeptName) {
		this.responsibleDeptName = responsibleDeptName;
	}

	@Column(name = "RESPONSIBLE_DEPT_NAME", nullable = true, length = 20)
	public String getResponsibleDeptName() {
		return responsibleDeptName;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	@Column(name = "RESPONSIBLE_PERSON", nullable = true, length = 20)
	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	@Column(name = "RESPONSIBLE_PERSON_NAME", nullable = true, length = 20)
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setTreatmentMeasures(String treatmentMeasures) {
		this.treatmentMeasures = treatmentMeasures;
	}

	@Column(name = "TREATMENT_MEASURES", nullable = true, length = 25)
	public String getTreatmentMeasures() {
		return treatmentMeasures;
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

	@Column(name = "UPDATE_TIME", nullable = true, length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

}
