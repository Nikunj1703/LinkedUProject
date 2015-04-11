/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author nratnap
 */
@ManagedBean
@SessionScoped
public class LogoutBean {


    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "linkedUHome.xhtml";
    }


}