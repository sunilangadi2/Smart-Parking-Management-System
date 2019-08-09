package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AttachmentDAO;
import dao.BookingDAO;
import dao.ParkingChargeDAO;
import dao.PaymentDAO;
import dao.PersonDAO;
import dao.PreBookingDAO;
import dao.SlotsDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import vo.AttachmentVO;
import vo.BookingVO;
import vo.ParkingChargeVO;
import vo.ParkingVO;
import vo.PaymentVO;
import vo.PersonVO;
import vo.PreBookingVO;
import vo.SlotsVO;
import vo.TransactionVO;
import vo.UserRole;
import vo.UserVO;
import vo.VehicleVO;



/**
 * Servlet implementation class Circular
 */
@WebServlet("/BookingController_ImageProcessing")
public class BookingController_ImageProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingController_ImageProcessing() {
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
		}else if(mode.equals("6")){
			preBookFromPreBooking(request, response);
		}else if(mode.equals("6a")){
			book(request, response);
		}else if(mode.equals("7")){
			getVehicle(request, response);
		}else if(mode.equals("10")){
			checkout(request, response);
		}else if(mode.equals("8")){
			generateBill(request, response);
		}else if(mode.equals("9")){
			bookingHome(request, response);
		}else if(mode.equals("11")){
			response.sendRedirect(request.getContextPath()+"/views/employee/Booking_ImageProcessing/takePictureCheckOut.jsp");
		}else{
			//default Add
		
			response.sendRedirect(request.getContextPath()+"/views/employee/Booking_ImageProcessing/takePicture.jsp");
			
		}
		
		
		
		
		
	}
	
protected void book(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		
		
		
		
ImageToText toText = new ImageToText();
		
		List myList=(List)session.getAttribute("fileList");
		
		 Iterator itr =  myList.iterator();
		 String FileLocation = "NA";
		 
		 
        System.out.println("Reading for session");
        
        if(itr.hasNext()) {
       	 
       	
        	FileLocation = (String)itr.next();
       	 
    	session.removeAttribute("fileList");
        }
		
		
        FileLocation =  session.getAttribute("url")+FileLocation;
        
        FileLocation = FileLocation.replace('\\', '/');
		
		System.out.println(FileLocation);
		
		System.out.println("Number Plate " +toText.getNumberPlate(FileLocation));
		
		String temp = (((toText.getNumberPlate(FileLocation)).replaceAll(" ", "")).replace("—", "-")).trim();
		
		System.out.println("Cleaned "+temp);
		
		VehicleVO inputData = new VehicleVO();
		inputData.setNumberOfVehicle(temp);
		session.setAttribute("numberOfVehicle", temp);
		
		VehicleDAO dao = new VehicleDAO();
		List ls = dao.search(inputData);
		
		Iterator vitr = ls.iterator();
		
		if (vitr.hasNext()) {
			VehicleVO vehicleVO = (VehicleVO) vitr.next();
			System.out.println(vehicleVO);
			
			long vehicleID = vehicleVO.getVehicleID();
			session.setAttribute("vehicleID", vehicleID);
			session.setAttribute("selectedUserID", vehicleVO.getUserID().getUserID());
			
			
			ParkingVO parkingVO = new ParkingVO();
			parkingVO.setParkingID((long)session.getAttribute("parkingID"));
			
			
			SlotsVO slotsVO = new SlotsVO();
			slotsVO.setParkingID(parkingVO);
			
			SlotsDAO DAO_obj=new SlotsDAO();
			List obj= DAO_obj.getAllSlotsByParkingID(slotsVO);
			
			
			BookingDAO bookingDAO = new BookingDAO();
			List bookedSlot = bookingDAO.showAllBookedSlots(parkingVO);
			

			PreBookingDAO preBookingDAO = new PreBookingDAO();
			List preBookedSlot = preBookingDAO.showAllByParkingVO(parkingVO);
			
			System.out.println(preBookedSlot);
			
			session.setAttribute("obj_preBooked", preBookedSlot);
			session.setAttribute("obj_Booked", bookedSlot);
			session.setAttribute("obj_all", obj);
			response.sendRedirect(request.getContextPath()+"/views/employee/Booking/registerBooking.jsp");
			
			
		}else{
			
			response.sendRedirect(request.getContextPath()+"/views/employee/Booking_ImageProcessing/addUser.jsp");
			
		}
		
		
				
		
}
	

protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
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
	
	
	
	
	
ImageToText toText = new ImageToText();
	
	List myList=(List)session.getAttribute("fileList");
	
	 Iterator itr =  myList.iterator();
	 String FileLocation = "NA";
	 
	 
    System.out.println("Reading for session");
    
    if(itr.hasNext()) {
   	 
   	
    	FileLocation = (String)itr.next();
   	 
	session.removeAttribute("fileList");
    }
	
	
    FileLocation =  session.getAttribute("url")+FileLocation;
    
    FileLocation = FileLocation.replace('\\', '/');
	
	System.out.println(FileLocation);
	
	System.out.println("Number Plate " +toText.getNumberPlate(FileLocation));
	
	String temp = (((toText.getNumberPlate(FileLocation)).replaceAll(" ", "")).replace("—", "-")).trim();
	
	System.out.println("Cleaned "+temp);
	
	VehicleVO inputData = new VehicleVO();
	inputData.setNumberOfVehicle(temp);
	session.setAttribute("numberOfVehicle", temp);
	
	VehicleDAO dao = new VehicleDAO();
	List ls = dao.search(inputData);
	
	Iterator vitr = ls.iterator();
	
	if (vitr.hasNext()) {
		VehicleVO vehicleVO = (VehicleVO) vitr.next();
		System.out.println(vehicleVO);
		
		long vehicleID = vehicleVO.getVehicleID();
		session.setAttribute("vehicleID", vehicleID);
		session.setAttribute("selectedUserID", vehicleVO.getUserID().getUserID());
		
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID((long)session.getAttribute("parkingID"));
		
		BookingDAO bookingDAO = new BookingDAO();
		List bookedVehicle = bookingDAO.showVehicleForGivenParking(parkingVO,vehicleVO);
		
		Iterator vhitr = bookedVehicle.iterator();
		
		if(vhitr.hasNext()){
		
			BookingVO bookingVO = (BookingVO) vhitr.next();
			
			response.sendRedirect(request.getContextPath()+"/BookingController?mode=8&id="+bookingVO.getBookingID());
			
		}else{
			
			response.sendRedirect(request.getContextPath()+"/views/employee/Booking/vehicleNotParkedOrNotRegistered.jsp");
		}
			
		
		
		
	}else{
		
		response.sendRedirect(request.getContextPath()+"/views/employee/Booking/vehicleNotParkedOrNotRegistered.jsp");
		
	}
	
	
			
	
}
	protected void bookingHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath()+"/views/employee/Booking/bookingHome.jsp");
		
	}
protected void getVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		long usID = Long.valueOf(request.getParameter("userID"));
		session.setAttribute("selectedUserID", usID);
		
		UserVO user = new UserVO();
		user.setUserID(usID);
		
		VehicleDAO DAO_obj=new VehicleDAO();
		List obj= DAO_obj.getAllVehicleByUserID(user);
		
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/employee/Booking/selectVehicle.jsp");
		
		
		
}
protected void preBookFromPreBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		
		PreBookingVO preBookingVO = new PreBookingVO();
		preBookingVO.setPreBookingID(Long.valueOf(request.getParameter("id")));
		
		PreBookingDAO bookingDAO = new PreBookingDAO();
		List ls = bookingDAO.getElementByID(preBookingVO);
		Iterator itr =ls.iterator();
		
		PreBookingVO preBookingVO2 = (PreBookingVO) itr.next();
		
		
			
		
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		String inTime = format.format(date);
		
	
		double paymentInitial = 0.0;
			
		ParkingVO parkingVO =preBookingVO2.getParkingID();
		
		
		
		UserVO userVO = preBookingVO2.getUserID();
		

		
		
		
		VehicleVO vehicleVO =preBookingVO2.getVehicleID();
		
		BookingVO VO_obj =new BookingVO();
		VO_obj.setInTime(inTime);
		VO_obj.setParkingID(parkingVO);
		VO_obj.setPaymentInitial(paymentInitial);
		VO_obj.setUserID(userVO);
		VO_obj.setVehicleID(vehicleVO);
		VO_obj.setPreBookingID(preBookingVO2);
		SlotsVO slotsVO = preBookingVO2.getSlotID();
		VO_obj.setSlotID(slotsVO);
		
		
		
				
		
		
		BookingDAO DAO_obj=new BookingDAO();
		DAO_obj.insert(VO_obj);
		
		show(request, response);
		
		
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
			}else if(mode.equals("8")){
				registerUser(request, response);
			}else{
						//default Add
						response.sendRedirect(request.getContextPath()+"/views/admin/Category/addCategory.jsp");
						}//default
					
			
			
			
			
		
		
	}
	
