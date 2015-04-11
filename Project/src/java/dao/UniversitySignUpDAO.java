/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.UniversitySignUpBean;

/**
 *
 * @author DELL
 */
public interface UniversitySignUpDAO {

    public int createProfile( UniversitySignUpBean aProfile);
    public ArrayList findAll();
   // public ArrayList findByName(String aName); // either get one back or several if multiple same name allowed  
    
}


