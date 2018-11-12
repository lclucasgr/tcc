package br.edu.utfpr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "livros_didaticos")
public class LivroDidatico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String titulo;

    //@NotEmpty
    //@Min(value = 1)
    //@Max(value = 9)
    private String serie;

    //@NotEmpty
    //@Min(value = 2000)
    //@Max(value = 2099)
    private int pnld;

    private String disciplina;

    private String editora;

    private String disponibilidade;

    @Column(unique = true)
    private String codigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getPnld() {
        return pnld;
    }

    public void setPnld(int pnld) {
        this.pnld = pnld;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTextByDisciplina() {
        switch (disciplina) {
            case "0":
                return "Portugues";
            case "1":
                return "Matematica";
            case "2":
                return "Quimica";
            case "3":
                return "Fisica";
            case "4":
                return "Biologia";
            case "5":
                return "Lingua Estrangeira";
            case "6":
                return "Historia";
            case "7":
                return "Ciencias";
            case "8":
                return "Ed. Fisica";
            case "9":
                return "Filosofia";
            case "10":
                return "Sociologia";
            default:
                return "Geografia";
        }
    }

    public String getTextBySerie() {
        switch (serie) {
            case "0":
                return "6º ano";
            case "1":
                return "7º ano";
            case "2":
                return "8º ano";
            case "3":
                return "9º ano";
            case "4":
                return "1º ano";
            case "5":
                return "2º ano";
            default:
                return "3º ano";
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
