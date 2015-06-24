package com.wonders.stpt.hiddenDangersCorrect.dao;

import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HdcChangeLog;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/23
 * Time: 14:42
 * To change this template use File | Settings | File Templates.
 */
public interface HdcChangeLogDao extends
        AbstractHibernateDao<HdcChangeLog> {
    public Page findHdcChangeLogByPage(Map<String, Object> filter,
                                       int pageNo, int pageSize,String operateTypeId);
}
