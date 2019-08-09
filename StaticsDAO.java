package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.MyUtility;

public class StaticsDAO {

	
	public List totalBookingParkingeWise(){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("select parkingID.parkingName,parkingID.parkingID, count(*) FROM BookingVO "
		    		+ " GROUP BY parkingID ");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}

	
	public List totalParkingeWise(){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("select parkingID.parkingName,parkingID.parkingID, sum(paymentFinal) FROM BookingVO "
		    		+ " GROUP BY parkingID ");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
	
}
