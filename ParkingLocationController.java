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

import vo.ParkingEmployeeVO;
import vo.ParkingVO;
import vo.PersonVO;
import vo.UserVO;
import dao.ParkingDAO;
import dao.ParkingEmployeeDAO;
import dao.PaymentDAO;
import dao.PersonDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import dao.UserLogDAO;



/**
 * Servlet implementation class Circular
 */
@SuppressWarnings("rawtypes")
@WebServlet("/ParkingLocationController")
public class ParkingLocationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingLocationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   
		
		ParkingDAO DAO_obj=new ParkingDAO();
		
		List obj= DAO_obj.showAll();
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/user/Parking/selectParking.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(Long.valueOf(request.getParameter("id")));
		
		 ParkingDAO DAO_obj=new ParkingDAO();
			
			List obj= DAO_obj.getElementByID(parkingVO);
			
			Iterator itr = obj.iterator();
			       
			ParkingVO parkingVO2   =(ParkingVO)   itr.next();
			
			
			HttpSession session = request.getSession();
			session.setAttribute("lat", parkingVO2.getMapLat());
			session.setAttribute("lon", parkingVO2.getMapLon());
			session.setAttribute("obj_all", obj);
			response.sendRedirect(request.getContextPath()+"/views/user/Parking/showLocation.jsp");
			
			
			
			
		
		
	}
}
