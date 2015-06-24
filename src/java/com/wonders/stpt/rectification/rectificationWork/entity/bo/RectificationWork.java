package com.wonders.stpt.rectification.rectificationWork.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;
@Entity
@Table(name="RECTIFICATION_WORK")
public class RectificationWork implements Serializable,BusinessObject {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 用户（操作人）
	 */
	private String userName;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 所属部门
	 */
	private String deptName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 删除标志0为显示1为删除
	 */
	private String removed;
	/**
	 * 整改线路
	 */
	private String lines;
	/**
	 * 风险编号
	 */
	private String dangerNumber;
	/**
	 * 子系统
	 */
	private String childSYS;
	/**
	 * 风险点
	 */
	private String riskPoint;
	/**
	 * 等级
	 */
	private String levels;
	/**
	 * 专家建议
	 */
	private String expertAdvice;
	/**
	 * 现象说明
	 */
	private String phenomenon;
	/**
	 * 整改措施
	 */
	private String rectificationMethod;
	/**
	 * 责任单位/部门
	 */
	private String deptWork;
	/**
	 * 分管领导
	 */
	private String leaderShip;
	/**
	 * 节点目标
	 */
	private String targetNode;
	/**
	 * 进展情况
	 */
	private String cwip;
	/**
	 * 消项情况
	 */
	private String workState;
	/**
	 * 牵头部门
	 */
	private String leadDept;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 工号
	 */
    private String gonghao;
    /**
     * 部门id
     */
    private String dept_id;
    private String inputDate;
    
	@Id
	@GenericGenerator(name="system-uuid",strategy="uuid.hex")
	@GeneratedValue(generator="system-uuid")
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="USERNAME",nullable=true,length=100)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="OPERATION_TIME",nullable=true,length=10)
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	@Column(name="DEPT_NAME",nullable=true,length=50)
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Column(name="CREATE_TIME",nullable=true,length=10)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="REMOVED",nullable=true,length=1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Column(name="LINES",nullable=true,length=100)
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	@Column(name="DANGER_NUMBER",nullable=true,length=50)
	public String getDangerNumber() {
		return dangerNumber;
	}
	public void setDangerNumber(String dangerNumber) {
		this.dangerNumber = dangerNumber;
	}
	@Column(name="CHILD_SYS",nullable=true,length=100)
	public String getChildSYS() {
		return childSYS;
	}
	public void setChildSYS(String childSYS) {
		this.childSYS = childSYS;
	}
	@Column(name="RISK_POINT",nullable=true,length=1000)
	public String getRiskPoint() {
		return riskPoint;
	}
	public void setRiskPoint(String riskPoint) {
		this.riskPoint = riskPoint;
	}
	@Column(name="LEVELS",nullable=true,length=10)
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	@Column(name="EXPERT_ADVICE",nullable=true,length=4000)
	public String getExpertAdvice() {
		return expertAdvice;
	}
	public void setExpertAdvice(String expertAdvice) {
		this.expertAdvice = expertAdvice;
	}
	@Column(name="PHENOMENON",nullable=true,length=4000)
	public String getPhenomenon() {
		return phenomenon;
	}
	public void setPhenomenon(String phenomenon) {
		this.phenomenon = phenomenon;
	}
	@Column(name="RECTIFICATION_METHOD",nullable=true,length=4000)
	public String getRectificationMethod() {
		return rectificationMethod;
	}
	public void setRectificationMethod(String rectificationMethod) {
		this.rectificationMethod = rectificationMethod;
	}
	@Column(name="DEPT_WORK",nullable=true,length=100)
	public String getDeptWork() {
		return deptWork;
	}
	public void setDeptWork(String deptWork) {
		this.deptWork = deptWork;
	}
	@Column(name="LEADER_SHIP",nullable=true,length=100)
	public String getLeaderShip() {
		return leaderShip;
	}
	public void setLeaderShip(String leaderShip) {
		this.leaderShip = leaderShip;
	}
	@Column(name="TARGET_NODE",nullable=true,length=100)
	public String getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}
	@Column(name="CWIP",nullable=true,length=100)
	public String getCwip() {
		return cwip;
	}
	public void setCwip(String cwip) {
		this.cwip = cwip;
	}
	@Column(name="WORK_STATE",nullable=true,length=100)
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	@Column(name="LEAD_DEPT",nullable=true,length=100)
	public String getLeadDept() {
		return leadDept;
	}
	public void setLeadDept(String leadDept) {
		this.leadDept = leadDept;
	}
	@Column(name="REMARK",nullable=true,length=4000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="GONGHAO", nullable=true,length=100)
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	@Column(name="DEPT_ID",nullable=true,length=100)
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	@Column(name="INPUT_DATE",nullable=true,length=100)
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	
}
