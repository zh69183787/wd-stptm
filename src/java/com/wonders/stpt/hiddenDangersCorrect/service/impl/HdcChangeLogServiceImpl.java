package com.wonders.stpt.hiddenDangersCorrect.service.impl;

import com.wonders.stpt.hiddenDangersCorrect.dao.HdcChangeLogDao;
import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HdcChangeLog;
import com.wonders.stpt.hiddenDangersCorrect.service.HdcChangeLogService;
import com.wondersgroup.framework.core.bo.Page;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/23
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public class HdcChangeLogServiceImpl implements HdcChangeLogService{
    private HdcChangeLogDao hdcChangeLogDao;

    public HdcChangeLogDao getHdcChangeLogDao() {
        return hdcChangeLogDao;
    }

    public void setHdcChangeLogDao(HdcChangeLogDao hdcChangeLogDao) {
        this.hdcChangeLogDao = hdcChangeLogDao;
    }

    /**
     * 存储log记录
     * @param bo
     */
    @Override
    public void addLog(HdcChangeLog bo) {
        this.hdcChangeLogDao.save(bo);
    }

    public Page findHdcChangeLogByPage(Map<String, Object> filter,
                                       int pageNo, int pageSize,String operateTypeId){
        return this.hdcChangeLogDao.findHdcChangeLogByPage(filter,pageNo,pageSize,operateTypeId);
    }

    public HdcChangeLog findHdcChangeLogById(String id){
        return this.hdcChangeLogDao.load(id);
    }
}
