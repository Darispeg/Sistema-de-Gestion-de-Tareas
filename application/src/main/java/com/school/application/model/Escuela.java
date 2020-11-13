package com.school.application.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Escuela {

    private long idEscuela;
    
    @NotBlank
    @NotNull
    @Size(min = 2, max = 60)
    private String nombre;

    @NotBlank
    @NotNull
    private String descripcion;

    @Size(min = 7, max = 13)
    private String telefono;


    @NotBlank
    @NotNull
    @Email
    private String correo;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 40)
    private String contrasena;

    public long getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(long idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
