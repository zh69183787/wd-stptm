package com.wonders.stpt.rectification.rectificationRecord.entity.vo;

import java.util.Date;

import com.wondersgroup.framework.core5.model.vo.ValueObject;
/**
 * 整改记录vo
 * @author Administrator
 *
 */
public class RectificationRecordvo implements ValueObject {

	/**
	 * 整改记录主键
	 */
	private String id;
	/**
	 * 操作人
	 */
	private String username;
	/**
	 * 操作时间
	 */
	private Date operatetime; 
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 是否删除
	 */
	private String removed;
	/**
	 * 整改工作关联外键
	 */
	private String wid;
	/**
	 *  整改措施
	 */
	private String rectificationMethod;
	/**
	 * 节点目标 
	 */
	private String targetNode;
	/**
	 * 进展情况
	 */
	private String cwip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getRectificationMethod() {
		return rectificationMethod;
	}
	public void setRectificationMethod(String rectificationMethod) {
		this.rectificationMethod = rectificationMethod;
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
	
}
