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

package com.wonders.stpt.accidentCase.TAttach.entity.vo;

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * TAttach实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-2-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TAttachVO implements ValueObject {

	private Long id;
	private String appname;
	private String fileextname;
	private String filename;
	private Long filesize;
	private String filetype;
	private String groupid;
	private String groupname;
	private String keyWord;
//	private String matchDegree;
	private String memo;
	private Long operateTime;
	private String operator;
	private String path;
	private Long removed;
	private String savefilename;
	private String status;
	private String uploaddate;
	private String uploader;
	private String uploaderLoginName;
	private String version;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppname() {
		return appname;
	}

	public void setFileextname(String fileextname) {
		this.fileextname = fileextname;
	}

	public String getFileextname() {
		return fileextname;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWord() {
		return keyWord;
	}

	/*public void setMatchDegree(String matchDegree) {
		this.matchDegree = matchDegree;
	}

	public String getMatchDegree() {
		return matchDegree;
	}*/

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return memo;
	}

	public void setOperateTime(Long operateTime) {
		this.operateTime = operateTime;
	}

	public Long getOperateTime() {
		return operateTime;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setRemoved(Long removed) {
		this.removed = removed;
	}

	public Long getRemoved() {
		return removed;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploaderLoginName(String uploaderLoginName) {
		this.uploaderLoginName = uploaderLoginName;
	}

	public String getUploaderLoginName() {
		return uploaderLoginName;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

}
