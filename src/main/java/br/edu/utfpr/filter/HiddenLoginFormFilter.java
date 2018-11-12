/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.filter;

import br.edu.utfpr.util.RoleUtil;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lcluc
 */
//@WebFilter(filterName = "HiddenLoginFormFilter", urlPatterns = {"/index.jsf"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class HiddenLoginFormFilter implements Filter {
    
   
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {  
        
        boolean isAuthenticated = false;
        
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        //está logado se for diferente de nulo
        if(req.getUserPrincipal() != null){
            isAuthenticated = true;
            String path = "biblio/principal?faces-redirect=true";
            if(req.isUserInRole(RoleUtil.ADMIN)){
                path = "admin/principal?faces-redirect=true";       
            }
            
            res.sendRedirect(path);
        }
        else{
            //permite o acesso ao formulario de login
            chain.doFilter(req, response); 
        }
    }  

    @Override
    public void init(FilterConfig fc) throws ServletException {
       
    }

    @Override
    public void destroy() {
        
    }
    
}
