/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ImageBean;
import model.StudentBean;

/**
 *
 * @author admin
 */
public interface StudentDAO {
    
    public int createStudentDb(StudentBean aStudent);
    //public ArrayList findAll();
    public ArrayList findByEmail(String UserID); 
    public ArrayList findByUserId2(String UserID, String Password1);
    public ArrayList searchAllStudentDb();
    public ArrayList searchSelectedStudentDb(String firstName, String lastName, int minScore, int maxScore, String[] college);
    //public boolean uploadImageStudentDb(ImageBean aImageBean);
    //public int updateStudentDb(StudentBean signUpBean);
    public StudentBean findByName(String aName,String bName); 
    public String findPathByEmail(String Email);
    public String findPassword(String Email);
    //public ArrayList findByEmailSearchStudent(String Email);
    
}
