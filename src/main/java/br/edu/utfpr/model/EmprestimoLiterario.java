package br.edu.utfpr.model;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emprestimos_literarios")
public class EmprestimoLiterario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<LivroLiterario> livroLiterarios;

    //c - close / o - open // p - partial
    public enum Status {
        C, O, P
    }

    @Enumerated(EnumType.STRING)
    private Status status = Status.O;

    @NotNull(message = "DATA EMPRESTIMO - Nao pode ser vazia")
    private Date dataEmprestimo;

    @NotNull(message = "DATA DEVOLUCAO - Nao pode ser vazia")
    private Date dataDevolucao;

    public EmprestimoLiterario() {
        this.livroLiterarios = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.livroLiterarios = new ArrayList<>();
    }

    public String formatStatus(Status status) {
        if (status == Status.O) {
            return "Ativo";
        } else {
            return "Finalizado";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<LivroLiterario> getLivroLiterarios() {
        return livroLiterarios;
    }

    public void setLivroLiterarios(List<LivroLiterario> livroLiterarios) {
        this.livroLiterarios = livroLiterarios;
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

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
