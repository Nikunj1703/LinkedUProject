/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.SearchUniversityBean;
import model.UniversityBean;

/**
 *
 * @author admin
 */
public interface UniversityDAO {
    
    //public int createStudentDb(StudentBean aStudent);
    //public ArrayList findAll();
    public ArrayList searchAllUniversity(UniversityBean universityBean); 
//    public ArrayList findByUserId2(String UserID, String Password1);
//    public ArrayList searchAllStudentDb();
//    public ArrayList searchSelectedStudentDb(String firstName, String lastName, int minScore, int maxScore, String[] college);
    //public boolean uploadImageStudentDb(ImageBean aImageBean);
    //public int updateStudentDb(StudentBean signUpBean);
    public UniversityBean findByUniversityName(String university);
    public ArrayList findByUniversityId(String email, String password);
    public SearchUniversityBean findByName(String university);
    
    
}