protected void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		
		PersonVO personVO = new PersonVO();
		personVO.setEmail(email);
		personVO.setFirstName(firstName);
		personVO.setLastName(lastName);
		personVO.setMobileNumber(mobileNumber);
		
		PersonDAO dao = new PersonDAO();
		dao.insert(personVO);
		
		UserVO VO_obj = new UserVO();
		VO_obj.setPassword(password);
		VO_obj.setPersonID(personVO);
		VO_obj.setUserName(userName);
		VO_obj.setUserType(UserRole.REGISTERED_USER);
		 AttachmentVO attachmentVO = new AttachmentVO();

			//getting file from Session
			
			
			
					List myList=(List)session.getAttribute("fileList");
					
					 Iterator itr =  myList.iterator();
			         
			         System.out.println("Reading for session");
			         int i =0;
			         while (itr.hasNext()) {
			        	 
			        	
			     		attachmentVO.setAttachmentType("Profile Picture");
			     		
			        	 //file name
			     		attachmentVO.setPath((String)itr.next());
			        	 
			        	
			        	 
			        	 AttachmentDAO attachmentDAO = new AttachmentDAO();
				     		attachmentDAO.insert(attachmentVO);
				     
			        	 
			        	 i++;
						}
					
			         VO_obj.setAttachmentID(attachmentVO);
					
					session.removeAttribute("fileList");
					
					VO_obj.setAttachmentID(attachmentVO);
		
		
		UserDAO DAO_obj=new UserDAO();
		DAO_obj.insert(VO_obj);
		

		long RG_userID = VO_obj.getUserID();
		String model = request.getParameter("model");
		String numberOfVehicle = (String)session.getAttribute("numberOfVehicle");
		
		UserVO userVO = new UserVO();
		userVO.setUserID(RG_userID);
		
		VehicleVO vehicleVO_obj = new VehicleVO();
		vehicleVO_obj.setModel(model);
		vehicleVO_obj.setNumberOfVehicle(numberOfVehicle);
		vehicleVO_obj.setUserID(userVO);
		
		
		
		
		
		
		VehicleDAO vehicleDAO_obj=new VehicleDAO();
		vehicleDAO_obj.insert(vehicleVO_obj);
		
		
		
		session.setAttribute("vehicleID", vehicleVO_obj.getVehicleID());
		session.setAttribute("selectedUserID", vehicleVO_obj.getUserID().getUserID());
		

		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID((long)session.getAttribute("parkingID"));
		
		
		SlotsVO slotsVO = new SlotsVO();
		slotsVO.setParkingID(parkingVO);
		
		SlotsDAO slotDAO_obj=new SlotsDAO();
		List obj= slotDAO_obj.getAllSlotsByParkingID(slotsVO);
		
		
		BookingDAO bookingDAO = new BookingDAO();
		List bookedSlot = bookingDAO.showAllBookedSlots(parkingVO);
		

		PreBookingDAO preBookingDAO = new PreBookingDAO();
		List preBookedSlot = preBookingDAO.showAllByParkingVO(parkingVO);
		
		System.out.println(preBookedSlot);
		
		session.setAttribute("obj_preBooked", preBookedSlot);
		session.setAttribute("obj_Booked", bookedSlot);
		session.setAttribute("obj_all", obj);
		response.sendRedirect(request.getContextPath()+"/views/employee/Booking/registerBooking.jsp");
		
		
	}


