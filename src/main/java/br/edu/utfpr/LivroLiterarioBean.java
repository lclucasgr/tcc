/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Classificacao;
import br.edu.utfpr.model.LivroLiterario;
import br.edu.utfpr.model.service.AlunoService;
import br.edu.utfpr.model.service.ClassificacaoService;
import br.edu.utfpr.model.service.LivroLiterarioService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LivroLiterarioBean implements Serializable {

    private Long livroId;
    private Long classificacaoId;
    private LivroLiterario livroLiterario;
    private List<LivroLiterario> livroLiterarioListG;
    private List<LivroLiterario> livroLiterarioListTrue;
    private List<LivroLiterario> livroLiterarioListFalse;
    private LivroLiterarioService livroLiterarioService;
    private ClassificacaoService classificacaoService;

    public LivroLiterarioBean() {

    }

    @PostConstruct
    public void init() {
        livroLiterario = new LivroLiterario();
        livroLiterarioService = new LivroLiterarioService();
        classificacaoService = new ClassificacaoService();
        findAll();
    }

    public Long getClassificacaoId() {
        return classificacaoId;
    }

    public void setClassificacaoId(Long classificacaoId) {
        this.classificacaoId = classificacaoId;
    }

    public LivroLiterario getLivroLiterario() {
        return livroLiterario;
    }

    public void setLivroLiterario(LivroLiterario livroLiterario) {
        this.livroLiterario = livroLiterario;
    }

    public List<LivroLiterario> getLivroLiterarioList() {
        return livroLiterarioListG;
    }

    public void setLivroLiterarioList(List<LivroLiterario> livroLiterarioListG) {
        this.livroLiterarioListG = livroLiterarioListG;
    }

    public LivroLiterarioService getLivroLiterarioService() {
        return livroLiterarioService;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public void edit(LivroLiterario livroLiterario) {
        this.livroLiterario = livroLiterario;
    }

    public List<LivroLiterario> getLivroLiterarioListTrue() {
        return livroLiterarioListTrue;
    }

    public void setLivroLiterarioListTrue(List<LivroLiterario> livroLiterarioListTrue) {
        this.livroLiterarioListTrue = livroLiterarioListTrue;
    }

    public List<LivroLiterario> getLivroLiterarioListFalse() {
        return livroLiterarioListFalse;
    }

    public void setLivroLiterarioListFalse(List<LivroLiterario> livroLiterarioListFalse) {
        this.livroLiterarioListFalse = livroLiterarioListFalse;
    }

    public void delete(LivroLiterario livroLiterario) {
        //System.out.println("Id " + livroLiterario.getId());
        LivroLiterario livroLiterarioOriginal = livroLiterarioService.getById(livroLiterario.getId());
        //System.out.println("Livro: " + livroLiterarioOriginal);
        boolean isSuccess = livroLiterarioService.delete(livroLiterarioOriginal);
        //System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.livroLiterario = new LivroLiterario();
    }

    public void persist() {
        //System.out.println("Id: " + livroLiterario.getId());

        if (livroLiterario.getId() == null) {
            Classificacao classificacao = classificacaoService.getById(classificacaoId);
            livroLiterario.setClassificacao(classificacao);
            livroLiterario.setDisponibilidade("T");
            System.out.println("Livro Cadastrado : " + livroLiterario.getDisponibilidade() + " " + livroLiterario.getTitulo());

            if (livroLiterarioService.save(livroLiterario)) {
                findAll();
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            }
            //System.out.println("Salvando aluno **********************");
        } else {
            //System.out.println("Atualizando aluno **********************");
            Classificacao classificacao = classificacaoService.getById(classificacaoId);
            LivroLiterario livroLiterarioOriginal = livroLiterarioService.getById(livroLiterario.getId());
            livroLiterarioOriginal.setTitulo(livroLiterario.getTitulo());
            livroLiterarioOriginal.setAutor(livroLiterario.getAutor());
            livroLiterarioOriginal.setGenero(livroLiterario.getGenero());
            livroLiterarioOriginal.setPaginas(livroLiterario.getPaginas());
            livroLiterarioOriginal.setEdicao(livroLiterario.getEdicao());
            livroLiterarioOriginal.setIsbn(livroLiterario.getIsbn());
            livroLiterarioOriginal.setClassificacao(classificacao);
            livroLiterarioOriginal.setCodigo(livroLiterario.getCodigo());
            livroLiterarioService.update(livroLiterarioOriginal);
            findAll();
        }

        this.livroLiterario = new LivroLiterario();
    }

    public List<LivroLiterario> findAll() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.livroLiterarioListG = livroLiterarioService.findAllOrderedByTitle();
        //System.out.println("Tamanho " + livroLiterarioList.size());
        return livroLiterarioListG;
    }

    public List<LivroLiterario> findAllTrue() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.livroLiterarioListTrue = livroLiterarioService.findAllOrderedByTrue();
        //System.out.println("Tamanho " + livroLiterarioList.size());
        return livroLiterarioListTrue;
    }

    public List<LivroLiterario> findAllFalse() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.livroLiterarioListFalse = livroLiterarioService.findAllOrderedByFalse();
        //System.out.println("Tamanho " + livroLiterarioList.size());
        return livroLiterarioListFalse;
    }

    public void getCurrentLivro() {

        this.livroLiterario = this.livroLiterarioService.getById(livroId);

    }

}
