package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.BookingVO;
import vo.ParkingVO;
import vo.PreBookingVO;
import vo.SlotsVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class PreBookingDAO {
	
	
	public void insert(PreBookingVO VO_obj){
		
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
	
	
	

public List getElementByID(PreBookingVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM PreBookingVO WHERE preBookingID = '"+VO_obj.getPreBookingID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(PreBookingVO VO_obj){
	  
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
	
  
	public void delete(PreBookingVO VO_obj){
		
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

		
			  Query q = session.createQuery("FROM PreBookingVO WHERE status = 1 ");
		 
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
		List<SlotsVO> temp = new ArrayList<SlotsVO>();
		List tempData = null;
		  try{
		  
				 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

		
			  Query q = session.createQuery("FROM PreBookingVO where status = 1 AND parkingID.parkingID ="+parkingVO.getParkingID());
		 
			  tempData = q.list();
			  
			  Iterator itr = tempData.iterator();
			  
			
			  
			  while (itr.hasNext()) {
				  PreBookingVO boVo = (PreBookingVO) itr.next();
				
				  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					
					
				  Date dateExt2 = null;
					try {
						dateExt2 = format.parse(boVo.getExtendedTime2());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  
					Date now = new Date();
				  
					
				  if(dateExt2.getTime() >= now.getTime() ){
				  temp.add(boVo.getSlotID());
				  
				  }
				  
				  
				  
			}
			  
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		 
		  					 
		  					}
		 return temp;
		
		}
  public List  search(PreBookingVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM PreBookingVO WHERE categoryName like '%"+VO_obj.getPreBookingID()+"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
  
  public List  getPreBookingByParkingID(ParkingVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static 

	    	Query q = session.createQuery("FROM PreBookingVO WHERE status = 1 AND parkingID.parkingID = "+VO_obj.getParkingID()+" ORDER BY preBookingID DESC ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	

}
