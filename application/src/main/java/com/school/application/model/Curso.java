package com.school.application.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Curso {

    private long idCurso;

    @Digits(integer = 1, fraction = 1, message = "No puede poner numeros enteros")
    @Positive(message = "Este campo solo admite numeros Positivos")
    private long idEscuela;

    @Digits(integer = 5, fraction = 0, message = "No puede poner numeros enteros")
    @Positive(message = "Este campo solo admite numeros Positivos")
    private long idGrado;

    @Size(min = 2, max = 8, message = "Exedio el numero de caracteres permitidos")
    @NotNull(message = "Este campo no puede ser nulo")
    private String paralelo;

    @Digits(integer = 5, fraction = 0, message = "No puede poner numeros decimales")
    @Positive(message =  "Este campo solo admite numeros Positivos")
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
