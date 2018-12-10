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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "turmas")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "TURMA - não pode ser vazio")
    @Size(min = 2, max = 2, message = "TURMA - Somente 2 caracteres")
    @Pattern(regexp = "\\d[A-Za-z]", message = "TURMA - O Primeiro caracter deve ser qualquer número de 0 a 9 e o segundo uma letra entre A e Z")
    //@Column(unique = true)
    private String nome;

    @NotEmpty(message = "DIA DA SEMANA - não pode ser vazio")
    private String dia;

    @NotEmpty(message = "HORÁRIO - nao pode ser vazio")
    private String horario;

    @NotEmpty(message = "PROFESSOR DE PORTUGUÊS - nao pode ser vazio")
    @Size(min = 2, max = 50, message = "PROFESSOR DE PORTUGUÊS - o nome deve ser entre 2 e 50 caracteres")
    @Pattern(regexp = "([A-ZÀ-Ú][a-zà-ú]+\\s?)+", message = "PROFESSOR DE PORTUGUÊS - O nome deve conter somente letras de A-Z e iniciar com maiúscula")
    private String professor;

    @NotEmpty(message = "TURNO - não pode ser vazio")
    private String turno;

    public Turma() {
    }

    public Turma(String nome, String dia, String professor, String horario, String turno) {
        this.nome = nome;
        this.dia = dia;
        this.professor = professor;
        this.horario = horario;
        this.turno = turno;
    }

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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTextByWeekday() {
        switch (dia) {
            case "0":
                return "Segunda-Feira";
            case "1":
                return "Terça-Feira";
            case "2":
                return "Quarta-Feira";
            case "3":
                return "Quinta-Feira";
            default:
                return "Sexta-Feira";
        }
    }

    public String getTextBySchedule() {
        switch (horario) {
            case "0":
                return "Primeiro";
            case "1":
                return "Segundo";
            case "2":
                return "Terceiro";
            case "3":
                return "Quarto";
            default:
                return "Quinto";
        }
    }

    public String getTextByTurn() {
        switch (turno) {
            case "0":
                return "Manha";
            default:
                return "Tarde";
        }
    }

}
