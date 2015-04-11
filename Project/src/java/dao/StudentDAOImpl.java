/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controller.LoginBean;
import model.StudentBean;


/**
 *
 * @author admin
 */
public class StudentDAOImpl implements StudentDAO {

    StudentBean studentBean;

   /* @Override
    public ArrayList findAll() {

        ArrayList aProfileCollection = null;
        return aProfileCollection;

    }*/
    
    
    
  /*@Override
    public boolean uploadImageStudentDb(ImageBean aImageBean) {
        
        Connection DBConn = null;
        try{
        DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();  
            
            
            InputStream fin = new FileInputStream((File) studentBean.getImageBean().getUploadedFile());

            PreparedStatement ps = DBConn.prepareStatement(
            "INSERT INTO images VALUES (?, ?)");
            ps.setInt(1, 1477);
             ps.setBinaryStream(2, fin);
             ps.execute();

        // --- reading the columns
       
        stmt.close();
        ps.close();
        DBConn.close();
        }catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return true;
  }
  
    
*/
    
    
 @Override
    public int createStudentDb(StudentBean aStudent) {
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
            String temp = "";
            String tempTarget = "";
            
            String insertString;
            
            String[] major = aStudent.getMajor();
            String[] targetSchool = aStudent.getTargetSchool();
            
            for (int i = 0; i < targetSchool.length; i++) {
                tempTarget += targetSchool[i] + ";";
            }

            for (int i = 0; i < major.length; i++) {
                temp += major[i] + ";";
            }
            
            Statement stmt = DBConn.createStatement();
            
            //String myTemp = aStudent.getFirstName();
            
            
            insertString = "INSERT INTO StudentDb.Students VALUES ('"
                    + aStudent.getFirstName().toUpperCase()
                    + "','" + aStudent.getLastName().toUpperCase()
                    + "','" + aStudent.getEmail()
                    + "','" + aStudent.getPassword1()
                    + "','" + aStudent.getPassword2()
                    + "','" + aStudent.getDob().toUpperCase()
                    + "'," + aStudent.getContactNo()
                    + ",'" + aStudent.getAddress1()
                    + "','" + aStudent.getAddress2()
                    + "','" + aStudent.getCity()
                    + "','" + aStudent.getState().toUpperCase()
                    + "','" + aStudent.getCountry().toUpperCase()
                    + "','" + aStudent.getZip()
                    + "','" + aStudent.getSchoolName().toUpperCase()
                    + "','" + aStudent.getGpa().toUpperCase()
                    + "','" + aStudent.getExam().toUpperCase()
                    + "'," + aStudent.getScore()
                    + ",'" + tempTarget
                    + "','" + temp
                    + "','" + aStudent.getExtraC().toUpperCase()
                    + "','" + aStudent.getAwards()
                    + "','" + aStudent.getSports().toUpperCase()
                    + "','" + aStudent.getPhotoPath()
                    + "','" + aStudent.getSop()
                    + "')";
            
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
    public ArrayList searchAllStudentDb() {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        //String query = "SELECT * FROM Project353.Users WHERE userID LIKE '" + userID + "'";
        String query = "SELECT * FROM StudentDb.Students ";
        StudentBean studentBean;
        ArrayList aStudentCollection = new ArrayList();
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
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
                String firstName;
                String lastName;
                String email;
                String password1;
                String password2;
                String dob;
                String contactNo;
                String address1;
                String address2;
                String city;
                String state;
                String country;
                String zip;
                String schoolName;
                String gpa;
                String exam;
                String score;
                String targetSchool;
                String major;
                String extraC;
                String awards;
                String sports;
                String photoPath;
                String sop;
               
                
                
                firstName = rs.getString("FirstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                password1 = rs.getString("password1");
                password2 = rs.getString("password2");
                dob = rs.getString("dob");
                contactNo = rs.getString("contactNo");
                address1 = rs.getString("address1");
                address2 = rs.getString("address2");
                city = rs.getString("city");
                state = rs.getString("states");
                country = rs.getString("country");
                zip = rs.getString("zip");
                schoolName = rs.getString("schoolName");
                gpa = rs.getString("gpa");
                exam = rs.getString("exam");
                score = rs.getString("score");
                targetSchool = rs.getString("targetSchool");
                major = rs.getString("major");
                extraC = rs.getString("extraC");
                awards = rs.getString("awards");
                sports = rs.getString("sports");
                photoPath = rs.getString("photoPath");
                sop = rs.getString("sop");
                
                String tempInd[] = major.split(";");
                
                String tempTarget[] = targetSchool.split(";");
                
                               
                
                // make a ProfileBean object out of the values
                studentBean = new StudentBean(firstName, lastName, email, password1, password2, dob, contactNo, address1, address2, city, state, country, zip, schoolName, gpa, exam, score, tempTarget, tempInd, extraC, awards, sports, photoPath, sop);
                // add the newly created object to the collection
                aStudentCollection.add(studentBean);
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
        return aStudentCollection;
    
    }
    
    
    @Override
    public ArrayList searchSelectedStudentDb(String firstName,String lastName, int minScore, int maxScore, String[] college) {
        
        ArrayList aStudentCollection = new ArrayList();
        
        String tempCollege="";
        String[] college1=new String[4];
        for(int i=0;i<college.length;i++){
            tempCollege=tempCollege+college[i]+ ";";
            college1[i]=college[i];
        }
        
        if(college.length!=4){
            for(int i=(4-college.length);i<4;i++){
                college1[i]="NA";
            }
        }
        
        if(maxScore>=minScore){
        
        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        //String query = "SELECT * FROM Project353.Users WHERE userID LIKE '" + userID + "'";
        String query = "SELECT * FROM StudentDb.Students ";
        
        if(firstName.isEmpty()){
           if(tempCollege==""){
            if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
            else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
          }
           else{
               if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
            }
            else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
            }
           }
        }
        else if(lastName.isEmpty()){
             if(tempCollege==""){
            if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
            else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName +"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
          }
            else{
                if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
                }
                else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName +"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
            }
           }
        }
        else if(firstName.isEmpty()==false && lastName.isEmpty()==false){
            if(tempCollege==""){
            if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
            query = "SELECT * FROM StudentDb.Students ";
            query += "WHERE (FirstName LIKE UPPER('" + firstName +"%') OR LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
            else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName +"%') OR LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + "))";      
            }
           }
            else{
                if((minScore!=0 || maxScore!=0) || (minScore==0 && maxScore!=0) || (minScore!=0 && maxScore==0)){
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName +"%') OR LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
            }
            else if(minScore==0 && maxScore==0){
                maxScore=1000;
                query = "SELECT * FROM StudentDb.Students ";
                query += "WHERE (FirstName LIKE UPPER('" + firstName +"%') OR LastName LIKE UPPER('" + lastName+"%')) AND (Score >= (" + minScore + ") AND Score <= (" + maxScore + ")) AND (TargetSchool LIKE ('%"+college1[0]+"%') OR TargetSchool LIKE ('%"+college1[1]+"%') OR TargetSchool LIKE ('%"+college1[2]+"%') OR TargetSchool LIKE ('%"+college1[3]+"%'))";      
            }
            }
        }
        
        StudentBean studentBean;
        //ArrayList aStudentCollection = new ArrayList();
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
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
              //  String firstName;
               // String lastName;
                String email;
                String password1;
                String password2;
                String dob;
                String contactNo;
                String address1;
                String address2;
                String city;
                String state;
                String country;
                String zip;
                String schoolName;
                String gpa;
                String exam;
                String score;
                String targetSchool;
                String major;
                String extraC;
                String awards;
                String sports;
                String photoPath;
                String sop;
               
                
                
                firstName = rs.getString("FirstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                password1 = rs.getString("password1");
                password2 = rs.getString("password2");
                dob = rs.getString("dob");
                contactNo = rs.getString("contactNo");
                address1 = rs.getString("address1");
                address2 = rs.getString("address2");
                city = rs.getString("city");
                state = rs.getString("states");
                country = rs.getString("country");
                zip = rs.getString("zip");
                schoolName = rs.getString("schoolName");
                gpa = rs.getString("gpa");
                exam = rs.getString("exam");
                score = rs.getString("score");
                targetSchool = rs.getString("targetSchool");
                major = rs.getString("major");
                extraC = rs.getString("extraC");
                awards = rs.getString("awards");
                sports = rs.getString("sports");
                photoPath = rs.getString("photoPath");
                sop = rs.getString("sop");
                
                String tempInd[] = major.split(";");
                String tempTarget[] = targetSchool.split(";");

                               
                
                // make a ProfileBean object out of the values
                studentBean = new StudentBean(firstName, lastName, email, password1, password2, dob, contactNo, address1, address2, city, state, country, zip, schoolName, gpa, exam, score, tempTarget, tempInd, extraC, awards, sports, photoPath, sop);
                // add the newly created object to the collection
                aStudentCollection.add(studentBean);
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
        return aStudentCollection;
    
    }
        else{
            return aStudentCollection;
        }
    
    }
    
    
    @Override
    public String findPathByEmail(String Email){
        String query = "SELECT PHOTOPATH FROM StudentDb.Students ";
        query += "WHERE Email = '" + Email + "'";
        Connection DBConn = null;
        String photoPath="";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/studentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            
            while (rs.next()) {
                photoPath = rs.getString("photoPath");
            }
               rs.close();
                stmt.close();
            }
            
            
         catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
           } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return photoPath;
    }
    
    
    @Override
    public ArrayList findByEmail(String Email) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        //String query = "SELECT * FROM Project353.Users WHERE userID LIKE '" + userID + "'";
        String query = "SELECT * FROM StudentDb.Students ";
        query += "WHERE Email = '" + Email + "'";
        
       
        //ArrayList aUserCollection = new UserDAOImpl().selectUserFromDB(query);
        ArrayList aStudentCollection = selectStudentFromDB(query);
        return aStudentCollection;

    }
    
    private ArrayList selectStudentFromDB(String query) {
       // ArrayList aUserCollection = new ArrayList();
            ArrayList aStudentBeanCollection = new ArrayList();
            Connection DBConn = null;
            try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/studentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            String firstName,lastName,email,password1,password2,dob,contactNo, photoPath, sop;
            String address1,address2,city,states,country,zip,schoolName,gpa,exam,score,targetSchool,major,extraC,awards,sports;
            StudentBean aStudentBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                firstName = rs.getString("FirstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                password1 = rs.getString("password1");
                password2 = rs.getString("password2");
                dob = rs.getString("dob");
                contactNo = rs.getString("contactNo");
                address1 = rs.getString("address1");
                address2 = rs.getString("address2");
                city = rs.getString("city");
                states = rs.getString("states");
                country = rs.getString("country");
                zip = rs.getString("zip");
                schoolName = rs.getString("schoolName");
                gpa = rs.getString("gpa");
                exam = rs.getString("exam");
                score = rs.getString("score");
                targetSchool = rs.getString("targetSchool");
                major = rs.getString("major");
                extraC = rs.getString("extraC");
                awards = rs.getString("awards");
                sports = rs.getString("sports");
                photoPath = rs.getString("photoPath");
                sop = rs.getString("sop");
                
                String tempInd[] = targetSchool.split(";"); // 
                String tempInt[] = major.split(";"); //

                // make a ProfileBean object out of the values
                studentBean = new StudentBean(firstName, lastName, email, password1, password2, dob, contactNo, address1, address2, city, states, country, zip, schoolName, gpa, exam, score, tempInd, tempInt, extraC, awards, sports, photoPath, sop);
                // add the newly created object to the collection
                aStudentBeanCollection.add(studentBean);
            }
            
                rs.close();
                stmt.close();
            }
            
            
         catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
         return aStudentBeanCollection;
    }
    
    @Override
    public ArrayList findByUserId2(String emailID, String Password1) {

        // if interested in matching wild cards, use: LIKE and '%" + deptNo + "%'";
        //String query = "SELECT * FROM Project353.Users WHERE userID LIKE '" + userID + "'";
        String query = "SELECT * FROM StudentDb.Students ";
        query += "WHERE Email = '" + emailID + "' and Password1 = '"+ Password1 +"'";
        
       
        //ArrayList aUserCollection = new UserDAOImpl().selectUserFromDB(query);
        ArrayList aUserCollection = selectFromLoginDB(query);  
        
        
        return aUserCollection;

    }
    
