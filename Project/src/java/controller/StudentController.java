/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import model.StudentBean;
import model.SearchStudentBean;
/**
 *
 * @author admin
 */


@ManagedBean
@SessionScoped
public class StudentController implements Serializable {

    // This corresponds to the response to be sent back to the client
    private String response;
    private StudentBean studentBean;
    private SearchStudentBean searchStudentBean;
    private String details;
    
    public StudentController() {
        studentBean = new StudentBean();
        searchStudentBean= new SearchStudentBean();
    }

    @ManagedProperty(value="#{imageUploadBean}")
    private ImageUploadBean imageUploadBean;

    public ImageUploadBean getImageUploadBean() {
        return imageUploadBean;
    }

    public void setImageUploadBean(ImageUploadBean imageUploadBean) {
        this.imageUploadBean = imageUploadBean;
    }

    public SearchStudentBean getSearchStudentBean() {
        return searchStudentBean;
    }

    public void setSearchStudentBean(SearchStudentBean searchStudentBean) {
        this.searchStudentBean = searchStudentBean;
    }
    private ArrayList studentList;
    
    public ArrayList getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList studentList) {
        this.studentList = studentList;
    }

      
    public String isValid1(ComponentSystemEvent event){
        
        String navi=null;
         FacesContext fc = FacesContext.getCurrentInstance();
         ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
         
        
        if(studentBean.getPassword1().equals(studentBean.getPassword2())){
        StudentDAO aStudentDAO = new StudentDAOImpl();    // Creating a new object each time.
        ArrayList studentExist = aStudentDAO.findByEmail(studentBean.getEmail()); // Doing anything with the object after this?
       // int rowCount;
        
        if(!studentExist.isEmpty()){
            nav.performNavigation("error?faces-redirect=true");
        }
        
        
     }
        else{
            nav.performNavigation("error?faces-redirect=true");
        }
            return navi;
    }
    
    
    
    
    
    public String searchAll(){
      // String temp =studentBean.getFirstName();
       if(searchStudentBean.getFirstName().isEmpty() && searchStudentBean.getLastName().isEmpty() && searchStudentBean.getMinScore()==0 && searchStudentBean.getMaxScore()==0 && searchStudentBean.getCollege().length==0){
        StudentDAO aStudentDAO = new StudentDAOImpl();    // Creating a new object each time.
        
        //String[] temp = studentBean.getMajor();
        //String majorarr = temp.toString();
        studentList=aStudentDAO.searchAllStudentDb();
        if(!studentList.isEmpty())
                return "newDisplay.xhtml";//return "studentSearchResult.xhtml"; // navigate to "echo.xhtml"
            else
                return "noRecord.xhtml"; 
       }
       
       //if(!searchStudentBean.getFirstName().isEmpty() || !searchStudentBean.getLastName().isEmpty()){
       else{
        StudentDAO aStudentDAO = new StudentDAOImpl();    // Creating a new object each time.
        
        String[] collegeList = searchStudentBean.getCollege();
        //String majorarr = temp.toString();
        studentList=aStudentDAO.searchSelectedStudentDb(searchStudentBean.getFirstName(), searchStudentBean.getLastName(), searchStudentBean.getMinScore(), searchStudentBean.getMaxScore(), collegeList);
        if(!studentList.isEmpty())
                return "newDisplay.xhtml"; // navigate to "echo.xhtml"
            else
                return "noRecord.xhtml"; 
       }
        //return "error.xhtml";
    }
    
    public String createStudent(){
        studentBean.setPhotoPath(imageUploadBean.findPath());
        StudentDAO aStudentDAO = new StudentDAOImpl();    // Creating a new object each time.
        int rowCount;
        boolean flag;
        
        rowCount=aStudentDAO.createStudentDb(getStudentBean());
       // flag=aStudentDAO.uploadImageStudentDb(getImageBean());
            if(rowCount==1)
                return "thankYouStudent.xhtml"; // navigate to "echo.xhtml"
            else
                return "errorSignUp.xhtml"; 
        
    }
    
    
    public String errorSignUp(){
        return "Error in Signing Up New Account <br/>Please try again!";
    }

//    public String uploadImage(){
//        StudentDAO aStudentDAO = new StudentDAOImpl();
//        boolean flag;
//        flag=aStudentDAO.uploadImageStudentDb(getImageBean());
//        return "something";
//    }
    /**
     * @return the response
     */
    public String getResponse() {
      //  JavaMailApp jma= new JavaMailApp();
       // jma.triggerMail(studentBean.getFirstName(),studentBean.getLastName(),studentBean.getPassword1(),studentBean.getPassword2(),studentBean.getDob(),studentBean.getEmail(),studentBean.getContactNo(), studentBean.getAddress1(), studentBean.getAddress2(), studentBean.getCity(), studentBean.getState(), studentBean.getCountry(), studentBean.getZip());
        String resultStr = "";
        
        resultStr +="Congratulations! Thank you for registration!"+"<br/>";
        
        resultStr += "Hello " + studentBean.getFirstName() +" "+studentBean.getLastName()+ "<br/>";
        resultStr += "Your password is sent to your registered email address which is "+studentBean.getEmail()+ "<br/>";
        
        
        response=resultStr;
        return response;
    }
    
    public String getPasswordError(){
        String resultStr = "";
        
        resultStr +="Unmatched Passwords!<br/>Please enter same password in Password and Confirm Password field"+"<br/>";
        
        response=resultStr;
        return response;
    }
    
    public String getError(){
        String resultStr = "";
        
        resultStr +="Email ID already exist!<br/>Please proceed with Sign-In"+"<br/>";
        
        response=resultStr;
        return response;
    }
    
    public String getNoRecord(){
        String resultStr = "";
        
        resultStr +="No Record Found!<br/>Please search with different catagory"+"<br/>";
        
        response=resultStr;
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the signUpBean
     */
    public StudentBean getStudentBean() {
        return studentBean;
    }

    /**
     * @param studentBean
     */
    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }
    
    //New Addition 18 Nov
    
    public String getDetails() {
        String str = "Name: " +studentBean.getFirstName() + " " + studentBean.getLastName();
        StudentDAO aStudentDetails = new StudentDAOImpl();
        studentBean =(StudentBean) aStudentDetails.findByName(studentBean.getFirstName(), studentBean.getLastName());
        str+= "<br/><br/>School Attended: "+studentBean.getSchoolName();
        str+= "<br/><br/>Current GPA: "+studentBean.getGpa();
        str+="<br/><br/>Exam Taken: "+studentBean.getExam();
        str+="<br/><br/>Score: "+studentBean.getScore();
        str+="<br/><br/>Target School: "+studentBean.targetSchoolString().replace(", .", ".");
        str+="<br/><br/>Major: "+studentBean.majorString().replace(", .", ".");
        str+="<br/><br/>Extra Curricular: "+studentBean.getExtraC();
        str+="<br/><br/>Awards: "+studentBean.getAwards();
        str+="<br/><br/>Sports: "+studentBean.getSports();
        
        
        details = str;
        return details;
    }

    public String findPhotoPath(){
        return studentBean.getPhotoPath();
    }
    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
     public String searchItem()
     {                                               
          StudentDAO aSearchDAO = new StudentDAOImpl();    // Creating a new object each time.
          if(searchStudentBean.getFirstName()!=null || searchStudentBean.getLastName()!=null)
            studentBean = aSearchDAO.findByName(searchStudentBean.getFirstName(),searchStudentBean.getLastName());                   
           return "studentIndividualDetails.xhtml";
     }   
     
}
