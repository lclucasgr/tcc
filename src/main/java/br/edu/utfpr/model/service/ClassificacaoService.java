/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.service;

import br.edu.utfpr.model.Classificacao;
import br.edu.utfpr.model.dao.ClassificacaoDAO;

/**
 *
 * @author Lucas
 */
public class ClassificacaoService extends AbstractService<Long, Classificacao>{
    
    public ClassificacaoService()
    {
        dao = new ClassificacaoDAO();
    }
}    
