/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ScheduleAppointmentMail {

    private String studenttName;
    private String university;
    String date;
    String slot;
    String email;

    public ScheduleAppointmentMail() {
    }

    public ScheduleAppointmentMail(String studenttName, String university, String date, String slot, String email) {
        this.studenttName = studenttName;
        this.university = university;
        this.date = date;
        this.slot = slot;
        this.email = email;
    }

    public String getStudenttName() {
        return studenttName;
    }

    public void setStudenttName(String studenttName) {
        this.studenttName = studenttName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

      
    
    public void triggerMail(String studentName, String university, String date, String slot, String email) {

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
            message.setSubject("Your slot is booked!");

            message.setContent("Dear "+studentName+"</br>"+" Your Slot is booked! You are required to visit "+university+"campus on "+date+"and your time slot for appointment is "+slot+ "<br/>" + "Please arrive 15 minutes prior to your scheduled appointment."+"<br/>"+"Regards,"+"<br/>"+"Linked U Team","text/html");
          
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
