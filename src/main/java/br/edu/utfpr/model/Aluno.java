/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "alunos")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "MATRICULA - Nao pode ser vazio")
    //@Column(unique = true)
    private String matricula;

    @NotEmpty(message = "NOME - Nao pode ser vazio")
    @Size(min = 2, max = 255, message = "NOME - Tamanho entre 2 e 255")
    @Pattern(regexp = "([A-ZÀ-Ú][a-zà-ú]+\\s?)+", message = "NOME - O nome deve conter somente letras de A-Z")
    private String nome;

    @NotEmpty(message = "EMAIL - Nao pode ser vazio")
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "EMAIL - Email invalido")
    @Size(min = 2, max = 255, message = "EMAIL - Tamanho entre 2 e 255")
    private String email;

    private String celular;

    private String mae;

    private String pai;

    @ManyToOne
    private Turma turma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

}
