/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import controller.Controller;
import controller.ImageUploadBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controller.LoginBean;
import controller.UpdateController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.SearchUniversityBean;
import model.UniversityBean;
import model.UniversitySignUpBean;



/**
 *
 * @author nratnap
 */
@ManagedBean
@SessionScoped
public class UniversityDAOImpl implements UniversityDAO{
    
   // UniversitySignUpBean universitySignUpBean;
    
    
    @ManagedProperty(value="#{updateController}")
    private UpdateController updateController;

    public UpdateController getUpdateController() {
        return updateController;
    }

    public void setUpdateController(UpdateController updateController) {
        this.updateController = updateController;
    }
   
    
    
    
    
    
    @Override
    public ArrayList searchAllUniversity(UniversityBean universityBean){
        
        
        int flag = 0;
        String query = "SELECT * FROM StudentDb.University ";

        if (!universityBean.getState().equals("All")) {
            query += "WHERE STATE = '" + universityBean.getState() + "'";
            flag = 1;
        }
        if (flag == 1) {
            query = query + " AND ";
        } else {
            query = query + " WHERE ";
        }

        query += universityBean.getExam() + " > " + universityBean.getMinScore();

        if (universityBean.getAccRate()!=null) {
            if (universityBean.getAccRate().equals("LT30")) {
                query += " AND AccptRate < 30";
            } else if (universityBean.getAccRate().equals("LT50")) {
                query += " AND AccptRate > 30 AND AccptRate < 50";
            } else if (universityBean.getAccRate().equals("LT80")) {
                query += " AND AccptRate > 50 AND AccptRate < 80";
            } else {
                query += " AND AccptRate > 80";
            }
        }

        if (universityBean.getSchRate()!=null) {
            if (universityBean.getSchRate().equals("LT30")) {
                query += " AND AccptRate < 30";
            } else if (universityBean.getSchRate().equals("LT50")) {
                query += " AND AccptRate > 30 AND AccptRate < 50";
            } else if (universityBean.getSchRate().equals("LT80")) {
                query += " AND AccptRate > 50 AND AccptRate < 80";
            } else {
                query += " AND AccptRate > 80";
            }
        }

        SearchUniversityBean searchUniversityBean;
        ArrayList searchUniversityList = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String university;
                String state;
                String exam;
                int minScore;
                int maxScore;
                String accRate;
                String schRate;

                String Name, Addr1, Addr2, City, State, Country, Zipcode, Email, Username, Password, contact,
                        aboutUniversity, selectedCourses;
                int fees, AccptRate, ScshipRate;
                int ACTscore, SATscore;

                Name = rs.getString("Name");
                Addr1 = rs.getString("Addr1");
                Addr2 = rs.getString("Addr2");
                City = rs.getString("City");
                State = rs.getString("State");
                Country = rs.getString("Country");
                Zipcode = rs.getString("Zipcode");
                Email = rs.getString("Email");
               // Username = rs.getString("Username");
                Password = rs.getString("Password");
                contact = rs.getString("contact");
                aboutUniversity = rs.getString("aboutUniversity");
                selectedCourses = rs.getString("selectedCourses");

                fees = rs.getInt("fees");
                ScshipRate = rs.getInt("ScshipRate");
                AccptRate = rs.getInt("AccptRate");
                ACTscore = rs.getInt("ACTscore");
                SATscore = rs.getInt("SATscore");

                String tempClg[] = selectedCourses.split(";");

                searchUniversityBean = new SearchUniversityBean(
                        Name, Addr1, Addr2, City, State, Country, Zipcode, Email, Password, contact,
                        aboutUniversity, selectedCourses, fees, AccptRate, ScshipRate, ACTscore, SATscore);
                searchUniversityList.add(searchUniversityBean);

            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return searchUniversityList;
           }
        
    @Override
    public UniversityBean findByUniversityName(String university) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";        

        ArrayList aUniversityCollection = new ArrayList();
        UniversityBean universityBean;
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDB";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();  
          
            String query = "SELECT * FROM StudentDB.University ";
            query += "WHERE UNIVERSITY = '" + university + "'";
            ResultSet rs = stmt.executeQuery(query);
           
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
                String university1;
                String state;
                String exam;
                int minScore;
                int maxScore;
                String accRate;
                String schRate;
               
                
                
                university1 = rs.getString("university");
                state = rs.getString("state");
                exam = rs.getString("exam");
                minScore = rs.getInt("minScore");
                maxScore = rs.getInt("maxScore");
                accRate = rs.getString("accRate");
                schRate = rs.getString("schRate");
                
                
                

                               
                
