/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.EmprestimoDidatico;
import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.util.JPAUtil;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author lcluc
 */
public class EmprestimoDidaticoDAO extends AbstractDAO<Long, EmprestimoDidatico> {

    public List<EmprestimoDidatico> findAllOrderedByIdDesc() {
        this.entityManager = JPAUtil.getEntityManager();

        String queryString = "SELECT e FROM EmprestimoDidatico e ORDER BY e.id DESC";

        Query query = entityManager.createQuery(queryString);

        List<EmprestimoDidatico> queryResult = query.getResultList();

        return queryResult;
    }
}
