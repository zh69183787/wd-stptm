package com.wonders.stpt.util;

import com.wondersgroup.framework.core5.model.vo.CheckTree;

public class UserViewModel extends CheckTree {
	/**
	 * 主键id
	 */
	private long id;

	/**
	 * 名称
	 */
	private String name;
	private long orders;
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

	public long getOrders() {
		return orders;
	}

	public void setOrders(long orders) {
		this.orders = orders;
	}

}
