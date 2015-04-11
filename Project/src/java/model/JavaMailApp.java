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

public class JavaMailApp {

    private String firstName;
    private String lastName;
    String universityName;
    String recomm1;
    String recomm2;
    String recomm3;
    String email;

    public JavaMailApp() {
    }

    public JavaMailApp(String firstName, String lastName,String email, String recomm1,String recomm2,String recomm3,String universityName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.recomm1 = recomm1;
        this.recomm2 = recomm2;
        this.recomm3 = recomm3;
        this.universityName = universityName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getRecomm1() {
        return recomm1;
    }

    public void setRecomm1(String recomm1) {
        this.recomm1 = recomm1;
    }

    public String getRecomm2() {
        return recomm2;
    }

    public void setRecomm2(String recomm2) {
        this.recomm2 = recomm2;
    }

    public String getRecomm3() {
        return recomm3;
    }

    public void setRecomm3(String recomm3) {
        this.recomm3 = recomm3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    
    
    
    public void triggerMail(String firstName, String lastName,String email, String recomm1,String recomm2,String recomm3,String universityName) {

        // Recipient's email ID needs to be mentioned.
        String to1 = recomm1;
        String to2 = recomm2;
        String to3 = recomm3;

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
                    new InternetAddress(to1));
            
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to2));

            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to3));


            // Set Subject: header field
            message.setSubject("Recommondation Required for "+firstName+" "+lastName+" -"+universityName);

            // Send the actual HTML message, as big as you like
           /* message.setContent("Dear "+firstName+" "+lastName+","+"</br>"+" Congratulations! Your account has been successfully created."+"</br>"+"Your userID is "+userID+"</br>"+"Your Password is "+password1+"</br>"+"Your security question: "+question+"<br/>"+"Your Answer: "+answer+"<br/>"+"You can now avail financial consultancy from Nikunj Ratnaparkhi and Shweta Vyas.</br>"+ "<img src=\"http://www.rgagnon.com/images/jht.gif\">",
                    "text/html");*/
            
            
            message.setContent("Dear Recommendator"+"</br>"+" Congratulations! "+ "<img src=\"http://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Wave.svg/170px-Wave.svg.png\">","text/html");
            
            //message.append("<img src=\"cid:image2\" width=\"15%\" height=\"15%\" /><br>");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
