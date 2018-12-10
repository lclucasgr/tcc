package br.edu.utfpr.controller;

import br.edu.utfpr.util.MessageUtil;
import br.edu.utfpr.util.RoleUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController {

    private String email;
    private String senha;

    public LoginController() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String invalidarLogin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.getSession(false).invalidate();
        System.out.println("Matando a sessão com logout");
        return "/index?faces-redirect=true";
        //return "/index?faces-redirect=false";
    }

    public String validarLogin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(email, senha);

            if (request.isUserInRole(RoleUtil.ADMIN)) {
                return "admin/principal?faces-redirect=true";
            } else {
                return "biblio/principal?faces-redirect=true";
            }

        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            showMessage();
            return null;

        }

    }

    public void showMessage() {
        MessageUtil.showMessage("Login e senha invalidos", "", FacesMessage.SEVERITY_ERROR);
    }

}
