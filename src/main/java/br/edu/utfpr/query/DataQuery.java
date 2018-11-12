package br.edu.utfpr.query;

import br.edu.utfpr.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DataQuery {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    
    public DataQuery()
    {
        emf = Persistence.createEntityManagerFactory("tcc_jpa");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public boolean logar(String email, String senha)
    {
        try{
            User l = em.createQuery("Login.control", User.class).setParameter("email", email).setParameter("senha", senha).getSingleResult();
            if(l != null)
            {
                return true;
            }
            return false;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public User validarLogin(String email, String senha)
    {
        User librarier;
        EntityManager em = emf.createEntityManager();
        
        String sql = "SELECT l FROM librariers WHERE l.email = :email AND l.senha = :senha";
        Query query = em.createQuery(sql);
        
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        librarier = (User) query.getSingleResult();
        return librarier;
    }
}
