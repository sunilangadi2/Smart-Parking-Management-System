package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import controller.MyUtility;
import vo.FeedBackVO;


public class FeedBackDAO {
	
	
	public void insert(FeedBackVO VO_obj){
		
		Session session = null;
		
		 try{
			  
			   session = MyUtility.getSession();// Static Method which makes only one object as method is static
			  
			 
			  
			  Transaction t= session.beginTransaction();
			
			  session.save(VO_obj);
			  
			  t.commit();
			
			
	    }catch(Exception e){
			 e.printStackTrace();
			  
			  }finally{
			        
				      //session.close();
			  
			          }
			  
			  
		
	}
	
	
	
  public List getElementByID(FeedBackVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
		     session = MyUtility.getSession();// Static Method which makes only one object as method is static
		    
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM FeedBackVO WHERE feedbackID = '"+VO_obj.getFeedBackID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(FeedBackVO VO_obj){
	  
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
	
  
	public void delete(FeedBackVO VO_obj){
		
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
			  
		
			  Query q = session.createQuery("FROM FeedBackVO ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     //session.close();
		  					 		 }
		 return temp;
		
		}	
	
	
	public List  search(FeedBackVO VO_obj){
		
			Session session = null;
		    List temp = null;
		 
		    try{
		 
		    	 session = MyUtility.getSession();// Static Method which makes only one object as method is static
		    	

		    	Query q = session.createQuery("FROM FeedBackVO WHERE name like '%"+VO_obj.getName()+"%' ");
	
		    	temp = q.list();
		
		    }catch(Exception e){
		 		    	System.out.println(e.getMessage());
		    		}finally{
		    				session.close();
		    				}
		return temp;
	}

}
