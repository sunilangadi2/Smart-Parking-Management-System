package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.ParkingChargeVO;
import vo.ParkingVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class ParkingChargeDAO {
	
	
	public void insert(ParkingChargeVO VO_obj){
		
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
	
	
	

public List getElementByID(ParkingChargeVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM ParkingChargeVO WHERE parkingChargeID = '"+VO_obj.getParkingChargeID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(ParkingChargeVO VO_obj){
	  
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
	
  
	public void delete(ParkingChargeVO VO_obj){
		
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

		
			  Query q = session.createQuery("FROM ParkingChargeVO ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  public List showAllByParkingVO(ParkingVO parkingVO){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM ParkingChargeVO "
			  		+ " where parkingID.parkingID ="+parkingVO.getParkingID());
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}
  public List  search(ParkingChargeVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM ParkingChargeVO WHERE parkingChargeID like '%"+VO_obj.getParkingChargeID()+"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
	

}
