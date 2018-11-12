package br.edu.utfpr.model;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emprestimos_didaticos")
public class EmprestimoDidatico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<LivroDidatico> livroDidaticos;

    private Date dataEmprestimo;
    private Date dataDevolucao;

    public EmprestimoDidatico() {
        this.livroDidaticos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        this.livroDidaticos = new ArrayList<>();
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

    public List<LivroDidatico> getLivroDidaticos() {
        return livroDidaticos;
    }

    public void setLivroDidaticos(List<LivroDidatico> livroDidaticos) {
        this.livroDidaticos = livroDidaticos;
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

}
