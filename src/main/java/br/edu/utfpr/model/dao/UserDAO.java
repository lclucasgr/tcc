package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.User;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAO extends AbstractDAO<Long, User> {
    

    public User findByEmail(String email)
    {
       User librarier; 
       
       try
       {
           librarier = createNamedQuery("Librarier.findByEmail").setParameter("email", email).getSingleResult();
           return librarier;
       }catch(NoResultException e)
       {
           return null;
       }
    }
    
    public User validarLogin(String email, String senha)
    {
        User librarier;     
        
        String sql = "SELECT l FROM Librarier l WHERE l.email = :email AND l.senha = :senha";
        Query query = entityManager.createQuery(sql);
        
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        librarier = (User) query.getSingleResult();
        return librarier;
    }
    
   
    
}