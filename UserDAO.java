package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.UserVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class UserDAO {
	
	
	public void insert(UserVO VO_obj){
		
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
	
	
	

public List getElementByID(UserVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM UserVO WHERE userID = '"+VO_obj.getUserID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
public List login(UserVO VO_obj){
	
	Session session = null;
	List temp = null;
	
	
	try{
	  
		 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
	
	    Transaction tr = session.beginTransaction();
	 
	    Query q = session.createQuery("FROM UserVO WHERE userName = '"+VO_obj.getUserName()+"' AND password = '"+VO_obj.getPassword()+"' AND status = 1 ");
	    temp = q.list();
	
	    tr.commit();
	 
	 }catch(Exception e){
		                e.printStackTrace();
	  					}finally{
	  							
	  							session.close();
	  
	  							}
	  
	 return temp;
	
		
}



public List employeeLogin(UserVO VO_obj){
	
	Session session = null;
	List temp = null;
	
	
	try{
	  
		 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
	
	    Transaction tr = session.beginTransaction();
	 
	    Query q = session.createQuery("FROM ParkingEmployeeVO WHERE userID.userID = "+VO_obj.getUserID()+"  ");
	    temp = q.list();
	
	    tr.commit();
	 
	 }catch(Exception e){
		                e.printStackTrace();
	  					}finally{
	  							
	  							session.close();
	  
	  							}
	  
	 return temp;
	
		
}
  public void update(UserVO VO_obj){
	  
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
	
  
	public void delete(UserVO VO_obj){
		
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
	
  public List showAllRegisterUser(){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM UserVO where userType = 1 and  status = 1 ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  public List showAllAdmin(){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM UserVO where userType = 3 and status = 1 ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  
  public List showAllEmployee(){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM UserVO where userType = 2 and  status = 1 ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  public List showAll(){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM UserVO where status = 1 	");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  

  public List  search(UserVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM UserVO WHERE userID like '%"+VO_obj.getUserID()+"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
	

}
