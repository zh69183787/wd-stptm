
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
 
package com.wonders.stpt.myNotice.action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Autocompleter;



import com.wonders.stpt.dbBusiness.util.PageInfo;
import com.wonders.stpt.dbBusiness.util.PageResultSet;
import com.wonders.stpt.dbBusiness.util.TodoItemVo;
import com.wonders.stpt.dbFollow.entity.bo.TMsgUserMassage;
import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.myNotice.service.TMsgUsermessageService;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.GlobalFunc;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.RandomGUID;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author ycl
 * @version $Revision$ 
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */
 
public class TMsgUsermessageAction extends BaseAjaxAction { 
	private MsgUserMassage msgUsermessage = new MsgUserMassage();
	
	
	
	public MsgUserMassage getMsgUsermessage() {
		return msgUsermessage;
	}

	public void setMsgUsermessage(MsgUserMassage msgUsermessage) {
		this.msgUsermessage = msgUsermessage;
	}

	public TMsgUsermessageService getMsgUsermessageService() {
		return msgUsermessageService;
	}

	public void setMsgUsermessageService(
			TMsgUsermessageService msgUsermessageService) {
		this.msgUsermessageService = msgUsermessageService;
	}

	private TMsgUsermessageService msgUsermessageService;
	
	
	public String findTMsgUsermessageById() {
		String id = super.getServletRequest().getParameter("sid");
		
		MsgUserMassage bo = msgUsermessageService.findTMsgUsermessageById(Long.parseLong(id));
		
		if (bo != null) {
			String json = VOUtils.getJsonData(bo);
			createJSonData("{\"success\":true,\"result\":[" + json.toString()+ "]}");
		}
		return AJAX;
	}

	public String showList(){
		
		
		String userId=AuthorUtil.GetLoginUserID(PageUtils.GetRequest());
		//String  userId="1062";
		String caseType = servletRequest.getParameter("caseType");
		String msgType =servletRequest.getParameter("msgType");
		
		if(caseType == null || "".equals(caseType)){
			caseType  = "0";
		}
		
		if(msgType == null || "".equals(msgType)){
			msgType  = "1";
		}
		
		msgUsermessage.setCaseType(caseType);
		msgUsermessage.setMsgType(msgType);
		
		
		PageResultSet<Object> result  = new PageResultSet<Object>();
		int size = PageUtils.GetDefaultPageSize();
		int pageNo =0;
		String strPageNo= PageUtils.GetParameter("number");
		if(strPageNo != null && !"".equals(strPageNo)){
			pageNo = Integer.parseInt(strPageNo);
		}
		
		int recordCount = msgUsermessageService.recordCount(userId, this.msgUsermessage);
		
		PageInfo pageinfo = new PageInfo(recordCount, msgUsermessage.pageSize, pageNo);	
		List list = msgUsermessageService.findTMsgUsermessageByPage(userId,this.msgUsermessage, pageinfo.getBeginIndex(), size);
		result.setPageInfo(pageinfo);
		
		servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("startNo", pageinfo.getBeginIndex()+1);
		this.servletRequest.setAttribute("endNo", pageinfo.getBeginIndex()+size);
		
		return "showList";
		

	}
	
	
	
	public String showDetail(){
		String sid= this.servletRequest.getParameter("sid");
		if(sid!=null && !"".equals(sid)){
			msgUsermessage.setSid(Long.parseLong(sid));
			this.msgUsermessageService.updateMsgState(this.msgUsermessage,"1");
			this.msgUsermessage=this.msgUsermessageService.findTMsgUsermessageById(Long.parseLong(sid));
			
		}
		return "detail";
	}
	
	public String newNotice(){
		String reply  = this.servletRequest.getParameter("reply");
		String sid = this.servletRequest.getParameter("sid");
		String sender = this.servletRequest.getParameter("sender");
		if(reply!=null && "1".equals(reply)){
			this.msgUsermessage=this.msgUsermessageService.findTMsgUsermessageById(Long.parseLong(sid));
			msgUsermessage.setTitle("Reply:"+msgUsermessage.getTitle());
			this.servletRequest.setAttribute("sender", sender);
		}else if(reply!=null && "2".equals(reply)){
			this.msgUsermessage=this.msgUsermessageService.findTMsgUsermessageById(Long.parseLong(sid));
			msgUsermessage.setEmpidsend(0);
			msgUsermessage.setTitle("CC:"+msgUsermessage.getTitle());
		}
		
		return "newNotice";
	}
	
	
	
	public void deleteItem(){
		int rtn =0;
		try{
			String sid = this.servletRequest.getParameter("sid");
			String msgType = this.servletRequest.getParameter("msgType");
			if(sid != null && !"".equals(sid)){
				rtn = this.msgUsermessageService.deleteItem(msgType,Long.parseLong(sid));
			}
			this.servletResponse.getWriter().write(rtn+"");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public String choosePerson(){
		return "choosePerson";
	}
	
	public String attachment(){
		
		return "attachment";
	}
		
	public void sendMsg() throws IOException{
		this.msgUsermessageService.sendMsg();
		PageUtils.js_alert("发送成功","showList.action?msgType=2");
		//this.servletResponse.sendRedirect("showList.action?msgType=2");
	}	
	
	public void setTMsgUsermessageService(TMsgUsermessageService tMsgUsermessageService) {
		this.msgUsermessageService = tMsgUsermessageService;
	}
}
	
