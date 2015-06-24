package com.wonders.stpt;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wonders.stpt.UserInfo;
/**
 * 用户跨域访问
 * @author sunjiawei
 *
 */
public class CrossIpLogin {
	private UserInfo userInfo = new UserInfo();
	/**
	 * 后台post方式传递参数
	 * @author sunjiawei
	 * @param token
	 * @return 用户信息
	 */
	public String postSecret(String token){				
		//tDomainAuthentication = tDomainAuthenticationService.findTDomainAuthenticationById("test1");
		Properties properties = new Properties();
    	String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
    	try {
			properties.load(new FileInputStream(path));
			String secret=properties.getProperty("secret");
			String urlPortal=properties.getProperty("urlPortal");
			String appName=properties.getProperty("appName");
			//String returnUrl=properties.getProperty("returnUrl");
			//String secret = "124a77748fcb48a7a0863f30970a2a04";
			String sign = getMD5(appName+token+"getSession"+secret);
			
			//String urls = "http://10.1.41.252:8088/portal/domainCrossParam.jsp"+"?appName=test1&token="+token+"&method=getSession&sign="+sign;
			//String urls = "http://10.1.41.119:8080/testExcel/wonders/test1.jsp";
			String urls = urlPortal+"/domainCrossParam.jsp";
			//System.out.println("urls==============="+urls);
			URL url = null;
			HttpURLConnection http = null;
			String result = "error";
			try {
			url = new URL(urls);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.connect();
			
			String param = "appName="+appName+"&token="+token+"&method=getSession&sign="+sign;
			http.getOutputStream().write(param.getBytes());    
			http.getOutputStream().flush();
			http.getOutputStream().close();
			//System.out.println("getResponseCode====="+http.getResponseCode());
			if (http.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
			http.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
			//System.out.println("inputLine敏111====="+inputLine);
				result = inputLine;
			}
			
			//System.out.println(result);
			in.close();
			}
			} catch (Exception e) {
			System.out.println("err");
			} finally {
			if (http != null)
			http.disconnect();
			}
			
			return result;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
    	
	}
	
