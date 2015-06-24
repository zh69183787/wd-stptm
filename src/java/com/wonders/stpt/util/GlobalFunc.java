package com.wonders.stpt.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.hibernate.Session;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wonders.stpt.shortMseeage.model.bo.ShortMessageTask;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganNodeType;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.dao.NodeUserDAO;
import com.wondersgroup.framework.security.bo.SecurityUser;






public class GlobalFunc extends JdbcDaoSupport {
	public static String getByIdMobile(String id){
		String mobile="";
		String sql="select mobile1 from cs_user where id=" + Long.parseLong(id) +" and removed=0";
		Object obj =CommonDao.GetOldDatabaseDao().fetchColumn(sql);
		if(obj !=null){
			mobile = obj.toString();
		}
		return mobile;	
	}
	
	public static String getIdByLoginName(String logiName) {
		String id="";
		String sqls = "select id from cs_user where login_name in (" + logiName
		+ ") and removed=0 order by id";
		Object obj =CommonDao.GetOldDatabaseDao().fetchColumn(sqls);
		if(obj!=null){
			id = obj.toString();
		}
		return id;
	}
	
	public static int getSequence(String seqName)  {
		int seq = 0;
		String sql = "select "+seqName+".nextval AS SEQUENCE FROM DUAL";
		Object obj = CommonDao.GetOldDatabaseDao().fetchColumn(sql);
		if(obj != null && !"".equals(obj)){
			seq = Integer.parseInt(obj+"");
		}
		return seq;
	}
	
	
	/*public static void insertMsg(String id, String mobile, String content,AbstractHibernateDaoImpl dao )  {
		try{
			Date date = null;
			Calendar cl = Calendar.getInstance();
			cl.setTime(new java.util.Date());
			date = cl.getTime();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = time.format(date);
			Session session = null;
			String sql="insert into T_SHORT_MSG(ID,STATUS,MOBILE,CONTENT,INSERTDATE)";
			sql+=" values('"+id+"',0,'"+mobile+"','"+content+"','"+dateTime+"')";
			if(dao !=null){
				session = dao.getHibernateTemplate().getSessionFactory().getCurrentSession();
			}
			else {
				dao = CommonDao.GetOldDatabaseDao();
				session = dao.getHibernateTemplate().getSessionFactory().openSession();
				
			}
			
			session.createSQLQuery(sql).executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/
	
	public static void insertMsg( String receivers, String content,AbstractHibernateDaoImpl dao )  {
		try{
			ShortMessageTask bo = new ShortMessageTask();
			bo.setReceiversMsg(receivers);
			bo.setContent(content);
			bo.setSendUserLoginname(AuthorUtil.GetLoginUserName());
			bo.setSendUserName(AuthorUtil.GetLoginUserRealName());
			bo.setPlanSendTime(new Date());
			bo.setSendType("1");
			bo.setRemoved("0");
			bo.setOperateTime(new Date());
			bo.setTaskStatus("1");
			//bo.setPeriod((long)1);
			dao.save(bo);
			
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public static String systemTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		c = Calendar.getInstance(Locale.CHINESE);
		return simpleDateTimeFormat.format(c.getTime());
	}
	
	public static OrganNode GetNodeByCode(String code){
		OrganNode node = null;
		OrganNodeType nodeType = null;
		try{
			String sql="select a.id,a.code,a.name,organ_node_type_id from CS_ORGAN_NODE a  where a.code='"+code+"'";
			CommonDao dao = CommonDao.GetOldDatabaseDao();
			List lst = dao.fetchAll(sql);
			if (lst !=null && lst.size()>0){
				Object[] arr = (Object[])lst.get(0);
				node = new OrganNode();
				node.setId(Long.parseLong(arr[0]+""));
				node.setCode(arr[1]+"");
				node.setName(arr[2]+"");
				if(arr[3]!=null){
					nodeType = new OrganNodeType();
					sql="select id,a.code,a.name,down,image,people,is_top,a.operator,a.removed from CS_ORGAN_NODE_type a where a.id="+arr[3];
					List lst2 = dao.fetchAll(sql);
					if (lst2 !=null && lst2.size()>0){
						Object[] type = (Object[])lst2.get(0);
						nodeType.setId(Long.parseLong(type[0]+""));
						nodeType.setCode(type[1]+"");
						nodeType.setName(type[2]+"");
						nodeType.setDown(Integer.parseInt(type[3]+""));
						nodeType.setImage(type[4]+"");
						nodeType.setPeople(Integer.parseInt(type[5]+""));
						nodeType.setTop(Integer.parseInt(type[6]+""));
						nodeType.setOperator(type[7]+"");
						nodeType.setRemoved(Integer.parseInt(type[8]+""));
						node.setOrganNodeType(nodeType);
					}
				}
				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return node;
		
	}
	
	public static OrganNode GetNodeById(String id){
		OrganNode node = null;
		OrganNodeType nodeType = null;
		try{
			String sql="select a.id,a.code,a.name,organ_node_type_id from CS_ORGAN_NODE a  where a.id='"+id+"'";
			CommonDao dao = CommonDao.GetOldDatabaseDao();
			List lst = dao.fetchAll(sql);
			if (lst !=null && lst.size()>0){
				Object[] arr = (Object[])lst.get(0);
				node = new OrganNode();
				node.setId(Long.parseLong(arr[0]+""));
				node.setCode(arr[1]+"");
				node.setName(arr[2]+"");
				if(arr[3]!=null){
					nodeType = new OrganNodeType();
					sql="select id,a.code,a.name,down,image,people,is_top,a.operator,a.removed from CS_ORGAN_NODE_type a where a.id="+arr[3];
					List lst2 = dao.fetchAll(sql);
					if (lst2 !=null && lst2.size()>0){
						Object[] type = (Object[])lst2.get(0);
						nodeType.setId(Long.parseLong(type[0]+""));
						nodeType.setCode(type[1]+"");
						nodeType.setName(type[2]+"");
						nodeType.setDown(Integer.parseInt(type[3]+""));
						nodeType.setImage(type[4]+"");
						nodeType.setPeople(Integer.parseInt(type[5]+""));
						nodeType.setTop(Integer.parseInt(type[6]+""));
						nodeType.setOperator(type[7]+"");
						nodeType.setRemoved(Integer.parseInt(type[8]+""));
						node.setOrganNodeType(nodeType);
					}
				}
				
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return node;
		
	}
	
	public static OrganTree getOrganTreeByCode(String code){
		OrganTree organTree = null;
		try{
		   String sql="select a.id,a.code,a.name,a.root_node_id,a.organ_tree_type_id from cs_organ_tree a where a.code='"+code+"'";
		   List list = CommonDao.GetOldDatabaseDao().fetchAll(sql);
		   if(list!= null && list.size()>0){
			   organTree = new OrganTree();
			   Object[] arr = (Object[])list.get(0);
			   organTree.setId(Long.parseLong(arr[0]+""));
			   organTree.setCode(arr[1]+"");
			   organTree.setName(arr[2]+"");
		   }
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return organTree;
	}
	
	public static OrganNode[] getOrganNodesByParentAndTree(Object treeId,
			Object parentNodeId) {
		OrganNode[] nodes = new OrganNode[0];
		try {
			List<OrganNode> nodeList = new ArrayList<OrganNode>();
				
			String sql = "select b.id,b.code,b.name,b.organ_node_type_id, ";
			sql+="c.code as  type_code,c.name as type_name,down,image,people,is_top,a.operator  ";
			sql+=" from cs_organ_model a ";
			sql+=" inner join CS_ORGAN_NODE b on a.org_node_id=b.id";
			sql+=" inner join CS_ORGAN_NODE_TYPE c on b.organ_node_type_id = c.id";
			sql+=" where a.nodestatus =0 and org_tree_id =" + treeId
					+ " and parent_node_id=" + parentNodeId +" order by a.orders ";
			CommonDao dao = CommonDao.GetOldDatabaseDao();
			List lst = dao.fetchAll(sql);
			if (lst != null) {
				for (int i = 0; i < lst.size(); i++) {
					Object[] arr = (Object[]) lst.get(i);
					OrganNode node = new OrganNode();
					node.setId(Long.parseLong(arr[0] + ""));
					node.setCode(arr[1] + "");
					node.setName(arr[2] + "");
					OrganNodeType nodeType = new OrganNodeType();
					nodeType.setId(Long.parseLong(arr[3] + ""));
					nodeType.setCode(arr[4] + "");
					nodeType.setName(arr[5] + "");
					nodeType.setDown(Integer.parseInt(arr[6] + ""));
					nodeType.setImage(arr[7] + "");
					nodeType.setPeople(Integer.parseInt(arr[8] + ""));
					nodeType.setTop(Integer.parseInt(arr[9] + ""));
					nodeType.setOperator(arr[10] + "");
					node.setOrganNodeType(nodeType);
					
					/*if (arr[3] != null) {
						OrganNodeType nodeType = new OrganNodeType();
						String sql1 = "select id,a.code,a.name,down,image,people,is_top,a.operator,a.removed from CS_ORGAN_NODE_type a where a.id="
								+ arr[3];
						List lst2 = dao.fetchAll(sql1);
						if (lst2 != null && lst2.size() > 0) {
							Object[] type = (Object[]) lst2.get(0);
							nodeType.setId(Long.parseLong(type[0] + ""));
							nodeType.setCode(type[1] + "");
							nodeType.setName(type[2] + "");
							nodeType.setDown(Integer.parseInt(type[3] + ""));
							nodeType.setImage(type[4] + "");
							nodeType.setPeople(Integer.parseInt(type[5] + ""));
							nodeType.setTop(Integer.parseInt(type[6] + ""));
							nodeType.setOperator(type[7] + "");
							nodeType.setRemoved(Integer.parseInt(type[8] + ""));
							node.setOrganNodeType(nodeType);
						}
					}*/
					nodeList.add(node);
					

				}
				nodes = nodeList.toArray(nodes);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nodes;
	}
	
	
	public static List<Map> getUsersByOrganOrder(String nodeId){
		List<Map> usersList = new ArrayList<Map>();
		try{
			String sql="select SECURITY_USER_ID,ORDERS from CS_USER_ORGANNODE where ORGAN_NODE_ID=" + nodeId + " order by ORDERS asc";
			List lst = CommonDao.GetOldDatabaseDao().fetchAll(sql);
			if(lst != null){
				for (int i = 0; i < lst.size(); i++) {
					Map row = new HashMap<String, Object>();
					Object[] arr = (Object[]) lst.get(i);
					row.put(NodeUserDAO.SECURITY_SUER_ID, arr[0]);
					row.put(NodeUserDAO.ORDERS, arr[1]);
					usersList.add(row);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return usersList;
	}
	
	public static SecurityUser loadUserById(Object id){
		SecurityUser user = null;
		try{
			String sql = "select id,login_name,name,sex,mobile1 from cs_user where id="+id;
			List lst = CommonDao.GetOldDatabaseDao().fetchAll(sql);
			user = new SecurityUser();
			if (lst !=null && lst.size()>0){
				Object[] arr = (Object[]) lst.get(0);
				user.setId(Long.parseLong(arr[0]+""));
				user.setLoginName(arr[1]+"");
				user.setName(arr[2]+"");
				user.setSex(arr[3]+"");
				user.setMobile1(arr[4]+"");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return user;
	}
	
	
	public static List<UserAndOrder> getUsesByOrgNode(Object nodeId){
		List<UserAndOrder >uoList = new ArrayList<UserAndOrder>();
		try{
			String sql = "select  b.id,login_name,name,sex,a.ORDERS from CS_USER_ORGANNODE a inner join cs_user b on a.SECURITY_USER_ID = b.id ";
			sql+=" where a.ORGAN_NODE_ID="+nodeId;
			sql+=" order by a.orders ";
			List lst = CommonDao.GetOldDatabaseDao().fetchAll(sql);
			
			if (lst !=null){
				for(int i =0;i<lst.size();i++){
					UserAndOrder uo =  new UserAndOrder();
					SecurityUser user = new SecurityUser();
					Object[] arr = (Object[]) lst.get(i);
					user.setId(Long.parseLong(arr[0]+""));
					user.setLoginName(arr[1]+"");
					user.setName(arr[2]+"");
					user.setSex(arr[3]+"");
					
					uo.setUser(user) ;
					uo.setOrders(arr[4]+"");
					uoList.add(uo);
				}
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return uoList;
	}
	
	
	 

	
	
}
