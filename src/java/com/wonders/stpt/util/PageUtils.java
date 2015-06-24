package com.wonders.stpt.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;

public class PageUtils {
	public static HttpServletRequest GetRequest() {
		HttpServletRequest request = null;
		ActionContext ctx = null;
		try {
			ctx = ActionContext.getContext();
			request = (HttpServletRequest) ctx
					.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return request;
	}
	
	
	public static int GetDefaultPageSize() {
		int pageSize =0;
		try{
			Object obj = PropertiesReader.GetProptery("pageSize")+"";
			if(obj != null  && !"".equals(obj)){
				pageSize = Integer.parseInt(obj.toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return pageSize ;
	}
	
	
	public static HttpServletResponse getResponse() {
		HttpServletResponse res= null;
		ActionContext ctx = null;
		try {
			ctx = ActionContext.getContext();
			res = (HttpServletResponse) ctx
					.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return res;
	}
	
	public static String GetParameter(HttpServletRequest request, String key) {
		String value = "";
		try {
			if (request.getParameterMap().containsKey(key)) {
				value = request.getParameter(key);
			}

			if (("".equals(value)) && (request.getAttribute(key) != null)) {
				value = request.getAttribute(key) + "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}
	
	public static String GetParameter(String key) {
		String value = "";
		HttpServletRequest request = null;
		ActionContext ctx = null;
		try {
			ctx = ActionContext.getContext();
			request = (HttpServletRequest) ctx
					.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
			value = GetParameter(request, key);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}
	
	public static void js_alert(String message,String url){
		HttpServletResponse response=PageUtils.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String script = "<script>alert('" + message
				+ "');window.location.href='"+url+"';</script>";

		try {
			response.getWriter().write(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
}
