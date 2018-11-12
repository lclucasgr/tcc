/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
public class AlunoDAO extends AbstractDAO<Long, Aluno> {

    public List<Aluno> findAllOrderedByName() {
        this.entityManager = JPAUtil.getEntityManager();

        String queryString = "SELECT a FROM Aluno a ORDER BY a.nome";

        Query query = entityManager.createQuery(queryString);

        List<Aluno> queryResult = query.getResultList();

        return queryResult;
    }

}
