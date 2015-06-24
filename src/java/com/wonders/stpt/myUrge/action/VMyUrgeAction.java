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

package com.wonders.stpt.myUrge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.components.Autocompleter;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wonders.stpt.dbBusiness.util.PageInfo;
import com.wonders.stpt.dbBusiness.util.PageResultSet;
import com.wonders.stpt.myNotice.entity.bo.MsgUserMassage;
import com.wonders.stpt.myUrge.entity.bo.FlowUrgen;
import com.wonders.stpt.myUrge.entity.bo.VUrgeInfo;
import com.wonders.stpt.myUrge.service.FlowUrgenService;
import com.wonders.stpt.myUrge.service.UrgeService;
import com.wonders.stpt.myUrge.service.VMyUrgeService;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.CommonDao;
import com.wonders.stpt.util.GlobalFunc;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.PropertiesReader;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author laowei
 * @version $Revision$
 * @date 2014-6-23
 * @author modify by $Author$
 * @since 1.0
 */

public class VMyUrgeAction extends BaseAjaxAction {
	private VUrgeInfo vUrgeInfo = new VUrgeInfo();
	private VMyUrgeService myUrgeService;
	private UrgeService urgeService;

	public UrgeService getUrgeService() {
		return urgeService;
	}

	public void setUrgeService(UrgeService urgeService) {
		this.urgeService = urgeService;
	}

	private FlowUrgen flowUrgen;

	public FlowUrgen getFlowUrgen() {
		return flowUrgen;
	}

	public void setFlowUrgen(FlowUrgen flowUrgen) {
		this.flowUrgen = flowUrgen;
	}

	public FlowUrgenService getFlowUrgenService() {
		return flowUrgenService;
	}

	public void setFlowUrgenService(FlowUrgenService flowUrgenService) {
		this.flowUrgenService = flowUrgenService;
	}

	private FlowUrgenService flowUrgenService;

	public VUrgeInfo getVUrgeInfo() {
		return vUrgeInfo;
	}

	public void setVUrgeInfo(VUrgeInfo vUrgeInfo) {
		this.vUrgeInfo = vUrgeInfo;
	}

	public void setmyUrgeService(VMyUrgeService vMyUrgeService) {
		this.myUrgeService = vMyUrgeService;
	}

	public String showMyUrge() {
		int size = 20;
		
		int pageNo = 0;
		String strPageNo = this.servletRequest.getParameter("pageNumber");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}

		int recordCount = 0;
		try {
			recordCount = myUrgeService.getCountMyUrge(this.servletRequest);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		PageInfo pageinfo = new PageInfo(recordCount, size, pageNo);
		PageResultSet<Object> result = new PageResultSet<Object>();
		result.setPageInfo(pageinfo);
		 int startIndex =size*pageNo;
		 int endIndex =startIndex+size-1;
	   
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		
		List list = new ArrayList();
		list = myUrgeService.findVMyUrgeByPage(this.servletRequest, pageinfo
				.getBeginIndex(), size);

		// this.servletRequest.setAttribute("list",list);
		result.setList(list);
		this.servletRequest.setAttribute("page", result);
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		this.servletRequest.setAttribute("startIndex", endIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);
		return "showMyUrge";
	}

