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

package com.wonders.stpt.securityreport.entity.bo;

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
 * SecReport实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-8-14
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_SEC_REPORT")
public class SecReport implements Serializable, BusinessObject {

	private String id; // id

	private String company; // company

	private String content; // content

	private String creatPerson; // creatPerson

	private Date creatTime; // creatTime

	private Date filingDate; // filingDate

	private String modifyPerson; // modifyPerson

	private Date modifyTime; // modifyTime

	private String remove; // remove

	private String titleQuarter; // titleQuarter

	private String titleYear; // titleYear
	
	private String createPersonName;
	
	private String modifyPersonName;

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "COMPANY", nullable = true, length = 50)
	public String getCompany() {
		return company;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CONTENT", nullable = true, length = 4000)
	public String getContent() {
		return content;
	}

	public void setCreatPerson(String creatPerson) {
		this.creatPerson = creatPerson;
	}

	@Column(name = "CREAT_PERSON", nullable = true, length = 20)
	public String getCreatPerson() {
		return creatPerson;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Column(name = "CREAT_TIME", nullable = true, length = 7)
	public Date getCreatTime() {
		return creatTime;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	@Column(name = "FILING_DATE", nullable = true, length = 7)
	public Date getFilingDate() {
		return filingDate;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	@Column(name = "MODIFY_PERSON", nullable = true, length = 20)
	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "MODIFY_TIME", nullable = true, length = 7)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}

	@Column(name = "REMOVE", nullable = true, length = 1)
	public String getRemove() {
		return remove;
	}

	public void setTitleQuarter(String titleQuarter) {
		this.titleQuarter = titleQuarter;
	}

	@Column(name = "TITLE_QUARTER", nullable = true, length = 10)
	public String getTitleQuarter() {
		return titleQuarter;
	}

	public void setTitleYear(String titleYear) {
		this.titleYear = titleYear;
	}

	@Column(name = "TITLE_YEAR", nullable = true, length = 10)
	public String getTitleYear() {
		return titleYear;
	}

	@Column(name = "CREATE_PERSON_NAME", nullable = true, length = 20)
	public String getCreatePersonName() {
		return createPersonName;
	}

	public void setCreatePersonName(String createPersonName) {
		this.createPersonName = createPersonName;
	}

	@Column(name = "MODIFY_PERSON_NAME", nullable = true, length = 20)
	public String getModifyPersonName() {
		return modifyPersonName;
	}

	public void setModifyPersonName(String modifyPersonName) {
		this.modifyPersonName = modifyPersonName;
	}

}
