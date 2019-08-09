package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyUtility {
	
	private static SessionFactory sessionFactory=null;
	public static Session getSession(){
		
      
	  Session session =(MyUtility.getSessionFactory()).openSession();
	  
	  
	  return session;
		  
		 
		
	} 
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory==null)
		{
		sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}

}
