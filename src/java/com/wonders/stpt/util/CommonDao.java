package com.wonders.stpt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;
public class CommonDao extends AbstractHibernateDaoImpl<Object> {

	public static CommonDao GetOldDatabaseDao() {
		CommonDao dao = null;
		HttpServletRequest res = null;
		try {
			dao = (CommonDao) WebApplicationContextUtils
					.getWebApplicationContext(
					CommonDao.GetRequest().getSession()
					.getServletContext()).getBean("connectionOld");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return dao;
	}
	
	
	

	public static CommonDao GetNewDatabaseDao() {
		CommonDao dao = null;

		try {
			dao = (CommonDao) WebApplicationContextUtils
					.getWebApplicationContext(
							CommonDao.GetRequest().getSession()
					.getServletContext()).getBean("connectionNew");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dao;
	}

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

	public  Session getOpenedSession(){
		Session session = null;
		HttpServletRequest res = null;
		try{
			res = CommonDao.GetRequest();
			if(res.getAttribute("opened_session")==null){
				session = this.getHibernateTemplate().getSessionFactory().openSession();
				
				System.out.println( "["+GlobalFunc.systemTime() +"]Connection [" + session.hashCode()
						+ "] opened by thread:" + Thread.currentThread().hashCode());
				
				res.setAttribute("opened_session", session);
			}
			session = (Session)res.getAttribute("opened_session");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return session ;
	}
	
	public boolean executeSql(String sql) {

		boolean success = false;
		Transaction tx = null;
		Session session = null;

		try {
			
			this.getOpenedSession().createSQLQuery(sql).executeUpdate();
			
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (session != null) {
				//session.close();
			}
		}
		return success;
	}

	public List fetchPage(String sql, int startIndex, int pageSize) {

		Session session = null;
		List list = new ArrayList();
		try {
			
			SQLQuery query = this.getOpenedSession().createSQLQuery(sql);
			list = query.setFirstResult(startIndex).setMaxResults(pageSize)
					.list();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (session != null) {
				//session.close();
			}
		}
		return list;
	}
	
	public List fetchAll(String sql) {

		Session session = null;
		List list = new ArrayList();
		try {
			
			SQLQuery query = this.getOpenedSession().createSQLQuery(sql);
			list = query.list();
			
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (session != null) {
				//session.close();
			}
		}
		return list;
	}
	
	
	
	
	
	public Object fetchColumn(String sql) {

		Session session = null;
		Object obj= null;
		try {
			
			session =this.getOpenedSession();
			
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			if(list!= null && list.size()>0){
				obj = list.get(0);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (session != null) {
				//session.close();
			}
		}
		return obj;
	}
	
	public Object fetchColumnWithNewSession(String sql) {

		Session session = null;
		Object obj= null;
		try {
			
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			if(list!= null && list.size()>0){
				obj = list.get(0);
			}
			session.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (session != null) {
				//session.close();
			}
		}
		return obj;
	}
	
	
	public Object doSave(Object obj){

		Session session = null;
		
		try {
			session =this.getOpenedSession();
			
			session.saveOrUpdate(obj);
		} catch (Exception ex) {
			ex.printStackTrace();

		} 
		return obj;
	}

}