	// 获取Cookie组合字符串的MD5码的字符串----------------------------------------------------------------------------  
    public static String getMD5(String value) {  
        String result = null;  
        try{  
            byte[] valueByte = value.getBytes();  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(valueByte);  
            result = toHex(md.digest());  
        }catch(NoSuchAlgorithmException e1){  
            e1.printStackTrace();  
        }  
        return result;  
    }  
    // 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private static String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    }  
    /**
     * 将相关用户信息存入cookie
     * @author sunjiawei
     * @param cookie
     * @param response      
     */
    public void saveCookie(String cookie,HttpServletResponse response){
    	Document doc = null;  
    	        try {  
    	          doc = DocumentHelper.parseText(cookie);  
    	       } catch (DocumentException e2) {  
    	           // TODO 自动生成 catch 块  
    	           e2.printStackTrace();  
    	        }
    	       Element domainCross = doc.getRootElement();
    	       Element userInfo = domainCross.element("userInfo");
    	       Element token = domainCross.element("token");
    	       Element userId = userInfo.element("userId");
    	       Element loginName = userInfo.element("loginName");
    	       Element userName = userInfo.element("userName");
    	       Element deptId = userInfo.element("deptId");
    	       Element deptName = userInfo.element("deptName");
    	        	       
    	           	       
    	       String strUserId = userId.getTextTrim();
    	       String strLoginName = loginName.getTextTrim();
    	       String strUserName = userName.getTextTrim();
    	       try {
				strUserName = URLEncoder.encode(strUserName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    	       String strDeptId = deptId.getTextTrim();
    	       String strDeptName = deptName.getTextTrim();
    	       try {
    	    	   strDeptName = URLEncoder.encode(strDeptName,"UTF-8");
   			} catch (UnsupportedEncodingException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}  
    	       
    	       String strToken = token.getTextTrim();
    	       

Cookie cUserId = new Cookie("userId",strUserId);
Cookie cLoginName = new Cookie("loginName",strLoginName);
Cookie cUserName = new Cookie("userName",strUserName);
Cookie cDeptId = new Cookie("deptId",strDeptId);
Cookie cDeptName = new Cookie("deptName",strDeptName);
Cookie cToken = new Cookie("token",strToken);

cUserId.setPath("/");
cLoginName.setPath("/");
cUserName.setPath("/");
cDeptId.setPath("/");
cDeptName.setPath("/");
cToken.setPath("/");

//c.setMaxAge(100*60);
//cUserId.setMaxAge(10*60*60);
//cLoginName.setMaxAge(10*60*60);
//cUserName.setMaxAge(10*60*60);
//cDeptId.setMaxAge(10*60*60);
//cDeptName.setMaxAge(10*60*60);
cToken.setMaxAge(5*60);


response.addCookie(cUserId);
response.addCookie(cLoginName);
response.addCookie(cUserName);
response.addCookie(cDeptId);
response.addCookie(cDeptName);
response.addCookie(cToken);


    }
    /**
     * 根据变量名读取cookie值
     * @author sunjiawei
     * @param request
     * @param name:存入cookie的变量名
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            try {
				return URLDecoder.decode(cookieMap.get(name),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}  
        	//return cookieMap.get(name);
            //return cookie.getValue();
        }else{
            return null;
        }
    }

    /**
     * 读取cookie的内容
     * @author sunjiawei
     * @param request
     * @return Map<String, String>
     */
    protected static Map<String, String> readCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i].getValue());
            }
        }
        return cookieMap;
    }
    
    
    /**
     * 建立连接
     * @author sunjiawei
     * @param response
     */
    public void connectPortal(HttpServletResponse response,String returnUrl){
    	Properties properties = new Properties();
    	String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
    	try {
			properties.load(new FileInputStream(path));
			String urlPortal=properties.getProperty("urlPortal");
			String appName=properties.getProperty("appName");
			//String returnUrl=properties.getProperty("returnUrl");
			//System.out.println("urlPortal="+urlPortal);
			try {
				response.sendRedirect(urlPortal+"/domainCrossAuth.jsp?appName="+appName+"&returnUrl="+returnUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    public void setUserInfo(HttpServletRequest request ,UserInfo userInfo ){
    	Map<String, String> cookieMap = readCookieMap(request);
    	String userId = cookieMap.get("userId"); 
    	String loginName = cookieMap.get("loginName");
    	String userName = "";
		try {
			userName = URLDecoder.decode(cookieMap.get("userName"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String deptId = cookieMap.get("deptId");
    	String deptName = "";
		try {
			deptName = URLDecoder.decode(cookieMap.get("deptName"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	userInfo.setUserId(userId);
    	userInfo.setLoginName(loginName);
    	userInfo.setUserName(userName);
    	userInfo.setDeptId(deptId);
    	userInfo.setDeptName(deptName);
    	
    	//System.out.println("eee-===="+userId);
    	//System.out.println("dddd-===="+userInfo.getUserId());
    	
    }

//    public String getUserId(){
//    	String userId = userInfo.getUserId();
//    	return userId;
//    }
//    public String postSecret1(String token){				
//		//tDomainAuthentication = tDomainAuthenticationService.findTDomainAuthenticationById("test1");
//		Properties properties = new Properties();
//    	String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
//    	try {
//			properties.load(new FileInputStream(path));
//			String secret=properties.getProperty("secret");
//			String urlPortal=properties.getProperty("urlPortal");
//			String appName=properties.getProperty("appName");
//			String deptUserId = "2855"; 
//			//String returnUrl=properties.getProperty("returnUrl");
//			//String secret = "124a77748fcb48a7a0863f30970a2a04";
//			String sign = getMD5(appName+token+"getDeptLeaders"+secret+deptUserId);
//			
//			//String urls = "http://10.1.41.252:8088/portal/domainCrossParam.jsp"+"?appName=test1&token="+token+"&method=getSession&sign="+sign;
//			//String urls = "http://10.1.41.119:8080/testExcel/wonders/test1.jsp";
//			String urls = urlPortal+"/domainCrossParam.jsp";
//			System.out.println("urls==============="+urls);
//			URL url = null;
//			HttpURLConnection http = null;
//			String result = "error";
//			try {
//			url = new URL(urls);
//			http = (HttpURLConnection) url.openConnection();
//			http.setDoInput(true);
//			http.setDoOutput(true);
//			http.setUseCaches(false);
//			http.setConnectTimeout(50000);
//			http.setReadTimeout(50000);
//			http.setRequestMethod("POST");
//			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
//			http.connect();
//			String param = "appName="+appName+"&token="+token+"&method=getDeptLeaders&sign="+sign+"&deptUserId="+deptUserId+"&dataType=json";
//			http.getOutputStream().write(param.getBytes());    
//			http.getOutputStream().flush();
//			http.getOutputStream().close();
//			//System.out.println("getResponseCode====="+http.getResponseCode());
//			if (http.getResponseCode() == 200) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//			http.getInputStream()));
//			String inputLine;
//			while ((inputLine = in.readLine()) != null) {
//			//System.out.println("inputLine敏111====="+inputLine);
//				result = inputLine;
//			}
//			
//			//System.out.println(result);
//			in.close();
//			}
//			} catch (Exception e) {
//			System.out.println("err");
//			} finally {
//			if (http != null)
//			http.disconnect();
//			}
//			
//			return result;
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			return null;
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			return null;
//		}
//    	
//	}
//    
//    public void saveCookie1(String zs,HttpServletResponse response){    
//    	        Cookie cZs = new Cookie("zs",zs);
//    	        cZs.setPath("/");
//    	        response.addCookie(cZs);
//    }
	
}
