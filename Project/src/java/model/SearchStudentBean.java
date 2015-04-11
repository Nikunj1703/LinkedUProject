/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nratnap
 */
public class SearchStudentBean {

    
    private String firstName;
    private String lastName;
    private int minScore=0;
    private int maxScore=0;
    private String[] college;
   

    public SearchStudentBean() {
    }

    public SearchStudentBean(String firstName, String lastName, int minScore, int maxScore, String[] college) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.college = college;
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

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
    
    // These correspond to the form elements
    public String[] getCollege() {
        return college;
    }

    public void setCollege(String[] college) {
        this.college = college;
    }
    

  
   

 
    
    
    
    
   
}
