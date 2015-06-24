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

package com.wonders.stpt.supplier.entity.bo;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * Supplierʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-6-12
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_SUPPLIER")
@SequenceGenerator(name="supplierAAA",sequenceName="SEQ_T_SUPPLIER")
public class Supplier implements Serializable, BusinessObject {

	private Long id; // id

	private String address; // address

	private String contactPerson; // contactPerson

	private String contactTel; // contactTel

	private String legalPerson; // legalPerson

	private String legalPersonTel; // legalPersonTel

	private String memo; // memo

	private String name; // name

	private Long operateTime; // operateTime

	private String operator; // operator

	private String removed; // removed

	private String type; // type

	private String zip; // zip

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="supplierAAA")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS", nullable = true, length = 200)
	public String getAddress() {
		return address;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "CONTACT_PERSON", nullable = true, length = 100)
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	@Column(name = "CONTACT_TEL", nullable = true, length = 100)
	public String getContactTel() {
		return contactTel;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "LEGAL_PERSON", nullable = true, length = 200)
	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}

	@Column(name = "LEGAL_PERSON_TEL", nullable = true, length = 50)
	public String getLegalPersonTel() {
		return legalPersonTel;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MEMO", nullable = true, length = 2000)
	public String getMemo() {
		return memo;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 22)
	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 200)
	public String getOperator() {
		return operator;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE", nullable = true, length = 100)
	public String getType() {
		return type;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "ZIP", nullable = true, length = 100)
	public String getZip() {
		return zip;
	}

}
