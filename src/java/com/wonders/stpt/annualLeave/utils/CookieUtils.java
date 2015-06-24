package com.wonders.stpt.annualLeave.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class CookieUtils {

	public static String getCookieValue(HttpServletRequest request,String cookieName) throws UnsupportedEncodingException{
		if(StringUtils.isEmpty(cookieName)) return null;
		Map<String, String> cookieMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		String cookieValue=null;
		if(cookies !=null){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];
				cookieMap.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
			}
			for (Cookie cookie:cookies) {
				if(cookieName.equals(cookie.getName())){
					cookieValue = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
					break;
				}
			}
		}
		return cookieValue;
	}
}
