package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.AttachmentVO;
import vo.ParkingVO;
import vo.SlotsVO;
import vo.UserVO;
import dao.AttachmentDAO;
import dao.ParkingDAO;
import dao.SlotsDAO;



/**
 * Servlet implementation class Circular
 */
@WebServlet("/SlotsController")
public class SlotsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// mode=0 Add New
		//mode=1 Insert
		//mode=2 Edit
		//mode=3 update
		//mode=4 Delete
		//mode=5 Show 		
		
		//Mode Type defined to String to control over illegal parameter passing through URL
		String mode="0";
		 
		//Getting the mode variable, if not than default to 0(zero)  
		if(request.getParameter("mode")!=null)
		   mode = request.getParameter("mode");
		else
			mode="0";
		
		
		if(mode.equals("2")){
			edit(request, response);
		}
		else if(mode.equals("4")) {
			delete(request, response);
			
		}else if(mode.equals("5")){
			show(request, response);
		}else if(mode.equals("6")){
			response.sendRedirect(request.getContextPath()+"/views/admin/Parking/addSlots.jsp");
			
		}else{
			//default Add
			HttpSession session = request.getSession();
			session.setAttribute("parkingID", Long.valueOf(request.getParameter("id")));
			show(request, response);
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// mode=0 Add New
		//mode=1 Insert
		//mode=2 Edit
		//mode=3 update
		//mode=4 Delete
		//mode=5 Show 
		//Mode Type defined to String to control over illegal parameter passing through URL
		 String mode="0";
		
		//Getting the mode variable, if not than default to 0(zero) 
		if(request.getParameter("mode")!=null)
			   mode = request.getParameter("mode");
			else
				doGet(request, response);
			
			
			if(mode.equals("1")){
				       insert(request, response);
			}else if(mode.equals("3")){
						update(request, response);
			}else{
						//default Add
				      HttpSession session = request.getSession();
				      session.setAttribute("parkingID", Long.valueOf(request.getParameter("id")));
						show(request, response);
						}//default
					
			
			
			
			
		
		
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
		
		
		long parkingID = Long.valueOf(request.getParameter("parkingID"));
		String description = request.getParameter("description");
	
		String slotName = request.getParameter("slotName");
		
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parkingID);
		
		
		SlotsVO VO_obj = new SlotsVO();
		
		AttachmentVO attachmentVO = new AttachmentVO();

		//getting file from Session
		
		
		
				List myList=(List)session.getAttribute("fileList");
				
				 Iterator itr =  myList.iterator();
		         
		         System.out.println("Reading for session");
		         int i =0;
		         while (itr.hasNext()) {
		        	 
		        	
		     		attachmentVO.setAttachmentType("Parking data");
		     		
		        	 //file name
		     		attachmentVO.setPath((String)itr.next());
		        	 
		        	
		        	 
		        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
			     		attachmentDAO.insert(attachmentVO);
			     
		        	 
		        	 i++;
					}
				
		         VO_obj.setAttachmentID(attachmentVO);
				
				session.removeAttribute("fileList");
				

		
		VO_obj.setDescription(description);
		VO_obj.setAttachmentID(attachmentVO);
		VO_obj.setParkingID(parkingVO);
		VO_obj.setSlotName(slotName);
		
		
		
		
		
		
		SlotsDAO DAO_obj=new SlotsDAO();
		DAO_obj.insert(VO_obj);
		
		show(request, response);
		
	}
	
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
    	
	
		long parkingID = Long.valueOf(request.getParameter("parkingID"));
		long slotID = Long.valueOf(request.getParameter("slotID"));
		String description = request.getParameter("description");
		
		String slotName = request.getParameter("slotName");
		
		SlotsDAO DAO_obj=new SlotsDAO();
		SlotsVO vo1 = null;
		
		
		
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parkingID);
		
		SlotsVO VO_obj = new SlotsVO();
		VO_obj.setSlotID(slotID);
		VO_obj.setDescription(description);
		
		VO_obj.setParkingID(parkingVO);
		VO_obj.setSlotName(slotName);
		
		
		
		
		
	
		if(session.getAttribute("fileList") != null){
			 AttachmentVO attachmentVO = new AttachmentVO();

				//getting file from Session
				
				
				
						List myList=(List)session.getAttribute("fileList");
						
						 Iterator itr =  myList.iterator();
				         
				         System.out.println("Reading for session");
				         int i =0;
				         while (itr.hasNext()) {
				        	 
				        	
				     		attachmentVO.setAttachmentType("Parking Data");
				     		
				        	 //file name
				     		attachmentVO.setPath((String)itr.next());
				        	 
				        	
				        	 
				        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
					     		attachmentDAO.insert(attachmentVO);
					     
				        	 
				        	 i++;
							}
						
				         VO_obj.setAttachmentID(attachmentVO);
						
						session.removeAttribute("fileList");
						
						VO_obj.setAttachmentID(attachmentVO);
			
			
		}else{
	
			List lsTemp = DAO_obj.getElementByID(VO_obj);
			Iterator itrTemp = lsTemp.iterator();
			
			vo1 = (SlotsVO)itrTemp.next();
			
			VO_obj.setAttachmentID(vo1.getAttachmentID());
		}
		
		
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long slotID=Long.parseLong(request.getParameter("id"));
    	SlotsVO VO_obj =new SlotsVO();
		VO_obj.setSlotID(slotID);
		
		SlotsDAO DAO_obj=new SlotsDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/Parking/editSlotInfo.jsp");
	}
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		
		/*This code fetches the user Id of logged in User 
		Begin*/ 
		HttpSession session = request.getSession();
		long userID;
		if (session.getAttribute("userID") != null) {

			userID = (long) session.getAttribute("userID");

		} else {
			userID=1;
		}
		/*ENDS fetching user id*/
		
    	Long slotID=Long.valueOf(request.getParameter("id"));
    	SlotsVO VO_obj =new SlotsVO();
		VO_obj.setSlotID(slotID);
		
		SlotsDAO DAO_obj=new SlotsDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SlotsDAO DAO_obj=new SlotsDAO();
    	SlotsVO slotsVO = new SlotsVO();
    	HttpSession session = request.getSession();
    	
    	ParkingVO parkingVO = new ParkingVO();
    	parkingVO.setParkingID((Long)(session.getAttribute("parkingID")));
    	
    	slotsVO.setParkingID(parkingVO);
    	
		List obj= DAO_obj.search(slotsVO);
		
		ParkingDAO dao = new ParkingDAO();
		List parking= dao.getElementByID(parkingVO);
		
		session.setAttribute("obj", parking);
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/Parking/manageSlots.jsp");
	
	}

}
