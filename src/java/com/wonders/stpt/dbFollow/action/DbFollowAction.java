package com.wonders.stpt.dbFollow.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;


import net.sf.json.JSONObject;

import com.wonders.stpt.dbFollow.entity.bo.DbFollow;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowChild;
import com.wonders.stpt.dbFollow.entity.bo.DbFollowPlan;
import com.wonders.stpt.dbFollow.entity.bo.SendTodoItem;
import com.wonders.stpt.dbFollow.entity.bo.TMsgUserMassage;
import com.wonders.stpt.dbFollow.entity.vo.FollowChildInfo;
import com.wonders.stpt.dbFollow.entity.vo.FollowPlanInfo;
import com.wonders.stpt.dbFollow.service.DocDbService;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

public class DbFollowAction extends BaseAjaxAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocDbService docDbService;
	private DbFollow dbFollow = new DbFollow();
//	private String dbId;
//	private String followType;
	public DbFollow getDbFollow() {
		return dbFollow;
	}

	public void setDbFollow(DbFollow dbFollow) {
		this.dbFollow = dbFollow;
	}

	@Override
	public Object getModel() {
		return dbFollow;
	}
	
	public void setDocDbService(DocDbService docDbService) {
		this.docDbService = docDbService;
	}
	
	public String toFollowAdd(){
		String dbId = this.servletRequest.getParameter("dbId");
		String followType = this.servletRequest.getParameter("followType");
		if(docDbService.ifFollowed(dbId)){
			this.servletRequest.setAttribute("dbId", "0");
		}else{
			Object[] str = docDbService.findDocDbById(dbId);
			if(str!=null&&str.length==3&&str[1]!=null&&str[2]!=null){
				List<String[]> list = new ArrayList<String[]>();
				String[] str1 = str[1].toString().split(",");
				String[] str2 = str[2].toString().split(",");
				String[] strRe = new String[2];
				for(int i=0;i<str1.length&&i<str2.length;i++){
					strRe[0] = str1[i];
					strRe[1] = str2[i];
					list.add(strRe.clone());
				}
				for(int i=0;i<10;i++){
					strRe[0] = "";
					strRe[1] = "";
					list.add(strRe.clone());
				}
				this.servletRequest.setAttribute("list", list);
				this.servletRequest.setAttribute("dbName", str[0]);
				this.servletRequest.setAttribute("dbId", dbId);
				this.servletRequest.setAttribute("followType", followType);
			}
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String saveFollowAdd() throws FileNotFoundException, IOException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath();
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(path));
		
		String basePath = properties.getProperty("basePath").toString();
		
		String childInfo = this.servletRequest.getParameter("childInfo");
		//System.out.println("childInfo========"+childInfo);
		//List<FollowChildInfo> list = new ArrayList<FollowChildInfo>();
		List<FollowChildInfo> childList = VOUtils.getBeanListFromJsonData(childInfo, FollowChildInfo.class);
		List<DbFollowChild> list = new ArrayList<DbFollowChild>();
		List<SendTodoItem> todolist = new ArrayList<SendTodoItem>();
		Date date = new Date();
		dbFollow.setCreateTime(date);
		dbFollow.setFollowState("1");
		dbFollow.setRemoved("0");
		docDbService.save(dbFollow);
		String dbId = this.servletRequest.getParameter("dbId");
		String followType = this.servletRequest.getParameter("followType");
		docDbService.updateDocDbById(dbId, followType);
		String id = dbFollow.getId();
		if(id!=null){
			for(int i=0;i<childList.size();i++){
				DbFollowChild dbFollowChild = new DbFollowChild();
				dbFollowChild.setParentId(id);
				dbFollowChild.setFollowDeptId(childList.get(i).getFollowDeptId());
				dbFollowChild.setFollowDeptName(childList.get(i).getFollowDeptName());
				dbFollowChild.setRequire(childList.get(i).getRequire());
				dbFollowChild.setPlanSubmitTime(childList.get(i).getPlanSubmitTime());
				dbFollowChild.setCreateTime(date);
				dbFollowChild.setCreatePerson(dbFollow.getCreatePerson());
				dbFollowChild.setRemoved("0");
				dbFollowChild.setFollowState("1");//进行中
				list.add(dbFollowChild);
			}
			docDbService.saveOrUpdateAll(list);
			
			for(int i=0;i<list.size();i++){
				SendTodoItem todoItemBo = new SendTodoItem();
				todoItemBo.setApp("dbFollow");
				todoItemBo.setOccurTime(df.format(date));
				todoItemBo.setTitle("督办跟踪-"+dbFollow.getDbName());
				todoItemBo.setLoginName(docDbService.getDeptLeader(list.get(i).getFollowDeptId()));
				todoItemBo.setStatus(0);
				todoItemBo.setRemoved(0);
				todoItemBo.setTypename("督办跟踪");
				todoItemBo.setUrl(basePath+"/dbFollow/findDbFollowChildByPage.action?id="+list.get(i).getId());
				todoItemBo.setPname("督办跟踪");
				todoItemBo.setPincident(0);
				todoItemBo.setCname("督办跟踪");
				todoItemBo.setCincident(0);
				todoItemBo.setStepName("");
				todoItemBo.setTaskId("");
				todoItemBo.setType(1);
				todoItemBo.setInitiator("ST/G01000000013");
				todoItemBo.setData(list.get(i).getId()+"");
				todolist.add(todoItemBo);
			}
			docDbService.saveOrUpdateAll2(todolist);
		}
		
		return SUCCESS;
	}
	
	public String findDeptByName(){
		String name = this.servletRequest.getParameter("name");
		List<Object[]> list = docDbService.findDeptByName(name);
		String str = "{\"params\":[";
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i>0){
					str += ",";
				}
				str += "[\""+list.get(i)[0]+"\",\""+list.get(i)[1]+"\"]";
			}
		}
		str += "]}";
		//System.out.println(str);
		createJSonData(str);
		return AJAX;
	}
	
	public String toFollowEdit(){
		String id = this.servletRequest.getParameter("id");
		if(id!=null){
			dbFollow = (DbFollow) docDbService.load(id,DbFollow.class);
			List<DbFollowChild> list = docDbService.findFollowChildByParentId(id);
			DbFollowChild dbFollowChild = new DbFollowChild();
			for(int i=0;i<10;i++){
				list.add(dbFollowChild);
			}
			this.servletRequest.setAttribute("list", list);
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String saveFollowEdit() throws FileNotFoundException, IOException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath();
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(path));
		
		String basePath = properties.getProperty("basePath").toString();
		
		String id = this.servletRequest.getParameter("id");
		String modifyPerson = this.servletRequest.getParameter("modifyPerson");
		String childInfo = this.servletRequest.getParameter("childInfo");
		Date date = new Date();
		dbFollow = (DbFollow) docDbService.load(id,DbFollow.class);
		dbFollow.setModifyPerson(modifyPerson);
		dbFollow.setModifyTime(date);
		docDbService.update(dbFollow);
		List<FollowChildInfo> childList = VOUtils.getBeanListFromJsonData(childInfo, FollowChildInfo.class);
		List<DbFollowChild> list2 = new ArrayList<DbFollowChild>();
		List<DbFollowChild> list = new ArrayList<DbFollowChild>();
		List<SendTodoItem> todolist = new ArrayList<SendTodoItem>();
		for(int i=0;i<childList.size();i++){
			DbFollowChild dbFollowChild = new DbFollowChild();
			if(!"".equals(childList.get(i).getId())){
				dbFollowChild = (DbFollowChild) docDbService.load(childList.get(i).getId(),DbFollowChild.class);
				dbFollowChild.setRemoved(childList.get(i).getRemoved());
				dbFollowChild.setModifyTime(date);
				dbFollowChild.setModifyPerson(modifyPerson);
				dbFollowChild.setRequire(childList.get(i).getRequire());
				dbFollowChild.setPlanSubmitTime(childList.get(i).getPlanSubmitTime());
				list2.add(dbFollowChild);
			}else{
				dbFollowChild.setParentId(id);
				dbFollowChild.setFollowDeptId(childList.get(i).getFollowDeptId());
				dbFollowChild.setFollowDeptName(childList.get(i).getFollowDeptName());
				dbFollowChild.setCreateTime(date);
				dbFollowChild.setCreatePerson(modifyPerson);
				dbFollowChild.setRemoved("0");
				dbFollowChild.setFollowState("1");//进行中
				dbFollowChild.setRequire(childList.get(i).getRequire());
				dbFollowChild.setPlanSubmitTime(childList.get(i).getPlanSubmitTime());
				list.add(dbFollowChild);
			}
		}
		docDbService.saveOrUpdateAll(list2);
		docDbService.saveOrUpdateAll(list);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				SendTodoItem todoItemBo = new SendTodoItem();
				todoItemBo.setApp("dbFollow");
				todoItemBo.setOccurTime(df.format(date));
				todoItemBo.setTitle("督办跟踪-"+dbFollow.getDbName());
				todoItemBo.setLoginName(docDbService.getDeptLeader(list.get(i).getFollowDeptId()));
				todoItemBo.setStatus(0);
				todoItemBo.setRemoved(0);
				todoItemBo.setTypename("督办跟踪");
				todoItemBo.setUrl(basePath+"/dbFollow/findDbFollowChildByPage.action?id="+list.get(i).getId());
				todoItemBo.setPname("督办跟踪");
				todoItemBo.setPincident(0);
				todoItemBo.setCname("督办跟踪");
				todoItemBo.setCincident(0);
				todoItemBo.setStepName("");
				todoItemBo.setTaskId("");
				todoItemBo.setType(1);
				todoItemBo.setInitiator("ST/G01000000013");
				todoItemBo.setData(list.get(i).getId()+"");
				todolist.add(todoItemBo);
			}
			docDbService.saveOrUpdateAll2(todolist);
			docDbService.updateTodoItem(id);//有部门重新办理，删除代办项
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findDbFollowByPage() {
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");	
		
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		
		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}

		String key = null;
		String value = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(this.dbFollow);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			value = this.servletRequest.getParameter(key);
			if (value != null && value.trim().length() > 0) {
				Object res = this.getValueByParamName(
						this.dbFollow, key);
				if (res != null) {
					filter.put(key, res);
				}
			}
		}
		if (currentPage == 0) {
			page = docDbService.findDbFollowByPage(filter, start / size + 1, size);
		}else {
			page = docDbService.findDbFollowByPage(filter, currentPage, size);
		}
		String depts = "";
		List<String> deptList = new ArrayList<String>();
		if(page.getResult()!=null&&page.getResult().size()>0){
			for(int i=0;i<page.getResult().size();i++){
				DbFollow bo = (DbFollow) page.getResult().get(i);
				List<DbFollowChild> list = docDbService.findFollowChildByParentId(bo.getId());
				if(list!=null&&list.size()>0){
					for(int j=0;j<list.size();j++){
						if(j>0){
							depts += "，";
						}
						depts += list.get(j).getFollowDeptName();
						if("2".equals(list.get(j).getFollowState())){
							depts += "(完成)";
						}
					}
				}
				deptList.add(depts);
				depts = "";
			}
		}
		this.servletRequest.setAttribute("page", page);
		this.servletRequest.setAttribute("deptList", deptList);
		return SUCCESS;
	}
	
	private Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}
	
	public void deleteDbFollow(){
		String id = this.servletRequest.getParameter("id");
		dbFollow = (DbFollow) docDbService.load(id,DbFollow.class);
		dbFollow.setRemoved("1");
		docDbService.update(dbFollow);
	}
	
	public String findDbFollowChildByPage() {
		Page page;
		String currentPageStr = this.servletRequest.getParameter("number");
		String oldDeptId = this.servletRequest.getParameter("oldDeptId");
		String id = this.servletRequest.getParameter("id");
		String dbName = this.servletRequest.getParameter("dbName");
		String followType = this.servletRequest.getParameter("followType");
		String planSubmitTimeStart = this.servletRequest.getParameter("planSubmitTimeStart");
		String planSubmitTimeEnd = this.servletRequest.getParameter("planSubmitTimeEnd");
		
//		Cookie[] cookies = this.servletRequest.getCookies();
//		for (Cookie cookie:cookies) {
//			if("oldDeptId".equals(cookie.getName()))
//			{
//				oldDeptId = cookie.getValue();
//				
//				break;
//			}
//		}
		System.out.println("oldDeptIdJava===="+oldDeptId);
		
		int currentPage = 0;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		
		int start = 0;
		int size = 10;
		String pStart = this.servletRequest.getParameter("start");
		String pSize = this.servletRequest.getParameter("limit");
		if (pStart != null) {
			start = Integer.parseInt(pStart);
		}
		if (pSize != null) {
			size = Integer.parseInt(pSize);
		}
		Map<String, Object> filter = new HashMap<String, Object>();
		if(oldDeptId!=null&&!"".equals(oldDeptId)){
			filter.put("oldDeptId", oldDeptId);
			this.servletRequest.setAttribute("oldDeptId", oldDeptId);
		}
		if(id!=null&&!"".equals(id)){
			filter.put("id", id);
			this.servletRequest.setAttribute("id", id);
		}
		if(dbName!=null&&!"".equals(dbName)){
			filter.put("dbName", dbName);
			this.servletRequest.setAttribute("dbName", dbName);
		}
		if(followType!=null&&!"".equals(followType)){
			filter.put("followType", followType);
			this.servletRequest.setAttribute("followType", followType);
		}
		if(planSubmitTimeStart!=null&&!"".equals(planSubmitTimeStart)){
			filter.put("planSubmitTimeStart", planSubmitTimeStart);
			this.servletRequest.setAttribute("planSubmitTimeStart", planSubmitTimeStart);
		}
		if(planSubmitTimeEnd!=null&&!"".equals(planSubmitTimeEnd)){
			filter.put("planSubmitTimeEnd", planSubmitTimeEnd);
			this.servletRequest.setAttribute("planSubmitTimeEnd", planSubmitTimeEnd);
		}
		
		if (currentPage == 0) {
			page = docDbService.findDbFollowChildByPage(filter, start / size + 1, size);
		}else {
			page = docDbService.findDbFollowChildByPage(filter, currentPage, size);
		}
		this.servletRequest.setAttribute("page", page);
		return SUCCESS;
	}
	
	public String findFollowPlanList(){
		String followChildId = this.servletRequest.getParameter("followChildId");
		String infoType = this.servletRequest.getParameter("infoType");
		String followState = this.servletRequest.getParameter("followState");
		if(followState!=null){
			this.servletRequest.setAttribute("followState", followState);
		}
		this.servletRequest.setAttribute("infoType", infoType);
		if(followChildId!=null){
			DbFollowChild dbFollowChild = (DbFollowChild) docDbService.load(followChildId,DbFollowChild.class);
			this.servletRequest.setAttribute("dbFollowChild", dbFollowChild);
			dbFollow = (DbFollow)docDbService.load(dbFollowChild.getParentId(), DbFollow.class);
			List<DbFollowPlan> list = docDbService.findFollowPlanByFollowChildId(followChildId);
			DbFollowPlan dbFollowPlan = new DbFollowPlan();
			for(int i=0;i<10;i++){
				list.add(dbFollowPlan);
			}
			this.servletRequest.setAttribute("list", list);
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String saveFollowPlan() throws FileNotFoundException, IOException{
		String dealPerson = this.servletRequest.getParameter("dealPerson");
		String followChildId = this.servletRequest.getParameter("followChildId");
		String planInfo = this.servletRequest.getParameter("planInfo");
		String infoType = this.servletRequest.getParameter("infoType");
		String followState = this.servletRequest.getParameter("followState");
		String dbName = this.servletRequest.getParameter("dbName");
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = df.format(date);
		List<FollowPlanInfo> planList = VOUtils.getBeanListFromJsonData(planInfo, FollowPlanInfo.class);
		List<DbFollowPlan> list = new ArrayList<DbFollowPlan>();
		String historyInfo = "";
		DbFollowChild dbFollowChild = (DbFollowChild) docDbService.load(followChildId, DbFollowChild.class);
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath();
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(path));
		
		String basePath = properties.getProperty("basePath").toString();
		boolean flag = false;
		for(int i=0;i<planList.size();i++){
			DbFollowPlan dbFollowPlan = new DbFollowPlan();
			if(!"".equals(planList.get(i).getId())){
				if("0".equals(planList.get(i).getIfChanged())){
					continue;
				}
				dbFollowPlan = (DbFollowPlan) docDbService.load(planList.get(i).getId(),DbFollowPlan.class);
				dbFollowPlan.setModifyTime(date);
				dbFollowPlan.setModifyPerson(dealPerson);
				if("1".equals(infoType)){
					if("1".equals(planList.get(i).getRemoved())){
						dbFollowPlan.setRemoved(planList.get(i).getRemoved());
					}else{
						historyInfo = dbFollowPlan.getPlanChangeHistory();
						if(historyInfo==null||"".equals(historyInfo)){
							historyInfo = "";
						}else{
							historyInfo += "<p>&nbsp</p>";
						}
						historyInfo = historyInfo+"<b style=\"display:inline\">计划变更时间：</b>"+dateStr
									+"&nbsp;&nbsp;<b style=\"display:inline\">操作人：</b>"+dealPerson
									+"<br><b style=\"display:inline\">变更原因：</b>"+planList.get(i).getChangeReason()
									+"<br><b style=\"display:inline\">原计划名称：</b>"+dbFollowPlan.getPlanName()
									+"&nbsp;&nbsp;<b style=\"display:inline\">原计划完成时间：</b>"+dbFollowPlan.getPlanFinishTime()
									+"&nbsp;&nbsp;<b style=\"display:inline\">原计划成果：</b>"+dbFollowPlan.getPlanResult();
						dbFollowPlan.setPlanChangeHistory(historyInfo);
						dbFollowPlan.setPlanName(planList.get(i).getPlanName());
						dbFollowPlan.setPlanFinishTime(planList.get(i).getPlanFinishTime());
						dbFollowPlan.setPlanResult(planList.get(i).getPlanResult());
						
						if(!"".equals(planList.get(i).getChangeReason())){
							flag = true;
						}
					}
				}else if("2".equals(infoType)){
					dbFollowPlan.setFinishTime(planList.get(i).getFinishTime());
					dbFollowPlan.setAttach(planList.get(i).getAttach());
					
					flag = true;
				}
			}else{
				dbFollowPlan.setFollowChildId(followChildId);
				dbFollowPlan.setCreateTime(date);
				dbFollowPlan.setCreatePerson(dealPerson);
				dbFollowPlan.setPlanName(planList.get(i).getPlanName());
				dbFollowPlan.setPlanFinishTime(planList.get(i).getPlanFinishTime());
				dbFollowPlan.setPlanResult(planList.get(i).getPlanResult());
				dbFollowPlan.setRemoved(planList.get(i).getRemoved());
				
				flag = true;
			}
			
			list.add(dbFollowPlan);
		}
		docDbService.saveOrUpdateAll(list);
		if("2".equals(followState)){
			dbFollowChild.setFollowState(followState);
			docDbService.update(dbFollowChild);//已完成
			
			docDbService.updateTodoItem(dbFollowChild.getId());//删除代办项
			
			//若全部部门都已处理完成，给田琳插入一条代办项
			if(docDbService.ifAllDeptDone(dbFollowChild.getParentId())){
				List<SendTodoItem> todolist = new ArrayList<SendTodoItem>();
				
				SendTodoItem todoItemBo = new SendTodoItem();
				todoItemBo.setApp("dbFollow");
				todoItemBo.setOccurTime(df.format(date));
				todoItemBo.setTitle("督办跟踪-"+dbName);
				todoItemBo.setLoginName("ST/G01000000013");
				todoItemBo.setStatus(0);
				todoItemBo.setRemoved(0);
				todoItemBo.setTypename("督办跟踪");
				todoItemBo.setUrl(basePath+"/dbFollow/findDbFollowByPage.action?id="+dbFollowChild.getParentId());
				todoItemBo.setPname("督办跟踪");
				todoItemBo.setPincident(0);
				todoItemBo.setCname("督办跟踪");
				todoItemBo.setCincident(0);
				todoItemBo.setStepName("");
				todoItemBo.setTaskId("");
				todoItemBo.setType(1);
				todoItemBo.setInitiator("ST/G01000000013");
				todoItemBo.setData(dbFollowChild.getParentId()+"");
				todolist.add(todoItemBo);
				docDbService.saveOrUpdateAll2(todolist);
			}
		}
		if(flag){
			TMsgUserMassage msg = new TMsgUserMassage();
			List<TMsgUserMassage> msglist = new ArrayList<TMsgUserMassage>();
			String url = basePath+"/dbFollow/findDbFollowByPage.action?id="+dbFollowChild.getParentId();
			msg.setTitle(dbFollowChild.getFollowDeptName()+"变更了计划或提交了成果，相关的督办流程为："+dbName+"。");
			msg.setContent("您有条督办跟踪相关通知:<a target='_blank' href='"+url+"' ><font color='red'>请点击此处查看详细内容。</font></a>");
			msg.setEmpidrec(10366);
			msg.setEmpidsend(10366);
			msglist.add(msg);
			docDbService.saveOrUpdateAll2(msglist);
		}
		return SUCCESS;
	}
	
	public String toFollowView(){
		String id = this.servletRequest.getParameter("id");
		String deal = this.servletRequest.getParameter("deal");
		this.servletRequest.setAttribute("deal", deal);
		if(id!=null){
			dbFollow = (DbFollow) docDbService.load(id,DbFollow.class);
			List<DbFollowChild> childLlist = docDbService.findFollowChildByParentId(id);
			if(childLlist!=null&&childLlist.size()>0){
				List<Object[]> list = new ArrayList<Object[]>();
				for(int i=0;i<childLlist.size();i++){
					List<DbFollowPlan> planList = docDbService.findFollowPlanByFollowChildId(childLlist.get(i).getId());
					Object[] obj = new Object[2];
					obj[0] = childLlist.get(i);
					obj[1] = planList;
					list.add(obj);
				}
				this.servletRequest.setAttribute("list", list);
			}
		}
		return SUCCESS;
	}
	
	public String finishFollow(){
		String id = this.servletRequest.getParameter("id");
		String suggest = this.servletRequest.getParameter("suggest");
		String modifyPerson = this.servletRequest.getParameter("modifyPerson");
		dbFollow = (DbFollow) docDbService.load(id,DbFollow.class);
		dbFollow.setSuggest(suggest);
		dbFollow.setFollowState("2");
		dbFollow.setModifyPerson(modifyPerson);
		dbFollow.setModifyTime(new Date());
		docDbService.save(dbFollow);
		
		String dbId = docDbService.findDbFollowById(id).get(0).getDbId();
		docDbService.updateDocDbFollowStateById(dbId);
		
		//删除代办项
		docDbService.updateTodoItem(id);
		return SUCCESS;
	}
	
	public String simpleFollow(){
		String followChildId = this.servletRequest.getParameter("followChildId");
		if(followChildId!=null){
			DbFollowChild dbFollowChild = (DbFollowChild) docDbService.load(followChildId,DbFollowChild.class);
			this.servletRequest.setAttribute("dbFollowChild", dbFollowChild);
			dbFollow = (DbFollow)docDbService.load(dbFollowChild.getParentId(), DbFollow.class);
		}
		return SUCCESS;
	}
	
	public String saveSimpleFollow() throws FileNotFoundException, IOException{
		String followChildId = this.servletRequest.getParameter("followChildId");
		String attach = this.servletRequest.getParameter("attach");
		String dealPerson = this.servletRequest.getParameter("dealPerson");
		String dbName = this.servletRequest.getParameter("dbName");
		if(followChildId!=null){
			DbFollowChild dbFollowChild = (DbFollowChild) docDbService.load(followChildId,DbFollowChild.class);
			dbFollowChild.setAttach(attach);
			dbFollowChild.setModifyPerson(dealPerson);
			dbFollowChild.setModifyTime(new Date());
			dbFollowChild.setFollowState("2");
			docDbService.update(dbFollowChild);
			docDbService.updateTodoItem(followChildId);//删除代办项
			
			//若全部部门都已处理完成，给田琳插入一条代办项
			if(docDbService.ifAllDeptDone(dbFollowChild.getParentId())){
				Date date = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				List<SendTodoItem> todolist = new ArrayList<SendTodoItem>();
				String path = Thread.currentThread().getContextClassLoader()
						.getResource("config.properties").getPath();
				
				Properties properties = new Properties();
				properties.load(new FileInputStream(path));
				
				String basePath = properties.getProperty("basePath").toString();
				SendTodoItem todoItemBo = new SendTodoItem();
				todoItemBo.setApp("dbFollow");
				todoItemBo.setOccurTime(df.format(date));
				todoItemBo.setTitle("督办跟踪-"+dbName);
				todoItemBo.setLoginName("ST/G01000000013");
				todoItemBo.setStatus(0);
				todoItemBo.setRemoved(0);
				todoItemBo.setTypename("督办跟踪");
				todoItemBo.setUrl(basePath+"/dbFollow/findDbFollowByPage.action?id="+dbFollowChild.getParentId());
				todoItemBo.setPname("督办跟踪");
				todoItemBo.setPincident(0);
				todoItemBo.setCname("督办跟踪");
				todoItemBo.setCincident(0);
				todoItemBo.setStepName("");
				todoItemBo.setTaskId("");
				todoItemBo.setType(1);
				todoItemBo.setInitiator("ST/G01000000013");
				todoItemBo.setData(dbFollowChild.getParentId()+"");
				todolist.add(todoItemBo);
				docDbService.saveOrUpdateAll2(todolist);
			}
		}
		return SUCCESS;
	}
}
