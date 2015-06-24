package com.wonders.stpt.rectification.rectificationRecord.entity.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * 整改记录实体
 * @author Administrator
 *
 */
@Entity
@Table(name="RECTIFICATION_RECORD")
public class RectificationRecord implements Serializable, BusinessObject {

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
	@Column(name="USER_NAME",nullable=true,length=100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="OPERATETIME",nullable=true,length=10)
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	@Column(name="CREATETIME",nullable=true,length=10)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@Column(name="REMOVED",nullable=true,length=1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Column(name="WID",nullable=true,length=32)
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	@Column(name="RECTIFICATION_METHOD",nullable=true,length=4000)
	public String getRectificationMethod() {
		return rectificationMethod;
	}
	public void setRectificationMethod(String rectificationMethod) {
		this.rectificationMethod = rectificationMethod;
	}
	@Column(name="TARGET_NODE",nullable=true,length=4000)
	public String getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}
	@Column(name="CWIP",nullable=true,length=4000)
	public String getCwip() {
		return cwip;
	}
	public void setCwip(String cwip) {
		this.cwip = cwip;
	}
}
