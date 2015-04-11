/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDAOImpl;
import dao.UniversityDAOImpl;
import java.util.ArrayList;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.StudentBean;
import model.UniversitySignUpBean;
import model.UniversityUpdateBean;

/**
 *
 * @author admin
 */
@ManagedBean
@SessionScoped
public class UpdateController {

    // This corresponds to the response to be sent back to the client
  //  private String userId;
    //private LoginBean theLogin;
    private String updateStatus;
    private StudentBean studentBean;
    private UniversitySignUpBean universitySignUpBean;
   

    

    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    
    

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UniversitySignUpBean getUniversitySignUpBean() {
        return universitySignUpBean;
    }

    public void setUniversitySignUpBean(UniversitySignUpBean universitySignUpBean) {
        this.universitySignUpBean = universitySignUpBean;
    }

    

   
    
    
    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }

    public UpdateController() {
        //theLogin = new LoginBean();
        studentBean = new StudentBean();
        universitySignUpBean = new UniversitySignUpBean();
    }

    public StudentBean getSignUpBean() {
        return studentBean;
    }

    public void setSignUpBean(StudentBean signUpBean) {
        this.studentBean = signUpBean;
    }

    

   

  
   
   

   

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String isLogin(ComponentSystemEvent event){

        if (controller.findEmail()==null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("login?faces-redirect=true");
           
        }
        return null;
    }
    public String retrieveProfilePart1() {
        StudentDAOImpl aStudentDAOImpl = new StudentDAOImpl();    // Creating a new object each time.
       // ArrayList result = aStudentDAOImpl.findByEmail(studentBean.getEmail()); // Replace with this later.
        ArrayList result = aStudentDAOImpl.findByEmail(controller.findEmail()); 
        studentBean = (StudentBean) result.get(0); // if multiple found, just pick the 1st one. If none?
        if (studentBean != null) 
            return "updateStudentBeforeApply.xhtml"; // navigate to "update2.xhtml"
        else
            return "error.xhtml"; 
    }
    
    public String retrieveUniversityProfile(){
         UniversityDAOImpl aUniversityDAOImpl = new UniversityDAOImpl();    // Creating a new object each time.
       // ArrayList result = aStudentDAOImpl.findByEmail(studentBean.getEmail()); // Replace with this later.
        ArrayList result = aUniversityDAOImpl.findByUniversityId(controller.findEmail(), controller.findPassword());
        universitySignUpBean = (UniversitySignUpBean) result.get(0); // if multiple found, just pick the 1st one. If none?
        
        if (universitySignUpBean != null) 
            return "updatePrepopulateUniversity.xhtml"; // navigate to "update2.xhtml"
        else
            return "error.xhtml"; 
    }
    
    
    
//    public void updateThis() {
//        StudentDAOImpl aStudentDAOImpl = new StudentDAOImpl();   // Creating a new object each time.
//        int status = aStudentDAOImpl.updateStudentDb(studentBean); // Doing anything with the object after this?
//        if (status != 0) {
//            updateStatus = "Record updated successfully ...";
//        } else {
//            updateStatus = "Record update failed!";
//        }
//
//    }
    
    public String updateUniversityProfile(){
        String tempEmail = controller.findEmail();
        String newAbout = universitySignUpBean.getAboutUniversity();
        int newFees = universitySignUpBean.getFees();
        float newARate = universitySignUpBean.getAccptRate();
        float newSRate = universitySignUpBean.getScRate();
        int newACT = universitySignUpBean.getACTScore();
        int newSAT = universitySignUpBean.getSATScore();
        
        UniversityDAOImpl aUniversityDAOImpl = new UniversityDAOImpl();
        int rowCount = aUniversityDAOImpl.updateUniversityInDb(tempEmail, newAbout, newFees, newARate, newSRate, newACT, newSAT);
        if(rowCount==1){
            return "updateUniversityMsg.xhtml";
        }
        else{
            return "error.xhtml";
        }
        
    }
    
   
    
    public String tempAboutValue(){
        System.out.println("here");
        //return universitySignUpBean.getAboutUniversity();
        return "Hello"; 
    }
    
    public String updateUniversityMsg(){
        return "The changes have been saved in database.";
    }
    String response;
    public String saveStudentUpdate(){
        
        
        String tempEmail = controller.findEmail();
        StudentDAOImpl studentDAOImpl= new StudentDAOImpl();
        //ArrayList studentDetails = studentDAOImpl.findByEmail(tempEmail);
        //studentBean = (StudentBean)studentDetails.get(0);
        
        StudentDAOImpl aStudentDAO = new StudentDAOImpl();
        
        String tempMajor ="";
        
        for(int i=0;i<studentBean.getMajor().length;i++){
            tempMajor+=studentBean.getMajor()[i]+";";
        }
        
        int rowCount = aStudentDAO.updateStudentInDb(tempEmail, studentBean.getContactNo(), studentBean.getAddress1(), studentBean.getAddress2(), studentBean.getCity(), studentBean.getState(), studentBean.getCountry(), studentBean.getZip(), studentBean.getSchoolName(), studentBean.getGpa(), studentBean.getExam(), studentBean.getScore(), tempMajor, studentBean.getExtraC(), studentBean.getAwards(), studentBean.getSports());
        if(rowCount==1){
            response="Profile updated <br/>";
        }
        return "studentUpdateMsg.xhtml";
    }
    
    public String studentUpdateMsg(){
        return response;
    }
    
  
}
