package com.school.application.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class Grado {

    private long idGrado;

    @NotBlank
    @NonNull
    @Size(min = 1, max = 20)
    private String grado;

    public long getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(long idGrado) {
        this.idGrado = idGrado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}
