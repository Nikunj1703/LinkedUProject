/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UniversitySignUpDAO;
import dao.UniversitySignUpDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.UniversitySignUpBean;

/**
 *
 * @author DELL
 */

@ManagedBean
@SessionScoped
public class UniversitySignUpController {

    // This is the model that captures the user profile info
    private UniversitySignUpBean theModel;
//    private Exam exam;
// This corresponds to the response to be sent back to the client
    private String response;
    
    public UniversitySignUpController() {
        
    theModel = new UniversitySignUpBean();
    
}

    /**
     * @return the theModel
     */
    public UniversitySignUpBean getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(UniversitySignUpBean theModel) {
        this.theModel = theModel;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        String resultStr="";
        //resultStr = theModel.getName();
        
       // resultStr += theModel.getName() + "<br/>";
        resultStr += "Congratulations " + theModel.getName() + "<br/>"
                +"You are linked with us.";
//        resultStr += "Fee Structure:"+ theModel.getFees() + "<br/>"+
//                "Acceptance Rate:" + theModel.getAccptRate();
//        resultStr += "Minimum Scores required:" + "<br/>"+
//                "ACT Score" + theModel.getACTScore() + "SAT Score" + theModel.getSATScore();
//        resultStr += "<br/>"+"<br/>"+"Contact us:";
        
        response = resultStr;
        
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
    
    
    public String createProfile() {
        UniversitySignUpDAO aProfileDAO = new UniversitySignUpDAOImpl();    // Creating a new object each time.
        int rowCount = aProfileDAO.createProfile(theModel); // Doing anything with the object after this?
        if (rowCount == 1)
            return "UniversitySignUpResponse.xhtml"; // navigate to "response.xhtml"
        else
            return "error.xhtml"; 
    }
}
