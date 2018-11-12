/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.LivroDidatico;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
public class LivroDidaticoDAO extends AbstractDAO<Long, LivroDidatico> {

    public List<LivroDidatico> findAllOrderedByTrue() {
        this.entityManager = JPAUtil.getEntityManager();

        String queryString = "SELECT l FROM LivroDidatico l WHERE l.disponibilidade = 'T' ORDER BY l.titulo";

        Query query = entityManager.createQuery(queryString);

        List<LivroDidatico> queryResult = query.getResultList();

        return queryResult;
    }

    public List<LivroDidatico> findAllOrderedByFalse() {
        this.entityManager = JPAUtil.getEntityManager();

        String queryString = "SELECT l FROM LivroDidatico l WHERE l.disponibilidade = 'F' ORDER BY l.titulo";

        Query query = entityManager.createQuery(queryString);

        List<LivroDidatico> queryResult = query.getResultList();

        return queryResult;
    }

}
