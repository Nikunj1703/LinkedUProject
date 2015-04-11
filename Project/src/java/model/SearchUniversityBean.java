/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LENOVO
 */
public class SearchUniversityBean {

    private String name;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String email;
    private String password;
    private String contact;
    private String aboutUniversity;
    private String selectedCourses;
    private int fees;
    private int accptRate;
    private int scshipRate;
    private int aCTscore;
    private int sATscore;

    public SearchUniversityBean() {

    }

    public SearchUniversityBean(String Name, String Addr1, String Addr2, String City, String State, String Country,
            String Zipcode, String Email, String Password, String contact,
            String aboutUniversity, String selectedCourses, int fees, int AccptRate, int ScshipRate,
            int ACTscore, int SATscore) {

        this.name = Name;
        this.addr1 = Addr1;
        this.addr2 = Addr2;
        this.city = City;
        this.state = State;
        this.country = Country;
        this.zipcode = Zipcode;
        this.email = Email;
        this.password = Password;
        this.contact = contact;
        this.aboutUniversity = aboutUniversity;
        this.selectedCourses = selectedCourses;
        this.fees = fees;
        this.accptRate = AccptRate;
        this.scshipRate = ScshipRate;
        this.aCTscore = ACTscore;
        this.sATscore = SATscore;
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
    public String getSelectedCourses() {
        return selectedCourses;
    }

    /**
     * @param selectedCourses the selectedCourses to set
     */
    public void setSelectedCourses(String selectedCourses) {
        this.selectedCourses = selectedCourses;
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
     * @return the accptRate
     */
    public int getAccptRate() {
        return accptRate;
    }

    /**
     * @param accptRate the accptRate to set
     */
    public void setAccptRate(int accptRate) {
        this.accptRate = accptRate;
    }

    /**
     * @return the scshipRate
     */
    public int getScshipRate() {
        return scshipRate;
    }

    /**
     * @param scshipRate the scshipRate to set
     */
    public void setScshipRate(int scshipRate) {
        this.scshipRate = scshipRate;
    }

    /**
     * @return the aCTscore
     */
    public int getaCTscore() {
        return aCTscore;
    }

    /**
     * @param aCTscore the aCTscore to set
     */
    public void setaCTscore(int aCTscore) {
        this.aCTscore = aCTscore;
    }

    /**
     * @return the sATscore
     */
    public int getsATscore() {
        return sATscore;
    }

    /**
     * @param sATscore the sATscore to set
     */
    public void setsATscore(int sATscore) {
        this.sATscore = sATscore;
    }

    

    
}
