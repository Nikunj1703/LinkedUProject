/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class UniversitySignUpBean {
    private String name;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    
    private String password;
    private String password2;
    private String email;
    private String contact;
    private String aboutUniversity;
    private String[] selectedCourses;
    private List<String> courses;
    private int fees;
    private float AccptRate;
    private float scRate;
    private int ACTScore;
    private int SATScore;
    
    
public UniversitySignUpBean() {
        courses = new ArrayList<String>();
    
        courses.add("Art");
        courses.add("Information Technology");
        courses.add("Management & Quantitative Methods");
        courses.add("Marketing");
        courses.add("Mathematics");
        courses.add("Music");
        courses.add("Theatre");
        courses.add("Others");  
             
    }

    public UniversitySignUpBean(String name, String addr1, String addr2, String city,
            String state, String country, String zipcode, String email, String contact, 
            String password, String aboutUniversity, String[] selectedCourses,
            int fees, float AccptRate,int ACTScore, int SATScore)
    {
        this.name = name;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.email = email;
        this.contact = contact;
        //this.userName = username;
        this.password = password;
        this.aboutUniversity = aboutUniversity;
        this.selectedCourses = selectedCourses;
        this.fees = fees;
        this.AccptRate = AccptRate;
        this.ACTScore = ACTScore;
        this.SATScore = SATScore;
        

    }

    public UniversitySignUpBean(String name, String city, String state, String country, String contact, String aboutUniversity, String[] selectedCourses, int fees, float AccptRate, float scRate, int ACTScore, int SATScore) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.contact = contact;
        this.aboutUniversity = aboutUniversity;
        this.selectedCourses = selectedCourses;
        this.fees = fees;
        this.AccptRate = AccptRate;
        this.scRate = scRate;
        this.ACTScore = ACTScore;
        this.SATScore = SATScore;
    }
    
    

   

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the addr1
     */
    public String getAddr1() {
        return addr1;
    }

    /**
     * @param addr1 the addr1 to set
     */
    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    /**
     * @return the addr2
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * @param addr2 the addr2 to set
     */
    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
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

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return the aboutUniversity
     */
    public String getAboutUniversity() {
        return aboutUniversity;
    }

    /**
     * @param aboutUniversity the aboutUniversity to set
     */
    public void setAboutUniversity(String aboutUniversity) {
        this.aboutUniversity = aboutUniversity;
    }

    /**
     * @return the selectedCourses
     */
    public String[] getSelectedCourses() {
        return selectedCourses;
    }

    /**
     * @param selectedCourses the selectedCourses to set
     */
    public void setSelectedCourses(String[] selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    /**
     * @return the courses
     */
    public List<String> getCourses() {
        return courses;
    }

    /**
     * @param courses the courses to set
     */
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    /**
     * @return the fees
     */
    public int getFees() {
        return fees;
    }

    /**
     * @param fees the fees to set
     */
    public void setFees(int fees) {
        this.fees = fees;
    }

    /**
     * @return the AccptRate
     */
    public float getAccptRate() {
        return AccptRate;
    }

    /**
     * @param AccptRate the AccptRate to set
     */
    public void setAccptRate(float AccptRate) {
        this.AccptRate = AccptRate;
    }

    /**
     * @return the ACTScore
     */
    public int getACTScore() {
        return ACTScore;
    }

    /**
     * @param ACTScore the ACTScore to set
     */
    public void setACTScore(int ACTScore) {
        this.ACTScore = ACTScore;
    }

    /**
     * @return the SATScore
     */
    public int getSATScore() {
        return SATScore;
    }

    /**
     * @param SATScore the SATScore to set
     */
    public void setSATScore(int SATScore) {
        this.SATScore = SATScore;
    }

    /**
     * @return the scRate
     */
    public float getScRate() {
        return scRate;
    }

    /**
     * @param scRate the scRate to set
     */
    public void setScRate(float scRate) {
        this.scRate = scRate;
    }

    
    
    public String selectedCourseString(){
        String temp="";
        for(int i=0;i<getSelectedCourses().length;i++){
            temp+=getSelectedCourses()[i] +";";
        }
        return temp;
    }

}