/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDAOImpl;
import dao.UniversityDAO;
import dao.UniversityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.JavaMailApp;
import model.SearchUniversityBean;
import model.StudentBean;
import model.UniversityBean;




/**
 *
 * @author admin
 */


@ManagedBean (name="universityController")
@SessionScoped
public class UniversityController implements Serializable {

    
    private StudentBean studentBean;
    private UpdateController updateController;
    private UniversityBean universityBean;
    private StudentController studentController;
    String response="";
    String tempEmail;
    private SearchUniversityBean searchUniversityBean;
    private ArrayList searchUniversityList;

    public SearchUniversityBean getSearchUniversityBean() {
        return searchUniversityBean;
    }

    public void setSearchUniversityBean(SearchUniversityBean searchUniversityBean) {
        this.searchUniversityBean = searchUniversityBean;
    }

    public ArrayList getSearchUniversityList() {
        return searchUniversityList;
    }

    public void setSearchUniversityList(ArrayList searchUniversityList) {
        this.searchUniversityList = searchUniversityList;
    }
    
    @ManagedProperty(value="#{fileUploadMBean}")
    private FileUploadMBean fileUploadMBean;

    public FileUploadMBean getFileUploadMBean() {
        return fileUploadMBean;
    }

    public void setFileUploadMBean(FileUploadMBean fileUploadMBean) {
        this.fileUploadMBean = fileUploadMBean;
    }
    
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
   
    
    
    

    public UniversityController() {
        universityBean= new UniversityBean();
        studentBean = new StudentBean();
        searchUniversityBean = new SearchUniversityBean();
    }

    public UniversityBean getUniversityBean() {
        return universityBean;
    }

    public void setUniversityBean(UniversityBean universityBean) {
        this.universityBean = universityBean;
    }

  
    
    
    public StudentBean getStudentBean() {
        return studentBean;
    }

    public void setStudentBean(StudentBean studentBean) {
        this.studentBean = studentBean;
    }

//    public String universitySpecificPage(){
//        if(universityBean.getUniversityName().equals("Illinois State University"))
//               return "ISUAdditionalPage.xhtml";
//        else if(universityBean.getUniversityName().equals("Massachusetts Institute of Technology"))
//                return "MITAdditionalPage.xhtml";
//        else if(universityBean.getUniversityName().equals("University of Illinois, Urbana Champaign"))
//                return "UIUCAdditionalPage.xhtml";
//        else 
//                return "PUAdditionalPage.xhtml";
//    }

    
    
    ArrayList universityList;
    
    public ArrayList getUniversityList() {
        return universityList;
    }

    public void setUniversityList(ArrayList universityList) {
        this.universityList = universityList;
    }
    
    public String searchItem()
     {                                               
          UniversityDAO aUniversitySearchDAO = new UniversityDAOImpl();    // Creating a new object each time.

        if (searchUniversityBean.getName() != null) {
            searchUniversityBean = aUniversitySearchDAO.findByName(searchUniversityBean.getName());
        }
        return "universityIndividualDetails.xhtml";
     } 
    
    public String searchUniversity(){
       // if(universityBean.getUniversityNameArray().length==0 && universityBean.getState().equals("All") && (universityBean.getExam().contentEquals("ACT") || universityBean.getExam().contentEquals("SAT")) && universityBean.getMinScore()==0 && universityBean.getMaxScore()==0 && universityBean.getAccRate().isEmpty() && universityBean.getSchRate().isEmpty()){
       UniversityDAOImpl aUniversityDAO = new UniversityDAOImpl();    // Creating a new object each time.

        searchUniversityList = aUniversityDAO.searchAllUniversity(universityBean);
        if(!searchUniversityList.isEmpty())
                return "universityDisplay.xhtml";//return "studentSearchResult.xhtml"; // navigate to "echo.xhtml"
            else
                return "noUniversityRecord.xhtml"; 
     //  }
       
       //if(!searchStudentBean.getFirstName().isEmpty() || !searchStudentBean.getLastName().isEmpty()){
       //else{
//        StudentDAO aStudentDAO = new StudentDAOImpl();    // Creating a new object each time.
//        
//        String[] collegeList = searchStudentBean.getCollege();
//        //String majorarr = temp.toString();
//        studentList=aStudentDAO.searchSelectedStudentDb(searchStudentBean.getFirstName(), searchStudentBean.getLastName(), searchStudentBean.getMinScore(), searchStudentBean.getMaxScore(), collegeList);
//        if(!studentList.isEmpty())
//                return "newDisplay.xhtml"; // navigate to "echo.xhtml"
//            else
//                return "noRecord.xhtml"; 
           // return "noRecord.xhtml"; 
       //}
    }
    
    public String universityIndividualDetails(){
        String details;
        details = "College Name: " + searchUniversityBean.getName()+"<br/>";
        details = details + "Address: " + searchUniversityBean.getAddr1() + ", "+searchUniversityBean.getAddr2()+"<br/>";
        details = details + searchUniversityBean.getCity()+", "+searchUniversityBean.getState()+"<br/>";
        details = details + searchUniversityBean.getZipcode()+" ,"+searchUniversityBean.getCountry();
        return details;
        
    }
    
    public String universitySearchResponse(){
        return "Done!------------";
    }
    
    
    
    public void sendMail(){
         JavaMailApp jma= new JavaMailApp();
         StudentDAOImpl aStudentDAO=new StudentDAOImpl();
       
          FacesContext facesContext = FacesContext.getCurrentInstance();
          Controller controller = (Controller) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "controller");
         tempEmail = controller.findEmail();
         ArrayList listStudentDb = aStudentDAO.findByEmail(tempEmail);
         studentBean = (StudentBean)listStudentDb.get(0);
         System.out.println("Here-1- "+tempEmail);
         jma.triggerMail(studentBean.getFirstName(), studentBean.getLastName(),tempEmail, universityBean.getRecomm1(),universityBean.getRecomm2(),universityBean.getRecomm3(),universityBean.getUniversityName());
    }
    
    public String getResponse(){
        response+="Application sent successfully <br/>";
        response+="One of the representatives from the "+universityBean.getUniversityName()+" will contact you soon.<br/>";
        response+="All The Best!";
        
        return response;
    }
    
    public String saveDetailsInDb(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Controller controller = (Controller) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "controller");
        tempEmail = controller.findEmail();
        StudentDAOImpl studentDAOImpl= new StudentDAOImpl();
        ArrayList studentDetails = studentDAOImpl.findByEmail(tempEmail);
        studentBean = (StudentBean)studentDetails.get(0);
        
        
        
        FileUploadMBean fileUploadMBean = (FileUploadMBean) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "fileUploadMBean");
        String tempPath = fileUploadMBean.findFilePath();
        
        
        UniversityDAOImpl aUniversityDAO = new UniversityDAOImpl();
        int rowCount = aUniversityDAO.insertStudentInUniversityDb(universityBean.getUniversityName(), universityBean.getRecomm1(), universityBean.getRecomm2(), universityBean.getRecomm3(),tempEmail, studentBean.getFirstName(), studentBean.getLastName(), studentBean.getDob(), studentBean.getExam(), studentBean.getGpa(), studentBean.getMajor(), studentBean.getExtraC(), studentBean.getScore(), studentBean.getCountry(), tempPath);
        if(rowCount==1){
            response+="Your application is saved in the database <br/>";
        }
        return "applySuccess.xhtml";
    }
    
    
    
    
}
