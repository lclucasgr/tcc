/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.controller;

import br.edu.utfpr.model.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lcluc
 */

@SessionScoped
@ManagedBean(name = "librarierController")
public class LibrarierController {
    
    private User librarier;
    //public static final String INJECTION_NAME = "#{librarierController}";

    public User getLibrarier() {
        return librarier;
    }

    public void setLibrarier(User librarier) {
        this.librarier = librarier;
    }
    
    public String logout()
    {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "index";
    }
    
}