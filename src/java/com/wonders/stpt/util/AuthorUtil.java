package com.wonders.stpt.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.sun.net.httpserver.HttpServer;
import com.wonders.stpt.myNotice.dao.impl.TMsgUsermessageDaoImpl;
import com.wondersgroup.framework.security.bo.SecurityUser;

public class AuthorUtil {
	public static String getUserInfoThroughCA(String appName, String token,
			String method, String secret, String dataType, String dataParams) {

		URL url = null;
		HttpURLConnection http = null;
		String textEntity = "";

		String path = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath();

		Properties properties2 = new Properties();
		try {
			properties2.load(new FileInputStream(path));
		} catch (FileNotFoundException e1) {
			return null;
		} catch (IOException e1) {
			return null;
		}

		String serverUrl = properties2.getProperty("urlCa").toString()
				+ properties2.getProperty("serverPath").toString();

		try {
			url = new URL(serverUrl + "/dataExchange");
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + token
					+ "&method=" + method + "&dataType=" + dataType
					+ "&dataParams=" + dataParams + "&sign="
					+ MD5Util.MD5Encode(appName + token + method + secret);

			OutputStreamWriter osw = new OutputStreamWriter(http
					.getOutputStream(), "utf-8");
			osw.write(param);
			osw.flush();
			osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						http.getInputStream(), "utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
			} else {
				return "没有通过用户认证";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (http != null)
				http.disconnect();
		}
		return textEntity;
	}

	
	public static String GetLoginUserID() {

		return AuthorUtil.GetLoginUserID(PageUtils.GetRequest());

	}
	
	public static String GetLoginDeptID(){
		String deptId="";
		try {
			Cookie[] cookies = PageUtils.GetRequest().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("deptId")) {
					deptId =cookies[i].getValue();
				}
			}

		} catch (Exception ex){ 
			ex.printStackTrace();
		}
		
		return deptId;
	} 
	
	public static String GetLoginUserID(HttpServletRequest req) {

		String userId = "";

		try {
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userId")) {
					userId = cookies[i].getValue();
				}
			}

			String oldUserId = GetUserIdByName(req);
			if (oldUserId != null && !"".equals(oldUserId)) {
				userId = oldUserId;
			}
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return userId;

	}

	public static String GetLoginUserName(HttpServletRequest req) {

		String loginName = "";

		try {
			Cookie[] cookies = req.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginName")) {
					loginName = cookies[i].getValue();
				}
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return loginName;

	}
	
	public static String GetLoginRealName() {

		String loginName = "";

		try {
			Cookie[] cookies = PageUtils.GetRequest().getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userName")) {
					loginName = cookies[i].getValue();
				}
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return loginName;

	}
	
	
	
	public static String GetLoginUserName() {
		return AuthorUtil.GetLoginUserName(PageUtils.GetRequest());
	}
	
	
	public static String GetLoginUserRealName() {
		String userId= AuthorUtil.GetLoginUserID();
		SecurityUser user = AuthorUtil.loadUserById(userId);
		return user.getName();
	}
	
	public static SecurityUser loadUserById(Object id){
		SecurityUser user = null;
		try{
			String sql = "select id,login_name,name,sex,mobile1 from cs_user where id="+id;
			List lst = CommonDao.GetOldDatabaseDao().fetchAll(sql);
			user = new SecurityUser();
			if (lst !=null && lst.size()>0){
				Object[] arr = (Object[]) lst.get(0);
				user.setId(Long.parseLong(arr[0]+""));
				user.setLoginName(arr[1]+"");
				user.setName(arr[2]+"");
				user.setSex(arr[3]+"");
				user.setMobile1(arr[4]+"");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}
	
	public static SecurityUser GetLoginUser(){
		SecurityUser user = null;
		try{
			String sql = "select id,login_name,name,sex,mobile1,ext5 as deptId from cs_user where id="+AuthorUtil.GetLoginUserID();
			List lst = CommonDao.GetOldDatabaseDao().fetchAll(sql);
			user = new SecurityUser();
			if (lst !=null && lst.size()>0){
				Object[] arr = (Object[]) lst.get(0);
				user.setId(Long.parseLong(arr[0]+""));
				user.setLoginName(arr[1]+"");
				user.setName(arr[2]+"");
				user.setSex(arr[3]+"");
				user.setMobile1(arr[4]+"");
				user.setExt5(arr[5]+"");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}
	
	
	

	public static String GetUserIdByName(HttpServletRequest req) {

		String userId = "";
		String loginName = "";
		Cookie[] cookies = req.getCookies();
		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("loginName")) {
				loginName = cookies[i].getValue();
			}
		}

	
		Map<String, Object> ft = new HashMap<String, Object>();
		if (loginName!= null && loginName.length() >=12) {
			loginName = loginName.substring(0, 12);
		}
		

		String sql = "select id from CS_USER where LOGIN_NAME='" + loginName+ "'";
			Object obj = CommonDao.GetOldDatabaseDao().fetchColumn(sql);
		if(obj!=null){
			userId=obj+"";	
		}
		
		return userId;

	}
	
	
	
	

}
