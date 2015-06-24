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

package com.wonders.stpt.user.entity.bo;

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
 * User实体定义
 * 
 * @author mengjie
 * @version $Revision$
 * @date 2012-11-20
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_USER")
public class User implements Serializable, BusinessObject {

	private Long id; // id

	private String address; // address

	private String birthday; // birthday

	private String birthplace; // birthplace

	private String caddress; // caddress

	private String company; // company

	private String cphone; // cphone

	private String cpostcode; // cpostcode

	private String degree; // degree

	private String dept; // dept

	private String flag; // flag

	private String grade; // grade

	private String household; // household

	private String idcard; // idcard

	private String loginName; // loginName

	private String major; // major

	private String mobile1; // mobile1

	private String mobile2; // mobile2

	private String name; // name

	private String nation; // nation

	private String nickName; // nickName

	private String operateTime; // operateTime

	private String operator; // operator

	private String password; // password

	private String phone; // phone

	private String political; // political

	private String postcode; // postcode

	private String rank; // rank

	private String remark; // remark

	private String removed; // removed

	private String retire; // retire

	private String sex; // sex

	private String title; // title

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "BIRTHDAY", nullable = true, length = 50)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name = "BIRTHPLACE", nullable = true, length = 50)
	public String getBirthplace() {
		return birthplace;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	@Column(name = "CADDRESS", nullable = true, length = 200)
	public String getCaddress() {
		return caddress;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "COMPANY", nullable = true, length = 200)
	public String getCompany() {
		return company;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	@Column(name = "CPHONE", nullable = true, length = 50)
	public String getCphone() {
		return cphone;
	}

	public void setCpostcode(String cpostcode) {
		this.cpostcode = cpostcode;
	}

	@Column(name = "CPOSTCODE", nullable = true, length = 50)
	public String getCpostcode() {
		return cpostcode;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "DEGREE", nullable = true, length = 50)
	public String getDegree() {
		return degree;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "DEPT", nullable = true, length = 200)
	public String getDept() {
		return dept;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 1)
	public String getFlag() {
		return flag;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "GRADE", nullable = true, length = 200)
	public String getGrade() {
		return grade;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	@Column(name = "HOUSEHOLD", nullable = true, length = 50)
	public String getHousehold() {
		return household;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "IDCARD", nullable = true, length = 50)
	public String getIdcard() {
		return idcard;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "LOGIN_NAME", nullable = true, length = 100)
	public String getLoginName() {
		return loginName;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "MAJOR", nullable = true, length = 200)
	public String getMajor() {
		return major;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	@Column(name = "MOBILE1", nullable = true, length = 50)
	public String getMobile1() {
		return mobile1;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	@Column(name = "MOBILE2", nullable = true, length = 50)
	public String getMobile2() {
		return mobile2;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", nullable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "NATION", nullable = true, length = 50)
	public String getNation() {
		return nation;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "NICK_NAME", nullable = true, length = 100)
	public String getNickName() {
		return nickName;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATOR", nullable = true, length = 50)
	public String getOperator() {
		return operator;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PASSWORD", nullable = true, length = 100)
	public String getPassword() {
		return password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "PHONE", nullable = true, length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	@Column(name = "POLITICAL", nullable = true, length = 50)
	public String getPolitical() {
		return political;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "POSTCODE", nullable = true, length = 50)
	public String getPostcode() {
		return postcode;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Column(name = "RANK", nullable = true, length = 50)
	public String getRank() {
		return rank;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", nullable = true, length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setRetire(String retire) {
		this.retire = retire;
	}

	@Column(name = "RETIRE", nullable = true, length = 50)
	public String getRetire() {
		return retire;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "SEX", nullable = true, length = 1)
	public String getSex() {
		return sex;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

}
