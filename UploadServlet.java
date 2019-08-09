package controller;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 5000 * 2024;
   private int maxMemSize = 40000 * 1024;
   private File file ;

   public void init( ){
      // Get the file location where it would be stored.
	    
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
      // Check that we have a file upload request
	   
	   filePath = getServletContext().getRealPath(request.getServletPath());
	   int path = filePath.lastIndexOf('\\');
	   String path1= filePath.substring(0, path) +"\\attachments\\";
	   System.out.println(path1);
	   filePath =path1;
	   HttpSession session1 = request.getSession();
	   session1.setAttribute("url", path1);
	  
	   isMultipart = ServletFileUpload.isMultipartContent(request);
      
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart )
      {
        
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      String path2= filePath.substring(0, path) +"\\temp\\";
      factory.setRepository(new File(path2));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

  
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
        	  String fileName = fi.getName();
			  
        	  		 /* String fieldName = fi.getFieldName();
			            String contentType = fi.getContentType();
			            boolean isInMemory = fi.isInMemory();
			            long sizeInBytes = fi.getSize();*/
        	  
        	  
            // Write the file
            
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }
            
            
                       
            
            String src = path1.concat(encryptFileName(fileName));
            
            File f3 = new File(src);  
            fi.write(f3);
            JSONObject obj=new JSONObject();
            
            obj.put("imgsrc",f3.getName());
             
              out.print(obj);
              out.flush();
            
            
            List myFileList=null;
            
            HttpSession session = request.getSession();
            myFileList = (List) session.getAttribute("fileList");
           
            
            if(myFileList != null){
            			myFileList.add(f3.getName());
		            }else{
		            	myFileList = new ArrayList<String>();
		            
		            	myFileList.add(f3.getName());
		            	
		            }
           
            session.setAttribute("fileList", myFileList);
            
         }
      }
 
      
      
      
      /*Below code is to fetch the list of upload file name's */
      /*Begin*/
      HttpSession session = request.getSession();
      List myFileList = (List) session.getAttribute("fileList");
      
      Iterator itr =  myFileList.iterator();
      
      
     
      System.out.println("Fetching file Names from session :");
      int j = 0 ;
      while (itr.hasNext()) {  
					      	System.out.println(" File Name "+ (++j) +" :"+itr.next());
							}
      
      /*Ends*/
     
      
   }catch(Exception ex) {
       System.out.println(ex);
   }
   }
   public void doGet(HttpServletRequest request, 
                       HttpServletResponse response)
        throws ServletException, java.io.IOException {
        
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required."); 
	   
	   
		   
   } 

   private String encryptFileName(String fileName){
	 
	   Random r = new Random();
	   String file[] = fileName.split("\\.");
	  
	   byte[] unencodedFile = file[0].getBytes();
	   MessageDigest md = null;
	   try {
	   md = MessageDigest.getInstance("MD5");
	   } catch (Exception e) {}
	   md.reset();
	   md.update(unencodedFile);
	   byte[] encodedFile = md.digest();
	   StringBuffer buf = new StringBuffer();
	   for (int i = 0; i < encodedFile.length; i++) {
	   if (((int) encodedFile[i] & 0xff) < 0x10) {
	   buf.append("0");
	   }
	   buf.append(Long.toString((int) encodedFile[i] & 0xff, 16));
	   }
	  
	   String encryptedFileName=(buf.toString()).concat(String.valueOf(r.nextInt())); 

	   
	   return encryptedFileName+"."+file[1];
   }
}