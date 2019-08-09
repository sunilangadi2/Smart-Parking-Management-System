package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ParkingChargeVO;
import vo.ParkingVO;
import dao.ParkingChargeDAO;



/**
 * Servlet implementation class Circular
 */
@WebServlet("/ParkingChargeController")
public class ParkingChargeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingChargeController() {
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
			response.sendRedirect(request.getContextPath()+"/views/employee/ParkingCharges/addParkingCharge.jsp");
			
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
						response.sendRedirect(request.getContextPath()+"/views/employee/ParkingCharges/addParkingCharge.jsp");
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
		
		long parkingID = (long)(session.getAttribute("parkingID"));
		float charge = Float.valueOf(request.getParameter("charge"));;
		float upperLimit = Float.valueOf(request.getParameter("upperLimit"));;
		float lowerLimit = Float.valueOf(request.getParameter("lowerLimit"));;
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parkingID);
		
		
		ParkingChargeVO VO_obj =new ParkingChargeVO();
		VO_obj.setParkingID(parkingVO);
		VO_obj.setCharge(charge);
		VO_obj.setLowerLimit(lowerLimit);
		VO_obj.setUpperLimit(upperLimit);
		
		
		
		
		ParkingChargeDAO DAO_obj=new ParkingChargeDAO();
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
		long parkingChargeID = Long.valueOf(request.getParameter("parkingChargeID"));
		
		float charge = Float.valueOf(request.getParameter("charge"));;
		float upperLimit = Float.valueOf(request.getParameter("upperLimit"));;
		float lowerLimit = Float.valueOf(request.getParameter("lowerLimit"));;
		
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parkingID);
		
		
		ParkingChargeVO VO_obj =new ParkingChargeVO();
		VO_obj.setParkingChargeID(parkingChargeID);
		VO_obj.setParkingID(parkingVO);
		VO_obj.setCharge(charge);
		VO_obj.setLowerLimit(lowerLimit);
		VO_obj.setUpperLimit(upperLimit);
		
		
		
		ParkingChargeDAO DAO_obj=new ParkingChargeDAO();
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long parkingChargeID=Long.parseLong(request.getParameter("id"));
    	ParkingChargeVO VO_obj =new ParkingChargeVO();
		VO_obj.setParkingChargeID(parkingChargeID);
		
		ParkingChargeDAO DAO_obj=new ParkingChargeDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/employee/ParkingCharges/editParkingCharge.jsp");
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
		
    	int parkingChargeID=Integer.parseInt(request.getParameter("id"));
    	ParkingChargeVO VO_obj =new ParkingChargeVO();
		VO_obj.setParkingChargeID(parkingChargeID);
		
		ParkingChargeDAO DAO_obj=new ParkingChargeDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ParkingChargeDAO DAO_obj=new ParkingChargeDAO();
    	HttpSession session = request.getSession();
    	
    	
    	
    	ParkingVO parkingVO = new ParkingVO();
    	parkingVO.setParkingID((long)(session.getAttribute("parkingID")));
		List obj= DAO_obj.showAllByParkingVO(parkingVO);
		
		
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/employee/ParkingCharges/showParkingCharges.jsp");
	
	}

}
