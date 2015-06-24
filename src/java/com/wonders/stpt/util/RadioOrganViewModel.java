package com.wonders.stpt.util;

import com.wondersgroup.framework.core5.model.vo.BaseTree;

public class RadioOrganViewModel extends BaseTree {
	/**
	 * 主键id
	 */
	private long id;
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
    /**
     * 
     */
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		super.setText(name);
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
