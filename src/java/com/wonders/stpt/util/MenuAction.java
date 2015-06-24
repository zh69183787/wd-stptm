package com.wonders.stpt.util;

import java.io.IOException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;


import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.dao.NodeUserDAO;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;


public class MenuAction extends BaseAjaxAction {
	/**
	 * 组织类型为部门的code
	 */
	public static final String CODE_OF_DEPARTMENT="CODE3";
	/**
	 * 申通树根code
	 */
	public static final String CODE_OF_ROOT_TREE="stjt";
	/**
	 * 申通树根节点code
	 */
	public static final String CODE_OF_ROOT_NODE="shengtongjituan";
	//public static final String CODE_OF_ROOT_NODE="stjt";
	
	/**
	 * 菜单服务接口
	 */
	//private MenuService menuService;
	
	private OrganNodeService organNodeService;
	
	private OrganTreeService organTreeService;
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public OrganNodeService getOrganNodeService() {
		return organNodeService;
	}

	public void setOrganNodeService(OrganNodeService organNodeService) {
		this.organNodeService = organNodeService;
	}

	public OrganTreeService getOrganTreeService() {
		return organTreeService;
	}

	public void setOrganTreeService(OrganTreeService organTreeService) {
		this.organTreeService = organTreeService;
	}



	

	

	
	public String loadPersonTreeNew() throws IOException {
		
		String parentId = PageUtils.GetParameter("id");
		List result = new ArrayList();
		List childNodeList = new ArrayList();
		if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")) {
			//OrganNode parentNode=organNodeService.loadOrganNodeWithLazy(Long.parseLong(parentId),new String[]{"organNodeType"});
			OrganNode parentNode=GlobalFunc.GetNodeById(parentId);
			
			if(parentNode.getOrganNodeType()!= null &&  parentNode.getOrganNodeType().getPeople()==1){
				//childNodeList = organNodeService.getUsersByOrganOrder(Long.parseLong(parentId));// 已排序
				/* childNodeList = GlobalFunc.getUsersByOrganOrder(parentId);
			
				for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
					Map element = (Map) iter.next();
					String userId = element.get(NodeUserDAO.SECURITY_SUER_ID).toString();
					String order = element.get(NodeUserDAO.ORDERS).toString();
					//SecurityUser menuInfo = userService.loadUserById(Long.parseLong(userId));
					SecurityUser menuInfo = GlobalFunc.loadUserById(userId);
					menuInfo.setOrders(Long.parseLong(order));
					
					UserViewModel menuViewModel = new UserViewModel();
//					SecurityUser menuInfo = (SecurityUser) iter.next();
					BeanUtils.copyProperties(menuInfo, menuViewModel, new String[] { "sex" });
					menuViewModel.setLeaf(true);
					menuViewModel.setName(URLEncoder.encode(menuViewModel.getName(),"UTF-8"));
					result.add(menuViewModel);
				}*/
				//createJSonData(VOUtils.getJsonDataFromCollection(result));
				List<UserAndOrder> list = GlobalFunc.getUsesByOrgNode(parentId);
				for(int i =0;i<list.size();i++){
					UserAndOrder element = list.get(i);
					
					//String userId = element.get(NodeUserDAO.SECURITY_SUER_ID).toString();
					String order = element.getOrders();
					//SecurityUser menuInfo = userService.loadUserById(Long.parseLong(userId));
					SecurityUser menuInfo = element.getUser();
					menuInfo.setOrders(Long.parseLong(order));
					
					UserViewModel menuViewModel = new UserViewModel();
//					SecurityUser menuInfo = (SecurityUser) iter.next();
					BeanUtils.copyProperties(menuInfo, menuViewModel, new String[] { "sex" });
					menuViewModel.setLeaf(true);
					menuViewModel.setName(URLEncoder.encode(menuViewModel.getName(),"UTF-8"));
					result.add(menuViewModel);
				}
				HttpServletResponse response = PageUtils.getResponse();
				response.getWriter().print(MixPersonReturnString(result));
				return AJAX;							
			}
			else
			{				
				//OrganTree tree=organTreeService.getOrganTreeByCode(CODE_OF_ROOT_TREE);
				OrganTree tree = GlobalFunc.getOrganTreeByCode(CODE_OF_ROOT_TREE);
				
				//OrganNode node=organNodeService.loadOrganNodeById(Long.valueOf(parentId).longValue());
				OrganNode node=GlobalFunc.GetNodeById(parentId);
				//OrganNode[] chilrenNodes = organNodeService.getChildNodes(node,tree);
				OrganNode[] chilrenNodes  = GlobalFunc.getOrganNodesByParentAndTree( tree.getId(),node.getId());
				if(chilrenNodes !=null){
					childNodeList=Arrays.asList(chilrenNodes);
				}
					
			}
		}
		else {	
			
			//OrganNode rootNode = organNodeService.loadOrganNodeByCode(CODE_OF_ROOT_NODE);
			OrganNode rootNode  = GlobalFunc.GetNodeByCode(CODE_OF_ROOT_NODE);
			childNodeList.add(rootNode );
		}
		for(int i =0;i<childNodeList.size();i++){
			
			try{
				RadioOrganViewModel menuViewModel = new RadioOrganViewModel();
				
				OrganNode menuInfo = (OrganNode)childNodeList.get(i);
				if(menuInfo !=null){
					BeanUtils.copyProperties(menuInfo, menuViewModel, new String[] { "icon" });
					menuInfo.setName(URLEncoder.encode(this.getShortOrgName(menuInfo.getName()),"UTF-8"));
					result.add(menuInfo);
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		/*for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
		
			try{
				RadioOrganViewModel menuViewModel = new RadioOrganViewModel();
				
				Object obj = iter.next();
				if(obj!=null){
					OrganNode menuInfo = (OrganNode)obj;
					BeanUtils.copyProperties(menuInfo, menuViewModel, new String[] { "icon" });
					menuInfo.setName(URLEncoder.encode(this.getShortOrgName(menuInfo.getName()),"UTF-8"));
					result.add(menuInfo);
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}*/
		//createJSonData(VOUtils.getJsonDataFromCollection(result));
		HttpServletResponse response = PageUtils.getResponse();
		response.getWriter().print(MixDeptReturnString(result));
		return AJAX;
	}
	
	public String MixPersonReturnString(List result){
		String returnText = "";
		
		for(int i=0;i<result.size();i++){
			UserViewModel node = (UserViewModel)result.get(i);
			
			returnText += node.getId() + ",";
			returnText += node.getName() + ",1!";

		}
		if(!"".equals(returnText )){
			returnText = returnText.substring(0,returnText.length()-1);
		}
		
		
		return returnText.trim();
	}
	
	
	public  String getShortOrgName(String name){
		if (name==null || name.equals("")){
			return name;
		}
		Pattern pattern = Pattern.compile("[0-9][^0-9]");
		String[] strs = pattern.split(name);
		if (strs.length > 1) {
			strs[0] = name.substring(0, name.indexOf(name.replaceFirst(strs[0], "")) + 1);
			strs[1] = name.replaceFirst(strs[0], "");
			return strs[1];
		}
		else {
			return name;
		}
		
	}
	
	public String MixDeptReturnString(List result){
		String returnText = "";
		
		for(int i=0;i<result.size();i++){
			OrganNode node = (OrganNode)result.get(i);
			
			returnText += node.getId() + ",";
			returnText += node.getName() + ",0!";
		}
		if(returnText.length()>0){
			returnText = returnText.substring(0,returnText.length()-1);
		}
		
		
		return returnText.trim();
	}
	
	
	

}
