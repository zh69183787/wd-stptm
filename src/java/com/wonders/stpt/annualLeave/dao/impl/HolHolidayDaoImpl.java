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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.stpt.annualLeave.dao.HolHolidayDao;
import com.wonders.stpt.annualLeave.model.HolHoliday;
import com.wonders.stpt.annualLeave.model.VUserdep;
import com.wonders.stpt.csUser.entity.bo.CsUser;
import com.wonders.stpt.user.entity.bo.User;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * HolHolidayʵ�嶨��
 *
 * @author ycl
 * @version $Revision$
 * @date 2014-7-16
 * @author modify by $Author$
 * @since 1.0
 */

public class HolHolidayDaoImpl extends AbstractHibernateDaoImpl<HolHoliday>
        implements HolHolidayDao {

    @Override
    public Page find(Map filter, int pageNo, int pageSize) {
        StringBuffer stringBuffer = new StringBuffer("SELECT * FROM (SELECT T.*," +
                "(SELECT DISTINCT NAME" +
                "          FROM V_USERDEP V" +
                "         WHERE T.HOL_ID = SUBSTR(V.LOGIN_NAME, 0, 12)) NAME," +
                "       (SELECT DISTINCT DEPT_NAME" +
                "          FROM V_USERDEP V" +
                "         WHERE V.DEPT_ID = T.DEPT_ID) DEPT_NAME" +
                "  FROM (SELECT TO_CHAR(sysdate, 'yyyy') HOL_YEAR," +
                "               SUM(HOL_DAYS) HOL_DAYS," +
                "               SUM(HOL_DAYS_LEFT) HOL_DAYS_LEFT," +
                "               HOL_ID," +
                "               MIN(DEPT_ID) DEPT_ID" +
                "          FROM (SELECT DISTINCT HOL_YEAR," +
                "                                HOL_DAYS," +
                "                                HOL_DAYS_LEFT," +
                "                                SUBSTR(V.LOGIN_NAME, 0, 12) HOL_ID" +
                "                  FROM TA_HOL_HOLIDAY, V_USERDEP V" +
                "                 WHERE V.ID = HOL_ID) T," +
                "               V_USERDEP V" +
                "         WHERE SUBSTR(V.LOGIN_NAME, 0, 12) = T.HOL_ID" +
                "           AND HOL_YEAR <= TO_CHAR(sysdate, 'yyyy')" +
                "           AND HOL_YEAR >= (TO_CHAR(sysdate, 'yyyy') - 1)" +
                "         GROUP BY T.HOL_ID) T) WHERE 1=1 ");


        if (filter == null)
            filter = new HashMap<String, Object>();

        if (filter.containsKey("deptName") && StringUtils.isNotBlank((String) filter.get("deptName"))) {
            stringBuffer.append(" AND DEPT_NAME LIKE :deptName");
            filter.put("deptName", "%" + (String) filter.get("deptName") + "%");
        }  if (filter.containsKey("holYear") && StringUtils.isNotBlank((String) filter.get("holYear"))) {
            stringBuffer.append(" AND HOL_YEAR = :holYear");
        }  if (filter.containsKey("holLoginName") && StringUtils.isNotBlank((String) filter.get("holLoginName"))) {
            String loginName = (String) filter.get("holLoginName");
            stringBuffer.append(" AND HOL_ID LIKE :holLoginName");
            if (loginName.length() > 12)
                loginName = loginName.substring(0, 12);
            filter.put("holLoginName", "%" + loginName + "%");
        }  if (filter.containsKey("holName") && StringUtils.isNotBlank((String) filter.get("holName"))) {
            stringBuffer.append(" AND NAME LIKE :holName");
            filter.put("holName", "%" + (String) filter.get("holName") + "%");
        }
        stringBuffer.append(" ORDER BY HOL_ID ASC");


        List<Object[]> list = getSession().createSQLQuery(stringBuffer.toString()).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).setProperties(filter).list();
        Integer total = ((BigDecimal)getSession().createSQLQuery("SELECT COUNT(1) FROM ("+stringBuffer.toString()+")").setProperties(filter).uniqueResult()).intValue();
ArrayList<HolHoliday> reulst = new ArrayList<HolHoliday>();
        for (Object[] o : list) {
            HolHoliday holHoliday = new HolHoliday();
            holHoliday.setHolYear((String)o[0]);
            if(o[1]!=null)
            holHoliday.setHolDays(((BigDecimal)o[1]).longValue());
            if(o[2]!=null)
            holHoliday.setHolDaysLeft(((BigDecimal)o[2]).longValue());
            holHoliday.setLoginName((String) o[3]);
            if(o[4]!=null)
            holHoliday.setDeptId(((BigDecimal)o[4]).longValue()+"");
            holHoliday.setName((String) o[5]);
            holHoliday.setDeptName((String) o[6]);
            reulst.add(holHoliday);
        }
        Page page = new Page((pageNo-1)*pageSize+1,pageSize,total,pageSize,reulst);
        page.setCurrentPageNo(pageNo);
        return page;
    }

    public Page findHolHolidayByPage(Map<String, Object> filter, int pageNo,
                                     int pageSize) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t from HolHoliday t ";
        String countHql = "select count(*) from HolHoliday t ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            filterPart += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
                if (filterCounter > 0) {
                    filterPart += " and ";
                }
                String paramName = i.next();
                if("deptName".equals(paramName)){
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
                }else if("holName".equals(paramName)){
                    filterPart +="t.holId in (select cs.id from CsUser cs where cs.name like '%"+filter.get(paramName)+"%')";
                }else if("holLoginName".equals(paramName)){
                    filterPart +="t.holId in (select cs.id from CsUser cs where cs.loginName like '%"+filter.get(paramName)+"%')";
                }else if("deptName".equals(paramName)){
                    filterPart +="t.holId in (select v.id from VUserdep v where v.deptName like '%"+filter.get(paramName)+"%')";
                }else{
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }

                filterCounter++;
            }
        }
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
                countHql + filterPart);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HolHoliday> findByYearAndHolPersonId(String year,String loginName) {
        String hql="from HolHoliday t where t.removed='0' and t.holId=?";
        if(StringUtils.isNotEmpty(year)){
            hql+=" and t.holYear=?";
        }
        hql+=" order by t.holYear ASC";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0,loginName);
        if(StringUtils.isNotEmpty(year)){
            query.setString(1, year);
        }
        return query.list();
    }



    @Override
    public List<HolHoliday> findByStartYearAndHolPersonId(String year,
                                                          String loginName) {
        if(StringUtils.isEmpty(year)){
            return null;
        }
        String hql="from HolHoliday t where t.removed='0' and t.holId=? and t.holYear>=? order by t.holYear desc";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0,loginName).setString(1, year);
        return query.list();
    }

    @Override
    public List<HolHoliday> findAllNotUsedByPersonId(String loginName) {
        String hql="from HolHoliday t where t.removed='0' and t.holId=? and t.holDaysLeft>='0' order by t.holYear ASC";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0,loginName);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CsUser> findUsersByName(String name) {
        String hql="from CsUser t where t.removed='0' and t.loginName like '"+name+"%' order by t.id asc";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public HolHoliday findLastHolidaysSetByholPersonId(String loginName) {
        String hql="from HolHoliday t where t.removed='0' and t.holId=? order by t.holYear DESC";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0, loginName).setMaxResults(1);
        List<HolHoliday> list = query.list();
        if(list!=null && list.size()>0) return list.get(0);;
        return null;
    }

    @Override
    public void saveOrUpdateAll(List<HolHoliday> list) {
        getHibernateTemplate().saveOrUpdateAll(list);
    }

    @Override
    public com.wonders.stpt.csUser.entity.bo.CsUser findUserById(long id) {
        String hql="from com.wonders.stpt.csUser.entity.bo.CsUser t where t.removed='0' and t.id=?";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setLong(0,id);
        List<com.wonders.stpt.csUser.entity.bo.CsUser> list = query.list();
        if(list!=null && list.size()==1) return list.get(0);
        return null;
    }

    @Override
    public HolHoliday findById(String id) {
        String hql="from HolHoliday t where t.removed='0' and t.id=?";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setString(0,id);
        List<HolHoliday> list = query.list();
        if(list!=null && list.size()==1) return list.get(0);
        return null;
    }

    @Override
    public Object[] findHolHolidaySettime() {
        String sql ="select t.over_year overyear,t.month month from TA_HOL_HOLIDAY_SETTIME t ";
        SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setMaxResults(1);
        query.addScalar("overyear", Hibernate.LONG).addScalar("month",Hibernate.LONG);
        List<Object[]> list = query.list();
        if(list!=null && list.size()==1){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updateHolHolidaySettime(Long overyear, Long month) {
        String sql="update TA_HOL_HOLIDAY_SETTIME t set t.over_year=?, t.month=?";
        SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setLong(0, overyear).setLong(1, month);
        query.executeUpdate();
    }

    @Override
    public VUserdep findVUserdepById(Long id) {
        String hql="from VUserdep t where t.id=?";
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        query.setLong(0, id);
        List<VUserdep> list = query.list();
        if(list!=null && list.size()>0) return list.get(0);
        return null;
    }

    @Override
    public void saveAllHolHoliday(List<HolHoliday> list) {
        if(list!=null && list.size()>0){
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            for(int i=0;i<list.size(); i++){
                session.save(list.get(i));
                if(i/20==0){
                    session.flush();
                }
            }
            session.flush();
        }
    }

    @Override
    public List<HolHoliday> findOtherAccountByLoginName(String loginName,String holyear) {
        String hql="from HolHoliday t where t.holId in (select c.id from CsUser c where c.loginName like '%"+loginName+"%') and t.holYear='"+holyear+"'";
        return getHibernateTemplate().find(hql);
    }


}
