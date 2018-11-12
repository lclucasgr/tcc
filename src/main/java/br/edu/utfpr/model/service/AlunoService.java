/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.dao.AlunoDAO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class AlunoService extends AbstractService<Long, Aluno> implements Serializable {

    public AlunoService() {
        dao = new AlunoDAO();
    }

    public List<Aluno> findAllOrderedByName() {
        return ((AlunoDAO) dao).findAllOrderedByName();
    }

}
