/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.UniversitySignUpBean;

/**
 *
 * @author DELL
 */
public class UniversitySignUpDAOImpl implements UniversitySignUpDAO {

    @Override
    public int createProfile(UniversitySignUpBean aProfile) {
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
            String[] selectedCourses = aProfile.getSelectedCourses();
            for (int i = 0; i < selectedCourses.length; i++) {
                temp += selectedCourses[i] + ";";
            }
            String insertString;
            Statement stmt = DBConn.createStatement();
            insertString = "INSERT INTO StudentDb.University VALUES ('"
                    + aProfile.getName()
                    + "','" + aProfile.getAddr1()
                    + "','" + aProfile.getAddr2()
                    + "','" + aProfile.getCity()
                    + "','" + aProfile.getState()
                    + "','" + aProfile.getCountry()
                    + "','" + aProfile.getZipcode()
                    + "','" + aProfile.getEmail()                                      
                    + "','" + aProfile.getPassword()
                     + "','" + aProfile.getContact()
                     + "','" + aProfile.getAboutUniversity()
                    + "','" + temp
                    + "'," + aProfile.getFees() 
                    + "," + aProfile.getAccptRate()
                    + "," + aProfile.getScRate()
                    + "," + aProfile.getACTScore()
                    + "," + aProfile.getSATScore()
                    + ")";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // if insert is successful, rowCount will be set to 1 (1 row inse   rted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public ArrayList findAll() {

        String query = "SELECT * FROM StudentDb.University";
        ArrayList aProfileCollection = selectProfilesFromDB(query);
        return aProfileCollection;

    }

    @SuppressWarnings("empty-statement")
    private ArrayList selectProfilesFromDB(String query) {
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/Project353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            
            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String name, addr1, addr2, city, state, country, email, contact,password,zipcode,aboutUniversity,selectedCourses;
            int fees, ACTscore, SATscore;
            float AccptRate;
            UniversitySignUpBean aProfileBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
                name = rs.getString("name");
                addr1 = rs.getString("addr1");
                addr2 = rs.getString("addr2");
                city = rs.getString("City");
                state = rs.getString("State");
                country = rs.getString("country");
                zipcode = rs.getString("zipcode");
                email = rs.getString("email");
                contact = rs.getString("ContactNo");
                password = rs.getString("password");
                aboutUniversity = rs.getString("aboutUniversity");
                selectedCourses = rs.getString("selectedCourses");
                fees = rs.getInt("fees");
                AccptRate = rs.getFloat("AccptRate");
                ACTscore = rs.getInt("ACTscore");
                SATscore = rs.getInt("SATscore");
                
                
                String tempInd[] = {selectedCourses}; 
               
                // make a ProfileBean object out of the values
                aProfileBean = new UniversitySignUpBean(name, addr1, addr2, city, state, country, zipcode, email, 
                                 contact, password, aboutUniversity, tempInd ,fees,AccptRate,
                                 ACTscore,SATscore);
                // add the newly created object to the collection
                aProfileBeanCollection.add(aProfileBean);
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
        return aProfileBeanCollection;
    }

//    @Override
//    public ArrayList findByName(String email) {
//        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
//        String query = "SELECT * FROM StudentDb.University ";
//        query += "WHERE Email = '" + email + "'";
//
//        ArrayList aProfileCollection = selectProfilesFromDB(query);
//        return aProfileCollection;
//    }

   
}

