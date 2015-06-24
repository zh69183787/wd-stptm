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

package com.wonders.stpt.overtimeWarn.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * TSubprocessʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class TSubprocessVO implements ValueObject {

	private String guid;
	private String cincident;
	private String cname;
	private String ctaskid;
	private String pincident;
	private String pname;
	private Long status;
	private Date upddate;

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	public void setCincident(String cincident) {
		this.cincident = cincident;
	}

	public String getCincident() {
		return cincident;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCname() {
		return cname;
	}

	public void setCtaskid(String ctaskid) {
		this.ctaskid = ctaskid;
	}

	public String getCtaskid() {
		return ctaskid;
	}

	public void setPincident(String pincident) {
		this.pincident = pincident;
	}

	public String getPincident() {
		return pincident;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPname() {
		return pname;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getStatus() {
		return status;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	public Date getUpddate() {
		return upddate;
	}

}
