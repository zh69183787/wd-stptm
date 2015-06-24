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

package com.wonders.stpt.dbBusiness.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * DbBusiness实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2013-6-19
 * @author modify by $Author$
 * @since 1.0
 */

public class DbBusinessVO implements ValueObject {

	private String id;
	private String attribute;
	private String bhAll;
	private String bhHead;
	private String bhLast;
	private String creatMemo;
	private String creatTime;
	private String dealFile;
	private String depementX;
	private String depementXid;
	private String depementXsfid;
	private String depementZ;
	private String depementZid;
	private String depementZsfid;
	private String flag;
	private String followType;
	private String idsgl;
	private String instantId;
	private String leader;
	private String leaderId;
	private String leaderName;
	private String operateTime;
	private String operator;
	private String operatordeptid;
	private String processName;
	private Byte removed;
	private String returnTime;
	private String sendPeople;
	private String taskId;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setBhAll(String bhAll) {
		this.bhAll = bhAll;
	}

	public String getBhAll() {
		return bhAll;
	}

	public void setBhHead(String bhHead) {
		this.bhHead = bhHead;
	}

	public String getBhHead() {
		return bhHead;
	}

	public void setBhLast(String bhLast) {
		this.bhLast = bhLast;
	}

	public String getBhLast() {
		return bhLast;
	}

	public void setCreatMemo(String creatMemo) {
		this.creatMemo = creatMemo;
	}

	public String getCreatMemo() {
		return creatMemo;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setDealFile(String dealFile) {
		this.dealFile = dealFile;
	}

	public String getDealFile() {
		return dealFile;
	}

	public void setDepementX(String depementX) {
		this.depementX = depementX;
	}

	public String getDepementX() {
		return depementX;
	}

	public void setDepementXid(String depementXid) {
		this.depementXid = depementXid;
	}

	public String getDepementXid() {
		return depementXid;
	}

	public void setDepementXsfid(String depementXsfid) {
		this.depementXsfid = depementXsfid;
	}

	public String getDepementXsfid() {
		return depementXsfid;
	}

	public void setDepementZ(String depementZ) {
		this.depementZ = depementZ;
	}

	public String getDepementZ() {
		return depementZ;
	}

	public void setDepementZid(String depementZid) {
		this.depementZid = depementZid;
	}

	public String getDepementZid() {
		return depementZid;
	}

	public void setDepementZsfid(String depementZsfid) {
		this.depementZsfid = depementZsfid;
	}

	public String getDepementZsfid() {
		return depementZsfid;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFollowType(String followType) {
		this.followType = followType;
	}

	public String getFollowType() {
		return followType;
	}

	public void setIdsgl(String idsgl) {
		this.idsgl = idsgl;
	}

	public String getIdsgl() {
		return idsgl;
	}

	public void setInstantId(String instantId) {
		this.instantId = instantId;
	}

	public String getInstantId() {
		return instantId;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperatordeptid(String operatordeptid) {
		this.operatordeptid = operatordeptid;
	}

	public String getOperatordeptid() {
		return operatordeptid;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setRemoved(Byte removed) {
		this.removed = removed;
	}

	public Byte getRemoved() {
		return removed;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setSendPeople(String sendPeople) {
		this.sendPeople = sendPeople;
	}

	public String getSendPeople() {
		return sendPeople;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskId() {
		return taskId;
	}

}
