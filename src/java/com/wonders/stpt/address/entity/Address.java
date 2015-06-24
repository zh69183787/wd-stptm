package com.wonders.stpt.address.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_user_register")
public class Address {
private String name;//姓名
private String email;//邮件
private String  mobile;//手机
private String office_phone;//单位电话
 private String fax;//单位传真
 private String deptName;//单位名称
private String login_Name;//登录名
 
public String getLogin_Name() {
	return login_Name;
}
public void setLogin_Name(String loginName) {
	login_Name = loginName;
}
@Column(name = "NAME", nullable = true, length = 20)
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Column(name = "EMAIL1", nullable = true, length = 20)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Column(name = "MOBLIE1", nullable = true, length = 20)
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
@Column(name = "OFFICE_PHONE", nullable = true, length = 20)
public String getOffice_phone() {
	return office_phone;
}
public void setOffice_phone(String officePhone) {
	office_phone = officePhone;
}
@Column(name = "FAX", nullable = true, length = 20)
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
@Column(name = "DEPT_NAME", nullable = true, length = 20)
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}

}