                // make a ProfileBean object out of the values
                universityBean = new UniversityBean(null, state, exam, minScore, maxScore, accRate, schRate);
                // add the newly created object to the collection
                aUniversityCollection.add(universityBean);
                               
                
                // make a ProfileBean object out of the values
               //searchStudentBean = new SearchStudentBean(firstName,  lastName, minScore, maxScore, college);
                // add the newly created object to the collection
                //aUserCollection.add(loginBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        universityBean = (UniversityBean) aUniversityCollection.get(0);
        return universityBean; 
    }
    
    public int insertStudentInUniversityDb(String universityName,String email1, String email2, String email3, String reEmail, String firstName,String lastName, String dob, String exam, String gpa, String[] major, String extraC, String score, String country, String filePath){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String temp="";
            String tempTarget = "";
            
            String insertString;
            
            
            Statement stmt = DBConn.createStatement();
            
            UniversityBean universityBean;
            LoginBean loginBean;
            
          //  String tempClg[] = university.split(";");
            for(int i=0;i<major.length;i++){
                temp=temp+major[i];
            }
            
            String newTemp=universityName;
            
            insertString = "INSERT INTO StudentDb.UniversityStudent VALUES ('"
                    + universityName
                    + "','" + email1
                    + "','" + email2
                    + "','" + email3
                    + "','" + reEmail
                    + "','" + firstName
                    + "','" + lastName
                    + "','" + dob
                    + "','" + exam
                    + "','" + gpa
                    + "','" + temp
                    + "','" + extraC
                    + "','" + score
                    + "','" + country
                    + "','" + filePath
                    +"')";
            
           /* insertString2 = "INSERT INTO Project353.LoginInfo VALUES ('"
                    + aUser.getUserID()
                    + "','" + aUser.getPassword1()
                    + "')";
            */

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            
            //rowCount=stmt.executeUpdate(insertString2);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }
    
    @Override
    public ArrayList findByUniversityId(String email, String password){
        ArrayList aUniversityCollection = new ArrayList();
        UniversitySignUpBean universitySignUpBean;
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDB";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();  
          
            String query = "SELECT * FROM StudentDB.University ";
            query += "WHERE Email = '" + email + "' AND Password = '" +password + "'";
            ResultSet rs = stmt.executeQuery(query);
           
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
                String name;
                String city;
                String state;
                String country;
                String contact;
                String aboutUniversity;
                String selectedCourses;
                int fees;
                float AccptRate;
                float ScshipRate;
                int  ACTscore;
                int  SATscore;
               
                
                
                
                
                name = rs.getString("name");
                city = rs.getString("city");
                state = rs.getString("state");
                country = rs.getString("country");
                contact = rs.getString("contact");
                aboutUniversity = rs.getString("aboutUniversity");
                selectedCourses = rs.getString("selectedCourses");
                fees = rs.getInt("fees");
                AccptRate = rs.getFloat("AccptRate");
                ScshipRate = rs.getFloat("ScshipRate");
                ACTscore = rs.getInt("ACTscore");
                SATscore = rs.getInt("SATscore");
                
                
                String [] temp = selectedCourses.split(";");
                               
                
                 //make a ProfileBean object out of the values
                universitySignUpBean = new UniversitySignUpBean(name, city, state, country, contact, aboutUniversity, temp, fees, AccptRate, ScshipRate, ACTscore, SATscore);
               
                // add the newly created object to the collection
                aUniversityCollection.add(universitySignUpBean);
                 
                
                // make a ProfileBean object out of the values
              
                // add the newly created object to the collection
                
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return aUniversityCollection; 
    }
    
    
    public int updateUniversityInDb(String tempEmail, String newAbout, int newFees, float newARate, float newSRate, int newACT, int newSAT){
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        int rowCount=0;
        
        try{
            String updateString;
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            String[] tempCourses= null;
          //  String test = updateController.tempAboutValue();
            
            
//            for(int i=0;i<universitySignUpBean.getCourses().toArray().length;i++){
//                tempCourses[i]+=universitySignUpBean.getCourses().toArray()[i];
//            }
//            
            updateString = "UPDATE StudentDb.University SET "
                    + "AboutUniversity = '" + newAbout+ "', "
                    + "Fees = " + newFees+ ", "
                    + "AccptRate = " +newARate+ ", "
                    + "ScshipRate = " + newSRate+ ", "
                    + "ActScore = " + newACT+ ", "
                    + "SatScore = " + newSAT+ ""+"WHERE EMAIL = '"+tempEmail+"'";
                    
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("Update String: "+updateString);
            DBConn.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
        return rowCount;

    }
    
    
    //Piyush 29 Nov
    
    @Override
    public SearchUniversityBean findByName(String university){
         ArrayList aUniversityCollection = new ArrayList();
        SearchUniversityBean  searchUniversityBean = new SearchUniversityBean();  
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDB";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();  
          
            String query = "SELECT * FROM StudentDB.University ";
            query += "WHERE NAME = '" + university + "'";
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName,college_temp;
           
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0                              
                String state;
                String exam;
                int minScore;
                int maxScore;
                String accRate;
                String schRate;

                String Name, Addr1, Addr2, City, State, Country, Zipcode, Email, Password, contact,
                        aboutUniversity, selectedCourses;
                int fees, AccptRate, ScshipRate;
                int ACTscore, SATscore;

                Name = rs.getString("Name");
                Addr1 = rs.getString("Addr1");
                Addr2 = rs.getString("Addr2");
                City = rs.getString("City");
                State = rs.getString("State");
                Country = rs.getString("Country");
                Zipcode = rs.getString("Zipcode");
                Email = rs.getString("Email");
                Password = rs.getString("Password");
                contact = rs.getString("contact");
                aboutUniversity = rs.getString("aboutUniversity");
                selectedCourses = rs.getString("selectedCourses");
                
                fees = rs.getInt("fees");
                ScshipRate = rs.getInt("ScshipRate");
                AccptRate = rs.getInt("AccptRate");
                ACTscore = rs.getInt("ACTscore");
                SATscore = rs.getInt("SATscore");
                                                                                          
                String tempClg[] = selectedCourses.split(";");

                searchUniversityBean = new SearchUniversityBean(
                        Name, Addr1, Addr2, City, State, Country, Zipcode, Email, Password, contact,
                        aboutUniversity, selectedCourses,fees, AccptRate, ScshipRate,ACTscore, SATscore);
                aUniversityCollection.add(searchUniversityBean);
                
                // make a ProfileBean object out of the values
               //searchStudentBean = new SearchStudentBean(firstName,  lastName, minScore, maxScore, college);
                // add the newly created object to the collection
                //aUserCollection.add(loginBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        searchUniversityBean = (SearchUniversityBean) aUniversityCollection.get(0);
        return searchUniversityBean; 
       
    }
    
    
}
