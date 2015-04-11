/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.ScheduleAppointmentMail;
import model.ScheduleBean;
/**
 *
 * @author svyas
 */


@ManagedBean
@SessionScoped
public class ScheduleController implements Serializable {

    // This corresponds to the response to be sent back to the client
    private String response;
    private ScheduleBean scheduleBean;
    private String availabilityMessage="";
    
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    

    public String getAvailabilityMessage() {
        if(scheduleBean.getDate().isEmpty() || scheduleBean.getSlot().isEmpty()){
            availabilityMessage = "";
        }
        else{
        ScheduleDAO aScheduleDAO = new ScheduleDAOImpl();
        ArrayList scheduleList=aScheduleDAO.checkAvailabilityInDB(scheduleBean.getDate(),scheduleBean.getSlot());
        if(!scheduleList.isEmpty())
              availabilityMessage = " Sorry! Slot is already booked. Please choose another time slot!";
        else{
            
                availabilityMessage = " The chosen time slot is available! Click on Book Slot to confirm your appointment.";
            }
        }
    
        return availabilityMessage;
    }

    public ScheduleController() {
        scheduleBean = new ScheduleBean();
    }

    
    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }
    
    public String bookSlot(){
        ScheduleDAO aScheduleDAO = new ScheduleDAOImpl();
        scheduleBean.setEmail(controller.findEmail());
        ArrayList scheduleList=aScheduleDAO.checkAvailabilityInDB(scheduleBean.getDate(),scheduleBean.getSlot());
        if(!scheduleList.isEmpty())
                return "error.xhtml";
        else{
            
            int rowCount=aScheduleDAO.createSchedule(scheduleBean);
            if(rowCount==1){
               
         ScheduleAppointmentMail jma= new ScheduleAppointmentMail();
         
          FacesContext facesContext = FacesContext.getCurrentInstance();
          ScheduleController scheduleController = (ScheduleController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "scheduleController");
          String tempEmail=controller.findEmail();
          System.out.println("Here-1- "+tempEmail);
          
          String tempStudentName = controller.findStudentName();
          jma.triggerMail(tempStudentName, scheduleBean.getUniversity(),scheduleBean.getDate(),scheduleBean.getSlot(),tempEmail);
    
    
                return "responseAppointment.xhtml";
            }
            else{
                return "error.xhtml";
            }
        }
                
       }
        
        
  
    /**
     * @return the response
     */
    public String getResponse() {
      //  JavaMailApp jma= new JavaMailApp();
       // jma.triggerMail(studentBean.getFirstName(),studentBean.getLastName(),studentBean.getPassword1(),studentBean.getPassword2(),studentBean.getDob(),studentBean.getEmail(),studentBean.getContactNo(), studentBean.getAddress1(), studentBean.getAddress2(), studentBean.getCity(), studentBean.getState(), studentBean.getCountry(), studentBean.getZip());
        String resultStr = "";
        
        resultStr +="Congratulations! Your appointment is booked!"+"<br/>";
        
        response=resultStr;
        return response;
    }
    

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
     
}
