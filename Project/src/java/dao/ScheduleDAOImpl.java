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
import controller.ScheduleController;
import model.ScheduleBean;


/**
 *
 * @author admin
 */
public class ScheduleDAOImpl implements ScheduleDAO {

    ScheduleBean scheduleBean;
    
    
 @Override
    public int createSchedule(ScheduleBean aSchedule) {
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
            
            String insertString;
           
            Statement stmt = DBConn.createStatement();
            
            //String myTemp = aStudent.getFirstName();
            
            
            insertString = "INSERT INTO StudentDb.Appointments VALUES ('"
                    + aSchedule.getEmail()
                    + "','" + aSchedule.getUniversity()
                    + "','" + aSchedule.getDate()
                    + "','" + aSchedule.getSlot()
                    + "')";

            rowCount = stmt.executeUpdate(insertString);
            System.out.println("insert string =" + insertString);
            
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

  
    
  
    @Override
    public ArrayList checkAvailabilityInDB(String date, String slot) {

        //String query = "SELECT * FROM Project353.Users WHERE userID LIKE '" + userID + "'";
        String query = "SELECT * FROM StudentDb.Appointments WHERE Date1 = '" + date + "' AND Slot = '" + slot + "'";
        ScheduleBean scheduleBean;
        ArrayList aScheduleCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/StudentDb";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();  
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
               
                String university;
                String email;
                
                email = rs.getString("Email");
                university = rs.getString("University");
                date = rs.getString("Date1");
                slot = rs.getString("Slot");
                
                scheduleBean = new ScheduleBean(email, university, date, slot);
                // add the newly created object to the collection
                aScheduleCollection.add(scheduleBean);
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
        return aScheduleCollection;
    
    }
}

    
    
