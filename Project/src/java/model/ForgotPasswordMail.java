/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BillyLim
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ForgotPasswordMail {

   private String password;
   private String email;

    public ForgotPasswordMail() {
    }

    public ForgotPasswordMail(String password, String email) {
        this.password = password;
        this.email = email;
    }

    
    public void triggerMail(String password, String email) {

        // Recipient's email ID needs to be mentioned.
        String to = email;
        

        // Sender's email ID needs to be mentioned
        String from = "nratnap@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            
          

            // Set Subject: header field
            message.setSubject("Password Recovery: Linked U");

            // Send the actual HTML message, as big as you like
           /* message.setContent("Dear "+firstName+" "+lastName+","+"</br>"+" Congratulations! Your account has been successfully created."+"</br>"+"Your userID is "+userID+"</br>"+"Your Password is "+password1+"</br>"+"Your security question: "+question+"<br/>"+"Your Answer: "+answer+"<br/>"+"You can now avail financial consultancy from Nikunj Ratnaparkhi and Shweta Vyas.</br>"+ "<img src=\"http://www.rgagnon.com/images/jht.gif\">",
                    "text/html");*/
            
            
            message.setContent("Dear User"+"</br>"+" Your Password  "+password,"text/html");
            
            //message.append("<img src=\"cid:image2\" width=\"15%\" height=\"15%\" /><br>");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
