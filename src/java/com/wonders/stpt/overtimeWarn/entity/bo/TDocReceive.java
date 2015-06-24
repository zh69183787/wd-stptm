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
 * TDocReceiveʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_DOC_RECEIVE")
public class TDocReceive implements Serializable, BusinessObject {

	private String id; // id

	private String attach; // attach

	private Long autScanFlag; // autScanFlag

	private String blMode; // blMode

	private String blResult; // blResult

	private String chiefDep; // chiefDep

	private String chiefPerson; // chiefPerson

	private String content; // content

	private String dealfile; // dealfile

	private String filedate; // filedate

	private String filezh; // filezh

	private String flag; // flag

	private String instanceid; // instanceid

	private String keyword; // keyword

	private String lastdate; // lastdate

	private String modelid; // modelid

	private String nbOpinion; // nbOpinion

	private String num; // num

	private String operateTime; // operateTime

	private String operator; // operator

	private String ordinaryDep; // ordinaryDep

	private String ordinaryPerson; // ordinaryPerson

	private String pbOpinion; // pbOpinion

	private String personname; // personname

	private String priorities; // priorities

	private String receiveDept; // receiveDept

	private String receivePerson; // receivePerson

	private String remark; // remark

	private Long removed; // removed

	private String secretClass; // secretClass

	private String swdate; // swdate

	private String swid; // swid

	private String swtype; // swtype

	private String swunit; // swunit

	private String taskid; // taskid

	private String title; // title

	private String userid; // userid

	private String workitemid; // workitemid
	
	private String unittype="";
	
	
	public String getUnittype() {
		return unittype;
	}

	public void setUnittype(String unittype) {
		this.unittype = unittype;
	}

	public String getProcessstatus() {
		return processstatus;
	}

	public void setProcessstatus(String processstatus) {
		this.processstatus = processstatus;
	}

	private String overTime ="";
	
	private String beginDate="";
	private String endDate="";
    private String processstatus="";
    
	
	public String getProcessStatus() {
		return processstatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processstatus = processStatus;
	}

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

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Column(name = "ATTACH", nullable = true, length = 4000)
	public String getAttach() {
		return attach;
	}

	public void setAutScanFlag(Long autScanFlag) {
		this.autScanFlag = autScanFlag;
	}

	@Column(name = "AUT_SCAN_FLAG", nullable = true, length = 22)
	public Long getAutScanFlag() {
		return autScanFlag;
	}

	public void setBlMode(String blMode) {
		this.blMode = blMode;
	}

	@Column(name = "BL_MODE", nullable = true, length = 50)
	public String getBlMode() {
		return blMode;
	}

	public void setBlResult(String blResult) {
		this.blResult = blResult;
	}

	@Column(name = "BL_RESULT", nullable = true, length = 500)
	public String getBlResult() {
		return blResult;
	}

	public void setChiefDep(String chiefDep) {
		this.chiefDep = chiefDep;
	}

	@Column(name = "CHIEF_DEP", nullable = true, length = 200)
	public String getChiefDep() {
		return chiefDep;
	}

	public void setChiefPerson(String chiefPerson) {
		this.chiefPerson = chiefPerson;
	}

	@Column(name = "CHIEF_PERSON", nullable = true, length = 200)
	public String getChiefPerson() {
		return chiefPerson;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT", nullable = true, length = 4000)
	public String getContent() {
		return content;
	}

	public void setDealfile(String dealfile) {
		this.dealfile = dealfile;
	}

	@Column(name = "DEALFILE", nullable = true, length = 4000)
	public String getDealfile() {
		return dealfile;
	}

	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}

	@Column(name = "FILEDATE", nullable = true, length = 19)
	public String getFiledate() {
		return filedate;
	}

	public void setFilezh(String filezh) {
		this.filezh = filezh;
	}

	@Column(name = "FILEZH", nullable = true, length = 100)
	public String getFilezh() {
		return filezh;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}

	@Column(name = "INSTANCEID", nullable = true, length = 100)
	public String getInstanceid() {
		return instanceid;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "KEYWORD", nullable = true, length = 100)
	public String getKeyword() {
		return keyword;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	@Column(name = "LASTDATE", nullable = true, length = 19)
	public String getLastdate() {
		return lastdate;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	@Column(name = "MODELID", nullable = true, length = 100)
	public String getModelid() {
		return modelid;
	}

	public void setNbOpinion(String nbOpinion) {
		this.nbOpinion = nbOpinion;
	}

	@Column(name = "NB_OPINION", nullable = true, length = 500)
	public String getNbOpinion() {
		return nbOpinion;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "NUM", nullable = true, length = 50)
	public String getNum() {
		return num;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 19)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 100)
	public String getOperator() {
		return operator;
	}

	public void setOrdinaryDep(String ordinaryDep) {
		this.ordinaryDep = ordinaryDep;
	}

	@Column(name = "ORDINARY_DEP", nullable = true, length = 200)
	public String getOrdinaryDep() {
		return ordinaryDep;
	}

	public void setOrdinaryPerson(String ordinaryPerson) {
		this.ordinaryPerson = ordinaryPerson;
	}

	@Column(name = "ORDINARY_PERSON", nullable = true, length = 200)
	public String getOrdinaryPerson() {
		return ordinaryPerson;
	}

	public void setPbOpinion(String pbOpinion) {
		this.pbOpinion = pbOpinion;
	}

	@Column(name = "PB_OPINION", nullable = true, length = 500)
	public String getPbOpinion() {
		return pbOpinion;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	@Column(name = "PERSONNAME", nullable = true, length = 4000)
	public String getPersonname() {
		return personname;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}

	@Column(name = "PRIORITIES", nullable = true, length = 200)
	public String getPriorities() {
		return priorities;
	}

	public void setReceiveDept(String receiveDept) {
		this.receiveDept = receiveDept;
	}

	@Column(name = "RECEIVE_DEPT", nullable = true, length = 200)
	public String getReceiveDept() {
		return receiveDept;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	@Column(name = "RECEIVE_PERSON", nullable = true, length = 200)
	public String getReceivePerson() {
		return receivePerson;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 22)
	public Long getRemoved() {
		return removed;
	}

	public void setSecretClass(String secretClass) {
		this.secretClass = secretClass;
	}

	@Column(name = "SECRET_CLASS", nullable = true, length = 50)
	public String getSecretClass() {
		return secretClass;
	}

	public void setSwdate(String swdate) {
		this.swdate = swdate;
	}

	@Column(name = "SWDATE", nullable = true, length = 19)
	public String getSwdate() {
		return swdate;
	}

	public void setSwid(String swid) {
		this.swid = swid;
	}

	@Column(name = "SWID", nullable = true, length = 100)
	public String getSwid() {
		return swid;
	}

	public void setSwtype(String swtype) {
		this.swtype = swtype;
	}

	@Column(name = "SWTYPE", nullable = true, length = 50)
	public String getSwtype() {
		return swtype;
	}

	public void setSwunit(String swunit) {
		this.swunit = swunit;
	}

	@Column(name = "SWUNIT", nullable = true, length = 100)
	public String getSwunit() {
		return swunit;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	@Column(name = "TASKID", nullable = true, length = 200)
	public String getTaskid() {
		return taskid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "USERID", nullable = true, length = 100)
	public String getUserid() {
		return userid;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}

	@Column(name = "WORKITEMID", nullable = true, length = 100)
	public String getWorkitemid() {
		return workitemid;
	}

}
