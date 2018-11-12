/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.query;

import java.util.Date;

/**
 *
 * @author lcluc
 */
public class EmprestimoAluno {

    private String codigoLivro;
    private String nomeLivro;
    private String nomeAluno;
    private String turma;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public EmprestimoAluno() {
    }

    public EmprestimoAluno(String codigoLivro, String nomeLivro, String nomeAluno, String turma, Date dataEmprestimo, Date dataDevolucao) {
        this.codigoLivro = codigoLivro;
        this.nomeLivro = nomeLivro;
        this.nomeAluno = nomeAluno;
        this.turma = turma;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public String getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(String codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataEmprestimo) {
        this.dataDevolucao = dataDevolucao;
    }

}
