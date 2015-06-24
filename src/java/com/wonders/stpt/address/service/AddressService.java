package com.wonders.stpt.address.service;

import java.util.List;

import com.wonders.stpt.address.entity.*;

public interface AddressService {
	// 列表视图
	public List GroupAddressByPage(Address bean, int pageNo, int pageSize);

	public int recordCount(Address bean);

	public List PersonAddress(String login);

	// 分类视图
	public List SortViewByCode1();

	public List SortViewByCode2();

	public List SortViewByCode3();

	/**
	 * 个人通讯录
	 */
	// 添加
	public void addPersonAddress(PersonAddress bean);

	// 删除
	public void delPersonAddress(String id);

	// 通过id 查询
	public List findPersonAddress(String id);

	// 修改
	public void updatePersonAddress(PersonAddress bean);

	public List GroupAddressByPagePerson(PersonAddress bean, int pageNo,
			int pageSize);

	public int recordCountPerson(PersonAddress bean);

	// 分类更多
	public List SortView(String sql, int pageNo, int pageSize);

	public int recordCount(String sql);
}