//    @Override
//    public int updateStudentDb(StudentBean aStudent) {
//
//        ArrayList aProfileBeanCollection = new ArrayList();
//        Connection DBConn = null;
//        try {
//            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
//            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
//            String myDB = "jdbc:derby://localhost:1527/profile";
//            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
//            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
//
//            // With the connection made, create a statement to talk to the DB server.
//            // Create a SQL statement to query, retrieve the rows one by one (by going to the
//            // columns), and formulate the result string to send back to the client.
//            Statement stmt = DBConn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            String name, gender, pos, industries, interests;
//            ProfileBean aProfileBean;
//            while (rs.next()) {
//                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
//                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
//                FirstName = rs.getString("ClientName");
//                gender = rs.getString("Gender");
//                pos = rs.getString("Position");
//                industries = rs.getString("Industries");
//                interests = rs.getString("Interests");
//
//                String tempInd[] = industries.split(";"); // <-- what's wrong with this?
//                String tempInt[] = interests.split(";"); // <-- what's wrong with this?
//
//                // make a ProfileBean object out of the values
//                aProfileBean = new ProfileBean(name, gender, pos, tempInd, tempInt);
//                // add the newly created object to the collection
//                aProfileBeanCollection.add(aProfileBean);
//            }
//            rs.close();
//            stmt.close();
//        } catch (Exception e) {
//            System.err.println("ERROR: Problems with SQL select");
//            e.printStackTrace();
//        }
//        try {
//            DBConn.close();
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return aProfileBeanCollection;
//        
//        
//            // SQL UPDATE Syntax [http://www.w3schools.com]:
//            // UPDATE table_name
//            // SET column1=value, column2=value2,...
//            // WHERE some_column=some_value
//            // Note: Notice the WHERE clause in the UPDATE syntax. The WHERE clause specifies which record or records that should be updated. If you omit the WHERE clause, all records will be updated!
//            updateString = "UPDATE Project353.Users SET "
//                    + "FirstName = '" + aStudent.getFirstName().toUpperCase() + "', "
//                    + "LastName = '" + aStudent.getLastName().toUpperCase() + "', "
//                    + "Email = '" + aStudent.getEmail() + "', "
//                    + "Password1 = '" + aStudent.getPassword1() + "', "
//                    + "Password2 = '" + aStudent.getPassword2() + "', "
//                    + "Dob = '" + aStudent.getDob() + "', "
//                    + "ContactNo = '" + aStudent.getContactNo() + "'"
//                    + "Address1 = '" + aStudent.getAddress1() + "', "
//                    + "Address2 = '" + aStudent.getAddress2() + "'"
//                    + "City = '" + aStudent.getCity() + "', "
//                    + "States = '" + aStudent.getState() + "', "
//                    + "Country = '" + aStudent.getCountry() + "', "
//                    + "Zip = '" + aStudent.getZip() + "', "
//                    + "SchoolName = '" + aStudent.getSchoolName() + "', "
//                    + "Gpa = '" + aStudent.getGpa() + "', "
//                    + "Exam = '" + aStudent.getExam() + "'"
//                    + "Score = '" + aStudent.getScore() + "', "
//                    + "TargetSchool = '" + aStudent.getTargetSchool() + "', "
//                    + "Major = '" + aStudent.getMajor() + "', "
//                    + "ExtraC = '" + aStudent.getExtraC() + "', "
//                    + "Awards = '" + aStudent.getAwards() + "', "
//                    + "Sports = '" + aStudent.getSports() + "'"
//                    + "WHERE Email = '" + aStudent.getEmail() + "'";
//           
//
//    }
//
//    
    
    
    
    private ArrayList selectFromLoginDB(String query) {
        ArrayList aUserCollection = new ArrayList();
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
            String emailID, password;
            LoginBean loginBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
                emailID = rs.getString("Email");
                password = rs.getString("Password1");
                
                // make a ProfileBean object out of the values
                loginBean = new LoginBean(emailID, password);
                // add the newly created object to the collection
                aUserCollection.add(loginBean);
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
        return aUserCollection;
    }
    
    //New Addition 18 Nov
    @Override
    public StudentBean findByName(String fname, String lname) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";        

        ArrayList aStudentCollection = new ArrayList();
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
          
            String query = "SELECT * FROM StudentDB.STUDENTS ";
            query += "WHERE FIRSTNAME = '" + fname + "'" + "AND LASTNAME = '" + lname + "'";
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName,college_temp;
           
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
               String email;
                String password1;
                String password2;
                String dob;
                String contactNo;
                String address1;
                String address2;
                String city;
                String state;
                String country;
                String zip;
                String schoolName;
                String gpa;
                String exam;
                String score;
                String targetSchool;
                String major;
                String extraC;
                String awards;
                String sports;
                String photoPath;
                String sop;
                
                
                firstName = rs.getString("FirstName");
                lastName = rs.getString("lastName");
                email = rs.getString("email");
                password1 = rs.getString("password1");
                password2 = rs.getString("password2");
                dob = rs.getString("dob");
                contactNo = rs.getString("contactNo");
                address1 = rs.getString("address1");
                address2 = rs.getString("address2");
                city = rs.getString("city");
                state = rs.getString("states");
                country = rs.getString("country");
                zip = rs.getString("zip");
                schoolName = rs.getString("schoolName");
                gpa = rs.getString("gpa");
                exam = rs.getString("exam");
                score = rs.getString("score");
                targetSchool = rs.getString("targetSchool");
                major = rs.getString("major");
                extraC = rs.getString("extraC");
                awards = rs.getString("awards");
                sports = rs.getString("sports");
                photoPath = rs.getString("photoPath");
                sop = rs.getString("sop");
                
                String tempInd[] = major.split(";");
                String tempTarget[] = targetSchool.split(";");

                               
                
                // make a ProfileBean object out of the values
                studentBean = new StudentBean(firstName, lastName, email, password1, password2, dob, contactNo, address1, address2, city, state, country, zip, schoolName, gpa, exam, score, tempTarget, tempInd, extraC, awards, sports, photoPath, sop);
                // add the newly created object to the collection
                aStudentCollection.add(studentBean);
                               
                
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
        StudentBean studentBean = (StudentBean) aStudentCollection.get(0);
        return studentBean; 
    }
    
    
    public String findPassword(String email){
         Connection DBConn = null;
         String password1="";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDB";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();  
            String query = "SELECT Password1 FROM StudentDB.STUDENTS ";
            query += "WHERE Email = '" + email + "'";
            ResultSet rs = stmt.executeQuery(query);
            
             while (rs.next()) {
               password1 = rs.getString("password1");
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
        
        return password1; 
             
    }
    
    
    public int updateStudentInDb(String tempEmail, String contactNo,String address1, String address2, String city, String state, String country, String zip, String schoolName, String gpa, String exam, String score, String major, String extraC, String awards, String sports){
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        int rowCount=0;
        try{
            String updateString;
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
           updateString = "UPDATE StudentDb.STUDENTS SET "
                    + "ContactNo = " + contactNo+ ", "
                    + "Address1 = '" + address1+ "', "
                    + "Address2 = '" + address2+ "', "
                    + "city = '" + city+ "', "
                    + "states = '" + state+ "', "
                    + "country = '" + country+ "', "
                    + "zip = '" + zip+ "', "
                    + "schoolName = '" + schoolName+ "', "
                    + "gpa = '" + gpa+ "', "
                    + "exam = '" + exam+ "', "
                    + "score = " + score+ ", "
                    + "major = '" + major+ "', "
                    + "extraC = '" + extraC+ "', "
                    + "awards = '" + awards+ "', "
                    + "sports = '" + sports+ "'"+" WHERE EMAIL = '"+tempEmail+"'";
                    
            rowCount = stmt.executeUpdate(updateString);
            System.out.println("Update String: "+updateString);
            DBConn.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
        return rowCount; 
    }
    
    
    
    
    
    
    
}
