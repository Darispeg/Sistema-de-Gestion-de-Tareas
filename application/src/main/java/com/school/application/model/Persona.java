package com.school.application.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;



public class Persona {

    //@Size(min = 7, max = 9, message="Por favor introduzca un CI valido")
    private long ci;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String nombre;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String aPaterno;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String aMaterno;

    @NotNull
    @Positive
    private long edad;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 10)
    private String sexo;

    @Size(min = 7, max = 12)
    private String telefono;

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public long getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
