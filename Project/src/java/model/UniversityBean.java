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
public class UniversityBean {
    String universityName;
    String recomm1;
    String recomm2;
    String recomm3;
    String reEmail;
    String[] universityNameArray;
    String state;
    String exam;
    int minScore;
    int maxScore;
    String accRate;
    String schRate;
    

    public UniversityBean() {
    }

    public UniversityBean(String[] universityNameArray, String state, String exam, int minScore, int maxScore, String accRate, String schRate) {
        this.universityNameArray = universityNameArray;
        this.state = state;
        this.exam = exam;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.accRate = accRate;
        this.schRate = schRate;
        
    }

    public String[] getUniversityNameArray() {
        return universityNameArray;
    }

    public void setUniversityNameArray(String[] universityNameArray) {
        this.universityNameArray = universityNameArray;
    }
   

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
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

    public String getAccRate() {
        return accRate;
    }

    public void setAccRate(String accRate) {
        this.accRate = accRate;
    }

    public String getSchRate() {
        return schRate;
    }

    public void setSchRate(String schRate) {
        this.schRate = schRate;
    }

 

    public String getRecomm1() {
        return recomm1;
    }

    public String getReEmail() {
        return reEmail;
    }

    public void setReEmail(String reEmail) {
        this.reEmail = reEmail;
    }

    public void setRecomm1(String recomm1) {
        this.recomm1 = recomm1;
    }

    public String getRecomm2() {
        return recomm2;
    }

    public void setRecomm2(String recomm2) {
        this.recomm2 = recomm2;
    }

    public String getRecomm3() {
        return recomm3;
    }

    public void setRecomm3(String recomm3) {
        this.recomm3 = recomm3;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
    
    
    
}
