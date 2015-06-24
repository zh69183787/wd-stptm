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

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * AssetTaskCheckʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-7-4
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_ASSET_TASK_CHECK")
public class AssetTaskCheck implements Serializable, BusinessObject {

	private Long id; // id

	private String assetInfoId; // assetInfoId

	private String checkdate; // checkdate

	private String checkinfo; // checkinfo

	private String checkperson; // checkperson

	private String removed; // removed

	private String taskId; // taskId
	
	private String operateDate;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAssetInfoId(String assetInfoId) {
		this.assetInfoId = assetInfoId;
	}

	@Column(name = "ASSET_INFO_ID", nullable = true, length = 40)
	public String getAssetInfoId() {
		return assetInfoId;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	@Column(name = "CHECKDATE", nullable = true, length = 20)
	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckinfo(String checkinfo) {
		this.checkinfo = checkinfo;
	}

	@Column(name = "CHECKINFO", nullable = true, length = 300)
	public String getCheckinfo() {
		return checkinfo;
	}

	public void setCheckperson(String checkperson) {
		this.checkperson = checkperson;
	}

	@Column(name = "CHECKPERSON", nullable = true, length = 60)
	public String getCheckperson() {
		return checkperson;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TASK_ID", nullable = true, length = 40)
	public String getTaskId() {
		return taskId;
	}

	@Column(name = "OPERATE_DATE", nullable = true, length = 20)
	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	
	
}
