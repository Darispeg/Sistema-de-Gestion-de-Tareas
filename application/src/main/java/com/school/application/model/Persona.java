package com.school.application.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class Persona {

    //@Size(min = 7, max = 9, message="Por favor introduzca un CI valido")
    private long ci;

    @NotEmpty(message="Este campo no puede estar vacio")
    private String nombre;

    @NotEmpty(message="Este campo no puede estar vacio")
    private String aPaterno;

    @NotEmpty(message="Este campo no puede estar vacio")
    private String aMaterno;

    @Positive(message = "Este campo solo acepta numeros positivos")
    private long edad;

    private String sexo;

    @Size(min = 8, max=12, message = "Exedio el maximo de caracteres permitidos")
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
