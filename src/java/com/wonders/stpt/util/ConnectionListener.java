package com.wonders.stpt.util;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

public class ConnectionListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent e) {
		try {
			ServletRequest res = e.getServletRequest(); 
			if (res != null && res.getAttribute("opened_session") != null) {
				
				Session session = (Session)res.getAttribute("opened_session");
				
				if(	!session.getSessionFactory().isClosed()){
					session.close();
					//session.getSessionFactory().close();
					System.out.println( "["+GlobalFunc.systemTime() +"]:Connection [" + session.hashCode()
							+ "] has been closed which opened by thread:" + Thread.currentThread().hashCode());
				}
				res.removeAttribute("opened_dao");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

}
