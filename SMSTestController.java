package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.TransactionVO;
import dao.TransactionDAO;

/**
 * Servlet implementation class SMSTestController
 */
@WebServlet("/SMSTestController")
public class SMSTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMSTestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		ServletContext context=getServletContext();
		
		if(context.getInitParameter("smsStatus").equalsIgnoreCase("on")){
			
			String toMobileNumber = "8553902239";
			String message = "Thanks you";
			
			response.sendRedirect("http://psms.krishsoftweb.in/sendsms.aspx?mobile="+context.getInitParameter("mobile")+"&pass="+context.getInitParameter("smsGatewayPassword")+"&senderid="+context.getInitParameter("senderid")+"&to="+toMobileNumber+"&msg="+message+" ");
			
		/*	TransactionVO transactionVO = new TransactionVO();
			transactionVO.setSentTime(bookingTime);
			transactionVO.setTransactionType(1);
			transactionVO.setUserID(userVO2);
			transactionVO.setDescription(userVO2.getPersonID().getEmail()+" ---> "+message);
			
			TransactionDAO dao2 = new TransactionDAO();
			dao2.insert(transactionVO);
			*/
			}
		
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
