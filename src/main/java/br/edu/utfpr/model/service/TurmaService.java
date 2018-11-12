/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Turma;
import br.edu.utfpr.model.dao.TurmaDAO;
import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class TurmaService extends AbstractService<Long, Turma> implements Serializable {

    public TurmaService() {
        dao = new TurmaDAO();
    }

}
