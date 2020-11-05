package com.school.application.model;

public class Profesor {

    private long idProfesor;

    private long ci;

    private long idEscuela;

    private String correo;

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
