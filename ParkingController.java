package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttachmentDAO;
import dao.ParkingDAO;
import vo.AttachmentVO;
import vo.ParkingVO;
import vo.UserVO;
import vo.VehicleVO;

/**
 * Servlet implementation class 
 */
@WebServlet("/ParkingController")
public class ParkingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingController() {
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
		}else{
			//default Add
			response.sendRedirect(request.getContextPath()+"/views/admin/Parking/addParking.jsp");
			
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
						response.sendRedirect(request.getContextPath()+"/views/admin/Parking/addParking.jsp");
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
		
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String parkingName = request.getParameter("parkingName");
		String landmark = request.getParameter("landmark");
		double commission = Double.valueOf(request.getParameter("commission"));
		double mapLat = Double.valueOf(request.getParameter("mapLat"));
		double mapLon = Double.valueOf(request.getParameter("mapLon"));
		String pincode = request.getParameter("pincode");

		
		ParkingVO VO_obj =new ParkingVO();
		
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
				
				

		
		
		
		
		
		
		VO_obj.setAddressLine1(addressLine1);
		VO_obj.setAddressLine2(addressLine2);
		VO_obj.setCommission(commission);
		VO_obj.setLandmark(landmark);
		VO_obj.setMapLat(mapLat);
		VO_obj.setMapLon(mapLon);
		VO_obj.setParkingName(parkingName);
		VO_obj.setPincode(pincode);
		VO_obj.setStatus(1);
		
		
		
		
		ParkingDAO DAO_obj=new ParkingDAO();
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
    	
	
		String addressLine1 = request.getParameter("addressLine1");
		String addressLine2 = request.getParameter("addressLine2");
		String parkingName = request.getParameter("parkingName");
		String landmark = request.getParameter("landmark");
		double commission = Double.valueOf(request.getParameter("commission"));
		double mapLat = Double.valueOf(request.getParameter("mapLat"));
		double mapLon = Double.valueOf(request.getParameter("mapLon"));
		String pincode = request.getParameter("pincode");
		long parkingID = Long.valueOf(request.getParameter("parkingID"));
		
		ParkingVO VO_obj =new ParkingVO();
		VO_obj.setParkingID(parkingID);
		VO_obj.setAddressLine1(addressLine1);
		VO_obj.setAddressLine2(addressLine2);
		VO_obj.setCommission(commission);
		VO_obj.setLandmark(landmark);
		VO_obj.setMapLat(mapLat);
		VO_obj.setMapLon(mapLon);
		VO_obj.setParkingName(parkingName);
		VO_obj.setPincode(pincode);
		VO_obj.setStatus(1);
		
		
		
		
		ParkingDAO DAO_obj=new ParkingDAO();
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long ParkingID=Long.parseLong(request.getParameter("id"));
    	ParkingVO VO_obj =new ParkingVO();
		VO_obj.setParkingID(ParkingID);
		
		ParkingDAO DAO_obj=new ParkingDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/Parking/editParkingInfo.jsp");
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
		
    	int ParkingID=Integer.parseInt(request.getParameter("id"));
    	ParkingVO VO_obj =new ParkingVO();
		VO_obj.setParkingID(ParkingID);
		
		ParkingDAO DAO_obj=new ParkingDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		ParkingVO vo2 = null; 
		
		if(obj.size()==1){
				Iterator itr = obj.iterator();
		
				vo2 = (ParkingVO) itr.next();
				
				vo2.setStatus(0);
				DAO_obj.update(vo2);
				
				
		}else{
			System.out.println("nothing to delete");
		}
		
		
		
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ParkingDAO DAO_obj=new ParkingDAO();
		List obj= DAO_obj.showAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/Parking/manageParking.jsp");
	
	}

}
