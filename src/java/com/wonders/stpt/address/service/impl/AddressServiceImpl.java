package com.wonders.stpt.address.service.impl;

import java.util.List;

import com.wonders.stpt.address.dao.AddressDao;
import com.wonders.stpt.address.entity.*;
import com.wonders.stpt.address.service.AddressService;

public class AddressServiceImpl implements AddressService {
	private AddressDao addressDao;

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	// 列表视图
	public List GroupAddressByPage(Address bean, int pageNo, int pageSize) {
		return addressDao.GroupAddressByPage(bean, pageNo, pageSize);
	}

	public int recordCount(Address bean) {
		return addressDao.recordCount(bean);
	}

	// 详细信息
	public List PersonAddress(String login) {
		return addressDao.PersonAddress(login);
	}

	// 分类视图
	@Override
	public List SortViewByCode1() {

		return addressDao.SortViewByCode1();
	}

	@Override
	public List SortViewByCode2() {
		return addressDao.SortViewByCode2();
	}

	@Override
	public List SortViewByCode3() {

		return addressDao.SortViewByCode3();
	}

	/**
	 * 個人通訊錄
	 */

	@Override
	public List GroupAddressByPagePerson(PersonAddress bean, int pageNo,
			int pageSize) {

		return addressDao.GroupAddressByPagePerson(bean, pageNo, pageSize);
	}

	@Override
	public int recordCountPerson(PersonAddress bean) {
		return addressDao.recordCountPerson(bean);
	}

	@Override
	public void addPersonAddress(PersonAddress bean) {
		addressDao.addPersonAddress(bean);
	}

	@Override
	public void delPersonAddress(String id) {
		addressDao.delPersonAddress(id);
	}

	@Override
	public List findPersonAddress(String id) {
		return addressDao.findPersonAddress(id);
	}

	@Override
	public void updatePersonAddress(PersonAddress bean) {
		addressDao.updatePersonAddress(bean);
	}

	@Override
	public List SortView(String sql, int pageNo, int pageSize) {
		return addressDao.SortView(sql, pageNo, pageSize);
	}

	@Override
	public int recordCount(String sql) {
		return addressDao.recordCount(sql);
	}
}
