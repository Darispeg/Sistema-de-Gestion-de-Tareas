package com.school.application.model.vistaModel;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProfesorMateria {
    
    private long id;

    private String paralelo; //Solo para vista

    @NotNull
    @Positive
    @Digits(integer = 5, fraction = 0)
    private long idMateria;

    private String materia; //Solo para vista

    @NotNull
    @Positive
    @Digits(integer = 5, fraction = 0)
    private long idProfesor;

    private long ciProfesor; //solo para vista

    private boolean estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public long getCi() {
        return ciProfesor;
    }

    public void setCi(long ciProfesor) {
        this.ciProfesor = ciProfesor;
    }
}
