/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.LivroLiterario;
import br.edu.utfpr.model.dao.LivroLiterarioDAO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class LivroLiterarioService extends AbstractService<Long, LivroLiterario> implements Serializable {

    public LivroLiterarioService() {
        dao = new LivroLiterarioDAO();
    }

    public List<LivroLiterario> findAllOrderedByTitle() {
        return ((LivroLiterarioDAO) dao).findAllOrderedByTitle();
    }

    public List<LivroLiterario> findAllOrderedByTrue() {
        return ((LivroLiterarioDAO) dao).findAllOrderedByTrue();
    }

    public List<LivroLiterario> findAllOrderedByFalse() {
        return ((LivroLiterarioDAO) dao).findAllOrderedByFalse();
    }
}
