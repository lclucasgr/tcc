/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.model.Classificacao;
import br.edu.utfpr.model.EmprestimoDidatico;
import br.edu.utfpr.model.dao.EmprestimoDidaticoDAO;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class EmprestimoDidaticoService extends AbstractService<Long, EmprestimoDidatico> {

    public EmprestimoDidaticoService() {
        dao = new EmprestimoDidaticoDAO();
    }

    public List<EmprestimoDidatico> findAllOrderedByIdDesc() {
        return ((EmprestimoDidaticoDAO) dao).findAllOrderedByIdDesc();
    }

}
