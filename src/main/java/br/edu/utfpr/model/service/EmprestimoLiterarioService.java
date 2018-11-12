/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.model.dao.EmprestimoDidaticoDAO;
import br.edu.utfpr.model.dao.EmprestimoLiterarioDAO;
import br.edu.utfpr.query.EmprestimoAluno;
import java.util.List;

/**
 *
 * @author lcluc
 */
public class EmprestimoLiterarioService extends AbstractService<Long, EmprestimoLiterario> {

    public EmprestimoLiterarioService() {
        dao = new EmprestimoLiterarioDAO();
    }

    public List<EmprestimoLiterario> findAllOrderedByIdDesc() {
        return ((EmprestimoLiterarioDAO) dao).findAllOrderedByIdDesc();
    }

    public List<EmprestimoAluno> findLivrosEmprestados() {
        return ((EmprestimoLiterarioDAO) dao).findLivrosEmprestados();
    }

    public EmprestimoLiterario getEmprestimoByLivro(Long idLivro) {
        return ((EmprestimoLiterarioDAO) dao).getEmprestimoByLivro(idLivro);
    }

}
