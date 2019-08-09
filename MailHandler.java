package controller;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {
	
	public static void sendMail(String email,String msg,String AuthEmail,String password){
		
		java.util.Properties properties = new java.util.Properties();
        properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         javax.mail.Session mailSession = javax.mail.Session.getInstance(properties);

        
        
 
         try {
            MimeMessage message = new MimeMessage(mailSession);
   
           
            message.setContent("<h1>Smart Parking </h1>"
            		+ "<p>"+msg+" </p> "
            		+ ""
            		+ "","text/html" );
            message.setSubject("Parking System");
            
            InternetAddress sender = new InternetAddress(AuthEmail, "Administrator");
             InternetAddress receiver = new InternetAddress(email);
            message.setFrom(sender);
            message.setRecipient(Message.RecipientType.TO, receiver);
             message.saveChanges();
            
            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", 587, AuthEmail,password);
             transport.sendMessage(message, message.getAllRecipients());
             
             
             
            transport.close();
                      
        } catch (Exception e) {
            System.out.println("err = " + e);
         }
		
		
		
	}

}
