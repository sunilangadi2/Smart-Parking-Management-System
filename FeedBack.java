package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.FeedBackVO;
import vo.UserVO;
import dao.FeedBackDAO;

/**
 * Servlet implementation class Circular
 */
@WebServlet("/FeedBack")
@SuppressWarnings("rawtypes")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedBack() {
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
			
		}else if(mode.equals("manage")){
			show(request, response);
		}else{
			//default Add
			response.sendRedirect(request.getContextPath()+"/views/employee/FeedBack/giveFeedBack.jsp");
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
				response.sendRedirect(request.getContextPath()+"/views/employee/FeedBack/giveFeedBack.jsp");
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
		
		String name = request.getParameter("Name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String description= request.getParameter("description");
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		
		FeedBackVO VO_obj =new FeedBackVO();
		VO_obj.setUserID(userVO);
		VO_obj.setName(name);
		VO_obj.setEmail(email);
		VO_obj.setSubject(subject);
		VO_obj.setDescription(description);
		
		Date currentDate=null;
		try {
			currentDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").parse(new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		VO_obj.setCreatedDate(currentDate);
		VO_obj.setModifiedDate(currentDate);
		
		
		
		FeedBackDAO DAO_obj=new FeedBackDAO();
		DAO_obj.insert(VO_obj);
		
		response.sendRedirect(request.getContextPath()+"/views/employee/FeedBack/success.jsp");
		
	}
	
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//not to be updated 
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    // not to be edited
	}
    
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	

    	
    	int feedBackID=Integer.parseInt(request.getParameter("id"));
    	FeedBackVO VO_obj =new FeedBackVO();
		VO_obj.setFeedBackID(feedBackID);
		
		FeedBackDAO DAO_obj=new FeedBackDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	FeedBackDAO DAO_obj=new FeedBackDAO();
    	
    	
		
		List obj= DAO_obj.showAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/FeedBack/viewFeedBack.jsp");
	
	}

}
