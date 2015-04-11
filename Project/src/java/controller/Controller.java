/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.StudentDAO;
import dao.StudentDAOImpl;
import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import java.util.ArrayList;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.ForgotPasswordBean;
import model.ForgotPasswordMail;
import model.StudentBean;
import model.UniversitySignUpBean;



/**
 *
 * @author admin
 */
@ManagedBean (name="controller")
@SessionScoped
public class Controller {

    int count=1;
    // This corresponds to the response to be sent back to the client
    private String response="";
    private LoginBean loginBean;
    private StudentBean studentBean;
    private ForgotPasswordBean forgotPasswordBean;
    private ForgotPasswordMail forgotPasswordMail;
    private UniversitySignUpBean universitySignUpBean;
    
    
    
    
    
    /**
     * Creates a new instance of ProfileController
     */
    public Controller() {
        loginBean = new LoginBean();
        forgotPasswordBean = new ForgotPasswordBean();
        forgotPasswordMail = new ForgotPasswordMail();
        universitySignUpBean = new UniversitySignUpBean();
        //studentBean = new StudentBean();
    }

    public ForgotPasswordBean getForgotPasswordBean() {
        return forgotPasswordBean;
    }

    public void setForgotPasswordBean(ForgotPasswordBean forgotPasswordBean) {
        this.forgotPasswordBean = forgotPasswordBean;
    }
    
    

    public LoginBean getTheModel() {
        return loginBean;
    }

    public void setTheModel(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

   
    public String getResponse() {
            return response;
    }
    
   
    
    
    public String countAttempt(ComponentSystemEvent event){
        String navi = null;

        if (count>3) {
            
            FacesContext fc = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("access-denied?faces-redirect=true");
           
        }

        return navi;
    }
    String tempEmail;
    String tempPassword;
    
    public String findEmail(){
        
        
        return tempEmail;
        //return "Hello";
    }
    
    public String findPassword(){
        return tempPassword;
    }
    public String authenticate() {
       response="";
        StudentDAO aStudentDAO = new StudentDAOImpl();
        UniversityDAO aUniversityDAO = new UniversityDAOImpl();
        ArrayList rowIdentify = aStudentDAO.findByUserId2(loginBean.getEmailID(), loginBean.getPassword());
        tempEmail = loginBean.getEmailID();
        tempPassword = loginBean.getPassword();
        
        if(rowIdentify.isEmpty()){
            ArrayList rowIdentifyUniversity = aUniversityDAO.findByUniversityId(loginBean.getEmailID(), loginBean.getPassword());
            if(rowIdentifyUniversity.isEmpty()){
                count++;
                return "LoginBad.xhtml"; 
            }
            else{
                universitySignUpBean = (UniversitySignUpBean)rowIdentifyUniversity.get(0);
                response+="Welcome Back, "+universitySignUpBean.getName()+"!"+"<br/><br/>";
                response+="Below are the details which are being shown to the students: <br/><br/>";
                response+="University Name: "+universitySignUpBean.getName()+"<br/>";
                //response+="City: "+universitySignUpBean.getCity()+"<br/>";
               // response+="State: "+universitySignUpBean.getState()+"<br/>";
               // response+="Country: "+universitySignUpBean.getCountry()+"<br/>";
               // response+="Contact: "+universitySignUpBean.getContact()+"<br/>";
                response+="About University: "+universitySignUpBean.getAboutUniversity()+"<br/>";
               // response+="Selected Courses: "+universitySignUpBean.selectedCourseString()+"<br/>";
                response+="Fees: "+universitySignUpBean.getFees()+"<br/>";
                response+="Acceptance Rate: "+universitySignUpBean.getAccptRate()+"<br/>";
                response+="Scholarship Rate: "+universitySignUpBean.getScRate()+"<br/>";
                response+="ACT Score: "+universitySignUpBean.getACTScore()+"<br/>";
                response+="SAT Score: "+universitySignUpBean.getSATScore();
                
                count=1;
                return "LoginGoodUniversity.xhtml";
                
            }
            
            
        }
        else{
                    ArrayList listStudentDb = aStudentDAO.findByEmail(loginBean.getEmailID());
                    studentBean = (StudentBean)listStudentDb.get(0);
                    response+="Welcome Back, "+studentBean.getFirstName()+"!"+"<br/><br/>";
                    //response+="Either directly apply for the University or update your profile and proceed";
                    response+="<u>Below are your profile details: </u><br/>";
                    response+= "<br/><br/>School Attended: "+studentBean.getSchoolName();
                    response+= "<br/><br/>Current GPA: "+studentBean.getGpa();
                    response+="<br/><br/>Exam Taken: "+studentBean.getExam();
                    response+="<br/><br/>Score: "+studentBean.getScore();
                    response+="<br/><br/>Target School: "+studentBean.targetSchoolString().replace(", .", ".");
                    response+="<br/><br/>Major: "+studentBean.majorString().replace(", .", ".");
                    response+="<br/><br/>Extra Curricular: "+studentBean.getExtraC();
                    response+="<br/><br/>Awards: "+studentBean.getAwards();
                    response+="<br/><br/>Sports: "+studentBean.getSports();


                    count=1;
                    return "LoginGood.xhtml";
        
       
                
            
        }
        
    }
        
    public String findStudentName(){
        return studentBean.getFirstName();
    }

    public String getError(){
        String resultStr = "";
        
        resultStr +="Incorrect Credentials"+"<br/>"+"Please try again.";
        
        response=resultStr;
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    
    
    public String forgotPassword(){
        return "forgotPassword.xhtml";
    }
    
    String forgotPasswordResponse="";
    public String fetchPasswordFromDb(){
        StudentDAO aStudentDAO = new StudentDAOImpl();
        String password = aStudentDAO.findPassword(forgotPasswordBean.getEmail());
        forgotPasswordMail.triggerMail(password, forgotPasswordBean.getEmail());
        return "forgotPasswordResponse.xhtml";
        
    }
    
    public String fetchPasswordResponse(){
        forgotPasswordResponse+="Your password sent to your registered email address "+forgotPasswordBean.getEmail()+"<br/>Thank you for using our services";
        return forgotPasswordResponse;
    }

}
