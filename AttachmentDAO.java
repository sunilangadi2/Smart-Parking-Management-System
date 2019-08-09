package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import vo.AttachmentVO;
import controller.MyUtility;

@SuppressWarnings("rawtypes")
public class AttachmentDAO {
	
	
	public void insert(AttachmentVO VO_obj){
		
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
	
	
	
  public List getElementByID(AttachmentVO VO_obj){
		
		Session session = null;
		List temp = null;
		
		
		try{
		  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static
		
		    Transaction tr = session.beginTransaction();
		 
		    Query q = session.createQuery("FROM AttachmentVO WHERE attachmentID = '"+VO_obj.getAttachmentID()+"'");
		    temp = q.list();
		
		    tr.commit();
		 
		 }catch(Exception e){
			                e.printStackTrace();
		  					}finally{
		  							
		  							session.close();
		  
		  							}
		  
		 return temp;
		
			
	}
  
  public void update(AttachmentVO VO_obj){
	  
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
	
  
	public void delete(AttachmentVO VO_obj){
		
		Session session = null;
		
		 try{
			  
			 session = MyUtility.getSession();// Static Method which makes only one object as method is static
			 
			  Transaction t= session.beginTransaction();

/*			 
 *          Method 1 
 *          Query q = session.createQuery("from AttachmentVO where dID = :dID ");
			  q.setParameter("dID", VO_obj.getdID());
			  AttachmentVO dvo = (AttachmentVO)q.list().get(0);
*/
			
			 /* Method 2 */ 
			  AttachmentVO dvo = (AttachmentVO)session.get(AttachmentVO.class, VO_obj.getAttachmentID());
			  
			  

			  session.delete(dvo);
			
			  t.commit();
			
			
			  }catch(Exception e){
				  	e.printStackTrace();
				  				//System.out.println(e.getMessage());
			  					}finally{
			  							session.close();
			  
			  							}
			  
			  
		
	}
	
  public List showAll(){
		
		Session session = null;
		List temp = null;
		  try{
		  
			  session = MyUtility.getSession();// Static Method which makes only one object as method is static
		
			  Query q = session.createQuery("FROM AttachmentVO ");
		 
			  temp = q.list();
		  	
		  }catch(Exception e){
			  				 e.printStackTrace();
		  					 }finally{
		  						     session.close();
		  					 		 }
		 return temp;
		
		}	
  
  public List  search(AttachmentVO VO_obj){
		
		Session session = null;
	    List temp = null;
	 
	    try{
	 
	    	session = MyUtility.getSession();// Static Method which makes only one object as method is static
			

	    	Query q = session.createQuery("FROM AttachmentVO WHERE attachmentType like '%"+VO_obj.getAttachmentType() +"%' ");

	    	temp = q.list();
	
	    }catch(Exception e){
	 		    	System.out.println(e.getMessage());
	    		}finally{
	    				session.close();
	    				}
	return temp;
}
	
	
	

}
