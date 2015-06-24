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

package com.wonders.stpt.sthr.CsUser.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * CsUser实体定义
 * 
 * @author 2055
 * @version $Revision$
 * @date 2012-3-14
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "CS_USER")
public class CsUser implements Serializable, BusinessObject {

	private Long accounttype; // accounttype

	private String address; // address

	private Long authenticType; // authenticType

	private String certificate; // certificate

	private String deptCode; // deptCode

	private String email; // email

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private String ext4; // ext4

	private String ext5; // ext5

	private String ext6; // ext6

	private String ext7; // ext7

	private String ext8; // ext8

	private String fax; // fax

	private String homePhone; // homePhone

	private Long id; // id

	private String loginName; // loginName

	private String mobile1; // mobile1

	private String mobile2; // mobile2

	private String name; // name

	private String officePhone; // officePhone

	private Long operateTime; // operateTime

	private String operator; // operator

	private String password; // password

	private Long removed; // removed

	private String sex; // sex

	private Long status; // status

	private Long usertype; // usertype

	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}

	@Column(name = "ACCOUNTTYPE", nullable = true, length = 10)
	public Long getAccounttype() {
		return accounttype;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ADDRESS", nullable = true, length = 510)
	public String getAddress() {
		return address;
	}

	public void setAuthenticType(Long authenticType) {
		this.authenticType = authenticType;
	}

	@Column(name = "AUTHENTIC_TYPE", nullable = true, length = 10)
	public Long getAuthenticType() {
		return authenticType;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	@Column(name = "CERTIFICATE", nullable = true, length = 510)
	public String getCertificate() {
		return certificate;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	@Column(name = "DEPT_CODE", nullable = true, length = 255)
	public String getDeptCode() {
		return deptCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL", nullable = true, length = 200)
	public String getEmail() {
		return email;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT1", nullable = true, length = 510)
	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT2", nullable = true, length = 510)
	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT3", nullable = true, length = 510)
	public String getExt3() {
		return ext3;
	}

	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}

	@Column(name = "EXT4", nullable = true, length = 510)
	public String getExt4() {
		return ext4;
	}

	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}

	@Column(name = "EXT5", nullable = true, length = 510)
	public String getExt5() {
		return ext5;
	}

	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}

	@Column(name = "EXT6", nullable = true, length = 510)
	public String getExt6() {
		return ext6;
	}

	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}

	@Column(name = "EXT7", nullable = true, length = 510)
	public String getExt7() {
		return ext7;
	}

	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}

	@Column(name = "EXT8", nullable = true, length = 510)
	public String getExt8() {
		return ext8;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "FAX", nullable = true, length = 100)
	public String getFax() {
		return fax;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "HOME_PHONE", nullable = true, length = 100)
	public String getHomePhone() {
		return homePhone;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "LOGIN_NAME", nullable = false, length = 200)
	public String getLoginName() {
		return loginName;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	@Column(name = "MOBILE1", nullable = true, length = 100)
	public String getMobile1() {
		return mobile1;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	@Column(name = "MOBILE2", nullable = true, length = 100)
	public String getMobile2() {
		return mobile2;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "OFFICE_PHONE", nullable = true, length = 100)
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 19)
	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 100)
	public String getOperator() {
		return operator;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD", nullable = false, length = 200)
	public String getPassword() {
		return password;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public Long getRemoved() {
		return removed;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "SEX", nullable = true, length = 510)
	public String getSex() {
		return sex;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "STATUS", nullable = true, length = 19)
	public Long getStatus() {
		return status;
	}

	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}

	@Column(name = "USERTYPE", nullable = true, length = 10)
	public Long getUsertype() {
		return usertype;
	}

}
