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

import vo.ParkingVO;
import dao.StaticsDAO;

/**
 * Servlet implementation class ParkingWiseReportController
 */
@WebServlet("/StatisticsController")
public class StatisticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		StaticsDAO dao = new StaticsDAO();
		List ls = dao.totalParkingeWise();

		Iterator itr = ls.iterator();
		
		while(itr.hasNext()){
			
			Object[] objects = (Object[]) itr.next();
			String parkingName = (String) objects[0];
			long parkingID = (long) objects[1];
			double count = (double) objects[2];
			
			System.out.println(parkingName+" "+parkingID+" "+count);
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("entries", ls);
		
	    response.sendRedirect(request.getContextPath()+"/views/employee/totalRevenue.jsp");
	


	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		StaticsDAO dao = new StaticsDAO();
		List ls = dao.totalBookingParkingeWise();

		Iterator itr = ls.iterator();
		
		while(itr.hasNext()){
			
			Object[] objects = (Object[]) itr.next();
			String parkingName = (String) objects[0];
			long parkingID = (long) objects[1];
			int count = (int) objects[2];
			
			System.out.println(parkingName+" "+parkingID+" "+count);
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("entries", ls);
		
	    response.sendRedirect(request.getContextPath()+"/views/employee/home.jsp");
		
		
		
	}

}
