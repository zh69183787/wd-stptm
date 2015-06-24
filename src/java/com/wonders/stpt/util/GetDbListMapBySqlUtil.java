package com.wonders.stpt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.util.DbUtil;

public class GetDbListMapBySqlUtil {
	private DbUtil dbUtil;
	
	public DbUtil getDbUtil() {
		return dbUtil;
	}
	public void setDbUtil(DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	/**
	 * 根据sql  获取数据库listMap
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDbListMapBySql(String sql) {
		System.out.println(sql);
		List<Map<String, Object>> list = dbUtil.getJdbcTemplate().queryForList(sql);
		if(list==null||list.size()<=0){
			list=new ArrayList<Map<String, Object>>();
		}
		return list;
	}

}
