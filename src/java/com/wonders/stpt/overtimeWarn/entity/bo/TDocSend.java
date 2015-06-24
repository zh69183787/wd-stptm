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

package com.wonders.stpt.overtimeWarn.entity.bo;

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
 * TDocSendʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_DOC_SEND")
public class TDocSend implements Serializable, BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id; // id

	private String attachRemark; // attachRemark

	private String attFlag; // attFlag

	private Long autScanFlag; // autScanFlag

	private String code1; // code1

	private String code2; // code2

	private String code3; // code3

	private String contentAtt; // contentAtt

	private String contentAttMain; // contentAttMain

	private String contentAttTyping; // contentAttTyping

	private String docClass; // docClass

	private String docCount; // docCount

	private String docKeyword; // docKeyword

	private String docState; // docState

	private String docTitle; // docTitle

	private String fileType; // fileType

	private String flag; // flag

	private String hj; // hj

	private String lineType; // lineType

	private String modelid; // modelid

	private String operateTime; // operateTime

	private String operator; // operator

	private String personname; // personname

	private String pinstanceid; // pinstanceid

	private Long removed; // removed

	private String secretClass; // secretClass

	private String secretLimit; // secretLimit

	private String sendDate; // sendDate

	private String sendId; // sendId

	private String sendId1; // sendId1

	private String sendId2; // sendId2

	private String sendInside; // sendInside

	private String sendInsideId; // sendInsideId

	private String sendMain; // sendMain

	private String sendMainId; // sendMainId

	private String sendMainW; // sendMainW

	private String sendReport; // sendReport

	private String sendReportId; // sendReportId

	private String sendReportW; // sendReportW

	private String sendTitleType; // sendTitleType

	private String sendType; // sendType

	private String sendUser; // sendUser

	private String sendUserdept; // sendUserdept

	private String sendUserLeader; // sendUserLeader

	private String signDept; // signDept

	private String signLead; // signLead

	private String snedCopy; // snedCopy

	private String taskid; // taskid

	private String taskuser; // taskuser

	private String typeTitle; // typeTitle

	private String userid; // userid

	private String viseLead; // viseLead

	private String workitemid; // workitemid
	
	private String name;//签发
	private String content;//主抄送
	private String processstatus;//完成状态
	private String year;//年份时间
	private String unittype;//分类
	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String beginDate="";
	private String endDate="";
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private String overTime ="";
	
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAttachRemark(String attachRemark) {
		this.attachRemark = attachRemark;
	}

	@Column(name = "ATTACH_REMARK", nullable = true, length = 4000)
	public String getAttachRemark() {
		return attachRemark;
	}

	public void setAttFlag(String attFlag) {
		this.attFlag = attFlag;
	}

	@Column(name = "ATT_FLAG", nullable = true, length = 2)
	public String getAttFlag() {
		return attFlag;
	}

	public void setAutScanFlag(Long autScanFlag) {
		this.autScanFlag = autScanFlag;
	}

	@Column(name = "AUT_SCAN_FLAG", nullable = true, length = 22)
	public Long getAutScanFlag() {
		return autScanFlag;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	@Column(name = "CODE1", nullable = true, length = 20)
	public String getCode1() {
		return code1;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	@Column(name = "CODE2", nullable = true, length = 20)
	public String getCode2() {
		return code2;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	@Column(name = "CODE3", nullable = true, length = 20)
	public String getCode3() {
		return code3;
	}

	public void setContentAtt(String contentAtt) {
		this.contentAtt = contentAtt;
	}

	@Column(name = "CONTENT_ATT", nullable = true, length = 200)
	public String getContentAtt() {
		return contentAtt;
	}

	public void setContentAttMain(String contentAttMain) {
		this.contentAttMain = contentAttMain;
	}

	@Column(name = "CONTENT_ATT_MAIN", nullable = true, length = 200)
	public String getContentAttMain() {
		return contentAttMain;
	}

	public void setContentAttTyping(String contentAttTyping) {
		this.contentAttTyping = contentAttTyping;
	}

	@Column(name = "CONTENT_ATT_TYPING", nullable = true, length = 200)
	public String getContentAttTyping() {
		return contentAttTyping;
	}

	public void setDocClass(String docClass) {
		this.docClass = docClass;
	}

	@Column(name = "DOC_CLASS", nullable = true, length = 20)
	public String getDocClass() {
		return docClass;
	}

	public void setDocCount(String docCount) {
		this.docCount = docCount;
	}

	@Column(name = "DOC_COUNT", nullable = true, length = 400)
	public String getDocCount() {
		return docCount;
	}

	public void setDocKeyword(String docKeyword) {
		this.docKeyword = docKeyword;
	}

	@Column(name = "DOC_KEYWORD", nullable = true, length = 400)
	public String getDocKeyword() {
		return docKeyword;
	}

	public void setDocState(String docState) {
		this.docState = docState;
	}

	@Column(name = "DOC_STATE", nullable = true, length = 200)
	public String getDocState() {
		return docState;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	@Column(name = "DOC_TITLE", nullable = true, length = 400)
	public String getDocTitle() {
		return docTitle;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FILE_TYPE", nullable = true, length = 200)
	public String getFileType() {
		return fileType;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setHj(String hj) {
		this.hj = hj;
	}

	@Column(name = "HJ", nullable = true, length = 60)
	public String getHj() {
		return hj;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	@Column(name = "LINE_TYPE", nullable = true, length = 200)
	public String getLineType() {
		return lineType;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	@Column(name = "MODELID", nullable = true, length = 200)
	public String getModelid() {
		return modelid;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 400)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 200)
	public String getOperator() {
		return operator;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	@Column(name = "PERSONNAME", nullable = true, length = 2000)
	public String getPersonname() {
		return personname;
	}

	public void setPinstanceid(String pinstanceid) {
		this.pinstanceid = pinstanceid;
	}

	@Column(name = "PINSTANCEID", nullable = true, length = 200)
	public String getPinstanceid() {
		return pinstanceid;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public Long getRemoved() {
		return removed;
	}

	public void setSecretClass(String secretClass) {
		this.secretClass = secretClass;
	}

	@Column(name = "SECRET_CLASS", nullable = true, length = 200)
	public String getSecretClass() {
		return secretClass;
	}

	public void setSecretLimit(String secretLimit) {
		this.secretLimit = secretLimit;
	}

	@Column(name = "SECRET_LIMIT", nullable = true, length = 200)
	public String getSecretLimit() {
		return secretLimit;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "SEND_DATE", nullable = true, length = 20)
	public String getSendDate() {
		return sendDate;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "SEND_ID", nullable = true, length = 400)
	public String getSendId() {
		return sendId;
	}

	public void setSendId1(String sendId1) {
		this.sendId1 = sendId1;
	}

	@Column(name = "SEND_ID_1", nullable = true, length = 4000)
	public String getSendId1() {
		return sendId1;
	}

	public void setSendId2(String sendId2) {
		this.sendId2 = sendId2;
	}

	@Column(name = "SEND_ID_2", nullable = true, length = 4000)
	public String getSendId2() {
		return sendId2;
	}

	public void setSendInside(String sendInside) {
		this.sendInside = sendInside;
	}

	@Column(name = "SEND_INSIDE", nullable = true, length = 4000)
	public String getSendInside() {
		return sendInside;
	}

	public void setSendInsideId(String sendInsideId) {
		this.sendInsideId = sendInsideId;
	}

	@Column(name = "SEND_INSIDE_ID", nullable = true, length = 4000)
	public String getSendInsideId() {
		return sendInsideId;
	}

	public void setSendMain(String sendMain) {
		this.sendMain = sendMain;
	}

	@Column(name = "SEND_MAIN", nullable = true, length = 4000)
	public String getSendMain() {
		return sendMain;
	}

	public void setSendMainId(String sendMainId) {
		this.sendMainId = sendMainId;
	}

	@Column(name = "SEND_MAIN_ID", nullable = true, length = 4000)
	public String getSendMainId() {
		return sendMainId;
	}

	public void setSendMainW(String sendMainW) {
		this.sendMainW = sendMainW;
	}

	@Column(name = "SEND_MAIN_W", nullable = true, length = 4000)
	public String getSendMainW() {
		return sendMainW;
	}

	public void setSendReport(String sendReport) {
		this.sendReport = sendReport;
	}

	@Column(name = "SEND_REPORT", nullable = true, length = 4000)
	public String getSendReport() {
		return sendReport;
	}

	public void setSendReportId(String sendReportId) {
		this.sendReportId = sendReportId;
	}

	@Column(name = "SEND_REPORT_ID", nullable = true, length = 4000)
	public String getSendReportId() {
		return sendReportId;
	}

	public void setSendReportW(String sendReportW) {
		this.sendReportW = sendReportW;
	}

	@Column(name = "SEND_REPORT_W", nullable = true, length = 4000)
	public String getSendReportW() {
		return sendReportW;
	}

	public void setSendTitleType(String sendTitleType) {
		this.sendTitleType = sendTitleType;
	}

	@Column(name = "SEND_TITLE_TYPE", nullable = true, length = 200)
	public String getSendTitleType() {
		return sendTitleType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	@Column(name = "SEND_TYPE", nullable = true, length = 200)
	public String getSendType() {
		return sendType;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	@Column(name = "SEND_USER", nullable = true, length = 200)
	public String getSendUser() {
		return sendUser;
	}

	public void setSendUserdept(String sendUserdept) {
		this.sendUserdept = sendUserdept;
	}

	@Column(name = "SEND_USERDEPT", nullable = true, length = 200)
	public String getSendUserdept() {
		return sendUserdept;
	}

	public void setSendUserLeader(String sendUserLeader) {
		this.sendUserLeader = sendUserLeader;
	}

	@Column(name = "SEND_USER_LEADER", nullable = true, length = 200)
	public String getSendUserLeader() {
		return sendUserLeader;
	}

	public void setSignDept(String signDept) {
		this.signDept = signDept;
	}

	@Column(name = "SIGN_DEPT", nullable = true, length = 4000)
	public String getSignDept() {
		return signDept;
	}

	public void setSignLead(String signLead) {
		this.signLead = signLead;
	}

	@Column(name = "SIGN_LEAD", nullable = true, length = 4000)
	public String getSignLead() {
		return signLead;
	}

	public void setSnedCopy(String snedCopy) {
		this.snedCopy = snedCopy;
	}

	@Column(name = "SNED_COPY", nullable = true, length = 4000)
	public String getSnedCopy() {
		return snedCopy;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	@Column(name = "TASKID", nullable = true, length = 200)
	public String getTaskid() {
		return taskid;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	@Column(name = "TASKUSER", nullable = true, length = 400)
	public String getTaskuser() {
		return taskuser;
	}

	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}

	@Column(name = "TYPE_TITLE", nullable = true, length = 200)
	public String getTypeTitle() {
		return typeTitle;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "USERID", nullable = true, length = 200)
	public String getUserid() {
		return userid;
	}

	public void setViseLead(String viseLead) {
		this.viseLead = viseLead;
	}

	@Column(name = "VISE_LEAD", nullable = true, length = 200)
	public String getViseLead() {
		return viseLead;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}

	@Column(name = "WORKITEMID", nullable = true, length = 200)
	public String getWorkitemid() {
		return workitemid;
	}

}
