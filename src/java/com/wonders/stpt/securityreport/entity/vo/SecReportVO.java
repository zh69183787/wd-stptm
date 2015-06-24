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

package com.wonders.stpt.securityreport.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * SecReport实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

public class SecReportVO implements ValueObject {

	private String id;
	private String company;
	private String content;
	private String creatPerson;
	private Date creatTime;
	private Date filingDate;
	private String modifyPerson;
	private Date modifyTime;
	private String remove;
	private String titleQuarter;
	private String titleYear;
	private String createPersonName;	
	private String modifyPersonName;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCreatPerson(String creatPerson) {
		this.creatPerson = creatPerson;
	}

	public String getCreatPerson() {
		return creatPerson;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	public Date getFilingDate() {
		return filingDate;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}

	public String getRemove() {
		remove = "0";
		return remove;
	}

	public void setTitleQuarter(String titleQuarter) {
		this.titleQuarter = titleQuarter;
	}

	public String getTitleQuarter() {
		return titleQuarter;
	}

	public void setTitleYear(String titleYear) {
		this.titleYear = titleYear;
	}

	public String getTitleYear() {
		return titleYear;
	}

	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	public String getModifyPersonName() {
		return modifyPersonName;
	}

	public void setModifyPersonName(String modifyPersonName) {
		this.modifyPersonName = modifyPersonName;
	}

}
