package com.wonders.stpt.hiddenDangersCorrect.dao.impl;

import com.wonders.stpt.hiddenDangersCorrect.dao.HdcChangeLogDao;
import com.wonders.stpt.hiddenDangersCorrect.entity.bo.HdcChangeLog;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/3/23
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class HdcChangeLogDaoImpl extends
        AbstractHibernateDaoImpl<HdcChangeLog> implements HdcChangeLogDao {

    public Page findHdcChangeLogByPage(Map<String, Object> filter,
                                               int pageNo, int pageSize,String operateTypeId) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t,l from HiddenDangersCorrect t,HdcChangeLog l where t.id = l.hdcId";
        String countHql = "select count(*) from HiddenDangersCorrect t ,HdcChangeLog l where t.id = l.hdcId ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
                    filterPart += " and ";
                String paramName = i.next();
                if(paramName.equals("lines")){
                    filterPart += "(t." + paramName + " like :lines_a or t." + paramName + " like :lines_b or t.lines like '%全路网%' )";
                    args.add(new HqlParameter("lines_a", filter.get(paramName)+"%"));
                    args.add(new HqlParameter("lines_b", "%,"+filter.get(paramName)+"%"));
                }else if(paramName.equals("dangersState")||paramName.equals("correctMethod")||paramName.equals("major")||paramName.equals("workDept")||paramName.equals("workPerson")||paramName.equals("inputDept")){
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
                }else if(paramName.equals("orgLastDateStart")){
                    filterPart += "l.orgLastDate >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("orgLastDateEnd")){
                    filterPart += "l.orgLastDate <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("nowLastDateStart")){
                    filterPart += "l.nowLastDate >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("nowLastDateEnd")){
                    filterPart += "l.nowLastDate <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("finishDateStart")){
                    filterPart += "t.finishDate >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("finishDateEnd")){
                    filterPart += "t.finishDate <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("inputDateStart")){
                    filterPart += "t.inputDate >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("inputDateEnd")){
                    filterPart += "t.inputDate <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("deptId")){
                    if(filter.get("companyId")!=null){
                        if ("1".equals(operateTypeId)) {
                            filterPart += "t.inputDeptId = '" + filter.get(paramName)+"' ";
                        }else if("2".equals(operateTypeId)){
                            filterPart += "(t.inputDeptId = '" + filter.get(paramName) +"' or t.workDeptId like '%," +
                                    filter.get("companyId") + ",%'" + " or t.supervisionDeptId = '" + filter.get("companyId")+"') " +
                                    " and (t.checkState = '2' or t.checkState = '3') ";
                        }else if ("3".equals(operateTypeId)) {
                            filterPart += "(t.inputDeptId = '" + filter.get(paramName) +"' or t.workDeptId like '%," +
                                    filter.get("companyId") + ",%'" + " or t.supervisionDeptId = '" + filter.get("companyId")+"') ";
                        }else{
                            //filterPart += " t.checkState = '2' ";
                            filterPart += " 1=1 ";
                        }
                    }else{
                        filterPart += " 1=1 ";
                    }

                }else if(paramName.equals("workState")){
                    filterPart += "t.workState = :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }else if(paramName.equals("companyId")){
                    filterPart += " 1 = 1 ";
                }
                else{
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }
                filterCounter++;
            }
        }
        filterPart += " ORDER BY l.operateTime DESC";
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
                countHql + filterPart);
    }
}
