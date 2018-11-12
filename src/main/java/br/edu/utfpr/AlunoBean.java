package br.edu.utfpr;

import br.edu.utfpr.model.Aluno;
import br.edu.utfpr.model.Turma;
import br.edu.utfpr.model.service.AlunoService;
import br.edu.utfpr.model.service.TurmaService;
import br.edu.utfpr.util.MessageUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AlunoBean implements Serializable {

    private Long turmaId;
    private Aluno aluno;
    private List<Aluno> alunoList;
    private AlunoService alunoService;
    private TurmaService turmaService;
    private Long alunoId;

    public AlunoBean() {

    }

    @PostConstruct
    public void init() {
        aluno = new Aluno();
        alunoService = new AlunoService();
        turmaService = new TurmaService();
        findAll();
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    public AlunoService getAlunoService() {
        return alunoService;
    }

    public void edit(Aluno aluno) {
        this.aluno = aluno;
    }

    public void delete(Aluno aluno) {
        //System.out.println("Id " + aluno.getId());
        Aluno alunoOriginal = alunoService.getById(aluno.getId());
        //System.out.println("Turma: " + alunoOriginal);
        boolean isSuccess = alunoService.delete(alunoOriginal);
        //System.out.println("Deletado " + isSuccess);
        if (isSuccess) {
            findAll();
            //System.out.println("findall executado ");
            MessageUtil.showMessage("Removido com sucesso", "", FacesMessage.SEVERITY_INFO);
        }
        this.aluno = new Aluno();
    }

    public void persist() {
        //System.out.println("Id: " + aluno.getId());

        if (aluno.getId() == null) {
            Turma turma = turmaService.getById(turmaId);
            aluno.setTurma(turma);
            if (alunoService.save(aluno)) {
                findAll();
                MessageUtil.showMessage("Persistido com sucesso", "", FacesMessage.SEVERITY_INFO);
            }
            //System.out.println("Salvando aluno **********************");
        } else {
            //System.out.println("Atualizando aluno **********************");
            Turma turma = turmaService.getById(turmaId);
            Aluno alunoOriginal = alunoService.getById(aluno.getId());
            alunoOriginal.setMatricula(aluno.getMatricula());
            alunoOriginal.setNome(aluno.getNome());
            alunoOriginal.setEmail(aluno.getEmail());
            alunoOriginal.setCelular(aluno.getCelular());
            alunoOriginal.setMae(aluno.getMae());
            alunoOriginal.setPai(aluno.getPai());
            alunoOriginal.setTurma(turma);
            alunoService.update(alunoOriginal);
            findAll();
        }

        this.aluno = new Aluno();
    }

    public List<Aluno> findAll() {
        //System.out.println("************************ findallllllllllllllllllll");
        this.alunoList = alunoService.findAllOrderedByName();
        //System.out.println("Tamanho " + alunoList.size());
        return alunoList;
    }

    public void getCurrentAluno() {
        System.out.println("***************aLUNO " + alunoId);
        this.aluno = this.alunoService.getById(alunoId);
        System.out.println("***************detalhes " + aluno);
    }

}
