package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ParkingVO;
import vo.PersonVO;
import vo.UserVO;
import vo.VehicleVO;
import dao.VehicleDAO;



/**
 * Servlet implementation class VehicleContoller
 */
@WebServlet("/RU_VehicleController")
public class RU_VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RU_VehicleController() {
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
			response.sendRedirect(request.getContextPath()+"/views/user/Vehicle/addVehicleDetails.jsp");
			
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
						response.sendRedirect(request.getContextPath()+"/views/user/Vehicle/addVehicleDetails.jsp");
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
		
		
		
		String model = request.getParameter("model");
		String numberOfVehicle = request.getParameter("numberOfVehicle");
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		
		VehicleVO VO_obj = new VehicleVO();
		VO_obj.setModel(model);
		VO_obj.setNumberOfVehicle(numberOfVehicle);
		VO_obj.setUserID(userVO);
		
		
		
		
		
		
		VehicleDAO DAO_obj=new VehicleDAO();
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
    	
	
		
		long vehicleID = Long.valueOf(request.getParameter("vehicleID"));
		String model = request.getParameter("model");
		String numberOfVehicle = request.getParameter("numberOfVehicle");
		
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		
		VehicleVO VO_obj = new VehicleVO();
		VO_obj.setVehicleID(vehicleID);
		VO_obj.setModel(model);
		VO_obj.setNumberOfVehicle(numberOfVehicle);
		VO_obj.setUserID(userVO);
		
		
		
		
		
		
		
		
		
		VehicleDAO DAO_obj=new VehicleDAO();
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long vehicleID=Long.parseLong(request.getParameter("id"));
    	VehicleVO VO_obj =new VehicleVO();
		VO_obj.setVehicleID(vehicleID);
		
		VehicleDAO DAO_obj=new VehicleDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/user/Vehicle/editVehicleDetails.jsp");
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
		
    	Long vehicleID=Long.valueOf(request.getParameter("id"));
    	VehicleVO VO_obj =new VehicleVO();
		VO_obj.setVehicleID(vehicleID);
		
		VehicleDAO DAO_obj=new VehicleDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    		
    	UserVO VO_obj =new UserVO();
		VO_obj.setUserID(userID);
		
		VehicleVO vehicleVO = new VehicleVO();
		vehicleVO.setUserID(VO_obj);
		
		VehicleDAO DAO_obj=new VehicleDAO();
		List obj= DAO_obj.search(vehicleVO);
		
	
		
		
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/user/Vehicle/manageVehicleDetails.jsp");
	
	}

}
