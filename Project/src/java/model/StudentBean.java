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
public class StudentBean {
    // These correspond to the form elements

    private String firstName;
    private String lastName;
    private String password1;
    private String password2;
    private String dob;
    private String email;
    private String contactNo;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String schoolName;
    private String gpa;
    private String[] targetSchool;
    private String[] major;
    private String score;
    private String extraC;
    private String awards;
    private String sports;
    private String exam;
    private String photoPath;
    private String sop;

    public StudentBean() {
    }

    public StudentBean(String firstName, String lastName, String email, String password1, String password2, String dob, String contactNo, String address1, String address2, String city, String state, String country, String zip, String schoolName, String gpa, String exam, String score, String[] targetSchool, String[] major, String extraC, String awards, String sports, String photoPath, String sop) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.dob = dob;
        this.contactNo = contactNo;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.schoolName = schoolName;
        this.gpa = gpa;
        this.exam=exam;
        this.score = score;
        this.targetSchool = targetSchool;
        this.major = major;
        this.extraC = extraC;
        this.awards = awards;
        this.sports = sports;
        this.photoPath = photoPath;
        this.sop = sop;
    }

   
    

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String[] getTargetSchool() {
        return targetSchool;
    }

    public void setTargetSchool(String[] targetSchool) {
        this.targetSchool = targetSchool;
    }

    public String[] getMajor() {
        return major;
    }

    public void setMajor(String[] major) {
        this.major = major;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getExtraC() {
        return extraC;
    }

    public void setExtraC(String extraC) {
        this.extraC = extraC;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

  
   

    
    
    /**
     * @return the firstName
     */
    

    
    
    
    
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

    
    /**
     * @return the uPassword
     */
    public String getPassword1() {
        return password1;
    }

    /**
     * @param uPassword the uPassword to set
     */
    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    /**
     * @return the cPassword
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param uPassword the uPassword to set
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
     * @return the sQuestion
     */
    
    /**
   
    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
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
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop;
    }
    
    
    
    public String targetSchoolString(){
        String temp = "";
        for(int i=0;i<getTargetSchool().length;i++){
            temp+=getTargetSchool()[i]+", ";
        }
        temp+=".";
        return temp;
    }
    
    public String majorString(){
        String temp = "";
        for(int i=0;i<getMajor().length;i++){
            temp+=getMajor()[i]+", ";
        }
        temp+=".";
        return temp;
    }

   
}