	public void sendMsgMulti() throws IOException {

		String summary = PageUtils.GetParameter("summary");
		String count = PageUtils.GetParameter("count");
		String text = PageUtils.GetParameter("text");
		String username = PageUtils.GetParameter("username");
		String serviceType = PageUtils.GetParameter("serviceType");
		String processname = PageUtils.GetParameter("processname");
		String incident = PageUtils.GetParameter("incident");
		String sendUserId = AuthorUtil.GetLoginUserID();
		String sendUserName = AuthorUtil.GetLoginUserRealName();
		if(sendUserName!=null && sendUserName.length()==16){
			sendUserName = sendUserName.substring(0,12);
		}

		// System.out.println("-------------------------------count="+count);
		int count_num = 0;
		try {
			count_num = Integer.parseInt(count);
		} catch (Exception e) {
			count_num = 0;
		}
		
		String loginName_all = PageUtils.GetParameter("loginName");
		String serviceId_all = PageUtils.GetParameter("serviceId");
		String phoneNo_all = PageUtils.GetParameter("phone");
		String user_all = PageUtils.GetParameter("user");
		String userid_all = PageUtils.GetParameter("userid");

		String[] name = loginName_all.split(",");
		String[] id = serviceId_all.split(",");
		String[] phone = phoneNo_all.split(",");
		String[] cname = user_all.split(",");
		String[] userids = userid_all.split(",");
		String msg = "";

		HttpServletResponse res = PageUtils.getResponse();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		for (int i = 0; i < count_num; i++) {
			String content = "";
			content = text + "催办人：" + username + "，摘要：" + summary;
			String loginName = name[i];
			String serviceId = id[i];
			String phoneNo = phone[i];
			String userid = userids[i];
			
			long useridL = 0;
			long sendUserIdL = 0;

			try {
				useridL = Long.parseLong(userid);
			} catch (Exception e) {
			}

			try {
				sendUserIdL = Long.parseLong(sendUserId);
			} catch (Exception e) {
			}

			// sendUserId
			if (loginName != null && loginName.indexOf("ST/") != -1){
				loginName = loginName.substring(3);	
			}
			
			if(phoneNo== null || "".equals(phone)){
				phoneNo = GlobalFunc.getByIdMobile(userid);
			}
			
			if (phoneNo != null && !"".equals(phoneNo)) {
				try {
					// 保存催办BO
					flowUrgen = new FlowUrgen();

					flowUrgen.setProcessname(processname);
					flowUrgen.setIncident(incident);
					flowUrgen.setSend_content(content);
					flowUrgen.setTaskid(serviceId);
					flowUrgen.setSend_user(sendUserIdL);
					flowUrgen.setSend_username(sendUserName);
					flowUrgen.setUrgened_user(useridL);
					flowUrgen.setUrgened_username(cname[i]);
					flowUrgen.setUrgened_mb(phoneNo);
					flowUrgen.setUrgen_timeFormat("");
					flowUrgenService.insert(flowUrgen);
					
					String receiver = cname[i]+"|"+loginName+"|"+phoneNo;
					GlobalFunc.insertMsg(receiver, content, CommonDao.GetNewDatabaseDao());
					msg += "催办成功！短信已发送至" + cname[i] + "\n";
					
				} catch (Exception e) {
					msg += "催办失败！短信未发送至" + cname[i] + "\n";
					e.printStackTrace();
				}
			}

		}

		out.print(msg);
		out.flush();
		out.close();

	}

	public String sendMsgLeader() {
		String processName = PageUtils.GetParameter("processName");
		String incident = PageUtils.GetParameter("incident");
		String username = PageUtils.GetParameter("user_name");
		Map map = urgeService.urgeLeaders(processName, incident);
		map = flowUrgenService.AddUrgeInfo(map);
		this.getServletRequest().setAttribute("map", map);
		this.getServletRequest().setAttribute("username", username);
		return "sucess";
	}

	public String sendMsgPerson() {
		String processName = StringUtil.getNotNullValueString(PageUtils
				.GetParameter("processName"));
		String incident = StringUtil.getNotNullValueString(PageUtils
				.GetParameter("incident"));
		String username = StringUtil.getNotNullValueString((String) this
				.getServletRequest().getSession().getAttribute("user_name"));
		Map map = urgeService.urgePersons(processName, incident);
		map = flowUrgenService.AddUrgeInfo(map);
		this.getServletRequest().setAttribute("map", map);
		this.getServletRequest().setAttribute("username", username);
		return "sucess";
	}
	
	public String flowUrgenList(){
		
		int size = 10;
		int pageNo = 0;
		
		String strPageNo = this.servletRequest.getParameter("pageNumber");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		
		int recordCount = flowUrgenService.getUrgeLogCount();
		PageInfo pageinfo = new PageInfo(recordCount, size, pageNo);
		PageResultSet<Object> result = new PageResultSet<Object>();
		result.setPageInfo(pageinfo);

		List list = new ArrayList();
		list = flowUrgenService.findUrgeLogByPage( pageinfo.getBeginIndex(), size);
		
		result.setList(list);
		this.servletRequest.setAttribute("page", result);
		this.servletRequest.setAttribute("startNo", pageinfo.getBeginIndex()+1);
		this.servletRequest.setAttribute("endNo", pageinfo.getBeginIndex()+size);
		
		return "flowUrgenList";
	}
	
	
	
}
