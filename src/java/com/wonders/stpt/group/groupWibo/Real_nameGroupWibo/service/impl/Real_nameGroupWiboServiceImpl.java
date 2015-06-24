package com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.service.impl;

import java.util.Map;

import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.dao.Real_nameGroupWiboDao;
import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.entity.bo.Real_nameGroupWibo;
import com.wonders.stpt.group.groupWibo.Real_nameGroupWibo.service.Real_nameGroupWiboService;
import com.wondersgroup.framework.core.bo.Page;

public class Real_nameGroupWiboServiceImpl implements Real_nameGroupWiboService {

	private Real_nameGroupWiboDao real_nameGroupWiboDao;
	
	public void setReal_nameGroupWiboDao(Real_nameGroupWiboDao real_nameGroupWiboDao) {
		this.real_nameGroupWiboDao = real_nameGroupWiboDao;
	}

	@Override
	public void addReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo) {
		// TODO Auto-generated method stub
		real_nameGroupWiboDao.save(real_nameGroupWibo);
	}

	@Override
	public void deleteReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo) {
		// TODO Auto-generated method stub
		real_nameGroupWiboDao.delete(real_nameGroupWibo);
	}

	@Override
	public Real_nameGroupWibo findReal_nameGroupWibo(String id) {
		// TODO Auto-generated method stub
		return real_nameGroupWiboDao.load(id);
	}

	@Override
	public void updateReal_nameGroupWibo(Real_nameGroupWibo real_nameGroupWibo) {
		// TODO Auto-generated method stub
		real_nameGroupWiboDao.update(real_nameGroupWibo);
	}

	@Override
	public Page findReal_nameGroupWiboByPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return real_nameGroupWiboDao.findAllWithPage(pageNo, pageSize);
	}

	@Override
	public Page findReal_nameGroupWiboByPage(Map<String, Object> filter,
			int pageNo, int pageSize, String type) {
		// TODO Auto-generated method stub
		return real_nameGroupWiboDao.findReal_nameGroupWiboByPage(filter, pageNo, pageSize, type);
	}

}
