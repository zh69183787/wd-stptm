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

package com.wonders.stpt.accidentCase.MetroAccidentCase.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroAccidentCase实体定义
 * 
 * @author zhangty
 * @version $Revision$
 * @date 2012-2-23
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroAccidentCaseVO implements ValueObject {

	private String caseId;
	private String approvalStatus;
	private String attachment;
	private String caseCategory;
	private String caseCategoryDuty;
	private String caseCategoryName;
	private String caseCType;
	private String caseCTypeCode;
	private String caseIntroduction;
	private String caseName;
	private String caseType;
	private String causeAnalysis;
	private String commentsAnalysis;
	private String correctiveMeasures;
	private String keyWord;
	private String metroLine;
	private String metroLineName;
	private String metroStation;
	private String metroStationName;
	private Date occurETime;
	private Date occurSTime;
	private String remarks;
	private String reportDept;
	private String reportDeptName;
	private String reportPerson;
	private String reportPersonName;
	private Date reportTime;
	private String responsibleDept;
	private String responsibleDeptName;
	private String responsiblePerson;
	private String responsiblePersonName;
	private String treatmentMeasures;
	private String updatePerson;
	private Date updateTime;

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	public String getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategoryDuty(String caseCategoryDuty) {
		this.caseCategoryDuty = caseCategoryDuty;
	}

	public String getCaseCategoryDuty() {
		return caseCategoryDuty;
	}

	public void setCaseCategoryName(String caseCategoryName) {
		this.caseCategoryName = caseCategoryName;
	}

	public String getCaseCategoryName() {
		return caseCategoryName;
	}

	public void setCaseCType(String caseCType) {
		this.caseCType = caseCType;
	}

	public String getCaseCType() {
		return caseCType;
	}

	public void setCaseCTypeCode(String caseCTypeCode) {
		this.caseCTypeCode = caseCTypeCode;
	}

	public String getCaseCTypeCode() {
		return caseCTypeCode;
	}

	public void setCaseIntroduction(String caseIntroduction) {
		this.caseIntroduction = caseIntroduction;
	}

	public String getCaseIntroduction() {
		return caseIntroduction;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	public void setCommentsAnalysis(String commentsAnalysis) {
		this.commentsAnalysis = commentsAnalysis;
	}

	public String getCommentsAnalysis() {
		return commentsAnalysis;
	}

	public void setCorrectiveMeasures(String correctiveMeasures) {
		this.correctiveMeasures = correctiveMeasures;
	}

	public String getCorrectiveMeasures() {
		return correctiveMeasures;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setMetroLine(String metroLine) {
		this.metroLine = metroLine;
	}

	public String getMetroLine() {
		return metroLine;
	}

	public void setMetroLineName(String metroLineName) {
		this.metroLineName = metroLineName;
	}

	public String getMetroLineName() {
		return metroLineName;
	}

	public void setMetroStation(String metroStation) {
		this.metroStation = metroStation;
	}

	public String getMetroStation() {
		return metroStation;
	}

	public void setMetroStationName(String metroStationName) {
		this.metroStationName = metroStationName;
	}

	public String getMetroStationName() {
		return metroStationName;
	}

	public void setOccurETime(Date occurETime) {
		this.occurETime = occurETime;
	}

	public Date getOccurETime() {
		return occurETime;
	}

	public void setOccurSTime(Date occurSTime) {
		this.occurSTime = occurSTime;
	}

	public Date getOccurSTime() {
		return occurSTime;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setReportDept(String reportDept) {
		this.reportDept = reportDept;
	}

	public String getReportDept() {
		return reportDept;
	}

	public void setReportDeptName(String reportDeptName) {
		this.reportDeptName = reportDeptName;
	}

	public String getReportDeptName() {
		return reportDeptName;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setResponsibleDept(String responsibleDept) {
		this.responsibleDept = responsibleDept;
	}

	public String getResponsibleDept() {
		return responsibleDept;
	}

	public void setResponsibleDeptName(String responsibleDeptName) {
		this.responsibleDeptName = responsibleDeptName;
	}

	public String getResponsibleDeptName() {
		return responsibleDeptName;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setTreatmentMeasures(String treatmentMeasures) {
		this.treatmentMeasures = treatmentMeasures;
	}

	public String getTreatmentMeasures() {
		return treatmentMeasures;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

}
