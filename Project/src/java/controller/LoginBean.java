/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nratnap
 */
@ManagedBean (name="loginBean")
@SessionScoped
public class LoginBean {

    private String emailID;
    private String password;

    public LoginBean() {
    }

    public LoginBean(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }
    //LoginBean lb=new LoginBean("Billy111","aaa");
    
    

    /**
     * @return the userName
     */
    public String getEmailID() {
        return emailID;
    }

   
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
