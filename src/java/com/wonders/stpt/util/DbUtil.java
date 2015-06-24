package com.wonders.stpt.util;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DbUtil{
	private static DataSource dataSource=null;
	private static JdbcTemplate jdbcTemplate=null;
	
	public JdbcTemplate getJdbcTemplate() {
		try{
			if(dataSource==null){
				throw new Exception("数据源为空！");
			}
			
			if(jdbcTemplate==null){
				jdbcTemplate = new JdbcTemplate(dataSource);
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return jdbcTemplate;
	}

	public static DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		DbUtil.dataSource = dataSource;
	}
	
	
}
