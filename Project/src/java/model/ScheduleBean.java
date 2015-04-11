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

public class ScheduleBean {
    // These correspond to the form elements
    private String email;
    private String university;
    private String date="";
    private String slot="";

    public ScheduleBean(String email,String university, String date, String slot) {
        this.email= email;
        this.university = university;
        this.date = date;
        this.slot = slot;
    }

    public ScheduleBean() {
    }

    public String getUniversity() {
        return university;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
     
    
}
