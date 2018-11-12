package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.LivroDidatico;
import br.edu.utfpr.model.service.AlunoService;
import br.edu.utfpr.model.service.LivroDidaticoService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "livroDidaticoBean")
@ViewScoped
public class LivroDidaticoBean implements Serializable {

    private LivroDidatico livroDidatico;
    private LivroDidaticoService livroDidaticoService;
    private List<LivroDidatico> livroDidaticoList;
    private List<LivroDidatico> livroDidaticoListTrue;
    private List<LivroDidatico> livroDidaticoListFalse;

    public LivroDidaticoBean() {

    }

    @PostConstruct
    public void init() {
        livroDidatico = new LivroDidatico();
        livroDidaticoService = new LivroDidaticoService();
        findAll();
    }

    public LivroDidatico getLivroDidatico() {
        return livroDidatico;
    }

    public void setLivroDidatico(LivroDidatico livroDidatico) {
        this.livroDidatico = livroDidatico;
    }

    public List<LivroDidatico> getLivroDidaticoList() {
        return livroDidaticoList;
    }

    public void setLivroDidaticoList(List<LivroDidatico> livroDidaticoList) {
        this.livroDidaticoList = livroDidaticoList;
    }

    public LivroDidaticoService getLivroDidaticoService() {
        return livroDidaticoService;
    }

    public void edit(LivroDidatico livroDidatico) {
        this.livroDidatico = livroDidatico;
    }

    public void delete(LivroDidatico livroDidatico) {
        //System.out.println("Id " + livroDidatico.getId());
        LivroDidatico livroDidaticoOriginal = livroDidaticoService.getById(livroDidatico.getId());
        //System.out.println("Livro: " + livroDidaticoOriginal);
        boolean isSuccess = livroDidaticoService.delete(livroDidaticoOriginal);
        //System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.livroDidatico = new LivroDidatico();
    }

    public void persist() {
        System.out.println("Id: " + livroDidatico.getId());
        if (livroDidatico.getId() == null) {

            livroDidatico.setDisponibilidade("T");

            if (livroDidaticoService.save(livroDidatico)) {
                findAll();
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            }
        } else {
            LivroDidatico livroDidaticoOriginal = livroDidaticoService.getById(livroDidatico.getId());
            livroDidaticoOriginal.setTitulo(livroDidatico.getTitulo());
            livroDidaticoOriginal.setDisciplina(livroDidatico.getDisciplina());
            livroDidaticoOriginal.setPnld(livroDidatico.getPnld());
            livroDidaticoOriginal.setSerie(livroDidatico.getSerie());
            livroDidaticoOriginal.setEditora(livroDidatico.getEditora());
            livroDidaticoOriginal.setCodigo(livroDidatico.getCodigo());
            livroDidaticoService.update(livroDidaticoOriginal);
            findAll();
        }

        this.livroDidatico = new LivroDidatico();
    }

    public List<LivroDidatico> findAll() {
        System.out.println("************************ findallllllllllllllllllll");
        this.livroDidaticoList = livroDidaticoService.findAll();
        System.out.println("Tamanho " + livroDidaticoList.size());
        return livroDidaticoList;
    }

    public List<LivroDidatico> findAllTrue() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.livroDidaticoListTrue = livroDidaticoService.findAllOrderedByTrue();
        //System.out.println("Tamanho " + livroLiterarioList.size());
        return livroDidaticoListTrue;
    }

    public List<LivroDidatico> findAllFalse() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.livroDidaticoListFalse = livroDidaticoService.findAllOrderedByFalse();
        //System.out.println("Tamanho " + livroLiterarioList.size());
        return livroDidaticoListFalse;
    }

}
