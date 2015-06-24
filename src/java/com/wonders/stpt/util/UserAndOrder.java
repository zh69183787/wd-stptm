package com.wonders.stpt.util;

import com.wondersgroup.framework.security.bo.SecurityUser;

public class UserAndOrder {
	private String orders="";
	private SecurityUser user = null;
	
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public SecurityUser getUser() {
		return user;
	}
	public void setUser(SecurityUser user) {
		this.user = user;
	}
	
}
