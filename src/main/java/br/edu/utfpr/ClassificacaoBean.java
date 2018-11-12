package br.edu.utfpr;

import br.edu.utfpr.model.Classificacao;
import br.edu.utfpr.model.service.ClassificacaoService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "classificacaoBean")
@ViewScoped
public class ClassificacaoBean implements Serializable {

    private Classificacao classificacao;
    private ClassificacaoService classificacaoService;
    private List<Classificacao> classificacaoList;

    public ClassificacaoBean() {

    }

    @PostConstruct
    public void init() {
        classificacao = new Classificacao();
        classificacaoService = new ClassificacaoService();
        classificacaoList = new ArrayList<>();
        findAll();
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setGenero(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public ClassificacaoService getClassificacaoService() {
        return classificacaoService;
    }

    public List<Classificacao> getClassificacaoList() {
        return classificacaoList;
    }

    public void setGeneroList(List<Classificacao> classificacaoList) {
        this.classificacaoList = classificacaoList;
    }

    public void edit(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public void delete(Classificacao classificacao) {

        //System.out.println("Id " + classificacao.getId());
        Classificacao classificacaoOriginal = classificacaoService.getById(classificacao.getId());
        //System.out.println("Genero: " + classificacaoOriginal);
        boolean isSuccess = classificacaoService.delete(classificacaoOriginal);
        //System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.classificacao = new Classificacao();
    }

    public void persist() {
        //System.out.println("Id: " + classificacao.getId());
        if (classificacao.getId() == null) {
            if (classificacaoService.save(classificacao)) {
                findAll();
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            }
        } else {
            Classificacao generoOriginal = classificacaoService.getById(classificacao.getId());
            generoOriginal.setNome(classificacao.getNome());
            generoOriginal.setCor(classificacao.getCor());
            classificacaoService.update(generoOriginal);
            findAll();
        }

        this.classificacao = new Classificacao();
    }

    public void reset() {
        this.classificacao = new Classificacao();
    }

    public List<Classificacao> findAll() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.classificacaoList = classificacaoService.findAll();
        //System.out.println("Tamanho " + classificacaoList.size());
        return classificacaoList;
    }
}
