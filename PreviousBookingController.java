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

import dao.BookingDAO;
import vo.BookingVO;
import vo.ParkingVO;
import vo.PreBookingVO;
import vo.UserVO;
import vo.VehicleVO;



/**
 * Servlet implementation class Circular
 */
@WebServlet("/PreviousBookingController")
public class PreviousBookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviousBookingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		
		
		BookingDAO DAO_obj=new BookingDAO();
		List obj= DAO_obj.showAllofUser(userVO);
		
		
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/user/BookingDetails/myPreviousBookingDetails.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
	}
	
}
