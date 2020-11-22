package com.school.application.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Profesor {

    private long idProfesor;

    @Positive
    @Digits(integer = 8, fraction = 0)
    private long ci;

    @Positive
    private long idEscuela;



    @NotBlank
    @Email    
    private String correo;

    @NotBlank
    @Size(min = 5, max = 40)
    private String contrasena;

    public long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public long getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(long idEscuela) {
        this.idEscuela = idEscuela;
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
