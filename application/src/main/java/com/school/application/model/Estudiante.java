package com.school.application.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Estudiante {
    private long idEstudiante;
    
    @Size(min = 7, max = 9, message = "Por favor introduzca un CI valido")
    private long ci;
    
    @NotNull(message = "El Id del Curso es Obligatorio")
    private long idCurso;
    
    @NotEmpty
    @Size(max = 60, message = "Exedio el maximo de caracteres para un Email")
    @Email
    private String correo;
    
    @NotEmpty
    @Size(min = 5, max = 50, message = "Exedio el maximo de caractere para una Contraseña")
    private String contrasena;

    public long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}