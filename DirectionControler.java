package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookingDAO;
import vo.ParkingVO;

/**
 * Servlet implementation class DirectionControler
 */
@WebServlet("/DirectionControler")
public class DirectionControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectionControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID((long)session.getAttribute("parkingID"));
		
		BookingDAO bookingDAO = new BookingDAO();
		List bookedSlotVehicle = bookingDAO.showAllBookedSlotsVehicles(parkingVO);
		session.setAttribute("obj_all", bookedSlotVehicle);
		
		
		response.sendRedirect(request.getContextPath()+"/views/employee/DirectionToParkingLot/selectVehicle.jsp");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
