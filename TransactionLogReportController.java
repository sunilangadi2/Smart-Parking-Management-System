package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.BookingVO;
import vo.ParkingVO;
import vo.TransactionVO;
import vo.UserLogVO;

import com.itextpdf.text.Document;

import dao.BookingDAO;
import dao.TransactionDAO;
import dao.UserLogDAO;

/**
 * Servlet implementation class ParkingWiseReportController
 */
@WebServlet("/TransactionLogReportController")
public class TransactionLogReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionLogReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		

		Document document = new Document();
	    try {
			
			
			//Domestic Help Details List Report
			
			TransactionDAO dao = new TransactionDAO();
			
			java.util.List ls = dao.showAll();
			Iterator itr = ls.iterator();
			
			 HttpSession session = request.getSession();
		      ServletContext sc = session.getServletContext();
		      String fileName = sc.getRealPath("/backEnd/images/logo.png"); 
		     
		      TransactionVO transactionVO;

		      PdfPTable table3 = new PdfPTable(7); // 3 columns.
		      table3.setWidthPercentage(100);
		      PdfPTable table4 = new PdfPTable(4); // 3 columns.

		      
		      ServletContext context=getServletContext();  
			
			  String path =	context.getInitParameter("reportPath");
		      PdfWriter.getInstance(document, new FileOutputStream(path+"ReportDemo3.pdf"));

		      int i = 0 ;
		    
			      
		    	  
		    	  
		    	  Font font1 = new Font(Font.FontFamily.HELVETICA  , 20, Font.BOLD);
			      Font font2 = new Font(Font.FontFamily.COURIER    , 16,
			            Font.ITALIC | Font.UNDERLINE);
			      
			      document.open();
			     
			      Paragraph paragraph = new Paragraph(new Phrase("Transaction Log Report",font1));
			     
			      paragraph.setAlignment(Element.ALIGN_CENTER);
			      document.add(paragraph);
			      
			      Chunk caseNumber = new Chunk("Logging ",font2);
			      Paragraph paragraph1 = new Paragraph();
			      paragraph1.add(caseNumber);
			      paragraph1.setAlignment(Element.ALIGN_CENTER);
			      
			     
			      
			      document.add(paragraph1);  
			      Image image1 = Image.getInstance(fileName);
			      image1.setAlignment(Element.ALIGN_LEFT);
			      image1.scaleAbsolute(150, 150);

			      document.add(image1);
			      
			      
			  					
			      Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

			      Paragraph table3Title = new Paragraph("User Details",font3);
			      table3Title.setAlignment(Element.ALIGN_LEFT);
			      table3Title.setSpacingBefore(1);
			      table3Title.setSpacingAfter(1);

			      document.add(table3Title);
			      table3.setHeaderRows(1);
			      PdfPCell cell12a = new PdfPCell(new Paragraph("S No."));
			      PdfPCell cell12 = new PdfPCell(new Paragraph("Transaction Type "));
			      PdfPCell cell13 = new PdfPCell(new Paragraph("Sent Time"));
			      PdfPCell cell14 = new PdfPCell(new Paragraph("Description"));
			      PdfPCell cell15 = new PdfPCell(new Paragraph("Name"));
			      PdfPCell cell16 = new PdfPCell(new Paragraph("Mobile Number"));
			      PdfPCell cell16b = new PdfPCell(new Paragraph("Email"));
			      
			      
			      cell12a.setBackgroundColor(BaseColor.GRAY);
			      cell12.setBackgroundColor(BaseColor.GRAY);
			      cell13.setBackgroundColor(BaseColor.GRAY);
			      cell14.setBackgroundColor(BaseColor.GRAY);
			      cell15.setBackgroundColor(BaseColor.GRAY);
			      cell16.setBackgroundColor(BaseColor.GRAY);
			      cell16b.setBackgroundColor(BaseColor.GRAY);
			      
			      
			      table3.addCell(cell12a);
			      table3.addCell(cell12);
			      table3.addCell(cell13);
			      table3.addCell(cell14);
			      table3.addCell(cell15);
			      table3.addCell(cell16);
			      table3.addCell(cell16b);
			      table3.completeRow();
			      
			      
			      while(itr.hasNext()) {

			    	  System.out.println("Transaction Log Report");
				     
				    	  
			    	  transactionVO = (TransactionVO) itr.next();
							

			      
			      i++;
			      PdfPCell cell17a = new PdfPCell(new Paragraph("TL "+i+""));
			      
			      String transType = "";
			      if(transactionVO.getTransactionType() == 1){
			    	  transType = "Email";
			    	  
			      }else{
			    	  transType ="SMS";
			      }
			      PdfPCell cell17 = new PdfPCell(new Paragraph(transType));
			      PdfPCell cell18 = new PdfPCell(new Paragraph(transactionVO.getSentTime()));
			 
				
			      
			      PdfPCell cell19 = new PdfPCell(new Paragraph(transactionVO.getDescription()));
			      PdfPCell cell20 = new PdfPCell(new Paragraph(transactionVO.getUserID().getPersonID().getFirstName()+" "+ transactionVO.getUserID().getPersonID().getLastName()));
			      PdfPCell cell21 = new PdfPCell(new Paragraph(transactionVO.getUserID().getPersonID().getMobileNumber()));
			      PdfPCell cell22 = new PdfPCell(new Paragraph(transactionVO.getUserID().getPersonID().getEmail()));
			      
			      table3.addCell(cell17a);
			      table3.addCell(cell17);
			      table3.addCell(cell18);
			      table3.addCell(cell19);
			      table3.addCell(cell20);
			      table3.addCell(cell21);
			      table3.addCell(cell22);
			      table3.completeRow();
			      
			      
			   

		      
		      }
			      
			      document.add(table3);





	    }  catch(Exception e){
	          e.printStackTrace();
	        }
	    finally {

	          document.close();

	    	System.out.println("Document Closed");
	    }
	    

	    response.sendRedirect(request.getContextPath()+"/TransactionLogController");


	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
	}

}
