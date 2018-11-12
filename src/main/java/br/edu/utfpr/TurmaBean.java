package br.edu.utfpr;

import br.edu.utfpr.model.Turma;
import br.edu.utfpr.model.service.TurmaService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "turmaBean")
@ViewScoped
public class TurmaBean implements Serializable {

    private Turma turma;
    private TurmaService turmaService;
    private List<Turma> turmaList;

    public TurmaBean() {

    }

    @PostConstruct
    public void init() {
        turma = new Turma();
        turmaService = new TurmaService();
        turmaList = new ArrayList<>();
        findAll();
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    public TurmaService getTurmaService() {
        return turmaService;
    }

    public void edit(Turma turma) {
        System.out.println("Turma id para editar " + turma.getId());
        this.turma = turma;
    }

    public void delete(Turma turma) {
        System.out.println("Id " + turma.getId());
        Turma turmaOriginal = turmaService.getById(turma.getId());
        System.out.println("Turma: " + turmaOriginal);
        boolean isSuccess = turmaService.delete(turmaOriginal);
        System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.turma = new Turma();
    }

    public void persist() {
        System.out.println("Id: " + turma.getId());
        if (turma.getId() == null) {
            if (turmaService.save(turma)) {
                findAll();
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            }
        } else {
            Turma turmaOriginal = turmaService.getById(turma.getId());
            turmaOriginal.setNome(turma.getNome());
            turmaOriginal.setDia(turma.getDia());
            turmaOriginal.setHorario(turma.getHorario());
            turmaOriginal.setProfessor(turma.getProfessor());
            turmaOriginal.setTurno(turma.getTurno());
            turmaService.update(turmaOriginal);
            findAll();
        }

        this.turma = new Turma();
    }

    public void reset() {
        this.turma = new Turma();
    }

    public List<Turma> findAll() {
        System.out.println("************************ findallllllllllllllllllll");
        this.turmaList = turmaService.findAll();
        System.out.println("Tamanho " + turmaList.size());
        return turmaList;
    }
}
