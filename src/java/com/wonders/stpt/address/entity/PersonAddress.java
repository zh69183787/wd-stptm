package com.wonders.stpt.address.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_personal_address")
public class PersonAddress {

	private String id;// Id
	private String name;// 姓名
	private String email;// 邮件
	private String mobil;// 手机
	private String unitTel;// 单位电话
	private String unitFax;// 单位传真
	private String unitName;// 单位名称
	private String operator; // 操作者编号
	private String removed;
	private String lastDate; // 最后操作时间

	public PersonAddress() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = true, length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", nullable = true, length = 30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBLIE", nullable = true, length = 30)
	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	@Column(name = "UNITFAX", nullable = true, length = 30)
	public String getUnitFax() {
		return unitFax;
	}

	public void setUnitFax(String unitFax) {
		this.unitFax = unitFax;
	}

	@Column(name = "OPERATOR", nullable = true, length = 30)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "UNITNAME", nullable = true, length = 30)
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Column(name = "UNITTEL", nullable = true, length = 30)
	public String getUnitTel() {
		return unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

	@Column(name = "REMOVED", nullable = true, length = 30)
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "LASTDATE", nullable = true, length = 30)
	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

}
