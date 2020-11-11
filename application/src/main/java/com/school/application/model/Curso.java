package com.school.application.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class Curso {

    private long idCurso;

    @Positive(message = "Este campo solo admite numeros Positivos")
    private long idEscuela;

    @Positive(message = "Este campo solo admite numeros Positivos")
    private long idGrado;

    @Size(min = 2, max = 8, message = "Exedio el numero de caracteres permitidos")
    @NotNull(message = "Este campo no puede ser nulo")
    private String paralelo;

    @Positive(message = "Este campo solo puede ser positivo")
    private long gestion;

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public long getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(long idEscuela) {
        this.idEscuela = idEscuela;
    }

    public long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(long idGrado) {
        this.idGrado = idGrado;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public long getGestion() {
        return gestion;
    }

    public void setGestion(long gestion) {
        this.gestion = gestion;
    }
}
