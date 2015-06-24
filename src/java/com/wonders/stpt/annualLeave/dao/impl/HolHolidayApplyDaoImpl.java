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

package com.wonders.stpt.annualLeave.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wonders.stpt.annualLeave.dao.HolHolidayApplyDao;
import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.HolHolidayApply;
import com.wonders.stpt.annualLeave.model.data.entity.bo.OldCsUser;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHoliday;
import com.wonders.stpt.annualLeave.model.data.entity.bo.THolHolidayapp;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HolHolidayApplyʵ�嶨��
 *
 * @author ycl
 * @author modify by $Author$
 * @version $Revision$
 * @date 2014-7-16
 * @since 1.0
 */

public class HolHolidayApplyDaoImpl extends
        AbstractHibernateDaoImpl<HolHolidayApply> implements HolHolidayApplyDao {

    private SessionFactory sessionFactory31old;
    private SessionFactory sessionFactory31new;

    public SessionFactory getSessionFactory31old() {
        return sessionFactory31old;
    }

    public void setSessionFactory31old(SessionFactory sessionFactory31old) {
        this.sessionFactory31old = sessionFactory31old;
    }

    public SessionFactory getSessionFactory31new() {
        return sessionFactory31new;
    }

    public void setSessionFactory31new(SessionFactory sessionFactory31new) {
        this.sessionFactory31new = sessionFactory31new;
    }

    public Page findHolHolidayApplyByPage(Map<String, Object> filter,
                                          int pageNo, int pageSize) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t from HolHolidayApply t ";
        String countHql = "select count(*) from HolHolidayApply t ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            filterPart += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                if (filterCounter > 0) {
                    filterPart += " and ";
                }
                String paramName = i.next();

                if ("startDate".equals(paramName)) {
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                } else if ("holDays".equals(paramName)) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if ("applyName".equals(paramName)) {
                    filterPart += "t.applyUser in (select cs.id from CsUser cs where cs.name like '%" + filter.get(paramName) + "%')";
                } else {
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }
                filterCounter++;
            }
        }
        filterPart += " order by t.applyDate DESC";
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
                countHql + filterPart);
    }

    @Override
    public void saveHolHolidayApplyAndUpdateHolHoliday(HolHolidayApply holHolidayApply, List<HolHoliday> holHolidayList) {
        getHibernateTemplate().saveOrUpdate(holHolidayApply);
        getHibernateTemplate().saveOrUpdateAll(holHolidayList);
    }

    @Override
    public void updateHolHolidayApply(HolHolidayApply holHolidayApply) {
        getHibernateTemplate().update(holHolidayApply);
    }

    @Override
    public CsUser findCsUserById(long id) {
        return (CsUser) getHibernateTemplate().get(CsUser.class, id);
    }

    @Override
    public List<HolHolidayApply> findByApplyUserAndHolState(long applyUser, long[] holState) {
        String hql = "from HolHolidayApply t where t.applyUser=?";
        String holStateFilter = "";
        if (holState != null && holState.length > 0) {
            for (int i = 0, length = holState.length; i < length; i++) {
                if (i != length - 1) {
                    holStateFilter += holState[i] + ",";
                } else {
                    holStateFilter += holState[i];
                }
            }
            hql += " and t.holState in (" + holStateFilter + ")";
        }
        hql += " order by t.applyDate DESC";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setLong(0, applyUser);
        return query.list();
    }


    //begin
    @Override
    public List<THolHoliday> findAllTholidayAfterYear(String year) {
        String hql = "from THolHoliday t where t.holYear >=? and t.removed='0'";
        List<THolHoliday> list;
        Session session = null;
        try {
            session = sessionFactory31old.openSession();
            Query query = session.createQuery(hql);
            query.setString(0, year);
            list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public OldCsUser findOldCsUserById(String id) {
        String hql = "from OldCsUser t where t.id=? and t.removed='0'";
        Session session = null;
        try {
            session = sessionFactory31old.openSession();
            Query query = session.createQuery(hql);
            query.setString(0, id);
            List<OldCsUser> list = query.list();
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public List<CsUser> findNewCsUserByLoginName(String loginName) {
        String hql = "from CsUser t where t.loginName like '%" + loginName + "%' and t.removed='0'";
        Session session = null;
        try {
            session = sessionFactory31new.openSession();
            Query query = session.createQuery(hql);
            return query.list();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public List<THolHolidayapp> findAllTholidayApp() {
        String hql = "from THolHolidayapp t where t.removed='0' and t.applyUser is not null order by t.id ASC";
        Session session = null;
        try {
            session = sessionFactory31old.openSession();
            Query query = session.createQuery(hql);
            List<THolHolidayapp> list = query.list();
            return list;
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public String findLoginNameByIdAndDeptId(String id, String deptId) {
        String sql = "select t.login_name loginname from v_userdep t where t.removed='0' and t.id='" + id + "' and t.parent_node_id='" + deptId + "'";
        Session session = null;
        try {
            session = sessionFactory31old.openSession();
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.addScalar("loginname", Hibernate.STRING);
            List<String> list = sqlQuery.list();
            if (list != null && list.size() == 1) {
                return list.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public String findNewCsUserIdByLoginNameAndDeptId(String loginName, String deptId) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(deptId)) {
            return null;
        }
        String sql = "select t.id id from v_userdep t where t.login_name like '%" + loginName + "%' and t.dept_id='" + deptId + "'";
        Session session = null;
        try {
            session = sessionFactory31new.openSession();
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.addScalar("id", Hibernate.STRING);
            List<String> list = sqlQuery.list();
            if (list != null && list.size() == 1) {
                return list.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public void saveAllHolidayApply(List<HolHolidayApply> list) {
        if (list != null && list.size() > 0) {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            for (int i = 0; i < list.size(); i++) {
                session.save(list.get(i));
                if (i / 20 == 0) {
                    session.flush();
                }
            }
            session.flush();
        }
    }

    @Override
    public List<CsUser> findCsUserByIds(List<Long> userId) {
        String hql = "from CsUser t where t.id in (:userId) and t.removed='0'";
        Session session = null;
        session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql).setParameterList("userId", userId);
        return query.list();
    }
}
