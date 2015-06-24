package com.wonders.stpt.util;

import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

public class CommonAjaxAction extends BaseAjaxAction {
	
  public String getFirstTaskId(){
	 
	  try{
		String processName = PageUtils.GetParameter("processname");
		//processName = new String(processName.getBytes("iso-8859-1"), "utf-8");
		String incident = PageUtils.GetParameter("incident");
		if(!processName.equals("") && !"".equals(incident)){
			String sql="select taskid from tasks where processName='"+processName+"' and incident="+incident;
			String taskkid = CommonDao.GetOldDatabaseDao().fetchColumn(sql)+"";
			PageUtils.getResponse().getWriter().write(taskkid);
		}
		
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }
	 
	 return AJAX;  
  }
}
