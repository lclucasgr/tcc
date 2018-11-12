/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.model.dao;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.EmprestimoLiterario;
import br.edu.utfpr.model.LivroLiterario;
import br.edu.utfpr.query.EmprestimoAluno;
import br.edu.utfpr.util.JPAUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Lucas
 */
public class EmprestimoLiterarioDAO extends AbstractDAO<Long, EmprestimoLiterario> {

    public List<EmprestimoLiterario> findAllOrderedByIdDesc() {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();
        String queryString = "SELECT e FROM EmprestimoLiterario e ORDER BY e.id DESC";

        Query query = entityManager.createQuery(queryString);

        List<EmprestimoLiterario> queryResult = query.getResultList();

        return queryResult;
    }

    //usado para relatório
    public List<EmprestimoAluno> findLivrosEmprestados() {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();
        //pega todos os emprestimos ativos
        //PROBLEMA NESSES EMPRESTIMOS ATIVOS PODEM TER LIVROS DEVOLVIDOS QUE ESTAO NA BIBLIOTECA
        //OU EM POSSE DE OUTRO s
        String queryString = "SELECT DISTINCT el FROM EmprestimoLiterario el "
                + "JOIN el.livroLiterarios ll WHERE el.status = :status";

        Query query = entityManager.createQuery(queryString);
        //query.setParameter("status", EmprestimoLiterario.Status.O);
        query.setParameter("status", EmprestimoLiterario.Status.O);

        //retorna os emprestimos
        List<EmprestimoLiterario> queryResult = query.getResultList();

        List<EmprestimoAluno> emprestimoAlunos = new ArrayList<>();
        for (EmprestimoLiterario el : queryResult) {
            Long idAluno = el.getAluno().getId();
            String nomeAluno = el.getAluno().getNome();
            String turmaAluno = el.getAluno().getTurma().getNome();
            Date dataEmprestimo = el.getDataEmprestimo();
            Date dataDevolucao = el.getDataDevolucao();
            //percorre os livros
            for (LivroLiterario livro : el.getLivroLiterarios()) {
                String codigoLivro = livro.getCodigo();
                String nomeLivro = livro.getTitulo();

                if (livro.getDisponibilidade().equals("T")) {
                    continue;
                }

                EmprestimoLiterario emprestimoLiterario = this.getEmprestimoByLivro(livro.getId());
                Long idOutroAluno = emprestimoLiterario.getAluno().getId();

                //emprestado para outro aluno. Portanto, foi devolvido.
                if (idAluno == idOutroAluno) {
                    if (el.getId() == emprestimoLiterario.getId()) {
                        //mesmo emprestimo
                        emprestimoAlunos.add(new EmprestimoAluno(codigoLivro, nomeLivro, nomeAluno, turmaAluno, dataEmprestimo, dataDevolucao));
                    } else if (el.getId() > emprestimoLiterario.getId()) {
                        //emprestimo diferente
                        emprestimoAlunos.add(new EmprestimoAluno(codigoLivro, nomeLivro, nomeAluno, turmaAluno, dataEmprestimo, dataDevolucao));
                    }

                }
            }
        }

        //criar um list de EmprestimoAluno
        return emprestimoAlunos;
    }

    //usado para devolucao
    public EmprestimoLiterario getEmprestimoByLivro(Long idLivro) {
        this.entityManager = JPAUtil.getEntityManager();
        this.entityManager.clear();

        String queryString = "SELECT DISTINCT el FROM EmprestimoLiterario el "
                + "JOIN el.livroLiterarios ll WHERE ll.id = :id ORDER BY el.id DESC";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", idLivro);
        List<EmprestimoLiterario> queryResult = query.getResultList();

        return queryResult.get(0);
    }
}
