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

package com.wonders.stpt.annualLeave.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * HolConfigʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TA_HOL_CONFIG")
public class HolConfig implements Serializable, BusinessObject {

	private String id; // id

	private String flag; // flag

	private String hdate; // hdate

	private String hday; // hday

	private String hmonth; // hmonth

	private String hyear; // hyear

	private String operateTime; // operateTime

	private Long operator; // operator

	private Long removed; // removed
	
	private String memo;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 1)
	public String getFlag() {
		return flag;
	}

	public void setHdate(String hdate) {
		this.hdate = hdate;
	}

	@Column(name = "HDATE", nullable = true, length = 10)
	public String getHdate() {
		return hdate;
	}

	public void setHday(String hday) {
		this.hday = hday;
	}

	@Column(name = "HDAY", nullable = true, length = 2)
	public String getHday() {
		return hday;
	}

	public void setHmonth(String hmonth) {
		this.hmonth = hmonth;
	}

	@Column(name = "HMONTH", nullable = true, length = 2)
	public String getHmonth() {
		return hmonth;
	}

	public void setHyear(String hyear) {
		this.hyear = hyear;
	}

	@Column(name = "HYEAR", nullable = true, length = 4)
	public String getHyear() {
		return hyear;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 19)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 22)
	public Long getOperator() {
		return operator;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 22)
	public Long getRemoved() {
		return removed;
	}

	@Column(name = "MEMO", nullable = true, length = 32)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
