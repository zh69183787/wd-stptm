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

package com.wonders.stpt.task.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * AssetTaskʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-19
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ASSET_TASK")
public class AssetTask implements Serializable, BusinessObject {

	private String id; // id

	private String checkpersonlist; // checkpersonlist

	private String completerate; // completerate

	private String endtime; // endtime

	private String errornum; // errornum

	private String realitytime; // realitytime

	private String starttime; // starttime

	private String taskmemo; // taskmemo

	private String taskname; // taskname

	private String taskstatus; // taskstatus

	private String taskuser; // taskuser

	private String taskmemoFilter;
	
	private String operateDate;
	private String removed;
	
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setCheckpersonlist(String checkpersonlist) {
		this.checkpersonlist = checkpersonlist;
	}

	@Column(name = "CHECKPERSONLIST", nullable = true, length = 800)
	public String getCheckpersonlist() {
		return checkpersonlist;
	}

	public void setCompleterate(String completerate) {
		this.completerate = completerate;
	}

	@Column(name = "COMPLETERATE", nullable = true, length = 20)
	public String getCompleterate() {
		return completerate;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Column(name = "ENDTIME", nullable = true, length = 20)
	public String getEndtime() {
		return endtime;
	}

	public void setErrornum(String errornum) {
		this.errornum = errornum;
	}

	@Column(name = "ERRORNUM", nullable = true, length = 50)
	public String getErrornum() {
		return errornum;
	}

	public void setRealitytime(String realitytime) {
		this.realitytime = realitytime;
	}

	@Column(name = "REALITYTIME", nullable = true, length = 20)
	public String getRealitytime() {
		return realitytime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	@Column(name = "STARTTIME", nullable = true, length = 20)
	public String getStarttime() {
		return starttime;
	}

	public void setTaskmemo(String taskmemo) {
		this.taskmemo = taskmemo;
	}

	@Column(name = "TASKMEMO", nullable = true, length = 400)
	public String getTaskmemo() {
		return taskmemo;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	@Column(name = "TASKNAME", nullable = true, length = 200)
	public String getTaskname() {
		return taskname;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	@Column(name = "TASKSTATUS", nullable = true, length = 50)
	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskuser(String taskuser) {
		this.taskuser = taskuser;
	}

	@Column(name = "TASKUSER", nullable = true, length = 60)
	public String getTaskuser() {
		return taskuser;
	}

	@Column(name ="TASKMEMO_FILTER ",nullable = true,length = 400)
	public String getTaskmemoFilter() {
		return taskmemoFilter;
	}

	public void setTaskmemoFilter(String taskmemoFilter) {
		this.taskmemoFilter = taskmemoFilter;
	}

	@Column(name="OPERATE_DATE",nullable=true,length=20)
	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name="REMOVED",nullable=true,length=2)
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	
	
}