protected void generateBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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

		long bookingID=Long.parseLong(request.getParameter("id"));
    	BookingVO VO_obj =new BookingVO();
		VO_obj.setBookingID(bookingID);
		
		BookingDAO DAO_obj=new BookingDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		
		Iterator itr = obj.iterator();
		BookingVO bookingVO = (BookingVO) itr.next();
		
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		String outTime = format.format(date);
		
		bookingVO.setOutTime(outTime);
		
		 Date datnIN = null;
		try {
			datnIN = format.parse(bookingVO.getInTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	      Date dateOut = null ;
		try {
			dateOut = format.parse(bookingVO.getOutTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      long diff = dateOut.getTime() - datnIN.getTime();
	      
			long diffMinutes = diff / (60 * 1000);
			
			
			
			ParkingVO parkingVO = new ParkingVO();
			long parkingID = (long)(session.getAttribute("parkingID"));
			parkingVO.setParkingID(parkingID);
			
			ParkingChargeVO chargeVO = new ParkingChargeVO();
			ParkingChargeDAO chargeDAO = new ParkingChargeDAO();
			List ls = chargeDAO.showAllByParkingVO(parkingVO);
			Iterator pcitr = ls.iterator();
			
			float total_hrs = (float)diffMinutes/60;
			float applicablecharges = 0;
			
			System.out.println(total_hrs+"Total hrs "+diffMinutes);
			while (pcitr.hasNext()) {
				
				chargeVO = (ParkingChargeVO) pcitr.next();
				
				
				if(total_hrs > chargeVO.getLowerLimit() && total_hrs < chargeVO.getUpperLimit() ){
					
					applicablecharges += chargeVO.getCharge()* (total_hrs - chargeVO.getLowerLimit());
					
					
					System.out.println("Charges calculate as per cond 1 (Variable Charges) "+chargeVO.getLowerLimit()+" - "+total_hrs+" for Duration("+(total_hrs - chargeVO.getLowerLimit())+") is Rs "+chargeVO.getCharge()* (total_hrs - chargeVO.getLowerLimit()));
				
				}else if(total_hrs > chargeVO.getLowerLimit()){
				
					applicablecharges += chargeVO.getCharge() * (chargeVO.getUpperLimit() - chargeVO.getLowerLimit());
				
					System.out.println("Charges calculate as per cond 2 (fixed Charges)"+chargeVO.getLowerLimit()+"-"+chargeVO.getUpperLimit()+" is Rs "+(chargeVO.getCharge() * (chargeVO.getUpperLimit() - chargeVO.getLowerLimit())));
				
				
				
				}else{
					System.out.println("Do nothing");
					
				}
				
				
			}
			
			
			
			
			
		bookingVO.setPaymentFinal(applicablecharges - bookingVO.getPaymentInitial());
		
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setAmount(bookingVO.getPaymentFinal());
		paymentVO.setParkingID(bookingVO.getParkingID());
		paymentVO.setPaymentType("Final Payment");
		paymentVO.setUserID(bookingVO.getUserID());
		paymentVO.setPaymentTime(outTime);
		PaymentDAO dao = new PaymentDAO();
		dao.insert(paymentVO);
		
		DAO_obj.update(bookingVO);
		
		

		
		String message = "Thanks for Booking with our Parking System for your vehicle "+bookingVO.getVehicleID().getModel()+" -> "+bookingVO.getVehicleID().getNumberOfVehicle();
		message+= " \n from "+bookingVO.getInTime()+" to "+bookingVO.getOutTime()+" Final bill Amount : "+(bookingVO.getPaymentFinal()-bookingVO.getPaymentInitial());
		


		
		
		
		
		ServletContext context=getServletContext();  
		
		if(context.getInitParameter("emailStatus").equalsIgnoreCase("on")){
		
		MailHandler.sendMail(bookingVO.getUserID().getPersonID().getEmail(), message,context.getInitParameter("masterEmail"),context.getInitParameter("masterPassword"));
	
		TransactionVO transactionVO = new TransactionVO();
		transactionVO.setSentTime(bookingVO.getOutTime());
		transactionVO.setTransactionType(1);
		transactionVO.setUserID(bookingVO.getUserID());
		transactionVO.setDescription(bookingVO.getUserID().getPersonID().getEmail()+" ---> "+message);
		
		TransactionDAO dao2 = new TransactionDAO();
		dao2.insert(transactionVO);
		
		}
		
		
		if(context.getInitParameter("smsStatus").equalsIgnoreCase("on")){
			
			MailHandler.sendMail(bookingVO.getUserID().getPersonID().getEmail(), message,context.getInitParameter("masterEmail"),context.getInitParameter("masterPassword"));
		
			TransactionVO transactionVO = new TransactionVO();
			transactionVO.setSentTime(bookingVO.getOutTime());
			transactionVO.setTransactionType(1);
			transactionVO.setUserID(bookingVO.getUserID());
			transactionVO.setDescription(bookingVO.getUserID().getPersonID().getEmail()+" ---> "+message);
			
			TransactionDAO dao2 = new TransactionDAO();
			dao2.insert(transactionVO);
			
			}
		show(request, response);
		
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

		long usID = (long)session.getAttribute("selectedUserID");
		long parking = (long)session.getAttribute("parkingID");
		long vehicleID =  (long)session.getAttribute("vehicleID");
		
		
		
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		String inTime = format.format(date);
		
		//String inTime = request.getParameter("inTime");
		//String outTime= request.getParameter("outTime");

		double paymentInitial = Double.valueOf(request.getParameter("paymentInitial"));
			
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parking);
		
		
		
		UserVO userVO = new UserVO();
		userVO.setUserID(usID);
		

		
		
		
		VehicleVO vehicleVO =new VehicleVO();
		vehicleVO.setVehicleID(vehicleID);
		
		BookingVO VO_obj =new BookingVO();
		VO_obj.setInTime(inTime);
		VO_obj.setParkingID(parkingVO);
		VO_obj.setPaymentInitial(paymentInitial);
		VO_obj.setUserID(userVO);
		VO_obj.setVehicleID(vehicleVO);
        long slotID = Long.valueOf(request.getParameter("slotID"));
		
		SlotsVO slotsVO = new SlotsVO();
		slotsVO.setSlotID(slotID);
		VO_obj.setSlotID(slotsVO);
		
		
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setAmount(VO_obj.getPaymentInitial());
		paymentVO.setParkingID(VO_obj.getParkingID());
		paymentVO.setPaymentType("Initial Payment");
		paymentVO.setUserID(VO_obj.getUserID());
		paymentVO.setPaymentTime(inTime);
		
		
		PaymentDAO dao = new PaymentDAO();
		dao.insert(paymentVO);
				
		
		
		BookingDAO DAO_obj=new BookingDAO();
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
    	
		String inTime = request.getParameter("inTime");
		String outTime= request.getParameter("outTime");

		double paymentInitial = Double.valueOf(request.getParameter("paymentInitial"));
		long parkingID= Long.valueOf(request.getParameter("parkingID"));
		
		ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID(parkingID);
		
		
		long user= Long.valueOf(request.getParameter("user"));
		UserVO userVO = new UserVO();
		userVO.setUserID(user);
		

		long preBookingID= Long.valueOf(request.getParameter("preBookingID"));
		PreBookingVO preBookingVO = new PreBookingVO();
		preBookingVO.setPreBookingID(preBookingID);
		
		long vehicleID= Long.valueOf(request.getParameter("vehicleID"));
		VehicleVO vehicleVO =new VehicleVO();
		vehicleVO.setVehicleID(vehicleID);
		
		
		long bookingID= Long.valueOf(request.getParameter("bookingID"));
		BookingVO VO_obj =new BookingVO();
		VO_obj.setBookingID(bookingID);
		VO_obj.setInTime(inTime);
		VO_obj.setOutTime(outTime);
		VO_obj.setParkingID(parkingVO);
		VO_obj.setPaymentInitial(paymentInitial);
		VO_obj.setPreBookingID(preBookingVO);
		VO_obj.setUserID(userVO);
		VO_obj.setVehicleID(vehicleVO);
		
		
		
		BookingDAO DAO_obj=new BookingDAO();
		DAO_obj.update(VO_obj);
		
		show(request, response);
	}
    
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	long bookingID=Long.parseLong(request.getParameter("id"));
    	BookingVO VO_obj =new BookingVO();
		VO_obj.setBookingID(bookingID);
		
		BookingDAO DAO_obj=new BookingDAO();
		List obj= DAO_obj.getElementByID(VO_obj);
		
		HttpSession session = request.getSession();
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath()+"/views/admin/Category/editCategory.jsp");
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
		
    	int bookingID=Integer.parseInt(request.getParameter("id"));
    	BookingVO VO_obj =new BookingVO();
		VO_obj.setBookingID(bookingID);
		
		BookingDAO DAO_obj=new BookingDAO();
		DAO_obj.delete(VO_obj);
		show(request, response);
		
		
	}
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession();
		
    	
    	ParkingVO parkingVO = new ParkingVO();
		parkingVO.setParkingID((long)session.getAttribute("parkingID"));
		
		    	
    	BookingDAO DAO_obj=new BookingDAO();
		   	
    	List obj= DAO_obj.getBookingByParkingID(parkingVO);
		
		session.setAttribute("obj_all", obj);
		
		
		response.sendRedirect(request.getContextPath()+"/views/employee/Booking/manageBooking.jsp");
	
	}

}
