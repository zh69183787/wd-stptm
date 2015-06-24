package com.wonders.stpt.overtimeWarn.action;

import java.util.List;
import com.wonders.stpt.dbBusiness.util.PageInfo;
import com.wonders.stpt.dbBusiness.util.PageResultSet;
import com.wonders.stpt.overtimeWarn.entity.bo.HtXx;
import com.wonders.stpt.overtimeWarn.entity.bo.Incidents;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocReceive;
import com.wonders.stpt.overtimeWarn.entity.bo.TDocSend;
import com.wonders.stpt.overtimeWarn.entity.bo.TReceiveDirective;
import com.wonders.stpt.overtimeWarn.entity.bo.TSubprocess;
import com.wonders.stpt.overtimeWarn.entity.bo.Tasks;
import com.wonders.stpt.overtimeWarn.service.OverTimeService;
import com.wonders.stpt.util.AuthorUtil;
import com.wonders.stpt.util.PageUtils;
import com.wonders.stpt.util.PropertiesReader;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

public class OverTimeAction extends BaseAjaxAction {

	private TDocReceive docReceive = new TDocReceive();
	private TDocSend docSend = new TDocSend();
	private TReceiveDirective receiveDirective = new TReceiveDirective();;
	private TSubprocess subprocess = new TSubprocess();
	private Tasks tasks = new Tasks();
	private Incidents incidents = new Incidents();
	private HtXx htxx = new HtXx();

	private OverTimeService overTimeService;

	public OverTimeService getOverTimeService() {
		return overTimeService;
	}

	public void setOverTimeService(OverTimeService overTimeService) {
		this.overTimeService = overTimeService;
	}

	public TDocReceive getDocReceive() {
		return docReceive;
	}

	public void setDocReceive(TDocReceive docReceive) {
		this.docReceive = docReceive;
	}

	public TDocSend getDocSend() {
		return docSend;
	}

	public void setDocSend(TDocSend docSend) {
		this.docSend = docSend;
	}

	public TReceiveDirective getReceiveDirective() {
		return receiveDirective;
	}

	public void setReceiveDirective(TReceiveDirective receiveDirective) {
		this.receiveDirective = receiveDirective;
	}

	public TSubprocess getSubprocess() {
		return subprocess;
	}

	public void setSubprocess(TSubprocess subprocess) {
		this.subprocess = subprocess;
	}

	public Tasks getTask() {
		return tasks;
	}

	public void setTask(Tasks task) {
		this.tasks = task;
	}

	public Incidents getIncidents() {
		return incidents;
	}

	public void setIncidents(Incidents incidents) {
		this.incidents = incidents;
	}

	public HtXx getHtxx() {
		return htxx;
	}

	public void setHtxx(HtXx htxx) {
		this.htxx = htxx;
	}

	/**
	 * 收文超时监控查询
	 * 
	 * @return
	 */
	public String showReceiveList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCount(this.docReceive);

		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPage(this.docReceive,
				pageinfo.getBeginIndex(), pageSize);

		result.setPageInfo(pageinfo);
		result.setList(list);
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
	    
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));

		this.servletRequest.setAttribute("processstatus",
				this.docReceive.getProcessstatus());
		this.servletRequest.setAttribute("unittype",
				PageUtils.GetParameter("docReceive.unittype"));
		this.servletRequest.setAttribute("year", this.docReceive.getSwtype());
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);
		
		return "showReceiveList";
	}

	public String showTaskList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCountt(this.htxx);

		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPaget(this.htxx,
				pageinfo.getBeginIndex(), pageSize);

		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		this.servletRequest.setAttribute("status", this.htxx.getStatus());
		
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		return "tasklist";
	}

	public String showIncidentsList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCounti(this.htxx);
		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPagei(this.htxx,
				pageinfo.getBeginIndex(), pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		this.servletRequest.setAttribute("status", this.htxx.getStatus());
		
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		
		return "incidentslist";
	}

	/**
	 * 合同审批 超时监控查询
	 * 
	 * @return
	 */
	public String showHtXxList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCount(this.htxx);

		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPage(this.htxx,
				pageinfo.getBeginIndex(), pageSize);

		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		this.servletRequest.setAttribute("status", this.htxx.getStatus());
		
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		
		return "htxxlist";
	}

	/**
	 * 发文超时监控查询
	 * 
	 * @return
	 */
	public String showSendList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCount(this.docSend);
		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPage(this.docSend,
				pageinfo.getBeginIndex(), pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));


		this.servletRequest.setAttribute("processstatus",this.docSend.getProcessstatus());
		this.servletRequest.setAttribute("unittype",PageUtils.GetParameter("docSend.unittype"));
		this.servletRequest.setAttribute("year",this.docSend.getYear());
		
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		
		return "showSendList";
	}

	/**
	 * 呈批超时监控查询
	 * 
	 * @return
	 */
	public String showReceiveDirectiveList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCount(this.receiveDirective);
		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPage(this.receiveDirective,
				pageinfo.getBeginIndex(), pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		
		this.servletRequest.setAttribute("processstatus",this.receiveDirective.getProcessstatus());
		String Zleader= receiveDirective.getZleader();
		this.servletRequest.setAttribute("zleader",Zleader);
		//this.servletRequest.setAttribute("unittype",PageUtils.GetParameter("receiveDirective."));
		this.servletRequest.setAttribute("year",this.receiveDirective.getYear());
		
		int startIndex =recordCount>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		
		return "showReceiveDirectiveList";
	}

	/**
	 * 合同审批监控查询(新)
	 * 
	 * @return
	 */
	public String showSubprocessList() {
		int pageNo = 1;
		PageResultSet<Object> result = new PageResultSet<Object>();
		String strPageNo = PageUtils.GetParameter("number");
		if (strPageNo != null && !"".equals(strPageNo)) {
			pageNo = Integer.parseInt(strPageNo);
		}
		int pageSize = PageUtils.GetDefaultPageSize();
		int recordCount = overTimeService.recordCountL(this.htxx);
		PageInfo pageinfo = new PageInfo(recordCount, pageSize, pageNo);
		List list = overTimeService.findRecordByPageL(this.htxx,
				pageinfo.getBeginIndex(), pageSize);
		result.setPageInfo(pageinfo);
		result.setList(list);
		
		// servletRequest.setAttribute("list", list);
		this.servletRequest.setAttribute("result", result);
		this.servletRequest.setAttribute("loginName",
				AuthorUtil.GetLoginUserName(PageUtils.GetRequest()));
		this.servletRequest.setAttribute("ultimusip",
				PropertiesReader.GetProptery("ultimusip"));
		
		this.servletRequest.setAttribute("processstatus",this.htxx.getStatusHt());
		
		int startIndex =pageSize>0?pageSize*(pageNo-1)+1:0;
		int endIndex =startIndex+pageSize-1;
	    if(endIndex >recordCount){
	    	endIndex =recordCount;
	    }
		this.servletRequest.setAttribute("startIndex", startIndex);
		this.servletRequest.setAttribute("endIndex", endIndex);

		
		return "showSubprocessList";
	}
}
