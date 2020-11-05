package com.school.application.model;

public class Curso {

    private long idCurso;

    private long idEscuela;

    private long idGrado;

    private String paralelo;

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
