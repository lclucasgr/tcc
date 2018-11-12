/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.model.LivroLiterario;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
public class LivroLiterarioDAO extends AbstractDAO<Long, LivroLiterario> {

    public List<LivroLiterario> findAllOrderedByTitle() {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();
        String queryString = "SELECT l FROM LivroLiterario l ORDER BY l.titulo";

        Query query = entityManager.createQuery(queryString);

        List<LivroLiterario> queryResult = query.getResultList();

        return queryResult;
    }

    public List<LivroLiterario> findAllOrderedByTrue() {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();
        String queryString = "SELECT l FROM LivroLiterario l WHERE l.disponibilidade = 'T' ORDER BY l.titulo";

        Query query = entityManager.createQuery(queryString);

        List<LivroLiterario> queryResult = query.getResultList();

        return queryResult;
    }

    public List<LivroLiterario> findAllOrderedByFalse() {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();
        String queryString = "SELECT l FROM LivroLiterario l WHERE l.disponibilidade = 'F' ORDER BY l.titulo";

        Query query = entityManager.createQuery(queryString);

        List<LivroLiterario> queryResult = query.getResultList();

        return queryResult;
    }

}
