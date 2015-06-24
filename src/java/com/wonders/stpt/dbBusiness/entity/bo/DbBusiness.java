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

package com.wonders.stpt.dbBusiness.entity.bo;

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
 * DbBusiness实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2013-6-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_DOC_DB")
public class DbBusiness implements Serializable, BusinessObject {

	private String id; // id

	private String attribute; // attribute

	private String bhAll; // bhAll

	private String bhHead; // bhHead

	private String bhLast; // bhLast

	private String creatMemo; // creatMemo

	private String creatTime; // creatTime

	private String dealFile; // dealFile

	private String depementX; // depementX

	private String depementXid; // depementXid

	private String depementXsfid; // depementXsfid

	private String depementZ; // depementZ

	private String depementZid; // depementZid

	private String depementZsfid; // depementZsfid

	private String flag; // flag

	private String followType; // followType

	private String idsgl; // idsgl

	private String instantId; // instantId

	private String leader; // leader

	private String leaderId; // leaderId

	private String leaderName; // leaderName

	private String operateTime; // operateTime

	private String operator; // operator

	private String operatordeptid; // operatordeptid

	private String processName; // processName

	private Byte removed; // removed

	private String returnTime; // returnTime

	private String sendPeople; // sendPeople

	private String taskId; // taskId

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Column(name = "ATTRIBUTE", nullable = true, length = 50)
	public String getAttribute() {
		return attribute;
	}

	public void setBhAll(String bhAll) {
		this.bhAll = bhAll;
	}

	@Column(name = "BH_ALL", nullable = true, length = 50)
	public String getBhAll() {
		return bhAll;
	}

	public void setBhHead(String bhHead) {
		this.bhHead = bhHead;
	}

	@Column(name = "BH_HEAD", nullable = true, length = 20)
	public String getBhHead() {
		return bhHead;
	}

	public void setBhLast(String bhLast) {
		this.bhLast = bhLast;
	}

	@Column(name = "BH_LAST", nullable = true, length = 20)
	public String getBhLast() {
		return bhLast;
	}

	public void setCreatMemo(String creatMemo) {
		this.creatMemo = creatMemo;
	}

	@Column(name = "CREAT_MEMO", nullable = true, length = 2000)
	public String getCreatMemo() {
		return creatMemo;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	@Column(name = "CREAT_TIME", nullable = true, length = 20)
	public String getCreatTime() {
		return creatTime;
	}

	public void setDealFile(String dealFile) {
		this.dealFile = dealFile;
	}

	@Column(name = "DEAL_FILE", nullable = true, length = 100)
	public String getDealFile() {
		return dealFile;
	}

	public void setDepementX(String depementX) {
		this.depementX = depementX;
	}

	@Column(name = "DEPEMENT_X", nullable = true, length = 900)
	public String getDepementX() {
		return depementX;
	}

	public void setDepementXid(String depementXid) {
		this.depementXid = depementXid;
	}

	@Column(name = "DEPEMENT_XID", nullable = true, length = 900)
	public String getDepementXid() {
		return depementXid;
	}

	public void setDepementXsfid(String depementXsfid) {
		this.depementXsfid = depementXsfid;
	}

	@Column(name = "DEPEMENT_XSFID", nullable = true, length = 900)
	public String getDepementXsfid() {
		return depementXsfid;
	}

	public void setDepementZ(String depementZ) {
		this.depementZ = depementZ;
	}

	@Column(name = "DEPEMENT_Z", nullable = true, length = 900)
	public String getDepementZ() {
		return depementZ;
	}

	public void setDepementZid(String depementZid) {
		this.depementZid = depementZid;
	}

	@Column(name = "DEPEMENT_ZID", nullable = true, length = 800)
	public String getDepementZid() {
		return depementZid;
	}

	public void setDepementZsfid(String depementZsfid) {
		this.depementZsfid = depementZsfid;
	}

	@Column(name = "DEPEMENT_ZSFID", nullable = true, length = 800)
	public String getDepementZsfid() {
		return depementZsfid;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setFollowType(String followType) {
		this.followType = followType;
	}

	@Column(name = "FOLLOW_TYPE", nullable = true, length = 1)
	public String getFollowType() {
		return followType;
	}

	public void setIdsgl(String idsgl) {
		this.idsgl = idsgl;
	}

	@Column(name = "IDSGL", nullable = true, length = 2000)
	public String getIdsgl() {
		return idsgl;
	}

	public void setInstantId(String instantId) {
		this.instantId = instantId;
	}

	@Column(name = "INSTANT_ID", nullable = true, length = 20)
	public String getInstantId() {
		return instantId;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	@Column(name = "LEADER", nullable = true, length = 200)
	public String getLeader() {
		return leader;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	@Column(name = "LEADER_ID", nullable = true, length = 300)
	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	@Column(name = "LEADER_NAME", nullable = true, length = 300)
	public String getLeaderName() {
		return leaderName;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 20)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 20)
	public String getOperator() {
		return operator;
	}

	public void setOperatordeptid(String operatordeptid) {
		this.operatordeptid = operatordeptid;
	}

	@Column(name = "OPERATORDEPTID", nullable = true, length = 20)
	public String getOperatordeptid() {
		return operatordeptid;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	@Column(name = "PROCESS_NAME", nullable = true, length = 20)
	public String getProcessName() {
		return processName;
	}

	public void setRemoved(Byte removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public Byte getRemoved() {
		return removed;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	@Column(name = "RETURN_TIME", nullable = true, length = 20)
	public String getReturnTime() {
		return returnTime;
	}

	public void setSendPeople(String sendPeople) {
		this.sendPeople = sendPeople;
	}

	@Column(name = "SEND_PEOPLE", nullable = true, length = 100)
	public String getSendPeople() {
		return sendPeople;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TASK_ID", nullable = true, length = 200)
	public String getTaskId() {
		return taskId;
	}

}
