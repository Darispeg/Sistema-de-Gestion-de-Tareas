package com.school.application.model;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class Tarea {

    private long idTarea;

    @NotNull
    @Positive
    @Digits(integer = 5, fraction = 0)
    private long idCurso;
    
    @NotNull
    @Positive
    @Digits(integer = 5, fraction = 0)
    private long idMateria;

    @NotBlank
    @Size(min = 5, max = 80)
    private String titulo;
    
    @NotBlank
    @Size(min = 5, max = 500)
    private String descripcion;
    
    private Date fechaInicio;
    
    @NotNull
    private Date fechaFinal;
    
    private boolean estado;

    public long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(long idTarea) {
        this.idTarea = idTarea;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
