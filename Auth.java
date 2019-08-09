package authentication;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.ParkingEmployeeVO;
import vo.UserLogVO;
import vo.UserVO;
import dao.BookingDAO;
import dao.StaticsDAO;
import dao.UserDAO;
import dao.UserLogDAO;




/**
 * Servlet Filter implementation class Auth
 */
@WebFilter("/*")
public class Auth implements Filter {

    /**
     * Default constructor. 
     */
    public Auth() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here 
		String uri = ((HttpServletRequest)request).getRequestURI();

		HttpSession session =((HttpServletRequest) request).getSession();
		RequestDispatcher rd;
	    
		//Check whether the User has Requested to Submit User Name and Password
		
		 if ( uri.contains("bypass")|| uri.contains("signup") || uri.contains("UploadServletSimple")  || uri.contains("Registration")  ){
		
			 chain.doFilter(request, response);
			 
		 }else if ( uri.contains("backEnd") || uri.contains("attachments") || uri.contains("frontEnd")  && !uri.contains("jsp")){//all css, js, images, and other supporting files are located here
		        
			  chain.doFilter(request, response);
		        
		    }else if(request.getParameter("login") !=null && request.getParameter("login").equals("true") ){
			
		    	        session.removeAttribute("userID");
						String userName = request.getParameter("userName");
						String password = request.getParameter("password");
						
						
						
						UserVO userVO = new UserVO();
						userVO.setUserName(userName);
						userVO.setPassword(password);
						
						UserDAO userDAO = new UserDAO();
						List ls= userDAO.login(userVO);
					
						
						//Check for Authentic User 
						if(ls != null && ls.size()>=1){
							UserVO user;
							Iterator itr = ls.iterator();
							
							
							user = (UserVO)itr.next();
							 	
							 	
							 	session.setAttribute("userID", user.getUserID());
							 	session.setAttribute("userType", user.getUserTypeName());
							 	session.setAttribute("userTypeID", user.getUserType());
							 	session.setAttribute("path", user.getAttachmentID().getPath());
							 	
							 	session.setAttribute("name",user.getPersonID().getFirstName()+" "+user.getPersonID().getLastName() );
							 	session.setAttribute("email", user.getPersonID().getEmail());
							 	
							 	System.out.println("\n User ID "+session.getAttribute("userID"));
							  	System.out.println("\n User Type  "+ session.getAttribute("userType"));
							 	
							  	Calendar calendar = Calendar.getInstance();
						        Date date = calendar.getTime();
								
								SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
															
								String loginTime = format.format(date);
							  	UserLogVO logVO = new UserLogVO();
							  	logVO.setLoginTime(loginTime);
							  	logVO.setUserID(user);
							  	logVO.setUserType(user.getUserType());
							  	
							  	UserLogDAO dao = new UserLogDAO();
							  	dao.insert(logVO);
							  	
							  	
							 	
							 	if(user.getUserType() == 2){//for Employee
							 		
										 	
							 				List empls = userDAO.employeeLogin(user);
							 		
							 				Iterator empITR = empls.iterator();
							 				
							 				
							 				ParkingEmployeeVO obj = (ParkingEmployeeVO) empITR.next();
						 		            
						 		            session.setAttribute("parkingID",obj.getParkingID().getParkingID());
						 		            
						 		           StaticsDAO dao1 = new StaticsDAO();
						 		  		List ls1 = dao1.totalBookingParkingeWise();

						 		  		Iterator itr1 = ls1.iterator();
						 		  		
						 		  		while(itr1.hasNext()){
						 		  			
						 		  			Object[] objects = (Object[]) itr1.next();
						 		  			String parkingName = (String) objects[0];
						 		  			long parkingID = (long) objects[1];
						 		  			int count = (int) objects[2];
						 		  			
						 		  			System.out.println(parkingName+" "+parkingID+" "+count);
						 		  		}
						 		  		
						 		  		session.setAttribute("entries", ls1);
						 		  		
										 
										 	 	
										 	rd=request.getRequestDispatcher("/views/employee/home.jsp");  
											rd.forward(request,response);
										 	
										 	
										
										 	
							 				
							 			}else if(user.getUserType() == 1){//for Registered User 
							 				
							 				
							 				BookingDAO bookingDAO = new BookingDAO();
							 				List booking_ls = bookingDAO.showAllofUserLogin(user);
							 				
							 				session.setAttribute("obj_bookingDetails", booking_ls);
							 				
							 				
							 				rd=request.getRequestDispatcher("/views/user/home.jsp");  
											rd.forward(request,response);  
											
											
							 				
							 				
							 			}else if(user.getUserType() == 3){//for Admin
							 				
							 				
							 				 StaticsDAO dao1 = new StaticsDAO();
								 		  		List ls1 = dao1.totalBookingParkingeWise();

								 		  		Iterator itr1 = ls1.iterator();
								 		  		
								 		  		while(itr1.hasNext()){
								 		  			
								 		  			Object[] objects = (Object[]) itr1.next();
								 		  			String parkingName = (String) objects[0];
								 		  			long parkingID = (long) objects[1];
								 		  			int count = (int) objects[2];
								 		  			
								 		  			System.out.println(parkingName+" "+parkingID+" "+count);
								 		  		}
								 		  		
								 		  		session.setAttribute("entries", ls1);
								 		  		
										 	 	
							 				
							 			    rd=request.getRequestDispatcher("/views/admin/home.jsp");  
											rd.forward(request,response);  
							 				
							 				
							 				
							 			}else {
							 				rd=request.getRequestDispatcher("/views/login.jsp");  
											rd.forward(request,response);  
											
							 				
							 			}
						
						}else {// if note authenticated user
							
			 				rd=request.getRequestDispatcher("/views/login.jsp");  
							rd.forward(request,response);  
							
			 				
			 			}
						
								
			}else if(session.getAttribute("userID")!=null){
			
			
					chain.doFilter(request, response);
					
				  }else{
									
						rd=request.getRequestDispatcher("/views/login.jsp");  
						rd.forward(request,response);  
						
					   }
		
		
		
		//  System.out.println("Auth called ");


	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
