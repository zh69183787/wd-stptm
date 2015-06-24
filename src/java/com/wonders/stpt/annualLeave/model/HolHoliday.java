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

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * HolHolidayʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "TA_HOL_HOLIDAY")
public class HolHoliday implements Serializable, BusinessObject {

	private String id; // id

	private Long holDays; // holDays

	private Long holDaysLeft; // holDaysLeft

	private Long holDaysWait; // holDaysWait

	private String holId; // holId

	private String holState; // holState

	private String holYear; // holYear

	private String operateTime; // operateTime

	private Long operator; // operator

	private String remark; // remark

	private Long removed; // removed

    private String deptName;

    private String deptId;

    private String name;

    private String loginName;

    @Transient
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Transient
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

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

	public void setHolDays(Long holDays) {
		this.holDays = holDays;
	}

	@Column(name = "HOL_DAYS", nullable = true, length = 22)
	public Long getHolDays() {
		return holDays;
	}

	public void setHolDaysLeft(Long holDaysLeft) {
		this.holDaysLeft = holDaysLeft;
	}

	@Column(name = "HOL_DAYS_LEFT", nullable = true, length = 22)
	public Long getHolDaysLeft() {
		return holDaysLeft;
	}

	public void setHolDaysWait(Long holDaysWait) {
		this.holDaysWait = holDaysWait;
	}

	@Column(name = "HOL_DAYS_WAIT", nullable = true, length = 22)
	public Long getHolDaysWait() {
		return holDaysWait;
	}

	public void setHolId(String holId) {
		this.holId = holId;
	}

	@Column(name = "HOL_ID", nullable = true, length = 200)
	public String getHolId() {
		return holId;
	}

	public void setHolState(String holState) {
		this.holState = holState;
	}

	@Column(name = "HOL_STATE", nullable = true, length = 200)
	public String getHolState() {
		return holState;
	}

	public void setHolYear(String holYear) {
		this.holYear = holYear;
	}

	@Column(name = "HOL_YEAR", nullable = true, length = 4)
	public String getHolYear() {
		return holYear;
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

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 4000)
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

	
}
