/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.EmprestimoDidatico;
import br.edu.utfpr.model.LivroDidatico;
import br.edu.utfpr.model.service.AlunoService;
import br.edu.utfpr.model.service.EmprestimoDidaticoService;
import br.edu.utfpr.model.service.LivroDidaticoService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author lcluc
 */
@ManagedBean
@SessionScoped
public class EmprestimoDidaticoBean implements Serializable {

    private Long alunoId;
    private Long livroDidaticoId;
    private EmprestimoDidatico emprestimoDidatico;
    private List<EmprestimoDidatico> emprestimoDidaticoList;
    private EmprestimoDidaticoService emprestimoDidaticoService;
    private AlunoService alunoService;
    private LivroDidaticoService livroDidaticoService;
    private Long emprestimoId;

    public EmprestimoDidaticoBean() {

    }

    @PostConstruct
    public void init() {
        emprestimoDidatico = new EmprestimoDidatico();
        emprestimoDidaticoService = new EmprestimoDidaticoService();
        alunoService = new AlunoService();
        livroDidaticoService = new LivroDidaticoService();
        findAll();
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getLivroDidaticoId() {
        return livroDidaticoId;
    }

    public void setLivroDidaticoId(Long livroDidaticoId) {
        this.livroDidaticoId = livroDidaticoId;
    }

    public EmprestimoDidatico getEmprestimoDidatico() {
        return emprestimoDidatico;
    }

    public void setEmprestimoDidatico(EmprestimoDidatico emprestimoDidatico) {
        this.emprestimoDidatico = emprestimoDidatico;
    }

    public List<EmprestimoDidatico> getEmprestimoDidaticoList() {
        return emprestimoDidaticoList;
    }

    public void setEmprestimoDidaticoList(List<EmprestimoDidatico> emprestimoDidaticoList) {
        this.emprestimoDidaticoList = emprestimoDidaticoList;
    }

    public EmprestimoDidaticoService getEmprestimoLivroDidaticoService() {
        return emprestimoDidaticoService;
    }

    public void edit(EmprestimoDidatico emprestimoDidatico) {
        this.emprestimoDidatico = emprestimoDidatico;
    }

    public Long getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public void delete(EmprestimoDidatico emprestimoDidatico) {
        //System.out.println("Id " + emprestimoLivroLiterario.getId());
        EmprestimoDidatico emprestimoDidaticoOriginal = emprestimoDidaticoService.getById(emprestimoDidatico.getId());
        //System.out.println("Turma: " + emprestimoLivroLiterarioOriginal);
        boolean isSuccess = emprestimoDidaticoService.delete(emprestimoDidaticoOriginal);
        System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.emprestimoDidatico = new EmprestimoDidatico();
    }

    public void deleteLivroEmprestimo(LivroDidatico livroDidatico) {

        livroDidatico = livroDidaticoService.getById(livroDidatico.getId());
        livroDidatico.setDisponibilidade("T");
        livroDidaticoService.update(livroDidatico);

        EmprestimoDidatico emprestimoDidatico = emprestimoDidaticoService.getById(emprestimoId);
        //System.out.println("Antes " + livroLiterario.getTitulo());

        int index = -1;
        for (int i = 0; i < emprestimoDidatico.getLivroDidaticos().size(); i++) {
            LivroDidatico l = emprestimoDidatico.getLivroDidaticos().get(i);
            if (l.getId() == livroDidatico.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            emprestimoDidatico.getLivroDidaticos().remove(index);
        }

        System.out.println("Depois");
        for (LivroDidatico l : emprestimoDidatico.getLivroDidaticos()) {
            System.out.println("Livro " + l.getTitulo());
        }

        emprestimoDidaticoService.update(emprestimoDidatico);

        MessageUtil.showMessage("Livro removido com sucesso", "", FacesMessage.SEVERITY_INFO);

    }

    public String persist() {

        if (emprestimoDidatico.getId() == null) {
            Aluno aluno = alunoService.getById(alunoId);

            emprestimoDidatico.setAluno(aluno);

            emprestimoDidaticoService.save(emprestimoDidatico);
            this.emprestimoId = emprestimoDidatico.getId();
            MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            this.emprestimoDidatico = new EmprestimoDidatico();
            findAll();
            return redirectToAddBooks();

//            if () {
//                this.emprestimoId = emprestimoLiterario.getId();
//                findAll();
//                livroLiterario = livroLiterarioService.getById(livroLiterarioId);
//                livroLiterario.setDisponibilidade("F");
//                livroLiterarioService.update(livroLiterario);
//                System.out.println("Livro emprestado: " + livroLiterario.getDisponibilidade() + " " + livroLiterario.getTitulo());
//                redirectToAddBooks();
//
//
//            }
        } else {
            Aluno aluno = alunoService.getById(alunoId);
            LivroDidatico livroDidatico = livroDidaticoService.getById(livroDidaticoId);
            EmprestimoDidatico emprestimoDidaticoOriginal = emprestimoDidaticoService.getById(emprestimoDidatico.getId());
            emprestimoDidaticoOriginal.setDataEmprestimo(emprestimoDidatico.getDataEmprestimo());
            emprestimoDidaticoOriginal.setDataDevolucao(emprestimoDidatico.getDataDevolucao());
            emprestimoDidaticoOriginal.setAluno(aluno);
            emprestimoDidatico.getLivroDidaticos().add(livroDidatico);
            emprestimoDidaticoService.update(emprestimoDidaticoOriginal);
            findAll();
        }

        this.emprestimoDidatico = new EmprestimoDidatico();
        return "";
    }

    public void devolver() {
        LivroDidatico livroDidatico = livroDidaticoService.getById(livroDidaticoId);
        livroDidatico.setDisponibilidade("T");
        livroDidatico = livroDidaticoService.update(livroDidatico);
        System.out.println("Livro devolvido " + livroDidatico.getTitulo() + " Status :" + livroDidatico.getDisponibilidade());

    }

    public void adicionarLivroEmprestimo() {
        //livro
        LivroDidatico livroDidatico = livroDidaticoService.getById(livroDidaticoId);
        livroDidatico.setDisponibilidade("F");
        livroDidatico = livroDidaticoService.update(livroDidatico);
        //emprestimo
        this.emprestimoDidatico = emprestimoDidaticoService.getById(emprestimoId);
        this.emprestimoDidatico.getLivroDidaticos().add(livroDidatico);
        emprestimoDidaticoService.update(emprestimoDidatico);
        System.out.println("Livro adicionado " + livroDidatico.getTitulo());
    }

    public String finalizarAdicaoLivros() {
        this.emprestimoDidatico = new EmprestimoDidatico();
        return "emprestimo-didatico?faces-redirect=true";
    }

    public List<LivroDidatico> listarLivrosEmprestimo() {

        //emprestimo
        this.emprestimoDidatico = emprestimoDidaticoService.getById(emprestimoId);
        return this.emprestimoDidatico.getLivroDidaticos();
    }

    public String redirectToAddBooks() {
        return "add-livros-didaticos";
    }

    public List<EmprestimoDidatico> findAll() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.emprestimoDidaticoList = emprestimoDidaticoService.findAllOrderedByIdDesc();
        //System.out.println("Tamanho " + emprestimoLiterarioList.size());
        return emprestimoDidaticoList;
    }

}
