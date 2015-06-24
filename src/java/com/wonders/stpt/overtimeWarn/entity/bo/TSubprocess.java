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
 * TSubprocessʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_SUBPROCESS")
public class TSubprocess implements Serializable, BusinessObject {

	private String guid; // guid

	private String cincident; // cincident

	private String cname; // cname

	private String ctaskid; // ctaskid

	private String pincident; // pincident

	private String pname; // pname

	private Long status; // status

	private Date upddate; // upddate
	
	
	private String overTime ="";
	
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUID")
	public String getGuid() {
		return guid;
	}

	public void setCincident(String cincident) {
		this.cincident = cincident;
	}

	@Column(name = "CINCIDENT", nullable = true, length = 50)
	public String getCincident() {
		return cincident;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CNAME", nullable = true, length = 50)
	public String getCname() {
		return cname;
	}

	public void setCtaskid(String ctaskid) {
		this.ctaskid = ctaskid;
	}

	@Column(name = "CTASKID", nullable = true, length = 50)
	public String getCtaskid() {
		return ctaskid;
	}

	public void setPincident(String pincident) {
		this.pincident = pincident;
	}

	@Column(name = "PINCIDENT", nullable = true, length = 50)
	public String getPincident() {
		return pincident;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "PNAME", nullable = true, length = 50)
	public String getPname() {
		return pname;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "STATUS", nullable = true, length = 22)
	public Long getStatus() {
		return status;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	@Column(name = "UPDDATE", nullable = true, length = 7)
	public Date getUpddate() {
		return upddate;
	}

}
