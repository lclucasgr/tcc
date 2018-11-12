/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
//@NamedQueries({
//@NamedQuery(name = "Login.control", query = "SELECT l FROM librariers l WHERE l.email = :email and l.senha = :senha"),    
//@NamedQuery(name = "Librarier.findByEmail", query = "SELECT l FROM librariers l WHERE l.email = :email")
//    
//})
public class User implements Serializable {
        
    @Id     
    private String email;
    
    private String senha;
    
    public User() {
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
    
    
    
}