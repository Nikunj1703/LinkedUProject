/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.ScheduleBean;

/**
 *
 * @author admin
 */
public interface ScheduleDAO {
    
    public int createSchedule(ScheduleBean aSchedule);
    public ArrayList checkAvailabilityInDB(String date,String slot);
    
}
