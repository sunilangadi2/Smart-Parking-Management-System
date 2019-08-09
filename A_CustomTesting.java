package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ParkingEmployeeVO;
import vo.UserVO;
import dao.UserDAO;

/**
 * Servlet implementation class A_CustomTesting
 */
@WebServlet("/A_CustomTesting")
public class A_CustomTesting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_CustomTesting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
           UserDAO DAO_obj=new UserDAO();
		
		List obj= DAO_obj.showAllEmployee();
		
		UserVO user;
		ParkingEmployeeVO employeeVO;
		Iterator itr = obj.iterator();
		
		while (itr.hasNext()) {
			Object[] objMain = (Object[])itr.next();
			
			
		 	user = (UserVO)objMain[0];
		 	employeeVO =(ParkingEmployeeVO)objMain[1];
		 	
		 	
		 	System.out.println(user.getUserID());
		 	System.out.println(employeeVO.getParkingID().getParkingName());
		 	
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
