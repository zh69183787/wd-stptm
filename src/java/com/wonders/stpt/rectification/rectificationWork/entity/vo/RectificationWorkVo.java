package com.wonders.stpt.rectification.rectificationWork.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

public class RectificationWorkVo implements ValueObject {

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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
	public String getDangerNumber() {
		return dangerNumber;
	}
	public void setDangerNumber(String dangerNumber) {
		this.dangerNumber = dangerNumber;
	}
	public String getChildSYS() {
		return childSYS;
	}
	public void setChildSYS(String childSYS) {
		this.childSYS = childSYS;
	}
	public String getRiskPoint() {
		return riskPoint;
	}
	public void setRiskPoint(String riskPoint) {
		this.riskPoint = riskPoint;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getExpertAdvice() {
		return expertAdvice;
	}
	public void setExpertAdvice(String expertAdvice) {
		this.expertAdvice = expertAdvice;
	}
	public String getPhenomenon() {
		return phenomenon;
	}
	public void setPhenomenon(String phenomenon) {
		this.phenomenon = phenomenon;
	}
	public String getRectificationMethod() {
		return rectificationMethod;
	}
	public void setRectificationMethod(String rectificationMethod) {
		this.rectificationMethod = rectificationMethod;
	}
	public String getDeptWork() {
		return deptWork;
	}
	public void setDeptWork(String deptWork) {
		this.deptWork = deptWork;
	}
	public String getLeaderShip() {
		return leaderShip;
	}
	public void setLeaderShip(String leaderShip) {
		this.leaderShip = leaderShip;
	}
	public String getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}
	public String getCwip() {
		return cwip;
	}
	public void setCwip(String cwip) {
		this.cwip = cwip;
	}
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	public String getLeadDept() {
		return leadDept;
	}
	public void setLeadDept(String leadDept) {
		this.leadDept = leadDept;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	
}
