package com.wonders.stpt.annualLeave.utils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.namespace.QName;
/**
 * 
 */

import net.sf.json.JSONObject;


/** 
 * @ClassName: Msg 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-7 上午9:56:56 
 *  
 */
public class HttpRequestHelper {
	

	/**调用portal外部接口方法
	 * @param method
	 * @param paramsXml
	 * @return
	 */
	public static String portalService(String data,String urls) {
		
		String result = "";
		try {
			URL url = null;
			HttpURLConnection http = null;
			
			try {
				url = new URL(urls);
				http = (HttpURLConnection) url.openConnection();
				http.setDoInput(true);
				http.setDoOutput(true);
				http.setUseCaches(false);
				http.setConnectTimeout(50000);
				http.setReadTimeout(50000);
				http.setRequestMethod("POST");
				// http.setRequestProperty("Content-Type",
				// "text/xml; charset=UTF-8");
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.connect();
				String param = "&data="+ data;  
				  
				    OutputStreamWriter osw = new OutputStreamWriter(http.getOutputStream(), "utf-8");  
				    osw.write(param);  
				    osw.flush();  
				    osw.close();  
				  
				    
				if (http.getResponseCode() == 200) {
					BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));
					String inputLine;
					while ((inputLine = in.readLine()) != null) {
						result += inputLine;
					}
					in.close();
					//result = "["+result+"]";
				}
			} catch (Exception e) {
				System.out.println("err");
				e.printStackTrace();
			} finally {
				if (http != null) http.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
