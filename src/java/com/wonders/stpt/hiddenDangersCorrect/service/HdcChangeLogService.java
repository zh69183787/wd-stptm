package com.wonders.stpt.hiddenDangersCorrect.service;

import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HdcChangeLog;
import com.wondersgroup.framework.core.bo.Page;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/23
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
public interface HdcChangeLogService {
    public void addLog(HdcChangeLog bo);
    public Page findHdcChangeLogByPage(Map<String, Object> filter,
                                       int pageNo, int pageSize,String operateTypeId);
    public HdcChangeLog findHdcChangeLogById(String id);
}
