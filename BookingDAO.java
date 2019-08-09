package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.BookingVO;
import vo.ParkingVO;
import vo.SlotsVO;
import vo.UserVO;
import vo.VehicleVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class BookingDAO {
	
	
	  public List  getBookingByParkingID(ParkingVO VO_obj){
			
			Session session = null;
		    List temp = null;
		 
		    try{
		 
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		    	Query q = session.createQuery("FROM BookingVO WHERE parkingID.parkingID = "+VO_obj.getParkingID()+" ORDER BY bookingID DESC ");

		    	temp = q.list();
		
		    }catch(Exception e){
		 		    	System.out.println(e.getMessage());
		    		}finally{
		    				session.close();
		    				}
		return temp;
	}
	  
	public void insert(BookingVO VO_obj){
		
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
	
	
	

public List getElementByID(BookingVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM BookingVO WHERE bookingID = '"+VO_obj.getBookingID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(BookingVO VO_obj){
	  
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
	
  
	public void delete(BookingVO VO_obj){
		
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
	
  public List showAllofUser(UserVO userVO){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM BookingVO where userID.userID = "+userVO.getUserID()+" ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  
  public List showAllofUserLogin(UserVO userVO){
		
		Session session = null;
		List temp = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM BookingVO where userID.userID = "+userVO.getUserID()+""
			  		+ " AND outTime IS NULL ");
		 
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

		
			  Query q = session.createQuery("FROM BookingVO ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  public List showAllBookedSlots(ParkingVO parkingVO){
		
		Session session = null;
		List<SlotsVO> temp = new ArrayList<SlotsVO>();
		List tempData = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM BookingVO where parkingID.parkingID ="+parkingVO.getParkingID()+""
			  		+ " and outTime IS NULL");
		 
			  tempData = q.list();
			  
			  Iterator itr = tempData.iterator();
			  
			  
			  while (itr.hasNext()) {
				  BookingVO boVo = (BookingVO) itr.next();
				  temp.add(boVo.getSlotID());
			}
			  
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  
  public List showAllBookedSlotsVehicles(ParkingVO parkingVO){
		
		Session session = null;
		List temp = null;
		
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM BookingVO where parkingID.parkingID ="+parkingVO.getParkingID()+""
			  		+ " and outTime IS NULL");
		 
			  temp = q.list();
			  
			  
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  
  public List showVehicleForGivenParking(ParkingVO parkingVO,VehicleVO vehicleVO){
		
		Session session = null;
		List temp = null;
		
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM BookingVO where vehicleID.vehicleID = "+vehicleVO.getVehicleID()+"  "
			  		+ " AND parkingID.parkingID ="+parkingVO.getParkingID()+""
			  		+ " and outTime IS NULL");
		 
			  temp = q.list();
			  
			  
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  public List  search(BookingVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM BookingVO WHERE categoryName like '%"+VO_obj.getBookingID()+"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
	

}
