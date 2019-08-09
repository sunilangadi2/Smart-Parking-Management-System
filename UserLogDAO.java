package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.UserLogVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class UserLogDAO {
	
	
	public void insert(UserLogVO VO_obj){
		
		Session session = null;
		
		 try{
			  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
			  
			  Transaction t= session.beginTransaction();
			
			  session.save(VO_obj);
			  
			  t.commit();
			
			
	    }catch(Exception e){
			 e.printStackTrace();
			  
			  }finally{
			        
				      session.close();
			  
			          }
			  
			  
		
	}
	
	
	

public List getElementByID(UserLogVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM UserLogVO WHERE logID = '"+VO_obj.getLogID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(UserLogVO VO_obj){
	  
		Session session = null;
		
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
			  Transaction tr = session.beginTransaction();
		 
			  session.update(VO_obj);
		 
			  tr.commit();
		 
		  }catch(Exception e){
			  				
			  				System.out.println(e.getMessage());
		  					}finally{
		  						    session.close();
		  							}
		  
		}
	
  
	public void delete(UserLogVO VO_obj){
		
		Session session = null;
		
		 try{
			  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

			  Transaction t= session.beginTransaction();

			  session.delete(VO_obj);
			
			  t.commit();
			
			
			  }catch(Exception e){
				  	
				  				System.out.println(e.getMessage());
			  					}finally{
			  							session.close();
			  
			  							}
			  
			  
		
	}
	
  public List showAll(){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM UserLogVO ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  public List  search(UserLogVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM UserLogVO WHERE categoryName like '%"+VO_obj.getUserID()+"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
	

}
