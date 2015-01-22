/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.EMS.Controller;

import com.EMS.constants.Constants;
//import com.EMS.entities.LoginFacade;
import java.io.Serializable;
import java.sql.SQLException;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
//import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
//import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shravani
 */

@RequestScoped
@Named
public class LoginBean implements Serializable {
    
     private static final long serialVersionUID = 1L;
     private String username, password;
     //@EJB private LoginFacade loginFacade;
     String msg = "SUCCESS";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
     public String login() throws SQLException, ServletException{
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        
         if(this.password.equalsIgnoreCase(Constants.DEFAULT_PASSWORD))
         {
            return "/register?faces-redirect=true";
         }
         try{
             req.login(username,password);
         }catch(ServletException e){
              fc.addMessage(null, new FacesMessage(e.getMessage()));
              return "login";
            }    
         
         //if(loginFacade.loginCheck(username, password).equals(msg)){
        if (req.isUserInRole("student")) {
                 return "/faces/Student/*";
            } else if (req.isUserInRole("lecturer")) {
                 return "/faces/Lecturer/*";
            } else if (req.isUserInRole("admin")) {
                 return "/faces/Admin/*";
            } else {
                 return "login";
             }
         }
         
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
            return null;
        }
        return "/login";
    }

}
