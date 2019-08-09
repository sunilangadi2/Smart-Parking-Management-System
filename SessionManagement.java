package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.UserVO;
import dao.UserDAO;

/**
 * Servlet implementation class SessionManagement
 */
@WebServlet("/SessionManagement")
public class SessionManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = request.getParameter("token");
		HttpSession session = request.getSession();
		
		if(token.equals("signout")){
			
			session.removeAttribute("userID");
		 	session.removeAttribute("permission");
		 	session.removeAttribute("userType");
			
		 	
		 	
		 	
		 	response.sendRedirect(request.getContextPath()+"/views/login.jsp");
		}else if(token.equals("adminEditProfile")){
			
			UserVO userVO = new UserVO();
			userVO.setUserID((long)session.getAttribute("userID"));
			
			UserDAO DAO_obj=new UserDAO();
			List obj= DAO_obj.getElementByID(userVO);
			
			
			session.setAttribute("obj", obj);
			
			response.sendRedirect(request.getContextPath()+"/views/admin/editProfile.jsp");
			
		}else if(token.equals("employeeEditProfile")){
			
			
			UserVO userVO = new UserVO();
			userVO.setUserID((long)session.getAttribute("userID"));
			
			UserDAO DAO_obj=new UserDAO();
			List obj= DAO_obj.getElementByID(userVO);
			
			
			session.setAttribute("obj", obj);
			
			response.sendRedirect(request.getContextPath()+"/views/employee/editProfile.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
